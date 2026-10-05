import estruturas.Pilha;
import modelos.Status;
import modelos.Tarefa;

import java.util.Scanner;

// Gerenciador de tarefas: array + enum + pilha (para desfazer o "adicionar").
public class Main {
    static Tarefa[] tarefas = new Tarefa[20]; // Array
    static int quantidade = 0;
    static Pilha historico = new Pilha(); // Pilha
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = -1;
        while (opcao != 0) {
            mostrarMenu();
            System.out.print("Escolha: ");
            opcao = lerNumero();

            if (opcao == 1) {
                adicionarTarefa();
            } else if (opcao == 2) {
                listarTarefas();
            } else if (opcao == 3) {
                buscarTarefa();
            } else if (opcao == 4) {
                ordenarTarefas();
            } else if (opcao == 5) {
                concluirTarefa();
            } else if (opcao == 6) {
                desfazerAdicao();
            } else if (opcao == 7) {
                mostrarUltimaAdicionada();
            }
        }
    }

    // Lê um número; se o usuário digitar outra coisa, devolve -1.
    static int lerNumero() {
        try {
            return Integer.parseInt(teclado.nextLine());
        } catch (NumberFormatException erro) {
            return -1;
        }
    }

    static void mostrarMenu() {
        System.out.println();
        System.out.println("1 - Adicionar tarefa");
        System.out.println("2 - Listar tarefas");
        System.out.println("3 - Buscar tarefa");
        System.out.println("4 - Ordenar tarefas por título");
        System.out.println("5 - Concluir tarefa");
        System.out.println("6 - Desfazer última tarefa adicionada");
        System.out.println("7 - Ver última tarefa adicionada");
        System.out.println("0 - Sair");
    }

    static void adicionarTarefa() {
        if (quantidade == tarefas.length) {
            System.out.println("Lista cheia.");
            return;
        }
        System.out.print("Título: ");
        Tarefa tarefa = new Tarefa(teclado.nextLine());
        tarefas[quantidade] = tarefa;
        quantidade++;
        historico.empilhar(tarefa);
    }

    // Percorre o array.
    static void listarTarefas() {
        for (int i = 0; i < quantidade; i++) {
            System.out.println(i + " - " + tarefas[i].getTitulo() + " (" + tarefas[i].getStatus() + ")");
        }
    }

    // Busca sequencial.
    static void buscarTarefa() {
        System.out.print("Título: ");
        String titulo = teclado.nextLine();
        for (int i = 0; i < quantidade; i++) {
            if (tarefas[i].getTitulo().equals(titulo)) {
                System.out.println("Encontrada na posição " + i);
                return;
            }
        }
        System.out.println("Não encontrada.");
    }

    // Ordenação bubble sort.
    static void ordenarTarefas() {
        for (int i = 0; i < quantidade - 1; i++) {
            for (int j = 0; j < quantidade - 1 - i; j++) {
                if (tarefas[j].getTitulo().compareTo(tarefas[j + 1].getTitulo()) > 0) {
                    Tarefa auxiliar = tarefas[j];
                    tarefas[j] = tarefas[j + 1];
                    tarefas[j + 1] = auxiliar;
                }
            }
        }
    }

    static void concluirTarefa() {
        listarTarefas();
        System.out.print("Posição da tarefa: ");
        int posicao = lerNumero();
        if (posicao < 0 || posicao >= quantidade) {
            System.out.println("Posição inválida.");
            return;
        }
        tarefas[posicao].setStatus(Status.CONCLUIDA);
    }

    // Desempilha a última tarefa adicionada e a remove do array.
    static void desfazerAdicao() {
        Tarefa tarefa = historico.desempilhar();
        if (tarefa == null) {
            System.out.println("Nada para desfazer.");
            return;
        }
        int posicao = 0;
        while (tarefas[posicao] != tarefa) {
            posicao++;
        }
        for (int i = posicao; i < quantidade - 1; i++) {
            tarefas[i] = tarefas[i + 1];
        }
        quantidade--;
        tarefas[quantidade] = null;
        System.out.println("Removida: " + tarefa.getTitulo());
    }

    static void mostrarUltimaAdicionada() {
        Tarefa tarefa = historico.consultarTopo();
        if (tarefa == null) {
            System.out.println("Pilha vazia.");
            return;
        }
        System.out.println("Última adicionada: " + tarefa.getTitulo());
    }
}
