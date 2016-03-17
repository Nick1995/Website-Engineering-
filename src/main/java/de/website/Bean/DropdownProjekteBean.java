package de.website.Bean;

import de.website.database.Nutzer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 17.03.2016.
 */
@ManagedBean(name = "dropdownProjekteBean")
@RequestScoped
public class DropdownProjekteBean {
    private String selectedItem; // +getter +setter
    private List<String> sektoren; // +getter (no setter necessary)
    Exchange ex = Exchange.getInstance();
    Nutzer nutzer = Nutzer.getInstance();
    DbQuery dbCon = nutzer.getDbCon();
    String refreshJs="onDocumentReady()";

    public String getRefreshJs() {
        return refreshJs;
    }

    @PostConstruct
    public void init() {
        sektoren = new ArrayList<String>();
        sektoren = dbCon.getSektor(ex.getCid());
    }

    public void ajaxListener(AjaxBehaviorEvent event) {
        int sid = dbCon.getSID(selectedItem);
        ex.setSid(sid);

    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public List<String> getSektoren() {
        return sektoren;
    }

    public void setSektoren(List<String> sektoren) {
        this.sektoren = sektoren;
    }
}
