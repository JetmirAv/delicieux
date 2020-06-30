package edu.fiek.delicieux.model;

public class ShopListItem {

    String name;
    String quantity;
    boolean bought;

    public ShopListItem(String name, String quantity, boolean bought) {
        this.name = name;
        this.quantity = quantity;
        this.bought = bought;
    }

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

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }
}
