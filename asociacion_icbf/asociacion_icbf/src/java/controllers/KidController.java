/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controllers;

import entitys.Acudiente;
import entitys.Jardin;
import entitys.Kid;
import auxiliares.auxiliarKidJardin;
import facades.AcudienteFacade;
import facades.JardinFacade;
import facades.KidFacade;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import newMethods.Mailer;

/**
 *
 * @author david
 */
@Named(value = "kidController")
@SessionScoped
public class KidController implements Serializable {

   private Kid kid;
   private Jardin jardin;
   private Acudiente acudiente;
   private List<Jardin> jardines;
   List <Kid> kids235;
   
   private String nombre;
   private String rutaReal;
   private Part archivo;
   
   private JasperPrint jasperPrint;
   
    @EJB
    private KidFacade kidFacade;
    @EJB
    private JardinFacade jardinFacade;
    @EJB
    private AcudienteFacade acudienteFacade;
    
    @PostConstruct
    public void init(){
        kid= new Kid();
        jardin = new Jardin();
        acudiente = new Acudiente();
    }

    public Acudiente getAcudiente() {
        return acudiente;
    }

    public void setAcudiente(Acudiente acudiente) {
        this.acudiente = acudiente;
    }
    
    
    public Jardin getJardin() {
        return jardin;
    }

    public void setJardin(Jardin jardin) {
        this.jardin = jardin;
    } 
    
    public KidController() {
    }
    
    public Kid getKid(){
        return kid;
    }
    
    public void setKid(Kid kid){
        this.kid = kid;
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
    
    
    
    //Metodos CRUD
    public String register() throws UnsupportedEncodingException{
        if (kidFacade.validarNUIP(kid.getNiup())== null && calcularEdad(kid.getFechaNacimiento())<5) {
            kid.setIdjardin(jardinFacade.find(jardin.getIdjardin()));
            kid.setIdacudiente(acudienteFacade.find(acudiente.getIdacudiente()));
            kid.setFoto(subir(getArchivo()));
            kidFacade.create(kid);
            enviarCorreo(kid.getIdacudiente().getIdusuario().getCorreo(), "Registro de Niño exitoso");
            kid=new Kid();
            acudiente = new Acudiente();
            jardin = new Jardin();
            return "niños";
        }
        else{
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puede registrar al niño porque ya esta registrado o tiene mas de 5 años"));
            return null;
                   
        }
        
    }
    
    public void delete(Kid kid){
        kidFacade.remove(kid);
    }
    
    public String preEdit(Kid kid){
        
        this.kid=kid;
        return "niñosEdit";
    }
    
    public String edit(){
        kid.setIdjardin(jardinFacade.find(jardin.getIdjardin()));
        kid.setIdacudiente(acudienteFacade.find(acudiente.getIdacudiente()));
        kidFacade.edit(kid);
        kid=new Kid();
        return "niños";
    }
    
    public Kid findId(int idKid){
        return kidFacade.find(idKid);
    }
    
    public List<Kid> listar(){
        return kidFacade.findAll();
    }
    
    
    
    public List<Acudiente> listarAcudiente(){
        return acudienteFacade.findAll();
    }
    
    public void enviarCorreo(String us, String asunto) throws UnsupportedEncodingException{
        
            
            
            Mailer.send(us,asunto,mensajeConEstilo());
            
            
    }
    
    public String mensajeConEstilo()
    {
        return "<h1 style=\"font-size: 20px; color:#5353ec; font-weight: bold; text-transform: uppercase ; \">Hola te queremos comunicar que " + "</h1>" + "<img src='https://cdn.vectorstock.com/i/preview-1x/73/79/analysis-stock-market-black-icon-on-white-vector-31617379.jpg'/ style=\"float: left;\"><p>" +" El registro del morrillo ha sido exitoso "+ "<br>\n"
                    + "<p style=\"text-align: center; color: #307EDF\">\n"
                    + "</p> \n"
                    + "<br>\n"
                    + "<p style=\"color:#5353ec;font-weight: bold;\" > Gracias por formar parte de nuestra comunidad y te recordamos que eres muy importante para nosotros. </p> ";
    
    }
    
    public void felizCumple() throws UnsupportedEncodingException{
        List <Kid> kids = kidFacade.cumpleKid();
        if(kids != null){
            //List<String> correos = new ArrayList<>();
            //List<String> nombres = new ArrayList<>();
            //List<String> apellidos = new ArrayList<>();
            for(Kid kid: kids){
                String correo = kid.getIdacudiente().getIdusuario().getCorreo();
                
                String nombre = kid.getNombre();
                
                String apellido = kid.getApellido();
                
                Mailer.send(correo, "Cumplea;os de "+nombre, "Queremos desearle un feliz cumplea;os a " +nombre + " " +apellido);
                
            }
            
            
        }
       
    }
    
    
    public void ninoViejo() throws UnsupportedEncodingException{
        List <Kid> kids = kidFacade.kidViejo();
        if(kids != null){
            //List<String> correos = new ArrayList<>();
            //List<String> nombres = new ArrayList<>();
            //List<String> apellidos = new ArrayList<>();
            for(Kid kid: kids){
                String correo = kid.getIdacudiente().getIdusuario().getCorreo();
                
                String nombre = kid.getNombre();
                
                String apellido = kid.getApellido();
                
                Mailer.send(correo, "Estudiante no apto MATRICULA PROXIMO AÑO", "Queremos recordarte que el chico " +nombre + " " +apellido +" Ya no podra ser parte de nuestras instutuciones a partir del año siguiente por favor inscribir matrucula en una de las instituciones de su preferencia.");
                
            }
            
            
        }
       
    }
    
    public int calcularEdad(Date fechaNacimiento) {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaNac = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(fechaNac, fechaActual).getYears();
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
    
    public void generarPDF(ActionEvent actionEvent)throws JRException, IOException{
        kids235 = kidFacade.findAll();
        
        List<auxiliarKidJardin> datosParaInforme = new ArrayList<>();

        for (Kid kid235 : kids235) {
            if (kid235.getIdjardin() != null) {
                auxiliarKidJardin dto = new auxiliarKidJardin(
                    kid235.getIdkid(),
                    kid235.getNiup(),
                    kid235.getNombre(),
                    kid235.getApellido(),
                    kid235.getIdjardin().getNombre()
                );
                datosParaInforme.add(dto);
            }
        }
        JRBeanCollectionDataSource beanCollection = new JRBeanCollectionDataSource(datosParaInforme);
        
        String ruta=FacesContext.getCurrentInstance().getExternalContext().getRealPath("//Reportes//");
        jasperPrint=JasperFillManager.fillReport(ruta+"//newReport.jasper", new HashMap(),beanCollection);
        
        
        
        HttpServletResponse httpResponse=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpResponse.addHeader("Content disposition", "attachment; filename=Jardines.pdf");
        ServletOutputStream outPutStream=httpResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outPutStream);
        FacesContext.getCurrentInstance().responseComplete();
    }
    
   
    
    
    
    
    
    
    
}

    
    

