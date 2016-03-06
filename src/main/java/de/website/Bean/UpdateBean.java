package de.website.Bean;

import de.website.database.Nutzer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.File;
import java.util.ArrayList;
import javax.servlet.http.Part;

/**
 * Created by Nick on 04.03.2016.
 */
@ManagedBean(name = "update")
@RequestScoped
public class UpdateBean {
    Nutzer nutzer = Nutzer.getInstance();
    DbQuery dbCon = nutzer.getDbCon();
    Exchange ex = Exchange.getInstance();
    //(bauherr, architekt, ort, startzeit, endzeit, bauvolumen, leistung, massnahmen, ID
    public void insertData(String bauherr, String architekt, String ort, String startzeit, String endzeit, String bauvolumen,
                           String leistung, String massnahmen){
        int pid =ex.getPid();
        dbCon.insertTextData(bauherr, architekt, ort, startzeit, endzeit, bauvolumen, leistung, massnahmen,pid);
    }

}
