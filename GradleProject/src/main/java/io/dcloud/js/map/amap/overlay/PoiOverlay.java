package io.dcloud.js.map.amap.overlay;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.PoiItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class PoiOverlay {
    public AMap mAMap;
    public ArrayList<Marker> mPoiMarks = new ArrayList<>();
    public List<PoiItem> mPois;

    public PoiOverlay(AMap aMap, List<PoiItem> list) {
        this.mAMap = aMap;
        this.mPois = list;
    }

    private LatLngBounds getLatLngBounds() {
        LatLngBounds.Builder builder = LatLngBounds.builder();
        for (int i = 0; i < this.mPois.size(); i++) {
            builder.include(new LatLng(this.mPois.get(i).getLatLonPoint().getLatitude(), this.mPois.get(i).getLatLonPoint().getLongitude()));
        }
        return builder.build();
    }

    private MarkerOptions getMarkerOptions(int i) {
        return new MarkerOptions().position(new LatLng(this.mPois.get(i).getLatLonPoint().getLatitude(), this.mPois.get(i).getLatLonPoint().getLongitude())).title(getTitle(i)).snippet(getSnippet(i)).icon(getBitmapDescriptor(i));
    }

    public void addToMap() {
        for (int i = 0; i < this.mPois.size(); i++) {
            try {
                Marker markerAddMarker = this.mAMap.addMarker(getMarkerOptions(i));
                markerAddMarker.setObject(Integer.valueOf(i));
                this.mPoiMarks.add(markerAddMarker);
            } catch (Throwable th) {
                th.printStackTrace();
                return;
            }
        }
    }

    public BitmapDescriptor getBitmapDescriptor(int i) {
        return null;
    }

    public int getPoiIndex(Marker marker) {
        for (int i = 0; i < this.mPoiMarks.size(); i++) {
            if (this.mPoiMarks.get(i).equals(marker)) {
                return i;
            }
        }
        return -1;
    }

    public PoiItem getPoiItem(int i) {
        if (i < 0 || i >= this.mPois.size()) {
            return null;
        }
        return this.mPois.get(i);
    }

    public String getSnippet(int i) {
        return this.mPois.get(i).getSnippet();
    }

    public String getTitle(int i) {
        return this.mPois.get(i).getTitle();
    }

    public void removeFromMap() {
        Iterator<Marker> it = this.mPoiMarks.iterator();
        while (it.hasNext()) {
            it.next().remove();
        }
    }

    public void zoomToSpan() {
        try {
            if (this.mPois != null && this.mPois.size() > 0) {
                if (this.mAMap == null) {
                    return;
                }
                if (this.mPois.size() == 1) {
                    this.mAMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(this.mPois.get(0).getLatLonPoint().getLatitude(), this.mPois.get(0).getLatLonPoint().getLongitude()), 18.0f));
                } else {
                    this.mAMap.moveCamera(CameraUpdateFactory.newLatLngBounds(getLatLngBounds(), 5));
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
