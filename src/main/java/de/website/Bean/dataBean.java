package de.website.Bean;

import de.webiste.database.Data;
import de.webiste.database.Nutzer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 03.03.2016.
 */

@ManagedBean(name = "data")
@RequestScoped
public class DataBean {
    private String cat;
    private String[] catList;
    Nutzer nutzer = Nutzer.getInstance();
    DbQuery dbCon =  nutzer.getDbCon();

    @PostConstruct
//    public void getCat2(){
//        catList = dbCon.getCategories();
////        return catList;
//    }
    public String[] getCatList(){
        List<String> catArr = new ArrayList<String>();
        catArr = dbCon.getCategories();
        catList = new String[catArr.size()];
        for(int i = 0; i < catArr.size(); i++){
            catList[i] = catArr.get(i);
        }
        return catList;
    }
    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }




}
