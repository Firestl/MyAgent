package io.dcloud.js.map.amap.adapter;

import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;

/* JADX INFO: loaded from: classes3.dex */
public class MapCircleProxy {
    public MapPoint mCenter;
    public double mFillOpacity;
    public String mJsId;
    public double mRadius;
    public int mStrokeColor = -16777216;
    public double mStrokeOpacity = 1.0d;
    public int mFillColor = -16777216;
    public double mLineWidth = 5.0d;
    public Circle mMapCircle = null;

    public MapCircleProxy(MapPoint mapPoint, double d) {
        this.mCenter = mapPoint;
        this.mRadius = d;
    }

    private int combineOpacity(int i, double d) {
        return (((int) (d * 255.0d)) << 24) + i;
    }

    private CircleOptions getCircleOptions() {
        return new CircleOptions().center(this.mCenter.getLatLng()).radius(this.mRadius).strokeColor(combineOpacity(this.mStrokeColor, this.mStrokeOpacity)).fillColor(combineOpacity(this.mFillColor, this.mFillOpacity)).strokeWidth((int) this.mLineWidth);
    }

    public MapPoint getCenter() {
        return this.mCenter;
    }

    public Circle getCircle() {
        return this.mMapCircle;
    }

    public int getFillColor() {
        return this.mFillColor;
    }

    public double getFillOpacity() {
        return this.mFillOpacity;
    }

    public double getLineWidth() {
        return this.mLineWidth;
    }

    public double getRadius() {
        return this.mRadius;
    }

    public int getStrokeColor() {
        return this.mStrokeColor;
    }

    public double getStrokeOpacity() {
        return this.mStrokeOpacity;
    }

    public void initMapCircle(DHMapView dHMapView) {
        this.mMapCircle = dHMapView.getMap().addCircle(getCircleOptions());
    }

    public void setCenter(MapPoint mapPoint) {
        this.mCenter = mapPoint;
        Circle circle = this.mMapCircle;
        if (circle != null) {
            circle.setCenter(mapPoint.getLatLng());
        }
    }

    public void setFillColor(int i) {
        this.mFillColor = i;
        Circle circle = this.mMapCircle;
        if (circle != null) {
            circle.setFillColor(combineOpacity(i, this.mFillOpacity));
        }
    }

    public void setFillOpacity(double d) {
        this.mFillOpacity = d;
        Circle circle = this.mMapCircle;
        if (circle != null) {
            circle.setFillColor(combineOpacity(this.mFillColor, d));
        }
    }

    public void setLineWidth(double d) {
        this.mLineWidth = d;
        Circle circle = this.mMapCircle;
        if (circle != null) {
            circle.setStrokeWidth((int) d);
        }
    }

    public void setRadius(double d) {
        this.mRadius = d;
        Circle circle = this.mMapCircle;
        if (circle != null) {
            circle.setRadius(d);
        }
    }

    public void setStrokeColor(int i) {
        int i2 = i | (-16777216);
        this.mStrokeColor = i2;
        Circle circle = this.mMapCircle;
        if (circle != null) {
            circle.setStrokeColor(combineOpacity(i2, this.mStrokeOpacity));
        }
    }

    public void setStrokeOpacity(double d) {
        this.mStrokeOpacity = d;
        Circle circle = this.mMapCircle;
        if (circle != null) {
            circle.setStrokeColor(combineOpacity(this.mStrokeColor, d));
        }
    }
}
