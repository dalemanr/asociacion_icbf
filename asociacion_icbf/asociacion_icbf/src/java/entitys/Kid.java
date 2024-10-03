/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entitys;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author david
 */
@Entity
@Table(name = "kids")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kid.findAll", query = "SELECT k FROM Kid k"),
    @NamedQuery(name = "Kid.findByIdkid", query = "SELECT k FROM Kid k WHERE k.idkid = :idkid"),
    @NamedQuery(name = "Kid.findByNiup", query = "SELECT k FROM Kid k WHERE k.niup = :niup"),
    @NamedQuery(name = "Kid.findByFoto", query = "SELECT k FROM Kid k WHERE k.foto = :foto"),
    @NamedQuery(name = "Kid.findByNombre", query = "SELECT k FROM Kid k WHERE k.nombre = :nombre"),
    @NamedQuery(name = "Kid.findByApellido", query = "SELECT k FROM Kid k WHERE k.apellido = :apellido"),
    @NamedQuery(name = "Kid.findByFechaNacimiento", query = "SELECT k FROM Kid k WHERE k.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Kid.findBySangre", query = "SELECT k FROM Kid k WHERE k.sangre = :sangre"),
    @NamedQuery(name = "Kid.findByCiudadNacimiento", query = "SELECT k FROM Kid k WHERE k.ciudadNacimiento = :ciudadNacimiento"),
    @NamedQuery(name = "Kid.findByEps", query = "SELECT k FROM Kid k WHERE k.eps = :eps")})
public class Kid implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idkid")
    private Integer idkid;
    @Size(max = 50)
    @Column(name = "niup")
    private String niup;
    @Size(max = 255)
    @Column(name = "foto")
    private String foto;
    @Size(max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 255)
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Size(max = 45)
    @Column(name = "sangre")
    private String sangre;
    @Size(max = 45)
    @Column(name = "ciudad_nacimiento")
    private String ciudadNacimiento;
    @Size(max = 45)
    @Column(name = "eps")
    private String eps;
    @OneToMany(mappedBy = "idkid", fetch = FetchType.LAZY)
    private List<Asistencia> asistenciaList;
    @OneToMany(mappedBy = "idkid", fetch = FetchType.LAZY)
    private List<AvanceAcademico> avanceAcademicoList;
    @JoinColumn(name = "idjardin", referencedColumnName = "idjardin")
    @ManyToOne(fetch = FetchType.LAZY)
    private Jardin idjardin;
    @JoinColumn(name = "idacudiente", referencedColumnName = "idacudiente")
    @ManyToOne(fetch = FetchType.LAZY)
    private Acudiente idacudiente;

    public Kid() {
    }

    public Kid(Integer idkid) {
        this.idkid = idkid;
    }

    public Integer getIdkid() {
        return idkid;
    }

    public void setIdkid(Integer idkid) {
        this.idkid = idkid;
    }

    public String getNiup() {
        return niup;
    }

    public void setNiup(String niup) {
        this.niup = niup;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSangre() {
        return sangre;
    }

    public void setSangre(String sangre) {
        this.sangre = sangre;
    }

    public String getCiudadNacimiento() {
        return ciudadNacimiento;
    }

    public void setCiudadNacimiento(String ciudadNacimiento) {
        this.ciudadNacimiento = ciudadNacimiento;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    @XmlTransient
    public List<Asistencia> getAsistenciaList() {
        return asistenciaList;
    }

    public void setAsistenciaList(List<Asistencia> asistenciaList) {
        this.asistenciaList = asistenciaList;
    }

    @XmlTransient
    public List<AvanceAcademico> getAvanceAcademicoList() {
        return avanceAcademicoList;
    }

    public void setAvanceAcademicoList(List<AvanceAcademico> avanceAcademicoList) {
        this.avanceAcademicoList = avanceAcademicoList;
    }

    public Jardin getIdjardin() {
        return idjardin;
    }

    public void setIdjardin(Jardin idjardin) {
        this.idjardin = idjardin;
    }

    public Acudiente getIdacudiente() {
        return idacudiente;
    }

    public void setIdacudiente(Acudiente idacudiente) {
        this.idacudiente = idacudiente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idkid != null ? idkid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kid)) {
            return false;
        }
        Kid other = (Kid) object;
        if ((this.idkid == null && other.idkid != null) || (this.idkid != null && !this.idkid.equals(other.idkid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.Kid[ idkid=" + idkid + " ]";
    }
    
}
