package io.dcloud.js.map.amap.overlay;

import android.content.Context;
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
import com.amap.api.services.busline.BusLineItem;
import com.amap.api.services.busline.BusStationItem;
import com.amap.api.services.core.LatLonPoint;
import io.dcloud.js.map.amap.adapter.AMapR;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class BusLineOverlay {
    public BitmapDescriptor busBit;
    public BitmapDescriptor endBit;
    public AMap mAMap;
    public BusLineItem mBusLineItem;
    public Polyline mBusLinePolyline;
    public ArrayList<Marker> mBusStationMarks = new ArrayList<>();
    public List<BusStationItem> mBusStations;
    public Context mContext;
    public BitmapDescriptor startBit;

    public BusLineOverlay(Context context, AMap aMap, BusLineItem busLineItem) {
        this.mContext = context;
        this.mBusLineItem = busLineItem;
        this.mAMap = aMap;
        this.mBusStations = busLineItem.getBusStations();
    }

    private void destroyBit() {
        BitmapDescriptor bitmapDescriptor = this.startBit;
        if (bitmapDescriptor != null) {
            bitmapDescriptor.recycle();
            this.startBit = null;
        }
        BitmapDescriptor bitmapDescriptor2 = this.endBit;
        if (bitmapDescriptor2 != null) {
            bitmapDescriptor2.recycle();
            this.endBit = null;
        }
        BitmapDescriptor bitmapDescriptor3 = this.busBit;
        if (bitmapDescriptor3 != null) {
            bitmapDescriptor3.recycle();
            this.busBit = null;
        }
    }

    private LatLngBounds getLatLngBounds(List<LatLonPoint> list) {
        LatLngBounds.Builder builder = LatLngBounds.builder();
        for (int i = 0; i < list.size(); i++) {
            builder.include(new LatLng(list.get(i).getLatitude(), list.get(i).getLongitude()));
        }
        return builder.build();
    }

    private MarkerOptions getMarkerOptions(int i) {
        MarkerOptions markerOptionsSnippet = new MarkerOptions().position(new LatLng(this.mBusStations.get(i).getLatLonPoint().getLatitude(), this.mBusStations.get(i).getLatLonPoint().getLongitude())).title(getTitle(i)).snippet(getSnippet(i));
        if (i == 0) {
            markerOptionsSnippet.icon(getStartBitmapDescriptor());
        } else if (i == this.mBusStations.size() - 1) {
            markerOptionsSnippet.icon(getEndBitmapDescriptor());
        } else {
            markerOptionsSnippet.anchor(0.5f, 0.5f);
            markerOptionsSnippet.icon(getBusBitmapDescriptor());
        }
        return markerOptionsSnippet;
    }

    public void addToMap() {
        try {
            this.mBusLinePolyline = this.mAMap.addPolyline(new PolylineOptions().addAll(AMapServicesUtil.convertArrList(this.mBusLineItem.getDirectionsCoordinates())).color(getBusColor()).width(getBuslineWidth()));
            if (this.mBusStations.size() < 1) {
                return;
            }
            for (int i = 1; i < this.mBusStations.size() - 1; i++) {
                this.mBusStationMarks.add(this.mAMap.addMarker(getMarkerOptions(i)));
            }
            this.mBusStationMarks.add(this.mAMap.addMarker(getMarkerOptions(0)));
            this.mBusStationMarks.add(this.mAMap.addMarker(getMarkerOptions(this.mBusStations.size() - 1)));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public BitmapDescriptor getBusBitmapDescriptor() {
        BitmapDescriptor bitmapDescriptorFromResource = BitmapDescriptorFactory.fromResource(AMapR.AMAP_DRAWABLE_AMAP_BUS);
        this.busBit = bitmapDescriptorFromResource;
        return bitmapDescriptorFromResource;
    }

    public int getBusColor() {
        return Color.parseColor("#537edc");
    }

    public int getBusStationIndex(Marker marker) {
        for (int i = 0; i < this.mBusStationMarks.size(); i++) {
            if (this.mBusStationMarks.get(i).equals(marker)) {
                return i;
            }
        }
        return -1;
    }

    public BusStationItem getBusStationItem(int i) {
        if (i < 0 || i >= this.mBusStations.size()) {
            return null;
        }
        return this.mBusStations.get(i);
    }

    public float getBuslineWidth() {
        return 18.0f;
    }

    public BitmapDescriptor getEndBitmapDescriptor() {
        BitmapDescriptor bitmapDescriptorFromResource = BitmapDescriptorFactory.fromResource(AMapR.AMAP_DRAWABLE_AMAP_END);
        this.endBit = bitmapDescriptorFromResource;
        return bitmapDescriptorFromResource;
    }

    public String getSnippet(int i) {
        return "";
    }

    public BitmapDescriptor getStartBitmapDescriptor() {
        BitmapDescriptor bitmapDescriptorFromResource = BitmapDescriptorFactory.fromResource(AMapR.AMAP_DRAWABLE_AMAP_START);
        this.startBit = bitmapDescriptorFromResource;
        return bitmapDescriptorFromResource;
    }

    public String getTitle(int i) {
        return this.mBusStations.get(i).getBusStationName();
    }

    public void removeFromMap() {
        Polyline polyline = this.mBusLinePolyline;
        if (polyline != null) {
            polyline.remove();
        }
        try {
            Iterator<Marker> it = this.mBusStationMarks.iterator();
            while (it.hasNext()) {
                it.next().remove();
            }
            destroyBit();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void zoomToSpan() {
        if (this.mAMap == null) {
            return;
        }
        try {
            List<LatLonPoint> directionsCoordinates = this.mBusLineItem.getDirectionsCoordinates();
            if (directionsCoordinates == null || directionsCoordinates.size() <= 0) {
                return;
            }
            this.mAMap.moveCamera(CameraUpdateFactory.newLatLngBounds(getLatLngBounds(directionsCoordinates), 5));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
