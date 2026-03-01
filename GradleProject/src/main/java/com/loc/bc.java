package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.idl.face.platform.common.ConstantHelper;
import com.baidu.idl.face.platform.utils.CameraPreviewUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Random;

/* JADX INFO: compiled from: StatisticsManager.java */
/* JADX INFO: loaded from: classes2.dex */
public class bc {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f3673a = false;
    public static int b = 20;
    public static int c = 20;
    public static WeakReference<ax> d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static int f3674e;

    /* JADX INFO: compiled from: StatisticsManager.java */
    public static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static int f3675a = 1;
        public static int b = 2;
        public static int c = 3;
        public Context d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public bb f3676e;
        public int f;
        public List<bb> g;

        public a(Context context, int i) {
            this.d = context;
            this.f = i;
        }

        public a(Context context, int i, bb bbVar) {
            this(context, i);
            this.f3676e = bbVar;
        }

        public a(Context context, int i, List<bb> list) {
            this(context, i);
            this.g = list;
        }

        @Override // java.lang.Runnable
        public final void run() {
            String str;
            String str2;
            Throwable th;
            ByteArrayOutputStream byteArrayOutputStream;
            int i = this.f;
            if (i == 1) {
                try {
                    if (this.d != null && this.f3676e != null) {
                        synchronized (bc.class) {
                            if (this.d != null && this.f3676e != null) {
                                bc.a(this.d, this.f3676e.a());
                                return;
                            }
                            return;
                        }
                    }
                    return;
                } catch (Throwable th2) {
                    th = th2;
                    str = ConstantHelper.LOG_STM;
                    str2 = "as";
                }
            } else {
                if (i != 2) {
                    if (i == 3) {
                        try {
                            if (this.d == null) {
                                return;
                            }
                            ax axVarA = bd.a(bc.d);
                            bd.a(this.d, axVarA, z.h, 1000, CameraPreviewUtils.MIN_PREVIEW_PIXELS, "2");
                            if (axVarA.g == null) {
                                axVarA.g = new be(new bi(this.d, new bf(new bj(new bl()))));
                            }
                            axVarA.h = 3600000;
                            if (TextUtils.isEmpty(axVarA.i)) {
                                axVarA.i = "cKey";
                            }
                            if (axVarA.f == null) {
                                axVarA.f = new bp(this.d, axVarA.h, axVarA.i, new bm(axVarA.f3664a, new bn(this.d, bc.f3673a, bc.c * 1024, bc.b * 1024, "staticUpdate", bc.f3674e * 1024)));
                            }
                            ay.a(axVarA);
                            return;
                        } catch (Throwable th3) {
                            ab.b(th3, ConstantHelper.LOG_STM, "usd");
                            return;
                        }
                    }
                    return;
                }
                try {
                    synchronized (bc.class) {
                        if (this.g != null && this.d != null) {
                            ByteArrayOutputStream byteArrayOutputStream2 = null;
                            byte[] byteArray = new byte[0];
                            try {
                                byteArrayOutputStream = new ByteArrayOutputStream();
                            } catch (Throwable th4) {
                                th = th4;
                            }
                            try {
                                for (bb bbVar : this.g) {
                                    if (bbVar != null) {
                                        byteArrayOutputStream.write(bbVar.a());
                                    }
                                }
                                byteArray = byteArrayOutputStream.toByteArray();
                                try {
                                    byteArrayOutputStream.close();
                                } catch (Throwable th5) {
                                    th = th5;
                                    th.printStackTrace();
                                }
                            } catch (Throwable th6) {
                                th = th6;
                                byteArrayOutputStream2 = byteArrayOutputStream;
                                try {
                                    ab.b(th, ConstantHelper.LOG_STM, "aStB");
                                    if (byteArrayOutputStream2 != null) {
                                        try {
                                            byteArrayOutputStream2.close();
                                        } catch (Throwable th7) {
                                            th = th7;
                                            th.printStackTrace();
                                        }
                                    }
                                } finally {
                                }
                            }
                            bc.a(this.d, byteArray);
                            return;
                        }
                        return;
                    }
                } catch (Throwable th8) {
                    th = th8;
                    str = ConstantHelper.LOG_STM;
                    str2 = "apb";
                }
            }
            ab.b(th, str, str2);
        }
    }

    public static void a(Context context) {
        ab.d().submit(new a(context, a.c));
    }

    public static /* synthetic */ void a(Context context, byte[] bArr) throws IOException {
        ax axVarA = bd.a(d);
        bd.a(context, axVarA, z.h, 1000, CameraPreviewUtils.MIN_PREVIEW_PIXELS, "2");
        if (axVarA.f3665e == null) {
            axVarA.f3665e = new am();
        }
        try {
            ay.a(Integer.toString(new Random().nextInt(100)) + Long.toString(System.nanoTime()), bArr, axVarA);
        } catch (Throwable th) {
            ab.b(th, ConstantHelper.LOG_STM, "wts");
        }
    }

    public static synchronized void a(bb bbVar, Context context) {
        ab.d().submit(new a(context, a.f3675a, bbVar));
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0009, code lost:
    
        if (r4.size() == 0) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized void a(java.util.List<com.loc.bb> r4, android.content.Context r5) {
        /*
            java.lang.Class<com.loc.bc> r0 = com.loc.bc.class
            monitor-enter(r0)
            if (r4 == 0) goto L1f
            int r1 = r4.size()     // Catch: java.lang.Throwable -> Lc
            if (r1 != 0) goto Lc
            goto L1f
        Lc:
            java.util.concurrent.ExecutorService r1 = com.loc.ab.d()     // Catch: java.lang.Throwable -> L1c
            com.loc.bc$a r2 = new com.loc.bc$a     // Catch: java.lang.Throwable -> L1c
            int r3 = com.loc.bc.a.b     // Catch: java.lang.Throwable -> L1c
            r2.<init>(r5, r3, r4)     // Catch: java.lang.Throwable -> L1c
            r1.submit(r2)     // Catch: java.lang.Throwable -> L1c
            monitor-exit(r0)
            return
        L1c:
            r4 = move-exception
            monitor-exit(r0)
            throw r4
        L1f:
            monitor-exit(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.bc.a(java.util.List, android.content.Context):void");
    }

    public static synchronized void a(boolean z, int i) {
        f3673a = z;
        f3674e = Math.max(0, i);
    }

    public static synchronized void b(List<bb> list, Context context) {
        try {
            List<bb> listB = as.b();
            if (listB != null && listB.size() > 0) {
                list.addAll(listB);
            }
        } catch (Throwable unused) {
        }
        a(list, context);
    }
}
