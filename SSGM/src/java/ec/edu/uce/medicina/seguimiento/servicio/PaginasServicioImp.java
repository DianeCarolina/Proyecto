/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.servicio;

import ec.edu.uce.medicina.seguimiento.dao.PaginasDao;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoAbstractFactory;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoFactory;
import ec.edu.uce.medicina.seguimiento.modelo.Paginas;
import javax.ejb.EJB;
import javax.ejb.Stateless;

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
public class PaginasServicioImp implements PaginasServicio {

    @EJB
    private PaginasDao paginasDao = null;

    public PaginasServicioImp() {
        DaoFactory factoria = DaoAbstractFactory.getInstance();
        this.paginasDao = factoria.getPaginasDao();
    }

    @Override
    public void insertarPaginas(Paginas paginas) {
        paginasDao.create(paginas);
    }

    @Override
    public void eliminarPaginas(Paginas paginas) {
        paginasDao.remove(paginas);
    }

    @Override
    public void actualizarPaginas(Paginas paginas) {
        paginasDao.edit(paginas);
    }

}
