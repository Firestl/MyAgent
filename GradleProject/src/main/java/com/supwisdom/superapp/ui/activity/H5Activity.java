package com.supwisdom.superapp.ui.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ClientCertRequest;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.GeolocationPermissions;
import android.webkit.PermissionRequest;
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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidtranscoder.format.MediaFormatExtraConstants;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson.JSON;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.baidu.idl.face.platform.utils.BitmapUtils;
import com.bumptech.glide.Glide;
import com.dmcbig.mediapicker.utils.FileUtils;
import com.google.gson.Gson;
import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.camera.CameraManager;
import com.huawei.hms.framework.common.ContainerUtils;
import com.lzy.okgo.cookie.SerializableCookie;
import com.supwisdom.superapp.WXApplication;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.superapp.service.model.LocationBean;
import com.supwisdom.zueb.R;
import com.ta.utdid2.android.utils.StringUtils;
import com.taobao.weex.el.parse.Operators;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.open.SocialConstants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.constant.AbsoluteConst;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import rx.exceptions.OnErrorFailedException;
import supwisdom.bj1;
import supwisdom.dn1;
import supwisdom.et1;
import supwisdom.ev0;
import supwisdom.fm1;
import supwisdom.fn1;
import supwisdom.hm1;
import supwisdom.j7;
import supwisdom.jc;
import supwisdom.kn1;
import supwisdom.lm1;
import supwisdom.ln1;
import supwisdom.mj1;
import supwisdom.mm1;
import supwisdom.ni1;
import supwisdom.pz1;
import supwisdom.ql0;
import supwisdom.sh1;
import supwisdom.sw1;
import supwisdom.tn1;
import supwisdom.vl1;
import supwisdom.vm1;
import supwisdom.wl1;
import supwisdom.xu0;
import supwisdom.y7;
import supwisdom.ym1;
import supwisdom.yu0;
import supwisdom.zm1;

/* JADX INFO: loaded from: classes2.dex */
public class H5Activity extends WXBaseActivity implements View.OnClickListener, DownloadListener, View.OnLongClickListener, xu0, fm1.a {
    public static String A0 = "";
    public static Pattern B0 = Pattern.compile("\\s*filename\\s*=\\s*(\"?)([^\"]*)\\1\\s*$", 2);
    public static Pattern C0 = Pattern.compile("\\s*filename\\*\\s*=\\s*[^']*('')([^']*)\\s*$", 2);
    public static final FrameLayout.LayoutParams D0 = new FrameLayout.LayoutParams(-1, -1);
    public static String z0 = "immersive_status_bar";
    public j0 F;
    public String G;
    public String H;
    public String I;
    public String J;
    public ImageView K;
    public Boolean L;
    public ql0 M;
    public ql0 N;
    public TextView P;
    public long Q;
    public LinearLayout R;
    public TextView S;
    public TextView T;
    public View U;
    public String V;
    public bj1 W;
    public LinearLayout X;
    public String Z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WebView f4119a;
    public String a0;
    public DownloadManager b;
    public String b0;
    public ProgressBar c;
    public String c0;
    public Uri d;
    public String d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public View f4120e;
    public JSONArray e0;
    public View f;
    public List<hm1> f0;
    public View g;
    public View h;
    public String h0;
    public ImageButton i;
    public View j;
    public TextView k;
    public TextView l;
    public ImageView m;
    public String m0;
    public String n0;
    public ValueCallback<Uri> o;
    public ValueCallback<Uri[]> p;
    public String p0;
    public File q;
    public tn1 q0;
    public ql0 r;
    public String r0;
    public View s0;
    public PermissionRequest t0;
    public ev0.d u0;
    public String v;
    public View v0;
    public int w;
    public FrameLayout w0;
    public String x;
    public WebChromeClient.CustomViewCallback x0;
    public String y;
    public Handler y0;
    public String z;
    public String n = zm1.f10014e;
    public Intent s = null;
    public boolean t = false;
    public long u = 0;
    public boolean A = true;
    public ev0.d B = null;
    public AMapLocationClient C = null;
    public int D = 2;
    public int E = 30;
    public AMapLocationListener O = new j();
    public boolean Y = false;
    public Bitmap g0 = null;
    public Boolean i0 = false;
    public String j0 = "";
    public Boolean k0 = false;
    public String l0 = "";
    public String o0 = "";

    public class a implements DialogInterface.OnDismissListener {
        public a() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            if (H5Activity.this.p != null) {
                H5Activity.this.p.onReceiveValue(null);
                H5Activity.this.p = null;
            }
        }
    }

    public class a0 implements DialogInterface.OnDismissListener {
        public a0() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            if (H5Activity.this.p != null) {
                H5Activity.this.p.onReceiveValue(null);
                H5Activity.this.p = null;
            }
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        @TargetApi(21)
        public void onClick(View view) {
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("*/*");
            intent.putExtra("android.intent.extra.MIME_TYPES", new String[]{"image/*", "video/*"});
            if (Build.VERSION.SDK_INT >= 21) {
                H5Activity.this.startActivityForResult(intent, 100);
            } else {
                H5Activity.this.startActivityForResult(intent, 101);
            }
        }
    }

    public class b0 implements View.OnClickListener {
        public b0() {
        }

        @Override // android.view.View.OnClickListener
        @TargetApi(21)
        public void onClick(View view) {
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("*/*");
            intent.putExtra("android.intent.extra.MIME_TYPES", new String[]{"image/*", "video/*"});
            if (Build.VERSION.SDK_INT >= 21) {
                H5Activity.this.startActivityForResult(intent, 100);
            } else {
                H5Activity.this.startActivityForResult(intent, 101);
            }
            H5Activity.this.r.dismiss();
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType("*/*");
            if (Build.VERSION.SDK_INT >= 21) {
                H5Activity.this.startActivityForResult(intent, 100);
            } else {
                H5Activity.this.startActivityForResult(intent, 101);
            }
        }
    }

    public class c0 implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4126a;

        public c0(String str) {
            this.f4126a = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (!"2".equals(this.f4126a)) {
                H5Activity.this.g("");
            } else {
                H5Activity.this.g("2");
                H5Activity.this.r.dismiss();
            }
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Uri uriFromFile;
            if (!H5Activity.this.getPackageManager().hasSystemFeature("android.hardware.camera.any")) {
                Toast.makeText(H5Activity.this, "设备无摄像头", 0).show();
                return;
            }
            String absolutePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
            H5Activity.this.q = new File(absolutePath, System.nanoTime() + ".mp4");
            if (!H5Activity.this.q.getParentFile().exists()) {
                H5Activity.this.q.getParentFile().mkdirs();
            }
            if (H5Activity.this.q.exists()) {
                H5Activity.this.q.delete();
            }
            try {
                H5Activity.this.q.createNewFile();
            } catch (Throwable th) {
                th.printStackTrace();
            }
            Intent intent = new Intent("android.media.action.VIDEO_CAPTURE");
            if (H5Activity.this.getApplicationInfo().targetSdkVersion > 23) {
                H5Activity h5Activity = H5Activity.this;
                uriFromFile = FileProvider.getUriForFile(h5Activity, "com.supwisdom.zueb.UploadFileProvider", h5Activity.q);
            } else {
                uriFromFile = Uri.fromFile(H5Activity.this.q);
            }
            intent.putExtra("output", uriFromFile);
            intent.addFlags(1);
            intent.putExtra("android.intent.extra.durationLimit", 30);
            if (Build.VERSION.SDK_INT < 23) {
                H5Activity.this.startActivityForResult(intent, 104);
                return;
            }
            if (y7.a(H5Activity.this, "android.permission.CAMERA") == 0) {
                H5Activity.this.startActivityForResult(intent, 104);
                return;
            }
            H5Activity h5Activity2 = H5Activity.this;
            H5Activity h5Activity3 = H5Activity.this;
            h5Activity2.W = new bj1(h5Activity3, h5Activity3.getString(R.string.string_camera), H5Activity.this.getString(R.string.string_camera_content));
            H5Activity.this.W.show();
            j7.a(H5Activity.this, new String[]{"android.permission.CAMERA"}, 103);
        }
    }

    public class d0 implements View.OnClickListener {
        public d0() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            H5Activity.this.r.dismiss();
        }
    }

    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            H5Activity.this.g("");
        }
    }

    public class e0 implements View.OnClickListener {
        public e0() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            H5Activity h5Activity = H5Activity.this;
            h5Activity.f4119a.loadUrl(h5Activity.h0);
            H5Activity.this.N.dismiss();
        }
    }

    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            H5Activity.this.r.dismiss();
        }
    }

    public class f0 extends Handler {
        public f0() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                H5Activity h5Activity = H5Activity.this;
                h5Activity.a(0, h5Activity.g0);
            } else {
                if (i != 2) {
                    return;
                }
                H5Activity h5Activity2 = H5Activity.this;
                h5Activity2.a(1, h5Activity2.g0);
            }
        }
    }

    public class g implements View.OnClickListener {
        public g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            H5Activity.this.finish();
        }
    }

    public class g0 implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4134a;
        public final /* synthetic */ String b;
        public final /* synthetic */ String c;
        public final /* synthetic */ String d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ TextView f4135e;
        public final /* synthetic */ long f;
        public final /* synthetic */ String g;

        public class a implements sw1<et1> {
            public a() {
            }

            @Override // supwisdom.sw1
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNext(et1 et1Var) throws Throwable {
                g0 g0Var = g0.this;
                H5Activity.this.a(et1Var, g0Var.f4134a, g0Var.f, g0Var.g, g0Var.d);
            }

            @Override // supwisdom.sw1
            public void onCompleted() {
            }

            @Override // supwisdom.sw1
            public void onError(Throwable th) {
                try {
                    if (th instanceof OnErrorFailedException) {
                        th.printStackTrace();
                    } else {
                        th.getMessage();
                        H5Activity.this.y0.sendEmptyMessage(2);
                    }
                } catch (Exception e2) {
                    lm1.a(e2);
                    e2.printStackTrace();
                }
            }
        }

        public g0(String str, String str2, String str3, String str4, TextView textView, long j, String str5) {
            this.f4134a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.f4135e = textView;
            this.f = j;
            this.g = str5;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f4134a.startsWith("blob")) {
                H5Activity.this.f4119a.loadUrl(tn1.a(this.f4134a, this.b, this.c, this.d));
                Toast.makeText(H5Activity.this, "图片不存在。下载错误", 0).show();
                H5Activity.this.M.dismiss();
            } else {
                this.f4135e.setVisibility(8);
                H5Activity.this.P.setVisibility(0);
                mj1.b().c(this.f4134a, CookieManager.getInstance().getCookie(this.f4134a)).b(pz1.c()).b(pz1.c()).a(new a());
            }
            String unused = H5Activity.A0 = this.b;
        }
    }

    public class h0 implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4138a;
        public final /* synthetic */ String b;
        public final /* synthetic */ String c;

        public h0(String str, String str2, String str3) {
            this.f4138a = str;
            this.b = str2;
            this.c = str3;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            File file = Environment.getExternalStorageState().equals("mounted") ? Build.VERSION.SDK_INT >= 29 ? new File(H5Activity.this.getExternalFilesDir(null), H5Activity.b(this.f4138a, this.b, this.c)) : Environment.getExternalStorageDirectory() : Environment.getRootDirectory();
            Uri uriForFile = Build.VERSION.SDK_INT >= 24 ? FileProvider.getUriForFile(H5Activity.this, "com.supwisdom.zueb.UploadFileProvider", file) : Uri.fromFile(file);
            Intent intent = new Intent();
            intent.setFlags(1);
            intent.addFlags(268435456);
            intent.setAction("android.intent.action.VIEW");
            intent.setDataAndType(uriForFile, ym1.a(file));
            H5Activity.this.startActivity(intent);
            H5Activity.this.M.dismiss();
        }
    }

    public class i implements ev0.c {
        public i() {
        }

        @Override // supwisdom.ev0.c
        public void a(JSONObject jSONObject, ev0.d dVar) {
            Intent intent = new Intent(H5Activity.this, (Class<?>) CaptureActivity.class);
            intent.putExtra(CaptureActivity.KEY_HANDLE_SCAN_RESULT, false);
            H5Activity h5Activity = H5Activity.this;
            h5Activity.u0 = dVar;
            if (y7.a(h5Activity, "android.permission.CAMERA") == 0) {
                H5Activity.this.startActivityForResult(intent, 201);
                return;
            }
            j7.a(H5Activity.this, new String[]{"android.permission.CAMERA"}, 202);
            H5Activity h5Activity2 = H5Activity.this;
            H5Activity h5Activity3 = H5Activity.this;
            h5Activity2.W = new bj1(h5Activity3, h5Activity3.getString(R.string.string_camera), H5Activity.this.getString(R.string.string_camera_content));
            H5Activity.this.W.show();
        }
    }

    public class i0 implements View.OnClickListener {
        public i0() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            H5Activity.this.M.dismiss();
        }
    }

    public class j implements AMapLocationListener {
        public j() {
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
                    ev0.d dVar2 = H5Activity.this.B;
                    if (dVar2 != null) {
                        dVar2.callback(new Gson().toJson(locationBean));
                        return;
                    }
                    return;
                }
                vm1 vm1Var = new vm1(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                if (!H5Activity.this.n.equals(zm1.f10014e)) {
                    vm1Var = zm1.a(zm1.f10014e, H5Activity.this.n, vm1Var.a(), vm1Var.b());
                }
                locationBean.setType(H5Activity.this.n);
                locationBean.setHorizontalAccuracy("");
                locationBean.setAccuracy(aMapLocation.getAccuracy() + "");
                locationBean.setVerticalAccuracy("");
                locationBean.setLatitude(String.valueOf(vm1Var.a()));
                locationBean.setLongitude(String.valueOf(vm1Var.b()));
                if (H5Activity.this.D == 0) {
                    ev0.d dVar3 = H5Activity.this.B;
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
                if (H5Activity.this.D == 1) {
                    ev0.d dVar4 = H5Activity.this.B;
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
                if (H5Activity.this.D != 2 || (dVar = H5Activity.this.B) == null) {
                    return;
                }
                dVar.callback(new Gson().toJson(locationBean));
            }
        }
    }

    public class j0 extends BroadcastReceiver {
        public j0() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.DOWNLOAD_COMPLETE".equals(intent.getAction())) {
                Toast.makeText(H5Activity.this, "已下载到文件中心", 0).show();
            } else if ("android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED".equals(intent.getAction())) {
                H5Activity.this.startActivity(new Intent("android.intent.action.VIEW_DOWNLOADS"));
            }
        }
    }

    public class k implements ev0.c {
        public k() {
        }

        @Override // supwisdom.ev0.c
        public void a(JSONObject jSONObject, ev0.d dVar) {
            H5Activity h5Activity = H5Activity.this;
            h5Activity.B = dVar;
            h5Activity.a(jSONObject);
        }
    }

    public static class k0 extends FrameLayout {
        public k0(Context context) {
            super(context);
            setBackgroundColor(context.getResources().getColor(android.R.color.black));
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            return true;
        }
    }

    public class l implements ev0.c {
        public l() {
        }

        @Override // supwisdom.ev0.c
        public void a(JSONObject jSONObject, ev0.d dVar) {
            H5Activity.this.finish();
        }
    }

    public class l0 extends WebViewClient {
        public l0() {
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
            H5Activity.this.v = str;
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
                    H5Activity.this.g.setVisibility(0);
                    H5Activity.this.f4119a.setVisibility(8);
                }
            }
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(23)
        public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
            if (webResourceRequest.isForMainFrame()) {
                if (webResourceRequest.getUrl().getLastPathSegment() == null || !(webResourceRequest.getUrl().getLastPathSegment().endsWith("png") || webResourceRequest.getUrl().getLastPathSegment().endsWith(BitmapUtils.IMAGE_KEY_SUFFIX) || webResourceRequest.getUrl().getLastPathSegment().endsWith("ico"))) {
                    Log.d("webview", webResourceResponse.getStatusCode() + "");
                    H5Activity.this.g.setVisibility(0);
                    H5Activity.this.f4119a.setVisibility(8);
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
        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            if ("https://saas.17wanxiao.com/digitalPeople3/model/SZR.zip?v=1.7".equals(webResourceRequest.getUrl().toString())) {
                return new WebResourceResponse("application/zip", "UTF-8", H5Activity.this.l());
            }
            Log.i(WXBaseActivity.TAG, "shouldInterceptRequest: " + webResourceRequest.getUrl());
            return super.shouldInterceptRequest(webView, webResourceRequest);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Log.d("webview", "shouldOverrideUrlLoading   " + str);
            if (!TextUtils.isEmpty(H5Activity.this.v) && !H5Activity.this.v.equals(str) && H5Activity.this.t && System.currentTimeMillis() - H5Activity.this.u < 600) {
                H5Activity h5Activity = H5Activity.this;
                h5Activity.t = false;
                if (h5Activity.f4119a.canGoBack()) {
                    H5Activity.this.f4119a.goBack();
                } else {
                    H5Activity.this.finish();
                }
                return true;
            }
            if (str.startsWith("http") || str.startsWith("https")) {
                if (str.contains("faceCollectionH5Services") && y7.a(H5Activity.this, "android.permission.CAMERA") != 0) {
                    j7.a(H5Activity.this, new String[]{"android.permission.CAMERA"}, 0);
                }
                H5Activity.this.t = false;
                return false;
            }
            if (str.startsWith("weixin://wap/pay?")) {
                try {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(Uri.parse(str));
                    H5Activity.this.startActivity(intent);
                } catch (Throwable unused) {
                    Toast.makeText(H5Activity.this, "还未安装微信客户端", 0).show();
                }
                return true;
            }
            if (!str.contains("platformapi/startApp")) {
                try {
                    Intent intent2 = new Intent();
                    intent2.setFlags(268468224);
                    intent2.setAction("android.intent.action.VIEW");
                    intent2.setData(Uri.parse(str));
                    H5Activity.this.startActivity(intent2);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                return true;
            }
            Log.d("webview", "contains(\"platformapi/startApp\")   " + str);
            try {
                Intent intent3 = new Intent();
                intent3.setAction("android.intent.action.VIEW");
                intent3.setData(Uri.parse(str));
                H5Activity.this.startActivity(intent3);
            } catch (Throwable unused2) {
                Toast.makeText(H5Activity.this, "还未安装支付宝客户端", 0).show();
            }
            return true;
        }

        public /* synthetic */ l0(H5Activity h5Activity, j jVar) {
            this();
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            if (i == -1) {
                return;
            }
            H5Activity.this.g.setVisibility(0);
            H5Activity.this.f4119a.setVisibility(8);
        }
    }

    public class m implements ev0.c {
        public m() {
        }

        @Override // supwisdom.ev0.c
        public void a(JSONObject jSONObject, ev0.d dVar) {
            double dDoubleValue = Double.valueOf(dn1.a(H5Activity.this)).doubleValue() / 255.0d;
            com.alibaba.fastjson.JSONObject jSONObject2 = new com.alibaba.fastjson.JSONObject();
            jSONObject2.put("brightness", (Object) (dDoubleValue + ""));
            dVar.callback(jSONObject2.toJSONString());
        }
    }

    public class m0 implements IUiListener {
        public m0() {
        }

        @Override // com.tencent.tauth.IUiListener
        public void onCancel() {
            Log.e(WXBaseActivity.TAG, "onCancel: ======");
        }

        @Override // com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            Log.e(WXBaseActivity.TAG, "onComplete:  ======= ");
        }

        @Override // com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            Log.e(WXBaseActivity.TAG, "onError:  ======= " + uiError.errorMessage);
        }

        @Override // com.tencent.tauth.IUiListener
        public void onWarning(int i) {
            Log.e(WXBaseActivity.TAG, "onWarning: ======== ");
        }
    }

    public class n implements ev0.c {
        public n() {
        }

        @Override // supwisdom.ev0.c
        public void a(JSONObject jSONObject, ev0.d dVar) {
            try {
                String string = jSONObject.getString("brightness");
                H5Activity.this.w = (int) (Double.valueOf(string).doubleValue() * 255.0d);
                dn1.a(H5Activity.this, H5Activity.this.w);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    public class o implements ev0.c {
        public o() {
        }

        @Override // supwisdom.ev0.c
        public void a(JSONObject jSONObject, ev0.d dVar) {
            H5Activity.this.m();
        }
    }

    public class p implements ev0.c {
        public p() {
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x004c  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0063  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x006d  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0081  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x008a  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x0099  */
        @Override // supwisdom.ev0.c
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void a(org.json.JSONObject r6, supwisdom.ev0.d r7) {
            /*
                r5 = this;
                r7 = 0
                java.lang.String r0 = "navbarhidden"
                boolean r0 = r6.getBoolean(r0)     // Catch: org.json.JSONException -> L36
                com.supwisdom.superapp.ui.activity.H5Activity r1 = com.supwisdom.superapp.ui.activity.H5Activity.this     // Catch: org.json.JSONException -> L34
                java.lang.String r2 = "navigationBarBackgroundColor"
                java.lang.String r2 = r6.getString(r2)     // Catch: org.json.JSONException -> L34
                com.supwisdom.superapp.ui.activity.H5Activity.a(r1, r2)     // Catch: org.json.JSONException -> L34
                java.lang.String r1 = "showRightCloseBtn"
                boolean r1 = r6.getBoolean(r1)     // Catch: org.json.JSONException -> L34
                java.lang.String r2 = "showLeftBackBtn"
                boolean r2 = r6.getBoolean(r2)     // Catch: org.json.JSONException -> L32
                com.supwisdom.superapp.ui.activity.H5Activity r3 = com.supwisdom.superapp.ui.activity.H5Activity.this     // Catch: org.json.JSONException -> L30
                java.lang.String r4 = "backHome"
                boolean r4 = r6.getBoolean(r4)     // Catch: org.json.JSONException -> L30
                com.supwisdom.superapp.ui.activity.H5Activity.a(r3, r4)     // Catch: org.json.JSONException -> L30
                java.lang.String r3 = "moreBtnHidden"
                boolean r6 = r6.getBoolean(r3)     // Catch: org.json.JSONException -> L30
                goto L3e
            L30:
                r6 = move-exception
                goto L3a
            L32:
                r6 = move-exception
                goto L39
            L34:
                r6 = move-exception
                goto L38
            L36:
                r6 = move-exception
                r0 = 0
            L38:
                r1 = 0
            L39:
                r2 = 0
            L3a:
                r6.printStackTrace()
                r6 = 0
            L3e:
                com.supwisdom.superapp.ui.activity.H5Activity r3 = com.supwisdom.superapp.ui.activity.H5Activity.this
                java.lang.String r3 = com.supwisdom.superapp.ui.activity.H5Activity.h(r3)
                java.lang.String r4 = ""
                boolean r3 = r4.equals(r3)
                if (r3 != 0) goto L5b
                com.supwisdom.superapp.ui.activity.H5Activity r3 = com.supwisdom.superapp.ui.activity.H5Activity.this
                android.view.View r4 = r3.f4120e
                java.lang.String r3 = com.supwisdom.superapp.ui.activity.H5Activity.h(r3)
                int r3 = android.graphics.Color.parseColor(r3)
                r4.setBackgroundColor(r3)
            L5b:
                com.supwisdom.superapp.ui.activity.H5Activity r3 = com.supwisdom.superapp.ui.activity.H5Activity.this
                boolean r3 = com.supwisdom.superapp.ui.activity.H5Activity.i(r3)
                if (r3 == 0) goto L69
                com.supwisdom.superapp.ui.activity.H5Activity r3 = com.supwisdom.superapp.ui.activity.H5Activity.this
                r4 = 1
                com.supwisdom.superapp.ui.activity.H5Activity.a(r3, r4)
            L69:
                r3 = 8
                if (r1 == 0) goto L7f
                com.supwisdom.superapp.ui.activity.H5Activity r1 = com.supwisdom.superapp.ui.activity.H5Activity.this
                android.widget.LinearLayout r1 = com.supwisdom.superapp.ui.activity.H5Activity.k(r1)
                r1.setVisibility(r7)
                com.supwisdom.superapp.ui.activity.H5Activity r1 = com.supwisdom.superapp.ui.activity.H5Activity.this
                android.widget.ImageView r1 = com.supwisdom.superapp.ui.activity.H5Activity.l(r1)
                r1.setVisibility(r3)
            L7f:
                if (r0 == 0) goto L88
                com.supwisdom.superapp.ui.activity.H5Activity r0 = com.supwisdom.superapp.ui.activity.H5Activity.this
                android.view.View r0 = r0.f4120e
                r0.setVisibility(r3)
            L88:
                if (r2 != 0) goto L91
                com.supwisdom.superapp.ui.activity.H5Activity r0 = com.supwisdom.superapp.ui.activity.H5Activity.this
                android.widget.TextView r0 = r0.k
                r0.setVisibility(r3)
            L91:
                com.supwisdom.superapp.ui.activity.H5Activity r0 = com.supwisdom.superapp.ui.activity.H5Activity.this
                android.widget.ImageView r0 = com.supwisdom.superapp.ui.activity.H5Activity.l(r0)
                if (r6 == 0) goto L9b
                r7 = 8
            L9b:
                r0.setVisibility(r7)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.supwisdom.superapp.ui.activity.H5Activity.p.a(org.json.JSONObject, supwisdom.ev0$d):void");
        }
    }

    public class q implements ev0.c {
        public q() {
        }

        @Override // supwisdom.ev0.c
        public void a(JSONObject jSONObject, ev0.d dVar) {
            H5Activity.this.u0 = dVar;
            Intent intent = new Intent();
            intent.putExtra("verifyType", "check");
            intent.setClass(H5Activity.this, FaceLivenessExpActivity.class);
            H5Activity.this.startActivityForResult(intent, 34);
        }
    }

    public class r implements ev0.c {
        public r() {
        }

        @Override // supwisdom.ev0.c
        public void a(JSONObject jSONObject, ev0.d dVar) {
            H5Activity.this.p();
            H5Activity.this.i0 = true;
        }
    }

    public class s implements ev0.c {
        public s() {
        }

        @Override // supwisdom.ev0.c
        public void a(JSONObject jSONObject, ev0.d dVar) {
            String string;
            try {
                string = jSONObject.getString("navigationBarBackgroundColor");
            } catch (JSONException e2) {
                e2.printStackTrace();
                string = "";
            }
            WXApplication.homeUniMP.sendUniMPEvent("addRouterPathListener", string);
        }
    }

    public class t implements ev0.c {
        public t() {
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // supwisdom.ev0.c
        public void a(JSONObject jSONObject, ev0.d dVar) {
            H5Activity.this.u0 = dVar;
            try {
                String string = jSONObject.getString("type");
                byte b = -1;
                switch (string.hashCode()) {
                    case 48:
                        if (string.equals("0")) {
                            b = 0;
                        }
                        break;
                    case 49:
                        if (string.equals("1")) {
                            b = 1;
                        }
                        break;
                    case 50:
                        if (string.equals("2")) {
                            b = 2;
                        }
                        break;
                }
                if (b == 0) {
                    H5Activity.this.g("0");
                    return;
                }
                if (b != 1) {
                    if (b != 2) {
                        return;
                    }
                    H5Activity.this.e("2");
                    return;
                }
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.addCategory("android.intent.category.OPENABLE");
                intent.setType("*/*");
                intent.putExtra("android.intent.extra.MIME_TYPES", new String[]{"image/*", "video/*"});
                if (Build.VERSION.SDK_INT >= 21) {
                    H5Activity.this.startActivityForResult(intent, 100);
                } else {
                    H5Activity.this.startActivityForResult(intent, 101);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    public class u extends LinearLayoutManager {
        public u(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
        public boolean canScrollHorizontally() {
            return false;
        }
    }

    public class v implements ev0.c {
        public v() {
        }

        @Override // supwisdom.ev0.c
        public void a(JSONObject jSONObject, ev0.d dVar) {
            try {
                String string = jSONObject.getString("userName");
                String string2 = jSONObject.getString("path");
                WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
                req.userName = string;
                req.path = URLDecoder.decode(string2);
                req.miniprogramType = 0;
                WXApplication.api.sendReq(req);
            } catch (JSONException e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    public class w implements ev0.c {
        public w() {
        }

        @Override // supwisdom.ev0.c
        public void a(JSONObject jSONObject, ev0.d dVar) {
            try {
                String string = jSONObject.getString("url");
                Intent intent = new Intent(H5Activity.this, (Class<?>) NewWindowActivity.class);
                intent.setData(Uri.parse(string));
                intent.setFlags(268435456);
                H5Activity.this.startActivity(intent);
            } catch (JSONException e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    public class x extends WebChromeClient {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ yu0 f4158a;
        public final /* synthetic */ l0 b;

        public x(yu0 yu0Var, l0 l0Var) {
            this.f4158a = yu0Var;
            this.b = l0Var;
        }

        @Override // android.webkit.WebChromeClient
        public View getVideoLoadingProgressView() {
            FrameLayout frameLayout = new FrameLayout(H5Activity.this);
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            return frameLayout;
        }

        @Override // android.webkit.WebChromeClient
        public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
            webView.getSettings().setJavaScriptEnabled(true);
            return super.onCreateWindow(webView, z, z2, message);
        }

        @Override // android.webkit.WebChromeClient
        public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
            callback.invoke(str, H5Activity.this.A, false);
            super.onGeolocationPermissionsShowPrompt(str, callback);
            if (Build.VERSION.SDK_INT < 23 || !H5Activity.this.A || y7.a(H5Activity.this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
                return;
            }
            H5Activity h5Activity = H5Activity.this;
            H5Activity h5Activity2 = H5Activity.this;
            h5Activity.W = new bj1(h5Activity2, h5Activity2.getString(R.string.string_location), H5Activity.this.getString(R.string.string_location_content));
            H5Activity.this.W.show();
            j7.a(H5Activity.this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 500);
        }

        @Override // android.webkit.WebChromeClient
        public void onHideCustomView() {
            H5Activity.this.n();
        }

        @Override // android.webkit.WebChromeClient
        public void onPermissionRequest(PermissionRequest permissionRequest) {
            H5Activity.this.t0 = permissionRequest;
            if (y7.a(H5Activity.this, "android.permission.RECORD_AUDIO") != 0) {
                j7.a(H5Activity.this, new String[]{"android.permission.RECORD_AUDIO"}, 16);
            } else {
                permissionRequest.grant(permissionRequest.getResources());
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            if (i == 100) {
                H5Activity.this.c.setVisibility(8);
            } else {
                H5Activity.this.c.setVisibility(0);
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            this.f4158a.a(this.b);
            if (str == null || webView.getUrl().contains(str)) {
                return;
            }
            H5Activity.this.l.setText(str);
        }

        @Override // android.webkit.WebChromeClient
        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
            H5Activity.this.a(view, customViewCallback);
        }

        @Override // android.webkit.WebChromeClient
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            H5Activity.this.a(valueCallback, fileChooserParams);
            return true;
        }
    }

    public class y implements ni1.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ni1 f4159a;

        public y(ni1 ni1Var) {
            this.f4159a = ni1Var;
        }

        @Override // supwisdom.ni1.a
        public void a() {
            this.f4159a.dismiss();
        }

        @Override // supwisdom.ni1.a
        public void b() {
            this.f4159a.dismiss();
        }
    }

    public class z implements Handler.Callback {
        public z() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                H5Activity.this.P.setText(H5Activity.formatFileSize(Long.parseLong(H5Activity.this.V)).concat("/").concat(H5Activity.formatFileSize(H5Activity.this.Q)));
            } else if (i == 2) {
                Toast.makeText(H5Activity.this, "", 0).show();
            } else if (i == 3) {
                H5Activity.this.U.setVisibility(8);
                H5Activity.this.S.setVisibility(8);
                H5Activity.this.T.setVisibility(0);
                H5Activity.this.R.setVisibility(0);
                H5Activity.this.y0.removeCallbacksAndMessages(null);
            }
            return true;
        }
    }

    public H5Activity() {
        new f0();
        this.u0 = null;
        this.y0 = new Handler(new z());
    }

    public static String formatFileSize(long j2) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        if (j2 == 0) {
            return "0B";
        }
        if (j2 < 1024) {
            return decimalFormat.format(j2) + "B";
        }
        if (j2 < 1048576) {
            return decimalFormat.format(j2 / 1024.0d) + "KB";
        }
        if (j2 < 1073741824) {
            return decimalFormat.format(j2 / 1048576.0d) + "MB";
        }
        return decimalFormat.format(j2 / 1.073741824E9d) + "GB";
    }

    public static String readFiles(String str) {
        String str2 = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(str)), "UTF-8"));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                str2 = str2 + line;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return str2;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        Uri data;
        super.onActivityResult(i2, i3, intent);
        if (i2 == 34) {
            if (this.u0 == null || intent == null) {
                return;
            }
            String stringExtra = intent.getStringExtra(FaceLivenessExpActivity.H);
            com.alibaba.fastjson.JSONObject jSONObject = new com.alibaba.fastjson.JSONObject();
            jSONObject.put("photoStr", (Object) stringExtra);
            this.u0.callback(jSONObject.toJSONString());
            return;
        }
        if (i2 != 104) {
            if (i2 == 201) {
                if (i2 == 201) {
                    if (this.u0 != null && intent != null) {
                        String stringExtra2 = intent.getStringExtra(CaptureActivity.SCNA_CODE_RESULT);
                        com.alibaba.fastjson.JSONObject jSONObject2 = new com.alibaba.fastjson.JSONObject();
                        if (this.k0.booleanValue()) {
                            stringExtra2 = URLEncoder.encode(stringExtra2);
                        }
                        jSONObject2.put("ScanCode", (Object) stringExtra2);
                        this.u0.callback(jSONObject2.toJSONString());
                        return;
                    }
                    ev0.d dVar = this.u0;
                    if (dVar != null) {
                        dVar.callback(null);
                        return;
                    }
                    if (intent != null) {
                        new com.alibaba.fastjson.JSONObject().put("codeString", (Object) intent.getStringExtra(CaptureActivity.SCNA_CODE_RESULT));
                    }
                    finish();
                    return;
                }
                return;
            }
            switch (i2) {
                case 100:
                    break;
                case 101:
                    if (this.o == null) {
                        return;
                    }
                    Uri data2 = (intent == null || i3 != -1) ? null : intent.getData();
                    try {
                        Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(getContentResolver().openInputStream(data2));
                        com.alibaba.fastjson.JSONObject jSONObject3 = new com.alibaba.fastjson.JSONObject();
                        jSONObject3.put("photoStr", (Object) a(bitmapDecodeStream));
                        this.u0.callback(jSONObject3.toJSONString());
                        this.o.onReceiveValue(data2);
                        this.o = null;
                        ql0 ql0Var = this.r;
                        if (ql0Var == null || !ql0Var.isShowing()) {
                            return;
                        }
                        this.r.dismiss();
                        return;
                    } catch (FileNotFoundException e2) {
                        throw new RuntimeException(e2);
                    }
                case 102:
                    t();
                    ql0 ql0Var2 = this.r;
                    if (ql0Var2 == null || !ql0Var2.isShowing()) {
                        return;
                    }
                    this.r.dismiss();
                    return;
                default:
                    return;
            }
        }
        if (this.u0 != null && intent != null) {
            if (intent == null || i3 != -1) {
                data = null;
            } else {
                try {
                    data = intent.getData();
                } catch (FileNotFoundException e3) {
                    throw new RuntimeException(e3);
                }
            }
            Bitmap bitmapDecodeStream2 = BitmapFactory.decodeStream(getContentResolver().openInputStream(data));
            com.alibaba.fastjson.JSONObject jSONObject4 = new com.alibaba.fastjson.JSONObject();
            jSONObject4.put("photoStr", (Object) a(bitmapDecodeStream2));
            this.u0.callback(jSONObject4.toJSONString());
        }
        if (Build.VERSION.SDK_INT >= 21) {
            ValueCallback<Uri[]> valueCallback = this.p;
            if (valueCallback == null) {
                return;
            }
            valueCallback.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(i3, intent));
            this.p = null;
        }
        ql0 ql0Var3 = this.r;
        if (ql0Var3 == null || !ql0Var3.isShowing()) {
            return;
        }
        this.r.dismiss();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.Y) {
            finish();
        } else {
            m();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.backBt || view.getId() == R.id.backTxt) {
            if (this.Y) {
                finish();
                return;
            } else {
                m();
                return;
            }
        }
        if (view.getId() == R.id.reloadBt) {
            this.f4119a.clearView();
            this.f4119a.setVisibility(0);
            this.g.setVisibility(8);
            this.f4119a.reload();
            return;
        }
        if (view.getId() == R.id.closeTxt) {
            finish();
        } else if (view.getId() == R.id.iv_share_service) {
            a(this.d0, this.b0, this.e0);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int i2 = configuration.orientation;
        if (i2 == 1) {
            getWindow().clearFlags(1024);
            getWindow().addFlags(2048);
        } else {
            if (i2 != 2) {
                return;
            }
            getWindow().clearFlags(2048);
            getWindow().addFlags(1024);
        }
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_h5);
        if (getIntent().getBooleanExtra(z0, true)) {
            mm1.a(this);
            WXBaseActivity.transparencyBar(this);
            WXBaseActivity.setLightStatusBar(this, true);
        }
        this.b = (DownloadManager) getSystemService(AbsoluteConst.SPNAME_DOWNLOAD);
        fn1.w = sh1.c.c(fn1.o);
        this.f4119a = (WebView) findViewById(R.id.webView);
        this.c = (ProgressBar) findViewById(R.id.loadingBar);
        this.i = (ImageButton) findViewById(R.id.backBt);
        this.k = (TextView) findViewById(R.id.backTxt);
        this.g = findViewById(R.id.netWorkErrorView);
        this.h = findViewById(R.id.reloadBt);
        this.f = findViewById(R.id.navBarView);
        this.f4120e = findViewById(R.id.fullBarView);
        this.j = findViewById(R.id.closeTxt);
        this.K = (ImageView) findViewById(R.id.iv_image);
        this.m = (ImageView) findViewById(R.id.iv_share_service);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_right_close);
        this.X = linearLayout;
        linearLayout.setOnClickListener(new g());
        AMapLocationClient aMapLocationClient = new AMapLocationClient(getApplicationContext());
        this.C = aMapLocationClient;
        aMapLocationClient.setLocationListener(this.O);
        this.i.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.l = (TextView) findViewById(R.id.titleTxt);
        com.alibaba.fastjson.JSONObject object = JSON.parseObject("{\"openHijacking\":false} ");
        if (object.getBoolean("hideShare") != null && object.getBoolean("hideShare").booleanValue()) {
            this.m.setVisibility(8);
        }
        this.b0 = getIntent().getStringExtra("collect");
        String stringExtra = getIntent().getStringExtra("iconUrl");
        this.c0 = stringExtra;
        if (stringExtra != null && !"".equals(stringExtra)) {
            c(1);
        }
        this.d0 = getIntent().getStringExtra("appId");
        this.Z = getIntent().getStringExtra("title");
        this.h0 = getIntent().getStringExtra("attachmentUrl");
        this.j0 = getIntent().getStringExtra("appType");
        this.m0 = getIntent().getStringExtra(SocialConstants.PARAM_APP_DESC);
        this.n0 = getIntent().getStringExtra("columnId");
        getIntent().getStringExtra("serviceInfo");
        this.o0 = getIntent().getStringExtra("shareUrl");
        this.p0 = getIntent().getStringExtra("shareConfig");
        this.r0 = getIntent().getStringExtra("typeId");
        getIntent().getStringExtra("typeUrl");
        if (getIntent().getBooleanExtra(IApp.ConfigProperty.CONFIG_SHORTCUT, false)) {
            this.m.setVisibility(8);
        }
        String str = this.r0;
        if (str != null && str.contains("scan")) {
            WXApplication.homeUniMP.sendUniMPEvent("scanQuickActionListener", this.r0);
        }
        WebSettings settings = this.f4119a.getSettings();
        if (Build.VERSION.SDK_INT >= 17) {
            settings.setMediaPlaybackRequiresUserGesture(false);
        }
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setJavaScriptEnabled(true);
        settings.setDisplayZoomControls(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setSupportMultipleWindows(false);
        settings.setGeolocationDatabasePath(getDir("database", 0).getPath());
        settings.setGeolocationEnabled(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        tn1 tn1Var = new tn1(this);
        this.q0 = tn1Var;
        this.f4119a.addJavascriptInterface(tn1Var, "Android");
        this.q0.a(new h());
        this.d = getIntent().getData();
        o();
        HashMap map = new HashMap();
        String str2 = fn1.w;
        if (str2 != null && !str2.equals("") && !TextUtils.isEmpty(this.d.toString())) {
            if (!TextUtils.isEmpty(this.z)) {
                map.put(this.z, fn1.w);
            }
            if (this.d.toString().contains(fn1.k)) {
                map.put("X-Id-Token", fn1.w);
            }
            f(this.d.getHost());
        }
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
        if (!userAgentString.contains("SuperApp")) {
            settings.setUserAgentString(userAgentString + " SuperApp");
        }
        this.f4119a.setOnLongClickListener(this);
        yu0 yu0VarB = yu0.b(this, this.f4119a);
        l0 l0Var = new l0(this, null);
        yu0VarB.a(l0Var);
        yu0VarB.a("sysScan", new i());
        yu0VarB.a("sysGetLocation", new k());
        yu0VarB.a("closewindow", new l());
        yu0VarB.a("sysGetBrightness", new m());
        yu0VarB.a("sysSetBrightness", new n());
        yu0VarB.a("goback", new o());
        yu0VarB.a("setnavbar", new p());
        yu0VarB.a("sysFaceLiveness", new q());
        yu0VarB.a("sysSetOpenAbility", new ev0.c() { // from class: supwisdom.ek1
            @Override // supwisdom.ev0.c
            public final void a(JSONObject jSONObject, ev0.d dVar) {
                this.f7510a.a(jSONObject, dVar);
            }
        });
        yu0VarB.a("sysInfoAuthorize", new r());
        yu0VarB.a("", new s());
        yu0VarB.a("sysPickImage", new t());
        yu0VarB.a("sysJumpWXMiniProgram", new v());
        yu0VarB.a("openNewWebview", new w());
        this.f4119a.setWebChromeClient(new x(yu0VarB, l0Var));
        this.trackPageName = this.d.toString();
        String string = this.d.toString();
        this.a0 = this.d.toString();
        if (!TextUtils.isEmpty(this.x)) {
            Uri.Builder builderBuildUpon = Uri.parse(string).buildUpon();
            builderBuildUpon.appendQueryParameter(this.x, fn1.w);
            string = builderBuildUpon.toString();
        }
        String str3 = string;
        if (getIntent().getBooleanExtra("isWeex", false)) {
            String str4 = "<!DOCTYPE html>\n<html>\n<head>\n  <meta charset=\"utf-8\">\n  <title>" + this.d.getQueryParameter("title") + "</title>\n  <meta name=\"weex-viewport\" content=\"750\">\n  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, user-scalable=no\">\n  <meta name=\"apple-mobile-web-app-capable\" content=\"yes\">\n  <meta name=\"apple-touch-fullscreen\" content=\"yes\"><meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\">\n  <meta name=\"format-detection\" content=\"telephone=no, email=no\">\n  <style>\n    html, body, #weex {\n      width: 100%;\n      height: 100%;\n    }\n    html, body {\n      -ms-overflow-style: scrollbar;\n      -webkit-tap-highlight-color: transparent;\n      padding: 0;\n      margin: 0;\n      width: 100%;\n      height: 100%;\n      overflow-x: hidden;\n      -webkit-overflow-scrolling: touch;\n    }\n  </style>\n</head>\n<body>\n  <div id=\"root\"></div>\n<script src=\"" + str3 + "\"></script></body></html>";
            String queryParameter = this.d.getQueryParameter("backColor");
            String queryParameter2 = this.d.getQueryParameter("navBarColor");
            String queryParameter3 = this.d.getQueryParameter("statusBarColor");
            if (queryParameter != null) {
                this.k.setTextColor(Color.parseColor("#" + queryParameter));
                this.l.setTextColor(Color.parseColor("#" + queryParameter));
                jc.a(this.i, ColorStateList.valueOf(Color.parseColor("#" + queryParameter)));
            }
            if (queryParameter2 != null) {
                this.f4120e.setBackgroundColor(Color.parseColor("#" + queryParameter2));
            }
            if (queryParameter3 != null && queryParameter3.trim().equalsIgnoreCase("light")) {
                WXBaseActivity.setLightStatusBar(this, false);
            } else if (queryParameter3 != null && queryParameter3.trim().equalsIgnoreCase("dark")) {
                WXBaseActivity.setLightStatusBar(this, true);
            }
            this.f4119a.loadDataWithBaseURL(str3, str4, "text/html", "UTF-8", null);
        } else {
            if (str3.contains("windowConfig")) {
                String strSubstring = str3.substring(str3.substring(0, str3.indexOf("windowConfig=")).length() + 1);
                Log.e(WXBaseActivity.TAG, "windowConfig: ====== " + strSubstring);
                if (strSubstring.contains("showRightCloseBtn:true")) {
                    this.X.setVisibility(0);
                    this.m.setVisibility(8);
                } else {
                    this.X.setVisibility(8);
                    this.m.setVisibility(0);
                }
                if (strSubstring.contains("showLeftBackBtn:false")) {
                    this.k.setVisibility(8);
                }
                if (strSubstring.contains("backHome:true")) {
                    this.Y = true;
                }
                if (strSubstring.contains("navigationBarBackgroundColor")) {
                    String strSubstring2 = str3.substring(str3.lastIndexOf("navigationBarBackgroundColor") + 28 + 1);
                    if (strSubstring2.startsWith("#")) {
                        this.f4120e.setBackgroundColor(Color.parseColor(strSubstring2.substring(0, 7)));
                    }
                }
            }
            if (this.L == null) {
                if (str3.contains("windowConfig")) {
                    this.f4119a.loadUrl(str3.substring(0, str3.indexOf("windowConfig=")).concat(str3.substring(str3.lastIndexOf(Operators.BLOCK_END_STR) + 1)), map);
                } else if (str3.contains("{idToken}")) {
                    this.f4119a.loadUrl(str3.replace("{idToken}", fn1.w), map);
                } else {
                    this.f4119a.loadUrl(str3, map);
                }
            }
        }
        this.f4119a.setDownloadListener(this);
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.B != null) {
            this.C.stopLocation();
            this.C.onDestroy();
        }
        this.f4119a.destroy();
        this.f4119a = null;
    }

    @Override // android.webkit.DownloadListener
    public void onDownloadStart(String str, String str2, String str3, String str4, long j2) {
        if (StringUtils.isEmpty(str)) {
            Toast.makeText(this, "文件地址错误!", 0).show();
            return;
        }
        String strB = b(str, str3, str4);
        if (StringUtils.isEmpty(strB)) {
            Toast.makeText(this, "文件名有误!", 0).show();
        } else {
            a(str, str2, str3, str4, j2, strB, A0.equals(strB));
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 != 4) {
            return super.onKeyUp(i2, keyEvent);
        }
        if (this.v0 == null) {
            return super.onKeyUp(i2, keyEvent);
        }
        n();
        return true;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        WebView.HitTestResult hitTestResult = this.f4119a.getHitTestResult();
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
        this.f4119a.onPause();
        unregisterReceiver(this.F);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, supwisdom.j7.c
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        if (i2 == 16) {
            PermissionRequest permissionRequest = this.t0;
            permissionRequest.grant(permissionRequest.getResources());
            return;
        }
        if (i2 == 17) {
            if (iArr[0] == 0 && strArr[0].equals("android.permission.WRITE_EXTERNAL_STORAGE")) {
                r();
            } else {
                Toast.makeText(this, "文件权限禁用了。请务必开启文件读写权限", 0).show();
            }
            if (this.W.isShowing()) {
                this.W.dismiss();
                return;
            }
            return;
        }
        if (i2 == 103) {
            if (this.W.isShowing()) {
                this.W.dismiss();
            }
            if (iArr[0] == 0) {
                Intent intent = this.s;
                if (intent != null) {
                    startActivityForResult(intent, 102);
                }
            } else {
                Toast.makeText(this, "相机权限禁用了。请务必开启相机权限", 0).show();
            }
            if (this.W.isShowing()) {
                this.W.dismiss();
                return;
            }
            return;
        }
        if (i2 == 202) {
            if (iArr.length <= 0 || iArr[0] != 0) {
                Toast.makeText(this, "相机权限禁用了。请务必开启相机权限", 0).show();
            } else {
                Intent intent2 = new Intent(this, (Class<?>) CaptureActivity.class);
                intent2.putExtra(CaptureActivity.KEY_HANDLE_SCAN_RESULT, false);
                startActivityForResult(intent2, 201);
            }
            if (this.W.isShowing()) {
                this.W.dismiss();
                return;
            }
            return;
        }
        if (i2 == 500) {
            if (iArr.length == 0 || iArr[0] != 0) {
                this.A = false;
            } else {
                this.f4119a.reload();
            }
            if (this.W.isShowing()) {
                this.W.dismiss();
                return;
            }
            return;
        }
        if (i2 == 600) {
            if (a(iArr)) {
                s();
            } else {
                Toast.makeText(this, "定位权限禁用了,请开启定位权限！", 0).show();
            }
            if (this.W.isShowing()) {
                this.W.dismiss();
                return;
            }
            return;
        }
        if (i2 != 700) {
            super.onRequestPermissionsResult(i2, strArr, iArr);
            return;
        }
        if (iArr.length <= 0 || iArr[0] != 0) {
            Toast.makeText(this, "文件权限禁用了。请务必开启文件读写权限", 0).show();
        } else {
            g("");
        }
        if (this.W.isShowing()) {
            this.W.dismiss();
        }
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.f4119a.onResume();
        this.F = new j0();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.DOWNLOAD_COMPLETE");
        intentFilter.addAction("android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED");
        registerReceiver(this.F, intentFilter);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        com.alibaba.fastjson.JSONObject object = JSON.parseObject("{\"openHijacking\":false} ");
        if (object.getBoolean("isEnableEncode") != null) {
            this.k0 = object.getBoolean("isEnableEncode");
        }
    }

    @Override // android.app.Activity
    public boolean shouldShowRequestPermissionRationale(String str) {
        return super.shouldShowRequestPermissionRationale(str);
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean supportShouldUpRecreateTask(Intent intent) {
        return super.supportShouldUpRecreateTask(intent);
    }

    public class h implements tn1.b {
        public h() {
        }

        @Override // supwisdom.tn1.b
        public void a(String str) {
            Toast.makeText(H5Activity.this, "已保存到系统相册", 0).show();
            H5Activity.this.M.dismiss();
        }

        @Override // supwisdom.tn1.b
        public void a() {
            Toast.makeText(H5Activity.this, "下载失败", 0).show();
            H5Activity.this.M.dismiss();
        }
    }

    public /* synthetic */ void d(View view) {
        Intent intent = new Intent(this, (Class<?>) ServiceEvaluateActivity.class);
        intent.putExtra("appId", this.d0);
        startActivity(intent);
        this.N.dismiss();
    }

    public /* synthetic */ void e(View view) {
        try {
            WXApplication.homeUniMP.sendUniMPEvent("workGuide", this.d0);
            this.N.dismiss();
            finish();
        } catch (Exception e2) {
            Toast.makeText(this, "跳转失败", 0).show();
            e2.printStackTrace();
        }
        this.N.dismiss();
    }

    public /* synthetic */ void f(View view) {
        if (sh1.c.a("isAgreeInfo").booleanValue()) {
            sh1.c.a("isAgreeInfo", (Boolean) false);
            Toast.makeText(this, "您已取消授权", 0).show();
        } else {
            sh1.c.a("isAgreeInfo", (Boolean) true);
            Toast.makeText(this, "授权成功", 0).show();
        }
        this.N.dismiss();
    }

    public /* synthetic */ void g(View view) {
        b((Activity) this);
    }

    public final InputStream l() {
        try {
            return getAssets().open("SZR.zip");
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    public final void m() {
        Log.d("webview bf size:", "" + this.f4119a.copyBackForwardList().getSize());
        boolean zCanGoBack = this.f4119a.canGoBack();
        if (this.f4119a.getVisibility() == 8) {
            this.f4119a.setVisibility(0);
            this.K.setVisibility(8);
        } else if (!zCanGoBack) {
            setResult(300, getIntent());
            super.onBackPressed();
        } else {
            this.j.setVisibility(0);
            this.t = true;
            this.u = System.currentTimeMillis();
            this.f4119a.goBack();
        }
    }

    public final void n() {
        if (this.v0 == null) {
            return;
        }
        setStatusBarVisibility(true);
        ((FrameLayout) getWindow().getDecorView()).removeView(this.w0);
        this.w0 = null;
        this.v0 = null;
        this.x0.onCustomViewHidden();
        this.f4119a.setVisibility(0);
        setRequestedOrientation(1);
    }

    public final void o() {
        com.alibaba.fastjson.JSONArray array = JSON.parseArray(getIntent().getStringExtra(fn1.u));
        if (array == null || array.size() == 0) {
            com.alibaba.fastjson.JSONObject object = JSON.parseObject("{\"openHijacking\":false} ");
            if (object.getBoolean("carryIdToken") == null || !object.getBoolean("carryIdToken").booleanValue()) {
                return;
            }
            this.z = "userToken";
            this.y = "userToken";
            return;
        }
        for (int i2 = 0; i2 < array.size(); i2++) {
            com.alibaba.fastjson.JSONObject jSONObject = array.getJSONObject(i2);
            String string = jSONObject.getString("tokenType");
            String string2 = jSONObject.getString("tokenKey");
            if (string.equals("header")) {
                this.z = string2;
            } else if (string.equals(SerializableCookie.COOKIE)) {
                this.y = string2;
            } else if (string.equals("url")) {
                this.x = string2;
            }
        }
    }

    public final void p() {
        com.alibaba.fastjson.JSONObject object = JSON.parseObject("{\"openHijacking\":false} ");
        if (object.getBoolean("openAuthorInfo") == null || !object.getBoolean("openAuthorInfo").booleanValue() || sh1.c.a("isAgreeInfo").booleanValue()) {
            return;
        }
        String str = this.h0;
        if (str == null || "".equals(str)) {
            ni1 ni1Var = new ni1(this, this.Z);
            ni1Var.show();
            ni1Var.a(new y(ni1Var));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ void q() {
        try {
            this.g0 = (Bitmap) Glide.with((FragmentActivity) this).asBitmap().load(this.c0).error(getResources().getDrawable(R.drawable.push_small)).submit().get();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final void r() {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(this.G));
        request.setAllowedNetworkTypes(3);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, b(this.G, this.H, this.J));
        request.setMimeType(this.J);
        request.setVisibleInDownloadsUi(true);
        request.setAllowedOverRoaming(true);
        request.setNotificationVisibility(1);
        request.addRequestHeader("Cookie", CookieManager.getInstance().getCookie(this.G));
        request.addRequestHeader("User-Agent", this.I);
        this.b.enqueue(request);
    }

    public final void s() {
        AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();
        aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        aMapLocationClientOption.setGpsFirst(false);
        aMapLocationClientOption.setHttpTimeOut(this.E * 1000);
        aMapLocationClientOption.setInterval(2000L);
        aMapLocationClientOption.setNeedAddress(true);
        aMapLocationClientOption.setOnceLocation(true);
        aMapLocationClientOption.setOnceLocationLatest(true);
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);
        aMapLocationClientOption.setSensorEnable(false);
        aMapLocationClientOption.setWifiScan(true);
        aMapLocationClientOption.setLocationCacheEnable(true);
        aMapLocationClientOption.setGeoLanguage(AMapLocationClientOption.GeoLanguage.DEFAULT);
        this.C.setLocationOption(aMapLocationClientOption);
        this.C.startLocation();
    }

    @TargetApi(21)
    public final void t() {
        String absolutePath;
        File fileA;
        this.s = null;
        File file = this.q;
        if (file == null || !file.exists() || (fileA = ln1.a(this, (absolutePath = this.q.getAbsolutePath()), CameraManager.MAX_FRAME_HEIGHT, 900, 400)) == null) {
            return;
        }
        sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(fileA)));
        Uri uriFromFile = Uri.fromFile(fileA);
        if (this.u0 != null) {
            try {
                Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(getContentResolver().openInputStream(uriFromFile));
                com.alibaba.fastjson.JSONObject jSONObject = new com.alibaba.fastjson.JSONObject();
                jSONObject.put("photoStr", (Object) a(bitmapDecodeStream));
                this.u0.callback(jSONObject.toJSONString());
            } catch (FileNotFoundException e2) {
                throw new RuntimeException(e2);
            }
        }
        ValueCallback<Uri> valueCallback = this.o;
        if (valueCallback != null) {
            valueCallback.onReceiveValue(Uri.parse(absolutePath));
            this.o = null;
        }
        ValueCallback<Uri[]> valueCallback2 = this.p;
        if (valueCallback2 != null) {
            valueCallback2.onReceiveValue(new Uri[]{uriFromFile});
            this.p = null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0029, code lost:
    
        r0 = r7.group(2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002d, code lost:
    
        r0 = java.net.URLDecoder.decode(r0, "utf-8");
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0032, code lost:
    
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0033, code lost:
    
        r2.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0057, code lost:
    
        r0 = r4.group(2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005b, code lost:
    
        r0 = java.net.URLDecoder.decode(r0, "utf-8");
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0060, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0061, code lost:
    
        r1.printStackTrace();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String b(java.lang.String r9, java.lang.String r10, java.lang.String r11) {
        /*
            r0 = 0
            boolean r1 = com.ta.utdid2.android.utils.StringUtils.isEmpty(r10)     // Catch: java.lang.Throwable -> L68
            if (r1 != 0) goto L6c
            java.lang.String r1 = ";"
            java.lang.String[] r1 = r10.split(r1)     // Catch: java.lang.Throwable -> L68
            int r2 = r1.length     // Catch: java.lang.Throwable -> L68
            r3 = 0
            r4 = 0
        L10:
            java.lang.String r5 = "utf-8"
            r6 = 2
            if (r4 >= r2) goto L3a
            r7 = r1[r4]     // Catch: java.lang.Throwable -> L68
            boolean r8 = com.ta.utdid2.android.utils.StringUtils.isEmpty(r7)     // Catch: java.lang.Throwable -> L68
            if (r8 != 0) goto L37
            java.util.regex.Pattern r8 = com.supwisdom.superapp.ui.activity.H5Activity.C0     // Catch: java.lang.Throwable -> L68
            java.util.regex.Matcher r7 = r8.matcher(r7)     // Catch: java.lang.Throwable -> L68
            boolean r8 = r7.find()     // Catch: java.lang.Throwable -> L68
            if (r8 == 0) goto L37
            java.lang.String r0 = r7.group(r6)     // Catch: java.lang.Throwable -> L68
            java.lang.String r0 = java.net.URLDecoder.decode(r0, r5)     // Catch: java.lang.Throwable -> L32
            goto L3a
        L32:
            r2 = move-exception
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L68
            goto L3a
        L37:
            int r4 = r4 + 1
            goto L10
        L3a:
            boolean r2 = com.ta.utdid2.android.utils.StringUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> L68
            if (r2 == 0) goto L6c
            int r2 = r1.length     // Catch: java.lang.Throwable -> L68
        L41:
            if (r3 >= r2) goto L6c
            r4 = r1[r3]     // Catch: java.lang.Throwable -> L68
            boolean r7 = com.ta.utdid2.android.utils.StringUtils.isEmpty(r4)     // Catch: java.lang.Throwable -> L68
            if (r7 != 0) goto L65
            java.util.regex.Pattern r7 = com.supwisdom.superapp.ui.activity.H5Activity.B0     // Catch: java.lang.Throwable -> L68
            java.util.regex.Matcher r4 = r7.matcher(r4)     // Catch: java.lang.Throwable -> L68
            boolean r7 = r4.find()     // Catch: java.lang.Throwable -> L68
            if (r7 == 0) goto L65
            java.lang.String r0 = r4.group(r6)     // Catch: java.lang.Throwable -> L68
            java.lang.String r0 = java.net.URLDecoder.decode(r0, r5)     // Catch: java.lang.Throwable -> L60
            goto L6c
        L60:
            r1 = move-exception
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L68
            goto L6c
        L65:
            int r3 = r3 + 1
            goto L41
        L68:
            r1 = move-exception
            r1.printStackTrace()
        L6c:
            if (r0 != 0) goto L90
            java.lang.String r0 = android.webkit.URLUtil.guessFileName(r9, r10, r11)
            java.lang.String r10 = "bin"
            boolean r10 = r0.endsWith(r10)
            if (r10 == 0) goto L90
            java.lang.String r10 = "."
            int r11 = r9.lastIndexOf(r10)
            java.lang.String r9 = r9.substring(r11)
            int r10 = r0.lastIndexOf(r10)
            java.lang.String r10 = r0.substring(r10)
            java.lang.String r0 = r0.replace(r10, r9)
        L90:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.supwisdom.superapp.ui.activity.H5Activity.b(java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    public /* synthetic */ void c(View view) {
        Toast.makeText(this, "暂未开通", 0).show();
        this.N.dismiss();
    }

    public void g(String str) {
        Uri uriFromFile;
        if (!getPackageManager().hasSystemFeature("android.hardware.camera.any")) {
            Toast.makeText(this, "设备无摄像头", 0).show();
            return;
        }
        File file = new File(getApplicationContext().getCacheDir().getAbsolutePath(), System.nanoTime() + FileUtils.JPEG_FILE_SUFFIX);
        this.q = file;
        if (!file.getParentFile().exists()) {
            this.q.getParentFile().mkdirs();
        }
        if (this.q.exists()) {
            this.q.delete();
        }
        try {
            this.q.createNewFile();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (!"".equals(str)) {
            intent.putExtra("android.intent.extras.CAMERA_FACING", 1);
        }
        if (getApplicationInfo().targetSdkVersion > 23) {
            uriFromFile = FileProvider.getUriForFile(this, "com.supwisdom.zueb.UploadFileProvider", this.q);
        } else {
            uriFromFile = Uri.fromFile(this.q);
        }
        intent.putExtra("output", uriFromFile);
        if (Build.VERSION.SDK_INT >= 23) {
            if (y7.a(this, "android.permission.CAMERA") != 0) {
                bj1 bj1Var = new bj1(this, getString(R.string.string_camera), getString(R.string.string_camera_content));
                this.W = bj1Var;
                bj1Var.show();
                this.s = intent;
                j7.a(this, new String[]{"android.permission.CAMERA"}, 103);
                return;
            }
            startActivityForResult(intent, 102);
            return;
        }
        startActivityForResult(intent, 102);
    }

    public /* synthetic */ void h(View view) {
        this.N.dismiss();
    }

    @SuppressLint({"UseCompatLoadingForDrawables"})
    public final void c(int i2) {
        new Thread(new Runnable() { // from class: supwisdom.zj1
            @Override // java.lang.Runnable
            public final void run() {
                this.f10007a.q();
            }
        }).start();
    }

    public final void e(String str) {
        ql0 ql0Var = new ql0(this, 2131820756);
        this.r = ql0Var;
        ql0Var.setOnDismissListener(new a0());
        View viewInflate = LayoutInflater.from(this).inflate(R.layout.dialog_bottom_select_pictrue, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_select_pictrue_album);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.tv_select_choose_file);
        TextView textView3 = (TextView) viewInflate.findViewById(R.id.tv_select_video);
        TextView textView4 = (TextView) viewInflate.findViewById(R.id.tv_select_pictrue_camera);
        TextView textView5 = (TextView) viewInflate.findViewById(R.id.tv_select_pictrue_cancel);
        if ("2".equals(str)) {
            textView.setText("选择照片");
            textView2.setVisibility(8);
            textView3.setVisibility(8);
        }
        textView.setOnClickListener(new b0());
        textView4.setOnClickListener(new c0(str));
        textView5.setOnClickListener(new d0());
        this.r.setContentView(viewInflate);
        this.r.show();
    }

    public final void f(String str) {
        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        if (str.contains(fn1.k)) {
            str = fn1.k;
        }
        if (TextUtils.isEmpty(this.y)) {
            cookieManager.setCookie(str, fn1.o + ContainerUtils.KEY_VALUE_DELIMITER + fn1.w);
        } else {
            cookieManager.setCookie(str, this.y + ContainerUtils.KEY_VALUE_DELIMITER + sh1.c.c(fn1.o));
        }
        cookieManager.setCookie(str, "Domain=" + str);
        cookieManager.setCookie(str, "Path=/");
        if (Build.VERSION.SDK_INT >= 21) {
            cookieManager.flush();
        } else {
            CookieSyncManager.getInstance().sync();
        }
    }

    public final void a(final String str, final String str2, JSONArray jSONArray) {
        String str3;
        View view;
        LinearLayout linearLayout;
        LinearLayout linearLayout2;
        RecyclerView recyclerView;
        int i2;
        final H5Activity h5Activity;
        int i3;
        LinearLayout linearLayout3;
        int i4;
        String str4 = this.c0;
        if (str4 != null && !"".equals(str4)) {
            c(1);
        }
        final Tencent tencentCreateInstance = Tencent.createInstance(com.igexin.push.core.b.m, this);
        this.N = new ql0(this, 2131820756);
        View viewInflate = LayoutInflater.from(this).inflate(R.layout.layout_share_dialog, (ViewGroup) null);
        this.N.setContentView(viewInflate);
        this.N.show();
        LinearLayout linearLayout4 = (LinearLayout) viewInflate.findViewById(R.id.ll_share_wechat);
        LinearLayout linearLayout5 = (LinearLayout) viewInflate.findViewById(R.id.ll_share_friends);
        LinearLayout linearLayout6 = (LinearLayout) viewInflate.findViewById(R.id.ll_share_qq);
        LinearLayout linearLayout7 = (LinearLayout) viewInflate.findViewById(R.id.ll_share_contacts);
        LinearLayout linearLayout8 = (LinearLayout) viewInflate.findViewById(R.id.ll_share_collect);
        LinearLayout linearLayout9 = (LinearLayout) viewInflate.findViewById(R.id.ll_share_guide);
        LinearLayout linearLayout10 = (LinearLayout) viewInflate.findViewById(R.id.ll_share_warrant);
        LinearLayout linearLayout11 = (LinearLayout) viewInflate.findViewById(R.id.ll_share_download);
        LinearLayout linearLayout12 = (LinearLayout) viewInflate.findViewById(R.id.ll_share_evaluate);
        LinearLayout linearLayout13 = (LinearLayout) viewInflate.findViewById(R.id.ll_share_copy);
        RecyclerView recyclerView2 = (RecyclerView) viewInflate.findViewById(R.id.recycler_share);
        u uVar = new u(this);
        uVar.setOrientation(0);
        recyclerView2.setLayoutManager(uVar);
        recyclerView2.setNestedScrollingEnabled(false);
        String str5 = this.p0;
        if (str5 != null) {
            Boolean bool = JSON.parseObject(str5).getBoolean("wechatEnabled");
            linearLayout2 = linearLayout11;
            Boolean bool2 = JSON.parseObject(this.p0).getBoolean("momentsEnabled");
            str3 = "";
            Boolean bool3 = JSON.parseObject(this.p0).getBoolean("qqEnabled");
            linearLayout = linearLayout10;
            Boolean bool4 = JSON.parseObject(this.p0).getBoolean("addressBookEnabled");
            view = viewInflate;
            Boolean bool5 = JSON.parseObject(this.p0).getBoolean("copyLinkEnabled");
            recyclerView = recyclerView2;
            Boolean bool6 = JSON.parseObject(this.p0).getBoolean("guideEnabled");
            Boolean bool7 = JSON.parseObject(this.p0).getBoolean("collectEnabled");
            Boolean bool8 = JSON.parseObject(this.p0).getBoolean("evaluateEnabled");
            if (bool.booleanValue()) {
                i4 = 0;
                linearLayout4.setVisibility(0);
                i2 = 8;
            } else {
                i4 = 0;
                i2 = 8;
                linearLayout4.setVisibility(8);
            }
            if (bool2.booleanValue()) {
                linearLayout5.setVisibility(i4);
            } else {
                linearLayout5.setVisibility(i2);
            }
            if (bool3.booleanValue()) {
                linearLayout6.setVisibility(i4);
            } else {
                linearLayout6.setVisibility(i2);
            }
            if (bool4.booleanValue()) {
                linearLayout7.setVisibility(i4);
            } else {
                linearLayout7.setVisibility(i2);
            }
            if (bool5.booleanValue()) {
                linearLayout13.setVisibility(i4);
            } else {
                linearLayout13.setVisibility(i2);
            }
            if (bool6.booleanValue()) {
                linearLayout9.setVisibility(i4);
            } else {
                linearLayout9.setVisibility(i2);
            }
            if (bool7.booleanValue()) {
                linearLayout8.setVisibility(i4);
            } else {
                linearLayout8.setVisibility(i2);
            }
            if (bool8.booleanValue()) {
                linearLayout12 = linearLayout12;
                linearLayout12.setVisibility(i4);
            } else {
                linearLayout12 = linearLayout12;
                linearLayout12.setVisibility(i2);
            }
        } else {
            str3 = "";
            view = viewInflate;
            linearLayout = linearLayout10;
            linearLayout2 = linearLayout11;
            recyclerView = recyclerView2;
            i2 = 8;
        }
        if (jSONArray != null) {
            linearLayout12.setVisibility(i2);
            List<hm1> array = JSON.parseArray(jSONArray.toString(), hm1.class);
            h5Activity = this;
            h5Activity.f0 = array;
            fm1 fm1Var = new fm1(h5Activity, array);
            recyclerView.setAdapter(fm1Var);
            fm1Var.a(h5Activity);
        } else {
            h5Activity = this;
        }
        View view2 = view;
        final ImageView imageView = (ImageView) view2.findViewById(R.id.iv_share_collect);
        final TextView textView = (TextView) view2.findViewById(R.id.tv_share_collect);
        TextView textView2 = (TextView) view2.findViewById(R.id.tv_share_warrant);
        LinearLayout linearLayout14 = linearLayout;
        linearLayout14.setVisibility(h5Activity.i0.booleanValue() ? 0 : 8);
        String str6 = h5Activity.j0;
        if (str6 != null) {
            if (str6.equals("4")) {
                i3 = 8;
                linearLayout12.setVisibility(8);
                linearLayout9.setVisibility(8);
            } else {
                i3 = 8;
            }
            if (h5Activity.j0.contains("remote")) {
                linearLayout12.setVisibility(i3);
                linearLayout9.setVisibility(i3);
            }
        } else {
            i3 = 8;
        }
        if (h5Activity.d0.contains("remote")) {
            linearLayout12.setVisibility(i3);
            linearLayout9.setVisibility(i3);
        }
        textView2.setText(!sh1.c.a("isAgreeInfo").booleanValue() ? "信息授权" : "取消授权");
        String str7 = h5Activity.h0;
        if (str7 == null || str3.equals(str7)) {
            linearLayout3 = linearLayout2;
        } else {
            linearLayout9.setVisibility(8);
            linearLayout14.setVisibility(8);
            linearLayout8.setVisibility(8);
            linearLayout3 = linearLayout2;
            linearLayout3.setVisibility(0);
        }
        if (str2 != null) {
            imageView.setSelected(str2.equals("1"));
            textView.setText(imageView.isSelected() ? "取消收藏" : "收藏");
        }
        TextView textView3 = (TextView) view2.findViewById(R.id.tv_share_cancel);
        linearLayout4.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.bk1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                this.f7075a.a(view3);
            }
        });
        linearLayout5.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.ik1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                this.f7961a.b(view3);
            }
        });
        linearLayout6.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.hk1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                this.f7846a.a(tencentCreateInstance, view3);
            }
        });
        linearLayout7.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.jk1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                this.f8072a.c(view3);
            }
        });
        linearLayout12.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.ak1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                this.f6960a.d(view3);
            }
        });
        linearLayout9.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.dk1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                this.f7354a.e(view3);
            }
        });
        linearLayout14.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.fk1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                this.f7612a.f(view3);
            }
        });
        linearLayout13.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.ck1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                this.f7226a.g(view3);
            }
        });
        linearLayout8.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.gk1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                this.f7743a.a(str2, imageView, textView, str, view3);
            }
        });
        linearLayout3.setOnClickListener(h5Activity.new e0());
        textView3.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.yj1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                this.f9888a.h(view3);
            }
        });
    }

    public /* synthetic */ void b(View view) {
        a(1, this.g0);
        this.N.dismiss();
    }

    @Override // supwisdom.fm1.a
    public void b(int i2) {
        this.f4119a.loadUrl(this.f0.get(i2).c());
        ql0 ql0Var = this.N;
        if (ql0Var != null) {
            ql0Var.dismiss();
        }
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

    public final void b(Activity activity) {
        ((ClipboardManager) activity.getSystemService("clipboard")).setPrimaryClip(ClipData.newRawUri("Label", Uri.parse(this.a0)));
    }

    public /* synthetic */ void a(View view) {
        a(0, this.g0);
        this.N.dismiss();
    }

    public /* synthetic */ void a(Tencent tencent, View view) {
        a(tencent, this);
        this.N.dismiss();
    }

    public /* synthetic */ void a(String str, ImageView imageView, TextView textView, String str2, View view) {
        if (str == null) {
            return;
        }
        String str3 = this.n0;
        if (str3 == null || "".equals(str3)) {
            mj1.b().c(fn1.w, str2, str.equals("0") ? "0" : "1").enqueue(new wl1(this, str, imageView, textView));
        } else {
            mj1.b().b(fn1.w, "external", this.d0, str.equals("0") ? "0" : "1", this.Z, this.c0, this.m0, this.a0, this.n0).enqueue(new vl1(this, str, imageView, textView));
        }
    }

    public final void a(Tencent tencent, Activity activity) {
        Bundle bundle = new Bundle();
        bundle.putString("title", this.Z);
        URLEncoder.encode(this.a0);
        String str = this.o0;
        if (str != null) {
            bundle.putString("targetUrl", fn1.g.concat(str));
        }
        String str2 = this.c0;
        if (str2 != null && !"".equals(str2)) {
            bundle.putString("imageUrl", this.c0);
        }
        bundle.putString("appName", getString(R.string.app_name));
        tencent.shareToQQ(activity, bundle, new m0());
    }

    public final void a(int i2, Bitmap bitmap) {
        if (kn1.a(this).a()) {
            String str = this.Z;
            URLEncoder.encode(this.a0);
            String str2 = this.o0;
            if (str2 != null) {
                String strConcat = fn1.g.concat(str2);
                if (bitmap == null) {
                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.push_small);
                }
                kn1.a(this).a(strConcat, str, bitmap, "", i2);
                return;
            }
            return;
        }
        Toast.makeText(getApplicationContext(), "未安装微信或版本不支持分享功能", 0).show();
    }

    public final void a(String str, String str2, String str3, String str4, long j2, String str5, boolean z2) {
        if (str.startsWith("blob")) {
            j7.a(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 0);
        }
        this.Q = j2;
        if (this.M == null) {
            this.M = new ql0(this, 2131820756);
            View viewInflate = LayoutInflater.from(this).inflate(R.layout.layout_preview_dialog, (ViewGroup) null);
            this.s0 = viewInflate;
            this.M.setContentView(viewInflate);
            this.M.setCancelable(false);
        }
        ql0 ql0Var = this.M;
        if (ql0Var != null) {
            ql0Var.show();
        }
        TextView textView = (TextView) this.s0.findViewById(R.id.tv_file_name);
        TextView textView2 = (TextView) this.s0.findViewById(R.id.tv_file_size);
        TextView textView3 = (TextView) this.s0.findViewById(R.id.tv_at_once_download);
        TextView textView4 = (TextView) this.s0.findViewById(R.id.tv_cancel_download);
        this.U = this.s0.findViewById(R.id.ll_download);
        this.P = (TextView) this.s0.findViewById(R.id.tv_progress_size);
        this.S = (TextView) this.s0.findViewById(R.id.tv_download);
        this.T = (TextView) this.s0.findViewById(R.id.tv_look);
        this.R = (LinearLayout) this.s0.findViewById(R.id.ll_download_finish);
        if (!z2) {
            this.S.setText("下载");
            if (str.contains(AbsoluteConst.SPNAME_DOWNLOAD)) {
                this.S.setText("请确认是否要下载此文件?");
            } else {
                this.S.setText("下载");
            }
        } else {
            this.S.setText("你已下载此文件，请确认是否重复下载?");
        }
        textView.setText(str5);
        textView2.setText(formatFileSize(j2));
        this.U.setOnClickListener(new g0(str, str5, str2, str4, textView3, j2, str3));
        this.T.setOnClickListener(new h0(str, str3, str4));
        textView4.setOnClickListener(new i0());
    }

    public final void a(WebChromeClient.FileChooserParams fileChooserParams, String str) {
        ql0 ql0Var = new ql0(this, 2131820756);
        this.r = ql0Var;
        ql0Var.setOnDismissListener(new a());
        View viewInflate = LayoutInflater.from(this).inflate(R.layout.dialog_bottom_select_pictrue, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tv_select_pictrue_album);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.tv_select_choose_file);
        TextView textView3 = (TextView) viewInflate.findViewById(R.id.tv_select_video);
        TextView textView4 = (TextView) viewInflate.findViewById(R.id.tv_select_pictrue_camera);
        TextView textView5 = (TextView) viewInflate.findViewById(R.id.tv_select_pictrue_cancel);
        fileChooserParams.getAcceptTypes();
        textView.setOnClickListener(new b());
        textView2.setOnClickListener(new c());
        textView3.setOnClickListener(new d());
        textView4.setOnClickListener(new e());
        textView5.setOnClickListener(new f());
        this.r.setContentView(viewInflate);
        this.r.show();
    }

    public final boolean a(int[] iArr) {
        for (int i2 : iArr) {
            if (i2 != 0) {
                return false;
            }
        }
        return true;
    }

    public void a(ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        ValueCallback<Uri[]> valueCallback2 = this.p;
        if (valueCallback2 != null) {
            valueCallback2.onReceiveValue(null);
        }
        this.p = valueCallback;
        a(fileChooserParams, "");
    }

    public final void a(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        if (this.v0 != null) {
            customViewCallback.onCustomViewHidden();
            return;
        }
        FrameLayout frameLayout = (FrameLayout) getWindow().getDecorView();
        k0 k0Var = new k0(this);
        this.w0 = k0Var;
        k0Var.addView(view, D0);
        frameLayout.addView(this.w0, D0);
        this.v0 = view;
        setStatusBarVisibility(false);
        this.x0 = customViewCallback;
        this.f4119a.setVisibility(8);
        setRequestedOrientation(0);
    }

    public /* synthetic */ void a(JSONObject jSONObject, ev0.d dVar) {
        try {
            this.e0 = jSONObject.getJSONArray("funcArr");
            Log.e(WXBaseActivity.TAG, "handle: ===== " + jSONObject.getJSONArray("funcArr"));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final void a(JSONObject jSONObject) {
        List<String> listB;
        String[] strArr = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
        if (jSONObject != null) {
            this.n = TextUtils.isEmpty(jSONObject.optString("type")) ? zm1.f10014e : jSONObject.optString("type");
            this.D = jSONObject.has(MediaFormatExtraConstants.KEY_LEVEL) ? jSONObject.optInt(MediaFormatExtraConstants.KEY_LEVEL) : 2;
            this.E = jSONObject.has("cacheTimeout") ? jSONObject.optInt("cacheTimeout") : 30;
        }
        try {
            if (Build.VERSION.SDK_INT >= 23 && getApplicationInfo().targetSdkVersion >= 23 && (listB = b(strArr)) != null && listB.size() > 0) {
                bj1 bj1Var = new bj1(this, getString(R.string.string_location), getString(R.string.string_location_content));
                this.W = bj1Var;
                bj1Var.show();
                getClass().getMethod("requestPermissions", String[].class, Integer.TYPE).invoke(this, (String[]) listB.toArray(new String[listB.size()]), 600);
                return;
            }
        } catch (Throwable unused) {
        }
        s();
    }

    /* JADX WARN: Removed duplicated region for block: B:65:0x00b2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00bc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:86:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(supwisdom.et1 r3, java.lang.String r4, long r5, java.lang.String r7, java.lang.String r8) throws java.lang.Throwable {
        /*
            r2 = this;
            r5 = 0
            java.io.InputStream r3 = r3.byteStream()     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L95
            java.lang.String r6 = android.os.Environment.getExternalStorageState()     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8d
            java.lang.String r0 = "mounted"
            boolean r6 = r6.equals(r0)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8d
            java.lang.String r0 = "sam"
            if (r6 != 0) goto L28
            boolean r6 = android.os.Environment.isExternalStorageRemovable()     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8d
            if (r6 != 0) goto L1a
            goto L28
        L1a:
            java.io.File r4 = r2.getCacheDir()     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8d
            r4.getPath()     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8d
            java.lang.String r4 = "无法下载"
            android.util.Log.e(r0, r4)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8d
            goto L52
        L28:
            java.lang.String r6 = "下载到SD卡"
            android.util.Log.e(r0, r6)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8d
            int r6 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8d
            r0 = 29
            if (r6 < r0) goto L39
            java.io.File r6 = r2.getExternalFilesDir(r5)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8d
            goto L3d
        L39:
            java.io.File r6 = android.os.Environment.getExternalStorageDirectory()     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8d
        L3d:
            java.lang.String r4 = b(r4, r7, r8)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8d
            java.io.File r7 = new java.io.File     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8d
            java.lang.String r8 = "utf-8"
            java.lang.String r4 = java.net.URLDecoder.decode(r4, r8)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8d
            r7.<init>(r6, r4)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8d
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8d
            r4.<init>(r7)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8d
            r5 = r4
        L52:
            r4 = 8192(0x2000, float:1.148E-41)
            byte[] r4 = new byte[r4]     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8d
            r6 = 0
            r7 = 0
        L58:
            int r8 = r3.read(r4)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8d
            r0 = -1
            if (r8 == r0) goto L72
            if (r5 == 0) goto L58
            r5.write(r4, r6, r8)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8d
            int r7 = r7 + r8
            java.lang.String r8 = java.lang.String.valueOf(r7)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8d
            r2.V = r8     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8d
            android.os.Handler r8 = r2.y0     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8d
            r0 = 1
            r8.sendEmptyMessage(r0)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8d
            goto L58
        L72:
            android.os.Handler r4 = r2.y0     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8d
            r6 = 3
            r4.sendEmptyMessage(r6)     // Catch: java.lang.Throwable -> L88 java.lang.Exception -> L8d
            if (r3 == 0) goto L82
            r3.close()     // Catch: java.io.IOException -> L7e
            goto L82
        L7e:
            r3 = move-exception
            r3.printStackTrace()
        L82:
            if (r5 == 0) goto Lae
            r5.close()     // Catch: java.io.IOException -> Laa
            goto Lae
        L88:
            r4 = move-exception
            r1 = r5
            r5 = r3
            r3 = r1
            goto Lb0
        L8d:
            r4 = move-exception
            r1 = r5
            r5 = r3
            r3 = r1
            goto L97
        L92:
            r4 = move-exception
            r3 = r5
            goto Lb0
        L95:
            r4 = move-exception
            r3 = r5
        L97:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> Laf
            if (r5 == 0) goto La4
            r5.close()     // Catch: java.io.IOException -> La0
            goto La4
        La0:
            r4 = move-exception
            r4.printStackTrace()
        La4:
            if (r3 == 0) goto Lae
            r3.close()     // Catch: java.io.IOException -> Laa
            goto Lae
        Laa:
            r3 = move-exception
            r3.printStackTrace()
        Lae:
            return
        Laf:
            r4 = move-exception
        Lb0:
            if (r5 == 0) goto Lba
            r5.close()     // Catch: java.io.IOException -> Lb6
            goto Lba
        Lb6:
            r5 = move-exception
            r5.printStackTrace()
        Lba:
            if (r3 == 0) goto Lc4
            r3.close()     // Catch: java.io.IOException -> Lc0
            goto Lc4
        Lc0:
            r3 = move-exception
            r3.printStackTrace()
        Lc4:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.supwisdom.superapp.ui.activity.H5Activity.a(supwisdom.et1, java.lang.String, long, java.lang.String, java.lang.String):void");
    }

    public final String a(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 1);
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
