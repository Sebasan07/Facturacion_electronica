package co.edu.ufps.facturacion.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.ufps.facturacion.dao.ProductoDAO;

/**
 * Servlet implementation class ProductoController
 */
@WebServlet({ "/inicio/producto/ver", // ver productos - la lista
		"/inicio/producto/agregar", // vista agregar
		"/inicio/producto/editar", // vista editar producto
		"/producto/agregar/validar", // agregar producto
		"/producto/eliminar", // eliminar producto
		"/producto/editar/validar"// editar producto
})
public class ProductoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductoDAO pDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductoController() {
		super();
		pDAO = new ProductoDAO();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();

		if (request.getSession().getAttribute("usuario") == null) {
			request.getRequestDispatcher("/login").forward(request, response);
			return;
		}

		if (path.contains("/inicio")) {
			verInicio(request,response,path);
		}

	}

	protected void verInicio(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {

		path = path.replace("/inicio/producto/", "");

		switch (path) {
		case "ver":
			request.setAttribute("productos", pDAO.list());
			request.getRequestDispatcher("verProductos.jsp").forward(request, response);
			break;
		case "agregar":
			request.getRequestDispatcher("Dashboard/agregarProducto.jsp").forward(request, response);
			break;
		case "editar":
			request.setAttribute("producto", pDAO.find(request.getParameter("codigo")));
			request.getRequestDispatcher("Dashboard/editarProducto.jsp").forward(request, response);
			break;
		default: 
			request.getRequestDispatcher("/inicio").forward(request, response);
			break;
		}

	}
	
	protected void agregarProducto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		

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
