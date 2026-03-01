package com.getui.gtc.g;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import com.getui.gtc.BuildConfig;
import com.huawei.hms.framework.network.grs.local.model.CountryCodeBean;
import com.igexin.push.GtPushInterface;
import com.igexin.push.core.g;
import com.umeng.analytics.pro.bm;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Map<String, ClassLoader> f2229a = new HashMap();

    public static ClassLoader a(Bundle bundle) {
        return f2229a.get(bundle.getString(CountryCodeBean.SPECIAL_COUNTRYCODE_CN));
    }

    public static void a(Context context, ClassLoader classLoader, String str, String str2, String str3) throws Throwable {
        Class<?> clsLoadClass = classLoader.loadClass(str);
        com.getui.gtc.i.c.a.a("start load for class:" + str + ", appId: " + str2 + ", cid: " + str3);
        clsLoadClass.getDeclaredMethod("onCreate", Context.class, String.class, String.class).invoke(clsLoadClass.newInstance(), context, str2, str3);
        com.getui.gtc.i.c.a.a("load success for class:".concat(String.valueOf(str)));
        f2229a.put(str, classLoader);
    }

    public static void a(Context context, String str, String str2, String str3) throws Throwable {
        a(context, context.getClassLoader(), str, str2, str3);
    }

    public static void a(Context context, String str, String str2, String str3, String str4, String str5) throws Throwable {
        Class<?> cls = Class.forName("com.getui.gtc.SdkLoader");
        Method declaredMethod = cls.getDeclaredMethod("load", Context.class, String.class, String.class, String.class, String.class, String.class);
        declaredMethod.setAccessible(true);
        declaredMethod.invoke(cls, context, str, str2, str3, str4, str5);
    }

    public static void a(Context context, String str, String str2, String str3, String str4, String str5, com.getui.gtc.g.a.b bVar) {
        com.getui.gtc.g.a.a aVar = new com.getui.gtc.g.a.a(bVar);
        if (aVar.b != null) {
            aVar.a(str, str2, str3, str4, str5);
            return;
        }
        try {
            Class<?> cls = (Class) com.getui.gtc.g.a.a.a(context, Class.forName(g.f)).second;
            if (cls == null) {
                cls = Class.forName(g.f);
            }
            Intent intent = new Intent(context, cls);
            intent.setType(BuildConfig.VERSION_NAME);
            context.bindService(intent, new ServiceConnection() { // from class: com.getui.gtc.g.a.a.2

                /* JADX INFO: renamed from: a */
                public final /* synthetic */ String f2227a;
                public final /* synthetic */ String b;
                public final /* synthetic */ String c;
                public final /* synthetic */ String d;

                /* JADX INFO: renamed from: e */
                public final /* synthetic */ String f2228e;

                public AnonymousClass2(String str6, String str22, String str32, String str42, String str52) {
                    str = str6;
                    str = str22;
                    str = str32;
                    str = str42;
                    str = str52;
                }

                @Override // android.content.ServiceConnection
                public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    a.this.b = GtPushInterface.Stub.asInterface(iBinder);
                    a.this.a(str, str, str, str, str);
                }

                @Override // android.content.ServiceConnection
                public final void onServiceDisconnected(ComponentName componentName) {
                    a.this.b = null;
                }
            }, 1);
        } catch (Exception e2) {
            com.getui.gtc.i.c.a.b(e2);
        }
    }

    public static boolean a(Context context, Bundle bundle) {
        try {
            String string = bundle.getString("dp");
            String string2 = bundle.getString(bm.aA);
            String string3 = bundle.getString("gd");
            String string4 = bundle.getString("od");
            String string5 = bundle.getString(CountryCodeBean.SPECIAL_COUNTRYCODE_CN);
            if (string == null) {
                a(context, string5, string2, string3);
                return true;
            }
            a(context, string, string4, string5, string2, string3);
            return true;
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.c(th.toString());
            return false;
        }
    }
}
