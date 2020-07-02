package edu.fiek.delicieux.helpers;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class ClusterLatLng implements ClusterItem {

    private final LatLng positon;
    private final String title;

    public ClusterLatLng(LatLng positon, String title) {
        this.positon =  positon;
        this.title = title;
    }

    @Override
    public LatLng getPosition() {
        return positon;
    }

    public String getTitle() {
        return title;
    }
}
