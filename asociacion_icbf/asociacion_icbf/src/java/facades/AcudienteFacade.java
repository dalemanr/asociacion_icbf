/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facades;

import entitys.Acudiente;
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
public class AcudienteFacade extends AbstractFacade<Acudiente> {

    @PersistenceContext(unitName = "asociacion_icbfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AcudienteFacade() {
        super(Acudiente.class);
    }
    
    public Acudiente buscarAcudiente(int usuario){
        List<Acudiente> list = new ArrayList<>();
        Query consulta = em.createQuery("SELECT a FROM Acudiente a WHERE a.idusuario.idusuario = :usuario"); 
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
