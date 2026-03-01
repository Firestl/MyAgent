package com.efs.sdk.base.core.b;

import com.efs.sdk.base.core.util.Log;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ConcurrentHashMap<Byte, e> f1806a = new ConcurrentHashMap<>();

    public final e a(byte b) {
        if (!this.f1806a.containsKey(Byte.valueOf(b))) {
            if (b == 1) {
                this.f1806a.putIfAbsent(Byte.valueOf(b), new g());
            } else if (b != 2) {
                Log.w("efs.cache", "Cache module not support protocol ".concat(String.valueOf((int) b)));
            } else {
                this.f1806a.putIfAbsent(Byte.valueOf(b), new d());
            }
        }
        return this.f1806a.get(Byte.valueOf(b));
    }
}
