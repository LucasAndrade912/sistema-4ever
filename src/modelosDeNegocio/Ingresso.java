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

    public double calcularPreco() {
        double preco = evento.getPreco();
        int idade = participante.calcularIdade();
        double desconto = 1;

        if (idade < 18) desconto += 0.1;
        else if (idade >= 60) desconto += 0.2;

        if (participante instanceof Convidado) desconto += 0.5;

        return preco * desconto;
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
