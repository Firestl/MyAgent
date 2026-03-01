package com.cmic.gen.sdk.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import com.cmic.gen.sdk.a.b;
import com.cmic.gen.sdk.e.k;

/* JADX INFO: loaded from: classes.dex */
public class c implements b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @SuppressLint({"StaticFieldLeak"})
    public static c f1665a;
    public a b;
    public a c;
    public b d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Context f1666e;

    public c(Context context) {
        this.f1666e = context;
        b();
    }

    public static c a(Context context) {
        if (f1665a == null) {
            synchronized (c.class) {
                if (f1665a == null) {
                    f1665a = new c(context);
                }
            }
        }
        return f1665a;
    }

    private void b() {
        String strB = k.b("sdk_config_version", "");
        if (TextUtils.isEmpty(strB) || !com.cmic.gen.sdk.auth.c.SDK_VERSION.equals(strB)) {
            b bVarA = b.a(true);
            this.d = bVarA;
            this.b = bVarA.a();
            if (!TextUtils.isEmpty(strB)) {
                c();
            }
        } else {
            b bVarA2 = b.a(false);
            this.d = bVarA2;
            this.b = bVarA2.b();
        }
        this.d.a(this);
        this.c = this.d.a();
    }

    private void c() {
        com.cmic.gen.sdk.e.c.b("UmcConfigManager", "delete localConfig");
        this.d.c();
    }

    public a a() {
        try {
            return this.b.clone();
        } catch (CloneNotSupportedException unused) {
            return this.c;
        }
    }

    @Override // com.cmic.gen.sdk.a.b.a
    public void a(a aVar) {
        this.b = aVar;
    }

    public void a(com.cmic.gen.sdk.a aVar) {
        this.d.a(aVar);
    }
}
