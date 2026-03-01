package com.supwisdom.superapp.ui.activity;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.sdk.app.AuthTask;
import com.baidu.idl.face.platform.FaceConfig;
import com.baidu.idl.face.platform.FaceSDKManager;
import com.baidu.idl.face.platform.LivenessTypeEnum;
import com.g.gysdk.EloginActivityParam;
import com.g.gysdk.GYManager;
import com.g.gysdk.GYResponse;
import com.g.gysdk.GyCallBack;
import com.g.gysdk.GyConfig;
import com.g.gysdk.GyPreloginResult;
import com.google.gson.Gson;
import com.huawei.hms.framework.common.hianalytics.CrashHianalyticsData;
import com.igexin.sdk.PushManager;
import com.supwisdom.superapp.WXApplication;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.superapp.service.model.Response;
import com.supwisdom.superapp.view.FullVideoView;
import com.supwisdom.superapp.wxapi.WXEntryActivity;
import com.supwisdom.zueb.R;
import com.ta.utdid2.device.UTDevice;
import com.taobao.weex.common.Constants;
import com.tencent.connect.UnionInfo;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.tencent.wework.api.IWWAPI;
import com.tencent.wework.api.IWWAPIEventHandler;
import com.tencent.wework.api.WWAPIFactory;
import com.tencent.wework.api.model.BaseMessage;
import com.tencent.wework.api.model.WWAuthMessage;
import io.dcloud.feature.oauth.weixin.WeiXinOAuthService;
import io.dcloud.feature.sdk.DCUniMPSDK;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
import org.json.JSONException;
import retrofit2.Call;
import retrofit2.Callback;
import supwisdom.bn1;
import supwisdom.ct1;
import supwisdom.dj1;
import supwisdom.et1;
import supwisdom.fn1;
import supwisdom.in1;
import supwisdom.j7;
import supwisdom.jn1;
import supwisdom.kj1;
import supwisdom.lm1;
import supwisdom.mj1;
import supwisdom.oi1;
import supwisdom.om1;
import supwisdom.pm1;
import supwisdom.sh1;
import supwisdom.ti1;
import supwisdom.ui1;
import supwisdom.xi1;
import supwisdom.xs1;
import supwisdom.y7;

/* JADX INFO: loaded from: classes2.dex */
public class LoginActivity extends WXBaseActivity implements IUiListener, View.OnClickListener, TextWatcher, TextView.OnEditorActionListener, in1.d, WXApplication.k {
    public static List<LivenessTypeEnum> G0 = new ArrayList();
    public static boolean H0 = false;
    public static LoginActivity I0;
    public ImageButton A;
    public LinearLayout A0;
    public String B;
    public Boolean B0;
    public boolean C;
    public Boolean C0;
    public ImageView D;
    public Tencent E;
    public ui1 F;
    public LinearLayout G;
    public TextView H;
    public CheckBox I;
    public TextView J;
    public String K;
    public TextView L;
    public JSONObject M;
    public TextView N;
    public String O;
    public String P;
    public String Q;
    public oi1 S;
    public JSONObject U;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public EditText f4207a;
    public EditText b;
    public ImageButton c;
    public ImageButton d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ImageButton f4208e;
    public View f;
    public View g;
    public String g0;
    public TextView h;
    public LinearLayout h0;
    public TextView i;
    public LinearLayout i0;
    public View j;
    public TextView j0;
    public View k;
    public TextView k0;
    public View l;
    public TextView l0;
    public ProgressBar m;
    public ArrayList<String> m0;
    public ProgressBar n;
    public EloginActivityParam n0;
    public ProgressBar o;
    public JSONObject o0;
    public TextView p;
    public TextView q;
    public TextView r;
    public Boolean r0;
    public TextView s;
    public FullVideoView t;
    public Boolean t0;
    public LinearLayout u;
    public String u0;
    public LinearLayout v;
    public JSONObject v0;
    public ImageView w;
    public ui1 w0;
    public ImageView x;
    public CheckBox x0;
    public ImageView y;
    public TextView y0;
    public ImageView z;
    public LinearLayout z0;
    public boolean R = true;
    public String T = "";
    public Boolean V = false;
    public Integer W = 0;
    public String X = "";
    public String Y = "";
    public Boolean Z = true;
    public Boolean a0 = true;
    public Handler b0 = new Handler(Looper.getMainLooper());
    public boolean c0 = false;
    public Boolean d0 = true;
    public Boolean e0 = true;
    public Boolean f0 = false;
    public String p0 = "";
    public boolean q0 = true;
    public Boolean s0 = false;
    public boolean D0 = false;
    public Handler E0 = new s();
    public AtomicBoolean F0 = new AtomicBoolean(false);

    public class a implements GyCallBack {
        public a() {
        }

        @Override // com.g.gysdk.GyCallBack
        public void onFailed(GYResponse gYResponse) {
            Log.e(WXBaseActivity.TAG, "弹登录界面需要的 预登录失败 prelogin:" + gYResponse);
            LoginActivity.this.s();
            LoginActivity.this.F0.set(false);
        }

        @Override // com.g.gysdk.GyCallBack
        public void onSuccess(GYResponse gYResponse) {
            Log.d(WXBaseActivity.TAG, "弹登录界面需要的 预登录成功 prelogin:" + gYResponse);
            LoginActivity.this.s();
            LoginActivity.this.F0.set(false);
            Intent intent = new Intent(LoginActivity.this, (Class<?>) GYLoginActivityFullscreen.class);
            intent.putExtra("gyuid", LoginActivity.this.p0);
            intent.putExtra("userCompletedCheckEnabled", LoginActivity.this.V);
            LoginActivity.this.startActivity(intent);
        }
    }

    public class a0 implements Runnable {
        public a0() {
        }

        @Override // java.lang.Runnable
        public void run() {
            LoginActivity.this.t();
        }
    }

    public class b implements MediaPlayer.OnPreparedListener {
        public b() {
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            LoginActivity.this.t.start();
            mediaPlayer.setLooping(true);
        }
    }

    public class b0 implements Callback<Response<JSONObject>> {
        public b0() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Log.d("onFailure", "onFailure");
            LoginActivity.this.k("网络错误");
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200) {
                LoginActivity.this.o.setVisibility(8);
                LoginActivity.this.i.setVisibility(0);
                LoginActivity.this.k(LoginActivity.this.getResources().getString(R.string.account_error));
                LoginActivity.this.x();
                return;
            }
            Response<JSONObject> responseBody = response.body();
            String string = responseBody.data.getString("idToken");
            JSONArray jSONArray = responseBody.data.getJSONArray("accounts");
            fn1.w = string;
            fn1.v = LoginActivity.this.f4207a.getText().toString().trim();
            if (jSONArray != null && jSONArray.size() != 0) {
                sh1.c.b(fn1.o, fn1.w);
                String string2 = responseBody.data.getString("cid");
                Intent intent = new Intent(LoginActivity.this, (Class<?>) MultiAccountLoginAt.class);
                intent.putExtra("accountJA", jSONArray.toJSONString());
                intent.putExtra(Constants.Value.PASSWORD, LoginActivity.this.b.getText().toString().trim());
                intent.putExtra("loginType", "codeLogin");
                intent.putExtra("cid", string2);
                LoginActivity.this.startActivity(intent);
                return;
            }
            if (TextUtils.isEmpty(string)) {
                sh1.c.b(fn1.o, fn1.w);
                Toast.makeText(LoginActivity.this, response.message(), 0).show();
                return;
            }
            if (responseBody.data.getInteger("passwordStatus") != null) {
                LoginActivity.this.W = responseBody.data.getInteger("passwordStatus");
            }
            if (responseBody.data.getBoolean("userNonCompleted") != null) {
                if (responseBody.data.getBoolean("userNonCompleted").booleanValue() && LoginActivity.this.V.booleanValue()) {
                    LoginActivity.this.a(string, responseBody.data.getBoolean("userNonCompleted"), LoginActivity.this.V, LoginActivity.this.W.intValue());
                    return;
                }
                sh1.c.b(fn1.o, fn1.w);
                LoginActivity loginActivity = LoginActivity.this;
                in1.b(loginActivity, loginActivity);
                jn1.a();
            }
        }
    }

    public class c implements MediaPlayer.OnCompletionListener {
        public c() {
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            LoginActivity.this.F();
        }
    }

    public class c0 implements Callback<Response<JSONObject>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4214a;

        public c0(String str) {
            this.f4214a = str;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            LoginActivity loginActivity = LoginActivity.this;
            Toast.makeText(loginActivity, loginActivity.getResources().getString(R.string.net_error), 0).show();
            if (LoginActivity.this.F != null) {
                LoginActivity.this.F.dismiss();
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody = response.body();
            if (response.code() == 200 && responseBody.code == 0) {
                fn1.H = responseBody.data.getString("nonce");
                LoginActivity.this.g(this.f4214a);
                return;
            }
            LoginActivity loginActivity = LoginActivity.this;
            Toast.makeText(loginActivity, loginActivity.getResources().getString(R.string.login_error), 0).show();
            if (LoginActivity.this.F != null) {
                LoginActivity.this.F.dismiss();
            }
        }
    }

    public class d implements MediaPlayer.OnErrorListener {
        public d() {
        }

        @Override // android.media.MediaPlayer.OnErrorListener
        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            LoginActivity.this.F();
            return true;
        }
    }

    public class d0 implements Callback<Response<JSONObject>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4216a;

        public d0(String str) {
            this.f4216a = str;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Toast.makeText(LoginActivity.this, R.string.net_error, 0).show();
            if (LoginActivity.this.F != null) {
                LoginActivity.this.F.dismiss();
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody = response.body();
            if (response.code() == 200 && responseBody.code == 0) {
                JSONObject jSONObject = responseBody.data;
                fn1.H = jSONObject.getString("nonce");
                boolean zBooleanValue = jSONObject.getBoolean("federatedBindStatus").booleanValue();
                String string = jSONObject.getString("state");
                if (zBooleanValue) {
                    JSONArray jSONArray = jSONObject.getJSONArray("accounts");
                    if (jSONArray == null || jSONArray.size() != 1) {
                        Intent intent = new Intent(LoginActivity.this, (Class<?>) MultiAccountLoginAt.class);
                        intent.putExtra("accountJA", jSONArray.toJSONString());
                        intent.putExtra("loginType", "federatedLogin");
                        LoginActivity.this.startActivity(intent);
                    } else {
                        LoginActivity.this.j(TextUtils.isEmpty(jSONArray.getJSONObject(0).getString("id")) ? "" : jSONArray.getJSONObject(0).getString("id"));
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "用户未绑定", 0).show();
                    Intent intent2 = new Intent(LoginActivity.this, (Class<?>) BindThirdActivity.class);
                    intent2.putExtra("bindType", this.f4216a);
                    intent2.putExtra("state", string);
                    LoginActivity.this.startActivity(intent2);
                }
            } else {
                Toast.makeText(LoginActivity.this, "获取用户信息失败", 0).show();
            }
            if (LoginActivity.this.F != null) {
                LoginActivity.this.F.dismiss();
            }
        }
    }

    public class e implements Callback<Response<JSONObject>> {

        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ String f4218a;

            public a(String str) {
                this.f4218a = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                Map<String, String> mapAuthV2 = new AuthTask(LoginActivity.this).authV2(this.f4218a, true);
                Message message = new Message();
                message.what = 2;
                message.obj = mapAuthV2;
                LoginActivity.this.E0.sendMessage(message);
            }
        }

        public e() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            LoginActivity loginActivity = LoginActivity.this;
            Toast.makeText(loginActivity, loginActivity.getResources().getString(R.string.net_error), 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody = response.body();
            if (response.code() != 200 || responseBody.code != 0) {
                LoginActivity loginActivity = LoginActivity.this;
                Toast.makeText(loginActivity, loginActivity.getResources().getString(R.string.authorize_error), 0).show();
                return;
            }
            JSONObject jSONObject = responseBody.data;
            String string = jSONObject.getString("authzInfo");
            fn1.H = jSONObject.getString("nonce");
            LoginActivity.this.B = jSONObject.getString("callbackUri");
            new Thread(new a(string)).start();
        }
    }

    public class e0 implements Callback<Response<JSONObject>> {
        public e0() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Toast.makeText(LoginActivity.this, R.string.net_error, 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody = response.body();
            if (response.code() != 200 || responseBody.code != 0) {
                Toast.makeText(LoginActivity.this, R.string.login_error, 0).show();
                return;
            }
            fn1.w = responseBody.data.getString("idToken");
            fn1.v = LoginActivity.this.f4207a.getText().toString().trim();
            if (responseBody.data.getBoolean("userNonCompleted").booleanValue()) {
                LoginActivity.this.V.booleanValue();
            }
            sh1.c.b(fn1.o, fn1.w);
            LoginActivity loginActivity = LoginActivity.this;
            in1.b(loginActivity, loginActivity);
            jn1.a();
        }
    }

    public class f implements Callback<Response<JSONObject>> {
        public f() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            LoginActivity loginActivity = LoginActivity.this;
            Toast.makeText(loginActivity, loginActivity.getResources().getString(R.string.net_error), 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody = response.body();
            if (response.code() != 200 || responseBody.code != 0) {
                LoginActivity loginActivity = LoginActivity.this;
                Toast.makeText(loginActivity, loginActivity.getResources().getString(R.string.authorize_error), 0).show();
                return;
            }
            JSONObject jSONObject = responseBody.data;
            fn1.H = jSONObject.getString("nonce");
            LoginActivity.this.B = jSONObject.getString("callbackUri");
            LoginActivity loginActivity2 = LoginActivity.this;
            loginActivity2.E.login(loginActivity2, "get_simple_userinfo", loginActivity2);
        }
    }

    public class f0 implements Callback<Response<JSONObject>> {
        public f0() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            LoginActivity.this.k(LoginActivity.this.getResources().getString(R.string.net_error));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody = response.body();
            if (response.code() != 200 || responseBody.code != 0) {
                LoginActivity.this.k("发送失败");
            } else {
                fn1.H = responseBody.data.getString("nonce");
                LoginActivity.this.A();
            }
        }
    }

    public class g extends ClickableSpan {
        public g() {
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            Intent intent = new Intent(LoginActivity.this, (Class<?>) PrivacyActivity.class);
            intent.putExtra("policyType", 0);
            LoginActivity.this.startActivity(intent);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            textPaint.setUnderlineText(false);
        }
    }

    public class g0 implements Callback<Response<JSONObject>> {
        public g0() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            LoginActivity.this.k(LoginActivity.this.getResources().getString(R.string.net_error));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody = response.body();
            if (response.code() != 200 || responseBody.code != 0) {
                LoginActivity.this.k("发送失败");
                return;
            }
            fn1.H = responseBody.data.getString("nonce");
            Intent intent = new Intent(LoginActivity.this, (Class<?>) InputVerifyCodeActivity.class);
            intent.putExtra("phoneNumber", LoginActivity.this.s.getText().toString());
            intent.putExtra("type", "login");
            LoginActivity.this.startActivity(intent);
        }
    }

    public class h extends ClickableSpan {
        public h() {
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            Intent intent = new Intent(LoginActivity.this, (Class<?>) PrivacyActivity.class);
            intent.putExtra("policyType", 1);
            LoginActivity.this.startActivity(intent);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            textPaint.setUnderlineText(false);
        }
    }

    public class h0 implements Callback<Response<JSONObject>> {
        public h0() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            LoginActivity.this.f("");
            Log.d("onFailure", "onFailure");
            LoginActivity.this.k("网络错误");
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200) {
                LoginActivity.this.f("");
                return;
            }
            Response<JSONObject> responseBody = response.body();
            if (!responseBody.data.getBoolean("mfaEnabled").booleanValue() || responseBody.data.getBoolean("mfaEnabled") == null) {
                LoginActivity.this.f("");
                return;
            }
            if (responseBody.data.getBoolean("need") == null || !responseBody.data.getBoolean("need").booleanValue()) {
                LoginActivity.this.f(responseBody.data.getString("state"));
                return;
            }
            if (responseBody.data.getString("state") != null) {
                LoginActivity.this.K = responseBody.data.getString("state");
                if (responseBody.data.getBoolean("mfaTypeSecurePhone") != null && responseBody.data.getBoolean("mfaTypeSecurePhone").booleanValue()) {
                    LoginActivity loginActivity = LoginActivity.this;
                    loginActivity.a("securephone", loginActivity.K, responseBody.data.getBoolean("mfaTypeSecurePhone"), responseBody.data.getBoolean("mfaTypeSecureEmail"), responseBody.data.getBoolean("mfaTypeFaceVerify"), responseBody.data.getBoolean("mfaTypeFedAuth"));
                    return;
                }
                if (responseBody.data.getBoolean("mfaTypeSecureEmail") != null && responseBody.data.getBoolean("mfaTypeSecureEmail").booleanValue()) {
                    LoginActivity loginActivity2 = LoginActivity.this;
                    loginActivity2.a("secureemail", loginActivity2.K, responseBody.data.getBoolean("mfaTypeSecurePhone"), responseBody.data.getBoolean("mfaTypeSecureEmail"), responseBody.data.getBoolean("mfaTypeFaceVerify"), responseBody.data.getBoolean("mfaTypeFedAuth"));
                } else if (responseBody.data.getBoolean("mfaTypeFaceVerify") != null && responseBody.data.getBoolean("mfaTypeFaceVerify").booleanValue()) {
                    LoginActivity loginActivity3 = LoginActivity.this;
                    loginActivity3.a("faceverify", loginActivity3.K, responseBody.data.getBoolean("mfaTypeSecurePhone"), responseBody.data.getBoolean("mfaTypeSecureEmail"), responseBody.data.getBoolean("mfaTypeFaceVerify"), responseBody.data.getBoolean("mfaTypeFedAuth"));
                } else {
                    if (responseBody.data.getBoolean("mfaTypeFedAuth") == null || !responseBody.data.getBoolean("mfaTypeFedAuth").booleanValue()) {
                        return;
                    }
                    LoginActivity loginActivity4 = LoginActivity.this;
                    loginActivity4.a("fedauth", loginActivity4.K, responseBody.data.getBoolean("mfaTypeSecurePhone"), responseBody.data.getBoolean("mfaTypeSecureEmail"), responseBody.data.getBoolean("mfaTypeFaceVerify"), responseBody.data.getBoolean("mfaTypeFedAuth"));
                }
            }
        }
    }

    public class i extends ClickableSpan {
        public i() {
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            Intent intent = new Intent(LoginActivity.this, (Class<?>) PrivacyActivity.class);
            intent.putExtra("policyType", 0);
            LoginActivity.this.startActivity(intent);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            textPaint.setUnderlineText(false);
        }
    }

    public class i0 implements Callback<Response<JSONObject>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4227a;

        public i0(String str) {
            this.f4227a = str;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Log.d("onFailure", "onFailure");
            LoginActivity.this.k("网络错误");
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200) {
                String string = LoginActivity.this.getResources().getString(R.string.account_error);
                try {
                    if (response.errorBody() != null) {
                        String strOptString = new org.json.JSONObject(response.errorBody().string()).optString("message");
                        if (strOptString.contains("locked")) {
                            LoginActivity.this.k("用户账号已被锁定");
                        } else {
                            LoginActivity.this.k(strOptString);
                        }
                    }
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    LoginActivity.this.k(string);
                    return;
                }
            }
            Response<JSONObject> responseBody = response.body();
            String string2 = responseBody.data.getString("idToken");
            JSONArray jSONArray = responseBody.data.getJSONArray("accounts");
            fn1.w = string2;
            fn1.v = LoginActivity.this.f4207a.getText().toString().trim();
            if (jSONArray != null && jSONArray.size() != 0) {
                sh1.c.b(fn1.o, fn1.w);
                String string3 = responseBody.data.getString("cid");
                Intent intent = new Intent(LoginActivity.this, (Class<?>) MultiAccountLoginAt.class);
                intent.putExtra("accountJA", jSONArray.toJSONString());
                intent.putExtra(Constants.Value.PASSWORD, LoginActivity.this.b.getText().toString().trim());
                intent.putExtra("mfaState", this.f4227a);
                intent.putExtra("loginType", "passwordLogin");
                intent.putExtra("cid", string3);
                LoginActivity.this.startActivity(intent);
                return;
            }
            if (TextUtils.isEmpty(string2)) {
                sh1.c.b(fn1.o, fn1.w);
                Toast.makeText(LoginActivity.this, response.message(), 0).show();
                return;
            }
            if (responseBody.data.getInteger("passwordStatus") != null) {
                LoginActivity.this.W = responseBody.data.getInteger("passwordStatus");
            }
            if (responseBody.data.getBoolean("userNonCompleted") != null) {
                if ((responseBody.data.getBoolean("userNonCompleted").booleanValue() && LoginActivity.this.V.booleanValue()) || (LoginActivity.this.D0 && LoginActivity.this.W.intValue() != 0)) {
                    LoginActivity.this.a(string2, responseBody.data.getBoolean("userNonCompleted"), LoginActivity.this.V, LoginActivity.this.W.intValue());
                    return;
                }
                sh1.c.b(fn1.o, fn1.w);
                LoginActivity loginActivity = LoginActivity.this;
                in1.b(loginActivity, loginActivity);
                jn1.a();
            }
        }
    }

    public class j extends ClickableSpan {
        public j() {
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            Intent intent = new Intent(LoginActivity.this, (Class<?>) PrivacyActivity.class);
            intent.putExtra("policyType", 1);
            LoginActivity.this.startActivity(intent);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            textPaint.setUnderlineText(false);
        }
    }

    public class k implements IUiListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4229a;

        public k(String str) {
            this.f4229a = str;
        }

        @Override // com.tencent.tauth.IUiListener
        public void onCancel() {
            LoginActivity loginActivity = LoginActivity.this;
            loginActivity.n(loginActivity.getResources().getString(R.string.qq_login_error));
        }

        @Override // com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            try {
                JSONObject object = JSON.parseObject(String.valueOf(obj));
                LoginActivity.this.a("qq", this.f4229a, object.getString("openid"), object.getString("unionid"), "");
            } catch (Exception unused) {
                LoginActivity loginActivity = LoginActivity.this;
                loginActivity.n(loginActivity.getResources().getString(R.string.net_error));
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            LoginActivity loginActivity = LoginActivity.this;
            loginActivity.n(loginActivity.getResources().getString(R.string.qq_login_error));
        }

        @Override // com.tencent.tauth.IUiListener
        public void onWarning(int i) {
        }
    }

    public class l extends ClickableSpan {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ GyPreloginResult f4230a;

        public l(GyPreloginResult gyPreloginResult) {
            this.f4230a = gyPreloginResult;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            Intent intent = new Intent(LoginActivity.this, (Class<?>) H5Activity.class);
            intent.setData(Uri.parse(this.f4230a.getPrivacyUrl()));
            LoginActivity.this.startActivity(intent);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            textPaint.setUnderlineText(false);
        }
    }

    public class m implements Callback<Response<JSONObject>> {
        public m() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            Log.e(WXBaseActivity.TAG, "onFailure: ======= ");
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody;
            if (response.code() == 200 && (responseBody = response.body()) != null) {
                try {
                    LoginActivity.this.M = responseBody.data.getJSONObject("loginPageConfig");
                    LoginActivity.this.v0 = responseBody.data.getJSONObject("loginPageConfig");
                    LoginActivity.this.o0 = responseBody.data.getJSONObject("priorityLoginConfig");
                    LoginActivity.this.U = responseBody.data.getJSONObject("loginModeConfig");
                    if (LoginActivity.this.U.getBoolean("userCompletedCheckEnabled") != null) {
                        LoginActivity.this.V = LoginActivity.this.U.getBoolean("userCompletedCheckEnabled");
                    }
                    if (LoginActivity.this.M.getBoolean("userPasswordDetectEnabled") != null) {
                        LoginActivity.this.D0 = LoginActivity.this.M.getBoolean("userPasswordDetectEnabled").booleanValue();
                    }
                    if (LoginActivity.this.U.getBoolean("userPasswordDetectEnabled") != null) {
                        LoginActivity.this.D0 = LoginActivity.this.U.getBoolean("userPasswordDetectEnabled").booleanValue();
                    }
                    if (LoginActivity.this.U.getString("configInputmessage") != null) {
                        LoginActivity.this.X = LoginActivity.this.U.getString("configInputmessage");
                        if (!"".equals(LoginActivity.this.X)) {
                            LoginActivity.this.f4207a.setHint(LoginActivity.this.X);
                        }
                    }
                    if (LoginActivity.this.U.getString("configPasswordInputMessage") != null) {
                        LoginActivity.this.Y = LoginActivity.this.U.getString("configPasswordInputMessage");
                        if (!"".equals(LoginActivity.this.Y)) {
                            LoginActivity.this.b.setHint(LoginActivity.this.Y);
                        }
                    }
                    if (LoginActivity.this.U.getBoolean("activeUserEnabled") != null) {
                        LoginActivity.this.Z = LoginActivity.this.U.getBoolean("activeUserEnabled");
                    }
                    if (LoginActivity.this.U.getBoolean("forgotPasswordEnabled") != null) {
                        LoginActivity.this.a0 = LoginActivity.this.U.getBoolean("forgotPasswordEnabled");
                    }
                    if (LoginActivity.this.U.getString("configNoticeContent") != null) {
                        LoginActivity.this.T = LoginActivity.this.U.getString("configNoticeContent");
                        LoginActivity.this.J.setVisibility(0);
                        LoginActivity.this.J.setText(LoginActivity.this.T);
                        LoginActivity.this.J.setClickable(true);
                    }
                    if (LoginActivity.this.U.getBoolean("federationOpenweixinEnabled") != null) {
                        LoginActivity.this.d0 = LoginActivity.this.U.getBoolean("federationOpenweixinEnabled");
                        LoginActivity.this.w.setVisibility(LoginActivity.this.d0.booleanValue() ? 0 : 8);
                    }
                    if (LoginActivity.this.U.getBoolean("federationQqEnabled") != null) {
                        LoginActivity.this.s0 = LoginActivity.this.U.getBoolean("federationQqEnabled");
                        if (fn1.N && LoginActivity.this.s0.booleanValue()) {
                            LoginActivity.this.x.setVisibility(0);
                        } else {
                            LoginActivity.this.x.setVisibility(8);
                        }
                    }
                    if (LoginActivity.this.U.getBoolean("federationWorkweixinEnabled") != null) {
                        LoginActivity.this.f0 = LoginActivity.this.U.getBoolean("federationWorkweixinEnabled");
                        LoginActivity.this.z.setVisibility(LoginActivity.this.f0.booleanValue() ? 0 : 8);
                    }
                    if (LoginActivity.this.v0 != null && LoginActivity.this.v0.getString("configCustomEntryName") != null) {
                        LoginActivity.this.g0 = LoginActivity.this.v0.getString("configCustomEntryName");
                        LoginActivity.this.L.setText(LoginActivity.this.g0);
                    }
                    if (LoginActivity.this.U.getBoolean("authenticationSmsCodeEnabled") != null) {
                        LoginActivity.this.q0 = LoginActivity.this.U.getBoolean("authenticationSmsCodeEnabled").booleanValue();
                        if (LoginActivity.this.q0) {
                            LoginActivity.this.q.setVisibility(0);
                        } else {
                            LoginActivity.this.q.setVisibility(8);
                        }
                    }
                    if (LoginActivity.this.U.getBoolean("temporaryEnabled") != null) {
                        LoginActivity.this.t0 = LoginActivity.this.U.getBoolean("temporaryEnabled");
                        if (LoginActivity.this.t0.booleanValue()) {
                            LoginActivity.this.N.setVisibility(0);
                            LoginActivity.this.u0 = LoginActivity.this.U.getString("temporaryUrl");
                        }
                    }
                    if (LoginActivity.this.U.getBoolean("authenticationFaceEnabled") != null) {
                        LoginActivity.this.r0 = LoginActivity.this.U.getBoolean("authenticationFaceEnabled");
                        if (in1.a() != null) {
                            LoginActivity.this.A.setEnabled(true);
                        } else {
                            LoginActivity.this.A.setAlpha(0.5f);
                            LoginActivity.this.A.setEnabled(false);
                        }
                        if (LoginActivity.this.r0.booleanValue()) {
                            LoginActivity.this.v();
                        }
                    }
                    if (LoginActivity.this.v0 != null && LoginActivity.this.v0.getString("encryptEnabled") != null) {
                        LoginActivity.this.C0 = LoginActivity.this.v0.getBoolean("encryptEnabled");
                        if (LoginActivity.this.C0.booleanValue()) {
                            LoginActivity.this.p();
                        }
                    }
                    if (LoginActivity.this.U.getBoolean("authenticationOperateMobileEnabled") != null) {
                        LoginActivity.this.e0 = LoginActivity.this.U.getBoolean("authenticationOperateMobileEnabled");
                        if (LoginActivity.this.e0.booleanValue()) {
                            LoginActivity.this.j0.setVisibility(0);
                            LoginActivity.this.m0 = new ArrayList();
                            if (y7.a(LoginActivity.this, "android.permission.READ_EXTERNAL_STORAGE") == -1) {
                                LoginActivity.this.m0.add("android.permission.READ_EXTERNAL_STORAGE");
                                LoginActivity.this.m0.add("android.permission.WRITE_EXTERNAL_STORAGE");
                            }
                            if (y7.a(LoginActivity.this, "android.permission.READ_PHONE_STATE") == -1) {
                                LoginActivity.this.m0.add("android.permission.READ_PHONE_STATE");
                            }
                            if (LoginActivity.this.m0 != null && !LoginActivity.this.m0.isEmpty()) {
                                j7.a(LoginActivity.this, (String[]) LoginActivity.this.m0.toArray(new String[0]), 0);
                            }
                            if (LoginActivity.this.m0 == null || LoginActivity.this.m0.isEmpty()) {
                                LoginActivity.this.x();
                            } else {
                                j7.a(LoginActivity.this, (String[]) LoginActivity.this.m0.toArray(new String[0]), 16);
                            }
                        } else {
                            LoginActivity.this.j0.setVisibility(8);
                        }
                    }
                    Log.e(WXBaseActivity.TAG, "questionExtra: ======= " + LoginActivity.this.M);
                    Log.e(WXBaseActivity.TAG, "loginModeConfig: ======= " + LoginActivity.this.U);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public class n implements Callback<et1> {
        public n() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<et1> call, Throwable th) {
            Log.e(WXBaseActivity.TAG, "onFailure: ======= ");
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<et1> call, retrofit2.Response<et1> response) {
            try {
                LoginActivity.this.Q = response.body().string();
                Log.i(WXBaseActivity.TAG, "onResponse: ==== " + LoginActivity.this.Q);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public class o implements Callback<et1> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Boolean f4233a;
        public final /* synthetic */ Boolean b;
        public final /* synthetic */ int c;
        public final /* synthetic */ String d;

        public class a implements ti1.a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ org.json.JSONObject f4235a;
            public final /* synthetic */ ti1 b;

            public a(org.json.JSONObject jSONObject, ti1 ti1Var) {
                this.f4235a = jSONObject;
                this.b = ti1Var;
            }

            @Override // supwisdom.ti1.a
            public void a() {
                Intent intent = new Intent(LoginActivity.this, (Class<?>) BasicInformationActivity.class);
                intent.putExtra("monitorConfig", this.f4235a.toString());
                intent.putExtra("userToken", o.this.d);
                intent.putExtra("userNonCompleted", o.this.f4233a);
                intent.putExtra("userCompletedCheckEnabled", o.this.b);
                intent.putExtra("passwordStatus", o.this.c);
                LoginActivity.this.startActivity(intent);
                this.b.dismiss();
            }
        }

        public o(Boolean bool, Boolean bool2, int i, String str) {
            this.f4233a = bool;
            this.b = bool2;
            this.c = i;
            this.d = str;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<et1> call, Throwable th) {
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<et1> call, retrofit2.Response<et1> response) {
            try {
                org.json.JSONObject jSONObject = new org.json.JSONObject(response.body().string());
                fn1.H = jSONObject.getJSONObject("data").getString("nonce");
                LoginActivity.this.m.setVisibility(8);
                LoginActivity.this.h.setVisibility(0);
                LoginActivity.this.f4207a.setEnabled(true);
                LoginActivity.this.b.setEnabled(true);
                LoginActivity.this.j.setClickable(true);
                ti1 ti1Var = new ti1(LoginActivity.this, this.f4233a, this.b, this.c);
                ti1Var.show();
                ti1Var.a(new a(jSONObject, ti1Var));
            } catch (Exception e2) {
                e2.printStackTrace();
                if (this.c != 0) {
                    LoginActivity.this.k("当前密码为弱密码,请到平台进行修改");
                } else {
                    LoginActivity.this.k(e2.getMessage());
                }
            }
        }
    }

    public class p implements Callback<Response<JSONObject>> {

        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ String f4237a;

            public a(String str) {
                this.f4237a = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                Map<String, String> mapAuthV2 = new AuthTask(LoginActivity.this).authV2(this.f4237a, true);
                Message message = new Message();
                message.what = 11;
                message.obj = mapAuthV2;
                WXApplication.federatedCodeHandler.sendMessage(message);
            }
        }

        public p() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            LoginActivity loginActivity = LoginActivity.this;
            Toast.makeText(loginActivity, loginActivity.getResources().getString(R.string.net_error), 0).show();
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() == 200 && response.body() != null && response.body().data != null && response.body().code == 0) {
                new Thread(new a(response.body().data.getString("authzInfo"))).start();
            } else {
                LoginActivity loginActivity = LoginActivity.this;
                Toast.makeText(loginActivity, loginActivity.getResources().getString(R.string.authorize_error), 0).show();
            }
        }
    }

    public class q implements Callback<Response<JSONObject>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4238a;

        public q(String str) {
            this.f4238a = str;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null || response.body().data == null || response.body().code != 0) {
                return;
            }
            JSONObject jSONObject = response.body().data;
            jSONObject.getString("authzInfo");
            fn1.H = jSONObject.getString("nonce");
            LoginActivity.this.B = jSONObject.getString("callbackUri");
            LoginActivity.this.a(null, null, null, null, this.f4238a);
        }
    }

    public class r implements GyCallBack {

        public class a implements GyCallBack {
            public a() {
            }

            @Override // com.g.gysdk.GyCallBack
            public void onFailed(GYResponse gYResponse) {
                Log.e(WXBaseActivity.TAG, "登录前一个界面 提前预登录失败 prelogin:" + gYResponse);
                Toast.makeText(LoginActivity.this, "预登录超时", 0).show();
                LoginActivity.this.C();
            }

            @Override // com.g.gysdk.GyCallBack
            public void onSuccess(GYResponse gYResponse) {
                Log.d(WXBaseActivity.TAG, "登录前一个界面 提前预登录成功 prelogin:" + gYResponse);
                LoginActivity.this.B();
            }
        }

        public r() {
        }

        @Override // com.g.gysdk.GyCallBack
        public void onFailed(GYResponse gYResponse) {
            Log.e(WXBaseActivity.TAG, "init onFailed response:" + gYResponse);
        }

        @Override // com.g.gysdk.GyCallBack
        public void onSuccess(GYResponse gYResponse) {
            Log.d(WXBaseActivity.TAG, "init onSuccess response:" + gYResponse);
            LoginActivity.this.p0 = gYResponse.getGyuid();
            if (GYManager.getInstance().isPreLoginResultValid()) {
                return;
            }
            GYManager.getInstance().ePreLogin(3000, new a());
        }
    }

    public class s extends Handler {
        public s() {
        }

        @Override // android.os.Handler
        @SuppressLint({"HandlerLeak"})
        public void handleMessage(Message message) {
            Log.d("alipay111", message.what + "");
            if (message.what != 2) {
                return;
            }
            pm1 pm1Var = new pm1((Map) message.obj, true);
            String strC = pm1Var.c();
            Log.d("alipay", new Gson().toJson(pm1Var));
            if (TextUtils.equals(strC, "9000") && TextUtils.equals(pm1Var.b(), "200")) {
                LoginActivity.this.a(pm1Var.a(), null, null, null, "");
                sh1.c.b(fn1.b0, pm1Var.a());
            } else {
                if (TextUtils.equals(strC, "6001")) {
                    return;
                }
                Toast.makeText(LoginActivity.this, R.string.authorize_error, 0).show();
            }
        }
    }

    public class t implements GyCallBack {

        public class a implements GyCallBack {
            public a() {
            }

            @Override // com.g.gysdk.GyCallBack
            public void onFailed(GYResponse gYResponse) {
                Log.e(WXBaseActivity.TAG, "弹登录界面需要的 预登录失败 prelogin:" + gYResponse);
                LoginActivity.this.s();
                LoginActivity.this.F0.set(false);
            }

            @Override // com.g.gysdk.GyCallBack
            public void onSuccess(GYResponse gYResponse) {
                Log.d(WXBaseActivity.TAG, "弹登录界面需要的 预登录成功 prelogin:" + gYResponse);
                LoginActivity.this.s();
                LoginActivity.this.F0.set(false);
            }
        }

        public t() {
        }

        @Override // com.g.gysdk.GyCallBack
        public void onFailed(GYResponse gYResponse) {
            Log.e(WXBaseActivity.TAG, "初始化时 提前预登录失败 prelogin:" + gYResponse);
        }

        @Override // com.g.gysdk.GyCallBack
        public void onSuccess(GYResponse gYResponse) {
            Log.d(WXBaseActivity.TAG, "初始化时 提前预登录成功 prelogin:" + gYResponse);
            LoginActivity.this.B0 = true;
            if (LoginActivity.this.o0.getString("priorityAppWay") == null || !"operateMobile".equals(LoginActivity.this.o0.getString("priorityAppWay"))) {
                return;
            }
            if (!GYManager.getInstance().isPreLoginResultValid()) {
                LoginActivity.this.E();
                GYManager.getInstance().ePreLogin(5000, new a());
                return;
            }
            LoginActivity.this.i0.setVisibility(0);
            LoginActivity.this.h0.setVisibility(8);
            LoginActivity.this.l.setSelected(true);
            LoginActivity.this.i.setSelected(true);
            LoginActivity.this.l.setVisibility(0);
            LoginActivity.this.j.setVisibility(8);
            LoginActivity.this.H.setVisibility(8);
            LoginActivity.this.j0.setText("账号密码登录");
            LoginActivity.this.B();
            LoginActivity.this.r();
        }
    }

    public class u implements GyCallBack {
        public u() {
        }

        @Override // com.g.gysdk.GyCallBack
        public void onFailed(GYResponse gYResponse) {
            Log.e(WXBaseActivity.TAG, "启动授权页再预登录-预登录失败 response" + gYResponse);
            LoginActivity.this.s();
        }

        @Override // com.g.gysdk.GyCallBack
        public void onSuccess(GYResponse gYResponse) {
            Log.d(WXBaseActivity.TAG, "启动授权页再预登录-预登录成功 response" + gYResponse);
            LoginActivity.this.s();
            LoginActivity.this.m();
        }
    }

    public class v implements View.OnClickListener {
        public v() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Log.d(WXBaseActivity.TAG, "点击了隐私协议checkBox，当前状态：" + ((CheckBox) view).isChecked());
        }
    }

    public class w implements View.OnClickListener {
        public w() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LoginActivity.this.o.setVisibility(0);
            LoginActivity.this.i.setVisibility(8);
            Log.d(WXBaseActivity.TAG, "一键登录按钮 onLoginClick:");
            if (LoginActivity.this.x0.isChecked()) {
                return;
            }
            Toast.makeText(LoginActivity.this, "请先仔细阅读协议并勾选，然后再点击登录", 0).show();
            throw new IllegalStateException("请先仔细阅读协议并勾选，然后再点击登录");
        }
    }

    public class x implements EloginActivityParam.UIErrorListener {
        public x() {
        }

        @Override // com.g.gysdk.EloginActivityParam.UIErrorListener
        public void onError(String str) {
            Log.e(WXBaseActivity.TAG, "UIErrorListener.onError:" + str);
            LoginActivity.this.o.setVisibility(8);
            LoginActivity.this.i.setVisibility(0);
            LoginActivity.this.f4207a.setEnabled(true);
            LoginActivity.this.b.setEnabled(true);
            LoginActivity.this.j.setClickable(true);
            if ("UI不合规不能登录：不得遮挡 activity".equals(str) || "UI不合规不能登录：不得修改必要元素内容 NumberTextview".equals(str)) {
                Toast.makeText(LoginActivity.this, "当前网络不佳，请退出重新登录", 0).show();
            }
        }
    }

    public class y implements GyCallBack {
        public y() {
        }

        @Override // com.g.gysdk.GyCallBack
        public void onFailed(GYResponse gYResponse) {
            Log.e(WXBaseActivity.TAG, "登录失败 response:" + gYResponse);
            LoginActivity.this.o.setVisibility(8);
            LoginActivity.this.i.setVisibility(0);
            try {
                new org.json.JSONObject(gYResponse.getMsg()).getInt("errorCode");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override // com.g.gysdk.GyCallBack
        public void onSuccess(GYResponse gYResponse) {
            Log.d(WXBaseActivity.TAG, "登录成功 response:" + gYResponse);
            LoginActivity.this.o.setVisibility(8);
            LoginActivity.this.i.setVisibility(0);
            try {
                org.json.JSONObject jSONObject = new org.json.JSONObject(gYResponse.getMsg());
                org.json.JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                jSONObject.getString(CrashHianalyticsData.PROCESS_ID);
                String string = jSONObject2.getString("token");
                Log.d(WXBaseActivity.TAG, "token:" + string + "  expiredTime:" + jSONObject2.getLong("expiredTime"));
                LoginActivity.this.l(string);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public class z implements Runnable {
        public z() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (LoginActivity.this.w0 == null) {
                LoginActivity.this.w0 = new ui1(LoginActivity.this);
            }
            LoginActivity.this.w0.show();
        }
    }

    public static synchronized String getAppName(Context context) {
        try {
        } catch (Exception e2) {
            lm1.a(e2);
            e2.printStackTrace();
            return null;
        }
        return context.getResources().getString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes);
    }

    public static void loginFinish() {
        I0.finish();
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        if (this.f4207a.getText().toString().trim().equals("") || this.b.getText().toString().trim().equals("")) {
            this.j.setSelected(false);
            this.h.setSelected(false);
        } else {
            this.j.setSelected(true);
            this.h.setSelected(true);
            this.k.setSelected(true);
        }
        if (TextUtils.isEmpty(this.s.getText().toString())) {
            this.k.setSelected(false);
        } else {
            this.k.setSelected(true);
        }
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        if (i2 == 1 && (i3 == SecureCertificationActivity.N || i3 == SecureCertificationActivity.O || i3 == SecureCertificationActivity.P)) {
            f(this.K);
        }
        Tencent.onActivityResultData(i2, i3, intent, this);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        ui1 ui1Var = this.F;
        if (ui1Var != null) {
            ui1Var.dismiss();
        }
    }

    @Override // com.tencent.tauth.IUiListener
    public void onCancel() {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.accountActiveBt /* 2131296300 */:
                Intent intent = new Intent();
                intent.setClass(this, AccountInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.accountClearBtn /* 2131296301 */:
                this.f4207a.setText("");
                break;
            case R.id.alipayLoginBtn /* 2131296338 */:
                if (!this.I.isChecked()) {
                    e("alipayLogin");
                } else {
                    l();
                }
                break;
            case R.id.backBtn /* 2131296358 */:
                finish();
                break;
            case R.id.eyeBt /* 2131296575 */:
                this.c.setSelected(!r3.isSelected());
                if (!this.c.isSelected()) {
                    this.b.setInputType(129);
                } else {
                    this.b.setInputType(144);
                }
                break;
            case R.id.faceLoginBtn /* 2131296577 */:
                if (!this.I.isChecked()) {
                    e("faceLogin");
                } else {
                    startActivity(new Intent(this, (Class<?>) FaceLoginActivity.class));
                }
                break;
            case R.id.forgetBt /* 2131296600 */:
                Intent intent2 = new Intent();
                intent2.setClass(this, ForgetPasswordActivity.class);
                startActivity(intent2);
                break;
            case R.id.loginBt /* 2131296851 */:
                G();
                break;
            case R.id.passwordLoginBtn /* 2131296916 */:
                this.v.setVisibility(0);
                this.u.setVisibility(8);
                break;
            case R.id.qqLoginBtn /* 2131296949 */:
                if (!this.I.isChecked()) {
                    e("qqLogin");
                } else {
                    y();
                }
                break;
            case R.id.smsLoginBtn /* 2131297056 */:
                this.v.setVisibility(8);
                this.u.setVisibility(0);
                break;
            case R.id.smsSendBtn /* 2131297058 */:
                n();
                break;
            case R.id.tv_gy_login /* 2131297205 */:
                if (this.h0.getVisibility() != 0) {
                    this.i0.setVisibility(8);
                    this.h0.setVisibility(0);
                    this.j.setSelected(false);
                    this.h.setSelected(false);
                    this.l.setVisibility(8);
                    this.j.setVisibility(0);
                    this.z0.setVisibility(0);
                    this.A0.setVisibility(8);
                    this.j0.setText("本机一键登录");
                    C();
                } else if (!GYManager.getInstance().isPreLoginResultValid()) {
                    E();
                    GYManager.getInstance().ePreLogin(5000, new a());
                } else {
                    this.F0.set(false);
                    Intent intent3 = new Intent(this, (Class<?>) GYLoginActivityFullscreen.class);
                    intent3.putExtra("gyuid", this.p0);
                    intent3.putExtra("userCompletedCheckEnabled", this.V);
                    startActivity(intent3);
                }
                break;
            case R.id.tv_notice_content /* 2131297227 */:
                if (this.J.getVisibility() != 8) {
                    new xi1(this, this.T).show();
                } else {
                    this.J.setClickable(false);
                }
                break;
            case R.id.tv_question /* 2131297247 */:
                z();
                break;
            case R.id.tv_visitor_register /* 2131297288 */:
                Intent intent4 = new Intent();
                intent4.setClass(this, H5Activity.class);
                intent4.setFlags(268435456);
                intent4.setData(Uri.parse(this.u0));
                startActivity(intent4);
                break;
            case R.id.wechatLoginBtn /* 2131297337 */:
                if (!this.I.isChecked()) {
                    e("wechatLogin");
                } else if (!WXApplication.api.isWXAppInstalled()) {
                    Toast.makeText(this, "您还未安装微信客户端", 0).show();
                } else {
                    SendAuth.Req req = new SendAuth.Req();
                    req.scope = WeiXinOAuthService.DEFAULT_SCOPE;
                    req.state = "superapp_wx_login";
                    WXEntryActivity.b = "login";
                    WXApplication.api.sendReq(req);
                }
                break;
            case R.id.workWeChatLoginBtn /* 2131302341 */:
                if (!this.I.isChecked()) {
                    e("workWechatLogin");
                } else {
                    IWWAPI iwwapiCreateWWAPI = WWAPIFactory.createWWAPI(this);
                    iwwapiCreateWWAPI.registerApp(com.igexin.push.core.b.m);
                    WWAuthMessage.Req req2 = new WWAuthMessage.Req();
                    req2.sch = fn1.G;
                    req2.appId = fn1.E;
                    req2.agentId = fn1.F;
                    req2.state = "ww";
                    if (iwwapiCreateWWAPI.isWWAppSupportAPI()) {
                        iwwapiCreateWWAPI.sendMessage(req2, new IWWAPIEventHandler() { // from class: supwisdom.rk1
                            @Override // com.tencent.wework.api.IWWAPIEventHandler
                            public final void handleResp(BaseMessage baseMessage) {
                                this.f9072a.b(baseMessage);
                            }
                        });
                    }
                }
                break;
        }
    }

    @Override // com.tencent.tauth.IUiListener
    public void onComplete(Object obj) {
        ui1 ui1Var = new ui1(this);
        this.F = ui1Var;
        ui1Var.show();
        try {
            JSONObject object = JSON.parseObject(String.valueOf(obj));
            String string = object.getString("access_token");
            this.E.setAccessToken(string, object.getString("expires_time"));
            this.E.setOpenId(object.getString("openid"));
            new UnionInfo(null, this.E.getQQToken()).getUnionId(new k(string));
        } catch (Exception unused) {
            n(getResources().getString(R.string.qq_login_error));
        }
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.w0 = new ui1(this);
        this.E = Tencent.createInstance(com.igexin.push.core.b.m, getApplicationContext(), "com.supwisdom.zueb.UploadFileProvider");
        Log.d("网络超时测试", System.currentTimeMillis() + "");
        WXApplication.setInitDCUniMPSDKCallback(this);
        this.trackPageName = getResources().getString(R.string.login_activity);
        WXBaseActivity.transparencyBar(this);
        WXBaseActivity.setLightStatusBar(this, true);
        String str = om1.c(this) + File.separator + fn1.i + ".wgt";
        setContentView(R.layout.layout_login);
        this.f4207a = (EditText) findViewById(R.id.userNameTxt);
        this.b = (EditText) findViewById(R.id.passwordTxt);
        this.c = (ImageButton) findViewById(R.id.eyeBt);
        this.t = (FullVideoView) findViewById(R.id.videoViewBg);
        this.j = findViewById(R.id.loginBt);
        this.w = (ImageView) findViewById(R.id.wechatLoginBtn);
        this.m = (ProgressBar) findViewById(R.id.loading);
        this.o = (ProgressBar) findViewById(R.id.gy_loading);
        this.D = (ImageView) findViewById(R.id.loginBg);
        this.h = (TextView) findViewById(R.id.loginTxt);
        this.f = findViewById(R.id.forgetBt);
        this.f4208e = (ImageButton) findViewById(R.id.backBtn);
        this.p = (TextView) findViewById(R.id.accountActiveBt);
        this.q = (TextView) findViewById(R.id.smsLoginBtn);
        this.v = (LinearLayout) findViewById(R.id.passwordLoginLayout);
        this.u = (LinearLayout) findViewById(R.id.smsLoginLayout);
        this.r = (TextView) findViewById(R.id.passwordLoginBtn);
        this.s = (TextView) findViewById(R.id.phoneTxt);
        this.k = findViewById(R.id.smsSendBtn);
        this.n = (ProgressBar) findViewById(R.id.smsSendLoading);
        this.g = findViewById(R.id.smsSendTxt);
        this.d = (ImageButton) findViewById(R.id.accountClearBtn);
        this.A = (ImageButton) findViewById(R.id.faceLoginBtn);
        this.x = (ImageView) findViewById(R.id.qqLoginBtn);
        this.y = (ImageView) findViewById(R.id.alipayLoginBtn);
        this.z = (ImageView) findViewById(R.id.workWeChatLoginBtn);
        this.G = (LinearLayout) findViewById(R.id.ll_other_login_tips);
        this.L = (TextView) findViewById(R.id.tv_question);
        this.N = (TextView) findViewById(R.id.tv_visitor_register);
        this.h0 = (LinearLayout) findViewById(R.id.ll_account_password);
        this.I = (CheckBox) findViewById(R.id.agreeBtn);
        this.H = (TextView) findViewById(R.id.privacyTv);
        this.z0 = (LinearLayout) findViewById(R.id.privacyLL);
        this.i0 = (LinearLayout) findViewById(R.id.ll_gy_login);
        this.j0 = (TextView) findViewById(R.id.tv_gy_login);
        this.i = (TextView) findViewById(R.id.tv_gy_txt);
        this.A0 = (LinearLayout) findViewById(R.id.ll_privacy_gy);
        this.l = findViewById(R.id.fl_gy_login);
        this.x0 = (CheckBox) findViewById(R.id.agreeBtn_gy);
        this.k0 = (TextView) findViewById(R.id.tv_phone_number);
        this.l0 = (TextView) findViewById(R.id.tv_slogan);
        this.y0 = (TextView) findViewById(R.id.tv_privacy_gy);
        this.J = (TextView) findViewById(R.id.tv_notice_content);
        String str2 = this.T;
        if (str2 == null || "".equals(str2)) {
            this.J.setVisibility(8);
            this.J.setClickable(false);
        } else {
            this.J.setVisibility(0);
            this.J.setText(this.T);
            this.J.setClickable(true);
        }
        this.J.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.f4208e.setOnClickListener(this);
        this.q.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.p.setOnClickListener(this);
        this.r.setOnClickListener(this);
        this.A.setOnClickListener(this);
        this.x.setOnClickListener(this);
        this.y.setOnClickListener(this);
        this.z.setOnClickListener(this);
        this.L.setOnClickListener(this);
        this.N.setOnClickListener(this);
        this.H.setOnClickListener(this);
        this.I.setOnClickListener(this);
        this.j0.setOnClickListener(this);
        if (!TextUtils.isEmpty(fn1.T)) {
            try {
                String str3 = new String(fn1.T.getBytes("iso8859-1"), "UTF-8");
                if ("".equals(this.X)) {
                    this.f4207a.setHint(str3);
                } else {
                    this.f4207a.setHint(this.X);
                }
            } catch (UnsupportedEncodingException e2) {
                lm1.a(e2);
                e2.printStackTrace();
            }
        }
        this.b.setHint(!"".equals(this.Y) ? this.Y : "输入密码");
        this.w.setVisibility(this.d0.booleanValue() ? 0 : 8);
        this.f4207a.setRawInputType(2);
        this.f4207a.addTextChangedListener(this);
        this.b.addTextChangedListener(this);
        this.b.setOnEditorActionListener(this);
        this.s.addTextChangedListener(this);
        this.f.setOnClickListener(this);
        this.w.setOnClickListener(this);
        if (!fn1.M) {
            this.A.setVisibility(8);
        } else if (in1.a() != null) {
            this.A.setEnabled(true);
        } else {
            this.A.setAlpha(0.5f);
            this.A.setEnabled(false);
        }
        if (!fn1.O) {
            this.y.setVisibility(8);
        }
        if (!fn1.N) {
            this.x.setVisibility(8);
        }
        if (!fn1.P) {
            this.w.setVisibility(8);
        }
        if (fn1.L.equals("video")) {
            this.t.setVisibility(0);
            this.D.setVisibility(8);
            D();
        } else {
            this.t.setVisibility(8);
            this.D.setVisibility(0);
            this.D.setImageResource(R.drawable.login_bg);
        }
        if (!this.q0) {
            this.q.setVisibility(8);
        }
        if (!fn1.S) {
            this.f.setVisibility(8);
        }
        if (fn1.R) {
            this.p.setVisibility(0);
        }
        if (this.A.getVisibility() == 8 && this.w.getVisibility() == 8 && this.x.getVisibility() == 8 && this.y.getVisibility() == 8) {
            this.G.setVisibility(8);
        }
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("messageTypeSuperApp");
        if (intent != null && !TextUtils.isEmpty(stringExtra)) {
            if (stringExtra.equals("login")) {
                Toast.makeText(this, "账号在其他设备登录，请重新登录", 0).show();
            } else {
                Toast.makeText(this, "您的账号已重置密码，请重新登录" + getAppName(this), 0).show();
            }
        }
        C();
        I0 = this;
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        F();
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
        if (this.f4207a.getText() == null || this.b.getText() == null || this.f4207a.getText().toString().trim().equals("") || this.b.getText().toString().trim().equals("")) {
            return true;
        }
        f("");
        return true;
    }

    @Override // com.tencent.tauth.IUiListener
    public void onError(UiError uiError) {
        Toast.makeText(this, uiError.errorMessage, 0).show();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4 || i2 == 3) {
            keyEvent.getRepeatCount();
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        List<ActivityManager.RunningTaskInfo> runningTasks;
        super.onPause();
        if (this.t.canPause()) {
            this.t.pause();
        }
        JSONObject object = JSON.parseObject("{\"openHijacking\":false} ");
        if (object.getBoolean("openHijacking") == null || !object.getBoolean("openHijacking").booleanValue() || (runningTasks = ((ActivityManager) getSystemService("activity")).getRunningTasks(1)) == null || !runningTasks.get(0).topActivity.getPackageName().equalsIgnoreCase("com.supwisdom.zueb")) {
            return;
        }
        new kj1(this, R.style.Dialog).b();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, supwisdom.j7.c
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        if (i2 != 16) {
            return;
        }
        x();
    }

    @Override // android.app.Activity
    public void onRestart() {
        super.onRestart();
        k("");
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.t.isPlaying()) {
            return;
        }
        this.t.resume();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        JSONObject object = JSON.parseObject("{\"openHijacking\":false} ");
        if (object.getBoolean("isEncrypt") != null && (object.getBoolean("isEncrypt").booleanValue() || this.C0.booleanValue())) {
            p();
        }
        if (object.getBoolean("loginProblem") != null) {
            boolean zBooleanValue = object.getBoolean("loginProblem").booleanValue();
            this.R = zBooleanValue;
            if (!zBooleanValue) {
                this.L.setVisibility(4);
            }
        }
        if (object.getBoolean("visitorRegister") != null && object.getBoolean("visitorRegister").booleanValue()) {
            this.N.setVisibility(0);
        }
        o();
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
    }

    @Override // com.tencent.tauth.IUiListener
    public void onWarning(int i2) {
        Log.d("qq", "onWarning");
    }

    public final void A() {
        mj1.b().f(this.s.getText().toString(), fn1.H).enqueue(new g0());
    }

    public final void B() {
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
        spannableString.setSpan(new i(), iIndexOf, string.length() + iIndexOf, 34);
        spannableString.setSpan(new j(), iIndexOf2, string2.length() + iIndexOf2, 34);
        spannableString.setSpan(new l(preLoginResult), iIndexOf3, strConcat2.length() + iIndexOf3, 34);
        this.y0.setMovementMethod(LinkMovementMethod.getInstance());
        this.y0.setText(spannableString);
    }

    public final void C() {
        String string = getResources().getString(R.string.privacy_tips);
        String string2 = getResources().getString(R.string.privacy_tips_key1);
        String string3 = getResources().getString(R.string.privacy_tips_key2);
        int iIndexOf = string.indexOf(string2);
        int iIndexOf2 = string.indexOf(string3);
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.result_text)), iIndexOf, string2.length() + iIndexOf, 34);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.result_text)), iIndexOf2, string3.length() + iIndexOf2, 34);
        spannableString.setSpan(new g(), iIndexOf, string2.length() + iIndexOf, 34);
        spannableString.setSpan(new h(), iIndexOf2, string3.length() + iIndexOf2, 34);
        this.H.setMovementMethod(LinkMovementMethod.getInstance());
        this.H.setText(spannableString);
    }

    public final void D() {
        this.t.setOnPreparedListener(new b());
        this.t.setOnCompletionListener(new c());
        this.t.setOnErrorListener(new d());
        try {
            this.t.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/raw/" + R.raw.login_video));
        } catch (Exception e2) {
            lm1.a(e2);
            e2.printStackTrace();
        }
    }

    public void E() {
        runOnUiThread(new z());
    }

    public final void F() {
        try {
            this.t.stopPlayback();
        } catch (Exception e2) {
            lm1.a(e2);
            e2.printStackTrace();
        }
    }

    public final void G() {
        String string;
        String string2;
        String str;
        if (this.j.isSelected()) {
            this.m.setVisibility(0);
            this.f4207a.setEnabled(false);
            this.b.setEnabled(false);
            this.h.setVisibility(8);
            this.j.setClickable(false);
            if (!this.I.isChecked()) {
                e("twoFactorLogin");
                return;
            }
            String strC = sh1.c.c(fn1.p);
            if (strC == null || "".equals(strC)) {
                strC = UTDevice.getUtdid(this);
                sh1.c.b(fn1.p, strC);
            }
            if (!"".equals(this.Q) && (str = this.Q) != null) {
                try {
                    this.O = bn1.a(str, this.f4207a.getText().toString());
                    this.P = bn1.a(this.Q, this.b.getText().toString());
                    Log.e(WXBaseActivity.TAG, "onResponse: rsaName === " + this.O);
                    Log.e(WXBaseActivity.TAG, "onResponse: rsaPassword === " + this.P);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            String str2 = this.O;
            if (str2 != null) {
                string = m("__RSA__".concat(String.valueOf(str2)));
                Log.e(WXBaseActivity.TAG, "doLogin: ==== " + string);
            } else {
                string = this.f4207a.getText().toString();
            }
            String str3 = this.P;
            if (str3 != null) {
                string2 = m("__RSA__".concat(String.valueOf(str3)));
                Log.e(WXBaseActivity.TAG, "doLogin: ==== " + string2);
            } else {
                string2 = this.b.getText().toString();
            }
            mj1.b().l(string, strC != null ? strC : "", string2).enqueue(new h0());
        }
    }

    public final String m(String str) {
        return str != null ? Pattern.compile("\\s*|\t|\r|\n").matcher(str).replaceAll("") : "";
    }

    public void n() {
        if (this.k.isSelected()) {
            this.n.setVisibility(0);
            this.g.setVisibility(8);
            this.s.setEnabled(false);
            this.k.setClickable(false);
            if (this.I.isChecked()) {
                mj1.b().f().enqueue(new f0());
            } else {
                e("doGainSMS");
            }
        }
    }

    public final void o() {
        mj1.b().c().enqueue(new m());
    }

    public final void p() {
        mj1.b().getPublicKey().enqueue(new n());
    }

    public final void q() {
        if (this.j.isSelected()) {
            this.m.setVisibility(0);
            this.h.setVisibility(8);
            if (this.I.isChecked()) {
                return;
            }
            e("gyLogin");
        }
    }

    public final void r() {
        E();
        if (!GYManager.getInstance().isPreLoginResultValid()) {
            GYManager.getInstance().ePreLogin(5000, new u());
        } else {
            s();
            m();
        }
    }

    public void s() {
        runOnUiThread(new a0());
    }

    public final void t() {
        ui1 ui1Var = this.w0;
        if (ui1Var != null) {
            ui1Var.dismiss();
            this.w0 = null;
        }
    }

    public void u() {
        mj1.b().e(getPackageName(), UUID.randomUUID().toString(), UTDevice.getUtdid(this)).enqueue(new p());
    }

    public void v() {
        FaceSDKManager.getInstance().initialize(this, fn1.b, fn1.c);
        FaceConfig faceConfig = FaceSDKManager.getInstance().getFaceConfig();
        G0.add(LivenessTypeEnum.Eye);
        faceConfig.setLivenessTypeList(G0);
        faceConfig.setLivenessRandom(H0);
        faceConfig.setBlurnessValue(0.5f);
        faceConfig.setBrightnessValue(40.0f);
        faceConfig.setCropFaceValue(400);
        faceConfig.setHeadPitchValue(10);
        faceConfig.setHeadRollValue(10);
        faceConfig.setHeadYawValue(10);
        faceConfig.setMinFaceSize(200);
        faceConfig.setNotFaceValue(0.6f);
        faceConfig.setOcclusionValue(0.5f);
        faceConfig.setCheckFaceQuality(true);
        faceConfig.setFaceDecodeNumberOfThreads(2);
        FaceSDKManager.getInstance().setFaceConfig(faceConfig);
    }

    public final void w() {
        GYManager.getInstance().init(GyConfig.with(getApplicationContext()).preLoginUseCache(true).debug(false).eLoginDebug(false).channel("com.superwisdom.superapp").callBack(new r()).build());
        GYManager.getInstance().ePreLogin(8000, new t());
    }

    public final void x() {
        GYManager.getInstance().setDebug(true);
        w();
    }

    public final void y() {
        String strC = sh1.c.c(fn1.p);
        if (strC == null || "".equals(strC)) {
            strC = UTDevice.getUtdid(this);
            sh1.c.b(fn1.p, strC);
        }
        mj1.b().f(getPackageName(), UUID.randomUUID().toString(), strC).enqueue(new f());
    }

    public final void z() {
        new dj1(this, this.v0, this.Z, this.a0).show();
    }

    public void l() {
        String string = UUID.randomUUID().toString();
        String strC = sh1.c.c(fn1.p);
        if (strC == null || "".equals(strC)) {
            strC = UTDevice.getUtdid(this);
            sh1.c.b(fn1.p, strC);
        }
        mj1.b().b(getPackageName(), string, strC).enqueue(new e());
    }

    public final void e(final String str) {
        if (this.v.getVisibility() == 0) {
            this.m.setVisibility(8);
            this.h.setVisibility(0);
            this.f4207a.setEnabled(true);
            this.b.setEnabled(true);
            this.j.setClickable(true);
        } else {
            this.s.setEnabled(true);
            this.k.setClickable(true);
            this.n.setVisibility(8);
            this.g.setVisibility(0);
        }
        oi1 oi1Var = new oi1(this);
        this.S = oi1Var;
        oi1Var.a(new oi1.c() { // from class: supwisdom.tk1
            @Override // supwisdom.oi1.c
            public final void a() {
                this.f9300a.i(str);
            }
        });
        this.S.show();
    }

    public void f(String str) {
        String string;
        String string2;
        String str2;
        if (this.j.isSelected()) {
            this.m.setVisibility(0);
            this.f4207a.setEnabled(false);
            this.b.setEnabled(false);
            this.h.setVisibility(8);
            this.j.setClickable(false);
            if (!this.I.isChecked()) {
                e("login");
                return;
            }
            String strC = sh1.c.c(fn1.p);
            if (strC == null || "".equals(strC)) {
                strC = UTDevice.getUtdid(this);
                sh1.c.b(fn1.p, strC);
            }
            String str3 = strC;
            if (!"".equals(this.Q) && (str2 = this.Q) != null) {
                try {
                    this.O = bn1.a(str2, this.f4207a.getText().toString());
                    this.P = bn1.a(this.Q, this.b.getText().toString());
                    Log.e(WXBaseActivity.TAG, "onResponse: rsaName === " + this.O);
                    Log.e(WXBaseActivity.TAG, "onResponse: rsaPassword === " + this.P);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            String str4 = this.O;
            if (str4 != null) {
                string = m("__RSA__".concat(String.valueOf(str4)));
                Log.e(WXBaseActivity.TAG, "doLogin: ==== " + string);
            } else {
                string = this.f4207a.getText().toString();
            }
            String str5 = string;
            String str6 = this.P;
            if (str6 != null) {
                string2 = m("__RSA__".concat(String.valueOf(str6)));
                Log.e(WXBaseActivity.TAG, "doLogin: ==== " + string2);
            } else {
                string2 = this.b.getText().toString();
            }
            mj1.b().b(str5, string2, getPackageName(), "", str3, "android", PushManager.getInstance().getClientid(this), str).enqueue(new i0(str));
        }
    }

    public final void g(String str) {
        mj1.b().k(fn1.H).enqueue(new d0(str));
    }

    public final void h(String str) {
        mj1.b().j(getPackageName(), UUID.randomUUID().toString(), UTDevice.getUtdid(this)).enqueue(new q(str));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public /* synthetic */ void i(java.lang.String r5) {
        /*
            Method dump skipped, instruction units count: 318
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.supwisdom.superapp.ui.activity.LoginActivity.i(java.lang.String):void");
    }

    public final void j(String str) {
        mj1.b().d(fn1.H, str, PushManager.getInstance().getClientid(this)).enqueue(new e0());
    }

    public void k(String str) {
        if (this.v.getVisibility() == 0) {
            this.m.setVisibility(8);
            this.h.setVisibility(0);
            this.f4207a.setEnabled(true);
            this.b.setEnabled(true);
            this.j.setClickable(true);
        } else {
            this.s.setEnabled(true);
            this.k.setClickable(true);
            this.n.setVisibility(8);
            this.g.setVisibility(0);
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(this, str, 0).show();
    }

    @Override // com.supwisdom.superapp.WXApplication.k
    public void j() {
        u();
    }

    public final void m() {
        this.x0.setOnClickListener(new v());
        this.i0.setVisibility(0);
        this.h0.setVisibility(8);
        this.l.setSelected(true);
        this.i.setSelected(true);
        this.l.setVisibility(0);
        this.j.setVisibility(8);
        this.z0.setVisibility(8);
        this.A0.setVisibility(0);
        B();
        this.j0.setText("账号密码登录");
        this.n0 = new EloginActivityParam().setActivity(this).setNumberTextview(this.k0).setSloganTextview(this.l0).setLoginButton(this.l).setPrivacyCheckbox(this.x0).setPrivacyTextview(this.y0).setUiErrorListener(new x()).setLoginOnClickListener(new w());
        GYManager.getInstance().eAccountLogin(this.n0, 5000, new y());
    }

    public /* synthetic */ void b(BaseMessage baseMessage) {
        if (baseMessage instanceof WWAuthMessage.Resp) {
            WWAuthMessage.Resp resp = (WWAuthMessage.Resp) baseMessage;
            int i2 = resp.errCode;
            if (i2 == -1) {
                Toast.makeText(this, "登录取消", 0).show();
            } else if (i2 == 1) {
                Toast.makeText(this, "登录失败", 0).show();
            } else if (i2 == 0) {
                h(resp.code);
            }
        }
    }

    public final void l(String str) {
        String strC = sh1.c.c(fn1.p);
        if (strC == null || "".equals(strC)) {
            strC = UTDevice.getUtdid(this);
            sh1.c.b(fn1.p, strC);
        }
        String str2 = strC;
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("token", str);
            if (!"".equals(this.p0)) {
                jSONObject.put("gyuid", this.p0);
            }
            jSONObject.put("appId", com.igexin.push.core.b.m);
            jSONObject.put("deviceId", str2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString());
        mj1.b().c(str, this.p0, com.igexin.push.core.b.m, "", str2, "android", PushManager.getInstance().getClientid(this), this.K).enqueue(new b0());
    }

    public final void n(String str) {
        ui1 ui1Var = this.F;
        if (ui1Var != null) {
            ui1Var.dismiss();
        }
        Toast.makeText(this, str, 0).show();
    }

    public final void a(String str, String str2, String str3, String str4, String str5) {
        mj1.b().b(this.B, fn1.H, str, str2, str3, str4, str5).enqueue(new c0(str));
    }

    public final void a(String str, String str2, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4) {
        Intent intent = new Intent(this, (Class<?>) SecureCertificationActivity.class);
        intent.putExtra("mfaTypeSecurePhone", bool);
        intent.putExtra("mfaTypeSecureEmail", bool2);
        intent.putExtra("mfaTypeFaceVerify", bool3);
        intent.putExtra("mfaTypeFedAuth", bool4);
        intent.putExtra("type", str);
        intent.putExtra("state", str2);
        startActivityForResult(intent, 1);
    }

    @Override // supwisdom.in1.d
    public void k() {
        if (!DCUniMPSDK.getInstance().isInitialize()) {
            this.C = true;
        } else {
            Log.d("小程序", "立即启动");
            openMini();
        }
    }

    @Override // com.supwisdom.superapp.WXApplication.k
    public void a(boolean z2) {
        Log.d("小程序", z2 + "初始化结果");
        if (z2 && this.C) {
            openMini();
        }
    }

    @Override // com.supwisdom.superapp.WXApplication.k
    public void a(int i2) {
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        getWindow().getDecorView().setLayerType(2, paint);
    }

    public void a(String str, Boolean bool, Boolean bool2, int i2) {
        mj1.b().h(str).enqueue(new o(bool, bool2, i2, str));
    }

    public /* synthetic */ void a(BaseMessage baseMessage) {
        if (baseMessage instanceof WWAuthMessage.Resp) {
            WWAuthMessage.Resp resp = (WWAuthMessage.Resp) baseMessage;
            int i2 = resp.errCode;
            if (i2 == -1) {
                Toast.makeText(this, "登录取消", 0).show();
            } else if (i2 == 1) {
                Toast.makeText(this, "登录失败", 0).show();
            } else if (i2 == 0) {
                h(resp.code);
            }
        }
    }
}
