package com.loc;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: AmapWifi.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dd {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f3722a;
    public String b;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f3723e;
    public short g;
    public boolean h;
    public int c = -113;
    public long f = 0;

    public dd(boolean z) {
        this.h = z;
    }

    public static long a(String str) {
        long j;
        if (str == null || str.length() == 0) {
            return 0L;
        }
        int i = 0;
        long j2 = 0;
        for (int length = str.length() - 1; length >= 0; length--) {
            long jCharAt = str.charAt(length);
            if (jCharAt < 48 || jCharAt > 57) {
                long j3 = 97;
                if (jCharAt < 97 || jCharAt > 102) {
                    j3 = 65;
                    if (jCharAt < 65 || jCharAt > 70) {
                        if (jCharAt != 58 && jCharAt != 124) {
                            return 0L;
                        }
                    }
                }
                j = (jCharAt - j3) + 10;
            } else {
                j = jCharAt - 48;
            }
            j2 += j << i;
            i += 4;
        }
        if (i != 48) {
            return 0L;
        }
        return j2;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        dd ddVar = new dd(this.h);
        ddVar.f3722a = this.f3722a;
        ddVar.b = this.b;
        ddVar.c = this.c;
        ddVar.d = this.d;
        ddVar.f3723e = this.f3723e;
        ddVar.f = this.f;
        ddVar.g = this.g;
        ddVar.h = this.h;
        return ddVar;
    }

    public final String toString() {
        return "AmapWifi{mac=" + this.f3722a + ", ssid='" + this.b + Operators.SINGLE_QUOTE + ", rssi=" + this.c + ", frequency=" + this.d + ", timestamp=" + this.f3723e + ", lastUpdateUtcMills=" + this.f + ", freshness=" + ((int) this.g) + ", connected=" + this.h + Operators.BLOCK_END;
    }
}
