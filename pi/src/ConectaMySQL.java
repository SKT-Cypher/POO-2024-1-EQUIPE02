import java.sql.*;
public class ConectaMySQL {
	private final static String url = "jdbc:mysql://localhost:3306/banco_evento";
	private final static String username = "root"; //insira o nome de usuario do seu mysql workbench
	private final static String password = ""; //insira a senha de usuario do seu mysql workbench, se não tiver deixe vazio
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private String nome = null, telefone = null;
	private double nota;
	public static void main(String args[]){
		ConectaMySQL b = new ConectaMySQL();
		b.openDB();
		b.closeDB();
	}
	public Connection openDB(){
		try{
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			System.out.println("\nConexão estabelecida com sucesso!\n");
		}catch(SQLException e){
			System.out.println("\nNão foi possível estabelecer conexão " + e + "\n");
			System.exit(1);	}
		return con;
		}
	public void closeDB(){
		try{
			con.close();
		}catch(SQLException e){
			System.out.println("\nNão foi possível fechar conexão " + e + "\n");
			System.exit(1);		
		}	
	}
	public static void fechaConexao(Connection cn, 
			PreparedStatement stmt, ResultSet rs) throws SQLException {
		if(cn!=null)
			cn.close();
		if(rs!=null)
			rs.close();
		if(stmt!=null)
			stmt.close();		
	}	
	public static void fechaConexao(Connection cn, PreparedStatement stmt) throws SQLException {
		if(cn!=null)
			cn.close();
		if(stmt!=null)
			stmt.close();	
	}
	public static void fechaConexao(Connection cn, ResultSet rs) throws SQLException {
		if(cn!=null)
			cn.close();
		if(rs!=null)
			rs.close();
	}
	public Connection getConexao(String username, String password) throws SQLException{
		try{
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			System.out.println("\nConexão estabelecida com sucesso!\n");
		}catch(SQLException e){
			System.out.println("\nNão foi possível estabelecer conexão " + e + "\n");
			System.exit(1);	}
		return con;
		}
}
