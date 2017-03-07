/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.dao;

import ec.edu.uce.medicina.seguimiento.dto.GraficoBarrasCaPersona;
import ec.edu.uce.medicina.seguimiento.dto.GraficoEsCarrera;
import ec.edu.uce.medicina.seguimiento.modelo.Carrera;
import ec.edu.uce.medicina.seguimiento.modelo.Facultad;
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
public interface CarreraDao {

    void create(Carrera carrera);

    void edit(Carrera carrera);

    void remove(Carrera carrera);

    Carrera find(Object id);

    List<Carrera> findAll();

    List<Carrera> findRange(int[] range);

    int count();

    boolean buscarRegistroPorFacultad(Facultad iFacultad);

    List<GraficoEsCarrera> listaGraficoEstudianteCarrera();

    List<GraficoBarrasCaPersona> listaGraficoEnEsCarrera();

    List<Carrera> buscarCarreraPorFacultad(int idFacultad);
}
