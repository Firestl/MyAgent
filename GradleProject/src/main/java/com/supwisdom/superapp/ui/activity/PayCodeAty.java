package com.supwisdom.superapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.supwisdom.superapp.WXApplication;
import com.supwisdom.superapp.WXBaseActivity;
import com.supwisdom.superapp.service.model.Response;
import com.supwisdom.zueb.R;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalCodeCompoundResult;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalCodeInitResult;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalSocketReceiveResult;
import com.synjones.mobilegroup.libofflinecodesdk.beans.ExternalSocketStateResult;
import com.synjones.mobilegroup.libofflinecodesdk.core.OfflineCodePayController;
import com.synjones.mobilegroup.libofflinecodesdk.listeners.GetOnlineAndOfflineListener;
import com.synjones.mobilegroup.libofflinecodesdk.listeners.OffInitListener;
import com.synjones.mobilegroup.libofflinecodesdk.listeners.OnReceivePaySocketMessageListener;
import com.taobao.weex.el.parse.Operators;
import io.dcloud.common.constant.AbsoluteConst;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import supwisdom.aj1;
import supwisdom.en1;
import supwisdom.fn1;
import supwisdom.hn1;
import supwisdom.mj1;
import supwisdom.on1;
import supwisdom.sh1;

/* JADX INFO: loaded from: classes2.dex */
public class PayCodeAty extends WXBaseActivity implements View.OnClickListener, aj1.b, OffInitListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ImageView f4326a;
    public TextView b;
    public TextView c;
    public TextView d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f4327e;
    public aj1 f;
    public LinearLayout g;
    public LinearLayout h;
    public TextView i;
    public String j;
    public int k;
    public String l;
    public String m = "https://yktapp.nwpu.edu.cn";
    public String n = com.umeng.ccg.a.r;
    public String o = "ws://yktapp.nwpu.edu.cn";

    public class a implements OnReceivePaySocketMessageListener {
        public a() {
        }

        @Override // com.synjones.mobilegroup.libofflinecodesdk.listeners.OnReceivePaySocketMessageListener
        public void onSocketReceiveDataReslut(ExternalSocketReceiveResult externalSocketReceiveResult) {
            if ("0".equals(externalSocketReceiveResult.code)) {
                Map map = (Map) JSON.parseObject(JSON.toJSONString(externalSocketReceiveResult.data.get("data")), Map.class);
                Log.i(WXBaseActivity.TAG, "content: ====  " + map.get("content"));
                JSONObject object = JSON.parseObject(String.valueOf(map.get("content")));
                if (object != null) {
                    Log.i(WXBaseActivity.TAG, "onSocketReceiveDataReslut:  === " + object.getString("accType"));
                    Log.i(WXBaseActivity.TAG, "onSocketReceiveDataReslut:  === " + object.getString("tranamt"));
                    Log.i(WXBaseActivity.TAG, "onSocketReceiveDataReslut:  === " + object.getString(AbsoluteConst.EVENTS_RESUME));
                    Log.i(WXBaseActivity.TAG, "onSocketReceiveDataReslut:  === " + object.getString("jndatetime"));
                    Intent intent = new Intent(PayCodeAty.this, (Class<?>) PaySuccessAty.class);
                    intent.putExtra("accType", object.getString("accType"));
                    intent.putExtra("tranamt", object.getString("tranamt"));
                    intent.putExtra(AbsoluteConst.EVENTS_RESUME, object.getString(AbsoluteConst.EVENTS_RESUME));
                    intent.putExtra("jndatetime", object.getString("jndatetime"));
                    PayCodeAty.this.startActivity(intent);
                    PayCodeAty.this.finish();
                }
            }
        }

        @Override // com.synjones.mobilegroup.libofflinecodesdk.listeners.OnReceivePaySocketMessageListener
        public void onSocketStateResult(ExternalSocketStateResult externalSocketStateResult) {
        }
    }

    public class b implements Callback<Response<JSONObject>> {
        public b() {
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<Response<JSONObject>> call, Throwable th) {
            if (PayCodeAty.this.j != null) {
                PayCodeAty.this.l();
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<Response<JSONObject>> call, retrofit2.Response<Response<JSONObject>> response) {
            if (response.code() != 200) {
                Toast.makeText(PayCodeAty.this.getApplicationContext(), "获取token失败", 0).show();
                return;
            }
            Response<JSONObject> responseBody = response.body();
            JSONObject jSONObject = responseBody.data;
            if (jSONObject != null) {
                PayCodeAty.this.l = jSONObject.getString("userToken");
                Log.e(WXBaseActivity.TAG, "onResponse:  ======= token --- " + jSONObject.getString("userToken"));
                PayCodeAty payCodeAty = PayCodeAty.this;
                en1.b(payCodeAty, "USER_TOKEN", payCodeAty.l);
                if (PayCodeAty.this.l != null) {
                    PayCodeAty payCodeAty2 = PayCodeAty.this;
                    payCodeAty2.a(payCodeAty2.n, payCodeAty2.m, payCodeAty2.l, PayCodeAty.this.o);
                } else {
                    Toast.makeText(PayCodeAty.this, responseBody.message, 0).show();
                    if (PayCodeAty.this.j != null) {
                        PayCodeAty.this.l();
                    }
                }
            }
        }
    }

    public final void e(String str) {
        this.k = en1.a(this, "PAY_ID", 0);
        OfflineCodePayController.getInstance().registerPaymentListener(str, new a());
    }

    public final void l() {
        this.k = en1.a(this, "PAY_ID", 0);
        Log.i(WXBaseActivity.TAG, "getQrCode: payId ===== " + this.k);
        OfflineCodePayController.getInstance().getOnlineAndOfflineCode(this.k, new GetOnlineAndOfflineListener() { // from class: supwisdom.gl1
            @Override // com.synjones.mobilegroup.libofflinecodesdk.listeners.GetOnlineAndOfflineListener
            public final void onGetOnlineAndOffline(ExternalCodeCompoundResult externalCodeCompoundResult) {
                this.f7746a.a(externalCodeCompoundResult);
            }
        });
    }

    public final void m() {
        mj1.b().e(fn1.w).enqueue(new b());
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 500) {
            l();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.backBt) {
            onBackPressed();
        } else if (id == R.id.ll_value) {
            this.f.c();
        } else {
            if (id != R.id.tv_refresh) {
                return;
            }
            l();
        }
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        WXBaseActivity.transparencyBar(this);
        WXBaseActivity.setLightStatusBar(this, true);
        setContentView(R.layout.activity_paycode);
        this.f4326a = (ImageView) findViewById(R.id.iv_qrCode);
        this.b = (TextView) findViewById(R.id.backBt);
        this.c = (TextView) findViewById(R.id.tv_value);
        this.i = (TextView) findViewById(R.id.tv_refresh);
        this.d = (TextView) findViewById(R.id.tv_account);
        this.h = (LinearLayout) findViewById(R.id.ll_account);
        this.f4327e = (TextView) findViewById(R.id.tv_accountInfo);
        this.g = (LinearLayout) findViewById(R.id.ll_value);
        this.f4326a.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.f = new aj1(this, this);
        String strC = sh1.c.c(fn1.o);
        String strB = hn1.b(strC);
        String strC2 = hn1.c(strC);
        this.f4327e.setText("(" + strC2 + Operators.SPACE_STR + strB + ")");
        String strA = en1.a(this, "PAY_TYPE", (String) null);
        this.j = strA;
        if (strA != null) {
            this.c.setText(strA);
        }
        String strA2 = en1.a(this, "USER_TOKEN", (String) null);
        this.l = strA2;
        if (strA2 != null) {
            a(this.n, this.m, strA2, this.o);
        } else {
            m();
        }
    }

    @Override // com.supwisdom.superapp.WXBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.synjones.mobilegroup.libofflinecodesdk.listeners.OffInitListener
    public void onInitResult(ExternalCodeInitResult externalCodeInitResult) {
        String strA = en1.a(this, "PAY_TYPE", (String) null);
        this.k = en1.a(this, "PAY_ID", 0);
        Log.i(WXBaseActivity.TAG, "result: ============== " + externalCodeInitResult.msg);
        Log.i(WXBaseActivity.TAG, "payType: ============== " + strA);
        Log.i(WXBaseActivity.TAG, "payId: ============== " + this.k);
        if (!"0".equals(externalCodeInitResult.code)) {
            m();
        } else if (strA == null) {
            this.f.c();
        } else {
            this.c.setText(strA);
            l();
        }
    }

    @Override // android.view.Window.Callback
    public void onPointerCaptureChanged(boolean z) {
        Log.d(WXBaseActivity.TAG, "hasCapture---" + z);
    }

    public final void a(String str, String str2, String str3, String str4) {
        OfflineCodePayController.getInstance().init(WXApplication.instance, str, str2, str3, str4, this);
    }

    public /* synthetic */ void a(ExternalCodeCompoundResult externalCodeCompoundResult) {
        if (externalCodeCompoundResult.data != null && "0".equals(externalCodeCompoundResult.code)) {
            on1.a(externalCodeCompoundResult.data.qrcode, this.f4326a);
            e(externalCodeCompoundResult.data.barcode);
        } else {
            Toast.makeText(this, externalCodeCompoundResult.msg, 0).show();
        }
    }

    @Override // supwisdom.aj1.b
    public void a(String str, String str2) {
        this.j = str;
        this.k = Integer.parseInt(str2);
        this.c.setText(str);
        en1.b(this, "PAY_TYPE", str);
        en1.b(this, "PAY_ID", Integer.parseInt(str2));
        l();
    }
}
