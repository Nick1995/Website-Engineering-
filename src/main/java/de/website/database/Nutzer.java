package de.website.database;

import de.website.Bean.DbQuery;

import java.sql.Connection;

/**
 * Created by Nick on 29.02.2016.
 */
public class Nutzer {
    public String user;
    public String password;
    private DbQuery dbCon;

    public DbQuery getDbCon() {
        return dbCon;
    }
    public void setDbCon(DbQuery dbCon) {
        this.dbCon = dbCon;
    }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }



    //get Instance Methode
    private static Nutzer instance = null;
    protected Nutzer() {
    }
    public static Nutzer getInstance() {
        if (instance == null) {
            instance = new Nutzer();
        }
        return instance;
    }
    }
