package de.website.Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

/**
 * Created by Nick on 09.03.2016.
 */

@ManagedBean(name = "navigate2Projekte", eager = true)
@RequestScoped
public class Navigate2ProjekteBean implements Serializable {

    public String moveToProjekte(String cid) {

        Exchange ex = Exchange.getInstance();
        ex.setCid(Integer.parseInt(cid));
            return "displayProjekte";
    }
}

