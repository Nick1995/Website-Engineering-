package de.website.Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Nick on 09.03.2016.
 */
@RequestScoped
@ManagedBean(name = "navigate2Projekte")
public class Navigate2ProjekteBean {
    String navigate;

    public void setNavigate(String navigate) {
        this.navigate = navigate;
    }

    public String getNavigate(){
        return "displayProjekte";
    }
}
