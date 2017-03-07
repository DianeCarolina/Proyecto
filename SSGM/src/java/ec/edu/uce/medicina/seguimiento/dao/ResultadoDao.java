/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.dao;

import ec.edu.uce.medicina.seguimiento.modelo.Persona;
import ec.edu.uce.medicina.seguimiento.modelo.Resultado;
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
public interface ResultadoDao {

    void create(Resultado resultado);

    void edit(Resultado resultado);

    void remove(Resultado resultado);

    Resultado find(Object id);

    List<Resultado> findAll();

    List<Resultado> findRange(int[] range);

    int count();

    boolean buscarRegistroPorPersona(Persona iPersona);

    List<Resultado> listaResultadoPorPregunta(Integer idPregunta, Integer idPersona);

    Resultado obtenerResultado(Integer idPregunta,String resultado, Integer idPersona);

}
