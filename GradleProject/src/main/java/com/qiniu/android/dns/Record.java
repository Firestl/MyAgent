package com.qiniu.android.dns;

/* JADX INFO: loaded from: classes2.dex */
public final class Record {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f3868a;
    public final int b;
    public final int c;
    public final long d;

    public enum Source {
        Unknown,
        DnspodFree,
        DnspodEnterprise,
        System
    }

    public Record(String str, int i, int i2, long j, Source source) {
        this.f3868a = str;
        this.b = i;
        this.c = i2 < 600 ? 600 : i2;
        this.d = j;
    }

    public boolean a() {
        return a(System.currentTimeMillis() / 1000);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Record)) {
            return false;
        }
        Record record = (Record) obj;
        return this.f3868a.equals(record.f3868a) && this.b == record.b && this.c == record.c && this.d == record.d;
    }

    public boolean a(long j) {
        return this.d + ((long) this.c) < j;
    }
}
