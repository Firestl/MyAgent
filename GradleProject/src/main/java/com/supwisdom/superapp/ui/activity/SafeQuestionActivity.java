package com.supwisdom.superapp.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSONObject;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.superapp.service.model.Response;
import com.supwisdom.zueb.R;
import java.util.ArrayList;
import org.json.JSONException;
import retrofit2.Call;
import retrofit2.Callback;
import supwisdom.ct1;
import supwisdom.ej1;
import supwisdom.fn1;
import supwisdom.mj1;
import supwisdom.ui1;
import supwisdom.xs1;

/* JADX INFO: loaded from: classes2.dex */
public class SafeQuestionActivity extends WXBaseActivity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f4340a;
    public TextView b;
    public ImageView c;
    public ImageView d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public EditText f4341e;
    public EditText f;
    public TextView g;
    public Button h;
    public ArrayList<String> i;
    public ej1 j;
    public ui1 k;
    public TextWatcher l;
    public int m = -1;
    public String n = null;

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
            if (TextUtils.isEmpty(SafeQuestionActivity.this.f4341e.getText().toString()) || TextUtils.isEmpty(SafeQuestionActivity.this.f.getText().toString())) {
                SafeQuestionActivity.this.h.setTextColor(SafeQuestionActivity.this.getResources().getColor(R.color.color_B6BAC0));
                SafeQuestionActivity.this.h.setBackground(SafeQuestionActivity.this.getResources().getDrawable(R.drawable.shape_radius22_colorf1f3f6));
                SafeQuestionActivity.this.h.setSelected(false);
            } else {
                SafeQuestionActivity.this.h.setTextColor(SafeQuestionActivity.this.getResources().getColor(R.color.ffffff));
                SafeQuestionActivity.this.h.setBackground(SafeQuestionActivity.this.getResources().getDrawable(R.drawable.shape_radius22_colored663f));
                SafeQuestionActivity.this.h.setSelected(true);
            }
        }
    }

    public class b implements DialogInterface.OnDismissListener {
        public b() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            SafeQuestionActivity.this.i.add(SafeQuestionActivity.this.m, SafeQuestionActivity.this.n);
            String strC = SafeQuestionActivity.this.j.c();
            if (TextUtils.isEmpty(strC)) {
                return;
            }
            if (SafeQuestionActivity.this.c.isSelected()) {
                SafeQuestionActivity.this.f4340a.setText("问题1：" + strC);
                return;
            }
            SafeQuestionActivity.this.b.setText("问题2：" + strC);
        }
    }

    public class c implements Callback<Response<JSONObject>> {
        public c() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            SafeQuestionActivity.this.e("网络错误");
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null) {
                SafeQuestionActivity.this.e("设置失败");
                return;
            }
            if (response.body().data != null) {
                fn1.H = response.body().data.getString("nonce");
            }
            SafeQuestionActivity.this.o();
        }
    }

    public class d implements Callback<Response<JSONObject>> {
        public d() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            SafeQuestionActivity.this.e("网络错误");
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null) {
                SafeQuestionActivity.this.e("设置失败");
                return;
            }
            if (response.body().data != null) {
                fn1.H = response.body().data.getString("nonce");
            }
            SafeQuestionActivity.this.e(response.body().data.getString("message"));
            SafeQuestionActivity.this.setResult(-1);
            SafeQuestionActivity.this.finish();
        }
    }

    public final void l() {
        this.c.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.jl1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f8074a.a(view);
            }
        });
        this.d.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.il1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f7963a.b(view);
            }
        });
        this.h.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.kl1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f8183a.c(view);
            }
        });
        this.g.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.hl1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f7848a.d(view);
            }
        });
        a aVar = new a();
        this.l = aVar;
        this.f.addTextChangedListener(aVar);
        this.f4341e.addTextChangedListener(this.l);
        this.j.setOnDismissListener(new b());
    }

    public void m() {
        mj1.b().d(fn1.w).enqueue(new c());
    }

    public final void n() {
        this.f4340a = (TextView) findViewById(R.id.tv_question1);
        this.b = (TextView) findViewById(R.id.tv_question2);
        this.c = (ImageView) findViewById(R.id.iv_question1);
        this.d = (ImageView) findViewById(R.id.iv_question2);
        this.f4341e = (EditText) findViewById(R.id.et_question1);
        this.f = (EditText) findViewById(R.id.et_question2);
        this.h = (Button) findViewById(R.id.btn_sure);
        this.g = (TextView) findViewById(R.id.tv_back);
        ArrayList<String> arrayList = this.i;
        if (arrayList != null || arrayList.size() >= 2) {
            this.f4340a.setText("问题1：" + this.i.get(0));
            this.b.setText("问题2：" + this.i.get(1));
        }
    }

    public final void o() {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
            jSONObject.put("question1", this.f4340a.getText().toString().substring(4));
            jSONObject.put("question1Answer", this.f4341e.getText().toString());
            jSONObject.put("question2", this.b.getText().toString().substring(4));
            jSONObject.put("question2Answer", this.f.getText().toString());
        } catch (JSONException unused) {
        }
        mj1.b().w(fn1.w, ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new d());
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_safe_question);
        Intent intent = getIntent();
        this.i = new ArrayList<>();
        try {
            int intExtra = intent.getIntExtra("number", 0);
            for (int i = 0; i < intExtra; i++) {
                this.i.add(intent.getStringExtra("question" + i));
            }
        } catch (Exception unused) {
        }
        this.j = new ej1(this);
        this.k = new ui1(this);
        n();
        l();
    }

    public /* synthetic */ void b(View view) {
        for (int i = 0; i < this.i.size(); i++) {
            if (this.i.get(i).equals(this.f4340a.getText().toString().substring(4))) {
                this.m = i;
                this.n = this.i.get(i);
                this.i.remove(i);
            }
        }
        this.j.a(this.i);
        this.j.show();
        this.c.setSelected(false);
    }

    public /* synthetic */ void c(View view) {
        if (this.h.isSelected()) {
            this.k.show();
            m();
        }
    }

    public /* synthetic */ void d(View view) {
        finish();
    }

    public final void e(String str) {
        ui1 ui1Var = this.k;
        if (ui1Var != null) {
            ui1Var.dismiss();
        }
        Toast.makeText(this, str, 0).show();
    }

    public /* synthetic */ void a(View view) {
        for (int i = 0; i < this.i.size(); i++) {
            if (this.i.get(i).equals(this.b.getText().toString().substring(4))) {
                this.m = i;
                this.n = this.i.get(i);
                this.i.remove(i);
            }
        }
        this.j.a(this.i);
        this.j.show();
        this.c.setSelected(true);
    }
}
