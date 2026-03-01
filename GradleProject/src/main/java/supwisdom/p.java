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
import com.newcapec.virtualcard.entity.ECardResData;
import com.newcapec.virtualcard.entity.T6ApplyOfflineAuthResult;
import com.taobao.weex.common.WXRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public class p implements m {
    public static String g = "";
    public n b;
    public j d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public j f8748e;
    public String f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k f8747a = new k();
    public final Handler c = new Handler(Looper.getMainLooper());

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f8749a;

        public a(int i) {
            this.f8749a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i = this.f8749a;
            if (i == 0) {
                p.this.b.a();
            } else if (1 == i) {
                p.this.b.b();
            } else if (2 == i) {
                p.this.b.d();
            }
        }
    }

    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String[] f8750a;

        public b(String[] strArr) {
            this.f8750a = strArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                ECardResData<String> eCardResDataA = p.this.f8747a.a(this.f8750a);
                if (eCardResDataA == null) {
                    p.a(p.this, "Open0002", "开通失败");
                    return;
                }
                if (eCardResDataA.isSuccess()) {
                    JSONObject object = JSON.parseObject(hw0.a(eCardResDataA.getResultData(), b0.a(R.string.sm4_key)));
                    a.a.a.c.c cVarM = a.a.a.c.c.m();
                    cVarM.e(object.getString("name"));
                    cVarM.b();
                    d0.a("a.a.a.b.j", "openOfflineCode: success");
                    p.this.a();
                    return;
                }
                p.a(p.this, "Open0003", eCardResDataA.getMessage());
                d0.b("offlineCodeapplyBind: " + eCardResDataA);
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                String[] strArr = new String[2];
                int iGenSM2Key = new SMKeyAlg(Logger.getLogger("V8QRUtils")).GenSM2Key("appsecretkeyforv8qrcode", strArr);
                if (iGenSM2Key != 8000) {
                    p.a(p.this, "Auth0001", String.format("生成密钥失败(%s)", String.valueOf(iGenSM2Key)));
                    return;
                }
                ECardResData<String> eCardResDataA = p.this.f8747a.a(strArr[0]);
                if (eCardResDataA == null) {
                    d0.b("applyOfflineAuthorization: " + eCardResDataA.toString());
                    p.a(p.this, "Auth0002", "授权失败");
                    return;
                }
                if (!eCardResDataA.isSuccess()) {
                    p.a(p.this, "Auth0003", "授权失败");
                    return;
                }
                String strA = hw0.a(eCardResDataA.getResultData(), b0.a(R.string.sm4_key));
                d0.a("a.a.a.b.j", "applyOfflineAuth: sm4_decode" + strA);
                T6ApplyOfflineAuthResult t6ApplyOfflineAuthResult = (T6ApplyOfflineAuthResult) JSON.parseObject(strA, T6ApplyOfflineAuthResult.class);
                d0.a("a.a.a.b.j", "applyOfflineAuth: success");
                d0.a("a.a.a.b.j", "userPriKey: " + strArr[1]);
                String str = new String(new byte[]{Byte.decode(t6ApplyOfflineAuthResult.getDevcode()).byteValue()});
                a.a.a.c.c cVarM = a.a.a.c.c.m();
                cVarM.f(strArr[0]);
                a.a.a.c.c.d.c = strArr[1];
                a.a.a.c.c.d.f1071e = t6ApplyOfflineAuthResult.getAuthorinfo();
                a.a.a.c.c.d.f = t6ApplyOfflineAuthResult.getAuthordate();
                a.a.a.c.c.d.j = str;
                cVarM.b();
                if (p.this.b != null) {
                    p.this.b.c();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                p.a(p.this, "Auth0004", e2.getMessage());
            }
        }
    }

    public class d extends j {

        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f8753a;

            public a(int i) {
                this.f8753a = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                int i = this.f8753a;
                if (i == 0) {
                    p.this.b.a();
                } else if (2 == i) {
                    p.this.b.d();
                }
            }
        }

        public class b implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f8754a;

            public b(int i) {
                this.f8754a = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                p.this.b.a("Gen0001", String.format("生码错误(%s)", Integer.valueOf(this.f8754a)));
            }
        }

        public class c implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Bitmap f8755a;

            public c(Bitmap bitmap) {
                this.f8755a = bitmap;
            }

            @Override // java.lang.Runnable
            public void run() {
                p.this.b.a(this.f8755a);
            }
        }

        /* JADX INFO: renamed from: supwisdom.p$d$d, reason: collision with other inner class name */
        public class RunnableC0224d implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Exception f8756a;

            public RunnableC0224d(Exception exc) {
                this.f8756a = exc;
            }

            @Override // java.lang.Runnable
            public void run() {
                p.this.b.a("Gen002", this.f8756a.getMessage());
            }
        }

        public d(int i) {
            super(i);
        }

        @Override // supwisdom.j
        public void a() {
            try {
                int iH = p.this.h();
                if (1 != iH) {
                    if (p.this.b != null) {
                        p.this.c.post(new a(iH));
                    }
                    p pVar = p.this;
                    j jVar = pVar.d;
                    if (jVar != null) {
                        jVar.c();
                        pVar.d = null;
                        return;
                    }
                    return;
                }
                byte[] bArr = new byte[122];
                int iA = b0.a(NCPBase.stringToHex(a.a.a.c.c.m().d()), a.a.a.c.c.m().e(), a.a.a.c.c.m().k(), a.a.a.c.c.m().j(), bArr);
                if (iA != 8000) {
                    if (p.this.b != null) {
                        p.this.c.post(new b(iA));
                    }
                    p pVar2 = p.this;
                    j jVar2 = pVar2.d;
                    if (jVar2 != null) {
                        jVar2.c();
                        pVar2.d = null;
                        return;
                    }
                    return;
                }
                String str = new String(bArr, "ISO-8859-1");
                p.this.f = str;
                Bitmap bitmapA = new h().a(str, 0);
                p pVar3 = p.this;
                j jVar3 = pVar3.f8748e;
                if (jVar3 != null) {
                    jVar3.c();
                    pVar3.f8748e = null;
                }
                if (p.this.b != null) {
                    p.this.c.post(new c(bitmapA));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                p pVar4 = p.this;
                if (pVar4.b != null) {
                    pVar4.c.post(new RunnableC0224d(e2));
                }
                p pVar5 = p.this;
                j jVar4 = pVar5.d;
                if (jVar4 != null) {
                    jVar4.c();
                    pVar5.d = null;
                }
            }
        }
    }

    public static /* synthetic */ void a(p pVar, String str, String str2) {
        if (pVar.b != null) {
            pVar.c.post(new o(pVar, str, str2));
        }
    }

    @Override // supwisdom.m
    public void a() {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new c());
    }

    @Override // supwisdom.m
    public void a(n nVar) {
        this.b = nVar;
    }

    @Override // supwisdom.m
    public void a(boolean z) {
        j jVar = this.d;
        if (jVar != null) {
            jVar.c();
        }
        d dVar = new d(WXRequest.DEFAULT_TIMEOUT_MS);
        this.d = dVar;
        dVar.b();
    }

    @Override // supwisdom.m
    public void a(String[] strArr) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new b(strArr));
    }

    @Override // supwisdom.m
    public String b() {
        return this.f;
    }

    @Override // supwisdom.m
    public String c() {
        return String.format("%s (%s)", a.a.a.c.c.m().i(), a.a.a.c.c.m().f());
    }

    @Override // supwisdom.m
    public void d() {
    }

    @Override // supwisdom.m
    public void e() {
        j jVar = this.d;
        if (jVar != null) {
            jVar.c();
            this.d = null;
        }
        j jVar2 = this.f8748e;
        if (jVar2 != null) {
            jVar2.c();
            this.f8748e = null;
        }
    }

    @Override // supwisdom.m
    public void f() {
    }

    @Override // supwisdom.m
    public void g() {
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

    @Override // supwisdom.m
    public void a(String str, String str2, String str3) {
        g = str2;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            if (this.b != null) {
                this.c.post(new o(this, "Check0002", "非法参数"));
                return;
            }
            return;
        }
        if (!a.a.a.c.c.m().c(str2, str3)) {
            a.a.a.c.c.m().l();
            AsyncTask.THREAD_POOL_EXECUTOR.execute(new q(this, str));
            return;
        }
        try {
            int iH = h();
            if (this.b != null) {
                this.c.post(new a(iH));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            String str4 = "校验失败：" + e2.getMessage();
            if (this.b != null) {
                this.c.post(new o(this, "Check0001", str4));
            }
        }
    }
}
