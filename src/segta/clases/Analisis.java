/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segta.clases;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Quales Group
 */
@Entity
@Table(name = "analisis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Analisis.findAll", query = "SELECT a FROM Analisis a")
    , @NamedQuery(name = "Analisis.findByIdAnalisis", query = "SELECT a FROM Analisis a WHERE a.idAnalisis = :idAnalisis")
    , @NamedQuery(name = "Analisis.findByFecha", query = "SELECT a FROM Analisis a WHERE a.fecha = :fecha")
    , @NamedQuery(name = "Analisis.findByObservaciones", query = "SELECT a FROM Analisis a WHERE a.observaciones = :observaciones")})
public class Analisis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAnalisis")
    private Integer idAnalisis;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "idLote", referencedColumnName = "idLote")
    @ManyToOne(optional = false)
    private Lote idLote;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAnalisis")
    private Collection<Analisisdeterminacion> analisisdeterminacionCollection;

    public Analisis() {
    }

    public Analisis(Integer idAnalisis) {
        this.idAnalisis = idAnalisis;
    }

    public Analisis(Integer idAnalisis, Date fecha) {
        this.idAnalisis = idAnalisis;
        this.fecha = fecha;
    }

    public Integer getIdAnalisis() {
        return idAnalisis;
    }

    public void setIdAnalisis(Integer idAnalisis) {
        this.idAnalisis = idAnalisis;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Lote getIdLote() {
        return idLote;
    }

    public void setIdLote(Lote idLote) {
        this.idLote = idLote;
    }

    @XmlTransient
    public Collection<Analisisdeterminacion> getAnalisisdeterminacionCollection() {
        return analisisdeterminacionCollection;
    }

    public void setAnalisisdeterminacionCollection(Collection<Analisisdeterminacion> analisisdeterminacionCollection) {
        this.analisisdeterminacionCollection = analisisdeterminacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnalisis != null ? idAnalisis.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Analisis)) {
            return false;
        }
        Analisis other = (Analisis) object;
        if ((this.idAnalisis == null && other.idAnalisis != null) || (this.idAnalisis != null && !this.idAnalisis.equals(other.idAnalisis))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "segta.clases.Analisis[ idAnalisis=" + idAnalisis + " ]";
    }
    
}
