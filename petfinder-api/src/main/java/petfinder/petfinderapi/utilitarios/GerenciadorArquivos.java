package petfinder.petfinderapi.utilitarios;

import java.io.FileReader;

public interface GerenciadorArquivos{
    public void gravaArquivoCSV(ListaObj lista, String nomeArquivo);

    public String leArquivoCSV(String nomeArq);
}
