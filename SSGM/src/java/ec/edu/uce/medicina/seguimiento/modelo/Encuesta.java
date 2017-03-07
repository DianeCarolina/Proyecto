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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * <b>
 * Clase con la descripción de la entidad encuesta.
 * </b>
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@Entity
@Table(name = "encuesta", catalog = "seguimiento", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Encuesta.findAll", query = "SELECT e FROM Encuesta e"),
    @NamedQuery(name = "Encuesta.findByIdEncuesta", query = "SELECT e FROM Encuesta e WHERE e.idEncuesta = :idEncuesta"),
    @NamedQuery(name = "Encuesta.findByTituloEncuesta", query = "SELECT e FROM Encuesta e WHERE e.tituloEncuesta = :tituloEncuesta"),
    @NamedQuery(name = "Encuesta.findByFechaInicio", query = "SELECT e FROM Encuesta e WHERE e.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Encuesta.findByFechaFinal", query = "SELECT e FROM Encuesta e WHERE e.fechaFinal = :fechaFinal"),
    @NamedQuery(name = "Encuesta.findByActivo", query = "SELECT e FROM Encuesta e WHERE e.activo = :activo")})
public class Encuesta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_encuesta", nullable = false)
    private Integer idEncuesta;
    @Size(max = 800)
    @Column(name = "titulo_encuesta", length = 800)
    private String tituloEncuesta;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Column(name = "activo")
    private Boolean activo;
    @JoinColumn(name = "id_carrera", referencedColumnName = "id_carrera", nullable = false)
    @ManyToOne(optional = false)
    private Carrera idCarrera;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEncuesta")
    private List<Categoria> categoriaList;

    public Encuesta() {
    }

    public Encuesta(Integer idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public Integer getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(Integer idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public String getTituloEncuesta() {
        return tituloEncuesta;
    }

    public void setTituloEncuesta(String tituloEncuesta) {
        this.tituloEncuesta = tituloEncuesta;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Carrera getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Carrera idCarrera) {
        this.idCarrera = idCarrera;
    }

    @XmlTransient
    public List<Categoria> getCategoriaList() {
        return categoriaList;
    }

    public void setCategoriaList(List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEncuesta != null ? idEncuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Encuesta)) {
            return false;
        }
        Encuesta other = (Encuesta) object;
        if ((this.idEncuesta == null && other.idEncuesta != null) || (this.idEncuesta != null && !this.idEncuesta.equals(other.idEncuesta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uce.medicina.seguimiento.modelo.Encuesta[ idEncuesta=" + idEncuesta + " ]";
    }

}
