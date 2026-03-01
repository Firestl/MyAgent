package io.dcloud.common.adapter.ui;

import android.os.Build;
import android.webkit.JavascriptInterface;
import io.dcloud.common.DHInterface.IReflectAble;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.StringUtil;
import java.util.HashMap;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes2.dex */
public class ReceiveJSValue implements IReflectAble {
    public static String SYNC_HANDLER = "SYNC_HANDLER";
    public static HashMap<String, ReceiveJSValueCallback> arrs = new HashMap<>();
    public String android42Js = null;

    public interface ReceiveJSValueCallback {
        String callback(JSONArray jSONArray);
    }

    public static final void addJavascriptInterface(AdaWebview adaWebview) {
        if (Build.VERSION.SDK_INT > 17) {
            adaWebview.mWebViewImpl.addJavascriptInterface(new ReceiveJSValue(), SYNC_HANDLER);
            return;
        }
        ReceiveJSValue receiveJSValue = new ReceiveJSValue();
        adaWebview.mReceiveJSValue_android42 = receiveJSValue;
        receiveJSValue.android42Js = "window.SYNC_HANDLER||(window.SYNC_HANDLER = {__js__call__native__: function(uuid, js) {return window.prompt('__js__call__native__','sync:' + JSON.stringify([uuid, js]));}});";
    }

    public static final String registerCallback(String str, ReceiveJSValueCallback receiveJSValueCallback) {
        return registerCallback(null, str, receiveJSValueCallback);
    }

    @JavascriptInterface
    public final String __js__call__native__(String str, String str2) {
        ReceiveJSValueCallback receiveJSValueCallbackRemove = arrs.remove(str);
        Logger.d("ReceiveJSValue", "__js__call__native__ js=" + str2);
        return receiveJSValueCallbackRemove != null ? receiveJSValueCallbackRemove.callback(JSONUtil.createJSONArray(str2)) : "";
    }

    public static final String registerCallback(AdaWebview adaWebview, String str, ReceiveJSValueCallback receiveJSValueCallback) {
        ReceiveJSValue receiveJSValue;
        String strValueOf = String.valueOf(receiveJSValueCallback.hashCode());
        arrs.put(strValueOf, receiveJSValueCallback);
        return ((adaWebview == null || (receiveJSValue = adaWebview.mReceiveJSValue_android42) == null) ? "" : receiveJSValue.android42Js) + StringUtil.format("window.SYNC_HANDLER && " + SYNC_HANDLER + ".__js__call__native__('" + strValueOf + "',(function(){var ret = %s;var type = (typeof ret );return JSON.stringify([type,ret]);})());", str);
    }
}
