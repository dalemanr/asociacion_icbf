/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entitys;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author david
 */
@Entity
@Table(name = "asistencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asistencia.findAll", query = "SELECT a FROM Asistencia a"),
    @NamedQuery(name = "Asistencia.findByIdasistencia", query = "SELECT a FROM Asistencia a WHERE a.idasistencia = :idasistencia"),
    @NamedQuery(name = "Asistencia.findByAsistencia", query = "SELECT a FROM Asistencia a WHERE a.asistencia = :asistencia"),
    @NamedQuery(name = "Asistencia.findByFecha", query = "SELECT a FROM Asistencia a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "Asistencia.findByDescripci\u00f3n", query = "SELECT a FROM Asistencia a WHERE a.descripci\u00f3n = :descripci\u00f3n")})
public class Asistencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idasistencia")
    private Integer idasistencia;
    @Size(max = 45)
    @Column(name = "asistencia")
    private String asistencia;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Size(max = 45)
    @Column(name = "descripci\u00f3n")
    private String descripción;
    @JoinColumn(name = "idkid", referencedColumnName = "idkid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Kid idkid;

    public Asistencia() {
    }
    
    @PrePersist
    protected void onCreate() {
        fecha = new Date(); // Establecer la fecha actual antes de la persistencia
    }

    public Asistencia(Integer idasistencia) {
        this.idasistencia = idasistencia;
    }

    public Integer getIdasistencia() {
        return idasistencia;
    }

    public void setIdasistencia(Integer idasistencia) {
        this.idasistencia = idasistencia;
    }

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public Kid getIdkid() {
        return idkid;
    }

    public void setIdkid(Kid idkid) {
        this.idkid = idkid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idasistencia != null ? idasistencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asistencia)) {
            return false;
        }
        Asistencia other = (Asistencia) object;
        if ((this.idasistencia == null && other.idasistencia != null) || (this.idasistencia != null && !this.idasistencia.equals(other.idasistencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.Asistencia[ idasistencia=" + idasistencia + " ]";
    }
    
}
