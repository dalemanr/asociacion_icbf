/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facades;

import entitys.AvanceAcademico;
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
public class AvanceAcademicoFacade extends AbstractFacade<AvanceAcademico> {

    @PersistenceContext(unitName = "asociacion_icbfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AvanceAcademicoFacade() {
        super(AvanceAcademico.class);
    }
    
    public List<AvanceAcademico> FindByNIUP(String niup){
        List<AvanceAcademico> list = new ArrayList<>();
        Query consulta = em.createQuery("SELECT a FROM AvanceAcademico a WHERE a.idkid.niup = :niup");
        consulta.setParameter("niup", niup);
        list = consulta.getResultList();
        if(list.isEmpty()){
            return null;
        }
        else {
        return list;
        }
    }
    
}
