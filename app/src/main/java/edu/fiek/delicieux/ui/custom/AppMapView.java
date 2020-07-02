package edu.fiek.delicieux.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;

public class AppMapView extends MapView {


    public AppMapView(Context context) {
        super(context);
    }

    public AppMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AppMapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public AppMapView(Context context, GoogleMapOptions googleMapOptions) {
        super(context, googleMapOptions);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                System.out.println("unlocked");
                this.getParent().requestDisallowInterceptTouchEvent(false);
                break;

            case MotionEvent.ACTION_DOWN:
                System.out.println("locked");
                this.getParent().requestDisallowInterceptTouchEvent(true);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
