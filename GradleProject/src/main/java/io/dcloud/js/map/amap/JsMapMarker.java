package io.dcloud.js.map.amap;

import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.js.map.amap.adapter.DHMapView;
import io.dcloud.js.map.amap.adapter.IFJsOverlay;
import io.dcloud.js.map.amap.adapter.MapMarker;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes3.dex */
public class JsMapMarker extends JsMapObject implements IFJsOverlay {
    public MapMarker mMapMarker;

    public JsMapMarker(IWebview iWebview) {
        super(iWebview);
    }

    private void init(JsMapPoint jsMapPoint) {
        MapMarker mapMarker = new MapMarker(jsMapPoint.getMapPoint(), this.mWebview);
        this.mMapMarker = mapMarker;
        mapMarker.setUuid(this.mUUID);
    }

    @Override // io.dcloud.js.map.amap.JsMapObject
    public void createObject(JSONArray jSONArray) {
        init(JsMapManager.getJsMapManager().getMapPoint(this.mWebview, JSONUtil.getJSONObject(jSONArray, 0)));
    }

    @Override // io.dcloud.js.map.amap.adapter.IFJsOverlay
    public Object getMapOverlay() {
        return this.mMapMarker;
    }

    @Override // io.dcloud.js.map.amap.JsMapObject
    public void onAddToMapView(DHMapView dHMapView) {
        super.onAddToMapView(dHMapView);
        this.mMapMarker.initMapMarker(dHMapView);
    }

    @Override // io.dcloud.js.map.amap.JsMapObject
    public void updateObject(String str, JSONArray jSONArray) {
        if ("setPoint".equals(str)) {
            this.mMapMarker.setMapPoint(JsMapManager.getJsMapManager().getMapPoint(this.mWebview, JSONUtil.getJSONObject(jSONArray, 0)).getMapPoint());
            return;
        }
        if ("setLabel".equals(str)) {
            this.mMapMarker.setLabel(JSONUtil.getString(jSONArray, 0));
            return;
        }
        if ("setBubble".equals(str)) {
            this.mMapMarker.setBubble(JSONUtil.getString(jSONArray, 0), JSONUtil.getString(jSONArray, 1), jSONArray.optBoolean(4));
            return;
        }
        if ("setIcon".equals(str)) {
            this.mMapMarker.setIcon(JSONUtil.getString(jSONArray, 0));
            return;
        }
        if ("setBubbleIcon".equals(str)) {
            this.mMapMarker.setBubbleIcon(JSONUtil.getString(jSONArray, 0));
            return;
        }
        if ("setBubbleLabel".equals(str)) {
            this.mMapMarker.setBubbleLabel(JSONUtil.getString(jSONArray, 0));
            return;
        }
        if ("hide".equals(str)) {
            this.mMapMarker.hide();
            return;
        }
        if (AbsoluteConst.EVENTS_WEBVIEW_SHOW.equals(str)) {
            this.mMapMarker.show();
            return;
        }
        if ("bringToTop".equals(str)) {
            this.mMapMarker.bringToTop();
            return;
        }
        if ("hideBubble".equals(str)) {
            this.mMapMarker.hideBubble();
            return;
        }
        if ("setIcons".equals(str)) {
            this.mMapMarker.setIcons(JSONUtil.getJSONArray(jSONArray, 0), jSONArray.optInt(1));
        } else if ("setDraggable".equals(str)) {
            this.mMapMarker.setDraggable(jSONArray.optBoolean(0));
        } else if ("loadImage".equals(str)) {
            this.mMapMarker.loadImage(JSONUtil.getString(jSONArray, 0));
        } else if ("loadImageDataURL".equals(str)) {
            this.mMapMarker.loadImageDataURL(JSONUtil.getString(jSONArray, 0));
        }
    }
}
