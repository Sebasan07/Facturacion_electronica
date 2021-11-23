package co.edu.ufps.facturacion.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.ufps.facturacion.dao.EmpresaDAO;
import co.edu.ufps.facturacion.dao.UsuarioDAO;
import co.edu.ufps.facturacion.entities.Usuario;

/**
 * Servlet implementation class AdministradorController
 */
@WebServlet({ "/login/validar", "/registro/validar" })
public class AdministradorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioDAO uDAO;
	private EmpresaDAO eDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdministradorController() {
		super();
		uDAO = new UsuarioDAO();
		eDAO = new EmpresaDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();

		if (request.getSession().getAttribute("usuario") != null) {
			request.getRequestDispatcher("/").forward(request, response);
			return;
		}

		switch (path) {
		case "/login/validar":
			break;
		case "/registro/validar":
			break;
		default:
			break;
		}
	}

	protected void logear(HttpServletRequest request, HttpServletResponse response, Usuario usuario)
			throws ServletException, IOException {

		if (uDAO.logear(usuario)) {
			request.getSession().setAttribute("usuario", usuario);
			response.sendRedirect(request.getContextPath()+"/");
			return;
		} else {
			request.setAttribute("mensaje", "No existe");
			request.getRequestDispatcher("/Login").forward(request, response);
			// response.sendRedirect(request.getContextPath() + "/Usuario/Login);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
