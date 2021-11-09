package co.edu.ufps.facturacion.dao;

import co.edu.ufps.facturacion.connection.Conexion;
import co.edu.ufps.facturacion.entities.*;

public class RangoNumeracionDAO extends Conexion<RangoNumeracion> implements GenericDAO<RangoNumeracion>{

	public RangoNumeracionDAO() {
		super(RangoNumeracion.class);
	}
}
