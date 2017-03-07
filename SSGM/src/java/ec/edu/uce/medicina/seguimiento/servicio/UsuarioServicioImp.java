/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.servicio;

import ec.edu.uce.medicina.seguimiento.dao.UsuarioDao;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoAbstractFactory;
import ec.edu.uce.medicina.seguimiento.fabrica.DaoFactory;
import ec.edu.uce.medicina.seguimiento.modelo.Usuario;
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
public class UsuarioServicioImp implements UsuarioServicio {

    @EJB
    private UsuarioDao usuarioDao = null;

    public UsuarioServicioImp() {
        DaoFactory factoria = DaoAbstractFactory.getInstance();
        this.usuarioDao = factoria.getUsuarioDao();
    }

    @Override
    public void insertarUsuario(Usuario usuario) {
        usuarioDao.create(usuario);
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        usuarioDao.remove(usuario);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        usuarioDao.edit(usuario);
    }

    @Override
    public List<Usuario> listarUsuario() {
        return usuarioDao.findAll();
    }

    @Override
    public Usuario findUsuarioId(Integer usuarioId) {
        return usuarioDao.find(usuarioId);
    }

    @Override
    public Usuario buscarUsuarioPass(String user, String pass) {
        return usuarioDao.buscarUsuarioPass(user, pass);
    }

}
