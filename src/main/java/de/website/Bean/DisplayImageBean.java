package de.website.Bean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Nick on 07.03.2016.
 */
@RequestScoped
@ManagedBean(name = "imageBean")
public class DisplayImageBean {
    private String projectID;
    private String imageID;
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;
    Exchange ex = Exchange.getInstance();
    public List<DisplayImageBean> getAllImage() {
        List<DisplayImageBean> imageInfo = new ArrayList<DisplayImageBean>();
        Connection con = ex.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select iid from bilder order by id ";
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                DisplayImageBean idb = new DisplayImageBean();
                idb.setProjectID(rs.getString("iid"));
                imageInfo.add(idb);
            }
        } catch (SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        }
        return imageInfo;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }
}