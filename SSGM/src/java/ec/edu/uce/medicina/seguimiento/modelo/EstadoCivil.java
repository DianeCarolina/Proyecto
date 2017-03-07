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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *<b>
 * Clase con la descripción de la entidad estado civil.
 * </b>
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@Entity
@Table(name = "estado_civil", catalog = "seguimiento", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoCivil.findAll", query = "SELECT e FROM EstadoCivil e"),
    @NamedQuery(name = "EstadoCivil.findByIdEstadocivil", query = "SELECT e FROM EstadoCivil e WHERE e.idEstadocivil = :idEstadocivil"),
    @NamedQuery(name = "EstadoCivil.findByNomEstadocivil", query = "SELECT e FROM EstadoCivil e WHERE e.nomEstadocivil = :nomEstadocivil")})
public class EstadoCivil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estadocivil", nullable = false)
    private Integer idEstadocivil;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nom_estadocivil", nullable = false, length = 50)
    private String nomEstadocivil;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadocivil")
    private List<Persona> personaList;

    public EstadoCivil() {
    }

    public EstadoCivil(Integer idEstadocivil) {
        this.idEstadocivil = idEstadocivil;
    }

    public EstadoCivil(Integer idEstadocivil, String nomEstadocivil) {
        this.idEstadocivil = idEstadocivil;
        this.nomEstadocivil = nomEstadocivil;
    }

    public Integer getIdEstadocivil() {
        return idEstadocivil;
    }

    public void setIdEstadocivil(Integer idEstadocivil) {
        this.idEstadocivil = idEstadocivil;
    }

    public String getNomEstadocivil() {
        return nomEstadocivil;
    }

    public void setNomEstadocivil(String nomEstadocivil) {
        this.nomEstadocivil = nomEstadocivil;
    }

    @XmlTransient
    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadocivil != null ? idEstadocivil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoCivil)) {
            return false;
        }
        EstadoCivil other = (EstadoCivil) object;
        if ((this.idEstadocivil == null && other.idEstadocivil != null) || (this.idEstadocivil != null && !this.idEstadocivil.equals(other.idEstadocivil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uce.medicina.seguimiento.modelo.EstadoCivil[ idEstadocivil=" + idEstadocivil + " ]";
    }

}
