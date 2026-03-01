package com.supwisdom.superapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.alibaba.fastjson.JSONObject;
import com.supwisdom.superapp.service.model.Response;
import com.supwisdom.superapp.view.VerifyCodeView;
import com.supwisdom.superapp.view.VerifyCodeView4;
import com.supwisdom.zueb.R;
import com.taobao.weex.common.Constants;
import org.json.JSONException;
import retrofit2.Call;
import retrofit2.Callback;
import supwisdom.ct1;
import supwisdom.dm1;
import supwisdom.fn1;
import supwisdom.mj1;
import supwisdom.xs1;

/* JADX INFO: loaded from: classes2.dex */
public class VertificateActivity extends AppCompatActivity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4378a = 1;
    public TextView b;
    public TextView c;
    public TextView d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f4379e;
    public TextView f;
    public TextView g;
    public Button h;
    public Button i;
    public EditText j;
    public EditText k;
    public EditText l;
    public VerifyCodeView4 m;
    public ConstraintLayout n;
    public ConstraintLayout o;
    public ConstraintLayout p;
    public ImageButton q;
    public String r;
    public TextWatcher s;
    public boolean t;

    public class a implements Callback<Response<JSONObject>> {
        public a() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Toast.makeText(VertificateActivity.this, "网络错误", 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null) {
                Toast.makeText(VertificateActivity.this, "验证失败", 0).show();
                return;
            }
            if (response.body().data != null) {
                fn1.H = response.body().data.getString("nonce");
            }
            Intent intent = new Intent(VertificateActivity.this, (Class<?>) VertificateFinishActivity.class);
            intent.putExtra("content", VertificateActivity.this.k.getText().toString());
            intent.putExtra("type", 4);
            VertificateActivity.this.startActivity(intent);
            VertificateActivity.this.t = true;
            VertificateActivity.this.finish();
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
            if (VertificateActivity.this.f4378a == 1) {
                VertificateActivity vertificateActivity = VertificateActivity.this;
                vertificateActivity.a(vertificateActivity.h, !TextUtils.isEmpty(charSequence.toString()));
            } else if (VertificateActivity.this.f4378a == 2) {
                VertificateActivity vertificateActivity2 = VertificateActivity.this;
                vertificateActivity2.a(vertificateActivity2.h, true ^ TextUtils.isEmpty(VertificateActivity.this.j.getText().toString().trim()));
            } else {
                VertificateActivity vertificateActivity3 = VertificateActivity.this;
                vertificateActivity3.a(vertificateActivity3.i, (TextUtils.isEmpty(VertificateActivity.this.k.getText().toString().trim()) || TextUtils.isEmpty(VertificateActivity.this.l.getText().toString().trim())) ? false : true);
            }
        }
    }

    public class c implements VerifyCodeView.b {
        public c() {
        }

        @Override // com.supwisdom.superapp.view.VerifyCodeView.b
        public void a() {
            VertificateActivity vertificateActivity = VertificateActivity.this;
            vertificateActivity.e(vertificateActivity.m.getEditContent());
        }

        @Override // com.supwisdom.superapp.view.VerifyCodeView.b
        public void b() {
        }
    }

    public class d implements Callback<Response<JSONObject>> {
        public d() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Toast.makeText(VertificateActivity.this, "网络错误", 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null) {
                Toast.makeText(VertificateActivity.this, "验证失败", 0).show();
                return;
            }
            if (response.body().data != null) {
                fn1.H = response.body().data.getString("nonce");
            }
            if (VertificateActivity.this.f4378a == 1) {
                VertificateActivity.this.b(false);
            } else if (VertificateActivity.this.f4378a == 2) {
                VertificateActivity.this.n();
            } else {
                VertificateActivity.this.l();
            }
        }
    }

    public class e implements Callback<Response<JSONObject>> {
        public e() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Toast.makeText(VertificateActivity.this, "网络错误", 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null || response.body().data == null) {
                Toast.makeText(VertificateActivity.this, "验证失败", 0).show();
                return;
            }
            String string = response.body().data.getString("nonce");
            if (string != null) {
                fn1.H = string;
            }
            String string2 = response.body().data.getString("message");
            if (!string2.equals("验证成功")) {
                Toast.makeText(VertificateActivity.this, string2, 0).show();
                return;
            }
            Intent intent = new Intent(VertificateActivity.this, (Class<?>) VertificateFinishActivity.class);
            intent.putExtra("content", VertificateActivity.this.j.getText().toString());
            intent.putExtra("type", 1);
            VertificateActivity.this.startActivity(intent);
            VertificateActivity.this.t = true;
            VertificateActivity.this.finish();
        }
    }

    public class f implements Callback<Response<JSONObject>> {
        public f() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Toast.makeText(VertificateActivity.this, "网络错误", 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null || response.body().code != 0) {
                Toast.makeText(VertificateActivity.this, response.message(), 0).show();
                return;
            }
            if (response.body().data != null) {
                fn1.H = response.body().data.getString("nonce");
                SpannableString spannableString = new SpannableString("+86 " + response.body().data.getString("preMobile"));
                spannableString.setSpan(new TextAppearanceSpan(VertificateActivity.this, 14), 0, 3, 33);
                spannableString.setSpan(new TextAppearanceSpan(VertificateActivity.this, 16), 4, spannableString.length(), 33);
                VertificateActivity.this.d.setText(spannableString);
            }
        }
    }

    public class g implements Callback<Response<JSONObject>> {
        public g() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Toast.makeText(VertificateActivity.this, "网络错误", 0).show();
            VertificateActivity.this.f.setVisibility(8);
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null || response.body().code != 0) {
                Toast.makeText(VertificateActivity.this, "验证码发送失败", 0).show();
                VertificateActivity.this.f.setVisibility(8);
            } else {
                if (response.body().data != null) {
                    fn1.H = response.body().data.getString("nonce");
                }
                VertificateActivity.this.f.setVisibility(0);
                VertificateActivity.this.m();
            }
        }
    }

    public class h implements Callback<Response<JSONObject>> {
        public h() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Toast.makeText(VertificateActivity.this, "网络错误", 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null || response.body().code != 0) {
                Toast.makeText(VertificateActivity.this, response.message(), 0).show();
                return;
            }
            if (response.body().data == null) {
                Toast.makeText(VertificateActivity.this, response.body().message, 0).show();
                return;
            }
            fn1.H = response.body().data.getString("nonce");
            if (response.body().data.getBoolean("exists").booleanValue()) {
                Toast.makeText(VertificateActivity.this, "手机号已被使用", 0).show();
            }
        }
    }

    public class i implements Callback<Response<JSONObject>> {
        public i() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Toast.makeText(VertificateActivity.this, "网络错误", 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null) {
                Toast.makeText(VertificateActivity.this, "验证失败", 0).show();
                return;
            }
            if (response.body().data != null) {
                fn1.H = response.body().data.getString("nonce");
            }
            if (response.body().message == null) {
                Toast.makeText(VertificateActivity.this, "验证失败", 0).show();
                return;
            }
            if (!response.body().message.equals("验证成功")) {
                Toast.makeText(VertificateActivity.this, response.body().message, 0).show();
                return;
            }
            Intent intent = new Intent(VertificateActivity.this, (Class<?>) VertificateFinishActivity.class);
            intent.putExtra("content", VertificateActivity.this.d.getText().toString());
            intent.putExtra("type", 0);
            VertificateActivity.this.startActivity(intent);
            VertificateActivity.this.t = true;
            VertificateActivity.this.finish();
        }
    }

    @Override // android.app.Activity
    public void finish() {
        if (this.t) {
            setResult(-1);
        }
        super.finish();
    }

    public final void n() {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
            jSONObject.put("identityNo", this.j.getText().toString());
            jSONObject.put("accountName", this.r);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        ct1 ct1VarCreate = ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString());
        Log.e(VertificateActivity.class.getSimpleName(), "+++" + jSONObject.toString());
        mj1.b().t(fn1.w, ct1VarCreate).enqueue(new e());
    }

    public final void o() {
        this.l.setInputType(129);
        this.s = new b();
        this.m.setInputCompleteListener(new c());
        this.b.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.ql1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f8939a.a(view);
            }
        });
        this.g.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.rl1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f9074a.b(view);
            }
        });
        this.h.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.nl1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f8541a.c(view);
            }
        });
        this.f4379e.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.pl1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f8803a.d(view);
            }
        });
        this.j.addTextChangedListener(this.s);
        this.k.addTextChangedListener(this.s);
        this.l.addTextChangedListener(this.s);
        this.q.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.ol1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f8686a.e(view);
            }
        });
        this.i.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.sl1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f9190a.f(view);
            }
        });
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_vertificate);
        q();
        o();
        try {
            this.r = getIntent().getStringExtra("account");
            int intExtra = getIntent().getIntExtra("type", 1);
            this.f4378a = intExtra;
            if (intExtra == 1) {
                this.n.setVisibility(0);
                p();
            } else if (intExtra == 2) {
                this.o.setVisibility(0);
                this.n.setVisibility(8);
                this.c.setText(getResources().getString(R.string.input_vertification));
            } else {
                this.p.setVisibility(0);
                this.n.setVisibility(8);
                this.c.setText(getResources().getString(R.string.account_password_vertificate));
            }
        } catch (Exception unused) {
        }
    }

    public final void p() {
        mj1.b().c(fn1.w).enqueue(new d());
    }

    public final void q() {
        this.b = (TextView) findViewById(R.id.tv_back);
        this.c = (TextView) findViewById(R.id.tv_title);
        this.d = (TextView) findViewById(R.id.tv_mobile);
        this.f4379e = (TextView) findViewById(R.id.tv_change_mode);
        this.g = (TextView) findViewById(R.id.tv_send);
        this.h = (Button) findViewById(R.id.btn_next);
        this.j = (EditText) findViewById(R.id.et_vertification);
        this.n = (ConstraintLayout) findViewById(R.id.cl_mobile);
        this.o = (ConstraintLayout) findViewById(R.id.cl_vertification);
        this.p = (ConstraintLayout) findViewById(R.id.cl_account_password);
        this.m = (VerifyCodeView4) findViewById(R.id.vc_code);
        this.f = (TextView) findViewById(R.id.tv_tips);
        this.k = (EditText) findViewById(R.id.et_account);
        this.l = (EditText) findViewById(R.id.et_password);
        this.q = (ImageButton) findViewById(R.id.ib_eye);
        this.i = (Button) findViewById(R.id.btn_sure);
    }

    public final void r() {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
        } catch (JSONException unused) {
        }
        mj1.b().h(fn1.w, ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new g());
    }

    public /* synthetic */ void c(View view) {
        p();
    }

    public /* synthetic */ void d(View view) {
        InformationPerfectionActivity.x = true;
        finish();
    }

    public /* synthetic */ void e(View view) {
        if (this.q.isSelected()) {
            this.l.setInputType(144);
        } else {
            this.l.setInputType(129);
        }
    }

    public /* synthetic */ void f(View view) {
        p();
    }

    public final void l() {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
            jSONObject.put("accountName", this.k.getText().toString());
            jSONObject.put(Constants.Value.PASSWORD, this.l.getText().toString());
        } catch (JSONException unused) {
        }
        mj1.b().l(fn1.w, ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new a());
    }

    public final void m() {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
        } catch (JSONException unused) {
        }
        mj1.b().B(fn1.w, ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new h());
    }

    public /* synthetic */ void b(View view) {
        if (this.g.getText().toString().equals(getResources().getString(R.string.send_code))) {
            if (!TextUtils.isEmpty(this.d.getText().toString())) {
                r();
            }
            new dm1(this, 60000L, 1000L).start();
        }
    }

    public /* synthetic */ void a(View view) {
        finish();
    }

    public final void e(String str) {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
            jSONObject.put("code", str);
        } catch (JSONException unused) {
        }
        mj1.b().d(fn1.w, ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new i());
    }

    public final void a(Button button, boolean z) {
        if (z) {
            button.setBackground(getResources().getDrawable(R.drawable.shape_radius22_colored663f));
            button.setTextColor(getResources().getColor(R.color.ffffff));
            button.setClickable(true);
        } else {
            button.setBackground(getResources().getDrawable(R.drawable.shape_radius22_colorf1f3f6));
            button.setTextColor(getResources().getColor(R.color.color_B6BAC0));
            button.setClickable(false);
        }
    }

    public final void b(boolean z) {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
            jSONObject.put("identityNo", "522123199610141017");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().u(fn1.w, ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new f());
    }
}
