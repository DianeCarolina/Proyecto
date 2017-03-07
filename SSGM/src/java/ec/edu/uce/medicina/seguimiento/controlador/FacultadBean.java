/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.controlador;

import ec.edu.uce.medicina.seguimiento.modelo.Facultad;
import ec.edu.uce.medicina.seguimiento.servicio.CarreraServicio;
import ec.edu.uce.medicina.seguimiento.servicio.FacultadServicio;
import ec.edu.uce.medicina.seguimiento.servicio.UniversidadServicio;
import ec.edu.uce.medicina.seguimiento.util.MensajesFaces;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

/**
 * Esta clase FacultadBean se encarga de enlazar los atributos con las páginas
 * XHTML en la administración de la facultad
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@ViewScoped
@ManagedBean
public class FacultadBean implements Serializable {

    /**
     * Serial version de la clase.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Atributo facultad de tipo Facultad
     */
    private Facultad nuevaFacultad;
    /**
     * Atributo items de la universidad
     */
    private List<SelectItem> itemsUniversidad;
    /**
     * Atributo id de la universidad
     */
    private int idUniversidad;
    /**
     * Atributo lista de la facultad
     */
    private List<Facultad> listaFacultad;
    /**
     * Atributo temporal de la facultad
     */
    private Facultad facultadtemp;
    /**
     * Atributo request context
     */
    private RequestContext req;
    /**
     * Transformara la clase en un java beans que puede ser gestionada por
     * contenedores ejb
     */
    @EJB
    private FacultadServicio serFacultad;
    @EJB
    private UniversidadServicio serUniversidad;
    @EJB
    private CarreraServicio serCarrera;

    /**
     * Constructor por defecto
     */
    public FacultadBean() {
    }

    /**
     * Inicialización de variables
     */
    @PostConstruct
    public void inicializar() {
        nuevaFacultad = new Facultad();
        listaFacultad = serFacultad.listarFacultad();
        itemsUniversidad = serUniversidad.oneMenuUniversidad(serUniversidad.listarUniversidad());
    }

    /**
     * Método para ingreso Nueva Facultad
     */
    public void ingresoNuevaFacultad() {
        try {
            nuevaFacultad.setIdUniversidad(serUniversidad.buscarUniversidadPorId(idUniversidad));
            serFacultad.insertarFacultad(nuevaFacultad);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dlgFa').hide()"); //cerrar el dialogo después de realizar un evento
            nuevaFacultad = new Facultad();
            idUniversidad = 0;
            req = null;
            MensajesFaces.informacion("GUARDADO CORRECTAMENTE", "");
        } catch (Exception e) {
            MensajesFaces.error("NO SE PUDO GUARDAR", "" + e);
        }

    }

    /**
     * Método para cerrar la ventana de la facultad
     */
    public void cerrar() {
        nuevaFacultad = new Facultad();
        idUniversidad = 0;
    }

    /**
     * Método para encontrar la facultad
     *
     * @param id tipo entero
     * @return facultad temporal
     */
    public Facultad encontrar(int id) {
        facultadtemp = new Facultad();
        facultadtemp = serFacultad.buscarFacultadPorId(id);
        idUniversidad = facultadtemp.getIdUniversidad().getIdUniversidad();
        return facultadtemp;
    }

    /**
     * Método para editar facultad
     */
    public void editarFacultad() {
        try {
            nuevaFacultad = facultadtemp;
            nuevaFacultad.setIdUniversidad(serUniversidad.buscarUniversidadPorId(idUniversidad));
            serFacultad.actualizarFacultad(nuevaFacultad);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('facuDialog').hide()");
            nuevaFacultad = new Facultad();
            idUniversidad = 0;
            req = null;
            MensajesFaces.informacion("ACTUALIZADO CORRECTAMENTE", "");
        } catch (Exception e) {
            MensajesFaces.advertencia("NO SE PUEDE ACTUALIZAR", "" + e);
        }
    }

    /**
     * Método para listar facultad
     *
     * @return lista de la facultad
     */
    public List<Facultad> recuperarFacultadTodos() {
        return serFacultad.listarFacultad();

    }

    /**
     * Método para eliminar Facultad verificando si existe un registro en la
     * carrera
     *
     * @param fa tipo facultad
     */
    public void eliminarFacultad(Facultad fa) {
        try {
            boolean verificarFacultadEnCarrera = serCarrera.buscarRegistroPorFacultad(fa);
            if (verificarFacultadEnCarrera) {
                MensajesFaces.informacion("NO SE PUEDE ELIMINAR", "");
            } else {
                serFacultad.eliminarFacultad(fa);
                MensajesFaces.informacion("ELIMINADO CORRECTAMENTE", "");
            }

        } catch (Exception e) {
            MensajesFaces.advertencia("ERROR AL ELIMINAR", "" + e);
        }
    }

    //get y set
    /**
     * Devuelve nuevaFacultad
     *
     * @return nuevaFacultad
     */
    public Facultad getNuevaFacultad() {
        return nuevaFacultad;
    }

    /**
     * Modifica nuevaFacultad
     *
     * @param nuevaFacultad tipo Facultad
     */
    public void setNuevaFacultad(Facultad nuevaFacultad) {
        this.nuevaFacultad = nuevaFacultad;
    }

    /**
     * Devuelve itemsUniversidad
     *
     * @return itemsUniversidad
     */
    public List<SelectItem> getItemsUniversidad() {
        return itemsUniversidad;
    }

    /**
     * Modifica itemsUniversidad
     *
     * @param itemsUniversidad tipo lista
     */
    public void setItemsUniversidad(List<SelectItem> itemsUniversidad) {
        this.itemsUniversidad = itemsUniversidad;
    }

    /**
     * Devuelve idUniversidad
     *
     * @return idUniversidad
     */
    public int getIdUniversidad() {
        return idUniversidad;
    }

    /**
     * Modifica idUniversidad
     *
     * @param idUniversidad tipo entero
     */
    public void setIdUniversidad(int idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    /**
     * Devuelve listaFacultad
     *
     * @return listaFacultad
     */
    public List<Facultad> getListaFacultad() {
        return listaFacultad;
    }

    /**
     * Modifica listaFacultad
     *
     * @param listaFacultad tipo lista
     */
    public void setListaFacultad(List<Facultad> listaFacultad) {
        this.listaFacultad = listaFacultad;
    }

    /**
     * Devuelve facultadtemp
     *
     * @return facultadtemp
     */
    public Facultad getFacultadtemp() {
        return facultadtemp;
    }

    /**
     * Modifica facultadtemp
     *
     * @param facultadtemp tipo Facultad
     */
    public void setFacultadtemp(Facultad facultadtemp) {
        this.facultadtemp = facultadtemp;
    }

}
