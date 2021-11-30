package co.edu.ufps.facturacion.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.ufps.facturacion.dao.DetalleFacturaDAO;
import co.edu.ufps.facturacion.dao.FacturaDAO;
import co.edu.ufps.facturacion.entities.Cliente;
import co.edu.ufps.facturacion.entities.Factura;

/**
 * Servlet implementation class FacturaController
 */
@WebServlet({ "/inicio/factura/ver", //
		"/inicio/factura/agregar", 
		"/inicio/factura/editar",

		"/inicio/factura/agregar/validar", 
		"/inicio/factura/eliminar/validar",
		"/inicio/factura/editar/validar"

})
public class FacturaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FacturaDAO fDAO;
	private DetalleFacturaDAO dfDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FacturaController() {
		super();
		fDAO = new FacturaDAO();
		dfDAO = new DetalleFacturaDAO();
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
			request.getRequestDispatcher("/inicio/factura/ver").forward(request, response);
			return;
		} else {
			switch (path) {
			case "ver":
				request.setAttribute("facturas", fDAO.list());// VER SI EST¡ VACÌA EN LA VISTA <%IF(CLIENTES!=NULL)%>
				request.getRequestDispatcher("Dashboard/verFacturas.jsp").forward(request, response);
				break;
			case "agregar":
				request.getRequestDispatcher("Dashboard/agregarFactura.jsp").forward(request, response);
				break;
			case "editar":
				fa = fDAO.find(request.getParameter("CUFE"));
				if (fa != null) {
					request.setAttribute("factura", fa);
					request.getRequestDispatcher("Dashboard/editarFactura.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("/inicio").forward(request, response);
				}
				break;
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
			//eliminarFactura(request, response);
			break;
		case "editar":
			//editarFactura(request,response);
			break;
		default:
			request.getRequestDispatcher("/inicio").forward(request, response);
			break;
		}

	}
	
	protected void emitirFactura(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Date fechaExpedicion = new Date();
        SimpleDateFormat getYearFormat = new SimpleDateFormat("dd/MM/yyyy");
        String [] dMY=getYearFormat.format(fechaExpedicion).split("/");
        Date fechaVencimiento = new GregorianCalendar((Integer.parseInt(dMY[2])+1),Integer.parseInt(dMY[1]),Integer.parseInt(dMY[0])).getTime();
		String firma = request.getParameter("firma");
		String totalDescuento;
        
        
        
		Factura f=new Factura(cufe, estado, fechaExpedicion, fechaVencimiento, firma, totalDescuento, valorNeto, cliente, empresa, rangoNumeracionBean);
		String cufe =generarCufe(f);
	}
	
	private String generarCufe(Factura f) {
		String cufe ="";
		
		
		return "";
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
