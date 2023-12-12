package modelosDeNegocio;

public class Ingresso {
    private String codigo;
    private String telefone;
    private Evento evento = null;
    private Participante participante = null;

    public Ingresso(String codigo, String telefone, Evento evento, Participante participante) {
        this.codigo = codigo;
        this.telefone = telefone;
        this.evento = evento;
        this.participante = participante;
    }

    public Ingresso(String codigo, String telefone) {
        this.codigo = codigo;
        this.telefone = telefone;
    }

    public double calcularPreco() {
        double preco = evento.getPreco();
        int idade = participante.calcularIdade();
        double desconto = 0;

        if (idade < 18) desconto += 0.1;
        else if (idade >= 60) desconto += 0.2;

        if (participante instanceof Convidado) desconto += 0.5;

        return preco - preco * desconto;
    }

    public String getCodigo() {
        return codigo;
    }

    public Evento getEvento() {
        return evento;
    }

    public String getTelefone() {
        return telefone;
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

    @Override
    public String toString() {
        return "Ingresso{" +
                "codigo='" + codigo + '\'' +
                ", telefone='" + telefone + '\'' +
                ", evento=" + evento.getId() +
                ", participante=" + participante.getCpf() +
                '}';
    }
}
