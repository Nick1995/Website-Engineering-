package de.website.Bean;

/**
 * Created by Nick on 06.03.2016.
 */
import java.io.File;
import java.io.InputStream;
import java.sql.*;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.xml.transform.Result;

import de.website.database.Nutzer;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.model.UploadedFile;
@ManagedBean(name = "uploadImage")
@SessionScoped
public class ImageUploadBean {
    final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(DbQuery.class);
    private UploadedFile file;
    private FileUpload image;


    Nutzer nutzer = Nutzer.getInstance();
    DbQuery dbCon = nutzer.getDbCon();
    Exchange ex = Exchange.getInstance();

    public void upload() {
        System.out.println(image.getAllowTypes());

        if (this.file != null) {
            try {
                int iid = ex.getPid();
                if(iid == 0){
                    FacesMessage msg = new FacesMessage("Bitte wählen Sie ein Projekt aus!");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }else {
                    System.out.println(file.getFileName());
                    InputStream fin = file.getInputstream();
                    Nutzer nutzer = Nutzer.getInstance();
                    DbQuery dbCon = nutzer.getDbCon();
                    Connection con = ex.getConnection();
                    //TODO: check If Image already exists
                        PreparedStatement pre = con.prepareStatement("insert into Bilder (iid, Bilder) values(?,?)");
                        pre.setInt(1, ex.getPid());
                        pre.setBinaryStream(2, fin, file.getSize());

                        pre.executeUpdate();
                        pre.close();
                        FacesMessage msg = new FacesMessage("Das Bild ", file.getFileName() + " wurde erfolgreich gespeichert.");
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            } catch (Exception e) {
                //TODO: Bild zu groß Exception abfangen
                FacesMessage msg = new FacesMessage("Die Daten konnten nicht gespeichert werden");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                logger.error("Fehler Datenbank-Bilder-Upload: " + e);
            }
        }
        else{
            FacesMessage msg = new FacesMessage("Bitte wählen Sie ein Bild aus!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public FileUpload getImage() {
        return image;
    }

    public void setImage(FileUpload image) {
        this.image = image;
    }
}
