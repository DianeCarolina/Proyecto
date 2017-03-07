/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.servicio;

import ec.edu.uce.medicina.seguimiento.dto.GraficoBarrasCaPersona;
import ec.edu.uce.medicina.seguimiento.dto.GraficoEsCarrera;
import ec.edu.uce.medicina.seguimiento.modelo.Carrera;
import ec.edu.uce.medicina.seguimiento.modelo.Facultad;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.faces.model.SelectItem;

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
public interface CarreraServicio {

    void insertarCarrera(Carrera carrera);

    void eliminarCarrera(Carrera carrera);

    void actualizarCarrera(Carrera carrera);

    List<Carrera> listarCarrera();

    Carrera buscarCarreraPorId(Object id);

    Map<String, String> niveles();

    Map<String, String> modalidades();

    List<SelectItem> oneMenuCarrera(List<Carrera> listaCarrera);

    boolean buscarRegistroPorFacultad(Facultad iFacultad);

    List<GraficoEsCarrera> listaGraficoEstudianteCarrera();

    List<GraficoBarrasCaPersona> listaGraficoEnEsCarrera();
    
    List<Carrera> buscarCarreraPorFacultad (int idFacultad);
}
