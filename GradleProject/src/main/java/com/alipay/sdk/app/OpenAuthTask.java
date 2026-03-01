package com.alipay.sdk.app;

import android.os.Bundle;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import supwisdom.vp;

/* JADX INFO: loaded from: classes.dex */
public final class OpenAuthTask {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Map<String, a> f1605a = new ConcurrentHashMap();

    public enum BizType {
        Invoice("20000920"),
        AccountAuth("20000067"),
        Deduct("60000157");

        public String appId;

        BizType(String str) {
            this.appId = str;
        }
    }

    public interface a {
        void a(int i, String str, Bundle bundle);
    }

    public static void a(String str, int i, String str2, Bundle bundle) {
        a aVarRemove = f1605a.remove(str);
        if (aVarRemove != null) {
            try {
                aVarRemove.a(i, str2, bundle);
            } catch (Throwable th) {
                vp.a(th);
            }
        }
    }
}
