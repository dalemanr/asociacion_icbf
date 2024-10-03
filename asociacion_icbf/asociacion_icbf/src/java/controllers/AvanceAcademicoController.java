/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controllers;

import entitys.Acudiente;
import entitys.AvanceAcademico;
import entitys.Jardin;
import entitys.Kid;
import entitys.MadreComunitaria;
import entitys.Usuario;
import facades.AcudienteFacade;
import facades.AvanceAcademicoFacade;
import facades.JardinFacade;
import facades.KidFacade;
import facades.MadreComunitariaFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author david
 */
@Named(value = "avanceAcademicoController")
@SessionScoped
public class AvanceAcademicoController implements Serializable {

  FacesContext context = FacesContext.getCurrentInstance();
   UsuarioController usuarioController = (UsuarioController) context.getApplication().evaluateExpressionGet(context, "#{usuarioController}", UsuarioController.class);

   private AvanceAcademico avanceAcademico;
   private Jardin jardin;
   private Kid kid;
   private MadreComunitaria madreComunitaria;
   private MadreComunitaria madre1;
   private Usuario usuario;
   private Acudiente acudiente;
   private String variable;
   private String fechaEntrega;
   private List<AvanceAcademico> avanceAcademicosEncontradas;
   private String descripcion;
   private String Anio;
   
   
    @EJB
    private AcudienteFacade acudienteFacade;
    @EJB
    private AvanceAcademicoFacade avanceAcademicoFacade;
    @EJB
    private JardinFacade jardinFacade;
    @EJB
    private KidFacade kidFacade;
    @EJB
    private MadreComunitariaFacade madreComunitariaFacade;
    
    @PostConstruct
    public void init(){
        avanceAcademico= new AvanceAcademico();
        jardin = new Jardin();
        kid = new Kid();
        madreComunitaria = new MadreComunitaria();
    }

    public Acudiente getAcudiente() {
        return acudiente;
    }

    public void setAcudiente(Acudiente acudiente) {
        this.acudiente = acudiente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
      

    public MadreComunitaria getMadre1() {
        return madre1;
    }

    public void setMadre1(MadreComunitaria madre1) {
        this.madre1 = madre1;
    }

   public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
    
    

    public MadreComunitaria getMadreComunitaria() {
        return madreComunitaria;
    }

    public void setMadreComunitaria(MadreComunitaria madreComunitaria) {
        this.madreComunitaria = madreComunitaria;
    }
    
    public Kid getKid() {
        return kid;
    }

    public void setKid(Kid kid) {
        this.kid = kid;
    }
    
    
    public Jardin getJardin() {
        return jardin;
    }

    public void setJardin(Jardin jardin) {
        this.jardin = jardin;
    } 
    
    public AvanceAcademicoController() {
    }
    
    public AvanceAcademico getAvanceAcademico(){
        return avanceAcademico;
    }
    
    public void setAvanceAcademico(AvanceAcademico avanceAcademico){
        this.avanceAcademico = avanceAcademico;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public List<AvanceAcademico> getAvanceAcademicosEncontradas() {
        return avanceAcademicosEncontradas;
    }

    public void setAvanceAcademicosEncontradas(List<AvanceAcademico> avanceAcademicosEncontradas) {
        this.avanceAcademicosEncontradas = avanceAcademicosEncontradas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAnio() {
        return Anio;
    }

    public void setAnio(String Anio) {
        this.Anio = Anio;
    }
    
    
    
    
    
    //Metodos CRUD
    public String register(){
        avanceAcademico.setIdkid(kidFacade.find(kid.getIdkid()));
        avanceAcademico.getAñoEscolar();
        avanceAcademico.getDescripción();
        avanceAcademicoFacade.create(avanceAcademico);
        avanceAcademico=new AvanceAcademico();
        
    
        return "list";
    }
    
    
    
    public void delete(AvanceAcademico avanceAcademico){
        avanceAcademicoFacade.remove(avanceAcademico);
    }
    
    public String preEdit(AvanceAcademico avanceAcademico){
        this.avanceAcademico=avanceAcademico;
        return "edit";
    }
    
    public String edit(){
        avanceAcademico.setIdkid(kidFacade.find(kid.getIdkid()));
        avanceAcademicoFacade.edit(avanceAcademico);
        avanceAcademico=new AvanceAcademico();
        return "list";
    }
    
    public AvanceAcademico findId(int idAvanceAcademico){
        return avanceAcademicoFacade.find(idAvanceAcademico);
    }
    
    public List<AvanceAcademico> listar(){
        return avanceAcademicoFacade.findAll();
    }
    
    public List<Kid> listarKid(){
        madre1=null;
        madre1 = madreComunitariaFacade.buscarMadre(usuarioController.UsuarioLogeado().getIdusuario());
        return kidFacade.buscarKid(jardinFacade.buscarJardin(madre1.getIdjardin().getIdjardin()).getIdjardin());
    }
    
   
    public AvanceAcademico buscarPorFNUIP(){
       Acudiente acudiente1 = acudienteFacade.buscarAcudiente(usuarioController.UsuarioLogeado().getIdusuario());
       Kid kid1 = kidFacade.buscarKidAcudiente(acudiente1.getIdacudiente());
       avanceAcademicosEncontradas = avanceAcademicoFacade.FindByNIUP(kid1.getNiup());
       return null;
    }
    
    
}
