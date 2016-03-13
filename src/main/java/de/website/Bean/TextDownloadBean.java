package de.website.Bean;

import de.website.database.Nutzer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Nick on 12.03.2016.
 */
@ManagedBean(name = "textDownload")
@RequestScoped
public class TextDownloadBean {
    Nutzer nutzer = Nutzer.getInstance();
    DbQuery dbCon = nutzer.getDbCon();
    Exchange ex = Exchange.getInstance();

    public String getProjektName(String pid) {
        return (dbCon.getStoredValue("Name", "projekte", pid));
    }

    public String getOrt(String pid) {
        return (dbCon.getStoredValue("ort", "daten", pid));
    }

    public String getBauherr(String pid) {
        return dbCon.getStoredValue("bauherr", "daten", pid);
    }

    public String getStartzeit(String pid) {
        return dbCon.getStoredValue("startzeit", "daten", pid);
    }

    public String getEndzeit(String pid) {
        return dbCon.getStoredValue("endzeit", "daten", pid);
    }

    public String getMassnahmen(String pid) {
        return dbCon.getStoredValue("massnahmen", "daten", pid);
    }

    public String getBauvolumen(String pid) {
        return dbCon.getStoredValue("bauvolumen", "daten", pid);
    }

    public String getLeistung(String pid) {
        return dbCon.getStoredValue("leistung", "daten", pid);
    }

    public String getArchitekt(String pid) {
        return dbCon.getStoredValue("architekt", "daten", pid);
    }
}
