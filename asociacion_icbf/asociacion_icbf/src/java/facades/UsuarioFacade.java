/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facades;

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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "asociacion_icbfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Usuario login(String correo, String clave){
        List<Usuario> list = new ArrayList<>();
        Query consulta = em.createQuery("SELECT u FROM Usuario u WHERE u.correo = :correo AND u.clave = :clave"); 
        consulta.setParameter("correo", correo);
        consulta.setParameter("clave", clave);
        list = consulta.getResultList();
        if(list.isEmpty()){
            return null;
        }
        else {
        return list.get(0);
        }
        
         
    }
    
    public List<Usuario> buscarMadre(){
        List<Usuario> list = new ArrayList<>();
        Query consulta = em.createQuery("SELECT u FROM Usuario u WHERE u.idrol.idrol = 2"); 
        list = consulta.getResultList();
        if(list.isEmpty()){
            return null;
        }
        else {
        return list;
        }
    }
    
    public Usuario validarNUIP(String cedula){
         List<Usuario> list = new ArrayList<>();
        Query consulta = em.createQuery("SELECT u FROM Usuario u WHERE u.cedula =:cedula"); 
        consulta.setParameter("cedula", cedula);
        list = consulta.getResultList();
         if(list.isEmpty()){
            return null;
        }
        else {
        return list.get(0);
        }
    }
    
}
