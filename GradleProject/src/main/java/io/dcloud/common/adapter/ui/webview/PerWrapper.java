package io.dcloud.common.adapter.ui.webview;

import io.dcloud.common.adapter.ui.AdaWebview;
import io.dcloud.common.adapter.util.PlatformUtil;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes2.dex */
public class PerWrapper {
    public String action;
    public JSONArray args;
    public boolean async;
    public Object result;
    public String service;
    public AdaWebview webview;

    public PerWrapper(Object obj, AdaWebview adaWebview, String str, String str2, JSONArray jSONArray, boolean z) {
        this.result = obj;
        this.webview = adaWebview;
        this.service = str;
        this.action = str2;
        this.args = jSONArray;
        this.async = z;
    }

    public void confirm(String str) {
        Object obj = this.result;
        if (obj != null) {
            PlatformUtil.invokeMethod(obj, "confirm", new Class[]{String.class}, str);
        }
    }
}
