package io.dcloud.js.map.amap.overlay;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class TraceOverlay {
    public static final int TRACE_STATUS_FAILURE = 3;
    public static final int TRACE_STATUS_FINISH = 2;
    public static final int TRACE_STATUS_PREPARE = 4;
    public static final int TRACE_STATUS_PROCESSING = 1;
    public AMap mAMap;
    public int mDistance;
    public PolylineOptions mOption;
    public Polyline mPolyline;
    public int mWaitTime;
    public List<LatLng> mTracedList = new ArrayList();
    public int mTraceStatus = 4;

    public TraceOverlay(AMap aMap, List<LatLng> list) {
        this.mAMap = aMap;
        options();
        this.mOption.addAll(list);
        this.mPolyline = aMap.addPolyline(this.mOption);
    }

    private PolylineOptions options() {
        if (this.mOption == null) {
            PolylineOptions polylineOptions = new PolylineOptions();
            this.mOption = polylineOptions;
            polylineOptions.setCustomTexture(BitmapDescriptorFactory.fromAsset("tracelinetexture.png"));
            this.mOption.width(40.0f);
        }
        return this.mOption;
    }

    public void add(List<LatLng> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        this.mTracedList.addAll(list);
        options();
        if (this.mPolyline == null) {
            this.mPolyline = this.mAMap.addPolyline(this.mOption);
        }
        this.mPolyline.setPoints(this.mTracedList);
    }

    public int getDistance() {
        return this.mDistance;
    }

    public int getTraceStatus() {
        return this.mTraceStatus;
    }

    public int getWaitTime() {
        return this.mWaitTime;
    }

    public void remove() {
        Polyline polyline = this.mPolyline;
        if (polyline != null) {
            polyline.remove();
        }
    }

    public void setDistance(int i) {
        this.mDistance = i;
    }

    public void setProperCamera(List<LatLng> list) {
        LatLngBounds.Builder builder = LatLngBounds.builder();
        if (list == null || list.size() == 0) {
            return;
        }
        Iterator<LatLng> it = list.iterator();
        while (it.hasNext()) {
            builder.include(it.next());
        }
        try {
            this.mAMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 20));
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
        }
    }

    public void setTraceStatus(int i) {
        this.mTraceStatus = i;
    }

    public void setWaitTime(int i) {
        this.mWaitTime = i;
    }

    public void zoopToSpan() {
        setProperCamera(this.mOption.getPoints());
    }

    public TraceOverlay(AMap aMap) {
        this.mAMap = aMap;
        options();
    }
}
