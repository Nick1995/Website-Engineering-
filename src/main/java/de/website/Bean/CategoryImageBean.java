package de.website.Bean;

import de.website.database.Nutzer;
import org.apache.log4j.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 08.03.2016.
 */
@RequestScoped
@ManagedBean(name = "categoryImageBean")
public class CategoryImageBean {
    Exchange ex = Exchange.getInstance();
    Nutzer nutzer = Nutzer.getInstance();
    final static Logger logger = Logger.getLogger(DbQuery.class);
    private int imageID;
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;




    public List<CategoryImageBean> getAllImage() {
        List<CategoryImageBean> imageInfo = new ArrayList<CategoryImageBean>();
        connect2db();
        Connection con = ex.getConnection();
        try {
           String strSql = "select id from bilder where " + Kid() + " order by id";
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                CategoryImageBean cib = new CategoryImageBean();
                cib.setImageID(rs.getInt("id"));
                imageInfo.add(cib);
            }
        } catch (SQLException e) {
            logger.error("Fehler bei der Datenbankabfrage: ", e);
        }
        return imageInfo;
    }

    private void connect2db() {
        try {
            DbQuery dBcon = new DbQuery("open", "");
            nutzer.setDbCon(dBcon);

        } catch (Exception exc) {
            logger.error("Die Datenbank konnte vom User open nicht erreicht werden.");
            FacesMessage msg = new FacesMessage("Die Datenbank konnte nicht erreicht werden");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    private String Kid(){
        ArrayList<String> kid = new ArrayList<String>();
        String sqlId = "";
        Connection con = ex.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select id from projekte where kid = 1";
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                kid.add(rs.getString("id"));
            }

            sqlId = " iid = " + kid.get(0);
            for (int i = 1; i < kid.size() - 1; i++) {
                sqlId += " or iid = " + kid.get(i);
            }
            return sqlId;
        }catch (Exception e){
            logger.error("Fehler Datenbankabfrage: " ,e);
        }
        return sqlId;
    }
    //getter + setter
    public int getImageID() {
        return imageID;
    }
    public void setImageID(int imageID) {
        this.imageID = imageID;
    }


}
