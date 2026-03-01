package com.loc;

import android.content.Context;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;

/* JADX INFO: compiled from: StatisticsEntity.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f3671a;
    public String b;
    public String c;
    public String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f3672e;

    public bb(Context context, String str, String str2, String str3) throws j {
        if (TextUtils.isEmpty(str3) || str3.length() > 256) {
            throw new j("无效的参数 - IllegalArgumentException");
        }
        this.f3671a = context.getApplicationContext();
        this.c = str;
        this.d = str2;
        this.b = str3;
    }

    public final void a(String str) throws j {
        if (TextUtils.isEmpty(str) || str.length() > 65536) {
            throw new j("无效的参数 - IllegalArgumentException");
        }
        this.f3672e = str;
    }

    public final byte[] a() {
        int iCurrentTimeMillis;
        byte[] bArrA;
        byte[] byteArray = new byte[0];
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            try {
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                try {
                    u.a(byteArrayOutputStream2, this.c);
                    u.a(byteArrayOutputStream2, this.d);
                    u.a(byteArrayOutputStream2, this.b);
                    u.a(byteArrayOutputStream2, String.valueOf(n.q(this.f3671a)));
                    try {
                        iCurrentTimeMillis = (int) (System.currentTimeMillis() / 1000);
                    } catch (Throwable unused) {
                        iCurrentTimeMillis = 0;
                    }
                    byteArrayOutputStream2.write(new byte[]{(byte) ((iCurrentTimeMillis >> 24) & 255), (byte) ((iCurrentTimeMillis >> 16) & 255), (byte) ((iCurrentTimeMillis >> 8) & 255), (byte) (iCurrentTimeMillis & 255)});
                    byte[] bArrA2 = (TextUtils.isEmpty(this.f3672e) || (bArrA = u.a(this.f3672e)) == null) ? new byte[]{0, 0} : u.a(bArrA.length);
                    byteArrayOutputStream2.write(bArrA2);
                    byteArrayOutputStream2.write(u.a(this.f3672e));
                    byteArray = byteArrayOutputStream2.toByteArray();
                    byteArrayOutputStream2.close();
                } catch (Throwable th) {
                    th = th;
                    byteArrayOutputStream = byteArrayOutputStream2;
                    try {
                        ab.b(th, "se", "tds");
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                        return byteArray;
                    } catch (Throwable th2) {
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (Throwable th3) {
                                th3.printStackTrace();
                            }
                        }
                        throw th2;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (Throwable th5) {
            th5.printStackTrace();
        }
        return byteArray;
    }
}
