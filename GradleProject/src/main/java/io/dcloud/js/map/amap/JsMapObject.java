package io.dcloud.js.map.amap;

import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.js.map.amap.adapter.DHMapView;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes3.dex */
public abstract class JsMapObject {
    public IWebview mWebview;
    public DHMapView mMapView = null;
    public String mUUID = null;
    public String mJsId = null;

    public JsMapObject(IWebview iWebview) {
        this.mWebview = null;
        this.mWebview = iWebview;
    }

    public abstract void createObject(JSONArray jSONArray);

    public void onAddToMapView(DHMapView dHMapView) {
        this.mMapView = dHMapView;
    }

    public void setJsId(String str) {
        this.mJsId = str;
    }

    public void setUUID(String str) {
        this.mUUID = str;
    }

    public abstract void updateObject(String str, JSONArray jSONArray);

    public String updateObjectSYNC(String str, JSONArray jSONArray) {
        return null;
    }
}
