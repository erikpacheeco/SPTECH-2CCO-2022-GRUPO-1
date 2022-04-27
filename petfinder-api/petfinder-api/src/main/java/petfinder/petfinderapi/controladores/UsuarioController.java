package petfinder.petfinderapi.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import petfinder.petfinderapi.entidades.Endereco;
import petfinder.petfinderapi.entidades.Usuario;
import petfinder.petfinderapi.utilitarios.ListaObj;
import petfinder.petfinderapi.repositorios.CaracteristicaRepositorio;
import petfinder.petfinderapi.repositorios.EnderecoRepositorio;
import petfinder.petfinderapi.repositorios.InstituicaoRepositorio;
import petfinder.petfinderapi.repositorios.UsuarioHasInteresseRepositorio;
import petfinder.petfinderapi.repositorios.UsuarioRepositorio;
import petfinder.petfinderapi.requisicao.UsuarioLogin;
import petfinder.petfinderapi.resposta.Message;
import petfinder.petfinderapi.resposta.UsuarioSemSenha;

import java.util.ArrayList;
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

    // enums
    ListaObj<String> nivelAcesso = new ListaObj<String>(
        new String[]{
            "SYSADM", "ADM", "PETOPS", "CHATOPS", "USER", "UNSIGNED"
        }
    );

    // endpoints

    // retorna todos os usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioSemSenha>> getUsuario() {
        List<Usuario> listaUsuario = usuarioRepository.findAll();

        // verificando se lista de usuários está vazia
        if (listaUsuario.isEmpty()) {

            // 204 no content
            return ResponseEntity.status(204).build();
        }

        // exibindo DTO do usuário (sem a senha e com o endereço completo)
        List<UsuarioSemSenha> usuariosSemSenha = new ArrayList<UsuarioSemSenha>();

        listaUsuario.stream().forEach(usuario -> {
            UsuarioSemSenha usuarioSemSenha = new UsuarioSemSenha(usuario, getEnderecoById(usuario.getFkEndereco()));
            usuariosSemSenha.add(usuarioSemSenha);
        });

        // 200
        return ResponseEntity.status(200).body(usuariosSemSenha);
    }

    // retorna usuário baseado no ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioSemSenha> getUsuarioById(@PathVariable int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        // verificando se usuário existe
        if (!usuarioRepository.existsById(id)) {

            // 404 usuario não encontrado
            return ResponseEntity.status(404).build();
        }

        // 200 retornando DTO do usuário (sem senha e com endereço completo)
        UsuarioSemSenha usuarioSemSenha = new UsuarioSemSenha(usuario.get(), getEnderecoById(usuario.get().getFkEndereco()));

        System.out.println(usuarioSemSenha);

        return ResponseEntity.status(200).body(usuarioSemSenha);
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
                    novoUsuario.setLogado(usuarioAtual.isLogado());
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

    @PostMapping("/autenticacao")
    public ResponseEntity<Object> login(@RequestBody @Valid UsuarioLogin usuarioLogin) {

        // verificando se usuário existe
        List<Usuario> listaUsuario = usuarioRepository.findByEmailESenha(usuarioLogin.getEmail(), usuarioLogin.getSenha());

        if (listaUsuario.isEmpty()) {
            
            // 401 falha na autenticação
            return ResponseEntity.status(401).build();
        }

        // usuário existente
        Usuario usuario = listaUsuario.get(0);

        // autenticando usuário
        usuario.setLogado(true);
        usuarioRepository.save(usuario);

        // retornando DTO do usuário (sem senha e com endereço completo)
        UsuarioSemSenha usuarioSemSenha = new UsuarioSemSenha(usuario, getEnderecoById(usuario.getFkEndereco()));

        // 200
        return ResponseEntity.status(200).body(usuarioSemSenha);        
    }

    @DeleteMapping("/autenticacao/{id}")
    public ResponseEntity<Object> deleteLogoff(@PathVariable int id) {

        // verificando se usuário existe
        if (usuarioRepository.existsById(id)) {
            Usuario usuario = usuarioRepository.findById(id).get();
            
            // usuário já está deslogado
            if (!usuario.isLogado()) {

                // usuário já estava logado
                return ResponseEntity.status(202).body(new Message("Usuário já se encontra deslogado."));
            }

            // deslogando usuário
            usuario.setLogado(false);
            usuarioRepository.save(usuario);

            // 200 - usuário deslogado com sucesso
            return ResponseEntity.status(200).body(new Message("Usuário deslogado com sucesso"));
        }

        // 404 - usuário não encontrado
        return ResponseEntity.status(404).body(new Message("Usuário não encontrado"));

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

                List<UsuarioSemSenha> usuariosSemSenha = new ArrayList<UsuarioSemSenha>();
                listaUsuario.stream().forEach(usuario -> {
                    UsuarioSemSenha usuarioSemSenha = new UsuarioSemSenha(usuario, getEnderecoById(usuario.getFkEndereco()));
                    usuariosSemSenha.add(usuarioSemSenha);
                });
                
                // 200 - ok
                return ResponseEntity.status(200).body(usuariosSemSenha);
                
            }
            
            // 400 - nível de acesso inválido
            return ResponseEntity.status(404).body(new Message("Nível de acesso inválido"));
        }
        // 404 - instituicao inexistente
        return ResponseEntity.status(404).body(new Message("Instituicao inexistente"));
    }

    // retorna endereco baseado no id
    private Endereco getEnderecoById(Integer id) {

        // se id for null, retorna null
        if (Objects.nonNull(id)) {
            Optional<Endereco> endereco = enderecoRepository.findById(id);

            // se endereco existe, retorna endereco
            if (endereco.isPresent()) {
                return endereco.get();
            }

            // endereço inexistente no banco
            return null;
        }

        return null;
    }
}
