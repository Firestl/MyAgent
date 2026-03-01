package com.supwisdom.superapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.igexin.sdk.PushManager;
import com.supwisdom.superapp.WXApplication;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.superapp.service.model.Response;
import com.supwisdom.superapp.view.VerifyCodeView;
import com.supwisdom.superapp.view.VerifyCodeView4;
import com.supwisdom.zueb.R;
import com.ta.utdid2.device.UTDevice;
import io.dcloud.feature.sdk.DCUniMPSDK;
import org.json.JSONException;
import retrofit2.Call;
import retrofit2.Callback;
import supwisdom.cm1;
import supwisdom.ct1;
import supwisdom.fn1;
import supwisdom.in1;
import supwisdom.mj1;
import supwisdom.qi1;
import supwisdom.sh1;
import supwisdom.ui1;
import supwisdom.xs1;

/* JADX INFO: loaded from: classes2.dex */
public class InputVerifyCodeActivity extends WXBaseActivity implements in1.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f4189a;
    public TextView b;
    public TextView c;
    public TextView d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f4190e;
    public TextView f;
    public String g;
    public VerifyCodeView4 h;
    public CountDownTimer i;
    public String j;
    public String k;
    public LinearLayout l;
    public LinearLayout m;
    public LinearLayout n;
    public Button o;
    public TextView p;
    public EditText q;
    public ImageView r;
    public String s;
    public String t;
    public Context u;
    public ui1 v;
    public qi1 w;

    public class a implements Callback<Response<JsonObject>> {
        public a() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            Toast.makeText(InputVerifyCodeActivity.this.u, InputVerifyCodeActivity.this.getResources().getString(R.string.net_error), 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            Response<JsonObject> responseBody = response.body();
            if (response.code() != 200 || responseBody == null) {
                Toast.makeText(InputVerifyCodeActivity.this.u, InputVerifyCodeActivity.this.getResources().getString(R.string.send_error), 0).show();
                return;
            }
            fn1.H = responseBody.data.get("nonce").getAsString();
            if (responseBody.code == Response.CODE_SUCCESS) {
                InputVerifyCodeActivity.this.i.start();
            } else {
                Toast.makeText(InputVerifyCodeActivity.this.u, responseBody.message, 0).show();
            }
        }
    }

    public class b implements Callback<Response<JsonObject>> {
        public b() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            Toast.makeText(InputVerifyCodeActivity.this.u, "网络出错", 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            Response<JsonObject> responseBody = response.body();
            if (response.code() != 200 || responseBody == null) {
                Toast.makeText(InputVerifyCodeActivity.this.u, InputVerifyCodeActivity.this.getResources().getString(R.string.send_error), 0).show();
                return;
            }
            fn1.H = responseBody.data.get("nonce").getAsString();
            if (responseBody.code == Response.CODE_SUCCESS) {
                InputVerifyCodeActivity.this.i.start();
            } else {
                Toast.makeText(InputVerifyCodeActivity.this.u, responseBody.message, 0).show();
            }
        }
    }

    public class c implements Callback<Response<JsonObject>> {
        public c() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            Toast.makeText(InputVerifyCodeActivity.this.u, InputVerifyCodeActivity.this.getResources().getString(R.string.net_error), 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            Response<JsonObject> responseBody = response.body();
            if (response.code() != 200 || responseBody == null) {
                Toast.makeText(InputVerifyCodeActivity.this.u, InputVerifyCodeActivity.this.getResources().getString(R.string.send_error), 0).show();
                return;
            }
            fn1.H = responseBody.data.get("nonce").getAsString();
            if (responseBody.code == Response.CODE_SUCCESS) {
                InputVerifyCodeActivity.this.i.start();
            } else {
                Toast.makeText(InputVerifyCodeActivity.this.u, responseBody.message, 0).show();
            }
        }
    }

    public class d implements Callback<Response<JSONObject>> {
        public d() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Toast.makeText(InputVerifyCodeActivity.this.u, InputVerifyCodeActivity.this.getResources().getString(R.string.net_error), 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody = response.body();
            if (response.code() == 200 && responseBody.code == 0) {
                fn1.H = responseBody.data.getString("nonce");
                InputVerifyCodeActivity.this.i.start();
            } else {
                Toast.makeText(InputVerifyCodeActivity.this.u, InputVerifyCodeActivity.this.getResources().getString(R.string.send_error), 0).show();
            }
        }
    }

    public class e implements Callback<Response<JsonObject>> {
        public e() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            Toast.makeText(InputVerifyCodeActivity.this.u, "网络错误", 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            if (response.code() != 200 && (response.code() != 0 || response.body() == null || response.body().data == null)) {
                Toast.makeText(InputVerifyCodeActivity.this.u, "发送失败", 0).show();
                return;
            }
            if (response.body().data.get("nonce") != null) {
                fn1.H = response.body().data.get("nonce").getAsString();
            }
            InputVerifyCodeActivity.this.i.start();
            Toast.makeText(InputVerifyCodeActivity.this.u, response.body().data.get("message").getAsString(), 0).show();
        }
    }

    public class f implements Callback<Response<JsonObject>> {
        public f() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            Toast.makeText(InputVerifyCodeActivity.this.u, InputVerifyCodeActivity.this.getResources().getString(R.string.net_error), 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            Response<JsonObject> responseBody = response.body();
            if (response.code() != 200 || responseBody == null) {
                Toast.makeText(InputVerifyCodeActivity.this.u, InputVerifyCodeActivity.this.getResources().getString(R.string.deal_error), 0).show();
                return;
            }
            JsonObject jsonObject = responseBody.data;
            fn1.H = jsonObject.get("nonce").getAsString();
            if (responseBody.code != Response.CODE_SUCCESS) {
                Toast.makeText(InputVerifyCodeActivity.this.u, jsonObject.get("message").getAsString(), 0).show();
                return;
            }
            Intent intent = new Intent(InputVerifyCodeActivity.this.u, (Class<?>) ResetPasswordActivity.class);
            intent.putExtra("type", InputVerifyCodeActivity.this.j);
            InputVerifyCodeActivity.this.startActivity(intent);
        }
    }

    public class g implements Callback<Response<JsonObject>> {
        public g() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            Toast.makeText(InputVerifyCodeActivity.this.u, "网络错误", 0).show();
            InputVerifyCodeActivity.this.v.dismiss();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            InputVerifyCodeActivity.this.v.dismiss();
            if (response.code() != 200 || response.body() == null || response.body().data == null) {
                Toast.makeText(InputVerifyCodeActivity.this.u, "验证失败", 0).show();
                return;
            }
            String string = response.body().data.get("message").toString();
            Toast.makeText(InputVerifyCodeActivity.this.u, string, 0).show();
            String asString = response.body().data.get("nonce").getAsString();
            if (asString != null) {
                fn1.H = asString;
            }
            response.body().data.get("userId").getAsString();
            if (string.equals("验证成功")) {
                Intent intent = new Intent(InputVerifyCodeActivity.this, (Class<?>) ResetPasswordActivity.class);
                intent.putExtra("type", "forget");
                InputVerifyCodeActivity.this.startActivity(intent);
                InputVerifyCodeActivity.this.finish();
            }
        }
    }

    public class h implements VerifyCodeView.b {
        public h() {
        }

        @Override // com.supwisdom.superapp.view.VerifyCodeView.b
        public void a() {
            if (InputVerifyCodeActivity.this.j.equals("bind")) {
                if (TextUtils.isEmpty(InputVerifyCodeActivity.this.k)) {
                    InputVerifyCodeActivity.this.u();
                    return;
                } else {
                    InputVerifyCodeActivity.this.l();
                    return;
                }
            }
            if (InputVerifyCodeActivity.this.j.equals("forget")) {
                InputVerifyCodeActivity.this.o();
            } else if (InputVerifyCodeActivity.this.j.equals("preVerity")) {
                InputVerifyCodeActivity.this.s();
            } else if (InputVerifyCodeActivity.this.j.equals("login")) {
                InputVerifyCodeActivity.this.q();
            }
        }

        @Override // com.supwisdom.superapp.view.VerifyCodeView.b
        public void b() {
        }
    }

    public class i extends CountDownTimer {
        public i(long j, long j2) {
            super(j, j2);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            InputVerifyCodeActivity.this.b.setText("获取验证码");
            InputVerifyCodeActivity inputVerifyCodeActivity = InputVerifyCodeActivity.this;
            inputVerifyCodeActivity.b.setTextColor(inputVerifyCodeActivity.getResources().getColor(R.color.color_032));
            InputVerifyCodeActivity.this.b.setEnabled(true);
            InputVerifyCodeActivity.this.f4190e.setText("获取验证码");
            InputVerifyCodeActivity inputVerifyCodeActivity2 = InputVerifyCodeActivity.this;
            inputVerifyCodeActivity2.f4190e.setTextColor(inputVerifyCodeActivity2.getResources().getColor(R.color.color_032));
            InputVerifyCodeActivity.this.f4190e.setEnabled(true);
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            TextView textView = InputVerifyCodeActivity.this.b;
            StringBuilder sb = new StringBuilder();
            long j2 = j / 1000;
            sb.append(j2);
            sb.append("s 后重发");
            textView.setText(sb.toString());
            InputVerifyCodeActivity inputVerifyCodeActivity = InputVerifyCodeActivity.this;
            inputVerifyCodeActivity.b.setTextColor(inputVerifyCodeActivity.getResources().getColor(R.color.color_c40));
            InputVerifyCodeActivity.this.b.setEnabled(false);
            InputVerifyCodeActivity.this.f4190e.setText(j2 + "s 后重发");
            InputVerifyCodeActivity inputVerifyCodeActivity2 = InputVerifyCodeActivity.this;
            inputVerifyCodeActivity2.f4190e.setTextColor(inputVerifyCodeActivity2.getResources().getColor(R.color.color_c40));
            InputVerifyCodeActivity.this.f4190e.setEnabled(false);
        }
    }

    public class j implements View.OnClickListener {
        public j() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            InputVerifyCodeActivity.this.p();
        }
    }

    public class k implements View.OnClickListener {
        public k() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            InputVerifyCodeActivity.this.finish();
        }
    }

    public class l implements TextWatcher {
        public l() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (TextUtils.isEmpty(charSequence.toString().trim())) {
                InputVerifyCodeActivity.this.o.setBackground(InputVerifyCodeActivity.this.getResources().getDrawable(R.drawable.shape_radius22_colorf1f3f6));
                InputVerifyCodeActivity.this.o.setTextColor(InputVerifyCodeActivity.this.getResources().getColor(R.color.color_B6BAC0));
                InputVerifyCodeActivity.this.o.setClickable(false);
            } else {
                InputVerifyCodeActivity.this.o.setBackground(InputVerifyCodeActivity.this.getResources().getDrawable(R.drawable.shape_radius22_colored663f));
                InputVerifyCodeActivity.this.o.setTextColor(InputVerifyCodeActivity.this.getResources().getColor(R.color.ffffff));
                InputVerifyCodeActivity.this.o.setClickable(true);
            }
        }
    }

    public class m implements Callback<Response<JsonObject>> {
        public m() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            Toast.makeText(InputVerifyCodeActivity.this.u, "网络出错", 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            Response<JsonObject> responseBody = response.body();
            if (response.code() != 200 || responseBody == null) {
                Toast.makeText(InputVerifyCodeActivity.this.u, InputVerifyCodeActivity.this.getResources().getString(R.string.deal_error), 0).show();
                return;
            }
            fn1.H = responseBody.data.get("nonce").getAsString();
            if (responseBody.code != Response.CODE_SUCCESS) {
                Toast.makeText(InputVerifyCodeActivity.this.u, InputVerifyCodeActivity.this.getResources().getString(R.string.verify_error), 0).show();
                return;
            }
            Intent intent = new Intent(InputVerifyCodeActivity.this.u, (Class<?>) ResetPasswordActivity.class);
            intent.putExtra("type", InputVerifyCodeActivity.this.j);
            InputVerifyCodeActivity.this.startActivity(intent);
        }
    }

    public class n implements Callback<Response<JsonObject>> {
        public n() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            Toast.makeText(InputVerifyCodeActivity.this.u, "网络出错", 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            Response<JsonObject> responseBody = response.body();
            if (response.code() != 200 || responseBody == null) {
                Toast.makeText(InputVerifyCodeActivity.this.u, InputVerifyCodeActivity.this.getResources().getString(R.string.verify_error), 0).show();
                return;
            }
            JsonObject jsonObject = responseBody.data;
            fn1.H = jsonObject.get("nonce").getAsString();
            boolean asBoolean = jsonObject.get("canUseAsSecureMobile").getAsBoolean();
            if (responseBody.code != Response.CODE_SUCCESS) {
                Toast.makeText(InputVerifyCodeActivity.this.u, responseBody.message, 0).show();
                return;
            }
            if (!asBoolean) {
                Intent intent = new Intent(InputVerifyCodeActivity.this.u, (Class<?>) BindPhoneActivity.class);
                intent.putExtra("verifyType", "bind");
                InputVerifyCodeActivity.this.startActivity(intent);
            } else {
                Intent intent2 = new Intent(InputVerifyCodeActivity.this.u, (Class<?>) UseAsSecureActivity.class);
                intent2.putExtra("verifyType", "bind");
                intent2.putExtra("prePhoneNumber", InputVerifyCodeActivity.this.g);
                intent2.putExtra("open_email_type", InputVerifyCodeActivity.this.getIntent().getBooleanExtra("open_email_type", false));
                intent2.putExtra("open_mobile_type", InputVerifyCodeActivity.this.getIntent().getBooleanExtra("open_mobile_type", false));
                InputVerifyCodeActivity.this.startActivity(intent2);
            }
        }
    }

    public class o implements Callback<Response<JSONObject>> {
        public o() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Toast.makeText(InputVerifyCodeActivity.this.u, InputVerifyCodeActivity.this.getResources().getString(R.string.net_error), 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody = response.body();
            if (response.code() == 200 && responseBody.code == 0) {
                String string = responseBody.data.getString("idToken");
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                sh1.c.b(fn1.o, string);
                in1.b(InputVerifyCodeActivity.this.u, InputVerifyCodeActivity.this);
                return;
            }
            if (response.code() != 200 || responseBody.code != 100000) {
                Toast.makeText(InputVerifyCodeActivity.this.u, InputVerifyCodeActivity.this.getResources().getString(R.string.verify_error), 0).show();
                return;
            }
            JSONObject jSONObject = responseBody.data;
            String string2 = jSONObject.getString("cid");
            JSONArray jSONArray = jSONObject.getJSONArray("accounts");
            if (jSONArray == null || jSONArray.size() == 0) {
                return;
            }
            Intent intent = new Intent(InputVerifyCodeActivity.this.u, (Class<?>) MultiAccountLoginAt.class);
            intent.putExtra("accountJA", jSONArray.toJSONString());
            intent.putExtra("cid", string2);
            intent.putExtra("loginType", "codeLogin");
            InputVerifyCodeActivity.this.startActivity(intent);
        }
    }

    public class p implements Callback<Response<JsonObject>> {
        public p() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            Toast.makeText(InputVerifyCodeActivity.this.u, "网络出错", 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            Response<JsonObject> responseBody = response.body();
            if (response.code() != 200 || responseBody == null) {
                Toast.makeText(InputVerifyCodeActivity.this.u, InputVerifyCodeActivity.this.getResources().getString(R.string.deal_error), 0).show();
                return;
            }
            fn1.H = responseBody.data.get("nonce").getAsString();
            if (responseBody.code != Response.CODE_SUCCESS) {
                Toast.makeText(InputVerifyCodeActivity.this.u, InputVerifyCodeActivity.this.getResources().getString(R.string.verify_error), 0).show();
                return;
            }
            Intent intent = new Intent(InputVerifyCodeActivity.this.u, (Class<?>) ResetPasswordActivity.class);
            intent.putExtra("type", InputVerifyCodeActivity.this.j);
            InputVerifyCodeActivity.this.startActivity(intent);
        }
    }

    @Override // supwisdom.in1.d
    public void k() {
        try {
            WXApplication.homeUniMP = DCUniMPSDK.getInstance().openUniMP(this.u, fn1.i, cm1.class);
            finishAffinity();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final void l() {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
            jSONObject.put("emailAddress", this.f.getText().toString());
            jSONObject.put("code", this.h.getEditContent());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().u(ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new f());
    }

    public final void m() {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
            jSONObject.put("question", this.p.getText().toString());
            jSONObject.put("answer", this.q.getText().toString());
        } catch (JSONException unused) {
        }
        mj1.b().b(fn1.w, ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new g());
    }

    public void n() {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
            jSONObject.put("mobile", this.f4189a.getText().toString().trim());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().x(ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new a());
    }

    public final void o() {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
            jSONObject.put("code", this.h.getEditContent());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().p(ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new p());
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        WXBaseActivity.transparencyBar(this);
        this.trackPageName = getResources().getString(R.string.inputVerifyCode_activity);
        WXBaseActivity.setLightStatusBar(this, true);
        setContentView(R.layout.activity_verifycode);
        this.u = this;
        this.j = getIntent().getStringExtra("type");
        this.f4189a = (TextView) findViewById(R.id.phoneTxt);
        this.b = (TextView) findViewById(R.id.retrySendBtn);
        this.h = (VerifyCodeView4) findViewById(R.id.verifyCodeView4);
        this.c = (TextView) findViewById(R.id.backBt);
        this.d = (TextView) findViewById(R.id.titleTv);
        this.f4190e = (TextView) findViewById(R.id.tv_retry_send);
        this.f = (TextView) findViewById(R.id.tv_email);
        this.m = (LinearLayout) findViewById(R.id.ll_email);
        this.l = (LinearLayout) findViewById(R.id.ll_phone);
        this.n = (LinearLayout) findViewById(R.id.ll_question);
        this.o = (Button) findViewById(R.id.btn_next);
        this.p = (TextView) findViewById(R.id.tv_question);
        this.q = (EditText) findViewById(R.id.et_answer);
        this.r = (ImageView) findViewById(R.id.iv_change_question);
        this.g = getIntent().getStringExtra("phoneNumber");
        this.k = getIntent().getStringExtra("email");
        this.s = getIntent().getStringExtra("question1");
        this.t = getIntent().getStringExtra("question2");
        this.v = new ui1(this.u);
        if (this.j.equals("type_question")) {
            this.n.setVisibility(0);
            this.m.setVisibility(8);
            this.l.setVisibility(8);
            this.h.setVisibility(8);
            this.p.setText(TextUtils.isEmpty(this.s) ? this.t : this.s);
        } else {
            String str = this.k;
            if (str == null || str.equals("")) {
                this.m.setVisibility(8);
                this.f4189a.setText(this.g);
                this.n.setVisibility(8);
            } else {
                this.l.setVisibility(8);
                this.m.setVisibility(0);
                this.f.setText(this.k);
                this.n.setVisibility(8);
            }
        }
        if (this.j.equals("preVerity")) {
            this.d.setText("预留手机号验证");
        } else if (!this.j.equals("type_question")) {
            this.h.setVisibility(0);
        }
        this.h.setInputCompleteListener(new h());
        this.b.setEnabled(false);
        i iVar = new i(60000L, 1000L);
        this.i = iVar;
        iVar.start();
        this.b.setOnClickListener(new j());
        this.f4190e.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.qk1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f8931a.a(view);
            }
        });
        this.c.setOnClickListener(new k());
        this.o.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.ok1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f8683a.b(view);
            }
        });
        this.q.addTextChangedListener(new l());
        this.r.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.nk1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f8537a.c(view);
            }
        });
    }

    public void p() {
        if (this.j.equals("bind")) {
            if (TextUtils.isEmpty(this.k)) {
                n();
                return;
            } else {
                v();
                return;
            }
        }
        if (this.j.equals("forget")) {
            b(!TextUtils.isEmpty(this.g));
        } else if (this.j.equals("login")) {
            r();
        } else if (this.j.equals("preVerity")) {
            t();
        }
    }

    public final void q() {
        String strC = sh1.c.c(fn1.p);
        if (strC == null || "".equals(strC)) {
            strC = UTDevice.getUtdid(this);
            sh1.c.b(fn1.p, strC);
        }
        mj1.b().a(this.g, this.h.getEditContent(), fn1.H, getPackageName(), strC, fn1.f7620a, "", PushManager.getInstance().getClientid(this)).enqueue(new o());
    }

    public final void r() {
        mj1.b().f(this.f4189a.getText().toString(), fn1.H).enqueue(new d());
    }

    public final void s() {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
            jSONObject.put("code", this.h.getEditContent());
            jSONObject.put("useAsSecureMobile", false);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().n(ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new n());
    }

    public final void t() {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().l(ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new c());
    }

    public final void u() {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
            jSONObject.put("code", this.h.getEditContent());
            jSONObject.put("mobile", this.g);
            jSONObject.put("emailAddress", "");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().u(ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new m());
    }

    public final void v() {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
            jSONObject.put("emailAddress", this.f.getText().toString());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().k(ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new e());
    }

    public /* synthetic */ void a(View view) {
        p();
    }

    public /* synthetic */ void b(View view) {
        if (this.o.isClickable()) {
            this.v.show();
            m();
        }
    }

    public /* synthetic */ void c(View view) {
        qi1 qi1Var = new qi1(this.u, this.s, this.t);
        this.w = qi1Var;
        qi1Var.show();
        this.w.a(new qi1.a() { // from class: supwisdom.pk1
            @Override // supwisdom.qi1.a
            public final void a(int i2) {
                this.f8802a.c(i2);
            }
        });
    }

    public void b(boolean z) {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
            jSONObject.put("checkType", z ? "mobile" : "emailAddress");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().v(ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new b());
    }

    public /* synthetic */ void c(int i2) {
        this.w.dismiss();
        this.p.setText(i2 == 1 ? this.s : this.t);
    }
}
