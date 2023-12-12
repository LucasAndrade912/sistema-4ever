package regrasDeNegocio;

import modelosDeNegocio.Convidado;
import modelosDeNegocio.Evento;
import modelosDeNegocio.Ingresso;
import modelosDeNegocio.Participante;
import repositorio.Repositorio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

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

        if (nascimento.isEmpty()) throw new Exception("Forneça a data de nascimento do participante.");

        Participante novoParticipante = new Participante(cpf, nascimento);
        repositorio.adicionar(novoParticipante);
    }

    public static void criarConvidado(String cpf, String nascimento, String empresa) throws Exception {
        if (repositorio.localizarParticipante(cpf) != null)
            throw new Exception("Já existe um convidado com este cpf. Informe outro.");

        if (nascimento.isEmpty()) throw new Exception("Forneça a data de nascimento do convidado.");

        if (empresa.isEmpty()) throw new Exception("Forneça a empresa do convidado.");

        Convidado novoConvidado = new Convidado(cpf, nascimento, empresa);
        repositorio.adicionar(novoConvidado);
    }

    public static void criarIngresso(int id, String cpf, String telefone) throws Exception {
        if (repositorio.localizarIngresso(id + "-" + cpf) != null)
            throw new Exception("Ingresso já criado, por favor informe outro ID ou CPF.");

        if (telefone.isEmpty()) throw new Exception("O telefone é obrigatório!!");

        Evento evento = repositorio.localizarEvento(id);
        Participante participante = repositorio.localizarParticipante(cpf);

        if (evento == null)
            throw new Exception("Evento inexistente. Por favor informe um evento existente.");

        if (participante == null)
            throw new Exception("Participante inexistente. Por favor informe um participante existente.");

        if (evento.lotado()) throw new Exception("O evento " + evento.getId() + " já está cheio, tente outro evento.");

        String codigo = id + "-" + cpf;
        Ingresso novoIngresso = new Ingresso(codigo, telefone);
        repositorio.adicionar(novoIngresso);

        evento.adicionar(novoIngresso);
        participante.adicionar(novoIngresso);
    }

    public static void apagarEvento(int id) throws Exception {
        Evento e = repositorio.localizarEvento(id);

        if (Objects.isNull(e))
            throw new Exception("O ID passado não corresponde a nenhum evento.");

        if (e.quantidadeIngressos() > 0)
            throw new Exception("O evento só pode ser apagado caso não tenha ingresso.");

        repositorio.apagar(e);
    }

    public static void apagarParticipante(String cpf) throws Exception {
        Participante participante = repositorio.localizarParticipante(cpf);

        if (Objects.isNull(participante))
            throw new Exception("O cpf passado não corresponde a nenhum participante.");

        LocalDate hoje = LocalDate.now();
        ArrayList<Ingresso> ingressos = participante.getIngressos();

        if (!ingressos.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            Ingresso ultimoIngresso = ingressos.get(ingressos.size() - 1);
            LocalDate dataUltimoIngresso = LocalDate.parse(ultimoIngresso.getEvento().getData(), formatter);

            if (hoje.isBefore(dataUltimoIngresso)) // data do último ingresso ainda não foi ultrapassada
                throw new Exception("O participante ainda tem ingresso em uso.");

            for (Ingresso ingresso : ingressos) {
                Evento evento = ingresso.getEvento();
                evento.remover(ingresso);
                repositorio.apagar(ingresso);
            }

            repositorio.apagar(participante);
        }
    }

    public static void apagarIngresso(String codigo) throws Exception {
        Ingresso ingresso = repositorio.localizarIngresso(codigo);

        if (Objects.isNull(ingresso))
            throw new Exception("O ingresso passado não corresponde a nenhum ingresso existente.");

        Evento evento = ingresso.getEvento();
        Participante participante = ingresso.getParticipante();
        evento.remover(ingresso);
        participante.remover(ingresso);
        repositorio.apagar(ingresso);
    }

    public static ArrayList<Evento> listarEventos() {
        return repositorio.getEventos();
    }

    public static ArrayList<Participante> listarParticipantes() {
        return repositorio.getParticipantes();
    }

    public static ArrayList<Ingresso> listarIngressos() {
        return repositorio.getIngressos();
    }
}
