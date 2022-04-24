package petfinder.petfinderapi.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petfinder.petfinderapi.entidades.Usuario;
import petfinder.petfinderapi.listaObj.ListaObj;
import petfinder.petfinderapi.repositorios.CaracteristicaRepositorio;
import petfinder.petfinderapi.repositorios.EnderecoRepositorio;
import petfinder.petfinderapi.repositorios.InstituicaoRepositorio;
import petfinder.petfinderapi.repositorios.UsuarioHasInteresseRepositorio;
import petfinder.petfinderapi.repositorios.UsuarioRepositorio;
import petfinder.petfinderapi.requisicao.UsuarioLogin;
import petfinder.petfinderapi.resposta.Message;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    // repositorios
    @Autowired
    private UsuarioRepositorio usuarioRepository;

    @Autowired
    private EnderecoRepositorio enderecoRepository;

    @Autowired
    private CaracteristicaRepositorio caracteristicaRepository;

    @Autowired
    private UsuarioHasInteresseRepositorio usuarioHasInteresseRepository;

    @Autowired
    private InstituicaoRepositorio instituicaoRepositorio;

    // enums
    ListaObj<String> nivelAcesso = new ListaObj<String>(
        new String[]{
            "SYSADM", "ADM", "PETOPS", "CHATOPS", "USER", "UNSIGNED"
        }
    );

    // endpoints

    // retorna todos os usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuario() {
        List<Usuario> listaUsuario = usuarioRepository.findAll();

        // verificando se lista de usuários está vazia
        if (listaUsuario.isEmpty()) {

            // 204 no content
            return ResponseEntity.status(204).build();
        }

        // 200
        return ResponseEntity.status(200).body(listaUsuario);
    }

    // retorna usuário baseado no ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> getUsuarioById(@PathVariable int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        // verificando se usuário existe
        if (!usuarioRepository.existsById(id)) {

            // 404 usuario não encontrado
            return ResponseEntity.status(404).build();
        }

        // 200
        return ResponseEntity.status(200).body(usuario);
    }

    // cadastro usuário
    @PostMapping
    public ResponseEntity<Object> postUsuario(@RequestBody @Valid Usuario novoUsuario) {

        // verificando se algum usuário já possui o email fornecido
        if (usuarioRepository.findByEmail(novoUsuario.getEmail()).size() > 0) {
            return ResponseEntity.status(409).body(new Message("Email já em uso."));
        }

        // verificando se nivelAcesso foi especificado corretamente
        if (nivelAcesso.elementoExiste(novoUsuario.getNivelAcesso())) {
            // cadastrando novo usuário
            usuarioRepository.save(novoUsuario);
            return ResponseEntity.status(201).build();
        }
        
        // 400 bad request - nível de acesso inválido
        return ResponseEntity.status(400).body(new Message("Nível de acesso do usuário inválido."));
    }

    // atualizando informações do usuário
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUsuario(@PathVariable int id, @RequestBody @Valid Usuario novoUsuario) {

        // verificando se usuário existe
        if (usuarioRepository.existsById(id)) {

            // pegando informações do usuário existente
            Usuario usuarioAtual = usuarioRepository.findById(id).get();

            // verificando se outro usuário já possui novo email
            if (usuarioAtual.getEmail().equals(novoUsuario.getEmail()) || usuarioRepository.findByEmail(novoUsuario.getEmail()).size() ==  0) {

                // verificando se nivel acesso está válido
                if (nivelAcesso.elementoExiste(novoUsuario.getNivelAcesso())) {

                    // atualizando informações do novo usuário
                    novoUsuario.setId(id);
                    usuarioRepository.save(novoUsuario);

                    // 200
                    return ResponseEntity.status(200).build();
                }

                // 400 bad request - nível de acesso inválido
                return ResponseEntity.status(400).body(new Message("Nivel de acesso inválido"));
            }

            // 409 email já existe
            return ResponseEntity.status(409).body(new Message("Email já em uso."));
        }

        // 404 usuário não encontrado
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable int id) {

        // verificando se usuário existe
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);

            // 200
            return ResponseEntity.status(200).build();
        } 

        // 404 usuário não encontrado
        return ResponseEntity.status(404).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid UsuarioLogin usuarioLogin) {

        // verificando se usuário existe
        List<Usuario> listaUsuario = usuarioRepository.findByEmailESenha(usuarioLogin.getEmail(), usuarioLogin.getSenha());

        if (listaUsuario.isEmpty()) {
            
            // 401 falha na autenticação
            return ResponseEntity.status(401).build();
        }

        // 200 autenticado
        return ResponseEntity.status(200).body(listaUsuario);        
    }

    @GetMapping("/acesso/{fkInstituicao}/{nivelAcessoReq}")
    public ResponseEntity<Object> getUsuarioByNivelAcesso(@PathVariable Integer fkInstituicao, @PathVariable String nivelAcessoReq) {
        nivelAcessoReq = nivelAcessoReq.toLowerCase();

        // verificando se instituicao existe
        if (instituicaoRepositorio.existsById(fkInstituicao)) {

            // validando nível de acesso
            if (nivelAcesso.elementoExiste(nivelAcessoReq)) {
                
                // lista de usuários da instituicao com o nível de acesso especificado
                List<Usuario> listaUsuario = usuarioRepository.findByNivelAcesso(fkInstituicao, nivelAcessoReq);
                
                // verificando se a lista está vazia
                if (listaUsuario.isEmpty()) {

                    // 204 no content
                    return ResponseEntity.status(204).build();
                }
                
                // 200 - ok
                return ResponseEntity.status(200).body(listaUsuario);
            }
            
            // 400 - nível de acesso inválido
            return ResponseEntity.status(404).body(new Message("Nível de acesso inválido"));
        }
        // 404 - instituicao inexistente
        return ResponseEntity.status(404).body(new Message("Instituicao inexistente"));
    }
}
