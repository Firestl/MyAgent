package com.supwisdom.superapp.ui.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import androidx.core.graphics.drawable.IconCompat;
import androidx.fragment.app.FragmentActivity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.sdk.app.AuthTask;
import com.bumptech.glide.Glide;
import com.g.gysdk.GYManager;
import com.g.gysdk.GYResponse;
import com.g.gysdk.GyCallBack;
import com.g.gysdk.GyConfig;
import com.lzy.okgo.model.HttpHeaders;
import com.supwisdom.superapp.WXApplication;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.superapp.WakeUpActivity;
import com.supwisdom.superapp.service.model.AppVersionInfo;
import com.supwisdom.zueb.R;
import com.ta.utdid2.device.UTDevice;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.ICallBack;
import io.dcloud.common.util.ZipUtils;
import io.dcloud.feature.sdk.DCUniMPSDK;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import supwisdom.c8;
import supwisdom.cj1;
import supwisdom.e8;
import supwisdom.en1;
import supwisdom.et1;
import supwisdom.fn1;
import supwisdom.hn1;
import supwisdom.im1;
import supwisdom.lm1;
import supwisdom.mj1;
import supwisdom.om1;
import supwisdom.rj1;
import supwisdom.sh1;
import supwisdom.t7;
import supwisdom.wi1;
import supwisdom.wm1;
import supwisdom.yi1;

/* JADX INFO: loaded from: classes2.dex */
public class MainActivity extends WXBaseActivity implements WXApplication.k, rj1.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f4250a;
    public boolean b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public yi1 f4251e;
    public String i;
    public String j;
    public String k;
    public rj1 c = rj1.e();
    public boolean d = false;
    public Boolean f = false;
    public String g = "";
    public Handler h = new Handler(Looper.getMainLooper());

    public class a implements GyCallBack {
        public a() {
        }

        @Override // com.g.gysdk.GyCallBack
        public void onFailed(GYResponse gYResponse) {
            Log.e(WXBaseActivity.TAG, "init onFailed response:" + gYResponse);
        }

        @Override // com.g.gysdk.GyCallBack
        public void onSuccess(GYResponse gYResponse) {
            Log.d(WXBaseActivity.TAG, "init onSuccess response:" + gYResponse);
        }
    }

    public class b implements GyCallBack {
        public b() {
        }

        @Override // com.g.gysdk.GyCallBack
        public void onFailed(GYResponse gYResponse) {
            Log.e(WXBaseActivity.TAG, "初始化时 提前预登录失败 prelogin:" + gYResponse);
        }

        @Override // com.g.gysdk.GyCallBack
        public void onSuccess(GYResponse gYResponse) {
            Log.d(WXBaseActivity.TAG, "初始化时 提前预登录成功 prelogin:" + gYResponse);
        }
    }

    public class c implements Callback<et1> {

        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ im1 f4255a;

            public a(im1 im1Var) {
                this.f4255a = im1Var;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    Intent intent = new Intent(MainActivity.this.getApplicationContext(), (Class<?>) H5Activity.class);
                    intent.setAction("android.intent.action.VIEW");
                    intent.setFlags(268435456);
                    if (this.f4255a.d() != null) {
                        intent.setData(Uri.parse(this.f4255a.d()));
                    }
                    intent.putExtra("typeId", this.f4255a.c());
                    intent.putExtra("typeUrl", this.f4255a.d());
                    intent.putExtra(IApp.ConfigProperty.CONFIG_SHORTCUT, true);
                    c8.a aVar = new c8.a(MainActivity.this, this.f4255a.c());
                    aVar.b(this.f4255a.b());
                    aVar.a(this.f4255a.b());
                    aVar.a(IconCompat.b(Glide.with((FragmentActivity) MainActivity.this).asBitmap().load(this.f4255a.a()).submit().get()));
                    aVar.a(intent);
                    e8.b(MainActivity.this, aVar.a());
                } catch (Exception e2) {
                    IconCompat.a(MainActivity.this, R.drawable.push_small);
                    e2.printStackTrace();
                }
            }
        }

        public c() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<et1> call, Throwable th) {
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<et1> call, Response<et1> response) {
            et1 et1VarBody = response.body();
            if (et1VarBody != null) {
                try {
                    MainActivity.this.i = et1VarBody.string();
                    JSONObject object = JSON.parseObject(MainActivity.this.i);
                    if (object.getInteger("code").intValue() == 0) {
                        List array = JSON.parseArray(object.getJSONObject("data").getJSONArray("menuInfo").toJSONString(), im1.class);
                        e8.d(MainActivity.this);
                        if (array == null || array.size() <= 0) {
                            return;
                        }
                        Iterator it = array.iterator();
                        while (it.hasNext()) {
                            new Thread(new a((im1) it.next())).start();
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public class d implements ICallBack {
        public d() {
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            return null;
        }
    }

    public class e implements Callback<et1> {
        public e() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<et1> call, Throwable th) {
            MainActivity.this.l();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<et1> call, Response<et1> response) {
            MainActivity.this.a(response);
        }
    }

    public class f implements ICallBack {
        public f() {
        }

        @Override // io.dcloud.common.DHInterface.ICallBack
        public Object onCallBack(int i, Object obj) {
            if (i != 1) {
                return null;
            }
            Log.d("startMini", DCUniMPSDK.getInstance().isInitialize() + "");
            Log.d("小程序", "小程序解压");
            return null;
        }
    }

    public class g implements Callback<com.supwisdom.superapp.service.model.Response<AppVersionInfo>> {
        public g() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<com.supwisdom.superapp.service.model.Response<AppVersionInfo>> call, Throwable th) {
            Log.d("网络超时测试", "onFailure-----" + System.currentTimeMillis() + "");
            MainActivity.this.l();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<com.supwisdom.superapp.service.model.Response<AppVersionInfo>> call, Response<com.supwisdom.superapp.service.model.Response<AppVersionInfo>> response) {
            Log.d("网络超时测试", System.currentTimeMillis() + "");
            com.supwisdom.superapp.service.model.Response<AppVersionInfo> responseBody = response.body();
            if (responseBody == null || responseBody.code != 0) {
                MainActivity.this.l();
                return;
            }
            int i = responseBody.data.updateStatus;
            if (i != AppVersionInfo.STATUS_TYPE_FORCE_UPDATE && i != AppVersionInfo.STATUS_TYPE_UPDATE) {
                MainActivity.this.l();
                return;
            }
            MainActivity mainActivity = MainActivity.this;
            if (!sh1.c.a("checkBox").booleanValue()) {
                MainActivity.this.l();
                return;
            }
            Intent intent = new Intent(mainActivity, (Class<?>) UpdateActivity.class);
            intent.putExtra(UpdateActivity.t, responseBody.data.updateStatus);
            intent.putExtra(UpdateActivity.u, responseBody.data.appUpdateDescriptionDTO.downloadUrl);
            intent.putExtra(UpdateActivity.v, responseBody.data.appUpdateDescriptionDTO.description);
            MainActivity.this.startActivityForResult(intent, 1);
        }
    }

    public class h implements cj1.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f4260a;
        public final /* synthetic */ String b;

        public h(boolean z, String str) {
            this.f4260a = z;
            this.b = str;
        }

        @Override // supwisdom.cj1.c
        public void a() {
            if (this.f4260a || MainActivity.this.d) {
                MainActivity.this.e(this.b);
            } else {
                MainActivity.this.m();
            }
            if ("".equals(MainActivity.this.g)) {
                return;
            }
            sh1.c.b("privacyVersion", MainActivity.this.g);
            MainActivity.this.f = false;
        }

        @Override // supwisdom.cj1.c
        public void b() {
            MainActivity.this.finish();
        }
    }

    public class i implements yi1.a {

        public class a implements wi1.a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ wi1 f4262a;

            public a(wi1 wi1Var) {
                this.f4262a = wi1Var;
            }

            @Override // supwisdom.wi1.a
            public void a() {
                this.f4262a.dismiss();
            }

            @Override // supwisdom.wi1.a
            public void b() {
                MainActivity.this.f4251e.dismiss();
                MainActivity.this.d = true;
                sh1.c.a("don't_check_notification_permission", (Boolean) true);
                MainActivity.this.l();
            }
        }

        public i() {
        }

        @Override // supwisdom.yi1.a
        public void a() {
            try {
                try {
                    Intent intent = new Intent();
                    if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT < 26) {
                        intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                        intent.putExtra("app_package", MainActivity.this.getPackageName());
                        intent.putExtra("app_uid", MainActivity.this.getApplicationInfo().uid);
                    } else if (Build.VERSION.SDK_INT >= 26) {
                        intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                        intent.putExtra("android.provider.extra.APP_PACKAGE", MainActivity.this.getPackageName());
                    } else {
                        intent.setAction("android.intent.action.VIEW");
                        intent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
                        intent.putExtra("com.android.settings.ApplicationPkgName", MainActivity.this.getPackageName());
                    }
                    MainActivity.this.startActivityForResult(intent, 1000);
                } catch (Exception unused) {
                    Intent intent2 = new Intent();
                    intent2.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent2.setData(Uri.fromParts("package", MainActivity.this.getPackageName(), null));
                    MainActivity.this.startActivity(intent2);
                }
            } finally {
                MainActivity.this.f4251e.dismiss();
                MainActivity.this.d = true;
            }
        }

        @Override // supwisdom.yi1.a
        public void b() {
            MainActivity.this.f4251e.dismiss();
            MainActivity.this.d = true;
            MainActivity.this.l();
        }

        @Override // supwisdom.yi1.a
        public void c() {
            wi1 wi1Var = new wi1(MainActivity.this);
            wi1Var.a(new a(wi1Var));
            wi1Var.show();
        }
    }

    public class j implements Callback<et1> {
        public j() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<et1> call, Throwable th) {
            MainActivity.this.t();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<et1> call, Response<et1> response) {
            et1 et1VarBody = response.body();
            try {
                if (et1VarBody != null) {
                    JSONArray jSONArray = new org.json.JSONObject(et1VarBody.string()).getJSONArray("data");
                    jSONArray.getJSONObject(0).getString("version");
                    MainActivity.this.g = jSONArray.getJSONObject(1).getString("version");
                    sh1.c.c(fn1.o);
                    String strC = sh1.c.c("privacyVersion");
                    if ("".equals(strC) || MainActivity.this.g.equals(strC)) {
                        MainActivity.this.t();
                    } else {
                        MainActivity.this.t();
                        MainActivity.this.f = true;
                        if (sh1.c.a("isAgreePrivacy").booleanValue()) {
                            sh1.c.a("isAgreePrivacy", (Boolean) false);
                            sh1.c.a("isAgreeFacePrivacy", (Boolean) false);
                        }
                    }
                } else {
                    MainActivity.this.t();
                }
            } catch (IOException | JSONException e2) {
                MainActivity.this.t();
                e2.printStackTrace();
            }
        }
    }

    public class k implements Callback<com.supwisdom.superapp.service.model.Response<JSONObject>> {

        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ String f4265a;

            public a(String str) {
                this.f4265a = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                Map<String, String> mapAuthV2 = new AuthTask(MainActivity.this).authV2(this.f4265a, true);
                Message message = new Message();
                message.what = 11;
                message.obj = mapAuthV2;
                WXApplication.federatedCodeHandler.sendMessage(message);
            }
        }

        public k() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<com.supwisdom.superapp.service.model.Response<JSONObject>> call, Throwable th) {
            MainActivity mainActivity = MainActivity.this;
            Toast.makeText(mainActivity, mainActivity.getResources().getString(R.string.net_error), 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<com.supwisdom.superapp.service.model.Response<JSONObject>> call, Response<com.supwisdom.superapp.service.model.Response<JSONObject>> response) {
            if (response.code() == 200 && response.body() != null && response.body().data != null && response.body().code == 0) {
                new Thread(new a(response.body().data.getString("authzInfo"))).start();
            } else {
                MainActivity mainActivity = MainActivity.this;
                Toast.makeText(mainActivity, mainActivity.getResources().getString(R.string.authorize_error), 0).show();
            }
        }
    }

    @Override // com.supwisdom.superapp.WXApplication.k
    public void j() {
        q();
    }

    public final void l() {
        if (sh1.c.a("firstOpen", false).booleanValue()) {
            Intent intent = new Intent();
            intent.setClass(this, GuideActivity.class);
            startActivity(intent);
            sh1.c.a("firstOpen", (Boolean) false);
            finish();
            return;
        }
        String strC = sh1.c.c(fn1.o);
        boolean zBooleanValue = sh1.c.a("don't_check_notification_permission").booleanValue();
        if (!sh1.c.a("isAgreePrivacy").booleanValue()) {
            cj1 cj1Var = new cj1(this, this.f);
            cj1Var.c();
            cj1Var.a(new h(zBooleanValue, strC));
        } else if (zBooleanValue) {
            e(strC);
        } else if (this.d) {
            e(strC);
        } else {
            m();
        }
    }

    public final void m() {
        t7 t7VarA = t7.a(this);
        if (Build.VERSION.SDK_INT >= 19) {
            if (t7VarA.a()) {
                this.d = true;
                l();
            } else {
                yi1 yi1Var = new yi1(this);
                this.f4251e = yi1Var;
                yi1Var.a(new i());
                this.f4251e.show();
            }
        }
    }

    public final void n() {
        PackageInfo packageInfo;
        Log.d("main_act", "checkVersion");
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e2) {
            lm1.a(e2);
            e2.printStackTrace();
            packageInfo = null;
        }
        String str = packageInfo.versionName;
        sh1.c.c(fn1.o);
        Log.d("网络超时测试", System.currentTimeMillis() + "");
        mj1.b().c("mobile", "mobile", fn1.f7620a, str).enqueue(new g());
    }

    public final void o() {
        int iIntValue = sh1.c.b(fn1.U).intValue();
        if (iIntValue != -1 && 20032 > iIntValue && !sh1.c.c(fn1.i).equals("W/\"668f51d0-5c2124\"")) {
            om1.a(this.f4250a);
        }
        if (!om1.b(this.f4250a)) {
            Log.d("小程序", "小程序本地");
            String str = fn1.i + ".wgt";
            File file = new File(getCacheDir(), fn1.i + ".wgt");
            try {
                InputStream inputStreamOpen = getAssets().open(str);
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int i2 = inputStreamOpen.read(bArr);
                            if (i2 <= 0) {
                                break;
                            } else {
                                fileOutputStream.write(bArr, 0, i2);
                            }
                        }
                    } finally {
                        fileOutputStream.close();
                    }
                } finally {
                    inputStreamOpen.close();
                }
            } catch (IOException e2) {
                lm1.a(e2);
                e2.printStackTrace();
            }
            a(fn1.i, file.getAbsolutePath(), new d());
            sh1.c.b(fn1.i, "W/\"668f51d0-5c2124\"");
        }
        sh1.c.a(fn1.U, (Integer) 20032);
        mj1.b().h(fn1.j, sh1.c.c(fn1.i)).enqueue(new e());
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 1000 && i3 == 0) {
            l();
        } else if (i2 == 1 && i3 == 99) {
            l();
        }
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        Uri data;
        super.onCreate(bundle);
        Log.d("main_act", "onCreate");
        this.trackPageName = getResources().getString(R.string.splash_activity);
        setContentView(R.layout.layout_main);
        this.f4250a = om1.c(this) + File.separator + fn1.i + ".wgt";
        setRequestedOrientation(1);
        p();
        WXApplication.setInitDCUniMPSDKCallback(this);
        if (Build.VERSION.SDK_INT >= 26) {
            u();
        }
        Intent intent = getIntent();
        if ("android.intent.action.VIEW".equals(intent.getAction()) && (data = intent.getData()) != null && data.toString().contains(getPackageName())) {
            en1.b(getApplicationContext(), "INTENT_URL", data.toString());
        }
        this.j = getIntent().getStringExtra("typeId");
        this.k = getIntent().getStringExtra("typeUrl");
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override // android.view.Window.Callback
    public void onPointerCaptureChanged(boolean z) {
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, supwisdom.j7.c
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        o();
        if (i2 == 16) {
            s();
        }
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // com.supwisdom.superapp.WXBaseActivity
    public void openMini() {
        super.openMini();
    }

    public void p() {
        mj1.b().d().enqueue(new j());
    }

    public void q() {
        mj1.b().e(getPackageName(), UUID.randomUUID().toString(), UTDevice.getUtdid(this)).enqueue(new k());
    }

    public final void r() {
        GYManager.getInstance().init(GyConfig.with(getApplicationContext()).preLoginUseCache(true).debug(false).eLoginDebug(false).channel("com.superwisdom.superapp").callBack(new a()).build());
        GYManager.getInstance().ePreLogin(8000, new b());
    }

    public final void s() {
        GYManager.getInstance().setDebug(true);
        r();
    }

    public final void t() {
        String[] strArr = new String[new ArrayList().size()];
        o();
    }

    public final void u() {
        mj1.b().g().enqueue(new c());
    }

    @Override // supwisdom.rj1.c
    public void d(String str) {
        try {
            int i2 = new org.json.JSONObject(str).getInt("errorCode");
            String strC = sh1.c.c(fn1.o);
            if (i2 != 0 || TextUtils.isEmpty(strC)) {
                return;
            }
            this.c.d();
            startActivity(new Intent(this, (Class<?>) WakeUpActivity.class));
            this.c.a(this);
            this.c.a();
        } catch (JSONException e2) {
            lm1.a(e2);
            e2.printStackTrace();
        }
    }

    public final void e(String str) {
        if (str == null || str.trim().equals("")) {
            Intent intent = new Intent();
            intent.setClass(this, LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        if (hn1.a(str, com.igexin.push.config.c.B)) {
            fn1.w = "";
            sh1.c.b(fn1.o, "");
            Intent intent2 = new Intent();
            intent2.addFlags(268468224);
            intent2.setClass(this, LoginActivity.class);
            startActivity(intent2);
            finish();
            return;
        }
        fn1.w = str;
        if (!DCUniMPSDK.getInstance().isInitialize()) {
            this.b = true;
            return;
        }
        String str2 = this.j;
        if (str2 != null) {
            if (str2.contains("scan")) {
                WXApplication.homeUniMP.sendUniMPEvent("scanQuickActionListener", this.j);
                return;
            }
            Intent intent3 = new Intent(getApplicationContext(), (Class<?>) H5Activity.class);
            intent3.putExtra("typeId", this.j);
            String str3 = this.k;
            if (str3 != null) {
                intent3.setData(Uri.parse(str3));
            }
            intent3.putExtra("typeUrl", this.k);
            startActivity(intent3);
        }
        Log.d("小程序", "立即启动");
        WXApplication.instance.userOnlineDetect();
        openMini();
    }

    public final void a(String str, String str2, ICallBack iCallBack) {
        String str3 = DCUniMPSDK.getInstance().getAppBasePath(getApplicationContext()) + str + File.separatorChar + "www/";
        File file = new File(str3);
        if (file.exists()) {
            file.delete();
        }
        try {
            ZipUtils.upZipFile(new File(str2), str3);
            iCallBack.onCallBack(1, "");
        } catch (IOException e2) {
            e2.printStackTrace();
            iCallBack.onCallBack(-1, e2.getMessage());
        }
    }

    public final void a(Response<et1> response) {
        InputStream inputStreamByteStream;
        FileOutputStream fileOutputStream;
        byte[] bArr;
        String strA;
        if (response.code() == 200) {
            Log.d("小程序", "小程序更新");
            try {
                inputStreamByteStream = response.body().byteStream();
                File file = new File(this.f4250a);
                if (file.exists()) {
                    file.delete();
                }
                fileOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream(file);
                } catch (FileNotFoundException e2) {
                    lm1.a(e2);
                    e2.printStackTrace();
                }
                bArr = new byte[8192];
            } catch (Throwable th) {
                lm1.a(th);
                th.printStackTrace();
            }
            while (true) {
                try {
                    int i2 = inputStreamByteStream.read(bArr);
                    if (i2 == -1) {
                        break;
                    } else {
                        fileOutputStream.write(bArr, 0, i2);
                    }
                } catch (Throwable th2) {
                    lm1.a(th2);
                    th2.printStackTrace();
                }
                strA = response.headers().a(HttpHeaders.HEAD_KEY_E_TAG);
                if (strA != null && !strA.trim().equals("")) {
                    sh1.c.b(fn1.i, strA);
                }
                n();
                return;
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            inputStreamByteStream.close();
            a(fn1.i, this.f4250a, new f());
            strA = response.headers().a(HttpHeaders.HEAD_KEY_E_TAG);
            if (strA != null) {
                sh1.c.b(fn1.i, strA);
            }
            n();
            return;
        }
        n();
    }

    @Override // com.supwisdom.superapp.WXApplication.k
    public void a(boolean z) {
        Log.d("小程序", z + "初始化结果");
        if (z && this.b) {
            openMini();
        }
    }

    @Override // com.supwisdom.superapp.WXApplication.k
    public void a(int i2) {
        wm1.b().a(getWindow().getDecorView());
    }
}
