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
  public  String username;
    String passwd;
    Nutzer nutzer = Nutzer.getInstance();
    public String start(String username, String passwd){
        try {
            DbQuery dBcon = new DbQuery(username, passwd);
            nutzer.setDbCon(dBcon);
            return moveToAdminPage();

        }catch (Exception exc){
            return moveToErrorPage();
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
