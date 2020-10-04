package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanProjeto;
import dao.DaoResultado;

@WebServlet("/servletPesquisa")
public class ServletPesquisa extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoResultado daoResultado = new DaoResultado();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String descricaoPesquisa = request.getParameter("descricaoconsulta");
		if (descricaoPesquisa != null && !descricaoPesquisa.trim().isEmpty()){
			try {
				
				
				List<BeanProjeto> listaPesquisa = daoResultado.listar(descricaoPesquisa);
				
				RequestDispatcher view = request.getRequestDispatcher("/cadastroResultado.jsp");
				request.setAttribute("resultados", listaPesquisa);
				view.forward(request, response);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoResultado.listar());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
