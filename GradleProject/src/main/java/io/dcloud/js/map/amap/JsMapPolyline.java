package io.dcloud.js.map.amap;

import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.js.map.amap.adapter.DHMapView;
import io.dcloud.js.map.amap.adapter.IFJsOverlay;
import io.dcloud.js.map.amap.adapter.MapPoint;
import io.dcloud.js.map.amap.adapter.MapPolylineProxy;
import java.util.ArrayList;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes3.dex */
public class JsMapPolyline extends JsMapObject implements IFJsOverlay {
    public MapPolylineProxy mPolyline;

    public JsMapPolyline(IWebview iWebview) {
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
        this.mPolyline = new MapPolylineProxy(jsArrToPointArr(JsMapManager.getJsMapManager().getJsToPointArry(this.mWebview, JSONUtil.getString(jSONArray, 0))));
    }

    @Override // io.dcloud.js.map.amap.adapter.IFJsOverlay
    public Object getMapOverlay() {
        return this.mPolyline;
    }

    @Override // io.dcloud.js.map.amap.JsMapObject
    public void onAddToMapView(DHMapView dHMapView) {
        super.onAddToMapView(dHMapView);
        this.mPolyline.initMapPolyline(dHMapView);
    }

    @Override // io.dcloud.js.map.amap.JsMapObject
    public void updateObject(String str, JSONArray jSONArray) {
        if ("setPath".equals(str)) {
            this.mPolyline.setPath(jsArrToPointArr(JsMapManager.getJsMapManager().getJsToPointArry(this.mWebview, JSONUtil.getString(jSONArray, 0))));
        } else if ("setStrokeColor".equals(str)) {
            this.mPolyline.setStrokeColor(PdrUtil.stringToColor(JSONUtil.getString(jSONArray, 0)));
        } else if ("setStrokeOpacity".equals(str)) {
            this.mPolyline.setStrokeOpacity(PdrUtil.parseFloat(JSONUtil.getString(jSONArray, 0), 0.0f));
        } else if ("setLineWidth".equals(str)) {
            this.mPolyline.setLineWidth(PdrUtil.parseInt(JSONUtil.getString(jSONArray, 0), 0));
        }
        DHMapView dHMapView = this.mMapView;
        if (dHMapView != null) {
            dHMapView.refreshDrawableState();
        }
    }
}
