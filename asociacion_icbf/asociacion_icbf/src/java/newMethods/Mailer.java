package newMethods;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mailer {
public static void send(String para,String asunto,String mensaje) throws UnsupportedEncodingException{

final String user="jose.aleman7@misena.edu.co";//cambiará en consecuencia al servidor utilizado
final String pass="wrhtbjjyeulndzuo";

//1st paso) Obtener el objeto de sesión

Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); //El servidor SMTP de Google
        props.setProperty("mail.smtp.starttls.enable", "true");//Para conectar de manera segura al servidor SMTP, mensaje cifrado
        props.setProperty("mail.smtp.port", "587");//El puerto SMTP seguro de Google
        props.setProperty("mail.smtp.starttls.required", "true");//Para conectar de manera segura al servidor SMTP, mensaje cifrado
        props.setProperty("mail.smtp.auth", "true"); //Usar autenticación mediante usuario y clave
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");//Seguridad en la capa de transporte



Session session;
    session = Session.getInstance(props, new javax.mail.Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, pass);
        }
    });



//2nd paso)compose message
try {
    //Para adjuntos
   /* BodyPart adjunto = new MimeBodyPart();
adjunto.setDataHandler(new DataHandler(new FileDataSource("f:/cartagena.txt")));
adjunto.setFileName("cartagena.txt");*/
    
BodyPart texto=new MimeBodyPart();
texto.setContent(mensaje,"text/html");
MimeMultipart multiparte=new MimeMultipart();
multiparte.addBodyPart(texto);
//multiparte.addBodyPart(adjunto);
 MimeMessage message = new MimeMessage(session);
 message.setFrom(new InternetAddress(user,"Java"));
 //Un solo destinatario
 
 //InternetAddress detinatario=new InternetAddress("pep@gmail.com");
InternetAddress[] destinatarios2={
    new InternetAddress(para),
    //new InternetAddress("nhrodrigueza@misena.edu.co"),
   // new InternetAddress("nelherson@hotmail.com")
    
}; //Correo masivo

/*InternetAddress[] destinatarios = new InternetAddress[correos.size()];
        for (int i = 0; i < correos.size(); i++) {
            destinatarios[i] = new InternetAddress(correos.get(i));
        }*/

InternetAddress destinatario=new InternetAddress(para);
 
 message.setRecipient(Message.RecipientType.TO,destinatario);
 message.setSubject(asunto);
 message.setContent(multiparte,"text/html; charset=utf-8");

 //3rd paso)send message
 Transport.send(message);

 System.out.println("Done");

 } catch (MessagingException e) {
	throw new RuntimeException(e);
 }
	
}
}
