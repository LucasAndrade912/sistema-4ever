package modelosDeNegocio;

import java.util.ArrayList;

public class Participante {
    private String cpf;
    private String nascimento;
    private ArrayList<Ingresso> ingressos = new ArrayList<>();

    public Participante(String cpf, String nascimento) {
        this.cpf = cpf;
        this.nascimento = nascimento;
    }

    public String getCpf() {
        return cpf;
    }
}
