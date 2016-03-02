package de.website.Bean;

import de.webiste.database.Data;
import de.webiste.database.Nutzer;
import org.apache.log4j.Logger;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Nick on 29.02.2016.
 */
public class DbQuery {

    final static Logger logger = Logger.getLogger(DbQuery.class);

    Connection connection;
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
    public ArrayList<String> getProjects(int ID){
        ArrayList<String> projects = new ArrayList<String>();
        ArrayList<String> projectsID = new ArrayList<String>();
        try {
            Statement myState = connection.createStatement();
            ResultSet result = myState.executeQuery("SELECT * FROM sektor WHERE SID =" + ID);
            while (result.next()){
                //TODO Name und ID verknüpfen??
                projects.add(result.getString("Name"));
                projectsID.add(result.getString("ID"));
            }
        }catch(Exception exc){
            try{
                Statement myState = connection.createStatement();
                ResultSet result = myState.executeQuery("SELECT * FROM sektor WHERE KID =" + ID);

            }catch (Exception exception){
                System.out.println("first Exception: ");
                logger.error("Fehler bei der DB Abfrage: ", exc);
                System.out.println("second Exception: ");
                logger.error("Fehler bei der DB Abfrage: ", exception);
            }
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
    public void insertData(File image,String bauherr, String architekt, String ort, String zeitraum, String bauvolumen,
            String leistung, String massnahmen,int pid){
        try {
            Statement myState = connection.createStatement();
            ResultSet result = myState.executeQuery("INSERT INTO daten (Bilder, bauherr, architekt, ort, zeitraum, " +
                    "bauvolumen, leistung, massnahmen, pid) VALUES(" + image + "," + bauherr + ","  + architekt + ","  +
                    ort + "," + zeitraum + ","  + bauvolumen + "," + leistung + "," + massnahmen + "," + pid + ");");

        }catch (Exception exc){
            logger.error("Fehler Datenbankabfrage: ", exc);
        }
}
}