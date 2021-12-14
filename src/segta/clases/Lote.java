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
@Table(name = "lote")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lote.findAll", query = "SELECT l FROM Lote l")
    , @NamedQuery(name = "Lote.findByIdLote", query = "SELECT l FROM Lote l WHERE l.idLote = :idLote")
    , @NamedQuery(name = "Lote.findByLote", query = "SELECT l FROM Lote l WHERE l.lote = :lote")
    , @NamedQuery(name = "Lote.findByDescripcion", query = "SELECT l FROM Lote l WHERE l.descripcion = :descripcion")
    , @NamedQuery(name = "Lote.findByDespacho", query = "SELECT l FROM Lote l WHERE l.despacho = :despacho")
    , @NamedQuery(name = "Lote.findByNumeroDespacho", query = "SELECT l FROM Lote l WHERE l.numeroDespacho = :numeroDespacho")
    , @NamedQuery(name = "Lote.findByNumeroBL", query = "SELECT l FROM Lote l WHERE l.numeroBL = :numeroBL")
    , @NamedQuery(name = "Lote.findByNumeroEmbarque", query = "SELECT l FROM Lote l WHERE l.numeroEmbarque = :numeroEmbarque")
    , @NamedQuery(name = "Lote.findByFactura", query = "SELECT l FROM Lote l WHERE l.factura = :factura")
    , @NamedQuery(name = "Lote.findByPackinglist", query = "SELECT l FROM Lote l WHERE l.packinglist = :packinglist")
    , @NamedQuery(name = "Lote.findByPuertoOrigen", query = "SELECT l FROM Lote l WHERE l.puertoOrigen = :puertoOrigen")
    , @NamedQuery(name = "Lote.findByNetoDespachado", query = "SELECT l FROM Lote l WHERE l.netoDespachado = :netoDespachado")
    , @NamedQuery(name = "Lote.findByOrdenDeCompra", query = "SELECT l FROM Lote l WHERE l.ordenDeCompra = :ordenDeCompra")
    , @NamedQuery(name = "Lote.findByConsigntario", query = "SELECT l FROM Lote l WHERE l.consigntario = :consigntario")
    , @NamedQuery(name = "Lote.findByLugarDeEntrega", query = "SELECT l FROM Lote l WHERE l.lugarDeEntrega = :lugarDeEntrega")
    , @NamedQuery(name = "Lote.findByBuque", query = "SELECT l FROM Lote l WHERE l.buque = :buque")
    , @NamedQuery(name = "Lote.findByPrescinto", query = "SELECT l FROM Lote l WHERE l.prescinto = :prescinto")
    , @NamedQuery(name = "Lote.findByBrutoDespachado", query = "SELECT l FROM Lote l WHERE l.brutoDespachado = :brutoDespachado")
    , @NamedQuery(name = "Lote.findByTaraDespachado", query = "SELECT l FROM Lote l WHERE l.taraDespachado = :taraDespa1chado")
    , @NamedQuery(name = "Lote.findByCamion", query = "SELECT l FROM Lote l WHERE l.camion = :camion")
    , @NamedQuery(name = "Lote.findByRemito", query = "SELECT l FROM Lote l WHERE l.remito = :remito")})
public class Lote implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLote")
    private Integer idLote;
    @Column(name = "lote")
    private String lote;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "despacho")
    @Temporal(TemporalType.DATE)
    private Date despacho;
    @Column(name = "numeroDespacho")
    private String numeroDespacho;
    @Column(name = "numeroBL")
    private String numeroBL;
    @Column(name = "numeroEmbarque")
    private String numeroEmbarque;
    @Column(name = "factura")
    private String factura;
    @Column(name = "packinglist")
    private String packinglist;
    @Column(name = "puertoOrigen")
    private String puertoOrigen;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "netoDespachado")
    private Float netoDespachado;
    @Column(name = "ordenDeCompra")
    private String ordenDeCompra;
    @Column(name = "consigntario")
    private String consigntario;
    @Column(name = "lugarDeEntrega")
    private String lugarDeEntrega;
    @Column(name = "buque")
    private String buque;
    @Column(name = "prescinto")
    private String prescinto;
    @Column(name = "brutoDespachado")
    private Float brutoDespachado;
    @Column(name = "taraDespachado")
    private Float taraDespachado;
    @Column(name = "remito")
    private String remito;
    @Column(name = "camion")
    private String camion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLote")
    private Collection<Analisis> analisisCollection;
    @JoinColumn(name = "idLoteContrato", referencedColumnName = "idloteContrato")
    @ManyToOne
    private Lotecontrato idLoteContrato;
    @OneToMany(mappedBy = "idLote")
    private Collection<Tambor> tamborCollection;
    @Transient
    private int cantTambores;
    @Transient
    private float netoLote;

    public Lote() {
    }

    public Lote(Integer idLote) {
        this.idLote = idLote;
    }

    public int getCantTambores() {
        return this.getTamborCollection().size();
    }

    public Float getNetoLote() {
        netoLote = (float) 0.0;
        for (Tambor t : this.tamborCollection) {
            netoLote = netoLote + t.getNeto();
        }
        return netoLote;
    }

    public Integer getIdLote() {
        return idLote;
    }

    public void setIdLote(Integer idLote) {
        Integer oldIdLote = this.idLote;
        this.idLote = idLote;
        changeSupport.firePropertyChange("idLote", oldIdLote, idLote);
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        String oldLote = this.lote;
        this.lote = lote;
        changeSupport.firePropertyChange("lote", oldLote, lote);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        String oldDescripcion = this.descripcion;
        this.descripcion = descripcion;
        changeSupport.firePropertyChange("descripcion", oldDescripcion, descripcion);
    }

    public Date getDespacho() {
        return despacho;
    }

    public void setDespacho(Date despacho) {
        Date oldDespacho = this.despacho;
        this.despacho = despacho;
        changeSupport.firePropertyChange("despacho", oldDespacho, despacho);
    }

    public String getNumeroDespacho() {
        return numeroDespacho;
    }

    public void setNumeroDespacho(String numeroDespacho) {
        String oldNumeroDespacho = this.numeroDespacho;
        this.numeroDespacho = numeroDespacho;
        changeSupport.firePropertyChange("numeroDespacho", oldNumeroDespacho, numeroDespacho);
    }

    public String getNumeroBL() {
        return numeroBL;
    }

    public void setNumeroBL(String numeroBL) {
        String oldNumeroBL = this.numeroBL;
        this.numeroBL = numeroBL;
        changeSupport.firePropertyChange("numeroBL", oldNumeroBL, numeroBL);
    }

    public String getNumeroEmbarque() {
        return numeroEmbarque;
    }

    public void setNumeroEmbarque(String numeroEmbarque) {
        String oldNumeroEmbarque = this.numeroEmbarque;
        this.numeroEmbarque = numeroEmbarque;
        changeSupport.firePropertyChange("numeroEmbarque", oldNumeroEmbarque, numeroEmbarque);
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        String oldFactura = this.factura;
        this.factura = factura;
        changeSupport.firePropertyChange("factura", oldFactura, factura);
    }

    public String getPackinglist() {
        return packinglist;
    }

    public void setPackinglist(String packinglist) {
        String oldPackinglist = this.packinglist;
        this.packinglist = packinglist;
        changeSupport.firePropertyChange("packinglist", oldPackinglist, packinglist);
    }

    public String getPuertoOrigen() {
        return puertoOrigen;
    }

    public void setPuertoOrigen(String puertoOrigen) {
        String oldPuertoOrigen = this.puertoOrigen;
        this.puertoOrigen = puertoOrigen;
        changeSupport.firePropertyChange("puertoOrigen", oldPuertoOrigen, puertoOrigen);
    }

    public Float getNetoDespachado() {
        return netoDespachado;
    }

    public void setNetoDespachado(Float netoDespachado) {
        Float oldNetoDespachado = this.netoDespachado;
        this.netoDespachado = netoDespachado;
        changeSupport.firePropertyChange("netoDespachado", oldNetoDespachado, netoDespachado);
    }

    public String getOrdenDeCompra() {
        return ordenDeCompra;
    }

    public void setOrdenDeCompra(String ordenDeCompra) {
        String oldOrdenDeCompra = this.ordenDeCompra;
        this.ordenDeCompra = ordenDeCompra;
        changeSupport.firePropertyChange("ordenDeCompra", oldOrdenDeCompra, ordenDeCompra);
    }

    public String getConsigntario() {
        return consigntario;
    }

    public void setConsigntario(String consigntario) {
        String oldConsigntario = this.consigntario;
        this.consigntario = consigntario;
        changeSupport.firePropertyChange("consigntario", oldConsigntario, consigntario);
    }

    public String getLugarDeEntrega() {
        return lugarDeEntrega;
    }

    public void setLugarDeEntrega(String lugarDeEntrega) {
        String oldLugarDeEntrega = this.lugarDeEntrega;
        this.lugarDeEntrega = lugarDeEntrega;
        changeSupport.firePropertyChange("lugarDeEntrega", oldLugarDeEntrega, lugarDeEntrega);
    }

    public String getBuque() {
        return buque;
    }

    public void setBuque(String buque) {
        String oldBuque = this.buque;
        this.buque = buque;
        changeSupport.firePropertyChange("buque", oldBuque, buque);
    }

    public String getPrescinto() {
        return prescinto;
    }

    public void setPrescinto(String prescinto) {
        String oldPrescinto = this.prescinto;
        this.prescinto = prescinto;
        changeSupport.firePropertyChange("prescinto", oldPrescinto, prescinto);
    }

    public Float getBrutoDespachado() {
        return brutoDespachado;
    }

    public void setBrutoDespachado(Float brutoDespachado) {
        Float oldBrutoDespachado = this.brutoDespachado;
        this.brutoDespachado = brutoDespachado;
        changeSupport.firePropertyChange("brutoDespachado", oldBrutoDespachado, brutoDespachado);
    }

    public Float getTaraDespachado() {
        return taraDespachado;
    }

    public void setTaraDespachado(Float taraDespachado) {
        Float oldTaraDespachado = this.taraDespachado;
        this.taraDespachado = taraDespachado;
        changeSupport.firePropertyChange("taraDespachado", oldTaraDespachado, taraDespachado);
    }

    public String getRemito() {
        return remito;
    }

    public void setRemito(String remito) {
        String oldRemito = this.remito;
        this.remito = remito;
        changeSupport.firePropertyChange("remito", oldRemito, remito);
    }

    public String getCamion() {
        return camion;
    }

    public void setCamion(String camion) {
        String oldCamion = this.camion;
        this.camion = camion;
        changeSupport.firePropertyChange("camion", oldCamion, camion);
    }

    @XmlTransient
    public Collection<Analisis> getAnalisisCollection() {
        return analisisCollection;
    }

    public void setAnalisisCollection(Collection<Analisis> analisisCollection) {
        this.analisisCollection = analisisCollection;
    }

    public Lotecontrato getIdLoteContrato() {
        return idLoteContrato;
    }

    public void setIdLoteContrato(Lotecontrato idLoteContrato) {
        Lotecontrato oldIdLoteContrato = this.idLoteContrato;
        this.idLoteContrato = idLoteContrato;
        changeSupport.firePropertyChange("idLoteContrato", oldIdLoteContrato, idLoteContrato);
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
        hash += (idLote != null ? idLote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lote)) {
            return false;
        }
        Lote other = (Lote) object;
        if ((this.idLote == null && other.idLote != null) || (this.idLote != null && !this.idLote.equals(other.idLote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return lote;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
