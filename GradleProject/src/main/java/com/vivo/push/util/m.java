package com.vivo.push.util;

import android.content.Context;
import android.os.Process;
import android.util.Log;
import java.util.Iterator;

/* JADX INFO: compiled from: LogController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class m implements n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f5639a = "(" + Process.myPid() + ")";

    @Override // com.vivo.push.util.n
    public final int a(String str, String str2) {
        return Log.e("VivoPush.".concat(String.valueOf(str)), f5639a + str2);
    }

    @Override // com.vivo.push.util.n
    public final int b(String str, String str2) {
        return Log.w("VivoPush.".concat(String.valueOf(str)), f5639a + str2);
    }

    @Override // com.vivo.push.util.n
    public final int c(String str, String str2) {
        return Log.d("VivoPush.".concat(String.valueOf(str)), f5639a + str2);
    }

    @Override // com.vivo.push.util.n
    public final int d(String str, String str2) {
        if (!o.a()) {
            return -1;
        }
        return Log.i("VivoPush.".concat(String.valueOf(str)), f5639a + str2);
    }

    @Override // com.vivo.push.util.n
    public final int e(String str, String str2) {
        if (!o.a()) {
            return -1;
        }
        return Log.v("VivoPush.".concat(String.valueOf(str)), f5639a + str2);
    }

    @Override // com.vivo.push.util.n
    public final int a(String str, Throwable th) {
        return Log.e("VivoPush.".concat(String.valueOf(str)), Log.getStackTraceString(th));
    }

    @Override // com.vivo.push.util.n
    public final int b(String str, String str2, Throwable th) {
        if (!o.a()) {
            return -1;
        }
        return Log.i("VivoPush.".concat(String.valueOf(str)), f5639a + str2, th);
    }

    @Override // com.vivo.push.util.n
    public final void c(Context context, String str) {
        if (a()) {
            a(context, str, 2);
        }
    }

    @Override // com.vivo.push.util.n
    public final void b(Context context, String str) {
        if (a()) {
            a(context, str, 1);
        }
    }

    @Override // com.vivo.push.util.n
    public final int a(String str, String str2, Throwable th) {
        return Log.e("VivoPush.".concat(String.valueOf(str)), f5639a + str2, th);
    }

    @Override // com.vivo.push.util.n
    public final String a(Throwable th) {
        return Log.getStackTraceString(th);
    }

    @Override // com.vivo.push.util.n
    public final void a(Context context, String str) {
        if (a()) {
            a(context, str, 0);
        }
    }

    private void a(Context context, String str, int i) {
        com.vivo.push.b.n nVar = new com.vivo.push.b.n();
        nVar.b(str);
        nVar.a(i);
        if (i > 0) {
            d(com.igexin.c.a.c.a.c.c, str);
        }
        if (context.getPackageName().equals("com.vivo.pushservice")) {
            nVar.a(true);
            Iterator<String> it = com.vivo.push.d.a.a().b().iterator();
            while (it.hasNext()) {
                com.vivo.push.a.a.a(context, nVar, it.next());
            }
            return;
        }
        nVar.a(false);
        com.vivo.push.a.a.a(context, nVar, context.getPackageName());
    }

    public static boolean a() {
        o.a();
        return com.vivo.push.d.a.a().c();
    }
}
