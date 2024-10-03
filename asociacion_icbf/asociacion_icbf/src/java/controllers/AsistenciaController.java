/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controllers;

import auxiliares.auxiliarAsistencias;
import entitys.Asistencia;
import entitys.Jardin;
import entitys.Kid;
import entitys.MadreComunitaria;
import entitys.Usuario;
import facades.AsistenciaFacade;
import facades.JardinFacade;
import facades.KidFacade;
import facades.MadreComunitariaFacade;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import newMethods.Mailer;

/**
 *
 * @author david
 */
@Named(value = "asistenciaController")
@SessionScoped
public class AsistenciaController implements Serializable {

    
     FacesContext context = FacesContext.getCurrentInstance();
   UsuarioController usuarioController = (UsuarioController) context.getApplication().evaluateExpressionGet(context, "#{usuarioController}", UsuarioController.class);
   private Asistencia asistencia;
   private Jardin jardin;
   private Kid kid;
   private MadreComunitaria madreComunitaria;
   private MadreComunitaria madre1;
   private Usuario usuario;
   private String variable;
   private List<Asistencia> asistenciasEncontradas;
   private String asistencia2;
   private String descripcion2;
   private List <Asistencia> asistenciasReport;
   private JasperPrint jasperPrint;
   
    @EJB
    private AsistenciaFacade asistenciaFacade;
    @EJB
    private JardinFacade jardinFacade;
    @EJB
    private KidFacade kidFacade;
    @EJB
    private MadreComunitariaFacade madreComunitariaFacade;
    
    @PostConstruct
    public void init(){
        asistencia= new Asistencia();
        jardin = new Jardin();
        kid = new Kid();
        madreComunitaria = new MadreComunitaria();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getAsistencia2() {
        return asistencia2;
    }

    public void setAsistencia2(String asistencia2) {
        this.asistencia2 = asistencia2;
    }

    public String getDescripcion2() {
        return descripcion2;
    }

    public void setDescripcion2(String descripcion2) {
        this.descripcion2 = descripcion2;
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
    
    public AsistenciaController() {
    }
    
    public Asistencia getAsistencia(){
        return asistencia;
    }
    
    public void setAsistencia(Asistencia asistencia){
        this.asistencia = asistencia;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public List<Asistencia> getAsistenciasEncontradas() {
        return asistenciasEncontradas;
    }

    public void setAsistenciasEncontradas(List<Asistencia> asistenciasEncontradas) {
        this.asistenciasEncontradas = asistenciasEncontradas;
    }
    
    
    
    //Metodos CRUD
    public void register() throws UnsupportedEncodingException{
        asistencia.setIdkid(kidFacade.find(kid.getIdkid())); 
        if("Presente".equals(asistencia.getAsistencia()) &&"Enfermo".equals(asistencia.getDescripción()))
        {enviarCorreo("jdaleman5324@gmail.com",
                        "Estudiante enfermo",
                        "El estudiante: "+asistencia.getIdkid().getNombre()+" "+asistencia.getIdkid().getApellido()+" Identificado con el documento " + asistencia.getIdkid().getNiup()+
                        " llego enfermo al jardín. Por favor comunicarse con su acudiente ");}
        asistenciaFacade.create(asistencia);
        asistencia=new Asistencia();
        jardin = new Jardin(); 
    }
    
    public void registerMasive() {
    FacesContext context = FacesContext.getCurrentInstance();
    List<Kid> listaDeEstudiantes = listarKid();
    
    try {
        for (Kid student : listaDeEstudiantes) {
            Asistencia asistenciaEstudiante = new Asistencia();
            asistenciaEstudiante.setIdkid(student);
            asistenciaEstudiante.setAsistencia(asistencia.getAsistencia());
            asistenciaEstudiante.setDescripción(asistencia.getDescripción());
           

            asistenciaFacade.create(asistenciaEstudiante);
        }

        // Reiniciar el objeto de asistencia después de registrar para evitar duplicados
        asistencia = new Asistencia();
        jardin = new Jardin();

        
    } catch (Exception e) {
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al registrar la asistencia masiva.", null));
        e.printStackTrace();
        
    }
}
    
    public void delete(Asistencia asistencia){
        asistenciaFacade.remove(asistencia);
    }
    
    public String preEdit(Asistencia asistencia){
        this.asistencia=asistencia;
        return "edit";
    }
    
    public String edit(){
        asistencia.setIdkid(kidFacade.find(kid.getIdkid()));
        asistenciaFacade.edit(asistencia);
        asistencia=new Asistencia();
        return "list";
    }
    
    public Asistencia findId(int idAsistencia){
        return asistenciaFacade.find(idAsistencia);
    }
    
    public List<Asistencia> listar(){
        return asistenciaFacade.findAll();
    }
    
    public List<Kid> listarKid(){
        madre1=null;
        madre1 = madreComunitariaFacade.buscarMadre(usuarioController.UsuarioLogeado().getIdusuario());
        return kidFacade.buscarKid(jardinFacade.buscarJardin(madre1.getIdjardin().getIdjardin()).getIdjardin());
    }
    
    public void preRegister(Kid kid){
        this.kid=kid;
    }
    
    public Asistencia buscarPorFecha() throws java.text.ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaConvertida = sdf.parse(variable); 
        madre1=null;
        madre1 = madreComunitariaFacade.buscarMadre(usuarioController.UsuarioLogeado().getIdusuario());
       asistenciasEncontradas = asistenciaFacade.FindByFecha(fechaConvertida,madre1.getIdjardin().getIdjardin());
       return null;
    }
    
    public Asistencia buscarPorFNUIP() throws java.text.ParseException{
       asistenciasEncontradas = asistenciaFacade.FindByNIUP(variable);
       return null;
    }
    
    public void horaAsistencia() throws IOException {
    LocalTime time = LocalTime.now();
    LocalTime ocho = LocalTime.of(8, 0);
    LocalTime diez = LocalTime.of(10, 0);
            
    if (time.isBefore(ocho) || time.isAfter(diez)) {
        FacesContext.getCurrentInstance().getExternalContext().redirect("../PermisosAsis.xhtml");
    } else {
        
    }
}
    
    public String verAsis(){
        return "verasistencia";
    }
    
    public String Asis(){
        return "asistencia";
    }
    
    public void generarPDF(ActionEvent actionEvent)throws JRException, IOException{
        asistenciasReport=asistenciaFacade.InasistenciasEnfermedad();
        List<auxiliarAsistencias> datosParaInforme = new ArrayList<>();

        for (Asistencia asistenciaReport : asistenciasReport) {
            if (asistenciaReport.getIdasistencia()!= null) {
                auxiliarAsistencias dto = new auxiliarAsistencias(
                    asistenciaReport.getIdkid().getIdkid(),
                    asistenciaReport.getIdkid().getNombre(),
                    asistenciaReport.getIdkid().getApellido(),
                    asistenciaReport.getAsistencia(),
                    asistenciaReport.getDescripción() 
                );
                datosParaInforme.add(dto);
            }
        }
        JRBeanCollectionDataSource beanCollection = new JRBeanCollectionDataSource(datosParaInforme);
        
        
        
        String ruta=FacesContext.getCurrentInstance().getExternalContext().getRealPath("//Reportes//");
        jasperPrint=JasperFillManager.fillReport(ruta+"//inasistencias.jasper", new HashMap(), beanCollection);
        
        
        
        HttpServletResponse httpResponse=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpResponse.addHeader("Content disposition", "attachment; filename=reporteJardin.pdf");
        ServletOutputStream outPutStream=httpResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outPutStream);
        FacesContext.getCurrentInstance().responseComplete();
    }
    
    public void generarPDFSemanal(ActionEvent actionEvent)throws JRException, IOException{
        madre1=null;
        madre1 = madreComunitariaFacade.buscarMadre(usuarioController.UsuarioLogeado().getIdusuario());
        asistenciasReport=asistenciaFacade.AsistenciaSemanal(madre1.getIdjardin().getIdjardin());
        List<auxiliarAsistencias> datosParaInforme = new ArrayList<>();

        for (Asistencia asistenciaReport : asistenciasReport) {
            if (asistenciaReport.getIdasistencia()!= null) {
                auxiliarAsistencias dto = new auxiliarAsistencias(
                    asistenciaReport.getIdkid().getIdkid(),
                    asistenciaReport.getIdkid().getNombre(),
                    asistenciaReport.getIdkid().getApellido(),
                    asistenciaReport.getAsistencia(),
                    asistenciaReport.getDescripción() 
                );
                datosParaInforme.add(dto);
            }
        }
        JRBeanCollectionDataSource beanCollection = new JRBeanCollectionDataSource(datosParaInforme);
        
        
        
        String ruta=FacesContext.getCurrentInstance().getExternalContext().getRealPath("//Reportes//");
        jasperPrint=JasperFillManager.fillReport(ruta+"//AsistenciaSemanal.jasper", new HashMap(), beanCollection);
        
        
        
        HttpServletResponse httpResponse=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpResponse.addHeader("Content disposition", "attachment; filename=reporteJardin.pdf");
        ServletOutputStream outPutStream=httpResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outPutStream);
        FacesContext.getCurrentInstance().responseComplete();
    }
    
    public void enviarCorreo(String us, String asunto, String mensaje) throws UnsupportedEncodingException{
        
            
            
            Mailer.send(us,asunto,mensaje);
            
            
    }
    
    
}