package de.webiste.database;

/**
 * Created by Nick on 29.02.2016.
 */
public class Nutzer {
    public String user = "open";
    public String password = "";

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
