package de.website.Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean (name = "navigationController")
@SessionScoped
public class NavigationController {
    public String processStartseite(){
        return "home.xhtml";
    }
}

