package petfinder.petfinderapi.utilitarios;

import petfinder.petfinderapi.entidades.Instituicao;

import java.io.FileReader;

public interface GerenciadorArquivos{
    public void gravaArquivoCSV(ListaObj lista, String nomeArquivo);

    public String leArquivoCSV(String nomeArq);

    public boolean leArquivoTxt(String nomeArq, Instituicao instituicao);
}
