package com.loc;

/* JADX INFO: compiled from: AmapLocation.java */
/* JADX INFO: loaded from: classes2.dex */
public class db {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f3720a;
    public long b = 0;
    public long c = 0;
    public double d = 0.0d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public double f3721e = 0.0d;
    public double f = 0.0d;
    public float g = 0.0f;
    public float h = 0.0f;
    public float i = 0.0f;
    public boolean j = false;

    public db(String str) {
        this.f3720a = str;
    }

    public final double a(db dbVar) {
        if (dbVar == null) {
            return 0.0d;
        }
        double d = this.f3721e;
        double d2 = this.d;
        double d3 = dbVar.f3721e;
        double d4 = dbVar.d;
        double d5 = (((90.0d - d2) * 21412.0d) / 90.0d) + 6356725.0d;
        double dCos = ((d3 * 0.01745329238474369d) - (d * 0.01745329238474369d)) * Math.cos((3.1415927410125732d * d2) / 180.0d) * d5;
        double d6 = ((d4 * 0.01745329238474369d) - (d2 * 0.01745329238474369d)) * d5;
        return Math.pow((dCos * dCos) + (d6 * d6), 0.5d);
    }
}
