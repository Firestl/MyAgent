package supwisdom;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.newcapec.smcrypto.NCPBase;
import com.newcapec.smcrypto.SMKeyAlg;
import com.newcapec.virtualcard.R;
import com.newcapec.virtualcard.entity.ApplyOfflineAuthResult;
import com.newcapec.virtualcard.entity.ECardResData;
import com.newcapec.virtualcard.entity.OnLineCodeResult;
import com.newcapec.virtualcard.entity.OpenOfflineCodeResult;
import com.newcapec.virtualcard.entity.PayResult;
import com.newcapec.virtualcard.entity.QueryPayStatusResult;
import com.taobao.weex.common.WXRequest;
import com.tencent.connect.common.Constants;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public class r implements m {
    public static String j = "";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f8977a;
    public String b;
    public n c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public j f8978e;
    public j f;
    public boolean i;
    public final Handler d = new Handler(Looper.getMainLooper());
    public final l g = new l();
    public final int h = v.e().a();

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f8979a;

        public a(int i) {
            this.f8979a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i = this.f8979a;
            if (i == 0) {
                r.this.c.a();
            } else if (1 == i) {
                r.this.c.b();
            } else if (2 == i) {
                r.this.c.d();
            }
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                ECardResData<String> eCardResDataB = r.this.g.b();
                if (eCardResDataB == null) {
                    d0.b("getCodeConfig:" + ((Object) null));
                    return;
                }
                if (eCardResDataB.isSuccess()) {
                    String strA = hw0.a(eCardResDataB.getResultData(), b0.a(R.string.sm4_key));
                    JSONObject object = JSON.parseObject(strA);
                    d0.b("getCodeConfig: data=" + strA);
                    int intValue = object.getIntValue("type");
                    String string = object.getString("color");
                    v.e().b(intValue);
                    v.e().a(b0.c(string));
                    return;
                }
                d0.b("getCodeConfig:" + eCardResDataB);
                if (TextUtils.isEmpty(eCardResDataB.getResultData())) {
                    return;
                }
                d0.b("getCodeConfig decode resultData：" + hw0.a(eCardResDataB.getResultData(), b0.a(R.string.sm4_key)));
            } catch (Exception e2) {
                d0.b("获取配置失败：" + e2.getMessage());
            }
        }
    }

    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String[] f8981a;

        public c(String[] strArr) {
            this.f8981a = strArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                ECardResData<String> eCardResDataA = r.this.g.a(this.f8981a);
                if (eCardResDataA == null) {
                    r.a(r.this, "Open0002", "开通失败");
                    return;
                }
                if (!eCardResDataA.isSuccess()) {
                    r.a(r.this, "Open0003", eCardResDataA.getMessage());
                    d0.b("offlineCodeapplyBind: " + eCardResDataA);
                    return;
                }
                OpenOfflineCodeResult openOfflineCodeResult = (OpenOfflineCodeResult) JSON.parseObject(hw0.a(eCardResDataA.getResultData(), b0.a(R.string.sm4_key)), OpenOfflineCodeResult.class);
                a.a.a.c.c cVarM = a.a.a.c.c.m();
                cVarM.e(openOfflineCodeResult.getUsername());
                cVarM.b();
                d0.a("a.a.a.b.l", "openOfflineCode: success");
                if (v.e().b() == -1) {
                    r.this.d();
                }
                r.this.a();
            } catch (Exception e2) {
                e2.printStackTrace();
                r.a(r.this, "Open0004", e2.getMessage());
            }
        }
    }

    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                String[] strArr = new String[2];
                int iGenSM2Key = new SMKeyAlg(Logger.getLogger("V8QRUtils")).GenSM2Key("appsecretkeyforv8qrcode", strArr);
                if (iGenSM2Key != 8000) {
                    r.a(r.this, "Auth0001", String.format("生成密钥失败(%s)", String.valueOf(iGenSM2Key)));
                    return;
                }
                ECardResData<String> eCardResDataA = r.this.g.a(strArr[0]);
                if (eCardResDataA == null) {
                    d0.b("applyOfflineAuthorization: " + eCardResDataA.toString());
                    r.a(r.this, "Auth0002", "授权失败");
                    return;
                }
                if (!eCardResDataA.isSuccess()) {
                    r.a(r.this, "Auth0003", eCardResDataA.getMessage());
                    return;
                }
                ApplyOfflineAuthResult applyOfflineAuthResult = (ApplyOfflineAuthResult) JSON.parseObject(b0.a(JSON.parseObject(hw0.a(eCardResDataA.getResultData(), b0.a(R.string.sm4_key))).getString("encryptdata"), b0.a(R.string.app_priKey), b0.a(R.string.platform_pubKey)), ApplyOfflineAuthResult.class);
                a.a.a.c.c cVarM = a.a.a.c.c.m();
                cVarM.f(strArr[0]);
                a.a.a.c.c.d.c = strArr[1];
                a.a.a.c.c.d.f1071e = applyOfflineAuthResult.getAuthorinfo();
                a.a.a.c.c.d.f = applyOfflineAuthResult.getAuthordate();
                cVarM.b();
                if (r.this.c != null) {
                    r.this.c.c();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                r.a(r.this, "Auth0004", e2.getMessage());
            }
        }
    }

    public class e extends j {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ boolean f8983e;

        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f8984a;

            public a(int i) {
                this.f8984a = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                int i = this.f8984a;
                if (i == 0) {
                    r.this.c.a();
                } else if (2 == i) {
                    r.this.c.d();
                }
            }
        }

        public class b implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f8985a;

            public b(int i) {
                this.f8985a = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                r.this.c.a("Gen0001", String.format("生码错误(%s)", Integer.valueOf(this.f8985a)));
            }
        }

        public class c implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Bitmap f8986a;

            public c(Bitmap bitmap) {
                this.f8986a = bitmap;
            }

            @Override // java.lang.Runnable
            public void run() {
                r.this.c.a(this.f8986a);
            }
        }

        public class d implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Exception f8987a;

            public d(Exception exc) {
                this.f8987a = exc;
            }

            @Override // java.lang.Runnable
            public void run() {
                r.this.c.a("Gen002", this.f8987a.getMessage());
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(int i, boolean z) {
            super(i);
            this.f8983e = z;
        }

        @Override // supwisdom.j
        public void a() {
            String str;
            try {
                if (this.f8983e) {
                    ECardResData<String> eCardResDataB = r.this.g.b(b0.a(true));
                    if (eCardResDataB == null) {
                        r.a(r.this, "Gen004", "付款码生成失败~");
                        r rVar = r.this;
                        j jVar = rVar.f8978e;
                        if (jVar != null) {
                            jVar.c();
                            rVar.f8978e = null;
                            return;
                        }
                        return;
                    }
                    if (!eCardResDataB.isSuccess()) {
                        r.a(r.this, "Gen003", eCardResDataB.getMessage());
                        r rVar2 = r.this;
                        j jVar2 = rVar2.f8978e;
                        if (jVar2 != null) {
                            jVar2.c();
                            rVar2.f8978e = null;
                            return;
                        }
                        return;
                    }
                    String strA = hw0.a(eCardResDataB.getResultData(), b0.a(R.string.sm4_key));
                    d0.a("a.a.a.b.l", "getOnlinePayCode: " + strA);
                    OnLineCodeResult onLineCodeResult = (OnLineCodeResult) JSON.parseObject(strA, OnLineCodeResult.class);
                    a.a.a.c.c.m().b(onLineCodeResult.getBalance());
                    str = onLineCodeResult.getPaycode();
                    r.this.b = str;
                } else {
                    int iH = r.this.h();
                    if (1 != iH) {
                        if (r.this.c != null) {
                            r.this.d.post(new a(iH));
                        }
                        r rVar3 = r.this;
                        j jVar3 = rVar3.f8978e;
                        if (jVar3 != null) {
                            jVar3.c();
                            rVar3.f8978e = null;
                            return;
                        }
                        return;
                    }
                    byte[] bArr = new byte[122];
                    int iA = b0.a(NCPBase.stringToHex(a.a.a.c.c.m().d()), b0.a(), a.a.a.c.c.m().k(), a.a.a.c.c.m().j(), bArr);
                    if (iA != 8000) {
                        if (r.this.c != null) {
                            r.this.d.post(new b(iA));
                        }
                        r rVar4 = r.this;
                        j jVar4 = rVar4.f8978e;
                        if (jVar4 != null) {
                            jVar4.c();
                            rVar4.f8978e = null;
                            return;
                        }
                        return;
                    }
                    r.this.f8977a = bArr;
                    str = new String(bArr, "ISO-8859-1");
                }
                Bitmap bitmapA = new h().a(str, r.this.h);
                r rVar5 = r.this;
                j jVar5 = rVar5.f;
                if (jVar5 != null) {
                    jVar5.c();
                    rVar5.f = null;
                }
                if (r.this.c != null) {
                    r.this.d.post(new c(bitmapA));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                r rVar6 = r.this;
                if (rVar6.c != null) {
                    rVar6.d.post(new d(e2));
                }
                r rVar7 = r.this;
                j jVar6 = rVar7.f8978e;
                if (jVar6 != null) {
                    jVar6.c();
                    rVar7.f8978e = null;
                }
            }
        }
    }

    public class f extends j {

        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ PayResult f8989a;

            public a(PayResult payResult) {
                this.f8989a = payResult;
            }

            @Override // java.lang.Runnable
            public void run() {
                r.this.c.a(this.f8989a);
            }
        }

        public f(int i, int i2) {
            super(i, i2);
        }

        @Override // supwisdom.j
        public void a() {
            r rVar;
            try {
                ECardResData<String> eCardResDataB = r.this.i ? r.this.g.b(r.this.b, b0.a(true)) : r.this.g.c(NCPBase.hexToString(r.this.f8977a));
                if (eCardResDataB != null && eCardResDataB.isSuccess()) {
                    QueryPayStatusResult queryPayStatusResult = (QueryPayStatusResult) JSON.parseObject(hw0.a(eCardResDataB.getResultData(), b0.a(R.string.sm4_key)), QueryPayStatusResult.class);
                    d0.a("a.a.a.b.l", "queryPayStatus: " + queryPayStatusResult.toString());
                    if ("1".equalsIgnoreCase(queryPayStatusResult.getStatus())) {
                        PayResult payResult = new PayResult();
                        payResult.setPayMoney(queryPayStatusResult.getTxamt());
                        payResult.setPayLocation("");
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(new PayResult.PayInfoItem("订单号", queryPayStatusResult.getJourno()));
                        payResult.setItemList(arrayList);
                        if (r.this.c != null) {
                            r.this.d.post(new a(payResult));
                        }
                        rVar = r.this;
                    } else {
                        if ("2".equalsIgnoreCase(queryPayStatusResult.getStatus())) {
                            r.a(r.this, "Poll0001", "该二维码已使用，请刷新。");
                        } else if ("3".equalsIgnoreCase(queryPayStatusResult.getStatus())) {
                            r.a(r.this, "Poll0002", "该二维码已过期，请刷新。");
                        } else if ("4".equalsIgnoreCase(queryPayStatusResult.getStatus())) {
                            r.a(r.this, "Poll0003", "该二维码是非法的，请重新生成二维码");
                        } else {
                            if ("5".equalsIgnoreCase(queryPayStatusResult.getStatus())) {
                                return;
                            }
                            if (!Constants.VIA_SHARE_TYPE_INFO.equalsIgnoreCase(queryPayStatusResult.getStatus())) {
                                if (!Constants.VIA_SHARE_TYPE_PUBLISHVIDEO.equalsIgnoreCase(queryPayStatusResult.getStatus()) && "7".equalsIgnoreCase(queryPayStatusResult.getStatus())) {
                                    r.a(r.this, "Poll0005", "支付失败");
                                    return;
                                }
                                return;
                            }
                            r.a(r.this, "Poll0004", "支付失败，你的账户余额不足");
                        }
                        rVar = r.this;
                    }
                    rVar.i();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public class g implements Runnable {

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                r.this.c.e();
            }
        }

        public g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                ECardResData<String> eCardResDataA = r.this.g.a();
                if (eCardResDataA == null) {
                    r.a(r.this, "Stop0001", "操作失败");
                    return;
                }
                if (!eCardResDataA.isSuccess()) {
                    r.a(r.this, "Stop0002", eCardResDataA.getMessage());
                }
                a.a.a.c.c.m().a();
                if (r.this.c != null) {
                    r.this.d.post(new a());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                r.a(r.this, "Stop0003", e2.getMessage());
            }
        }
    }

    public static /* synthetic */ void a(r rVar, String str, String str2) {
        if (rVar.c != null) {
            rVar.d.post(new t(rVar, str, str2));
        }
    }

    @Override // supwisdom.m
    public void a() {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new d());
    }

    @Override // supwisdom.m
    public void a(n nVar) {
        this.c = nVar;
    }

    @Override // supwisdom.m
    public void a(boolean z) {
        this.i = z;
        j jVar = this.f8978e;
        if (jVar != null) {
            jVar.c();
        }
        e eVar = new e(WXRequest.DEFAULT_TIMEOUT_MS, z);
        this.f8978e = eVar;
        eVar.b();
    }

    @Override // supwisdom.m
    public void a(String[] strArr) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new c(strArr));
    }

    @Override // supwisdom.m
    public String b() {
        if (this.i) {
            return this.b;
        }
        try {
            return new String(this.f8977a, "ISO-8859-1");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    @Override // supwisdom.m
    public String c() {
        return String.format("%s (%s)", a.a.a.c.c.m().i(), a.a.a.c.c.m().f());
    }

    @Override // supwisdom.m
    public void d() {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new b());
    }

    @Override // supwisdom.m
    public void e() {
        j jVar = this.f8978e;
        if (jVar != null) {
            jVar.c();
            this.f8978e = null;
        }
        j jVar2 = this.f;
        if (jVar2 != null) {
            jVar2.c();
            this.f = null;
        }
    }

    @Override // supwisdom.m
    public void f() {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new g());
    }

    @Override // supwisdom.m
    public void g() {
        if (this.f != null) {
            return;
        }
        f fVar = new f(2000, 2000);
        this.f = fVar;
        fVar.b();
    }

    public int h() {
        String strD = a.a.a.c.c.m().d();
        String strC = a.a.a.c.c.m().c();
        if (TextUtils.isEmpty(strD) || TextUtils.isEmpty(strC)) {
            return 0;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(io.dcloud.common.adapter.util.Logger.TIMESTAMP_YYYY_MM_DD, Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime((Date) Objects.requireNonNull(simpleDateFormat.parse(strC)));
        return Calendar.getInstance().getTimeInMillis() > calendar.getTimeInMillis() ? 2 : 1;
    }

    public final void i() {
        j jVar = this.f;
        if (jVar != null) {
            jVar.c();
            this.f = null;
        }
    }

    @Override // supwisdom.m
    public void a(String str, String str2, String str3) {
        j = str2;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            if (this.c != null) {
                this.d.post(new t(this, "Check0002", "非法参数"));
                return;
            }
            return;
        }
        if (!a.a.a.c.c.m().c(str2, str3)) {
            a.a.a.c.c.m().l();
            AsyncTask.THREAD_POOL_EXECUTOR.execute(new s(this, str));
            return;
        }
        try {
            int iH = h();
            if (this.c != null) {
                this.d.post(new a(iH));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            String str4 = "校验失败：" + e2.getMessage();
            if (this.c != null) {
                this.d.post(new t(this, "Check0001", str4));
            }
        }
    }
}
