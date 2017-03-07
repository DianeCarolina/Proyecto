/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.controlador;

import ec.edu.uce.medicina.seguimiento.modelo.Universidad;
import ec.edu.uce.medicina.seguimiento.servicio.FacultadServicio;
import ec.edu.uce.medicina.seguimiento.servicio.UniversidadServicio;
import ec.edu.uce.medicina.seguimiento.util.MensajesFaces;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 * Esta clase UniversidadBean se encarga de enlazar los atributos con las
 * páginas XHTML en la universidad
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@ViewScoped
@ManagedBean
public class UniversidadBean implements Serializable {

    /**
     * Serial version de la clase.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Atributo de la universidad
     */
    private Universidad universidad;
    /**
     * Atributo de id de la Universidad
     */
    private int idUniversidad;
    /**
     * Atributo lista de la Universidad
     */
    private List<Universidad> listUniversidad;
    /**
     * Atributo tipo de la Universidad
     */
    private Map<String, String> tipoUniversidad;
    /**
     * Atributo request context
     */
    private RequestContext req;
    /**
     * Transformara la clase en un java beans que puede ser gestionada por
     * contenedores ejb
     */
    @EJB
    private UniversidadServicio serUniversidad;

    @EJB
    private FacultadServicio serFacultad;

    /**
     * Constructor por defecto
     */
    public UniversidadBean() {
    }

    /**
     * Inicialización de variables
     */
    @PostConstruct
    public void init() {
        universidad = new Universidad();
        listUniversidad = serUniversidad.listarUniversidad();
        tipoUniversidad = serUniversidad.tipoUniversidades();
    }

    /**
     * Método para crear una Universidad
     */
    public void ingresoUniversidad() {
        try {
            serUniversidad.insertarUniversidad(universidad);
            listUniversidad = serUniversidad.listarUniversidad();
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dlgUni').hide()");
            universidad = new Universidad();
            req = null;
            MensajesFaces.informacion("GUARDADO CORRECTAMENTE", "");
        } catch (Exception e) {
            MensajesFaces.error("NO SE PUDO GUARDAR", "" + e);
        }
    }

    /**
     * Método para cerrar la ventana de la universidad
     */
    public void cerrar() {
        universidad = new Universidad();
    }

    /**
     * Método para encontrar una universidad
     *
     * @param idUniversidad
     * @return universidad
     */
    public Universidad encontrarUniversidad(Integer idUniversidad) {
        universidad = serUniversidad.buscarUniversidadPorId(idUniversidad);
        return universidad;
    }

    /**
     * Método para actualizar una universidad
     */
    public void actualizarUniversidad() {
        try {
            serUniversidad.actualizarUniversidad(universidad);
            listUniversidad = serUniversidad.listarUniversidad();
            req = RequestContext.getCurrentInstance();
            req.execute("PF('uniDialog1').hide()");
            universidad = new Universidad();
            req = null;
            MensajesFaces.informacion("ACTUALIZADO CORRECTAMENTE", "");
        } catch (Exception e) {
            MensajesFaces.error("NO SE PUEDE ACTUALIZAR", "" + e);
        }

    }

    /**
     * Método para eliminar Universidad
     *
     * @param uni
     */
    public void eliminarUniversidad(Universidad uni) {
        try {
            boolean verificarUniversidadEnFacultad = serFacultad.buscarRegistroPorUniversidad(uni);
            if (verificarUniversidadEnFacultad) {
                MensajesFaces.informacion("NO SE PUEDE ELIMINAR", "");
            } else {
                serUniversidad.eliminarUniversidad(uni);
                MensajesFaces.informacion("ELIMINADO CORRECTAMENTE", "");
            }
        } catch (Exception e) {
            MensajesFaces.error("ERROR AL ELIMINAR", "" + e);
        }
        listUniversidad = serUniversidad.listarUniversidad();
    }

    // set y get
    /**
     * Devuelve universidad
     *
     * @return universidad
     */
    public Universidad getUniversidad() {
        return universidad;
    }

    /**
     * Modifica universidad
     *
     * @param universidad tipo Universidad
     */
    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }

    /**
     * Devuelve listUniversidad
     *
     * @return listUniversidad
     */
    public List<Universidad> getListUniversidad() {
        return listUniversidad;
    }

    /**
     * Modifica listUniversidad
     *
     * @param listUniversidad tipo lista
     */
    public void setListUniversidad(List<Universidad> listUniversidad) {
        this.listUniversidad = listUniversidad;
    }

    /**
     * Devuelve tipoUniversidad
     *
     * @return tipoUniversidad
     */
    public Map<String, String> getTipoUniversidad() {
        return tipoUniversidad;
    }

    /**
     * Modifica tipoUniversidad
     *
     * @param tipoUniversidad tipo hash map
     */
    public void setTipoUniversidad(Map<String, String> tipoUniversidad) {
        this.tipoUniversidad = tipoUniversidad;
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

}
