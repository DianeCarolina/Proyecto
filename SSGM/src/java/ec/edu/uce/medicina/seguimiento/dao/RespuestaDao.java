/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.dao;

import ec.edu.uce.medicina.seguimiento.modelo.Respuesta;
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
public interface RespuestaDao {

    void create(Respuesta respuesta);

    void edit(Respuesta respuesta);

    void remove(Respuesta respuesta);

    Respuesta find(Object id);

    List<Respuesta> findAll();

    List<Respuesta> findRange(int[] range);

    int count();

    List<Respuesta> listaRespuestaPorPregunta(Object id);

}
