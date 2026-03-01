package com.zx.a.I8b7;

import com.zx.a.I8b7.t1;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes2.dex */
public class t implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ s f6282a;

    public t(s sVar) {
        this.f6282a = sVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            s sVar = this.f6282a;
            if (sVar.f6276a != null) {
                sVar.f6276a = new JSONArray();
                t1 t1Var = t1.a.f6285a;
                t1Var.f6284a.getClass();
                t1Var.f6284a.a(23, "", true);
            }
        } catch (Throwable unused) {
        }
    }
}
