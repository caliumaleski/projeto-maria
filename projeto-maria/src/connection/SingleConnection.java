package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 * Classe SingleConnection
 * Responsável Por Fazer a Conexão com o BD
 
 */
public class SingleConnection {

	private static String banco = "jdbc:postgresql://localhost:5432/projeto-maria?autoReconnect=true";
	private static String user = "postgres";
	private static String password = "admin";/*senha da sua hospedagem*/
	private static Connection connection = null;
	
	/*
	 * Chamada Estática do Método conectar()
	 */
	static {
		conectar();
	}
	
	/*
	 * Construtor da Classe SingleConnection()
	 * Chama o Método conectar()
	 */
	public SingleConnection() {
		conectar();
	}
	
	/*
	 * Método conectar()
	 * Provê os Meios de Conexão ao BD
	 */
	private static void conectar() {
		try {
			if(connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(banco, user, password);
				connection.setAutoCommit(false);
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao Conectar com o Banco de Dados");
		}
	}
	
	/*
	 * Método getConnection()
	 * Responsável Por Fazer Uso da Conexão na Aplicação
	 */
	public static Connection getConnection() {
		return connection;
	}
}
