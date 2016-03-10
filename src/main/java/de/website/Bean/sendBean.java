package de.website.Bean;

import org.apache.commons.mail.*;
import org.apache.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Nick on 01.03.2016.
 */

@ManagedBean(name="sendMail")
@RequestScoped
public class sendBean {
    //neu
    final static Logger logger = Logger.getLogger(sendBean.class);

    String name;
    String email;
    String subject;
    String text;


    public void mailSend(){

//        final String username = "username@gmail.com";
//        final String password = "password";
//        Session session;
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
try {
    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("testbeule2@gmail.com", "DfsdPl1234");
                }
            });
//}catch (Exception e){
//    e.printStackTrace();
//}
//
//        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("testbeule2@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("testbeule2@gmail.com"));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler,"
                    + "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


//     try {
//
//            // Create the email message            HtmlEmail email = new HtmlEmail();
//
//            String authuser = "testbeule2@gmail.com";
//            String authpwd = "DfsdPl1234";
//
//            email.setAuthenticator(new DefaultAuthenticator(authuser, authpwd));
//
//            //email.setSmtpPort(465);
//            //email.setSslSmtpPort("465");
//           // email.setSSLOnConnect(true);
//            email.setHostName("smtp.gmail.com");
//
//
//
//            // properties to configure encryption
//            email.getMailSession().getProperties().put("mail.smtps.auth", "true");
//            email.getMailSession().getProperties().put("mail.debug", "true");
//            email.getMailSession().getProperties().put("mail.smtps.port", "465");
//            email.getMailSession().getProperties().put("mail.smtps.socketFactory.port", "465");
//            email.getMailSession().getProperties().put("mail.smtps.socketFactory.class",   "javax.net.ssl.SSLSocketFactory");
//            email.getMailSession().getProperties().put("mail.smtps.socketFactory.fallback", "false");
//            email.getMailSession().getProperties().put("mail.smtp.starttls.enable", "true");
//
//         email.setFrom(getEmail(), getName());
//         email.addTo("testbeule2@gmail.com", "testBeule");
//         email.setSubject(getSubject());
//
//            // set the html message
//            //email.setHtmlMsg("<html>Test</html>");
//
//            // set the alternative message
//            email.setTextMsg(getText());
//
//            // send the email
//            email.send();
//
//        } catch (EmailException e){
//
//            logger.error("Fehler beim senden der E-Mail: ", e);
//
//        }catch (Exception ex){
//
//            logger.error("Fehler beim senden der E-Mail: ", ex);
//
//        }
//    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
