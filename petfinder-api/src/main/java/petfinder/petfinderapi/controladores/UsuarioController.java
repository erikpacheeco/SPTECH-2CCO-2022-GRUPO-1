package petfinder.petfinderapi.controladores;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import petfinder.petfinderapi.entidades.Demanda;
import petfinder.petfinderapi.entidades.Endereco;
import petfinder.petfinderapi.entidades.Usuario;
import petfinder.petfinderapi.entidades.UsuarioHasInteresse;
import petfinder.petfinderapi.repositorios.*;
import petfinder.petfinderapi.requisicao.InteresseUsuario;
import petfinder.petfinderapi.utilitarios.ListaObj;
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
@CrossOrigin
@Tag(name = "Usuario",description = "API para controlar as ações do usuário")
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

    @Autowired
    private DemandaRepositorio demandaRepository;

    // enums
    ListaObj<String> nivelAcesso = new ListaObj<String>(
        new String[]{
            "SYSADM", "ADM", "PETOPS", "CHATOPS", "USER", "UNSIGNED"
        }
    );

    private ListaObj<String> statusPossiveis = new ListaObj<String>(new String[]{"ABERTO", "CONCLUIDO", "CANCELADO", "DOCUMENTO_VALIDO",
            "PGTO_REALIZADO_USER", "PGTO_REALIZADO_INST", "RESGATE_INVALIDO", "RESGATE_VALIDO", "EM_ANDAMENTO"});

    // endpoints

    // retorna todos os usuarios
    @GetMapping
    @Operation(description = "Endpoint que retorna uma lista com todos os usuários")
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
            UsuarioSemSenha usuarioSemSenha = new UsuarioSemSenha(usuario, usuario.getEndereco());
            usuariosSemSenha.add(usuarioSemSenha);
        });

        // 200
        return ResponseEntity.status(200).body(usuariosSemSenha);
    }

    // retorna usuário baseado no ID
    @GetMapping("/{id}")
    @Operation(description = "Endpoint que retonar um usuario especifico filtrado pelo ID")
    public ResponseEntity<UsuarioSemSenha> getUsuarioById(@PathVariable int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        // verificando se usuário existe
        if (!usuarioRepository.existsById(id)) {

            // 404 usuario não encontrado
            return ResponseEntity.status(404).build();
        }

        // 200 retornando DTO do usuário (sem senha e com endereço completo)
        UsuarioSemSenha usuarioSemSenha = new UsuarioSemSenha(usuario.get(), usuario.get().getEndereco());

        System.out.println(usuarioSemSenha);

        return ResponseEntity.status(200).body(usuarioSemSenha);
    }

    // cadastro usuário

    @PostMapping
    @Operation(description = "Endpoint que cadastra um novo usuário")
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
    @Operation(description = "Endpoint que atualiza as informações de um usuario especifico filtrado pelo ID")
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
    @Operation(description = "Endpoint que deleta um usuario filtrado pelo ID")
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
    @Operation(description = "Endpoint que faz a autenticação e login do usuário")
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
        UsuarioSemSenha usuarioSemSenha = new UsuarioSemSenha(usuario, usuario.getEndereco());

        // 200
        return ResponseEntity.status(200).body(usuarioSemSenha);        
    }

    @DeleteMapping("/autenticacao/{id}")
    @Operation(description = "Endpoint que faz o logoff de um usuario especifico filtrado pelo ID")
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
    @Operation(description = "Endpoint que retorna uma lista de usuários de uma instituição com o mesmo nivel de acesso")
    public ResponseEntity<Object> getUsuarioByNivelAcesso(@PathVariable Integer fkInstituicao,
                                                          @PathVariable String nivelAcessoReq) {
        nivelAcessoReq = nivelAcessoReq.toLowerCase();

        // verificando se instituicao existe
        if (instituicaoRepositorio.existsById(fkInstituicao)) {

            // validando nível de acesso
            if (nivelAcesso.elementoExiste(nivelAcessoReq)) {

                // lista de usuários da instituicao com o nível de acesso especificado
                List<Usuario> listaUsuario = usuarioRepository.findByInstituicaoAndNivelAcesso(fkInstituicao, nivelAcessoReq);

                // verificando se a lista está vazia
                if (listaUsuario.isEmpty()) {

                    // 204 no content
                    return ResponseEntity.status(204).build();
                }

                List<UsuarioSemSenha> usuariosSemSenha = new ArrayList<UsuarioSemSenha>();
                listaUsuario.stream().forEach(usuario -> {
                    UsuarioSemSenha usuarioSemSenha = new UsuarioSemSenha(usuario, usuario.getEndereco());
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

    @GetMapping("/interesse/{idUsuario}")
    @Operation(description = "Endpoint que retorna a lista de interesses de um usuário")
    public ResponseEntity<Object> getUsuarioInteresse(@PathVariable Integer idUsuario) {

        // verificando se o usuario existe
        if (usuarioRepository.existsById(idUsuario)) {
            List<UsuarioHasInteresse> lista = usuarioHasInteresseRepository.findByFkUsuario(idUsuario);

            // verificando se a lista está vazia
            if (lista.isEmpty()) {

                // 204 no content
                return ResponseEntity.status(204).build();
            }

            // 200 - ok
            return ResponseEntity.status(200).body(lista);
        }

        // 404 - instituicao inexistente
        return ResponseEntity.status(404).body(new Message("Usuário não encontrado"));
    }

    @PostMapping("/interesse")
    @Operation(description = "Endpoint que retorna a lista de interesses disponívies para serem usados")
    public ResponseEntity postUsuarioInteresse(@RequestBody @Valid InteresseUsuario novoInteresse) {

        if (!caracteristicaRepository.findById(novoInteresse.getFkCaracteristica()).isPresent() ||
                !usuarioRepository.findById(novoInteresse.getFkUsuario()).isPresent()) {
            //404 - Not found
            return ResponseEntity.status(404).build();
        }

        UsuarioHasInteresse usuarioHasInteresse = new UsuarioHasInteresse();

        usuarioHasInteresse.setFkCaracteristica(caracteristicaRepository.getById(novoInteresse.getFkCaracteristica()));
        usuarioHasInteresse.setFkUsuario(usuarioRepository.getById(novoInteresse.getFkUsuario()));

        usuarioHasInteresseRepository.save(usuarioHasInteresse);

        // 201 interesse criado
        return ResponseEntity.status(201).body(usuarioHasInteresse);

    }

    // cadastra endereco
    @PostMapping("/endereco")
    @Operation(description = "Endpoint que cadastra o endereço de um usuário")
    public ResponseEntity<Object> postEndereco(@RequestBody @Valid Endereco novoEndereco) {
        List<Endereco> lista = enderecoRepository.findAll();

        if (lista.contains(novoEndereco)) {
            // 409 - Conflict
            return ResponseEntity.status(409).build();
        }

        enderecoRepository.save(novoEndereco);

        // 201 - Endereco criado
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/pontucacao/{idUsuario}")
    @Operation(description = "Endpoint que retorna a pontuação de um usuário")
    public ResponseEntity getPontuacao(@PathVariable int idUsuario) {

        if (usuarioRepository.existsById(idUsuario)) {

            List<Demanda> listaDemandasAll = demandaRepository.findAllByUsuarioId(idUsuario);
            List<Demanda> listaDemandasAberta = demandaRepository.findAllByUsuarioIdAndStatus(idUsuario, "ABERTO");
            List<Demanda> listaDemandasAndamento = demandaRepository.findAllByUsuarioIdAndStatus(idUsuario, "EM_ANDAMENTO");
            List<Demanda> listaDemandasConcluida = demandaRepository.findAllByUsuarioIdAndStatus(idUsuario, "CONCLUIDO");
            List<Demanda> listaDemandasCancelada = demandaRepository.findAllByUsuarioIdAndStatus(idUsuario, "CANCELADO");
            List<Demanda> listaDemandasPagamento = demandaRepository.findAllByUsuarioIdAndStatus(idUsuario, "PGTO_REALIZADO_USER");
            List<Demanda> listaDemandasResValido = demandaRepository.findAllByUsuarioIdAndStatus(idUsuario, "RESGATE_VALIDO");
            List<Demanda> listaDemandasResInvalido = demandaRepository.findAllByUsuarioIdAndStatus(idUsuario, "RESGATE_INVALIDO");
            List<Demanda> listaDemandasDocValido = demandaRepository.findAllByUsuarioIdAndStatus(idUsuario, "DOCUMENTO_VALIDO");

            Double pontos = 1000.0;

            // diminuindo pontos
            if (listaDemandasAberta.size() > (listaDemandasAll.size() * 0.3)) {
                pontos -= 10.0;
            }

            if (listaDemandasAndamento.size() < (listaDemandasAberta.size() * 0.3)) {
                pontos -= 10.0;
            }

            if (listaDemandasCancelada.size() > (listaDemandasConcluida.size() * 0.3)) {
                pontos -= 10.0;
            }

            if (listaDemandasResInvalido.size() > listaDemandasResValido.size()) {
                pontos -= 10.0;
            }

            // aumentando pontos
            if (listaDemandasPagamento.size() > (listaDemandasAll.size() * 0.3)) {
                pontos += 10.0;
            }

            if (listaDemandasDocValido.size() > (listaDemandasAll.size() * 0.3)) {
                pontos += 10.0;
            }

            if (listaDemandasConcluida.size() > (listaDemandasAll.size() * 0.3)) {
                pontos += 10.0;
            }

            return ResponseEntity.status(200).body(pontos);
        }
        return ResponseEntity.status(404).build();

    }

}
