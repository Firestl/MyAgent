package com.supwisdom.superapp.wxapi;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.igexin.sdk.PushManager;
import com.supwisdom.superapp.WXApplication;
import com.supwisdom.superapp.service.model.Response;
import com.supwisdom.superapp.ui.activity.BindThirdActivity;
import com.supwisdom.superapp.ui.activity.LoginActivity;
import com.supwisdom.superapp.ui.activity.MultiAccountLoginAt;
import com.supwisdom.zueb.R;
import com.ta.utdid2.device.UTDevice;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.utils.Log;
import com.umeng.analytics.MobclickAgent;
import io.dcloud.feature.sdk.DCUniMPSDK;
import io.dcloud.share.mm.AbsWXCallbackActivity;
import java.util.UUID;
import retrofit2.Call;
import retrofit2.Callback;
import supwisdom.cm1;
import supwisdom.fn1;
import supwisdom.in1;
import supwisdom.jn1;
import supwisdom.mj1;
import supwisdom.sh1;
import supwisdom.vi1;

/* JADX INFO: loaded from: classes2.dex */
public class WXEntryActivity extends AbsWXCallbackActivity implements IWXAPIEventHandler, in1.d {
    public static String b = "";
    public static Handler c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public vi1 f4417a;

    public class a implements Callback<Response<JsonObject>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4418a;

        public a(String str) {
            this.f4418a = str;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JsonObject>> call, Throwable th) {
            WXEntryActivity.this.f4417a.dismiss();
            Toast.makeText(WXEntryActivity.this, R.string.net_error, 0).show();
            WXEntryActivity.this.finish();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JsonObject>> call, retrofit2.Response<Response<JsonObject>> response) {
            Response<JsonObject> responseBody = response.body();
            if (response.code() != 200 || responseBody.code != 0) {
                WXEntryActivity.this.f4417a.dismiss();
                Toast.makeText(WXEntryActivity.this, R.string.authorize_error, 0).show();
                WXEntryActivity.this.finish();
            } else {
                JsonObject jsonObject = responseBody.data;
                String asString = jsonObject.get("callbackUri").getAsString();
                fn1.H = jsonObject.get("nonce").getAsString();
                WXEntryActivity.this.b(asString, this.f4418a);
            }
        }
    }

    public class b implements Callback<Response<JSONObject>> {
        public b() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            WXEntryActivity.this.f4417a.dismiss();
            Toast.makeText(WXEntryActivity.this, R.string.net_error, 0).show();
            WXEntryActivity.this.finish();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody = response.body();
            if (response.code() == 200 && responseBody.code == 0) {
                fn1.H = responseBody.data.getString("nonce");
                WXEntryActivity.this.a();
            } else {
                WXEntryActivity.this.f4417a.dismiss();
                Toast.makeText(WXEntryActivity.this, R.string.login_error, 0).show();
                WXEntryActivity.this.finish();
            }
        }
    }

    public class c implements Callback<Response<JSONObject>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4420a;

        public c(String str) {
            this.f4420a = str;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            WXEntryActivity.this.f4417a.dismiss();
            Toast.makeText(WXEntryActivity.this, R.string.net_error, 0).show();
            WXEntryActivity.this.finish();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            JSONObject jSONObject;
            Response<JSONObject> responseBody = response.body();
            if (response.code() == 200 && (jSONObject = responseBody.data) != null && responseBody.code == 0) {
                fn1.H = jSONObject.getString("nonce");
                WXEntryActivity.this.a(responseBody.data.getString("callbackUri"), this.f4420a);
            } else {
                WXEntryActivity.this.f4417a.dismiss();
                Toast.makeText(WXEntryActivity.this, "获取用户信息失败", 0).show();
                WXEntryActivity.this.finish();
            }
        }
    }

    public class d implements Callback<Response<JSONObject>> {
        public d() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            WXEntryActivity.this.f4417a.dismiss();
            Toast.makeText(WXEntryActivity.this, R.string.net_error, 0).show();
            WXEntryActivity.this.finish();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            WXEntryActivity.this.f4417a.dismiss();
            if (response.body() == null || response.body().data == null) {
                Toast.makeText(WXEntryActivity.this, "绑定失败", 0).show();
            } else {
                fn1.H = response.body().data.getString("nonce");
                Message message = new Message();
                message.what = 100;
                message.obj = "微信绑定成功";
                WXEntryActivity.c.sendMessage(message);
            }
            WXEntryActivity.this.finish();
        }
    }

    public class e implements Callback<Response<JSONObject>> {
        public e() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            WXEntryActivity.this.f4417a.dismiss();
            Toast.makeText(WXEntryActivity.this, R.string.net_error, 0).show();
            WXEntryActivity.this.finish();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody = response.body();
            if (response.code() != 200 || responseBody.code != 0) {
                WXEntryActivity.this.f4417a.dismiss();
                Toast.makeText(WXEntryActivity.this, "获取用户信息失败", 0).show();
                WXEntryActivity.this.finish();
                return;
            }
            JSONObject jSONObject = responseBody.data;
            fn1.H = jSONObject.getString("nonce");
            String string = jSONObject.getString("state");
            if (!jSONObject.getBoolean("federatedBindStatus").booleanValue()) {
                WXEntryActivity.this.f4417a.dismiss();
                Toast.makeText(WXEntryActivity.this, "用户未绑定微信", 0).show();
                Intent intent = new Intent(WXEntryActivity.this, (Class<?>) BindThirdActivity.class);
                intent.putExtra("bindType", ConstantsAPI.Token.WX_TOKEN_PLATFORMID_VALUE);
                intent.putExtra("state", string);
                WXEntryActivity.this.startActivity(intent);
                WXEntryActivity.this.finish();
                return;
            }
            JSONArray jSONArray = jSONObject.getJSONArray("accounts");
            if (jSONArray != null && jSONArray.size() == 1) {
                WXEntryActivity.this.c(TextUtils.isEmpty(jSONArray.getJSONObject(0).getString("id")) ? "" : jSONArray.getJSONObject(0).getString("id"));
                return;
            }
            Intent intent2 = new Intent(WXEntryActivity.this, (Class<?>) MultiAccountLoginAt.class);
            intent2.putExtra("accountJA", jSONArray.toJSONString());
            intent2.putExtra("loginType", "federatedLogin");
            WXEntryActivity.this.startActivity(intent2);
            WXEntryActivity.this.finish();
        }
    }

    public class f implements Callback<Response<JSONObject>> {
        public f(String str) {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            WXEntryActivity.this.f4417a.dismiss();
            Toast.makeText(WXEntryActivity.this, R.string.net_error, 0).show();
            WXEntryActivity.this.finish();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody = response.body();
            if (response.code() != 200 || responseBody.code != 0) {
                WXEntryActivity.this.f4417a.dismiss();
                Toast.makeText(WXEntryActivity.this, R.string.login_error, 0).show();
                WXEntryActivity.this.finish();
            } else {
                fn1.w = responseBody.data.getString("idToken");
                responseBody.data.getBoolean("userNonCompleted").booleanValue();
                sh1.c.b(fn1.o, fn1.w);
                WXEntryActivity wXEntryActivity = WXEntryActivity.this;
                in1.b(wXEntryActivity, wXEntryActivity);
                jn1.a();
            }
        }
    }

    public final void c(String str) {
        mj1.b().d(fn1.H, str, PushManager.getInstance().getClientid(this)).enqueue(new f(str));
    }

    @Override // supwisdom.in1.d
    public void k() {
        try {
            WXApplication.homeUniMP = DCUniMPSDK.getInstance().openUniMP(this, fn1.i, cm1.class);
            finish();
            LoginActivity.I0.finish();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.f4417a = new vi1(this);
        WXApplication.api.handleIntent(getIntent(), this);
    }

    @Override // android.app.Activity
    public void onPause() {
        MobclickAgent.onPageEnd(getResources().getString(R.string.wxEntry_activity));
        MobclickAgent.onPause(this);
        super.onPause();
    }

    @Override // io.dcloud.share.mm.AbsWXCallbackActivity, com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
    public void onReq(BaseReq baseReq) {
    }

    @Override // io.dcloud.share.mm.AbsWXCallbackActivity, com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
    public void onResp(BaseResp baseResp) {
        this.f4417a = new vi1(this);
        if (baseResp.getType() == 1) {
            Log.d("WXEntryActivityLog", "onResp: " + baseResp.errStr);
            Log.d("WXEntryActivityLog", "onResp: 错误码" + baseResp.errCode);
            int i = baseResp.errCode;
            if (i == -4) {
                Toast.makeText(this, "用户拒绝授权登录", 0).show();
                finish();
            } else if (i == -2) {
                Toast.makeText(this, "用户取消授权", 0).show();
                finish();
            } else if (i == 0) {
                String str = ((SendAuth.Resp) baseResp).code;
                Log.d("WXEntryActivityLog", "code: " + str);
                if ("bind".equals(b)) {
                    b(str);
                } else if ("login".equals(b)) {
                    a(str);
                } else if ("platform".equals(b)) {
                    Message message = new Message();
                    message.what = 1;
                    message.obj = str;
                    c.sendMessage(message);
                    finish();
                } else if ("getFederatedCode".equals(b)) {
                    Message message2 = new Message();
                    message2.what = 2;
                    message2.obj = str;
                    c.sendMessage(message2);
                    finish();
                }
            }
        } else if (baseResp.getType() == 2) {
            Toast.makeText(this, baseResp.errCode != 0 ? "分享失败" : "分享成功", 0).show();
        } else if (baseResp.getType() == 19) {
            String str2 = ((WXLaunchMiniProgram.Resp) baseResp).extMsg;
        }
        finish();
    }

    @Override // android.app.Activity
    public void onRestart() {
        super.onRestart();
    }

    @Override // android.app.Activity
    public void onResume() {
        MobclickAgent.onPageStart(getResources().getString(R.string.wxEntry_activity));
        MobclickAgent.onResume(this);
        super.onResume();
    }

    public final void b(String str, String str2) {
        mj1.b().i(str, fn1.H, str2).enqueue(new b());
    }

    public final void a(String str) {
        String string = UUID.randomUUID().toString();
        String strC = sh1.c.c(fn1.p);
        if (strC == null || "".equals(strC)) {
            strC = UTDevice.getUtdid(this);
            sh1.c.b(fn1.p, strC);
        }
        mj1.b().a(getPackageName(), string, strC).enqueue(new a(str));
    }

    public final void b(String str) {
        mj1.b().m(fn1.D, UTDevice.getUtdid(this), UTDevice.getUtdid(this)).enqueue(new c(str));
    }

    public final void a(String str, String str2) {
        mj1.b().b(str, fn1.H, fn1.w, str2).enqueue(new d());
    }

    public final void a() {
        mj1.b().k(fn1.H).enqueue(new e());
    }
}
