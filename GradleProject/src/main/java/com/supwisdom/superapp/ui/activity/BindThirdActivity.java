package com.supwisdom.superapp.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSONObject;
import com.igexin.sdk.PushManager;
import com.supwisdom.superapp.WXApplication;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.superapp.service.model.Response;
import com.supwisdom.zueb.R;
import com.ta.utdid2.device.UTDevice;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import io.dcloud.feature.sdk.DCUniMPSDK;
import java.io.IOException;
import java.util.regex.Pattern;
import retrofit2.Call;
import retrofit2.Callback;
import supwisdom.bn1;
import supwisdom.cm1;
import supwisdom.et1;
import supwisdom.fn1;
import supwisdom.in1;
import supwisdom.mj1;
import supwisdom.sh1;

/* JADX INFO: loaded from: classes2.dex */
public class BindThirdActivity extends WXBaseActivity implements View.OnClickListener, TextWatcher, in1.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f4048a;
    public TextView b;
    public TextView c;
    public TextView d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public EditText f4049e;
    public EditText f;
    public ImageButton g;
    public View h;
    public ProgressBar i;
    public String j;
    public String k;
    public JSONObject l;
    public String m;
    public Boolean n;
    public String o;
    public String p;
    public String q;
    public JSONObject r;
    public JSONObject s;

    public class a implements Callback<Response<JSONObject>> {
        public a() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Log.e(WXBaseActivity.TAG, "onFailure: ======= ");
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody;
            if (response.code() == 200 && (responseBody = response.body()) != null) {
                try {
                    BindThirdActivity.this.q = responseBody.data.getString("loginPageConfig");
                    BindThirdActivity.this.l = responseBody.data.getJSONObject("loginPageConfig");
                    BindThirdActivity.this.r = responseBody.data.getJSONObject("priorityLoginConfig");
                    BindThirdActivity.this.s = responseBody.data.getJSONObject("loginModeConfig");
                    if (BindThirdActivity.this.l == null || BindThirdActivity.this.l.getString("encryptEnabled") == null) {
                        return;
                    }
                    BindThirdActivity.this.n = BindThirdActivity.this.l.getBoolean("encryptEnabled");
                    if (BindThirdActivity.this.n.booleanValue()) {
                        BindThirdActivity.this.n();
                    }
                } catch (Exception e2) {
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
            Log.e(WXBaseActivity.TAG, "onFailure: ======= ");
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<et1> call, retrofit2.Response<et1> response) {
            try {
                BindThirdActivity.this.m = response.body().string();
                Log.i(WXBaseActivity.TAG, "onResponse: ==== " + BindThirdActivity.this.m);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public class c implements Callback<Response<JSONObject>> {
        public c() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            BindThirdActivity.this.f(BindThirdActivity.this.getResources().getString(R.string.net_error));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Log.d("onResponse", "onResponse");
            if (response.code() != 200) {
                BindThirdActivity.this.f(BindThirdActivity.this.getResources().getString(R.string.account_error));
                return;
            }
            String string = response.body().data.getString("idToken");
            if (TextUtils.isEmpty(string)) {
                Toast.makeText(BindThirdActivity.this, response.message(), 0).show();
            } else {
                BindThirdActivity.this.e(string);
            }
        }
    }

    public class d implements Callback<Response<JSONObject>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4053a;

        public d(String str) {
            this.f4053a = str;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            BindThirdActivity.this.f(BindThirdActivity.this.getResources().getString(R.string.net_error));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody = response.body();
            if (response.code() != 200 || responseBody.code != 0) {
                BindThirdActivity.this.f(BindThirdActivity.this.getResources().getString(R.string.bind_error));
            } else {
                JSONObject jSONObject = responseBody.data;
                sh1.c.b(fn1.o, this.f4053a);
                BindThirdActivity bindThirdActivity = BindThirdActivity.this;
                in1.b(bindThirdActivity, bindThirdActivity);
            }
        }
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        if (!TextUtils.isEmpty(this.f4049e.getText().toString()) && !TextUtils.isEmpty(this.f.getText().toString())) {
            this.h.setSelected(true);
        } else {
            this.h.setSelected(false);
            this.f4048a.setSelected(false);
        }
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void e(String str) {
        mj1.b().e(fn1.H, str).enqueue(new d(str));
    }

    public void f(String str) {
        this.i.setVisibility(8);
        this.f4049e.setEnabled(true);
        this.f.setEnabled(true);
        this.f4048a.setVisibility(0);
        Toast.makeText(this, str, 0).show();
    }

    public final String g(String str) {
        return str != null ? Pattern.compile("\\s*|\t|\r|\n").matcher(str).replaceAll("") : "";
    }

    @Override // supwisdom.in1.d
    public void k() {
        try {
            WXApplication.homeUniMP = DCUniMPSDK.getInstance().openUniMP(this, fn1.i, cm1.class);
            finishAffinity();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        openMini();
    }

    public final void l() {
        String strTrim;
        String string;
        String str;
        String strC = sh1.c.c(fn1.p);
        if (strC == null || "".equals(strC)) {
            strC = UTDevice.getUtdid(this);
            sh1.c.b(fn1.p, strC);
        }
        String str2 = strC;
        if (!"".equals(this.m) && (str = this.m) != null) {
            try {
                this.o = bn1.a(str, this.f4049e.getText().toString().trim());
                this.p = bn1.a(this.m, this.f.getText().toString());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        String str3 = this.o;
        if (str3 != null) {
            strTrim = g("__RSA__".concat(String.valueOf(str3)));
            Log.e(WXBaseActivity.TAG, "doLogin: ==== " + strTrim);
        } else {
            strTrim = this.f4049e.getText().toString().trim();
        }
        String str4 = strTrim;
        String str5 = this.p;
        if (str5 != null) {
            string = g("__RSA__".concat(String.valueOf(str5)));
            Log.e(WXBaseActivity.TAG, "doLogin: ==== " + string);
        } else {
            string = this.f.getText().toString();
        }
        mj1.b().b(str4, string, getPackageName(), "", str2, "android", PushManager.getInstance().getClientid(this), this.k).enqueue(new c());
    }

    public final void m() {
        mj1.b().c().enqueue(new a());
    }

    public final void n() {
        mj1.b().getPublicKey().enqueue(new b());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.backBt) {
            finish();
            return;
        }
        if (id != R.id.bindBt) {
            if (id != R.id.eyeBt) {
                return;
            }
            this.g.setSelected(!r2.isSelected());
            if (this.g.isSelected()) {
                this.f.setInputType(144);
                return;
            } else {
                this.f.setInputType(129);
                return;
            }
        }
        Log.d(ConstantsAPI.Token.WX_TOKEN_PLATFORMID_VALUE, "点击1");
        if (this.h.isSelected()) {
            Log.d(ConstantsAPI.Token.WX_TOKEN_PLATFORMID_VALUE, "点击2");
            this.i.setVisibility(0);
            this.f4049e.setEnabled(false);
            this.f.setEnabled(false);
            this.f4048a.setVisibility(8);
            l();
        }
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.trackPageName = getResources().getString(R.string.wxBind_activity);
        WXBaseActivity.transparencyBar(this);
        WXBaseActivity.setLightStatusBar(this, true);
        this.j = getIntent().getStringExtra("bindType");
        this.k = getIntent().getStringExtra("state");
        setContentView(R.layout.activity_wechat_bind);
        this.f4048a = (TextView) findViewById(R.id.nextTipTxt);
        this.b = (TextView) findViewById(R.id.backBt);
        this.f = (EditText) findViewById(R.id.passwordTxt);
        this.g = (ImageButton) findViewById(R.id.eyeBt);
        this.f4049e = (EditText) findViewById(R.id.accountTxt);
        this.i = (ProgressBar) findViewById(R.id.loading);
        this.c = (TextView) findViewById(R.id.tipsTv);
        this.h = findViewById(R.id.bindBt);
        this.d = (TextView) findViewById(R.id.titleTv);
        this.f4048a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.f4049e.addTextChangedListener(this);
        this.f.addTextChangedListener(this);
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
        String str = this.j;
        if (str != null) {
            if (str.equals("qq")) {
                this.c.setText(getResources().getString(R.string.please_bind_qq));
                this.d.setText(getResources().getString(R.string.bind_qq));
            } else {
                this.c.setText("您的支付宝未绑定账号，请先完成绑定！");
                this.d.setText("支付宝账号绑定");
            }
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        m();
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
