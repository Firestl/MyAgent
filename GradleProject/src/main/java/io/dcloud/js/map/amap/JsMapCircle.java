package io.dcloud.js.map.amap;

import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.js.map.amap.adapter.DHMapView;
import io.dcloud.js.map.amap.adapter.IFJsOverlay;
import io.dcloud.js.map.amap.adapter.MapCircleProxy;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes3.dex */
public class JsMapCircle extends JsMapObject implements IFJsOverlay {
    public MapCircleProxy mMapCircle;

    public JsMapCircle(IWebview iWebview) {
        super(iWebview);
    }

    @Override // io.dcloud.js.map.amap.JsMapObject
    public void createObject(JSONArray jSONArray) {
        this.mMapCircle = new MapCircleProxy(JsMapManager.getJsMapManager().getMapPoint(this.mWebview, JSONUtil.getJSONObject(jSONArray, 0)).getMapPoint(), Integer.parseInt(JSONUtil.getString(jSONArray, 1)));
    }

    @Override // io.dcloud.js.map.amap.adapter.IFJsOverlay
    public Object getMapOverlay() {
        return this.mMapCircle;
    }

    @Override // io.dcloud.js.map.amap.JsMapObject
    public void onAddToMapView(DHMapView dHMapView) {
        super.onAddToMapView(dHMapView);
        this.mMapCircle.initMapCircle(dHMapView);
    }

    @Override // io.dcloud.js.map.amap.JsMapObject
    public void updateObject(String str, JSONArray jSONArray) {
        if ("setCenter".equals(str)) {
            this.mMapCircle.setCenter(JsMapManager.getJsMapManager().getMapPoint(this.mWebview, JSONUtil.getJSONObject(jSONArray, 0)).getMapPoint());
        } else if ("setRadius".equals(str)) {
            this.mMapCircle.setRadius(Double.parseDouble(JSONUtil.getString(jSONArray, 0)));
        } else if ("setStrokeColor".equals(str)) {
            this.mMapCircle.setStrokeColor(JsMapManager.hexString2Int(JSONUtil.getString(jSONArray, 0)));
        } else if ("setStrokeOpacity".equals(str)) {
            this.mMapCircle.setStrokeOpacity(Float.parseFloat(JSONUtil.getString(jSONArray, 0)));
        } else if ("setFillColor".equals(str)) {
            this.mMapCircle.setFillColor(JsMapManager.hexString2Int(JSONUtil.getString(jSONArray, 0)));
        } else if ("setFillOpacity".equals(str)) {
            this.mMapCircle.setFillOpacity(Float.parseFloat(JSONUtil.getString(jSONArray, 0)));
        } else if ("setLineWidth".equals(str)) {
            this.mMapCircle.setLineWidth(Float.parseFloat(JSONUtil.getString(jSONArray, 0)));
        }
        DHMapView dHMapView = this.mMapView;
        if (dHMapView != null) {
            dHMapView.refreshDrawableState();
        }
    }
}
