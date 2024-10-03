/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auxiliares;

/**
 *
 * @author david
 */
public class auxiliarAsistencias {
    
    private Integer idKid;
    private String nombreKid;
    private String apellidoKid;
    private String asistencia;
    private String descripcion;

    public Integer getIdKid() {
        return idKid;
    }

    public void setIdKid(Integer idKid) {
        this.idKid = idKid;
    }

    public String getNombreKid() {
        return nombreKid;
    }

    public void setNombreKid(String nombreKid) {
        this.nombreKid = nombreKid;
    }

    public String getApellidoKid() {
        return apellidoKid;
    }

    public void setApellidoKid(String apellidoKid) {
        this.apellidoKid = apellidoKid;
    }

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public auxiliarAsistencias(Integer idKid, String nombreKid, String apellidoKid, String asistencia, String descripcion) {
        this.idKid = idKid;
        this.nombreKid = nombreKid;
        this.apellidoKid = apellidoKid;
        this.asistencia = asistencia;
        this.descripcion = descripcion;
    }
    
    
    
}
