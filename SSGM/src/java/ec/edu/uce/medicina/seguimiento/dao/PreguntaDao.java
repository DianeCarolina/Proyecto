/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.dao;

import ec.edu.uce.medicina.seguimiento.dto.PreguntaDTO;
import ec.edu.uce.medicina.seguimiento.modelo.Pregunta;
import java.util.List;
import javax.ejb.Local;

/**
 * <b>
 * Descripción de la clase.
 * </b>
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@Local
public interface PreguntaDao {

    void create(Pregunta pregunta);

    void edit(Pregunta pregunta);

    void remove(Pregunta pregunta);

    Pregunta find(Object id);

    List<Pregunta> findAll();

    List<Pregunta> findRange(int[] range);

    int count();

    List<PreguntaDTO> listaPreguntaPorCategoriaAdministrador(Integer id);

    List<Pregunta> listaPreguntaPorCategoriaCliente(Integer id);

    Integer codigoPorEncuesta(Integer idEncuesta);
}
