package de.website.Bean;

import de.website.database.Nutzer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.File;

/**
 * Created by Nick on 04.03.2016.
 */
@ManagedBean(name = "update")
@RequestScoped
public class UpdateBean {
    Nutzer nutzer = Nutzer.getInstance();
    DbQuery dbCon = nutzer.getDbCon();
    Exchange ex = Exchange.getInstance();
    private File image;
    //(File image,String bauherr, String architekt, String ort, String zeitraum, String bauvolumen,
   // String leistung, String massnahmen,int pid){
    public void insertData(String bauherr, String architekt, String ort, String zeitraum, String bauvolumen,
                           String leistung, String massnahmen){
        int pid =ex.getPid();
        dbCon.insertTextData(bauherr, architekt, ort, zeitraum, bauvolumen, leistung, massnahmen,pid);
        //TODO Bilder hinzufügen
    }
    public void insertImage(){
        int pid = ex.getPid();
        dbCon.insertImages(getImage(), pid);
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }
}
