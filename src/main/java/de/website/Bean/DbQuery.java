package de.website.Bean;

import de.website.database.Data;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Nick on 29.02.2016.
 */
public class DbQuery {

    final static Logger logger = Logger.getLogger(DbQuery.class);
    Exchange ex = Exchange.getInstance();

    private Connection connection;
    public DbQuery(String username, String passwd) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            throw new UnsupportedOperationException();
        }
        try {
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projektdaten", "open", "");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projektdaten", username, passwd);
            ex.setConnection(connection);
        } catch (Exception exception) {
            throw new UnsupportedOperationException();
        }
    }
//Abfragen
    public ArrayList<String> getCategories(){
        ArrayList <String> categories = new ArrayList<String>();
        try {
            Statement myState = connection.createStatement();
            ResultSet myRs = myState.executeQuery("SELECT * FROM kategorien");
            while (myRs.next()) {
                categories.add(myRs.getString("Name"));
            }
        }catch (Exception exception){
            System.out.println(exception.toString());
        }
        return categories;
    }
    public int getCID(String name){
        int cid = 0;
        ArrayList<Integer> allCid = new ArrayList<Integer>();
        try {
            Statement myState = connection.createStatement();
            ResultSet result = myState.executeQuery("SELECT ID FROM kategorien WHERE Name =" + "'" + name + "'");
            while (result.next()){
                allCid.add(Integer.parseInt(result.getString("ID")));
                cid = allCid.get(0);
            }
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return cid;
    }
    public int getSID(String name){
        int sid = 0;
        ArrayList<Integer> allSid = new ArrayList<Integer>();
        try {
            Statement myState = connection.createStatement();
            ResultSet result = myState.executeQuery("SELECT ID FROM sektor WHERE Name =" + "'" + name + "'");
            while (result.next()){
                allSid.add(Integer.parseInt(result.getString("ID")));
                sid = allSid.get(0);
            }
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return sid;
}
    public int getPID(String name){
        int pid = 0;
        ArrayList<Integer> allPid = new ArrayList<Integer>();
        try {
            Statement myState = connection.createStatement();
            ResultSet result = myState.executeQuery("SELECT ID FROM projekte WHERE Name =" + "'" + name + "'");
            while (result.next()){
                allPid.add(Integer.parseInt(result.getString("ID")));
                pid = allPid.get(0);
            }
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return pid;
    }
    public ArrayList<String> getSektor(int categoryID){
        ArrayList<String> sectors = new ArrayList<String>();
        ArrayList<String> sectorsID = new ArrayList<String>();
        try {
            Statement myState = connection.createStatement();
            ResultSet result = myState.executeQuery("SELECT * FROM sektor WHERE KID =" + categoryID);
            while (result.next()){
                //TODO Name und ID verknüpfen??
                sectors.add(result.getString("Name"));
                sectorsID.add(result.getString("ID"));
            }
        }catch(Exception exc){
            exc.printStackTrace();
        }
        return sectors;
    }
    //sucht nach Projekt anhand von Sektor oder Kategorie ID
    public ArrayList<String> getProjectsBySid(int  SID){
        ArrayList<String> projects = new ArrayList<String>();
        ArrayList<String> projectsID = new ArrayList<String>();
        try {
            Statement myState = connection.createStatement();
            ResultSet result = myState.executeQuery("SELECT * FROM projekte WHERE SID =" + SID);
            while (result.next()){
                //TODO Name und ID verknüpfen??
                projects.add(result.getString("Name"));
                projectsID.add(result.getString("ID"));
            }
        }catch(Exception exc){
            logger.error("Fehler bei der DB Abfrage: ", exc);
        }
        return projects;
    }
    public ArrayList<String> getProjectsByCid(int CID){
        ArrayList<String> projects = new ArrayList<String>();
        try{
            Statement myState = connection.createStatement();
            ResultSet result = myState.executeQuery("SELECT * FROM projekte WHERE KID =" + CID);
            while (result.next()){
                projects.add(result.getString("Name"));
            }

        }catch (Exception exception){
            logger.error("Fehler bei der DB Abfrage: ", exception);
        }
        return projects;
    }
    //Daten abfragen und in website.database.Data speichern
    public void getData(String PID){
        String ort;
        String bauherr;
        String zeitraum;
        String massnahmen;
        String bauvolumen;
        String leistung;
        String architekt;
        byte[]  picture;
        Data daten = Data.getInstance();
        try {
            Statement myState = connection.createStatement();
            ResultSet result = myState.executeQuery("SELECT * FROM daten WHERE PID =" + PID);
            while (result.next()){
                ort = result.getString("ort");
                bauherr = result.getString("bauherr");
                zeitraum = result.getString("zeitraum");
                bauvolumen = result.getString("bauvolumen");
                leistung = result.getString("leistung");
                massnahmen = result.getString("Massnahmen");
                architekt = result.getString("architekt");
                picture = result.getBytes("Bilder");

                daten.setOrt(ort);
                daten.setBauherr(bauherr);
                daten.setZeitraum(zeitraum);
                daten.setBauvolumen(bauvolumen);
                daten.setLeistung(leistung);
                daten.setMassnahmen(massnahmen);
                daten.setArchitekt(architekt);
                daten.setPicture(picture);

            }
        }catch(Exception exc){
            System.out.println(exc.toString());
        }
    }
//Abfragen Ende

//Inserts
    //neuen Datensatz anlegen
    public void insertTextData(String bauherr, String architekt, String ort, String zeitraum, String bauvolumen,
                               String leistung, String massnahmen, int pid){
        try {
            Statement myState = connection.createStatement();
            ResultSet result = myState.executeQuery("INSERT INTO daten (id, bauherr, architekt, ort, zeitraum, " +
                    "bauvolumen, leistung, massnahmen, pid) VALUES(0, " + bauherr + ", "  + architekt + ", "  +
                    ort + ", " + zeitraum + ", "  + bauvolumen + ", " + leistung + ", " + massnahmen + ", " + pid + ");");

        }catch (Exception exc){
            logger.error("Fehler Datenbank Insert: ", exc);
        }
}
    public void insertImages(File image, int pid) {
        try {
            FileInputStream is = new FileInputStream(image);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO daten (Bilder, pid) VALUES" + image + "," + pid + ");");
            statement.setBinaryStream(1, is, image.length());
            statement.execute();
        } catch (Exception exc) {
            logger.error("Fehler Datenbank Insert: ", exc);
        }
    }
}