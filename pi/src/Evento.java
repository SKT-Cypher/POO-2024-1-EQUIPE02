import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class Evento {
    private String nome;
    private String localizacao;
    private Date data;
    private Date horarioEvento;
    private Map<String, Ingresso> ingressos;

    public Evento(String nome, String localizacao, Date data, Date horarioEvento) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.data = data;
        this.horarioEvento = horarioEvento;
        this.ingressos = new HashMap<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getHorarioEvento() {
        return horarioEvento;
    }

    public void setHorarioEvento(Date horarioEvento) {
        this.horarioEvento = horarioEvento;
    }

    // Métodos para gerenciar ingressos

    public void adicionarIngresso(String tipo, double preco, int quantidadeDisponivel, int quantidadeInicial, double desconto) {
        Ingresso ingresso = new Ingresso(tipo, preco, quantidadeDisponivel, quantidadeInicial, desconto);
        ingressos.put(tipo, ingresso);
    }

    public boolean comprarIngresso(String tipo) {
        Ingresso ingresso = ingressos.get(tipo);
        if (ingresso != null && ingresso.getQuantidadeDisponivel() > 0) {
            ingresso.setQuantidadeDisponivel(ingresso.getQuantidadeDisponivel() - 1);
            return true;
        }
        return false;
    }

    public void emitirIngressoEletronico(Usuario cliente, String tipoIngresso) {
        Ingresso ingresso = ingressos.get(tipoIngresso);
        if (ingresso != null) {
        	JOptionPane.showMessageDialog(null, "Ingresso eletrônico emitido para " + cliente.getNome());

        } else {
            System.out.println("Tipo de ingresso não encontrado.");
        }
    }

    public void gerarRelatorioVendas() {
    	String texto = "";
        for (Map.Entry<String, Ingresso> entry : ingressos.entrySet()) {
            Ingresso ingresso = entry.getValue();
            texto += "Tipo de ingresso: " + ingresso.getTipo()+"\n";
            texto += "Quantidade Vendida: " + (ingresso.getQuantidadeInicial() - ingresso.getQuantidadeDisponivel())+"\n";
            texto += "Receita Gerada: R$ " + (ingresso.getQuantidadeInicial() - ingresso.getQuantidadeDisponivel()) * ingresso.getPreco()+"\n";
            texto += "---------------------------\n";
        }
        JOptionPane.showMessageDialog(null, texto);
    }
}
