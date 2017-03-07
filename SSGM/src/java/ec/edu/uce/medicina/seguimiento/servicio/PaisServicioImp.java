/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.servicio;

import ec.edu.uce.medicina.seguimiento.dao.PaisDao;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoAbstractFactory;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoFactory;
import ec.edu.uce.medicina.seguimiento.modelo.Pais;
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
public class PaisServicioImp implements PaisServicio {

    @EJB
    private PaisDao paisDao = null;

    public PaisServicioImp() {
        DaoFactory factoria = DaoAbstractFactory.getInstance();
        this.paisDao = factoria.getPaisDao();

    }

    @Override
    public Pais buscarPaisPorId(Object id) {
        return paisDao.find(id);
    }

    @Override
    public List<Pais> listarPais() {
        return paisDao.findAll();
    }

    @Override
    public List<SelectItem> oneMenuPais(List<Pais> listaPais) {
        List<SelectItem> itemsOneMenu = new ArrayList<>();
        for (Pais u : listaPais) {
            SelectItem tmp = new SelectItem(u.getIdPais(), u.getNomPais());
            itemsOneMenu.add(tmp);
        }
        return itemsOneMenu;
    }

}
