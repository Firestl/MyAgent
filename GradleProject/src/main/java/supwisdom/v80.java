package supwisdom;

import android.annotation.TargetApi;
import android.os.Trace;

/* JADX INFO: compiled from: TraceUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class v80 {
    public static void a(String str) {
        if (x80.f9718a >= 18) {
            b(str);
        }
    }

    @TargetApi(18)
    public static void b(String str) {
        Trace.beginSection(str);
    }

    @TargetApi(18)
    public static void b() {
        Trace.endSection();
    }

    public static void a() {
        if (x80.f9718a >= 18) {
            b();
        }
    }
}
