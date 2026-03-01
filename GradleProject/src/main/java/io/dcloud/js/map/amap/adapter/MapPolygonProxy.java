package io.dcloud.js.map.amap.adapter;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Polygon;
import com.amap.api.maps.model.PolygonOptions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MapPolygonProxy {
    public String mJsId;
    public ArrayList<MapPoint> mMapPoints;
    public int mStrokeColor = -16777216;
    public float mStrokeOpacity = 1.0f;
    public float mLineWidth = 5.0f;
    public int mFillColor = 0;
    public float mFillOpacity = 0.0f;
    public Polygon mMapPolygonImpl = null;

    public MapPolygonProxy(ArrayList<MapPoint> arrayList) {
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

    private PolygonOptions getPolygonOptions() {
        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.addAll(createRectangle());
        polygonOptions.strokeColor(combineOpacity(this.mStrokeColor, this.mStrokeOpacity));
        polygonOptions.fillColor(combineOpacity(this.mFillColor, this.mFillOpacity));
        polygonOptions.strokeWidth(this.mLineWidth);
        return polygonOptions;
    }

    public int getFillColor() {
        return this.mFillColor;
    }

    public float getFillOpacity() {
        return this.mFillOpacity;
    }

    public float getLineWidth() {
        return this.mLineWidth;
    }

    public Polygon getPolygon() {
        return this.mMapPolygonImpl;
    }

    public int getStrokeColor() {
        return this.mStrokeColor;
    }

    public float getStrokeOpacity() {
        return this.mStrokeOpacity;
    }

    public void initMapPolygon(DHMapView dHMapView) {
        this.mMapPolygonImpl = dHMapView.getMap().addPolygon(getPolygonOptions());
    }

    public void setFillColor(int i) {
        int i2 = i | (-16777216);
        this.mFillColor = i2;
        Polygon polygon = this.mMapPolygonImpl;
        if (polygon != null) {
            polygon.setFillColor(combineOpacity(i2, this.mFillOpacity));
        }
    }

    public void setFillOpacity(float f) {
        this.mFillOpacity = f;
        Polygon polygon = this.mMapPolygonImpl;
        if (polygon != null) {
            polygon.setFillColor(combineOpacity(this.mFillColor, f));
        }
    }

    public void setLineWidth(float f) {
        this.mLineWidth = f;
        Polygon polygon = this.mMapPolygonImpl;
        if (polygon != null) {
            polygon.setStrokeWidth(f);
        }
    }

    public void setPath(ArrayList<MapPoint> arrayList) {
        this.mMapPoints = arrayList;
        Polygon polygon = this.mMapPolygonImpl;
        if (polygon != null) {
            polygon.setPoints(createRectangle());
        }
    }

    public void setStrokeColor(int i) {
        int i2 = i - 16777216;
        this.mStrokeColor = i2;
        Polygon polygon = this.mMapPolygonImpl;
        if (polygon != null) {
            polygon.setStrokeColor(combineOpacity(i2, this.mStrokeOpacity));
        }
    }

    public void setStrokeOpacity(float f) {
        this.mStrokeOpacity = f;
        Polygon polygon = this.mMapPolygonImpl;
        if (polygon != null) {
            polygon.setStrokeColor(combineOpacity(this.mStrokeColor, f));
        }
    }
}
