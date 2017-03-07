/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.servicio;

import ec.edu.uce.medicina.seguimiento.modelo.Persona;
import ec.edu.uce.medicina.seguimiento.modelo.Resultado;
import java.util.List;
import javax.ejb.Local;

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
public interface ResultadoServicio {

    void insertarResultado(Resultado resultado);

    void eliminarResultado(Resultado resultado);

    void actualizarResultado(Resultado resultado);

    List<Resultado> listarResultado();

    Resultado findResultadoById(Integer resultadoId);

    boolean buscarRegistroPorPersona(Persona iPersona);

    List<Resultado> listaResultadoPorPregunta(Integer idPregunta, Integer idPersona);

    Resultado obtenerResultado(Integer idPregunta, String resultado, Integer idPersona);
}
