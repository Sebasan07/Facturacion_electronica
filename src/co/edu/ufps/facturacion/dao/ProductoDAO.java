package co.edu.ufps.facturacion.dao;

import co.edu.ufps.facturacion.connection.Conexion;
import co.edu.ufps.facturacion.entities.*;

public class ProductoDAO extends Conexion<Producto> implements GenericDAO<Producto>{

	public ProductoDAO() {
		super(Producto.class);
	}
}
