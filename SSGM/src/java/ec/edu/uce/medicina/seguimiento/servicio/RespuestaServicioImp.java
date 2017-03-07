/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.servicio;

import ec.edu.uce.medicina.seguimiento.dao.RespuestaDao;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoAbstractFactory;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoFactory;
import ec.edu.uce.medicina.seguimiento.modelo.Respuesta;
import java.util.List;
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
public class RespuestaServicioImp implements RespuestaServicio {

    @EJB
    private RespuestaDao respuestaDao = null;

    public RespuestaServicioImp() {
        DaoFactory factoria = DaoAbstractFactory.getInstance();
        this.respuestaDao = factoria.getRespuestaDao();
    }

    @Override
    public void insertarRespuesta(Respuesta respuesta) {
        respuestaDao.create(respuesta);
    }

    @Override
    public void eliminarRespuesta(Respuesta respuesta) {
        respuestaDao.remove(respuesta);
    }

    @Override
    public void actualizarRespuesta(Respuesta respuesta) {
        respuestaDao.edit(respuesta);
    }

    @Override
    public List<Respuesta> listarRespuesta() {
        return respuestaDao.findAll();
    }

    @Override
    public List<Respuesta> listaRespuestaPorPregunta(Object id) {
        return respuestaDao.listaRespuestaPorPregunta(id);
    }

    @Override
    public Respuesta buscarPorId(Object id) {
        return respuestaDao.find(id);
    }

}
