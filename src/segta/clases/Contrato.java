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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Quales Group
 */
@Entity
@Table(name = "contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c")
    , @NamedQuery(name = "Contrato.findByIdContrato", query = "SELECT c FROM Contrato c WHERE c.idContrato = :idContrato")
    , @NamedQuery(name = "Contrato.findByNumero", query = "SELECT c FROM Contrato c WHERE c.numero = :numero")
    , @NamedQuery(name = "Contrato.findByMercado", query = "SELECT c FROM Contrato c WHERE c.mercado = :mercado")
    , @NamedQuery(name = "Contrato.findByHomogeneizada", query = "SELECT c FROM Contrato c WHERE c.homogeneizada = :homogeneizada")
    , @NamedQuery(name = "Contrato.findByTamborNuevo", query = "SELECT c FROM Contrato c WHERE c.tamborNuevo = :tamborNuevo")})
public class Contrato implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idContrato")
    private Integer idContrato;
    @Column(name = "numero")
    private String numero;
    @Column(name = "mercado")
    private String mercado;
    @Column(name = "homogeneizada")
    private Boolean homogeneizada;
    @Column(name = "tamborNuevo")
    private Boolean tamborNuevo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idContrato")
    private Collection<Lotecontrato> lotecontratoCollection;
    @Transient
    private String determinaciones;
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    @ManyToOne
    private Cliente idCliente;
    @OneToMany(mappedBy = "idContrato")
    private Collection<Contratodeterminacion> contratodeterminacionCollection;

    public Contrato() {
    }

    public Contrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        Integer oldIdContrato = this.idContrato;
        this.idContrato = idContrato;
        changeSupport.firePropertyChange("idContrato", oldIdContrato, idContrato);
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        String oldNumero = this.numero;
        this.numero = numero;
        changeSupport.firePropertyChange("numero", oldNumero, numero);
    }

    public String getMercado() {
        return mercado;
    }

    public void setMercado(String mercado) {
        String oldMercado = this.mercado;
        this.mercado = mercado;
        changeSupport.firePropertyChange("mercado", oldMercado, mercado);
    }

    public Boolean getHomogeneizada() {
        return homogeneizada;
    }

    public void setHomogeneizada(Boolean homogeneizada) {
        Boolean oldHomogeneizada = this.homogeneizada;
        this.homogeneizada = homogeneizada;
        changeSupport.firePropertyChange("homogeneizada", oldHomogeneizada, homogeneizada);
    }

    public Boolean getTamborNuevo() {
        return tamborNuevo;
    }

    public void setTamborNuevo(Boolean tamborNuevo) {
        Boolean oldTamborNuevo = this.tamborNuevo;
        this.tamborNuevo = tamborNuevo;
        changeSupport.firePropertyChange("tamborNuevo", oldTamborNuevo, tamborNuevo);
    }

    public String getDeterminaciones() {
        determinaciones = "";
        this.contratodeterminacionCollection.forEach((d) -> {
            String nomdet = d.getIdDeterminacion().getNombre();
            determinaciones = determinaciones + nomdet + ", ";

        });
        return determinaciones;
    }

    @XmlTransient
    public Collection<Lotecontrato> getLotecontratoCollection() {
        return lotecontratoCollection;
    }

    public void setLotecontratoCollection(Collection<Lotecontrato> lotecontratoCollection) {
        this.lotecontratoCollection = lotecontratoCollection;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        Cliente oldIdCliente = this.idCliente;
        this.idCliente = idCliente;
        changeSupport.firePropertyChange("idCliente", oldIdCliente, idCliente);
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
        hash += (idContrato != null ? idContrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contrato)) {
            return false;
        }
        Contrato other = (Contrato) object;
        if ((this.idContrato == null && other.idContrato != null) || (this.idContrato != null && !this.idContrato.equals(other.idContrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "segta.clases.Contrato[ idContrato=" + idContrato + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
