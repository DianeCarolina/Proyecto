/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.controlador;

import ec.edu.uce.medicina.seguimiento.modelo.Usuario;
import ec.edu.uce.medicina.seguimiento.servicio.PerfilServicio;
import ec.edu.uce.medicina.seguimiento.servicio.UsuarioServicio;
import ec.edu.uce.medicina.seguimiento.util.EncryptionUtility;
import ec.edu.uce.medicina.seguimiento.util.MensajesFaces;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

/**
 * Esta clase UsuarioBean se encarga de enlazar los atributos con las páginas
 * XHTML en el usuario
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@ViewScoped
@ManagedBean
public class UsuarioBean implements Serializable {

    /**
     * Serial version de la clase.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Atributo usuario
     */
    private Usuario nuevoUsuario;
    /**
     * Atributo contraseña del usuario
     */
    private String pass;
    /**
     * Atributo contraseña del usuario
     */
    private String contrasenia;
    /**
     * Atributo fecha de registro del usuario
     */
    private Date fechaRegistro;
    /**
     * Atributo lista items Perfil
     */
    private List<SelectItem> itemsPerfil;
    /**
     * Atributo id Perfil
     */
    private int idPerfil;
    /**
     * Atributo temporal usuario
     */
    private Usuario usuariotemp;
    /**
     * Atributo request context
     */
    private RequestContext req;
    /**
     * Transformara la clase en un java beans que puede ser gestionada por
     * contenedores ejb
     */

    @EJB
    private UsuarioServicio serUsuario;
    @EJB
    private PerfilServicio serPerfil;

    /**
     * Constructor por defecto
     */
    public UsuarioBean() {
    }

    /**
     * Inicialización de variables
     */
    @PostConstruct
    public void inicializar() {
        nuevoUsuario = new Usuario();
        fechaRegistro = new Date();
        itemsPerfil = serPerfil.oneMenuPerfil(serPerfil.listarPerfil());

    }

    /**
     * Método para crear un usuario
     */
    public void ingresoNuevoUsuario() {
        try {

            nuevoUsuario.setIdPerfil(serPerfil.buscarPerfilPorId(idPerfil));
            nuevoUsuario.setFechaRegistro(fechaRegistro);
            nuevoUsuario.setContrasenia(EncryptionUtility.encrypt(nuevoUsuario.getContrasenia()));
            serUsuario.insertarUsuario(nuevoUsuario);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dlgUsuario').hide()");
            nuevoUsuario = new Usuario();
            idPerfil = 0;
            req = null;
            MensajesFaces.informacion("GUARDADO CORRECTAMENTE", "");
        } catch (Exception e) {
            MensajesFaces.advertencia("NO SE PUDO GUARDAR", "" + e);
        }
    }

    /**
     * Método para cerrar la ventana del usuario
     */
    public void cerrar() {
        nuevoUsuario = new Usuario();
        idPerfil = 0;
    }

    /**
     * Método para listado del usuario
     *
     * @return lista de usuario
     */
    public List<Usuario> recuperarUsuarioTodos() {
        return serUsuario.listarUsuario();

    }

    /**
     * Método para encontrar usuario
     *
     * @param id
     * @return usuariotemp
     * @throws java.lang.Exception
     */
    public Usuario encontrar(int id) throws Exception {
        usuariotemp = new Usuario();
        usuariotemp = serUsuario.findUsuarioId(id);
        contrasenia = usuariotemp.getContrasenia();
        pass = EncryptionUtility.decrypt(contrasenia);
        usuariotemp.setContrasenia(pass);
        idPerfil = usuariotemp.getIdPerfil().getIdPerfil();
        return usuariotemp;
    }

    /**
     * Método para editar del usuario
     *
     */
    public void editarUsuario() {
        try {
            nuevoUsuario = usuariotemp;
            nuevoUsuario.setIdPerfil(serPerfil.buscarPerfilPorId(idPerfil));
            nuevoUsuario.setContrasenia(EncryptionUtility.encrypt(nuevoUsuario.getContrasenia()));
            serUsuario.actualizarUsuario(nuevoUsuario);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('userDialog1').hide()");//cierra la ventana despues de realizar un evento
            nuevoUsuario = new Usuario();
            req = null;
            MensajesFaces.informacion("ACTUALIZADO CORRECTAMENTE", "");
        } catch (Exception e) {
            MensajesFaces.advertencia("NO SE PUEDE ACTUALIZAR", "" + e);
        }
    }

    /**
     * Método para elimanar del usuario
     *
     * @param us
     */
    public void eliminarUsuario(Usuario us) {
        try {
            serUsuario.eliminarUsuario(us);
            nuevoUsuario = new Usuario();
            MensajesFaces.informacion("ELIMINADO CORRECTAMENTE", "");
        } catch (Exception e) {
            MensajesFaces.error("NO SE PUEDE ELIMINAR", "" + e);
        }
    }

    //get y set
    /**
     * Devuelve el usuario
     *
     * @return nuevoUsuario
     */
    public Usuario getNuevoUsuario() {
        return nuevoUsuario;
    }

    /**
     * Modifica el usuario
     *
     * @param nuevoUsuario
     */
    public void setNuevoUsuario(Usuario nuevoUsuario) {
        this.nuevoUsuario = nuevoUsuario;
    }

    /**
     * Devuelve la fecha registro
     *
     * @return fechaRegistro
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Modifica la fecha registro
     *
     * @param fechaRegistro
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * Devuelve el items perfil
     *
     * @return itemsPerfil
     */
    public List<SelectItem> getItemsPerfil() {
        return itemsPerfil;
    }

    /**
     * Modifica el items perfil
     *
     * @param itemsPerfil
     */
    public void setItemsPerfil(List<SelectItem> itemsPerfil) {
        this.itemsPerfil = itemsPerfil;
    }

    /**
     * Devuelve el id de perfil
     *
     * @return idPerfil
     */
    public int getIdPerfil() {
        return idPerfil;
    }

    /**
     * Modifica el id de perfil
     *
     * @param idPerfil
     */
    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    /**
     * Devuelve la variable temporal de usuario
     *
     * @return usuariotemp
     */
    public Usuario getUsuariotemp() {
        return usuariotemp;
    }

    /**
     * Modifica la variable temporal de usuario
     *
     * @param usuariotemp
     */
    public void setUsuariotemp(Usuario usuariotemp) {
        this.usuariotemp = usuariotemp;
    }

    /**
     * Devuelve la contraseña del usuario
     *
     * @return pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * Modifica la contraseña del usuario
     *
     * @param pass
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

}
