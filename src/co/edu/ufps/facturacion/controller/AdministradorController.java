package co.edu.ufps.facturacion.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.ufps.facturacion.dao.EmpresaDAO;
import co.edu.ufps.facturacion.dao.RolUsuarioDAO;
import co.edu.ufps.facturacion.dao.UsuarioDAO;
import co.edu.ufps.facturacion.entities.RolUsuario;
import co.edu.ufps.facturacion.entities.Usuario;

/**
 * Servlet implementation class AdministradorController
 */
@WebServlet({ "/login/validar", "/registro/validar" })
public class AdministradorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioDAO uDAO;
	private EmpresaDAO eDAO;
	private RolUsuarioDAO rDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdministradorController() {
		super();
		uDAO = new UsuarioDAO();
		eDAO = new EmpresaDAO();
		rDAO = new RolUsuarioDAO();
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
			logear(request, response);
			break;
		case "/registro/validar":
			registrarUsuario(request, response);
			break;
		default:
			break;
		}
	}

	protected void logear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Usuario usuario = uDAO.find(request.getParameter("correo"));
		
		if (usuario !=null && uDAO.logear(usuario)) {
			request.getSession().setAttribute("usuario", usuario);
			response.sendRedirect(request.getContextPath()+"/");
			return;
		} else {
			request.setAttribute("mensaje", "No existe el usuario");
			request.getRequestDispatcher("/login").forward(request, response);
			// response.sendRedirect(request.getContextPath() + "/Usuario/Login);
		}

	}
	
	protected void registrarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String correo = request.getParameter("correo");

		if(uDAO.find(correo)==null) {
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			String pass = request.getParameter("pass");
			String rol = request.getParameter("rol");
			
			RolUsuario ru = rDAO.find(verRol(rol));
			uDAO.insert(new Usuario(correo, apellido, pass, (byte)1, nombre,null,ru));
			request.getRequestDispatcher("/login").forward(request, response);
		}else {
			request.setAttribute("mensaje", "Ya existe un usuario con este correo");
			request.getRequestDispatcher("/login").forward(request, response);
		}
	}

	private int verRol(String rol) {
		switch(rol) {
		case "Administrador":return 1;
		case "Contador":return 2;
		case "Vendedor":return 3;
		default:return 1;
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
