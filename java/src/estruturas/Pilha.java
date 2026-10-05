package estruturas;

// Pilha (LIFO) própria, baseada em array de tamanho fixo.
public class Pilha<T> {
    private final Object[] elementos;
    private int tamanho = 0;

    public Pilha(int capacidade) {
        elementos = new Object[capacidade];
    }

    public boolean empilhar(T elemento) {
        if (tamanho == elementos.length) {
            return false;
        }
        elementos[tamanho] = elemento;
        tamanho++;
        return true;
    }

    @SuppressWarnings("unchecked")
    public T desempilhar() {
        if (estaVazia()) {
            return null;
        }
        tamanho--;
        T elemento = (T) elementos[tamanho];
        elementos[tamanho] = null;
        return elemento;
    }

    @SuppressWarnings("unchecked")
    public T topo() {
        if (estaVazia()) {
            return null;
        }
        return (T) elementos[tamanho - 1];
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }
}
