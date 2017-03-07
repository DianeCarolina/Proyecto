/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.fabrica;

import ec.edu.uce.medicina.seguimiento.dao.CarreraDao;
import ec.edu.uce.medicina.seguimiento.dao.CarreraDaoImp;
import ec.edu.uce.medicina.seguimiento.dao.CategoriaDao;
import ec.edu.uce.medicina.seguimiento.dao.CategoriaDaoImp;
import ec.edu.uce.medicina.seguimiento.dao.CiudadDao;
import ec.edu.uce.medicina.seguimiento.dao.CiudadDaoImp;
import ec.edu.uce.medicina.seguimiento.dao.EncuestaDao;
import ec.edu.uce.medicina.seguimiento.dao.EncuestaDaoImp;
import ec.edu.uce.medicina.seguimiento.dao.EstadoCivilDao;
import ec.edu.uce.medicina.seguimiento.dao.EstadoCivilDaoImp;
import ec.edu.uce.medicina.seguimiento.dao.FacultadDao;
import ec.edu.uce.medicina.seguimiento.dao.FacultadDaoImp;
import ec.edu.uce.medicina.seguimiento.dao.PaginasDao;
import ec.edu.uce.medicina.seguimiento.dao.PaginasDaoImp;
import ec.edu.uce.medicina.seguimiento.dao.PaisDao;
import ec.edu.uce.medicina.seguimiento.dao.PaisDaoImp;
import ec.edu.uce.medicina.seguimiento.dao.PerfilDao;
import ec.edu.uce.medicina.seguimiento.dao.PerfilDaoImp;
import ec.edu.uce.medicina.seguimiento.dao.PerfilPaginasDao;
import ec.edu.uce.medicina.seguimiento.dao.PerfilPaginasDaoImp;
import ec.edu.uce.medicina.seguimiento.dao.PersonaDao;
import ec.edu.uce.medicina.seguimiento.dao.PersonaDaoImp;
import ec.edu.uce.medicina.seguimiento.dao.PreguntaDao;
import ec.edu.uce.medicina.seguimiento.dao.PreguntaDaoImp;
import ec.edu.uce.medicina.seguimiento.dao.ProvinciaDao;
import ec.edu.uce.medicina.seguimiento.dao.ProvinciaDaoImp;
import ec.edu.uce.medicina.seguimiento.dao.RespuestaDao;
import ec.edu.uce.medicina.seguimiento.dao.RespuestaDaoImp;
import ec.edu.uce.medicina.seguimiento.dao.ResultadoDao;
import ec.edu.uce.medicina.seguimiento.dao.ResultadoDaoImp;
import ec.edu.uce.medicina.seguimiento.dao.TipoPreguntaDao;
import ec.edu.uce.medicina.seguimiento.dao.TipoPreguntaDaoImp;
import ec.edu.uce.medicina.seguimiento.dao.UniversidadDao;
import ec.edu.uce.medicina.seguimiento.dao.UniversidadDaoImp;
import ec.edu.uce.medicina.seguimiento.dao.UsuarioDao;
import ec.edu.uce.medicina.seguimiento.dao.UsuarioDaoImp;

/**
 * <b>
 * Descripción de la clase.
 * </b>
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
public class DaoFactoryImp implements DaoFactory {

    @Override
    public CarreraDao getCarreraDao() {
        return new CarreraDaoImp();
    }

    @Override
    public CategoriaDao getCtegoriaDao() {
        return new CategoriaDaoImp();
    }

    @Override
    public CiudadDao getCiudadDao() {
        return new CiudadDaoImp();
    }

    @Override
    public EncuestaDao getEncuestaDao() {
        return new EncuestaDaoImp();
    }

    @Override
    public EstadoCivilDao getEstadoCivilDao() {
        return new EstadoCivilDaoImp();
    }

    @Override
    public FacultadDao getFacultadDao() {
        return new FacultadDaoImp();
    }

    @Override
    public PaisDao getPaisDao() {
        return new PaisDaoImp();
    }

    @Override
    public PerfilDao getPerfilDao() {
        return new PerfilDaoImp();
    }

    @Override
    public PersonaDao getPersonaDao() {
        return new PersonaDaoImp();
    }

    @Override
    public PreguntaDao getPreguntaDao() {
        return new PreguntaDaoImp();
    }

    @Override
    public ProvinciaDao getProvinciaDao() {
        return new ProvinciaDaoImp();
    }

    @Override
    public RespuestaDao getRespuestaDao() {
        return new RespuestaDaoImp();

    }

    @Override
    public ResultadoDao getResultadoDao() {
        return new ResultadoDaoImp();
    }

    @Override
    public TipoPreguntaDao getTipoPreguntaDao() {
        return new TipoPreguntaDaoImp();
    }

    @Override
    public UniversidadDao getUniversidadDao() {
        return new UniversidadDaoImp();
    }

    @Override
    public UsuarioDao getUsuarioDao() {
        return new UsuarioDaoImp();
    }

    @Override
    public PaginasDao getPaginasDao() {
        return new PaginasDaoImp();
    }

    @Override
    public PerfilPaginasDao getPerfilPaginasDao() {
        return new PerfilPaginasDaoImp();
    }

}
