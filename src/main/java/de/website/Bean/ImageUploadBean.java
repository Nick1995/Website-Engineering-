package de.website.Bean;

/**
 * Created by Nick on 06.03.2016.
 */
import java.io.File;
import java.io.InputStream;
import java.sql.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import de.website.database.Nutzer;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.model.UploadedFile;
@ManagedBean(name = "uploadImage")
@SessionScoped
public class ImageUploadBean {
    private UploadedFile file;
    private FileUpload image;


    Nutzer nutzer = Nutzer.getInstance();
    DbQuery dbCon = nutzer.getDbCon();
    Exchange ex = Exchange.getInstance();

    public void upload() {
        System.out.println(image.getAllowTypes());

        if (this.file != null) {
            try {

                System.out.println(file.getFileName());
                InputStream fin = file.getInputstream();
                Nutzer nutzer = Nutzer.getInstance();
                DbQuery dbCon = nutzer.getDbCon();
                Connection con = ex.getConnection();
                PreparedStatement pre = con.prepareStatement("insert into Bilder (iid, Bilder) values(?,?)");
                pre.setInt(1, ex.getPid());
                pre.setBinaryStream(2, fin, file.getSize());
                pre.executeUpdate();
                System.out.println("Inserting Successfully!");
                pre.close();
//                FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
//                FacesContext.getCurrentInstance().addMessage(null, msg);

            } catch (Exception e) {
                System.out.println("Exception-File Upload." + e.getMessage());
            }
        }
        else{
            FacesMessage msg = new FacesMessage("Please select image!!");
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
