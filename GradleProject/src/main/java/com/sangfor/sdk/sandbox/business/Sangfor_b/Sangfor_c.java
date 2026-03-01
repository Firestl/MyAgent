package com.sangfor.sdk.sandbox.business.Sangfor_b;

import android.annotation.SuppressLint;
import android.content.IOnPrimaryClipChangedListener;
import android.os.Handler;
import supwisdom.db1;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class Sangfor_c extends db1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @SuppressLint({"HandlerLeak"})
    public final Handler f3937a;

    /* JADX INFO: compiled from: Proguard */
    public class Sangfor_f extends IOnPrimaryClipChangedListener.Stub {
        public Sangfor_f() {
        }

        @Override // android.content.IOnPrimaryClipChangedListener
        public void dispatchPrimaryClipChanged() {
            Sangfor_c.this.f3937a.sendEmptyMessage(1);
        }
    }
}
