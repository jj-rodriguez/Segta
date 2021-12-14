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
import java.util.Date;
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
@Table(name = "pool")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pool.findAll", query = "SELECT p FROM Pool p")
    , @NamedQuery(name = "Pool.findByIdpool", query = "SELECT p FROM Pool p WHERE p.idpool = :idpool")
    , @NamedQuery(name = "Pool.findByFecha", query = "SELECT p FROM Pool p WHERE p.fecha = :fecha")
    , @NamedQuery(name = "Pool.findByNumero", query = "SELECT p FROM Pool p WHERE p.numero = :numero")
    , @NamedQuery(name = "Pool.findByColor", query = "SELECT p FROM Pool p WHERE p.color = :color")
    , @NamedQuery(name = "Pool.findByHumedad", query = "SELECT p FROM Pool p WHERE p.humedad = :humedad")
    , @NamedQuery(name = "Pool.findByDextrina", query = "SELECT p FROM Pool p WHERE p.dextrina = :dextrina")
    , @NamedQuery(name = "Tambor.findByestadoDextrina", query = "SELECT t FROM Tambor t WHERE t.estadoDextrina = :estadoDextrina")
    , @NamedQuery(name = "Pool.findByHmf", query = "SELECT p FROM Pool p WHERE p.hmf = :hmf")}
)
public class Pool implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpool")
    private Integer idpool;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "numero")
    private int numero;
    @Column(name = "color")
    private String color;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "humedad")
    private Float humedad;
    @Column (name = "estadoDextrina")
    private String estadoDextrina;
    @Column(name = "dextrina")
    private Float dextrina;
    @Column(name = "hmf")
    private Float hmf;
    @OneToMany(mappedBy = "idPool")
    private Collection<Tambor> tamborCollection;

    public Pool() {
    }

    public Pool(Integer idpool) {
        this.idpool = idpool;
    }

    public Integer getIdpool() {
        return idpool;
    }

    public void setIdpool(Integer idpool) {
        Integer oldIdpool = this.idpool;
        this.idpool = idpool;
        changeSupport.firePropertyChange("idpool", oldIdpool, idpool);
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        Date oldFecha = this.fecha;
        this.fecha = fecha;
        changeSupport.firePropertyChange("fecha", oldFecha, fecha);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        int oldNumero = this.numero;
        this.numero = numero;
        changeSupport.firePropertyChange("numero", oldNumero, numero);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        String oldColor = this.color;
        this.color = color;
        changeSupport.firePropertyChange("color", oldColor, color);
    }

    public Float getHumedad() {
        return humedad;
    }

    public void setHumedad(Float humedad) {
        Float oldHumedad = this.humedad;
        this.humedad = humedad;
        changeSupport.firePropertyChange("humedad", oldHumedad, humedad);
    }

    public Float getDextrina() {
        return dextrina;
    }

    public void setDextrina(Float dextrina) {
        Float oldDextrina = this.dextrina;
        this.dextrina = dextrina;
        changeSupport.firePropertyChange("dextrina", oldDextrina, dextrina);
    }
    public String getEstadoDextrina() {
        return estadoDextrina;
    }

    public void setEstadoDextrina(String estadoDextrina) {
        String oldEstadoDextrina = this.estadoDextrina;
        this.estadoDextrina = estadoDextrina;
        changeSupport.firePropertyChange("estadoDextrina", oldEstadoDextrina, estadoDextrina);
    }

    public Float getHmf() {
        return hmf;
    }

    public void setHmf(Float hmf) {
        Float oldHmf = this.hmf;
        this.hmf = hmf;
        changeSupport.firePropertyChange("dextrina", oldHmf, hmf);
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
        hash += (idpool != null ? idpool.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pool)) {
            return false;
        }
        Pool other = (Pool) object;
        if ((this.idpool == null && other.idpool != null) || (this.idpool != null && !this.idpool.equals(other.idpool))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "segta.clases.Pool[ idpool=" + idpool + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
