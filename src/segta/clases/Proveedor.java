/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segta.clases;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p")
    , @NamedQuery(name = "Proveedor.findByIdProveedor", query = "SELECT p FROM Proveedor p WHERE p.idProveedor = :idProveedor")
    , @NamedQuery(name = "Proveedor.findByRazonSocial", query = "SELECT p FROM Proveedor p WHERE p.razonSocial = :razonSocial")
    , @NamedQuery(name = "Proveedor.findByCuit", query = "SELECT p FROM Proveedor p WHERE p.cuit = :cuit")
    , @NamedQuery(name = "Proveedor.findByTelefono", query = "SELECT p FROM Proveedor p WHERE p.telefono = :telefono")
    , @NamedQuery(name = "Proveedor.findByMail", query = "SELECT p FROM Proveedor p WHERE p.mail = :mail")
    , @NamedQuery(name = "Proveedor.findByDireccion", query = "SELECT p FROM Proveedor p WHERE p.direccion = :direccion")
    , @NamedQuery(name = "Proveedor.findByAcopiador", query = "SELECT p FROM Proveedor p WHERE p.acopiador = :acopiador")
    , @NamedQuery(name = "Proveedor.findByBaja", query = "SELECT p FROM Proveedor p WHERE p.baja = :baja")
    , @NamedQuery(name = "Proveedor.findByRenapa", query = "SELECT p FROM Proveedor p WHERE p.renapa = :renapa")})
public class Proveedor implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProveedor")
    private Integer idProveedor;
    @Basic(optional = false)
    @Column(name = "razonSocial")
    private String razonSocial;
    @Column(name = "cuit")
    private BigInteger cuit;
    @Column(name = "telefono")
    private BigInteger telefono;
    @Column(name = "mail")
    private String mail;
    @Column(name = "direccion")
    private String direccion;
    @Lob
    @Column(name = "foto")
    private byte[] foto;
    @Column(name = "acopiador")
    private Boolean acopiador;
    @Column(name = "baja")
    private Integer baja;
    @Column(name = "renapa")
    private String renapa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProveedor")
    private Collection<Tambor> tamborCollection;
    @OneToMany(mappedBy = "idProveedorVenta")
    private Collection<Tambor> tamborCollection1;
    @JoinColumn(name = "idLocalidad", referencedColumnName = "idLocalidad")
    @ManyToOne
    private Localidad idLocalidad;
   
    
    public Proveedor() {
    }

    public Proveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Proveedor(Integer idProveedor, String razonSocial) {
        this.idProveedor = idProveedor;
        this.razonSocial = razonSocial;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        Integer oldIdProveedor = this.idProveedor;
        this.idProveedor = idProveedor;
        changeSupport.firePropertyChange("idProveedor", oldIdProveedor, idProveedor);
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        String oldRazonSocial = this.razonSocial;
        this.razonSocial = razonSocial;
        changeSupport.firePropertyChange("razonSocial", oldRazonSocial, razonSocial);
    }

    public BigInteger getCuit() {
        return cuit;
    }

    public void setCuit(BigInteger cuit) {
        BigInteger oldCuit = this.cuit;
        this.cuit = cuit;
        changeSupport.firePropertyChange("cuit", oldCuit, cuit);
    }

    public BigInteger getTelefono() {
        return telefono;
    }

    public void setTelefono(BigInteger telefono) {
        BigInteger oldTelefono = this.telefono;
        this.telefono = telefono;
        changeSupport.firePropertyChange("telefono", oldTelefono, telefono);
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        String oldMail = this.mail;
        this.mail = mail;
        changeSupport.firePropertyChange("mail", oldMail, mail);
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        String oldDireccion = this.direccion;
        this.direccion = direccion;
        changeSupport.firePropertyChange("direccion", oldDireccion, direccion);
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        byte[] oldFoto = this.foto;
        this.foto = foto;
        changeSupport.firePropertyChange("foto", oldFoto, foto);
    }
    public Boolean getAcopiador() {
        return acopiador;
    }

    public void setAcopiador(Boolean acopiador) {
        Boolean oldAcopiador = this.acopiador;
        this.acopiador = acopiador;
        changeSupport.firePropertyChange("acopiador", oldAcopiador, acopiador);
    }
    public Integer getBaja() {
        return baja;
    }

    public void setBaja(Integer baja) {
        Integer oldBaja = this.baja;
        this.baja = baja;
        changeSupport.firePropertyChange("baja", oldBaja, baja);
    }
    
    public String getRenapa() {
        return renapa;
    }

    public void setRenapa(String renapa) {
        String oldRenapa = this.renapa;
        this.renapa = renapa;
        changeSupport.firePropertyChange("renapa", oldRenapa, renapa);
    }

    @XmlTransient
    public Collection<Tambor> getTamborCollection() {
        return tamborCollection;
    }

    public void setTamborCollection(Collection<Tambor> tamborCollection) {
        this.tamborCollection = tamborCollection;
    }

    @XmlTransient
    public Collection<Tambor> getTamborCollection1() {
        return tamborCollection1;
    }

    public void setTamborCollection1(Collection<Tambor> tamborCollection1) {
        this.tamborCollection1 = tamborCollection1;
    }

    public Localidad getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(Localidad idLocalidad) {
        Localidad oldIdLocalidad = this.idLocalidad;
        this.idLocalidad = idLocalidad;
        changeSupport.firePropertyChange("idLocalidad", oldIdLocalidad, idLocalidad);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedor != null ? idProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.idProveedor == null && other.idProveedor != null) || (this.idProveedor != null && !this.idProveedor.equals(other.idProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return razonSocial;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
