/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entitys;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author david
 */
@Entity
@Table(name = "acudientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Acudiente.findAll", query = "SELECT a FROM Acudiente a"),
    @NamedQuery(name = "Acudiente.findByIdacudiente", query = "SELECT a FROM Acudiente a WHERE a.idacudiente = :idacudiente")})
public class Acudiente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idacudiente")
    private Integer idacudiente;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario idusuario;
    @OneToMany(mappedBy = "idacudiente", fetch = FetchType.LAZY)
    private List<Kid> kidList;

    public Acudiente() {
    }

    public Acudiente(Integer idacudiente) {
        this.idacudiente = idacudiente;
    }

    public Integer getIdacudiente() {
        return idacudiente;
    }

    public void setIdacudiente(Integer idacudiente) {
        this.idacudiente = idacudiente;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @XmlTransient
    public List<Kid> getKidList() {
        return kidList;
    }

    public void setKidList(List<Kid> kidList) {
        this.kidList = kidList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idacudiente != null ? idacudiente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acudiente)) {
            return false;
        }
        Acudiente other = (Acudiente) object;
        if ((this.idacudiente == null && other.idacudiente != null) || (this.idacudiente != null && !this.idacudiente.equals(other.idacudiente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.Acudiente[ idacudiente=" + idacudiente + " ]";
    }
    
}
