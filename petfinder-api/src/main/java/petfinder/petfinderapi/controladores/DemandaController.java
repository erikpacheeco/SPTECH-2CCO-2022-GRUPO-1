package petfinder.petfinderapi.controladores;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petfinder.petfinderapi.entidades.*;
import petfinder.petfinderapi.repositorios.*;
import petfinder.petfinderapi.resposta.*;
import petfinder.petfinderapi.service.ServiceDashboardAdmBI;
import petfinder.petfinderapi.service.ServiceDashboardChatOpsBI;
import petfinder.petfinderapi.service.ServiceDashboardSysadmBI;
import petfinder.petfinderapi.utilitarios.GerenciadorArquivos;
import petfinder.petfinderapi.utilitarios.ListaObj;
import petfinder.petfinderapi.requisicao.CriacaoDemanda;
import petfinder.petfinderapi.requisicao.DtoPatchDemanda;
import petfinder.petfinderapi.service.DemandaService;
import javax.validation.Valid;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/demandas")
@CrossOrigin
@Tag(name = "Demanda",description = "Essa API é utilizada para controlar as transações do sistema")
public class DemandaController implements GerenciadorArquivos{

    // repositorios
    @Autowired
    private DemandaRepositorio demandaRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private InstituicaoRepositorio instituicaoRepositorio;

    @Autowired
    private PetRepositorio petRepositorio;

    @Autowired
    private DemandaHistRepository demandaHistRepository;

    @Autowired
    private MensagemRepository mensagemRepository;

    @Autowired
    private DemandaService service;

    @Autowired
    private ServiceDashboardSysadmBI serviceDashboardSysadmBI;

    @Autowired
    private ServiceDashboardAdmBI serviceDashboardAdmBI;

    @Autowired
    private ServiceDashboardChatOpsBI serviceDashboardChatOpsBI;

    @Autowired
    private PremioRepositorio premioRepositorio;


    // enums
    private ListaObj<String> categoriasPossiveis = new ListaObj<String>(new String[]{"ADOCAO", "PAGAMENTO", "RESGATE"});
    private ListaObj<String> statusPossiveis = new ListaObj<String>(new String[]{"ABERTO", "CONCLUIDO", "AGUARDANDO_VALIDACAO_DOCUMENTO", "DOCUMENTO_VALIDO", "PGTO_REALIZADO_USER", "PGTO_REALIZADO_INST", "RESGATE_INVALIDO", "RESGATE_VALIDO", "EM_ANDAMENTO", "CANCELADO"});

    private ListaObj<String> tiposMenssagensPossiveis = new ListaObj<String>(new String[]{"MENSAGEM", "ARQ_DOCUMENTO", "ARQ_IMAGEM"});

    // endpoints

    @GetMapping("/{demandaId}/historico")
    @Operation(description = "Endpoint que retorna uma lista do hisórico de alguma demanda")
    public ResponseEntity<List<DtoDemandaHist>> getHistDemanda(@PathVariable int demandaId){
        return ResponseEntity.ok(service.getHistDemandas(demandaId));
    }

    @PatchMapping("/status/{idDemanda}")
    @Operation(description = "Endpoint para atualizar o status de uma demanda especifica pelo ID")
    public ResponseEntity<DtoDemanda> patchDemandaStatus(@PathVariable int idDemanda, @RequestBody @Valid DtoPatchDemanda dto) {
        return ResponseEntity.ok(service.patchDemandaStatus(idDemanda, dto));
    }

    @GetMapping("/dashboard/{idUsuario}")
    @Operation(description = "Endpoint que retorna dados BI para as dashboard, utilizando de uma DTO")
    public ResponseEntity<Object> getDashboardBI(@PathVariable int idUsuario){

        Optional<Usuario> usuario = usuarioRepositorio.findById(idUsuario);

        if(usuario.isPresent()){

            if (usuario.get().getNivelAcesso().equalsIgnoreCase("sysadm")){

                if(Objects.isNull(serviceDashboardSysadmBI.getDashboardSysadminBI(idUsuario))){
                    return ResponseEntity.status(204).build();
                }

                return ResponseEntity.status(200).body(serviceDashboardSysadmBI.getDashboardSysadminBI(idUsuario));

            } else if (usuario.get().getNivelAcesso().equalsIgnoreCase("adm")){

                if(Objects.isNull(serviceDashboardAdmBI.getDashboardAdminBI(idUsuario))){
                    return ResponseEntity.status(204).build();
                }

                return ResponseEntity.status(200).body(serviceDashboardAdmBI.getDashboardAdminBI(idUsuario));

            } else if (usuario.get().getNivelAcesso().equalsIgnoreCase("chatops")){

                if(Objects.isNull(serviceDashboardChatOpsBI.getDashboardChatOpsBI(idUsuario))){
                    return ResponseEntity.status(204).build();
                }

                return ResponseEntity.status(200).body(serviceDashboardChatOpsBI.getDashboardChatOpsBI(idUsuario));

            } else {
                return ResponseEntity.status(404).build();
            }

        }

        // 404 usuario not found
        return ResponseEntity.status(404).build();
    }

    @PostMapping
    @Operation(description = "Endpoint de criação de novas demandas, utilizando de uma DTO")
    public ResponseEntity<DtoDemanda> postDemanda(@RequestBody @Valid CriacaoDemanda novaDemanda){

        Optional<Usuario> usuario = usuarioRepositorio.findById(novaDemanda.getFkUsuario());
        Optional<Instituicao> instituicao = instituicaoRepositorio.findById(novaDemanda.getFkIntituicao());
        Pet pet = null;

        if (Objects.nonNull(novaDemanda.getFkPet())){
            if (petRepositorio.existsById(novaDemanda.getFkPet())){
                pet = petRepositorio.findById(novaDemanda.getFkPet()).get();
            }
        }

        if (instituicao.isPresent() && usuario.isPresent()){
            // verificando corpo da requisição
            if (!categoriasPossiveis.elementoExiste(novaDemanda.getCategoria())){
                // 400 bad request
                return ResponseEntity.status(400).build();
            }

            // 201 recurso criado
            Demanda demanda = new Demanda(novaDemanda.getCategoria(), usuario.get(), instituicao.get(), pet);
            demanda = demandaRepositorio.save(demanda);

            gerarHistoricoDemanda(demanda);
            demandaHistRepository.save(new DemandaHist(demanda));
            return ResponseEntity.ok(new DtoDemanda(demanda));

        }
        // 404 instituição not found
        return ResponseEntity.status(404).build();

    }

    @GetMapping
    @Operation(description = "Endpoint que retorna uma lista de demandas sem filtro")
    public ResponseEntity<List<DtoDemanda>> getDemanda(){
        return ResponseEntity.ok(service.getDemandas());
    }

    @GetMapping("/{idDemanda}")
    @Operation(description = "Endpoint que retorna uma demanda filtrada pelo ID")
    public ResponseEntity<DtoDemanda> getDemandaById(@PathVariable int idDemanda){
        return ResponseEntity.ok(service.getDemandaById(idDemanda));
    }

    @GetMapping("/chats/{userId}")
    @Operation(description = "Lista demandas no formato de chats")
    public ResponseEntity<DtoDemandaChats> getDemandaChats(@PathVariable Integer userId) {
        return ResponseEntity.ok(service.getChats(userId));
    }

    private void gerarHistoricoDemanda(Demanda demanda){
        DemandaHist demandaHist = new DemandaHist(demanda);
        demandaHistRepository.save(demandaHist);
    }

    private String gerarDataAtual(){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    // endpoints

    @GetMapping("/user/{idUsuario}")
    @Operation(description = "Endpoint que retorna uma lista de demandas filtradas pelo ID do Usuário")
    public ResponseEntity<List<Demanda>> getDemandaUserById(@PathVariable int idUsuario){
        Optional<Usuario> usuario = usuarioRepositorio.findById(idUsuario);
        // verificando se usuario existe
        if (usuario.isPresent()) {
            if (usuario.get().getNivelAcesso().equalsIgnoreCase("USER")){
                List<Demanda> lista = demandaRepositorio.findAllByUsuarioId(idUsuario);
                if (lista.isEmpty()){
                    return ResponseEntity.status(204).build();
                }
                return  ResponseEntity.status(200).body(lista);
            }

            if (usuario.get().getNivelAcesso().equalsIgnoreCase("CHATOPS") ||
                    usuario.get().getNivelAcesso().equalsIgnoreCase("PETOPS") ||
                    usuario.get().getNivelAcesso().equalsIgnoreCase("ADM")) {

                List<Demanda> lista = demandaRepositorio.findAllDemandaColaborador(idUsuario);
                if (lista.isEmpty()){
                    return ResponseEntity.status(204).build();
                }
                return  ResponseEntity.status(200).body(lista);
            }

            return ResponseEntity.status(400).build();

        }

        // 404 usuario not found
        return ResponseEntity.status(404).build();
    }

    // Aviso!!! Necessário reparos !!!
    @GetMapping("/user/{idUsuario}/grupoStatus")
    @Operation(description = "Endpoint que retorna uma lista de demandas de acordo com o status e filtrada pelo tipo de usuário")
    public ResponseEntity<DemandaUsuario> getDemandaColaboradorStatus(@PathVariable int idUsuario){
        Optional<Usuario> usuario = usuarioRepositorio.findById(idUsuario);
        if (usuario.isPresent()){
            if (usuario.get().getNivelAcesso().equalsIgnoreCase("USER")){
                // usuário é usuário
                List<Demanda> listaAberta = demandaRepositorio.findAllStatusAbertaUsuario(idUsuario);
                List<Demanda> listaEmAndamento = demandaRepositorio.findAllStatusEmAndamentoUsuario(idUsuario);
                List<Demanda> listaConluida = demandaRepositorio.findAllStatusConcluidoUsuario(idUsuario);

                DemandaUsuario demandaUsuario = new DemandaUsuario(listaAberta, listaEmAndamento, listaConluida);
                if (Objects.isNull(demandaUsuario)){
                    return ResponseEntity.status(404).build();
                }
                return ResponseEntity.status(200).body(demandaUsuario);
            }

            if (usuario.get().getNivelAcesso().equalsIgnoreCase("CHATOPS") ||
                    usuario.get().getNivelAcesso().equalsIgnoreCase("PETOPS") ||
            usuario.get().getNivelAcesso().equalsIgnoreCase("ADM")) {

                // usuário é colaboraor
                int idInstituicao = usuario.get().getInstituicao().getId();
                List<Demanda> listaAberta = demandaRepositorio.findAllStatusAbertaInstituicao(idInstituicao);
                List<Demanda> listaEmAndamento = demandaRepositorio.findAllStatusEmAndamentoColaborador(idUsuario);
                List<Demanda> listaConluida = demandaRepositorio.findAllStatusConcluidoColaborador(idUsuario);

                DemandaUsuario demandaUsuario = new DemandaUsuario(listaAberta, listaEmAndamento, listaConluida);
                if (Objects.isNull(demandaUsuario)){
                    return ResponseEntity.status(404).build();
                }
                return ResponseEntity.status(200).body(demandaUsuario);

            }

            return ResponseEntity.status(400).build();

        }
        return ResponseEntity.status(404).build();
    }



    @GetMapping("/instituicao/{idInstituicao}")
    @Operation(description = "Endpoint que retorna uma lista de demandas de uma instituição")
    public ResponseEntity<List<Demanda>> getDemandaByInstituicao(@PathVariable int idInstituicao){
        if (instituicaoRepositorio.existsById(idInstituicao)){
            List<Demanda> lista = demandaRepositorio.findAllByInstituicaoId(idInstituicao);
            if (lista.isEmpty()){
                return ResponseEntity.status(204).build();
            }
            return  ResponseEntity.status(200).body(lista);
        }
        // 404 instituição not found
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/instituicao/{idInstituicao}/{status}")
    @Operation(description = "Endpoint que retorna uma lista de demandas filtradas pela instituição e pelo status da demanda")
    public ResponseEntity<List<Demanda>> getDemandaByInstituicaoAndStatus(@PathVariable int idInstituicao, @PathVariable String status){
        if (instituicaoRepositorio.existsById(idInstituicao)){

            if (!statusPossiveis.elementoExiste(status)) {
                // 400 bad request - status da demanda inválida
                return ResponseEntity.status(400).build();
            }

            List<Demanda> lista = demandaRepositorio.findAllByInstituicaoAndStatus(idInstituicao, status);

            if (lista.isEmpty()){
                return ResponseEntity.status(204).build();
            }

            return  ResponseEntity.status(200).body(lista);
        }
        // 404 instituição not found
        return ResponseEntity.status(404).build();
    }

    @PutMapping("/{idDemanda}")
    @Operation(description = "Endpoint para atualizar por completo uma demanda")
    public ResponseEntity<Demanda> putDemanda(@PathVariable int idDemanda, @RequestBody @Valid Demanda demandaAtualizar){
        // verificando se demanda existe
        if (demandaRepositorio.existsById(idDemanda)){

            // validando categoria da demanda
            if (!categoriasPossiveis.elementoExiste(demandaAtualizar.getCategoria())){
                // 400 bad request - categoria da demanda inválida
                return ResponseEntity.status(400).build();
            }

            // validando status da demanda
            if (!statusPossiveis.elementoExiste(demandaAtualizar.getStatus())) {
                // 400 bad request - status da demanda inválida
                return ResponseEntity.status(400).build();
            }

            // atualizando valores da demanda
            Demanda demanda = demandaRepositorio.getById(idDemanda);
            demanda.setCategoria(demandaAtualizar.getCategoria().toUpperCase());
            demanda.setDataAbertura(demandaAtualizar.getDataAbertura());
            demanda.setDataFechamento(demandaAtualizar.getDataFechamento());
            demanda.setStatus(demandaAtualizar.getStatus().toUpperCase());
            demandaRepositorio.save(demanda);
            // 200
            return ResponseEntity.status(200).build();
        }

        // 404 demanda não encontrada
        return ResponseEntity.status(404).build();
    }

    @PatchMapping("/colaborador-abrir/{idDemanda}/{idColaborador}")
    @Operation(description = "Endpoint que insere um colaborador na demanda")
    public ResponseEntity<Object> patchDemandaColaborador(@PathVariable int idDemanda, @PathVariable int idColaborador){
        Optional<Demanda> demanda = demandaRepositorio.findById(idDemanda);
        Optional<Usuario> colaborador = usuarioRepositorio.findById(idColaborador);

        // verificando existencia da demanda
        if (demanda.isPresent()){
            if (colaborador.isPresent()){

                demanda.get().setStatus("EM_ANDAMENTO");
                demanda.get().setColaborador(colaborador.get());
                demanda.get().setDataAbertura(new Date());
                demandaRepositorio.save(demanda.get());

                gerarHistoricoDemanda(demanda.get());
                // 200 - empty response
                return ResponseEntity.status(200).build();
            }
            // 404 - demanda não encontrada
            return ResponseEntity.status(404).body(new Message("Colaborador não encontrada"));
        }
        // 404 - demanda não encontrada
        return ResponseEntity.status(404).body(new Message("Demanda não encontrada"));
    }

    @DeleteMapping("/{idDemanda}")
    @Operation(description = "Enpoint que deleta uma demanda especifica filtrada pelo ID")
    public ResponseEntity<Object> deleteDemanda(@PathVariable int idDemanda){

        // verificando se demanda existe
        if (demandaRepositorio.existsById(idDemanda)){
            // deletando demanda
            demandaRepositorio.deleteById(idDemanda);
            // 200
            return ResponseEntity.status(200).build();
        }

        // 404 demanda não encontrada
        return ResponseEntity.status(404).build();
    }

    @PostMapping("/mensagem")
    @Operation(description = "Endpoint que cria as mensagens relacionadas a uma demanda")
    public ResponseEntity<Mensagem> postMensagem(@RequestBody @Valid Mensagem mensagem){

        Optional<Demanda> demanda = demandaRepositorio.findById(mensagem.getDemanda().getId());
        Optional<Usuario> usuario = usuarioRepositorio.findById(mensagem.getUsuario().getId());

        if (demanda.isPresent() && usuario.isPresent()){
            if (!tiposMenssagensPossiveis.elementoExiste(mensagem.getTipo())){
                return ResponseEntity.status(400).build();
            }
            mensagem.setTipo(mensagem.getTipo().toUpperCase());
            mensagem.setDataEnvio(new Date());
            mensagemRepository.save(mensagem);

            return ResponseEntity.status(200).body(mensagem);
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/mensagem/{idDemanda}")
    @Operation(description = "Endpoint que retorna as mensagens da mais recente a mais antiga")
    public ResponseEntity<List<Mensagem>> getMensagemDecredcente(@PathVariable Integer idDemanda){
        if (demandaRepositorio.existsById(idDemanda)){
            List<Mensagem> lista = mensagemRepository.findAllMensagemDemandaDecrescente(idDemanda);

            if (!lista.isEmpty()){
                return ResponseEntity.status(200).body(lista);
            }
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }


    @GetMapping("/download/{idInstituicao}/{status}")
    @Operation(description = "Endpoint responsável por fazer o download do log de demandas com um tipo de status e de uma unica instiuição filtrada pelo seu ID")
    public ResponseEntity<Object> getDemandaCSV(@PathVariable int idInstituicao, @PathVariable String status){
        List<Demanda> listaRepositorio = demandaRepositorio.findAllByInstituicaoAndStatus(idInstituicao,status);
        ListaObj<Demanda> listaDemandas = new ListaObj<>(listaRepositorio.size());
        for(Demanda d : listaRepositorio){
            listaDemandas.adicionarElemento(d);
            System.out.println(d);
        }

        gravaArquivoCSV(listaDemandas,"demandas");
        String relatorio = leArquivoCSV("demandas");

        return ResponseEntity
                .status(200)
                .header("content-type", "text/csv")
                //.header("content-length", "9999999999")
                .header("content-disposition", "filename=\"demandas.csv\"")
                .body(relatorio);
    }

    @Override
    public void gravaArquivoCSV(ListaObj lista, String nomeArquivo) {
        FileWriter arq = null;
        Formatter saida = null;
        boolean deuRuim = false;
        nomeArquivo += ".csv";
        String formatacaoInsert = "%d;%s;%s;%s;%s\n";

        try {
            arq = new FileWriter(nomeArquivo);
            saida = new Formatter(arq);
        }
        catch (IOException err){
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        try{
            for (int i = 0; i < lista.getTamanho(); i++) {
                Demanda demanda = (Demanda) lista.getElemento(i);
                if(Objects.isNull(demanda.getColaborador())){
                    saida.format(formatacaoInsert,
                            demanda.getId(),demanda.getCategoria(),
                            demanda.getUsuario().getNome(),
                            demanda.getDataAbertura(),
                            '-');
                }else{
                    saida.format(formatacaoInsert,
                            demanda.getId(),demanda.getCategoria(),
                            demanda.getUsuario().getNome(),
                            demanda.getDataAbertura(),
                            demanda.getColaborador().getNome());
                }
            }
        }catch (FormatterClosedException err){
            System.out.println("Erro ao gravar arquivo");
            deuRuim = true;
        }
        finally {
            saida.close();
            try {
                arq.close();
            }
            catch (IOException err){
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if(deuRuim){
                System.exit(1);
            }
        }

    }

    @Override
    public String leArquivoCSV(String nomeArq) {
        FileReader arq = null;
        Scanner entrada = null;
        boolean deuRuim = false;
        nomeArq += ".csv";

        String relatorio ="";

        try {
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        }catch (FileNotFoundException err){
            System.out.println("Arquivo não existe");
            System.exit(1);
        }

        try {
            while (entrada.hasNext()){
                int id = entrada.nextInt();
                String nome = entrada.next();
                String categoria = entrada.next();
                String data = entrada.next();
                String colaborador = entrada.next();
                relatorio += id+";"+nome+";"+categoria+";"+data+";"+colaborador+";"+"\n";
            }
        }catch (NoSuchElementException err){
            System.out.println("Arquivo com problemas");
            deuRuim = true;
        }catch (IllegalStateException err){
            System.out.println("Erro na leitura do arquivo");
            deuRuim = true;
        }
        finally {
            entrada.close();
            try {
                arq.close();
            }
            catch (IOException err){
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if(deuRuim){
                System.exit(1);
            }
            return relatorio;
        }
    }

    @Override
    public boolean leArquivoTxt(String nomeArq, Instituicao instituicao) {
        return false;
    }

    @Override
    public void gravaRegistro(String registro, String nomeArq) {
        BufferedWriter saida = null;

        // try-catch para abrir o arquivo
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo: " + erro);
        }

        // try-catch para gravar o registro e fechar o arquivo
        try {
            saida.append(registro + "\n");
            saida.close();
        }
        catch (IOException erro) {
            System.out.println("Erro ao gravar o arquivo: " + erro);
        }
    }

    @Override
    public <T> T gravaArquivoTxt(List<Demanda> listaDemanda, List<Usuario> listaUsuario,
                                 List<Instituicao> listaInstituicao, List<Pet> listaPet, String nomeArq) {
        int contaRegCorpo = 0;

        // Monta o registro de header
        String header = "00DEMANDAS20221";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "0001";
        // Grava o registro de header
        gravaRegistro(header, nomeArq);

        // Monta e grava os registros de corpo
        String corpo;
        for (Demanda d : listaDemanda) {
            corpo = "02";
            corpo += String.format("%-9.9s", d.getCategoria());
            corpo += String.format("%10.10s", d.getDataAbertura());
            corpo += String.format("%10.10s", d.getDataFechamento());
            corpo += String.format("%-19.19s", d.getStatus());
            contaRegCorpo++;
            gravaRegistro(corpo, nomeArq);
        }

        for (Usuario u : listaUsuario) {
            corpo = "03";
            corpo += String.format("%-50.50s", u.getNome());
            contaRegCorpo++;
            gravaRegistro(corpo, nomeArq);
        }

        for (Instituicao i : listaInstituicao) {
            corpo = "04";
            corpo += String.format("%-50.50s", i.getNome());
            contaRegCorpo++;
            gravaRegistro(corpo, nomeArq);
        }

        for (Pet p : listaPet) {
            corpo = "05";
            corpo += String.format("%-50.50s", p.getNome());
            contaRegCorpo++;
            gravaRegistro(corpo, nomeArq);
        }

        // masi um for para o segundo corpo

        // Monta e grava o registro de trailer
        String trailer = "01";
        trailer += String.format("%04d", contaRegCorpo);
        trailer += String.format("%04d", demandaRepositorio.countByStatus("ABERTO"));
        trailer += String.format("%04d", demandaRepositorio.countByStatus("CONCLUIDO"));
        trailer += String.format("%04d", demandaRepositorio.countByStatusAndCategoria("ABERTO", "ADOCAO"));
        trailer += String.format("%04d", demandaRepositorio.countByStatusAndCategoria("CONCLUIDO", "PAGAMENTO"));
        gravaRegistro(trailer, nomeArq);
        return null;
    }

    @GetMapping("/export-demandas")
    public ResponseEntity getPetsGravarDoc(){
        List<Demanda> demanda = demandaRepositorio.findAll();
        List<Demanda> listaDemanda = new ArrayList<>();

        for (Demanda d : demanda) {
            listaDemanda.add(d);
        }

        List<Usuario> usuario = usuarioRepositorio.findAll();
        List<Usuario> listaUsuario = new ArrayList<>();

        for (Usuario u : usuario) {
            listaUsuario.add(u);
        }

        List<Instituicao> instituicao = instituicaoRepositorio.findAll();
        List<Instituicao> listaInstituicao = new ArrayList<>();

        for (Instituicao i : instituicao) {
            listaInstituicao.add(i);
        }

        List<Pet> pet = petRepositorio.findAll();
        List<Pet> listaPet = new ArrayList<>();

        for (Pet p : pet) {
            listaPet.add(p);
        }

        return ResponseEntity
                .status(200)
                .header("content-type", "text/csv")
                .header("content-disposition", "filename=\"demandas.txt\"")
                .body(gravaArquivoTxt(listaDemanda, listaUsuario, listaInstituicao, listaPet,"demandas.txt"));
    }

    @GetMapping("/count/apadrinhamentos/{idInstituicao}")
    ResponseEntity countApadrinhamentosByIntituicao(@PathVariable int idInstituicao) {
        int qtdApadrinhamentos = demandaRepositorio.countAllApadrinhamentos(idInstituicao);
        return ResponseEntity.status(200).body(qtdApadrinhamentos);
    }

    @GetMapping("/premios/get/{idUser}")
    public ResponseEntity getPetsApadrinhadosPorUser(@PathVariable int idUser) {
        List<PremioDto> pets = premioRepositorio.findByUsuarioIdDto(idUser);
        if (pets.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(pets);
    }

}
