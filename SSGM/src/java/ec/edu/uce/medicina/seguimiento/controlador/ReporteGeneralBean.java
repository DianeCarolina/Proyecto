/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.controlador;

import ec.edu.uce.medicina.seguimiento.modelo.Carrera;
import ec.edu.uce.medicina.seguimiento.modelo.Encuesta;
import ec.edu.uce.medicina.seguimiento.servicio.CarreraServicio;
import ec.edu.uce.medicina.seguimiento.servicio.EncuestaServicio;
import ec.edu.uce.medicina.seguimiento.servicio.FacultadServicio;
import ec.edu.uce.medicina.seguimiento.servicio.PreguntaServicio;
import ec.edu.uce.medicina.seguimiento.util.Constantes;
import ec.edu.uce.medicina.seguimiento.util.MensajesFaces;
import java.io.Serializable;
import java.util.Date;
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
 * Esta clase CarreraBean se encarga de enlazar los atributos con las páginas
 * XHTML conocido como Back beans, generar el reporte
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@ViewScoped
@ManagedBean
public class ReporteGeneralBean implements Serializable {

    /**
     * Serial version de la clase.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Atributo NOMBRE_REPORTE_JASPER
     */
    private String NOMBRE_REPORTE_JASPER = null;
    /**
     * Atributo opcion del reporte
     */
    private int opcionReporte;
    /**
     * Atributo id carrera
     */
    private int idCarrera;
    /**
     * Atributo id facultad
     */
    private int idFacultad;
    /**
     * Atributo id encuesta
     */
    private int idEncuesta;
    /**
     * Atributo estado del combo de la facultad
     */
    private String estadoComboFacultad;
    /**
     * Atributo estado del combo de la carrera
     */
    private String estadoComboCarrera;
    /**
     * Atributo estado del combo de la encuesta
     */
    private String estadoComboEncuesta;
    /**
     * Atributo enuesta
     */
    private Encuesta enuesta;
    /**
     * Atributo items de la carrera
     */
    private List<SelectItem> itemsCarrera;
    /**
     * Atributo items de la encuesta
     */
    private List<SelectItem> itemsEncuesta;
    /**
     * Atributo items de la Facultad
     */
    private List<SelectItem> itemsFacultad;
    /**
     * Atributo nombre del reporte
     */
    private String nombreReporte;
    /**
     * Atributo carrera
     */
    private Carrera carrera;
    /**
     * Atributo encuesta
     */
    private Encuesta encuesta;
    /**
     * Atributo id pregunta
     */
    private int idPregunta;
    /**
     * Atributo de la fecha de inicio para generar el reporte en este rango
     */
    private Date fechaDesde;
    /**
     * Atributo de la fecha fin para generar el reporte en este rango
     */
    private Date fechaHasta;

    /**
     * Transforma la clase en un java beans para que puede ser gestionada por
     * contenedores ejb
     */
    @EJB
    private CarreraServicio servCarrera;
    @EJB
    private PreguntaServicio servPregunta;
    @EJB
    private EncuestaServicio servEncuesta;
    @EJB
    private FacultadServicio servFacultad;
    @ManagedProperty("#{generadorReportes}")
    private GeneradorReportes generadorJasper;

    /**
     * Inicialización de variables
     */
    @PostConstruct
    private void ini() {
        this.estadoComboFacultad = Constantes.INACTIVO;
        this.estadoComboCarrera = Constantes.INACTIVO;
        this.estadoComboEncuesta = Constantes.INACTIVO;
        itemsCarrera = servCarrera.oneMenuCarrera(servCarrera.listarCarrera());
        itemsFacultad = servFacultad.oneMenuFacultad(servFacultad.listarFacultad());

    }

    /**
     * Metodo que va a controlar el estado del combo para la seleccion de
     * reportes
     *
     * @param ent
     */
    public void getCombo(AjaxBehaviorEvent ent) {
        switch (opcionReporte) {
            case 0:
                this.estadoComboFacultad = Constantes.INACTIVO;
                this.estadoComboCarrera = Constantes.INACTIVO;
                this.estadoComboEncuesta = Constantes.INACTIVO;
                idFacultad = 0;
                idCarrera = 0;
                idEncuesta = 0;
                break;
            case 1:
                this.estadoComboFacultad = Constantes.INACTIVO;
                this.estadoComboCarrera = Constantes.INACTIVO;
                this.estadoComboEncuesta = Constantes.INACTIVO;
                idEncuesta = 0;
                idFacultad = 0;
                idCarrera = 0;
                break;

            case 2:
                this.estadoComboFacultad = Constantes.ACTIVO;
                this.estadoComboCarrera = Constantes.INACTIVO;
                this.estadoComboEncuesta = Constantes.INACTIVO;
                idEncuesta = 0;
                if (idFacultad != 0) {
                    this.itemsCarrera = servCarrera.oneMenuCarrera(this.servCarrera.buscarCarreraPorFacultad(idFacultad));
                    if (!itemsCarrera.isEmpty()) {
                        this.estadoComboCarrera = Constantes.ACTIVO;
                    } else {
                        MensajesFaces.informacion("NO SE ENCONTRARON CARRERAS PARA LA FACULTAD ELEJIDA", null);
                    }

                } else {
                    this.estadoComboCarrera = Constantes.INACTIVO;
                    this.estadoComboEncuesta = Constantes.INACTIVO;
                    idEncuesta = 0;
                    idFacultad = 0;
                }
                if (idCarrera != 0) {
                    this.itemsEncuesta = servEncuesta.oneMenuEncuesta(servEncuesta.listaEncuestaPorCarrera(idCarrera));
                    if (!itemsEncuesta.isEmpty()) {
                        this.estadoComboEncuesta = Constantes.ACTIVO;
                    } else {

                        MensajesFaces.informacion("NO SE ENCONTRARON ENCUESTAS PARA LA CARRERA ELEGIDA", null);
                    }
                } else {
                    this.estadoComboEncuesta = Constantes.INACTIVO;
                    idEncuesta = 0;
                }
                break;
            case 3:
                idCarrera = 0;
                idEncuesta = 0;
                this.estadoComboFacultad = Constantes.ACTIVO;
                this.estadoComboCarrera = Constantes.INACTIVO;
                this.estadoComboEncuesta = Constantes.INACTIVO;
                if (idFacultad != 0) {
                    this.itemsCarrera = servCarrera.oneMenuCarrera(this.servCarrera.buscarCarreraPorFacultad(idFacultad));
                    if (!itemsCarrera.isEmpty()) {
                        this.estadoComboCarrera = Constantes.ACTIVO;
                    } else {
                        MensajesFaces.informacion("NO SE ENCONTRARON CARRERAS PARA LA FACULTAD ELEJIDA", null);
                    }

                } else {
                    this.estadoComboCarrera = Constantes.INACTIVO;
                    this.estadoComboEncuesta = Constantes.INACTIVO;
                    idEncuesta = 0;
                    idFacultad = 0;
                    idCarrera = 0;
                }
                break;

            case 4:

                idCarrera = 0;
                idEncuesta = 0;
                this.estadoComboFacultad = Constantes.ACTIVO;
                this.estadoComboCarrera = Constantes.INACTIVO;
                this.estadoComboEncuesta = Constantes.INACTIVO;
                if (idFacultad != 0) {
                    this.itemsCarrera = servCarrera.oneMenuCarrera(this.servCarrera.buscarCarreraPorFacultad(idFacultad));
                    if (!itemsCarrera.isEmpty()) {
                        this.estadoComboCarrera = Constantes.ACTIVO;
                    } else {
                        MensajesFaces.informacion("NO SE ENCONTRARON CARRERAS PARA LA FACULTAD ELEJIDA", null);
                    }

                } else {
                    this.estadoComboCarrera = Constantes.INACTIVO;
                    this.estadoComboEncuesta = Constantes.INACTIVO;
                    idEncuesta = 0;
                    idFacultad = 0;
                    idCarrera = 0;
                }
                break;
        }
    }

    /**
     * Metodo que permite por medio de un switch controlar y ejecutar la accion
     * segun sea el caso, y descarga segun el nombre que se escuentra en dicha
     * variable.
     */
    public void exportarPDF() {
        switch (opcionReporte) {
            case 0:
                MensajesFaces.informacion("INFO", "SELECCIONE UN REPORTE");
                break;
            case 1:
                if (opcionReporte == 1) {
                    this.nombreReporte = "ReporteGeneral-FacultadCarrera";
                    exportarPDF1();

                    this.estadoComboFacultad = Constantes.INACTIVO;
                    this.estadoComboCarrera = Constantes.INACTIVO;
                    this.estadoComboEncuesta = Constantes.INACTIVO;
                    idFacultad = 0;
                    idCarrera = 0;
                    idEncuesta = 0;
                }
                break;
            case 2:
                if (opcionReporte == 2) {
                    encuesta = servEncuesta.findEncuestaById(idEncuesta);
                    if (encuesta != null) {
                        this.nombreReporte = "ListadoEncuesta-" + encuesta.getTituloEncuesta();
                        exportarPDF1();
                    } else {
                        MensajesFaces.informacion("NO EXISTE", "EXISTE");
                    }
                    this.estadoComboFacultad = Constantes.INACTIVO;
                    this.estadoComboCarrera = Constantes.INACTIVO;
                    this.estadoComboEncuesta = Constantes.INACTIVO;
                    idFacultad = 0;
                    idCarrera = 0;
                    idEncuesta = 0;
                }
                break;

            case 3:
                if (opcionReporte == 3) {
                    carrera = servCarrera.buscarCarreraPorId(idCarrera);
                    if (carrera != null) {
                        this.nombreReporte = "NumeroEncuestados-" + carrera.getNomCarrera();
                        exportarPDF1();
                    } else {
                        MensajesFaces.informacion("NO EXISTE", "EXISTE");
                    }
                    this.estadoComboFacultad = Constantes.INACTIVO;
                    this.estadoComboCarrera = Constantes.INACTIVO;
                    this.estadoComboEncuesta = Constantes.INACTIVO;
                    idFacultad = 0;
                    idCarrera = 0;
                    idEncuesta = 0;
                }
                break;
            case 4:
                if (opcionReporte == 4) {
                    carrera = servCarrera.buscarCarreraPorId(idCarrera);
                    if (carrera != null) {
                        this.nombreReporte = "ListadoEncuesta-" + carrera.getNomCarrera();
                        exportarPDF1();
                    } else {
                        MensajesFaces.informacion("NO EXISTE", "EXISTE");
                    }
                    this.estadoComboFacultad = Constantes.INACTIVO;
                    this.estadoComboCarrera = Constantes.INACTIVO;
                    this.estadoComboEncuesta = Constantes.INACTIVO;
                    idFacultad = 0;
                    idCarrera = 0;
                    idEncuesta = 0;
                }
                break;

        }
    }

    /**
     * Metodo que invoca al reporte.
     */
    public void exportarPDF1() {
        @SuppressWarnings("rawtypes")
        Map parametros = new HashMap();
        try {
            switch (opcionReporte) {
                case 0:
                    MensajesFaces.informacion("INFO", "SELECCIONE UN REPORTE");
                    break;
                case 1:
                    try {
                        NOMBRE_REPORTE_JASPER = "ReporteUniversidad.jasper";
                        generadorJasper.setNombreJasper(NOMBRE_REPORTE_JASPER);
                        generadorJasper.setNombreReporte(nombreReporte);
                        generadorJasper.generarPDF();
                    } catch (Exception e) {
                        MensajesFaces.error("NO SE PUEDE GENERAR EL REPORTE" + e, null);
                    }
                    break;
                case 2:
                    try {
                        NOMBRE_REPORTE_JASPER = "ReporteListado.jasper";
                        generadorJasper.setNombreJasper(NOMBRE_REPORTE_JASPER);
                        generadorJasper.setNombreReporte(nombreReporte);
                        parametros.put("titulo", encuesta.getTituloEncuesta());
                        parametros.put("encuesta", idEncuesta);
                        generadorJasper.setParametrosReporte(parametros);
                        generadorJasper.generarPDF();
                    } catch (Exception e) {
                        MensajesFaces.error("NO SE PUEDE GENERAR EL REPORTE" + e, null);
                    }
                    break;

                case 3:
                    try {
                        NOMBRE_REPORTE_JASPER = "NumEncuestados.jasper";
                        generadorJasper.setNombreJasper(NOMBRE_REPORTE_JASPER);
                        generadorJasper.setNombreReporte(nombreReporte);
                        parametros.put("idCarrera", idCarrera);
                        generadorJasper.setParametrosReporte(parametros);
                        generadorJasper.generarPDF();
                    } catch (Exception e) {
                        MensajesFaces.error("NO SE PUEDE GENERAR EL REPORTE" + e, null);
                    }
                    break;

                case 4:
                    try {
                        NOMBRE_REPORTE_JASPER = "ListadoPorCarrera.jasper";
                        generadorJasper.setNombreJasper(NOMBRE_REPORTE_JASPER);
                        generadorJasper.setNombreReporte(nombreReporte);
                        parametros.put("idCarrera", idCarrera);
                        generadorJasper.setParametrosReporte(parametros);
                        generadorJasper.generarPDF();
                    } catch (Exception e) {
                        MensajesFaces.error("NO SE PUEDE GENERAR EL REPORTE" + e, null);
                    }
                    break;

            }
        } catch (Exception e) {
            MensajesFaces.error("NO SE PUEDE GENERAR EL REPORTE" + e, null);
        }

    }

    /**
     * Devuelve el NOMBRE REPORTE JASPER
     *
     * @return NOMBRE_REPORTE_JASPER
     */
    public String getNOMBRE_REPORTE_JASPER() {
        return NOMBRE_REPORTE_JASPER;
    }

    /**
     * Modifica el id carrera
     *
     * @param NOMBRE_REPORTE_JASPER
     */
    public void setNOMBRE_REPORTE_JASPER(String NOMBRE_REPORTE_JASPER) {
        this.NOMBRE_REPORTE_JASPER = NOMBRE_REPORTE_JASPER;
    }

    /**
     * Devuelve la opcion del reporte
     *
     * @return opcionReporte
     */
    public int getOpcionReporte() {
        return opcionReporte;
    }

    /**
     * Devuelve la opcion del reporte
     *
     * @param opcionReporte
     */
    public void setOpcionReporte(int opcionReporte) {
        this.opcionReporte = opcionReporte;
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
     * Devuelve el estado del combo de carrera
     *
     * @return estadoComboCarrera
     */
    public String getEstadoComboCarrera() {
        return estadoComboCarrera;
    }

    /**
     * Modifica el estado del combo de carrera
     *
     * @param estadoComboCarrera
     */
    public void setEstadoComboCarrera(String estadoComboCarrera) {
        this.estadoComboCarrera = estadoComboCarrera;
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
     * Devuelve el generador de Jasper
     *
     * @return generadorJasper
     */
    public GeneradorReportes getGeneradorJasper() {
        return generadorJasper;
    }

    /**
     * Modifica el generador de Jasper
     *
     * @param generadorJasper
     */
    public void setGeneradorJasper(GeneradorReportes generadorJasper) {
        this.generadorJasper = generadorJasper;
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
     * Devuelve la enuesta
     *
     * @return enuesta
     */
    public Encuesta getEnuesta() {
        return enuesta;
    }

    /**
     * Modifica la enuesta
     *
     * @param enuesta
     */
    public void setEnuesta(Encuesta enuesta) {
        this.enuesta = enuesta;
    }

    /**
     * Devuelve el items de la carrera
     *
     * @return itemsCarrera
     */
    public List<SelectItem> getItemsCarrera() {
        return itemsCarrera;
    }

    /**
     * Modifica el items de la carrera
     *
     * @param itemsCarrera
     */
    public void setItemsCarrera(List<SelectItem> itemsCarrera) {
        this.itemsCarrera = itemsCarrera;
    }

    /**
     * Devuelve items de encuesta
     *
     * @return itemsEncuesta
     */
    public List<SelectItem> getItemsEncuesta() {
        return itemsEncuesta;
    }

    /**
     * Modifica items de encuesta
     *
     * @param itemsEncuesta
     */
    public void setItemsEncuesta(List<SelectItem> itemsEncuesta) {
        this.itemsEncuesta = itemsEncuesta;
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

    /**
     * Devuelve el estado combo encuesta
     *
     * @return estadoComboEncuesta
     */
    public String getEstadoComboEncuesta() {
        return estadoComboEncuesta;
    }

    /**
     * Devuelve el estado combo facultad
     *
     * @return estadoComboFacultad
     */
    public String getEstadoComboFacultad() {
        return estadoComboFacultad;
    }

    /**
     * Modifica estado del combo de la facultad
     *
     * @param estadoComboFacultad
     */
    public void setEstadoComboFacultad(String estadoComboFacultad) {
        this.estadoComboFacultad = estadoComboFacultad;
    }

    /**
     * Devuelve el idFacultad
     *
     * @return idFacultad
     */
    public int getIdFacultad() {
        return idFacultad;
    }

    /**
     * Modifica el idFacultad
     *
     * @param idFacultad
     */
    public void setIdFacultad(int idFacultad) {
        this.idFacultad = idFacultad;
    }

    /**
     * Devuelve el itemsFacultad
     *
     * @return itemsFacultad
     */
    public List<SelectItem> getItemsFacultad() {
        return itemsFacultad;
    }

    /**
     * Modifica el itemsFacultad
     *
     * @param itemsFacultad
     */
    public void setItemsFacultad(List<SelectItem> itemsFacultad) {
        this.itemsFacultad = itemsFacultad;
    }

    /**
     * Modifica el estado combo encuesta
     *
     * @param estadoComboEncuesta
     */
    public void setEstadoComboEncuesta(String estadoComboEncuesta) {
        this.estadoComboEncuesta = estadoComboEncuesta;
    }

    /**
     * Devuelve el idPregunta
     *
     * @return idPregunta
     */
    public int getIdPregunta() {
        return idPregunta;
    }

    /**
     * Modifica id pregunta
     *
     * @param idPregunta
     */
    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    /**
     * Devuelve el fechaDesde
     *
     * @return fechaDesde
     */
    public Date getFechaDesde() {
        return fechaDesde;
    }

    /**
     * Modifica fecha desde
     *
     * @param fechaDesde
     */
    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    /**
     * Devuelve fechaHasta
     *
     * @return fechaHasta
     */
    public Date getFechaHasta() {
        return fechaHasta;
    }

    /**
     * Modifica la fecha hasta
     *
     * @param fechaHasta
     */
    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

}
