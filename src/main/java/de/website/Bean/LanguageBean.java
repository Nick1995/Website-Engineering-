package de.website.Bean;

/**
 * Created by Tobi on 16.02.2016.
 */

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Scope;


@ManagedBean(name="language")
@SessionScoped
public class LanguageBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Locale locale;

    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public String changeLang(String langCode) {

        Locale test = FacesContext.getCurrentInstance().getViewRoot().getLocale();

        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale (langCode));

        test = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        return null;
    }
}
