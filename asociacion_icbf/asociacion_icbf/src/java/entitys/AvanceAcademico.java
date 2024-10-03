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
@Table(name = "avance_academico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AvanceAcademico.findAll", query = "SELECT a FROM AvanceAcademico a"),
    @NamedQuery(name = "AvanceAcademico.findByIdavanceAcademico", query = "SELECT a FROM AvanceAcademico a WHERE a.idavanceAcademico = :idavanceAcademico"),
    @NamedQuery(name = "AvanceAcademico.findByA\u00f1oEscolar", query = "SELECT a FROM AvanceAcademico a WHERE a.a\u00f1oEscolar = :a\u00f1oEscolar"),
    @NamedQuery(name = "AvanceAcademico.findByNivel", query = "SELECT a FROM AvanceAcademico a WHERE a.nivel = :nivel"),
    @NamedQuery(name = "AvanceAcademico.findByNota", query = "SELECT a FROM AvanceAcademico a WHERE a.nota = :nota"),
    @NamedQuery(name = "AvanceAcademico.findByDescripci\u00f3n", query = "SELECT a FROM AvanceAcademico a WHERE a.descripci\u00f3n = :descripci\u00f3n"),
    @NamedQuery(name = "AvanceAcademico.findByFechaEntregaNotas", query = "SELECT a FROM AvanceAcademico a WHERE a.fechaEntregaNotas = :fechaEntregaNotas")})
public class AvanceAcademico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idavance_academico")
    private Integer idavanceAcademico;
    @Size(max = 45)
    @Column(name = "a\u00f1o_escolar")
    private String añoEscolar;
    @Size(max = 45)
    @Column(name = "nivel")
    private String nivel;
    @Size(max = 45)
    @Column(name = "nota")
    private String nota;
    @Size(max = 255)
    @Column(name = "descripci\u00f3n")
    private String descripción;
    @Column(name = "fecha_entrega_notas")
    @Temporal(TemporalType.DATE)
    private Date fechaEntregaNotas;
    @JoinColumn(name = "idkid", referencedColumnName = "idkid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Kid idkid;

    public AvanceAcademico() {
    }

    public AvanceAcademico(Integer idavanceAcademico) {
        this.idavanceAcademico = idavanceAcademico;
    }

    public Integer getIdavanceAcademico() {
        return idavanceAcademico;
    }

    public void setIdavanceAcademico(Integer idavanceAcademico) {
        this.idavanceAcademico = idavanceAcademico;
    }

    public String getAñoEscolar() {
        return añoEscolar;
    }

    public void setAñoEscolar(String añoEscolar) {
        this.añoEscolar = añoEscolar;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public Date getFechaEntregaNotas() {
        return fechaEntregaNotas;
    }

    public void setFechaEntregaNotas(Date fechaEntregaNotas) {
        this.fechaEntregaNotas = fechaEntregaNotas;
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
        hash += (idavanceAcademico != null ? idavanceAcademico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AvanceAcademico)) {
            return false;
        }
        AvanceAcademico other = (AvanceAcademico) object;
        if ((this.idavanceAcademico == null && other.idavanceAcademico != null) || (this.idavanceAcademico != null && !this.idavanceAcademico.equals(other.idavanceAcademico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.AvanceAcademico[ idavanceAcademico=" + idavanceAcademico + " ]";
    }
    
}
