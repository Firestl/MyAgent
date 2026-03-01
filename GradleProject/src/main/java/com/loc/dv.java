package com.loc;

import java.util.Locale;

/* JADX INFO: compiled from: Cgi.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dv {
    public int k;
    public boolean n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3752a = 0;
    public int b = 0;
    public int c = 0;
    public int d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3753e = 0;
    public int f = 0;
    public int g = 0;
    public int h = 0;
    public int i = 0;
    public int j = -113;
    public short l = 0;
    public long m = 0;
    public int o = 32767;
    public boolean p = true;

    public dv(int i, boolean z) {
        this.k = 0;
        this.n = false;
        this.k = i;
        this.n = z;
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof dv)) {
            dv dvVar = (dv) obj;
            int i = dvVar.k;
            if (i != 1) {
                return i != 2 ? i != 3 ? i == 4 && this.k == 4 && dvVar.c == this.c && dvVar.d == this.d && dvVar.b == this.b : this.k == 3 && dvVar.c == this.c && dvVar.d == this.d && dvVar.b == this.b : this.k == 2 && dvVar.i == this.i && dvVar.h == this.h && dvVar.g == this.g;
            }
            if (this.k == 1 && dvVar.c == this.c && dvVar.d == this.d && dvVar.b == this.b) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode;
        int i;
        int iHashCode2 = String.valueOf(this.k).hashCode();
        if (this.k == 2) {
            iHashCode = String.valueOf(this.i).hashCode() + String.valueOf(this.h).hashCode();
            i = this.g;
        } else {
            iHashCode = String.valueOf(this.c).hashCode() + String.valueOf(this.d).hashCode();
            i = this.b;
        }
        return iHashCode2 + iHashCode + String.valueOf(i).hashCode();
    }

    public final String toString() {
        int i = this.k;
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "unknown" : String.format(Locale.CHINA, "WCDMA lac=%d, cid=%d, mnc=%s, valid=%b, sig=%d, age=%d, reg=%b, pci=%d", Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.b), Boolean.valueOf(this.p), Integer.valueOf(this.j), Short.valueOf(this.l), Boolean.valueOf(this.n), Integer.valueOf(this.o)) : String.format(Locale.CHINA, "LTE lac=%d, cid=%d, mnc=%s, valid=%b, sig=%d, age=%d, reg=%b, pci=%d", Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.b), Boolean.valueOf(this.p), Integer.valueOf(this.j), Short.valueOf(this.l), Boolean.valueOf(this.n), Integer.valueOf(this.o)) : String.format(Locale.CHINA, "CDMA bid=%d, nid=%d, sid=%d, valid=%b, sig=%d, age=%d, reg=%b", Integer.valueOf(this.i), Integer.valueOf(this.h), Integer.valueOf(this.g), Boolean.valueOf(this.p), Integer.valueOf(this.j), Short.valueOf(this.l), Boolean.valueOf(this.n)) : String.format(Locale.CHINA, "GSM lac=%d, cid=%d, mnc=%s, valid=%b, sig=%d, age=%d, reg=%b", Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.b), Boolean.valueOf(this.p), Integer.valueOf(this.j), Short.valueOf(this.l), Boolean.valueOf(this.n));
    }
}
