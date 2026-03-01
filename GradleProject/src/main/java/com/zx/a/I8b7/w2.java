package com.zx.a.I8b7;

import com.zx.module.base.Listener;

/* JADX INFO: loaded from: classes2.dex */
public class w2 implements Listener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Listener f6302a;
    public final /* synthetic */ a3 b;

    public w2(a3 a3Var, Listener listener) {
        this.b = a3Var;
        this.f6302a = listener;
    }

    @Override // com.zx.module.base.Listener
    public void onMessage(String str, String str2) {
        if (str.equals("zxid") || str.equals("MESSAGE_ON_ZXID_RECEIVED")) {
            this.b.f6198a.set(false);
        }
        this.f6302a.onMessage(str, str2);
    }
}
