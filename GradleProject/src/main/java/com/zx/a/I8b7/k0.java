package com.zx.a.I8b7;

import android.media.MediaDrm;
import android.os.Build;
import android.util.Base64;
import com.zx.module.base.Callback;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/* JADX INFO: loaded from: classes2.dex */
public class k0 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Callback f6237a;

    public k0(Callback callback) {
        this.f6237a = callback;
    }

    @Override // java.lang.Runnable
    public void run() {
        MediaDrm mediaDrm;
        Throwable th;
        try {
            try {
                mediaDrm = new MediaDrm(new UUID(-1301668207276963122L, -6645017420763422227L));
            } catch (Throwable th2) {
                mediaDrm = null;
                th = th2;
            }
            try {
                String str = new String(Base64.encode(mediaDrm.getPropertyByteArray("deviceUniqueId"), 2), StandardCharsets.UTF_8);
                if (Build.VERSION.SDK_INT >= 28) {
                    mediaDrm.close();
                } else {
                    mediaDrm.release();
                }
                this.f6237a.callback(str);
            } catch (Throwable th3) {
                th = th3;
                try {
                    y1.a(th);
                    if (Build.VERSION.SDK_INT >= 28) {
                        if (mediaDrm != null) {
                            mediaDrm.close();
                        }
                    } else if (mediaDrm != null) {
                        mediaDrm.release();
                    }
                    this.f6237a.callback("");
                } catch (Throwable th4) {
                    try {
                        if (Build.VERSION.SDK_INT >= 28) {
                            if (mediaDrm != null) {
                                mediaDrm.close();
                            }
                        } else if (mediaDrm != null) {
                            mediaDrm.release();
                        }
                        this.f6237a.callback("");
                    } catch (Throwable unused) {
                    }
                    throw th4;
                }
            }
        } catch (Throwable unused2) {
        }
    }
}
