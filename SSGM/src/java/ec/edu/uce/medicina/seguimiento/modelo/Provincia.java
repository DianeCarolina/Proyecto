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
 *<b>
 * Clase con la descripción de la entidad provincia.
 * </b>
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@Entity
@Table(name = "provincia", catalog = "seguimiento", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Provincia.findAll", query = "SELECT p FROM Provincia p"),
    @NamedQuery(name = "Provincia.findByIdProvincia", query = "SELECT p FROM Provincia p WHERE p.idProvincia = :idProvincia"),
    @NamedQuery(name = "Provincia.findByCodProvincia", query = "SELECT p FROM Provincia p WHERE p.codProvincia = :codProvincia"),
    @NamedQuery(name = "Provincia.findByNomProvincia", query = "SELECT p FROM Provincia p WHERE p.nomProvincia = :nomProvincia")})
public class Provincia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_provincia", nullable = false)
    private Integer idProvincia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_provincia", nullable = false)
    private short codProvincia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nom_provincia", nullable = false, length = 50)
    private String nomProvincia;
    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais", nullable = false)
    @ManyToOne(optional = false)
    private Pais idPais;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProvincia")
    private List<Ciudad> ciudadList;

    public Provincia() {
    }

    public Provincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }

    public Provincia(Integer idProvincia, short codProvincia, String nomProvincia) {
        this.idProvincia = idProvincia;
        this.codProvincia = codProvincia;
        this.nomProvincia = nomProvincia;
    }

    public Integer getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }

    public short getCodProvincia() {
        return codProvincia;
    }

    public void setCodProvincia(short codProvincia) {
        this.codProvincia = codProvincia;
    }

    public String getNomProvincia() {
        return nomProvincia;
    }

    public void setNomProvincia(String nomProvincia) {
        this.nomProvincia = nomProvincia;
    }

    public Pais getIdPais() {
        return idPais;
    }

    public void setIdPais(Pais idPais) {
        this.idPais = idPais;
    }

    @XmlTransient
    public List<Ciudad> getCiudadList() {
        return ciudadList;
    }

    public void setCiudadList(List<Ciudad> ciudadList) {
        this.ciudadList = ciudadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProvincia != null ? idProvincia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Provincia)) {
            return false;
        }
        Provincia other = (Provincia) object;
        if ((this.idProvincia == null && other.idProvincia != null) || (this.idProvincia != null && !this.idProvincia.equals(other.idProvincia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uce.medicina.seguimiento.modelo.Provincia[ idProvincia=" + idProvincia + " ]";
    }
    
}
