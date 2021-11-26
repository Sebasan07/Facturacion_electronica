package co.edu.ufps.facturacion.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.ufps.facturacion.dao.ProductoDAO;
import co.edu.ufps.facturacion.entities.Producto;

/**
 * Servlet implementation class ProductoController
 */
@WebServlet({ "/inicio/producto/ver", // ver productos - la lista
		"/inicio/producto/agregar", // vista agregar
		"/inicio/producto/editar", // vista editar producto

		"/producto/agregar/", // agregar producto
		"/producto/eliminar", // eliminar producto
		"/producto/editar/"// editar producto
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
			verInicio(request, response, path);
		} else {
			verProductoCRUD(request, response, path);
			request.getRequestDispatcher("/inicio/producto/ver").forward(request, response);
			return;
		}

	}

	protected void verInicio(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {

		path = path.replace("/inicio/producto/", "");

		switch (path) {
		case "ver":
			request.setAttribute("productos", pDAO.list());
			request.getRequestDispatcher("Dashboard/verProductos.jsp").forward(request, response);
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

	protected void verProductoCRUD(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {

		path = path.replace("/producto/", "");

		switch (path) {
		case "agregar":
			agregarProducto(request, response);
			break;
		case "eliminar":
			eliminarProducto(request,response);
			break;
		case "editar":
			editarProducto(request,response);
			break;
		default:
			request.getRequestDispatcher("/inicio").forward(request, response);
			break;
		}

	}

	protected void agregarProducto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int codigo = Integer.parseInt(request.getParameter("codigo"));

		if (pDAO.find(codigo) == null) {
			String nombre = request.getParameter("nombre");
			String descripcion = request.getParameter("descripcion");
			String unidadMedida = request.getParameter("unidadMedida");
			double valorUnitario = Double.parseDouble(request.getParameter("valorUnitario"));
			double iva = Double.parseDouble(request.getParameter("iva"));
			double porcentajeDescuento = Double.parseDouble(request.getParameter("descuento"));

			pDAO.insert(new Producto(codigo, descripcion, (byte) 1, iva, nombre, porcentajeDescuento, unidadMedida,
					valorUnitario));
		}else {
			request.setAttribute("mensaje", "Ya existe el producto con el ID: "+codigo);
		}
	}
	
	protected void eliminarProducto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int codigo = Integer.parseInt(request.getParameter("codigo"));
		Producto p = pDAO.find(codigo);
		
		if (p != null) {
			p.setEstado((byte)0);
			pDAO.update(p);
		}else {
			request.setAttribute("mensaje", "No existe el producto con el ID: "+codigo);
		}
	}
	
	protected void editarProducto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int codigo = Integer.parseInt(request.getParameter("codigo"));
		Producto p = pDAO.find(codigo);
		if (p != null) {
			p.setNombre(request.getParameter("nombre"));
			p.setDescripcion(request.getParameter("descripcion"));
			p.setUnidadMedia(request.getParameter("unidadMedida"));
			p.setValorUnitario(Double.parseDouble(request.getParameter("valorUnitario")));
			p.setIva(Double.parseDouble(request.getParameter("iva")));
			p.setPorcentajeDescuento(Double.parseDouble(request.getParameter("descuento")));

			pDAO.update(p);
		}else {
			request.setAttribute("mensaje", "No existe el producto con el ID: "+codigo);
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
