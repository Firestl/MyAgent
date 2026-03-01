package io.dcloud.js.map.amap;

import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.js.map.amap.adapter.DHMapView;
import io.dcloud.js.map.amap.adapter.MapSearch;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class JsMapSearch extends JsMapObject {
    public MapSearch mMapSearch;

    public JsMapSearch(IWebview iWebview) {
        super(iWebview);
        this.mMapSearch = new MapSearch(iWebview);
    }

    @Override // io.dcloud.js.map.amap.JsMapObject
    public void createObject(JSONArray jSONArray) {
    }

    @Override // io.dcloud.js.map.amap.JsMapObject
    public void onAddToMapView(DHMapView dHMapView) {
        super.onAddToMapView(dHMapView);
        this.mMapSearch.setMapView(dHMapView);
    }

    @Override // io.dcloud.js.map.amap.JsMapObject
    public void setUUID(String str) {
        super.setUUID(str);
        this.mMapSearch.mCallbackId = this.mUUID;
    }

    @Override // io.dcloud.js.map.amap.JsMapObject
    public void updateObject(String str, JSONArray jSONArray) {
        try {
            if ("setPageCapacity".equals(str)) {
                this.mMapSearch.setPageCapacity(jSONArray.getString(0));
                return;
            }
            if ("poiSearchInCity".equals(str)) {
                this.mMapSearch.poiSearchInCity(jSONArray.getString(0), jSONArray.getString(1), jSONArray.getString(2));
                return;
            }
            if ("poiSearchNearBy".equals(str)) {
                this.mMapSearch.poiSearchNearBy(jSONArray.getString(0), JsMapManager.getJsMapManager().getMapPoint(this.mWebview, jSONArray.getJSONObject(1)).getMapPoint(), jSONArray.getString(2), jSONArray.getString(3));
                return;
            }
            if ("poiSearchInbounds".equals(str)) {
                this.mMapSearch.poiSearchInbounds(jSONArray.getString(0), JsMapManager.getJsMapManager().getMapPoint(this.mWebview, jSONArray.getJSONObject(1)).getMapPoint(), JsMapManager.getJsMapManager().getMapPoint(this.mWebview, jSONArray.getJSONObject(2)).getMapPoint(), jSONArray.getString(3));
                return;
            }
            if ("setTransitPolicy".equals(str)) {
                this.mMapSearch.setTransitPolicy(jSONArray.getString(0));
                return;
            }
            if ("setDrivingPolicy".equals(str)) {
                this.mMapSearch.setDrivingPolicy(jSONArray.getString(0));
                return;
            }
            if ("transitSearch".equals(str)) {
                this.mMapSearch.transitSearch(jSONArray.get(0) instanceof JSONObject ? JsMapManager.getJsMapManager().getMapPoint(this.mWebview, jSONArray.getJSONObject(0)).getMapPoint() : jSONArray.getString(0), jSONArray.get(1) instanceof JSONObject ? JsMapManager.getJsMapManager().getMapPoint(this.mWebview, jSONArray.getJSONObject(1)).getMapPoint() : jSONArray.getString(1), jSONArray.getString(2));
            } else if ("drivingSearch".equals(str)) {
                this.mMapSearch.drivingSearch(jSONArray.get(0) instanceof JSONObject ? JsMapManager.getJsMapManager().getMapPoint(this.mWebview, jSONArray.getJSONObject(0)).getMapPoint() : jSONArray.getString(0), jSONArray.getString(1), jSONArray.get(2) instanceof JSONObject ? JsMapManager.getJsMapManager().getMapPoint(this.mWebview, jSONArray.getJSONObject(2)).getMapPoint() : jSONArray.getString(2), jSONArray.getString(3));
            } else if ("walkingSearch".equals(str)) {
                this.mMapSearch.walkingSearch(jSONArray.get(0) instanceof JSONObject ? JsMapManager.getJsMapManager().getMapPoint(this.mWebview, jSONArray.getJSONObject(0)).getMapPoint() : jSONArray.getString(0), jSONArray.getString(1), jSONArray.get(2) instanceof JSONObject ? JsMapManager.getJsMapManager().getMapPoint(this.mWebview, jSONArray.getJSONObject(2)).getMapPoint() : jSONArray.getString(2), jSONArray.getString(3));
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }
}
