package io.dcloud.js.map.amap.overlay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import io.dcloud.js.map.amap.adapter.AMapR;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class RouteOverlay {
    public Bitmap busBit;
    public Bitmap driveBit;
    public Bitmap endBit;
    public Marker endMarker;
    public LatLng endPoint;
    public AMap mAMap;
    public Context mContext;
    public Bitmap startBit;
    public Marker startMarker;
    public LatLng startPoint;
    public Bitmap walkBit;
    public List<Marker> stationMarkers = new ArrayList();
    public List<Polyline> allPolyLines = new ArrayList();
    public boolean nodeIconVisible = true;

    public RouteOverlay(Context context) {
        this.mContext = context;
    }

    private void destroyBit() {
        Bitmap bitmap = this.startBit;
        if (bitmap != null) {
            bitmap.recycle();
            this.startBit = null;
        }
        Bitmap bitmap2 = this.endBit;
        if (bitmap2 != null) {
            bitmap2.recycle();
            this.endBit = null;
        }
        Bitmap bitmap3 = this.busBit;
        if (bitmap3 != null) {
            bitmap3.recycle();
            this.busBit = null;
        }
        Bitmap bitmap4 = this.walkBit;
        if (bitmap4 != null) {
            bitmap4.recycle();
            this.walkBit = null;
        }
        Bitmap bitmap5 = this.driveBit;
        if (bitmap5 != null) {
            bitmap5.recycle();
            this.driveBit = null;
        }
    }

    public void addPolyLine(PolylineOptions polylineOptions) {
        Polyline polylineAddPolyline;
        if (polylineOptions == null || (polylineAddPolyline = this.mAMap.addPolyline(polylineOptions)) == null) {
            return;
        }
        this.allPolyLines.add(polylineAddPolyline);
    }

    public void addStartAndEndMarker() {
        this.startMarker = this.mAMap.addMarker(new MarkerOptions().position(this.startPoint).icon(getStartBitmapDescriptor()).title("起点"));
        this.endMarker = this.mAMap.addMarker(new MarkerOptions().position(this.endPoint).icon(getEndBitmapDescriptor()).title("终点"));
    }

    public void addStationMarker(MarkerOptions markerOptions) {
        Marker markerAddMarker;
        if (markerOptions == null || (markerAddMarker = this.mAMap.addMarker(markerOptions)) == null) {
            return;
        }
        this.stationMarkers.add(markerAddMarker);
    }

    public BitmapDescriptor getBusBitmapDescriptor() {
        return BitmapDescriptorFactory.fromResource(AMapR.AMAP_DRAWABLE_AMAP_BUS);
    }

    public int getBusColor() {
        return Color.parseColor("#537edc");
    }

    public BitmapDescriptor getDriveBitmapDescriptor() {
        return BitmapDescriptorFactory.fromResource(AMapR.AMAP_DRAWABLE_AMAP_CAR);
    }

    public int getDriveColor() {
        return Color.parseColor("#537edc");
    }

    public BitmapDescriptor getEndBitmapDescriptor() {
        return BitmapDescriptorFactory.fromResource(AMapR.AMAP_DRAWABLE_AMAP_END);
    }

    public LatLngBounds getLatLngBounds() {
        LatLngBounds.Builder builder = LatLngBounds.builder();
        builder.include(new LatLng(this.startPoint.latitude, this.startPoint.longitude));
        builder.include(new LatLng(this.endPoint.latitude, this.endPoint.longitude));
        return builder.build();
    }

    public float getRouteWidth() {
        return 18.0f;
    }

    public BitmapDescriptor getStartBitmapDescriptor() {
        return BitmapDescriptorFactory.fromResource(AMapR.AMAP_DRAWABLE_AMAP_START);
    }

    public BitmapDescriptor getWalkBitmapDescriptor() {
        return BitmapDescriptorFactory.fromResource(AMapR.AMAP_DRAWABLE_AMAP_MAN);
    }

    public int getWalkColor() {
        return Color.parseColor("#6db74d");
    }

    public void removeFromMap() {
        Marker marker = this.startMarker;
        if (marker != null) {
            marker.remove();
        }
        Marker marker2 = this.endMarker;
        if (marker2 != null) {
            marker2.remove();
        }
        Iterator<Marker> it = this.stationMarkers.iterator();
        while (it.hasNext()) {
            it.next().remove();
        }
        Iterator<Polyline> it2 = this.allPolyLines.iterator();
        while (it2.hasNext()) {
            it2.next().remove();
        }
        destroyBit();
    }

    public void setNodeIconVisibility(boolean z) {
        try {
            this.nodeIconVisible = z;
            if (this.stationMarkers == null || this.stationMarkers.size() <= 0) {
                return;
            }
            for (int i = 0; i < this.stationMarkers.size(); i++) {
                this.stationMarkers.get(i).setVisible(z);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void zoomToSpan() {
        if (this.startPoint == null || this.mAMap == null) {
            return;
        }
        try {
            this.mAMap.animateCamera(CameraUpdateFactory.newLatLngBounds(getLatLngBounds(), 100));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
