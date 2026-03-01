package io.dcloud.js.map.amap;

import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.js.map.amap.adapter.DHMapView;
import io.dcloud.js.map.amap.adapter.IFJsOverlay;
import io.dcloud.js.map.amap.adapter.MapPoint;
import io.dcloud.js.map.amap.adapter.MapRoute;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes3.dex */
public class JsMapRoute extends JsMapObject implements IFJsOverlay {
    public MapRoute mMapRoute;

    public JsMapRoute(IWebview iWebview) {
        super(iWebview);
        this.mMapRoute = new MapRoute();
    }

    @Override // io.dcloud.js.map.amap.JsMapObject
    public void createObject(JSONArray jSONArray) {
    }

    @Override // io.dcloud.js.map.amap.adapter.IFJsOverlay
    public Object getMapOverlay() {
        return this.mMapRoute;
    }

    @Override // io.dcloud.js.map.amap.JsMapObject
    public void onAddToMapView(DHMapView dHMapView) {
        super.onAddToMapView(dHMapView);
        this.mMapRoute.initMapRoute(this.mWebview, dHMapView);
    }

    public void setPoint(MapPoint mapPoint, MapPoint mapPoint2) {
        this.mMapRoute.setRoute(mapPoint, mapPoint2);
    }

    public void setRoute(JsMapPoint jsMapPoint, JsMapPoint jsMapPoint2) {
        this.mMapRoute.setRoute(jsMapPoint.getMapPoint(), jsMapPoint2.getMapPoint());
    }

    @Override // io.dcloud.js.map.amap.JsMapObject
    public void updateObject(String str, JSONArray jSONArray) {
    }

    public void setRoute(Object obj) {
        this.mMapRoute.setRoute(obj);
    }
}
