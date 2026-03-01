package com.supwisdom.superapp.ui.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.igexin.sdk.PushManager;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.superapp.service.model.Response;
import com.supwisdom.zueb.R;
import com.ta.utdid2.device.UTDevice;
import com.taobao.weex.common.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import supwisdom.fn1;
import supwisdom.in1;
import supwisdom.jn1;
import supwisdom.mj1;
import supwisdom.sh1;
import supwisdom.vi1;

/* JADX INFO: loaded from: classes2.dex */
public class MultiAccountLoginAt extends WXBaseActivity implements in1.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public JSONArray f4274a;
    public RecyclerView b;
    public ImageView c;
    public vi1 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f4275e;
    public String f = "";

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MultiAccountLoginAt.this.finish();
        }
    }

    public class b implements Callback<Response<JSONObject>> {
        public b(String str) {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            MultiAccountLoginAt.this.d.dismiss();
            Toast.makeText(MultiAccountLoginAt.this, MultiAccountLoginAt.this.getResources().getString(R.string.net_error), 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody = response.body();
            MultiAccountLoginAt.this.d.dismiss();
            if (response.code() != 200 || responseBody.code != 0) {
                MultiAccountLoginAt multiAccountLoginAt = MultiAccountLoginAt.this;
                Toast.makeText(multiAccountLoginAt, multiAccountLoginAt.getResources().getString(R.string.login_error), 0).show();
                return;
            }
            fn1.w = responseBody.data.getString("idToken");
            responseBody.data.getBoolean("userNonCompleted").booleanValue();
            sh1.c.b(fn1.o, fn1.w);
            MultiAccountLoginAt multiAccountLoginAt2 = MultiAccountLoginAt.this;
            in1.b(multiAccountLoginAt2, multiAccountLoginAt2);
            jn1.a();
        }
    }

    public class c implements Callback<Response<JSONObject>> {
        public c(String str) {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            MultiAccountLoginAt.this.d.dismiss();
            Toast.makeText(MultiAccountLoginAt.this, MultiAccountLoginAt.this.getResources().getString(R.string.net_error), 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody = response.body();
            MultiAccountLoginAt.this.d.dismiss();
            if (response.code() != 200 || responseBody.code != 0) {
                MultiAccountLoginAt multiAccountLoginAt = MultiAccountLoginAt.this;
                Toast.makeText(multiAccountLoginAt, multiAccountLoginAt.getResources().getString(R.string.login_error), 0).show();
                return;
            }
            fn1.w = responseBody.data.getString("idToken");
            responseBody.data.getBoolean("userNonCompleted").booleanValue();
            sh1.c.b(fn1.o, fn1.w);
            MultiAccountLoginAt multiAccountLoginAt2 = MultiAccountLoginAt.this;
            in1.b(multiAccountLoginAt2, multiAccountLoginAt2);
            jn1.a();
        }
    }

    public class d implements Callback<Response<JSONObject>> {
        public d(String str) {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            MultiAccountLoginAt.this.d.dismiss();
            Toast.makeText(MultiAccountLoginAt.this, R.string.net_error, 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody = response.body();
            MultiAccountLoginAt.this.d.dismiss();
            if (response.code() != 200 || responseBody.code != 0) {
                Toast.makeText(MultiAccountLoginAt.this, R.string.login_error, 0).show();
                return;
            }
            fn1.w = responseBody.data.getString("idToken");
            responseBody.data.getBoolean("userNonCompleted").booleanValue();
            sh1.c.b(fn1.o, fn1.w);
            MultiAccountLoginAt multiAccountLoginAt = MultiAccountLoginAt.this;
            in1.b(multiAccountLoginAt, multiAccountLoginAt);
            jn1.a();
        }
    }

    public class e extends RecyclerView.g {

        public class a implements View.OnClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ JSONObject f4281a;

            public a(JSONObject jSONObject) {
                this.f4281a = jSONObject;
            }

            /* JADX WARN: Removed duplicated region for block: B:18:0x003c  */
            @Override // android.view.View.OnClickListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onClick(android.view.View r5) {
                /*
                    r4 = this;
                    com.supwisdom.superapp.ui.activity.MultiAccountLoginAt$e r5 = com.supwisdom.superapp.ui.activity.MultiAccountLoginAt.e.this
                    com.supwisdom.superapp.ui.activity.MultiAccountLoginAt r5 = com.supwisdom.superapp.ui.activity.MultiAccountLoginAt.this
                    java.lang.String r5 = com.supwisdom.superapp.ui.activity.MultiAccountLoginAt.a(r5)
                    int r0 = r5.hashCode()
                    r1 = -1144717956(0xffffffffbbc4fd7c, float:-0.006011663)
                    r2 = 2
                    r3 = 1
                    if (r0 == r1) goto L32
                    r1 = 391949710(0x175cad8e, float:7.130489E-25)
                    if (r0 == r1) goto L28
                    r1 = 1849083845(0x6e36c7c5, float:1.4141945E28)
                    if (r0 == r1) goto L1e
                    goto L3c
                L1e:
                    java.lang.String r0 = "federatedLogin"
                    boolean r5 = r5.equals(r0)
                    if (r5 == 0) goto L3c
                    r5 = 1
                    goto L3d
                L28:
                    java.lang.String r0 = "passwordLogin"
                    boolean r5 = r5.equals(r0)
                    if (r5 == 0) goto L3c
                    r5 = 0
                    goto L3d
                L32:
                    java.lang.String r0 = "codeLogin"
                    boolean r5 = r5.equals(r0)
                    if (r5 == 0) goto L3c
                    r5 = 2
                    goto L3d
                L3c:
                    r5 = -1
                L3d:
                    java.lang.String r0 = "id"
                    if (r5 == 0) goto L62
                    if (r5 == r3) goto L54
                    if (r5 == r2) goto L46
                    goto L6f
                L46:
                    com.supwisdom.superapp.ui.activity.MultiAccountLoginAt$e r5 = com.supwisdom.superapp.ui.activity.MultiAccountLoginAt.e.this
                    com.supwisdom.superapp.ui.activity.MultiAccountLoginAt r5 = com.supwisdom.superapp.ui.activity.MultiAccountLoginAt.this
                    com.alibaba.fastjson.JSONObject r1 = r4.f4281a
                    java.lang.String r0 = r1.getString(r0)
                    com.supwisdom.superapp.ui.activity.MultiAccountLoginAt.c(r5, r0)
                    goto L6f
                L54:
                    com.supwisdom.superapp.ui.activity.MultiAccountLoginAt$e r5 = com.supwisdom.superapp.ui.activity.MultiAccountLoginAt.e.this
                    com.supwisdom.superapp.ui.activity.MultiAccountLoginAt r5 = com.supwisdom.superapp.ui.activity.MultiAccountLoginAt.this
                    com.alibaba.fastjson.JSONObject r1 = r4.f4281a
                    java.lang.String r0 = r1.getString(r0)
                    com.supwisdom.superapp.ui.activity.MultiAccountLoginAt.b(r5, r0)
                    goto L6f
                L62:
                    com.supwisdom.superapp.ui.activity.MultiAccountLoginAt$e r5 = com.supwisdom.superapp.ui.activity.MultiAccountLoginAt.e.this
                    com.supwisdom.superapp.ui.activity.MultiAccountLoginAt r5 = com.supwisdom.superapp.ui.activity.MultiAccountLoginAt.this
                    com.alibaba.fastjson.JSONObject r1 = r4.f4281a
                    java.lang.String r0 = r1.getString(r0)
                    com.supwisdom.superapp.ui.activity.MultiAccountLoginAt.a(r5, r0)
                L6f:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.supwisdom.superapp.ui.activity.MultiAccountLoginAt.e.a.onClick(android.view.View):void");
            }
        }

        public class b extends RecyclerView.b0 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public TextView f4282a;
            public TextView b;

            public b(e eVar, View view) {
                super(view);
                this.f4282a = (TextView) view.findViewById(R.id.item_muti_account_identity);
                this.b = (TextView) view.findViewById(R.id.item_muti_account_name);
            }
        }

        public e() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int getItemCount() {
            JSONArray jSONArray = MultiAccountLoginAt.this.f4274a;
            if (jSONArray == null) {
                return 0;
            }
            return jSONArray.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public void onBindViewHolder(RecyclerView.b0 b0Var, int i) {
            b bVar = (b) b0Var;
            JSONArray jSONArray = MultiAccountLoginAt.this.f4274a;
            if (jSONArray == null || jSONArray.size() == 0) {
                return;
            }
            JSONObject jSONObject = MultiAccountLoginAt.this.f4274a.getJSONObject(i);
            bVar.b.setText(jSONObject.getString("username"));
            bVar.f4282a.setText(jSONObject.getString("identityTypeName"));
            bVar.itemView.setOnClickListener(new a(jSONObject));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public RecyclerView.b0 onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new b(this, LayoutInflater.from(MultiAccountLoginAt.this).inflate(R.layout.item_muti_account, viewGroup, false));
        }
    }

    public final void e(String str) {
        this.d.show();
        mj1.b().k(this.f4275e, str, PushManager.getInstance().getClientid(this)).enqueue(new c(str));
    }

    public final void f(String str) {
        this.d.show();
        String strC = sh1.c.c(fn1.p);
        if (strC == null || "".equals(strC)) {
            strC = UTDevice.getUtdid(this);
            sh1.c.b(fn1.p, strC);
        }
        mj1.b().g(getPackageName(), strC, this.f4275e, str).enqueue(new b(str));
    }

    public final void g(String str) {
        mj1.b().d(fn1.H, str, PushManager.getInstance().getClientid(this)).enqueue(new d(str));
    }

    @Override // supwisdom.in1.d
    public void k() {
        openMini();
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.trackPageName = getResources().getString(R.string.multiAccount_activity);
        WXBaseActivity.setLightStatusBar(this, true);
        setStatusBarVisibility(true);
        setContentView(R.layout.multi_account_login_at);
        String stringExtra = getIntent().getStringExtra("accountJA");
        getIntent().getStringExtra(Constants.Value.PASSWORD);
        this.f4275e = getIntent().getStringExtra("cid");
        this.f = getIntent().getStringExtra("loginType");
        getIntent().getStringExtra("mfaState");
        this.f4274a = JSON.parseArray(stringExtra);
        this.b = (RecyclerView) findViewById(R.id.muti_account_login_rv);
        this.c = (ImageView) findViewById(R.id.muti_account_login_back);
        this.b.setLayoutManager(new LinearLayoutManager(this));
        this.b.setAdapter(new e());
        this.d = new vi1(this);
        this.c.setOnClickListener(new a());
    }
}
