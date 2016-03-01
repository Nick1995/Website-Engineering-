package de.website.Bean;

import de.webiste.database.Nutzer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Nick on 29.02.2016.
 */
public class DbQuery {
    Connection connection;
    Nutzer user = Nutzer.getInstance();
    String username = user.getUser();
    String passwd = user.getPassword();
    public DbQuery() {
        try {
            //TODO hier eventuell für Login den Nutzer eintragen
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projektdaten", username, passwd);
        } catch (Exception exception) {
            exception.printStackTrace();
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
    public ArrayList<String> getSektor(String categoryID){
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
    public ArrayList<String> getProjects(String ID){
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
                exception.printStackTrace();
                System.out.println("second Exception: ");
                exc.printStackTrace();
            }
        }
        return projects;
    }
    public void getData(String ID){
        try {
            Statement myState = connection.createStatement();
            ResultSet result = myState.executeQuery("SELECT * FROM sektor WHERE PID =" + ID);
            while (result.next()){
                //TODO wie sollen Daten verarbeitet werden??
            }
        }catch(Exception exc){
            System.out.println(exc.toString());
        }
    }
}
