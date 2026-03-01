package com.supwisdom.superapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.superapp.service.model.Response;
import com.supwisdom.zueb.R;
import io.dcloud.js.map.amap.util.ChString;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import retrofit2.Call;
import retrofit2.Callback;
import supwisdom.ct1;
import supwisdom.fj1;
import supwisdom.fn1;
import supwisdom.hj1;
import supwisdom.mj1;
import supwisdom.xs1;
import supwisdom.zi1;

/* JADX INFO: loaded from: classes2.dex */
public class SecurityMethodActivity extends WXBaseActivity implements View.OnClickListener, TextWatcher, TextView.OnEditorActionListener, zi1 {
    public fj1 A;
    public List<String> B = new ArrayList();
    public boolean C = false;
    public LinearLayoutCompat D;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f4357a;
    public LinearLayout b;
    public LinearLayout c;
    public LinearLayout d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public FrameLayout f4358e;
    public TextView f;
    public String g;
    public EditText h;
    public TextView i;
    public LinearLayoutCompat j;
    public TextView k;
    public TextView l;
    public TextView m;
    public String n;
    public String o;
    public String p;
    public boolean q;
    public int r;
    public String s;
    public String t;
    public String u;
    public TextView v;
    public LinearLayout w;
    public TextView x;
    public ImageView y;
    public EditText z;

    public class a implements Callback<Response<JsonObject>> {
        public a() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            SecurityMethodActivity securityMethodActivity = SecurityMethodActivity.this;
            securityMethodActivity.g(securityMethodActivity.getResources().getString(R.string.net_error));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            Response<JsonObject> responseBody = response.body();
            if (response.code() != 200 || responseBody == null) {
                SecurityMethodActivity securityMethodActivity = SecurityMethodActivity.this;
                securityMethodActivity.g(securityMethodActivity.getResources().getString(R.string.send_error));
                return;
            }
            fn1.H = responseBody.data.get("nonce").getAsString();
            if (responseBody.code == Response.CODE_SUCCESS) {
                SecurityMethodActivity.this.C = true;
            } else {
                SecurityMethodActivity.this.g(responseBody.message);
            }
        }
    }

    public class b implements Callback<Response<JsonObject>> {
        public b() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            Toast.makeText(SecurityMethodActivity.this, "网络出错", 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            Response<JsonObject> responseBody = response.body();
            if (response.code() != 200 || responseBody == null) {
                SecurityMethodActivity securityMethodActivity = SecurityMethodActivity.this;
                Toast.makeText(securityMethodActivity, securityMethodActivity.getResources().getString(R.string.deal_error), 0).show();
                return;
            }
            fn1.H = responseBody.data.get("nonce").getAsString();
            if (responseBody.code == Response.CODE_SUCCESS) {
                SecurityMethodActivity.this.o();
            } else {
                Toast.makeText(SecurityMethodActivity.this, responseBody.message, 0).show();
            }
        }
    }

    public class c implements Callback<Response<JSONObject>> {
        public c() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Toast.makeText(SecurityMethodActivity.this, "网络错误", 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null || response.body().code != 0) {
                Toast.makeText(SecurityMethodActivity.this, "验证码发送失败", 0).show();
            } else if (response.body().data != null) {
                fn1.H = response.body().data.getString("nonce");
                SecurityMethodActivity.this.C = true;
            }
        }
    }

    public class d implements Callback<Response<JsonObject>> {
        public d() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            Toast.makeText(SecurityMethodActivity.this, "网络错误", 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            Response<JsonObject> responseBody = response.body();
            if (response.code() != 200 || responseBody == null) {
                SecurityMethodActivity securityMethodActivity = SecurityMethodActivity.this;
                Toast.makeText(securityMethodActivity, securityMethodActivity.getResources().getString(R.string.deal_error), 0).show();
                return;
            }
            fn1.H = responseBody.data.get("nonce").getAsString();
            if (responseBody.code == Response.CODE_SUCCESS) {
                SecurityMethodActivity.this.o();
            } else {
                Toast.makeText(SecurityMethodActivity.this, responseBody.message, 0).show();
            }
        }
    }

    public class e implements Callback<Response<JsonObject>> {
        public e() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            Toast.makeText(SecurityMethodActivity.this, "网络错误", 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            if (response.code() != 200 || response.body() == null || response.body().data == null) {
                Toast.makeText(SecurityMethodActivity.this, "验证失败", 0).show();
                return;
            }
            String string = response.body().data.get("message").toString();
            Toast.makeText(SecurityMethodActivity.this, string, 0).show();
            String asString = response.body().data.get("nonce").getAsString();
            if (asString != null) {
                fn1.H = asString;
            }
            if (string.contains("验证成功")) {
                SecurityMethodActivity.this.o();
            }
        }
    }

    public class f extends CountDownTimer {
        public f(long j, long j2) {
            super(j, j2);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            SecurityMethodActivity.this.i.setText(SecurityMethodActivity.this.getResources().getString(R.string.string_get_code));
            SecurityMethodActivity.this.i.setTextColor(SecurityMethodActivity.this.getResources().getColor(R.color.color_ED663F));
            SecurityMethodActivity.this.i.setClickable(true);
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            SecurityMethodActivity.this.i.setText("已发送 " + (j / 1000));
            SecurityMethodActivity.this.i.setTextColor(SecurityMethodActivity.this.getResources().getColor(R.color.color_6F737A));
            SecurityMethodActivity.this.i.setClickable(false);
        }
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        if (this.C) {
            this.f4358e.setSelected(true);
        }
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // supwisdom.zi1
    public void c(String str) {
        if (str.equals("phone")) {
            this.j.setVisibility(0);
            this.c.setVisibility(0);
            this.b.setVisibility(8);
            this.w.setVisibility(8);
            this.d.setVisibility(8);
            this.m.setVisibility(0);
            this.l.setText(this.n);
            return;
        }
        if (str.equals("email")) {
            this.j.setVisibility(0);
            this.b.setVisibility(0);
            this.c.setVisibility(8);
            this.w.setVisibility(8);
            this.d.setVisibility(8);
            this.m.setVisibility(8);
            this.l.setText(this.o);
            return;
        }
        if (str.equals("question")) {
            List<String> list = this.B;
            if (list != null && list.size() > 0) {
                this.x.setText(this.B.get(0));
            }
            this.d.setVisibility(0);
            this.b.setVisibility(8);
            this.w.setVisibility(8);
            this.c.setVisibility(8);
            this.j.setVisibility(8);
        }
    }

    public final void e(String str) {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
            jSONObject.put("code", str);
        } catch (JSONException unused) {
        }
        mj1.b().f(fn1.w, ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new d());
    }

    public /* synthetic */ void f(String str) {
        this.A.dismiss();
        this.x.setText(str);
    }

    public void g(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this, str, 0).show();
    }

    public final void l() {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
            jSONObject.put("question", this.x.getText().toString());
            jSONObject.put("answer", this.z.getText().toString());
        } catch (JSONException unused) {
        }
        mj1.b().c(fn1.w, ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new e());
    }

    public final void m() {
        new f(60000L, 1000L).start();
    }

    public void n() {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
            if (this.f4357a.equals("phone")) {
                jSONObject.put("checkType", "mobile");
            } else {
                jSONObject.put("checkType", "emailAddress");
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().r(fn1.w, ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new a());
    }

    public final void o() {
        Intent intent;
        if (this.q || this.r == 0) {
            intent = new Intent(this, (Class<?>) InformationPerfectionActivity.class);
            intent.putExtra("account", this.t);
            intent.putExtra("passwordStatus", this.r);
            intent.putExtra("monitorConfig", this.g);
        } else {
            intent = new Intent(this, (Class<?>) MonitorChangePassword.class);
            intent.putExtra("type", "");
            intent.putExtra("nonce", fn1.H);
        }
        intent.putExtra("userToken", this.s);
        intent.putExtra("username", this.p);
        startActivity(intent);
        finish();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backBt /* 2131296356 */:
                onBackPressed();
                break;
            case R.id.iv_change_question /* 2131296719 */:
                fj1 fj1Var = new fj1(this, this.B);
                this.A = fj1Var;
                fj1Var.show();
                this.A.a(new fj1.a() { // from class: supwisdom.ml1
                    @Override // supwisdom.fj1.a
                    public final void a(String str) {
                        this.f8405a.f(str);
                    }
                });
                break;
            case R.id.nextTipBtn /* 2131296890 */:
                if (this.f4358e.isSelected()) {
                    r();
                    break;
                }
                break;
            case R.id.tv_change_type /* 2131297180 */:
                new hj1(this, this, this.g).show();
                break;
            case R.id.tv_send /* 2131297270 */:
                if (this.c.getVisibility() == 0) {
                    if (this.f4357a.equals("phone")) {
                        n();
                    }
                } else if (this.b.getVisibility() == 0) {
                    n();
                } else if (this.w.getVisibility() == 0) {
                    s();
                }
                m();
                break;
        }
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        WXBaseActivity.transparencyBar(this);
        WXBaseActivity.setLightStatusBar(this, true);
        setContentView(R.layout.activity_security);
        this.f4357a = getIntent().getStringExtra("verifyType");
        this.g = getIntent().getStringExtra("monitorConfig");
        this.p = getIntent().getStringExtra("username");
        getIntent().getBooleanExtra("userCompletedCheckEnabled", false);
        this.q = getIntent().getBooleanExtra("userNonCompleted", false);
        this.r = getIntent().getIntExtra("passwordStatus", -1);
        this.s = getIntent().getStringExtra("userToken");
        this.t = getIntent().getStringExtra("nameEt");
        this.j = (LinearLayoutCompat) findViewById(R.id.ll_security);
        this.c = (LinearLayout) findViewById(R.id.phoneVerifyLayout);
        this.b = (LinearLayout) findViewById(R.id.emailVerifyLayout);
        this.w = (LinearLayout) findViewById(R.id.phoneMobilePre);
        this.d = (LinearLayout) findViewById(R.id.ll_question);
        this.f = (TextView) findViewById(R.id.tv_change_type);
        this.h = (EditText) findViewById(R.id.et_code);
        this.i = (TextView) findViewById(R.id.tv_send);
        this.m = (TextView) findViewById(R.id.tv_area);
        this.l = (TextView) findViewById(R.id.tv_secure_num);
        this.f4358e = (FrameLayout) findViewById(R.id.nextTipBtn);
        this.k = (TextView) findViewById(R.id.nextTipTxt);
        this.x = (TextView) findViewById(R.id.tv_question);
        this.y = (ImageView) findViewById(R.id.iv_change_question);
        this.z = (EditText) findViewById(R.id.et_answer);
        this.D = (LinearLayoutCompat) findViewById(R.id.ll_identity);
        this.v = (TextView) findViewById(R.id.backBt);
        this.k.setText(ChString.NextStep);
        this.d.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.h.addTextChangedListener(this);
        this.z.addTextChangedListener(this);
        this.f4358e.setOnClickListener(this);
        this.v.setOnClickListener(this);
        this.y.setOnClickListener(this);
        q();
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return false;
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void p() {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("code", this.h.getText().toString().trim());
            jSONObject.put("nonce", fn1.H);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().n(fn1.w, ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new b());
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void q() {
        try {
            if (this.g != null) {
                org.json.JSONObject jSONObject = new org.json.JSONObject(this.g);
                this.n = jSONObject.getJSONObject("data").getJSONObject("completed").getJSONObject("securityMobile").getString(com.umeng.commonsdk.internal.utils.f.f5404a);
                this.o = jSONObject.getJSONObject("data").getJSONObject("completed").getJSONObject("securityEmailAddress").getString(com.umeng.commonsdk.internal.utils.f.f5404a);
                jSONObject.getJSONObject("data").getString("nonce");
                this.u = jSONObject.getJSONObject("data").getJSONObject("safetyCertificate").getString("preMobile");
                jSONObject.getJSONObject("data").getJSONObject("completed").getJSONObject("securityQuestion").getJSONArray("questionList");
                jSONObject.getJSONObject("data").getJSONObject("completed").getJSONObject("securityQuestion").getJSONObject(com.umeng.commonsdk.internal.utils.f.f5404a);
                jSONObject.getJSONObject("data").getJSONObject("completed").getJSONObject("securityQuestion").getJSONObject(com.umeng.commonsdk.internal.utils.f.f5404a);
                a(jSONObject.getJSONObject("data").getJSONObject("completed").getJSONObject("securityQuestion").getJSONObject(com.umeng.commonsdk.internal.utils.f.f5404a));
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        String str = this.f4357a;
        byte b2 = -1;
        switch (str.hashCode()) {
            case -1823969346:
                if (str.equals("phone_safe")) {
                    b2 = 3;
                }
                break;
            case -1165870106:
                if (str.equals("question")) {
                    b2 = 2;
                }
                break;
            case 96619420:
                if (str.equals("email")) {
                    b2 = 1;
                }
                break;
            case 106642798:
                if (str.equals("phone")) {
                    b2 = 0;
                }
                break;
            case 562482798:
                if (str.equals("identity_safe")) {
                    b2 = 4;
                }
                break;
        }
        if (b2 == 0) {
            this.c.setVisibility(0);
            this.b.setVisibility(8);
            this.w.setVisibility(8);
            this.d.setVisibility(8);
            this.m.setVisibility(0);
            this.l.setText(this.n);
            return;
        }
        if (b2 == 1) {
            this.b.setVisibility(0);
            this.c.setVisibility(8);
            this.w.setVisibility(8);
            this.d.setVisibility(8);
            this.m.setVisibility(8);
            this.l.setText(this.o);
            return;
        }
        if (b2 == 2) {
            List<String> list = this.B;
            if (list != null && list.size() > 0) {
                this.x.setText(this.B.get(0));
            }
            this.d.setVisibility(0);
            this.b.setVisibility(8);
            this.w.setVisibility(8);
            this.c.setVisibility(8);
            this.j.setVisibility(8);
            this.C = true;
            return;
        }
        if (b2 == 3) {
            this.w.setVisibility(0);
            this.c.setVisibility(8);
            this.b.setVisibility(8);
            this.d.setVisibility(8);
            this.m.setVisibility(0);
            this.l.setText(this.u);
            return;
        }
        if (b2 != 4) {
            return;
        }
        this.w.setVisibility(8);
        this.c.setVisibility(8);
        this.b.setVisibility(8);
        this.d.setVisibility(8);
        this.m.setVisibility(8);
        this.D.setVisibility(0);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void r() {
        /*
            r5 = this;
            java.lang.String r0 = r5.f4357a
            int r1 = r0.hashCode()
            r2 = 3
            r3 = 2
            r4 = 1
            switch(r1) {
                case -1823969346: goto L2b;
                case -1165870106: goto L21;
                case 96619420: goto L17;
                case 106642798: goto Ld;
                default: goto Lc;
            }
        Lc:
            goto L35
        Ld:
            java.lang.String r1 = "phone"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L35
            r0 = 1
            goto L36
        L17:
            java.lang.String r1 = "email"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L35
            r0 = 2
            goto L36
        L21:
            java.lang.String r1 = "question"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L35
            r0 = 3
            goto L36
        L2b:
            java.lang.String r1 = "phone_safe"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L35
            r0 = 0
            goto L36
        L35:
            r0 = -1
        L36:
            if (r0 == 0) goto L47
            if (r0 == r4) goto L43
            if (r0 == r3) goto L43
            if (r0 == r2) goto L3f
            goto L54
        L3f:
            r5.l()
            goto L54
        L43:
            r5.p()
            goto L54
        L47:
            android.widget.EditText r0 = r5.h
            android.text.Editable r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            r5.e(r0)
        L54:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.supwisdom.superapp.ui.activity.SecurityMethodActivity.r():void");
    }

    public final void s() {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
        } catch (JSONException unused) {
        }
        mj1.b().p(fn1.w, ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new c());
    }

    public final List<String> a(org.json.JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                this.B.add(jSONObject.getString("question1"));
                if (jSONObject.length() == 4) {
                    this.B.add(jSONObject.getString("question2"));
                }
                if (jSONObject.length() == 6) {
                    this.B.add(jSONObject.getString("question3"));
                }
                if (jSONObject.length() == 8) {
                    this.B.add(jSONObject.getString("question4"));
                }
                if (jSONObject.length() == 10) {
                    this.B.add(jSONObject.getString("question5"));
                }
                if (jSONObject.length() == 12) {
                    this.B.add(jSONObject.getString("question6"));
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return this.B;
    }
}
