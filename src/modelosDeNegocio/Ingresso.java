package modelosDeNegocio;

public class Ingresso {
    private String codigo;
    private String telefone;
    private Evento evento;
    private Participante participante;

    public Ingresso(String codigo, String telefone) {
        this.codigo = codigo;
        this.telefone = telefone;
    }

    public String getCodigo() {
        return codigo;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }
}
