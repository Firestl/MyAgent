package io.dcloud.feature.weex_amap.adapter;

import com.taobao.weex.WXSDKInstance;

/* JADX INFO: loaded from: classes3.dex */
public class MapAbsMgr {
    public WXSDKInstance mInstance;
    public WXMapView mMap;

    public MapAbsMgr(WXSDKInstance wXSDKInstance, WXMapView wXMapView) {
        this.mInstance = wXSDKInstance;
        this.mMap = wXMapView;
    }
}
