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
@Table(name = "provincia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Provincia.findAll", query = "SELECT p FROM Provincia p")
    , @NamedQuery(name = "Provincia.findByIdProvincia", query = "SELECT p FROM Provincia p WHERE p.idProvincia = :idProvincia")
    , @NamedQuery(name = "Provincia.findByNombre", query = "SELECT p FROM Provincia p WHERE p.nombre = :nombre")})
public class Provincia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idProvincia")
    private Integer idProvincia;
    @Column(name = "Nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProvincia")
    private Collection<Localidad> localidadCollection;

    public Provincia() {
    }

    public Provincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }

    public Integer getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Localidad> getLocalidadCollection() {
        return localidadCollection;
    }

    public void setLocalidadCollection(Collection<Localidad> localidadCollection) {
        this.localidadCollection = localidadCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProvincia != null ? idProvincia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Provincia)) {
            return false;
        }
        Provincia other = (Provincia) object;
        if ((this.idProvincia == null && other.idProvincia != null) || (this.idProvincia != null && !this.idProvincia.equals(other.idProvincia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
