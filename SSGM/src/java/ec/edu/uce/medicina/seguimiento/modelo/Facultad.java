/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.modelo;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * <b>
 * Clase con la descripción de la entidad facultad.
 * </b>
 *
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@Entity
@Table(name = "facultad", catalog = "seguimiento", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facultad.findAll", query = "SELECT f FROM Facultad f"),
    @NamedQuery(name = "Facultad.findByIdFacultad", query = "SELECT f FROM Facultad f WHERE f.idFacultad = :idFacultad"),
    @NamedQuery(name = "Facultad.findByNomFacultad", query = "SELECT f FROM Facultad f WHERE f.nomFacultad = :nomFacultad"),
    @NamedQuery(name = "Facultad.findByTelefono", query = "SELECT f FROM Facultad f WHERE f.telefono = :telefono"),
    @NamedQuery(name = "Facultad.findByDireccionFacultad", query = "SELECT f FROM Facultad f WHERE f.direccionFacultad = :direccionFacultad"),
    @NamedQuery(name = "Facultad.findByDirectorFacultad", query = "SELECT f FROM Facultad f WHERE f.directorFacultad = :directorFacultad")})
public class Facultad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_facultad", nullable = false)
    private Integer idFacultad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nom_facultad", nullable = false, length = 100)
    private String nomFacultad;
    @Size(max = 20)
    @Column(name = "telefono", length = 20)
    private String telefono;
    @Size(max = 225)
    @Column(name = "direccion_facultad", length = 225)
    private String direccionFacultad;
    @Size(max = 300)
    @Column(name = "director_facultad", length = 300)
    private String directorFacultad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFacultad")
    private List<Carrera> carreraList;
    @JoinColumn(name = "id_universidad", referencedColumnName = "id_universidad", nullable = false)
    @ManyToOne(optional = false)
    private Universidad idUniversidad;

    public Facultad() {
    }

    public Facultad(Integer idFacultad) {
        this.idFacultad = idFacultad;
    }

    public Facultad(Integer idFacultad, String nomFacultad) {
        this.idFacultad = idFacultad;
        this.nomFacultad = nomFacultad;
    }

    public Integer getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(Integer idFacultad) {
        this.idFacultad = idFacultad;
    }

    public String getNomFacultad() {
        return nomFacultad;
    }

    public void setNomFacultad(String nomFacultad) {
        this.nomFacultad = nomFacultad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccionFacultad() {
        return direccionFacultad;
    }

    public void setDireccionFacultad(String direccionFacultad) {
        this.direccionFacultad = direccionFacultad;
    }

    public String getDirectorFacultad() {
        return directorFacultad;
    }

    public void setDirectorFacultad(String directorFacultad) {
        this.directorFacultad = directorFacultad;
    }

    @XmlTransient
    public List<Carrera> getCarreraList() {
        return carreraList;
    }

    public void setCarreraList(List<Carrera> carreraList) {
        this.carreraList = carreraList;
    }

    public Universidad getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(Universidad idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFacultad != null ? idFacultad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facultad)) {
            return false;
        }
        Facultad other = (Facultad) object;
        if ((this.idFacultad == null && other.idFacultad != null) || (this.idFacultad != null && !this.idFacultad.equals(other.idFacultad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uce.medicina.seguimiento.modelo.Facultad[ idFacultad=" + idFacultad + " ]";
    }

}
