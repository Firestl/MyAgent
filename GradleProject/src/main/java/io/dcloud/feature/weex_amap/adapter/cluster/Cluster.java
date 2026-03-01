package io.dcloud.feature.weex_amap.adapter.cluster;

import com.alibaba.fastjson.JSONArray;
import com.amap.api.maps.model.LatLng;
import io.dcloud.feature.uniapp.UniSDKInstance;
import io.dcloud.feature.weex_amap.adapter.WXMapView;
import io.dcloud.feature.weex_amap.adapter.marker.IUniMarker;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class Cluster {
    public ClusterRender mClusterRender;
    public String mId;
    public LatLng mLatLng;
    public IUniMarker mMarker;
    public int oldCont = 0;
    public boolean isMultiplexing = false;
    public List<ClusterItem> mClusterItems = new ArrayList();

    public Cluster(String str, LatLng latLng) {
        this.mId = str;
        this.mLatLng = latLng;
    }

    public void addClusterItem(ClusterItem clusterItem) {
        this.mClusterItems.add(clusterItem);
    }

    public void clearClusterItems() {
        List<ClusterItem> list = this.mClusterItems;
        if (list != null) {
            this.oldCont = list.size();
            this.mClusterItems.clear();
        }
    }

    public LatLng getCenterLatLng() {
        return this.mLatLng;
    }

    public int getClusterCount() {
        return this.mClusterItems.size();
    }

    public ClusterItem getClusterItem(int i) {
        if (this.mClusterItems.size() > i) {
            return this.mClusterItems.get(i);
        }
        return null;
    }

    public List<ClusterItem> getClusterItems() {
        return this.mClusterItems;
    }

    public ClusterRender getClusterRender() {
        return this.mClusterRender;
    }

    public String getId() {
        return this.mId;
    }

    public IUniMarker getMarker() {
        return this.mMarker;
    }

    public JSONArray getMarkerIds() {
        JSONArray jSONArray = new JSONArray();
        for (ClusterItem clusterItem : this.mClusterItems) {
            if (clusterItem != null) {
                jSONArray.add(clusterItem.getId());
            }
        }
        return jSONArray;
    }

    public int getOldCont() {
        return this.oldCont;
    }

    public boolean isContUpdate() {
        List<ClusterItem> list = this.mClusterItems;
        return (list == null || this.oldCont == list.size()) ? false : true;
    }

    public boolean isMarker() {
        return this.mClusterItems.size() == 1;
    }

    public boolean isMultiplexing() {
        return this.isMultiplexing;
    }

    public void removeClusterItem(ClusterItem clusterItem) {
        if (this.mClusterItems.contains(clusterItem)) {
            this.mClusterItems.remove(clusterItem);
        }
    }

    public void setClusterRender(ClusterRender clusterRender) {
        this.mClusterRender = clusterRender;
    }

    public void setMarker(IUniMarker iUniMarker) {
        this.mMarker = iUniMarker;
    }

    public void setMultiplexing(boolean z) {
        this.isMultiplexing = z;
    }

    public void updateClusterForRender(WXMapView wXMapView, UniSDKInstance uniSDKInstance) {
        ClusterRender clusterRender;
        IUniMarker iUniMarker = this.mMarker;
        if (iUniMarker == null || !(iUniMarker instanceof ClusterMarker) || (clusterRender = this.mClusterRender) == null) {
            return;
        }
        ((ClusterMarker) iUniMarker).updateClusterForRender(uniSDKInstance, wXMapView, clusterRender);
    }
}
