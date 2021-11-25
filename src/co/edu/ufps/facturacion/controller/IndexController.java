package co.edu.ufps.facturacion.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IndexController
 */
@WebServlet({ "/", "/inicio", "/index.jsp" })
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getSession().getAttribute("usuario") == null) {
			request.getRequestDispatcher("/login").forward(request, response);
			return;
		}

		String path = request.getServletPath();

		// request.setAttribute("nombre", "objeto");
		if (path.equals("/") || path.equals("/inicio") || path.equals("/index.jsp")) {
			request.getRequestDispatcher("inicio.jsp").forward(request, response);
		}else if(path.contains("/inicio/")) {
			verInicio(request,response,path);
		}
	}

	protected void verInicio(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		path = path.replace("/inicio/", "");
		
		//movimientos en el inicio
		switch(path) {
		case "":break;
		default:break;
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
