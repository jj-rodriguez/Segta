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
@Table(name = "contratodeterminacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contratodeterminacion.findAll", query = "SELECT c FROM Contratodeterminacion c")
    , @NamedQuery(name = "Contratodeterminacion.findByIdContratoDeterminacion", query = "SELECT c FROM Contratodeterminacion c WHERE c.idContratoDeterminacion = :idContratoDeterminacion")})
public class Contratodeterminacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idContratoDeterminacion")
    private Integer idContratoDeterminacion;
    @JoinColumn(name = "idContrato", referencedColumnName = "idContrato")
    @ManyToOne
    private Contrato idContrato;
    @JoinColumn(name = "idDeterminacion", referencedColumnName = "idDeterminacion")
    @ManyToOne
    private Determinacion idDeterminacion;

    public Contratodeterminacion() {
    }

    public Contratodeterminacion(Integer idContratoDeterminacion) {
        this.idContratoDeterminacion = idContratoDeterminacion;
    }

    public Integer getIdContratoDeterminacion() {
        return idContratoDeterminacion;
    }

    public void setIdContratoDeterminacion(Integer idContratoDeterminacion) {
        this.idContratoDeterminacion = idContratoDeterminacion;
    }

    public Contrato getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Contrato idContrato) {
        this.idContrato = idContrato;
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
        hash += (idContratoDeterminacion != null ? idContratoDeterminacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contratodeterminacion)) {
            return false;
        }
        Contratodeterminacion other = (Contratodeterminacion) object;
        if ((this.idContratoDeterminacion == null && other.idContratoDeterminacion != null) || (this.idContratoDeterminacion != null && !this.idContratoDeterminacion.equals(other.idContratoDeterminacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "segta.clases.Contratodeterminacion[ idContratoDeterminacion=" + idContratoDeterminacion + " ]";
    }
    
}
