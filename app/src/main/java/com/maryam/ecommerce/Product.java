package com.maryam.ecommerce;

import java.util.ArrayList;

public class Product {

    private String productName, foodGroup, cuisine;
    private ArrayList<String> dietPref;

    public Product(String productName, String foodGroup, ArrayList<String> dietPref, String cuisine){
        this.productName = productName;
        this.foodGroup = foodGroup;
        this.dietPref = dietPref;
        this.cuisine = cuisine;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFoodGroup() {
        return foodGroup;
    }

    public void setFoodGroup(String foodGroup) {
        this.foodGroup = foodGroup;
    }

    public ArrayList<String> getDietPref() {
        return dietPref;
    }

    public void setDietPref(ArrayList<String> dietPref) {
        this.dietPref = dietPref;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }
}
