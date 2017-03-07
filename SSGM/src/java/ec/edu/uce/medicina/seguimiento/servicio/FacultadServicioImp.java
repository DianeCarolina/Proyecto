/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.servicio;

import ec.edu.uce.medicina.seguimiento.dao.FacultadDao;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoAbstractFactory;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoFactory;
import ec.edu.uce.medicina.seguimiento.modelo.Facultad;
import ec.edu.uce.medicina.seguimiento.modelo.Universidad;
import java.util.ArrayList;
import java.util.List;
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
public class FacultadServicioImp implements FacultadServicio {

    @EJB
    private FacultadDao facultadDao = null;

    public FacultadServicioImp() {
        DaoFactory factoria = DaoAbstractFactory.getInstance();
        this.facultadDao = factoria.getFacultadDao();
    }

    @Override
    public void insertarFacultad(Facultad facultad) {
        facultadDao.create(facultad);
    }

    @Override
    public void eliminarFacultad(Facultad facultad) {
        facultadDao.remove(facultad);
    }

    @Override
    public void actualizarFacultad(Facultad facultad) {
        facultadDao.edit(facultad);
    }

    @Override
    public List<Facultad> listarFacultad() {
        return facultadDao.findAll();
    }

    @Override
    public Facultad buscarFacultadPorId(Object id) {
        return facultadDao.find(id);
    }

    @Override
    public List<Facultad> buscarFacultadPorUniversidad(int idUniversidad) {
        return facultadDao.buscarFacultadPorUniversidad(idUniversidad);
    }

    @Override
    public List<SelectItem> oneMenuFacultad(List<Facultad> listaFacultad) {
        List<SelectItem> itemsOneMenu = new ArrayList<>();
        for (Facultad u : listaFacultad) {
            SelectItem tmp = new SelectItem(u.getIdFacultad(), u.getNomFacultad());
            itemsOneMenu.add(tmp);
        }
        return itemsOneMenu;

    }

    @Override
    public boolean buscarRegistroPorUniversidad(Universidad iUniversidad) {
        return facultadDao.buscarRegistroPorUniveridad(iUniversidad);
    }

}
