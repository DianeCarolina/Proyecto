/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.servicio;

import ec.edu.uce.medicina.seguimiento.modelo.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 * <b>
 * Descripcion de la clase.
 * </b>
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@Local
public interface UsuarioServicio {

    void insertarUsuario(Usuario usuario);

    void eliminarUsuario(Usuario usuario);

    void actualizarUsuario(Usuario usuario);

    List<Usuario> listarUsuario();

    Usuario findUsuarioId(Integer usuarioId);

    Usuario buscarUsuarioPass(String user, String pass);

}
