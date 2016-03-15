//package de.website.Bean;
//
//import de.website.database.Nutzer;
//import org.apache.log4j.Logger;
//
//import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
//import javax.faces.context.FacesContext;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//
///**
// * Created by Nick on 11.03.2016.
// */
//
//@RequestScoped
//@ManagedBean(name = "kidBean")
//public class KidBean
//{
//
//    Exchange ex = Exchange.getInstance();
//    Nutzer nutzer = Nutzer.getInstance();
//    final static Logger logger = Logger.getLogger(DbQuery.class);
//    String kid;
//    public void getAllKid(){
//        ArrayList<String> kids = new ArrayList<String>();
//        try{
//            connect2db();
////            DbQuery dbCon = nutzer.getDbCon();
////            return dbCon.getCategories();
//            Connection con = ex.getConnection();
//            Statement statement = con.createStatement();
//            ResultSet rs = statement.executeQuery("SELECT id FROM kategorien");
//            while (rs.next()){
//                setKid(rs.getString("id"));
////                kids.add(rs.getString("id"));
//            }
////            return kids;
//
//        }catch (Exception e){
//            logger.error("Fehler Abfrage der KID: ", e);
////            return null;
//        }
//    }
//
//    public String getKid() {
//        return kid;
//    }
//
//    public void setKid(String kid) {
//        this.kid = kid;
//    }
//    private void connect2db() {
//        try {
//            DbQuery dBcon = new DbQuery("open", "");
//            nutzer.setDbCon(dBcon);
//
//        } catch (Exception exc) {
//            logger.error("Die Datenbank konnte vom User open nicht erreicht werden.");
//            FacesMessage msg = new FacesMessage("Die Datenbank konnte nicht erreicht werden");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//        }
//    }
//}
