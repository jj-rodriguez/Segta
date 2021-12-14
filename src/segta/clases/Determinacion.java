/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segta.clases;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Quales Group
 */
@Entity
@Table(name = "determinacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Determinacion.findAll", query = "SELECT d FROM Determinacion d")
    , @NamedQuery(name = "Determinacion.findByIdDeterminacion", query = "SELECT d FROM Determinacion d WHERE d.idDeterminacion = :idDeterminacion")
    , @NamedQuery(name = "Determinacion.findByNombre", query = "SELECT d FROM Determinacion d WHERE d.nombre = :nombre")
    , @NamedQuery(name = "Determinacion.findByUnidad", query = "SELECT d FROM Determinacion d WHERE d.unidad = :unidad")})
public class Determinacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDeterminacion")
    private Integer idDeterminacion;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "unidad")
    private String unidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDeterminacion")
    private Collection<Analisisdeterminacion> analisisdeterminacionCollection;
    @OneToMany(mappedBy = "idDeterminacion")
    private Collection<Contratodeterminacion> contratodeterminacionCollection;

    public Determinacion() {
    }

    public Determinacion(Integer idDeterminacion) {
        this.idDeterminacion = idDeterminacion;
    }

    public Determinacion(Integer idDeterminacion, String nombre, String unidad) {
        this.idDeterminacion = idDeterminacion;
        this.nombre = nombre;
        this.unidad = unidad;
    }

    public Integer getIdDeterminacion() {
        return idDeterminacion;
    }

    public void setIdDeterminacion(Integer idDeterminacion) {
        this.idDeterminacion = idDeterminacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    @XmlTransient
    public Collection<Analisisdeterminacion> getAnalisisdeterminacionCollection() {
        return analisisdeterminacionCollection;
    }

    public void setAnalisisdeterminacionCollection(Collection<Analisisdeterminacion> analisisdeterminacionCollection) {
        this.analisisdeterminacionCollection = analisisdeterminacionCollection;
    }

    @XmlTransient
    public Collection<Contratodeterminacion> getContratodeterminacionCollection() {
        return contratodeterminacionCollection;
    }

    public void setContratodeterminacionCollection(Collection<Contratodeterminacion> contratodeterminacionCollection) {
        this.contratodeterminacionCollection = contratodeterminacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDeterminacion != null ? idDeterminacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Determinacion)) {
            return false;
        }
        Determinacion other = (Determinacion) object;
        if ((this.idDeterminacion == null && other.idDeterminacion != null) || (this.idDeterminacion != null && !this.idDeterminacion.equals(other.idDeterminacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "segta.clases.Determinacion[ idDeterminacion=" + idDeterminacion + " ]";
    }
    
}
