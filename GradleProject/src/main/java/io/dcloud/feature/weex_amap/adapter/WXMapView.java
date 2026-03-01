package io.dcloud.feature.weex_amap.adapter;

import android.content.Context;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.model.LatLng;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import io.dcloud.feature.uniapp.UniSDKInstance;
import io.dcloud.feature.weex_amap.adapter.Constant;
import io.dcloud.feature.weex_amap.adapter.cluster.Cluster;
import io.dcloud.feature.weex_amap.adapter.cluster.ClusterItem;
import io.dcloud.feature.weex_amap.adapter.cluster.ClusterOverlay;
import io.dcloud.feature.weex_amap.adapter.cluster.CustomClusterRender;
import io.dcloud.feature.weex_amap.adapter.cluster.OnClusterClickListener;
import io.dcloud.feature.weex_amap.adapter.cluster.OnClusterCreateListener;
import io.dcloud.feature.weex_amap.adapter.cluster.RegionItem;
import io.dcloud.feature.weex_amap.adapter.marker.IUniMarker;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class WXMapView extends TextureMapView {
    public ClusterOverlay mClusterOverlay;
    public String mCoverViewCalloutComponetRef;
    public UniSDKInstance mInstance;

    public WXMapView(Context context, UniSDKInstance uniSDKInstance) {
        super(context);
        this.mInstance = uniSDKInstance;
    }

    public void addCluster(JSONArray jSONArray) {
        ArrayList<ClusterItem> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.size(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            String strValueOf = String.valueOf(jSONObject.hashCode());
            if (jSONObject.containsKey("id")) {
                strValueOf = jSONObject.getString("id");
            }
            if (jSONObject.getBooleanValue(Constant.JSONKEY.JOIN_CLUSTER)) {
                if (jSONObject.containsKey("clusterId")) {
                    this.mClusterOverlay.setClusterRenderer(new CustomClusterRender(jSONObject.getString("clusterId"), strValueOf, jSONObject));
                } else {
                    LatLng latLngCrateLatLng = MapResourceUtils.crateLatLng(jSONObject);
                    if (latLngCrateLatLng != null) {
                        arrayList.add(new RegionItem(strValueOf, latLngCrateLatLng, jSONObject));
                    }
                }
            }
        }
        if (arrayList.size() > 0) {
            ClusterOverlay clusterOverlay = this.mClusterOverlay;
            if (clusterOverlay == null) {
                createClusterOverlay(new JSONObject());
            } else {
                clusterOverlay.addClusterItems(arrayList);
            }
        }
    }

    public void createClusterOverlay(JSONObject jSONObject) {
        int realPxByWidth2 = WXViewUtils.getRealPxByWidth2(jSONObject.containsKey("gridSize") ? WXViewUtils.getRealPxByWidth2(WXUtils.getInteger(jSONObject.get("gridSize"), 60).intValue(), this.mInstance.getInstanceViewPortWidthWithFloat()) : 60, this.mInstance.getInstanceViewPortWidthWithFloat());
        boolean booleanValue = jSONObject.containsKey("enableDefaultStyle") ? jSONObject.getBooleanValue("enableDefaultStyle") : true;
        boolean booleanValue2 = jSONObject.containsKey("zoomOnClick") ? jSONObject.getBooleanValue("zoomOnClick") : true;
        ClusterOverlay clusterOverlay = this.mClusterOverlay;
        if (clusterOverlay == null) {
            this.mClusterOverlay = new ClusterOverlay(this, this.mInstance, realPxByWidth2, booleanValue, booleanValue2, getContext());
        } else {
            clusterOverlay.updateClusterOverlay(realPxByWidth2, booleanValue, booleanValue2);
        }
    }

    public void destroy() {
        ClusterOverlay clusterOverlay = this.mClusterOverlay;
        if (clusterOverlay != null) {
            clusterOverlay.onDestroy();
        }
        super.onDestroy();
    }

    public List<IUniMarker> getClusterAddMarkers() {
        ClusterOverlay clusterOverlay = this.mClusterOverlay;
        if (clusterOverlay != null) {
            return clusterOverlay.getClusterAddMarkers();
        }
        return null;
    }

    public JSONObject getClusterInfo(Cluster cluster) {
        ClusterOverlay clusterOverlay = this.mClusterOverlay;
        return clusterOverlay != null ? clusterOverlay.getClusterInfo(cluster) : new JSONObject();
    }

    public ClusterOverlay getClusterOverlay() {
        return this.mClusterOverlay;
    }

    public String getCoverViewCalloutComponetRef() {
        return this.mCoverViewCalloutComponetRef;
    }

    public UniSDKInstance getUniSDKInstance() {
        return this.mInstance;
    }

    public void removeCluster(JSONArray jSONArray) {
        if (this.mClusterOverlay == null || jSONArray.size() <= 0) {
            return;
        }
        this.mClusterOverlay.removeClusterItems(jSONArray);
    }

    public void setClusterClickListener(OnClusterClickListener onClusterClickListener) {
        ClusterOverlay clusterOverlay = this.mClusterOverlay;
        if (clusterOverlay != null) {
            clusterOverlay.setOnClusterClickListener(onClusterClickListener);
        }
    }

    public void setClusterCreateListener(OnClusterCreateListener onClusterCreateListener) {
        ClusterOverlay clusterOverlay = this.mClusterOverlay;
        if (clusterOverlay != null) {
            clusterOverlay.setClusterCreateListener(onClusterCreateListener);
        }
    }

    public void setCoverViewCalloutComponetRef(String str) {
        this.mCoverViewCalloutComponetRef = str;
    }
}
