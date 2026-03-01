package com.igexin.d;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import com.igexin.push.b.b;
import com.igexin.push.g.c;
import com.igexin.push.g.d;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushService;
import com.tencent.stat.DeviceInfo;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class a implements InvocationHandler {
    public static String b = "ZxExecutor";
    public static volatile a c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f3209a;

    /* JADX INFO: renamed from: com.igexin.d.a$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        public AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                if (a.c(a.this.f3209a)) {
                    a.a(a.this, a.this.f3209a);
                }
            } catch (Throwable th) {
                com.igexin.c.a.c.a.a(th);
            }
        }
    }

    /* JADX INFO: renamed from: com.igexin.d.a$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f3211a;
        public final /* synthetic */ String b;
        public final /* synthetic */ String c;

        public AnonymousClass2(Context context, String str, String str2) {
            this.f3211a = context;
            this.b = str;
            this.c = str2;
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                Class cls = (Class) d.a(this.f3211a, PushService.class).second;
                if (cls != null) {
                    Intent intent = new Intent(this.f3211a, (Class<?>) cls);
                    intent.putExtra("action", PushConsts.ACTION_BROADCAST_UPLOAD_TYPE253);
                    intent.putExtra("id", this.b);
                    intent.putExtra(DeviceInfo.TAG_ANDROID_ID, this.c);
                    c.a(this.f3211a, intent);
                }
            } catch (Throwable th) {
                com.igexin.c.a.c.a.a(th);
            }
        }
    }

    public static a a() {
        if (c == null) {
            synchronized (a.class) {
                if (c == null) {
                    a aVar = new a();
                    c = aVar;
                    return aVar;
                }
            }
        }
        return c;
    }

    public static /* synthetic */ void a(a aVar, Context context) {
        try {
            Class<?> cls = Class.forName("com.zx.sdk.api.ZXManager");
            Object objInvoke = cls.getDeclaredMethod("newSDK", String.class).invoke(cls, com.igexin.push.a.r);
            Method declaredMethod = objInvoke.getClass().getDeclaredMethod("init", Context.class);
            Method declaredMethod2 = objInvoke.getClass().getDeclaredMethod("allowPermissionDialog", Boolean.TYPE);
            declaredMethod.invoke(objInvoke, context);
            declaredMethod2.invoke(objInvoke, Boolean.FALSE);
            Class<?> cls2 = Class.forName("com.zx.sdk.api.ZXIDListener");
            objInvoke.getClass().getDeclaredMethod("getZXID", cls2).invoke(objInvoke, Proxy.newProxyInstance(a.class.getClassLoader(), new Class[]{cls2}, aVar));
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
        }
    }

    private void a(String str, String str2, Context context) {
        com.igexin.b.a.a().f3132a.execute(new AnonymousClass2(context, str, str2));
    }

    private void b(Context context) {
        this.f3209a = context.getApplicationContext();
        com.igexin.b.a.a().b().schedule(new AnonymousClass1(), 2000L, TimeUnit.MILLISECONDS);
    }

    public static boolean c(Context context) throws Throwable {
        b bVar;
        Cursor cursorA = null;
        try {
            bVar = new b(context);
        } catch (Throwable th) {
            th = th;
            bVar = null;
        }
        try {
            cursorA = bVar.a(com.igexin.push.core.b.Y, new String[]{"value"}, "id = 79");
            if (cursorA == null || !cursorA.moveToFirst()) {
                if (cursorA != null) {
                    try {
                        cursorA.close();
                    } catch (Throwable th2) {
                        com.igexin.c.a.c.a.a(th2);
                    }
                }
                bVar.close();
                return false;
            }
            boolean z = Boolean.parseBoolean(cursorA.getString(0));
            if (cursorA != null) {
                try {
                    cursorA.close();
                } catch (Throwable th3) {
                    com.igexin.c.a.c.a.a(th3);
                }
            }
            bVar.close();
            return z;
        } catch (Throwable th4) {
            th = th4;
            if (cursorA != null) {
                try {
                    cursorA.close();
                } catch (Throwable th5) {
                    com.igexin.c.a.c.a.a(th5);
                    throw th;
                }
            }
            if (bVar != null) {
                bVar.close();
            }
            throw th;
        }
    }

    private void d(Context context) {
        try {
            Class<?> cls = Class.forName("com.zx.sdk.api.ZXManager");
            Object objInvoke = cls.getDeclaredMethod("newSDK", String.class).invoke(cls, com.igexin.push.a.r);
            Method declaredMethod = objInvoke.getClass().getDeclaredMethod("init", Context.class);
            Method declaredMethod2 = objInvoke.getClass().getDeclaredMethod("allowPermissionDialog", Boolean.TYPE);
            declaredMethod.invoke(objInvoke, context);
            declaredMethod2.invoke(objInvoke, Boolean.FALSE);
            Class<?> cls2 = Class.forName("com.zx.sdk.api.ZXIDListener");
            objInvoke.getClass().getDeclaredMethod("getZXID", cls2).invoke(objInvoke, Proxy.newProxyInstance(a.class.getClassLoader(), new Class[]{cls2}, this));
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(th);
        }
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        try {
            String name = method.getName();
            byte b2 = -1;
            int iHashCode = name.hashCode();
            if (iHashCode != -530890460) {
                if (iHashCode == 1116433148 && name.equals("onFailed")) {
                    b2 = 1;
                }
            } else if (name.equals("onSuccess")) {
                b2 = 0;
            }
            if (b2 == 0) {
                Object obj2 = objArr[0];
                com.igexin.c.a.c.a.b(b, " get zxid success ".concat(String.valueOf(obj2)));
                JSONObject jSONObject = (JSONObject) Class.forName("com.zx.sdk.api.ZXID").getDeclaredMethod("getAids", new Class[0]).invoke(obj2, new Object[0]);
                com.igexin.b.a.a().f3132a.execute(new AnonymousClass2(this.f3209a, obj2.toString(), jSONObject instanceof JSONObject ? jSONObject.optString("venderAid", "") : ""));
                return null;
            }
            if (b2 != 1) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            for (Object obj3 : objArr) {
                sb.append(obj3);
                sb.append(",");
            }
            com.igexin.c.a.c.a.a("ZxExecutor | ", " get zxid failed code  msg = ".concat(String.valueOf(sb)));
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}
