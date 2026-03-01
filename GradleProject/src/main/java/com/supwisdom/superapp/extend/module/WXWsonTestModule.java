package com.supwisdom.superapp.extend.module;

import android.util.Log;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.utils.WXWsonJSONSwitch;

/* JADX INFO: loaded from: classes2.dex */
public class WXWsonTestModule extends WXModule {
    @JSMethod(uiThread = false)
    public Object back(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject(jSONObject);
        jSONObject2.put("longMax", (Object) Long.MAX_VALUE);
        jSONObject2.put("longMin", (Object) Long.MIN_VALUE);
        jSONObject2.put("javaJSON", (Object) jSONObject2.toJSONString());
        return jSONObject2;
    }

    @JSMethod(uiThread = false)
    public void backAsync(JSONObject jSONObject, JSCallback jSCallback) {
        JSONObject jSONObject2 = new JSONObject(jSONObject);
        jSONObject2.put("longMax", (Object) Long.MAX_VALUE);
        jSONObject2.put("longMin", (Object) Long.MIN_VALUE);
        jSONObject2.put("javaJSON", (Object) jSONObject2.toJSONString());
        jSCallback.invoke(jSONObject2);
    }

    @JSMethod(uiThread = false)
    public Object benchmark(JSONObject jSONObject) {
        if (jSONObject != null) {
            return jSONObject;
        }
        throw new RuntimeException("params is null");
    }

    @JSMethod(uiThread = false)
    public boolean callPass(JSONObject jSONObject) {
        if (jSONObject.get("refundId") != null) {
            if (jSONObject.get("refundId").getClass() == Long.class) {
                return ((Long) jSONObject.get("refundId")).longValue() == 6419458776149741L;
            }
            Log.e("Weex", "weex wson refundId failed case");
            return false;
        }
        if (jSONObject.get("refundId2") == null) {
            return true;
        }
        if (jSONObject.get("refundId2").getClass() == Long.class) {
            return ((Long) jSONObject.get("refundId2")).longValue() == 64194587761497416L;
        }
        Log.e("Weex", "weex wson refundId failed case");
        return false;
    }

    @JSMethod(uiThread = false)
    public void switchTrans(JSCallback jSCallback) {
        if (WXWsonJSONSwitch.USE_WSON) {
            WXBridgeManager.updateGlobalConfig(WXWsonJSONSwitch.WSON_OFF);
            jSCallback.invoke("wson off, use json");
        } else {
            WXBridgeManager.updateGlobalConfig("wson_on");
            jSCallback.invoke("wson on, use wson");
        }
    }
}
