/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segta.clases;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Quales Group
 */
@Entity
@Table(name = "tambor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tambor.findAll", query = "SELECT t FROM Tambor t")
    , @NamedQuery(name = "Tambor.findByIdTambor", query = "SELECT t FROM Tambor t WHERE t.idTambor = :idTambor")
    , @NamedQuery(name = "Tambor.findByNumero", query = "SELECT t FROM Tambor t WHERE t.numero = :numero")
    , @NamedQuery(name = "Tambor.findBySenasa", query = "SELECT t FROM Tambor t WHERE t.senasa = :senasa")
    , @NamedQuery(name = "Tambor.findByBruto", query = "SELECT t FROM Tambor t WHERE t.bruto = :bruto")
    , @NamedQuery(name = "Tambor.findByTara", query = "SELECT t FROM Tambor t WHERE t.tara = :tara")
    , @NamedQuery(name = "Tambor.findByNeto", query = "SELECT t FROM Tambor t WHERE t.neto = :neto")
    , @NamedQuery(name = "Tambor.findByEstado", query = "SELECT t FROM Tambor t WHERE t.estado = :estado")
    , @NamedQuery(name = "Tambor.findByHumedad", query = "SELECT t FROM Tambor t WHERE t.humedad = :humedad")
    , @NamedQuery(name = "Tambor.findByTrazabilidad", query = "SELECT t FROM Tambor t WHERE t.trazabilidad = :trazabilidad")
    , @NamedQuery(name = "Tambor.findByestadoDextrina", query = "SELECT t FROM Tambor t WHERE t.estadoDextrina = :estadoDextrina")
    , @NamedQuery(name = "Tambor.findByEstadoTambor", query = "SELECT t FROM Tambor t WHERE t.estadoTambor = :estadoTambor")
    , @NamedQuery(name = "Tambor.findByObservaciones", query = "SELECT t FROM Tambor t WHERE t.observaciones = :observaciones")
    , @NamedQuery(name = "Tambor.findByIdentificador", query = "SELECT t FROM Tambor t WHERE t.identificador = :identificador")
    , @NamedQuery(name = "Tambor.findBySenasanueva", query = "SELECT t FROM Tambor t WHERE t.senasanueva = :senasanueva")
    , @NamedQuery(name = "Tambor.findByRemito", query = "SELECT t FROM Tambor t WHERE t.remito = :remito")
    , @NamedQuery(name = "Tambor.findByRecepcion", query = "SELECT t FROM Tambor t WHERE t.recepcion = :recepcion")
    , @NamedQuery(name = "Tambor.findByDextrina", query = "SELECT t FROM Tambor t WHERE t.dextrina = :dextrina")})
public class Tambor implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTambor")
    private Integer idTambor;
    @Basic(optional = false)
    @Column(name = "numero")
    private int numero;
    @Column(name = "senasa")
    private String senasa;
    @Basic(optional = false)
    @Column(name = "bruto")
    private float bruto;
    @Basic(optional = false)
    @Column(name = "tara")
    private float tara;
    @Basic(optional = false)
    @Column(name = "neto")
    private float neto;
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "humedad")
    private Float humedad;
    @Column(name = "trazabilidad")
    private Boolean trazabilidad;
    @Column(name = "estadoDextrina")
    private String estadoDextrina;
    @Column(name = "estadoTambor")
    private String estadoTambor;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "identificador")
    private String identificador;
    @Column(name = "senasanueva")
    private String senasanueva;
    @Column(name = "remito")
    private String remito;
    @Column(name = "recepcion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recepcion;
    @Column(name = "dextrina")
    private Float dextrina;
    @JoinColumn(name = "idColor", referencedColumnName = "idColor")
    @ManyToOne
    private Color idColor;
    @JoinColumn(name = "idDescarga", referencedColumnName = "idDescargas")
    @ManyToOne(optional = false)
    private Descargas idDescarga;
    @JoinColumn(name = "idLote", referencedColumnName = "idLote")
    @ManyToOne
    private Lote idLote;
    @JoinColumn(name = "idPool", referencedColumnName = "idpool")
    @ManyToOne
    private Pool idPool;
    @JoinColumn(name = "idProveedor", referencedColumnName = "idProveedor")
    @ManyToOne(optional = false)
    private Proveedor idProveedor;
    @JoinColumn(name = "idProveedorVenta", referencedColumnName = "idProveedor")
    @ManyToOne
    private Proveedor idProveedorVenta;
    @JoinColumn(name = "acopiador", referencedColumnName = "idProveedor")
    @ManyToOne
    private Proveedor acopiador;
    @JoinColumn(name = "idSector", referencedColumnName = "idSector")
    @ManyToOne
    private Sector idSector;

    public Tambor() {
    }

    public Tambor(Integer idTambor) {
        this.idTambor = idTambor;
    }

    public Tambor(Integer idTambor, int numero, float bruto, float tara, float neto, String estado) {
        this.idTambor = idTambor;
        this.numero = numero;
        this.bruto = bruto;
        this.tara = tara;
        this.neto = neto;
        this.estado = estado;
    }

    public Integer getIdTambor() {
        return idTambor;
    }

    public void setIdTambor(Integer idTambor) {
        Integer oldIdTambor = this.idTambor;
        this.idTambor = idTambor;
        changeSupport.firePropertyChange("idTambor", oldIdTambor, idTambor);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        int oldNumero = this.numero;
        this.numero = numero;
        changeSupport.firePropertyChange("numero", oldNumero, numero);
    }

    public String getSenasa() {
        return senasa;
    }

    public void setSenasa(String senasa) {
        String oldSenasa = this.senasa;
        this.senasa = senasa;
        changeSupport.firePropertyChange("senasa", oldSenasa, senasa);
    }

    public float getBruto() {
        return bruto;
    }

    public void setBruto(float bruto) {
        float oldBruto = this.bruto;
        this.bruto = bruto;
        changeSupport.firePropertyChange("bruto", oldBruto, bruto);
    }

    public float getTara() {
        return tara;
    }

    public void setTara(float tara) {
        float oldTara = this.tara;
        this.tara = tara;
        changeSupport.firePropertyChange("tara", oldTara, tara);
    }

    public float getNeto() {
        return neto;
    }

    public void setNeto(float neto) {
        float oldNeto = this.neto;
        this.neto = neto;
        changeSupport.firePropertyChange("neto", oldNeto, neto);
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        String oldEstado = this.estado;
        this.estado = estado;
        changeSupport.firePropertyChange("estado", oldEstado, estado);
    }

    public Float getHumedad() {
        return humedad;
    }

    public void setHumedad(Float humedad) {
        Float oldHumedad = this.humedad;
        this.humedad = humedad;
        changeSupport.firePropertyChange("humedad", oldHumedad, humedad);
    }

    public Boolean getTrazabilidad() {
        return trazabilidad;
    }

    public void setTrazabilidad(Boolean trazabilidad) {
        Boolean oldTrazabilidad = this.trazabilidad;
        this.trazabilidad = trazabilidad;
        changeSupport.firePropertyChange("trazabilidad", oldTrazabilidad, trazabilidad);
    }

    public String getEstadoDextrina() {
        return estadoDextrina;
    }

    public void setEstadoDextrina(String estadoDextrina) {
        String oldEstadoDextrina = this.estadoDextrina;
        this.estadoDextrina = estadoDextrina;
        changeSupport.firePropertyChange("estadoDextrina", oldEstadoDextrina, estadoDextrina);
    }

    public String getEstadoTambor() {
        return estadoTambor;
    }

    public void setEstadoTambor(String estadoTambor) {
        String oldEstadoTambor = this.estadoTambor;
        this.estadoTambor = estadoTambor;
        changeSupport.firePropertyChange("estadoTambor", oldEstadoTambor, estadoTambor);
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        String oldObservaciones = this.observaciones;
        this.observaciones = observaciones;
        changeSupport.firePropertyChange("observaciones", oldObservaciones, observaciones);
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        String oldIdentificador = this.identificador;
        this.identificador = identificador;
        changeSupport.firePropertyChange("identificador", oldIdentificador, identificador);
    }

    public String getSenasanueva() {
        return senasanueva;
    }

    public void setSenasanueva(String senasanueva) {
        String oldSenasanueva = this.senasanueva;
        this.senasanueva = senasanueva;
        changeSupport.firePropertyChange("senasanueva", oldSenasanueva, senasanueva);
    }

    public String getRemito() {
        return remito;
    }

    public void setRemito(String remito) {
        String oldRemito = this.remito;
        this.remito = remito;
        changeSupport.firePropertyChange("remito", oldRemito, remito);
    }

    public Date getRecepcion() {
        return recepcion;
    }

    public void setRecepcion(Date recepcion) {
        Date oldRecepcion = this.recepcion;
        this.recepcion = recepcion;
        changeSupport.firePropertyChange("recepcion", oldRecepcion, recepcion);
    }

    public Float getDextrina() {
        return dextrina;
    }

    public void setDextrina(Float dextrina) {
        Float oldDextrina = this.dextrina;
        this.dextrina = dextrina;
        changeSupport.firePropertyChange("dextrina", oldDextrina, dextrina);
    }

    public Color getIdColor() {
        return idColor;
    }

    public void setIdColor(Color idColor) {
        Color oldIdColor = this.idColor;
        this.idColor = idColor;
        changeSupport.firePropertyChange("idColor", oldIdColor, idColor);
    }

    public Descargas getIdDescarga() {
        return idDescarga;
    }

    public void setIdDescarga(Descargas idDescarga) {
        Descargas oldIdDescarga = this.idDescarga;
        this.idDescarga = idDescarga;
        changeSupport.firePropertyChange("idDescarga", oldIdDescarga, idDescarga);
    }

    public Lote getIdLote() {
        return idLote;
    }

    public void setIdLote(Lote idLote) {
        Lote oldIdLote = this.idLote;
        this.idLote = idLote;
        changeSupport.firePropertyChange("idLote", oldIdLote, idLote);
    }

    public Pool getIdPool() {
        return idPool;
    }

    public void setIdPool(Pool idPool) {
        Pool oldIdPool = this.idPool;
        this.idPool = idPool;
        changeSupport.firePropertyChange("idPool", oldIdPool, idPool);
    }

    public Proveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedor idProveedor) {
        Proveedor oldIdProveedor = this.idProveedor;
        this.idProveedor = idProveedor;
        changeSupport.firePropertyChange("idProveedor", oldIdProveedor, idProveedor);
    }

    public Proveedor getIdProveedorVenta() {
        return idProveedorVenta;
    }

    public void setIdProveedorVenta(Proveedor idProveedorVenta) {
        Proveedor oldIdProveedorVenta = this.idProveedorVenta;
        this.idProveedorVenta = idProveedorVenta;
        changeSupport.firePropertyChange("idProveedorVenta", oldIdProveedorVenta, idProveedorVenta);
    }
    public Proveedor getAcopiador() {
        return acopiador;
    }

    public void setAcopiador(Proveedor acopiador) {
        Proveedor oldAcopiador = this.acopiador;
        this.acopiador = acopiador;
        changeSupport.firePropertyChange("idProveedorVenta", oldAcopiador, acopiador);
    }

    public Sector getIdSector() {
        return idSector;
    }

    public void setIdSector(Sector idSector) {
        Sector oldIdSector = this.idSector;
        this.idSector = idSector;
        changeSupport.firePropertyChange("idSector", oldIdSector, idSector);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTambor != null ? idTambor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tambor)) {
            return false;
        }
        Tambor other = (Tambor) object;
        if ((this.idTambor == null && other.idTambor != null) || (this.idTambor != null && !this.idTambor.equals(other.idTambor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "segta.clases.Tambor[ idTambor=" + idTambor + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
