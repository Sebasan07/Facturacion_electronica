package co.edu.ufps.facturacion.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class d {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date fechaExpedicion = new Date();

        SimpleDateFormat getYearFormat = new SimpleDateFormat("dd/MM/yyyy");
       
        String [] dMY=getYearFormat.format(fechaExpedicion).split("/");
        Date fecha = new GregorianCalendar((Integer.parseInt(dMY[2])+1),Integer.parseInt(dMY[1]),Integer.parseInt(dMY[0])).getTime();

	}

}
