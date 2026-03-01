package com.getui.gtc.dim.b;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Base64;
import com.getui.gtc.dim.AppDataProvider;
import com.getui.gtc.dim.Caller;
import com.getui.gtc.dim.DimRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class g extends f {
    public static final long g = SystemClock.elapsedRealtime();
    public static final Map<String, String> j = new HashMap<String, String>() { // from class: com.getui.gtc.dim.b.g.1
        {
            put("dim-2-1-1-1", "XhNWH0ANTAVL");
            put("dim-2-1-1-3", "XhNWHy4=");
            put("dim-2-1-1-4", "XhNWHy0=");
            put("dim-2-1-2-1", "XhNACVYbWhNd");
            put("dim-2-1-2-3", "XhNACTg=");
            put("dim-2-1-2-4", "XhNACTs=");
            put("dim-2-1-3-1", "WhtY");
            put("dim-2-1-3-2", "WhtYB0YKRg==");
            put("dim-2-1-4-1", "RAFTGlsXSAZTHlwZSw==");
            put("dim-2-1-5-1", "WBlQFA==");
            put("dim-2-1-6-1", "Xh1eF1MMQQBJBw==");
            put("dim-2-1-6-3", "Xh1eF1Ni");
            put("dim-2-1-6-4", "Xh1eF1Nh");
            put("dim-2-1-7-1", "VhhcDkEITBNaHg==");
            put("dim-2-1-8-1", "VhJEAVMHTh1UGl0CSw8=");
            put("dim-2-1-9-1", "VQdGCEw=");
            put("dim-2-1-10-1", "WhVRFFg=");
            put("dim-2-1-11-1", "RQpH");
            put("dim-2-1-12-1", "WhtVAEYHRBBFF1IA");
            put("dim-2-1-13-1", "RB1OEUcCUANKBUs=");
            put("dim-2-1-14-1", "VBVHFVwZSw==");
            put("dim-2-1-15-1", "WRxIH1ACSRZCG0sO");
            put("dim-2-1-16-1", "Xg4=");
            put("dim-2-1-16-2", "Xg5Ybg==");
            put("dim-2-1-17-1", "WxRXFkILRApVEkIR");
            put("dim-2-1-17-2", "WxRXFkILRApVG14KXRJACw==");
            put("dim-2-1-18-1", "QAlPBlkQXhhX");
            put("dim-2-1-18-2", "QAlPBlkKSQhGGVUcTxs=");
            put("dim-2-1-18-3", "QAlPBlkUVRY=");
            put("dim-2-1-19-1", "VBFdEU4HSQ9A");
            put("dim-2-1-19-2", "VBFdEU4HSQ9AH1EUQw==");
            put("dim-2-1-21-1", "VgZWCUUMXws=");
            put("dim-2-1-21-2", "VgZWCUUMXws=");
            put("dim-2-1-21-3", "VgZWCUUMXws=");
            put("dim-2-1-21-5", "VgZWCUUMXws=");
            put("dim-2-1-22-1", "UBVBFF0CRgNVHF8aRQxI");
        }
    };
    public Method h;
    public Method i;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final g f2168a = new g(0);
    }

    public g() {
    }

    public /* synthetic */ g(byte b) {
        this();
    }

    public static /* synthetic */ e a(g gVar, DimRequest dimRequest, boolean z, boolean z2) {
        return gVar.a(dimRequest, z, z2, false);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01e2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.Object a(com.getui.gtc.dim.DimRequest r6) {
        /*
            Method dump skipped, instruction units count: 1006
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.dim.b.g.a(com.getui.gtc.dim.DimRequest):java.lang.Object");
    }

    public static Object a(String str, AppDataProvider appDataProvider) {
        return a(str, appDataProvider, String.class, false, "");
    }

    public static Object a(String str, AppDataProvider appDataProvider, Class<?> cls, boolean z, Object obj) {
        try {
            String str2 = j.get(str);
            String str3 = str2 == null ? null : new String(com.getui.gtc.dim.e.a.a(Base64.decode(str2.getBytes(), 2)));
            if (str3 == null) {
                throw new IllegalStateException("decryptName==null");
            }
            try {
                Object appData = appDataProvider.getAppData(str3);
                if (!z) {
                    return cls.cast(appData);
                }
                List list = (List) appData;
                if (list != null && !list.isEmpty()) {
                    cls.cast(list.get(0));
                    if (list.size() > 1) {
                        cls.cast(list.get(list.size() - 1));
                    }
                }
                return appData;
            } catch (Throwable th) {
                com.getui.gtc.dim.e.b.b("dim call sys getProviderData failed for " + str + ",because " + th.getMessage());
                appDataProvider.onDataFailed(str3, th);
            }
        } catch (Throwable th2) {
            com.getui.gtc.dim.e.b.b("dim call sys getProviderData failed for " + str + ",because " + th2.getMessage());
        }
        return obj;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:113:0x01aa  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object b(java.lang.String r4, com.getui.gtc.dim.AppDataProvider r5) {
        /*
            Method dump skipped, instruction units count: 702
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.dim.b.g.b(java.lang.String, com.getui.gtc.dim.AppDataProvider):java.lang.Object");
    }

    public static g d() {
        return a.f2168a;
    }

    private Object j(String str) {
        try {
            String str2 = this.f;
            if (str2 != null && this.i != null) {
                this.i.invoke(null, str2);
                this.f = null;
            }
        } catch (Throwable th) {
            com.getui.gtc.dim.e.b.b(th);
        }
        try {
            if (this.h != null) {
                Bundle bundle = new Bundle();
                bundle.putString("dimKey", str);
                return this.h.invoke(null, bundle);
            }
        } catch (Throwable th2) {
            com.getui.gtc.dim.e.b.b(th2);
        }
        return Void.TYPE;
    }

    @Override // com.getui.gtc.dim.b.f
    public final /* bridge */ /* synthetic */ int a() {
        return super.a();
    }

    public final e a(DimRequest dimRequest, boolean z, boolean z2, boolean z3) {
        Object objA;
        Object objJ;
        Object objB;
        e eVar = new e("none");
        String key = dimRequest.getKey();
        if ((this.c & 2) != 0) {
            com.getui.gtc.dim.e.b.a(new Throwable("dim get sys trace, key: " + key + ", caller: " + dimRequest.getCaller()));
        }
        AppDataProvider appDataProvider = this.f2164e;
        boolean z4 = appDataProvider != null && a(key);
        com.getui.gtc.dim.e.b.b("dim can call sys for " + key + ", caller: " + dimRequest.getCaller() + ", allowProvider: " + z4 + ", provider: " + appDataProvider + ", gdi:" + z + ", dim:" + z2 + ", hc:" + z3);
        if (z4) {
            if (!z3 && ((z || z2) && (objB = b(key, appDataProvider)) != Void.TYPE)) {
                eVar.f2162a = "app_provider";
                eVar.b = objB;
            }
            return eVar;
        }
        if (z && (objJ = j(key)) != Void.TYPE) {
            eVar.f2162a = "gdi";
            eVar.b = objJ;
        }
        if (z2 && !com.getui.gtc.dim.e.c.a(eVar.b) && (objA = a(dimRequest)) != Void.TYPE) {
            eVar.f2162a = "sys";
            eVar.b = objA;
        }
        return eVar;
    }

    @Override // com.getui.gtc.dim.b.f
    public final /* bridge */ /* synthetic */ void a(int i) {
        super.a(i);
    }

    @Override // com.getui.gtc.dim.b.f
    public final /* bridge */ /* synthetic */ void a(AppDataProvider appDataProvider) {
        super.a(appDataProvider);
    }

    @Override // com.getui.gtc.dim.b.f
    public final /* bridge */ /* synthetic */ void a(Caller caller) {
        super.a(caller);
    }

    @Override // com.getui.gtc.dim.b.f
    public final /* bridge */ /* synthetic */ void a(String str, int i) {
        super.a(str, i);
    }

    @Override // com.getui.gtc.dim.b.f
    public final /* bridge */ /* synthetic */ boolean a(String str, Caller caller, boolean z) {
        return super.a(str, caller, z);
    }

    @Override // com.getui.gtc.dim.b.f
    public final /* bridge */ /* synthetic */ boolean a(String str, String str2) {
        return super.a(str, str2);
    }

    @Override // com.getui.gtc.dim.b.f
    public final /* bridge */ /* synthetic */ Boolean b(String str, String str2) {
        return super.b(str, str2);
    }

    @Override // com.getui.gtc.dim.b.f
    public final /* bridge */ /* synthetic */ void b(int i) {
        super.b(i);
    }

    @Override // com.getui.gtc.dim.b.f
    public final /* bridge */ /* synthetic */ void b(String str, int i) {
        super.b(str, i);
    }

    @Override // com.getui.gtc.dim.b.f
    public final /* bridge */ /* synthetic */ boolean b(String str, Caller caller, boolean z) {
        return super.b(str, caller, z);
    }

    @Override // com.getui.gtc.dim.b.f
    public final /* bridge */ /* synthetic */ int c() {
        return super.c();
    }

    @Override // com.getui.gtc.dim.b.f
    public final /* bridge */ /* synthetic */ void c(int i) {
        super.c(i);
    }

    @Override // com.getui.gtc.dim.b.f
    public final /* bridge */ /* synthetic */ void c(String str) {
        super.c(str);
    }

    @Override // com.getui.gtc.dim.b.f
    public final /* bridge */ /* synthetic */ void c(String str, int i) {
        super.c(str, i);
    }

    @Override // com.getui.gtc.dim.b.f
    public final /* bridge */ /* synthetic */ void c(String str, String str2) {
        super.c(str, str2);
    }

    @Override // com.getui.gtc.dim.b.f
    public final /* bridge */ /* synthetic */ void d(String str, String str2) {
        super.d(str, str2);
    }

    @Override // com.getui.gtc.dim.b.f
    public final /* bridge */ /* synthetic */ void e(String str, String str2) {
        super.e(str, str2);
    }

    @Override // com.getui.gtc.dim.b.f
    public final /* bridge */ /* synthetic */ void f(String str) {
        super.f(str);
    }

    @Override // com.getui.gtc.dim.b.f
    public final /* bridge */ /* synthetic */ void f(String str, String str2) {
        super.f(str, str2);
    }

    @Override // com.getui.gtc.dim.b.f
    public final /* bridge */ /* synthetic */ void g(String str, String str2) {
        super.g(str, str2);
    }
}
