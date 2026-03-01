package io.dcloud.js.map.amap;

import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.js.map.amap.adapter.DHMapView;
import io.dcloud.js.map.amap.adapter.IFJsOverlay;
import io.dcloud.js.map.amap.adapter.MapPoint;
import io.dcloud.js.map.amap.adapter.MapPolygonProxy;
import java.util.ArrayList;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes3.dex */
public class JsMapPolygon extends JsMapObject implements IFJsOverlay {
    public MapPolygonProxy mPolygon;

    public JsMapPolygon(IWebview iWebview) {
        super(iWebview);
    }

    private ArrayList<MapPoint> jsArrToPointArr(ArrayList<JsMapPoint> arrayList) {
        ArrayList<MapPoint> arrayList2 = new ArrayList<>();
        if (arrayList != null && arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                arrayList2.add(arrayList.get(i).getMapPoint());
            }
        }
        return arrayList2;
    }

    @Override // io.dcloud.js.map.amap.JsMapObject
    public void createObject(JSONArray jSONArray) {
        this.mPolygon = new MapPolygonProxy(jsArrToPointArr(JsMapManager.getJsMapManager().getJsToPointArry(this.mWebview, JSONUtil.getString(jSONArray, 0))));
    }

    @Override // io.dcloud.js.map.amap.adapter.IFJsOverlay
    public Object getMapOverlay() {
        return this.mPolygon;
    }

    @Override // io.dcloud.js.map.amap.JsMapObject
    public void onAddToMapView(DHMapView dHMapView) {
        super.onAddToMapView(dHMapView);
        this.mPolygon.initMapPolygon(dHMapView);
    }

    @Override // io.dcloud.js.map.amap.JsMapObject
    public void updateObject(String str, JSONArray jSONArray) {
        if ("setPath".equals(str)) {
            this.mPolygon.setPath(jsArrToPointArr(JsMapManager.getJsMapManager().getJsToPointArry(this.mWebview, JSONUtil.getString(jSONArray, 0))));
        } else if ("setStrokeColor".equals(str)) {
            this.mPolygon.setStrokeColor(PdrUtil.stringToColor(JSONUtil.getString(jSONArray, 0)));
        } else if ("setStrokeOpacity".equals(str)) {
            this.mPolygon.setStrokeOpacity(Float.parseFloat(JSONUtil.getString(jSONArray, 0)));
        } else if ("setFillColor".equals(str)) {
            this.mPolygon.setFillColor(PdrUtil.stringToColor(JSONUtil.getString(jSONArray, 0)));
        } else if ("setFillOpacity".equals(str)) {
            this.mPolygon.setFillOpacity(PdrUtil.parseFloat(JSONUtil.getString(jSONArray, 0), 0.0f));
        } else if ("setLineWidth".equals(str)) {
            this.mPolygon.setLineWidth(PdrUtil.parseFloat(JSONUtil.getString(jSONArray, 0), 0.0f));
        }
        DHMapView dHMapView = this.mMapView;
        if (dHMapView != null) {
            dHMapView.refreshDrawableState();
        }
    }
}
