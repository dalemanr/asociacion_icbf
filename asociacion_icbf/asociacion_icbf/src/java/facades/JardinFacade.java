/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facades;

import entitys.Jardin;
import entitys.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author david
 */
@Stateless
public class JardinFacade extends AbstractFacade<Jardin> {

    @PersistenceContext(unitName = "asociacion_icbfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JardinFacade() {
        super(Jardin.class);
    }
    
   public Jardin buscarJardin(int madre){
        List<Jardin> list = new ArrayList<>();
        Query consulta = em.createQuery("SELECT j FROM Jardin j WHERE j.idjardin =:madre"); 
        consulta.setParameter("madre", madre);
        list = consulta.getResultList();
         if(list.isEmpty()){
            return null;
        }
        else {
        return list.get(0);
        }
    }
   
   public Jardin validarJardin(String jardin){
         List<Jardin> list = new ArrayList<>();
        Query consulta = em.createQuery("SELECT j FROM Jardin j WHERE j.nombre =:jardin"); 
        consulta.setParameter("jardin", jardin);
        list = consulta.getResultList();
         if(list.isEmpty()){
            return null;
        }
        else {
        return list.get(0);
        }
    }
   
   
    public List<Jardin> JardinesDes(){
        List<Jardin> list = new ArrayList<>();
        Query consulta = em.createQuery("SELECT j FROM Jardin j WHERE j.estado=:estado"); 
        consulta.setParameter("estado", "Denegado");
        list = consulta.getResultList();
         if(list.isEmpty()){
            return null;
        }
        else {
        return list;
        }
    }
}
