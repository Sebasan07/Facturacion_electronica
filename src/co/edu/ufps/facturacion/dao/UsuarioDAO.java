package co.edu.ufps.facturacion.dao;

import co.edu.ufps.facturacion.connection.Conexion;
import co.edu.ufps.facturacion.entities.*;

public class UsuarioDAO extends Conexion<Usuario> implements GenericDAO<Usuario>{

	public UsuarioDAO() {
		super(Usuario.class);
	}
}
