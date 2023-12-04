package repositorio;

public class Repositorio {
    private ArrayList<Evento> eventos = new ArrayList<>();
    private ArrayList<Participante> participantes = new ArrayList<>();
    private ArrayList<Ingresso> ingressos = new ArrayList<>();

    public void criarEvento(data,descrição,capacidade, preco) {
        eventos.add(Evento evento = new Evento(String data, String decricao, int capacidade, double preco));
    }
    public void apagarEvento(id){
        eventos.remove(evento);
    }
    public Evento localizarEvento(int id) {
        for(Evento i : eventos)
            if (evento.getId() == id) {
                return i;
            }
        return null;
    }
}
