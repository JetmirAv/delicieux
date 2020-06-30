package edu.fiek.delicieux.model;

public class CookBook {

    String name;

    Integer imageUrl;

    public CookBook(String name, Integer imageUrl) {
        this.name = name;

        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }
}
