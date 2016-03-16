package de.website.Bean;

import de.website.database.Data;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Nick on 29.02.2016.
 */
public class DbQuery {

    final static Logger logger = Logger.getLogger(DbQuery.class);
    Exchange ex = Exchange.getInstance();
    int imageID;

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
    public InputStream imageIs() {
        try {
            String strSql = "select bilder from bilder where id = 1 order by id";
            //System.err.println("*select all***" + strSql);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(strSql);
            if (rs.next()) {
                InputStream is = rs.getBinaryStream("bilder");
                return is;
            }else return null;

        } catch (Exception e) {
            logger.error("Fehler bei der Datenbankabfrage: ", e);
            return null;
        }

    }
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
    public ArrayList<byte[]> displayImages(int pid){
        Exchange ex = Exchange.getInstance();
        Connection con = ex.getConnection();
        ArrayList<byte[]> images = new ArrayList<byte[]>();
        try {
            Statement state = con.createStatement();
            ResultSet res = state.executeQuery("SELECT * FROM bilder Where iid = " + pid);
            while (res.next()){
                images.add(res.getBytes("bilder"));
                return images;
            }
        }catch (Exception e){
            logger.error("Fehler DB-Abfrage Bilder: ", e);
            return null;
        }
        return null;
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
    public String getStoredValue(String type, String table , String pid){
        String value = "";
        try {
            Statement myState = connection.createStatement();
            ResultSet result = myState.executeQuery("SELECT " + type +" FROM " + table +" WHERE ID =" + pid + ";");
            while (result.next()) {
                value = result.getString(type);
            }
        }catch (Exception e){
            logger.error("Fehler Datenbank-Abfrage: ", e);
        }
        return value;
    }
    //TODO: prüfen ob Projekt gleichen Namens bereits existiert --funktioniert noch nicht
    public boolean checkIfProjetExists(String name){
        try{
            Statement myState = connection.createStatement();
            ResultSet result = myState.executeQuery("SELECT IID FROM daten WHERE NAME =" + name + ";");
            if (result != null){
                while (result.next()){
                    imageID = result.getInt("IID");
                }
                return true;
            }else return false;
        } catch (Exception exc){
            logger.error("Fehler Datenbank Insert: ", exc);
            return false;
    }
    }
//Abfragen Ende

//Inserts
    //neuen Sektor anlegen
    public void newSector(String sector, int kid){
        String strSql = "INSERT INTO sektor (Name, KID) VALUES ('" + sector + "', '" +  kid + "')";
        try {
            PreparedStatement prepState = connection.prepareStatement(strSql);
            prepState.execute();
        }catch (SQLException e){
            logger.error("Fehler Sektor anlegen", e);
        }
    }
    //neues Projekt anlegen
    public void newProjectKid(String project, int kid){
        String strSql = "INSERT INTO projekte (Name, KID) VALUES ('" + project + "', '" +  kid + "')";
        try {
            PreparedStatement prepState = connection.prepareStatement(strSql);
            prepState.execute();
        }catch (SQLException e){
            logger.error("Fehler Sektor anlegen", e);
        }
    }
    public void newProjectSid(String project, int sid){
        String strSql = "INSERT INTO projekte (Name, SID) VALUES ('" + project + "', '" +  sid + "')";
        try {
            PreparedStatement prepState = connection.prepareStatement(strSql);
            prepState.execute();
        }catch (SQLException e){
            logger.error("Fehler Sektor anlegen", e);
        }
    }
    //neuen Datensatz anlegen
    public void insertTextData(String bauherr, String architekt, String ort, String startzeit, String endzeit,String bauvolumen,
                               String leistung, String massnahmen, int pid){
        try {
            PreparedStatement prepState = connection.prepareStatement("INSERT INTO daten (bauherr, architekt, ort, startzeit, endzeit, " +
                    "bauvolumen, leistung, massnahmen, ID) VALUES( '" + bauherr + "', '"  + architekt + "', '"  +
                    ort + "', '" + startzeit + "', '"  + endzeit + "', '"  + bauvolumen + "', '" + leistung + "', '" + massnahmen + "', '" + pid + "');");
            prepState.execute();

        }catch(Exception exc){
            try {
                PreparedStatement prepState = connection.prepareStatement("UPDATE daten SET bauherr = '" + bauherr + "', architekt = '" + architekt + "', ort ='" + ort + "', startzeit = '" +
                        startzeit + "', endzeit = '" + endzeit + "', bauvolumen = '" + bauvolumen + "', leistung = '" + leistung + "', massnahmen = '" + massnahmen + "' WHERE ID = '" + pid + "';");
                prepState.execute();
            }catch (Exception exception){
                logger.error("Fehler Datenbank-Update: ", exception);
            }
        }
}
//Delete
    public void deleteNullImages(){
        try{
            PreparedStatement preState = connection.prepareStatement("delete from bilder where bilder = ''");
            preState.execute();

        }catch (Exception e){
            logger.error("Fehler beim Löschen: ", e);
        }
    }
    //Getter and Setter

    public int getImageID() {
        return imageID;
    }
}