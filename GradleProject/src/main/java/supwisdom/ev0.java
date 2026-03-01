package supwisdom;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import io.dcloud.feature.internal.sdk.SDK;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: WebViewJavaScriptBridgeBase.java */
/* JADX INFO: loaded from: classes2.dex */
public class ev0 {
    public static final String f = "ev0";
    public static boolean g = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public e f7533a;
    public ArrayList<HashMap<String, d>> b;
    public HashMap<String, d> c;
    public HashMap<String, c> d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public bv0 f7534e;

    /* JADX INFO: compiled from: WebViewJavaScriptBridgeBase.java */
    public class a implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f7535a;

        public a(String str) {
            this.f7535a = str;
        }

        @Override // supwisdom.ev0.d
        public void callback(String str) {
            HashMap map = new HashMap();
            map.put("responseId", this.f7535a);
            map.put("responseData", str);
            ev0.this.b(map);
        }
    }

    /* JADX INFO: compiled from: WebViewJavaScriptBridgeBase.java */
    public class b implements d {
        public b(ev0 ev0Var) {
        }

        @Override // supwisdom.ev0.d
        public void callback(String str) {
        }
    }

    /* JADX INFO: compiled from: WebViewJavaScriptBridgeBase.java */
    public interface c {
        void a(JSONObject jSONObject, d dVar);
    }

    /* JADX INFO: compiled from: WebViewJavaScriptBridgeBase.java */
    public interface d {
        void callback(String str);
    }

    /* JADX INFO: compiled from: WebViewJavaScriptBridgeBase.java */
    public interface e {
        void a(String str);
    }

    public String a() {
        return "javascript:WebViewJavascriptBridge._fetchQueue();";
    }

    public boolean b(Uri uri) {
        return (uri == null || uri.getScheme() == null || !uri.getScheme().equals("wvjbscheme")) ? false : true;
    }

    public void c(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.e(f, "WebViewJavascriptBridge: WARNING: ObjC got nil while fetching the message queue JSON from webview. This can happen if the WebViewJavascriptBridge JS is not currently present in the webview, e.g if the webview just loaded a new page.");
            return;
        }
        JSONArray jSONArrayA = a(URLDecoder.decode(str));
        if (jSONArrayA != null) {
            for (int i = 0; i < jSONArrayA.length(); i++) {
                try {
                    JSONObject jSONObjectOptJSONObject = jSONArrayA.optJSONObject(i);
                    a("flushMessageQueue", jSONObjectOptJSONObject.toString());
                    String strOptString = jSONObjectOptJSONObject.optString("responseId");
                    if (TextUtils.isEmpty(strOptString)) {
                        String strOptString2 = jSONObjectOptJSONObject.optString(SDK.UNIMP_EVENT_CALLBACKID);
                        d aVar = !TextUtils.isEmpty(strOptString2) ? new a(strOptString2) : new b(this);
                        this.f7534e.a(jSONObjectOptJSONObject.optString("handlerName"), jSONObjectOptJSONObject.optString("data"), aVar);
                        c cVar = this.d.get(jSONObjectOptJSONObject.optString("handlerName"));
                        if (cVar == null) {
                            Log.e(f, String.format("WVJBNoHandlerException, No handler for message from JS: %s", jSONObjectOptJSONObject));
                        } else {
                            String strOptString3 = jSONObjectOptJSONObject.optString("data");
                            cVar.a(!TextUtils.isEmpty(strOptString3) ? new JSONObject(strOptString3) : new JSONObject(), aVar);
                        }
                    } else {
                        this.c.get(strOptString).callback(jSONObjectOptJSONObject.optString("responseData"));
                        this.c.remove(strOptString);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public boolean d(Uri uri) {
        return (uri == null || uri.getHost() == null || !uri.getHost().equals("__WVJB_RETURN_MESSAGE__")) ? false : true;
    }

    public void e(Uri uri) {
        Log.i(f, String.format("WebViewJavascriptBridge: WARNING: Received unknown WebViewJavascriptBridge command %s://%s", "wvjbscheme", uri.getPath()));
    }

    public ev0 a(Context context, List<av0> list) {
        this.d = new HashMap<>();
        this.b = new ArrayList<>();
        this.c = new HashMap<>();
        bv0 bv0Var = new bv0(context, list);
        this.f7534e = bv0Var;
        bv0Var.a();
        return this;
    }

    public final void b(String str) {
        this.f7533a.a(str);
    }

    public final void b(HashMap map) {
        ArrayList<HashMap<String, d>> arrayList = this.b;
        if (arrayList != null) {
            arrayList.add(map);
        } else {
            a(map);
        }
    }

    public void a(Context context) {
        b(dv0.b(context, "webviewjsbridge.js"));
        if (this.b != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.b);
            this.b = null;
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                a((HashMap) it.next());
            }
        }
    }

    public boolean a(Uri uri) {
        return (uri == null || uri.getScheme() == null || uri.getHost() == null || !uri.getScheme().equals("wvjbscheme") || !uri.getHost().equals("__BRIDGE_LOADED__")) ? false : true;
    }

    public final void a(HashMap map) {
        String strReplaceAll = c(map).replaceAll("(\\\\)([^utrn])", "\\\\\\\\$1$2").replaceAll("(?<=[^\\\\])(\")", "\\\\\"");
        a("_dispatchMessage", strReplaceAll);
        b(String.format("javascript:WebViewJavascriptBridge._handleMessageFromObjC('%s');", strReplaceAll));
    }

    public final JSONArray a(String str) {
        a("decode", str);
        try {
            return new JSONArray(str);
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public boolean c(Uri uri) {
        return (uri == null || uri.getHost() == null || !uri.getHost().equals("__WVJB_QUEUE_MESSAGE__")) ? false : true;
    }

    public final String c(HashMap map) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(SDK.UNIMP_EVENT_CALLBACKID, map.get(SDK.UNIMP_EVENT_CALLBACKID));
            jSONObject.put("data", map.get("data"));
            jSONObject.put("handlerName", map.get("handlerName"));
            jSONObject.put("responseData", map.get("responseData"));
            jSONObject.put("responseId", map.get("responseId"));
            return jSONObject.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void a(e eVar) {
        this.f7533a = eVar;
    }

    public final void a(String str, String str2) {
        if (g) {
            Log.i(f, String.format("WVJB %s: %s", str, str2));
        }
    }
}
