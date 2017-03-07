/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.servicio;

import ec.edu.uce.medicina.seguimiento.modelo.Carrera;
import ec.edu.uce.medicina.seguimiento.modelo.Encuesta;
import java.util.List;
import javax.ejb.Local;
import javax.faces.model.SelectItem;

/**
 * <b>
 * Descripcion de la clase.
 * </b>
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@Local
public interface EncuestaServicio {

    List<Encuesta> listarEncuesta();

    Encuesta findEncuestaById(Integer encuestaId);

    boolean buscarRegistroPorCarrera(Carrera iCarrera);

    void insertarEncuesta(Encuesta encuesta);

    List<SelectItem> oneMenuEncuesta(List<Encuesta> listaEncuesta);

    List<Encuesta> listaEncuestaPorCarrera(Integer id);

    Encuesta buscarEncuestaPorCarrera(Integer idCarrera);

    void actualizarEncuesta(Encuesta encuesta);
}
