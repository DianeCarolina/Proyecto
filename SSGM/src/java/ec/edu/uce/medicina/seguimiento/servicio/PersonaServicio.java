/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.servicio;

import ec.edu.uce.medicina.seguimiento.modelo.Carrera;
import ec.edu.uce.medicina.seguimiento.modelo.Persona;
import java.util.List;
import java.util.Map;
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
public interface PersonaServicio {

    void insertarPersona(Persona persona);

    void eliminarPersona(Persona persona);

    void actualizarPersona(Persona persona);

    List<Persona> listarPersona();

    Persona findPersonaById(Integer personaId);

    Persona buscarPersonaPorCedula(String cedula);

    Map<String, String> generos();

    Map<String, String> discapacidades();

    Map<String, String> tipoIdentificacion();

    boolean buscarRegistroPorCarrera(Carrera iCarrera);

    List<SelectItem> oneMenuPersona(List<Persona> listaPersona);

    List<Persona> listaPersonaPorCarrera(Integer idCarrera);
}
