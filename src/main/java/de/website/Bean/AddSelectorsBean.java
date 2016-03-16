package de.website.Bean;

import de.website.database.Nutzer;
import org.apache.log4j.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Nick on 16.03.2016.
 */
@ManagedBean(name = "addSelectorBean")
@RequestScoped
public class AddSelectorsBean {
    Exchange ex = Exchange.getInstance();
    Nutzer nutzer = Nutzer.getInstance();
    DbQuery dbCon = nutzer.getDbCon();
    String selector;

    public void addSector(){
        String sector = getSelector();
        int kid = ex.getCid();
        dbCon.newSector(sector, kid);
    }
    public void addProject(){
        String project = getSelector();
        int kid = ex.getCid();
        int sid = ex.getSid();

        if(sid == 0) {
            dbCon.newProjectKid(project, kid);
        }else {
            dbCon.newProjectSid(project, sid);
        }
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public String getSelector() {
        return selector;
    }
}

