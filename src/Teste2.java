/**
 * SI - POO - Prof. Fausto Ayres
 * Teste da Fachada
 *
 */

import modelosDeNegocio.Participante;
import regrasDeNegocio.Fachada;

public class Teste2 {
    public static void main (String[] args) {
        //dados para teste
        String cpf1 = "91";
        String cpf2 = "92";
        String cpf3 = "93";
        int id=1;
        try {

            Fachada.criarParticipante(cpf1,"01/01/2000");
            Fachada.criarParticipante(cpf2,"01/01/2000");
            Fachada.criarConvidado(cpf3,"01/01/2000", "xxxxx");
            Fachada.criarEvento("01/01/2024", "teste", 2, 2);
        }catch (Exception e) {System.out.println("dados de teste--->"+e.getMessage());}

        System.out.println("\n-------TESTE DE EXCEÇÕES LANÇADAS PELOS METODOS DA FACHADA--------");
        try {
            Fachada.criarParticipante(cpf1,"01/01/2000");
            Fachada.criarParticipante(cpf1,"01/01/2000");
            System.out.println("*************1--->Nao lançou exceção para: criar participante duplicado ");
        }catch (Exception e) {System.out.println("1ok--->"+e.getMessage());}

        try {
            Fachada.criarParticipante("1000","");
            System.out.println("*************2--->Nao lançou exceção para: criar participante sem data ");
        }catch (Exception e) {System.out.println("2ok--->"+e.getMessage());}

        try {
            Fachada.criarConvidado("1001","01/01/2000", "");
            System.out.println("*************3--->Nao lançou exceção para: criar convidado sem empresa");
        }catch (Exception e) {System.out.println("3ok--->"+e.getMessage());}

        try {
            Fachada.criarEvento("", "teste", 10, 10);
            System.out.println("*************4--->Nao lançou exceção para: criar evento sem data");
        }catch (Exception e) {System.out.println("4ok--->"+e.getMessage());}

        try {
            Fachada.criarEvento("01/01/2000", "", 10, 10);
            System.out.println("*************5--->Nao lançou exceção para: criar evento sem descricao");
        }catch (Exception e) {System.out.println("5ok--->"+e.getMessage());}

        try {
            Fachada.criarEvento("01/01/2000", "teste", 1, 10);
            System.out.println("*************6--->Nao lançou exceção para: criar evento sem capacidade minima");
        }catch (Exception e) {System.out.println("6ok--->"+e.getMessage());}

        try {
            Fachada.criarEvento("01/01/2000", "teste", 2, -1);
            System.out.println("*************7--->Nao lançou exceção para: criar evento com preco negativo");
        }catch (Exception e) {System.out.println("7ok--->"+e.getMessage());}

        try {
            Fachada.criarIngresso(99,cpf1, "999999999");
            System.out.println("*************8--->Nao lançou exceção para: criar ingresso com id inexistente");
        }catch (Exception e) {System.out.println("8ok--->"+e.getMessage());}

        try {
            Fachada.criarIngresso(id,"9999000", "999999999");
            System.out.println("*************9--->Nao lançou exceção para: criar ingresso com cpf inexistente");
        }catch (Exception e) {System.out.println("9ok--->"+e.getMessage());}

        try {
            Fachada.criarIngresso(id,cpf1, "");
            System.out.println("*************10--->Nao lançou exceção para: criar ingresso com telefone inexistente");
        }catch (Exception e) {System.out.println("10ok--->"+e.getMessage());}

        try {
            Fachada.criarIngresso(id,cpf1, "999999999");
            Fachada.criarIngresso(id,cpf1, "999999999");
            System.out.println("*************11--->Nao lançou exceção para: criar ingresso duplicado");
        }catch (Exception e) {System.out.println("11ok--->"+e.getMessage());}

        try {
            Fachada.criarIngresso(id,cpf2, "999999999");
            Fachada.criarIngresso(id,cpf3, "999999999");
            System.out.println("*************12--->Nao lançou exceção para: criar ingresso alem da capacidade");
        }catch (Exception e) {System.out.println("12ok--->"+e.getMessage());}

        try {
            Fachada.apagarEvento(id);
            System.out.println("*************13--->Nao lançou exceção para: apagar evento ainda com ingresso");
        }catch (Exception e) {System.out.println("13ok--->"+e.getMessage());}

        try {
            Fachada.apagarParticipante(cpf1);
            System.out.println("*************14--->Nao lançou exceção para: apagar participante com ingresso em uso");
        }catch (Exception e) {System.out.println("14ok--->"+e.getMessage());}

    }
}
