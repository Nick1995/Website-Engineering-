package de.website.Bean;

import de.webiste.database.Nutzer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Nick on 01.03.2016.
 */
@ManagedBean(name = "login")
@RequestScoped
public class LoginBean {
    String user;
    String pwd;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public void start(){
        Nutzer nutzer = Nutzer.getInstance();
        nutzer.setUser(getUser());
        nutzer.setPassword(getPwd());
        try {
            DbQuery dBcon = new DbQuery();
            moveToAdminPage();
        }catch (Exception exc){
            moveToErrorPage();
            //TODO PW falsch
        }
    }
    private String moveToAdminPage(){
        return "mitgliederber";
    }

    private String moveToErrorPage(){
        return "error";
    }
}
