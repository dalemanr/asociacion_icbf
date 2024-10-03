/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facades;

import entitys.Administrador;
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
public class AdministradorFacade extends AbstractFacade<Administrador> {

    @PersistenceContext(unitName = "asociacion_icbfPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministradorFacade() {
        super(Administrador.class);
    }
    
    public Administrador buscarAdminUsuario(int idusuario){
        List<Administrador> list = new ArrayList<>();
        Query consulta = em.createQuery("SELECT a FROM Administrador a WHERE a.idusuario.idusuario =:idusuario"); 
        consulta.setParameter("idusuario", idusuario);
        list = consulta.getResultList();
         if(list.isEmpty()){
            return null;
        }
        else {
        return list.get(0);
        }
    }
}
