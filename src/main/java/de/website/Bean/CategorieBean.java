package de.website.Bean;

import de.website.database.Nutzer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 03.03.2016.
 */

@ManagedBean(name = "categories")
@SessionScoped
public class CategorieBean {

    private String selectedItem; // +getter +setter
    private List<String> categories; // +getter (no setter necessary)
    private String cat;

    Nutzer nutzer = Nutzer.getInstance();
    Exchange ex = Exchange.getInstance();
    DbQuery dbCon =  nutzer.getDbCon();
    int i;
    @PostConstruct
    public void init() {
        categories = new ArrayList<String >();
        categories = dbCon.getCategories();
    }
//
    public void ajaxListener(AjaxBehaviorEvent event) {
        int cid = dbCon.getCID(selectedItem);
        ex.setCid(cid);

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

    public void setCat(String cat) {
        this.cat = cat;
    }
}
