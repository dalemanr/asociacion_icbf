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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author david
 */
@Entity
@Table(name = "jardines")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jardin.findAll", query = "SELECT j FROM Jardin j"),
    @NamedQuery(name = "Jardin.findByIdjardin", query = "SELECT j FROM Jardin j WHERE j.idjardin = :idjardin"),
    @NamedQuery(name = "Jardin.findByNombre", query = "SELECT j FROM Jardin j WHERE j.nombre = :nombre"),
    @NamedQuery(name = "Jardin.findByEstado", query = "SELECT j FROM Jardin j WHERE j.estado = :estado")})
public class Jardin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idjardin")
    private Integer idjardin;
    @Size(max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 255)
    @Column(name = "estado")
    private String estado;
    @OneToMany(mappedBy = "idjardin", fetch = FetchType.LAZY)
    private List<MadreComunitaria> madreComunitariaList;
    @OneToMany(mappedBy = "idjardin", fetch = FetchType.LAZY)
    private List<Kid> kidList;

    public Jardin() {
    }

    public Jardin(Integer idjardin) {
        this.idjardin = idjardin;
    }

    public Integer getIdjardin() {
        return idjardin;
    }

    public void setIdjardin(Integer idjardin) {
        this.idjardin = idjardin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<MadreComunitaria> getMadreComunitariaList() {
        return madreComunitariaList;
    }

    public void setMadreComunitariaList(List<MadreComunitaria> madreComunitariaList) {
        this.madreComunitariaList = madreComunitariaList;
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
        hash += (idjardin != null ? idjardin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jardin)) {
            return false;
        }
        Jardin other = (Jardin) object;
        if ((this.idjardin == null && other.idjardin != null) || (this.idjardin != null && !this.idjardin.equals(other.idjardin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.Jardin[ idjardin=" + idjardin + " ]";
    }
    
}
