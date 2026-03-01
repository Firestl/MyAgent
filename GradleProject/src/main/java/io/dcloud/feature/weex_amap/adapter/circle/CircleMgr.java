package io.dcloud.feature.weex_amap.adapter.circle;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;
import io.dcloud.feature.weex_amap.adapter.Constant;
import io.dcloud.feature.weex_amap.adapter.MapAbsMgr;
import io.dcloud.feature.weex_amap.adapter.MapResourceUtils;
import io.dcloud.feature.weex_amap.adapter.WXMapView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class CircleMgr extends MapAbsMgr {
    public ArrayList<Circle> mCircleCaches;

    public CircleMgr(WXSDKInstance wXSDKInstance, WXMapView wXMapView) {
        super(wXSDKInstance, wXMapView);
        this.mCircleCaches = new ArrayList<>();
    }

    private CircleOptions createCircleOptions(JSONObject jSONObject) {
        CircleOptions circleOptions = null;
        if (jSONObject != null && jSONObject.containsKey(Constant.JSONKEY.LATITUDE)) {
            circleOptions = new CircleOptions();
            circleOptions.center(new LatLng(WXUtils.getDouble(jSONObject.get(Constant.JSONKEY.LATITUDE)), WXUtils.getDouble(jSONObject.get(Constant.JSONKEY.LONGITUDE))));
            if (jSONObject.containsKey("color")) {
                circleOptions.strokeColor(MapResourceUtils.getColor(jSONObject.getString("color")));
            }
            if (jSONObject.containsKey(Constant.Name.FILL_COLOR)) {
                circleOptions.fillColor(MapResourceUtils.getColor(jSONObject.getString(Constant.Name.FILL_COLOR)));
            }
            if (jSONObject.containsKey(Constant.Name.RADIUS)) {
                circleOptions.radius(WXUtils.parseFloat(jSONObject.get(Constant.Name.RADIUS)));
            }
            if (jSONObject.containsKey(Constant.Name.STROKE_WIDTH)) {
                circleOptions.strokeWidth(WXViewUtils.getRealPxByWidth2(WXUtils.parseFloat(jSONObject.get(Constant.Name.STROKE_WIDTH)), this.mInstance.getInstanceViewPortWidthWithFloat()));
            }
        }
        return circleOptions;
    }

    public void clearCircles() {
        ArrayList<Circle> arrayList = this.mCircleCaches;
        if (arrayList != null) {
            for (Circle circle : arrayList) {
                if (circle != null) {
                    circle.remove();
                }
            }
        }
    }

    public void setCircles(JSONArray jSONArray) {
        clearCircles();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.size(); i++) {
                CircleOptions circleOptionsCreateCircleOptions = createCircleOptions(jSONArray.getJSONObject(i));
                if (circleOptionsCreateCircleOptions != null) {
                    this.mCircleCaches.add(this.mMap.getMap().addCircle(circleOptionsCreateCircleOptions));
                }
            }
        }
    }
}
