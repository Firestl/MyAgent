package com.supwisdom.superapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.superapp.service.model.Response;
import com.supwisdom.zueb.R;
import com.taobao.weex.common.Constants;
import org.json.JSONException;
import retrofit2.Call;
import retrofit2.Callback;
import supwisdom.ct1;
import supwisdom.fn1;
import supwisdom.lm1;
import supwisdom.mj1;
import supwisdom.xs1;

/* JADX INFO: loaded from: classes2.dex */
public class MonitorChangePassword extends WXBaseActivity implements View.OnClickListener, TextWatcher, TextView.OnEditorActionListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public EditText f4269a;
    public EditText b;
    public View c;
    public View d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public View f4270e;
    public View f;
    public TextView g;
    public TextView h;
    public ProgressBar i;
    public String j;
    public TextView k;
    public TextView l;
    public String m;
    public LinearLayout n;

    public class a implements Callback<Response<JsonObject>> {
        public a() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            MonitorChangePassword.this.e("网络出错");
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            String str;
            Response<JsonObject> responseBody = response.body();
            if (response.code() != 200 || responseBody == null) {
                MonitorChangePassword.this.e((responseBody == null || (str = responseBody.message) == null || str.trim().equals("")) ? "处理出错" : responseBody.message);
                return;
            }
            JsonObject jsonObject = responseBody.data;
            if (responseBody.code != Response.CODE_SUCCESS) {
                MonitorChangePassword.this.e(responseBody.message);
            } else if (jsonObject != null) {
                MonitorChangePassword.this.k.setText(jsonObject.get("tips").isJsonNull() ? "" : jsonObject.get("tips").getAsString());
            }
        }
    }

    public class b implements Callback<Response<JsonObject>> {
        public b() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            Response<JsonObject> responseBody = response.body();
            if (response.code() != 200 || responseBody == null) {
                return;
            }
            MonitorChangePassword.this.l();
        }
    }

    public class c implements Callback<Response<JSONObject>> {
        public c() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            MonitorChangePassword.this.e("网络出错");
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            String str;
            Response<JSONObject> responseBody = response.body();
            if (response.code() != 200 || responseBody == null) {
                MonitorChangePassword.this.e((responseBody == null || (str = responseBody.message) == null || str.trim().equals("")) ? "修改失败" : responseBody.message);
                return;
            }
            JSONObject jSONObject = responseBody.data;
            fn1.H = TextUtils.isEmpty(jSONObject.getString("nonce")) ? fn1.H : jSONObject.getString("nonce");
            if (responseBody.code != Response.CODE_SUCCESS) {
                MonitorChangePassword.this.e(responseBody.message);
                return;
            }
            Toast.makeText(MonitorChangePassword.this, "重置成功!", 0).show();
            Intent intent = new Intent(MonitorChangePassword.this, (Class<?>) LoginActivity.class);
            intent.setFlags(268468224);
            MonitorChangePassword.this.startActivity(intent);
        }
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        if (this.f4269a.getText().toString().trim().equals("") || this.b.getText().toString().trim().equals("") || this.f4269a.getText().toString().trim().equals("")) {
            this.c.setSelected(false);
            this.g.setSelected(false);
        } else {
            this.c.setSelected(true);
            this.g.setSelected(true);
        }
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void e(String str) {
        this.i.setVisibility(8);
        this.f4269a.setEnabled(true);
        this.b.setEnabled(true);
        this.g.setVisibility(0);
        this.c.setClickable(true);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this, str, 0).show();
    }

    public final void l() {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
            jSONObject.put("confirmPassword", this.f4269a.getText().toString().trim());
            jSONObject.put("newPassword", this.b.getText().toString().trim());
        } catch (JSONException e2) {
            lm1.a(e2);
            e2.printStackTrace();
        }
        mj1.b().m(fn1.w, ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new c());
    }

    public void m() {
        if (this.c.isSelected()) {
            this.i.setVisibility(0);
            this.g.setVisibility(8);
            this.f4269a.setEnabled(false);
            this.b.setEnabled(false);
            this.c.setClickable(false);
            if (this.f4269a.getText().toString().trim().equals(this.b.getText().toString().trim())) {
                o();
            } else {
                e(getResources().getString(R.string.reset));
            }
        }
    }

    public final void n() {
        mj1.b().a().enqueue(new a());
    }

    public final void o() {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("userId", this.m);
            jSONObject.put(Constants.Value.PASSWORD, this.b.getText().toString().trim());
        } catch (JSONException e2) {
            lm1.a(e2);
            e2.printStackTrace();
        }
        mj1.b().f(ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new b());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.backBt || id == R.id.backTxt) {
            finish();
            return;
        }
        if (id == R.id.resetBt) {
            m();
            return;
        }
        if (id == R.id.eyeBt) {
            this.d.setSelected(!r4.isSelected());
            if (this.d.isSelected()) {
                this.f4269a.setInputType(144);
                return;
            } else {
                this.f4269a.setInputType(129);
                return;
            }
        }
        if (id == R.id.restEyeBt) {
            this.f4270e.setSelected(!r4.isSelected());
            if (this.f4270e.isSelected()) {
                this.b.setInputType(144);
            } else {
                this.b.setInputType(129);
            }
        }
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        WXBaseActivity.transparencyBar(this);
        WXBaseActivity.setLightStatusBar(this, true);
        setContentView(R.layout.layout_new_password);
        this.j = getIntent().getStringExtra("type");
        getIntent().getStringExtra("userToken");
        getIntent().getStringExtra("nonce");
        getIntent().getStringExtra("monitorConfig");
        this.m = getIntent().getStringExtra("username");
        this.c = findViewById(R.id.resetBt);
        this.d = findViewById(R.id.eyeBt);
        this.f = findViewById(R.id.backBt);
        this.h = (TextView) findViewById(R.id.titleTv);
        this.f4270e = findViewById(R.id.restEyeBt);
        this.g = (TextView) findViewById(R.id.resetTxt);
        this.f4269a = (EditText) findViewById(R.id.passwordTxt);
        this.b = (EditText) findViewById(R.id.resetPasswordTxt);
        this.i = (ProgressBar) findViewById(R.id.loading);
        this.k = (TextView) findViewById(R.id.tipsTv);
        this.l = (TextView) findViewById(R.id.noticeTv);
        this.n = (LinearLayout) findViewById(R.id.ll_identity_success);
        if ("1".equals(this.j)) {
            this.n.setVisibility(8);
        }
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.f4270e.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.f4269a.addTextChangedListener(this);
        this.f4269a.setOnEditorActionListener(this);
        this.b.addTextChangedListener(this);
        this.b.setOnEditorActionListener(this);
        if (this.j.equals("bind")) {
            this.h.setText("请设置密码");
            this.g.setText("设置密码");
            this.trackPageName = getResources().getString(R.string.setPassword_activity);
        } else {
            this.trackPageName = getResources().getString(R.string.resetPassword_activity);
        }
        n();
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (this.f4269a.getText() == null || this.f4269a.getText() == null || this.f4269a.getText() == null || this.b.getText().toString().trim().equals("") || this.b.getText().toString().trim().equals("") || this.b.getText().toString().trim().equals("")) {
            return false;
        }
        m();
        return false;
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
