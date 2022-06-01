package petfinder.petfinderapi.utilitarios;

import petfinder.petfinderapi.entidades.*;

import java.io.FileReader;
import java.util.List;

public interface GerenciadorArquivos{
    public void gravaArquivoCSV(ListaObj lista, String nomeArquivo);

    public String leArquivoCSV(String nomeArq);

    public void gravaRegistro(String registro, String nomeArq);

    public <T> T gravaArquivoTxt(List<Demanda> listaDemanda, List<Usuario> listaUsuario,
                                 List<Instituicao> listaInstituicao, List<Pet> listaPet, String nomeArq);

    public boolean leArquivoTxt(String nomeArq, Instituicao instituicao);

}
