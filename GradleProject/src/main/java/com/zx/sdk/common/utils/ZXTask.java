package com.zx.sdk.common.utils;

import com.zx.a.I8b7.m1;
import com.zx.a.I8b7.n2;
import com.zx.a.I8b7.y2;

/* JADX INFO: loaded from: classes2.dex */
public class ZXTask implements Runnable {
    public a errorCallback;
    public Runnable r;

    public interface a {
    }

    public ZXTask(Runnable runnable, a aVar) {
        this.r = runnable;
        this.errorCallback = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            Runnable runnable = this.r;
            if (runnable != null) {
                runnable.run();
            }
        } catch (Throwable th) {
            a aVar = this.errorCallback;
            if (aVar != null) {
                ((y2) aVar).f6307a.c.onMessage("MESSAGE_ON_ZXID_RECEIVED", m1.a(10008, th.getMessage()));
                StringBuilder sb = new StringBuilder();
                sb.append("ZXCore start failed: ");
                n2.a(th, sb);
            }
        }
    }
}
