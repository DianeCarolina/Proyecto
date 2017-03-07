/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.util;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <b>
 * Clase que contiene, todos los métodos de tipo fecha.
 * </b>
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
public class AplicacionUtil {

    /**
     * Método que permite convertir una Fecha de tipo Date a String.
     * @param date: fecha de tipo de dato Date.
     * @return date: fecha de tipo de dato String.
     */
    public static String dateToString(Date date) {
        String format = "dd-MM-YYYY"; //Formato de la fecha de tipo String
        Format formato = new SimpleDateFormat(format);
        return formato.format(date);
    }

    /**
     * Método que permite convertir una fecha de tipo String a String.
     * @param date: fecha de tipo de dato String.
     * @return resultado: fecha con tipo de dato String.
     */
    public static String stringADate(String date) {
        String resultado = "";
        try {
            String format = "E MMM dd HH:mm:ss Z yyyy";
            DateFormat formatter = new SimpleDateFormat(format, Locale.ENGLISH);
            Date dateGenerado = (Date) formatter.parse(date); // Convierte la fecha de String a Date
            resultado = dateToString(dateGenerado); // convierte Date a String 
        } catch (ParseException ex) {
            Logger.getLogger(AplicacionUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }

    /**
     *Método que permite convertir una fecha de tipo String a Date.
     * @param fecha: Fecha con tipo de dato String.
     * @return fechaDate: Fecha con tipo de dato Date.
     */
    public static Date parseFecha(String fecha) {

        String formatoString = "dd-MM-yyyy";
        SimpleDateFormat formato = new SimpleDateFormat(formatoString, Locale.getDefault());

        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        return fechaDate;
    }
/**
 * Método que permite calcular la edad.
 * @param fechaNac: fecha de nacimiento de la persona de tipo Date.
 * @return anio: años de la persona.
 */
    public static int edad(Date fechaNac) {
        //Se crea un objeto con la fecha actual
        Date fechaActual = new Date();
        //Se restan la fecha actual y la fecha de nacimiento
        int anio = fechaActual.getYear() - fechaNac.getYear();
        int mes = fechaActual.getMonth() - fechaNac.getMonth();
        int dia = fechaActual.getDay() - fechaNac.getDay();
        //Se ajusta el año dependiendo el mes y el día
        if (mes < 0 || (mes == 0 && dia < 0)) {
            anio--;
        }
        //Regresa la edad en base a la fecha de nacimiento
        return anio;
    }

}
