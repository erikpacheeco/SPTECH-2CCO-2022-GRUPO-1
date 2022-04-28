package petfinder.petfinderapi.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import petfinder.petfinderapi.entidades.Demanda;
import petfinder.petfinderapi.utilitarios.GerenciadorArquivos;
import petfinder.petfinderapi.utilitarios.ListaObj;
import petfinder.petfinderapi.repositorios.DemandaRepositorio;
import petfinder.petfinderapi.repositorios.InstituicaoRepositorio;
import petfinder.petfinderapi.repositorios.UsuarioRepositorio;
import petfinder.petfinderapi.requisicao.CriacaoDemanda;
import petfinder.petfinderapi.resposta.Message;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/demandas")
public class DemandaController implements GerenciadorArquivos{

    // repositorios
    @Autowired
    private DemandaRepositorio demandaRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private InstituicaoRepositorio instituicaoRepositorio;

    // enums
    private ListaObj<String> categoriasPossiveis = new ListaObj<String>(new String[]{"ADOCAO", "PAGAMENTO", "RESGATE"});
    private ListaObj<String> statusPossiveis = new ListaObj<String>(new String[]{"ABERTO", "CONCLUIDO", "CANCELADO", "DOCUMENTO_VALIDO",
            "PGTO_REALIZADO_USER", "PGTO_REALIZADO_INST", "RESGATE_INVALIDO", "RESGATE_VALIDO", "EM_ANDAMENTO"});

    private ListaObj<String> tiposMenssagensPossiveis = new ListaObj<String>(new String[]{"ARQUIVO", "MENSSAGEM"});

    // endpoints
    @PostMapping
    public ResponseEntity<Object> postDemanda(@RequestBody @Valid CriacaoDemanda novaDemanda){

        if (instituicaoRepositorio.existsById(novaDemanda.getFkIntituicao())){

            // verificando corpo da requisição
            if (!categoriasPossiveis.elementoExiste(novaDemanda.getCategoria())){

                // 400 bad request
                return ResponseEntity.status(400).body(new Message("Corpo da requisição vazio ou categoria de demanda inválida"));
            }

            if (!usuarioRepositorio.existsById(novaDemanda.getFkUsuario())){
                // 404 Usuario not found
                return ResponseEntity.status(404).body(new Message("Usuario não encontrada"));
            }

            // 201 recurso criado
            Demanda demanda = new Demanda(novaDemanda);
            demanda.setCategoria(demanda.getCategoria());
            demandaRepositorio.save(demanda);
            return ResponseEntity.status(201).build();

        }
        // 404 instituição not found
        return ResponseEntity.status(404).body(new Message("Instituição não encontrada"));

    }

    @GetMapping
    public ResponseEntity getDemanda(){
        List<Demanda> lista = demandaRepositorio.findAll();

        // 204, em caso de lista vazia
        if (lista.isEmpty()) {
            return ResponseEntity.status(204).body(new Message("Lista vázia"));
        }

        // 200
        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/{idDemanda}")
    public ResponseEntity<Object> getDemandaById(@PathVariable int idDemanda){
        Optional<Demanda> demanda = demandaRepositorio.findById(idDemanda);

        // verificando existencia da demanda
        if (demanda.isPresent()){

            // 200
            return ResponseEntity.status(200).body(demanda.get());
        }

        // 404 demanda não encontrada
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/user/{fkUsuario}")
    public ResponseEntity<Object> getDemandasUserById(@PathVariable int fkUsuario){

        // verificando se usuario existe
        if (usuarioRepositorio.existsById(fkUsuario)) {
            // Retornar demandas isoladas de acordo com o status
            // armazenando lista de demandas do usuário
            List<Demanda> lista = demandaRepositorio.findAllByFkUsuario(fkUsuario);

            // verificando se a lista de demandas do usuário está vazia
            if (lista.isEmpty()){

                // 204 no content
                return ResponseEntity.status(204).body(new Message("Lista vázia"));
            }

            // 200 
            return  ResponseEntity.status(200).body(lista);
        }

        // 404 usuario not found
        return ResponseEntity.status(404).body(new Message("Usuário não encontrado"));
    }

    @GetMapping("/instituicao/{fkInstituicao}")
    public ResponseEntity<Object> getDemandaAbertoInstituicao(@PathVariable int fkInstituicao){
        if (instituicaoRepositorio.existsById(fkInstituicao)){
            List<Demanda> lista = demandaRepositorio.findAllByFkInstituicaoAndStatus(fkInstituicao, "aberto");
            if (lista.isEmpty()){
                return ResponseEntity.status(204).body(new Message("Lista vázia"));
            }
            return  ResponseEntity.status(200).body(lista);
        }
        // 404 instituição not found
        return ResponseEntity.status(404).body(new Message("Instituição não encontrada"));

    } /// Terminar este!!!!!!

    @PutMapping("/{idDemanda}")
    public ResponseEntity<Object> patchDemanda(@PathVariable int idDemanda, @RequestBody @Valid Demanda demandaAtualizar){

        // verificando se demanda existe
        if (demandaRepositorio.existsById(idDemanda)){

            // validando categoria da demanda
            if (!categoriasPossiveis.elementoExiste(demandaAtualizar.getCategoria())){

                // 400 bad request - categoria da demanda inválida
                return ResponseEntity.status(400).body(new Message("Categoria da demanda inválida"));
            }

            // validando status da demanda
            if (!statusPossiveis.elementoExiste(demandaAtualizar.getStatus())) {

                // 400 bad request - status da demanda inválida
                return ResponseEntity.status(400).body(new Message("Status da demanda inválido"));
            }

            // atualizando valores da demanda
            Demanda demanda = demandaRepositorio.getById(idDemanda);
            demanda.setCategoria(demandaAtualizar.getCategoria());
            demanda.setDataAbertura(demandaAtualizar.getDataAbertura());
            demanda.setDataFechamento(demandaAtualizar.getDataFechamento());
            demanda.setStatus(demandaAtualizar.getStatus());
            demandaRepositorio.save(demanda);

            // 200
            return ResponseEntity.status(200).build();
        }

        // 404 demanda não encontrada
        return ResponseEntity.status(404).build();
    }

    @PatchMapping("/colaborador-abrir/{idDemanda}/{fkColaborador}")
    public ResponseEntity<Object> patchDemandaColaborador(@PathVariable int idDemanda, @PathVariable int fkColaborador){

        // verificando existencia da demanda
        if (demandaRepositorio.existsById(idDemanda)){
            if (usuarioRepositorio.existsById(fkColaborador)){
                // atualizando status da demanda
                Demanda demanda = demandaRepositorio.getById(idDemanda);
                demanda.setStatus("em_andamento");
                demanda.setFkColaborador(fkColaborador);
                demandaRepositorio.save(demanda);
                // 200 - empty response
                return ResponseEntity.status(200).build();
            }
            // 404 - demanda não encontrada
            return ResponseEntity.status(404).body(new Message("Colaborador não encontrada"));
        }
        // 404 - demanda não encontrada
        return ResponseEntity.status(404).body(new Message("Demanda não encontrada"));
    }

    @PatchMapping("/status/{idDemanda}/{statusAtualizar}")
    public ResponseEntity<Object> patchDemandaStatus(@PathVariable int idDemanda, @PathVariable String statusAtualizar){

        // verificando existencia da demanda
        if (demandaRepositorio.existsById(idDemanda)){

            // verificando validade do status
            if (!statusPossiveis.elementoExiste(statusAtualizar)){

                // 404 - status inválido
                return ResponseEntity.status(404).body(new Message("Status inválido"));
            }

            // atualizando status da demanda
            Demanda demanda = demandaRepositorio.getById(idDemanda);
            demanda.setStatus(statusAtualizar.toLowerCase(Locale.ROOT));
            demandaRepositorio.save(demanda);

            // 200 - empty response
            return ResponseEntity.status(200).build();
        }

        // 404 - demanda não encontrada
        return ResponseEntity.status(404).body(new Message("Demanda não encontrada"));
    }

    @DeleteMapping("/{idDemanda}")
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

    @GetMapping("/download/{idInstituicao}/{status}")
    public ResponseEntity<Object> getDemandaCSV(@PathVariable int idInstituicao, @PathVariable String status){
        List<Demanda> listaRepositorio = demandaRepositorio.findAllByFkInstituicaoAndStatus(idInstituicao,status);
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


//
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
                if(Objects.isNull(demanda.getFkColaborador())){
                    saida.format(formatacaoInsert,
                            demanda.getId(),demanda.getCategoria(),
                            usuarioRepositorio.findById(demanda.getFkUsuario()).get().getNome(),
                            demanda.getDataAbertura(),
                            '-');
                }else{
                    saida.format(formatacaoInsert,
                            demanda.getId(),demanda.getCategoria(),
                            usuarioRepositorio.findById(demanda.getFkUsuario()).get().getNome(),
                            demanda.getDataAbertura(),
                            usuarioRepositorio.findById(demanda.getFkColaborador()).get().getNome());
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
}
