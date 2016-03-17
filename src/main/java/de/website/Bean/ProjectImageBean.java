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
    List<String> wobauPid = new ArrayList<String>();

    public List<ProjectImageBean> getAllImage() {
        List<ProjectImageBean> imageInfo = new ArrayList<ProjectImageBean>();
        Connection con = ex.getConnection();
        int pid = ex.getPid();

        try {
            stmt = con.createStatement();
            String strSql = "select id from bilder where iid =" + pid + " order by id";
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

    public List<ProjectImageBean> getSpecialImage(String pid) {
        List<ProjectImageBean> imageInfo = new ArrayList<ProjectImageBean>();
        Connection con = ex.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select id from bilder where iid =" + pid + " order by id";
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

    public List<String> getWobauPid() {
        String sectorSql;
        List<String> sectors = new ArrayList<String>();
        Connection con = ex.getConnection();
        int kid = ex.getCid();
        try {
            stmt = con.createStatement();
            String strSql = "select id from projekte where kid = " + kid + " order by id";
            rs = stmt.executeQuery(strSql);
            while (rs.next()){
                wobauPid.add(rs.getString("id"));
//                ex.setPid(Integer.parseInt(rs.getString("id")));
            }
            if(! rs.next()){
                strSql = "select id from sektor where kid = " + kid + " order by id";
                rs = stmt.executeQuery(strSql);
                while (rs.next()){
                    sectors.add(rs.getString("id"));
                }
                sectorSql = sectors.get(0);
                for(int i = 1; i < sectors.size(); i++){
                    sectorSql += " or sid = " + sectors.get(i);
                }
                strSql = "select id from projekte where sid = " + sectorSql + " order by id";
                rs = stmt.executeQuery(strSql);
                while (rs.next()){
                    wobauPid.add(rs.getString("id"));
                }
            }
        } catch (Exception e) {
        e.printStackTrace();
            return null;
    }
        return wobauPid;
    }
}