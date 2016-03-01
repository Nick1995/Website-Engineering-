package de.website.Bean;

import org.apache.commons.mail.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Tobi on 29.02.2016.
 */
@ManagedBean(name="sendMail")
@RequestScoped
public class sendBean {
    
    final static Logger logger = Logger.getLogger(sendBean.class);

    public void mailSend(String name, String email, String subject, String text){
        
        String authuser = "...@gmail.com";
        String authpwd = "xxxx";

        Email simMail = new SimpleEmail();
        simMail.setHostName("smtp.googlemail.com");
        simMail.setSmtpPort(465);
        simMail.setAuthenticator(new DefaultAuthenticator(authuser, authpwd));
        simMail.setSSLOnConnect(true);

        try {
            simMail.setFrom(email);
            simMail.setSubject(subject);
            simMail.setMsg(text);
            //email.addTo("foo@bar.com");
            simMail.send();

        } catch (EmailException e){
            
            logger.error("Fehler beim senden der E-Mail: ", e);

        }
    }
}
