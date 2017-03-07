/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.controlador;

import com.cursojsf.validadores.Cedula;
import ec.edu.uce.medicina.seguimiento.modelo.Persona;
import ec.edu.uce.medicina.seguimiento.servicio.CarreraServicio;
import ec.edu.uce.medicina.seguimiento.servicio.CiudadServicio;
import ec.edu.uce.medicina.seguimiento.servicio.EstadoCivilServicio;
import ec.edu.uce.medicina.seguimiento.servicio.PaisServicio;
import ec.edu.uce.medicina.seguimiento.servicio.PersonaServicio;
import ec.edu.uce.medicina.seguimiento.servicio.ProvinciaServicio;
import ec.edu.uce.medicina.seguimiento.util.AplicacionUtil;
import ec.edu.uce.medicina.seguimiento.util.Constantes;
import ec.edu.uce.medicina.seguimiento.util.MensajesFaces;
import java.io.Serializable;
import java.util.Date;
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
 * Esta clase PersonaBean se encarga de enlazar los atributos con las páginas
 * XHTML registro de la persona, controlador de los graduados
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@ManagedBean
@ViewScoped
public class PersonaBean implements Serializable {

    /**
     * Serial version de la clase.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Atributo persona de la nuevaPersona
     */
    private Persona nuevaPersona;
    /**
     * Atributo persona de la nuevaPersonaAux
     */
    private Persona nuevaPersonaAux;
    /**
     * Atributo lista de estado civil
     */
    private List<SelectItem> itemsEstadoCivil;
    /**
     * Atributo id estado civil de la persona
     */
    private int idEstadoCivil;
    /**
     * Atributo sexo de la persona
     */
    private Map<String, String> genero;
    /**
     * Atributo discapacidad de la persona
     */
    private Map<String, String> discapacidad;
    /**
     * Atributo numero de cedula del estudiante
     */
    @Cedula(message = "CÉDULA INCORRECTA")
    private String cedulaPersona;
    /**
     * Atributo bandera de la persona encontrada
     */
    private int banderaPersonaEncontrada;
    /**
     * Atributo lista de carreras existentes
     */
    private List<SelectItem> itemsCarrera;
    /**
     * Atributo id carrerra en el que esta el estudiante
     */
    private int idCarrera;
    /**
     * Atributo estado de las provincias
     */
    private String estadoProvincias;
    /**
     * Atributo estado de las ciudades
     */
    private String estadoCiudades;
    /**
     * Atributo lista de país
     */
    private List<SelectItem> itemsPais;
    /**
     * Atributo id pais de la persona
     */
    private int idPais;
    /**
     * Atributo lista de provincias
     */
    private List<SelectItem> itemsProvincia;
    /**
     * Atributo id Provicnia en el que vive el estudiante
     */
    private int idProvincia;
    /**
     * Atributo lista de ciudades
     */
    private List<SelectItem> itemsCiudad;
    /**
     * Atributo id de la ciudad
     */
    private int idCiudad;
    /**
     * Atributo estado del boton del guardar
     */
    private String estadoBtnGuardar;
    /**
     * Atributo estado del boton del formulario
     */
    private String estadoBtnFormulario;
    /**
     * Atributo estado del boton de la persona encontrada
     */
    private String estadoItemPersonaEncontrada;
    /**
     * Atributo estado del estado de texto habilitado
     */
    private String estadoTextoEstHabilitado;
    /**
     * Atributo anio en el que nacio el graduado
     */
    private int anio;
    /**
     * Transformara la clase en un java beans que puede ser gestionada por
     * contenedores ejb
     */
    @EJB
    private PersonaServicio serPersona;
    @EJB
    private EstadoCivilServicio serEstadoCivil;
    @EJB
    private CarreraServicio serCarrera;
    @EJB
    private PaisServicio serPais;
    @EJB
    private ProvinciaServicio serProvincia;
    @EJB
    private CiudadServicio serCiudad;
    /**
     * Inyeccion de un bean es decir del validar graduado
     */
    @ManagedProperty(value = "#{validarGraduadoBean}")
    private ValidarGraduadoBean validarGraduado;

    /**
     * Constructor por defecto
     */
    public PersonaBean() {

    }

    /**
     * Inicialización de variables
     */
    @PostConstruct
    public void inicializar() {
        idPais = 0;
        idProvincia = 0;
        idCiudad = 0;
        estadoProvincias = Constantes.INACTIVO;
        estadoCiudades = Constantes.INACTIVO;
        nuevaPersona = new Persona();
        nuevaPersona = validarGraduado.getPersonaEncontrada();
        inicializarValorPersonaEncontrada();
        itemsEstadoCivil = serEstadoCivil.oneMenuEstadoCivil(serEstadoCivil.listarEstadoCivil());
        itemsCarrera = serCarrera.oneMenuCarrera(serCarrera.listarCarrera());
        genero = serPersona.generos();
        discapacidad = serPersona.discapacidades();
        itemsCiudad = serCiudad.oneMenuCiudad(serCiudad.listarCiudad());
        itemsPais = serPais.oneMenuPais(serPais.listarPais());
        itemsProvincia = serProvincia.oneMenuProvincia(serProvincia.listarProvincia());
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
                MensajesFaces.informacion("NO SE ENCONTRARON CIUDADES PARA LA PROVINCIA ELEGIDA", null);
            }
        } else {
            this.estadoCiudades = Constantes.INACTIVO;
            idCiudad = 0;
        }
    }

    /**
     * Método para crear un estudiante
     */
    public void ingresoNuevaPersona() {
        try {
            nuevaPersona.setNumeroIdentificacion(cedulaPersona);
            if (banderaPersonaEncontrada == 1) {
                nuevaPersona.setIdEstadocivil(serEstadoCivil.findEstadoCivilId(idEstadoCivil));
                nuevaPersona.setIdCarrera(serCarrera.buscarCarreraPorId(idCarrera));
                nuevaPersona.setIdCiudad(serCiudad.buscarCiudadPorId(idCiudad));
                serPersona.actualizarPersona(nuevaPersona);
                MensajesFaces.informacion("DATOS ACTUALIZADOS CORRECTAMENTE", "");
            } else {
                nuevaPersona.setIdEstadocivil(serEstadoCivil.findEstadoCivilId(idEstadoCivil));
                nuevaPersona.setIdCarrera(serCarrera.buscarCarreraPorId(idCarrera));
                nuevaPersona.setIdCiudad(serCiudad.buscarCiudadPorId(idCiudad));
                anio = AplicacionUtil.edad(nuevaPersona.getFechaNacimiento());
                nuevaPersona.setEdad(anio);
                serPersona.insertarPersona(nuevaPersona);
                estadoBtnFormulario = Constantes.ACTIVO;
                estadoBtnGuardar = Constantes.INACTIVO;
                estadoTextoEstHabilitado = Constantes.VISIBLE;
                estadoItemPersonaEncontrada = Constantes.INACTIVO;
                estadoProvincias = Constantes.INACTIVO;
                estadoCiudades = Constantes.INACTIVO;
                nuevaPersonaAux = nuevaPersona;
                MensajesFaces.informacion("PERSONA GUARDADA CORRECTAMENTE", "ESTUDIANTE HABILITADO PARA LLENAR LA ENCUESTA");
            }
        } catch (Exception e) {
            MensajesFaces.error("NO SE PUDO GUARDAR", "" + e);
        }
    }

    /**
     * Método para calcular la edad del graduado
     *
     * @param fechaNac
     * @return anio
     */
    public int edad(Date fechaNac) {
        anio = AplicacionUtil.edad(fechaNac);
        return anio;
    }

    /**
     * Método para encontrar la persona
     */
    private void inicializarValorPersonaEncontrada() {
        if (nuevaPersona.getIdPersona() != null) {
            cedulaPersona = nuevaPersona.getNumeroIdentificacion();
            banderaPersonaEncontrada = 1;
            idCiudad = nuevaPersona.getIdCiudad().getIdCiudad();
            idCarrera = nuevaPersona.getIdCarrera().getIdCarrera();
            idEstadoCivil = nuevaPersona.getIdEstadocivil().getIdEstadocivil();
            idProvincia = nuevaPersona.getIdCiudad().getIdProvincia().getIdProvincia();
            idPais = nuevaPersona.getIdCiudad().getIdProvincia().getIdPais().getIdPais();
            estadoBtnGuardar = Constantes.INACTIVO;
            estadoItemPersonaEncontrada = Constantes.INACTIVO;
            estadoBtnFormulario = Constantes.ACTIVO;
            estadoTextoEstHabilitado = Constantes.VISIBLE;
            nuevaPersonaAux = nuevaPersona;
        } else {
            cedulaPersona = validarGraduado.getNumeroCedula();
            banderaPersonaEncontrada = 2;
            idCiudad = 0;
            idCarrera = 0;
            idEstadoCivil = 0;
            idPais = 0;
            idProvincia = 0;
            estadoBtnGuardar = Constantes.ACTIVO;
            estadoBtnFormulario = Constantes.INACTIVO;
            estadoItemPersonaEncontrada = Constantes.ACTIVO;
            estadoTextoEstHabilitado = Constantes.NOVISIBLE;
        }

    }

    /**
     * Devuelve el genero
     *
     * @return genero
     */
    public Map<String, String> getGenero() {
        return genero;
    }

    /**
     * Modifica el genero
     *
     * @param genero
     */
    public void setGenero(Map<String, String> genero) {
        this.genero = genero;
    }

    /**
     * Devuelve la variable temporal de la persona
     *
     * @return nuevaPersona
     */
    public Persona getNuevaPersona() {
        return nuevaPersona;
    }

    /**
     * Modifica la variable temporal de la persona
     *
     * @param nuevaPersona
     */
    public void setNuevaPersona(Persona nuevaPersona) {
        this.nuevaPersona = nuevaPersona;
    }

    /**
     * Devuelve el items de estado civil
     *
     * @return itemsEstadoCivil
     */
    public List<SelectItem> getItemsEstadoCivil() {
        return itemsEstadoCivil;
    }

    /**
     * Modifica el items de estado civil
     *
     * @param itemsEstadoCivil
     */
    public void setItemsEstadoCivil(List<SelectItem> itemsEstadoCivil) {
        this.itemsEstadoCivil = itemsEstadoCivil;
    }

    /**
     * Devuelve el id estado civil
     *
     * @return idEstadoCivil
     */
    public int getIdEstadoCivil() {
        return idEstadoCivil;
    }

    /**
     * Modifica el id estado civil
     *
     * @param idEstadoCivil
     */
    public void setIdEstadoCivil(int idEstadoCivil) {
        this.idEstadoCivil = idEstadoCivil;
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
     * Devuelve el id de la carrera
     *
     * @return idCarrera
     */
    public int getIdCarrera() {
        return idCarrera;
    }

    /**
     * Modifica el id de la carrera
     *
     * @param idCarrera
     */
    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    /**
     * Devuelve el items del pais
     *
     * @return itemsPais
     */
    public List<SelectItem> getItemsPais() {
        return itemsPais;
    }

    /**
     * Modifica el items del pais
     *
     * @param itemsPais
     */
    public void setItemsPais(List<SelectItem> itemsPais) {
        this.itemsPais = itemsPais;
    }

    /**
     * Devuelve el id del pais
     *
     * @return idPais
     */
    public int getIdPais() {
        return idPais;
    }

    /**
     * Modifica el id del pais
     *
     * @param idPais
     */
    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    /**
     * Devuelve la items de la ciudad
     *
     * @return itemsCiudad
     */
    public List<SelectItem> getItemsCiudad() {
        return itemsCiudad;
    }

    /**
     * Modifica la items de la ciudad
     *
     * @param itemsCiudad
     */
    public void setItemsCiudad(List<SelectItem> itemsCiudad) {
        this.itemsCiudad = itemsCiudad;
    }

    /**
     * Devuelve el id de la ciudad
     *
     * @return idCiudad
     */
    public int getIdCiudad() {
        return idCiudad;
    }

    /**
     * Modifica el id de la ciudad
     *
     * @param idCiudad
     */
    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    /**
     * Devuelve la discapacidad de la persona
     *
     * @return discapacidad
     */
    public Map<String, String> getDiscapacidad() {
        return discapacidad;
    }

    /**
     * Modifica la discapacidad de la persona
     *
     * @param discapacidad
     */
    public void setDiscapacidad(Map<String, String> discapacidad) {
        this.discapacidad = discapacidad;
    }

    /**
     * Devuelve validar graduado
     *
     * @return validarGraduado
     */
    public ValidarGraduadoBean getValidarGraduado() {
        return validarGraduado;
    }

    /**
     * Modifica validar graduado
     *
     * @param validarGraduado
     */
    public void setValidarGraduado(ValidarGraduadoBean validarGraduado) {
        this.validarGraduado = validarGraduado;
    }

    /**
     * Devuelve la cedula de la persona
     *
     * @return cedulaPersona
     */
    public String getCedulaPersona() {
        return cedulaPersona;
    }

    /**
     * Modifica la cedula de la persona
     *
     * @param cedulaPersona
     */
    public void setCedulaPersona(String cedulaPersona) {
        this.cedulaPersona = cedulaPersona;
    }

    /**
     * Devuelve el id de la provincia
     *
     * @return idProvincia
     */
    public int getIdProvincia() {
        return idProvincia;
    }

    /**
     * Modifica el id de la provincia
     *
     * @param idProvincia
     */
    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }

    /**
     * Devuelve los items de las provincias
     *
     * @return itemsProvincia
     */
    public List<SelectItem> getItemsProvincia() {
        return itemsProvincia;
    }

    /**
     * Modifica los items de las provincias
     *
     * @param itemsProvincia
     */
    public void setItemsProvincia(List<SelectItem> itemsProvincia) {
        this.itemsProvincia = itemsProvincia;
    }

    /**
     * Devuelve el estado de las provincias
     *
     * @return estadoProvincias
     */
    public String getEstadoProvincias() {
        return estadoProvincias;
    }

    /**
     * Modifica el estado de las provincias
     *
     * @param estadoProvincias
     */
    public void setEstadoProvincias(String estadoProvincias) {
        this.estadoProvincias = estadoProvincias;
    }

    /**
     * Devuelve el estado de las ciudades
     *
     * @return estadoCiudades
     */
    public String getEstadoCiudades() {
        return estadoCiudades;
    }

    /**
     * Modifica el estado de las ciudades
     *
     * @param estadoCiudades
     */
    public void setEstadoCiudades(String estadoCiudades) {
        this.estadoCiudades = estadoCiudades;
    }

    /**
     * Devuelve el estado del boton de guardar
     *
     * @return estadoBtnGuardar
     */
    public String getEstadoBtnGuardar() {
        return estadoBtnGuardar;
    }

    /**
     * Modifica el estado del boton de guardar
     *
     * @param estadoBtnGuardar
     */
    public void setEstadoBtnGuardar(String estadoBtnGuardar) {
        this.estadoBtnGuardar = estadoBtnGuardar;
    }

    /**
     * Devuelve el estado boton del formulario
     *
     * @return estadoBtnFormulario
     */
    public String getEstadoBtnFormulario() {
        return estadoBtnFormulario;
    }

    /**
     * Modifica el estado boton del formulario
     *
     * @param estadoBtnFormulario
     */
    public void setEstadoBtnFormulario(String estadoBtnFormulario) {
        this.estadoBtnFormulario = estadoBtnFormulario;
    }

    /**
     * Devuelve la estadoItemPersonaEncontrada
     *
     * @return estadoItemPersonaEncontrada
     */
    public String getEstadoItemPersonaEncontrada() {
        return estadoItemPersonaEncontrada;
    }

    /**
     * Modifica la estadoItemPersonaEncontrada
     *
     * @param estadoItemPersonaEncontrada
     */
    public void setEstadoItemPersonaEncontrada(String estadoItemPersonaEncontrada) {
        this.estadoItemPersonaEncontrada = estadoItemPersonaEncontrada;
    }

    /**
     * Devuelve el estado del texto de la encuesta
     *
     * @return estadoTextoEstHabilitado
     */
    public String getEstadoTextoEstHabilitado() {
        return estadoTextoEstHabilitado;
    }

    /**
     * Modifica el estado del texto de la encuesta
     *
     * @param estadoTextoEstHabilitado
     */
    public void setEstadoTextoEstHabilitado(String estadoTextoEstHabilitado) {
        this.estadoTextoEstHabilitado = estadoTextoEstHabilitado;
    }

    /**
     * Devuelve la variable auxiliar de la persona
     *
     * @return nuevaPersonaAux
     */
    public Persona getNuevaPersonaAux() {
        return nuevaPersonaAux;
    }

    /**
     * Modifica la variable auxiliar de la persona
     *
     * @param nuevaPersonaAux
     */
    public void setNuevaPersonaAux(Persona nuevaPersonaAux) {
        this.nuevaPersonaAux = nuevaPersonaAux;
    }

    /**
     * Devuelve el anio del graduado
     *
     * @return anio
     */
    public int getAnio() {
        return anio;
    }

    /**
     * Modifica el anio del graduado
     *
     * @param anio
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

}
