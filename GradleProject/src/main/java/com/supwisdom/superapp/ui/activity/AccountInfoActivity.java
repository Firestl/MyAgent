package com.supwisdom.superapp.ui.activity;

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
import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.superapp.entity.response.ActivieInformationResponse;
import com.supwisdom.superapp.service.model.Response;
import com.supwisdom.zueb.R;
import io.dcloud.js.map.amap.util.ChString;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import supwisdom.ct1;
import supwisdom.fn1;
import supwisdom.mi1;
import supwisdom.mj1;
import supwisdom.xs1;
import supwisdom.zi1;

/* JADX INFO: loaded from: classes2.dex */
public class AccountInfoActivity extends WXBaseActivity implements View.OnClickListener, TextWatcher, zi1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f4020a;
    public TextView b;
    public FrameLayout c;
    public ProgressBar d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public EditText f4021e;
    public EditText f;
    public EditText g;
    public String h;
    public String i;
    public mi1 j;
    public ActivieInformationResponse k;

    public class a implements Callback<Response<JsonObject>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f4022a;

        public a(boolean z) {
            this.f4022a = z;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            AccountInfoActivity accountInfoActivity = AccountInfoActivity.this;
            accountInfoActivity.e(accountInfoActivity.getResources().getString(R.string.net_error));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            if (response.code() != 200 && (response.code() != 0 || response.body() == null || response.body().data == null)) {
                Toast.makeText(AccountInfoActivity.this, "请返回重试", 0).show();
                return;
            }
            AccountInfoActivity.this.k = (ActivieInformationResponse) JSON.parseObject(String.valueOf(response.body().data), ActivieInformationResponse.class);
            fn1.H = AccountInfoActivity.this.k.getNonce() == null ? fn1.H : AccountInfoActivity.this.k.getNonce();
            if (!AccountInfoActivity.this.k.getActivationValidateConfig().isValidateAccountEnabled()) {
                AccountInfoActivity.this.f4021e.setVisibility(8);
            }
            if (!AccountInfoActivity.this.k.getActivationValidateConfig().isValidateNameEnabled()) {
                AccountInfoActivity.this.f.setVisibility(8);
            }
            if (!AccountInfoActivity.this.k.getActivationValidateConfig().isValidateIdentityNoEnabled()) {
                AccountInfoActivity.this.g.setVisibility(8);
            }
            if (!AccountInfoActivity.this.k.getActivationModeConfig().isFaceVerifyEnabled() && AccountInfoActivity.this.k.getActivationModeConfig().isAlipayEnabled() && !AccountInfoActivity.this.k.getActivationModeConfig().isPreMobileVerifyEnabled()) {
                AccountInfoActivity.this.h = "skip";
            }
            if (this.f4022a) {
                AccountInfoActivity.this.l();
            }
        }
    }

    public class b implements Callback<Response<JsonObject>> {
        public b() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            AccountInfoActivity accountInfoActivity = AccountInfoActivity.this;
            accountInfoActivity.e(accountInfoActivity.getResources().getString(R.string.net_error));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            Response<JsonObject> responseBody = response.body();
            if (response.code() != 200 || responseBody == null) {
                AccountInfoActivity accountInfoActivity = AccountInfoActivity.this;
                accountInfoActivity.e(accountInfoActivity.getResources().getString(R.string.active_error));
                return;
            }
            fn1.H = responseBody.data.get("nonce").getAsString();
            if (responseBody.code != Response.CODE_SUCCESS) {
                AccountInfoActivity.this.e(responseBody.message);
                return;
            }
            if (AccountInfoActivity.this.h.equals(mi1.g)) {
                Intent intent = new Intent(AccountInfoActivity.this, (Class<?>) FaceLivenessExpActivity.class);
                intent.putExtra("verifyType", "bind");
                AccountInfoActivity.this.startActivity(intent);
                return;
            }
            if (!AccountInfoActivity.this.h.equals("skip")) {
                AccountInfoActivity.this.n();
                return;
            }
            Intent intent2 = new Intent(AccountInfoActivity.this, (Class<?>) BindPhoneActivity.class);
            if (AccountInfoActivity.this.k == null || AccountInfoActivity.this.k.getActivationTypeConfig() == null || !(AccountInfoActivity.this.k.getActivationTypeConfig().isSecureEmailAddressEnabled() || AccountInfoActivity.this.k.getActivationTypeConfig().isSecureMobileEnabled())) {
                intent2.setClass(AccountInfoActivity.this, UseAsSecureActivity.class);
            } else {
                intent2.setClass(AccountInfoActivity.this, BindPhoneActivity.class);
                intent2.putExtra("verifyType", "bind");
                if (AccountInfoActivity.this.k.getActivationTypeConfig().isSecureMobileEnabled() && AccountInfoActivity.this.k.getActivationTypeConfig().isSecureEmailAddressEnabled()) {
                    intent2.putExtra("open_another", true);
                }
                if (AccountInfoActivity.this.k.getActivationTypeConfig().isSecureMobileEnabled()) {
                    intent2.putExtra("type", "phone");
                } else {
                    intent2.putExtra("type", "email");
                }
            }
            AccountInfoActivity.this.startActivity(intent2);
        }
    }

    public class c implements Callback<Response<JsonObject>> {
        public c() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            AccountInfoActivity accountInfoActivity = AccountInfoActivity.this;
            accountInfoActivity.e(accountInfoActivity.getResources().getString(R.string.net_error));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            Response<JsonObject> responseBody = response.body();
            if (response.code() != 200 || responseBody == null) {
                AccountInfoActivity accountInfoActivity = AccountInfoActivity.this;
                accountInfoActivity.e(accountInfoActivity.getResources().getString(R.string.active_error));
                return;
            }
            JsonObject jsonObject = responseBody.data;
            fn1.H = jsonObject.get("nonce").getAsString();
            if (responseBody.code != Response.CODE_SUCCESS) {
                AccountInfoActivity accountInfoActivity2 = AccountInfoActivity.this;
                accountInfoActivity2.e(accountInfoActivity2.getResources().getString(R.string.active_error));
            } else {
                if (!jsonObject.get("enabled").getAsBoolean()) {
                    AccountInfoActivity.this.e("暂无预留手机号");
                    return;
                }
                AccountInfoActivity.this.i = jsonObject.get("preMobile").getAsString();
                AccountInfoActivity.this.m();
            }
        }
    }

    public class d implements Callback<Response<JsonObject>> {
        public d() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            AccountInfoActivity accountInfoActivity = AccountInfoActivity.this;
            accountInfoActivity.e(accountInfoActivity.getResources().getString(R.string.net_error));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            Response<JsonObject> responseBody = response.body();
            if (response.code() != 200 || responseBody == null) {
                AccountInfoActivity accountInfoActivity = AccountInfoActivity.this;
                accountInfoActivity.e(accountInfoActivity.getResources().getString(R.string.phone_error));
                return;
            }
            fn1.H = responseBody.data.get("nonce").getAsString();
            if (responseBody.code == Response.CODE_SUCCESS) {
                AccountInfoActivity.this.o();
            } else {
                AccountInfoActivity.this.e(responseBody.message);
            }
        }
    }

    public class e implements Callback<Response<JsonObject>> {
        public e() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            AccountInfoActivity accountInfoActivity = AccountInfoActivity.this;
            accountInfoActivity.e(accountInfoActivity.getResources().getString(R.string.net_error));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            Response<JsonObject> responseBody = response.body();
            if (response.code() != 200 || responseBody == null) {
                AccountInfoActivity accountInfoActivity = AccountInfoActivity.this;
                accountInfoActivity.e(accountInfoActivity.getResources().getString(R.string.send_error));
                return;
            }
            fn1.H = responseBody.data.get("nonce").getAsString();
            if (responseBody.code != Response.CODE_SUCCESS) {
                AccountInfoActivity.this.e(responseBody.message);
                return;
            }
            Intent intent = new Intent(AccountInfoActivity.this, (Class<?>) InputVerifyCodeActivity.class);
            intent.putExtra("phoneNumber", AccountInfoActivity.this.i);
            intent.putExtra("type", "preVerity");
            if (AccountInfoActivity.this.k != null && AccountInfoActivity.this.k.getActivationTypeConfig().isSecureMobileEnabled()) {
                intent.putExtra("open_mobile_type", true);
            }
            if (AccountInfoActivity.this.k != null && AccountInfoActivity.this.k.getActivationTypeConfig().isSecureEmailAddressEnabled()) {
                intent.putExtra("open_email_type", true);
            }
            AccountInfoActivity.this.startActivity(intent);
        }
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        boolean z = this.f.getVisibility() == 0 && TextUtils.isEmpty(this.f.getText().toString().trim());
        if (!TextUtils.isEmpty(this.f4021e.getText().toString().trim()) && !z) {
            if (this.g.getVisibility() == 0 && TextUtils.isEmpty(this.g.getText().toString().trim())) {
                this.c.setSelected(false);
                return;
            } else {
                this.c.setSelected(true);
                return;
            }
        }
        this.c.setSelected(false);
        if (TextUtils.isEmpty(this.g.getText().toString().trim()) || z) {
            this.c.setSelected(false);
        } else {
            this.c.setSelected(true);
        }
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void l() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
            jSONObject.put("accountName", this.f4021e.getText().toString().trim());
            jSONObject.put("identityNo", this.g.getText().toString().trim());
            jSONObject.put("name", this.f.getText().toString().trim());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().r(ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new b());
    }

    public final void m() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().b(ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new d());
    }

    public final void n() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().e(ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new c());
    }

    public final void o() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().l(ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new e());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.infoBackBtn) {
            finish();
            return;
        }
        if (id == R.id.nextTipBtn && this.c.isSelected()) {
            this.f.setEnabled(false);
            this.f4021e.setEnabled(false);
            this.g.setEnabled(false);
            this.d.setVisibility(0);
            this.b.setVisibility(8);
            this.c.setClickable(false);
            ActivieInformationResponse activieInformationResponse = this.k;
            if (activieInformationResponse == null || !(activieInformationResponse.getActivationModeConfig().isFaceVerifyEnabled() || this.k.getActivationModeConfig().isAlipayEnabled() || this.k.getActivationModeConfig().isPreMobileVerifyEnabled())) {
                this.h = "skip";
                b(true);
            } else {
                e("");
                this.j.a(this.k);
                this.j.b();
            }
        }
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.trackPageName = getResources().getString(R.string.accountInfo_activity);
        WXBaseActivity.transparencyBar(this);
        WXBaseActivity.setLightStatusBar(this, true);
        setContentView(R.layout.activity_accountinfo);
        this.f4020a = (TextView) findViewById(R.id.infoBackBtn);
        this.f4021e = (EditText) findViewById(R.id.accountNameEt);
        this.f = (EditText) findViewById(R.id.nameEt);
        this.g = (EditText) findViewById(R.id.identifyNumberEt);
        this.c = (FrameLayout) findViewById(R.id.nextTipBtn);
        this.b = (TextView) findViewById(R.id.nextTipTxt);
        this.d = (ProgressBar) findViewById(R.id.loading);
        this.f4020a.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.f4021e.addTextChangedListener(this);
        this.f.addTextChangedListener(this);
        this.g.addTextChangedListener(this);
        this.j = new mi1(this, this);
        this.b.setText(ChString.NextStep);
        b(false);
    }

    @Override // android.app.Activity
    public void onRestart() {
        super.onRestart();
        e("");
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // supwisdom.zi1
    public void c(String str) {
        if (!str.equals(mi1.g) && !str.equals(mi1.h)) {
            Toast.makeText(this, R.string.develop_tips, 0).show();
            return;
        }
        this.h = str;
        this.f.setEnabled(false);
        this.f4021e.setEnabled(false);
        this.g.setEnabled(false);
        this.d.setVisibility(0);
        this.b.setVisibility(8);
        this.c.setClickable(false);
        b(true);
    }

    public void e(String str) {
        this.f.setEnabled(true);
        this.f4021e.setEnabled(true);
        this.g.setEnabled(true);
        this.d.setVisibility(8);
        this.b.setVisibility(0);
        this.c.setClickable(true);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this, str, 0).show();
    }

    public final void b(boolean z) {
        mj1.b().e().enqueue(new a(z));
    }
}
