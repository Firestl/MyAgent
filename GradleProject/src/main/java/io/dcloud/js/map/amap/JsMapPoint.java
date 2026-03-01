package io.dcloud.js.map.amap;

import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.js.map.amap.adapter.MapPoint;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes3.dex */
public class JsMapPoint extends JsMapObject {
    public MapPoint mMapPoint;

    public JsMapPoint(IWebview iWebview, String str, String str2) {
        super(iWebview);
        this.mMapPoint = new MapPoint(str, str2);
    }

    @Override // io.dcloud.js.map.amap.JsMapObject
    public void createObject(JSONArray jSONArray) {
    }

    public MapPoint getMapPoint() {
        return this.mMapPoint;
    }

    @Override // io.dcloud.js.map.amap.JsMapObject
    public void updateObject(String str, JSONArray jSONArray) {
    }
}
