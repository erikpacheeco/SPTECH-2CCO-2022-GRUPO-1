package petfinder.petfinderapi.utilitarios;

public class FilaObj<T> {
    private int tamanho;
    private T[] fila;

    public FilaObj(int capacidade) {
        this.tamanho = 0;
        this.fila = (T[]) new Object[capacidade];
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public boolean isFull() {
        return tamanho == fila.length;
    }

    public void insert(T info) {
        if (isFull()) {
            throw new IllegalStateException();
        } else {
            fila[tamanho++] = info;
        }
    }

    public T peek() {
        return fila[0];
    }

    public T poll() {
        T primeiro = fila[0];
        if (!isEmpty()) {
            for (int i = 0; i < tamanho-1; i++) {
                fila[i] = fila[i+1];
            }
            tamanho--;
            fila[tamanho] = null;
        }
        return primeiro;
    }

    public void exibe() {
        for (int i = 0; i < fila.length; i++) {
            System.out.println(i);
        }
    }

    public T[] getFila() {
        return fila;
    }
}
