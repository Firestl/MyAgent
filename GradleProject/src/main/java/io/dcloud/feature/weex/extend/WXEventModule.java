package io.dcloud.feature.weex.extend;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.igexin.push.core.b;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public class WXEventModule extends WXModule {
    public static final int CAMERA_PERMISSION_REQUEST_CODE = 9;

    @JSMethod(uiThread = true)
    public void fireNativeGlobalEvent(String str, JSCallback jSCallback) {
        HashMap map = new HashMap();
        map.put("eventParam", "value");
        this.mWXSDKInstance.fireGlobalEventCallback(str, map);
        if (jSCallback != null) {
            HashMap map2 = new HashMap();
            map2.put(b.B, true);
            jSCallback.invoke(map2);
        }
    }

    @Override // com.taobao.weex.common.WXModule
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
    }

    @JSMethod(uiThread = true)
    public void openURL(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String scheme = Uri.parse(str).getScheme();
        StringBuilder sb = new StringBuilder();
        if (TextUtils.equals("http", scheme) || TextUtils.equals("https", scheme) || TextUtils.equals("file", scheme)) {
            sb.append(str);
        } else {
            sb.append("http:");
            sb.append(str);
        }
        Uri uri = Uri.parse(sb.toString());
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        intent.setData(uri);
        this.mWXSDKInstance.getContext().startActivity(intent);
        if (this.mWXSDKInstance.checkModuleEventRegistered("event", this)) {
            this.mWXSDKInstance.fireModuleEvent("event", this, new HashMap());
        }
    }
}
