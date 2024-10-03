/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entitys;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author david
 */
@Entity
@Table(name = "madres_comunitarias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MadreComunitaria.findAll", query = "SELECT m FROM MadreComunitaria m"),
    @NamedQuery(name = "MadreComunitaria.findByIdmadrecomunitaria", query = "SELECT m FROM MadreComunitaria m WHERE m.idmadrecomunitaria = :idmadrecomunitaria")})
public class MadreComunitaria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmadrecomunitaria")
    private Integer idmadrecomunitaria;
    @JoinColumn(name = "idjardin", referencedColumnName = "idjardin")
    @ManyToOne(fetch = FetchType.LAZY)
    private Jardin idjardin;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario idusuario;

    public MadreComunitaria() {
    }

    public MadreComunitaria(Integer idmadrecomunitaria) {
        this.idmadrecomunitaria = idmadrecomunitaria;
    }

    public Integer getIdmadrecomunitaria() {
        return idmadrecomunitaria;
    }

    public void setIdmadrecomunitaria(Integer idmadrecomunitaria) {
        this.idmadrecomunitaria = idmadrecomunitaria;
    }

    public Jardin getIdjardin() {
        return idjardin;
    }

    public void setIdjardin(Jardin idjardin) {
        this.idjardin = idjardin;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmadrecomunitaria != null ? idmadrecomunitaria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MadreComunitaria)) {
            return false;
        }
        MadreComunitaria other = (MadreComunitaria) object;
        if ((this.idmadrecomunitaria == null && other.idmadrecomunitaria != null) || (this.idmadrecomunitaria != null && !this.idmadrecomunitaria.equals(other.idmadrecomunitaria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.MadreComunitaria[ idmadrecomunitaria=" + idmadrecomunitaria + " ]";
    }
    
}
