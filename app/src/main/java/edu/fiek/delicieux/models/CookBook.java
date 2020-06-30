package edu.fiek.delicieux.models;

import java.util.List;

public class CookBook {

    String title;
    String description;
    String media;
    List<String> recipes;
    String restaurantId;


    public CookBook(String title, String description, String media, List<String> recipes, String restaurantId) {
        this.title = title;
        this.description = description;
        this.media = media;
        this.recipes = recipes;
        this.restaurantId = restaurantId;
    }

    public CookBook() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public List<String> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<String> recipes) {
        this.recipes = recipes;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }
}
