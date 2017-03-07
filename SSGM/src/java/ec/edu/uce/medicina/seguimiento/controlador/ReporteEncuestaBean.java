/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.controlador;

import com.cursojsf.validadores.Cedula;
import ec.edu.uce.medicina.seguimiento.modelo.Carrera;
import ec.edu.uce.medicina.seguimiento.modelo.Encuesta;
import ec.edu.uce.medicina.seguimiento.modelo.Persona;
import ec.edu.uce.medicina.seguimiento.servicio.CarreraServicio;
import ec.edu.uce.medicina.seguimiento.servicio.EncuestaServicio;
import ec.edu.uce.medicina.seguimiento.servicio.PersonaServicio;
import ec.edu.uce.medicina.seguimiento.util.Constantes;
import ec.edu.uce.medicina.seguimiento.util.MensajesFaces;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

/**
 * Esta clase ReporteEncuestaBean se encarga de enlazar los atributos con las
 * páginas XHTML en la visualización del reporte del encuestado
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@ManagedBean(name = "rEncuesta")
@ViewScoped
public class ReporteEncuestaBean implements Serializable {

    /**
     * Serial version de la clase.
     */
    private static final long serialVersionUID = 1L;
    /**
     * NOMBRE_REPORTE_JASPER
     */
    private String NOMBRE_REPORTE_JASPER = null;
    /**
     * Atributo lista de carreras existentes
     */
    private List<SelectItem> itemsCarrera;
    /**
     * Atributo lista de encuestas existentes
     */
    private List<SelectItem> itemsEncuesta;
    /**
     * Atributo id carrerra en el que esta el estudiante
     */
    private int idCarrera;
    /**
     * Atributo id encuesta del estudiante
     */
    private int idEncuesta;
    /**
     * Atributo estado Combo Encuesta
     */
    private String estadoComboEncuesta;
    /**
     * Atributo numero de cedula del estudiante
     */
    @Cedula(message = "CÉDULA INCORRECTA")
    private String cedula;
    /**
     * Atributo de la persona de tipo persona
     */
    private Persona persona;
    /**
     * Atributo carrera de la persona
     */
    private Carrera carrera;
    /**
     * Atributo encuesta de tipo encuesta
     */
    private Encuesta encuesta;
    /**
     * Transformara la clase en un java beans que puede ser gestionada por
     * contenedores ejb
     */
    @EJB
    private PersonaServicio serPersona;
    @EJB
    private CarreraServicio serCarrera;
    @EJB
    private EncuestaServicio serEncuesta;

    @ManagedProperty("#{generadorReportes}")
    private GeneradorReportes generadorJasper;
    /**
     * Nombre del reporte
     */
    private String nombreReporte;

    /**
     * Constructor por defecto
     */
    public ReporteEncuestaBean() {
    }

    /**
     * Inicialización de variables
     */
    @PostConstruct
    private void ini() {
        this.estadoComboEncuesta = Constantes.INACTIVO;
        itemsCarrera = serCarrera.oneMenuCarrera(serCarrera.listarCarrera());
        idEncuesta = 0;
        idCarrera = 0;
    }

    /**
     * Cambio de estado del combo box
     *
     * @param event
     */
    public void getCombo(AjaxBehaviorEvent event) {
        if (idCarrera != 0) {
            this.itemsEncuesta = serEncuesta.oneMenuEncuesta(serEncuesta.listaEncuestaPorCarrera(idCarrera));
            if (!itemsEncuesta.isEmpty()) {
                this.estadoComboEncuesta = Constantes.ACTIVO;
            } else {
                MensajesFaces.informacion("NO SE ENCONTRARON ENCUESTAS PARA LA CARRERA ELEGIDA", null);
            }
        } else {
            this.estadoComboEncuesta = Constantes.INACTIVO;
            idEncuesta = 0;
        }
    }

    /**
     * Método para validar encuesta por medio del id encuesta
     */
    public void validarEncuesta() {
        encuesta = serEncuesta.findEncuestaById(idEncuesta);
        if (encuesta != null) {
            this.nombreReporte = "ListadoEncuesta-" + encuesta.getTituloEncuesta();// el nombre con el que aparecera al momento de descargar el reporte
            exportarPDF1();// invoca al metodo exportarPDF
        } else {
            MensajesFaces.informacion("NO", "EXISTE");
        }
    }

    /**
     * Método para validar Persona por medio del ingreso de la cedula de
     * identidad del graduado
     */
    public void validarPersona() {
        persona = serPersona.buscarPersonaPorCedula(cedula);
        if (persona != null) {
            this.nombreReporte = "ReporteGeneralEncuesta-" + persona.getApellidos();
            exportarPDF();
        } else {
            MensajesFaces.informacion("NO", "EXISTE");
        }
    }

    /**
     * Método para exportar pdf el listado de la encuesta segun el titulo de la
     * encuesta
     */
    private void exportarPDF1() {
        try {
            @SuppressWarnings("rawtypes")
            Map parametros = new HashMap();
            NOMBRE_REPORTE_JASPER = "ReporteListado.jasper";
            generadorJasper.setNombreJasper(NOMBRE_REPORTE_JASPER);
            generadorJasper.setNombreReporte(nombreReporte);
            parametros.put("titulo", encuesta.getTituloEncuesta());
            parametros.put("encuesta", idEncuesta);
            generadorJasper.setParametrosReporte(parametros);
            generadorJasper.generarPDF();
        } catch (Exception e) {
            MensajesFaces.error("NO SE PUEDE GENERAR EL REPORTE", null);
        }
    }

    /**
     * Método para exportar en PDF un reporte generadouna ves ingresado los
     * parametros como la cedula de identidad del graduado
     */
    private void exportarPDF() {
        try {
            @SuppressWarnings("rawtypes")
            Map parametros = new HashMap();
            NOMBRE_REPORTE_JASPER = "ReporteEncuesta.jasper";
            generadorJasper.setNombreJasper(NOMBRE_REPORTE_JASPER);
            generadorJasper.setNombreReporte(nombreReporte);
            parametros.put("cedula", cedula);
            generadorJasper.setParametrosReporte(parametros);
            generadorJasper.generarPDF();
        } catch (Exception e) {
            MensajesFaces.error("NO SE PUEDE GENERAR EL REPORTE" + e, null);
        }

    }

    /**
     * Devuelve el nombre del reporte
     *
     * @return nombreReporte
     */
    public String getNombreReporte() {
        return nombreReporte;
    }

    /**
     * Modifica el nombre del reporte
     *
     * @param nombreReporte
     */
    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }

    /**
     * Devuelve la cedula de la persona
     *
     * @return cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Modifica la cedula de la persona
     *
     * @param cedula
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Devuelve generador Jasper de un reporte en JasperSoft
     *
     * @return generadorJasper
     */
    public GeneradorReportes getGeneradorJasper() {
        return generadorJasper;
    }

    /**
     * Modifica generador Jasper de un reporte en JasperSoft
     *
     * @param generadorJasper
     */
    public void setGeneradorJasper(GeneradorReportes generadorJasper) {
        this.generadorJasper = generadorJasper;
    }

    /**
     * Devuelve el objeto persona
     *
     * @return persona
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * Modifica el objeto persona
     *
     * @param persona
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    /**
     * Devuelve de items de la carrera
     *
     * @return itemsCarrera
     */
    public List<SelectItem> getItemsCarrera() {
        return itemsCarrera;
    }

    /**
     * Modifica de items de la carrera
     *
     * @param itemsCarrera
     */
    public void setItemsCarrera(List<SelectItem> itemsCarrera) {
        this.itemsCarrera = itemsCarrera;
    }

    /**
     * Devuelve el items de la encuesta
     *
     * @return itemsEncuesta
     */
    public List<SelectItem> getItemsEncuesta() {
        return itemsEncuesta;
    }

    /**
     * Modifica el items de la encuesta
     *
     * @param itemsEncuesta
     */
    public void setItemsEncuesta(List<SelectItem> itemsEncuesta) {
        this.itemsEncuesta = itemsEncuesta;
    }

    /**
     * Devuelve el NOMBRE de REPORTE JASPER
     *
     * @return NOMBRE_REPORTE_JASPER
     */
    public String getNOMBRE_REPORTE_JASPER() {
        return NOMBRE_REPORTE_JASPER;
    }

    /**
     * Modifica el NOMBRE de REPORTE JASPER
     *
     * @param NOMBRE_REPORTE_JASPER
     */
    public void setNOMBRE_REPORTE_JASPER(String NOMBRE_REPORTE_JASPER) {
        this.NOMBRE_REPORTE_JASPER = NOMBRE_REPORTE_JASPER;
    }

    /**
     * Devuelve el id carrera
     *
     * @return idCarrera
     */
    public int getIdCarrera() {
        return idCarrera;
    }

    /**
     * Modifica el id carrera
     *
     * @param idCarrera
     */
    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    /**
     * Devuelve el id de la encuesta
     *
     * @return idEncuesta
     */
    public int getIdEncuesta() {
        return idEncuesta;
    }

    /**
     * Modifica el id de la encuesta
     *
     * @param idEncuesta
     */
    public void setIdEncuesta(int idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    /**
     * Devuelve el estado combo de la encuesta
     *
     * @return estadoComboEncuesta
     */
    public String getEstadoComboEncuesta() {
        return estadoComboEncuesta;
    }

    /**
     * Modifica el estado combo de la encuesta
     *
     * @param estadoComboEncuesta
     */
    public void setEstadoComboEncuesta(String estadoComboEncuesta) {
        this.estadoComboEncuesta = estadoComboEncuesta;
    }

    /**
     * Devuelve la carrera
     *
     * @return carrera
     */
    public Carrera getCarrera() {
        return carrera;
    }

    /**
     * Modifica la carrera
     *
     * @param carrera
     */
    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    /**
     * Devuelve la encuesta
     *
     * @return encuesta
     */
    public Encuesta getEncuesta() {
        return encuesta;
    }

    /**
     * Modifica la encuesta
     *
     * @param encuesta
     */
    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }
}
