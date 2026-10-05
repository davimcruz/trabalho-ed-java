import estruturas.Pilha;
import modelos.Status;
import modelos.Tarefa;

import java.util.Scanner;

// Gerenciador de tarefas: array de tarefas + enum de status + pilha de "desfazer".
public class Main {
    static final int CAPACIDADE = 20;

    static Tarefa[] tarefas = new Tarefa[CAPACIDADE]; // Array
    static int quantidade = 0;

    // Cada mudança de status guarda a tarefa e o status anterior.
    record Mudanca(Tarefa tarefa, Status statusAnterior) {
    }

    static Pilha<Mudanca> historico = new Pilha<>(100);
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha: ");
            executar(opcao);
        } while (opcao != 0);
    }

    static void exibirMenu() {
        System.out.println("\n=== Gerenciador de Tarefas ===");
        System.out.println("1 - Adicionar tarefa");
        System.out.println("2 - Listar tarefas");
        System.out.println("3 - Buscar tarefa por título");
        System.out.println("4 - Ordenar tarefas por título");
        System.out.println("5 - Mudar status de uma tarefa");
        System.out.println("6 - Desfazer última mudança de status");
        System.out.println("7 - Ver última mudança (topo da pilha)");
        System.out.println("0 - Sair");
    }

    static void executar(int opcao) {
        switch (opcao) {
            case 1 -> adicionarTarefa();
            case 2 -> listarTarefas();
            case 3 -> buscarTarefa();
            case 4 -> ordenarTarefas();
            case 5 -> mudarStatus();
            case 6 -> desfazer();
            case 7 -> verTopo();
            case 0 -> System.out.println("Até mais!");
            default -> System.out.println("Opção inválida.");
        }
    }

    static void adicionarTarefa() {
        if (quantidade == CAPACIDADE) {
            System.out.println("Lista cheia.");
            return;
        }
        tarefas[quantidade] = new Tarefa(lerTexto("Título: "));
        quantidade++;
    }

    // Percurso do array.
    static void listarTarefas() {
        if (quantidade == 0) {
            System.out.println("Nenhuma tarefa.");
        }
        for (int indice = 0; indice < quantidade; indice++) {
            System.out.println(indice + " - " + tarefas[indice]);
        }
    }

    // Busca linear.
    static void buscarTarefa() {
        String titulo = lerTexto("Título: ");
        for (int indice = 0; indice < quantidade; indice++) {
            if (tarefas[indice].getTitulo().equalsIgnoreCase(titulo)) {
                System.out.println("Encontrada na posição " + indice + ": " + tarefas[indice]);
                return;
            }
        }
        System.out.println("Não encontrada.");
    }

    // Ordenação simples (bubble sort) por título.
    static void ordenarTarefas() {
        for (int passada = 0; passada < quantidade - 1; passada++) {
            for (int indice = 0; indice < quantidade - 1 - passada; indice++) {
                String atual = tarefas[indice].getTitulo();
                String proximo = tarefas[indice + 1].getTitulo();
                if (atual.compareToIgnoreCase(proximo) > 0) {
                    Tarefa auxiliar = tarefas[indice];
                    tarefas[indice] = tarefas[indice + 1];
                    tarefas[indice + 1] = auxiliar;
                }
            }
        }
        System.out.println("Tarefas ordenadas.");
    }

    static void mudarStatus() {
        listarTarefas();
        int indice = lerInteiro("Posição da tarefa: ");
        if (indice < 0 || indice >= quantidade) {
            System.out.println("Posição inválida.");
            return;
        }
        Status[] opcoes = Status.values();
        for (int posicao = 0; posicao < opcoes.length; posicao++) {
            System.out.println(posicao + " - " + opcoes[posicao]);
        }
        int escolhido = lerInteiro("Novo status: ");
        if (escolhido < 0 || escolhido >= opcoes.length) {
            System.out.println("Status inválido.");
            return;
        }
        Tarefa tarefa = tarefas[indice];
        if (!historico.empilhar(new Mudanca(tarefa, tarefa.getStatus()))) {
            System.out.println("Histórico cheio.");
            return;
        }
        tarefa.setStatus(opcoes[escolhido]);
        System.out.println("Atualizada: " + tarefa);
    }

    static void desfazer() {
        Mudanca mudanca = historico.desempilhar();
        if (mudanca == null) {
            System.out.println("Nada para desfazer.");
            return;
        }
        mudanca.tarefa().setStatus(mudanca.statusAnterior());
        System.out.println("Desfeito: " + mudanca.tarefa());
    }

    static void verTopo() {
        Mudanca mudanca = historico.topo();
        if (mudanca == null) {
            System.out.println("Histórico vazio.");
            return;
        }
        System.out.println("Última mudança: " + mudanca.tarefa().getTitulo()
                + " (status anterior: " + mudanca.statusAnterior() + ")");
    }

    static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return teclado.nextLine().trim();
    }

    static int lerInteiro(String mensagem) {
        try {
            return Integer.parseInt(lerTexto(mensagem));
        } catch (NumberFormatException erro) {
            return -1;
        }
    }
}
