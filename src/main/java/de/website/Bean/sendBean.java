package de.website.Bean;

import org.apache.commons.mail.*;
import org.apache.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Nick on 01.03.2016.
 */

// Tobi stinkt nach Kot
@ManagedBean(name="sendMail")
@RequestScoped
public class sendBean {
    final static Logger logger = Logger.getLogger(sendBean.class);

    public void mailSend(String name, String email, String subject, String text){

        String authuser = "...@gmail.com";
        String authpwd = "xxxx";

        try {

            Email simMail = new SimpleEmail();
            simMail.setHostName("smtp.googlemail.com");
            simMail.setSmtpPort(465);
            simMail.setAuthenticator(new DefaultAuthenticator(authuser, authpwd));
            simMail.setSSLOnConnect(true);
            simMail.setFrom(email);
            simMail.setSubject(subject);
            simMail.setMsg(text);
            //email.addTo("foo@bar.com");
            simMail.send();

        } catch (EmailException e){

            logger.error("Fehler beim senden der E-Mail: ", e);

        }catch (Exception ex){

            logger.error("Fehler beim senden der E-Mail: ", ex);

        }
    }
}
