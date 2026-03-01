package com.loc;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.Constants;
import io.dcloud.common.constant.AbsoluteConst;
import org.json.JSONObject;

/* JADX INFO: compiled from: H5LocationClient.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h {
    public a c;
    public Context d;
    public WebView f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f3800a = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public AMapLocationClient f3801e = null;
    public String g = "AMap.Geolocation.cbk";
    public AMapLocationClientOption b = null;
    public volatile boolean h = false;

    /* JADX INFO: compiled from: H5LocationClient.java */
    public class a implements AMapLocationListener {
        public a() {
        }

        @Override // com.amap.api.location.AMapLocationListener
        public final void onLocationChanged(AMapLocation aMapLocation) {
            if (h.this.h) {
                h.a(h.this, h.b(aMapLocation));
            }
        }
    }

    public h(Context context, WebView webView) {
        this.f = null;
        this.c = null;
        this.d = context.getApplicationContext();
        this.f = webView;
        this.c = new a();
    }

    public static /* synthetic */ void a(h hVar, final String str) {
        try {
            if (hVar.f != null) {
                if (Build.VERSION.SDK_INT < 19) {
                    hVar.f.post(new Runnable() { // from class: com.loc.h.2
                        @Override // java.lang.Runnable
                        public final void run() {
                            h.this.f.loadUrl(AbsoluteConst.PROTOCOL_JAVASCRIPT + h.this.g + "('" + str + "')");
                        }
                    });
                    return;
                }
                hVar.f.evaluateJavascript(AbsoluteConst.PROTOCOL_JAVASCRIPT + hVar.g + "('" + str + "')", new ValueCallback<String>() { // from class: com.loc.h.1
                    @Override // android.webkit.ValueCallback
                    public final /* bridge */ /* synthetic */ void onReceiveValue(String str2) {
                    }
                });
            }
        } catch (Throwable th) {
            ej.a(th, "H5LocationClient", "callbackJs()");
        }
    }

    public static String b(AMapLocation aMapLocation) {
        String locationDetail;
        JSONObject jSONObject = new JSONObject();
        String str = "errorInfo";
        if (aMapLocation == null) {
            jSONObject.put("errorCode", -1);
            locationDetail = "unknownError";
        } else {
            if (aMapLocation.getErrorCode() == 0) {
                jSONObject.put("errorCode", 0);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(Constants.Name.X, aMapLocation.getLongitude());
                jSONObject2.put(Constants.Name.Y, aMapLocation.getLatitude());
                jSONObject2.put("precision", aMapLocation.getAccuracy());
                jSONObject2.put("type", aMapLocation.getLocationType());
                jSONObject2.put(com.umeng.analytics.pro.bm.O, aMapLocation.getCountry());
                jSONObject2.put("province", aMapLocation.getProvince());
                jSONObject2.put("city", aMapLocation.getCity());
                jSONObject2.put("cityCode", aMapLocation.getCityCode());
                jSONObject2.put("district", aMapLocation.getDistrict());
                jSONObject2.put("adCode", aMapLocation.getAdCode());
                jSONObject2.put("street", aMapLocation.getStreet());
                jSONObject2.put("streetNum", aMapLocation.getStreetNum());
                jSONObject2.put("floor", aMapLocation.getFloor());
                jSONObject2.put("address", aMapLocation.getAddress());
                jSONObject.put("result", jSONObject2);
                return jSONObject.toString();
            }
            jSONObject.put("errorCode", aMapLocation.getErrorCode());
            jSONObject.put("errorInfo", aMapLocation.getErrorInfo());
            str = "locationDetail";
            locationDetail = aMapLocation.getLocationDetail();
        }
        jSONObject.put(str, locationDetail);
        return jSONObject.toString();
    }

    public final void a() {
        if (this.f == null || this.d == null || Build.VERSION.SDK_INT < 17 || this.h) {
            return;
        }
        try {
            this.f.getSettings().setJavaScriptEnabled(true);
            this.f.addJavascriptInterface(this, "AMapAndroidLoc");
            if (!TextUtils.isEmpty(this.f.getUrl())) {
                this.f.reload();
            }
            if (this.f3801e == null) {
                AMapLocationClient aMapLocationClient = new AMapLocationClient(this.d);
                this.f3801e = aMapLocationClient;
                aMapLocationClient.setLocationListener(this.c);
            }
            this.h = true;
        } catch (Throwable unused) {
        }
    }

    public final void b() {
        synchronized (this.f3800a) {
            this.h = false;
            if (this.f3801e != null) {
                this.f3801e.unRegisterLocationListener(this.c);
                this.f3801e.stopLocation();
                this.f3801e.onDestroy();
                this.f3801e = null;
            }
            this.b = null;
        }
    }

    @JavascriptInterface
    public final void getLocation(String str) {
        boolean z;
        boolean z2;
        AMapLocationClientOption aMapLocationClientOption;
        AMapLocationClientOption.AMapLocationMode aMapLocationMode;
        synchronized (this.f3800a) {
            if (this.h) {
                if (this.b == null) {
                    this.b = new AMapLocationClientOption();
                }
                int iOptInt = 5;
                long jOptLong = com.igexin.push.config.c.k;
                boolean z3 = true;
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    jOptLong = jSONObject.optLong(RemoteMessageConst.TO, com.igexin.push.config.c.k);
                    z = jSONObject.optInt("useGPS", 1) == 1;
                    try {
                        z2 = jSONObject.optInt("watch", 0) == 1;
                        try {
                            iOptInt = jSONObject.optInt("interval", 5);
                            String strOptString = jSONObject.optString(WXBridgeManager.METHOD_CALLBACK, null);
                            if (TextUtils.isEmpty(strOptString)) {
                                strOptString = "AMap.Geolocation.cbk";
                            }
                            this.g = strOptString;
                        } catch (Throwable unused) {
                        }
                    } catch (Throwable unused2) {
                        z2 = false;
                    }
                } catch (Throwable unused3) {
                    z = false;
                }
                try {
                    this.b.setHttpTimeOut(jOptLong);
                    if (z) {
                        aMapLocationClientOption = this.b;
                        aMapLocationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy;
                    } else {
                        aMapLocationClientOption = this.b;
                        aMapLocationMode = AMapLocationClientOption.AMapLocationMode.Battery_Saving;
                    }
                    aMapLocationClientOption.setLocationMode(aMapLocationMode);
                    AMapLocationClientOption aMapLocationClientOption2 = this.b;
                    if (z2) {
                        z3 = false;
                    }
                    aMapLocationClientOption2.setOnceLocation(z3);
                    if (z2) {
                        this.b.setInterval(iOptInt * 1000);
                    }
                } catch (Throwable unused4) {
                }
                if (this.f3801e != null) {
                    this.f3801e.setLocationOption(this.b);
                    this.f3801e.stopLocation();
                    this.f3801e.startLocation();
                }
            }
        }
    }

    @JavascriptInterface
    public final void stopLocation() {
        AMapLocationClient aMapLocationClient;
        if (this.h && (aMapLocationClient = this.f3801e) != null) {
            aMapLocationClient.stopLocation();
        }
    }
}
