package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.util.f;
import io.dcloud.common.constant.AbsoluteConst;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import supwisdom.bq;
import supwisdom.cq;
import supwisdom.jp;
import supwisdom.op;
import supwisdom.po;
import supwisdom.pp;
import supwisdom.qo;
import supwisdom.qp;
import supwisdom.so;
import supwisdom.vo;
import supwisdom.zp;

/* JADX INFO: loaded from: classes.dex */
public class AuthTask {
    public static final Object c = f.class;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Activity f1600a;
    public cq b;

    public class a implements f.e {
        public a() {
        }

        @Override // com.alipay.sdk.util.f.e
        public void a() {
        }

        @Override // com.alipay.sdk.util.f.e
        public void b() {
            AuthTask.this.c();
        }
    }

    public AuthTask(Activity activity) {
        this.f1600a = activity;
        qp.d().a(this.f1600a);
        this.b = new cq(activity, "去支付宝授权");
    }

    public synchronized String auth(String str, boolean z) {
        return innerAuth(new pp(this.f1600a, str, "auth"), str, z);
    }

    public synchronized Map<String, String> authV2(String str, boolean z) {
        pp ppVar;
        ppVar = new pp(this.f1600a, str, "authV2");
        return zp.a(ppVar, innerAuth(ppVar, str, z));
    }

    public final void b() {
        cq cqVar = this.b;
        if (cqVar != null) {
            cqVar.a();
        }
    }

    public final void c() {
        cq cqVar = this.b;
        if (cqVar != null) {
            cqVar.b();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x006b A[Catch: all -> 0x0146, TryCatch #0 {, blocks: (B:4:0x0003, B:5:0x0006, B:7:0x001e, B:9:0x006b, B:10:0x0074, B:11:0x007b, B:24:0x00e5, B:26:0x0132, B:27:0x013b, B:28:0x0145, B:17:0x0085, B:19:0x00d2, B:20:0x00db, B:6:0x0018, B:16:0x0082), top: B:32:0x0003, inners: #1, #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized java.lang.String innerAuth(supwisdom.pp r7, java.lang.String r8, boolean r9) {
        /*
            Method dump skipped, instruction units count: 329
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.AuthTask.innerAuth(supwisdom.pp, java.lang.String, boolean):java.lang.String");
    }

    public final f.e a() {
        return new a();
    }

    public final String a(Activity activity, String str, pp ppVar) {
        String strA = ppVar.a(str);
        List<vo.b> listR = vo.v().r();
        if (!vo.v().g || listR == null) {
            listR = po.d;
        }
        if (bq.b(ppVar, this.f1600a, listR)) {
            String strA2 = new f(activity, ppVar, a()).a(strA);
            if (!TextUtils.equals(strA2, AbsoluteConst.EVENTS_FAILED) && !TextUtils.equals(strA2, "scheme_failed")) {
                return TextUtils.isEmpty(strA2) ? qo.c() : strA2;
            }
            so.a(ppVar, "biz", "LogBindCalledH5");
            return b(activity, strA, ppVar);
        }
        so.a(ppVar, "biz", "LogCalledH5");
        return b(activity, strA, ppVar);
    }

    public final String b(Activity activity, String str, pp ppVar) {
        c cVarB;
        b();
        try {
            try {
                try {
                    List<op> listA = op.a(new jp().a(ppVar, activity, str).c().optJSONObject("form").optJSONObject("onload"));
                    c();
                    for (int i = 0; i < listA.size(); i++) {
                        if (listA.get(i).a() == com.alipay.sdk.protocol.a.WapPay) {
                            String strA = a(ppVar, listA.get(i));
                            c();
                            return strA;
                        }
                    }
                } catch (Throwable th) {
                    so.a(ppVar, "biz", "H5AuthDataAnalysisError", th);
                }
                c();
                cVarB = null;
            } catch (IOException e2) {
                c cVarB2 = c.b(c.NETWORK_ERROR.a());
                so.a(ppVar, "net", e2);
                c();
                cVarB = cVarB2;
            }
            if (cVarB == null) {
                cVarB = c.b(c.FAILED.a());
            }
            return qo.a(cVarB.a(), cVarB.b(), "");
        } catch (Throwable th2) {
            c();
            throw th2;
        }
    }

    public final String a(pp ppVar, op opVar) {
        String[] strArrB = opVar.b();
        Bundle bundle = new Bundle();
        bundle.putString("url", strArrB[0]);
        Intent intent = new Intent(this.f1600a, (Class<?>) H5AuthActivity.class);
        intent.putExtras(bundle);
        pp.a.a(ppVar, intent);
        this.f1600a.startActivity(intent);
        synchronized (c) {
            try {
                c.wait();
            } catch (InterruptedException unused) {
                return qo.c();
            }
        }
        String strA = qo.a();
        return TextUtils.isEmpty(strA) ? qo.c() : strA;
    }
}
