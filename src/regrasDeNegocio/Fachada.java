package regrasDeNegocio;

import repositorio.Repositorio;

public class Fachada {
    private static Repositorio repositorio = new Repositorio();

    public static void criarEvento(data,descrição,capacidade, preco) throws Exception {
        if (preco < 0):
            throw new Exception("Preço inválido!! Coloque um preço maior ou igual a zero.");
        elif (data == null or descricao == null):
            throw new Exception("A data e a descrição são obrigatórias!!");
        elif (capacidade < 2):
            throw new Exception("A capacidade do evento tem que ser maior que 2!!");

        Evento e = new Evento(data, descricao, capacidade, preco);
        Repositorio.adicionarEvento(e);
    }


}
