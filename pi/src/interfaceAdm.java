import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.sql.*;

import javax.swing.JOptionPane;

public class interfaceAdm {
	static Connection cn;
	public interfaceAdm(Connection cn) {
		this.cn = cn;
	}
	
	public static void main(String[] args) throws SQLException {
		
	}
public void exibirInterface(Usuario usuario) {
	boolean usando = true;
    while (usando) {
        int opcao = Integer.parseInt(JOptionPane.showInputDialog(null, "===== Menu =====      Administrador: "+usuario.getNome()+"\n\n1. Criar Evento\n2. Adicionar Ingresso\n3. Gerar Relatório de Vendas\n0. Voltar"));
        switch (opcao) {
            case 1:
                criarEvento();
                break;
            case 2:
                adicionarIngresso();
                break;
            case 3:
                gerarRelatorioVendas();
                break;
            case 0:
            	usando = false;
            	break;
            default:
            	JOptionPane.showMessageDialog(null, "Opção inválida, tente novamente.");
                break;
        }
    }
}

private static void criarEvento() {
	String textoFinal;
	boolean formatado = false;
	Date dataEvento = null;
	Date horaInicio = null;
	Date horaFim = null;
	String nomeEvento = JOptionPane.showInputDialog("Nome do Evento:");
    String localizacao = JOptionPane.showInputDialog("Localização:");
    
    do {
    String dataStr = JOptionPane.showInputDialog("Data do Evento (dd/mm/aaaa):");
    try {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        dataEvento = sdf.parse(dataStr);
        formatado = true;
    } catch (Exception e) {
    	JOptionPane.showMessageDialog(null, "Data inválida. Use o formato dd/mm/aaaa.");
    }
    } while (!formatado);
    formatado = false;
    
    do {
    String horario = JOptionPane.showInputDialog("Horário de Início do Evento (HH:mm):");
    try {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        horaInicio = sdf.parse(horario);
        formatado = true;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Horário inválido. Use o formato HH:mm.");
    }
    } while (!formatado);
    
    do {
        String horario = JOptionPane.showInputDialog("Horário de Fim do Evento (HH:mm):");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            horaFim = sdf.parse(horario);
            formatado = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Horário inválido. Use o formato HH:mm.");
        }
        } while (!formatado);
    
    try {
		PreparedStatement ps = cn.prepareStatement(
				"INSERT INTO eventos (nome, localizacao, data, horarioComeco, horarioFim) VALUES (?, ?, ?, ?, ?)");
		ps.setString(1, nomeEvento);
		ps.setString(2, localizacao);
		ps.setDate(3, new java.sql.Date(dataEvento.getTime())); // Correctly convert Date to java.sql.Date
		ps.setTime(4, new java.sql.Time(horaInicio.getTime())); // Correctly convert Date to java.sql.Time
		ps.setTime(5, new java.sql.Time(horaFim.getTime()));
		ps.executeUpdate();
		textoFinal = "Evento criado com sucesso!";
	} catch (SQLException e) {
		e.printStackTrace();
		textoFinal = "Ocorreu um erro ao criar o evento.";
	}
    JOptionPane.showMessageDialog(null, textoFinal);
}

private static void adicionarIngresso() {
	boolean eventoExiste = false;
	
	try {
	Statement st = cn.createStatement();
	PreparedStatement ps = cn.prepareStatement(
			"SELECT * FROM eventos");
	ResultSet rs = ps.executeQuery();
	
	if (rs.next()) {
		eventoExiste = true;
	}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
    if (eventoExiste)  {
        String tipoIngresso = JOptionPane.showInputDialog("Tipo do Ingresso:");
        double precoIngresso = Double.parseDouble(JOptionPane.showInputDialog("Preço do Ingresso:"));
        int quantidadeIngresso = Integer.parseInt(JOptionPane.showInputDialog("Quantidade disponível:"));
        //evento.adicionarIngresso(tipoIngresso, precoIngresso, quantidadeIngresso);
        JOptionPane.showMessageDialog(null, "Ingresso adicionado com sucesso!");
    } else {
        JOptionPane.showMessageDialog(null, "Crie um evento antes de adicionar ingressos.");
   }
}

private static void gerarRelatorioVendas() {
   /* if (evento != null) {
        evento.gerarRelatorioVendas();
    } else {
    	JOptionPane.showMessageDialog(null, "Não há eventos para gerar relatório de vendas.");
    }*/
}
}



