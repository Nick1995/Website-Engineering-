package de.website.Bean;

import de.website.database.Nutzer;
import org.apache.log4j.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 16.03.2016.
 */
@ManagedBean(name = "allCat")
@SessionScoped
public class AllCategoriesBean {

    private int categoryID;
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;
    Exchange ex = Exchange.getInstance();
    List<String> cids = new ArrayList<String>();
    Nutzer nutzer = Nutzer.getInstance();
    final static Logger logger = Logger.getLogger(DbQuery.class);

    public List<String> getCids() {
        connect2db();
        Connection con = ex.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select id from kategorien";
            rs = stmt.executeQuery(strSql);
            while (rs.next()){
                cids.add(rs.getString("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return cids;
    }

    private void connect2db() {
        try {
            DbQuery dBcon = new DbQuery("open", "");
            nutzer.setDbCon(dBcon);

        } catch (Exception exc) {
            logger.error("Die Datenbank konnte vom User open nicht erreicht werden.");
            FacesMessage msg = new FacesMessage("Die Datenbank konnte nicht erreicht werden");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

}
