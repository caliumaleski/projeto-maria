package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanProjeto;
import connection.SingleConnection;
/*
 * Classe DaoUsuario
 * Classe Que Prov� os M�todos e Valida��es Para Manipular Dados, e Acesso e Manipula��o do BD
 */
public class DaoResultado {

	private Connection connection;

	/*
	 * Construtor DaoUsuario() Recebe um Objeto connection da Classe
	 * SingleConnection
	 */
	public DaoResultado() {
		connection = SingleConnection.getConnection();
	}

	/*
	 * M�todo salvar() Respons�vel Por Fazer a Inser��o de Dados (INSERT) no BD
	 * 
	 * @param BeanCursoJsp usuario = Objeto Usu�rio da Classe BeanCursoJsp
	 */
	public void salvar(BeanProjeto resultado) {
		try {
			String sql = "INSERT INTO resultado(login, senha, jogo, placar, minimotemporada, maximotemporada, quebraminimo, quebramaximo) "
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, resultado.getLogin());
			insert.setString(2, resultado.getSenha());
			insert.setString(3, resultado.getJogo());
			insert.setString(4, resultado.getPlacar());
			insert.setString(5, resultado.getMinimotemporada());
			insert.setString(6, resultado.getMaximotemporada());
			insert.setString(7, resultado.getQuebraminimo());
			insert.setString(8, resultado.getQuebramaximo());
			
			insert.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	/*
	 * M�todo listar() Respons�vel Por Listar Todos os Usu�rios do Sistema
	 */
	
	
	public List<BeanProjeto> listar (String descricaoconsulta) throws SQLException{
		String sql = "SELECT * FROM resultado where login <> 'admin' and jogo like '%"+descricaoconsulta+"%'";
		return consultarResultados(sql);
	}
	
	
	public List<BeanProjeto> listar() throws Exception {
		String sql = "SELECT * FROM resultado where login <> 'admin'";
		return consultarResultados(sql);
	}

	private List<BeanProjeto> consultarResultados(String sql)
			throws SQLException {
		List<BeanProjeto> listar = new ArrayList<BeanProjeto>();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			BeanProjeto beanProjeto = new BeanProjeto();
			beanProjeto.setId(resultSet.getLong("id"));
			beanProjeto.setLogin(resultSet.getString("login"));
			beanProjeto.setSenha(resultSet.getString("senha"));
			beanProjeto.setJogo(resultSet.getString("jogo"));
			beanProjeto.setPlacar(resultSet.getString("placar"));
			beanProjeto.setMinimotemporada(resultSet.getString("minimotemporada"));
			beanProjeto.setMaximotemporada(resultSet.getString("maximotemporada"));
			beanProjeto.setQuebraminimo(resultSet.getString("quebraminimo"));
			beanProjeto.setQuebramaximo(resultSet.getString("quebramaximo"));
			
			
			
			listar.add(beanProjeto);
		}
		
		return listar;
	}

	/*
	 * M�todo delete() Respons�vel Por Fazer a Exclus�o (Delete) no BD
	 * 
	 * @param String id = Atributo ID do Usu�rio
	 */
	public void delete(String id) {
		if (id != null && !id.isEmpty()) {
			try {
				String sql = "DELETE FROM resultado WHERE id = '" + id
						+ "' and login <> 'admin'";
				PreparedStatement preparedStatement = connection
						.prepareStatement(sql);
				preparedStatement.execute();
				connection.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/*
	 * M�todo consultar() Respons�vel Por Fazer Consultas (SELECT) no BD
	 * 
	 * @param String id = Atributo ID do Usu�rio
	 */
	public BeanProjeto consultar(String id) throws Exception {
		String sql = "SELECT * FROM resultado WHERE id = '" + id
				+ "' and login <> 'admin'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			BeanProjeto beanProjeto = new BeanProjeto();
			beanProjeto.setId(resultSet.getLong("id"));
			beanProjeto.setLogin(resultSet.getString("login"));
			beanProjeto.setSenha(resultSet.getString("senha"));
			beanProjeto.setJogo(resultSet.getString("jogo"));
			beanProjeto.setPlacar(resultSet.getString("placar"));
			beanProjeto.setMinimotemporada(resultSet.getString("minimotemporada"));
			beanProjeto.setMaximotemporada(resultSet.getString("maximotemporada"));
			beanProjeto.setQuebraminimo(resultSet.getString("quebraminimo"));
			beanProjeto.setQuebramaximo(resultSet.getString("quebramaximo"));
			
			
			return beanProjeto;
		}
		return null; /* fotobase64, contenttype */
	}

	/*
	 * M�todo validarLogin Respons�vel Por Validar Login(N�o Pode Existir 1
	 * Mesmo Login Para 2 Usu�rios Diferentes)
	 * 
	 * @param String login = Atributo Login do Usu�rio
	 */
	public boolean validarLogin(String login) throws Exception {
		String sql = "SELECT COUNT(1) as qtde FROM resultado WHERE login = '"
				+ login + "'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt("qtde") <= 0;
		}
		return false;
	}

	public boolean validarLoginUpdate(String login, String id) throws Exception {
		String sql = "SELECT COUNT(1) as qtde FROM resultado WHERE login = '"
				+ login + "' and id <> " + id;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt("qtde") <= 0;
		}
		return false;
	}

	/*
	 * M�todo validarSenha Respons�vel Por Validar Senha(N�o Pode Existir 1
	 * Mesma Senha Para 2 Usu�rios Diferentes)
	 * 
	 * @param String senha = Atributo Senha do Usu�rio
	 */
	public boolean validarSenha(String senha) throws Exception {
		String sql = "SELECT COUNT(1) as qtde FROM resultado WHERE senha = '"
				+ senha + "'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			return resultSet.getInt("qtde") <= 0;
		}
		return false;
	}

	/*
	 * M�todo atualizar() M�todo Respons�vel Por Atualizar os Dados (UPDATE) no
	 * BD
	 * 
	 * @param BeanCursoJsp usuario = Objeto usuario da Classe BeanCursoJsp
	 */
	public void atualizar(BeanProjeto resultado) {
		try {
			StringBuilder sql = new StringBuilder();

			sql.append(" UPDATE resultado SET login = ?, senha = ?, jogo = ?, placar = ?, minimotemporada = ?, maximotemporada = ?, quebraminimo = ?, quebramaximo = ? ");

			

			sql.append(" WHERE id = " + resultado.getId());

			
			PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
			preparedStatement.setString(1, resultado.getLogin());
			preparedStatement.setString(2, resultado.getSenha());
			preparedStatement.setString(3, resultado.getJogo());
			preparedStatement.setString(4, resultado.getPlacar());
			preparedStatement.setString(5, resultado.getMinimotemporada());
			preparedStatement.setString(6, resultado.getMaximotemporada());
			preparedStatement.setString(7, resultado.getQuebraminimo());
			preparedStatement.setString(8, resultado.getQuebramaximo());
			
			

			preparedStatement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
