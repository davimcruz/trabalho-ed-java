package modelos;

public class Tarefa {
    private final String titulo;
    private Status status = Status.PENDENTE;

    public Tarefa(String titulo) {
        this.titulo = titulo;
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

    @Override
    public String toString() {
        return titulo + " [" + status + "]";
    }
}
