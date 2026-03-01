package com.supwisdom.superapp.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.alibaba.fastjson.JSONObject;
import com.supwisdom.superapp.WXApplication;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.superapp.ddshare.DDShareActivity;
import com.supwisdom.superapp.service.model.Response;
import com.supwisdom.superapp.view.VerifyCodeView;
import com.supwisdom.superapp.view.VerifyCodeView4;
import com.supwisdom.superapp.wxapi.WXEntryActivity;
import com.supwisdom.zueb.R;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.wework.api.IWWAPI;
import com.tencent.wework.api.WWAPIFactory;
import com.tencent.wework.api.model.WWAuthMessage;
import io.dcloud.feature.oauth.weixin.WeiXinOAuthService;
import org.json.JSONException;
import retrofit2.Call;
import retrofit2.Callback;
import supwisdom.am1;
import supwisdom.bm1;
import supwisdom.ct1;
import supwisdom.fn1;
import supwisdom.gj1;
import supwisdom.lr;
import supwisdom.mj1;
import supwisdom.or;
import supwisdom.vr;
import supwisdom.xs1;
import supwisdom.zi1;

/* JADX INFO: loaded from: classes2.dex */
public class SecureCertificationActivity extends WXBaseActivity implements zi1 {
    public static int N = 2;
    public static int O = 3;
    public static int P = 4;
    public LinearLayoutCompat C;
    public ConstraintLayout D;
    public TextView E;
    public Boolean I;
    public Boolean J;
    public Boolean K;
    public Boolean L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Handler f4346a;
    public or b;
    public LinearLayout c;
    public TextView d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f4347e;
    public String f;
    public String g;
    public String h;
    public TextView i;
    public TextView j;
    public TextView k;
    public TextView l;
    public TextView m;
    public TextView n;
    public VerifyCodeView4 o;
    public String p;
    public String q;
    public TextView r;
    public LinearLayoutCompat s;
    public LinearLayoutCompat t;
    public LinearLayout u;
    public LinearLayout v;
    public LinearLayout w;
    public LinearLayout x;
    public JSONObject y = null;
    public Boolean z = false;
    public Boolean A = false;
    public Boolean B = false;
    public String F = "";
    public String G = "";
    public String H = "";

    @SuppressLint({"NonConstantResourceId"})
    public final View.OnClickListener M = new View.OnClickListener() { // from class: supwisdom.ll1
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f8300a.a(view);
        }
    };

    public class a implements Callback<Response<JSONObject>> {
        public a() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Log.d("onFailure", "onFailure");
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null || response.body() == null) {
                return;
            }
            if (!response.body().data.getInteger("status").equals(2)) {
                SecureCertificationActivity.this.h("验证失败");
                return;
            }
            SecureCertificationActivity.this.setResult(SecureCertificationActivity.P, new Intent());
            SecureCertificationActivity.this.finish();
        }
    }

    public class b implements Callback<Response<JSONObject>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4349a;

        public b(String str) {
            this.f4349a = str;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Log.d("onFailure", "onFailure");
            SecureCertificationActivity.this.h("网络错误");
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200) {
                SecureCertificationActivity.this.h(SecureCertificationActivity.this.getResources().getString(R.string.account_error));
                return;
            }
            Response<JSONObject> responseBody = response.body();
            JSONObject jSONObject = responseBody.data;
            if (jSONObject == null) {
                Toast.makeText(SecureCertificationActivity.this, R.string.string_empty_data, 0).show();
                return;
            }
            SecureCertificationActivity.this.f4347e = jSONObject.getString("securePhone");
            SecureCertificationActivity.this.f = responseBody.data.getString("secureEmail");
            SecureCertificationActivity.this.g = responseBody.data.getString("attestServerUrl");
            if (SecureCertificationActivity.this.g != null && !"".equals(SecureCertificationActivity.this.g)) {
                fn1.h = SecureCertificationActivity.this.g;
            }
            if (responseBody.data.getJSONObject("fedAuth") != null) {
                SecureCertificationActivity.this.y = responseBody.data.getJSONObject("fedAuth");
            }
            SecureCertificationActivity.this.h = responseBody.data.getString("gid");
            SecureCertificationActivity.this.e(this.f4349a);
        }
    }

    public class c extends Handler {
        public c() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                String strValueOf = String.valueOf(message.obj);
                SecureCertificationActivity secureCertificationActivity = SecureCertificationActivity.this;
                secureCertificationActivity.b("openweixin", secureCertificationActivity.h, strValueOf);
            } else if (i == 2) {
                String strValueOf2 = String.valueOf(message.obj);
                SecureCertificationActivity secureCertificationActivity2 = SecureCertificationActivity.this;
                secureCertificationActivity2.b("dingtalk", secureCertificationActivity2.h, strValueOf2);
            }
        }
    }

    public class d implements VerifyCodeView.b {
        public d() {
        }

        @Override // com.supwisdom.superapp.view.VerifyCodeView.b
        public void a() {
            if (SecureCertificationActivity.this.l.getVisibility() == 0) {
                SecureCertificationActivity secureCertificationActivity = SecureCertificationActivity.this;
                secureCertificationActivity.d(secureCertificationActivity.o.getEditContent(), SecureCertificationActivity.this.h);
            } else if (SecureCertificationActivity.this.m.getVisibility() == 0) {
                SecureCertificationActivity secureCertificationActivity2 = SecureCertificationActivity.this;
                secureCertificationActivity2.c(secureCertificationActivity2.o.getEditContent(), SecureCertificationActivity.this.h);
            }
        }

        @Override // com.supwisdom.superapp.view.VerifyCodeView.b
        public void b() {
        }
    }

    public class e implements Callback<Response<JSONObject>> {
        public e() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Log.d("onFailure", "onFailure");
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200) {
                Toast.makeText(SecureCertificationActivity.this, "获取验证码失败", 0).show();
            }
        }
    }

    public class f implements Callback<Response<JSONObject>> {
        public f() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Log.d("onFailure", "onFailure");
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200) {
                Toast.makeText(SecureCertificationActivity.this, "获取验证码失败", 0).show();
            }
        }
    }

    public class g implements Callback<Response<JSONObject>> {
        public g() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Log.d("onFailure", "onFailure");
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null) {
                return;
            }
            if (!response.body().data.getInteger("status").equals(2)) {
                SecureCertificationActivity.this.h("验证码错误");
                return;
            }
            SecureCertificationActivity.this.setResult(SecureCertificationActivity.N, new Intent());
            SecureCertificationActivity.this.finish();
        }
    }

    public class h implements Callback<Response<JSONObject>> {
        public h() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Log.d("onFailure", "onFailure");
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null) {
                return;
            }
            if (!response.body().data.getInteger("status").equals(2)) {
                SecureCertificationActivity.this.h("验证码错误");
                return;
            }
            SecureCertificationActivity.this.setResult(SecureCertificationActivity.O, new Intent());
            SecureCertificationActivity.this.finish();
        }
    }

    public class i implements Callback<Response<JSONObject>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4356a;
        public final /* synthetic */ String b;

        public i(String str, String str2) {
            this.f4356a = str;
            this.b = str2;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Log.d("onFailure", "onFailure");
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null) {
                return;
            }
            JSONObject jSONObject = response.body().data;
            jSONObject.getString("callbackCode");
            SecureCertificationActivity.this.c(this.f4356a, jSONObject.getString("callbackCode"), this.b);
        }
    }

    public final void g(String str) {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("gid", str);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().h(ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new e());
    }

    public void h(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this, str, 0).show();
    }

    public final void l() {
        this.p = getIntent().getStringExtra("state");
        this.q = getIntent().getStringExtra("type");
        this.I = Boolean.valueOf(getIntent().getBooleanExtra("mfaTypeSecurePhone", false));
        this.J = Boolean.valueOf(getIntent().getBooleanExtra("mfaTypeSecureEmail", false));
        this.K = Boolean.valueOf(getIntent().getBooleanExtra("mfaTypeFaceVerify", false));
        this.L = Boolean.valueOf(getIntent().getBooleanExtra("mfaTypeFedAuth", false));
        b(this.q, this.p);
        this.o.setInputCompleteListener(new d());
    }

    public final void m() {
        this.c = (LinearLayout) findViewById(R.id.ll_left_back);
        this.d = (TextView) findViewById(R.id.tv_send);
        this.o = (VerifyCodeView4) findViewById(R.id.vc_code);
        this.i = (TextView) findViewById(R.id.tv_email);
        this.k = (TextView) findViewById(R.id.tv_phone);
        this.j = (TextView) findViewById(R.id.tv_face);
        this.r = (TextView) findViewById(R.id.tv_other);
        this.n = (TextView) findViewById(R.id.tv_secure_num);
        this.l = (TextView) findViewById(R.id.tv_secure_phone);
        this.m = (TextView) findViewById(R.id.tv_secure_email);
        this.s = (LinearLayoutCompat) findViewById(R.id.ll_certification);
        this.t = (LinearLayoutCompat) findViewById(R.id.ll_certification_platform);
        this.D = (ConstraintLayout) findViewById(R.id.cl_mobile);
        this.C = (LinearLayoutCompat) findViewById(R.id.ll_lack_certification);
        this.E = (TextView) findViewById(R.id.tv_lack);
        this.u = (LinearLayout) findViewById(R.id.ll_certification_wechat);
        this.v = (LinearLayout) findViewById(R.id.ll_certification_dingTalk);
        this.w = (LinearLayout) findViewById(R.id.ll_certification_workWeChat);
        this.x = (LinearLayout) findViewById(R.id.ll_certification_qq);
        this.c.setOnClickListener(this.M);
        this.d.setOnClickListener(this.M);
        this.i.setOnClickListener(this.M);
        this.k.setOnClickListener(this.M);
        this.j.setOnClickListener(this.M);
        this.r.setOnClickListener(this.M);
        this.u.setOnClickListener(this.M);
        this.v.setOnClickListener(this.M);
        this.w.setOnClickListener(this.M);
        this.x.setOnClickListener(this.M);
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @SuppressLint({"HandlerLeak"})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        WXBaseActivity.transparencyBar(this);
        WXBaseActivity.setLightStatusBar(this, true);
        setContentView(R.layout.activity_secure_certificat);
        m();
        l();
        this.f4346a = new c();
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f4346a.removeMessages(2);
        this.f4346a.removeMessages(1);
    }

    public final void f(String str) {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("gid", str);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().s(ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new f());
    }

    public final void c(String str, String str2) {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("code", str);
            jSONObject.put("gid", str2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().w(ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new h());
    }

    public final void d(String str, String str2) {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("code", str);
            jSONObject.put("gid", str2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().t(ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new g());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e(java.lang.String r7) {
        /*
            Method dump skipped, instruction units count: 494
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.supwisdom.superapp.ui.activity.SecureCertificationActivity.e(java.lang.String):void");
    }

    public final void b(String str, String str2, String str3) {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("gid", str2);
            jSONObject.put("federatedType", str);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().j(ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new i(str2, str3));
    }

    public /* synthetic */ void a(View view) {
        String str;
        String str2;
        switch (view.getId()) {
            case R.id.ll_certification_dingTalk /* 2131296799 */:
                if ("".equals(this.H)) {
                    h("当前账号未绑定钉钉");
                } else {
                    vr vrVar = new vr();
                    or orVarA = lr.a(this, "", true);
                    this.b = orVarA;
                    DDShareActivity.c = orVarA;
                    DDShareActivity.f3997e = "platform";
                    DDShareActivity.d = this.f4346a;
                    vrVar.b = "sns_login";
                    vrVar.c = "test";
                    if (!orVarA.b()) {
                        Toast.makeText(this, "您还未安装钉钉", 0).show();
                    } else if (this.b.a()) {
                        this.b.a(vrVar);
                    } else {
                        Toast.makeText(this, "钉钉版本过低,请先升级", 0).show();
                    }
                    b("dingtalk", this.h, "");
                }
                break;
            case R.id.ll_certification_qq /* 2131296801 */:
                b("qq", this.h, "");
                break;
            case R.id.ll_certification_wechat /* 2131296802 */:
                if ("".equals(this.F)) {
                    h("当前账号未绑定微信");
                } else if (!WXApplication.api.isWXAppInstalled()) {
                    h("您还未安装微信客户端");
                } else {
                    SendAuth.Req req = new SendAuth.Req();
                    req.scope = WeiXinOAuthService.DEFAULT_SCOPE;
                    req.state = "superapp_wx_login";
                    WXEntryActivity.b = "platform";
                    WXEntryActivity.c = this.f4346a;
                    WXApplication.api.sendReq(req);
                }
                break;
            case R.id.ll_certification_workWeChat /* 2131296803 */:
                if ("".equals(this.G)) {
                    h("当前账号未绑定企业微信");
                } else {
                    IWWAPI iwwapiCreateWWAPI = WWAPIFactory.createWWAPI(this);
                    iwwapiCreateWWAPI.registerApp(com.igexin.push.core.b.m);
                    WWAuthMessage.Req req2 = new WWAuthMessage.Req();
                    req2.sch = com.igexin.push.core.b.m;
                    req2.appId = com.igexin.push.core.b.m;
                    req2.agentId = com.igexin.push.core.b.m;
                    req2.state = "dd";
                    if (iwwapiCreateWWAPI.isWWAppSupportAPI()) {
                        iwwapiCreateWWAPI.sendMessage(req2, new bm1(this));
                    } else {
                        h("未安装企业微信");
                    }
                }
                break;
            case R.id.ll_left_back /* 2131296815 */:
                onBackPressed();
                break;
            case R.id.tv_face /* 2131297195 */:
                Toast.makeText(this, "当前无法使用人脸进行双因子认证", 0).show();
                break;
            case R.id.tv_other /* 2131297230 */:
                new gj1(this, this, this.I, this.J, this.K, this.L).show();
                break;
            case R.id.tv_send /* 2131297270 */:
                if (this.l.getVisibility() == 0) {
                    if (!TextUtils.isEmpty(this.f4347e) && !TextUtils.isEmpty(this.h) && (str2 = this.g) != null && !"".equals(str2)) {
                        g(this.h);
                    }
                } else if (this.m.getVisibility() == 0 && !TextUtils.isEmpty(this.f) && !TextUtils.isEmpty(this.h) && (str = this.g) != null && !"".equals(str)) {
                    f(this.h);
                }
                new am1(this, 60000L, 1000L).start();
                break;
        }
    }

    public final void c(String str, String str2, String str3) {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("gid", str);
            jSONObject.put("callbackCode", str2);
            jSONObject.put("fedAuthCode", str3);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().o(ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new a());
    }

    public final void b(String str, String str2) {
        mj1.b().d(str, str2).enqueue(new b(str));
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0035  */
    @Override // supwisdom.zi1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c(java.lang.String r6) {
        /*
            Method dump skipped, instruction units count: 235
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.supwisdom.superapp.ui.activity.SecureCertificationActivity.c(java.lang.String):void");
    }
}
