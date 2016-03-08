package de.website.Bean;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created by Nick on 03.03.2016.
 */
public class Exchange {

    private int cid;
    private int sid;
    private int pid;
    private Connection connection;
    private ArrayList<String> imageIDs;
    private String sqlStr;

    public String getSqlStr() {
        return sqlStr;
    }
    public void setSqlStr(String sqlQuery, int id, String sqlOrder) {
        this.sqlStr = sqlQuery + id + sqlOrder;
    }

    public void addImageID(String id){
        imageIDs.add(id);
    }
    public ArrayList<String> getImageIDs() {
        return imageIDs;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    //get instance
    private static Exchange instance = null;
    protected Exchange() {
    }
    public static Exchange getInstance() {
        if (instance == null) {
            instance = new Exchange();
        }
        return instance;
    }
}

