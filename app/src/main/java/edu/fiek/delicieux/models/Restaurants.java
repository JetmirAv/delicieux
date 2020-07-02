package edu.fiek.delicieux.models;

import com.google.android.gms.maps.model.LatLng;

public class Restaurants {

    String name;
    String location;
    String id;

    public Restaurants(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Restaurants(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getLocation() {
        String[] loc = location.split(",");
        return new LatLng(Double.parseDouble(loc[0].trim()), Double.parseDouble(loc[1].trim()));
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
