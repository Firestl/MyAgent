package io.dcloud.feature.weex_amap.adapter.control;

import android.widget.FrameLayout;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXSDKInstance;
import io.dcloud.feature.weex_amap.adapter.MapAbsMgr;
import io.dcloud.feature.weex_amap.adapter.WXMapView;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes3.dex */
public class ControlMgr extends MapAbsMgr {
    public ConcurrentHashMap<String, ControlView> mConcurrentCaches;
    public FrameLayout mControlRootView;
    public String mRef;

    public ControlMgr(WXSDKInstance wXSDKInstance, String str, WXMapView wXMapView, FrameLayout frameLayout) {
        super(wXSDKInstance, wXMapView);
        this.mRef = str;
        this.mConcurrentCaches = new ConcurrentHashMap<>();
        FrameLayout frameLayout2 = new FrameLayout(wXSDKInstance.getContext());
        this.mControlRootView = frameLayout2;
        frameLayout.addView(frameLayout2, 1, new FrameLayout.LayoutParams(-1, -1));
    }

    private void removeDiscardControl(ConcurrentHashMap<String, ControlView> concurrentHashMap) {
        for (int i = 0; i < concurrentHashMap.size(); i++) {
            concurrentHashMap.get(Integer.valueOf(i)).destroy();
        }
        this.mConcurrentCaches.clear();
    }

    public void setControls(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.size() <= 0) {
            return;
        }
        HashMap map = new HashMap();
        for (int i = 0; i < jSONArray.size(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            String strValueOf = String.valueOf(jSONObject.hashCode());
            if (jSONObject.containsKey("id")) {
                strValueOf = jSONObject.getString("id");
            }
            if (this.mConcurrentCaches.containsKey(strValueOf)) {
                ControlView controlViewRemove = this.mConcurrentCaches.remove(strValueOf);
                controlViewRemove.update(jSONObject);
                map.put(strValueOf, controlViewRemove);
            } else {
                map.put(strValueOf, new ControlView(this.mInstance, this.mRef, strValueOf, jSONObject, this.mControlRootView));
            }
        }
        removeDiscardControl(this.mConcurrentCaches);
        this.mConcurrentCaches.putAll(map);
    }
}
