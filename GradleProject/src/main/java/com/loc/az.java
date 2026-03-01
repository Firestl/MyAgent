package com.loc;

import android.content.Context;

/* JADX INFO: compiled from: OfflineLocEntity.java */
/* JADX INFO: loaded from: classes2.dex */
public final class az {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f3666a;
    public t b;
    public String c;

    public az(Context context, t tVar, String str) {
        this.f3666a = context.getApplicationContext();
        this.b = tVar;
        this.c = str;
    }

    public static String a(Context context, t tVar, String str) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("\"sdkversion\":\"");
            sb.append(tVar.c());
            sb.append("\",\"product\":\"");
            sb.append(tVar.a());
            sb.append("\",\"nt\":\"");
            sb.append(n.d(context));
            sb.append("\",\"details\":");
            sb.append(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return sb.toString();
    }

    public final byte[] a() {
        return u.a(a(this.f3666a, this.b, this.c));
    }
}
