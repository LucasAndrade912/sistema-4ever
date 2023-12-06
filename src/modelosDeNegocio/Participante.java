package modelosDeNegocio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Participante {
    private String cpf;
    private String nascimento;
    private ArrayList<Ingresso> ingressos = new ArrayList<>();

    public Participante(String cpf, String nascimento) {
        this.cpf = cpf;
        this.nascimento = nascimento;
    }

    public int calcularIdade() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        int anoNascimento = LocalDate.parse(this.nascimento, formatter).getYear();
        int anoAtual = LocalDate.now().getYear();

        return anoAtual - anoNascimento;
    }

    public void adicionar(Ingresso novoIngresso) {
        this.ingressos.add(novoIngresso);
        novoIngresso.setParticipante(this);
    }

    public String getCpf() {
        return cpf;
    }

    public ArrayList<Ingresso> getIngressos() {
        return ingressos;
    }
}
