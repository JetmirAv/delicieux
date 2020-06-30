package edu.fiek.delicieux.models;

public class Ingredients {

    String name;
    String quantity;

    public Ingredients(String name, String quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public Ingredients(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
