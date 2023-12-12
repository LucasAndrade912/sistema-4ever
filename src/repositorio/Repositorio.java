package repositorio;

import modelosDeNegocio.Convidado;
import modelosDeNegocio.Evento;
import modelosDeNegocio.Ingresso;
import modelosDeNegocio.Participante;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Repositorio {
    private ArrayList<Evento> eventos = new ArrayList<>();
    private ArrayList<Participante> participantes = new ArrayList<>();
    private ArrayList<Ingresso> ingressos = new ArrayList<>();

    public Repositorio() {
        carregarObjetos();
    }

    public void adicionar(Evento evento) {
        eventos.add(evento);
        salvarObjetos();
    }

    public void apagar(Evento evento) {
        eventos.remove(evento);
        salvarObjetos();
    }

    public Evento localizarEvento(int id) {
        for (Evento evento : eventos)
            if (evento.getId() == id) return evento;

        return null;
    }

    public void adicionar(Ingresso ingresso) {
        ingressos.add(ingresso);
        salvarObjetos();
    }

    public void apagar(Ingresso ingresso) {
        ingressos.remove(ingresso);
        salvarObjetos();
    }

    public Ingresso localizarIngresso(String codigo) {
        for (Ingresso ingresso : ingressos)
            if (ingresso.getCodigo().equals(codigo)) return ingresso;

        return null;
    }

    public void adicionar(Participante participante) {
        participantes.add(participante);
        salvarObjetos();
    }

    public void apagar(Participante participante) {
        participantes.remove(participante);
        salvarObjetos();
    }

    public Participante localizarParticipante(String cpf) {
        for (Participante participante : participantes)
            if (participante.getCpf().equals(cpf)) return participante;

        return null;
    }

    public int gerarId() {
        if (this.eventos.isEmpty()) return 1;

        Evento evento = eventos.get(eventos.size() - 1);
        return evento.getId() + 1;
    }

    private void carregarObjetos() {
        try {
            File f1 = new File( new File("eventos.csv").getCanonicalPath() ) ;
            File f2 = new File( new File("participantes.csv").getCanonicalPath() ) ;
            File f3 = new File( new File("ingressos.csv").getCanonicalPath() ) ;
            if (!f1.exists() || !f2.exists() || !f3.exists()) {
                FileWriter arquivo1 = new FileWriter(f1); arquivo1.close();
                FileWriter arquivo2 = new FileWriter(f2); arquivo2.close();
                FileWriter arquivo3 = new FileWriter(f3); arquivo3.close();
                return;
            }
        }
        catch(Exception ex)		{
            throw new RuntimeException("criacao dos arquivos vazios:"+ex.getMessage());
        }

        String linha;
        String[] partes;
        Evento evento;
        Participante participante;

        try	{
            String data, descricao, id, capacidade, preco ;
            File f = new File( new File("eventos.csv").getCanonicalPath() )  ;
            Scanner arquivo1 = new Scanner(f);
            while(arquivo1.hasNextLine()) 	{
                linha = arquivo1.nextLine().trim();
                partes = linha.split(";");
                id = partes[0];
                data = partes[1];
                descricao = partes[2];
                capacidade = partes[3];
                preco = partes[4];
                evento = new Evento(Integer.parseInt(id), data, descricao,
                        Integer.parseInt(capacidade), Double.parseDouble(preco));
                this.adicionar(evento);
            }
            arquivo1.close();
        }
        catch(Exception ex)		{
            throw new RuntimeException("leitura arquivo de eventos:"+ex.getMessage());
        }

        try	{
            String cpf, nascimento, empresa, listaId;
            File f = new File( new File("participantes.csv").getCanonicalPath())  ;
            Scanner arquivo2 = new Scanner(f);
            while(arquivo2.hasNextLine()) 	{
                linha = arquivo2.nextLine().trim();
                partes = linha.split(";");
                cpf = partes[0];
                nascimento = partes[1];
                if(partes.length==2) {
                    participante = new Participante(cpf,nascimento);
                    this.adicionar(participante);
                }
                else {
                    empresa = partes[2];
                    participante = new Convidado(cpf,nascimento,empresa);
                    this.adicionar(participante);
                }

            }
            arquivo2.close();
        }
        catch(Exception ex)		{
            throw new RuntimeException("leitura arquivo de participantes:"+ex.getMessage());
        }

        try	{
            String codigo, telefone,cpf;
            int id;
            Ingresso ingresso;
            File f = new File( new File("ingressos.csv").getCanonicalPath())  ;
            Scanner arquivo3 = new Scanner(f);
            while(arquivo3.hasNextLine()) 	{
                linha = arquivo3.nextLine().trim();
                partes = linha.split(";");
                codigo = partes[0];
                telefone = partes[1];
                id = Integer.parseInt(codigo.split("-")[0]);
                cpf = codigo.split("-")[1];
                evento = this.localizarEvento(id);
                participante = this.localizarParticipante(cpf);

                ingresso = new Ingresso(codigo,telefone,evento,participante);
                evento.adicionar(ingresso);
                participante.adicionar(ingresso);
                this.adicionar(ingresso);
            }
            arquivo3.close();
        }
        catch(Exception ex)		{
            throw new RuntimeException("leitura arquivo de participantes:"+ex.getMessage());
        }
    }

    private void salvarObjetos() {
        try	{
            File f = new File( new File("eventos.csv").getCanonicalPath());
            FileWriter arquivo1 = new FileWriter(f);
            for(Evento e : eventos) {
                arquivo1.write(e.getId()+";"+e.getData()+";"+e.getDescricao()+";"+e.getCapacidade()+";"+e.getPreco()+"\n");
            }
            arquivo1.close();
        }
        catch(Exception e){
            throw new RuntimeException("problema na criação do arquivo  eventos "+e.getMessage());
        }

        try	{
            File f = new File( new File("participantes.csv").getCanonicalPath())  ;
            FileWriter arquivo2 = new FileWriter(f) ;
            for(Participante p : participantes) {
                if(p instanceof Convidado c )
                    arquivo2.write(p.getCpf() +";" + p.getNascimento() +";" + c.getEmpresa()+"\n");
                else
                    arquivo2.write(p.getCpf() +";" + p.getNascimento() +"\n");

            }
            arquivo2.close();
        }
        catch (Exception e) {
            throw new RuntimeException("problema na criação do arquivo  participantes "+e.getMessage());
        }
        try	{
            File f = new File( new File("ingressos.csv").getCanonicalPath())  ;
            FileWriter arquivo3 = new FileWriter(f) ;
            for(Ingresso i : this.getIngressos()) {
                arquivo3.write(i.getCodigo() +";" + i.getTelefone()+"\n");

            }
            arquivo3.close();
        }
        catch (Exception e) {
            throw new RuntimeException("problema na criação do arquivo  participantes "+e.getMessage());
        }

    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }

    public ArrayList<Ingresso> getIngressos() {
        return ingressos;
    }
}
