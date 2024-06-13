package com.maryam.ecommerce;

public class Shopper {

    private String firstname, lastname, username, password;

    public Shopper(String firstname, String lastname, String username, String password){
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    public boolean login(String username, String password){
        return (this.username == username) && (this.password == password);
    }
}
