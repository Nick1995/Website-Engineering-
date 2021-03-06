package de.website.Bean;

import de.website.database.Nutzer;
import org.apache.log4j.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 08.03.2016.
 */

@ManagedBean(name = "categoryImageBean")
@RequestScoped
public class CategoryImageBean {
    Exchange ex = Exchange.getInstance();
    Nutzer nutzer = Nutzer.getInstance();
    final static Logger logger = Logger.getLogger(DbQuery.class);
    private String imageID;
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;


    public List<CategoryImageBean> getAllImage(String kid) {
        List<CategoryImageBean> imageInfo = new ArrayList<CategoryImageBean>();
        connect2db();
        Connection con = ex.getConnection();
        try {
            stmt = con.createStatement();
            String id = "";
            if(lookUpProjectsByKid(kid).equals("")){
                id = lookUpProjectsBySid(kid);
            }else if(lookUpSectorsByKid(kid).equals("")){
                id = lookUpProjectsByKid(kid);
            }

            String strSql = "select id from bilder where " + id + " order by id";
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                CategoryImageBean cib = new CategoryImageBean();
                cib.setImageID(rs.getString("id"));
                imageInfo.add(cib);
            }
        } catch (SQLException e) {
            logger.error("Fehler bei der Datenbankabfrage: ", e);
        }
        return imageInfo;
    }
    public String getCatName(String cid){
        DbQuery dbCon = nutzer.getDbCon();
        return dbCon.getCategoryName(cid);
    }
    private void connect2db() {
        try {
            DbQuery dBcon = new DbQuery("open", "");
            nutzer.setDbCon(dBcon);

        } catch (Exception exc) {
            logger.error("Die Datenbank konnte vom User open nicht erreicht werden.");
        }
    }
    private String lookUpProjectsByKid(String kidNum){
        connect2db();
        ArrayList<String> kid = new ArrayList<String>();
        String sqlId = "";
        Connection con = ex.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select id from projekte where kid = " + kidNum;
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
    private String lookUpSectorsByKid(String kidNum){
        connect2db();
        ArrayList<String> sectors = new ArrayList<String>();
        String sqlId = "";
        Connection con = ex.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select id from sektor where kid = " + kidNum;
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                sectors.add(rs.getString("id"));
            }

            sqlId = " sid = " + sectors.get(0);
            for (int i = 1; i < sectors.size() - 1; i++) {
                sqlId += " or sid = " + sectors.get(i);
            }
            return sqlId;
        }catch (Exception e){
            logger.error("Fehler Datenbankabfrage: " ,e);
        }
        return sqlId;
    }
    private String lookUpProjectsBySid(String kidNum){
        connect2db();
        ArrayList<String> sectors = new ArrayList<String>();
        String sqlId = "";
        Connection con = ex.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select id from projekte where " + lookUpSectorsByKid(kidNum);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                sectors.add(rs.getString("id"));
            }

            sqlId = " iid = " + sectors.get(0);
            for (int i = 1; i < sectors.size() - 1; i++) {
                sqlId += " or iid = " + sectors.get(i);
            }
            return sqlId;
        }catch (Exception e){
            logger.error("Fehler Datenbankabfrage: " ,e);
        }
        return sqlId;
    }
    //getter + setter

    public String getImageID() {
        return imageID;
    }
    public void setImageID(String imageID) {
        this.imageID = imageID;
    }
}