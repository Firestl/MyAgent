package com.vivo.push.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Message;
import android.text.TextUtils;
import com.vivo.push.q;
import com.vivo.push.util.ContextDelegate;
import com.vivo.push.util.o;
import com.vivo.push.util.s;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: CommandWorker.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a extends q {
    public static a c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final List<Integer> f5627e = Arrays.asList(3);
    public String d;
    public String f = "";
    public long g;

    public static synchronized a a() {
        if (c == null) {
            c = new a();
        }
        return c;
    }

    public final void b() {
        this.d = null;
    }

    @Override // com.vivo.push.q
    public final void b(Message message) {
        String stringExtra;
        Intent intent = (Intent) message.obj;
        if (intent == null || this.f5623a == null) {
            o.d("CommandWorker", " handleMessage error: intent : " + intent + ", mContext: " + this.f5623a);
            return;
        }
        if (!TextUtils.isEmpty(this.f) && this.f.contains("CommandService") && !b(intent)) {
            o.a("CommandWorker", " !checkIntentIsSecurity(intent)");
            return;
        }
        String packageName = this.f5623a.getPackageName();
        try {
            stringExtra = intent.getStringExtra("command_type");
        } catch (Exception e2) {
            o.a("CommandWorker", e2);
        }
        if (!TextUtils.isEmpty(stringExtra) && stringExtra.equals("reflect_receiver")) {
            int intExtra = intent.getIntExtra("command", -1);
            if (intExtra < 0) {
                intExtra = intent.getIntExtra("method", -1);
            }
            if (f5627e.contains(Integer.valueOf(intExtra)) && s.c(this.f5623a, packageName) && !s.c(this.f5623a)) {
                o.a("CommandWorker", "METHOD_ON_MESSAGE is not support");
                return;
            }
            String action = intent.getAction();
            if (TextUtils.isEmpty(this.d)) {
                String strA = a(this.f5623a, packageName, action);
                this.d = strA;
                if (TextUtils.isEmpty(strA)) {
                    o.d("CommandWorker", " reflectReceiver error: receiver for: " + action + " not found, package: " + packageName);
                    intent.setPackage(packageName);
                    this.f5623a.sendBroadcast(intent);
                    return;
                }
            }
            try {
                Class<?> cls = Class.forName(this.d);
                Object objNewInstance = cls.getConstructor(new Class[0]).newInstance(new Object[0]);
                Method method = cls.getMethod("onReceive", Context.class, Intent.class);
                intent.setClassName(packageName, this.d);
                method.invoke(objNewInstance, ContextDelegate.getContext(this.f5623a).getApplicationContext(), intent);
                return;
            } catch (Exception e3) {
                o.b("CommandWorker", "reflect e: ", e3);
                return;
            }
        }
        o.a("CommandWorker", "commandTypeStr is not satisfy == ".concat(String.valueOf(stringExtra)));
    }

    public final void a(String str) {
        this.f = str;
    }

    public final void a(Intent intent) {
        if (intent != null && this.f5623a != null) {
            Message messageObtain = Message.obtain();
            messageObtain.obj = intent;
            a(messageObtain);
        } else {
            o.d("CommandWorker", " sendMessage error: intent : " + intent + ", mContext: " + this.f5623a);
        }
    }

    public static String a(Context context, String str, String str2) {
        List<ResolveInfo> listQueryBroadcastReceivers;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        Intent intent = new Intent(str2);
        intent.setPackage(str);
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null || (listQueryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 64)) == null || listQueryBroadcastReceivers.size() <= 0) {
                return null;
            }
            return listQueryBroadcastReceivers.get(0).activityInfo.name;
        } catch (Exception e2) {
            o.a("CommandWorker", "error  " + e2.getMessage());
            return null;
        }
    }

    private boolean b(Intent intent) {
        if (intent == null) {
            return false;
        }
        PackageManager packageManager = this.f5623a.getPackageManager();
        if (this.g <= 0) {
            try {
                if (packageManager.getPackageInfo("com.vivo.pushservice", 0) != null) {
                    this.g = r1.versionCode;
                    o.a("CommandWorker", "push service version:" + this.g);
                }
            } catch (Exception e2) {
                o.a("CommandWorker", "getPackageInfo exception:" + e2.getMessage());
            }
        }
        if (this.g < 4040000) {
            return true;
        }
        String stringExtra = intent.getStringExtra("security_avoid_pull");
        if (TextUtils.isEmpty(stringExtra)) {
            o.a("CommandWorker", "checkIntentIsSecurityTextUtils.isEmpty");
            return false;
        }
        try {
            String strB = com.vivo.push.util.a.a(this.f5623a).b(stringExtra);
            if ("com.vivo.pushservice".equals(strB)) {
                return true;
            }
            o.a("CommandWorker", "!decrypt.equals, so decrypt == ".concat(String.valueOf(strB)));
            return false;
        } catch (Exception e3) {
            o.a("CommandWorker", "checkIntentIsSecurity Exception: " + e3.getMessage());
            return false;
        }
    }
}
