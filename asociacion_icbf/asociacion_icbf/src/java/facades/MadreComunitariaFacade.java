/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facades;

import entitys.Kid;
import entitys.MadreComunitaria;
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
public class MadreComunitariaFacade extends AbstractFacade<MadreComunitaria> {

    @PersistenceContext(unitName = "asociacion_icbfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MadreComunitariaFacade() {
        super(MadreComunitaria.class);
    }
    
    public MadreComunitaria buscarMadre(int usuario){
        List<MadreComunitaria> list = new ArrayList<>();
        Query consulta = em.createQuery("SELECT m FROM MadreComunitaria m WHERE m.idusuario.idusuario = :usuario"); 
        consulta.setParameter("usuario", usuario);
        list = consulta.getResultList();
        
        if(list.isEmpty()){
            return null;
        }
        else {
        return list.get(0);
        }
    }
    
    
    
    
}
