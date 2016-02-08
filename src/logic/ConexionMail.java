package logic;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import main.Correo;

/**
 * @author adriansb3105
 */
public class ConexionMail {

    Correo correo = new Correo();

    public ConexionMail(Correo correo) {
        this.correo = correo;
    }

    public boolean enviarCorreo() {
        
        /*
        hotmail
        host = smtp.live.com
        port = 25
        
        gmail
        host = smtp.gmail.com
        port = 587
        */
        
        try {
            Properties p = new Properties();
            p.put("mail.smtp.host", "smtp.live.com");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.setProperty("mail.smtp.port", "25");
            p.setProperty("mail.smtp.user", correo.getUsuario());
            p.setProperty("mail.smtp.auth", "true");

            Session s = Session.getDefaultInstance(p, null);
            BodyPart texto = new MimeBodyPart();
            texto.setText(correo.getMensaje());

            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource(correo.getRutaArchivo())));
            adjunto.setFileName(correo.getNombreArchivo());

            MimeMultipart m = new MimeMultipart();
            m.addBodyPart(texto);
            m.addBodyPart(adjunto);

            MimeMessage mensaje = new MimeMessage(s);
            mensaje.setFrom(new InternetAddress(correo.getUsuario()));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(correo.getDestinatario()));
            mensaje.setSubject(correo.getAsunto());
            mensaje.setContent(m);

            Transport t = s.getTransport("smtp");
            t.connect(correo.getUsuario(), correo.getContrasenia());
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            t.close();

            return true;

        } catch (MessagingException ex) {
            Logger.getLogger(ConexionMail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
