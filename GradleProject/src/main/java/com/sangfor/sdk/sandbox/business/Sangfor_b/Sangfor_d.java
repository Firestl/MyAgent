package com.sangfor.sdk.sandbox.business.Sangfor_b;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.IOnPrimaryClipChangedListener;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import com.sangfor.atrust.JNIBridge.JniTool;
import supwisdom.db1;
import supwisdom.t91;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class Sangfor_d extends db1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @SuppressLint({"HandlerLeak"})
    public final Handler f3939a;

    /* JADX INFO: compiled from: Proguard */
    public class Sangfor_f extends IOnPrimaryClipChangedListener.Stub {
        public Sangfor_f() {
        }

        @Override // android.content.IOnPrimaryClipChangedListener
        public void dispatchPrimaryClipChanged() {
            ClipData clipDataA = Sangfor_d.this.a();
            if (clipDataA == null || clipDataA.getItemCount() < 0 || clipDataA.getItemAt(0) == null) {
                t91.d("DefaultClipBusinessV29", "dispatchPrimaryClipChanged systemClipData is null");
            } else {
                Intent intent = clipDataA.getItemAt(0).getIntent();
                if (intent != null) {
                    t91.c("DefaultClipBusinessV29", "dispatchPrimaryClipChanged has intent");
                    if (TextUtils.equals(intent.getStringExtra("key_secure_package"), JniTool.a())) {
                        t91.d("DefaultClipBusinessV29", "dispatchPrimaryClipChanged is return");
                        return;
                    }
                } else {
                    t91.d("DefaultClipBusinessV29", "dispatchPrimaryClipChanged intent is null");
                }
            }
            Sangfor_d.this.f3939a.sendEmptyMessage(1);
        }
    }

    public final ClipData a() {
        throw null;
    }
}
