package com.supwisdom.superapp.ui.activity;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ClientCertRequest;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.GeolocationPermissions;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidtranscoder.format.MediaFormatExtraConstants;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.baidu.idl.face.platform.utils.BitmapUtils;
import com.google.gson.Gson;
import com.google.zxing.client.android.CaptureActivity;
import com.huawei.hms.framework.common.ContainerUtils;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.superapp.service.model.LocationBean;
import com.supwisdom.zueb.R;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.util.CustomPath;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import supwisdom.dn1;
import supwisdom.ev0;
import supwisdom.fn1;
import supwisdom.j7;
import supwisdom.jc;
import supwisdom.mm1;
import supwisdom.ql0;
import supwisdom.sh1;
import supwisdom.vm1;
import supwisdom.xu0;
import supwisdom.y7;
import supwisdom.yu0;
import supwisdom.zm1;

/* JADX INFO: loaded from: classes2.dex */
public class CommonWebViewActivity extends WXBaseActivity implements DownloadListener, xu0, View.OnLongClickListener, View.OnClickListener {
    public static String H = "immersive_status_bar";
    public n C;
    public Uri D;
    public ev0.d E;
    public View G;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ProgressBar f4062a;
    public WebView b;
    public Uri c;
    public View d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public View f4063e;
    public View f;
    public View g;
    public ImageButton h;
    public View i;
    public TextView j;
    public TextView k;
    public ImageView l;
    public ValueCallback<Uri[]> n;
    public ql0 o;
    public String r;
    public String s;
    public int t;
    public String u;
    public String v;
    public String w;
    public String m = zm1.f10014e;
    public boolean p = false;
    public long q = 0;
    public boolean x = true;
    public ev0.d y = null;
    public AMapLocationClient z = null;
    public int A = 2;
    public int B = 30;
    public AMapLocationListener F = new l();

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        @TargetApi(21)
        public void onClick(View view) {
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("*/*");
            intent.putExtra("android.intent.extra.MIME_TYPES", new String[]{"image/*", "video/*"});
            if (Build.VERSION.SDK_INT >= 21) {
                CommonWebViewActivity.this.startActivityForResult(Intent.createChooser(intent, "图片和视频"), 100);
            } else {
                CommonWebViewActivity.this.startActivityForResult(Intent.createChooser(intent, "图片和视频"), 101);
            }
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("*/*");
            if (Build.VERSION.SDK_INT >= 21) {
                CommonWebViewActivity.this.startActivityForResult(Intent.createChooser(intent, "选择文件"), 100);
            } else {
                CommonWebViewActivity.this.startActivityForResult(Intent.createChooser(intent, "选择文件"), 101);
            }
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            j7.a(CommonWebViewActivity.this, new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 700);
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CommonWebViewActivity.this.o.dismiss();
        }
    }

    public class e implements ev0.c {
        public e() {
        }

        @Override // supwisdom.ev0.c
        public void a(JSONObject jSONObject, ev0.d dVar) {
            Intent intent = new Intent(CommonWebViewActivity.this, (Class<?>) CaptureActivity.class);
            intent.putExtra(CaptureActivity.KEY_HANDLE_SCAN_RESULT, false);
            CommonWebViewActivity commonWebViewActivity = CommonWebViewActivity.this;
            commonWebViewActivity.E = dVar;
            if (y7.a(commonWebViewActivity, "android.permission.CAMERA") != 0) {
                j7.a(CommonWebViewActivity.this, new String[]{"android.permission.CAMERA"}, 202);
            } else {
                CommonWebViewActivity.this.startActivityForResult(intent, 201);
            }
        }
    }

    public class f implements ev0.c {
        public f() {
        }

        @Override // supwisdom.ev0.c
        public void a(JSONObject jSONObject, ev0.d dVar) {
            CommonWebViewActivity commonWebViewActivity = CommonWebViewActivity.this;
            commonWebViewActivity.y = dVar;
            commonWebViewActivity.a(jSONObject);
        }
    }

    public class g implements ev0.c {
        public g() {
        }

        @Override // supwisdom.ev0.c
        public void a(JSONObject jSONObject, ev0.d dVar) {
            CommonWebViewActivity.this.finish();
        }
    }

    public class h implements ev0.c {
        public h() {
        }

        @Override // supwisdom.ev0.c
        public void a(JSONObject jSONObject, ev0.d dVar) {
            double dDoubleValue = Double.valueOf(dn1.a(CommonWebViewActivity.this)).doubleValue() / 255.0d;
            com.alibaba.fastjson.JSONObject jSONObject2 = new com.alibaba.fastjson.JSONObject();
            jSONObject2.put("brightness", (Object) (dDoubleValue + ""));
            dVar.callback(jSONObject2.toJSONString());
        }
    }

    public class i implements ev0.c {
        public i() {
        }

        @Override // supwisdom.ev0.c
        public void a(JSONObject jSONObject, ev0.d dVar) {
            try {
                String string = jSONObject.getString("brightness");
                CommonWebViewActivity.this.t = (int) (Double.valueOf(string).doubleValue() * 255.0d);
                dn1.a(CommonWebViewActivity.this, CommonWebViewActivity.this.t);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    public class j implements ev0.c {
        public j() {
        }

        @Override // supwisdom.ev0.c
        public void a(JSONObject jSONObject, ev0.d dVar) {
            CommonWebViewActivity.this.l();
        }
    }

    public class k implements ev0.c {
        public k() {
        }

        @Override // supwisdom.ev0.c
        public void a(JSONObject jSONObject, ev0.d dVar) {
            boolean z;
            try {
                z = jSONObject.getBoolean("navbarhidden");
            } catch (JSONException e2) {
                e2.printStackTrace();
                z = false;
            }
            if (z) {
                CommonWebViewActivity.this.d.setVisibility(8);
            }
        }
    }

    public class l implements AMapLocationListener {
        public l() {
        }

        @Override // com.amap.api.location.AMapLocationListener
        public void onLocationChanged(AMapLocation aMapLocation) {
            ev0.d dVar;
            if (aMapLocation != null) {
                LocationBean locationBean = new LocationBean();
                locationBean.setCode(aMapLocation.getErrorCode());
                locationBean.setMessage(aMapLocation.getErrorInfo());
                if (aMapLocation.getErrorCode() != 0) {
                    Log.e("AmapError", "location Error, ErrCode:" + aMapLocation.getErrorCode() + ", errInfo:" + aMapLocation.getErrorInfo());
                    ev0.d dVar2 = CommonWebViewActivity.this.y;
                    if (dVar2 != null) {
                        dVar2.callback(new Gson().toJson(locationBean));
                        return;
                    }
                    return;
                }
                vm1 vm1Var = new vm1(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                if (!CommonWebViewActivity.this.m.equals(zm1.f10014e)) {
                    vm1Var = zm1.a(zm1.f10014e, CommonWebViewActivity.this.m, vm1Var.a(), vm1Var.b());
                }
                locationBean.setType(CommonWebViewActivity.this.m);
                locationBean.setHorizontalAccuracy("");
                locationBean.setAccuracy(aMapLocation.getAccuracy() + "");
                locationBean.setVerticalAccuracy("");
                locationBean.setLatitude(String.valueOf(vm1Var.a()));
                locationBean.setLongitude(String.valueOf(vm1Var.b()));
                if (CommonWebViewActivity.this.A == 0) {
                    ev0.d dVar3 = CommonWebViewActivity.this.y;
                    if (dVar3 != null) {
                        dVar3.callback(new Gson().toJson(locationBean));
                        return;
                    }
                    return;
                }
                locationBean.setCountry(aMapLocation.getCountry());
                locationBean.setCountryCode("");
                locationBean.setProvince(aMapLocation.getProvince());
                locationBean.setCity(aMapLocation.getCity());
                locationBean.setCityAdcode(aMapLocation.getCityCode());
                locationBean.setDistrict(aMapLocation.getDistrict());
                locationBean.setAdCode(aMapLocation.getAdCode());
                if (CommonWebViewActivity.this.A == 1) {
                    ev0.d dVar4 = CommonWebViewActivity.this.y;
                    if (dVar4 != null) {
                        dVar4.callback(new Gson().toJson(locationBean));
                        return;
                    }
                    return;
                }
                locationBean.setStreetNumber(aMapLocation.getStreetNum());
                locationBean.setStreet(aMapLocation.getStreet());
                locationBean.setAddress(aMapLocation.getAddress());
                locationBean.setDescription(aMapLocation.getDescription());
                if (CommonWebViewActivity.this.A != 2 || (dVar = CommonWebViewActivity.this.y) == null) {
                    return;
                }
                dVar.callback(new Gson().toJson(locationBean));
            }
        }
    }

    public class m implements DialogInterface.OnDismissListener {
        public m() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            if (CommonWebViewActivity.this.n != null) {
                CommonWebViewActivity.this.n.onReceiveValue(null);
                CommonWebViewActivity.this.n = null;
            }
        }
    }

    public class n extends BroadcastReceiver {
        public n() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String strSubstring;
            HashMap map = new HashMap();
            map.put("style", "1");
            map.put(Constants.Scheme.LOCAL, "true");
            String str = File.separator + "mnt" + File.separator + "sdcard" + (Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + CommonWebViewActivity.this.r);
            try {
                strSubstring = CommonWebViewActivity.this.r.substring(CommonWebViewActivity.this.r.indexOf(Operators.DOT_STR) + 1);
            } catch (Exception unused) {
                strSubstring = null;
            }
            if (!TextUtils.isEmpty(strSubstring) && (strSubstring.equals("PNG") || strSubstring.equals("png") || strSubstring.equals("JPG") || strSubstring.equals(BitmapUtils.IMAGE_KEY_SUFFIX) || strSubstring.equals("jepg") || strSubstring.equals("JEPG"))) {
                if (CommonWebViewActivity.this.b.getVisibility() == 0) {
                    CommonWebViewActivity.this.b.setVisibility(8);
                }
                CommonWebViewActivity.this.l.setVisibility(0);
                CommonWebViewActivity.this.f4062a.setVisibility(8);
                CommonWebViewActivity.this.l.setImageBitmap(BitmapFactory.decodeFile(str));
                return;
            }
            if (strSubstring.equals(CustomPath.CUSTOM_PATH_DOC) || strSubstring.equals("docx") || strSubstring.equals("ppt") || strSubstring.equals("pptx") || strSubstring.equals("xls") || strSubstring.equals("xlsx") || strSubstring.equals("pdf") || strSubstring.equals("txt") || strSubstring.equals("epub")) {
                return;
            }
            Intent intent2 = new Intent();
            intent2.setAction("android.intent.action.VIEW");
            intent2.addCategory("android.intent.category.DEFAULT");
            intent2.setData(CommonWebViewActivity.this.D);
            context.startActivity(intent2);
            CommonWebViewActivity.this.l();
        }
    }

    public class o extends WebChromeClient {
        public o() {
        }

        @Override // android.webkit.WebChromeClient
        public View getVideoLoadingProgressView() {
            FrameLayout frameLayout = new FrameLayout(CommonWebViewActivity.this);
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            return frameLayout;
        }

        @Override // android.webkit.WebChromeClient
        public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
            CommonWebViewActivity.this.b.getSettings().setJavaScriptEnabled(true);
            return super.onCreateWindow(webView, z, z2, message);
        }

        @Override // android.webkit.WebChromeClient
        public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
            callback.invoke(str, CommonWebViewActivity.this.x, false);
            super.onGeolocationPermissionsShowPrompt(str, callback);
            if (Build.VERSION.SDK_INT < 23 || !CommonWebViewActivity.this.x || y7.a(CommonWebViewActivity.this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
                return;
            }
            j7.a(CommonWebViewActivity.this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 500);
        }

        @Override // android.webkit.WebChromeClient
        public void onHideCustomView() {
            super.onHideCustomView();
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            if (i == 100) {
                CommonWebViewActivity.this.f4062a.setVisibility(8);
            } else {
                CommonWebViewActivity.this.f4062a.setVisibility(0);
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
        }

        @Override // android.webkit.WebChromeClient
        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
            CommonWebViewActivity.this.a(view, customViewCallback);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            CommonWebViewActivity.this.a(valueCallback, fileChooserParams);
            return true;
        }
    }

    static {
        new FrameLayout.LayoutParams(-1, -1);
    }

    public final void n() {
        this.b = (WebView) findViewById(R.id.common_webView);
        this.f4062a = (ProgressBar) findViewById(R.id.loadingBar);
        this.h = (ImageButton) findViewById(R.id.backBt);
        this.j = (TextView) findViewById(R.id.backTxt);
        this.f = findViewById(R.id.netWorkErrorView);
        this.g = findViewById(R.id.reloadBt);
        this.f4063e = findViewById(R.id.navBarView);
        this.d = findViewById(R.id.fullBarView);
        this.i = findViewById(R.id.closeTxt);
        this.l = (ImageView) findViewById(R.id.iv_image);
        AMapLocationClient aMapLocationClient = new AMapLocationClient(getApplicationContext());
        this.z = aMapLocationClient;
        aMapLocationClient.setLocationListener(this.F);
        this.h.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.k = (TextView) findViewById(R.id.titleTxt);
        this.b.setOnLongClickListener(this);
        this.b.setDownloadListener(this);
    }

    public final void o() {
        AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
        aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        aMapLocationClientOption.setGpsFirst(false);
        aMapLocationClientOption.setHttpTimeOut(this.B * 1000);
        aMapLocationClientOption.setInterval(2000L);
        aMapLocationClientOption.setNeedAddress(true);
        aMapLocationClientOption.setOnceLocation(true);
        aMapLocationClientOption.setOnceLocationLatest(true);
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);
        aMapLocationClientOption.setSensorEnable(false);
        aMapLocationClientOption.setWifiScan(true);
        aMapLocationClientOption.setLocationCacheEnable(true);
        aMapLocationClientOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);
        this.z.setLocationOption(aMapLocationClientOption);
        this.z.startLocation();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        l();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.backBt || view.getId() == R.id.backTxt) {
            l();
            return;
        }
        if (view.getId() != R.id.reloadBt) {
            if (view.getId() == R.id.closeTxt) {
                finish();
            }
        } else {
            this.b.clearView();
            this.b.setVisibility(0);
            this.f.setVisibility(8);
            this.b.reload();
        }
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_common_web_view);
        if (getIntent().getBooleanExtra(H, true)) {
            mm1.a(this);
            WXBaseActivity.transparencyBar(this);
            WXBaseActivity.setLightStatusBar(this, true);
        }
        fn1.w = sh1.c.c(fn1.o);
        n();
        m();
        this.c = getIntent().getData();
        HashMap map = new HashMap();
        String str = fn1.w;
        if (str != null && !str.equals("") && !TextUtils.isEmpty(this.c.toString())) {
            if (TextUtils.isEmpty(this.w)) {
                map.put(fn1.o, fn1.w);
            } else {
                map.put(this.w, fn1.w);
            }
            if (this.c.toString().contains(fn1.k)) {
                map.put("X-Id-Token", fn1.w);
            }
            e(this.c.getHost());
        }
        yu0 yu0VarB = yu0.b(this, this.b);
        yu0VarB.a(new p());
        yu0VarB.a("sysScan", new e());
        yu0VarB.a("sysGetLocation", new f());
        yu0VarB.a("closewindow", new g());
        yu0VarB.a("sysGetBrightness", new h());
        yu0VarB.a("sysSetBrightness", new i());
        yu0VarB.a("goback", new j());
        yu0VarB.a("setnavbar", new k());
        this.b.setWebViewClient(new p());
        this.b.setWebChromeClient(new o());
        this.trackPageName = this.c.toString();
        String string = this.c.toString();
        if (!TextUtils.isEmpty(this.u)) {
            Uri.Builder builderBuildUpon = Uri.parse(string).buildUpon();
            builderBuildUpon.appendQueryParameter(this.u, fn1.w);
            string = builderBuildUpon.toString();
        }
        String str2 = string;
        if (!getIntent().getBooleanExtra("isWeex", false)) {
            this.b.loadUrl(str2, map);
            return;
        }
        String str3 = "<!DOCTYPE html>\n<html>\n<head>\n  <meta charset=\"utf-8\">\n  <title>" + this.c.getQueryParameter("title") + "</title>\n  <meta name=\"weex-viewport\" content=\"750\">\n  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=no\">\n  <meta name=\"apple-mobile-web-app-capable\" content=\"yes\">\n  <meta name=\"apple-touch-fullscreen\" content=\"yes\"><meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\">\n  <meta name=\"format-detection\" content=\"telephone=no, email=no\">\n  <style>\n    html, body, #weex {\n      width: 100%;\n      height: 100%;\n    }\n    html, body {\n      -ms-overflow-style: scrollbar;\n      -webkit-tap-highlight-color: transparent;\n      padding: 0;\n      margin: 0;\n      width: 100%;\n      height: 100%;\n      overflow-x: hidden;\n      -webkit-overflow-scrolling: touch;\n    }\n  </style>\n</head>\n<body>\n  <div id=\"root\"></div>\n<script src=\"" + str2 + "\"></script></body></html>";
        String queryParameter = this.c.getQueryParameter("backColor");
        String queryParameter2 = this.c.getQueryParameter("navBarColor");
        String queryParameter3 = this.c.getQueryParameter("statusBarColor");
        if (queryParameter != null) {
            this.j.setTextColor(Color.parseColor("#" + queryParameter));
            this.k.setTextColor(Color.parseColor("#" + queryParameter));
            jc.a(this.h, ColorStateList.valueOf(Color.parseColor("#" + queryParameter)));
        }
        if (queryParameter2 != null) {
            this.d.setBackgroundColor(Color.parseColor("#" + queryParameter2));
        }
        if (queryParameter3 != null && queryParameter3.trim().equalsIgnoreCase("light")) {
            WXBaseActivity.setLightStatusBar(this, false);
        } else if (queryParameter3 != null && queryParameter3.trim().equalsIgnoreCase("dark")) {
            WXBaseActivity.setLightStatusBar(this, true);
        }
        this.b.loadDataWithBaseURL(str2, str3, "text/html", "UTF-8", null);
    }

    @Override // android.webkit.DownloadListener
    public void onDownloadStart(String str, String str2, String str3, String str4, long j2) {
        Log.d(WXBaseActivity.TAG, "开始下载");
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        WebView.HitTestResult hitTestResult = this.b.getHitTestResult();
        if (hitTestResult == null) {
            return false;
        }
        int type = hitTestResult.getType();
        if (type != 5 && type != 8) {
            return false;
        }
        hitTestResult.getExtra();
        return false;
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.b.onPause();
        unregisterReceiver(this.C);
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.b.onResume();
        this.C = new n();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.DOWNLOAD_COMPLETE");
        registerReceiver(this.C, intentFilter);
    }

    @Override // android.app.Activity
    public boolean shouldShowRequestPermissionRationale(String str) {
        return super.shouldShowRequestPermissionRationale(str);
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean supportShouldUpRecreateTask(Intent intent) {
        return super.supportShouldUpRecreateTask(intent);
    }

    public final List<String> b(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 23 && getApplicationInfo().targetSdkVersion >= 23) {
            try {
                for (String str : strArr) {
                    Method method = getClass().getMethod("checkSelfPermission", String.class);
                    Method method2 = getClass().getMethod("shouldShowRequestPermissionRationale", String.class);
                    if (((Integer) method.invoke(this, str)).intValue() != 0 || ((Boolean) method2.invoke(this, str)).booleanValue()) {
                        arrayList.add(str);
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return arrayList;
    }

    public final void e(String str) {
        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        if (str.contains(fn1.k)) {
            str = fn1.k;
        }
        if (TextUtils.isEmpty(this.v)) {
            cookieManager.setCookie(str, fn1.o + ContainerUtils.KEY_VALUE_DELIMITER + fn1.w);
        } else {
            cookieManager.setCookie(str, this.v + ContainerUtils.KEY_VALUE_DELIMITER + fn1.w);
        }
        cookieManager.setCookie(str, "Domain=" + str);
        cookieManager.setCookie(str, "Path=/");
        if (Build.VERSION.SDK_INT >= 21) {
            cookieManager.flush();
        } else {
            CookieSyncManager.getInstance().sync();
        }
    }

    public final void l() {
        Log.d("webview bf size:", "" + this.b.copyBackForwardList().getSize());
        boolean zCanGoBack = this.b.canGoBack();
        if (this.b.getVisibility() == 8) {
            this.b.setVisibility(0);
            this.l.setVisibility(8);
        } else if (!zCanGoBack) {
            setResult(300, getIntent());
            super.onBackPressed();
        } else {
            this.i.setVisibility(0);
            this.p = true;
            this.q = System.currentTimeMillis();
            this.b.goBack();
        }
    }

    public final void m() {
        WebSettings settings = this.b.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(true);
        settings.setSupportMultipleWindows(true);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setGeolocationEnabled(true);
        settings.setAppCacheMaxSize(Long.MAX_VALUE);
        settings.setPluginState(WebSettings.PluginState.ON_DEMAND);
        settings.setCacheMode(2);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setAllowFileAccess(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(getFilesDir().getAbsolutePath() + "webView");
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(-1);
        settings.setTextSize(WebSettings.TextSize.NORMAL);
        settings.setBlockNetworkImage(false);
        settings.setBlockNetworkLoads(false);
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(2);
        }
        String userAgentString = settings.getUserAgentString();
        if (userAgentString.contains("SuperApp")) {
            return;
        }
        settings.setUserAgentString(userAgentString + " SuperApp");
    }

    public final void a(JSONObject jSONObject) {
        List<String> listB;
        String[] strArr = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.READ_PHONE_STATE"};
        if (jSONObject != null) {
            this.m = TextUtils.isEmpty(jSONObject.optString("type")) ? zm1.f10014e : jSONObject.optString("type");
            this.A = jSONObject.has(MediaFormatExtraConstants.KEY_LEVEL) ? jSONObject.optInt(MediaFormatExtraConstants.KEY_LEVEL) : 2;
            this.B = jSONObject.has("cacheTimeout") ? jSONObject.optInt("cacheTimeout") : 30;
        }
        try {
            if (Build.VERSION.SDK_INT >= 23 && getApplicationInfo().targetSdkVersion >= 23 && (listB = b(strArr)) != null && listB.size() > 0) {
                getClass().getMethod("requestPermissions", String[].class, Integer.TYPE).invoke(this, (String[]) listB.toArray(new String[listB.size()]), 600);
                return;
            }
        } catch (Throwable unused) {
        }
        o();
    }

    public class p extends WebViewClient {
        public p() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            Log.d("webview", "onPageFinished   " + str);
            super.onPageFinished(webView, str);
            Log.d("", str);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            Log.d("webview", "onPageStarted   " + str);
            CommonWebViewActivity.this.s = str;
            super.onPageStarted(webView, str, bitmap);
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(23)
        public void onReceivedClientCertRequest(WebView webView, ClientCertRequest clientCertRequest) {
            clientCertRequest.ignore();
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            if (webResourceRequest.isForMainFrame()) {
                if (webResourceRequest.getUrl().getLastPathSegment() == null || !(webResourceRequest.getUrl().getLastPathSegment().endsWith("png") || webResourceRequest.getUrl().getLastPathSegment().endsWith(BitmapUtils.IMAGE_KEY_SUFFIX) || webResourceRequest.getUrl().getLastPathSegment().endsWith("ico"))) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        if (webResourceError.getErrorCode() == -1) {
                            return;
                        }
                        if (webResourceRequest.getUrl().toString().equals("about:blank") && webResourceError.getErrorCode() == -10) {
                            return;
                        }
                        Log.e("webView", "Error requesting '" + webResourceRequest.getUrl() + "': " + webResourceError.getErrorCode());
                    }
                    CommonWebViewActivity.this.f.setVisibility(0);
                    CommonWebViewActivity.this.b.setVisibility(8);
                }
            }
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(23)
        public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
            if (webResourceRequest.isForMainFrame()) {
                if (webResourceRequest.getUrl().getLastPathSegment() == null || !(webResourceRequest.getUrl().getLastPathSegment().endsWith("png") || webResourceRequest.getUrl().getLastPathSegment().endsWith(BitmapUtils.IMAGE_KEY_SUFFIX) || webResourceRequest.getUrl().getLastPathSegment().endsWith("ico"))) {
                    Log.d("webview", webResourceResponse.getStatusCode() + "");
                    CommonWebViewActivity.this.f.setVisibility(0);
                    CommonWebViewActivity.this.b.setVisibility(8);
                }
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.proceed();
        }

        @Override // android.webkit.WebViewClient
        @Deprecated
        public void onTooManyRedirects(WebView webView, Message message, Message message2) {
            message2.sendToTarget();
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Log.d("webview", "shouldOverrideUrlLoading   " + str);
            webView.loadUrl(str);
            if (!TextUtils.isEmpty(CommonWebViewActivity.this.s) && !CommonWebViewActivity.this.s.equals(str) && CommonWebViewActivity.this.p && System.currentTimeMillis() - CommonWebViewActivity.this.q < 600) {
                CommonWebViewActivity commonWebViewActivity = CommonWebViewActivity.this;
                commonWebViewActivity.p = false;
                if (commonWebViewActivity.b.canGoBack()) {
                    CommonWebViewActivity.this.b.goBack();
                } else {
                    CommonWebViewActivity.this.finish();
                }
                return true;
            }
            if (str.startsWith("http") || str.startsWith("https")) {
                CommonWebViewActivity.this.p = false;
                return false;
            }
            if (str.startsWith("weixin://wap/pay?")) {
                try {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(Uri.parse(str));
                    CommonWebViewActivity.this.startActivity(intent);
                } catch (Throwable unused) {
                    Toast.makeText(CommonWebViewActivity.this, "还未安装微信客户端", 0).show();
                }
                return true;
            }
            if (!str.contains("platformapi/startApp")) {
                try {
                    Intent intent2 = new Intent();
                    intent2.setAction("android.intent.action.VIEW");
                    intent2.setData(Uri.parse(str));
                    CommonWebViewActivity.this.startActivity(intent2);
                    return true;
                } catch (Throwable unused2) {
                    return false;
                }
            }
            Log.d("webview", "contains(\"platformapi/startApp\")   " + str);
            try {
                Intent intent3 = new Intent();
                intent3.setAction("android.intent.action.VIEW");
                intent3.setData(Uri.parse(str));
                CommonWebViewActivity.this.startActivity(intent3);
            } catch (Throwable unused3) {
                Toast.makeText(CommonWebViewActivity.this, "还未安装支付宝客户端", 0).show();
            }
            return true;
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            if (i == -1) {
                return;
            }
            CommonWebViewActivity.this.f.setVisibility(0);
            CommonWebViewActivity.this.b.setVisibility(8);
        }
    }

    public final void a(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        if (this.G != null) {
            customViewCallback.onCustomViewHidden();
        }
    }

    public void a(ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        ValueCallback<Uri[]> valueCallback2 = this.n;
        if (valueCallback2 != null) {
            valueCallback2.onReceiveValue(null);
        }
        this.n = valueCallback;
        a(fileChooserParams);
    }

    public final void a(WebChromeClient.FileChooserParams fileChooserParams) {
        ql0 ql0Var = new ql0(this, 2131820756);
        this.o = ql0Var;
        ql0Var.setOnDismissListener(new m());
        View viewInflate = LayoutInflater.from(this).inflate(R.layout.dialog_bottom_select_pictrue, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_select_pictrue_album);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.tv_select_choose_file);
        TextView textView3 = (TextView) viewInflate.findViewById(R.id.tv_select_pictrue_camera);
        TextView textView4 = (TextView) viewInflate.findViewById(R.id.tv_select_pictrue_cancel);
        textView.setOnClickListener(new a());
        textView2.setOnClickListener(new b());
        textView3.setOnClickListener(new c());
        textView4.setOnClickListener(new d());
        this.o.setContentView(viewInflate);
        this.o.show();
    }
}
