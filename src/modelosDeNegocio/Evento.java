package modelosDeNegocio;

import java.util.ArrayList;

public class Evento {
    private int id;
    private String data;
    private String descricao;
    private int capacidade;
    private double preco;
    private ArrayList<Ingresso> ingressos = new ArrayList<>();

    public Evento(int id, String data, String descricao, int capacidade, double preco) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.capacidade = capacidade;
        this.preco = preco;
    }

    public boolean lotado() {
        return this.ingressos.size() == this.capacidade;
    }

    public int quantidadeIngressos() {
        return this.ingressos.size();
    }

    public double totaArrecadado() {
        return quantidadeIngressos() * this.preco;
    }

    public void adicionar(Ingresso novoIngresso) {
        this.ingressos.add(novoIngresso);
        novoIngresso.setEvento(this);
    }

    public void remover(Ingresso ingresso) {
        this.ingressos.remove(ingresso);
        ingresso.setEvento(null);
    }

    public int getId() {
        return id;
    }

    public double getPreco() {
        return preco;
    }
}
