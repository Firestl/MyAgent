package io.dcloud.feature.weex_amap.adapter.cluster;

import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import io.dcloud.feature.uniapp.UniSDKInstance;
import io.dcloud.feature.weex_amap.adapter.WXMapView;
import io.dcloud.feature.weex_amap.adapter.marker.IUniMarker;
import io.dcloud.feature.weex_amap.adapter.marker.WXMarker;

/* JADX INFO: loaded from: classes3.dex */
public class ClusterMarker implements IUniMarker {
    public WXMarker mDiyMarker;
    public Marker mMarker;
    public int mMarkermCount;

    public ClusterMarker(WXMapView wXMapView, BitmapDescriptor bitmapDescriptor, LatLng latLng, int i) {
        this.mMarkermCount = 0;
        this.mMarkermCount = i;
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.anchor(0.5f, 0.5f).icon(bitmapDescriptor).position(latLng);
        this.mMarker = wXMapView.getMap().addMarker(markerOptions);
    }

    @Override // io.dcloud.feature.weex_amap.adapter.marker.IUniMarker
    public void destroy() {
        WXMarker wXMarker = this.mDiyMarker;
        if (wXMarker != null) {
            wXMarker.destroy();
            this.mDiyMarker = null;
        }
        Marker marker = this.mMarker;
        if (marker != null) {
            marker.destroy();
            this.mMarker = null;
        }
    }

    @Override // io.dcloud.feature.weex_amap.adapter.marker.IUniMarker
    public Marker getInstance() {
        WXMarker wXMarker = this.mDiyMarker;
        return wXMarker != null ? wXMarker.getInstance() : this.mMarker;
    }

    public int getMarkerCount() {
        return this.mMarkermCount;
    }

    public void updateClusterForRender(UniSDKInstance uniSDKInstance, WXMapView wXMapView, ClusterRender clusterRender) {
        Marker marker = this.mMarker;
        if (marker != null) {
            marker.destroy();
            this.mMarker = null;
        }
        WXMarker wXMarker = this.mDiyMarker;
        if (wXMarker != null) {
            wXMarker.destroy();
        }
        this.mDiyMarker = new WXMarker(uniSDKInstance, wXMapView, clusterRender.getMarkerData(), clusterRender.getMarkerId());
    }

    public void updateIcon(BitmapDescriptor bitmapDescriptor, int i) {
        Marker marker;
        if (this.mDiyMarker == null && (marker = this.mMarker) != null) {
            marker.setIcon(bitmapDescriptor);
        }
        this.mMarkermCount = i;
    }
}
