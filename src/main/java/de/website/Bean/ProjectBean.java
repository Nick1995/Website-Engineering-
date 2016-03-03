package de.website.Bean;

import de.website.database.Nutzer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 03.03.2016.
 */

@ManagedBean(name = "project")
@RequestScoped
public class ProjectBean {

    private String selectedItem; // +getter +setter
    private List<String> pro; // +getter (no setter necessary)

    Nutzer nutzer = Nutzer.getInstance();
    DbQuery dbCon =  nutzer.getDbCon();

    @PostConstruct
    public void init() {
        pro = new ArrayList<String >();
        pro = dbCon.getCategories();
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public List<String> getPro() {
        return pro;
    }

    public void setPro(List<String> pro) {
        this.pro = pro;
    }
}
