package de.website.Bean;

/**
 * Created by Tobi on 16.02.2016.
 */

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
@ApplicationScoped
public class LanguageBean implements Serializable {

    private static final long serialVersionUID = 1L;

    public String changeLang(String langCode) {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale (langCode));
        return null;
    }
}
