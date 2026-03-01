package io.dcloud.feature.weex_amap.adapter.polygon;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.maps.model.Polygon;
import com.amap.api.maps.model.PolygonOptions;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import io.dcloud.feature.weex_amap.adapter.Constant;
import io.dcloud.feature.weex_amap.adapter.MapAbsMgr;
import io.dcloud.feature.weex_amap.adapter.MapResourceUtils;
import io.dcloud.feature.weex_amap.adapter.WXMapView;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public class PolygonMgr extends MapAbsMgr {
    public ArrayList<Polygon> mPolygonCaches;

    public PolygonMgr(WXSDKInstance wXSDKInstance, WXMapView wXMapView) {
        super(wXSDKInstance, wXMapView);
        this.mPolygonCaches = new ArrayList<>();
    }

    private PolygonOptions createPolygonOptions(JSONObject jSONObject) {
        PolygonOptions polygonOptions = null;
        if (jSONObject != null && jSONObject.containsKey("points")) {
            polygonOptions = new PolygonOptions();
            polygonOptions.addAll(MapResourceUtils.crateLatLngs(jSONObject.getJSONArray("points")));
            if (jSONObject.containsKey(Constant.Name.STROKE_WIDTH)) {
                polygonOptions.strokeWidth(WXViewUtils.getRealSubPxByWidth(WXUtils.getFloat(jSONObject.get(Constant.Name.STROKE_WIDTH)), this.mInstance.getInstanceViewPortWidthWithFloat()));
            }
            if (jSONObject.containsKey(Constant.Name.STROKE_COLOR)) {
                polygonOptions.strokeColor(MapResourceUtils.getColor(jSONObject.getString(Constant.Name.STROKE_COLOR)));
            }
            if (jSONObject.containsKey(Constant.Name.FILL_COLOR)) {
                polygonOptions.fillColor(MapResourceUtils.getColor(jSONObject.getString(Constant.Name.FILL_COLOR)));
            }
            if (jSONObject.containsKey(Constant.JSONKEY.ZINDEX)) {
                polygonOptions.zIndex(jSONObject.getFloat(Constant.JSONKEY.ZINDEX).floatValue());
            }
        }
        return polygonOptions;
    }

    public void clearPolygon() {
        ArrayList<Polygon> arrayList = this.mPolygonCaches;
        if (arrayList != null) {
            Iterator<Polygon> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().remove();
            }
            this.mPolygonCaches.clear();
        }
    }

    public void setPolygon(JSONArray jSONArray) {
        clearPolygon();
        if (jSONArray == null || jSONArray.size() <= 0) {
            return;
        }
        for (int i = 0; i < jSONArray.size(); i++) {
            PolygonOptions polygonOptionsCreatePolygonOptions = createPolygonOptions(jSONArray.getJSONObject(i));
            if (polygonOptionsCreatePolygonOptions != null) {
                this.mPolygonCaches.add(this.mMap.getMap().addPolygon(polygonOptionsCreatePolygonOptions));
            }
        }
    }
}
