package de.website.Bean;


import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.mail.Transport;


/**
 * Created by Nick on 01.03.2016.
 */

@ManagedBean(name="sendMail")
@RequestScoped
public class sendBean {
    final static Logger logger = Logger.getLogger(sendBean.class);

    String name;
    String email;
    String subject;
    String text;


    public void mailSend(){

        String authuser = "testbeule2@gmail.com";
         String authpwd = "DfsdPl1234";

     try {




           // Create the email message
            HtmlEmail email = new HtmlEmail();


         email.setAuthenticator(new DefaultAuthenticator(authuser, authpwd));

         email.setHostName("smtp-relay.gmail.com");

            // properties to configure encryption

         email.setSSLCheckServerIdentity(true);
         email.setDebug(true);

         email.getMailSession().getTransport("smtp");
            email.getMailSession().getProperties().put("mail.smtp.auth", "true");
            email.getMailSession().getProperties().put("mail.debug", "true");
            email.getMailSession().getProperties().put("mail.smtp.port", "465");
            email.getMailSession().getProperties().put("mail.smtp.socketFactory.port", "465");
            email.getMailSession().getProperties().put("mail.smtp.socketFactory.fallback", "false");
            email.getMailSession().getProperties().put("mail.smtp.starttls.enable", "true");

       //  email.setStartTLSEnabled(true);

            email.setFrom(getEmail(), getName());
            email.addTo("testbeule2@gmail.com", "testBeule");
            email.setSubject(getSubject());

            // set the html message

            // set the alternative message
            email.setTextMsg(getText());

            // send the email
            email.send();




       }catch (Exception ex) {

         logger.error("Fehler beim senden der E-Mail: ", ex);

     }
    }

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
