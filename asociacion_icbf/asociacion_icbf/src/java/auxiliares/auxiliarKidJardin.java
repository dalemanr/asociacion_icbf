/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auxiliares;



/**
 *
 * @author david
 */

public class auxiliarKidJardin {
   
   
    
    
    private Integer idKid;
    private String nombreKid;
    private String apellidoKid;
    private String niup;
    private String nombreJardin;

    public auxiliarKidJardin() {
    }
    
    

    public String getNiup() {
        return niup;
    }

    public void setNiup(String niup) {
        this.niup = niup;
    }
    
    

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

  

        public String getNombreJardin() {
            return nombreJardin;
        }

        public void setNombreJardin(String nombreJardin) {
            this.nombreJardin = nombreJardin;
        }

   

    public auxiliarKidJardin (Integer idKid, String nombreKid, String apellidoKid, String niup, String nombreJardin) {
        this.idKid = idKid;
        this.niup = niup;
        this.nombreKid = nombreKid;
        this.apellidoKid = apellidoKid;  
        this.nombreJardin = nombreJardin;
    }
    
}

    

