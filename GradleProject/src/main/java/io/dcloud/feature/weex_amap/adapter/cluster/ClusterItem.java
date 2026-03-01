package io.dcloud.feature.weex_amap.adapter.cluster;

import com.alibaba.fastjson.JSONObject;
import com.amap.api.maps.model.LatLng;

/* JADX INFO: loaded from: classes3.dex */
public interface ClusterItem {
    JSONObject getData();

    String getId();

    LatLng getPosition();
}
