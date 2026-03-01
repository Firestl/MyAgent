package io.dcloud.feature.weex_amap.adapter.cluster;

import com.alibaba.fastjson.JSONObject;
import com.amap.api.maps.model.LatLng;

/* JADX INFO: loaded from: classes3.dex */
public class RegionItem implements ClusterItem {
    public JSONObject mData;
    public String mId;
    public LatLng mLatLng;

    public RegionItem(String str, LatLng latLng, JSONObject jSONObject) {
        this.mId = str;
        this.mLatLng = latLng;
        this.mData = jSONObject;
    }

    @Override // io.dcloud.feature.weex_amap.adapter.cluster.ClusterItem
    public JSONObject getData() {
        return this.mData;
    }

    @Override // io.dcloud.feature.weex_amap.adapter.cluster.ClusterItem
    public String getId() {
        return this.mId;
    }

    @Override // io.dcloud.feature.weex_amap.adapter.cluster.ClusterItem
    public LatLng getPosition() {
        return this.mLatLng;
    }
}
