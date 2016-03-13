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

//    public ArrayList<String> getWobauPid(){
//    public String getWobauPid(){
//        ArrayList<String> pids = new ArrayList<String>();
//        Connection con = ex.getConnection();
//        try {
//            stmt = con.createStatement();
//            String strSql = "select id from projekte where kid = 1 order by id";
//            rs = stmt.executeQuery(strSql);
//            if (rs.next()){
////                pids.add(rs.getString("id"));
//                return rs.getString("id");
//            }
//        } catch (Exception e) {
//        e.printStackTrace();
//            return null;
//    }
//        return null;
////        return pids;
//    }

    public List<String> getWobauPid() {
        Connection con = ex.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select id from projekte where kid = 1 order by id";
            rs = stmt.executeQuery(strSql);
            while (rs.next()){
                wobauPid.add(rs.getString("id"));
                ex.setPid(Integer.parseInt(rs.getString("id")));
            }
        } catch (Exception e) {
        e.printStackTrace();
            return null;
    }
        return wobauPid;
    }
}