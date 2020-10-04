package beans;

/*
 * Classe BeanProjeto
 * Classe Que Provê o Modelo de Objeto
 */
public class BeanProjeto {
	
	private Long id;
	private String login;
	private String senha;
	private String jogo;
	private String placar;
	private String minimotemporada;
	private String maximotemporada;
	private String quebraminimo;
	private String quebramaximo;
	
	
	
	
	
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLogin() {
		return this.login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return this.senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getJogo() {
		return jogo;
	}
	public void setJogo(String jogo) {
		this.jogo = jogo;
	}
	public String getPlacar() {
		return placar;
	}
	public void setPlacar(String placar) {
		this.placar = placar;
	}
	public String getMinimotemporada() {
		return minimotemporada;
	}
	public void setMinimotemporada(String minimotemporada) {
		this.minimotemporada = minimotemporada;
	}
	public String getMaximotemporada() {
		return maximotemporada;
	}
	public void setMaximotemporada(String maximotemporada) {
		this.maximotemporada = maximotemporada;
	}
	public String getQuebraminimo() {
		return quebraminimo;
	}
	public void setQuebraminimo(String quebraminimo) {
		this.quebraminimo = quebraminimo;
	}
	public String getQuebramaximo() {
		return quebramaximo;
	}
	public void setQuebramaximo(String quebramaximo) {
		this.quebramaximo = quebramaximo;
	}
	
	
	
	
	
	
}