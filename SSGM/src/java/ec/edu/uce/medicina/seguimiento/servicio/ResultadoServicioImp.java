/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.servicio;

import ec.edu.uce.medicina.seguimiento.dao.ResultadoDao;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoAbstractFactory;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoFactory;
import ec.edu.uce.medicina.seguimiento.modelo.Persona;
import ec.edu.uce.medicina.seguimiento.modelo.Resultado;
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
public class ResultadoServicioImp implements ResultadoServicio {

    @EJB
    private ResultadoDao resultadoDao = null;

    public ResultadoServicioImp() {
        DaoFactory factoria = DaoAbstractFactory.getInstance();
        this.resultadoDao = factoria.getResultadoDao();
    }

    @Override
    public void insertarResultado(Resultado resultado) {

        resultadoDao.create(resultado);
    }

    @Override
    public void eliminarResultado(Resultado resultado) {
        resultadoDao.remove(resultado);
    }

    @Override
    public void actualizarResultado(Resultado resultado) {
        resultadoDao.edit(resultado);
    }

    @Override
    public List<Resultado> listarResultado() {
        return resultadoDao.findAll();
    }

    @Override
    public Resultado findResultadoById(Integer resultadoId) {
        return resultadoDao.find(resultadoId);
    }

    @Override
    public boolean buscarRegistroPorPersona(Persona iPersona) {
        return resultadoDao.buscarRegistroPorPersona(iPersona);
    }

    @Override
    public List<Resultado> listaResultadoPorPregunta(Integer idPregunta, Integer idPersona) {
        return resultadoDao.listaResultadoPorPregunta(idPregunta, idPersona);
    }

    @Override
    public Resultado obtenerResultado(Integer idPregunta, String resultado, Integer idPersona) {
        return resultadoDao.obtenerResultado(idPregunta, resultado, idPersona);
    }

}
