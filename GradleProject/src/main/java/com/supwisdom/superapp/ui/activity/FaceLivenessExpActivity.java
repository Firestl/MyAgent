package com.supwisdom.superapp.ui.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.alibaba.fastjson.JSONObject;
import com.baidu.idl.face.platform.FaceStatusEnum;
import com.baidu.idl.face.platform.utils.Base64Utils;
import com.google.gson.JsonObject;
import com.igexin.sdk.PushManager;
import com.supwisdom.superapp.WXApplication;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.superapp.service.model.Response;
import com.supwisdom.zueb.R;
import io.dcloud.feature.sdk.DCUniMPSDK;
import java.util.HashMap;
import org.json.JSONException;
import retrofit2.Call;
import retrofit2.Callback;
import supwisdom.cm1;
import supwisdom.ct1;
import supwisdom.fn1;
import supwisdom.in1;
import supwisdom.mj1;
import supwisdom.sh1;
import supwisdom.vi1;
import supwisdom.xs1;

/* JADX INFO: loaded from: classes2.dex */
public class FaceLivenessExpActivity extends FaceLivenessActivity {
    public static String H = "SCAN_CODE";
    public String C;
    public String D;
    public String E;
    public String F;
    public vi1 G;

    public class a implements Callback<Response<JsonObject>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ HashMap f4085a;

        public a(HashMap map) {
            this.f4085a = map;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            FaceLivenessExpActivity faceLivenessExpActivity = FaceLivenessExpActivity.this;
            Toast.makeText(faceLivenessExpActivity, faceLivenessExpActivity.getResources().getString(R.string.net_error), 0).show();
            FaceLivenessExpActivity.this.m();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            Response<JsonObject> responseBody = response.body();
            if (response.code() != 200 || responseBody == null) {
                FaceLivenessExpActivity faceLivenessExpActivity = FaceLivenessExpActivity.this;
                Toast.makeText(faceLivenessExpActivity, faceLivenessExpActivity.getResources().getString(R.string.face_error), 0).show();
                FaceLivenessExpActivity.this.m();
            } else {
                fn1.H = responseBody.data.get("nonce").getAsString();
                if (responseBody.code == Response.CODE_SUCCESS) {
                    FaceLivenessExpActivity.this.b((HashMap<String, String>) this.f4085a);
                } else {
                    Toast.makeText(FaceLivenessExpActivity.this, responseBody.message, 0).show();
                    FaceLivenessExpActivity.this.m();
                }
            }
        }
    }

    public class b implements Callback<Response<JsonObject>> {
        public b() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            FaceLivenessExpActivity faceLivenessExpActivity = FaceLivenessExpActivity.this;
            Toast.makeText(faceLivenessExpActivity, faceLivenessExpActivity.getResources().getString(R.string.net_error), 0).show();
            FaceLivenessExpActivity.this.m();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            Response<JsonObject> responseBody = response.body();
            if (response.code() != 200 || responseBody == null) {
                FaceLivenessExpActivity faceLivenessExpActivity = FaceLivenessExpActivity.this;
                Toast.makeText(faceLivenessExpActivity, faceLivenessExpActivity.getResources().getString(R.string.face_error), 0).show();
                FaceLivenessExpActivity.this.m();
                return;
            }
            JsonObject jsonObject = responseBody.data;
            fn1.H = jsonObject.get("nonce").isJsonNull() ? fn1.H : jsonObject.get("nonce").getAsString();
            if (responseBody.code != Response.CODE_SUCCESS) {
                Toast.makeText(FaceLivenessExpActivity.this, responseBody.message, 0).show();
                FaceLivenessExpActivity.this.m();
            } else {
                Intent intent = new Intent(FaceLivenessExpActivity.this, (Class<?>) BindPhoneActivity.class);
                intent.putExtra("verifyType", "bind");
                FaceLivenessExpActivity.this.startActivity(intent);
            }
        }
    }

    public class c implements Callback<Response<JSONObject>> {
        public c() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            FaceLivenessExpActivity.this.G.dismiss();
            FaceLivenessExpActivity faceLivenessExpActivity = FaceLivenessExpActivity.this;
            Toast.makeText(faceLivenessExpActivity, faceLivenessExpActivity.getResources().getString(R.string.net_error), 0).show();
            FaceLivenessExpActivity.this.m();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody = response.body();
            if (response.code() != 200 || responseBody == null || responseBody.code != 0) {
                FaceLivenessExpActivity.this.G.dismiss();
                Toast.makeText(FaceLivenessExpActivity.this, "人脸不匹配,请切换账号试试！", 0).show();
                FaceLivenessExpActivity.this.m();
                return;
            }
            sh1.c.b(fn1.o, responseBody.data.getString("idToken"));
            in1.a(FaceLivenessExpActivity.this);
            in1.b(FaceLivenessExpActivity.this);
            try {
                FaceLivenessExpActivity.this.G.dismiss();
                WXApplication.homeUniMP = DCUniMPSDK.getInstance().openUniMP(FaceLivenessExpActivity.this, fn1.i, cm1.class);
                FaceLivenessExpActivity.this.finishAffinity();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public class d implements Callback<Response<JSONObject>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ HashMap f4088a;

        public d(HashMap map) {
            this.f4088a = map;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            FaceLivenessExpActivity.this.a("网络错误", false);
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null) {
                FaceLivenessExpActivity.this.a("验证失败", false);
                return;
            }
            if (response.body().data != null) {
                fn1.H = response.body().data.getString("nonce");
            }
            FaceLivenessExpActivity.this.d(this.f4088a);
        }
    }

    public class e implements Callback<Response<JsonObject>> {
        public e() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            FaceLivenessExpActivity faceLivenessExpActivity = FaceLivenessExpActivity.this;
            faceLivenessExpActivity.a(faceLivenessExpActivity.getResources().getString(R.string.net_error), true);
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            Response<JsonObject> responseBody = response.body();
            if (response.code() != 200 || responseBody == null) {
                FaceLivenessExpActivity faceLivenessExpActivity = FaceLivenessExpActivity.this;
                faceLivenessExpActivity.a(faceLivenessExpActivity.getResources().getString(R.string.face_error), true);
                return;
            }
            JsonObject jsonObject = responseBody.data;
            fn1.H = jsonObject.get("nonce").isJsonNull() ? fn1.H : jsonObject.get("nonce").getAsString();
            if (responseBody.code != Response.CODE_SUCCESS) {
                FaceLivenessExpActivity.this.a(responseBody.message, true);
                return;
            }
            FaceLivenessExpActivity.this.a("认证成功", false);
            Intent intent = new Intent(FaceLivenessExpActivity.this, (Class<?>) VertificateFinishActivity.class);
            intent.putExtra("type", 3);
            FaceLivenessExpActivity.this.startActivity(intent);
        }
    }

    public final void c(HashMap<String, String> map) {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().g(ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new a(map));
    }

    public final void d(HashMap<String, String> map) {
        byte[] bArrDecode = Base64Utils.decode(map.get("bestImage0"), 2);
        mj1.b().a(ct1.create(xs1.b("multipart/form-data"), fn1.H), mj1.a(bArrDecode)).enqueue(new e());
    }

    public final void e(HashMap<String, String> map) {
        mj1.b().c(fn1.w).enqueue(new d(map));
    }

    public final void f(String str) {
        if (!this.G.isShowing()) {
            this.G.show();
        }
        Long lValueOf = Long.valueOf(System.currentTimeMillis());
        String clientid = TextUtils.isEmpty(PushManager.getInstance().getClientid(this)) ? "" : PushManager.getInstance().getClientid(this);
        mj1.b().a(this.D, str, getPackageName(), this.E, fn1.f7620a, "aaa", lValueOf.toString(), clientid, in1.a(this.D, str, getPackageName(), this.E, fn1.f7620a, "aaa", lValueOf.toString(), clientid, this.F)).enqueue(new c());
    }

    @Override // com.supwisdom.superapp.ui.activity.FaceLivenessActivity, android.app.Activity
    public void finish() {
        super.finish();
    }

    @Override // com.supwisdom.superapp.ui.activity.FaceLivenessActivity, com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.trackPageName = getResources().getString(R.string.faceLiveness_activity);
        this.G = new vi1(this);
        this.C = getIntent().getStringExtra("verifyType");
        this.D = getIntent().getStringExtra("account");
        this.E = getIntent().getStringExtra("deviceId");
        this.F = getIntent().getStringExtra("privateKey");
        WXBaseActivity.transparencyBar(this);
        WXBaseActivity.setLightStatusBar(this, true);
        a(99, "android.permission.CAMERA");
    }

    @Override // com.supwisdom.superapp.ui.activity.FaceLivenessActivity, com.baidu.idl.face.platform.ILivenessStrategyCallback
    public void onLivenessCompletion(FaceStatusEnum faceStatusEnum, String str, HashMap<String, String> map) throws Throwable {
        super.onLivenessCompletion(faceStatusEnum, str, map);
        if (faceStatusEnum != FaceStatusEnum.OK || !this.t) {
            if (faceStatusEnum == FaceStatusEnum.Error_DetectTimeout || faceStatusEnum == FaceStatusEnum.Error_LivenessTimeout || faceStatusEnum == FaceStatusEnum.Error_Timeout) {
                Toast.makeText(this, "采集超时", 0).show();
                m();
                return;
            }
            return;
        }
        if (this.C.equals("bind")) {
            c(map);
            return;
        }
        if (this.C.equals("login")) {
            f(map.get("bestImage0"));
            return;
        }
        if (this.C.equals("information_complete")) {
            e(map);
            return;
        }
        if (this.C.equals("check")) {
            fn1.C = map.get("bestImage0");
            Intent intent = new Intent();
            intent.putExtra(H, map.get("bestImage0"));
            setResult(-1, intent);
            finish();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, supwisdom.j7.c
    @TargetApi(23)
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        boolean z = false;
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (iArr[i2] == 0) {
                z = true;
            }
        }
        if (z) {
            return;
        }
        a(99, "android.permission.CAMERA");
    }

    public final void b(HashMap<String, String> map) {
        byte[] bArrDecode = Base64Utils.decode(map.get("bestImage0"), 2);
        mj1.b().b(ct1.create(xs1.b("multipart/form-data"), fn1.H), mj1.a(bArrDecode)).enqueue(new b());
    }

    public void a(int i, String str) {
        if (str == null || str.length() <= 0) {
            return;
        }
        try {
            if (Build.VERSION.SDK_INT < 23 || checkSelfPermission(str) == 0) {
                return;
            }
            shouldShowRequestPermissionRationale(str);
            requestPermissions(new String[]{str}, i);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final void a(String str, boolean z) {
        if (z) {
            m();
        }
        Toast.makeText(this, str, 0).show();
    }
}
