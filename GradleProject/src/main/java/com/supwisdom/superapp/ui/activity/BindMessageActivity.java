package com.supwisdom.superapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSONObject;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.superapp.service.model.Response;
import com.supwisdom.zueb.R;
import org.json.JSONException;
import retrofit2.Call;
import retrofit2.Callback;
import supwisdom.ct1;
import supwisdom.fn1;
import supwisdom.mj1;
import supwisdom.ul1;
import supwisdom.xs1;

/* JADX INFO: loaded from: classes2.dex */
public class BindMessageActivity extends WXBaseActivity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4032a;
    public TextView b;
    public TextView c;
    public TextView d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public EditText f4033e;
    public EditText f;
    public Button g;
    public volatile boolean h = false;
    public String i = "";

    public class a implements TextWatcher {
        public a() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (TextUtils.isEmpty(charSequence.toString())) {
                BindMessageActivity.this.d.setClickable(false);
                BindMessageActivity.this.h = false;
            } else {
                BindMessageActivity.this.d.setClickable(true);
                BindMessageActivity.this.h = true;
            }
        }
    }

    public class b implements TextWatcher {
        public b() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (TextUtils.isEmpty(charSequence.toString()) || !BindMessageActivity.this.h) {
                BindMessageActivity.this.g.setClickable(false);
                BindMessageActivity.this.g.setBackground(BindMessageActivity.this.getResources().getDrawable(R.drawable.shape_radius22_colorf1f3f6));
                BindMessageActivity.this.g.setTextColor(BindMessageActivity.this.getResources().getColor(R.color.color_B6BAC0));
            } else {
                BindMessageActivity.this.g.setClickable(true);
                BindMessageActivity.this.g.setBackground(BindMessageActivity.this.getResources().getDrawable(R.drawable.shape_radius22_colored663f));
                BindMessageActivity.this.g.setTextColor(BindMessageActivity.this.getResources().getColor(R.color.ffffff));
            }
        }
    }

    public class c implements Callback<Response<JSONObject>> {
        public c() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Toast.makeText(BindMessageActivity.this, "初始化错误，请重试", 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null) {
                Toast.makeText(BindMessageActivity.this, "接口错误，请重试", 0).show();
                return;
            }
            if (response.body().data != null) {
                fn1.H = response.body().data.getString("nonce");
            }
            BindMessageActivity.this.p();
        }
    }

    public class d implements Callback<Response<JSONObject>> {
        public d() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Toast.makeText(BindMessageActivity.this, "初始化错误，请重试", 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null) {
                Toast.makeText(BindMessageActivity.this, "接口错误，请重试", 0).show();
                return;
            }
            if (response.body().data != null) {
                fn1.H = response.body().data.getString("nonce");
            }
            BindMessageActivity.this.p();
        }
    }

    public class e implements Callback<Response<JSONObject>> {
        public e() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Toast.makeText(BindMessageActivity.this, "初始化错误，请返回重试", 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null) {
                Toast.makeText(BindMessageActivity.this, "接口错误，请重试", 0).show();
                return;
            }
            if (response.body().data != null) {
                fn1.H = response.body().data.getString("nonce");
            }
            Toast.makeText(BindMessageActivity.this, response.body().message, 0).show();
        }
    }

    public class f implements Callback<Response<JSONObject>> {
        public f() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Toast.makeText(BindMessageActivity.this, "初始化错误，请返回重试", 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null) {
                Toast.makeText(BindMessageActivity.this, "接口错误，请重试", 0).show();
                return;
            }
            if (response.body().data != null) {
                fn1.H = response.body().data.getString("nonce");
            }
            Toast.makeText(BindMessageActivity.this, response.body().message, 0).show();
        }
    }

    public class g implements Callback<Response<JSONObject>> {
        public g() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Toast.makeText(BindMessageActivity.this, "初始化错误，请返回重试", 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null) {
                Toast.makeText(BindMessageActivity.this, "接口错误，请重试", 0).show();
                return;
            }
            if (response.body().data == null) {
                return;
            }
            if (response.body().data.getString("nonce") != null) {
                fn1.H = response.body().data.getString("nonce");
            }
            if (TextUtils.isEmpty(response.body().message) || !response.body().message.equals("绑定成功")) {
                Toast.makeText(BindMessageActivity.this, response.body().message, 0).show();
                return;
            }
            BindMessageActivity bindMessageActivity = BindMessageActivity.this;
            bindMessageActivity.i = bindMessageActivity.f4033e.getText().toString().trim();
            BindMessageActivity.this.finish();
        }
    }

    public class h implements Callback<Response<JSONObject>> {
        public h() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Toast.makeText(BindMessageActivity.this, "初始化错误，请返回重试", 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null) {
                Toast.makeText(BindMessageActivity.this, "接口错误，请重试", 0).show();
                return;
            }
            if (response.body().data == null) {
                return;
            }
            if (response.body().data.getString("nonce") != null) {
                fn1.H = response.body().data.getString("nonce");
            }
            if (TextUtils.isEmpty(response.body().message) || !response.body().message.equals("绑定成功")) {
                Toast.makeText(BindMessageActivity.this, response.body().message, 0).show();
                return;
            }
            BindMessageActivity bindMessageActivity = BindMessageActivity.this;
            bindMessageActivity.i = bindMessageActivity.f4033e.getText().toString().trim();
            BindMessageActivity.this.finish();
        }
    }

    @Override // android.app.Activity
    public void finish() {
        if (!TextUtils.isEmpty(this.i)) {
            setResult(-1, new Intent().putExtra("result", this.i));
        }
        super.finish();
    }

    public final void l() {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        if (this.f4032a == 2) {
            try {
                jSONObject.put("nonce", fn1.H);
                jSONObject.put("code", this.f.getText().toString().trim());
                jSONObject.put("emailAddress", this.f4033e.getText().toString().trim());
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            mj1.b().A(fn1.w, ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new g());
            return;
        }
        try {
            jSONObject.put("nonce", fn1.H);
            jSONObject.put("code", this.f.getText().toString().trim());
            jSONObject.put("mobile", this.f4033e.getText().toString().trim());
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
        mj1.b().o(fn1.w, ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new h());
    }

    public final void m() {
        if (this.f4032a == 2) {
            mj1.b().f(fn1.w).enqueue(new c());
        } else {
            mj1.b().j(fn1.w).enqueue(new d());
        }
    }

    public final void n() {
        this.f4033e.addTextChangedListener(new a());
        this.f.addTextChangedListener(new b());
        this.d.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.uj1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f9417a.a(view);
            }
        });
        this.g.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.vj1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f9515a.b(view);
            }
        });
        this.b.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.wj1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f9638a.c(view);
            }
        });
    }

    public final void o() {
        this.b = (TextView) findViewById(R.id.tv_back);
        this.c = (TextView) findViewById(R.id.tv_title);
        this.d = (TextView) findViewById(R.id.tv_send);
        this.f = (EditText) findViewById(R.id.et_code);
        this.f4033e = (EditText) findViewById(R.id.et_content);
        this.g = (Button) findViewById(R.id.btn_sure);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_bind_message);
        o();
        n();
        int intExtra = getIntent().getIntExtra("type", 1);
        this.f4032a = intExtra;
        if (intExtra == 2) {
            this.c.setText("绑定邮箱");
            this.f4033e.setHint("请输入邮箱地址");
        } else {
            this.c.setText("绑定手机");
            this.f4033e.setHint("请输入手机号");
        }
    }

    public final void p() {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        if (this.f4032a == 2) {
            try {
                jSONObject.put("nonce", fn1.H);
                jSONObject.put("emailAddress", this.f4033e.getText().toString().trim());
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            mj1.b().s(fn1.w, ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new e());
            return;
        }
        try {
            jSONObject.put("nonce", fn1.H);
            jSONObject.put("mobile", this.f4033e.getText().toString().trim());
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
        mj1.b().a(fn1.w, ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new f());
    }

    public /* synthetic */ void b(View view) {
        l();
    }

    public /* synthetic */ void c(View view) {
        finish();
    }

    public /* synthetic */ void a(View view) {
        if (this.d.getText().toString().equals(getResources().getString(R.string.send_code))) {
            m();
            new ul1(this, 60000L, 1000L).start();
        }
    }
}
