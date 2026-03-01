package com.supwisdom.superapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.superapp.service.model.Response;
import com.supwisdom.zueb.R;
import org.json.JSONException;
import retrofit2.Call;
import retrofit2.Callback;
import supwisdom.ct1;
import supwisdom.fn1;
import supwisdom.jj1;
import supwisdom.mj1;
import supwisdom.xs1;
import supwisdom.zi1;

/* JADX INFO: loaded from: classes2.dex */
public class ForgetPasswordActivity extends WXBaseActivity implements View.OnClickListener, TextWatcher, zi1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f4098a;
    public TextView b;
    public TextView c;
    public TextView d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public EditText f4099e;
    public String f;
    public String g;
    public String h;
    public String i;
    public jj1 j;
    public View k;
    public ProgressBar l;
    public Boolean m = false;
    public Boolean n;
    public Boolean o;

    public class a implements Callback<Response<JsonObject>> {
        public a() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            ForgetPasswordActivity forgetPasswordActivity = ForgetPasswordActivity.this;
            forgetPasswordActivity.f(forgetPasswordActivity.getResources().getString(R.string.net_error));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            Response<JsonObject> responseBody = response.body();
            if (response.code() != 200 || responseBody == null) {
                ForgetPasswordActivity forgetPasswordActivity = ForgetPasswordActivity.this;
                forgetPasswordActivity.f(forgetPasswordActivity.getResources().getString(R.string.user_verify_error));
                return;
            }
            JsonObject jsonObject = responseBody.data;
            fn1.H = jsonObject.get("nonce").getAsString();
            JsonObject asJsonObject = jsonObject.getAsJsonObject("securitySettingConfig");
            ForgetPasswordActivity.this.o = Boolean.valueOf(asJsonObject.get("mobileEnabled").getAsBoolean());
            ForgetPasswordActivity.this.n = Boolean.valueOf(asJsonObject.get("emailAddressEnabled").getAsBoolean());
            ForgetPasswordActivity.this.m = Boolean.valueOf(asJsonObject.get("questionEnabled").getAsBoolean());
            if (responseBody.code == Response.CODE_SUCCESS) {
                ForgetPasswordActivity.this.l();
            } else {
                ForgetPasswordActivity.this.f(responseBody.message);
            }
        }
    }

    public class b implements Callback<Response<JSONObject>> {
        public b() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            ForgetPasswordActivity forgetPasswordActivity = ForgetPasswordActivity.this;
            forgetPasswordActivity.f(forgetPasswordActivity.getResources().getString(R.string.net_error));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody = response.body();
            if (response.code() != 200 || responseBody == null) {
                ForgetPasswordActivity forgetPasswordActivity = ForgetPasswordActivity.this;
                forgetPasswordActivity.f(forgetPasswordActivity.getResources().getString(R.string.user_verify_error));
                return;
            }
            JSONObject jSONObject = responseBody.data;
            fn1.H = jSONObject.getString("nonce");
            if (responseBody.code != Response.CODE_SUCCESS) {
                ForgetPasswordActivity.this.f(responseBody.message);
                return;
            }
            ForgetPasswordActivity.this.f = TextUtils.isEmpty(jSONObject.getString("mobile")) ? "" : jSONObject.getString("mobile");
            ForgetPasswordActivity.this.g = jSONObject.getString("emailAddress");
            ForgetPasswordActivity.this.h = jSONObject.getString("question1");
            ForgetPasswordActivity.this.i = jSONObject.getString("question2");
            if (TextUtils.isEmpty(ForgetPasswordActivity.this.h) && TextUtils.isEmpty(ForgetPasswordActivity.this.i)) {
                ForgetPasswordActivity.this.m = false;
            }
            ForgetPasswordActivity forgetPasswordActivity2 = ForgetPasswordActivity.this;
            ForgetPasswordActivity forgetPasswordActivity3 = ForgetPasswordActivity.this;
            forgetPasswordActivity2.j = new jj1(forgetPasswordActivity3, forgetPasswordActivity3.o.booleanValue(), ForgetPasswordActivity.this.n.booleanValue(), ForgetPasswordActivity.this.m.booleanValue(), ForgetPasswordActivity.this);
            ForgetPasswordActivity.this.j.b();
            ForgetPasswordActivity.this.f("");
        }
    }

    public class c implements Callback<Response<JsonObject>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4102a;

        public c(String str) {
            this.f4102a = str;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            ForgetPasswordActivity forgetPasswordActivity = ForgetPasswordActivity.this;
            forgetPasswordActivity.f(forgetPasswordActivity.getResources().getString(R.string.net_error));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            Response<JsonObject> responseBody = response.body();
            if (response.code() != 200 || responseBody == null) {
                ForgetPasswordActivity forgetPasswordActivity = ForgetPasswordActivity.this;
                forgetPasswordActivity.f(forgetPasswordActivity.getResources().getString(R.string.send_error));
                return;
            }
            fn1.H = responseBody.data.get("nonce").getAsString();
            if (responseBody.code != Response.CODE_SUCCESS) {
                ForgetPasswordActivity.this.f(responseBody.message);
                return;
            }
            Intent intent = new Intent(ForgetPasswordActivity.this, (Class<?>) InputVerifyCodeActivity.class);
            if (this.f4102a.equals(jj1.j)) {
                intent.putExtra("phoneNumber", ForgetPasswordActivity.this.f);
            } else {
                if (TextUtils.isEmpty(ForgetPasswordActivity.this.g)) {
                    ForgetPasswordActivity forgetPasswordActivity2 = ForgetPasswordActivity.this;
                    forgetPasswordActivity2.f(forgetPasswordActivity2.getResources().getString(R.string.email_error));
                    return;
                }
                intent.putExtra("email", ForgetPasswordActivity.this.g);
            }
            intent.putExtra("type", "forget");
            ForgetPasswordActivity.this.startActivity(intent);
        }
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        if (TextUtils.isEmpty(this.f4099e.getText().toString())) {
            this.k.setSelected(false);
        } else {
            this.k.setSelected(true);
        }
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void l() {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
            jSONObject.put("username", this.f4099e.getText().toString().trim());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().d(ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new b());
    }

    public final void m() {
        mj1.b().b().enqueue(new a());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.backBt) {
            finish();
            return;
        }
        if (id == R.id.loginBt && this.k.isSelected()) {
            this.l.setVisibility(0);
            this.f4098a.setVisibility(8);
            this.f4099e.setEnabled(false);
            this.k.setClickable(false);
            m();
        }
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.trackPageName = getResources().getString(R.string.forgetPassword_activity);
        WXBaseActivity.transparencyBar(this);
        WXBaseActivity.setLightStatusBar(this, true);
        setContentView(R.layout.activity_forgetpassword);
        this.f4098a = (TextView) findViewById(R.id.nextTipTxt);
        this.l = (ProgressBar) findViewById(R.id.loading);
        this.k = findViewById(R.id.loginBt);
        this.f4099e = (EditText) findViewById(R.id.phoneTxt);
        this.c = (TextView) findViewById(R.id.tipsTv);
        this.b = (TextView) findViewById(R.id.backBt);
        this.d = (TextView) findViewById(R.id.titleTv);
        this.c.setText(R.string.forget_paw);
        this.d.setText(R.string.input_account);
        this.f4098a.setText(R.string.next_tip);
        this.b.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.f4099e.addTextChangedListener(this);
    }

    @Override // android.app.Activity
    public void onRestart() {
        super.onRestart();
        f("");
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void e(String str) {
        this.l.setVisibility(0);
        this.f4098a.setVisibility(8);
        this.f4099e.setEnabled(false);
        this.k.setClickable(false);
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
            if (str.equals(jj1.j)) {
                jSONObject.put("checkType", "mobile");
            } else {
                jSONObject.put("checkType", "emailAddress");
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().v(ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new c(str));
    }

    public void f(String str) {
        this.l.setVisibility(8);
        this.f4098a.setVisibility(0);
        this.f4099e.setEnabled(true);
        this.k.setClickable(true);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this, str, 0).show();
    }

    @Override // supwisdom.zi1
    public void c(String str) {
        if (str.equals(jj1.l)) {
            Toast.makeText(this, R.string.develop_tips, 0).show();
            return;
        }
        if (str.equals("question")) {
            if (TextUtils.isEmpty(this.h) && TextUtils.isEmpty(this.i)) {
                Toast.makeText(this, "未设置安全问题", 0).show();
                return;
            }
            Intent intent = new Intent(this, (Class<?>) InputVerifyCodeActivity.class);
            intent.putExtra("question1", this.h);
            intent.putExtra("question2", this.i);
            intent.putExtra("type", "type_question");
            startActivity(intent);
            return;
        }
        e(str);
    }
}
