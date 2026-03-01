package com.supwisdom.superapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;
import com.supwisdom.superapp.speech.BaiduASRDialog;
import com.supwisdom.superapp.ui.activity.H5Activity;
import com.supwisdom.superapp.view.VoiceLineView;
import com.taobao.weex.appfram.pickers.WXPickersModule;
import io.dcloud.common.constant.AbsoluteConst;
import java.io.File;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import supwisdom.ct1;
import supwisdom.et1;
import supwisdom.fn1;
import supwisdom.kh1;
import supwisdom.mj1;
import supwisdom.rj1;
import supwisdom.sh1;
import supwisdom.tm1;
import supwisdom.xs1;

/* JADX INFO: loaded from: classes2.dex */
public class WakeUpActivity extends BaiduASRDialog implements Runnable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f3987e;
    public TextView f;
    public ImageView g;
    public RecyclerView h;
    public g i;
    public JsonArray j;
    public RelativeLayout k;
    public RelativeLayout l;
    public VoiceLineView m;
    public ImageButton n;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            WakeUpActivity.this.finish();
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            WakeUpActivity.this.l.setVisibility(0);
            WakeUpActivity.this.k.setVisibility(8);
            WakeUpActivity.this.f.setText("你可以说");
            WakeUpActivity.this.f3987e.setText("查看我的日程");
            rj1.e().a();
        }
    }

    public class c implements Callback<et1> {
        public c() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<et1> call, Throwable th) {
            WakeUpActivity wakeUpActivity = WakeUpActivity.this;
            Toast.makeText(wakeUpActivity, wakeUpActivity.getResources().getString(com.supwisdom.zueb.R.string.net_error), 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<et1> call, Response<et1> response) {
            try {
                WakeUpActivity.this.f(new JSONObject(response.body().string()).optString("access_token"));
            } catch (IOException | JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    public class d implements Callback<et1> {
        public d() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<et1> call, Throwable th) {
            WakeUpActivity wakeUpActivity = WakeUpActivity.this;
            Toast.makeText(wakeUpActivity, wakeUpActivity.getResources().getString(com.supwisdom.zueb.R.string.net_error), 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<et1> call, Response<et1> response) {
            try {
                JSONArray jSONArrayOptJSONArray = new JSONObject(response.body().string()).optJSONArray(WXPickersModule.KEY_ITEMS);
                String str = "";
                if (jSONArrayOptJSONArray == null) {
                    return;
                }
                int i = 0;
                while (true) {
                    if (i >= jSONArrayOptJSONArray.length()) {
                        break;
                    }
                    String strOptString = jSONArrayOptJSONArray.optJSONObject(i).optString("ne");
                    String strOptString2 = jSONArrayOptJSONArray.optJSONObject(i).optString(AbsoluteConst.XML_ITEM);
                    if (strOptString.equals("SE")) {
                        str = strOptString2;
                        break;
                    } else {
                        i++;
                        str = strOptString2;
                    }
                }
                Log.d("keyword", str);
                WakeUpActivity.this.g(str);
            } catch (IOException | JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    public class e implements Callback<com.supwisdom.superapp.service.model.Response<JsonObject>> {
        public e() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<com.supwisdom.superapp.service.model.Response<JsonObject>> call, Throwable th) {
            WakeUpActivity wakeUpActivity = WakeUpActivity.this;
            Toast.makeText(wakeUpActivity, wakeUpActivity.getResources().getString(com.supwisdom.zueb.R.string.net_error), 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<com.supwisdom.superapp.service.model.Response<JsonObject>> call, Response<com.supwisdom.superapp.service.model.Response<JsonObject>> response) {
            if (response.code() != 200) {
                Toast.makeText(WakeUpActivity.this, "搜索失败", 0).show();
                return;
            }
            JsonObject jsonObject = response.body().data;
            Log.d("keyword", "搜索结果------" + new Gson().toJson((JsonElement) jsonObject));
            WakeUpActivity.this.j = jsonObject.getAsJsonArray("searchHits").isJsonNull() ? null : jsonObject.getAsJsonArray("searchHits");
            WakeUpActivity.this.i.notifyDataSetChanged();
            WakeUpActivity.this.k.setVisibility(0);
            WakeUpActivity.this.l.setVisibility(8);
        }
    }

    public class f implements Callback<com.supwisdom.superapp.service.model.Response<com.alibaba.fastjson.JSONObject>> {
        public f() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<com.supwisdom.superapp.service.model.Response<com.alibaba.fastjson.JSONObject>> call, Throwable th) {
            WakeUpActivity wakeUpActivity = WakeUpActivity.this;
            Toast.makeText(wakeUpActivity, wakeUpActivity.getResources().getString(com.supwisdom.zueb.R.string.net_error), 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<com.supwisdom.superapp.service.model.Response<com.alibaba.fastjson.JSONObject>> call, Response<com.supwisdom.superapp.service.model.Response<com.alibaba.fastjson.JSONObject>> response) {
            if (response.code() != 200) {
                Toast.makeText(WakeUpActivity.this, "搜索失败", 0).show();
                return;
            }
            com.alibaba.fastjson.JSONObject jSONObject = response.body().data;
            Log.d("keyword", "id查询结果------" + new Gson().toJson(jSONObject));
            WakeUpActivity.this.h(jSONObject.getString("serviceUrl"));
            WakeUpActivity.this.finish();
        }
    }

    public class g extends RecyclerView.g {

        public class a implements View.OnClickListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ String f3995a;

            public a(String str) {
                this.f3995a = str;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WakeUpActivity.this.e(this.f3995a);
            }
        }

        public class b extends RecyclerView.b0 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public TextView f3996a;
            public TextView b;
            public ImageView c;

            public b(g gVar, View view) {
                super(view);
                this.f3996a = (TextView) view.findViewById(com.supwisdom.zueb.R.id.serviceNameTxt);
                this.b = (TextView) view.findViewById(com.supwisdom.zueb.R.id.serviceTypeTxt);
                this.c = (ImageView) view.findViewById(com.supwisdom.zueb.R.id.servicePic);
            }
        }

        public g() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int getItemCount() {
            if (WakeUpActivity.this.j == null) {
                return 0;
            }
            return WakeUpActivity.this.j.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public void onBindViewHolder(RecyclerView.b0 b0Var, int i) {
            b bVar = (b) b0Var;
            JsonObject asJsonObject = WakeUpActivity.this.j.get(i).getAsJsonObject().isJsonNull() ? null : WakeUpActivity.this.j.get(i).getAsJsonObject().getAsJsonObject("content");
            String asString = WakeUpActivity.this.j.get(i).getAsJsonObject().isJsonNull() ? null : WakeUpActivity.this.j.get(i).getAsJsonObject().get("id").getAsString();
            if (asJsonObject != null) {
                bVar.f3996a.setText(asJsonObject.get("serveName") == null ? "" : asJsonObject.get("serveName").getAsString());
                JsonArray asJsonArray = asJsonObject.getAsJsonArray("serveLable");
                if (asJsonArray != null && asJsonArray.size() != 0) {
                    bVar.b.setText(asJsonArray.get(0).getAsString());
                }
                kh1 kh1VarA = Picasso.a((Context) WakeUpActivity.this).a(asJsonObject.get("serveImageUrl") != null ? asJsonObject.get("serveImageUrl").getAsString() : "");
                kh1VarA.a(new tm1());
                kh1VarA.a(bVar.c);
            }
            bVar.itemView.setOnClickListener(new a(asString));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public RecyclerView.b0 onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new b(this, LayoutInflater.from(WakeUpActivity.this).inflate(com.supwisdom.zueb.R.layout.item_service, viewGroup, false));
        }
    }

    public final void g(String str) {
        mj1.b().a(sh1.c.c(fn1.o), "mobile", "mobile", "1", "0", "0", str).enqueue(new e());
    }

    public final void h(String str) {
        if (str.contains(".wgt")) {
            String strSubstring = str.substring(0, str.indexOf(AbsoluteConst.JSON_VALUE_POSITION_STATIC));
            Intent intent = new Intent();
            intent.setClass(this, H5Activity.class);
            intent.setData(Uri.parse(strSubstring));
            startActivity(intent);
            return;
        }
        if (str.contains("js")) {
            return;
        }
        Intent intent2 = new Intent();
        intent2.setClass(this, H5Activity.class);
        intent2.setData(Uri.parse(str));
        startActivity(intent2);
    }

    @Override // com.supwisdom.superapp.speech.BaiduASRDialog
    public void o() {
        this.f.setText("你正在说");
    }

    @Override // com.supwisdom.superapp.speech.BaiduASRDialog, com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        WXBaseActivity.transparencyBar(this);
        WXBaseActivity.setLightStatusBar(this, false);
        setContentView(com.supwisdom.zueb.R.layout.activity_wakeup);
        this.f3987e = (TextView) findViewById(com.supwisdom.zueb.R.id.wakeup_txt);
        this.f = (TextView) findViewById(com.supwisdom.zueb.R.id.wakeup_title);
        this.g = (ImageView) findViewById(com.supwisdom.zueb.R.id.wakeup_back);
        this.h = (RecyclerView) findViewById(com.supwisdom.zueb.R.id.wakeup_rv);
        this.l = (RelativeLayout) findViewById(com.supwisdom.zueb.R.id.searchRl);
        this.m = (VoiceLineView) findViewById(com.supwisdom.zueb.R.id.voiceLine);
        this.k = (RelativeLayout) findViewById(com.supwisdom.zueb.R.id.searchResultRl);
        this.n = (ImageButton) findViewById(com.supwisdom.zueb.R.id.restartRec);
        this.g.setOnClickListener(new a());
        this.n.setOnClickListener(new b());
        this.i = new g();
        this.h.setLayoutManager(new GridLayoutManager(this, 3));
        this.h.setAdapter(this.i);
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "rec.log");
        if (file.exists()) {
            return;
        }
        try {
            file.createNewFile();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.supwisdom.superapp.speech.BaiduASRDialog, com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        rj1.e().c();
        rj1.e().b();
        super.onStop();
    }

    @Override // com.supwisdom.superapp.speech.BaiduASRDialog
    public void p() {
    }

    @Override // com.supwisdom.superapp.speech.BaiduASRDialog
    public void q() {
    }

    @Override // com.supwisdom.superapp.speech.BaiduASRDialog
    public void r() {
    }

    @Override // java.lang.Runnable
    public void run() {
    }

    public final void e(String str) {
        mj1.b().h(sh1.c.c(fn1.o), "mobile", "mobile", str).enqueue(new f());
    }

    public final void f(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("text", this.f3987e.getText().toString().trim());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().a(str, "UTF-8", ct1.create(xs1.b("application/json; charset=UTF-8"), jSONObject.toString())).enqueue(new d());
    }

    @Override // com.supwisdom.superapp.speech.BaiduASRDialog
    public void b(String[] strArr) {
        this.f3987e.setText("“" + strArr[0] + "”");
    }

    @Override // com.supwisdom.superapp.speech.BaiduASRDialog
    public void a(float f2) {
        this.m.setVolume((int) (f2 > 1.0f ? Math.log10(f2) * 20.0d : 0.0d));
    }

    @Override // com.supwisdom.superapp.speech.BaiduASRDialog
    public void a(int i, int i2) {
        Log.d("MainActivity---wake", i2 + "");
        rj1.e().c();
        mj1.b().g("client_credentials", fn1.I, fn1.J).enqueue(new c());
    }
}
