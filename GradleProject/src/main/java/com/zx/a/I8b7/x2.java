package com.zx.a.I8b7;

import android.text.TextUtils;
import com.zx.a.I8b7.s0;
import com.zx.a.I8b7.t1;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public class x2 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ a3 f6304a;

    public x2(a3 a3Var) {
        this.f6304a = a3Var;
    }

    @Override // java.lang.Runnable
    public void run() {
        s0 s0Var = s0.a.f6280a;
        try {
            s0Var.f6279a = System.currentTimeMillis();
            s0Var.b = UUID.randomUUID().toString().replaceAll("-", "");
            t1 t1Var = t1.a.f6285a;
            String strA = t1Var.f6284a.a(24);
            if (!TextUtils.isEmpty(strA)) {
                s0Var.c = Integer.parseInt(strA);
            }
            s0Var.c++;
            b3 b3Var = t1Var.f6284a;
            String str = s0Var.c + "";
            b3Var.getClass();
            t1Var.f6284a.a(24, str, true);
            y1.a("process start pts:" + s0Var.f6279a + ", pid:" + s0Var.b + ", rc:" + s0Var.c);
        } catch (Throwable th) {
            y1.a(th);
        }
        if (!this.f6304a.b.get()) {
            throw new IllegalStateException("ZXSdkImpl not init, should init firstly");
        }
        try {
            a3.a(this.f6304a);
        } catch (Throwable th2) {
            this.f6304a.c.onMessage("MESSAGE_ON_ZXID_RECEIVED", m1.a(10000, th2.getMessage()));
            StringBuilder sb = new StringBuilder();
            sb.append("ZXCore start failed: ");
            n2.a(th2, sb);
        }
    }
}
