package co.edu.ufps.facturacion.dao;

import co.edu.ufps.facturacion.connection.Conexion;
import co.edu.ufps.facturacion.entities.Cliente;

public class ClienteDAO extends Conexion<Cliente> implements GenericDAO<Cliente>{

	public ClienteDAO() {
		super(Cliente.class);
	}
}
