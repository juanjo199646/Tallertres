package com.i053113.tallertres.Models;

/**
 * Created by JUANJO FAJARDO on 12/10/2017.
 */

public class Users {
    private int id;
    private String name;
    private  String username;
    private String address;
    private String company;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }



    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}
}
