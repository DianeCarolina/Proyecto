/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.controlador;

import static com.sun.faces.el.ELUtils.createValueExpression;
import ec.edu.uce.medicina.seguimiento.dto.ResultadoAuxDTO;
import ec.edu.uce.medicina.seguimiento.modelo.Categoria;
import ec.edu.uce.medicina.seguimiento.modelo.Encuesta;
import ec.edu.uce.medicina.seguimiento.modelo.Persona;
import ec.edu.uce.medicina.seguimiento.modelo.Pregunta;
import ec.edu.uce.medicina.seguimiento.modelo.Respuesta;
import ec.edu.uce.medicina.seguimiento.modelo.Resultado;
import ec.edu.uce.medicina.seguimiento.modelo.TipoPregunta;
import ec.edu.uce.medicina.seguimiento.servicio.CategoriaServicio;
import ec.edu.uce.medicina.seguimiento.servicio.EncuestaServicio;
import ec.edu.uce.medicina.seguimiento.servicio.PersonaServicio;
import ec.edu.uce.medicina.seguimiento.servicio.PreguntaServicio;
import ec.edu.uce.medicina.seguimiento.servicio.RespuestaServicio;
import ec.edu.uce.medicina.seguimiento.servicio.ResultadoServicio;
import ec.edu.uce.medicina.seguimiento.servicio.TipoPreguntaServicio;
import ec.edu.uce.medicina.seguimiento.util.AplicacionUtil;
import ec.edu.uce.medicina.seguimiento.util.Constantes;
import ec.edu.uce.medicina.seguimiento.util.MensajesFaces;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UISelectItem;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.component.html.HtmlSelectManyCheckbox;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.context.FacesContext;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.panelgrid.PanelGrid;

/**
 * Esta clase EncuestaBean se encarga de enlazar los atributos con las páginas
 * XHTML en la visualización de la encuesta, para el graduado
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@ViewScoped
@ManagedBean(name = "beanEncuesta")
public class EncuestaBean implements Serializable {

    /**
     * Serial version de la clase.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Atributo lista de categorias de la encuesta
     */
    private List<Categoria> listaCategorias;
    /**
     * Atributo Lista de preguntas de la encuesta
     */
    private List<Pregunta> listaPreguntas;
    /**
     * Atributo Lista de Tipo de Preguntas de la encuesta
     */
    private List<TipoPregunta> listaTiposPregunta;
    /**
     * Atributo Lista de respuestas de la encuesta
     */
    private List<Respuesta> listaRespuestas;
    /**
     * Atributo Lista de resultados de la encuesta
     */
    private List<Resultado> resultados;
    /**
     * Lista de resultadosMultiples
     */
    private List<ResultadoAuxDTO> resultadosMultiples;
    /**
     * Atributo resultado de la encuesta
     */
    private Resultado resultado;
    /**
     * Atributo resultado auxiliar de la encuesta
     */
    private ResultadoAuxDTO resultadoAux;
    /**
     * Atributo número de resultado
     */
    private Integer numero;
    /**
     * Atributo número2 de resultado
     */
    private Integer numero2;
    /**
     * Atributo lista de resultado por pregunta
     */
    private List<Resultado> resultadoBusquedaPregunta;
    /**
     * Atributo encuesta activa de tipo encuesta
     */
    private Encuesta encuestaActiva;
    /**
     * Atributo de persona encontrada de tipo persona
     */
    private Persona personaEncontrada;
    /**
     * variables para visualizar encuesta
     */
    private HtmlSelectBooleanCheckbox selectBooleanCheckbox;
    private InputTextarea inputTextarea;
    private UISelectItem uISelectItem;
    private HtmlSelectManyCheckbox selectManyCheckbox;
    private HtmlSelectOneRadio selectOneRadio;
    private HtmlSelectOneMenu selectOneMenu;
    private PanelGrid panelGrid;
    private PanelGrid panelGridPreg;
    private OutputLabel outputLabel;
    private InputText imInputText;
    private Calendar calendar;
    private PanelGrid panelGridHijo;
    //------------------------
    /**
     * Transformara la clase en un java beans que puede ser gestionada por
     * contenedores ejb
     */
    @EJB
    private CategoriaServicio serCategoria;
    @EJB
    private PreguntaServicio serPregunta;
    @EJB
    private TipoPreguntaServicio serTipoPregunta;
    @EJB
    private RespuestaServicio serRespuesta;
    @EJB
    private EncuestaServicio serEncuesta;
    @EJB
    private PersonaServicio serPersona;
    @EJB
    private ResultadoServicio serResultado;

    /**
     * Constructor por defecto
     */
    public EncuestaBean() {
    }

    /**
     * Inicialización de variables
     */
    public void reiniciarListas() {
        //Crear objetos de tipo Array para la encuesta
        listaCategorias = new ArrayList<>();
        listaPreguntas = new ArrayList<>();
        listaTiposPregunta = new ArrayList<>();
        listaRespuestas = new ArrayList<>();
    }

    /**
     * Inicialización de variables
     * <ul>
     * <li>true: presenta la encuesta</li>
     * <li>false: presenta un mensaje indicando que no hay una encuesta
     * activa</li>
     * </ul>
     */
    @PostConstruct
    public void inicializar() {
        String parametroRecuperado = recuperarParametroVistaAnt();
        personaEncontrada = serPersona.buscarPersonaPorCedula(parametroRecuperado);
        resultadoBusquedaPregunta = null;
        reiniciarListas();
        //Visualiza la encuesta que se encuentra activa por carrera
        encuestaActiva = serEncuesta.buscarEncuestaPorCarrera(personaEncontrada.getIdCarrera().getIdCarrera());
        // condición para que se inicialice la encuesta activa
        if (encuestaActiva != null) {
            inicializarDatosEncuesta();
        } else {
            MensajesFaces.error("NO EXISTE ENCUESTAS ACTIVAS PARA ESTA CARRERA", "ERROR AL GENERAR LA ENCUESTA");
        }
        mostrar();// Invoca al método mostrar encuesta para la generación de la misma
    }

    /**
     * Método para recuperar la vista anterior
     */
    private String recuperarParametroVistaAnt() {
        Map params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String cedulaPersona = (String) params.get("idEstudiantEncuesta");
        return cedulaPersona;
    }

    /**
     * Método para inicializar los datos de la encuesta
     */
    private void inicializarDatosEncuesta() {
        listaCategorias = serCategoria.listaCategoriaPorEncuesta(encuestaActiva.getIdEncuesta());
    }

    /**
     * Método para mostrar la encuesta
     */
    public void mostrar() {
        if (panelGrid == null) {
            panelGrid = new PanelGrid();
            panelGrid.setColumns(1);
            panelGrid.setId("panelGridEncuesta");
            if (encuestaActiva != null) {
                numero = 0;
                numero2 = 0;
                resultados = new ArrayList<>();
                resultadosMultiples = new ArrayList<>();
                outputLabel = new OutputLabel();
                outputLabel.setStyle("font-size:14pt;font-weight: bold");
                outputLabel.setId("labelTitulo"); //debe ser unico
                //Crea un label para mostrar el titulo de la encuesta
                outputLabel.setValue(encuestaActiva.getTituloEncuesta());
                // Agrega al panelgrid principal el label del titulo
                panelGrid.getChildren().add(outputLabel);
                //Recorre las categorias de la encuesta con un foreach
                for (Categoria itemCategoria : listaCategorias) {
                    //Crea un label para mostrar la categoria
                    outputLabel = new OutputLabel();
                    outputLabel.setStyle("font-size:10pt;font-weight: bold");
                    outputLabel.setId("lblCategoria" + itemCategoria.getIdCategoria());
                    outputLabel.setValue(itemCategoria.getOrden() + "." + " " + itemCategoria.getNomCategoria());
                    //Agrega el label al panelgrid hijo
                    panelGrid.getChildren().add(outputLabel);
                    //Crea un panelgrid que va a contener las preguntas
                    panelGridHijo = new PanelGrid();
                    panelGridHijo.setStyle("width:100%");
                    panelGridHijo.setId("panelGridcategoria" + itemCategoria.getIdCategoria());//debe ser unico
                    panelGridHijo.setColumns(2);
                    listaPreguntas = serPregunta.listaPreguntaPorCategoriaCliente(itemCategoria.getIdCategoria());
                    //Recorre la lista de preguntas para cada categoria con un foreach
                    for (Pregunta itemPregunta : listaPreguntas) {
                        //Crea el label para mostrar cada pregunta
                        outputLabel = new OutputLabel();
                        outputLabel.setStyle("font-size:8pt;");
                        outputLabel.setId("lblPregunta" + itemPregunta.getIdPregunta());
                        outputLabel.setValue(itemPregunta.getOrden() + " " + itemPregunta.getTexto());
                        //Agrega al panelgridHijo correspondiente a la categoria
                        panelGridHijo.getChildren().add(outputLabel);
                        TipoPregunta tipoPregEncontrada = serTipoPregunta.buscarTipoPreguntaPorId(itemPregunta.getIdTipopregunta().getIdTipopregunta());
                        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++  
                        //Busca el tipo de pregunta para recorrer la respuesta y segun eso agregar el componente
                        if (tipoPregEncontrada.getControlTipo().equals("InputText")) {
                            //Se repite para los demás tipos de control con algunas excepciones
                            imInputText = new InputText();
                            imInputText.setId("inputTextPregunta" + itemPregunta.getIdPregunta());
                            imInputText.setStyle("width:100%");
                            if (itemPregunta.getRequerida()) {
                                imInputText.setRequired(true);
                                imInputText.setRequiredMessage(itemPregunta.getTexto() + " es un campo requerido");
                            }
                            //Variable resultadoBusquedaPregunta realiza la busqueda según la pregunta y la persona que realizo la encuesta
                            resultadoBusquedaPregunta = (List<Resultado>) serResultado.listaResultadoPorPregunta(itemPregunta.getIdPregunta(), personaEncontrada.getIdPersona());
                            if (!resultadoBusquedaPregunta.isEmpty()) {
                                imInputText.setValue(resultadoBusquedaPregunta.get(0).getResultado());
                                resultado = new Resultado(resultadoBusquedaPregunta.get(0).getIdResultado(), personaEncontrada, itemPregunta);
                            } else {
                                resultado = new Resultado(null, personaEncontrada, itemPregunta);
                            }

                            resultado.setResultado(Constantes.VACIO);
                            //Se añade a la lista de resultado
                            resultados.add(resultado);
                            imInputText.setValueExpression("value", createValueExpression("#{beanEncuesta.resultados.get(" + numero + ").resultado}", String.class));
                            //Agrega al panelgridHijo correspondiente a la pregunta
                            panelGridHijo.getChildren().add(imInputText);

                            numero = numero + 1;

                            if (itemPregunta.getTexto().equals("Apellidos") || itemPregunta.getTexto().equals("APELLIDOS")) {
                                imInputText.setValue(personaEncontrada.getApellidos());
                            } else if (itemPregunta.getTexto().equals("Nombres") || itemPregunta.getTexto().equals("Nombres completos")) {
                                imInputText.setValue(personaEncontrada.getNombres());
                            } else if (itemPregunta.getTexto().equals("Cédula/pasaporte")) {
                                imInputText.setValue(personaEncontrada.getNumeroIdentificacion());
                                imInputText.setDisabled(true);
                            } else if (itemPregunta.getTexto().equals("Edad") || itemPregunta.getTexto().equals("EDAD")) {
                                imInputText.setValue(personaEncontrada.getEdad());
                            }
                        }
                        if (tipoPregEncontrada.getControlTipo().equals("InputTextArea")) {
                            //Se agrega el inputTextArea tipos de control con algunas excepciones
                            inputTextarea = new InputTextarea();
                            inputTextarea.setId("inputTextAreaPregunta" + itemPregunta.getIdPregunta());
                            inputTextarea.setStyle("width:100%");
                            if (itemPregunta.getRequerida()) {
                                inputTextarea.setRequired(true);
                                inputTextarea.setRequiredMessage(itemPregunta.getTexto() + " es un campo requerido");
                            }

                            resultadoBusquedaPregunta = (List<Resultado>) serResultado.listaResultadoPorPregunta(itemPregunta.getIdPregunta(), personaEncontrada.getIdPersona());
                            //Variable resultadoBusquedaPregunta realiza la busqueda según la pregunta y la persona que realizo la encuesta
                            if (!resultadoBusquedaPregunta.isEmpty()) {
                                inputTextarea.setValue(resultadoBusquedaPregunta.get(0).getResultado());
                                resultado = new Resultado(resultadoBusquedaPregunta.get(0).getIdResultado(), personaEncontrada, itemPregunta);
                            } else {
                                resultado = new Resultado(null, personaEncontrada, itemPregunta);
                            }
                            resultado.setResultado(Constantes.VACIO);
                            //Se añade a la lista de resultado según el tipo de control InputTextArea
                            resultados.add(resultado);
                            inputTextarea.setValueExpression("value", createValueExpression("#{beanEncuesta.resultados.get(" + numero + ").resultado}", String.class));
                            panelGridHijo.getChildren().add(inputTextarea);
                            numero = numero + 1;

                        }
                        if (tipoPregEncontrada.getControlTipo().equals("Calendar")) {
                            //Se repite para crear el calendar
                            calendar = new Calendar();
                            calendar.setId("calendarPregunta" + itemPregunta.getIdPregunta());
                            calendar.setShowOn("button");
                            calendar.setPattern("dd-MM-yyyy");
                            calendar.setYearRange("1900:2100");
                            calendar.setNavigator(true);
                            if (itemPregunta.getRequerida()) {
                                calendar.setRequired(true);
                                calendar.setRequiredMessage(itemPregunta.getTexto() + " es un campo requerido");
                            }
                            //Variable resultadoBusquedaPregunta realiza la busqueda según la pregunta y la persona que realizo la encuesta
                            resultadoBusquedaPregunta = (List<Resultado>) serResultado.listaResultadoPorPregunta(itemPregunta.getIdPregunta(), personaEncontrada.getIdPersona());
                            if (!resultadoBusquedaPregunta.isEmpty() && resultadoBusquedaPregunta.get(0).getResultado() != null) {
                                calendar.setValue(AplicacionUtil.parseFecha(resultadoBusquedaPregunta.get(0).getResultado()));
                                resultado = new Resultado(resultadoBusquedaPregunta.get(0).getIdResultado(), personaEncontrada, itemPregunta);
                            } else if (!resultadoBusquedaPregunta.isEmpty()) {
                                resultado = new Resultado(resultadoBusquedaPregunta.get(0).getIdResultado(), personaEncontrada, itemPregunta);
                            } else {
                                resultado = new Resultado(null, personaEncontrada, itemPregunta);
                            }
                            if (itemPregunta.getTexto().equals("Fecha de la encuesta") || itemPregunta.getTexto().equals("Fecha") || itemPregunta.getTexto().equals("FECHA")) {
                                Date fechaActual = new Date();
                                String resultado1 = AplicacionUtil.dateToString(fechaActual);
                                calendar.setValue(AplicacionUtil.parseFecha(resultado1));
                                calendar.setDisabled(true);
                            }
                            resultado.setResultado(Constantes.VACIO);
                            //Se añade a la lista de resultado según el tipo de control Calendar
                            resultados.add(resultado);
                            calendar.setValueExpression("value", createValueExpression("#{beanEncuesta.resultados.get(" + numero + ").resultado}", Calendar.class));
                            panelGridHijo.getChildren().add(calendar);
                            numero = numero + 1;

                            if (itemPregunta.getTexto().equals("Fecha de nacimiento") || itemPregunta.getTexto().equals("Fecha de Nacimiento") || itemPregunta.getTexto().equals("FECHA DE NACIMIENTO")) {
                                calendar.setValue(personaEncontrada.getFechaNacimiento());
                            }
                        }

                        if (tipoPregEncontrada.getControlTipo().equals("ManyCheckbox")) {

                            selectManyCheckbox = new HtmlSelectManyCheckbox();
                            selectManyCheckbox.setId("selectManyCheckbox" + itemPregunta.getIdPregunta());
                            if (itemPregunta.getRequerida()) {
                                selectManyCheckbox.setRequired(true);
                                selectManyCheckbox.setRequiredMessage(itemPregunta.getTexto() + " es un campo requerido");
                            }
                            listaRespuestas = serRespuesta.listaRespuestaPorPregunta(itemPregunta.getIdPregunta());
                            resultadoBusquedaPregunta = (List<Resultado>) serResultado.listaResultadoPorPregunta(itemPregunta.getIdPregunta(), personaEncontrada.getIdPersona());

                            for (Respuesta itemRespuesta : listaRespuestas) {
                                uISelectItem = new UISelectItem();
                                uISelectItem.setId("item" + itemRespuesta.getIdRespuesta());
                                uISelectItem.setItemValue(itemRespuesta.getRespuestaTexto());
                                uISelectItem.setItemLabel(itemRespuesta.getRespuestaTexto());
                                selectManyCheckbox.getChildren().add(uISelectItem);
                            }

                            resultadoAux = new ResultadoAuxDTO(itemPregunta, itemCategoria.getIdCategoria().toString());
                            //Agrega las otras propiedades que tenga el resultado Auxiliar
                            resultadosMultiples.add(resultadoAux);
                            selectManyCheckbox.setValueExpression("value", createValueExpression("#{beanEncuesta.resultadosMultiples.get(" + numero2 + ").arrayResp}", String[].class));
                            panelGridHijo.getChildren().add(selectManyCheckbox);
                            numero2 = numero2 + 1;
                            String[] lista = new String[resultadoBusquedaPregunta.size()];
                            int i = 0;
                            //Obtiene el resultado de la pregunta segun la persona que lleno
                            if (!resultadoBusquedaPregunta.isEmpty()) {
                                for (Resultado item : resultadoBusquedaPregunta) {
                                    lista[i] = item.getResultado();
                                    i++;
                                }
                                //Modifica la lista del arreglo formado por los resultados
                                selectManyCheckbox.setValue(lista);

                            }
                        }
                        if (tipoPregEncontrada.getControlTipo().equals("OneRadio")) {
                            selectOneRadio = new HtmlSelectOneRadio();
                            selectOneRadio.setId("selectOneMenuPregunta" + itemPregunta.getIdPregunta());
                            if (itemPregunta.getRequerida()) {
                                selectOneRadio.setRequired(true);
                                selectOneRadio.setRequiredMessage(itemPregunta.getTexto() + " es un campo requerido");
                            }
                            listaRespuestas = serRespuesta.listaRespuestaPorPregunta(itemPregunta.getIdPregunta());

                            for (Respuesta itemRespuesta : listaRespuestas) {
                                uISelectItem = new UISelectItem();
                                uISelectItem.setId("item" + itemRespuesta.getIdRespuesta());
                                uISelectItem.setItemValue(itemRespuesta.getRespuestaTexto());
                                uISelectItem.setItemLabel(itemRespuesta.getRespuestaTexto());
                                selectOneRadio.getChildren().add(uISelectItem);
                            }
                            resultadoBusquedaPregunta = (List<Resultado>) serResultado.listaResultadoPorPregunta(itemPregunta.getIdPregunta(), personaEncontrada.getIdPersona());
                            if (!resultadoBusquedaPregunta.isEmpty()) {
                                selectOneRadio.setValue(resultadoBusquedaPregunta.get(0).getResultado());
                                resultado = new Resultado(resultadoBusquedaPregunta.get(0).getIdResultado(), personaEncontrada, itemPregunta);
                            } else {
                                resultado = new Resultado(null, personaEncontrada, itemPregunta);
                            }
                            resultado.setResultado(Constantes.VACIO);
                            resultados.add(resultado);
                            selectOneRadio.setValueExpression("value", createValueExpression("#{beanEncuesta.resultados.get(" + numero + ").resultado}", String.class));
                            panelGridHijo.getChildren().add(selectOneRadio);
                            numero = numero + 1;
                            //Recupera los datos del sexo de la persona
                            if (itemPregunta.getTexto().equals("Sexo") || itemPregunta.getTexto().equals("SEXO")) {
                                selectOneRadio.setValue(personaEncontrada.getGenero());
                            } else if ((itemPregunta.getTexto().equals("Estado civil")) || (itemPregunta.getTexto().equals("ESTADO CIVIL"))) {
                                selectOneRadio.setValue(personaEncontrada.getIdEstadocivil().getNomEstadocivil());
                            }
                        }
                        if (tipoPregEncontrada.getControlTipo().equals("OneMenu")) {
                            selectOneMenu = new HtmlSelectOneMenu();
                            selectOneMenu.setId("selectOneMenuPregunta" + itemPregunta.getIdPregunta());
                            selectOneMenu.setStyle("width:100%");
                            if (itemPregunta.getRequerida()) {
                                selectOneMenu.setRequired(true);
                                selectOneMenu.setRequiredMessage(itemPregunta.getTexto() + " es un campo requerido");
                            }

                            uISelectItem = new UISelectItem();
                            uISelectItem.setItemLabel("SELECCIONE UNO");
                            selectOneMenu.getChildren().add(uISelectItem);
                            listaRespuestas = serRespuesta.listaRespuestaPorPregunta(itemPregunta.getIdPregunta());
                            for (Respuesta itemRespuesta : listaRespuestas) {
                                uISelectItem = new UISelectItem();
                                uISelectItem.setId("item" + itemRespuesta.getIdRespuesta());
                                uISelectItem.setItemValue(itemRespuesta.getRespuestaTexto());
                                uISelectItem.setItemLabel(itemRespuesta.getRespuestaTexto());
                                selectOneMenu.getChildren().add(uISelectItem);
                            }
                            resultadoBusquedaPregunta = (List<Resultado>) serResultado.listaResultadoPorPregunta(itemPregunta.getIdPregunta(), personaEncontrada.getIdPersona());
                            if (!resultadoBusquedaPregunta.isEmpty()) {
                                selectOneMenu.setValue(resultadoBusquedaPregunta.get(0).getResultado());
                                resultado = new Resultado(resultadoBusquedaPregunta.get(0).getIdResultado(), personaEncontrada, itemPregunta);
                            } else {
                                resultado = new Resultado(null, personaEncontrada, itemPregunta);
                            }

                            resultado.setResultado(Constantes.VACIO);
                            resultados.add(resultado);
                            selectOneMenu.setValueExpression("value", createValueExpression("#{beanEncuesta.resultados.get(" + numero + ").resultado}", String.class));
                            panelGridHijo.getChildren().add(selectOneMenu);
                            numero = numero + 1;

                        }
                        if (tipoPregEncontrada.getControlTipo().equals("Vacio")) {
                            outputLabel = new OutputLabel();
                            outputLabel.setId("OutputTextVacio" + itemPregunta.getIdPregunta());
                            outputLabel.setStyle("width:100%");
                            panelGridHijo.getChildren().add(outputLabel);
                        }

                        //Agrega el panelgridhijo al panel grid principal
                        panelGrid.getChildren().add(panelGridHijo);

                    }
                }

            }
        }
    }

    /**
     * Método para guardar los resultados de la encuesta
     *
     * @return navegarMensajeEncuesta un mensaje una vez finalizada la accion
     */
    @SuppressWarnings("ThrowableResultIgnored")
    public String guardar() {
        String navegarMensajeEncuesta = Constantes.VACIO;
        try {
            if (resultadosMultiples.size() > 0) { // Condición me permite controlar si el resultado es vacío, cuando se tiene opción múltiples la respuesta
                List<Resultado> resBusquedaPregunta = serResultado.listaResultadoPorPregunta(resultadosMultiples.get(0).getIdPregunta().getIdPregunta(), personaEncontrada.getIdPersona());
                for (Resultado item : resBusquedaPregunta) {
                    serResultado.eliminarResultado(item);//elimina los datos para ingresar en el caso de cambiar su respuesta.
                }
            }

            //Aqui paso de las de resultadoAux a respuestas para poder almacenar en la base
            for (int i = 0; i < resultadosMultiples.size(); i++) {
                for (int j = 0; j < resultadosMultiples.get(i).getArrayResp().length; j++) {

                    Integer idPregunta = resultadosMultiples.get(i).getIdPregunta().getIdPregunta();
                    Resultado res = new Resultado(idPregunta, personaEncontrada, resultadosMultiples.get(i).getIdPregunta());
                    String respuesta = resultadosMultiples.get(i).getArrayResp()[j];
                    res.setResultado(respuesta);
                    //Consulto en BD si tengo la respuesta ya ingresada
                    resultados.add(res);
                }
            }

            //Aqui recorro los resultados
            for (Resultado itemResultado : resultados) {
                //EN LUGAR DE IMPRIMIR EN CONSOLA DEBES GUARDAR EN LA BASE
                if (itemResultado.getIdPregunta().getIdTipopregunta().getControlTipo().equals("Calendar") && itemResultado.getResultado() != null) {
                    String fecha = AplicacionUtil.stringADate(itemResultado.getResultado());
                    itemResultado.setResultado(fecha);
                }
                if (null != itemResultado.getIdResultado()) {
                    serResultado.actualizarResultado(itemResultado);//se actualiza los datos
                } else {
                    serResultado.insertarResultado(itemResultado);//se guarda los datos en la base de datos
                }
            }

            navegarMensajeEncuesta = "/paginas/cuestionario/mensajeEncuestaGuardada.jsf";
            MensajesFaces.informacion("DATOS GUARDADOS", "ENCUESTA GUARDADA");
        } catch (Exception e) {
            MensajesFaces.error("ERROR AL INTENTAR GUARDAR", "EXISTE UN INCONVENIENTE AL GUARDAR LA ENCUESTA, INFORME AL ADMINISTRADOR." + e);
        }
        return navegarMensajeEncuesta;
    }

    ///----------fin codigo para visualizar encuesta
    //set y get de crear una encuesta
    /**
     * Devuelve la PanelGrid para la pregunta
     *
     * @return panelGridPreg
     */
    public PanelGrid getPanelGridPreg() {
        return panelGridPreg;
    }

    /**
     * Modifica PanelGrid de la pregunta
     *
     * @param panelGridPreg tipo PanelGrid
     */
    public void setPanelGridPreg(PanelGrid panelGridPreg) {
        this.panelGridPreg = panelGridPreg;
    }

    /**
     * Devuelve lista de categorias
     *
     * @return listaCategorias
     */
    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    /**
     * Modifica listaCategorias
     *
     * @param listaCategorias tipo lista
     */
    public void setListaCategorias(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    /**
     * Devuelve lista de tipo pregunta
     *
     * @return listaTiposPregunta
     */
    public List<TipoPregunta> getListaTiposPregunta() {
        return listaTiposPregunta;
    }

    /**
     * Modifica lista Tipos Pregunta
     *
     * @param listaTiposPregunta tipo lista
     */
    public void setListaTiposPregunta(List<TipoPregunta> listaTiposPregunta) {
        this.listaTiposPregunta = listaTiposPregunta;
    }

    /**
     * Devuelve lista de respuesta
     *
     * @return listaRespuestas
     */
    public List<Respuesta> getListaRespuestas() {
        return listaRespuestas;
    }

    /**
     * Modifica listaRespuestas
     *
     * @param listaRespuestas tipo lista
     */
    public void setListaRespuestas(List<Respuesta> listaRespuestas) {
        this.listaRespuestas = listaRespuestas;
    }

    /**
     * Devuelve panelGrid
     *
     * @return panelGrid
     */
    public PanelGrid getPanelGrid() {
        return panelGrid;
    }

    /**
     * Devuelve lista de preguntas
     *
     * @return listaPreguntas
     */
    public List<Pregunta> getListaPreguntas() {
        return listaPreguntas;
    }

    /**
     * Modifica listaPreguntas
     *
     * @param listaPreguntas tipo lista
     */
    public void setListaPreguntas(List<Pregunta> listaPreguntas) {
        this.listaPreguntas = listaPreguntas;
    }

    /**
     * Devuelve encuestaActiva
     *
     * @return encuestaActiva
     */
    public Encuesta getEncuestaActiva() {
        return encuestaActiva;
    }

    /**
     * Modifica encuestaActiva
     *
     * @param encuestaActiva tipo Encuesta
     */
    public void setEncuestaActiva(Encuesta encuestaActiva) {
        this.encuestaActiva = encuestaActiva;
    }

    /**
     * Devuelve imInputText
     *
     * @return imInputText
     */
    public InputText getImInputText() {
        return imInputText;
    }

    /**
     * Modifica imInputText
     *
     * @param imInputText tipo InputText
     */
    public void setImInputText(InputText imInputText) {
        this.imInputText = imInputText;
    }

    /**
     * Devuelve panelGridHijo
     *
     * @return panelGridHijo
     */
    public PanelGrid getPanelGridHijo() {
        return panelGridHijo;
    }

    /**
     * Modifica panelGridHijo
     *
     * @param panelGridHijo tipo PanelGrid
     */
    public void setPanelGridHijo(PanelGrid panelGridHijo) {
        this.panelGridHijo = panelGridHijo;
    }

    /**
     * Devuelve inputTextarea
     *
     * @return inputTextarea
     */
    public InputTextarea getInputTextarea() {
        return inputTextarea;
    }

    /**
     * Modifica inputTextarea
     *
     * @param inputTextarea tipo InputTextarea
     */
    public void setInputTextarea(InputTextarea inputTextarea) {
        this.inputTextarea = inputTextarea;
    }

    /**
     * Modifica panelGrid
     *
     * @param panelGrid tipo PanelGrid
     */
    public void setPanelGrid(PanelGrid panelGrid) {
        this.panelGrid = panelGrid;
    }

    /**
     * Devuelve outputLabel
     *
     * @return outputLabel
     */
    public OutputLabel getOutputLabel() {
        return outputLabel;
    }

    /**
     * Modifica outputLabel
     *
     * @param outputLabel tipo OutputLabel
     */
    public void setOutputLabel(OutputLabel outputLabel) {
        this.outputLabel = outputLabel;
    }

    /**
     * Devuelve resultados de la encuesta
     *
     * @return resultados
     */
    public List<Resultado> getResultados() {
        return resultados;
    }

    /**
     * Modifica resultados
     *
     * @param resultados tipo lista resultado
     */
    public void setResultados(List<Resultado> resultados) {
        this.resultados = resultados;
    }

    /**
     * Devuelve resultados múltiples de la encuesta
     *
     * @return resultadosMultiples
     */
    public List<ResultadoAuxDTO> getResultadosMultiples() {
        return resultadosMultiples;
    }

    /**
     * Modifica resultados multiples
     *
     * @param resultadosMultiples tipo lista resultadosAuxDTO
     */
    public void setResultadosMultiples(List<ResultadoAuxDTO> resultadosMultiples) {
        this.resultadosMultiples = resultadosMultiples;
    }

    /**
     * Devuelve resultado de la encuesta
     *
     * @return resultado
     */
    public Resultado getResultado() {
        return resultado;
    }

    /**
     * Modifica resultado
     *
     * @param resultado tipo lista Resultado
     */
    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    /**
     * Devuelve resultadoAux de la encuesta
     *
     * @return resultadoAux
     */
    public ResultadoAuxDTO getResultadoAux() {
        return resultadoAux;
    }

    /**
     * Modifica resultadoAux
     *
     * @param resultadoAux tipo resultadosAuxDTO
     */
    public void setResultadoAux(ResultadoAuxDTO resultadoAux) {
        this.resultadoAux = resultadoAux;
    }

    /**
     * Devuelve numero como identificador del resultado múltiple
     *
     * @return numero
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * Modifica número
     *
     * @param numero tipo lista Integer
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    /**
     * Devuelve numero2
     *
     * @return numero2
     */
    public Integer getNumero2() {
        return numero2;
    }

    /**
     * Modifica numero2
     *
     * @param numero2 tipo lista Integer
     */
    public void setNumero2(Integer numero2) {
        this.numero2 = numero2;
    }

    /**
     * Devuelve calendar
     *
     * @return calendar
     */
    public Calendar getCalendar() {
        return calendar;
    }

    /**
     * Modifica calendar
     *
     * @param calendar tipo Calendar
     */
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    /**
     * Devuelve resultadoBusquedaPregunta de la encuesta
     *
     * @return resultadoBusquedaPregunta
     */
    public List<Resultado> getResultadoBusquedaPregunta() {
        return resultadoBusquedaPregunta;
    }

    /**
     * Modifica resultado Busqueda Pregunta
     *
     * @param resultadoBusquedaPregunta tipo lista Resultado
     */
    public void setResultadoBusquedaPregunta(List<Resultado> resultadoBusquedaPregunta) {
        this.resultadoBusquedaPregunta = resultadoBusquedaPregunta;
    }

}
