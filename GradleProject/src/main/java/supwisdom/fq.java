package supwisdom;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.JsPromptResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import io.dcloud.common.adapter.util.DeviceInfo;
import io.dcloud.common.constant.AbsoluteConst;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import supwisdom.gq;
import supwisdom.ro;

/* JADX INFO: loaded from: classes.dex */
public class fq extends eq implements gq.f, gq.g, gq.h {
    public boolean c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f7625e;
    public final pp f;
    public boolean g;
    public gq h;
    public hq i;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            fq.this.f7515a.finish();
        }
    }

    public class b extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ gq f7627a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(gq gqVar) {
            super(null);
            this.f7627a = gqVar;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.f7627a.a();
            fq.this.f7625e = false;
        }
    }

    public class c extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ gq f7628a;
        public final /* synthetic */ String b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(gq gqVar, String str) {
            super(null);
            this.f7628a = gqVar;
            this.b = str;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            fq.this.removeView(this.f7628a);
            fq.this.h.a(this.b);
            fq.this.f7625e = false;
        }
    }

    public class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f7629a;
        public final /* synthetic */ SslErrorHandler b;

        public class a implements DialogInterface.OnClickListener {
            public a() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                d.this.b.cancel();
                so.a(fq.this.f, "net", "SSLDenied", "2");
                qo.a(qo.c());
                d.this.f7629a.finish();
            }
        }

        public d(Activity activity, SslErrorHandler sslErrorHandler) {
            this.f7629a = activity;
            this.b = sslErrorHandler;
        }

        @Override // java.lang.Runnable
        public void run() {
            dq.a(this.f7629a, "安全警告", "安全连接证书校验无效，将无法保证访问数据的安全性，请安装支付宝后重试。", "确定", new a(), null, null);
        }
    }

    public static abstract class e implements Animation.AnimationListener {
        public e() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }

        public /* synthetic */ e(a aVar) {
            this();
        }
    }

    public static class g implements ro.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final f f7632a;
        public final String b;

        public g(f fVar, String str) {
            this.f7632a = fVar;
            this.b = str;
        }

        @Override // supwisdom.ro.a
        public void a(boolean z, JSONObject jSONObject, String str) {
            try {
                this.f7632a.a(new JSONObject().put("success", z).put("random", this.b).put("code", jSONObject).put("status", str));
            } catch (JSONException unused) {
            }
        }
    }

    public fq(Activity activity, pp ppVar, String str) {
        super(activity, str);
        this.c = true;
        this.d = "GET";
        this.f7625e = false;
        this.h = null;
        this.i = new hq();
        this.f = ppVar;
        e();
    }

    @Override // supwisdom.gq.f
    public synchronized void c(gq gqVar, String str) {
        if (!str.startsWith("http") && !gqVar.getUrl().endsWith(str)) {
            this.h.getTitle().setText(str);
        }
    }

    @Override // supwisdom.gq.g
    public synchronized boolean d(gq gqVar, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Activity activity = this.f7515a;
        if (activity == null) {
            return true;
        }
        if (bq.a(this.f, str, activity)) {
            return true;
        }
        if (str.startsWith("alipayjsbridge://")) {
            b(str.substring(17));
        } else if (TextUtils.equals(str, "sdklite://h5quit")) {
            a(false);
        } else if (str.startsWith(DeviceInfo.HTTP_PROTOCOL) || str.startsWith(DeviceInfo.HTTPS_PROTOCOL)) {
            this.h.a(str);
        } else {
            try {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                activity.startActivity(intent);
            } catch (Throwable th) {
                so.a(this.f, "biz", th);
            }
        }
        return true;
    }

    public final synchronized boolean e() {
        try {
            gq gqVar = new gq(this.f7515a, this.f, new gq.e(!c(), !c()));
            this.h = gqVar;
            gqVar.setChromeProxy(this);
            this.h.setWebClientProxy(this);
            this.h.setWebEventProxy(this);
            addView(this.h);
        } catch (Exception unused) {
            return false;
        }
        return true;
    }

    public final synchronized void f() {
        Activity activity = this.f7515a;
        gq gqVar = this.h;
        if (activity != null && gqVar != null) {
            if (this.c) {
                activity.finish();
            } else {
                gqVar.a("javascript:window.AlipayJSBridge.callListener('h5BackAction');");
            }
        }
    }

    public final synchronized void g() {
        WebView webView = this.h.getWebView();
        if (webView.canGoBack()) {
            webView.goBack();
        } else if (this.i == null || this.i.b()) {
            a(false);
        } else {
            h();
        }
    }

    public final synchronized boolean h() {
        if (this.i.b()) {
            this.f7515a.finish();
        } else {
            this.f7625e = true;
            gq gqVar = this.h;
            this.h = this.i.a();
            TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 1.0f, 1, 0.0f, 1, 0.0f);
            translateAnimation.setDuration(400L);
            translateAnimation.setFillAfter(false);
            translateAnimation.setAnimationListener(new b(gqVar));
            gqVar.setAnimation(translateAnimation);
            removeView(gqVar);
            addView(this.h);
        }
        return true;
    }

    public final void i() {
        gq gqVar = this.h;
        if (gqVar != null) {
            gqVar.getWebView().loadUrl("javascript:(function() {\n    if (window.AlipayJSBridge) {\n        return\n    }\n\n    function alipayjsbridgeFunc(url) {\n        var iframe = document.createElement(\"iframe\");\n        iframe.style.width = \"1px\";\n        iframe.style.height = \"1px\";\n        iframe.style.display = \"none\";\n        iframe.src = url;\n        document.body.appendChild(iframe);\n        setTimeout(function() {\n            document.body.removeChild(iframe)\n        }, 100)\n    }\n    window.alipayjsbridgeSetTitle = function(title) {\n        document.title = title;\n        alipayjsbridgeFunc(\"alipayjsbridge://setTitle?title=\" + encodeURIComponent(title))\n    };\n    window.alipayjsbridgeRefresh = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onRefresh?\")\n    };\n    window.alipayjsbridgeBack = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onBack?\")\n    };\n    window.alipayjsbridgeExit = function(bsucc) {\n        alipayjsbridgeFunc(\"alipayjsbridge://onExit?bsucc=\" + bsucc)\n    };\n    window.alipayjsbridgeShowBackButton = function(bshow) {\n        alipayjsbridgeFunc(\"alipayjsbridge://showBackButton?bshow=\" + bshow)\n    };\n    window.AlipayJSBridge = {\n        version: \"2.0\",\n        addListener: addListener,\n        hasListener: hasListener,\n        callListener: callListener,\n        callNativeFunc: callNativeFunc,\n        callBackFromNativeFunc: callBackFromNativeFunc\n    };\n    var uniqueId = 1;\n    var h5JsCallbackMap = {};\n\n    function iframeCall(paramStr) {\n        setTimeout(function() {\n        \tvar iframe = document.createElement(\"iframe\");\n        \tiframe.style.width = \"1px\";\n        \tiframe.style.height = \"1px\";\n        \tiframe.style.display = \"none\";\n        \tiframe.src = \"alipayjsbridge://callNativeFunc?\" + paramStr;\n        \tvar parent = document.body || document.documentElement;\n        \tparent.appendChild(iframe);\n        \tsetTimeout(function() {\n            \tparent.removeChild(iframe)\n        \t}, 0)\n        }, 0)\n    }\n\n    function callNativeFunc(nativeFuncName, data, h5JsCallback) {\n        var h5JsCallbackId = \"\";\n        if (h5JsCallback) {\n            h5JsCallbackId = \"cb_\" + (uniqueId++) + \"_\" + new Date().getTime();\n            h5JsCallbackMap[h5JsCallbackId] = h5JsCallback\n        }\n        var dataStr = \"\";\n        if (data) {\n            dataStr = encodeURIComponent(JSON.stringify(data))\n        }\n        var paramStr = \"func=\" + nativeFuncName + \"&cbId=\" + h5JsCallbackId + \"&data=\" + dataStr;\n        iframeCall(paramStr)\n    }\n\n    function callBackFromNativeFunc(h5JsCallbackId, data) {\n        var h5JsCallback = h5JsCallbackMap[h5JsCallbackId];\n        if (h5JsCallback) {\n            h5JsCallback(data);\n            delete h5JsCallbackMap[h5JsCallbackId]\n        }\n    }\n    var h5ListenerMap = {};\n\n    function addListener(jsFuncName, jsFunc) {\n        h5ListenerMap[jsFuncName] = jsFunc\n    }\n\n    function hasListener(jsFuncName) {\n        var jsFunc = h5ListenerMap[jsFuncName];\n        if (!jsFunc) {\n            return false\n        }\n        return true\n    }\n\n    function callListener(h5JsFuncName, data, nativeCallbackId) {\n        var responseCallback;\n        if (nativeCallbackId) {\n            responseCallback = function(responseData) {\n                var dataStr = \"\";\n                if (responseData) {\n                    dataStr = encodeURIComponent(JSON.stringify(responseData))\n                }\n                var paramStr = \"func=h5JsFuncCallback\" + \"&cbId=\" + nativeCallbackId + \"&data=\" + dataStr;\n                iframeCall(paramStr)\n            }\n        }\n        var h5JsFunc = h5ListenerMap[h5JsFuncName];\n        if (h5JsFunc) {\n            h5JsFunc(data, responseCallback)\n        } else if (h5JsFuncName == \"h5BackAction\") {\n            if (!window.alipayjsbridgeH5BackAction || !alipayjsbridgeH5BackAction()) {\n                var paramStr = \"func=back\";\n                iframeCall(paramStr)\n            }\n        } else {\n            console.log(\"AlipayJSBridge: no h5JsFunc \" + h5JsFuncName + data)\n        }\n    }\n    var event;\n    if (window.CustomEvent) {\n        event = new CustomEvent(\"alipayjsbridgeready\")\n    } else {\n        event = document.createEvent(\"Event\");\n        event.initEvent(\"alipayjsbridgeready\", true, true)\n    }\n    document.dispatchEvent(event);\n    setTimeout(excuteH5InitFuncs, 0);\n\n    function excuteH5InitFuncs() {\n        if (window.AlipayJSBridgeInitArray) {\n            var h5InitFuncs = window.AlipayJSBridgeInitArray;\n            delete window.AlipayJSBridgeInitArray;\n            for (var i = 0; i < h5InitFuncs.length; i++) {\n                try {\n                    h5InitFuncs[i](AlipayJSBridge)\n                } catch (e) {\n                    setTimeout(function() {\n                        throw e\n                    })\n                }\n            }\n        }\n    }\n})();\n;window.AlipayJSBridge.callListener('h5PageFinished');");
        }
    }

    @Override // android.view.ViewGroup
    public synchronized boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f7625e ? true : super.onInterceptTouchEvent(motionEvent);
    }

    @Override // supwisdom.eq
    public synchronized boolean b() {
        Activity activity = this.f7515a;
        if (activity == null) {
            return true;
        }
        if (!c()) {
            if (!this.f7625e) {
                f();
            }
            return true;
        }
        gq gqVar = this.h;
        if (gqVar != null && gqVar.getWebView() != null) {
            if (!gqVar.getWebView().canGoBack()) {
                qo.a(qo.c());
                activity.finish();
            } else if (d()) {
                com.alipay.sdk.app.c cVarB = com.alipay.sdk.app.c.b(com.alipay.sdk.app.c.NETWORK_ERROR.a());
                qo.a(qo.a(cVarB.a(), cVarB.b(), ""));
                activity.finish();
            }
            return true;
        }
        activity.finish();
        return true;
    }

    public synchronized void a(String str, String str2, boolean z) {
        this.d = str2;
        this.h.getTitle().setText(str);
        this.c = z;
    }

    public static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<gq> f7631a;
        public final String b;
        public final String c;
        public boolean d = false;

        public f(gq gqVar, String str, String str2, JSONObject jSONObject) {
            this.f7631a = new WeakReference<>(gqVar);
            this.b = str;
            this.c = str2;
        }

        public void a(JSONObject jSONObject) {
            gq gqVar;
            if (this.d || (gqVar = (gq) bq.a(this.f7631a)) == null) {
                return;
            }
            this.d = true;
            gqVar.a(String.format("javascript:window.AlipayJSBridge.callBackFromNativeFunc('%s','%s');", a(this.c), a(jSONObject.toString())));
        }

        public static String a(String str) {
            return TextUtils.isEmpty(str) ? "" : str.replace("'", "");
        }
    }

    public final synchronized void a(boolean z) {
        qo.a(z);
        this.f7515a.finish();
    }

    public synchronized void a(String str) {
        if ("POST".equals(this.d)) {
            this.h.a(str, (byte[]) null);
        } else {
            this.h.a(str);
        }
        eq.a(this.h.getWebView());
    }

    @Override // supwisdom.eq
    public synchronized void a() {
        this.h.a();
        this.i.c();
    }

    @Override // supwisdom.gq.f
    public synchronized boolean a(gq gqVar, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        if (str2.startsWith("<head>") && str2.contains("sdk_result_code:")) {
            this.f7515a.runOnUiThread(new a());
        }
        jsPromptResult.cancel();
        return true;
    }

    public boolean d() {
        return this.g;
    }

    public final synchronized boolean b(String str, String str2) {
        gq gqVar = this.h;
        try {
            gq gqVar2 = new gq(this.f7515a, this.f, new gq.e(!c(), !c()));
            this.h = gqVar2;
            gqVar2.setChromeProxy(this);
            this.h.setWebClientProxy(this);
            this.h.setWebEventProxy(this);
            if (!TextUtils.isEmpty(str2)) {
                this.h.getTitle().setText(str2);
            }
            this.f7625e = true;
            this.i.a(gqVar);
            TranslateAnimation translateAnimation = new TranslateAnimation(1, 1.0f, 1, 0.0f, 1, 0.0f, 1, 0.0f);
            translateAnimation.setDuration(400L);
            translateAnimation.setFillAfter(false);
            translateAnimation.setAnimationListener(new c(gqVar, str));
            this.h.setAnimation(translateAnimation);
            addView(this.h);
        } catch (Throwable unused) {
            return false;
        }
        return true;
    }

    @Override // supwisdom.gq.g
    public synchronized boolean a(gq gqVar, String str) {
        so.b(this.f, "biz", "h5ld", SystemClock.elapsedRealtime() + "|" + bq.e(str));
        i();
        return false;
    }

    @Override // supwisdom.gq.g
    public synchronized boolean a(gq gqVar, int i, String str, String str2) {
        this.g = true;
        so.a(this.f, "net", "SSLError", "onReceivedError:" + str2);
        gqVar.getRefreshButton().setVisibility(0);
        return false;
    }

    @Override // supwisdom.gq.g
    public synchronized boolean a(gq gqVar, SslErrorHandler sslErrorHandler, SslError sslError) {
        Activity activity = this.f7515a;
        if (activity == null) {
            return true;
        }
        so.a(this.f, "net", "SSLError", "2-" + sslError);
        activity.runOnUiThread(new d(activity, sslErrorHandler));
        return true;
    }

    public final synchronized void a(String str, String str2, String str3) {
        gq gqVar = this.h;
        if (gqVar == null) {
            return;
        }
        JSONObject jSONObjectC = bq.c(str3);
        f fVar = new f(gqVar, str, str2, jSONObjectC);
        Context context = gqVar.getContext();
        try {
            switch (fVar.b) {
                case "title":
                    if (jSONObjectC.has("title")) {
                        gqVar.getTitle().setText(jSONObjectC.optString("title", ""));
                        break;
                    }
                    break;
                case "refresh":
                    gqVar.getWebView().reload();
                    break;
                case "back":
                    g();
                    break;
                case "exit":
                    qo.a(jSONObjectC.optString("result", null));
                    a(jSONObjectC.optBoolean("success", false));
                    break;
                case "backButton":
                    gqVar.getBackButton().setVisibility(jSONObjectC.optBoolean(AbsoluteConst.EVENTS_WEBVIEW_SHOW, true) ? 0 : 4);
                    break;
                case "refreshButton":
                    gqVar.getRefreshButton().setVisibility(jSONObjectC.optBoolean(AbsoluteConst.EVENTS_WEBVIEW_SHOW, true) ? 0 : 4);
                    break;
                case "pushWindow":
                    b(jSONObjectC.optString("url"), jSONObjectC.optString("title", ""));
                    break;
                case "sdkInfo":
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("sdk_version", "15.8.00");
                    jSONObject.put("app_name", this.f.a());
                    jSONObject.put("app_version", this.f.b());
                    fVar.a(jSONObject);
                    break;
                case "canUseTaoLogin":
                    String url = gqVar.getUrl();
                    if (!bq.e(this.f, url)) {
                        so.a(this.f, "biz", "jsUrlErr", url);
                        break;
                    } else {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("enabled", ro.a(this.f, context));
                        fVar.a(jSONObject2);
                        break;
                    }
                    break;
                case "taoLogin":
                    String url2 = gqVar.getUrl();
                    if (!bq.e(this.f, url2)) {
                        so.a(this.f, "biz", "jsUrlErr", url2);
                        break;
                    } else {
                        String strOptString = jSONObjectC.optString("random");
                        JSONObject jSONObjectOptJSONObject = jSONObjectC.optJSONObject("options");
                        if (!TextUtils.isEmpty("random") && jSONObjectOptJSONObject != null) {
                            String strOptString2 = jSONObjectOptJSONObject.optString("url");
                            String strOptString3 = jSONObjectOptJSONObject.optString("action");
                            if (!TextUtils.isEmpty(strOptString2) && !TextUtils.isEmpty(strOptString3) && (context instanceof Activity)) {
                                ro.a(this.f, (Activity) context, 1010, strOptString2, strOptString3, new g(fVar, strOptString));
                            }
                        }
                        break;
                    }
                    break;
            }
        } catch (Throwable th) {
            so.a(this.f, "biz", "jInfoErr", th, str);
        }
    }

    @Override // supwisdom.gq.g
    public synchronized boolean b(gq gqVar, String str) {
        so.b(this.f, "biz", "h5ldd", SystemClock.elapsedRealtime() + "|" + bq.e(str));
        i();
        gqVar.getRefreshButton().setVisibility(0);
        return true;
    }

    public final synchronized void b(String str) {
        Map<String, String> mapA = bq.a(this.f, str);
        if (str.startsWith("callNativeFunc")) {
            a(mapA.get("func"), mapA.get("cbId"), mapA.get("data"));
        } else if (str.startsWith("onBack")) {
            g();
        } else if (str.startsWith("setTitle") && mapA.containsKey("title")) {
            this.h.getTitle().setText(mapA.get("title"));
        } else if (str.startsWith("onRefresh")) {
            this.h.getWebView().reload();
        } else if (str.startsWith("showBackButton") && mapA.containsKey("bshow")) {
            this.h.getBackButton().setVisibility(TextUtils.equals("true", mapA.get("bshow")) ? 0 : 4);
        } else if (str.startsWith("onExit")) {
            qo.a(mapA.get("result"));
            a(TextUtils.equals("true", mapA.get("bsucc")));
        } else if (str.startsWith("onLoadJs")) {
            this.h.a("javascript:(function() {\n    if (window.AlipayJSBridge) {\n        return\n    }\n\n    function alipayjsbridgeFunc(url) {\n        var iframe = document.createElement(\"iframe\");\n        iframe.style.width = \"1px\";\n        iframe.style.height = \"1px\";\n        iframe.style.display = \"none\";\n        iframe.src = url;\n        document.body.appendChild(iframe);\n        setTimeout(function() {\n            document.body.removeChild(iframe)\n        }, 100)\n    }\n    window.alipayjsbridgeSetTitle = function(title) {\n        document.title = title;\n        alipayjsbridgeFunc(\"alipayjsbridge://setTitle?title=\" + encodeURIComponent(title))\n    };\n    window.alipayjsbridgeRefresh = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onRefresh?\")\n    };\n    window.alipayjsbridgeBack = function() {\n        alipayjsbridgeFunc(\"alipayjsbridge://onBack?\")\n    };\n    window.alipayjsbridgeExit = function(bsucc) {\n        alipayjsbridgeFunc(\"alipayjsbridge://onExit?bsucc=\" + bsucc)\n    };\n    window.alipayjsbridgeShowBackButton = function(bshow) {\n        alipayjsbridgeFunc(\"alipayjsbridge://showBackButton?bshow=\" + bshow)\n    };\n    window.AlipayJSBridge = {\n        version: \"2.0\",\n        addListener: addListener,\n        hasListener: hasListener,\n        callListener: callListener,\n        callNativeFunc: callNativeFunc,\n        callBackFromNativeFunc: callBackFromNativeFunc\n    };\n    var uniqueId = 1;\n    var h5JsCallbackMap = {};\n\n    function iframeCall(paramStr) {\n        setTimeout(function() {\n        \tvar iframe = document.createElement(\"iframe\");\n        \tiframe.style.width = \"1px\";\n        \tiframe.style.height = \"1px\";\n        \tiframe.style.display = \"none\";\n        \tiframe.src = \"alipayjsbridge://callNativeFunc?\" + paramStr;\n        \tvar parent = document.body || document.documentElement;\n        \tparent.appendChild(iframe);\n        \tsetTimeout(function() {\n            \tparent.removeChild(iframe)\n        \t}, 0)\n        }, 0)\n    }\n\n    function callNativeFunc(nativeFuncName, data, h5JsCallback) {\n        var h5JsCallbackId = \"\";\n        if (h5JsCallback) {\n            h5JsCallbackId = \"cb_\" + (uniqueId++) + \"_\" + new Date().getTime();\n            h5JsCallbackMap[h5JsCallbackId] = h5JsCallback\n        }\n        var dataStr = \"\";\n        if (data) {\n            dataStr = encodeURIComponent(JSON.stringify(data))\n        }\n        var paramStr = \"func=\" + nativeFuncName + \"&cbId=\" + h5JsCallbackId + \"&data=\" + dataStr;\n        iframeCall(paramStr)\n    }\n\n    function callBackFromNativeFunc(h5JsCallbackId, data) {\n        var h5JsCallback = h5JsCallbackMap[h5JsCallbackId];\n        if (h5JsCallback) {\n            h5JsCallback(data);\n            delete h5JsCallbackMap[h5JsCallbackId]\n        }\n    }\n    var h5ListenerMap = {};\n\n    function addListener(jsFuncName, jsFunc) {\n        h5ListenerMap[jsFuncName] = jsFunc\n    }\n\n    function hasListener(jsFuncName) {\n        var jsFunc = h5ListenerMap[jsFuncName];\n        if (!jsFunc) {\n            return false\n        }\n        return true\n    }\n\n    function callListener(h5JsFuncName, data, nativeCallbackId) {\n        var responseCallback;\n        if (nativeCallbackId) {\n            responseCallback = function(responseData) {\n                var dataStr = \"\";\n                if (responseData) {\n                    dataStr = encodeURIComponent(JSON.stringify(responseData))\n                }\n                var paramStr = \"func=h5JsFuncCallback\" + \"&cbId=\" + nativeCallbackId + \"&data=\" + dataStr;\n                iframeCall(paramStr)\n            }\n        }\n        var h5JsFunc = h5ListenerMap[h5JsFuncName];\n        if (h5JsFunc) {\n            h5JsFunc(data, responseCallback)\n        } else if (h5JsFuncName == \"h5BackAction\") {\n            if (!window.alipayjsbridgeH5BackAction || !alipayjsbridgeH5BackAction()) {\n                var paramStr = \"func=back\";\n                iframeCall(paramStr)\n            }\n        } else {\n            console.log(\"AlipayJSBridge: no h5JsFunc \" + h5JsFuncName + data)\n        }\n    }\n    var event;\n    if (window.CustomEvent) {\n        event = new CustomEvent(\"alipayjsbridgeready\")\n    } else {\n        event = document.createEvent(\"Event\");\n        event.initEvent(\"alipayjsbridgeready\", true, true)\n    }\n    document.dispatchEvent(event);\n    setTimeout(excuteH5InitFuncs, 0);\n\n    function excuteH5InitFuncs() {\n        if (window.AlipayJSBridgeInitArray) {\n            var h5InitFuncs = window.AlipayJSBridgeInitArray;\n            delete window.AlipayJSBridgeInitArray;\n            for (var i = 0; i < h5InitFuncs.length; i++) {\n                try {\n                    h5InitFuncs[i](AlipayJSBridge)\n                } catch (e) {\n                    setTimeout(function() {\n                        throw e\n                    })\n                }\n            }\n        }\n    }\n})();\n");
        }
    }

    @Override // supwisdom.gq.h
    public synchronized void b(gq gqVar) {
        gqVar.getWebView().reload();
        gqVar.getRefreshButton().setVisibility(4);
    }

    @Override // supwisdom.gq.h
    public synchronized void a(gq gqVar) {
        f();
    }
}
