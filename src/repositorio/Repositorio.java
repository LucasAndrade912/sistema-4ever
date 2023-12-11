package repositorio;

import modelosDeNegocio.Evento;
import modelosDeNegocio.Ingresso;
import modelosDeNegocio.Participante;

import java.util.ArrayList;

public class Repositorio {
    private ArrayList<Evento> eventos = new ArrayList<>();
    private ArrayList<Participante> participantes = new ArrayList<>();
    private ArrayList<Ingresso> ingressos = new ArrayList<>();

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }

    public ArrayList<Ingresso> getIngressos() {
        return ingressos;
    }

    public void adicionar(Evento evento) {
        eventos.add(evento);
    }

    public void apagar(Evento evento) {
        eventos.remove(evento);
    }

    public Evento localizarEvento(int id) {
        for (Evento evento : eventos)
            if (evento.getId() == id) return evento;

        return null;
    }


    public void adicionar(Ingresso ingresso) {
        ingressos.add(ingresso);
    }

    public void apagar(Ingresso ingresso) {
        ingressos.remove(ingresso);
    }

    public Ingresso localizarIngresso(String codigo) {
        for (Ingresso ingresso : ingressos)
            if (ingresso.getCodigo().equals(codigo)) return ingresso;

        return null;
    }


    public void adicionar(Participante participante) {
        participantes.add(participante);
    }

    public void apagar(Participante participante) {
        participantes.remove(participante);
    }

    public Participante localizarParticipante(String cpf) {
        for (Participante participante : participantes)
            if (participante.getCpf().equals(cpf)) return participante;

        return null;
    }

    public int gerarId() {
        if (this.eventos == null) return 1;

        Evento evento = eventos.get(eventos.size() - 1);
        return evento.getId() + 1;
    }
}
