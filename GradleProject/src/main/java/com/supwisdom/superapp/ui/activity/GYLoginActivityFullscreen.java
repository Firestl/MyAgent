package com.supwisdom.superapp.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.g.gysdk.EloginActivityParam;
import com.g.gysdk.GYManager;
import com.g.gysdk.GYResponse;
import com.g.gysdk.GyCallBack;
import com.g.gysdk.GyPreloginResult;
import com.huawei.hms.framework.common.hianalytics.CrashHianalyticsData;
import com.igexin.sdk.PushManager;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.superapp.service.model.Response;
import com.supwisdom.zueb.R;
import com.ta.utdid2.device.UTDevice;
import io.dcloud.feature.sdk.DCUniMPSDK;
import org.json.JSONException;
import retrofit2.Call;
import retrofit2.Callback;
import supwisdom.ct1;
import supwisdom.fn1;
import supwisdom.in1;
import supwisdom.jn1;
import supwisdom.mj1;
import supwisdom.sh1;
import supwisdom.ui1;
import supwisdom.xs1;

/* JADX INFO: loaded from: classes2.dex */
public class GYLoginActivityFullscreen extends WXBaseActivity implements in1.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public CheckBox f4103a;
    public View b;
    public ui1 c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Boolean f4104e;
    public int f = 0;
    public ImageView g;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            GYLoginActivityFullscreen.this.m();
        }
    }

    public class b implements Callback<Response<JSONObject>> {
        public b() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Log.d("onFailure", "onFailure");
            GYLoginActivityFullscreen.this.e("网络错误");
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200) {
                GYLoginActivityFullscreen.this.e(GYLoginActivityFullscreen.this.getResources().getString(R.string.account_error));
                return;
            }
            Response<JSONObject> responseBody = response.body();
            String string = responseBody.data.getString("idToken");
            JSONArray jSONArray = responseBody.data.getJSONArray("accounts");
            fn1.w = string;
            if (jSONArray != null && jSONArray.size() != 0) {
                sh1.c.b(fn1.o, fn1.w);
                String string2 = responseBody.data.getString("cid");
                Intent intent = new Intent(GYLoginActivityFullscreen.this, (Class<?>) MultiAccountLoginAt.class);
                intent.putExtra("accountJA", jSONArray.toJSONString());
                intent.putExtra("loginType", "codeLogin");
                intent.putExtra("cid", string2);
                GYLoginActivityFullscreen.this.startActivity(intent);
                return;
            }
            if (TextUtils.isEmpty(string)) {
                sh1.c.b(fn1.o, fn1.w);
                Toast.makeText(GYLoginActivityFullscreen.this, response.message(), 0).show();
                return;
            }
            if (responseBody.data.getInteger("passwordStatus") != null) {
                GYLoginActivityFullscreen.this.f = responseBody.data.getInteger("passwordStatus").intValue();
            }
            if (responseBody.data.getBoolean("userNonCompleted") != null) {
                if (responseBody.data.getBoolean("userNonCompleted").booleanValue() && GYLoginActivityFullscreen.this.f4104e.booleanValue()) {
                    LoginActivity.I0.a(string, responseBody.data.getBoolean("userNonCompleted"), GYLoginActivityFullscreen.this.f4104e, GYLoginActivityFullscreen.this.f);
                    return;
                }
                sh1.c.b(fn1.o, fn1.w);
                GYLoginActivityFullscreen gYLoginActivityFullscreen = GYLoginActivityFullscreen.this;
                in1.b(gYLoginActivityFullscreen, gYLoginActivityFullscreen);
                jn1.a();
            }
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            GYLoginActivityFullscreen.this.finish();
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Log.d(WXBaseActivity.TAG, "点击了隐私协议checkBox，当前状态：" + ((CheckBox) view).isChecked());
        }
    }

    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Log.d(WXBaseActivity.TAG, "一键登录按钮 onLoginClick:");
            if (GYLoginActivityFullscreen.this.f4103a.isChecked()) {
                GYLoginActivityFullscreen.this.n();
            } else {
                Toast.makeText(GYLoginActivityFullscreen.this, "请先仔细阅读协议并勾选，然后再点击登录", 0).show();
                throw new IllegalStateException("请先仔细阅读协议并勾选，然后再点击登录");
            }
        }
    }

    public class f implements EloginActivityParam.UIErrorListener {
        public f() {
        }

        @Override // com.g.gysdk.EloginActivityParam.UIErrorListener
        public void onError(String str) {
            Log.e(WXBaseActivity.TAG, "UIErrorListener.onError:" + str);
            GYLoginActivityFullscreen.this.l();
        }
    }

    public class g implements GyCallBack {
        public g() {
        }

        @Override // com.g.gysdk.GyCallBack
        public void onFailed(GYResponse gYResponse) {
            Log.e(WXBaseActivity.TAG, "登录失败 response:" + gYResponse);
            try {
                new org.json.JSONObject(gYResponse.getMsg()).getInt("errorCode");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            GYLoginActivityFullscreen.this.l();
            GYLoginActivityFullscreen.this.finish();
        }

        @Override // com.g.gysdk.GyCallBack
        public void onSuccess(GYResponse gYResponse) {
            Log.d(WXBaseActivity.TAG, "登录成功 response:" + gYResponse);
            GYLoginActivityFullscreen.this.l();
            GYLoginActivityFullscreen.this.finish();
            try {
                org.json.JSONObject jSONObject = new org.json.JSONObject(gYResponse.getMsg());
                org.json.JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                jSONObject.getString(CrashHianalyticsData.PROCESS_ID);
                String string = jSONObject2.getString("token");
                Log.d(WXBaseActivity.TAG, "token:" + string + "  expiredTime:" + jSONObject2.getLong("expiredTime"));
                GYLoginActivityFullscreen.this.f(string);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public class h extends ClickableSpan {
        public h() {
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            Intent intent = new Intent(GYLoginActivityFullscreen.this, (Class<?>) PrivacyActivity.class);
            intent.putExtra("policyType", 0);
            GYLoginActivityFullscreen.this.startActivity(intent);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            textPaint.setUnderlineText(false);
        }
    }

    public class i extends ClickableSpan {
        public i() {
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            Intent intent = new Intent(GYLoginActivityFullscreen.this, (Class<?>) PrivacyActivity.class);
            intent.putExtra("policyType", 1);
            GYLoginActivityFullscreen.this.startActivity(intent);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            textPaint.setUnderlineText(false);
        }
    }

    public class j extends ClickableSpan {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ GyPreloginResult f4114a;

        public j(GyPreloginResult gyPreloginResult) {
            this.f4114a = gyPreloginResult;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            Intent intent = new Intent(GYLoginActivityFullscreen.this, (Class<?>) H5Activity.class);
            intent.setData(Uri.parse(this.f4114a.getPrivacyUrl()));
            GYLoginActivityFullscreen.this.startActivity(intent);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            textPaint.setUnderlineText(false);
        }
    }

    public class k implements Runnable {
        public k() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (GYLoginActivityFullscreen.this.c == null) {
                GYLoginActivityFullscreen.this.c = new ui1(GYLoginActivityFullscreen.this);
            }
            GYLoginActivityFullscreen.this.c.show();
        }
    }

    public final void f(String str) {
        String strC = sh1.c.c(fn1.p);
        if (strC == null || "".equals(strC)) {
            strC = UTDevice.getUtdid(this);
            sh1.c.b(fn1.p, strC);
        }
        String str2 = strC;
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("token", str);
            if (!"".equals(this.d)) {
                jSONObject.put("gyuid", this.d);
            }
            jSONObject.put("appId", com.igexin.push.core.b.m);
            jSONObject.put("deviceId", str2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString());
        mj1.b().c(str, this.d, com.igexin.push.core.b.m, "", str2, "android", PushManager.getInstance().getClientid(this), "").enqueue(new b());
    }

    @Override // supwisdom.in1.d
    public void k() {
        if (DCUniMPSDK.getInstance().isInitialize()) {
            Log.d("小程序", "立即启动");
            openMini();
        }
    }

    public void l() {
        runOnUiThread(new a());
    }

    public final void m() {
        ui1 ui1Var = this.c;
        if (ui1Var != null) {
            ui1Var.dismiss();
            this.c = null;
        }
    }

    public void n() {
        runOnUiThread(new k());
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_login_float);
        WXBaseActivity.transparencyBar(this);
        WXBaseActivity.setLightStatusBar(this, true);
        this.c = new ui1(this);
        ImageView imageView = (ImageView) findViewById(R.id.iv_close);
        this.g = imageView;
        imageView.setOnClickListener(new c());
        TextView textView = (TextView) findViewById(R.id.number_textview);
        TextView textView2 = (TextView) findViewById(R.id.slogan_textview);
        View viewFindViewById = findViewById(R.id.fl_gy_login);
        CheckBox checkBox = (CheckBox) findViewById(R.id.agreeBtn_gy);
        TextView textView3 = (TextView) findViewById(R.id.tv_privacy_gy);
        viewFindViewById.setSelected(true);
        this.d = getIntent().getStringExtra("gyuid");
        this.f4104e = Boolean.valueOf(getIntent().getBooleanExtra("userCompletedCheckEnabled", false));
        checkBox.setOnClickListener(new d());
        this.f4103a = checkBox;
        this.b = viewFindViewById;
        a(textView3);
        GYManager.getInstance().eAccountLogin(new EloginActivityParam().setActivity(this).setNumberTextview(textView).setSloganTextview(textView2).setLoginButton(viewFindViewById).setPrivacyCheckbox(checkBox).setPrivacyTextview(textView3).setUiErrorListener(new f()).setLoginOnClickListener(new e()), 5000, new g());
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        l();
        super.onDestroy();
    }

    public void e(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this, str, 0).show();
    }

    public final void a(TextView textView) {
        GyPreloginResult preLoginResult = GYManager.getInstance().getPreLoginResult();
        String strConcat = getResources().getString(R.string.privacy_tips).concat("《".concat(preLoginResult.getPrivacyName()).concat("》"));
        String string = getResources().getString(R.string.privacy_tips_key1);
        String string2 = getResources().getString(R.string.privacy_tips_key2);
        String strConcat2 = "《".concat(preLoginResult.getPrivacyName()).concat("》");
        int iIndexOf = strConcat.indexOf(string);
        int iIndexOf2 = strConcat.indexOf(string2);
        int iIndexOf3 = strConcat.indexOf(strConcat2);
        SpannableString spannableString = new SpannableString(strConcat);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.result_text)), iIndexOf, string.length() + iIndexOf, 34);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.result_text)), iIndexOf2, string2.length() + iIndexOf2, 34);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.result_text)), iIndexOf3, strConcat2.length() + iIndexOf3, 34);
        spannableString.setSpan(new h(), iIndexOf, string.length() + iIndexOf, 34);
        spannableString.setSpan(new i(), iIndexOf2, string2.length() + iIndexOf2, 34);
        spannableString.setSpan(new j(preLoginResult), iIndexOf3, strConcat2.length() + iIndexOf3, 34);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(spannableString);
    }
}
