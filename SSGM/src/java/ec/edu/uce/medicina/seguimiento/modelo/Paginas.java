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
 * Clase con la descripción de la entidad paginas.
 * </b>
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@Entity
@Table(name = "paginas", catalog = "seguimiento", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paginas.findAll", query = "SELECT p FROM Paginas p"),
    @NamedQuery(name = "Paginas.findByIdPaginas", query = "SELECT p FROM Paginas p WHERE p.idPaginas = :idPaginas"),
    @NamedQuery(name = "Paginas.findByNomPagina", query = "SELECT p FROM Paginas p WHERE p.nomPagina = :nomPagina"),
    @NamedQuery(name = "Paginas.findByUrl", query = "SELECT p FROM Paginas p WHERE p.url = :url"),
    @NamedQuery(name = "Paginas.findByActivo", query = "SELECT p FROM Paginas p WHERE p.activo = :activo"),
    @NamedQuery(name = "Paginas.findByDescripcion", query = "SELECT p FROM Paginas p WHERE p.descripcion = :descripcion")})
public class Paginas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_paginas", nullable = false)
    private Integer idPaginas;
    @Size(max = 100)
    @Column(name = "nom_pagina", length = 100)
    private String nomPagina;
    @Size(max = 225)
    @Column(name = "url", length = 225)
    private String url;
    @Column(name = "activo")
    private Boolean activo;
    @Size(max = 500)
    @Column(name = "descripcion", length = 500)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaginas")
    private List<PerfilPaginas> perfilPaginasList;

    public Paginas() {
    }

    public Paginas(Integer idPaginas) {
        this.idPaginas = idPaginas;
    }

    public Integer getIdPaginas() {
        return idPaginas;
    }

    public void setIdPaginas(Integer idPaginas) {
        this.idPaginas = idPaginas;
    }

    public String getNomPagina() {
        return nomPagina;
    }

    public void setNomPagina(String nomPagina) {
        this.nomPagina = nomPagina;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<PerfilPaginas> getPerfilPaginasList() {
        return perfilPaginasList;
    }

    public void setPerfilPaginasList(List<PerfilPaginas> perfilPaginasList) {
        this.perfilPaginasList = perfilPaginasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPaginas != null ? idPaginas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paginas)) {
            return false;
        }
        Paginas other = (Paginas) object;
        if ((this.idPaginas == null && other.idPaginas != null) || (this.idPaginas != null && !this.idPaginas.equals(other.idPaginas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uce.medicina.seguimiento.modelo.Paginas[ idPaginas=" + idPaginas + " ]";
    }

}
