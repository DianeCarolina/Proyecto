/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.controlador;

import ec.edu.uce.medicina.seguimiento.modelo.Usuario;
import ec.edu.uce.medicina.seguimiento.servicio.UsuarioServicio;
import ec.edu.uce.medicina.seguimiento.util.Constantes;
import ec.edu.uce.medicina.seguimiento.util.EncryptionUtility;
import ec.edu.uce.medicina.seguimiento.util.MensajesFaces;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * Esta clase EncuestaBean se encarga de enlazar los atributos con las páginas
 * XHTML el login del sistema, controlador que verifica los datos ingresados para la verificacion del usuario
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    /**
     * Serial version de la clase.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Atributo usuario
     */
    private Usuario user;
    /**
     * Atributo nombre del usuario el nick name
     */
    private String nombreUser;
    /**
     * Atributo nombre del usuario
     */
    private String password;
    /**
     * Atributo estado opcion de mantenimiento
     */
    private String estadoOpcionMantenimiento;
    /**
     * Transformara la clase en un java beans que puede ser gestionada por
     * contenedores ejb
     */
    @EJB
    private UsuarioServicio serUsuario;

    /**
     * Constructor por defecto
     */
    public LoginBean() {
    }

    /**
     * Inicialización de variables
     */
    @PostConstruct
    public void inicializar() {
        nombreUser = Constantes.VACIO;
        password = Constantes.VACIO;
        HttpSession iniSesion = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        iniSesion.setMaxInactiveInterval(5000);
    }

    /**
     * Método para la accion del boton del login comprobando las credenciales
     * ingresadas
     *
     * @return ruta
     * @throws java.lang.Exception
     */
    public String botonLogin() throws Exception {
        String ruta = Constantes.VACIO;
        user = serUsuario.buscarUsuarioPass(nombreUser, EncryptionUtility.encrypt(password));
        if (user != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", user);
            MensajesFaces.informacion("BIENVENIDO  " + user.getNombreUsuario(), "");
            ruta = "/paginas/administrador/inicioAdmin.jsf";
            estadoOpcionMantenimiento = (user.getIdPerfil().getTipoPerfil().equals("ADMINISTRADOR")) ? Constantes.VISIBLE : Constantes.NOVISIBLE;
        } else {
            nombreUser = Constantes.VACIO;
            password = Constantes.VACIO;
            MensajesFaces.advertencia("NO EXISTE EL USUARIO CON ESAS CREDENCIALES O NO ESTA HABILITADO", "");
        }
        return ruta;
    }

    /**
     * Método para cerrar la sesion
     *
     * @return ruta
     */
    public String cerrarSesion() {
        String ruta = Constantes.VACIO;
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) fc.getExternalContext().getSession(false);
        sesion.invalidate();
        ruta = "/paginas/inicio.jsf";

        return ruta;
    }
//get y set

    /**
     * Devuelve user
     *
     * @return user
     */
    public Usuario getUser() {
        return user;
    }

    /**
     * Modifica user
     *
     * @param user
     */
    public void setUser(Usuario user) {
        this.user = user;
    }

    /**
     * Devuelve nombreUser
     *
     * @return nombreUser
     */
    public String getNombreUser() {
        return nombreUser;
    }

    /**
     * Modifica nombreUser
     *
     * @param nombreUser
     */
    public void setNombreUser(String nombreUser) {
        this.nombreUser = nombreUser;
    }

    /**
     * Devuelve password
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Modifica password
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Devuelve estadoOpcionMantenimiento
     *
     * @return estadoOpcionMantenimiento
     */
    public String getEstadoOpcionMantenimiento() {
        return estadoOpcionMantenimiento;
    }

    /**
     * Modifica estadoOpcionMantenimiento
     *
     * @param estadoOpcionMantenimiento
     */
    public void setEstadoOpcionMantenimiento(String estadoOpcionMantenimiento) {
        this.estadoOpcionMantenimiento = estadoOpcionMantenimiento;
    }

}
