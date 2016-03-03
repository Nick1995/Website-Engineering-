package de.website.Bean;

/**
 * Created by Nick on 03.03.2016.
 */
public class Exchange {

    private int cid;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    //get instance
    private static Exchange instance = null;
    protected Exchange() {
    }
    public static Exchange getInstance() {
        if (instance == null) {
            instance = new Exchange();
        }
        return instance;
    }
}

