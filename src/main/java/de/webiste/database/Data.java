package de.webiste.database;

import java.awt.*;

/**
 * Created by Nick on 01.03.2016.
 */
public class Data {
    String ort;
    String bauherr;
    String zeitraum;
    String massnahmen;
    String bauvolumen;
    String leistung;
    String architekt;
    byte[]  picture;
    int sid;
    int pid;
    int kid;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getKid() {
        return kid;
    }

    public void setKid(int kid) {
        this.kid = kid;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getBauherr() {
        return bauherr;
    }

    public void setBauherr(String bauherr) {
        this.bauherr = bauherr;
    }

    public String getZeitraum() {
        return zeitraum;
    }

    public void setZeitraum(String zeitraum) {
        this.zeitraum = zeitraum;
    }

    public String getMassnahmen() {
        return massnahmen;
    }

    public void setMassnahmen(String massnahmen) {
        this.massnahmen = massnahmen;
    }

    public String getBauvolumen() {
        return bauvolumen;
    }

    public void setBauvolumen(String bauvolumen) {
        this.bauvolumen = bauvolumen;
    }

    public String getLeistung() {
        return leistung;
    }

    public void setLeistung(String leistung) {
        this.leistung = leistung;
    }

    public String getArchitekt() {
        return architekt;
    }

    public void setArchitekt(String architekt) {
        this.architekt = architekt;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }


    //get Instance Methode
    private static Data instance = null;
    protected Data() {
    }
    public static Data getInstance() {
        if (instance == null) {
            instance = new Data();
        }
        return instance;
    }
}
