/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.modelo;

import com.cursojsf.validadores.Cedula;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *<b>
 * Clase con la descripción de la entidad persona.
 * </b>
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@Entity
@Table(name = "persona", catalog = "seguimiento", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findByIdPersona", query = "SELECT p FROM Persona p WHERE p.idPersona = :idPersona"),
    @NamedQuery(name = "Persona.findByNombres", query = "SELECT p FROM Persona p WHERE p.nombres = :nombres"),
    @NamedQuery(name = "Persona.findByApellidos", query = "SELECT p FROM Persona p WHERE p.apellidos = :apellidos"),
    @NamedQuery(name = "Persona.findByTipoIdentificacion", query = "SELECT p FROM Persona p WHERE p.tipoIdentificacion = :tipoIdentificacion"),
    @NamedQuery(name = "Persona.findByNumeroIdentificacion", query = "SELECT p FROM Persona p WHERE p.numeroIdentificacion = :numeroIdentificacion"),
    @NamedQuery(name = "Persona.findByFechaNacimiento", query = "SELECT p FROM Persona p WHERE p.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Persona.findByLugarNacimiento", query = "SELECT p FROM Persona p WHERE p.lugarNacimiento = :lugarNacimiento"),
    @NamedQuery(name = "Persona.findByDireccion", query = "SELECT p FROM Persona p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Persona.findByTelefFijo", query = "SELECT p FROM Persona p WHERE p.telefFijo = :telefFijo"),
    @NamedQuery(name = "Persona.findByTelefMovil", query = "SELECT p FROM Persona p WHERE p.telefMovil = :telefMovil"),
    @NamedQuery(name = "Persona.findByCorreo", query = "SELECT p FROM Persona p WHERE p.correo = :correo"),
    @NamedQuery(name = "Persona.findByGenero", query = "SELECT p FROM Persona p WHERE p.genero = :genero"),
    @NamedQuery(name = "Persona.findByEdad", query = "SELECT p FROM Persona p WHERE p.edad = :edad"),
    @NamedQuery(name = "Persona.findByEstado", query = "SELECT p FROM Persona p WHERE p.estado = :estado"),
    @NamedQuery(name = "Persona.findByNacionalidad", query = "SELECT p FROM Persona p WHERE p.nacionalidad = :nacionalidad"),
    @NamedQuery(name = "Persona.findByDiscapacidad", query = "SELECT p FROM Persona p WHERE p.discapacidad = :discapacidad"),
    @NamedQuery(name = "Persona.findByNumConadis", query = "SELECT p FROM Persona p WHERE p.numConadis = :numConadis"),
    @NamedQuery(name = "Persona.findByPorcentajeDiscapacidad", query = "SELECT p FROM Persona p WHERE p.porcentajeDiscapacidad = :porcentajeDiscapacidad")})

public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_persona", nullable = false)
    private Integer idPersona;
    @Size(max = 300)
    @Column(name = "nombres", length = 300)
    private String nombres;
    @Size(max = 300)
    @Column(name = "apellidos", length = 300)
    private String apellidos;
    @Size(max = 50)
    @Column(name = "tipo_identificacion", length = 50)
    private String tipoIdentificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Cedula(message = "CÉDULA INCORRECTA") 
    @Column(name = "numero_identificacion", nullable = false, length = 10)
    private String numeroIdentificacion;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Size(max = 400)
    @Column(name = "lugar_nacimiento", length = 400)
    private String lugarNacimiento;
    @Size(max = 400)
    @Column(name = "direccion", length = 400)
    private String direccion;
    @Size(max = 30)
    @Column(name = "telef_fijo", length = 30)
    private String telefFijo;
    @Size(max = 30)
    @Column(name = "telef_movil", length = 30)
    private String telefMovil;
    @Size(max = 500)
    @Column(name = "correo", length = 500)
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "genero", nullable = false, length = 20)
    private String genero;
    @Column(name = "edad")
    private Integer edad;
    @Column(name = "estado")
    private Character estado;
    @Size(max = 100)
    @Column(name = "nacionalidad", length = 100)
    private String nacionalidad;
    @Size(max = 50)
    @Column(name = "discapacidad", length = 50)
    private String discapacidad;
    @Size(max = 20)
    @Column(name = "num_conadis", length = 20)
    private String numConadis;
    @Column(name = "porcentaje_discapacidad")
    private Integer porcentajeDiscapacidad;
    @JoinColumn(name = "id_carrera", referencedColumnName = "id_carrera", nullable = false)
    @ManyToOne(optional = false)
    private Carrera idCarrera;
    @JoinColumn(name = "id_ciudad", referencedColumnName = "id_ciudad", nullable = false)
    @ManyToOne(optional = false)
    private Ciudad idCiudad;
    @JoinColumn(name = "id_estadocivil", referencedColumnName = "id_estadocivil", nullable = false)
    @ManyToOne(optional = false)
    private EstadoCivil idEstadocivil;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona")
    private List<Resultado> resultadoList;

    public Persona() {
    }

    public Persona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Persona(Integer idPersona, String numeroIdentificacion, String genero) {
        this.idPersona = idPersona;
        this.numeroIdentificacion = numeroIdentificacion;
        this.genero = genero;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefFijo() {
        return telefFijo;
    }

    public void setTelefFijo(String telefFijo) {
        this.telefFijo = telefFijo;
    }

    public String getTelefMovil() {
        return telefMovil;
    }

    public void setTelefMovil(String telefMovil) {
        this.telefMovil = telefMovil;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
    }

    public String getNumConadis() {
        return numConadis;
    }

    public void setNumConadis(String numConadis) {
        this.numConadis = numConadis;
    }

    public Integer getPorcentajeDiscapacidad() {
        return porcentajeDiscapacidad;
    }

    public void setPorcentajeDiscapacidad(Integer porcentajeDiscapacidad) {
        this.porcentajeDiscapacidad = porcentajeDiscapacidad;
    }

      public Carrera getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Carrera idCarrera) {
        this.idCarrera = idCarrera;
    }

    public Ciudad getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Ciudad idCiudad) {
        this.idCiudad = idCiudad;
    }

    public EstadoCivil getIdEstadocivil() {
        return idEstadocivil;
    }

    public void setIdEstadocivil(EstadoCivil idEstadocivil) {
        this.idEstadocivil = idEstadocivil;
    }

    @XmlTransient
    public List<Resultado> getResultadoList() {
        return resultadoList;
    }

    public void setResultadoList(List<Resultado> resultadoList) {
        this.resultadoList = resultadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uce.medicina.seguimiento.modelo.Persona[ idPersona=" + idPersona + " ]";
    }
    
}
