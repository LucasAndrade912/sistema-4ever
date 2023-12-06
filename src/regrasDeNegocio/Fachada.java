package regrasDeNegocio;

import repositorio.Repositorio;

public class Fachada {
    private static Repositorio repositorio = new Repositorio();

    public static void criarEvento(String data, String descricao, int capacidade, double preco) throws Exception {
        if (preco < 0)
            throw new Exception("Preço inválido!! Coloque um preço maior ou igual a zero.");
        else if (data == null || descricao == null)
            throw new Exception("A data e a descrição são obrigatórias!!");
        else if (capacidade < 2)
            throw new Exception("A capacidade do evento tem que ser maior que 2!!");

        repositorio.adicionarEvento(data, descricao, capacidade, preco);
    }
}
