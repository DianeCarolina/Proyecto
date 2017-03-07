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
 * Clase con la descripción de la entidad universidad.
 * </b>
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@Entity
@Table(name = "universidad", catalog = "seguimiento", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Universidad.findAll", query = "SELECT u FROM Universidad u"),
    @NamedQuery(name = "Universidad.findByIdUniversidad", query = "SELECT u FROM Universidad u WHERE u.idUniversidad = :idUniversidad"),
    @NamedQuery(name = "Universidad.findByCodUniversidad", query = "SELECT u FROM Universidad u WHERE u.codUniversidad = :codUniversidad"),
    @NamedQuery(name = "Universidad.findByTipoUniversidad", query = "SELECT u FROM Universidad u WHERE u.tipoUniversidad = :tipoUniversidad"),
    @NamedQuery(name = "Universidad.findByNomUniversidad", query = "SELECT u FROM Universidad u WHERE u.nomUniversidad = :nomUniversidad"),
    @NamedQuery(name = "Universidad.findByDireccionUniversidad", query = "SELECT u FROM Universidad u WHERE u.direccionUniversidad = :direccionUniversidad"),
    @NamedQuery(name = "Universidad.findByTelefonoUniversidad", query = "SELECT u FROM Universidad u WHERE u.telefonoUniversidad = :telefonoUniversidad"),
    @NamedQuery(name = "Universidad.findBySitioWeb", query = "SELECT u FROM Universidad u WHERE u.sitioWeb = :sitioWeb"),
    @NamedQuery(name = "Universidad.findByPais", query = "SELECT u FROM Universidad u WHERE u.pais = :pais"),
    @NamedQuery(name = "Universidad.findByProvincia", query = "SELECT u FROM Universidad u WHERE u.provincia = :provincia"),
    @NamedQuery(name = "Universidad.findByCiudad", query = "SELECT u FROM Universidad u WHERE u.ciudad = :ciudad"),
    @NamedQuery(name = "Universidad.findByAutoridad", query = "SELECT u FROM Universidad u WHERE u.autoridad = :autoridad")})
public class Universidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_universidad", nullable = false)
    private Integer idUniversidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_universidad", nullable = false)
    private short codUniversidad;
    @Size(max = 70)
    @Column(name = "tipo_universidad", length = 70)
    private String tipoUniversidad;
    @Size(max = 100)
    @Column(name = "nom_universidad", length = 100)
    private String nomUniversidad;
    @Size(max = 100)
    @Column(name = "direccion_universidad", length = 100)
    private String direccionUniversidad;
    @Size(max = 20)
    @Column(name = "telefono_universidad", length = 20)
    private String telefonoUniversidad;
    @Size(max = 120)
    @Column(name = "sitio_web", length = 120)
    private String sitioWeb;
    @Size(max = 80)
    @Column(name = "pais", length = 80)
    private String pais;
    @Size(max = 80)
    @Column(name = "provincia", length = 80)
    private String provincia;
    @Size(max = 50)
    @Column(name = "ciudad", length = 50)
    private String ciudad;
    @Size(max = 80)
    @Column(name = "autoridad", length = 80)
    private String autoridad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUniversidad")
    private List<Facultad> facultadList;

    public Universidad() {
    }

    public Universidad(Integer idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    public Universidad(Integer idUniversidad, short codUniversidad) {
        this.idUniversidad = idUniversidad;
        this.codUniversidad = codUniversidad;
    }

    public Integer getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(Integer idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    public short getCodUniversidad() {
        return codUniversidad;
    }

    public void setCodUniversidad(short codUniversidad) {
        this.codUniversidad = codUniversidad;
    }

    public String getTipoUniversidad() {
        return tipoUniversidad;
    }

    public void setTipoUniversidad(String tipoUniversidad) {
        this.tipoUniversidad = tipoUniversidad;
    }

    public String getNomUniversidad() {
        return nomUniversidad;
    }

    public void setNomUniversidad(String nomUniversidad) {
        this.nomUniversidad = nomUniversidad;
    }

    public String getDireccionUniversidad() {
        return direccionUniversidad;
    }

    public void setDireccionUniversidad(String direccionUniversidad) {
        this.direccionUniversidad = direccionUniversidad;
    }

    public String getTelefonoUniversidad() {
        return telefonoUniversidad;
    }

    public void setTelefonoUniversidad(String telefonoUniversidad) {
        this.telefonoUniversidad = telefonoUniversidad;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getAutoridad() {
        return autoridad;
    }

    public void setAutoridad(String autoridad) {
        this.autoridad = autoridad;
    }

    @XmlTransient
    public List<Facultad> getFacultadList() {
        return facultadList;
    }

    public void setFacultadList(List<Facultad> facultadList) {
        this.facultadList = facultadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUniversidad != null ? idUniversidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Universidad)) {
            return false;
        }
        Universidad other = (Universidad) object;
        if ((this.idUniversidad == null && other.idUniversidad != null) || (this.idUniversidad != null && !this.idUniversidad.equals(other.idUniversidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uce.medicina.seguimiento.modelo.Universidad[ idUniversidad=" + idUniversidad + " ]";
    }
    
}
