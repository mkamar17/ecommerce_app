package com.maryam.ecommerce;

public class Shopper {

    String username;
    String password;

    public Shopper(String username, String password){
        this.username = username;
        this.password = password;
    }

    public boolean login(String username, String password){
        return (this.username == username) && (this.password == password);
    }
}
