/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.controlador;

import com.cursojsf.validadores.Cedula;
import ec.edu.uce.medicina.seguimiento.modelo.Carrera;
import ec.edu.uce.medicina.seguimiento.modelo.Persona;
import ec.edu.uce.medicina.seguimiento.servicio.CarreraServicio;
import ec.edu.uce.medicina.seguimiento.servicio.CiudadServicio;
import ec.edu.uce.medicina.seguimiento.servicio.EstadoCivilServicio;
import ec.edu.uce.medicina.seguimiento.servicio.PaisServicio;
import ec.edu.uce.medicina.seguimiento.servicio.PersonaServicio;
import ec.edu.uce.medicina.seguimiento.servicio.ProvinciaServicio;
import ec.edu.uce.medicina.seguimiento.servicio.ResultadoServicio;
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
import org.primefaces.context.RequestContext;

/**
 * Esta clase EstudianteBean se encarga de enlazar los atributos con las páginas
 * XHTML en la visualización del graduado
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@ManagedBean
@ViewScoped
public class EstudianteBean implements Serializable {

    /**
     * Serial version de la clase.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Atributo de persona de tipo persona
     */
    private Persona persona;
    /**
     * Atributo items de carrera que existen en el sistema
     */
    private List<SelectItem> itemsCarrera;
    /**
     * Atributo id carrerra
     */
    private Integer idCarrera;
    /**
     * Atributo lista de estado civil
     */
    private List<SelectItem> itemsEstadoCivil;
    /**
     * Atributo id Estado civil de la persona
     */
    private Integer idEstadoCivil;
    /**
     * Atributo lista de ciudades segun el país
     */
    private List<SelectItem> itemsCiudad;
    /**
     * Atributo id de la ciudad según las provincias
     */
    private Integer idCiudad;
    /**
     * Atributo lista de provincias
     */
    private List<SelectItem> itemsProvincia;
    /**
     * Atributo id provincia
     */
    private Integer idProvincia;
    /**
     * Atributo lista de país
     */
    private List<SelectItem> itemsPais;
    /**
     * Atributo id país de la persona
     */
    private Integer idPais;
    /**
     * Atributo sexo de la persona: masculino y femenino
     */
    private Map<String, String> genero;
    /**
     * Atributo estado provincias
     */
    private String estadoProvincias;
    /**
     * Atributo estado de ciudades
     */
    private String estadoCiudades;
    /**
     * Atributo carrera del estudiante de tipo carrera
     */
    private Carrera carrera;
    /**
     * Atributo temporal de id carrera
     */
    private Integer nuevaIdCarrera;
    /**
     * Atributo request context
     */
    private RequestContext req;
    // Variables para actualización
    /**
     * Numero de cedula del estudiante
     */
    @Cedula(message = "CÉDULA INCORRECTA")
    /**
     * Atributo número de cedula del estudiante
     */
    private String numeroCedula;
    /**
     * Nombre del jasper del reporte
     */
    private String NOMBRE_REPORTE_JASPER = null;
    @ManagedProperty("#{generadorReportes}")
    private GeneradorReportes generadorJasper;
    /**
     * Nombre del reporte
     */
    private String nombreReporte;

    //-------------
    /**
     * Transformara la clase en un java beans que puede ser gestionada por
     * contenedores ejb
     */
    @EJB
    private CarreraServicio serCarrera;
    @EJB
    private EstadoCivilServicio serEstadoCivil;
    @EJB
    private PaisServicio serPais;
    @EJB
    private CiudadServicio serCiudad;
    @EJB
    private ProvinciaServicio serProvincia;
    @EJB
    private PersonaServicio serPersona;
    @EJB
    private ResultadoServicio serResultado;

    /**
     * Constructor por defecto
     */
    public EstudianteBean() {
    }

    /**
     * Inicialización de variables
     */
    @PostConstruct
    public void inicializar() {
        //Instancia de la clase persona
        persona = new Persona();
        //inicialización de lista 
        itemsCarrera = serCarrera.oneMenuCarrera(serCarrera.listarCarrera());
        itemsEstadoCivil = serEstadoCivil.oneMenuEstadoCivil(serEstadoCivil.listarEstadoCivil());
        itemsCiudad = serCiudad.oneMenuCiudad(serCiudad.listarCiudad());
        itemsPais = serPais.oneMenuPais(serPais.listarPais());
        itemsProvincia = serProvincia.oneMenuProvincia(serProvincia.listarProvincia());
        genero = serPersona.generos();
        estadoProvincias = Constantes.INACTIVO;
        estadoCiudades = Constantes.INACTIVO;
        //inicializacion de variables de tipo entero
        idCarrera = 0;
        idEstadoCivil = 0;
        idPais = 0;
        idProvincia = 0;
        idCiudad = 0;
    }

    /**
     * Método para crear un estudiante
     */
    public void crearPersona() {
        try {
            persona.setIdCarrera(serCarrera.buscarCarreraPorId(nuevaIdCarrera));
            persona.setIdEstadocivil(serEstadoCivil.findEstadoCivilId(idEstadoCivil));
            persona.setIdCiudad(serCiudad.buscarCiudadPorId(idCiudad));
            serPersona.insertarPersona(persona);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dlgGra').hide()");// Cierra un dialogo después de ejecutar un evento
            persona = new Persona();
            idEstadoCivil = 0;
            idCiudad = 0;
            estadoProvincias = Constantes.INACTIVO;
            estadoCiudades = Constantes.INACTIVO;
            genero = serPersona.generos();
            idPais = 0;
            idProvincia = 0;
            req = null;
            MensajesFaces.informacion("GUARDADO CORRECTAMENTE", "");
        } catch (Exception e) {
            MensajesFaces.error("NO SE PUDO GUARDAR", "" + e);
        }
    }

    /**
     * Método para cerrar la ventana de la estudiante
     */
    public void cerrar() {
        persona = new Persona();
        idEstadoCivil = 0;
        idCiudad = 0;
        estadoProvincias = Constantes.INACTIVO;
        estadoCiudades = Constantes.INACTIVO;
        genero = serPersona.generos();
        idPais = 0;
        idProvincia = 0;
        req = null;
    }

    /**
     * Método para actualizar la persona
     */
    public void editarPersona() {
        try {
            persona.setNumeroIdentificacion(numeroCedula);
            persona.setIdCarrera(serCarrera.buscarCarreraPorId(idCarrera));
            persona.setIdCiudad(serCiudad.buscarCiudadPorId(idCiudad));
            persona.setIdEstadocivil(serEstadoCivil.findEstadoCivilId(idEstadoCivil));
            serPersona.actualizarPersona(persona);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('graduadoDialog1').hide()");
            persona = new Persona();
            numeroCedula = "";
            idCarrera = 0;
            idEstadoCivil = 0;
            idCiudad = 0;
            estadoProvincias = Constantes.INACTIVO;
            estadoCiudades = Constantes.INACTIVO;
            genero = serPersona.generos();
            idPais = 0;
            idProvincia = 0;
            req = null;
            MensajesFaces.informacion("ACTUALIZADA CORRECTAMENTE", "");
        } catch (Exception e) {
            MensajesFaces.advertencia("NO SE PUDO ACTUALIZAR", "" + e);
        }
    }

    /**
     * Método para recuperar los datos de la persona por el numero de cedula
     *
     * @param numCe tipo string
     */
    public void recuperarDatosCedula(String numCe) {
        persona = serPersona.buscarPersonaPorCedula(numCe);
        if (persona != null) {
            numeroCedula = numCe;
            idCarrera = persona.getIdCarrera().getIdCarrera();
            idCiudad = persona.getIdCiudad().getIdCiudad();
            idEstadoCivil = persona.getIdEstadocivil().getIdEstadocivil();
            idProvincia = persona.getIdCiudad().getIdProvincia().getIdProvincia();
            idPais = persona.getIdCiudad().getIdProvincia().getIdPais().getIdPais();
            MensajesFaces.informacion("PERSONA", "ENCONTRADA");
        } else {
            numeroCedula = Constantes.VACIO;
            persona = new Persona();
            MensajesFaces.informacion("PERSONA NO ENCONTRADA", "");
        }

    }

    /**
     * Metodo para validar la exitencia de la persona
     */
    /**
     * Metodo para validar la exitencia de la persona
     *
     * @param numCe tipo string numero de cedula valida la existencia de la
     * persona:
     * <ul>
     * <li>true: genera el reporte de la encuesta</li>
     * <li>false: presenta un mensaje que no hay una encuesta activa</li>
     * </ul>
     */
    public void validarPersona(String numCe) {
        persona = serPersona.buscarPersonaPorCedula(numCe);
        if (persona != null) {
            this.nombreReporte = "ReporteGeneralEncuesta-" + persona.getApellidos();
            exportarPDF(numCe);
        } else {
            MensajesFaces.informacion("NO", "EXISTE");
        }
    }

    /**
     * Metodo para exportar la encuesta segun el graduado
     */
    private void exportarPDF(String numCe) {
        try {
            @SuppressWarnings("rawtypes")
            Map parametros = new HashMap();
            NOMBRE_REPORTE_JASPER = "ReporteEncuesta.jasper";
            generadorJasper.setNombreJasper(NOMBRE_REPORTE_JASPER);
            generadorJasper.setNombreReporte(nombreReporte);
            parametros.put("cedula", numCe);
            generadorJasper.setParametrosReporte(parametros);
            generadorJasper.generarPDF();//llamara al metodo para generar el pdf
        } catch (Exception e) {
            MensajesFaces.error("NO SE PUEDE GENERAR EL REPORTE", null);
        }

    }

    /**
     * Metodo para obtner las provincias segun el pais
     *
     * @param ent de tipo AjaxBehaviorEvent
     */
    public void getProvincias(AjaxBehaviorEvent ent) {
        if (idPais != 0) {
            this.itemsProvincia = serProvincia.oneMenuProvincia(this.serProvincia
                    .buscarProvinciasPorPais(idPais));
            if (!itemsProvincia.isEmpty()) {
                this.estadoProvincias = Constantes.ACTIVO;
            } else {
                MensajesFaces.informacion("NO SE ENCONTRARON LAS PROVINCIAS PARA EL PAÍS ELEGIDO", null);
            }
        } else {
            this.estadoProvincias = Constantes.INACTIVO;
            this.estadoCiudades = Constantes.INACTIVO;
            idProvincia = 0;
            idCiudad = 0;
        }

    }

    /**
     * Metodo para obtner las ciudades segun la provincia
     *
     * @param ent de tipo AjaxBehaviorEvent
     */
    public void getCiudades(AjaxBehaviorEvent ent) {
        if (idProvincia != 0) {
            this.itemsCiudad = serCiudad.oneMenuCiudad(this.serCiudad
                    .buscarCiudadesPorProvincia(idProvincia));
            if (!itemsCiudad.isEmpty()) {
                this.estadoCiudades = Constantes.ACTIVO;
            } else {
                MensajesFaces.informacion("NO SE ENCONTRARON LAS CIUDADES PARA LA PROVINCIA ELEGIDA", null);
            }
        } else {
            this.estadoCiudades = Constantes.INACTIVO;
            idCiudad = 0;
        }

    }

    /**
     * Metodo para eliminar el registro de una persona y se verifica si existe
     * el registro en al resultado para poder eliminar el registro
     *
     * @param personatemp de tipo Persona
     */
    public void eliminarEstudiante(Persona personatemp) {
        try {
            boolean verificarPersonaEnResultado = serResultado.buscarRegistroPorPersona(personatemp);
            if (verificarPersonaEnResultado) {
                MensajesFaces.informacion("NO SE PUEDE ELIMINAR", "");
            } else {
                serPersona.eliminarPersona(personatemp);
                MensajesFaces.informacion("ELIMINADO CORRECTAMENTE", "");
            }
        } catch (Exception e) {
            MensajesFaces.advertencia("ERROR AL ELIMINAR", "" + e);
        }
    }

    /**
     * Metodo para obtner las de carreras existentes
     *
     * @param ent de tipo AjaxBehaviorEvent
     */
    public void getLista(AjaxBehaviorEvent ent) {
        carrera = serCarrera.buscarCarreraPorId(idCarrera);
        if (carrera != null) {
            nuevaIdCarrera = idCarrera;
            MensajesFaces.informacion("GRADUADO POR CARRERA", "");
        } else {
            idCarrera = 0;
            nuevaIdCarrera = 0;
        }
    }

    /**
     * Metodo para obtner el listado de graduados por carrera
     *
     *
     * @return persona el objeto de la persona
     */
    public List<Persona> recuperarGraduadoCarrera() {
        return serPersona.listaPersonaPorCarrera(nuevaIdCarrera);
    }

    /**
     * Devuelve items Carrera
     *
     * @return itemsCarrera
     */
    public List<SelectItem> getItemsCarrera() {
        return itemsCarrera;
    }

    /**
     * Modifica items Carrera
     *
     * @param itemsCarrera tipo lista
     */
    public void setItemsCarrera(List<SelectItem> itemsCarrera) {
        this.itemsCarrera = itemsCarrera;
    }

    /**
     * Devuelve id Carrera
     *
     * @return idCarrera
     */
    public Integer getIdCarrera() {
        return idCarrera;
    }

    /**
     * Modifica idCarrera
     *
     * @param idCarrera tipo Integer
     */
    public void setIdCarrera(Integer idCarrera) {
        this.idCarrera = idCarrera;
    }

    /**
     * Devuelve persona
     *
     * @return persona
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * Modifica persona
     *
     * @param persona tipo persona
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    /**
     * Devuelve items Estado Civil
     *
     * @return itemsEstadoCivil
     */
    public List<SelectItem> getItemsEstadoCivil() {
        return itemsEstadoCivil;
    }

    /**
     * Modifica items Estado Civil
     *
     * @param itemsEstadoCivil tipo lista
     */
    public void setItemsEstadoCivil(List<SelectItem> itemsEstadoCivil) {
        this.itemsEstadoCivil = itemsEstadoCivil;
    }

    /**
     * Devuelve id Estado Civil
     *
     * @return idEstadoCivil
     */
    public Integer getIdEstadoCivil() {
        return idEstadoCivil;
    }

    /**
     * Modifica id Estado Civil
     *
     * @param idEstadoCivil tipo Integer
     */
    public void setIdEstadoCivil(Integer idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
    }

    /**
     * Devuelve items Ciudad
     *
     * @return itemsCiudad
     */
    public List<SelectItem> getItemsCiudad() {
        return itemsCiudad;
    }

    /**
     * Modifica items Ciudad
     *
     * @param itemsCiudad tipo lista
     */
    public void setItemsCiudad(List<SelectItem> itemsCiudad) {
        this.itemsCiudad = itemsCiudad;
    }

    /**
     * Devuelve id Carrera
     *
     * @return idCarrera
     */
    public List<SelectItem> getItemsProvincia() {
        return itemsProvincia;
    }

    /**
     * Modifica itemsProvincia
     *
     * @param itemsProvincia tipo lista
     */
    public void setItemsProvincia(List<SelectItem> itemsProvincia) {
        this.itemsProvincia = itemsProvincia;
    }

    /**
     * Devuelve items Pais
     *
     * @return itemsPais
     */
    public List<SelectItem> getItemsPais() {
        return itemsPais;
    }

    /**
     * Modifica items Pais
     *
     * @param itemsPais tipo lista
     */
    public void setItemsPais(List<SelectItem> itemsPais) {
        this.itemsPais = itemsPais;
    }

    /**
     * Devuelve genero
     *
     * @return genero
     */
    public Map<String, String> getGenero() {
        return genero;
    }

    /**
     * Modifica genero
     *
     * @param genero tipo Map
     */
    public void setGenero(Map<String, String> genero) {
        this.genero = genero;
    }

    /**
     * Devuelve id Ciudad
     *
     * @return idCiudad
     */
    public Integer getIdCiudad() {
        return idCiudad;
    }

    /**
     * Modifica id Ciudad
     *
     * @param idCiudad tipo Integer
     */
    public void setIdCiudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }

    /**
     * Devuelve id Provincia
     *
     * @return idProvincia
     */
    public Integer getIdProvincia() {
        return idProvincia;
    }

    /**
     * Modifica idProvincia
     *
     * @param idProvincia tipo Integer
     */
    public void setIdProvincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }

    /**
     * Devuelve idPais
     *
     * @return idPais
     */
    public Integer getIdPais() {
        return idPais;
    }

    /**
     * Modifica idPais
     *
     * @param idPais tipo Integer
     */
    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    /**
     * Devuelve id Carrera
     *
     * @return idCarrera
     */
    public String getEstadoProvincias() {
        return estadoProvincias;
    }

    /**
     * Modifica estadoProvincias
     *
     * @param estadoProvincias tipo String
     */
    public void setEstadoProvincias(String estadoProvincias) {
        this.estadoProvincias = estadoProvincias;
    }

    /**
     * Devuelve estadoCiudades
     *
     * @return estadoCiudades
     */
    public String getEstadoCiudades() {
        return estadoCiudades;
    }

    /**
     * Modifica estadoCiudades
     *
     * @param estadoCiudades tipo String
     */
    public void setEstadoCiudades(String estadoCiudades) {
        this.estadoCiudades = estadoCiudades;
    }

    /**
     * Devuelve numeroCedula
     *
     * @return numeroCedula
     */
    public String getNumeroCedula() {
        return numeroCedula;
    }

    /**
     * Modifica numeroCedula
     *
     * @param numeroCedula tipo String
     */
    public void setNumeroCedula(String numeroCedula) {
        this.numeroCedula = numeroCedula;
    }

    /**
     * Devuelve Carrera
     *
     * @return carrera
     */
    public Carrera getCarrera() {
        return carrera;
    }

    /**
     * Modifica carrera Reporte
     *
     * @param carrera tipo carrera
     */
    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    /**
     * Devuelve nuevaIdCarrera
     *
     * @return nuevaIdCarrera
     */
    public Integer getNuevaIdCarrera() {
        return nuevaIdCarrera;
    }

    /**
     * Modifica nuevaIdCarrera
     *
     * @param nuevaIdCarrera tipo Integer
     */
    public void setNuevaIdCarrera(Integer nuevaIdCarrera) {
        this.nuevaIdCarrera = nuevaIdCarrera;
    }

    /**
     * Devuelve NOMBRE_REPORTE_JASPER
     *
     * @return NOMBRE_REPORTE_JASPER
     */
    public String getNOMBRE_REPORTE_JASPER() {
        return NOMBRE_REPORTE_JASPER;
    }

    /**
     * Modifica NOMBRE_REPORTE_JASPER
     *
     * @param NOMBRE_REPORTE_JASPER tipo String
     */
    public void setNOMBRE_REPORTE_JASPER(String NOMBRE_REPORTE_JASPER) {
        this.NOMBRE_REPORTE_JASPER = NOMBRE_REPORTE_JASPER;
    }

    /**
     * Devuelve generadorJasper
     *
     * @return generadorJasper
     */
    public GeneradorReportes getGeneradorJasper() {
        return generadorJasper;
    }

    /**
     * Modifica generadorJasper
     *
     * @param generadorJasper tipo GeneradorReportes
     */
    public void setGeneradorJasper(GeneradorReportes generadorJasper) {
        this.generadorJasper = generadorJasper;
    }

    /**
     * Devuelve nombreReporte
     *
     * @return nombreReporte
     */
    public String getNombreReporte() {
        return nombreReporte;
    }

    /**
     * Modifica nombre Reporte
     *
     * @param nombreReporte tipo String
     */
    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }

}
