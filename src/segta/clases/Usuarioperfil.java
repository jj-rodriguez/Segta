/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segta.clases;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Quales Group
 */
@Entity
@Table(name = "usuarioperfil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarioperfil.findAll", query = "SELECT u FROM Usuarioperfil u")
    , @NamedQuery(name = "Usuarioperfil.findByPerfil", query = "SELECT u FROM Usuarioperfil u WHERE u.perfil = :perfil AND u.idUsuario = :idUsuario")
    , @NamedQuery(name = "Usuarioperfil.findByIdUsuarioPerfil", query = "SELECT u FROM Usuarioperfil u WHERE u.idUsuarioPerfil = :idUsuarioPerfil")})
public class Usuarioperfil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Lob
    @Column(name = "perfil")
    private String perfil;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsuarioPerfil")
    private Integer idUsuarioPerfil;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public Usuarioperfil() {
    }

    public Usuarioperfil(Integer idUsuarioPerfil) {
        this.idUsuarioPerfil = idUsuarioPerfil;
    }

    public Usuarioperfil(Integer idUsuarioPerfil, String perfil) {
        this.idUsuarioPerfil = idUsuarioPerfil;
        this.perfil = perfil;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Integer getIdUsuarioPerfil() {
        return idUsuarioPerfil;
    }

    public void setIdUsuarioPerfil(Integer idUsuarioPerfil) {
        this.idUsuarioPerfil = idUsuarioPerfil;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuarioPerfil != null ? idUsuarioPerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarioperfil)) {
            return false;
        }
        Usuarioperfil other = (Usuarioperfil) object;
        if ((this.idUsuarioPerfil == null && other.idUsuarioPerfil != null) || (this.idUsuarioPerfil != null && !this.idUsuarioPerfil.equals(other.idUsuarioPerfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "segta.clases.Usuarioperfil[ idUsuarioPerfil=" + idUsuarioPerfil + " ]";
    }

}
