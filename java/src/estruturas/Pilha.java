package estruturas;

import modelos.Tarefa;

// Pilha (LIFO) feita com array.
public class Pilha {
    private Tarefa[] elementos = new Tarefa[100];
    private int topo = -1; // posição do último elemento (-1 = vazia)

    public boolean empilhar(Tarefa tarefa) {
        if (topo == elementos.length - 1) {
            return false; // cheia
        }
        topo++;
        elementos[topo] = tarefa;
        return true;
    }

    public Tarefa desempilhar() {
        if (estaVazia()) {
            return null;
        }
        Tarefa tarefa = elementos[topo];
        topo--;
        return tarefa;
    }

    public Tarefa consultarTopo() {
        if (estaVazia()) {
            return null;
        }
        return elementos[topo];
    }

    public boolean estaVazia() {
        return topo == -1;
    }
}
