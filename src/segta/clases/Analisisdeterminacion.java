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
@Table(name = "analisisdeterminacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Analisisdeterminacion.findAll", query = "SELECT a FROM Analisisdeterminacion a")
    , @NamedQuery(name = "Analisisdeterminacion.findByIdAnalisisDeterminacion", query = "SELECT a FROM Analisisdeterminacion a WHERE a.idAnalisisDeterminacion = :idAnalisisDeterminacion")
    , @NamedQuery(name = "Analisisdeterminacion.findByValor", query = "SELECT a FROM Analisisdeterminacion a WHERE a.valor = :valor")})
public class Analisisdeterminacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAnalisisDeterminacion")
    private Integer idAnalisisDeterminacion;
    @Basic(optional = false)
    @Column(name = "valor")
    private String valor;
    @JoinColumn(name = "idAnalisis", referencedColumnName = "idAnalisis")
    @ManyToOne(optional = false)
    private Analisis idAnalisis;
    @JoinColumn(name = "idDeterminacion", referencedColumnName = "idDeterminacion")
    @ManyToOne(optional = false)
    private Determinacion idDeterminacion;

    public Analisisdeterminacion() {
    }

    public Analisisdeterminacion(Integer idAnalisisDeterminacion) {
        this.idAnalisisDeterminacion = idAnalisisDeterminacion;
    }

    public Analisisdeterminacion(Integer idAnalisisDeterminacion, String valor) {
        this.idAnalisisDeterminacion = idAnalisisDeterminacion;
        this.valor = valor;
    }

    public Integer getIdAnalisisDeterminacion() {
        return idAnalisisDeterminacion;
    }

    public void setIdAnalisisDeterminacion(Integer idAnalisisDeterminacion) {
        this.idAnalisisDeterminacion = idAnalisisDeterminacion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Analisis getIdAnalisis() {
        return idAnalisis;
    }

    public void setIdAnalisis(Analisis idAnalisis) {
        this.idAnalisis = idAnalisis;
    }

    public Determinacion getIdDeterminacion() {
        return idDeterminacion;
    }

    public void setIdDeterminacion(Determinacion idDeterminacion) {
        this.idDeterminacion = idDeterminacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnalisisDeterminacion != null ? idAnalisisDeterminacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Analisisdeterminacion)) {
            return false;
        }
        Analisisdeterminacion other = (Analisisdeterminacion) object;
        if ((this.idAnalisisDeterminacion == null && other.idAnalisisDeterminacion != null) || (this.idAnalisisDeterminacion != null && !this.idAnalisisDeterminacion.equals(other.idAnalisisDeterminacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "segta.clases.Analisisdeterminacion[ idAnalisisDeterminacion=" + idAnalisisDeterminacion + " ]";
    }
    
}
