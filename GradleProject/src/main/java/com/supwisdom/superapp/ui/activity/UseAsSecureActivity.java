package com.supwisdom.superapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
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
import supwisdom.mj1;
import supwisdom.xs1;

/* JADX INFO: loaded from: classes2.dex */
public class UseAsSecureActivity extends WXBaseActivity implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f4375a;
    public TextView b;
    public FrameLayout c;
    public FrameLayout d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public FrameLayout f4376e;
    public String f;
    public ProgressBar g;

    public class a implements Callback<Response<JsonObject>> {
        public a() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            UseAsSecureActivity.this.e("网络出错");
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            Response<JsonObject> responseBody = response.body();
            if (response.code() != 200 || responseBody == null) {
                UseAsSecureActivity.this.e(UseAsSecureActivity.this.getResources().getString(R.string.verify_error));
                return;
            }
            fn1.H = responseBody.data.get("nonce").getAsString();
            if (responseBody.code != Response.CODE_SUCCESS) {
                UseAsSecureActivity.this.e(responseBody.message);
            } else {
                Intent intent = new Intent(UseAsSecureActivity.this, (Class<?>) ResetPasswordActivity.class);
                intent.putExtra("type", "bind");
                UseAsSecureActivity.this.startActivity(intent);
            }
        }
    }

    public void e(String str) {
        this.g.setVisibility(8);
        this.b.setVisibility(0);
        this.c.setClickable(true);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this, str, 0).show();
    }

    public final void l() {
        this.g.setVisibility(0);
        this.b.setVisibility(8);
        this.c.setClickable(false);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
            jSONObject.put("useAsSecureMobile", true);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().c(ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new a());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_set_safe_email /* 2131296593 */:
                Intent intent = new Intent(this, (Class<?>) BindPhoneActivity.class);
                intent.putExtra("verifyType", "bind");
                intent.putExtra("type", "email");
                startActivity(intent);
                break;
            case R.id.goSetPassBtn /* 2131296612 */:
                l();
                break;
            case R.id.goSetSecurePhoneBtn /* 2131296613 */:
                Intent intent2 = new Intent(this, (Class<?>) BindPhoneActivity.class);
                intent2.putExtra("verifyType", "bind");
                startActivity(intent2);
                break;
        }
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        WXBaseActivity.transparencyBar(this);
        WXBaseActivity.setLightStatusBar(this, true);
        setContentView(R.layout.activity_use_as_secure);
        this.f4375a = (TextView) findViewById(R.id.phoneTv);
        this.c = (FrameLayout) findViewById(R.id.goSetPassBtn);
        this.g = (ProgressBar) findViewById(R.id.loading);
        this.b = (TextView) findViewById(R.id.setPasswordTxt);
        this.d = (FrameLayout) findViewById(R.id.goSetSecurePhoneBtn);
        this.f4376e = (FrameLayout) findViewById(R.id.fl_set_safe_email);
        this.d.setSelected(true);
        this.c.setSelected(true);
        this.f4376e.setSelected(true);
        this.f4376e.setClickable(true);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.f4376e.setOnClickListener(this);
        this.f = getIntent().getStringExtra("prePhoneNumber");
        this.f4375a.setText("预留手机号：" + this.f);
        if (getIntent().getBooleanExtra("open_email_type", false)) {
            this.f4376e.setVisibility(8);
        }
        if (getIntent().getBooleanExtra("open_mobile_type", false)) {
            this.d.setVisibility(8);
        }
    }
}
