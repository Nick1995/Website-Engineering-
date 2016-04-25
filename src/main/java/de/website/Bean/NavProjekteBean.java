//package de.website.Bean;
//
//import de.website.database.Nutzer;
//
//import javax.annotation.PostConstruct;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Nick on 19.04.2016.
// */
//@ManagedBean(name = "navProjekteBean")
//@RequestScoped
//public class NavProjekteBean {
//    List<String> sektoren; // +getter (no setter necessary)
//    Exchange ex = Exchange.getInstance();
//    Nutzer nutzer = Nutzer.getInstance();
//    DbQuery dbCon = nutzer.getDbCon();
//    String refreshJs="onDocumentReady()";
//
//    @PostConstruct
//    public void init() {
//        sektoren = new ArrayList<String>();
//        sektoren = dbCon.getSektor(ex.getCid());
//    }
//
//    public void
//
//
//}
