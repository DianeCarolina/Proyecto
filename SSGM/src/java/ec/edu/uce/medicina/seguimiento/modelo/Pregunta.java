/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *<b>
 * Clase con la descripción de la entidad pregunta.
 * </b>
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@Entity
@Table(name = "pregunta", catalog = "seguimiento", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pregunta.findAll", query = "SELECT p FROM Pregunta p"),
    @NamedQuery(name = "Pregunta.findByIdPregunta", query = "SELECT p FROM Pregunta p WHERE p.idPregunta = :idPregunta"),
    @NamedQuery(name = "Pregunta.findByTexto", query = "SELECT p FROM Pregunta p WHERE p.texto = :texto"),
    @NamedQuery(name = "Pregunta.findByRequerida", query = "SELECT p FROM Pregunta p WHERE p.requerida = :requerida"),
    @NamedQuery(name = "Pregunta.findByOrden", query = "SELECT p FROM Pregunta p WHERE p.orden = :orden"),
    @NamedQuery(name = "Pregunta.findByDescripcion", query = "SELECT p FROM Pregunta p WHERE p.descripcion = :descripcion")})
public class Pregunta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pregunta", nullable = false)
    private Integer idPregunta;
    @Size(max = 1000)
    @Column(name = "texto", length = 1000)
    private String texto;
    @Column(name = "requerida")
    private Boolean requerida;
//    @Size(max = 8)
//    @Column(name = "orden", length = 8)
//    private String orden;
    @Column(name = "orden", precision = 5, scale = 3)
    private BigDecimal orden;
    @Size(max = 1000)
    @Column(name = "descripcion", length = 1000)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPregunta")
    private List<Resultado> resultadoList;
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria", nullable = false)
    @ManyToOne(optional = false)
    private Categoria idCategoria;
    @JoinColumn(name = "id_tipopregunta", referencedColumnName = "id_tipopregunta", nullable = false)
    @ManyToOne(optional = false)
    private TipoPregunta idTipopregunta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPregunta")
    private List<Respuesta> respuestaList;

    public Pregunta() {
    }

    public Pregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public Integer getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Boolean getRequerida() {
        return requerida;
    }

    public void setRequerida(Boolean requerida) {
        this.requerida = requerida;
    }

//    public String getOrden() {
//        return orden;
//    }
//
//    public void setOrden(String orden) {
//        this.orden = orden;
//    }

    public BigDecimal getOrden() {
        return orden;
    }

    public void setOrden(BigDecimal orden) {
        this.orden = orden;
    }
    

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Resultado> getResultadoList() {
        return resultadoList;
    }

    public void setResultadoList(List<Resultado> resultadoList) {
        this.resultadoList = resultadoList;
    }

    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    public TipoPregunta getIdTipopregunta() {
        return idTipopregunta;
    }

    public void setIdTipopregunta(TipoPregunta idTipopregunta) {
        this.idTipopregunta = idTipopregunta;
    }

    @XmlTransient
    public List<Respuesta> getRespuestaList() {
        return respuestaList;
    }

    public void setRespuestaList(List<Respuesta> respuestaList) {
        this.respuestaList = respuestaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPregunta != null ? idPregunta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pregunta)) {
            return false;
        }
        Pregunta other = (Pregunta) object;
        if ((this.idPregunta == null && other.idPregunta != null) || (this.idPregunta != null && !this.idPregunta.equals(other.idPregunta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uce.medicina.seguimiento.modelo.Pregunta[ idPregunta=" + idPregunta + " ]";
    }
    
}
