package de.website.Bean;

        import de.website.database.Nutzer;

        import javax.annotation.PostConstruct;
        import javax.faces.bean.ManagedBean;
        import javax.faces.bean.RequestScoped;
        import javax.faces.event.AjaxBehaviorEvent;
        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by Nick on 03.03.2016.
 */

@ManagedBean(name = "sectors")
@RequestScoped
public class SectorBean {

    private String selectedItem; // +getter +setter
    private List<String> sec; // +getter (no setter necessary)
    Exchange ex = Exchange.getInstance();
    Nutzer nutzer = Nutzer.getInstance();
    DbQuery dbCon = nutzer.getDbCon();

    @PostConstruct
    public void init() {
        sec = new ArrayList<String>();
        sec = dbCon.getSektor(ex.getCid());
    }

    public void ajaxListener(AjaxBehaviorEvent event) {
        int sid = dbCon.getSID(selectedItem);
        ex.setSid(sid);

    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public List<String> getSec() {
        return sec;
    }

    public void setSec(List<String> sec) {
        this.sec = sec;
    }
}
