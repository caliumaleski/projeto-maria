package servlet;



import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import beans.BeanProjeto;
import dao.DaoResultado;

@WebServlet("/salvarResultado")
@MultipartConfig
public class Resultado extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private DaoResultado daoResultado = new DaoResultado();
       
	/*
	 * Construtor Resultado()
	 * Recebe o Construtor da Classe HttpServlet
	 */
    public Resultado() {
        super();
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			String resultado = request.getParameter("resultado");
				if(acao != null && acao.equalsIgnoreCase("delete") && resultado != null) {
					daoResultado.delete(resultado);
					RequestDispatcher view = request.getRequestDispatcher("/cadastroResultado.jsp");
					request.setAttribute("resultados", daoResultado.listar());
					view.forward(request, response);
				} else if(acao != null && acao.equalsIgnoreCase("editar")) {
					BeanProjeto beanProjeto = daoResultado.consultar(resultado);
					RequestDispatcher view = request.getRequestDispatcher("/cadastroResultado.jsp");
					request.setAttribute("resultado", beanProjeto);
					view.forward(request, response);
				} else if(acao != null && acao.equalsIgnoreCase("listartodos")) {
					daoResultado.delete(resultado);
					RequestDispatcher view = request.getRequestDispatcher("/cadastroResultado.jsp");
					request.setAttribute("resultados", daoResultado.listar());
					view.forward(request, response);
				
					if (resultado != null){
						String contentType = "";
						byte[] fileBytes = null; 
						
						
						
						
						
						response.setHeader("Content-Disposition", "attachment;filename=arquivo."
					   + contentType.split("\\/")[1]);
						
						
						/*Coloca os bytes em um objeto de entrada para processar*/
						InputStream is = new ByteArrayInputStream(fileBytes);
						
						/*inicio da resposta para o navegador*/
						int read= 0;
						byte[] bytes = new byte[1024];
						OutputStream os = response.getOutputStream();
						
						
						while ((read = is.read(bytes)) != -1) {
							os.write(bytes, 0, read);
						}
						
						os.flush();
						os.close();
						
					}
				}else {
					RequestDispatcher view = request.getRequestDispatcher("/cadastroResultado.jsp");
					request.setAttribute("resultados", daoResultado.listar());
					view.forward(request, response);
				}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String acao = request.getParameter("acao");
			if(acao != null && acao.equalsIgnoreCase("reset")) {
				try {
					RequestDispatcher view = request.getRequestDispatcher("/cadastroResultado.jsp");
					request.setAttribute("resultados", daoResultado.listar());
					view.forward(request, response);
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else {
				String id = request.getParameter("id");
				String login = request.getParameter("login");
				String senha = request.getParameter("senha");
				String jogo = request.getParameter("jogo");
				String placar = request.getParameter("placar");
				String minimotemporada = request.getParameter("minimotemporada");
				String maximotemporada = request.getParameter("maximotemporada");
				String quebraminimo = request.getParameter("quebraminimo");
				String quebramaximo = request.getParameter("quebramaximo");
				
				
			 
		
				BeanProjeto resultado = new BeanProjeto();
				resultado.setId((id != null && !id.isEmpty()) ? Long.parseLong(id) : null);
				resultado.setLogin(login);
				resultado.setSenha(senha);
				resultado.setJogo(jogo);
				resultado.setPlacar(placar);
				resultado.setMinimotemporada(minimotemporada);
				resultado.setMaximotemporada(maximotemporada);
				resultado.setQuebraminimo(quebraminimo);
				resultado.setQuebramaximo(quebramaximo);
				
				
				
				try {	
					
					
						String msg = null;
						boolean podeInserir = true;
						
						if(login == null || login.isEmpty()) {
							msg = "Login Deve Ser Informado!";
							podeInserir = false;
						} else if(senha == null || senha.isEmpty()) {
							msg = "Senha Deve Ser Informada!";
							podeInserir = false;
						} else if(jogo == null || jogo.isEmpty()) {
							msg = "Nome Deve Ser Informado!";
							podeInserir = false;
						} else if(id == null || id.isEmpty() && !daoResultado.validarLogin(login)) {
							request.setAttribute("msg", "Este Login Pertence a Um Usuário!");
							podeInserir = false;
						} else if(id == null || id.isEmpty() && !daoResultado.validarSenha(senha)) {
							request.setAttribute("msg", "Esta Senha Pertence a Um Usuário!");
							podeInserir = false;
						}
						
						if(msg != null) {
							request.setAttribute("msg", msg);
						} else if(id == null || id.isEmpty() && daoResultado.validarLogin(login) && daoResultado.validarSenha(senha) && podeInserir) {
							daoResultado.salvar(resultado);
						}
						
						if(id != null && !id.isEmpty() && podeInserir) {
							if (!daoResultado.validarLoginUpdate(login, id)){
								request.setAttribute("msg", "Login já existe para outro usuário");
							}else {
							 daoResultado.atualizar(resultado);
							}
						}
						
						if(!podeInserir) {
							request.setAttribute("resultado", resultado);
						}
						
						RequestDispatcher view = request.getRequestDispatcher("/cadastroResultado.jsp");
						request.setAttribute("resultados", daoResultado.listar());
						//request.setAttribute("msg", "Salvo Com Sucesso!");
						view.forward(request, response);
					} catch(Exception e) {
						e.printStackTrace();
					}
			}
	}
	


}