/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facades;

import entitys.Asistencia;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author david
 */
@Stateless
public class AsistenciaFacade extends AbstractFacade<Asistencia> {

    @PersistenceContext(unitName = "asociacion_icbfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsistenciaFacade() {
        super(Asistencia.class);
    }
    
    public List<Asistencia> FindByFecha(Date fecha, int jardin){
        List<Asistencia> list = new ArrayList<>();
        Query consulta = em.createQuery("SELECT a FROM Asistencia a WHERE a.fecha = :fecha AND a.idkid.idjardin.idjardin=:jardin");
        consulta.setParameter("fecha", fecha);
        consulta.setParameter("jardin", jardin);
        list = consulta.getResultList();
        if(list.isEmpty()){
            return null;
        }
        else {
        return list;
        }
    }
    public List<Asistencia> FindByNIUP(String niup){
        List<Asistencia> list = new ArrayList<>();
        Query consulta = em.createQuery("SELECT a FROM Asistencia a WHERE a.idkid.niup = :niup");
        consulta.setParameter("niup", niup);
        list = consulta.getResultList();
        if(list.isEmpty()){
            return null;
        }
        else {
        return list;
        }
    }
    
    public List<Asistencia> InasistenciasEnfermedad(){
        List<Asistencia> list = new ArrayList<>();
        Query consulta = em.createQuery("SELECT a FROM Asistencia a WHERE a.descripción=:descripcion and a.asistencia=:asistencia");
        consulta.setParameter("descripcion", "Enfermo");
        consulta.setParameter("asistencia", "Ausente");
        list = consulta.getResultList();
        if(list.isEmpty()){
            return null;
        }
        else {
        return list;
        }
    }
    
    
    public List<Asistencia> AsistenciaSemanal(int jardin){
        LocalDate unaSemanaAtras = LocalDate.now().minusWeeks(1);
        Date fechaHaceUnaSemana = Date.from(unaSemanaAtras.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Asistencia> list = new ArrayList<>();
        TypedQuery<Asistencia> consulta = em.createQuery(
        "SELECT a FROM Asistencia a WHERE a.fecha >= :fecha AND a.idkid.idjardin.idjardin=:jardin", Asistencia.class);

        consulta.setParameter("fecha", fechaHaceUnaSemana);
        consulta.setParameter("jardin", jardin);
        list = consulta.getResultList();
        if(list.isEmpty()){
            return null;
        }
        else {
        return list;
        }
    }
}
