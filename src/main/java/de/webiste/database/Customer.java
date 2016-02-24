package de.webiste.database;

import java.util.Date;

/**
 * Created by Tobi on 24.02.2016.
 */
public class Customer {

    public String name;
    public String address;
    public Date created_date;


    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public long customerID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }


        //getter and setter methods
}
