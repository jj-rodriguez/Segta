/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segta.clases;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Quales Group
 */
@Entity
@Table(name = "lotecontrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lotecontrato.findAll", query = "SELECT l FROM Lotecontrato l")
    , @NamedQuery(name = "Lotecontrato.findByIdloteContrato", query = "SELECT l FROM Lotecontrato l WHERE l.idloteContrato = :idloteContrato")
    , @NamedQuery(name = "Lotecontrato.findByColor", query = "SELECT l FROM Lotecontrato l WHERE l.color = :color")
    , @NamedQuery(name = "Lotecontrato.findByEstado", query = "SELECT l FROM Lotecontrato l WHERE l.estado = :estado")
    , @NamedQuery(name = "Lotecontrato.findByCantLotes", query = "SELECT l FROM Lotecontrato l WHERE l.cantLotes = :cantLotes")
    , @NamedQuery(name = "Lotecontrato.findByMaxTambores", query = "SELECT l FROM Lotecontrato l WHERE l.maxTambores = :maxTambores")
    , @NamedQuery(name = "Lotecontrato.findByFechaEntrega", query = "SELECT l FROM Lotecontrato l WHERE l.fechaEntrega = :fechaEntrega")
    , @NamedQuery(name = "Lotecontrato.findByItem", query = "SELECT l FROM Lotecontrato l WHERE l.item = :item")})
public class Lotecontrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idloteContrato")
    private Integer idloteContrato;
    @Basic(optional = false)
    @Column(name = "color")
    private String color;
    @Column(name = "estado")
    private String estado;
    @Column(name = "cantLotes")
    private Integer cantLotes;
    @Column(name = "maxTambores")
    private Integer maxTambores;
    @Column(name = "fechaEntrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    @Column(name = "item")
    private Integer item;
    @OneToMany(mappedBy = "idLoteContrato")
    private Collection<Lote> loteCollection;
    @JoinColumn(name = "idContrato", referencedColumnName = "idContrato")
    @ManyToOne(optional = false)
    private Contrato idContrato;

    public Lotecontrato() {
    }

    public Lotecontrato(Integer idloteContrato) {
        this.idloteContrato = idloteContrato;
    }

    public Lotecontrato(Integer idloteContrato, String color) {
        this.idloteContrato = idloteContrato;
        this.color = color;
    }

    public Integer getIdloteContrato() {
        return idloteContrato;
    }

    public void setIdloteContrato(Integer idloteContrato) {
        this.idloteContrato = idloteContrato;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getCantLotes() {
        return cantLotes;
    }

    public void setCantLotes(Integer cantLotes) {
        this.cantLotes = cantLotes;
    }

    public Integer getMaxTambores() {
        return maxTambores;
    }

    public void setMaxTambores(Integer maxTambores) {
        this.maxTambores = maxTambores;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    @XmlTransient
    public Collection<Lote> getLoteCollection() {
        return loteCollection;
    }

    public void setLoteCollection(Collection<Lote> loteCollection) {
        this.loteCollection = loteCollection;
    }

    public Contrato getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Contrato idContrato) {
        this.idContrato = idContrato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idloteContrato != null ? idloteContrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lotecontrato)) {
            return false;
        }
        Lotecontrato other = (Lotecontrato) object;
        if ((this.idloteContrato == null && other.idloteContrato != null) || (this.idloteContrato != null && !this.idloteContrato.equals(other.idloteContrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "segta.clases.Lotecontrato[ idloteContrato=" + idloteContrato + " ]";
    }
    
}
