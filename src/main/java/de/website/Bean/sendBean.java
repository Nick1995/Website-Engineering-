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

    public void mailSend(String name, String email, String subject, String text){

        Email simMail = new SimpleEmail();
        simMail.setHostName("smtp.googlemail.com");
        simMail.setSmtpPort(465);
        simMail.setAuthenticator(new DefaultAuthenticator("username", "password"));
        simMail.setSSLOnConnect(true);

        try {
            simMail.setFrom(email);
            simMail.setSubject(subject);
            simMail.setMsg(text);
            //email.addTo("foo@bar.com");
            simMail.send();

        } catch (EmailException e){

        }
    }
}
