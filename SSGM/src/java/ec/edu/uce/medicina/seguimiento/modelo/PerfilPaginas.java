/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uce.medicina.seguimiento.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *<b>
 * Clase con la descripción de la entidad perfil de paginas.
 * </b>
 * @author MORETA DIANA
 * @version 1.0, 1/08/2016
 * @since JDK1.8
 */
@Entity
@Table(name = "perfil_paginas", catalog = "seguimiento", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PerfilPaginas.findAll", query = "SELECT p FROM PerfilPaginas p"),
    @NamedQuery(name = "PerfilPaginas.findByIdPerfilPaginas", query = "SELECT p FROM PerfilPaginas p WHERE p.idPerfilPaginas = :idPerfilPaginas")})
public class PerfilPaginas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_perfil_paginas", nullable = false)
    private Integer idPerfilPaginas;
    @JoinColumn(name = "id_paginas", referencedColumnName = "id_paginas", nullable = false)
    @ManyToOne(optional = false)
    private Paginas idPaginas;
    @JoinColumn(name = "id_perfil", referencedColumnName = "id_perfil", nullable = false)
    @ManyToOne(optional = false)
    private Perfil idPerfil;

    public PerfilPaginas() {
    }

    public PerfilPaginas(Integer idPerfilPaginas) {
        this.idPerfilPaginas = idPerfilPaginas;
    }

    public Integer getIdPerfilPaginas() {
        return idPerfilPaginas;
    }

    public void setIdPerfilPaginas(Integer idPerfilPaginas) {
        this.idPerfilPaginas = idPerfilPaginas;
    }

    public Paginas getIdPaginas() {
        return idPaginas;
    }

    public void setIdPaginas(Paginas idPaginas) {
        this.idPaginas = idPaginas;
    }

    public Perfil getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Perfil idPerfil) {
        this.idPerfil = idPerfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerfilPaginas != null ? idPerfilPaginas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PerfilPaginas)) {
            return false;
        }
        PerfilPaginas other = (PerfilPaginas) object;
        if ((this.idPerfilPaginas == null && other.idPerfilPaginas != null) || (this.idPerfilPaginas != null && !this.idPerfilPaginas.equals(other.idPerfilPaginas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uce.medicina.seguimiento.modelo.PerfilPaginas[ idPerfilPaginas=" + idPerfilPaginas + " ]";
    }

}
