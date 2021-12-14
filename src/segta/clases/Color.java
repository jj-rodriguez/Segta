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
@Table(name = "color")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Color.findAll", query = "SELECT c FROM Color c")
    , @NamedQuery(name = "Color.findByIdColor", query = "SELECT c FROM Color c WHERE c.idColor = :idColor")
    , @NamedQuery(name = "Color.findByColor", query = "SELECT c FROM Color c WHERE c.color = :color")
    , @NamedQuery(name = "Color.findByCalidad", query = "SELECT c FROM Color c WHERE c.calidad = :calidad")})
public class Color implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idColor")
    private Integer idColor;
    @Column(name = "color")
    private String color;
    @Column(name = "calidad")
    private Integer calidad;
    @OneToMany(mappedBy = "idColor")
    private Collection<Tambor> tamborCollection;

    public Color() {
    }

    public Color(Integer idColor) {
        this.idColor = idColor;
    }

    public Integer getIdColor() {
        return idColor;
    }

    public void setIdColor(Integer idColor) {
        Integer oldIdColor = this.idColor;
        this.idColor = idColor;
        changeSupport.firePropertyChange("idColor", oldIdColor, idColor);
    }

    public Integer getCalidad() {
        return calidad;
    }

    public void setCalidad(Integer calidad) {
        Integer oldCalidad = this.calidad;
        this.calidad = calidad;
        changeSupport.firePropertyChange("calidad", oldCalidad, calidad);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        String oldColor = this.color;
        this.color = color;
        changeSupport.firePropertyChange("color", oldColor, color);
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
        hash += (idColor != null ? idColor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Color)) {
            return false;
        }
        Color other = (Color) object;
        if ((this.idColor == null && other.idColor != null) || (this.idColor != null && !this.idColor.equals(other.idColor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return color;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
