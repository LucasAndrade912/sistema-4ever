package repositorio;

import modelosDeNegocio.Evento;
import modelosDeNegocio.Ingresso;
import modelosDeNegocio.Participante;

import java.util.ArrayList;

public class Repositorio {
    private ArrayList<Evento> eventos = new ArrayList<>();
    private ArrayList<Participante> participantes = new ArrayList<>();
    private ArrayList<Ingresso> ingressos = new ArrayList<>();
    private int id;

    public void adicionarEvento(String data, String descricao, int capacidade, double preco) {
        eventos.add(new Evento(gerarID(), data, descricao, capacidade, preco));
    }

    public void apagarEvento(int id) {

    }

    public Evento localizarEvento(int id) {
        for(Evento evento : eventos)
            if (evento.getId() == id) {
                return evento;
            }
        return null;
    }

    private int gerarID() {
        if (this.eventos == null)
            return 0;
        Evento e = eventos.get(eventos.size() - 1);
        return e.getId() + 1;
    }
}
