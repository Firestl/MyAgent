package com.supwisdom.superapp.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.FragmentActivity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.sdk.app.AuthTask;
import com.bumptech.glide.Glide;
import com.supwisdom.superapp.WXApplication;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.superapp.ddshare.DDShareActivity;
import com.supwisdom.superapp.entity.response.PersonalInformationResponse;
import com.supwisdom.superapp.service.model.Response;
import com.supwisdom.superapp.view.CircleImageView;
import com.supwisdom.superapp.view.PersonalMessageView;
import com.supwisdom.superapp.wxapi.WXEntryActivity;
import com.supwisdom.zueb.R;
import com.ta.utdid2.device.UTDevice;
import com.tencent.connect.UnionInfo;
import com.tencent.connect.common.Constants;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.tencent.wework.api.IWWAPI;
import com.tencent.wework.api.WWAPIFactory;
import com.tencent.wework.api.model.WWAuthMessage;
import io.dcloud.feature.oauth.weixin.WeiXinOAuthService;
import io.dcloud.feature.sdk.DCUniMPSDK;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;
import retrofit2.Call;
import retrofit2.Callback;
import supwisdom.bj1;
import supwisdom.cm1;
import supwisdom.ct1;
import supwisdom.et1;
import supwisdom.fn1;
import supwisdom.in1;
import supwisdom.j7;
import supwisdom.lm1;
import supwisdom.lr;
import supwisdom.mj1;
import supwisdom.or;
import supwisdom.pi1;
import supwisdom.pm1;
import supwisdom.sh1;
import supwisdom.si1;
import supwisdom.ui1;
import supwisdom.vr;
import supwisdom.xl1;
import supwisdom.xs1;
import supwisdom.y7;

/* JADX INFO: loaded from: classes2.dex */
public class InformationPerfectionActivity extends WXBaseActivity {
    public static boolean x = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f4161a;
    public ImageView b;
    public CircleImageView c;
    public Button d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public LinearLayout f4162e;
    public LinearLayout f;
    public ImageView g;
    public Tencent h;
    public ui1 i;
    public String j;
    public Context k = this;
    public IUiListener l;
    public Handler m;
    public or n;
    public Group o;
    public TextView p;
    public ArrayList<String> q;
    public ArrayList<Integer> r;
    public bj1 s;
    public String t;
    public int u;
    public TextView v;
    public String w;

    public class a implements Callback<Response<JSONObject>> {
        public a() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            InformationPerfectionActivity informationPerfectionActivity = InformationPerfectionActivity.this;
            informationPerfectionActivity.a(informationPerfectionActivity.getResources().getString(R.string.net_error), true);
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null || response.body().data == null || response.body().code != 0) {
                InformationPerfectionActivity.this.a(response.message(), true);
                return;
            }
            InformationPerfectionActivity.this.i.dismiss();
            fn1.H = response.body().data.getString("nonce");
            InformationPerfectionActivity.this.j = response.body().data.getString("callbackUri");
            if (InformationPerfectionActivity.this.h.isSessionValid()) {
                return;
            }
            Tencent tencent = InformationPerfectionActivity.this.h;
            InformationPerfectionActivity informationPerfectionActivity = InformationPerfectionActivity.this;
            tencent.login(informationPerfectionActivity, "get_simple_userinfo", informationPerfectionActivity.l);
        }
    }

    public class b implements Callback<Response<JSONObject>> {
        public b() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            InformationPerfectionActivity.this.a("网络错误", true);
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null || response.body().data == null) {
                InformationPerfectionActivity.this.a("绑定失败", true);
            } else {
                fn1.H = response.body().data.getString("nonce");
                InformationPerfectionActivity.this.l();
            }
        }
    }

    public class c implements Callback<Response<JSONObject>> {
        public c() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            InformationPerfectionActivity.this.a("绑定失败", true);
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() == 200 && response.body() != null && response.body().code == 0) {
                InformationPerfectionActivity.this.y();
            } else {
                InformationPerfectionActivity.this.a(response.message(), true);
            }
        }
    }

    public class d implements Callback<Response<JSONObject>> {
        public d() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            InformationPerfectionActivity.this.a("绑定失败", true);
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null || response.body().code != 0) {
                InformationPerfectionActivity.this.a(response.message(), true);
            } else {
                InformationPerfectionActivity.this.r();
                InformationPerfectionActivity.this.a("绑定成功", true);
            }
        }
    }

    public class e implements Callback<Response<JSONObject>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4167a;

        public e(String str) {
            this.f4167a = str;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            InformationPerfectionActivity informationPerfectionActivity = InformationPerfectionActivity.this;
            informationPerfectionActivity.a(informationPerfectionActivity.getResources().getString(R.string.net_error), true);
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null || response.body().data == null || response.body().code != 0) {
                InformationPerfectionActivity.this.a(response.message(), true);
                return;
            }
            JSONObject jSONObject = response.body().data;
            jSONObject.getString("authzInfo");
            fn1.H = jSONObject.getString("nonce");
            InformationPerfectionActivity.this.j = jSONObject.getString("callbackUri");
            InformationPerfectionActivity.this.f(this.f4167a);
        }
    }

    public class f implements Callback<Response<JSONObject>> {
        public f() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            InformationPerfectionActivity.this.a("网络错误", true);
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null || response.body().data == null) {
                InformationPerfectionActivity.this.a("绑定失败", true);
            } else {
                fn1.H = response.body().data.getString("nonce");
                InformationPerfectionActivity.this.l();
            }
        }
    }

    public class g implements Callback<Response<JSONObject>> {

        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ String f4170a;

            public a(String str) {
                this.f4170a = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                Map<String, String> mapAuthV2 = new AuthTask(InformationPerfectionActivity.this).authV2(this.f4170a, true);
                Message message = new Message();
                message.what = 11;
                message.obj = mapAuthV2;
                InformationPerfectionActivity.this.m.sendMessage(message);
            }
        }

        public g() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            InformationPerfectionActivity informationPerfectionActivity = InformationPerfectionActivity.this;
            informationPerfectionActivity.a(informationPerfectionActivity.getResources().getString(R.string.net_error), true);
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null || response.body().data == null || response.body().code != 0) {
                InformationPerfectionActivity.this.a(response.message(), true);
                return;
            }
            JSONObject jSONObject = response.body().data;
            String string = jSONObject.getString("authzInfo");
            fn1.H = jSONObject.getString("nonce");
            InformationPerfectionActivity.this.j = jSONObject.getString("callbackUri");
            new Thread(new a(string)).start();
        }
    }

    public class h implements Callback<Response<JSONObject>> {
        public h() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            InformationPerfectionActivity.this.a("网络错误", true);
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null || response.body().data == null) {
                InformationPerfectionActivity.this.a("绑定失败", true);
            } else {
                fn1.H = response.body().data.getString("nonce");
                InformationPerfectionActivity.this.l();
            }
        }
    }

    public class i implements Callback<et1> {
        public i() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<et1> call, Throwable th) {
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<et1> call, retrofit2.Response<et1> response) {
            try {
                org.json.JSONObject jSONObject = new org.json.JSONObject(response.body().string());
                if (jSONObject.getInt("code") == 0) {
                    if (InformationPerfectionActivity.this.i != null && InformationPerfectionActivity.this.i.isShowing()) {
                        InformationPerfectionActivity.this.i.dismiss();
                    }
                    InformationPerfectionActivity.this.a(jSONObject, jSONObject.getJSONObject("data").getString("nonce"));
                }
            } catch (Exception e2) {
                Toast.makeText(InformationPerfectionActivity.this, "初始化失败", 0).show();
                e2.printStackTrace();
            }
        }
    }

    public class j implements si1.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4173a;

        public j(String str) {
            this.f4173a = str;
        }

        @Override // supwisdom.si1.a
        public void a() {
            if (InformationPerfectionActivity.this.u == 0) {
                InformationPerfectionActivity.this.l(fn1.H);
                return;
            }
            Intent intent = new Intent(InformationPerfectionActivity.this, (Class<?>) MonitorChangePassword.class);
            intent.putExtra("type", "");
            intent.putExtra("userToken", InformationPerfectionActivity.this.t);
            intent.putExtra("nonce", this.f4173a);
            intent.putExtra("username", InformationPerfectionActivity.this.w);
            InformationPerfectionActivity.this.startActivity(intent);
        }
    }

    public class k extends Handler {
        public k() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 11) {
                if (i == 100) {
                    InformationPerfectionActivity.this.y();
                    return;
                } else if (i == 1) {
                    InformationPerfectionActivity.this.y();
                    return;
                } else {
                    if (InformationPerfectionActivity.this.i != null) {
                        InformationPerfectionActivity.this.i.dismiss();
                        return;
                    }
                    return;
                }
            }
            pm1 pm1Var = new pm1((Map) message.obj, true);
            String strC = pm1Var.c();
            if (TextUtils.equals(strC, "9000") && TextUtils.equals(pm1Var.b(), "200")) {
                InformationPerfectionActivity.this.e(pm1Var.a());
            } else if (TextUtils.equals(strC, "6001")) {
                InformationPerfectionActivity informationPerfectionActivity = InformationPerfectionActivity.this;
                informationPerfectionActivity.a(informationPerfectionActivity.getResources().getString(R.string.cancel_authorize), true);
            } else {
                InformationPerfectionActivity informationPerfectionActivity2 = InformationPerfectionActivity.this;
                informationPerfectionActivity2.a(informationPerfectionActivity2.getResources().getString(R.string.authorize_error), true);
            }
        }
    }

    public class l implements IUiListener {

        public class a implements IUiListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ String f4176a;

            public a(String str) {
                this.f4176a = str;
            }

            @Override // com.tencent.tauth.IUiListener
            public void onCancel() {
                InformationPerfectionActivity informationPerfectionActivity = InformationPerfectionActivity.this;
                informationPerfectionActivity.a(informationPerfectionActivity.getResources().getString(R.string.cancel_authorize), true);
            }

            @Override // com.tencent.tauth.IUiListener
            public void onComplete(Object obj) {
                try {
                    JSONObject object = JSON.parseObject(String.valueOf(obj));
                    InformationPerfectionActivity.this.b(this.f4176a, object.getString("openid"), object.getString("unionid"));
                } catch (Exception unused) {
                    InformationPerfectionActivity informationPerfectionActivity = InformationPerfectionActivity.this;
                    informationPerfectionActivity.a(informationPerfectionActivity.getResources().getString(R.string.net_error), true);
                }
            }

            @Override // com.tencent.tauth.IUiListener
            public void onError(UiError uiError) {
                InformationPerfectionActivity informationPerfectionActivity = InformationPerfectionActivity.this;
                informationPerfectionActivity.a(informationPerfectionActivity.getResources().getString(R.string.authorize_error), true);
            }

            @Override // com.tencent.tauth.IUiListener
            public void onWarning(int i) {
            }
        }

        public l() {
        }

        @Override // com.tencent.tauth.IUiListener
        public void onCancel() {
            InformationPerfectionActivity informationPerfectionActivity = InformationPerfectionActivity.this;
            informationPerfectionActivity.a(informationPerfectionActivity.getResources().getString(R.string.cancel_authorize), true);
        }

        @Override // com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            JSONObject object = JSON.parseObject(String.valueOf(obj));
            String string = object.getString("access_token");
            InformationPerfectionActivity.this.h.setAccessToken(string, object.getString("expires_time"));
            InformationPerfectionActivity.this.h.setOpenId(object.getString("openid"));
            UnionInfo unionInfo = new UnionInfo(null, InformationPerfectionActivity.this.h.getQQToken());
            try {
                InformationPerfectionActivity.this.h.setAccessToken(string, object.getString("expires_time"));
                InformationPerfectionActivity.this.h.setOpenId(object.getString("openid"));
                unionInfo.getUnionId(new a(string));
            } catch (Exception unused) {
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            InformationPerfectionActivity informationPerfectionActivity = InformationPerfectionActivity.this;
            informationPerfectionActivity.a(informationPerfectionActivity.getResources().getString(R.string.authorize_error), true);
        }

        @Override // com.tencent.tauth.IUiListener
        public void onWarning(int i) {
        }
    }

    public class m implements Callback<Response<JSONObject>> {
        public m() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            InformationPerfectionActivity informationPerfectionActivity = InformationPerfectionActivity.this;
            informationPerfectionActivity.a(informationPerfectionActivity.getResources().getString(R.string.net_error), true);
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody = response.body();
            if (response.code() != 200) {
                InformationPerfectionActivity.this.a("解绑失败", true);
            } else {
                if (responseBody.code != Response.CODE_SUCCESS) {
                    InformationPerfectionActivity.this.a(responseBody.message, true);
                    return;
                }
                InformationPerfectionActivity.this.i.dismiss();
                PersonalMessageView.D = "";
                InformationPerfectionActivity.this.r();
            }
        }
    }

    public class n implements Callback<Response<JSONObject>> {
        public n() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            InformationPerfectionActivity informationPerfectionActivity = InformationPerfectionActivity.this;
            informationPerfectionActivity.a(informationPerfectionActivity.getResources().getString(R.string.net_error), true);
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody = response.body();
            if (response.code() != 200) {
                InformationPerfectionActivity.this.a("解绑失败", true);
            } else {
                if (responseBody.code != Response.CODE_SUCCESS) {
                    InformationPerfectionActivity.this.a(responseBody.message, true);
                    return;
                }
                InformationPerfectionActivity.this.i.dismiss();
                PersonalMessageView.A = "";
                InformationPerfectionActivity.this.r();
            }
        }
    }

    public class o implements Callback<Response<JSONObject>> {
        public o() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            InformationPerfectionActivity informationPerfectionActivity = InformationPerfectionActivity.this;
            informationPerfectionActivity.a(informationPerfectionActivity.getResources().getString(R.string.net_error), true);
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody = response.body();
            if (response.code() != 200) {
                InformationPerfectionActivity.this.a("解绑失败", true);
            } else {
                if (responseBody.code != Response.CODE_SUCCESS) {
                    InformationPerfectionActivity.this.a(responseBody.message, true);
                    return;
                }
                InformationPerfectionActivity.this.i.dismiss();
                PersonalMessageView.B = "";
                InformationPerfectionActivity.this.r();
            }
        }
    }

    public class p implements Callback<Response<JSONObject>> {
        public p() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            InformationPerfectionActivity informationPerfectionActivity = InformationPerfectionActivity.this;
            informationPerfectionActivity.a(informationPerfectionActivity.getResources().getString(R.string.net_error), true);
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody = response.body();
            if (response.code() != 200) {
                InformationPerfectionActivity.this.a("解绑失败", true);
            } else {
                if (responseBody.code != Response.CODE_SUCCESS) {
                    InformationPerfectionActivity.this.a(responseBody.message, true);
                    return;
                }
                InformationPerfectionActivity.this.i.dismiss();
                PersonalMessageView.z = "";
                InformationPerfectionActivity.this.r();
            }
        }
    }

    public class q implements Callback<Response<JSONObject>> {
        public q() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            InformationPerfectionActivity informationPerfectionActivity = InformationPerfectionActivity.this;
            informationPerfectionActivity.a(informationPerfectionActivity.getResources().getString(R.string.net_error), true);
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody = response.body();
            if (response.code() != 200) {
                InformationPerfectionActivity.this.a("解绑失败", true);
            } else {
                if (responseBody.code != Response.CODE_SUCCESS) {
                    InformationPerfectionActivity.this.a(responseBody.message, true);
                    return;
                }
                InformationPerfectionActivity.this.i.dismiss();
                PersonalMessageView.C = "";
                InformationPerfectionActivity.this.r();
            }
        }
    }

    public class r implements View.OnClickListener {
        public r() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            InformationPerfectionActivity.this.onBackPressed();
        }
    }

    public class s implements Callback<Response<JSONObject>> {
        public s() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            InformationPerfectionActivity informationPerfectionActivity = InformationPerfectionActivity.this;
            informationPerfectionActivity.a(informationPerfectionActivity.getResources().getString(R.string.net_error), true);
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody = response.body();
            if (InformationPerfectionActivity.this.i != null && InformationPerfectionActivity.this.i.isShowing()) {
                InformationPerfectionActivity.this.i.dismiss();
            }
            if (response.code() != 200) {
                InformationPerfectionActivity informationPerfectionActivity = InformationPerfectionActivity.this;
                informationPerfectionActivity.a(informationPerfectionActivity.getResources().getString(R.string.get_user_info_error), true);
            } else {
                PersonalInformationResponse personalInformationResponse = (PersonalInformationResponse) JSON.parseObject(String.valueOf(responseBody.data), PersonalInformationResponse.class);
                if (personalInformationResponse != null) {
                    InformationPerfectionActivity.this.c(personalInformationResponse);
                }
            }
        }
    }

    public class t implements Callback<Response<JSONObject>> {

        public class a implements in1.d {
            public a() {
            }

            @Override // supwisdom.in1.d
            public void k() {
                if (DCUniMPSDK.getInstance().isInitialize()) {
                    try {
                        WXApplication.homeUniMP = DCUniMPSDK.getInstance().openUniMP(InformationPerfectionActivity.this, fn1.i, cm1.class);
                        InformationPerfectionActivity.this.finish();
                    } catch (Exception e2) {
                        lm1.a(e2);
                        e2.printStackTrace();
                    }
                }
            }
        }

        public t() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            InformationPerfectionActivity informationPerfectionActivity = InformationPerfectionActivity.this;
            informationPerfectionActivity.a(informationPerfectionActivity.getResources().getString(R.string.net_error), true);
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            Response<JSONObject> responseBody = response.body();
            if (response.code() != 200) {
                InformationPerfectionActivity informationPerfectionActivity = InformationPerfectionActivity.this;
                informationPerfectionActivity.a(informationPerfectionActivity.getResources().getString(R.string.login_error), true);
            } else {
                if (responseBody.code != Response.CODE_SUCCESS) {
                    InformationPerfectionActivity.this.a(responseBody.message, true);
                    return;
                }
                InformationPerfectionActivity.this.i.dismiss();
                sh1.c.b(fn1.o, fn1.w);
                in1.b(InformationPerfectionActivity.this.k, new a());
            }
        }
    }

    public class u implements Callback<Response<JSONObject>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f4186a;

        public u(String str) {
            this.f4186a = str;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            InformationPerfectionActivity.this.a("上传失败", true);
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null) {
                InformationPerfectionActivity.this.a("上传失败", true);
                return;
            }
            String string = response.body().data.getString("nonce");
            if (string != null) {
                fn1.H = string;
            }
            InformationPerfectionActivity.this.k(this.f4186a);
        }
    }

    public class v implements Callback<Response<JSONObject>> {
        public v() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            InformationPerfectionActivity.this.a("上传失败", true);
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if ((response.code() == 200 || response.code() == 0) && response.body() != null) {
                InformationPerfectionActivity.this.j(response.body().data.getString("imageUrl"));
            } else {
                InformationPerfectionActivity.this.a("上传失败", true);
            }
        }
    }

    public class w implements Callback<Response<JSONObject>> {
        public w() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            InformationPerfectionActivity.this.a("网络错误", true);
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200 || response.body() == null) {
                InformationPerfectionActivity.this.a("上传失败", true);
            } else {
                InformationPerfectionActivity.this.r();
                InformationPerfectionActivity.this.a("上传成功", true);
            }
        }
    }

    public final void l(String str) {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", str);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().k(fn1.w, ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new t());
    }

    public final void m() {
        mj1.b().e(getPackageName(), UUID.randomUUID().toString(), UTDevice.getUtdid(this)).enqueue(new g());
    }

    public final void n() {
        mj1.b().f(getPackageName(), UUID.randomUUID().toString(), UTDevice.getUtdid(this)).enqueue(new a());
    }

    public final void o() {
        this.c.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.mk1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f8400a.a(view);
            }
        });
        this.d.setOnClickListener(new View.OnClickListener() { // from class: supwisdom.lk1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f8297a.b(view);
            }
        });
        this.l = new l();
        this.v.setOnClickListener(new r());
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (x) {
            pi1 pi1Var = new pi1(this.k);
            pi1Var.a(this.r, this.f4161a);
            pi1Var.show();
            x = false;
        }
        if (i3 == -1) {
            if (i2 == 10) {
                try {
                    String[] strArr = {"_data"};
                    Cursor cursorQuery = getContentResolver().query(intent.getData(), strArr, null, null, null);
                    cursorQuery.moveToFirst();
                    int columnIndex = cursorQuery.getColumnIndex(strArr[0]);
                    this.i.show();
                    h(cursorQuery.getString(columnIndex));
                    cursorQuery.close();
                    return;
                } catch (Exception unused) {
                    Toast.makeText(this, "您未选择照片", 0).show();
                    return;
                }
            }
            if (i2 == 2) {
                if (intent != null) {
                    r();
                    return;
                }
                return;
            }
            if (i2 == 4) {
                r();
                return;
            }
            if (i2 == 0) {
                r();
                return;
            }
            if (i2 == 11101) {
                Tencent.onActivityResultData(i2, i3, intent, this.l);
                return;
            }
            if (i2 == 1) {
                r();
                return;
            }
            if (i2 == 3) {
                r();
            } else if (i2 == 1000) {
                Intent intent2 = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent2.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent2, 10);
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        ui1 ui1Var = this.i;
        if (ui1Var != null && ui1Var.isShowing()) {
            this.i.dismiss();
        }
        super.onBackPressed();
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @SuppressLint({"HandlerLeak"})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_information_perfection);
        try {
            this.f4161a = getIntent().getStringExtra("account");
            this.t = getIntent().getStringExtra("userToken");
            this.u = getIntent().getIntExtra("passwordStatus", -1);
            this.w = getIntent().getStringExtra("username");
        } catch (Exception unused) {
        }
        r();
        s();
        o();
        ui1 ui1Var = new ui1(this);
        this.i = ui1Var;
        ui1Var.setCanceledOnTouchOutside(false);
        this.i.show();
        this.h = Tencent.createInstance(com.igexin.push.core.b.m, this, "com.supwisdom.zueb.UploadFileProvider");
        this.m = new k();
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.m.removeMessages(11);
        this.m.removeMessages(1);
        this.m.removeMessages(100);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, supwisdom.j7.c
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        if (i2 == 1000 && iArr[0] == 0) {
            Intent intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, 10);
        }
        if (this.s.isShowing()) {
            this.s.dismiss();
        }
    }

    public final void p() {
        mj1.b().h(this.t).enqueue(new i());
    }

    public final void q() {
        String[] strArr = {"android.permission.WRITE_EXTERNAL_STORAGE"};
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < 1; i2++) {
            String str = strArr[i2];
            if (y7.a(this, str) != 0) {
                arrayList.add(str);
            }
        }
        String[] strArr2 = new String[arrayList.size()];
        if (arrayList.isEmpty()) {
            Intent intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, 10);
        } else {
            bj1 bj1Var = new bj1(this, getString(R.string.string_storage), getString(R.string.string_storage_content));
            this.s = bj1Var;
            bj1Var.show();
            j7.a(this, (String[]) arrayList.toArray(strArr2), 1000);
        }
    }

    public final void r() {
        mj1.b().b(this.t).enqueue(new s());
    }

    public final void s() {
        this.c = (CircleImageView) findViewById(R.id.civ_icon);
        this.b = (ImageView) findViewById(R.id.iv_icon_point);
        this.d = (Button) findViewById(R.id.btn_sure);
        this.f = (LinearLayout) findViewById(R.id.ll_item_bottom);
        this.f4162e = (LinearLayout) findViewById(R.id.ll_item_top);
        this.o = (Group) findViewById(R.id.group_top);
        this.p = (TextView) findViewById(R.id.tv_set_message_to_login);
        this.g = (ImageView) findViewById(R.id.iv_icon_right);
        this.v = (TextView) findViewById(R.id.backBt);
    }

    public final void t() {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().q(fn1.w, ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new m());
    }

    public final void u() {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().g(fn1.w, ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new n());
    }

    public final void v() {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().v(fn1.w, ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new p());
    }

    public final void w() {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().x(fn1.w, ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new o());
    }

    public final void x() {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().i(fn1.w, ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new q());
    }

    public final void y() {
        mj1.b().e(fn1.H, fn1.w).enqueue(new d());
    }

    public final void h(String str) {
        mj1.b().i(fn1.w).enqueue(new u(str));
    }

    public final void i(String str) {
        Glide.with((FragmentActivity) this).asBitmap().load(fn1.f + "api/v1/personal/user/completed/portrait/showPortrait?imageUrl=" + str + "&idToken=" + fn1.w).error(getResources().getDrawable(R.drawable.icon_head)).into(this.c);
    }

    public final void j(String str) {
        org.json.JSONObject jSONObject = new org.json.JSONObject();
        try {
            jSONObject.put("nonce", fn1.H);
            jSONObject.put("imageUrl", str);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        mj1.b().e(fn1.w, ct1.create(xs1.b("application/json; charset=utf-8"), jSONObject.toString())).enqueue(new w());
    }

    public final void k(String str) {
        try {
            mj1.b().a(fn1.w, mj1.a(BitmapFactory.decodeFile(str), "file")).enqueue(new v());
        } catch (Exception unused) {
            a("上传错误", true);
        }
    }

    public /* synthetic */ void b(View view) {
        if (this.d.isSelected()) {
            this.i.show();
            p();
        }
    }

    public final void c(PersonalInformationResponse personalInformationResponse) {
        fn1.H = personalInformationResponse.getNonce() == null ? fn1.H : personalInformationResponse.getNonce();
        if (personalInformationResponse.getPortrait() != null) {
            if (personalInformationResponse.getPortrait().isForced()) {
                if (personalInformationResponse.getPortrait().isCompleted()) {
                    this.b.setVisibility(8);
                    i(personalInformationResponse.getPortrait().getInfo());
                } else {
                    this.b.setVisibility(0);
                    this.c.setImageDrawable(getResources().getDrawable(R.drawable.icon_head));
                }
            } else {
                this.b.setVisibility(8);
                if (personalInformationResponse.getPortrait().isRequired()) {
                    i(personalInformationResponse.getPortrait().getInfo());
                } else {
                    this.c.setVisibility(8);
                    this.g.setVisibility(8);
                }
            }
        } else {
            this.b.setVisibility(8);
            this.c.setVisibility(8);
            this.g.setVisibility(8);
        }
        this.d.setSelected(false);
        this.p.setVisibility(8);
        this.f4162e.removeAllViews();
        this.f.removeAllViews();
        if (personalInformationResponse.getSecurityQuestion() != null && personalInformationResponse.getSecurityQuestion().getQuestionList() != null && personalInformationResponse.getSecurityQuestion().getQuestionList().size() > 0) {
            this.q = personalInformationResponse.getSecurityQuestion().getQuestionList();
        }
        if (!personalInformationResponse.getRealname().isCompleted()) {
            a(personalInformationResponse);
        }
        b(personalInformationResponse);
        if (this.f4162e.getChildCount() <= 0 && this.b.getVisibility() == 8) {
            this.o.setVisibility(0);
            this.o.setVisibility(0);
            this.p.setText("您可以继续完善信息，或者点击确定进入下一步~");
            this.p.setVisibility(0);
            this.d.setSelected(true);
            this.d.setBackground(getResources().getDrawable(R.drawable.shape_radius22_colored663f));
        }
        if (this.p.getVisibility() == 8 && this.b.getVisibility() == 8) {
            this.d.setSelected(true);
            this.p.setVisibility(0);
            this.p.setText("您可以继续完善信息，或者点击确定进入下一步~");
            this.d.setBackground(getResources().getDrawable(R.drawable.shape_radius22_colored663f));
        }
        if (this.f.getChildCount() <= 0) {
            this.f.setVisibility(8);
        }
        if (this.f4162e.getChildCount() <= 0) {
            this.o.setVisibility(8);
            this.f4162e.setVisibility(8);
        }
    }

    public final void e(String str) {
        mj1.b().a(this.j, fn1.H, fn1.w, str).enqueue(new h());
    }

    public final void f(String str) {
        mj1.b().d(this.j, fn1.H, fn1.w, str).enqueue(new f());
    }

    public final void g(String str) {
        mj1.b().j(getPackageName(), UUID.randomUUID().toString(), UTDevice.getUtdid(this)).enqueue(new e(str));
    }

    public final void b(PersonalInformationResponse personalInformationResponse) {
        if (personalInformationResponse.getSort() != null && personalInformationResponse.getSort().size() > 0) {
            for (int i2 = 0; i2 < personalInformationResponse.getSort().size() && !TextUtils.isEmpty(personalInformationResponse.getSort().get(i2)); i2++) {
                if (!personalInformationResponse.getSort().get(i2).equals("realname") || personalInformationResponse.getRealname() == null) {
                    if (personalInformationResponse.getSort().get(i2).equals("securityMobile") && personalInformationResponse.getSecurityMobile() != null) {
                        if (personalInformationResponse.getSecurityMobile() != null) {
                            a(personalInformationResponse.getSecurityMobile().isForced(), personalInformationResponse.getSecurityMobile().isCompleted(), personalInformationResponse.getSecurityMobile().isRequired(), 1, personalInformationResponse.getSecurityMobile().getInfo());
                        }
                    } else if (personalInformationResponse.getSort().get(i2).equals("securityEmailAddress") && personalInformationResponse.getSecurityEmailAddress() != null) {
                        if (personalInformationResponse.getSecurityEmailAddress() != null) {
                            a(personalInformationResponse.getSecurityEmailAddress().isForced(), personalInformationResponse.getSecurityEmailAddress().isCompleted(), personalInformationResponse.getSecurityEmailAddress().isRequired(), 2, personalInformationResponse.getSecurityEmailAddress().getInfo());
                        }
                    } else if (personalInformationResponse.getSort().get(i2).equals("openweixin") && personalInformationResponse.getOpenweixin() != null) {
                        if (personalInformationResponse.getOpenweixin() != null) {
                            a(personalInformationResponse.getOpenweixin().isForced(), personalInformationResponse.getOpenweixin().isCompleted(), personalInformationResponse.getOpenweixin().isRequired(), 4, personalInformationResponse.getOpenweixin().getInfo());
                        }
                    } else if (personalInformationResponse.getSort().get(i2).equals("workweixin") && personalInformationResponse.getWorkweixin() != null) {
                        if (personalInformationResponse.getWorkweixin() != null) {
                            a(personalInformationResponse.getWorkweixin().isForced(), personalInformationResponse.getWorkweixin().isCompleted(), personalInformationResponse.getWorkweixin().isRequired(), 5, personalInformationResponse.getWorkweixin().getInfo());
                        }
                    } else if (personalInformationResponse.getSort().get(i2).equals("dingtalk") && personalInformationResponse.getDingtalk() != null) {
                        if (personalInformationResponse.getDingtalk() != null) {
                            a(personalInformationResponse.getDingtalk().isForced(), personalInformationResponse.getDingtalk().isCompleted(), personalInformationResponse.getDingtalk().isRequired(), 8, personalInformationResponse.getDingtalk().getInfo());
                        }
                    } else if (personalInformationResponse.getSort().get(i2).equals("qq") && personalInformationResponse.getQq() != null) {
                        if (personalInformationResponse.getQq() != null) {
                            a(personalInformationResponse.getQq().isForced(), personalInformationResponse.getQq().isCompleted(), personalInformationResponse.getQq().isRequired(), Constants.REQUEST_LOGIN, personalInformationResponse.getQq().getInfo());
                        }
                    } else if (personalInformationResponse.getSort().get(i2).equals("alipay") && personalInformationResponse.getAlipay() != null) {
                        if (personalInformationResponse.getAlipay() != null) {
                            a(personalInformationResponse.getAlipay().isForced(), personalInformationResponse.getAlipay().isCompleted(), personalInformationResponse.getAlipay().isRequired(), 6, personalInformationResponse.getAlipay().getInfo());
                        }
                    } else if (personalInformationResponse.getSort().get(i2).equals("securityQuestion") && personalInformationResponse.getAlipay() != null && personalInformationResponse.getSecurityQuestion() != null) {
                        a(personalInformationResponse.getSecurityQuestion().isForced(), personalInformationResponse.getSecurityQuestion().isCompleted(), personalInformationResponse.getSecurityQuestion().isRequired(), 3, null);
                    }
                }
            }
            return;
        }
        if (personalInformationResponse.getRealname() != null && personalInformationResponse.getSecurityMobile() != null) {
            a(personalInformationResponse.getSecurityMobile().isForced(), personalInformationResponse.getSecurityMobile().isCompleted(), personalInformationResponse.getSecurityMobile().isRequired(), 1, personalInformationResponse.getSecurityMobile().getInfo());
        }
        if (personalInformationResponse.getSecurityEmailAddress() != null) {
            a(personalInformationResponse.getSecurityEmailAddress().isForced(), personalInformationResponse.getSecurityEmailAddress().isCompleted(), personalInformationResponse.getSecurityEmailAddress().isRequired(), 2, personalInformationResponse.getSecurityEmailAddress().getInfo());
        }
        if (personalInformationResponse.getSecurityQuestion() != null) {
            a(personalInformationResponse.getSecurityQuestion().isForced(), personalInformationResponse.getSecurityQuestion().isCompleted(), personalInformationResponse.getSecurityQuestion().isRequired(), 3, null);
        }
        if (personalInformationResponse.getOpenweixin() != null) {
            a(personalInformationResponse.getOpenweixin().isForced(), personalInformationResponse.getOpenweixin().isCompleted(), personalInformationResponse.getOpenweixin().isRequired(), 4, personalInformationResponse.getOpenweixin().getInfo());
        }
        if (personalInformationResponse.getWorkweixin() != null) {
            a(personalInformationResponse.getWorkweixin().isForced(), personalInformationResponse.getWorkweixin().isCompleted(), personalInformationResponse.getWorkweixin().isRequired(), 5, personalInformationResponse.getWorkweixin().getInfo());
        }
        if (personalInformationResponse.getDingtalk() != null) {
            a(personalInformationResponse.getDingtalk().isForced(), personalInformationResponse.getDingtalk().isCompleted(), personalInformationResponse.getDingtalk().isRequired(), 8, personalInformationResponse.getDingtalk().getInfo());
        }
        if (personalInformationResponse.getQq() != null) {
            a(personalInformationResponse.getQq().isForced(), personalInformationResponse.getQq().isCompleted(), personalInformationResponse.getQq().isRequired(), Constants.REQUEST_LOGIN, personalInformationResponse.getQq().getInfo());
        }
        if (personalInformationResponse.getAlipay() != null) {
            a(personalInformationResponse.getAlipay().isForced(), personalInformationResponse.getAlipay().isCompleted(), personalInformationResponse.getAlipay().isRequired(), 6, personalInformationResponse.getAlipay().getInfo());
        }
    }

    public final void l() {
        mj1.b().e(fn1.H, fn1.w).enqueue(new c());
    }

    public /* synthetic */ void a(View view) {
        q();
    }

    public final void a(String str, boolean z) {
        ui1 ui1Var;
        if (z && (ui1Var = this.i) != null && ui1Var.isShowing()) {
            this.i.dismiss();
        }
        Toast.makeText(this.k, str, 0).show();
    }

    public final void a(PersonalInformationResponse personalInformationResponse) {
        if (personalInformationResponse.getRealname() == null || personalInformationResponse.getRealname().isCompleted()) {
            return;
        }
        PersonalInformationResponse.PerfectionDetail.a ways = personalInformationResponse.getRealname().getWays();
        this.r = new ArrayList<>();
        ways.a();
        throw null;
    }

    public final void a(boolean z, boolean z2, boolean z3, int i2, String str) {
        PersonalMessageView personalMessageView = new PersonalMessageView(this);
        personalMessageView.setTag(Integer.valueOf(i2));
        if (z2) {
            personalMessageView.a(i2, str);
        } else {
            personalMessageView.a(i2, z);
        }
        if (z) {
            this.f4162e.setVisibility(0);
            this.f4162e.addView(personalMessageView);
            if (!z2) {
                this.p.setVisibility(0);
            }
        } else if (z3) {
            this.f.addView(personalMessageView);
        }
        personalMessageView.setListener(new PersonalMessageView.a() { // from class: supwisdom.kk1
            @Override // com.supwisdom.superapp.view.PersonalMessageView.a
            public final void onItemClick(int i3) {
                this.f8180a.c(i3);
            }
        });
    }

    public final void a(org.json.JSONObject jSONObject, String str) {
        si1 si1Var = new si1(this, jSONObject);
        si1Var.show();
        si1Var.a(new j(str));
    }

    public /* synthetic */ void c(int i2) {
        Intent intent = new Intent();
        if (i2 != 11101) {
            switch (i2) {
                case 0:
                    pi1 pi1Var = new pi1(this.k);
                    pi1Var.a(this.r, this.f4161a);
                    pi1Var.show();
                    break;
                case 1:
                    intent.putExtra("type", 1);
                    intent.setClass(this.k, BindMessageActivity.class);
                    startActivityForResult(intent, 1);
                    break;
                case 2:
                    intent.putExtra("type", 2);
                    intent.setClass(this.k, BindMessageActivity.class);
                    startActivityForResult(intent, 2);
                    break;
                case 3:
                    intent.putExtra("number", this.q.size());
                    for (int i3 = 0; i3 < this.q.size(); i3++) {
                        intent.putExtra("question" + i3, this.q.get(i3));
                    }
                    intent.setClass(this.k, SafeQuestionActivity.class);
                    startActivityForResult(intent, 3);
                    break;
                case 4:
                    if (PersonalMessageView.B.equals("bind")) {
                        w();
                    } else if (!WXApplication.api.isWXAppInstalled()) {
                        Toast.makeText(this.k, "您还未安装微信客户端", 0).show();
                    } else {
                        SendAuth.Req req = new SendAuth.Req();
                        req.scope = WeiXinOAuthService.DEFAULT_SCOPE;
                        req.state = "superapp_wx_login";
                        WXEntryActivity.b = "bind";
                        WXEntryActivity.c = this.m;
                        WXApplication.api.sendReq(req);
                    }
                    break;
                case 5:
                    if (PersonalMessageView.C.equals("bind")) {
                        x();
                    } else {
                        this.i.show();
                        IWWAPI iwwapiCreateWWAPI = WWAPIFactory.createWWAPI(this.k);
                        iwwapiCreateWWAPI.registerApp(com.igexin.push.core.b.m);
                        WWAuthMessage.Req req2 = new WWAuthMessage.Req();
                        req2.sch = com.igexin.push.core.b.m;
                        req2.appId = com.igexin.push.core.b.m;
                        req2.agentId = com.igexin.push.core.b.m;
                        req2.state = "dd";
                        if (iwwapiCreateWWAPI.isWWAppSupportAPI()) {
                            iwwapiCreateWWAPI.sendMessage(req2, new xl1(this));
                        } else {
                            a("未安装企业微信", true);
                        }
                    }
                    break;
                case 6:
                    if (PersonalMessageView.D.equals("bind")) {
                        t();
                    } else {
                        this.i.show();
                        m();
                    }
                    break;
                default:
                    if (PersonalMessageView.A.equals("bind")) {
                        u();
                    } else {
                        vr vrVar = new vr();
                        or orVarA = lr.a(this.k, "", true);
                        this.n = orVarA;
                        DDShareActivity.c = orVarA;
                        DDShareActivity.d = this.m;
                        vrVar.b = "sns_login";
                        vrVar.c = "test";
                        if (!orVarA.b()) {
                            Toast.makeText(this.k, "您还未安装钉钉", 0).show();
                        } else if (this.n.a()) {
                            this.n.a(vrVar);
                        } else {
                            Toast.makeText(this.k, "钉钉版本过低,请先升级", 0).show();
                        }
                    }
                    break;
            }
            return;
        }
        if (PersonalMessageView.z.equals("bind")) {
            v();
        } else {
            this.i.show();
            n();
        }
    }

    public final void b(String str, String str2, String str3) {
        mj1.b().a(this.j, fn1.H, str, str2, str3).enqueue(new b());
    }
}
