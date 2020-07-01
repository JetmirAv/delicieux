package edu.fiek.delicieux.models;

import java.util.List;

public class RecipesFood {

    String title;
    String description;
    String media;
    List<Ingredients> ingridients;

    public RecipesFood(String title, String description, String media, List<Ingredients> ingridients) {
        this.title = title;
        this.description = description;
        this.media = media;
        this.ingridients = ingridients;
    }

    public RecipesFood() {
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

    public List<Ingredients> getIngridients() {
        return ingridients;
    }

    public void setIngridients(List<Ingredients> ingridients) {
        this.ingridients = ingridients;
    }
}
