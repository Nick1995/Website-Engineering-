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

    public void sendMail(){

            Email email = new SimpleEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("username", "password"));
            email.setSSLOnConnect(true);
            //email.setFrom("user@gmail.com");
            email.setSubject("TestMail");
           // email.setMsg("This is a test mail ... :-)");
            //email.addTo("foo@bar.com");
           // email.send();


    }
}
