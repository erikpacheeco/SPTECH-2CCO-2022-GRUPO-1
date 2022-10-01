package petfinder.petfinderapi.controladores;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petfinder.petfinderapi.controladores.util.HeaderConfig;
import petfinder.petfinderapi.entidades.*;
import petfinder.petfinderapi.repositorios.*;
import petfinder.petfinderapi.requisicao.CriacaoUsuario;
import petfinder.petfinderapi.requisicao.DtoColaboradorRequest;
import petfinder.petfinderapi.requisicao.DtoSysadmRequest;
import petfinder.petfinderapi.requisicao.InteresseUsuario;
import petfinder.petfinderapi.utilitarios.FilaObj;
import petfinder.petfinderapi.utilitarios.ListaObj;
import petfinder.petfinderapi.requisicao.UsuarioLogin;
import petfinder.petfinderapi.resposta.ColaboradorSimples;
import petfinder.petfinderapi.resposta.Message;
import petfinder.petfinderapi.resposta.SysadmSimples;
import petfinder.petfinderapi.resposta.UsuarioSemSenha;
import petfinder.petfinderapi.service.ServiceUsuario;
import java.util.*;
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

    @Autowired
    private VisitantesRepositorio visitantesRepository;

    @Autowired
    private LeadsRepositorio leadsRepository;

    @Autowired
    private ClientesRepositorio clientesRepository;

    @Autowired
    private ServiceUsuario serviceUsuario;

    // enums
    ListaObj<String> nivelAcesso = new ListaObj<String>(
        new String[]{
            "SYSADM", "ADM", "PETOPS", "CHATOPS", "USER", "UNSIGNED"
        }
    );

    // endpoints

    @PostMapping("/sysadm")
    @Operation(description = "retorna colaboradores de uma instituicao")
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<SysadmSimples> postSysadm(@RequestBody @Valid DtoSysadmRequest dto) {
        SysadmSimples body = serviceUsuario.postSysadm(dto);
        return ResponseEntity.created(HeaderConfig.getLocation(body.getId())).body(body);
    }

    @PostMapping("/colaborador")
    @Operation(description = "retorna colaboradores de uma instituicao")
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    @ApiResponse(responseCode = "409", description = "Conflict", content = @Content)
    public ResponseEntity<ColaboradorSimples> postColaborador(@RequestBody @Valid DtoColaboradorRequest dto) {
        ColaboradorSimples body = serviceUsuario.postColaborador(dto);
        return ResponseEntity.created(HeaderConfig.getLocation(body.getId())).body(body);
    }

    @GetMapping("/por-instituicao/{id}")
    @Operation(description = "retorna colaboradores de uma instituicao")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "204", description = "No Content", content = @Content)
    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    public ResponseEntity<List<ColaboradorSimples>> getColaboradorByInstituicao(@PathVariable int id, @RequestParam(required = false) String categoria) {
        return ResponseEntity.ok(serviceUsuario.getColaboradorByInstituicaoId(id, categoria));
    }

    @GetMapping("/nivel-acesso/{nivelAcesso}")
    @Operation(description = "retorna usuario com um determinado nível de acesso")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "204", description = "No Content", content = @Content)
    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    public ResponseEntity<List<UsuarioSemSenha>> getUsuarioByNivelAcesso(@PathVariable String nivelAcesso) {
        return ResponseEntity.ok(serviceUsuario.getUsuarioByNivelAcesso(nivelAcesso));
    }

    @GetMapping("/padrinhos/{idInstituicao}")
    @Operation(description = "retorna lista de padrinhos de uma instituicao")
    @ApiResponse(responseCode = "200", description = "Ok")
    @ApiResponse(responseCode = "204", description = "No Content", content = @Content)
    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    public ResponseEntity<List<UsuarioSemSenha>> getPadrinhos(@PathVariable Integer idInstituicao) {
        return ResponseEntity.ok(serviceUsuario.getPadrinhos(idInstituicao));
    }

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
    @Operation(description = "Endpoint que retona um usuario especifico filtrado pelo ID")
    public ResponseEntity<UsuarioSemSenha> getUsuarioById(@PathVariable int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        // verificando se usuário existe
        if (!usuarioRepository.existsById(id)) {

            // 404 usuario não encontrado
            return ResponseEntity.status(404).build();
        }

        // 200 retornando DTO do usuário (sem senha e com endereço completo)
        UsuarioSemSenha usuarioSemSenha = new UsuarioSemSenha(usuario.get(), usuario.get().getEndereco());

        return ResponseEntity.status(200).body(usuarioSemSenha);
    }

    // cadastro usuário

    @PostMapping
    @Operation(description = "Endpoint que cadastra um novo usuário")
    public ResponseEntity<UsuarioSemSenha> postUsuario(@RequestBody @Valid CriacaoUsuario criacaoUsuario) {

        Usuario novoUsuario = criacaoUsuario.getUsuario();              

        // verificando se algum usuário já possui o email fornecido
        if (usuarioRepository.findByEmail(novoUsuario.getEmail()).size() > 0) {
            return ResponseEntity.status(409).build();
        }

        // verificando se nivelAcesso foi especificado corretamente
        if (nivelAcesso.elementoExiste(novoUsuario.getNivelAcesso())) {

            // cadastrando endereco do novo usuário
            Endereco endereco = enderecoRepository.save(novoUsuario.getEndereco());
            
            // cadastrando novo usuário
            novoUsuario.getEndereco().setId(endereco.getId());;
            novoUsuario = usuarioRepository.save(novoUsuario);
            UsuarioSemSenha res = new UsuarioSemSenha(novoUsuario, novoUsuario.getEndereco());

            // cadastrando interesses
            FilaObj<Caracteristica> fila = new FilaObj<Caracteristica>(criacaoUsuario.getInteresses());
            
            while (fila.isNotEmpty()) {
                // Validando interesses
                Caracteristica caracteristica = caracteristicaRepository.findByCaracteristicas(fila.poll().getCaracteristica());

                // relacionando interesse (Caracteristica) ao usuário
                if (Objects.nonNull(caracteristica)) {
                    UsuarioHasInteresse relation = new UsuarioHasInteresse();
                    relation.setCaracteristica(caracteristica);
                    relation.setUsuario(novoUsuario);
                    usuarioHasInteresseRepository.save(relation);
                }
            }

            // 201 usuário criado
            return ResponseEntity.status(201).body(res);
        }

        // 400 bad request - nível de acesso inválido
        return ResponseEntity.status(400).build();
    }

    // atualizando informações do usuário
    @PutMapping("/{id}")
    @Operation(description = "Endpoint que atualiza as informações de um usuario especifico filtrado pelo ID")
    public ResponseEntity<UsuarioSemSenha> updateUsuario(@PathVariable int id, @RequestBody @Valid UsuarioSemSenha novoUsuario) {

        // verificando se usuário existe
        if (usuarioRepository.existsById(id)) {

            // pegando informações do usuário existente
            Usuario usuarioAtual = usuarioRepository.findById(id).get();

            // verificando se outro usuário já possui novo email
            if (usuarioAtual.getEmail().equals(novoUsuario.getEmail()) || usuarioRepository.findByEmail(novoUsuario.getEmail()).size() ==  0) {
 
                // verificando se nivel acesso está válido
                if (nivelAcesso.elementoExiste(novoUsuario.getNivelAcesso())) {

                    // atualizando informações do novo usuário
                    usuarioAtual.setNome(novoUsuario.getNome());
                    usuarioAtual.setNivelAcesso(novoUsuario.getNivelAcesso());

                    usuarioRepository.save(usuarioAtual);

                    // 200
                    return ResponseEntity.status(200).body(new UsuarioSemSenha(novoUsuario, usuarioAtual.getEndereco()));
                }

                // 400 bad request - nível de acesso inválido
                return ResponseEntity.status(400).build();
            }

            // 409 email já existe
            return ResponseEntity.status(409).build();
        }

        // 404 usuário não encontrado
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Endpoint que deleta um usuario filtrado pelo ID")
    public ResponseEntity<Object> deleteUsuario(@PathVariable int id) {

        // verificando se usuário existe
        if (usuarioRepository.existsById(id)) {

            List<UsuarioHasInteresse> interesses = caracteristicaRepository.findInteressesByUserId(id);

            for (UsuarioHasInteresse i : interesses) {
                usuarioHasInteresseRepository.deleteById(i.getId());
            }

            usuarioRepository.deleteById(id);

            // 200
            return ResponseEntity.status(200).build();
        } 

        // 404 usuário não encontrado
        return ResponseEntity.status(404).build();
    }

    @PostMapping("/autenticacao")
    @Operation(description = "Endpoint que faz a autenticação e login do usuário")
    public ResponseEntity<UsuarioSemSenha> login(@RequestBody @Valid UsuarioLogin usuarioLogin) {

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
    public ResponseEntity<List<UsuarioHasInteresse>> getUsuarioInteresse(@PathVariable Integer idUsuario) {

        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        // verificando se o usuario existe
        if (usuario.isPresent()) {

            // nível de acesso diferente de USER
            if (!usuario.get().getNivelAcesso().equalsIgnoreCase("USER")) {
                return ResponseEntity.badRequest().build();
            }

            List<UsuarioHasInteresse> lista = caracteristicaRepository.findInteressesByUserId(idUsuario);

            // verificando se a lista está vazia
            if (lista.isEmpty()) {

                // 204 no content
                return ResponseEntity.status(204).build();
            }

            // 200 - ok
            return ResponseEntity.status(200).body(lista);
        }

        // 404 - usuário inexistente
        return ResponseEntity.status(404).build();
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

        usuarioHasInteresse.setCaracteristica(caracteristicaRepository.getById(novoInteresse.getFkCaracteristica()));
        usuarioHasInteresse.setUsuario(usuarioRepository.getById(novoInteresse.getFkUsuario()));

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

    @PostMapping("/usuario-resgate")
    public ResponseEntity postUsuarioResgate(@RequestBody Endereco novoEndereco) {

        if (novoEndereco == null) {
            return ResponseEntity.status(404).build();
        }
        enderecoRepository.save(novoEndereco);

        Usuario usuario = new Usuario();
        usuario.setNome("UNSIGNED");
        usuario.setEmail("");
        usuario.setSenha("UNSIGNED");
        usuario.setNivelAcesso("UNSIGNED");
        usuario.setEndereco(novoEndereco);
        usuario.setLogado(false);
        usuarioRepository.save(usuario);

        return ResponseEntity.status(200).body(usuario);
    }

    @GetMapping("/ultimo-visitante")
    @Operation(description = "Endpoint para pegar ultimo id visitante")
    public ResponseEntity getUltimoUsuarioVisitante() {

        long ultimoId = visitantesRepository.count();

        return ResponseEntity.status(201).body(ultimoId);
    }

    @PostMapping("/visitante")
    @Operation(description = "Endpoint para inserir novo visitante")
    public ResponseEntity postUsuarioVisitante(@RequestBody Visitantes novoVisitante) {

        if (novoVisitante.getDataVisita() == null) {
            return ResponseEntity.status(404).build();
        }

        visitantesRepository.save(novoVisitante);

        return ResponseEntity.status(201).build();
    }

    @GetMapping("/ultimo-lead")
    @Operation(description = "Endpoint para pegar ultimo id lead")
    public ResponseEntity getUltimoUsuarioLead() {

        long ultimoId = leadsRepository.count();

        return ResponseEntity.status(201).body(ultimoId);
    }

    @PostMapping("/lead")
    @Operation(description = "Endpoint para inserir novo lead")
    public ResponseEntity postUsuarioLead(@RequestBody Leads novoLead) {

        if (novoLead.getDataCadastro() == null) {
            return ResponseEntity.status(404).build();
        }

        leadsRepository.save(novoLead);

        return ResponseEntity.status(201).build();
    }

    @GetMapping("/ultimo-usuario-cadastrado")
    @Operation(description = "Endpoint para pegar ultimo id usuario cadastrado")
    public ResponseEntity getUltimoUsuarioCadastrado() {

        long ultimoId = usuarioRepository.count();

        return ResponseEntity.status(201).body(ultimoId);
    }

    @GetMapping("/ultimo-cliente")
    @Operation(description = "Endpoint para pegar ultimo id cliente")
    public ResponseEntity getUltimoUsuarioCliente() {

        long ultimoId = clientesRepository.count();

        return ResponseEntity.status(201).body(ultimoId);
    }

    @PostMapping("/cliente")
    @Operation(description = "Endpoint para inserir novo cliente")
    public ResponseEntity postUsuarioCliente(@RequestBody Clientes novoCliente) {

        if (novoCliente.getDataCliente() == null) {
            return ResponseEntity.status(404).build();
        }

        clientesRepository.save(novoCliente);

        return ResponseEntity.status(201).build();
    }
}
