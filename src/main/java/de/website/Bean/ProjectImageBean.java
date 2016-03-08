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
@ManagedBean(name = "projectImageBean")
public class ProjectImageBean {
//    private int projectID;
    private int imageID;
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;
    Exchange ex = Exchange.getInstance();

    public List<ProjectImageBean> getAllImage() {
        List<ProjectImageBean> imageInfo = new ArrayList<ProjectImageBean>();
        Connection con = ex.getConnection();
        int pid = ex.getPid();
        try {
            stmt = con.createStatement();
            String strSql = "select id from bilder where iid =" + pid + " order by id";
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                ProjectImageBean idb = new ProjectImageBean();
                idb.setImageID(rs.getInt("id"));
                imageInfo.add(idb);
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