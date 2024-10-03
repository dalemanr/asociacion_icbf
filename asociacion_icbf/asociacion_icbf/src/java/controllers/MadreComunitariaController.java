/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controllers;

import entitys.Jardin;
import entitys.MadreComunitaria;
import entitys.Usuario;
import facades.JardinFacade;
import facades.MadreComunitariaFacade;
import facades.UsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author david
 */
@Named(value = "madreComunitariaController")
@SessionScoped
public class MadreComunitariaController implements Serializable {

    private MadreComunitaria madreComunitaria;
    private Usuario usuario;
    private Jardin jardin;
    
    
    @EJB
    private JardinFacade jardinfacade;
    @EJB
    private UsuarioFacade usuariofacade;
    @EJB
    private MadreComunitariaFacade madreComunitariaFacade;
      
    @PostConstruct
    public void init(){
        madreComunitaria= new MadreComunitaria();
        usuario = new Usuario();
        jardin = new Jardin();
    }

    public Jardin getJardin() {
        return jardin;
    }

    public void setJardin(Jardin jardin) {
        this.jardin = jardin;
    }
    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public MadreComunitariaController() {
    }


    public MadreComunitaria getMadreComunitaria() {
        return madreComunitaria;
    }

    public void setMadreComunitaria(MadreComunitaria madreComunitaria) {
        this.madreComunitaria = madreComunitaria;
    }
    
    //Metodos CRUD
    public void register(){
        madreComunitaria.setIdjardin(jardinfacade.find(jardin.getIdjardin()));
        madreComunitaria.setIdusuario(usuariofacade.find(usuario.getIdusuario()));
        madreComunitariaFacade.create(madreComunitaria);
        madreComunitaria = new MadreComunitaria();
       
    }
    
    public void delete(MadreComunitaria madreComunitaria){
        madreComunitariaFacade.remove(madreComunitaria);
    }
    
    public MadreComunitaria preEdit(MadreComunitaria madreComunitaria){
        return this.madreComunitaria=madreComunitaria;
    }
    
    public String preEdit2(MadreComunitaria madreComunitaria){
         this.madreComunitaria=madreComunitaria;
         return "madreEdit";
    }
    
    public String edit(){
        madreComunitariaFacade.edit(madreComunitaria);
        madreComunitaria= new MadreComunitaria();
        return "list";
    }
    
    public MadreComunitaria findId(int idMadreComunitaria){
        return madreComunitariaFacade.find(idMadreComunitaria);
    }
    
    public List<MadreComunitaria> listar(){
        return madreComunitariaFacade.findAll();
    }
    
    public List<Usuario> listarMa(){
        return usuariofacade.buscarMadre();
    }
    
}
