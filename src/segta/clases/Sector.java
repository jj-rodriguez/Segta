/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segta.clases;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Quales Group
 */
@Entity
@Table(name = "sector")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sector.findAll", query = "SELECT s FROM Sector s")
    , @NamedQuery(name = "Sector.findByIdSector", query = "SELECT s FROM Sector s WHERE s.idSector = :idSector")
    , @NamedQuery(name = "Sector.findByNombre", query = "SELECT s FROM Sector s WHERE s.nombre = :nombre")})
public class Sector implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSector")
    private Integer idSector;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "idSector")
    private Collection<Tambor> tamborCollection;

    public Sector() {
    }

    public Sector(Integer idSector) {
        this.idSector = idSector;
    }

    public Sector(Integer idSector, String nombre) {
        this.idSector = idSector;
        this.nombre = nombre;
    }

    public Integer getIdSector() {
        return idSector;
    }

    public void setIdSector(Integer idSector) {
        Integer oldIdSector = this.idSector;
        this.idSector = idSector;
        changeSupport.firePropertyChange("idSector", oldIdSector, idSector);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        String oldNombre = this.nombre;
        this.nombre = nombre;
        changeSupport.firePropertyChange("nombre", oldNombre, nombre);
    }

    @XmlTransient
    public Collection<Tambor> getTamborCollection() {
        return tamborCollection;
    }

    public void setTamborCollection(Collection<Tambor> tamborCollection) {
        this.tamborCollection = tamborCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSector != null ? idSector.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sector)) {
            return false;
        }
        Sector other = (Sector) object;
        if ((this.idSector == null && other.idSector != null) || (this.idSector != null && !this.idSector.equals(other.idSector))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
