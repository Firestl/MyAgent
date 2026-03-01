package com.g.gysdk.a;

import com.g.gysdk.GyErrorCode;
import com.g.gysdk.a.al;
import io.dcloud.common.constant.AbsoluteConst;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicBoolean f1982a = new AtomicBoolean(false);

    public static a a(String str, boolean z, String str2, String str3, String str4) {
        if (str4 == null) {
            str4 = "";
        }
        String strReplace = str4.replace('|', '#').replace('\n', '#').replace('\r', '#');
        if (str3 == null) {
            str3 = "";
        }
        a aVar = new a();
        aVar.b(System.currentTimeMillis());
        as asVarA = ar.a(false, -1);
        StringBuilder sb = new StringBuilder();
        sb.append(d.f2000e);
        sb.append("|");
        sb.append(d.b().b);
        sb.append("|");
        sb.append(d.h);
        sb.append("|");
        sb.append(d.d);
        sb.append("|");
        sb.append(d.c);
        sb.append("|");
        sb.append(str);
        sb.append("|");
        sb.append(asVarA.f1981e);
        sb.append("|");
        sb.append(asVarA.g);
        sb.append("|");
        sb.append(ar.e(d.c()));
        sb.append("|");
        sb.append(z);
        sb.append("|");
        if (z) {
            str2 = "200";
        }
        sb.append(str2);
        sb.append("|");
        sb.append(str3);
        sb.append("|");
        sb.append(aVar.b());
        sb.append("|");
        sb.append(strReplace);
        sb.append("|");
        sb.append(ar.f(d.c()));
        aVar.a(sb.toString());
        return aVar;
    }

    public static void a() {
        if (f1982a.getAndSet(true)) {
            return;
        }
        al.a(al.b.Queue, new Runnable() { // from class: com.g.gysdk.a.b.3
            @Override // java.lang.Runnable
            public void run() {
                ak.a("handle offline log ...start<<<");
                try {
                    List<a> listA = g.b().a(10);
                    ak.a("handle offline log ...total:" + listA.size());
                    int i = 0;
                    for (a aVar : listA) {
                        i++;
                        ak.a("handle offline log index:" + i + " ... and " + (listA.size() - i) + " left");
                        if (aVar.e()) {
                            g.b().a(aVar.a());
                            ak.a("handle offline log index:" + i + " expired, just deleted");
                        } else {
                            boolean zA = ac.a(aVar);
                            StringBuilder sb = new StringBuilder();
                            sb.append("handle offline log index:");
                            sb.append(i);
                            sb.append(" result:");
                            sb.append(zA ? "success" : AbsoluteConst.EVENTS_FAILED);
                            ak.a(sb.toString());
                            if (zA) {
                                g.b().a(aVar.a());
                            }
                        }
                    }
                } catch (Throwable th) {
                    ak.e("handle offline log error", th);
                }
                ak.a("handle offline log ...done>>>");
                b.f1982a.set(false);
            }
        });
    }

    public static void a(final a aVar) {
        al.a(al.b.Queue, new Runnable() { // from class: com.g.gysdk.a.b.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ak.e("handle log ...");
                    long jA = g.b().a(aVar);
                    if (!ac.a(aVar)) {
                        throw new IllegalStateException("upload failed");
                    }
                    g.b().a(jA);
                    b.a();
                } catch (Throwable th) {
                    ak.e("handle log error", th);
                }
            }
        });
    }

    public static void a(final String str, final GyErrorCode gyErrorCode, final String str2, final String str3) {
        al.a(al.b.Queue, new Runnable() { // from class: com.g.gysdk.a.b.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ak.a("handle log action " + str + " ...");
                    boolean z = gyErrorCode == GyErrorCode.SUCCESS;
                    a aVarA = b.a(str, z, z ? "200" : String.valueOf(gyErrorCode.value), str2, str3);
                    long jA = g.b().a(aVarA);
                    if (!ac.a(aVarA)) {
                        throw new IllegalStateException("upload failed");
                    }
                    g.b().a(jA);
                    b.a();
                } catch (Throwable th) {
                    ak.e("handle log action " + str + " error", th);
                }
            }
        });
    }
}
