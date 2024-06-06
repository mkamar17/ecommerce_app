package com.maryam.ecommerce;

public class Shopper {

    String username;
    String password;

    public Shopper(String username, String password){
        this.username = username;
        this.password = password;
    }

    public boolean isValid(String username, String password){
        return true;
    }
}
