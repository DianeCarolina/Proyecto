/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.servicio;

import ec.edu.uce.medicina.seguimiento.dao.EstadoCivilDao;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoAbstractFactory;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoFactory;
import ec.edu.uce.medicina.seguimiento.modelo.EstadoCivil;
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
public class EstadoCivilServicioImp implements EstadoCivilServicio {

    @EJB
    private EstadoCivilDao estadoCivilDao = null;

    public EstadoCivilServicioImp() {
        DaoFactory factoria = DaoAbstractFactory.getInstance();
        this.estadoCivilDao = factoria.getEstadoCivilDao();
    }

    @Override
    public List<EstadoCivil> listarEstadoCivil() {
        return estadoCivilDao.findAll();
    }

    @Override
    public EstadoCivil findEstadoCivilId(Object id) {
        return estadoCivilDao.find(id);
    }

    @Override
    public List<SelectItem> oneMenuEstadoCivil(List<EstadoCivil> listaEstadoCivil) {
        List<SelectItem> itemsOneMenu = new ArrayList<SelectItem>();
        for (EstadoCivil u : listaEstadoCivil) {
            SelectItem tmp = new SelectItem(u.getIdEstadocivil(), u.getNomEstadocivil());
            itemsOneMenu.add(tmp);
        }
        return itemsOneMenu;
    }

}
