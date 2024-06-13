package com.maryam.ecommerce;

public class Shopper {

    private String firstname, lastname, username, password;

    public Shopper(String firstname, String lastname, String username, String password){
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    public String getFirstname(){return firstname;}
    public String getLastname(){return lastname;}
    public String getUsername(){return username;}
    public String getPassword(){return password;}

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

}
