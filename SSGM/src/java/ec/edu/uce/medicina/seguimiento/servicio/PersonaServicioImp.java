/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.servicio;

import ec.edu.uce.medicina.seguimiento.dao.PersonaDao;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoAbstractFactory;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoFactory;
import ec.edu.uce.medicina.seguimiento.modelo.Carrera;
import ec.edu.uce.medicina.seguimiento.modelo.Persona;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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
@Stateless
public class PersonaServicioImp implements PersonaServicio {

    private Map<String, String> genero;
    private Map<String, String> discapacidad;
    private Map<String, String> tipoIdentificacion;
    @EJB
    private PersonaDao personaDao = null;

    public PersonaServicioImp() {
        DaoFactory factoria = DaoAbstractFactory.getInstance();
        this.personaDao = factoria.getPersonaDao();
    }

    @Override
    public void insertarPersona(Persona persona) {
        personaDao.create(persona);
    }

    @Override
    public void eliminarPersona(Persona persona) {
        personaDao.remove(persona);
    }

    @Override
    public void actualizarPersona(Persona persona) {
        personaDao.edit(persona);
    }

    @Override
    public List<Persona> listarPersona() {
        return personaDao.findAll();
    }

    @Override
    public Persona findPersonaById(Integer personaId) {
        return personaDao.find(personaId);
    }

    @Override
    public Persona buscarPersonaPorCedula(String cedula) {
        return personaDao.buscarPersonaPorCedula(cedula);
    }

    @Override
    public Map<String, String> generos() {
        genero = new HashMap<>();
        genero.put("FEMENINO", "FEMENINO");
        genero.put("MASCULINO", "MASCULINO");
        return genero;
    }

    @Override
    public Map<String, String> discapacidades() {
        discapacidad = new HashMap<>();
        discapacidad.put("AUDTIVA", "AUDTIVA");
        discapacidad.put("FÍSICA", "FÍSICA");
        discapacidad.put("INTELECTUAL", "INTELECTUAL");
        discapacidad.put("LENGUAJE", "LENGUAJE");
        discapacidad.put("PSICOLÓGICO", "PSICOLÓGICO");
        discapacidad.put("VISUAL", "VISUAL");
        discapacidad.put("NINGUNA", "NINGUNA");
        return discapacidad;
    }

    @Override
    public boolean buscarRegistroPorCarrera(Carrera iCarrera) {
        return personaDao.buscarRegistroPorCarrera(iCarrera);
    }

    @Override
    public Map<String, String> tipoIdentificacion() {
        tipoIdentificacion = new HashMap<>();
        tipoIdentificacion.put("CÉDULA", "CÉDULA");
        tipoIdentificacion.put("PASAPORTE", "PASAPORTE");
        return tipoIdentificacion;
    }

    @Override
    public List<SelectItem> oneMenuPersona(List<Persona> listaPersona) {
        List<SelectItem> itemsOneMenu = new ArrayList<SelectItem>();
        for (Persona u : listaPersona) {
            SelectItem tmp = new SelectItem(u.getIdPersona(), u.getNumeroIdentificacion());
            itemsOneMenu.add(tmp);
        }
        return itemsOneMenu;

    }

    @Override
    public List<Persona> listaPersonaPorCarrera(Integer idCarrera) {
        return personaDao.listaPersonaPorCarrera(idCarrera);
    }

}
