package com.g.gysdk;

import android.content.Context;
import com.g.gysdk.a.aj;
import com.g.gysdk.a.s;
import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes.dex */
public class GyConfig {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1939a;
    public final String b;
    public final String c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f1940e;
    public final String f;
    public final String g;
    public final String h;
    public final boolean i;
    public final boolean j;
    public final boolean k;
    public final GyCallBack l;

    public static class Builder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Context f1941a;
        public String b;
        public String c;
        public String d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f1942e;
        public String f;
        public String g;
        public String h;
        public GyCallBack k;
        public boolean j = s.f2026a;
        public boolean i = aj.b;
        public boolean l = true;

        public Builder(Context context) {
            this.f1941a = context;
        }

        public GyConfig build() {
            return new GyConfig(this);
        }

        public Builder callBack(GyCallBack gyCallBack) {
            this.k = gyCallBack;
            return this;
        }

        public Builder channel(String str) {
            this.h = str;
            return this;
        }

        public Builder debug(boolean z) {
            this.i = z;
            return this;
        }

        public Builder eLoginDebug(boolean z) {
            this.j = z;
            return this;
        }

        @Deprecated
        public Builder mobile(String str, String str2) {
            this.d = str;
            this.f1942e = str2;
            return this;
        }

        public Builder preLoginUseCache(boolean z) {
            this.l = z;
            return this;
        }

        @Deprecated
        public Builder telecom(String str, String str2) {
            this.f = str;
            this.g = str2;
            return this;
        }

        @Deprecated
        public Builder unicom(String str, String str2) {
            this.b = str;
            this.c = str2;
            return this;
        }
    }

    public GyConfig(Builder builder) {
        this.f1939a = builder.f1941a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
        this.f1940e = builder.f1942e;
        this.f = builder.f;
        this.g = builder.g;
        this.h = builder.h;
        this.i = builder.i;
        this.j = builder.j;
        this.l = builder.k;
        this.k = builder.l;
    }

    public static Builder with(Context context) {
        return new Builder(context);
    }

    public GyCallBack callBack() {
        return this.l;
    }

    public String channel() {
        return this.h;
    }

    public Context context() {
        return this.f1939a;
    }

    public boolean debug() {
        return this.i;
    }

    public boolean eLoginDebug() {
        return this.j;
    }

    public String mobileAppId() {
        return this.d;
    }

    public String mobileAppKey() {
        return this.f1940e;
    }

    public boolean preLoginUseCache() {
        return this.k;
    }

    public String telecomAppId() {
        return this.f;
    }

    public String telecomAppKey() {
        return this.g;
    }

    public String toString() {
        return "GyConfig{context=" + this.f1939a + ", unicomAppId='" + this.b + Operators.SINGLE_QUOTE + ", unicomAppKey='" + this.c + Operators.SINGLE_QUOTE + ", mobileAppId='" + this.d + Operators.SINGLE_QUOTE + ", mobileAppKey='" + this.f1940e + Operators.SINGLE_QUOTE + ", telecomAppId='" + this.f + Operators.SINGLE_QUOTE + ", telecomAppKey='" + this.g + Operators.SINGLE_QUOTE + ", channel='" + this.h + Operators.SINGLE_QUOTE + ", debug=" + this.i + ", eLoginDebug=" + this.j + ", preLoginUseCache=" + this.k + ", callBack=" + this.l + Operators.BLOCK_END;
    }

    public String unicomAppId() {
        return this.b;
    }

    public String unicomAppKey() {
        return this.c;
    }
}
