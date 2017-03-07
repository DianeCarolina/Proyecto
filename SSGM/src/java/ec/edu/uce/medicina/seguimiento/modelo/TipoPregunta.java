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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *<b>
 * Clase con la descripción de la entidad tipo de pregunta.
 * </b>
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@Entity
@Table(name = "tipo_pregunta", catalog = "seguimiento", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPregunta.findAll", query = "SELECT t FROM TipoPregunta t"),
    @NamedQuery(name = "TipoPregunta.findByIdTipopregunta", query = "SELECT t FROM TipoPregunta t WHERE t.idTipopregunta = :idTipopregunta"),
    @NamedQuery(name = "TipoPregunta.findByNomTipopregunta", query = "SELECT t FROM TipoPregunta t WHERE t.nomTipopregunta = :nomTipopregunta"),
    @NamedQuery(name = "TipoPregunta.findByControlTipo", query = "SELECT t FROM TipoPregunta t WHERE t.controlTipo = :controlTipo")})
public class TipoPregunta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipopregunta", nullable = false)
    private Integer idTipopregunta;
    @Size(max = 700)
    @Column(name = "nom_tipopregunta", length = 700)
    private String nomTipopregunta;
    @Size(max = 800)
    @Column(name = "control_tipo", length = 800)
    private String controlTipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipopregunta")
    private List<Pregunta> preguntaList;

    public TipoPregunta() {
    }

    public TipoPregunta(Integer idTipopregunta) {
        this.idTipopregunta = idTipopregunta;
    }

    public Integer getIdTipopregunta() {
        return idTipopregunta;
    }

    public void setIdTipopregunta(Integer idTipopregunta) {
        this.idTipopregunta = idTipopregunta;
    }

    public String getNomTipopregunta() {
        return nomTipopregunta;
    }

    public void setNomTipopregunta(String nomTipopregunta) {
        this.nomTipopregunta = nomTipopregunta;
    }

    public String getControlTipo() {
        return controlTipo;
    }

    public void setControlTipo(String controlTipo) {
        this.controlTipo = controlTipo;
    }

    @XmlTransient
    public List<Pregunta> getPreguntaList() {
        return preguntaList;
    }

    public void setPreguntaList(List<Pregunta> preguntaList) {
        this.preguntaList = preguntaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipopregunta != null ? idTipopregunta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPregunta)) {
            return false;
        }
        TipoPregunta other = (TipoPregunta) object;
        if ((this.idTipopregunta == null && other.idTipopregunta != null) || (this.idTipopregunta != null && !this.idTipopregunta.equals(other.idTipopregunta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uce.medicina.seguimiento.modelo.TipoPregunta[ idTipopregunta=" + idTipopregunta + " ]";
    }
    
}
