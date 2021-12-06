package co.edu.ufps.facturacion.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.ufps.facturacion.dao.ClienteDAO;
import co.edu.ufps.facturacion.dao.DetalleFacturaDAO;
import co.edu.ufps.facturacion.dao.EmpresaDAO;
import co.edu.ufps.facturacion.dao.FacturaDAO;
import co.edu.ufps.facturacion.dao.RangoNumeracionDAO;
import co.edu.ufps.facturacion.entities.Articulo;
import co.edu.ufps.facturacion.entities.Cliente;
import co.edu.ufps.facturacion.entities.DetalleFactura;
import co.edu.ufps.facturacion.entities.Empresa;
import co.edu.ufps.facturacion.entities.Factura;
import co.edu.ufps.facturacion.entities.Producto;
import co.edu.ufps.facturacion.entities.RangoNumeracion;

/**
 * Servlet implementation class FacturaController
 */
@WebServlet({ "/inicio/factura/ver", //
		"/inicio/factura/agregar",
		"/inicio/factura/rango/agregar",

		"/inicio/factura/agregar/validar",
		"/inicio/factura/rango/agregar/validar",
		"/inicio/factura/eliminar/validar",
})
public class FacturaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FacturaDAO fDAO;
	private DetalleFacturaDAO dfDAO;
	private EmpresaDAO eDAO;
	private ClienteDAO cDAO;
	private RangoNumeracionDAO rgDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FacturaController() {
		super();
		fDAO = new FacturaDAO();
		dfDAO = new DetalleFacturaDAO();
		eDAO = new EmpresaDAO();
		cDAO = new ClienteDAO();
		rgDAO = new RangoNumeracionDAO();
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
			verInicio(request, response, path);
	}

	protected void verInicio(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {

		path = path.replace("/inicio/factura/", "");
		Factura fa = null;
		if (path.contains("/validar")) {
			verFacturaCRUD(request, response, path);
			response.sendRedirect(request.getContextPath() + "/inicio/factura/ver");
			return;
		} else {
			switch (path) {
			case "ver":
				request.setAttribute("facturas", fDAO.list());// VER SI EST¡ VACÌA EN LA VISTA <%IF(CLIENTES!=NULL)%>
				request.getRequestDispatcher("Dashboard/verFacturas.jsp").forward(request, response);
				break;
			case "agregar":
				request.getRequestDispatcher("Dashboard/emitirFactura.jsp").forward(request, response);
				break;
			case "rango/agregar":
				request.getRequestDispatcher("Dashboard/agregarRangosNumeracion.jsp").forward(request, response);
				break;
			/*
			 * case "editar": fa = fDAO.find(request.getParameter("CUFE")); if (fa != null)
			 * { request.setAttribute("factura", fa);
			 * request.getRequestDispatcher("Dashboard/editarFactura.jsp").forward(request,
			 * response); } else {
			 * request.getRequestDispatcher("/inicio/factura/ver").forward(request,
			 * response); } break;
			 */
			default:
				request.getRequestDispatcher("/inicio").forward(request, response);
				break;
			}
		}
	}

	protected void verFacturaCRUD(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {

		path = path.replace("/validar", "");

		switch (path) {
		case "agregar":
			emitirFactura(request, response);
			break;
		case "eliminar":
			eliminarFactura(request, response);
			break;
		default:
			request.getRequestDispatcher("/inicio").forward(request, response);
			break;
		}

	}
	
	protected void emitirFactura(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Cliente cl = cDAO.find(Integer.parseInt(request.getParameter("cliente")));
		Empresa em = (Empresa) request.getSession().getAttribute("empresa");
		RangoNumeracion rg = rgDAO.find(Integer.parseInt(request.getParameter("rango")));//ultimo rango
		
		Date fechaExpedicion = new Date();
        SimpleDateFormat getYearFormat = new SimpleDateFormat("dd/MM/yyyy");
        String [] dMY=getYearFormat.format(fechaExpedicion).split("/");
        Date fechaVencimiento = new GregorianCalendar((Integer.parseInt(dMY[2])+1),Integer.parseInt(dMY[1]),Integer.parseInt(dMY[0])).getTime();
		String firma = request.getParameter("firma");
		double totalDescuento =Double.parseDouble(request.getParameter("totalDescuento"));//descuentos de productos y de iva
        double valorNeto = Double.parseDouble(request.getParameter("valorNeto"));//total a pagar
        
		Factura f=new Factura("", (byte)1, fechaExpedicion, fechaVencimiento, firma, totalDescuento, valorNeto, cl,em,rg);
		f.generarCufe();
		
		fDAO.insert(f);
		insertarDetalleFactura(request,response,f);
	}
	
	private void insertarDetalleFactura(HttpServletRequest request, HttpServletResponse response, Factura f)
			throws ServletException, IOException {
		DetalleFactura df = null;
		List<Articulo> articulos = request.getAttribute("productos")==null ? null:(List<Articulo>) request.getAttribute("productos");
		
		for(Articulo a:articulos) {
			df= new DetalleFactura(0, a.getCantidad(), f, a.getProducto());
			dfDAO.insert(df);
		}
	}
	
	protected void eliminarFactura(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Factura f= fDAO.find(request.getParameter("cufe"));
		f.setEstado((byte)0);
		fDAO.update(f);
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
