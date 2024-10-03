/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controllers;

import entitys.Acudiente;
import entitys.Administrador;
import entitys.Rol;
import entitys.Usuario;
import facades.AcudienteFacade;
import facades.AdministradorFacade;
import facades.RolFacade;
import facades.UsuarioFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import newMethods.Mailer;

/**
 *
 * @author david
 */
@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

   private Usuario usuario;
   private Rol rol;
   private Usuario usuarioLogeado;
   private Administrador administrador;
   private Acudiente acudiente;
   
   
   private String nombre;
   private String rutaReal;
   private Part archivo;
   
   
    @EJB
    private AcudienteFacade acudienteFacade;
    @EJB
    private AdministradorFacade administradorFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private RolFacade rolFacade;
    
    @PostConstruct
    public void init(){
        usuario= new Usuario();
        rol = new Rol();
        usuarioLogeado = UsuarioLogeado();
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public Acudiente getAcudiente() {
        return acudiente;
    }

    public void setAcudiente(Acudiente acudiente) {
        this.acudiente = acudiente;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRutaReal() {
        return rutaReal;
    }

    public void setRutaReal(String rutaReal) {
        this.rutaReal = rutaReal;
    }

    public Part getArchivo() {
        return archivo;
    }

    public void setArchivo(Part archivo) {
        this.archivo = archivo;
    }
    
    

    public Usuario getUsuarioLogeado() {
        return usuarioLogeado;
    }

    public void setUsuarioLogeado(Usuario usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }
    
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    } 
    
    public UsuarioController() {
    }
    
    public Usuario getUsuario(){
        return usuario;
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    
    //Metodos CRUD
    public String register() throws UnsupportedEncodingException{
        if(usuarioFacade.validarNUIP(usuario.getCedula())==null){
        usuario.setIdrol(rolFacade.find(rol.getIdrol()));
        usuario.setFoto(subir(getArchivo()));
        usuarioFacade.create(usuario);
        enviarCorreo(usuario.getCorreo(),"Registro de usuario exitoso");
        usuario=new Usuario();
        rol = new Rol();
        return "Usuarios";}
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puede registrar al usuario porque ya esta registrado en el sistema"));
            return null;
        }
    }
    
    public void delete(Usuario usuario){
        
        if (usuario.getIdrol().getIdrol()==1){
            administrador=administradorFacade.buscarAdminUsuario(usuario.getIdusuario());
            administradorFacade.remove(administrador);
            usuarioFacade.remove(usuario);
        }
        else if(usuario.getIdrol().getIdrol()==3){
            acudiente=acudienteFacade.buscarAcudiente(usuario.getIdusuario());
            acudienteFacade.remove(acudiente);
            usuarioFacade.remove(usuario);
        }
        else {
            usuarioFacade.remove(usuario);
        }   
    }
    
    public String preEdit(Usuario usuario){
        this.rol = usuario.getIdrol();
        this.usuario=usuario;
        return "usuarioEdit";
    }
    
    public String edit(){
        usuario.setIdrol(rolFacade.find(rol.getIdrol()));
        usuario.setFoto(subir(getArchivo()));
        usuarioFacade.edit(usuario);
        usuario=new Usuario();
        return "usuarios";
    }
    
    public Usuario findId(int idUsuario){
        return usuarioFacade.find(idUsuario);
    }
    
    public List<Usuario> listar(){
        return usuarioFacade.findAll();
    }
    public List<Rol> listarRol(){
        return rolFacade.findAll();
    }
    
    public String subir(Part archivo){
        String ruta = FacesContext.getCurrentInstance().getExternalContext().getRealPath("archivos");
        ruta=ruta.substring(0, ruta.indexOf("\\build"));
        ruta = ruta + "\\web\\archivos\\";
        try{
                this.nombre = archivo.getSubmittedFileName();
                this.rutaReal = "/archivos/" + nombre;
                ruta = ruta + nombre; 
                FileOutputStream out;
            try (InputStream in = archivo.getInputStream()) {
                byte[] datos = new byte[in.available()];//crear el array determinando el tamaño del archivo
                in.read(datos);//lectuara de los datos del archivo
                File archivoServer = new File(ruta);// java.io.file
                out = new FileOutputStream(archivoServer);
                out.write(datos);
               // ruta=ruta.replace("\\", "\\\\");
                //ciudadFacade.cargaMasiva(ruta, "documentos");
                 in.close();
            } //crear el array determinando el tamñano del archivo
              out.close();
               return this.rutaReal;
              
                
        }catch(Exception e){
              System.out.println("Error: "+ e.getMessage());
              return"";
        }
    }
    
    public Usuario UsuarioLogeado() {
        return (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UsuarioLogeado");
    }
    
    public String login(){
        Usuario usuarioLogeado=null;
        String claveUs = this.usuario.getClave();

        
        usuarioLogeado=usuarioFacade.login(this.usuario.getCorreo(), claveUs);
        if(usuarioLogeado!=null){
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("UsuarioLogeado", usuarioLogeado);
            if(usuarioLogeado.getIdrol().getIdrol().equals(1)){return"/admin/listas/usuarios";}//admin
            if(usuarioLogeado.getIdrol().getIdrol().equals(2)){return"/asistencia/verasistencia";}//madre
            if(usuarioLogeado.getIdrol().getIdrol().equals(3)){return"/acudiente/notasHijo";}//acudiente
            else{return "";}
        }
        else {
            return "personas/list";
        }
    }
    
    
    public void verificarSesisonAdmin(){
        try{
            Usuario us=UsuarioLogeado();
            if(us==null || us.getIdrol().getIdrol()!= 1){
                FacesContext.getCurrentInstance().getExternalContext().redirect("../../permisosAdmin.xhtml");
            }
            else{
            }
        }
        catch(Exception e){}
        
    }
    
    
    public void verificarSesisonMama(){
        try{
            Usuario us=UsuarioLogeado();
            if(us==null || us.getIdrol().getIdrol()!= 2){
                FacesContext.getCurrentInstance().getExternalContext().redirect("../../permisosAdmin.xhtml");
            }
            else{
            }
        }
        catch(Exception e){}
        
    }
    
    public void enviarCorreo(String us, String asunto) throws UnsupportedEncodingException{
        
            
            
            Mailer.send(us,asunto,mensajeConEstilo());
            
            
    }
    public String mensajeConEstilo()
    {
        return "<h1 style=\"font-size: 20px; color:#5353ec; font-weight: bold; text-transform: uppercase ; \">Hola te queremos comunicar que " + "</h1>" + "<img src='https://cdn.vectorstock.com/i/preview-1x/73/79/analysis-stock-market-black-icon-on-white-vector-31617379.jpg'/ style=\"float: left;\"><p>" +"Tu registro de usuario para la plataforma del ICBF ha sido Exitoso "+ "<br>\n"
                    + "<p style=\"text-align: center; color: #307EDF\">\n"
                    + "</p> \n"
                    + "<br>\n"
                    + "<p style=\"color:#5353ec;font-weight: bold;\" > Gracias por formar parte de nuestra comunidad y te recordamos que eres muy importante para nosotros. </p> ";
    
    }
    
    public String irUs(){
        return "usuarios";
    }
    public String irNi(){
        return "niños";
    }
    public String irMA(){
        return "madres";
    }
    public String irJa(){
        return "jardines";
    }
    
   
    
    public void logout() throws IOException {
    FacesContext context = FacesContext.getCurrentInstance();
    ExternalContext externalContext = context.getExternalContext();
    
    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    externalContext.redirect(externalContext.getRequestContextPath() + "/faces/login.xhtml");
    }
    
   
    
}
