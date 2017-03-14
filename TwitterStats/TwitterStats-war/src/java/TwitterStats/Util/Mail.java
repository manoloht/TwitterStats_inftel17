/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwitterStats.Util;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author alberto carrion
 */
public class Mail {

    public Mail() {

    }

    public boolean enviarEmail(String nombre, String apellidos, String email, String mensaje) throws MessagingException {

        String emailEmisor = "statsmasterinftel17@gmail.com";
        String passEmisor = "statsmasterinftel";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailEmisor, passEmisor);
            }
        });

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(emailEmisor));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailEmisor));
            message.setSubject("Contacto desde Web");
            message.setText("Nombre: " + nombre + "\nApellidos: " + apellidos + "\nEmail: " + email + "\nMensaje: " + mensaje);
            Transport.send(message);
            return true;

        } catch (AddressException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
