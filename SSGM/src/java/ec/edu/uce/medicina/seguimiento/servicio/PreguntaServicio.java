/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.servicio;

import ec.edu.uce.medicina.seguimiento.dto.PreguntaDTO;
import ec.edu.uce.medicina.seguimiento.modelo.Pregunta;
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
public interface PreguntaServicio {

    void insertarPregunta(Pregunta pregunta);

    void eliminarPregunta(Pregunta pregunta);

    void actualizarPregunta(Pregunta pregunta);

    List<Pregunta> listarPregunta();

    List<SelectItem> oneMenuPregunta(List<Pregunta> listaPregunta);

    Pregunta buscarPreguntaPorId(Object id);

    List<PreguntaDTO> listaPreguntaPorCategoriaAdministrador(Integer id);

    List<Pregunta> listaPreguntaPorCategoriaCliente(Integer id);

    Integer codigoPorEncuesta(Integer idEncuesta);
}
