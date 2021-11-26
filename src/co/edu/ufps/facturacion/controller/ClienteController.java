package co.edu.ufps.facturacion.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.ufps.facturacion.dao.ClienteDAO;
import co.edu.ufps.facturacion.dao.TipoDocumentoDAO;
import co.edu.ufps.facturacion.entities.Cliente;
import co.edu.ufps.facturacion.entities.TipoDocumento;

/**
 * Servlet implementation class ClienteController
 */
@WebServlet({ "/inicio/cliente/ver", // ver clientes - la lista
		"/inicio/cliente/agregar", // vista agregar
		"/inicio/cliente/editar", // vista editar cliente

		"/cliente/agregar/", // agregar cliente
		"/cliente/eliminar", // eliminar cliente
		"/cliente/editar/"// editar cliente
})
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClienteDAO cDAO;
	private TipoDocumentoDAO tDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClienteController() {
		super();
		cDAO = new ClienteDAO();
		tDAO = new TipoDocumentoDAO();
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
			verClienteCRUD(request, response, path);
			request.getRequestDispatcher("/inicio/cliente/ver").forward(request, response);
			return;
		}
	}

	protected void verInicio(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {

		path = path.replace("/inicio/cliente/", "");
		Cliente cl = null;
		switch (path) {
		case "ver":
			request.setAttribute("clientes", cDAO.list());// VER SI EST� VAC�A EN LA VISTA <%IF(CLIENTES!=NULL)%>
			request.getRequestDispatcher("Dashboard/verClientes.jsp").forward(request, response);
			break;
		case "agregar":
			request.getRequestDispatcher("Dashboard/agregarCliente.jsp").forward(request, response);
			break;
		case "editar":
			cl = cDAO.find(Integer.parseInt(request.getParameter("documento")));
			if (cl != null) {
				request.setAttribute("cliente", cl);
				request.getRequestDispatcher("Dashboard/editarCliente.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/inicio").forward(request, response);
			}
			break;
		default:
			request.getRequestDispatcher("/inicio").forward(request, response);
			break;
		}

	}

	protected void verClienteCRUD(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {

		path = path.replace("/cliente/", "");

		switch (path) {
		case "agregar":
			agregarCliente(request, response);
			break;
		case "eliminar":
			eliminarCliente(request,response);
			break;
		case "editar":
			// editarProducto(request,response);
			break;
		default:
			request.getRequestDispatcher("/inicio").forward(request, response);
			break;
		}

	}

	protected void agregarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int documento = Integer.parseInt(request.getParameter("documento"));

		if (cDAO.find(documento) == null) {
			String nombre = request.getParameter("nombre");
			String nombreComercial = request.getParameter("nombreComercial");
			String direccion = request.getParameter("direccion");
			String pais = request.getParameter("pais");
			String departamento = request.getParameter("departamento");
			String municipio = request.getParameter("municipio");
			String correo = request.getParameter("correo");
			String telefono = request.getParameter("telefono");
			String contribuyente = request.getParameter("contribuyente");
			String regimen = request.getParameter("regimen");

			// hace split al texto del tipo documento (ej: 1. cedula(CC) la posici�n [0] es
			// el n�mero que nos sirve para buscar en la base de datos
			TipoDocumento tipo = tDAO.find(Integer.parseInt(request.getParameter("tipoDocumento").split(".")[0]));

			cDAO.insert(new Cliente(documento, municipio, contribuyente, correo, departamento, direccion, (byte)1,
					nombre, nombreComercial, pais, regimen, telefono, tipo));

		} else {
			request.setAttribute("mensaje", "Ya existe un cliente con documento: " + documento);
		}
	}
	
	protected void eliminarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int documento = Integer.parseInt(request.getParameter("documento"));
		Cliente c = cDAO.find("documento");
		
		if (c != null) {
			c.setEstado((byte)0);
			cDAO.update(c);
		}else {
			request.setAttribute("mensaje", "No existe el cliente con documento: "+documento);
		}
	}
	
	protected void editarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int documento = Integer.parseInt(request.getParameter("documento"));
		Cliente c = cDAO.find("documento");
		
		if (c != null) {
			c.setNombre(request.getParameter("nombre"));
			c.setNombreComercial(request.getParameter("nombreComercial"));
			c.setDireccion(request.getParameter("direccion"));
			c.setPais(request.getParameter("pais"));
			c.setDepartamento(request.getParameter("departamento"));
			c.setCiudad(request.getParameter("municipio"));
			c.setCorreo(request.getParameter("correo"));
			c.setTelefono(request.getParameter("telefono"));
			c.setContribuyente(request.getParameter("contribuyente"));
			c.setRegimenContable(request.getParameter("regimen"));
			c.setEstado(Byte.parseByte(request.getParameter("estado")));

			TipoDocumento tipo = tDAO.find(Integer.parseInt(request.getParameter("tipoDocumento").split(".")[0]));
			c.setTipoDocumentoBean(tipo);
			
			cDAO.update(c);
		}else {
			request.setAttribute("mensaje", "No existe el cliente con documento: "+documento);
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
