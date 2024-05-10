import java.util.Scanner;
import java.util.Date;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.sql.*;

public class Main {
    static Evento evento;
    static ConectaMySQL conexao = new ConectaMySQL();
	static Connection cn = conexao.openDB();
    static interfaceAdm adm = new interfaceAdm(cn);
    static interfaceUsuario usuario = new interfaceUsuario();
    
    public static void main(String[] args) throws SQLException {

		Statement st = cn.createStatement();
    	
        while (true) {
            int opcao = Integer.parseInt(JOptionPane.showInputDialog(null, "1. Cadastro\n2.Login\n0.Sair")); {
            	switch (opcao) {
            	case 1:
            		cadastro(cn);
            		break;
            	case 2:
            		login(cn);
            		break;
            	case 0:
            		JOptionPane.showMessageDialog(null, "Saindo...");
                    System.exit(0);
            	 default:
                 	JOptionPane.showMessageDialog(null, "Opção inválida, tente novamente.");
                    break;
            	}
            }
        }
            
            /*int opcao = Integer.parseInt(JOptionPane.showInputDialog(null, "Escolha como quer entrar\n\n1. Administrador\n2. Usuário\n0. Sair"));
            switch (opcao) {
                case 1:
                	interfaceAdm adm = new interfaceAdm();
                    adm.exibirInterface();
                    
                    break;
                case 2:
                	interfaceUsuario usuario = new interfaceUsuario(evento);
                    usuario.exibirInterface();
                    break;
              
                case 0:
                	JOptionPane.showMessageDialog(null, "Saindo...");
                    System.exit(0);
                default:
                	JOptionPane.showMessageDialog(null, "Opção inválida, tente novamente.");
                    break;
            }*/
        }
        
    

    /*private static void exibirMenu() {
        System.out.println("===== Menu =====");
        System.out.println("1. Criar Evento");
        System.out.println("2. Adicionar Ingresso");
        System.out.println("3. Comprar Ingresso");
        System.out.println("4. Emitir Ingresso Eletrônico");
        System.out.println("5. Gerar Relatório de Vendas");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }*/
    
    private static void cadastro(Connection cn) {
    	int tipoConta = Integer.parseInt(JOptionPane.showInputDialog(null, "Tipo de conta:\n1. Usuário\n2.Administrador"));
    	String nome = JOptionPane.showInputDialog(null, "Nome:");
    	String email = JOptionPane.showInputDialog(null, "E-mail:");
    	String CPF = JOptionPane.showInputDialog(null, "CPF:");
    	String senha = JOptionPane.showInputDialog(null, "Senha:");
    	boolean sucesso;
    	
    	
    	try {
    		PreparedStatement ps = cn.prepareStatement(
					"INSERT INTO usuarios (nome, email, CPF, senha, ADM) VALUES (?, ?, ?, ?, ?)");
    		ps.setString(1, nome);
			ps.setString(2, email);
			ps.setString(3, CPF);
			ps.setString(4, senha);
			ps.setBoolean(5, (tipoConta == 1) ? false : true);
			ps.executeUpdate();
			sucesso = true;
		} catch (SQLException e) {
			e.printStackTrace();
			sucesso = false;
		}
    	
    	Usuario user = new Usuario(nome, email, CPF, senha);
    	JOptionPane.showMessageDialog(null, (sucesso) ? "Conta criada com sucesso!" : "Ocorreu um erro ao criar a conta.");
    	if (sucesso && tipoConta == 1) {
    		usuario.exibirInterface(user);
    	} else if (sucesso) {
    		adm.exibirInterface(user);
    	}
    }
    
    private static void login(Connection cn) {
    	boolean contaExiste = false;
    	String email = JOptionPane.showInputDialog(null, "E-mail:");
    	String senha = JOptionPane.showInputDialog(null, "Senha:");
    	Statement st;
		try {
			st = cn.createStatement();
			PreparedStatement ps = cn.prepareStatement(
					"SELECT * FROM usuarios WHERE email = ? AND senha = ?");
			ps.setString(1, email);
			ps.setString(2, senha);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "Login feito com sucesso.");
                contaExiste = true;
                Usuario user = new Usuario(rs.getString("nome"), rs.getString("email"), rs.getString("CPF"), rs.getString("senha"));
                if (rs.getBoolean("ADM")) {
                	adm.exibirInterface(user);
                } else {
                	usuario.exibirInterface(user);
                }
            } else {
			
			JOptionPane.showMessageDialog(null, "E-mail ou senha incorreto(s).");
			
            }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }

    private static void criarEvento() {
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
        
        
        
        //evento = new Evento(nomeEvento, localizacao, dataEvento, horaEvento);
        JOptionPane.showMessageDialog(null, "Evento criado com sucesso!");
    }

    private static void adicionarIngresso(Scanner scanner) {
        if (evento != null) {
            String tipoIngresso = JOptionPane.showInputDialog("Tipo do Ingresso:");
            double precoIngresso = Double.parseDouble(JOptionPane.showInputDialog("Preço do Ingresso:"));
            int quantidadeIngresso = Integer.parseInt(JOptionPane.showInputDialog("Quantidade disponível:"));
            evento.adicionarIngresso(tipoIngresso, precoIngresso, quantidadeIngresso, 0, 0);
            JOptionPane.showMessageDialog(null, "Ingresso adicionado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Crie um evento antes de adicionar ingressos.");
        }
    }

    private static void comprarIngresso(Scanner scanner) {
        if (evento != null) {
            String tipoIngresso = JOptionPane.showInputDialog("Tipo do Ingresso:");

            boolean ingressoComprado = evento.comprarIngresso(tipoIngresso);
            if (ingressoComprado) {
            	JOptionPane.showMessageDialog(null, "Ingresso comprado com sucesso!");
            } else {
            	JOptionPane.showMessageDialog(null, "Ingresso do tipo '" + tipoIngresso + "' indisponível.");
            }
        } else {
        	JOptionPane.showMessageDialog(null, "Crie um evento antes de comprar ingressos.");
        }
    }

    /*private static void emitirIngressoEletronico(Scanner scanner) {
        if (evento != null) {
            String nomeCliente = JOptionPane.showInputDialog("Nome do Cliente:");
            String emailCliente = JOptionPane.showInputDialog("Email do Cliente:");
            Usuario cliente = new Usuario(nomeCliente, emailCliente);

            String tipoIngresso = JOptionPane.showInputDialog("Tipo do Ingresso:");

            evento.emitirIngressoEletronico(cliente, tipoIngresso);
        } else {
        	JOptionPane.showMessageDialog(null, "Crie um evento antes de emitir ingressos eletrônicos.");
        }
    }*/

    private static void gerarRelatorioVendas() {
        if (evento != null) {
            evento.gerarRelatorioVendas();
        } else {
        	JOptionPane.showMessageDialog(null, "Não há eventos para gerar relatório de vendas.");
        }
    }
}
