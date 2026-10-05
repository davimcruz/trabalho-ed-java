# Trabalho de Estrutura de Dados I — Gerenciador de Tarefas (N1)

**Aluno:** Davi Machado
**Disciplina:** Estrutura de Dados I — UniAlfa (ADS) — Prof. George Mendes Marra
**Tema:** Gerenciador de tarefas com pilha de desfazer (undo)

## Descrição

Programa de console em Java para cadastrar tarefas, buscá-las, ordená-las e mudar seu status.
Cada mudança de status é empilhada, permitindo desfazer a última alteração.

## Itens da ementa cobertos (N1)

| Item | Onde |
|------|------|
| Array | `Main.java` — vetor `tarefas` com inserção, percurso, busca linear e ordenação (bubble sort) |
| Enum | `modelos/Status.java` — `PENDENTE`, `EM_ANDAMENTO`, `CONCLUIDA` |
| Pilha | `estruturas/Pilha.java` — implementação própria (array) com `empilhar`, `desempilhar` e `topo` |
| Menu e métodos | `Main.java` — menu de console, um método por funcionalidade |

## Como executar

```bash
cd java
javac -d out src/Main.java src/modelos/*.java src/estruturas/*.java
java -cp out Main
```

Requer JDK 17 ou superior.
