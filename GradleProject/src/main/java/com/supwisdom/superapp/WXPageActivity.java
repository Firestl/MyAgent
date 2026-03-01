package com.supwisdom.superapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.manager.DefaultConnectivityMonitorFactory;
import com.lzy.okgo.model.HttpHeaders;
import com.supwisdom.superapp.util.HashUtil;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.RenderContainer;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import supwisdom.bj1;
import supwisdom.et1;
import supwisdom.j7;
import supwisdom.mj1;
import supwisdom.sh1;
import supwisdom.th1;
import supwisdom.y7;

/* JADX INFO: loaded from: classes2.dex */
public class WXPageActivity extends WXBaseActivity implements View.OnClickListener, IWXRenderListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WXSDKInstance f3983a;
    public Uri b;
    public FrameLayout c;
    public ProgressBar d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public RenderContainer f3984e;
    public View f;
    public View g;
    public View h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public Map<String, Object> n;
    public TextView o;
    public int p = 0;
    public bj1 q;

    public class a implements Handler.Callback {
        public a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            WXPageActivity.this.a(message);
            return false;
        }
    }

    public class b implements Callback<et1> {
        public b() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<et1> call, Throwable th) {
            String str = WXPageActivity.this.k;
            if (str == null || str.trim().equals("")) {
                WXPageActivity.this.f.setVisibility(0);
            }
            WXPageActivity.this.d.setVisibility(8);
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<et1> call, Response<et1> response) {
            try {
                if (response.code() == 200) {
                    et1 et1VarBody = response.body();
                    String strString = et1VarBody.string();
                    et1VarBody.close();
                    String strA = response.headers().a(HttpHeaders.HEAD_KEY_E_TAG);
                    if (strA == null || strA.trim().equals("")) {
                        strA = HashUtil.a(strString, HashUtil.f4395a);
                    }
                    if (WXPageActivity.this.j == null || strA == null || !WXPageActivity.this.j.equals(strA)) {
                        WXPageActivity.this.f3983a.render(WXPageActivity.this.i, strString, WXPageActivity.this.n, (String) null, WXRenderStrategy.APPEND_ASYNC);
                        sh1.c.b(WXPageActivity.this.m, strA);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
                WXPageActivity.this.d.setVisibility(8);
                Toast.makeText(WXPageActivity.this, "渲染页面错误", 1).show();
            }
        }
    }

    public WXPageActivity() {
        new Handler(new a());
    }

    public void a(Message message) {
        TextView textView = this.o;
        if (textView == null || message.obj == null) {
            return;
        }
        int i = this.p + 1;
        this.p = i;
        if (i > 100) {
            this.p = 0;
            textView.setText("");
        }
        this.o.append(message.obj.toString() + "\n");
    }

    public final void l() {
        mj1.b().h(this.i, this.j).enqueue(new b());
    }

    public final void m() {
        String[] strArr = {"android.permission.RECORD_AUDIO", DefaultConnectivityMonitorFactory.NETWORK_PERMISSION, "android.permission.INTERNET", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.MODIFY_AUDIO_SETTINGS", "android.permission.WRITE_SETTINGS", "android.permission.READ_PHONE_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.CHANGE_WIFI_STATE"};
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 9; i++) {
            String str = strArr[i];
            if (y7.a(this, str) != 0) {
                arrayList.add(str);
            }
        }
        String[] strArr2 = new String[arrayList.size()];
        if (arrayList.isEmpty()) {
            return;
        }
        bj1 bj1Var = new bj1(this, getString(com.supwisdom.zueb.R.string.string_storage), getString(com.supwisdom.zueb.R.string.string_storage_content));
        this.q = bj1Var;
        bj1Var.show();
        j7.a(this, (String[]) arrayList.toArray(strArr2), 123);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        WXSDKInstance wXSDKInstance = this.f3983a;
        if (wXSDKInstance != null) {
            wXSDKInstance.onActivityResult(i, i2, intent);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        WXSDKInstance wXSDKInstance = this.f3983a;
        if (wXSDKInstance != null) {
            wXSDKInstance.onBackPressed();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == com.supwisdom.zueb.R.id.reloadBt) {
            this.d.setVisibility(0);
            this.f.setVisibility(8);
            l();
        } else if (id == com.supwisdom.zueb.R.id.fullBarView) {
            finish();
        }
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Uri data = getIntent().getData();
        this.b = data;
        String string = data.toString();
        String queryParameter = this.b.getQueryParameter("showStatusBar");
        if (queryParameter == null || !queryParameter.trim().equalsIgnoreCase("no")) {
            WXBaseActivity.transparencyBar(this);
            WXBaseActivity.setLightStatusBar(this, true);
        } else {
            setStatusBarVisibility(false);
        }
        setContentView(com.supwisdom.zueb.R.layout.activity_wxpage);
        this.f = findViewById(com.supwisdom.zueb.R.id.netWorkErrorView);
        this.g = findViewById(com.supwisdom.zueb.R.id.fullBarView);
        View viewFindViewById = findViewById(com.supwisdom.zueb.R.id.reloadBt);
        this.h = viewFindViewById;
        viewFindViewById.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.c = (FrameLayout) findViewById(com.supwisdom.zueb.R.id.weexViewContainer);
        this.d = (ProgressBar) findViewById(com.supwisdom.zueb.R.id.loadingBar);
        this.f3983a = new WXSDKInstance(this);
        RenderContainer renderContainer = new RenderContainer(this);
        this.f3984e = renderContainer;
        this.f3983a.setRenderContainer(renderContainer);
        this.c.addView(this.f3984e);
        this.f3983a.registerRenderListener(this);
        int iIndexOf = string.indexOf(63);
        String strSubstring = iIndexOf < 0 ? string : string.substring(0, iIndexOf);
        this.l = strSubstring;
        this.trackPageName = strSubstring;
        this.i = string;
        HashMap map = new HashMap();
        this.n = map;
        map.put("bundleUrl", this.i);
        String str = (String) th1.c.a("", this.l);
        this.k = str;
        if (str != null && !str.trim().equals("")) {
            this.f3983a.render(this.i, this.k, this.n, (String) null, WXRenderStrategy.APPEND_ASYNC);
        }
        String strA = th1.c.a(this.l);
        this.m = strA;
        this.j = sh1.c.c(strA);
        l();
        m();
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        WXSDKInstance wXSDKInstance = this.f3983a;
        if (wXSDKInstance != null) {
            wXSDKInstance.onActivityDestroy();
        }
        super.onDestroy();
    }

    @Override // com.taobao.weex.IWXRenderListener
    public void onException(WXSDKInstance wXSDKInstance, String str, String str2) {
        Log.d("onException", "onException");
        Toast.makeText(this, str2, 0).show();
        this.d.setVisibility(8);
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        WXSDKInstance wXSDKInstance = this.f3983a;
        if (wXSDKInstance != null) {
            wXSDKInstance.onActivityPause();
        }
    }

    @Override // android.view.Window.Callback
    public void onPointerCaptureChanged(boolean z) {
    }

    @Override // com.taobao.weex.IWXRenderListener
    public void onRefreshSuccess(WXSDKInstance wXSDKInstance, int i, int i2) {
        Log.d("onRefreshSuccess", "onRefreshSuccess");
        this.d.setVisibility(8);
    }

    @Override // com.taobao.weex.IWXRenderListener
    public void onRenderSuccess(WXSDKInstance wXSDKInstance, int i, int i2) {
        Log.d("onRenderSuccess", "onRenderSuccess");
        this.d.setVisibility(8);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, supwisdom.j7.c
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        WXSDKInstance wXSDKInstance = this.f3983a;
        if (wXSDKInstance != null) {
            wXSDKInstance.onRequestPermissionsResult(i, strArr, iArr);
            if (this.q.isShowing()) {
                this.q.dismiss();
            }
        }
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        WXSDKInstance wXSDKInstance = this.f3983a;
        if (wXSDKInstance != null) {
            wXSDKInstance.onActivityResume();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        WXSDKInstance wXSDKInstance = this.f3983a;
        if (wXSDKInstance != null) {
            wXSDKInstance.onActivityStop();
        }
    }

    @Override // com.taobao.weex.IWXRenderListener
    public void onViewCreated(WXSDKInstance wXSDKInstance, View view) {
    }
}
