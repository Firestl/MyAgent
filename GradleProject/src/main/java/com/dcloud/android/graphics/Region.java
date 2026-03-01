package com.dcloud.android.graphics;

/* JADX INFO: loaded from: classes.dex */
public class Region extends android.graphics.Region {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1779a;
    public int b;

    public Region() {
        this(1);
    }

    public void a() {
        this.b++;
    }

    public boolean b() {
        return this.b >= this.f1779a;
    }

    public int c() {
        return this.b;
    }

    public Region(int i) {
        this.f1779a = 2;
        this.b = 1;
        this.f1779a = i;
    }
}
