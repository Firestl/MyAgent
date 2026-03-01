package com.loc;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;

/* JADX INFO: compiled from: HttpsDecisionUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile b f3830a = new b(0);
    public x b = new x("HttpsDecisionUtil");

    /* JADX INFO: compiled from: HttpsDecisionUtil.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static p f3831a = new p();
    }

    /* JADX INFO: compiled from: HttpsDecisionUtil.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f3832a;
        public int b;
        public final boolean c;
        public boolean d;

        public b() {
            this.b = 0;
            this.f3832a = true;
            this.c = true;
            this.d = false;
        }

        public /* synthetic */ b(byte b) {
            this();
        }

        public final void a(Context context) {
            if (context != null && this.b <= 0 && Build.VERSION.SDK_INT >= 4) {
                this.b = context.getApplicationContext().getApplicationInfo().targetSdkVersion;
            }
        }

        public final void a(boolean z) {
            this.f3832a = z;
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0023  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x002a  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x002e A[RETURN] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean a() {
            /*
                r5 = this;
                boolean r0 = r5.d
                r1 = 1
                if (r0 != 0) goto L2f
                int r0 = android.os.Build.VERSION.SDK_INT
                r2 = 28
                r3 = 0
                if (r0 < r2) goto Le
                r0 = 1
                goto Lf
            Le:
                r0 = 0
            Lf:
                boolean r4 = r5.f3832a
                if (r4 == 0) goto L23
                int r4 = r5.b
                if (r4 > 0) goto L19
                r4 = 28
            L19:
                if (r4 < r2) goto L1d
                r2 = 1
                goto L1e
            L1d:
                r2 = 0
            L1e:
                if (r2 == 0) goto L21
                goto L23
            L21:
                r2 = 0
                goto L24
            L23:
                r2 = 1
            L24:
                if (r0 == 0) goto L2a
                if (r2 == 0) goto L2a
                r0 = 1
                goto L2b
            L2a:
                r0 = 0
            L2b:
                if (r0 == 0) goto L2e
                goto L2f
            L2e:
                return r3
            L2f:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.loc.p.b.a():boolean");
        }

        public final void b(boolean z) {
            this.d = z;
        }
    }

    public static p a() {
        return a.f3831a;
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("https")) {
            return str;
        }
        try {
            Uri.Builder builderBuildUpon = Uri.parse(str).buildUpon();
            builderBuildUpon.scheme("https");
            return builderBuildUpon.build().toString();
        } catch (Throwable unused) {
            return str;
        }
    }

    public static void b(Context context) {
        b(context, true);
    }

    public static void b(Context context, boolean z) {
        SharedPreferences.Editor editorB = x.b(context, "open_common");
        x.a(editorB, "a3", z);
        x.a(editorB);
    }

    public static boolean b() {
        return Build.VERSION.SDK_INT == 19;
    }

    public final void a(Context context) {
        if (this.f3830a == null) {
            this.f3830a = new b((byte) 0);
        }
        this.f3830a.a(x.a(context, "open_common", "a3", true));
        this.f3830a.a(context);
    }

    public final void a(Context context, boolean z) {
        if (this.f3830a == null) {
            this.f3830a = new b((byte) 0);
        }
        b(context, z);
        this.f3830a.a(z);
    }

    public final void a(boolean z) {
        if (this.f3830a == null) {
            this.f3830a = new b((byte) 0);
        }
        this.f3830a.b(z);
    }

    public final boolean b(boolean z) {
        byte b2 = 0;
        if (b()) {
            return false;
        }
        if (z) {
            return true;
        }
        if (this.f3830a == null) {
            this.f3830a = new b(b2);
        }
        return this.f3830a.a();
    }
}
