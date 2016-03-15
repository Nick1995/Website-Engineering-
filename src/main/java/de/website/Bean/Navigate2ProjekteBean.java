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

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    @ManagedProperty(value = "#{param.pageId}")

    private String pageId;

    public String moveToProjekte() {

        if (pageId.equals("1")) {
            return "displayProjekte";
        } else {
            return "home";
        }
    }
}

