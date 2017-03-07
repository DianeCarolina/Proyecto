/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.servicio;

import ec.edu.uce.medicina.seguimiento.dao.CarreraDao;
import ec.edu.uce.medicina.seguimiento.dto.GraficoBarrasCaPersona;
import ec.edu.uce.medicina.seguimiento.dto.GraficoEsCarrera;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoAbstractFactory;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoFactory;
import ec.edu.uce.medicina.seguimiento.modelo.Carrera;
import ec.edu.uce.medicina.seguimiento.modelo.Facultad;
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
public class CarreraServicioImp implements CarreraServicio {

    private Map<String, String> modalidad;
    private Map<String, String> nivel;

    @EJB
    private CarreraDao carreraDao = null;

    public CarreraServicioImp() {
        DaoFactory factoria = DaoAbstractFactory.getInstance();
        this.carreraDao = factoria.getCarreraDao();
    }

    @Override
    public void insertarCarrera(Carrera carrera) {
        carreraDao.create(carrera);
    }

    @Override
    public void eliminarCarrera(Carrera carrera) {
        carreraDao.remove(carrera);
    }

    @Override
    public void actualizarCarrera(Carrera carrera) {
        carreraDao.edit(carrera);
    }

    @Override
    public List<Carrera> listarCarrera() {
        return carreraDao.findAll();
    }

    @Override
    public Carrera buscarCarreraPorId(Object id) {
        return carreraDao.find(id);
    }

    @Override
    public Map<String, String> niveles() {
        nivel = new HashMap<>();
        nivel.put("TÉCNICO SUPERIOR", "TÉCNICO SUPERIOR");
        nivel.put("TECNOLÓGICO SUPERIOR", "TECNOLÓGICO SUPERIOR");
        nivel.put("TERCER NIVEL", "TERCER NIVEL");
        nivel.put("ESPECIALIZACIÓN MÉDICA", "ESPECIALIZACIÓN MÉDICA");
        nivel.put("MAESTRÍA", "MAESTRÍA");
        nivel.put("DOCTORADO (PH.D)", "DOCTORADO (PH.D)");
        return nivel;
    }

    @Override
    public Map<String, String> modalidades() {
        modalidad = new HashMap<>();
        modalidad.put("PRESENCIAL", "PRESENCIAL");
        modalidad.put("SEMIPRESENCIAL", "SEMIPRESENCIAL");
        modalidad.put("DISTANCIA", "DISTANCIA");
        modalidad.put("VIRTUAL O EN LÍNEA", "VIRTUAL O EN LÍNEA");
        return modalidad;
    }

    @Override
    public List<SelectItem> oneMenuCarrera(List<Carrera> listaCarrera) {
        List<SelectItem> itemsOneMenu = new ArrayList<SelectItem>();
        for (Carrera u : listaCarrera) {
            SelectItem tmp = new SelectItem(u.getIdCarrera(), u.getNomCarrera());
            itemsOneMenu.add(tmp);
        }
        return itemsOneMenu;

    }

    @Override
    public boolean buscarRegistroPorFacultad(Facultad iFacultad) {
        return carreraDao.buscarRegistroPorFacultad(iFacultad);
    }

    @Override
    public List<GraficoEsCarrera> listaGraficoEstudianteCarrera() {
        return carreraDao.listaGraficoEstudianteCarrera();
    }

    @Override
    public List<GraficoBarrasCaPersona> listaGraficoEnEsCarrera() {
        return carreraDao.listaGraficoEnEsCarrera();
    }

    @Override
    public List<Carrera> buscarCarreraPorFacultad(int idFacultad) {
        return carreraDao.buscarCarreraPorFacultad(idFacultad);
    }

}
