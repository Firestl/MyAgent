package io.dcloud.feature.weex_amap.adapter.ground;

import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.GroundOverlay;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.Constants;
import com.taobao.weex.utils.WXUtils;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.feature.uniapp.bridge.UniJSCallback;
import io.dcloud.feature.uniapp.utils.bitmap.BitmapLoadCallback;
import io.dcloud.feature.weex.adapter.FrescoLoadUtil;
import io.dcloud.feature.weex_amap.R;
import io.dcloud.feature.weex_amap.adapter.Constant;
import io.dcloud.feature.weex_amap.adapter.MapAbsMgr;
import io.dcloud.feature.weex_amap.adapter.WXMapView;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public class GroundMgr extends MapAbsMgr {
    public HashMap<String, GroundOverlay> mGroundCaches;

    public GroundMgr(WXSDKInstance wXSDKInstance, WXMapView wXMapView) {
        super(wXSDKInstance, wXMapView);
        this.mGroundCaches = new HashMap<>();
    }

    private void editGroundOverlay(JSONObject jSONObject, UniJSCallback uniJSCallback) {
        HashMap map = new HashMap();
        try {
            if (!jSONObject.containsKey("id") || !jSONObject.containsKey("src") || !jSONObject.containsKey("bounds")) {
                map.put("type", Constants.Event.FAIL);
                map.put("msg", DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_weex_amap_msg_bounds_cannot_null));
                uniJSCallback.invoke(map);
                return;
            }
            String string = jSONObject.getString("id");
            boolean z = !this.mGroundCaches.containsKey(string);
            String string2 = jSONObject.getString("src");
            JSONObject jSONObject2 = jSONObject.getJSONObject("bounds");
            boolean booleanValue = jSONObject.containsKey("visible") ? jSONObject.getBooleanValue("visible") : true;
            int iIntValue = WXUtils.getInteger(jSONObject.get(Constant.JSONKEY.ZINDEX), 1).intValue();
            float f = 1.0f;
            if (jSONObject.containsKey("opacity")) {
                float floatValue = jSONObject.getFloatValue("opacity");
                if (floatValue >= 1.0f) {
                    f = 0.0f;
                } else if (floatValue > 0.0f) {
                    f = 1.0f - floatValue;
                }
            } else {
                f = 0.0f;
            }
            LatLngBounds bounds = getBounds(jSONObject2);
            if (bounds == null) {
                map.put("type", Constants.Event.FAIL);
                map.put("msg", DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_weex_amap_msg_bounds_create_fail));
                uniJSCallback.invoke(map);
            } else {
                if (z) {
                    GroundOverlayOptions groundOverlayOptions = new GroundOverlayOptions();
                    groundOverlayOptions.visible(booleanValue);
                    groundOverlayOptions.transparency(f);
                    groundOverlayOptions.zIndex(iIntValue);
                    groundOverlayOptions.positionFromBounds(bounds);
                    loadImageToIcon(string, string2, groundOverlayOptions, null, uniJSCallback);
                    return;
                }
                GroundOverlay groundOverlay = this.mGroundCaches.get(string);
                groundOverlay.setVisible(booleanValue);
                groundOverlay.setTransparency(f);
                groundOverlay.setZIndex(iIntValue);
                groundOverlay.setPositionFromBounds(bounds);
                loadImageToIcon(string, string2, null, groundOverlay, uniJSCallback);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            map.put("type", Constants.Event.FAIL);
            map.put("msg", e2.getMessage());
            uniJSCallback.invoke(map);
        }
    }

    private LatLngBounds getBounds(JSONObject jSONObject) {
        if (jSONObject == null || !jSONObject.containsKey("southwest") || !jSONObject.containsKey("northeast")) {
            return null;
        }
        JSONObject jSONObject2 = jSONObject.getJSONObject("southwest");
        JSONObject jSONObject3 = jSONObject.getJSONObject("northeast");
        if (jSONObject2 != null && jSONObject3 != null && jSONObject2.containsKey(Constant.JSONKEY.LATITUDE) && jSONObject2.containsKey(Constant.JSONKEY.LONGITUDE) && jSONObject3.containsKey(Constant.JSONKEY.LATITUDE) && jSONObject3.containsKey(Constant.JSONKEY.LONGITUDE)) {
            return new LatLngBounds(new LatLng(jSONObject2.getDoubleValue(Constant.JSONKEY.LATITUDE), jSONObject2.getDoubleValue(Constant.JSONKEY.LONGITUDE)), new LatLng(jSONObject3.getDoubleValue(Constant.JSONKEY.LATITUDE), jSONObject3.getDoubleValue(Constant.JSONKEY.LONGITUDE)));
        }
        return null;
    }

    private void loadImageToIcon(final String str, String str2, final GroundOverlayOptions groundOverlayOptions, final GroundOverlay groundOverlay, final UniJSCallback uniJSCallback) {
        FrescoLoadUtil.getInstance().loadImageBitmap(this.mInstance.getContext(), this.mInstance.rewriteUri(Uri.parse(str2), "image").toString(), new BitmapLoadCallback<Bitmap>() { // from class: io.dcloud.feature.weex_amap.adapter.ground.GroundMgr.1
            @Override // io.dcloud.feature.uniapp.utils.bitmap.BitmapLoadCallback
            public void onFailure(String str3, Throwable th) {
                HashMap map = new HashMap();
                map.put("type", Constants.Event.FAIL);
                uniJSCallback.invoke(map);
            }

            @Override // io.dcloud.feature.uniapp.utils.bitmap.BitmapLoadCallback
            public void onSuccess(String str3, Bitmap bitmap) {
                BitmapDescriptor bitmapDescriptorFromBitmap = BitmapDescriptorFactory.fromBitmap(bitmap);
                GroundOverlay groundOverlay2 = groundOverlay;
                if (groundOverlay2 != null) {
                    groundOverlay2.setImage(bitmapDescriptorFromBitmap);
                    return;
                }
                GroundOverlayOptions groundOverlayOptions2 = groundOverlayOptions;
                if (groundOverlayOptions2 != null) {
                    groundOverlayOptions2.image(bitmapDescriptorFromBitmap);
                    GroundOverlay groundOverlayAddGroundOverlay = GroundMgr.this.mMap.getMap().addGroundOverlay(groundOverlayOptions);
                    if (groundOverlayAddGroundOverlay == null) {
                        HashMap map = new HashMap();
                        map.put("type", Constants.Event.FAIL);
                        uniJSCallback.invoke(map);
                    } else {
                        GroundMgr.this.mGroundCaches.put(str, groundOverlayAddGroundOverlay);
                        if (uniJSCallback != null) {
                            HashMap map2 = new HashMap();
                            map2.put("type", "success");
                            uniJSCallback.invoke(map2);
                        }
                    }
                }
            }
        });
    }

    public void addGroundOverlay(JSONObject jSONObject, UniJSCallback uniJSCallback) {
        editGroundOverlay(jSONObject, uniJSCallback);
    }

    public void destroy() {
        HashMap<String, GroundOverlay> map = this.mGroundCaches;
        if (map != null && map.size() > 0) {
            Iterator<String> it = this.mGroundCaches.keySet().iterator();
            while (it.hasNext()) {
                GroundOverlay groundOverlay = this.mGroundCaches.get(it.next());
                if (groundOverlay != null && groundOverlay.isVisible()) {
                    groundOverlay.remove();
                }
            }
        }
        this.mGroundCaches.clear();
    }

    public void removeGroundOverlay(String str, UniJSCallback uniJSCallback) {
        HashMap map = new HashMap();
        try {
            if (this.mGroundCaches != null && this.mGroundCaches.size() > 0 && !TextUtils.isEmpty(str) && this.mGroundCaches.containsKey(str)) {
                this.mGroundCaches.remove(str).remove();
            }
            map.put("type", "success");
            uniJSCallback.invoke(map);
        } catch (Exception e2) {
            e2.printStackTrace();
            map.put("type", Constants.Event.FAIL);
            map.put("msg", e2.getMessage());
            uniJSCallback.invoke(map);
        }
    }

    public void updateGroundOverlay(JSONObject jSONObject, UniJSCallback uniJSCallback) {
        editGroundOverlay(jSONObject, uniJSCallback);
    }
}
