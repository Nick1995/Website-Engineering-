package de.website.Bean;

import de.website.database.Nutzer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Nick on 04.03.2016.
 */
@ManagedBean(name = "update")
@RequestScoped
public class TextUploadBean {
    private String ort;
    private String bauherr;
    private String startzeit;
    private String endzeit;
    private String massnahmen;
    private String bauvolumen;
    private String leistung;
    private String architekt;
    Nutzer nutzer = Nutzer.getInstance();
    DbQuery dbCon = nutzer.getDbCon();
    Exchange ex = Exchange.getInstance();
    //(bauherr, architekt, ort, startzeit, endzeit, bauvolumen, leistung, massnahmen, ID
    public void insertData(){
        int pid =ex.getPid();
        dbCon.insertTextData(bauherr, architekt, ort, startzeit, endzeit, bauvolumen, leistung, massnahmen,pid);
        dbCon.deleteNullImages();
    }

    public String getOrt() {
        return (dbCon.getStoredValue("ort"));
    }

    public String getBauherr() {
        return dbCon.getStoredValue("bauherr");
    }

    public String getStartzeit() {
        return dbCon.getStoredValue("startzeit");
    }

    public String getEndzeit() {
        return dbCon.getStoredValue("endzeit");
    }

    public String getMassnahmen() {
        return dbCon.getStoredValue("massnahmen");
    }

    public String getBauvolumen() {
        return dbCon.getStoredValue("bauvolumen");
    }

    public String getLeistung() {
        return dbCon.getStoredValue("leistung");
    }

    public String getArchitekt() {
        return dbCon.getStoredValue("architekt");
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public void setBauherr(String bauherr) {
        this.bauherr = bauherr;
    }

    public void setStartzeit(String startzeit) {
        this.startzeit = startzeit;
    }

    public void setEndzeit(String endzeit) {
        this.endzeit = endzeit;
    }

    public void setMassnahmen(String massnahmen) {
        this.massnahmen = massnahmen;
    }

    public void setBauvolumen(String bauvolumen) {
        this.bauvolumen = bauvolumen;
    }

    public void setLeistung(String leistung) {
        this.leistung = leistung;
    }

    public void setArchitekt(String architekt) {
        this.architekt = architekt;
    }
}
