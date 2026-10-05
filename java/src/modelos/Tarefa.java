package modelos;

public class Tarefa {
    private String titulo;
    private Status status;

    public Tarefa(String titulo) {
        this.titulo = titulo;
        this.status = Status.PENDENTE;
    }

    public String getTitulo() {
        return titulo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
