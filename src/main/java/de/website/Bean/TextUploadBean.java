package de.website.Bean;

import de.website.database.Nutzer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Nick on 04.03.2016.
 */
@ManagedBean(name = "textUpdate")
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
    int pid =ex.getPid();
    public void insertData(){
        dbCon.insertTextData(bauherr, architekt, ort, startzeit, endzeit, bauvolumen, leistung, massnahmen,pid);
        dbCon.deleteNullImages();
    }

    public String getOrt() {
        return (dbCon.getStoredValue("ort", "daten", Integer.toString(pid)));
    }

    public String getBauherr() {
        return dbCon.getStoredValue("bauherr", "daten", Integer.toString(pid));
    }

    public String getStartzeit() {
        return dbCon.getStoredValue("startzeit", "daten", Integer.toString(pid));
    }

    public String getEndzeit() {
        return dbCon.getStoredValue("endzeit", "daten", Integer.toString(pid));
    }

    public String getMassnahmen() {
        return dbCon.getStoredValue("massnahmen", "daten", Integer.toString(pid));
    }

    public String getBauvolumen() {
        return dbCon.getStoredValue("bauvolumen", "daten", Integer.toString(pid));
    }

    public String getLeistung() {
        return dbCon.getStoredValue("leistung", "daten", Integer.toString(pid));
    }

    public String getArchitekt() {
        return dbCon.getStoredValue("architekt", "daten", Integer.toString(pid));
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
