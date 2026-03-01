package io.dcloud.feature.weex_amap.adapter.polyline;

import android.net.Uri;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import io.dcloud.feature.weex_amap.R;
import io.dcloud.feature.weex_amap.adapter.MapAbsMgr;
import io.dcloud.feature.weex_amap.adapter.MapResourceUtils;
import io.dcloud.feature.weex_amap.adapter.WXMapView;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public class PolylineMgr extends MapAbsMgr {
    public ArrayList<Polyline> mPolylineCaches;

    public PolylineMgr(WXSDKInstance wXSDKInstance, WXMapView wXMapView) {
        super(wXSDKInstance, wXMapView);
        this.mPolylineCaches = new ArrayList<>();
    }

    public void clearPolylines() {
        ArrayList<Polyline> arrayList = this.mPolylineCaches;
        if (arrayList != null) {
            Iterator<Polyline> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().remove();
            }
            this.mPolylineCaches.clear();
        }
    }

    public PolylineOptions createPolylineOptions(JSONObject jSONObject) {
        PolylineOptions polylineOptions = null;
        if (jSONObject != null && jSONObject.containsKey("points")) {
            polylineOptions = new PolylineOptions();
            polylineOptions.setPoints(MapResourceUtils.crateLatLngs(jSONObject.getJSONArray("points")));
            if (jSONObject.containsKey("width")) {
                polylineOptions.width(WXViewUtils.getRealSubPxByWidth(WXUtils.getFloat(jSONObject.getString("width")), this.mInstance.getInstanceViewPortWidthWithFloat()));
            }
            if (jSONObject.containsKey("colorList")) {
                JSONArray jSONArray = jSONObject.getJSONArray("colorList");
                if (jSONArray != null && jSONArray.size() > 0) {
                    ArrayList arrayList = new ArrayList();
                    Iterator<Object> it = jSONArray.iterator();
                    while (it.hasNext()) {
                        arrayList.add(Integer.valueOf(MapResourceUtils.getColor(it.next().toString())));
                    }
                    polylineOptions.colorValues(arrayList).useGradient(true);
                }
            } else if (jSONObject.containsKey("color")) {
                polylineOptions.color(MapResourceUtils.getColor(jSONObject.getString("color")));
            }
            if (jSONObject.containsKey("dottedLine") && jSONObject.getBoolean("dottedLine").booleanValue()) {
                polylineOptions.setDottedLine(true);
                polylineOptions.setDottedLineType(0);
            } else {
                polylineOptions.setDottedLine(false);
            }
            if (jSONObject.containsKey("arrowLine") && jSONObject.getBoolean("arrowLine").booleanValue()) {
                polylineOptions.setUseTexture(true);
                if (jSONObject.containsKey("arrowIconPath")) {
                    BitmapDescriptor bitmapDescriptorFromPath = BitmapDescriptorFactory.fromPath(this.mInstance.rewriteUri(Uri.parse(jSONObject.getString("arrowIconPath")), "image").getPath());
                    if (bitmapDescriptorFromPath != null) {
                        polylineOptions.setCustomTexture(bitmapDescriptorFromPath);
                    }
                } else {
                    BitmapDescriptor bitmapDescriptorFromResource = BitmapDescriptorFactory.fromResource(R.drawable.dcloud_traffic_texture_blue);
                    if (bitmapDescriptorFromResource != null) {
                        polylineOptions.setCustomTexture(bitmapDescriptorFromResource);
                    }
                }
            } else {
                polylineOptions.setUseTexture(false);
            }
        }
        return polylineOptions;
    }

    public void setPolyline(JSONArray jSONArray) {
        Polyline polylineAddPolyline;
        if (jSONArray == null || jSONArray.size() <= 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.size(); i++) {
            PolylineOptions polylineOptionsCreatePolylineOptions = createPolylineOptions(jSONArray.getJSONObject(i));
            if (polylineOptionsCreatePolylineOptions != null && (polylineAddPolyline = this.mMap.getMap().addPolyline(polylineOptionsCreatePolylineOptions)) != null) {
                arrayList.add(polylineAddPolyline);
            }
        }
        clearPolylines();
        if (arrayList.size() > 0) {
            this.mPolylineCaches.addAll(arrayList);
        }
    }
}
