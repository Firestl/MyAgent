package io.dcloud.feature.weex_amap.adapter.cluster;

import com.alibaba.fastjson.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class CustomClusterRender implements ClusterRender {
    public JSONObject mClusterData;
    public String mClusterId;
    public String mMarkerId;

    public CustomClusterRender(String str, String str2, JSONObject jSONObject) {
        this.mMarkerId = str2;
        this.mClusterId = str;
        this.mClusterData = jSONObject;
    }

    @Override // io.dcloud.feature.weex_amap.adapter.cluster.ClusterRender
    public String getClusterId() {
        return this.mClusterId;
    }

    @Override // io.dcloud.feature.weex_amap.adapter.cluster.ClusterRender
    public JSONObject getMarkerData() {
        return this.mClusterData;
    }

    @Override // io.dcloud.feature.weex_amap.adapter.cluster.ClusterRender
    public String getMarkerId() {
        return this.mMarkerId;
    }
}
