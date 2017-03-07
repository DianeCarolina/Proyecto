/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.controlador;

import ec.edu.uce.medicina.seguimiento.util.MensajesFaces;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.query.QueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRProperties;

/**
 * Esta clase GeneradorReportes se encarga de enlazar los atributos con las
 * páginas XHTML en la generacion de reportes
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
/**
 * Clase Jasper para generar el reporte
 *
 * @uml.property name="jasperPrint"
 * @uml.associationEnd
 */
public class GeneradorReportes implements Serializable {

    private static final long serialVersionUID = 1L;

    private JasperPrint jasperPrint;
    /**
     * Mapa de parametros que se van a pasar al reporte
     */
    // No se usa un Mapa de genericos debido a que JasperFillManager.fillReport
    // no soporta este tipo de mapas.
    @SuppressWarnings("rawtypes")
    private Map parametrosReporte = new HashMap();
    /**
     * Nombre del archivo .jasper que genera el reporte
     */
    private String nombreJasper;
    /**
     * Path en el cual se van a guardar los archivos .jasper y .jrxml del
     * reporte. Este path lo lee del archivo reportes.properties
     */
    private String path;
    private String url;
    private String user;
    private String pass;
    private Properties properties;
//    private int flagViewDescarga;
    /**
     * Ruta del archivo reportes.properties. Es un path relativo a la carpeta
     * bin del glassfish
     */
    private String PATH_RAIZ;
    private String ARCHIVO_CONFIGURACION;
    /**
     * Nombre con el cual se genera el archivo de reporte en los diferentes
     * formatos
     */
    private String nombreReporte;
    /**
     * Atributo para la conexión
     */
    private Connection conn;

    /**
     * DataSource que se lo obtiene del servidor de aplicaciones
     *
     * name="dataSource" readOnly="true"
     */
    /**
     * Constructor. Carga el archivo de propiedades properties y lee la
     * propiedad path
     */
    public GeneradorReportes() {
        properties = System.getProperties();
        PATH_RAIZ = properties.getProperty("com.sun.aas.installRoot");
        ARCHIVO_CONFIGURACION = PATH_RAIZ + "\\modules\\Reportes\\ReportesEncuesta.properties";
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(ARCHIVO_CONFIGURACION));
            path = props.getProperty("path");//path 
            url = props.getProperty("url");//url de la base de datos postgres
            user = props.getProperty("user");// user de la base de datos postgres
            pass = props.getProperty("pass");// password de la base de datos de postgres
        } catch (FileNotFoundException e) {
            System.out.println("Error FileNotFoundException: " + e.getMessage());
            MensajesFaces.error("NO SE PUEDE GENERAR EL REPORTE", "NO SE ENCUENTRA EL ARCHIVO PROPERTIES EN LA RUTA ESTABLECIDA.");
        } catch (IOException e) {
            System.out.println("Error IOException: " + e.getMessage());
        }
    }

    /**
     * Obtiene la conexion a la base y genera el reporte
     *
     * @throws Exception si no pudo conectarse a la base de datos o no pudo
     * generar el reporte
     */
    public void generarReporte() throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, pass);
            conn.setAutoCommit(false);
            JRProperties.setProperty(QueryExecuterFactory.QUERY_EXECUTER_FACTORY_PREFIX + "plsql", "com.jaspersoft.jrx.query.PlSqlQueryExecuterFactory");
            jasperPrint = JasperFillManager.fillReport(path + "\\"
                    + nombreJasper, parametrosReporte, conn);

            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQLException: " + e.getMessage());
            throw new Exception("ERROR AL CONECTARSE A LA BASE DE DATOS ");
        } catch (JRException e) {
            System.out.println("Error JRException: " + e.getMessage());
            throw new Exception("NO SE PUDO GENERAR EL REPORTE");
        }
    }

    /**
     * Invoca a la generacion del reporte y genera el Stream con la informacion
     * del reporte que sera devuelto a la pagina
     *
     * @param extension extension del archivo a generarse (pdf, docx, xlsx. odt.
     * pptx)
     * @return el Stream con la informacion del reporte que sera devuelto a la
     * p�gina crear y descargar del pdf reporte,quieres attachment, para q se
     * abre online
     * @throws Exception si no logro generar el reporte
     */
    public ServletOutputStream generarResponse(String extension) throws Exception {
        generarReporte();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
                .getCurrentInstance().getExternalContext().getResponse();

        httpServletResponse.addHeader("Content-disposition",
                "attachment; filename=" + nombreReporte + "." + extension);

        ServletOutputStream servletOutputStream = null;
        try {
            servletOutputStream = httpServletResponse.getOutputStream();
        } catch (IOException e) {
            System.out.println("Error SQLException: " + e.getMessage());
        }
        return servletOutputStream;
    }

    /**
     * Retorna el reporte en formato PDF con el Stream que obtiene de
     * generarResponse
     *
     * @throws Exception si no logra generar el reporte
     */
    public void generarPDF() throws Exception {
        ServletOutputStream servletOutputStream = generarResponse("pdf");
        JasperExportManager.exportReportToPdfStream(jasperPrint,
                servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    // getters y setters
    /**
     * @return nombreJasper @uml.property name="nombreJasper"
     */
    public String getNombreJasper() {
        return nombreJasper;
    }

    /**
     * @param nombreJasper
     *
     */
    public void setNombreJasper(String nombreJasper) {
        this.nombreJasper = nombreJasper;
    }

    /**
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path
     *
     */
    public void setPath(String path) {
        this.path = path;
    }

    @SuppressWarnings("rawtypes")
    public Map getParametrosReporte() {
        return parametrosReporte;
    }

    @SuppressWarnings("rawtypes")
    public void setParametrosReporte(Map parametrosReporte) {
        this.parametrosReporte = parametrosReporte;
    }

    /**
     * @return nombreReporte
     */
    public String getNombreReporte() {
        return nombreReporte;
    }

    /**
     * @param nombreReporte
     *
     */
    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }
}
