package com.supwisdom.superapp.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.zueb.R;
import com.umeng.commonsdk.internal.utils.f;
import io.dcloud.js.map.amap.util.ChString;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import supwisdom.ct1;
import supwisdom.et1;
import supwisdom.fn1;
import supwisdom.hj1;
import supwisdom.mi1;
import supwisdom.mj1;
import supwisdom.xs1;
import supwisdom.zi1;

/* JADX INFO: loaded from: classes2.dex */
public class BasicInformationActivity extends WXBaseActivity implements TextWatcher, zi1, View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public EditText f4028a;
    public EditText b;
    public EditText c;
    public FrameLayout d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f4029e;
    public ProgressBar f;
    public boolean g;
    public boolean h;
    public boolean i;
    public String j;
    public String k;
    public boolean l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public TextView r;
    public String s;
    public boolean t;
    public boolean u;
    public int v;
    public String w;
    public String x;
    public boolean y;
    public String z;

    public class a implements Callback<et1> {
        public a() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<et1> call, Throwable th) {
            BasicInformationActivity basicInformationActivity = BasicInformationActivity.this;
            basicInformationActivity.f(basicInformationActivity.getResources().getString(R.string.net_error));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<et1> call, Response<et1> response) {
            try {
                JSONObject jSONObject = new JSONObject(response.body().string());
                int i = jSONObject.getInt("code");
                String string = jSONObject.getString("message");
                fn1.H = jSONObject.getJSONObject("data").getString("nonce");
                if (i == 0) {
                    BasicInformationActivity.this.o();
                    BasicInformationActivity.this.f.setVisibility(8);
                    BasicInformationActivity.this.f4029e.setVisibility(0);
                    BasicInformationActivity.this.d.setClickable(true);
                } else {
                    BasicInformationActivity.this.f(string);
                }
            } catch (Exception e2) {
                if (response.code() == 500) {
                    BasicInformationActivity basicInformationActivity = BasicInformationActivity.this;
                    basicInformationActivity.e(basicInformationActivity.k);
                } else {
                    BasicInformationActivity.this.f(e2.getMessage());
                    e2.printStackTrace();
                }
            }
        }
    }

    public class b implements Callback<et1> {
        public b() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<et1> call, Throwable th) {
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<et1> call, Response<et1> response) {
            try {
                JSONObject jSONObject = new JSONObject(response.body().string());
                if (jSONObject.getInt("code") == 0) {
                    BasicInformationActivity.this.s = jSONObject.toString();
                    fn1.H = jSONObject.getJSONObject("data").getString("nonce");
                    BasicInformationActivity.this.l();
                }
            } catch (Exception e2) {
                BasicInformationActivity.this.f("初始化失败");
                e2.printStackTrace();
            }
        }
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        boolean z = this.b.getVisibility() == 0 && TextUtils.isEmpty(this.b.getText().toString().trim());
        if (!TextUtils.isEmpty(this.f4028a.getText().toString().trim()) && !z) {
            if (this.c.getVisibility() == 0 && TextUtils.isEmpty(this.c.getText().toString().trim())) {
                this.d.setSelected(false);
                return;
            } else {
                this.d.setSelected(true);
                return;
            }
        }
        this.d.setSelected(false);
        if (TextUtils.isEmpty(this.c.getText().toString().trim()) || z) {
            this.d.setSelected(false);
        } else {
            this.d.setSelected(true);
        }
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void l() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
            jSONObject.put("name", this.b.getText().toString().trim());
            jSONObject.put("accountName", this.f4028a.getText().toString().trim());
            jSONObject.put("identityNo", this.c.getText().toString().trim());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().j(this.k, ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new a());
    }

    public final void m() {
        this.s = getIntent().getStringExtra("monitorConfig");
        this.k = getIntent().getStringExtra("userToken");
        this.v = getIntent().getIntExtra("passwordStatus", -1);
        this.t = getIntent().getBooleanExtra("userCompletedCheckEnabled", true);
        this.u = getIntent().getBooleanExtra("userNonCompleted", false);
        try {
            if (this.s != null) {
                JSONObject jSONObject = new JSONObject(this.s);
                jSONObject.getJSONObject("data").getJSONObject("safetyCertificateSettingConfig").getBoolean("basicEnabled");
                this.g = jSONObject.getJSONObject("data").getJSONObject("safetyCertificateSettingConfig").getBoolean("basicIdentityNoEnabled");
                this.h = jSONObject.getJSONObject("data").getJSONObject("safetyCertificateSettingConfig").getBoolean("basicAccountEnabled");
                this.i = jSONObject.getJSONObject("data").getJSONObject("safetyCertificateSettingConfig").getBoolean("basicNameEnabled");
                this.j = jSONObject.getJSONObject("data").getString("nonce");
                this.l = jSONObject.getJSONObject("data").getJSONObject("safetyCertificateSettingConfig").getBoolean("identityEnabled");
                this.m = jSONObject.getJSONObject("data").getJSONObject("safetyCertificateSettingConfig").getBoolean("identitySecureMobileEnabled");
                this.n = jSONObject.getJSONObject("data").getJSONObject("safetyCertificateSettingConfig").getBoolean("identitySecureEmailAddressEnabled");
                this.o = jSONObject.getJSONObject("data").getJSONObject("safetyCertificateSettingConfig").getBoolean("identitySecureQuestionEnabled");
                jSONObject.getJSONObject("data").getJSONObject("safetyCertificateSettingConfig").getBoolean("identitySecureAppPushEnabled");
                this.p = jSONObject.getJSONObject("data").getJSONObject("safetyCertificateSettingConfig").getBoolean("identityRealNameFaceverifyEnabled");
                jSONObject.getJSONObject("data").getJSONObject("safetyCertificateSettingConfig").getBoolean("identityRealNameIdentityPicEnabled");
                this.q = jSONObject.getJSONObject("data").getJSONObject("safetyCertificateSettingConfig").getBoolean("identityRealNamePreMobileEenabled");
                this.w = jSONObject.getJSONObject("data").getJSONObject("completed").getJSONObject("securityMobile").getString(f.f5404a);
                this.x = jSONObject.getJSONObject("data").getJSONObject("completed").getJSONObject("securityEmailAddress").getString(f.f5404a);
                this.y = jSONObject.getJSONObject("data").getJSONObject("completed").getJSONObject("securityQuestion").getBoolean("completed");
                this.z = jSONObject.getJSONObject("data").getJSONObject("safetyCertificate").getString("preMobile");
                if (!this.i) {
                    this.b.setVisibility(8);
                }
                if (!this.g) {
                    this.c.setVisibility(8);
                }
                if (this.h) {
                    return;
                }
                this.f4028a.setVisibility(8);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public final void n() {
        this.r = (TextView) findViewById(R.id.infoBackBtn);
        this.f4028a = (EditText) findViewById(R.id.accountNameEt);
        this.b = (EditText) findViewById(R.id.nameEt);
        this.c = (EditText) findViewById(R.id.identifyNumberEt);
        this.d = (FrameLayout) findViewById(R.id.nextTipBtn);
        this.f4029e = (TextView) findViewById(R.id.nextTipTxt);
        this.f = (ProgressBar) findViewById(R.id.loading);
        this.f4028a.addTextChangedListener(this);
        this.b.addTextChangedListener(this);
        this.c.addTextChangedListener(this);
        new mi1(this, this);
        this.f4029e.setText(ChString.NextStep);
        this.d.setOnClickListener(this);
        this.r.setOnClickListener(this);
    }

    public final void o() {
        Intent intent;
        if (!this.l) {
            if (this.t || this.v == 0) {
                intent = new Intent(this, (Class<?>) InformationPerfectionActivity.class);
                intent.putExtra("account", this.b.getText().toString());
                intent.putExtra("passwordStatus", this.v);
                intent.putExtra("monitorConfig", this.s);
            } else {
                intent = new Intent(this, (Class<?>) MonitorChangePassword.class);
                intent.putExtra("type", "1");
                intent.putExtra("nonce", this.j);
                intent.putExtra("monitorConfig", this.s);
                intent.putExtra("userCompletedCheckEnabled", this.t);
                intent.putExtra("passwordStatus", this.v);
                intent.putExtra("userToken", this.k);
                intent.putExtra("nameEt", this.b.getText().toString());
            }
            intent.putExtra("username", this.f4028a.getText().toString().trim());
            intent.putExtra("userToken", this.k);
            startActivity(intent);
            return;
        }
        if ((this.m && !this.w.equals(com.igexin.push.core.b.m)) || ((this.n && !this.x.equals(com.igexin.push.core.b.m)) || ((this.o && this.y) || ((this.q && !this.z.equals(com.igexin.push.core.b.m)) || this.p)))) {
            p();
            return;
        }
        Intent intent2 = new Intent(this, (Class<?>) InformationPerfectionActivity.class);
        intent2.putExtra("account", this.b.getText().toString());
        intent2.putExtra("passwordStatus", this.v);
        intent2.putExtra("username", this.f4028a.getText().toString().trim());
        intent2.putExtra("userToken", this.k);
        intent2.putExtra("monitorConfig", this.s);
        startActivity(intent2);
    }

    @Override // android.view.View.OnClickListener
    @SuppressLint({"NonConstantResourceId"})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.infoBackBtn) {
            finish();
            return;
        }
        if (id == R.id.nextTipBtn && this.d.isSelected()) {
            this.b.setEnabled(false);
            this.f4028a.setEnabled(false);
            this.c.setEnabled(false);
            this.f.setVisibility(0);
            this.f4029e.setVisibility(8);
            this.d.setClickable(false);
            l();
        }
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        WXBaseActivity.transparencyBar(this);
        WXBaseActivity.setLightStatusBar(this, true);
        setContentView(R.layout.activity_basic_information);
        n();
        m();
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void p() {
        new hj1(this, this, this.s).show();
    }

    public final void e(String str) {
        mj1.b().h(fn1.w).enqueue(new b());
    }

    public final void f(String str) {
        this.b.setEnabled(true);
        this.f4028a.setEnabled(true);
        this.c.setEnabled(true);
        this.f.setVisibility(8);
        this.f4029e.setVisibility(0);
        this.d.setClickable(true);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this, str, 0).show();
    }

    @Override // supwisdom.zi1
    public void c(String str) {
        Intent intent = new Intent(this, (Class<?>) SecurityMethodActivity.class);
        if (str.equals("phone") || str.equals("email") || str.equals("question") || str.equals("phone_safe") || str.equals("identity_safe")) {
            intent.putExtra("verifyType", str);
        }
        intent.putExtra("username", this.f4028a.getText().toString().trim());
        intent.putExtra("monitorConfig", this.s);
        intent.putExtra("userCompletedCheckEnabled", this.t);
        intent.putExtra("userNonCompleted", this.u);
        intent.putExtra("passwordStatus", this.v);
        intent.putExtra("userToken", this.k);
        intent.putExtra("nameEt", this.b.getText().toString());
        startActivity(intent);
    }
}
