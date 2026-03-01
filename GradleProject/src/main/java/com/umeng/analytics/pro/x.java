package com.umeng.analytics.pro;

import android.content.Context;
import android.text.TextUtils;
import com.taobao.weex.ui.component.list.template.CellDataManager;
import com.umeng.analytics.CoreProtocol;
import com.umeng.analytics.pro.k;
import com.umeng.analytics.vshelper.PageNameMonitor;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.service.UMGlobalContext;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: ViewPageTracker.java */
/* JADX INFO: loaded from: classes2.dex */
public class x {
    public static final int c = 5;
    public static JSONArray d = new JSONArray();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Object f5320e = new Object();
    public final Map<String, Long> f = new HashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Stack<String> f5321a = new Stack<>();
    public com.umeng.analytics.vshelper.a b = PageNameMonitor.getInstance();

    public static void a(Context context) {
        String string;
        if (context != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                synchronized (f5320e) {
                    string = d.toString();
                    d = new JSONArray();
                }
                if (string.length() > 0) {
                    jSONObject.put("__a", new JSONArray(string));
                    if (jSONObject.length() > 0) {
                        k.a(context).a(w.a().c(), jSONObject, k.a.PAGE);
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    public int a() {
        return 2;
    }

    public void b(String str) {
        Long l;
        Context appContext;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (!this.f.containsKey(str)) {
            if (UMConfigure.isDebugLog() && this.f5321a.size() == 0) {
                UMLog.aq(l.G, 0, "\\|", new String[]{CellDataManager.VIRTUAL_COMPONENT_SEPRATOR}, new String[]{str}, null, null);
                return;
            }
            return;
        }
        synchronized (this.f) {
            l = this.f.get(str);
            this.f.remove(str);
        }
        if (l == null) {
            return;
        }
        if (UMConfigure.isDebugLog() && this.f5321a.size() > 0 && str.equals(this.f5321a.peek())) {
            this.f5321a.pop();
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - l.longValue();
        synchronized (f5320e) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(f.v, str);
                jSONObject.put("duration", jCurrentTimeMillis);
                jSONObject.put(f.x, l);
                jSONObject.put("type", a());
                d.put(jSONObject);
                if (d.length() >= 5 && (appContext = UMGlobalContext.getAppContext(null)) != null) {
                    UMWorkDispatch.sendEvent(appContext, 4099, CoreProtocol.getInstance(appContext), null);
                }
            } catch (Throwable unused) {
            }
        }
        if (!UMConfigure.isDebugLog() || this.f5321a.size() == 0) {
            return;
        }
        UMLog.aq(l.E, 0, "\\|", new String[]{CellDataManager.VIRTUAL_COMPONENT_SEPRATOR}, new String[]{str}, null, null);
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (UMConfigure.isDebugLog() && this.f5321a.size() != 0) {
            UMLog.aq(l.F, 0, "\\|", new String[]{CellDataManager.VIRTUAL_COMPONENT_SEPRATOR}, new String[]{this.f5321a.peek()}, null, null);
        }
        this.b.customPageBegin(str);
        synchronized (this.f) {
            this.f.put(str, Long.valueOf(System.currentTimeMillis()));
            if (UMConfigure.isDebugLog()) {
                this.f5321a.push(str);
            }
        }
    }

    public void b() {
        String key;
        synchronized (this.f) {
            key = null;
            long j = 0;
            for (Map.Entry<String, Long> entry : this.f.entrySet()) {
                if (entry.getValue().longValue() > j) {
                    long jLongValue = entry.getValue().longValue();
                    key = entry.getKey();
                    j = jLongValue;
                }
            }
        }
        if (key != null) {
            b(key);
        }
    }
}
