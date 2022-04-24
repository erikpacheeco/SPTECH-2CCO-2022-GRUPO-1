package petfinder.petfinderapi.controladores;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import petfinder.petfinderapi.entidades.Instituicao;
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
import java.util.Objects;
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

    // enum
    ListaObj<String> nivelAcesso = new ListaObj<String>(
        new String[]{
            "SYSADM", "ADM", "PETOPS", "CHATOPS", "USER", "UNSIGNED"
        }
    );

    // endpoints
    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuario() {
        List<Usuario> listaUsuario = usuarioRepository.findAll();
        if (listaUsuario.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(listaUsuario);
    }

    // retorna usuário baseado no ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> getUsuarioById(@PathVariable int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(usuario);
    }

    // cadastro usuário
    @PostMapping
    public ResponseEntity<Object> postUsuario(@RequestBody @Valid Usuario novoUsuario) {
        if (Objects.nonNull(novoUsuario)) {

            // verificando se algum usuário já possui o email fornecido
            if (usuarioRepository.findByEmail(novoUsuario.getEmail()).size() > 0) {
                return ResponseEntity.status(409).body(new Message("Email já em uso."));
            }

            // verificando se nivelAcesso foi especificado corretamente
            if (nivelAcesso.verificaElemento(novoUsuario.getNivelAcesso())) {
                // cadastrando novo usuário
                usuarioRepository.save(novoUsuario);
                return ResponseEntity.status(201).build();
            }
            
            return ResponseEntity.status(400).body(new Message("Nível de usuário inválido."));
        } 

        // bad request
        return ResponseEntity.status(400).build();
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
                if (nivelAcesso.verificaElemento(novoUsuario.getNivelAcesso())) {
                    // atualizando informações do novo usuário
                    novoUsuario.setId(id);
                    usuarioRepository.save(novoUsuario);
                    return ResponseEntity.status(200).build();
                }

                return ResponseEntity.status(400).body(new Message("Nivel de acesso inválido"));
            }

            // email já existente
            return ResponseEntity.status(409).body(new Message("Email já em uso."));
        }

        // usuário não encontrado (pelo id)
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable int id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid UsuarioLogin usuarioLogin) {
        List<Usuario> listaUsuario = usuarioRepository.findByEmailESenha(usuarioLogin.getEmail(), usuarioLogin.getSenha());
        if (listaUsuario.isEmpty()) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.status(200).body(listaUsuario);        
    }

    @GetMapping("/acesso/{fkInstituicao}/{nivelAcessoReq}")
    public ResponseEntity<Object> getUsuarioByNivelAcesso(@PathVariable Integer fkInstituicao, @PathVariable String nivelAcessoReq) {
        nivelAcessoReq = nivelAcessoReq.toLowerCase();

        // verificando se instituicao existe
        if (instituicaoRepositorio.existsById(fkInstituicao)) {

            if (nivelAcesso.verificaElemento(nivelAcessoReq)) {

                
                // lista de usuários da instituicao com o nível de acesso especificado
                List<Usuario> listaUsuario = usuarioRepository.findByNivelAcesso(fkInstituicao, nivelAcessoReq);
                
                // verificando se a lista está vazia
                if (listaUsuario.isEmpty()) {
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
