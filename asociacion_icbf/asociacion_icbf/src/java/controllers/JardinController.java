/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controllers;

import auxiliares.auxiliarCantidadNiñosJardin;
import entitys.Jardin;
import entitys.Kid;
import facades.JardinFacade;
import facades.KidFacade;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
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

/**
 *
 * @author david
 */
@Named(value = "jardinController")
@SessionScoped
public class JardinController implements Serializable {

    private Jardin jardin;
    List<Jardin> jardines;
    List<Object[]> kidsss;
    private JasperPrint jasperPrint;
    private JasperPrint jasperPrint2;
    
    
    @EJB
    private KidFacade kidFacade;
    @EJB
    private JardinFacade jardinFacade;
      
    @PostConstruct
    public void init(){
        jardin= new Jardin();
    }
    
    public JardinController() {
    }


    public Jardin getJardin() {
        return jardin;
    }

    public void setJardin(Jardin jardin) {
        this.jardin = jardin;
    }
    
    //Metodos CRUD
    public void register(){
        if(jardinFacade.validarJardin(jardin.getNombre())==null){
        jardinFacade.create(jardin);
        jardin = new Jardin();
        }
        else{FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No se puede registrar porque el jardin ya existe"));         
        }
    }
    
    public void delete(Jardin jardin){
        jardinFacade.remove(jardin);
    }
    
    public Jardin preEdit(Jardin jardin){
        return this.jardin=jardin;
    }
    
    public String preEdit2(Jardin jardin){
         this.jardin=jardin;
         return "jardinEdit";
    }
    
    public String edit(){
        jardinFacade.edit(jardin);
        jardin= new Jardin();
        return "jardines";
    }
    
    public Jardin findId(int idJardin){
        return jardinFacade.find(idJardin);
    }
    
    public List<Jardin> listar(){
        return jardinFacade.findAll();
    }
    
    public void generarPDF(ActionEvent actionEvent)throws JRException, IOException{
        jardines=jardinFacade.JardinesDes();
        JRBeanCollectionDataSource beanCollection = new JRBeanCollectionDataSource(jardines);
        
        
        
        String ruta=FacesContext.getCurrentInstance().getExternalContext().getRealPath("//Reportes//");
        jasperPrint=JasperFillManager.fillReport(ruta+"//reportes.jasper", new HashMap(), beanCollection);
        
        
        
        HttpServletResponse httpResponse=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpResponse.addHeader("Content disposition", "attachment; filename=reporteJardin.pdf");
        ServletOutputStream outPutStream=httpResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outPutStream);
        FacesContext.getCurrentInstance().responseComplete();
    }
    
    public void generarPDFNinosJardin(ActionEvent actionEvent)throws JRException, IOException{
        kidsss=kidFacade.kidJardin2();
        List<auxiliarCantidadNiñosJardin> datosParaInforme = new ArrayList<>();

        for (Object[] kid : kidsss) {
                Long cantidadDeKids = (long) kid[0]; 
                String nombreJardin = (String) kid[1];
                
                auxiliarCantidadNiñosJardin dto = new auxiliarCantidadNiñosJardin(
                    cantidadDeKids,
                    nombreJardin
                );
                datosParaInforme.add(dto);
           }
     
        JRBeanCollectionDataSource beanCollection = new JRBeanCollectionDataSource(datosParaInforme);
        
        
        
        String ruta=FacesContext.getCurrentInstance().getExternalContext().getRealPath("//Reportes//");
        jasperPrint2=JasperFillManager.fillReport(ruta+"//NiñosJardin.jasper", new HashMap(), beanCollection);
        
        
        
        HttpServletResponse httpResponse=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpResponse.addHeader("Content disposition", "attachment; filename=reporteJardin.pdf");
        ServletOutputStream outPutStream=httpResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint2, outPutStream);
        FacesContext.getCurrentInstance().responseComplete();
    }
    
}
