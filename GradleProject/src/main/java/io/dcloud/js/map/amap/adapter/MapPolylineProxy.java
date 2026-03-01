package io.dcloud.js.map.amap.adapter;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MapPolylineProxy {
    public String mJsId;
    public ArrayList<MapPoint> mMapPoints;
    public int mStrokeColor = -16777216;
    public float mStrokeOpacity = 1.0f;
    public int mLineWidth = 5;
    public Polyline mMapPolylineImpl = null;

    public MapPolylineProxy(ArrayList<MapPoint> arrayList) {
        this.mMapPoints = arrayList;
    }

    private int combineOpacity(int i, double d) {
        return (((int) (d * 255.0d)) << 24) + i;
    }

    private List<LatLng> createRectangle() {
        ArrayList arrayList = new ArrayList();
        ArrayList<MapPoint> arrayList2 = this.mMapPoints;
        if (arrayList2 != null && !arrayList2.isEmpty()) {
            Iterator<MapPoint> it = this.mMapPoints.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getLatLng());
            }
        }
        return arrayList;
    }

    private PolylineOptions getNewGraphic() {
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.width(this.mLineWidth);
        float f = this.mStrokeOpacity;
        if (f != 1.0f) {
            polylineOptions.color(combineOpacity(this.mStrokeColor, f));
        } else {
            polylineOptions.color(this.mStrokeColor);
        }
        polylineOptions.addAll(createRectangle());
        return polylineOptions;
    }

    public float getLineWidth() {
        return this.mLineWidth;
    }

    public Polyline getPolyline() {
        return this.mMapPolylineImpl;
    }

    public int getStrokeColor() {
        return this.mStrokeColor;
    }

    public float getStrokeOpacity() {
        return this.mStrokeOpacity;
    }

    public void initMapPolyline(DHMapView dHMapView) {
        this.mMapPolylineImpl = dHMapView.getMap().addPolyline(getNewGraphic());
    }

    public void setLineWidth(int i) {
        this.mLineWidth = i;
        Polyline polyline = this.mMapPolylineImpl;
        if (polyline != null) {
            polyline.setWidth(i);
        }
    }

    public void setPath(ArrayList<MapPoint> arrayList) {
        this.mMapPoints = arrayList;
        Polyline polyline = this.mMapPolylineImpl;
        if (polyline != null) {
            polyline.setPoints(createRectangle());
        }
    }

    public void setStrokeColor(int i) {
        int i2 = i | (-2013265920);
        this.mStrokeColor = i2;
        Polyline polyline = this.mMapPolylineImpl;
        if (polyline != null) {
            polyline.setColor(combineOpacity(i2, this.mStrokeOpacity));
        }
    }

    public void setStrokeOpacity(float f) {
        this.mStrokeOpacity = f;
        Polyline polyline = this.mMapPolylineImpl;
        if (polyline != null) {
            polyline.setColor(combineOpacity(this.mStrokeColor, f));
        }
    }
}
