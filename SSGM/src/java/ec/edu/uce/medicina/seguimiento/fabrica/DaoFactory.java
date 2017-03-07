/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.fabrica;

import ec.edu.uce.medicina.seguimiento.dao.CarreraDao;
import ec.edu.uce.medicina.seguimiento.dao.CategoriaDao;
import ec.edu.uce.medicina.seguimiento.dao.CiudadDao;
import ec.edu.uce.medicina.seguimiento.dao.EncuestaDao;
import ec.edu.uce.medicina.seguimiento.dao.EstadoCivilDao;
import ec.edu.uce.medicina.seguimiento.dao.FacultadDao;
import ec.edu.uce.medicina.seguimiento.dao.PaginasDao;
import ec.edu.uce.medicina.seguimiento.dao.PaisDao;
import ec.edu.uce.medicina.seguimiento.dao.PerfilDao;
import ec.edu.uce.medicina.seguimiento.dao.PerfilPaginasDao;
import ec.edu.uce.medicina.seguimiento.dao.PersonaDao;
import ec.edu.uce.medicina.seguimiento.dao.PreguntaDao;
import ec.edu.uce.medicina.seguimiento.dao.ProvinciaDao;
import ec.edu.uce.medicina.seguimiento.dao.RespuestaDao;
import ec.edu.uce.medicina.seguimiento.dao.ResultadoDao;
import ec.edu.uce.medicina.seguimiento.dao.TipoPreguntaDao;
import ec.edu.uce.medicina.seguimiento.dao.UniversidadDao;
import ec.edu.uce.medicina.seguimiento.dao.UsuarioDao;


/**
 * <b>
 * Descripción de la clase.
 * </b>
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
public interface DaoFactory {

    public CarreraDao getCarreraDao();

    public CategoriaDao getCtegoriaDao();

    public CiudadDao getCiudadDao();

    public EncuestaDao getEncuestaDao();

    public EstadoCivilDao getEstadoCivilDao();

    public FacultadDao getFacultadDao();

    public PaisDao getPaisDao();

    public PerfilDao getPerfilDao();

    public PersonaDao getPersonaDao();

    public PreguntaDao getPreguntaDao();

    public ProvinciaDao getProvinciaDao();

    public RespuestaDao getRespuestaDao();

    public ResultadoDao getResultadoDao();

    public TipoPreguntaDao getTipoPreguntaDao();

    public UniversidadDao getUniversidadDao();

    public UsuarioDao getUsuarioDao();

    public PaginasDao getPaginasDao();

    public PerfilPaginasDao getPerfilPaginasDao();

  
}
