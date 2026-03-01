package io.dcloud.invocation;

import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.taobao.weex.el.parse.Operators;
import dc.squareup.okhttp3.HttpUrl;
import io.dcloud.common.DHInterface.AbsMgr;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IEventCallback;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.ISysEventListener;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.io.DHFile;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.adapter.ui.AdaUniWebView;
import io.dcloud.common.adapter.util.PermissionUtil;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.feature.internal.sdk.SDK;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import supwisdom.a8;
import supwisdom.j7;

/* JADX INFO: loaded from: classes3.dex */
public class a implements IEventCallback {
    public static HashMap<Integer, HashMap<String, io.dcloud.invocation.c>> b = new HashMap<>(2);
    public static a c = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList<Integer> f6705a = new ArrayList<>();

    /* JADX INFO: renamed from: io.dcloud.invocation.a$a, reason: collision with other inner class name */
    public class C0188a extends PermissionUtil.Request {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ JSONArray f6706a;
        public final /* synthetic */ IWebview b;
        public final /* synthetic */ String[] c;
        public final /* synthetic */ JSONArray d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ JSONArray f6707e;
        public final /* synthetic */ JSONArray f;

        public C0188a(JSONArray jSONArray, IWebview iWebview, String[] strArr, JSONArray jSONArray2, JSONArray jSONArray3, JSONArray jSONArray4) {
            this.f6706a = jSONArray;
            this.b = iWebview;
            this.c = strArr;
            this.d = jSONArray2;
            this.f6707e = jSONArray3;
            this.f = jSONArray4;
        }

        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
        public void onDenied(String str) {
            String strConvertNativePermission = PermissionUtil.convertNativePermission(str);
            try {
                if (j7.a(this.b.getActivity(), strConvertNativePermission)) {
                    this.f6707e.put(strConvertNativePermission);
                } else {
                    this.f.put(strConvertNativePermission);
                }
            } catch (RuntimeException unused) {
            }
            a.this.a(this.b, this.c, this.d, this.f6706a, this.f6707e, this.f);
        }

        @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
        public void onGranted(String str) {
            this.f6706a.put(PermissionUtil.convertNativePermission(str));
            a.this.a(this.b, this.c, this.d, this.f6706a, this.f6707e, this.f);
        }
    }

    public class b implements ISysEventListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ IWebview f6708a;
        public final /* synthetic */ String b;

        public b(a aVar, IWebview iWebview, String str) {
            this.f6708a = iWebview;
            this.b = str;
        }

        @Override // io.dcloud.common.DHInterface.ISysEventListener
        public boolean onExecute(ISysEventListener.SysEventType sysEventType, Object obj) {
            if (sysEventType != ISysEventListener.SysEventType.onActivityResult) {
                return false;
            }
            Object[] objArr = (Object[]) obj;
            int iIntValue = ((Integer) objArr[0]).intValue();
            int iIntValue2 = ((Integer) objArr[1]).intValue();
            Intent intent = (Intent) objArr[2];
            StringBuffer stringBuffer = new StringBuffer(Operators.ARRAY_START_STR);
            stringBuffer.append(iIntValue);
            stringBuffer.append(",");
            stringBuffer.append(iIntValue2);
            if (intent != null) {
                stringBuffer.append(",");
                stringBuffer.append(a.a(this.f6708a, intent));
            }
            stringBuffer.append(Operators.ARRAY_END_STR);
            Deprecated_JSUtil.execCallback(this.f6708a, this.b, stringBuffer.toString(), JSUtil.OK, true, true);
            return true;
        }
    }

    public class c implements IEventCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ IWebview f6709a;
        public final /* synthetic */ ISysEventListener b;

        public c(a aVar, IWebview iWebview, ISysEventListener iSysEventListener) {
            this.f6709a = iWebview;
            this.b = iSysEventListener;
        }

        @Override // io.dcloud.common.DHInterface.IEventCallback
        public Object onCallBack(String str, Object obj) {
            if (!PdrUtil.isEquals(str, AbsoluteConst.EVENTS_WINDOW_CLOSE) && !PdrUtil.isEquals(str, "close")) {
                return null;
            }
            this.f6709a.obtainApp().unregisterSysEventListener(this.b, ISysEventListener.SysEventType.onActivityResult);
            return null;
        }
    }

    public static /* synthetic */ class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f6710a;

        static {
            int[] iArr = new int[ISysEventListener.SysEventType.values().length];
            f6710a = iArr;
            try {
                iArr[ISysEventListener.SysEventType.onActivityResult.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public a(AbsMgr absMgr) {
        c = this;
    }

    public static void b(Class cls, JSONStringer jSONStringer, ArrayList<String> arrayList) throws JSONException {
        for (Class superclass = cls.getSuperclass(); superclass != null; superclass = superclass.getSuperclass()) {
            String name = superclass.getName();
            if (!arrayList.contains(name)) {
                jSONStringer.value(name);
                arrayList.add(name);
                a(superclass, jSONStringer, arrayList);
            }
            if (superclass == Object.class) {
                return;
            }
        }
    }

    @Override // io.dcloud.common.DHInterface.IEventCallback
    public Object onCallBack(String str, Object obj) {
        if (!PdrUtil.isEquals(str, "close") || !(obj instanceof IWebview)) {
            return null;
        }
        try {
            ((AdaFrameView) ((IWebview) obj).obtainFrameView()).removeFrameViewListener(this);
            this.f6705a.remove(Integer.valueOf(((IWebview) obj).hashCode()));
            HashMap<String, io.dcloud.invocation.c> mapRemove = b.remove(Integer.valueOf(((IWebview) obj).hashCode()));
            if (mapRemove == null) {
                return null;
            }
            for (Map.Entry<String, io.dcloud.invocation.c> entry : mapRemove.entrySet()) {
                b.remove(entry.getKey());
                entry.getValue().a();
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public String a(IWebview iWebview, String str, String[] strArr) {
        io.dcloud.invocation.c cVarRemove;
        int i;
        String strB;
        Class cls;
        String str2;
        io.dcloud.invocation.c cVarA;
        Object objA;
        JSONArray jSONArray;
        if (!this.f6705a.contains(Integer.valueOf(iWebview.hashCode()))) {
            this.f6705a.add(Integer.valueOf(iWebview.hashCode()));
            ((AdaFrameView) iWebview.obtainFrameView()).addFrameViewListener(this);
        }
        if ("__Instance".equals(str)) {
            String str3 = strArr[0];
            String str4 = strArr[1];
            if (strArr.length <= 2 || PdrUtil.isEmpty(strArr[2])) {
                jSONArray = null;
            } else {
                JSONArray jSONArrayCreateJSONArray = JSONUtil.createJSONArray(strArr[2]);
                JSONObject jSONObject = JSONUtil.getJSONObject(jSONArrayCreateJSONArray, 0);
                zOptBoolean = jSONObject != null ? "boolean".equals(jSONObject.optString("type")) ? jSONObject.optBoolean("value") : true ^ PdrUtil.isEquals("__super__constructor__", JSONUtil.getString(jSONObject, "value")) : true;
                jSONArray = jSONArrayCreateJSONArray;
            }
            if (zOptBoolean) {
                try {
                    a(iWebview, str3, new io.dcloud.invocation.c(iWebview, this, io.dcloud.invocation.d.a(str4), str3, jSONArray));
                } catch (Exception e2) {
                    String strA = a(e2, "new " + str4);
                    Log.e("InvProxy", "NativeObject.execMethod __Instance " + str4 + " method ; params=" + jSONArray + e2);
                    return strA;
                }
            }
        } else if ("release".equals(str) || "__autoCollection".equals(str)) {
            String str5 = strArr[0];
            HashMap<String, io.dcloud.invocation.c> mapA = a(iWebview);
            if (mapA != null && (cVarRemove = mapA.remove(str5)) != null) {
                cVarRemove.a();
            }
        } else {
            if ("getWebviewById".equals(str)) {
                return b(iWebview, SDK.obtainWebview(iWebview.obtainFrameView().obtainApp().obtainAppId(), strArr[0]).obtainWebview());
            }
            if ("currentWebview".equals(str)) {
                return iWebview instanceof AdaUniWebView ? "" : b(iWebview, iWebview.obtainWebview());
            }
            if ("getContext".equals(str)) {
                String str6 = strArr[0];
                String strB2 = b(iWebview, iWebview.getActivity());
                a(iWebview, "onActivityResult", str6);
                return strB2;
            }
            if ("importFields".equals(str)) {
                io.dcloud.invocation.c cVarA2 = a(iWebview, strArr[0]);
                if (cVarA2 != null) {
                    return JSUtil.wrapJsVar(cVarA2.a(iWebview, cVarA2.f6713a));
                }
            } else {
                if ("import".equals(str)) {
                    return Deprecated_JSUtil.wrapJsVar(io.dcloud.invocation.d.a(iWebview, this, strArr[0]), false);
                }
                if ("__plusGetAttribute".equals(str)) {
                    String str7 = strArr[0];
                    String str8 = strArr[1];
                    io.dcloud.invocation.c cVarA3 = a(iWebview, str7);
                    if (cVarA3 != null && (objA = io.dcloud.invocation.c.a(cVarA3.b, cVarA3.c, str8)) != null) {
                        return b(iWebview, objA);
                    }
                } else if ("__plusSetAttribute".equals(str)) {
                    String str9 = strArr[0];
                    String str10 = strArr[1];
                    JSONArray jSONArrayCreateJSONArray2 = JSONUtil.createJSONArray(strArr[2]);
                    io.dcloud.invocation.c cVarA4 = a(iWebview, str9);
                    if (cVarA4 != null) {
                        io.dcloud.invocation.c.b(iWebview, this, cVarA4.b, cVarA4.c, str10, jSONArrayCreateJSONArray2);
                    }
                } else {
                    if ("implements".equals(str)) {
                        String str11 = strArr[0];
                        io.dcloud.invocation.b bVar = new io.dcloud.invocation.b(iWebview, strArr[1], JSONUtil.createJSONArray(strArr[2]), strArr[3]);
                        bVar.f6711a = str11;
                        return b(iWebview, bVar.a(null));
                    }
                    if (!"__loadDylib".equals(str) && !"__release".equals(str)) {
                        if ("__inheritList".equals(str)) {
                            String str12 = strArr[0];
                            try {
                                String str13 = strArr[1];
                                strB = (TextUtils.isEmpty(str13) || (cVarA = a(iWebview, str13)) == null) ? io.dcloud.invocation.d.b(str12) : io.dcloud.invocation.d.c(cVarA.b);
                            } catch (Exception e3) {
                                strB = a(e3, "importClass " + str12);
                            }
                        } else if (!"__execCFunction".equals(str)) {
                            if ("__newObject".equals(str)) {
                                String str14 = strArr[0];
                                JSONArray jSONArrayCreateJSONArray3 = JSONUtil.createJSONArray(strArr[1]);
                                try {
                                    strB = b(iWebview, io.dcloud.invocation.c.a(iWebview, this, io.dcloud.invocation.d.a(str14), jSONArrayCreateJSONArray3));
                                } catch (Exception e4) {
                                    String strA2 = a(e4, "newObject " + str14);
                                    Log.e("InvProxy", "NativeObject.execMethod __newObject " + str14 + " method ; params=" + jSONArrayCreateJSONArray3 + e4);
                                    return strA2;
                                }
                            } else if ("__execStatic".equals(str)) {
                                String str15 = strArr[0];
                                String str16 = strArr[1];
                                if (a(str15, str16, iWebview)) {
                                    return null;
                                }
                                JSONArray jSONArrayCreateJSONArray4 = (strArr.length <= 2 || PdrUtil.isEmpty(strArr[2])) ? null : JSONUtil.createJSONArray(strArr[2]);
                                Class clsA = io.dcloud.invocation.d.a(str15);
                                if (clsA == null) {
                                    str2 = str15;
                                    cls = String.class;
                                } else {
                                    cls = clsA;
                                    str2 = null;
                                }
                                try {
                                    Object objA2 = io.dcloud.invocation.c.a(iWebview, this, cls, str2, str16, jSONArrayCreateJSONArray4);
                                    if (objA2 != null) {
                                        strB = b(iWebview, objA2);
                                    }
                                } catch (Exception e5) {
                                    String strA3 = a(e5, "static " + cls.getName() + Operators.DOT_STR + str16);
                                    Log.e("InvProxy", "NativeObject.execMethod " + str16 + " method ; params=" + cls + e5);
                                    return strA3;
                                }
                            } else if ("__exec".equals(str)) {
                                String str17 = strArr[0];
                                String str18 = strArr[1];
                                if (a("", str18, iWebview)) {
                                    return null;
                                }
                                JSONArray jSONArrayCreateJSONArray5 = JSONUtil.createJSONArray(strArr[2]);
                                io.dcloud.invocation.c cVarA5 = a(iWebview, str17);
                                if (cVarA5 != null) {
                                    try {
                                        Object objA3 = cVarA5.a(iWebview, str18, jSONArrayCreateJSONArray5);
                                        if (objA3 != null) {
                                            strB = b(iWebview, objA3);
                                        }
                                    } catch (Exception e6) {
                                        String strA4 = a(e6, cVarA5.b.getName() + Operators.DOT_STR + str18);
                                        Log.e("InvProxy", "NativeObject.execMethod " + str18 + " method ; params=" + jSONArrayCreateJSONArray5 + e6);
                                        return strA4;
                                    }
                                }
                            } else if ("__saveContent".equals(str)) {
                                DHFile.writeFile(strArr[1].toString().getBytes(), 0, iWebview.obtainFrameView().obtainApp().convert2AbsFullPath(iWebview.obtainFullUrl(), strArr[0]));
                            } else if ("requestPermissions".equals(str)) {
                                JSONArray jSONArrayCreateJSONArray6 = JSONUtil.createJSONArray(strArr[1]);
                                ArrayList arrayList = new ArrayList();
                                JSONArray jSONArray2 = new JSONArray();
                                JSONArray jSONArray3 = new JSONArray();
                                JSONArray jSONArray4 = new JSONArray();
                                int i2 = 0;
                                while (i2 < jSONArrayCreateJSONArray6.length()) {
                                    String strOptString = jSONArrayCreateJSONArray6.optString(i2);
                                    if (a8.a(iWebview.getActivity(), strOptString) == 0) {
                                        i = i2;
                                        jSONArray2.put(strOptString);
                                        a(iWebview, strArr, jSONArrayCreateJSONArray6, jSONArray2, jSONArray3, jSONArray4);
                                    } else if (iWebview.getActivity().getApplicationInfo().targetSdkVersion < 23 || Build.VERSION.SDK_INT < 23) {
                                        jSONArray4.put(strOptString);
                                        i = i2;
                                        a(iWebview, strArr, jSONArrayCreateJSONArray6, jSONArray2, jSONArray3, jSONArray3);
                                    } else {
                                        arrayList.add(strOptString);
                                        i = i2;
                                    }
                                    i2 = i + 1;
                                }
                                if (arrayList.size() == 0) {
                                    return null;
                                }
                                PermissionUtil.requestSystemPermissions(iWebview.getActivity(), (String[]) arrayList.toArray(new String[arrayList.size()]), PermissionUtil.getRequestCode(), new C0188a(jSONArray2, iWebview, strArr, jSONArrayCreateJSONArray6, jSONArray3, jSONArray4));
                            }
                        }
                        return strB;
                    }
                }
            }
        }
        return null;
    }

    public static String b(IWebview iWebview, Object obj) {
        Class<?> cls = obj.getClass();
        String strB = io.dcloud.invocation.d.b(cls);
        String str = (io.dcloud.invocation.d.a((Class) cls) || cls == String.class || cls == CharSequence.class || cls.isArray()) ? "basic" : "object";
        StringBuffer stringBuffer = new StringBuffer();
        a(iWebview, obj, cls, stringBuffer);
        return Deprecated_JSUtil.wrapJsVar(StringUtil.format("{\"type\":\"%s\", \"value\":%s, \"className\":\"%s\",\"superClassNames\":%s}", str, stringBuffer.toString(), strB, a((Class) cls)), false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(IWebview iWebview, String[] strArr, JSONArray jSONArray, JSONArray jSONArray2, JSONArray jSONArray3, JSONArray jSONArray4) {
        if (jSONArray.length() == jSONArray2.length() + jSONArray3.length() + jSONArray4.length()) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("granted", jSONArray2);
                jSONObject.put("deniedPresent", jSONArray3);
                jSONObject.put("deniedAlways", jSONArray4);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            Deprecated_JSUtil.execCallback(iWebview, strArr[0], jSONObject.toString(), JSUtil.OK, true, false);
        }
    }

    public static String a(Exception exc, String str) {
        Exception message;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        if (exc.getCause() != null) {
            message = exc.getCause();
        } else {
            boolean zIsEmpty = TextUtils.isEmpty(exc.getMessage());
            message = exc;
            if (!zIsEmpty) {
                message = exc.getMessage();
            }
        }
        sb.append(message);
        sb.append(";at ");
        sb.append(str);
        objArr[0] = sb.toString();
        return StringUtil.format("throw '%s';", objArr);
    }

    private void a(IWebview iWebview, String str, String str2) {
        if (d.f6710a[ISysEventListener.SysEventType.valueOf(str).ordinal()] != 1) {
            return;
        }
        b bVar = new b(this, iWebview, str2);
        iWebview.obtainApp().registerSysEventListener(bVar, ISysEventListener.SysEventType.onActivityResult);
        iWebview.obtainFrameView().addFrameViewListener(new c(this, iWebview, bVar));
    }

    public static String a(IWebview iWebview, Object obj) {
        String strValueOf;
        Class<?> cls = obj.getClass();
        String strB = io.dcloud.invocation.d.b(cls);
        if (cls != String.class && cls != CharSequence.class) {
            if (io.dcloud.invocation.d.a((Class) cls)) {
                strValueOf = String.valueOf(obj);
            } else {
                String strA = a(obj);
                a(iWebview, strA, obj);
                return StringUtil.format("plus.ios.__Tool.New(%s, true)", Deprecated_JSUtil.wrapJsVar(StringUtil.format("{\"type\":\"%s\", \"value\":%s, \"className\":\"%s\",\"superClassNames\":%s}", "object", JSUtil.QUOTE + strA + JSUtil.QUOTE, strB, a((Class) cls)), false));
            }
        } else {
            strValueOf = JSUtil.QUOTE + String.valueOf(obj) + JSUtil.QUOTE;
        }
        return Deprecated_JSUtil.wrapJsVar(StringUtil.format("{\"type\":\"%s\", \"value\":%s, \"className\":\"%s\",\"superClassNames\":%s}", "basic", strValueOf, strB, a((Class) cls)), false);
    }

    public static String a(Class cls) {
        JSONStringer jSONStringer = new JSONStringer();
        ArrayList arrayList = new ArrayList();
        try {
            jSONStringer.array();
            b(cls, jSONStringer, arrayList);
            jSONStringer.endArray();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        String string = jSONStringer.toString();
        return string == null ? HttpUrl.PATH_SEGMENT_ENCODE_SET_URI : string;
    }

    public static void a(Class cls, JSONStringer jSONStringer, ArrayList<String> arrayList) throws JSONException {
        Class<?>[] interfaces = cls.getInterfaces();
        if (interfaces != null) {
            for (Class<?> cls2 : interfaces) {
                String name = cls2.getName();
                if (!arrayList.contains(name)) {
                    jSONStringer.value(name);
                    arrayList.add(name);
                    a(cls2, jSONStringer, arrayList);
                }
            }
        }
    }

    public static void a(IWebview iWebview, Object obj, Class cls, StringBuffer stringBuffer) {
        if (cls != String.class && cls != CharSequence.class) {
            if (io.dcloud.invocation.d.a(cls)) {
                stringBuffer.append(String.valueOf(obj));
                return;
            }
            if (cls.isArray()) {
                int length = Array.getLength(obj);
                stringBuffer.append(Operators.ARRAY_START_STR);
                for (int i = 0; i < length; i++) {
                    stringBuffer.append(b(iWebview, io.dcloud.invocation.d.a(Array.get(obj, i), cls)));
                    if (i != length - 1) {
                        stringBuffer.append(",");
                    }
                }
                stringBuffer.append(Operators.ARRAY_END_STR);
                return;
            }
            String strA = a(obj);
            a(iWebview, strA, obj);
            stringBuffer.append(JSUtil.QUOTE);
            stringBuffer.append(strA);
            stringBuffer.append(JSUtil.QUOTE);
            return;
        }
        stringBuffer.append(JSONObject.quote(String.valueOf(obj)));
    }

    public static HashMap<String, io.dcloud.invocation.c> a(IWebview iWebview) {
        HashMap<String, io.dcloud.invocation.c> map = b.get(Integer.valueOf(iWebview.hashCode()));
        if (map != null) {
            return map;
        }
        HashMap<String, io.dcloud.invocation.c> map2 = new HashMap<>(2);
        b.put(Integer.valueOf(iWebview.hashCode()), map2);
        return map2;
    }

    public io.dcloud.invocation.c a(HashMap<String, io.dcloud.invocation.c> map, String str) {
        return map.get(str);
    }

    public io.dcloud.invocation.c a(IWebview iWebview, String str) {
        return a(a(iWebview), str);
    }

    public static void a(IWebview iWebview, String str, io.dcloud.invocation.c cVar) {
        a(iWebview).put(str, cVar);
    }

    public static io.dcloud.invocation.c a(IWebview iWebview, String str, Object obj) {
        Class<?> cls = obj.getClass();
        cls.getName();
        io.dcloud.invocation.c cVar = new io.dcloud.invocation.c(c, cls, str, obj);
        a(iWebview, str, cVar);
        return cVar;
    }

    public static String a(Object obj) {
        return IFeature.F_INVOCATION + obj.hashCode();
    }

    private boolean a(String str, String str2, IWebview iWebview) {
        return iWebview != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && Boolean.parseBoolean(iWebview.obtainApp().obtainConfigProperty(IApp.ConfigProperty.CONFIG_USE_ENCRYPTION)) && "setWebContentsDebuggingEnabled".equalsIgnoreCase(str2) && (TextUtils.isEmpty(str) || "WebView".equalsIgnoreCase(str) || "android.webkit.WebView".equalsIgnoreCase(str));
    }

    public void a(String str) {
        TextUtils.isEmpty(str);
    }
}
