package petfinder.petfinderapi.listaObj;

public class ListaObj<T>{


    private T[] vetor;
    private int nroElem;

    public ListaObj(int tam) {
        vetor = (T[]) new Object[tam];
        this.nroElem = 0;
    }

    public ListaObj(T[] lista) {
        vetor = (T[]) new Object[lista.length];
        this.nroElem = 0;

        for (T elemento : lista) {
            adicionarElemento(elemento);
        }
    }

    public void adicionarElemento(T elemento){
        if (nroElem < vetor.length){
            vetor[nroElem++] = elemento;
        } else {
            System.out.println("\nLista cheia");
        }
    }

    public void exibirLista(){
        if (nroElem == 0){
            System.out.println("Lista vazia");
        } else {
//            System.out.println("Intens da lista:");
            for (int i = 0; i < nroElem; i++) {
                System.out.println(String.format("%s", vetor[i]));
            }
        }
    }

    public int buscaElemento(T elemento){

        for (int i = 0; i < nroElem; i++) {
            if (elemento.equals(vetor[i])){
                return i;
            }
        }
        return -1;
    }

    public boolean verificaElemento(T elemento){
        if (buscaElemento(elemento) == -1){
            return false;
        }
        return true;
    }

    public boolean removePeloIndice(int indiceElemento){
        if (indiceElemento < nroElem && indiceElemento > -1){
            for (int i = indiceElemento; i < nroElem -1; i++) {
                vetor[i] = vetor[i + 1];
            }
            /*
                for (int i = indiceElemento + 1; i < nroElem; i++) {
                vetor[i - 1] = vetor[i];
            }
             */
            nroElem--;
            return true;
        }
        return false;
    }

    public boolean removeElemento(T elemento){
        int indice = buscaElemento(elemento);
        return removePeloIndice(indice);
        // return removePeloIndice(buscaElemento(elemento));
    }

    public int getTamanho(){
        return nroElem;
    }

    public T getElemnto(int indice){
        if (indice < 0 || indice >= nroElem){
            return null;
        }
        return vetor[indice];
    }

    public void limpa(){
        nroElem = 0;
    }

}
