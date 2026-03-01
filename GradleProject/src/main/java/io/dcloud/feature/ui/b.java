package io.dcloud.feature.ui;

import android.content.Context;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameItem;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.ViewRect;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.JsEventUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public abstract class b {
    public static byte n = ViewRect.POSITION_STATIC;
    public static byte o = ViewRect.POSITION_ABSOLUTE;
    public static byte p = ViewRect.POSITION_DOCK;
    public static byte q = ViewRect.DOCK_LEFT;
    public static byte r = ViewRect.DOCK_RIGHT;
    public static byte s = ViewRect.DOCK_TOP;
    public static byte t = ViewRect.DOCK_BOTTOM;
    public HashMap<String, String> b;
    public String d;
    public IWebview i;
    public Context l;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c f6616a = null;
    public a c = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f6617e = null;
    public String f = null;
    public String g = null;
    public JSONObject h = null;
    public byte j = o;
    public byte k = s;
    public HashMap<String, ArrayList<String[]>> m = null;

    public b(String str) {
        this.b = null;
        this.d = str;
        this.b = new HashMap<>();
    }

    public abstract String a(IWebview iWebview, String str, JSONArray jSONArray);

    public abstract void a(int i, int i2, int i3, int i4, int i5, int i6);

    public void a(Context context, a aVar, IWebview iWebview, String str, JSONObject jSONObject) {
        this.c = aVar;
        this.l = context;
        this.i = iWebview;
        this.f6617e = str;
        if (jSONObject == null) {
            jSONObject = JSONUtil.createJSONObject("{}");
        }
        this.h = jSONObject;
        f();
    }

    public final byte b() {
        return this.k;
    }

    public final byte c() {
        return this.j;
    }

    public abstract AdaFrameItem d();

    public abstract void e();

    public void f() {
        JSONObject jSONObject = this.h;
        if (!JSONUtil.isNull(jSONObject, "id") && PdrUtil.isEmpty(this.f)) {
            this.f = JSONUtil.getString(jSONObject, "id");
        }
        this.g = jSONObject.optString("tid");
        String string = JSONUtil.getString(jSONObject, "position");
        if (!PdrUtil.isEmpty(string)) {
            if (AbsoluteConst.JSON_VALUE_POSITION_ABSOLUTE.equals(string)) {
                this.j = o;
            } else if ("dock".equals(string)) {
                this.j = p;
            } else if (AbsoluteConst.JSON_VALUE_POSITION_STATIC.equals(string)) {
                this.j = n;
            }
        }
        String string2 = JSONUtil.getString(jSONObject, "dock");
        if (PdrUtil.isEmpty(string2)) {
            return;
        }
        if ("bottom".equals(string2)) {
            this.k = t;
            return;
        }
        if ("top".equals(string2)) {
            this.k = s;
        } else if ("left".equals(string2)) {
            this.k = q;
        } else if ("right".equals(string2)) {
            this.k = r;
        }
    }

    public void g() {
        Iterator<c> it = this.c.c.iterator();
        while (it.hasNext()) {
            a(this, it.next().m);
        }
    }

    public String h() {
        return StringUtil.format("(function(){return {uuid:'%s',identity:'%s',option:%s}})()", this.f6617e, this.d, this.h);
    }

    public void b(String str, String str2) {
        ArrayList<String[]> arrayList;
        HashMap<String, ArrayList<String[]>> map = this.m;
        if (map == null || (arrayList = map.get(str2)) == null) {
            return;
        }
        for (String[] strArr : arrayList) {
            if (strArr[0].equals(str)) {
                arrayList.remove(strArr);
            }
        }
        if (arrayList.size() == 0) {
            this.m.remove(str2);
        }
    }

    public final Context a() {
        return this.l;
    }

    public void a(String str, String str2, String str3) {
        if (this.m == null) {
            this.m = new HashMap<>(2);
        }
        ArrayList<String[]> arrayList = this.m.get(str2);
        if (arrayList == null) {
            arrayList = new ArrayList<>(2);
            this.m.put(str2, arrayList);
        }
        arrayList.add(new String[]{str, str3});
    }

    public static void a(b bVar, HashMap<String, ArrayList<String[]>> map) {
        if (map != null) {
            for (ArrayList<String[]> arrayList : map.values()) {
                if (arrayList != null) {
                    for (int size = arrayList.size() - 1; size >= 0; size--) {
                        String str = arrayList.get(size)[0];
                        if (bVar.c.a(str, str, (String) null) == bVar) {
                            arrayList.remove(size);
                        }
                    }
                }
            }
        }
    }

    public boolean a(String str) {
        ArrayList<String[]> arrayList;
        HashMap<String, ArrayList<String[]>> map = this.m;
        if (map == null || (arrayList = map.get(str)) == null) {
            return false;
        }
        return !arrayList.isEmpty();
    }

    public final boolean a(String str, String str2) {
        return a(str, str2, true);
    }

    public boolean a(String str, String str2, boolean z) {
        ArrayList<String[]> arrayList;
        IWebview iWebviewObtainWebView;
        Logger.d("execCallback pEventType=" + str + ";");
        HashMap<String, ArrayList<String[]>> map = this.m;
        if (map == null || (arrayList = map.get(str)) == null) {
            return false;
        }
        int size = arrayList.size();
        String strEventListener_format = JsEventUtil.eventListener_format(str, str2, z);
        boolean z2 = false;
        for (int i = size - 1; i >= 0; i--) {
            String[] strArr = arrayList.get(i);
            String str3 = strArr[0];
            String str4 = strArr[1];
            c cVarA = this.c.a(str3, str3, (String) null);
            if (cVarA != null && !cVarA.I && (iWebviewObtainWebView = cVarA.y.obtainWebView()) != null) {
                Deprecated_JSUtil.execCallback(iWebviewObtainWebView, str4, strEventListener_format, JSUtil.OK, true, true);
                z2 = true;
            }
        }
        return z2;
    }
}
