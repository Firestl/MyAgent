package com.supwisdom.superapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.JsonObject;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.superapp.service.model.Response;
import com.supwisdom.zueb.R;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import supwisdom.ct1;
import supwisdom.fn1;
import supwisdom.jj1;
import supwisdom.lm1;
import supwisdom.mj1;
import supwisdom.xs1;

/* JADX INFO: loaded from: classes2.dex */
public class BindPhoneActivity extends WXBaseActivity implements View.OnClickListener, TextWatcher, TextView.OnEditorActionListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public EditText f4042a;
    public FrameLayout b;
    public ProgressBar c;
    public TextView d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f4043e;
    public TextView f;
    public TextView g;
    public TextView h;
    public TextView i;
    public String j;
    public String k;
    public TextView l;
    public LinearLayout m;
    public LinearLayout n;
    public ImageButton o;
    public EditText p;
    public TextView q;
    public String r;
    public Context s;
    public TextView t;

    public class a implements Callback<Response<JsonObject>> {
        public a() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            BindPhoneActivity bindPhoneActivity = BindPhoneActivity.this;
            bindPhoneActivity.e(bindPhoneActivity.getResources().getString(R.string.net_error));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            Response<JsonObject> responseBody = response.body();
            if (response.code() != 200 || responseBody == null) {
                BindPhoneActivity bindPhoneActivity = BindPhoneActivity.this;
                bindPhoneActivity.e(bindPhoneActivity.getResources().getString(R.string.phone_error));
                return;
            }
            JsonObject jsonObject = responseBody.data;
            fn1.H = jsonObject.get("nonce").getAsString();
            boolean asBoolean = jsonObject.get("exists").getAsBoolean();
            if (responseBody.code != Response.CODE_SUCCESS || asBoolean) {
                BindPhoneActivity.this.e(responseBody.message);
            } else {
                BindPhoneActivity.this.n();
            }
        }
    }

    public class b implements Callback<Response<JsonObject>> {
        public b() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            BindPhoneActivity bindPhoneActivity = BindPhoneActivity.this;
            bindPhoneActivity.e(bindPhoneActivity.getResources().getString(R.string.net_error));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            Response<JsonObject> responseBody = response.body();
            if (response.code() != 200 || responseBody == null) {
                BindPhoneActivity bindPhoneActivity = BindPhoneActivity.this;
                bindPhoneActivity.e(bindPhoneActivity.getResources().getString(R.string.send_error));
                return;
            }
            fn1.H = responseBody.data.get("nonce").getAsString();
            if (responseBody.code != Response.CODE_SUCCESS) {
                BindPhoneActivity.this.e(responseBody.message);
                return;
            }
            Intent intent = new Intent(BindPhoneActivity.this, (Class<?>) InputVerifyCodeActivity.class);
            intent.putExtra("phoneNumber", BindPhoneActivity.this.f4042a.getText().toString());
            intent.putExtra("type", "bind");
            BindPhoneActivity.this.startActivity(intent);
        }
    }

    public class c implements Callback<Response<JsonObject>> {
        public c() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            Toast.makeText(BindPhoneActivity.this.s, "网络错误", 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            if (response.code() != 200 && (response.code() != 0 || response.body() == null || response.body().data == null)) {
                Toast.makeText(BindPhoneActivity.this.s, "激活失败", 0).show();
                return;
            }
            JsonObject jsonObject = response.body().data;
            try {
                if (jsonObject.get("exists").getAsBoolean()) {
                    Toast.makeText(BindPhoneActivity.this.s, jsonObject.get("message").getAsString(), 0).show();
                    return;
                }
                if (jsonObject.get("nonce") != null) {
                    fn1.H = jsonObject.get("nonce").getAsString();
                }
                BindPhoneActivity.this.o();
            } catch (Exception unused) {
                Toast.makeText(BindPhoneActivity.this.s, "激活错误", 0).show();
            }
        }
    }

    public class d implements Callback<Response<JsonObject>> {
        public d() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            Toast.makeText(BindPhoneActivity.this.s, "网络错误", 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            if (response.code() != 200 && (response.code() != 0 || response.body() == null || response.body().data == null)) {
                Toast.makeText(BindPhoneActivity.this.s, "发送失败", 0).show();
                return;
            }
            JsonObject jsonObject = response.body().data;
            if (jsonObject.get("nonce") != null) {
                fn1.H = jsonObject.get("nonce").getAsString();
            }
            if (response.body().code != Response.CODE_SUCCESS) {
                Toast.makeText(BindPhoneActivity.this.s, response.body().data.get("message").getAsString(), 0).show();
                return;
            }
            Intent intent = new Intent(BindPhoneActivity.this, (Class<?>) InputVerifyCodeActivity.class);
            intent.putExtra("email", BindPhoneActivity.this.p.getText().toString());
            intent.putExtra("type", "bind");
            BindPhoneActivity.this.startActivity(intent);
        }
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        if (this.f4042a.getText().toString().trim().equals("") || this.f4042a.getText().toString().trim().length() < 11) {
            this.b.setSelected(false);
        } else {
            this.b.setSelected(true);
        }
        this.q.setSelected(!this.p.getText().toString().trim().equals(""));
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void e(String str) {
        this.d.setVisibility(0);
        this.c.setVisibility(8);
        this.f4042a.setEnabled(true);
        this.b.setClickable(true);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this, str, 0).show();
    }

    public final void l() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
            jSONObject.put("emailAddress", this.p.getText().toString());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().q(ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new c());
    }

    public void m() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
            jSONObject.put("mobile", this.f4042a.getText().toString().trim());
        } catch (JSONException e2) {
            lm1.a(e2);
            e2.printStackTrace();
        }
        mj1.b().i(ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new a());
    }

    public final void n() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
            jSONObject.put("mobile", this.f4042a.getText().toString().trim());
        } catch (JSONException e2) {
            lm1.a(e2);
            e2.printStackTrace();
        }
        mj1.b().x(ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new b());
    }

    public final void o() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
            jSONObject.put("emailAddress", this.p.getText().toString());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().k(ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new d());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.nextTipBtn) {
            this.d.setVisibility(8);
            this.c.setVisibility(0);
            this.f4042a.setEnabled(false);
            this.b.setClickable(false);
            if (this.f4042a.getText() == null || this.f4042a.getText().toString().trim().length() < 11) {
                e("请输入合法的手机号码");
                return;
            } else {
                m();
                return;
            }
        }
        if (view.getId() == R.id.backBt) {
            finish();
            return;
        }
        if (view.getId() == R.id.emailVerifyTxt) {
            this.m.setVisibility(0);
            this.n.setVisibility(8);
            return;
        }
        if (view.getId() == R.id.loginTxt1 || view.getId() == R.id.loginTxt2) {
            startActivity(new Intent(this, (Class<?>) LoginActivity.class));
            return;
        }
        if (view.getId() == R.id.phoneVerifyTxt) {
            this.m.setVisibility(8);
            this.n.setVisibility(0);
            return;
        }
        if (view.getId() == R.id.accountClearBtn) {
            this.f4042a.setText("");
            return;
        }
        if (view.getId() == R.id.tv_email) {
            l();
            return;
        }
        if (id == R.id.tv_change_type) {
            if (this.m.getVisibility() == 0) {
                this.m.setVisibility(8);
                this.n.setVisibility(0);
            } else {
                this.m.setVisibility(0);
                this.n.setVisibility(8);
            }
        }
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.trackPageName = getResources().getString(R.string.bindPhone_activity);
        WXBaseActivity.transparencyBar(this);
        WXBaseActivity.setLightStatusBar(this, true);
        setContentView(R.layout.layout_bindphone);
        this.s = this;
        this.f4042a = (EditText) findViewById(R.id.phoneTxt);
        this.o = (ImageButton) findViewById(R.id.accountClearBtn);
        this.l = (TextView) findViewById(R.id.backBt);
        this.f4043e = (TextView) findViewById(R.id.emailVerifyTxt);
        this.f = (TextView) findViewById(R.id.loginTxt1);
        this.i = (TextView) findViewById(R.id.bindPhoneTitleTv);
        this.g = (TextView) findViewById(R.id.loginTxt2);
        this.j = getIntent().getStringExtra("phoneNumber");
        this.k = getIntent().getStringExtra("verifyType");
        this.r = getIntent().getStringExtra("type");
        this.b = (FrameLayout) findViewById(R.id.nextTipBtn);
        this.d = (TextView) findViewById(R.id.nextTipTxt);
        this.c = (ProgressBar) findViewById(R.id.loading);
        this.m = (LinearLayout) findViewById(R.id.emailVerifyLayout);
        this.n = (LinearLayout) findViewById(R.id.phoneVerifyLayout);
        this.h = (TextView) findViewById(R.id.phoneVerifyTxt);
        this.p = (EditText) findViewById(R.id.et_email);
        this.q = (TextView) findViewById(R.id.tv_email);
        this.t = (TextView) findViewById(R.id.tv_change_type);
        this.d.setText("发送验证码");
        this.b.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.f4043e.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.o.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.q.setOnClickListener(this);
        this.t.setOnClickListener(this);
        this.f4042a.setText(this.j);
        this.b.setSelected(true);
        this.f4042a.addTextChangedListener(this);
        this.p.addTextChangedListener(this);
        if (this.k.equals(jj1.j)) {
            this.n.setVisibility(0);
            this.m.setVisibility(8);
            return;
        }
        if (!this.k.equals("bind")) {
            this.n.setVisibility(8);
            this.m.setVisibility(0);
            return;
        }
        if (getIntent().getBooleanExtra("open_another", false)) {
            this.t.setVisibility(0);
        }
        if (TextUtils.isEmpty(this.r) || !this.r.equals("email")) {
            this.i.setText("绑定手机号");
        } else {
            this.m.setVisibility(0);
            this.n.setVisibility(8);
        }
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (!TextUtils.isEmpty(this.f4042a.getText().toString().trim())) {
            return false;
        }
        m();
        return false;
    }

    @Override // android.app.Activity
    public void onRestart() {
        super.onRestart();
        e("");
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
