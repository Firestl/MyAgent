package io.dcloud.js.map.amap.adapter;

import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;

/* JADX INFO: loaded from: classes3.dex */
public class MapPoint {
    public float mLatitude;
    public float mLongitude;

    public MapPoint(String str, String str2) {
        this.mLongitude = Float.parseFloat(str2);
        this.mLatitude = Float.parseFloat(str);
    }

    public LatLng getLatLng() {
        try {
            return new LatLng(this.mLatitude, this.mLongitude);
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            return new LatLng(39.90403d, 116.407525d);
        }
    }

    public LatLonPoint getLatLngPoint() {
        try {
            return new LatLonPoint(this.mLatitude, this.mLongitude);
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            return new LatLonPoint(39.90403d, 116.407525d);
        }
    }

    public double getLatitude() {
        return this.mLatitude;
    }

    public double getLongitude() {
        return this.mLongitude;
    }

    public void setLatitude(String str) {
        this.mLatitude = Float.parseFloat(str);
    }

    public void setLongitude(String str) {
        this.mLongitude = Float.parseFloat(str);
    }
}
