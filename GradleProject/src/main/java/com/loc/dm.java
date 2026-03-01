package com.loc;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: AMapLocationStaticsEntity.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dm implements Parcelable {
    public static final Parcelable.Creator<dm> CREATOR = new Parcelable.Creator<dm>() { // from class: com.loc.dm.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ dm createFromParcel(Parcel parcel) {
            dm dmVar = new dm();
            dmVar.c(parcel.readString());
            dmVar.d(parcel.readString());
            dmVar.e(parcel.readString());
            dmVar.f(parcel.readString());
            dmVar.b(parcel.readString());
            dmVar.c(parcel.readLong());
            dmVar.d(parcel.readLong());
            dmVar.a(parcel.readLong());
            dmVar.b(parcel.readLong());
            dmVar.a(parcel.readString());
            return dmVar;
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ dm[] newArray(int i) {
            return new dm[i];
        }
    };

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f3734e;
    public String f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f3733a = 0;
    public long b = 0;
    public long c = 0;
    public long d = 0;
    public String g = "first";
    public String h = "";
    public String i = "";
    public String j = null;

    public final long a() {
        long j = this.d;
        long j2 = this.c;
        if (j - j2 <= 0) {
            return 0L;
        }
        return j - j2;
    }

    public final void a(long j) {
        this.c = j;
    }

    public final void a(String str) {
        this.i = str;
    }

    public final String b() {
        return this.i;
    }

    public final void b(long j) {
        this.d = j;
    }

    public final void b(String str) {
        this.j = str;
    }

    public final String c() {
        return this.j;
    }

    public final void c(long j) {
        this.f3733a = j;
    }

    public final void c(String str) {
        this.f3734e = str;
    }

    public final String d() {
        return this.f3734e;
    }

    public final void d(long j) {
        this.b = j;
    }

    public final void d(String str) {
        this.f = str;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String e() {
        return this.f;
    }

    public final void e(String str) {
        this.g = str;
    }

    public final String f() {
        return this.g;
    }

    public final void f(String str) {
        this.h = str;
    }

    public final String g() {
        return this.h;
    }

    public final long h() {
        long j = this.b;
        long j2 = this.f3733a;
        if (j <= j2) {
            return 0L;
        }
        return j - j2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        try {
            parcel.writeString(this.f3734e);
            parcel.writeString(this.f);
            parcel.writeString(this.g);
            parcel.writeString(this.h);
            parcel.writeString(this.j);
            parcel.writeLong(this.f3733a);
            parcel.writeLong(this.b);
            parcel.writeLong(this.c);
            parcel.writeLong(this.d);
            parcel.writeString(this.i);
        } catch (Throwable unused) {
        }
    }
}
