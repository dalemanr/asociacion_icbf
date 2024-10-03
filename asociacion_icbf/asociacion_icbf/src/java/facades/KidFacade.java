/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facades;

import entitys.Kid;
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
public class KidFacade extends AbstractFacade<Kid> {

    @PersistenceContext(unitName = "asociacion_icbfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KidFacade() {
        super(Kid.class);
    }
    
    public List<Kid> buscarKid(int jardin){
        List<Kid> list = new ArrayList<>();
        Query consulta = em.createQuery("SELECT k FROM Kid k WHERE k.idjardin.idjardin =:jardin"); 
        consulta.setParameter("jardin", jardin);
        list = consulta.getResultList();
         if(list.isEmpty()){
            return null;
        }
        else {
        return list;
        }
    }
    
    public Kid buscarKidAcudiente(int acudiente){
        List<Kid> list = new ArrayList<>();
        Query consulta = em.createQuery("SELECT k FROM Kid k WHERE k.idacudiente.idacudiente =:acudiente"); 
        consulta.setParameter("acudiente", acudiente);
        list = consulta.getResultList();
         if(list.isEmpty()){
            return null;
        }
        else {
        return list.get(0);
        }
    }
    
    public List<Kid> cumpleKid(){
        Date date = new Date();
        List<Kid> list = new ArrayList<>();
        Query consulta = em.createQuery("SELECT k FROM Kid k " +
                        "WHERE FUNCTION('MONTH', k.fechaNacimiento) = FUNCTION('MONTH', :date) " +
                        "AND FUNCTION('DAY', k.fechaNacimiento) = FUNCTION('DAY', :date)"); 
        consulta.setParameter("date", date);
        list = consulta.getResultList();
         if(list.isEmpty()){
            return null;
        }
        else {
        return list;
        }
    }
    
    public List<Kid> kidViejo(){
        
        List<Kid> list = new ArrayList<>();
        Query consulta = em.createQuery("SELECT k FROM Kid k WHERE (EXTRACT(YEAR FROM CURRENT_DATE) - EXTRACT(YEAR FROM k.fechaNacimiento)) >= 5"); 
        
        list = consulta.getResultList();
         if(list.isEmpty()){
            return null;
        }
        else {
        return list;
        }
    }
    public List<Kid> kidsJardines(){
        
        List<Kid> list = new ArrayList<>();
        Query consulta = em.createQuery("SELECT COUNT(k), j.nombre FROM Kid k JOIN k.idjardin j GROUP BY j.nombre");
        
        list = consulta.getResultList();
         if(list.isEmpty()){
            return null;
        }
        else {
        return list;
        }
    }
    
    public Kid validarNUIP(String niup){
         List<Kid> list = new ArrayList<>();
        Query consulta = em.createQuery("SELECT k FROM Kid k WHERE k.niup =:niup"); 
        consulta.setParameter("niup", niup);
        list = consulta.getResultList();
         if(list.isEmpty()){
            return null;
        }
        else {
        return list.get(0);
        }
    }
    
    public List<Kid> kidJardin() {
    List<Kid> list = new ArrayList<>();
    Query consulta = em.createQuery("SELECT k, j FROM Kid k JOIN k.idjardin j GROUP BY j.nombre"); 
    list = consulta.getResultList();
    if(list.isEmpty()) {
        return null;
    } 
    else {
        return list;
        }
    }
    
    public List<Object[]> kidJardin2() {
    List<Object[]> list = new ArrayList<>();
    Query consulta = em.createQuery("SELECT COUNT(k), j.nombre FROM Kid k JOIN k.idjardin j GROUP BY j.nombre");
    list = consulta.getResultList();
    
    if(list.isEmpty()) {
        return null;
    } 
    else {
        return list;
        }
    }
    
   

}
