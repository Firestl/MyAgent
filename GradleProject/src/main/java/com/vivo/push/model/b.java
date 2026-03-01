package com.vivo.push.model;

import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: PushPackageInfo.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f5619a;
    public String d;
    public long b = -1;
    public int c = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f5620e = false;
    public boolean f = false;

    public b(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalAccessError("PushPackageInfo need a non-null pkgName.");
        }
        this.f5619a = str;
    }

    public final String a() {
        return this.f5619a;
    }

    public final long b() {
        return this.b;
    }

    public final boolean c() {
        return this.f5620e;
    }

    public final boolean d() {
        return this.f;
    }

    public final String toString() {
        return "PushPackageInfo{mPackageName=" + this.f5619a + ", mPushVersion=" + this.b + ", mPackageVersion=" + this.c + ", mInBlackList=" + this.f5620e + ", mPushEnable=" + this.f + Operators.BLOCK_END_STR;
    }

    public final void a(long j) {
        this.b = j;
    }

    public final void b(boolean z) {
        this.f = z;
    }

    public final void a(boolean z) {
        this.f5620e = z;
    }

    public final void a(int i) {
        this.c = i;
    }

    public final void a(String str) {
        this.d = str;
    }
}
