/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.modelo;

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
 * <b>
 * Clase con la descripción de la entidad Carrera.
 * </b>
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@Entity
@Table(name = "carrera", catalog = "seguimiento", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carrera.findAll", query = "SELECT c FROM Carrera c"),
    @NamedQuery(name = "Carrera.findByIdCarrera", query = "SELECT c FROM Carrera c WHERE c.idCarrera = :idCarrera"),
    @NamedQuery(name = "Carrera.findByNomCarrera", query = "SELECT c FROM Carrera c WHERE c.nomCarrera = :nomCarrera"),
    @NamedQuery(name = "Carrera.findByTelefono", query = "SELECT c FROM Carrera c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "Carrera.findByNomDirector", query = "SELECT c FROM Carrera c WHERE c.nomDirector = :nomDirector"),
    @NamedQuery(name = "Carrera.findByModalidad", query = "SELECT c FROM Carrera c WHERE c.modalidad = :modalidad"),
    @NamedQuery(name = "Carrera.findByDuracionCarrera", query = "SELECT c FROM Carrera c WHERE c.duracionCarrera = :duracionCarrera"),
    @NamedQuery(name = "Carrera.findByTituloOtorga", query = "SELECT c FROM Carrera c WHERE c.tituloOtorga = :tituloOtorga"),
    @NamedQuery(name = "Carrera.findByNumRegOrganoColegiado", query = "SELECT c FROM Carrera c WHERE c.numRegOrganoColegiado = :numRegOrganoColegiado"),
    @NamedQuery(name = "Carrera.findByFechaOrganoColegiado", query = "SELECT c FROM Carrera c WHERE c.fechaOrganoColegiado = :fechaOrganoColegiado"),
    @NamedQuery(name = "Carrera.findByNumRegConesup", query = "SELECT c FROM Carrera c WHERE c.numRegConesup = :numRegConesup"),
    @NamedQuery(name = "Carrera.findByFechaRegConesup", query = "SELECT c FROM Carrera c WHERE c.fechaRegConesup = :fechaRegConesup"),
    @NamedQuery(name = "Carrera.findByNivel", query = "SELECT c FROM Carrera c WHERE c.nivel = :nivel"),
    @NamedQuery(name = "Carrera.findByCodSubareaEspecifica", query = "SELECT c FROM Carrera c WHERE c.codSubareaEspecifica = :codSubareaEspecifica")})
public class Carrera implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_carrera", nullable = false)
    private Integer idCarrera;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "nom_carrera", nullable = false, length = 300)
    private String nomCarrera;
    @Size(max = 20)
    @Column(name = "telefono", length = 20)
    private String telefono;
    @Size(max = 225)
    @Column(name = "nom_director", length = 225)
    private String nomDirector;
    @Size(max = 80)
    @Column(name = "modalidad", length = 80)
    private String modalidad;
    @Column(name = "duracion_carrera")
    private Integer duracionCarrera;
    @Size(max = 500)
    @Column(name = "titulo_otorga", length = 500)
    private String tituloOtorga;
    @Size(max = 40)
    @Column(name = "num_reg_organo_colegiado", length = 40)
    private String numRegOrganoColegiado;
    @Column(name = "fecha_organo_colegiado")
    @Temporal(TemporalType.DATE)
    private Date fechaOrganoColegiado;
    @Size(max = 40)
    @Column(name = "num_reg_conesup", length = 40)
    private String numRegConesup;
    @Column(name = "fecha_reg_conesup")
    @Temporal(TemporalType.DATE)
    private Date fechaRegConesup;
    @Size(max = 80)
    @Column(name = "nivel", length = 80)
    private String nivel;
    @Size(max = 20)
    @Column(name = "cod_subarea_especifica", length = 20)
    private String codSubareaEspecifica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCarrera")
    private List<Encuesta> encuestaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCarrera")
    private List<Persona> personaList;
    @JoinColumn(name = "id_facultad", referencedColumnName = "id_facultad", nullable = false)
    @ManyToOne(optional = false)
    private Facultad idFacultad;

    public Carrera() {
    }

    public Carrera(Integer idCarrera) {
        this.idCarrera = idCarrera;
    }

    public Carrera(Integer idCarrera, String nomCarrera) {
        this.idCarrera = idCarrera;
        this.nomCarrera = nomCarrera;
    }

    public Integer getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Integer idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getNomCarrera() {
        return nomCarrera;
    }

    public void setNomCarrera(String nomCarrera) {
        this.nomCarrera = nomCarrera;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNomDirector() {
        return nomDirector;
    }

    public void setNomDirector(String nomDirector) {
        this.nomDirector = nomDirector;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public Integer getDuracionCarrera() {
        return duracionCarrera;
    }

    public void setDuracionCarrera(Integer duracionCarrera) {
        this.duracionCarrera = duracionCarrera;
    }

    public String getTituloOtorga() {
        return tituloOtorga;
    }

    public void setTituloOtorga(String tituloOtorga) {
        this.tituloOtorga = tituloOtorga;
    }

    public String getNumRegOrganoColegiado() {
        return numRegOrganoColegiado;
    }

    public void setNumRegOrganoColegiado(String numRegOrganoColegiado) {
        this.numRegOrganoColegiado = numRegOrganoColegiado;
    }

    public Date getFechaOrganoColegiado() {
        return fechaOrganoColegiado;
    }

    public void setFechaOrganoColegiado(Date fechaOrganoColegiado) {
        this.fechaOrganoColegiado = fechaOrganoColegiado;
    }

    public String getNumRegConesup() {
        return numRegConesup;
    }

    public void setNumRegConesup(String numRegConesup) {
        this.numRegConesup = numRegConesup;
    }

    public Date getFechaRegConesup() {
        return fechaRegConesup;
    }

    public void setFechaRegConesup(Date fechaRegConesup) {
        this.fechaRegConesup = fechaRegConesup;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getCodSubareaEspecifica() {
        return codSubareaEspecifica;
    }

    public void setCodSubareaEspecifica(String codSubareaEspecifica) {
        this.codSubareaEspecifica = codSubareaEspecifica;
    }

    @XmlTransient
    public List<Encuesta> getEncuestaList() {
        return encuestaList;
    }

    public void setEncuestaList(List<Encuesta> encuestaList) {
        this.encuestaList = encuestaList;
    }

    @XmlTransient
    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    public Facultad getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(Facultad idFacultad) {
        this.idFacultad = idFacultad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCarrera != null ? idCarrera.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carrera)) {
            return false;
        }
        Carrera other = (Carrera) object;
        if ((this.idCarrera == null && other.idCarrera != null) || (this.idCarrera != null && !this.idCarrera.equals(other.idCarrera))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uce.medicina.seguimiento.modelo.Carrera[ idCarrera=" + idCarrera + " ]";
    }

}
