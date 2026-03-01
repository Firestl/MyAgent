package com.supwisdom.superapp.ddshare;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;
import com.alibaba.fastjson.JSONObject;
import com.supwisdom.superapp.service.model.Response;
import com.supwisdom.zueb.R;
import com.ta.utdid2.device.UTDevice;
import java.util.UUID;
import retrofit2.Call;
import retrofit2.Callback;
import supwisdom.fn1;
import supwisdom.mj1;
import supwisdom.nr;
import supwisdom.or;
import supwisdom.qr;
import supwisdom.rr;
import supwisdom.sh1;
import supwisdom.ui1;
import supwisdom.wr;

/* JADX INFO: loaded from: classes2.dex */
public class DDShareActivity extends Activity implements nr {
    public static or c = null;
    public static Handler d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static String f3997e = "";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f3998a;
    public ui1 b;

    public class a implements Callback<Response<JSONObject>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f3999a;

        public a(String str) {
            this.f3999a = str;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            DDShareActivity.this.b("网络错误");
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null || response.body().data == null) {
                DDShareActivity.this.b("绑定失败");
                return;
            }
            JSONObject jSONObject = response.body().data;
            fn1.H = jSONObject.getString("nonce") == null ? fn1.H : jSONObject.getString("nonce");
            DDShareActivity.this.a(jSONObject.getString("callbackUri"), this.f3999a);
        }
    }

    public class b implements Callback<Response<JSONObject>> {
        public b() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            DDShareActivity.this.b("网络错误");
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null || response.body().data == null) {
                DDShareActivity.this.b("绑定失败");
            } else {
                fn1.H = response.body().data.getString("nonce") == null ? fn1.H : response.body().data.getString("nonce");
                DDShareActivity.this.a();
            }
        }
    }

    public class c implements Callback<Response<JSONObject>> {
        public c() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            DDShareActivity.this.b("网络错误");
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null || response.body().code != 0 || response.body().data == null) {
                DDShareActivity.this.b("绑定失败");
                return;
            }
            fn1.H = response.body().data.getString("nonce") == null ? fn1.H : response.body().data.getString("nonce");
            if (!response.body().data.getBoolean("success").booleanValue()) {
                DDShareActivity.this.b("绑定失败");
                return;
            }
            DDShareActivity.this.b("");
            Message message = new Message();
            message.what = 1;
            message.obj = "success";
            DDShareActivity.d.sendMessage(message);
        }
    }

    @Override // supwisdom.nr
    public void a(qr qrVar) {
    }

    public final void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            Toast.makeText(this.f3998a, str, 0).show();
        }
        this.b.dismiss();
        finish();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ddshare);
        this.f3998a = this;
        ui1 ui1Var = new ui1(this.f3998a);
        this.b = ui1Var;
        ui1Var.show();
        try {
            c.a(getIntent(), this);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // supwisdom.nr
    public void a(rr rrVar) {
        if (rrVar.a() == 100 && (rrVar instanceof wr)) {
            int i = rrVar.f9087a;
            if (i == -2) {
                b("授权取消");
                return;
            }
            if (i != 0) {
                b("授权异常" + rrVar.b);
                return;
            }
            if ("platform".equals(f3997e)) {
                String str = ((wr) rrVar).c;
                Message message = new Message();
                message.what = 2;
                message.obj = str;
                d.sendMessage(message);
                finish();
                return;
            }
            a(((wr) rrVar).c);
            return;
        }
        finish();
    }

    public final void a(String str) {
        String strC = sh1.c.c(fn1.p);
        if (strC == null || "".equals(strC)) {
            strC = UTDevice.getUtdid(this);
            sh1.c.b(fn1.p, strC);
        }
        mj1.b().h(getPackageName(), UUID.randomUUID().toString(), strC).enqueue(new a(str));
    }

    public final void a(String str, String str2) {
        mj1.b().e(str, fn1.H, fn1.w, str2).enqueue(new b());
    }

    public final void a() {
        mj1.b().e(fn1.H, fn1.w).enqueue(new c());
    }
}
