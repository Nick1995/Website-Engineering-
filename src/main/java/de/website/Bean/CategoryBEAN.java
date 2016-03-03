package de.website.Bean;

import de.webiste.database.Nutzer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 03.03.2016.
 */

@ManagedBean(name = "categories")
@RequestScoped
public class CategoryBean {

    private String selectedItem; // +getter +setter
    private List<String> categories; // +getter (no setter necessary)

    Nutzer nutzer = Nutzer.getInstance();
    DbQuery dbCon =  nutzer.getDbCon();

    @PostConstruct
    public void init() {
        categories = new ArrayList<String >();
        categories = dbCon.getCategories();
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
