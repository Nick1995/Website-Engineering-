package de.website.Bean;

import org.apache.commons.mail.*;
import org.apache.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

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

        try {

            // Create the email message
            HtmlEmail email = new HtmlEmail();
            email.setHostName("smtp.gmail.com");
            email.addTo("nick.fahrendorff@gmail.com", "Nick Fahrendorff");
            email.setFrom(getEmail(), getName());
            email.setSubject(getSubject());

            // set the html message
            email.setHtmlMsg("<html>Test</html>");

            // set the alternative message
            email.setTextMsg(getText());

            // send the email
            email.send();

        } catch (EmailException e){

            logger.error("Fehler beim senden der E-Mail: ", e);

        }catch (Exception ex){

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
