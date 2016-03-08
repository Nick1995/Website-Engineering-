package de.website.Bean;

import de.website.database.Nutzer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 08.03.2016.
 */
@RequestScoped
@ManagedBean(name = "categoryImageBean")
public class CategoryImageBean {
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;
    Exchange ex = Exchange.getInstance();
    Nutzer nutzer = Nutzer.getInstance();
    private int imageID;
    private void connect2db(){
        try {
            DbQuery dBcon = new DbQuery("open", "");
            nutzer.setDbCon(dBcon);

        }catch (Exception exc){
            //TODO PW falsch
        }
    }
    public List<CategoryImageBean> getAllImage() {
        List<CategoryImageBean> imageInfo = new ArrayList<CategoryImageBean>();
        connect2db();
        Connection con = ex.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select id from bilder where iid=1 order by id";
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                CategoryImageBean cib = new CategoryImageBean();
                cib.setImageID(rs.getInt("id"));
                imageInfo.add(cib);
            }
        } catch (SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        }
        return imageInfo;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
