/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segta.clases;

import java.beans.PropertyChangeSupport;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Quales Group
 */
@Entity
@Table(name = "descargas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Descargas.findAll", query = "SELECT d FROM Descargas d")
    , @NamedQuery(name = "Descargas.findByIdDescargas", query = "SELECT d FROM Descargas d WHERE d.idDescargas = :idDescargas")
    , @NamedQuery(name = "Descargas.findByFecha", query = "SELECT d FROM Descargas d WHERE d.fecha = :fecha")
    , @NamedQuery(name = "Descargas.findByCamion", query = "SELECT d FROM Descargas d WHERE d.camion = :camion")
    , @NamedQuery(name = "Descargas.findByObservaciones", query = "SELECT d FROM Descargas d WHERE d.observaciones = :observaciones")})
public class Descargas implements Serializable {
    
     @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDescargas")
    private Integer idDescargas;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "camion")
    private String camion;
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "idProveedor", referencedColumnName = "idProveedor")
    @ManyToOne(optional = false)
    private Proveedor idProveedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDescarga")
    private Collection<Tambor> tamborCollection;
    @Transient
    private String obsTambores;

    public Descargas() {
    }
    public Proveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedor idProveedor) {
        Proveedor oldIdProveedor = this.idProveedor;
        this.idProveedor = idProveedor;
        changeSupport.firePropertyChange("idProveedor", oldIdProveedor, idProveedor);
    }
    public Descargas(Integer idDescargas) {
        this.idDescargas = idDescargas;
    }

    public Integer getIdDescargas() {
        return idDescargas;
    }

    public void setIdDescargas(Integer idDescargas) {
        this.idDescargas = idDescargas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCamion() {
        return camion;
    }

    public void setCamion(String camion) {
        this.camion = camion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public String getObsTambores() {
        obsTambores = "";
        this.tamborCollection.forEach((t) -> {
            if (!t.getObservaciones().equals("")){
                String obs = "Tambor "+t.getNumero() +": "+t.getObservaciones();
                obsTambores = obsTambores + obs + ", ";
            }
        });
        return obsTambores;
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
        hash += (idDescargas != null ? idDescargas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Descargas)) {
            return false;
        }
        Descargas other = (Descargas) object;
        if ((this.idDescargas == null && other.idDescargas != null) || (this.idDescargas != null && !this.idDescargas.equals(other.idDescargas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "segta.clases.Descargas[ idDescargas=" + idDescargas + " ]";
    }
    
}
