package regrasDeNegocio;

import modelosDeNegocio.Convidado;
import modelosDeNegocio.Evento;
import modelosDeNegocio.Ingresso;
import modelosDeNegocio.Participante;
import repositorio.Repositorio;

public class Fachada {
    private static Repositorio repositorio = new Repositorio();

    public static void criarEvento(String data, String descricao, int capacidade, double preco) throws Exception {
        if (preco < 0)
            throw new Exception("Preço inválido!! Coloque um preço maior ou igual a zero.");
        if (data.isEmpty() || descricao.isEmpty())
            throw new Exception("A data e a descrição são obrigatórias!!");
        if (capacidade < 2)
            throw new Exception("A capacidade do evento tem que ser maior que 2!!");

        Evento novoEvento = new Evento(repositorio.gerarId(), data, descricao, capacidade, preco);
        repositorio.adicionar(novoEvento);
    }

    public static void criarParticipante(String cpf, String nascimento) throws Exception {
        if (repositorio.localizarParticipante(cpf) != null)
            throw new Exception("Já existe um participante com este cpf. Informe outro.");

        Participante novoParticipante = new Participante(cpf, nascimento);
        repositorio.adicionar(novoParticipante);
    }

    public static void criarConvidado(String cpf, String nascimento, String empresa) throws Exception {
        if (repositorio.localizarParticipante(cpf) != null)
            throw new Exception("Já existe um convidado com este cpf. Informe outro.");

        Convidado novoConvidado = new Convidado(cpf, nascimento, empresa);
        repositorio.adicionar(novoConvidado);
    }

    public static void criarIngresso(int id, String cpf, String telefone) throws Exception {
        if (telefone.isEmpty()) throw new Exception("O telefone é obrigatório!!");

        Evento evento = repositorio.localizarEvento(id);
        Participante participante = repositorio.localizarParticipante(cpf);

        if (evento == null)
            throw new Exception("Evento inexistente. Por favor informe um evento existente.");

        if (participante == null)
            throw new Exception("Participante inexistente. Por favor informe um participante existente.");

        if (evento.lotado()) throw new Exception("O evento já está cheio, tente outro evento.");

        String codigo = id + "-" + cpf;
        Ingresso novoIngresso = new Ingresso(codigo, telefone);
    }
}
