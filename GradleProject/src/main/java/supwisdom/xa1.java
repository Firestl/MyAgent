package supwisdom;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import io.dcloud.common.util.ReflectUtils;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class xa1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final PackageManager f9727a;
    public final ComponentName b;
    public final ComponentName c;
    public final Map<Intent.FilterComparison, ResolveInfo> d = new HashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Map<va1, ResolveInfo> f9728e = new HashMap();

    public xa1(Context context, wa1 wa1Var) {
        context.getClassLoader();
        this.f9727a = context.getPackageManager();
        ComponentName componentNameC = c();
        this.b = componentNameC;
        ComponentName componentNameB = b();
        this.c = componentNameB;
        try {
            t91.c("ShareRestrictionManager", "ResolverActivityComp: " + componentNameC + "\nDocumentsActivityComp: " + componentNameB + "\nChooserActivityAction: " + a() + "\nUseParceledListSlice: " + d());
        } catch (Throwable th) {
            t91.a("ShareRestrictionManager", "ShareRestrictionManager error", th);
        }
    }

    public ResolveInfo a(Intent intent) {
        t91.c("ShareRestrictionManager", "getLastChoosenResolveInfo: " + intent);
        return this.d.get(new Intent.FilterComparison(intent));
    }

    public void b(Intent intent, ResolveInfo resolveInfo) {
        t91.c("ShareRestrictionManager", "updateLastChoosenResolveInfo: " + intent);
        this.d.put(new Intent.FilterComparison(intent), resolveInfo);
    }

    public final ComponentName c() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        return intent.resolveActivity(this.f9727a);
    }

    public final boolean d() {
        Method declaredMethod;
        if (ea1.f7460a == null) {
            return false;
        }
        Class<?> cls = null;
        try {
            cls = Class.forName(ReflectUtils.CLASSNAME_IPACKAGEMANAGER);
            if (Build.VERSION.SDK_INT <= 31) {
                Class<?> cls2 = Integer.TYPE;
                declaredMethod = cls.getDeclaredMethod("queryIntentActivities", Intent.class, String.class, cls2, cls2);
            } else {
                declaredMethod = cls.getDeclaredMethod("queryIntentActivities", Intent.class, String.class, Long.TYPE, Integer.TYPE);
            }
            return ea1.f7460a.isAssignableFrom(declaredMethod.getReturnType());
        } catch (Exception e2) {
            t91.b("shouldUseParceledListSlice error", e2.toString());
            return a(cls);
        }
    }

    public final String a() {
        Intent intentCreateChooser = Intent.createChooser(new Intent("android.intent.action.MAIN"), "");
        if (intentCreateChooser != null) {
            return intentCreateChooser.getAction();
        }
        return null;
    }

    public final ComponentName b() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("*/*");
        return intent.resolveActivity(this.f9727a);
    }

    public void a(Intent intent, ResolveInfo resolveInfo) {
        this.f9728e.put(new va1(intent), resolveInfo);
    }

    public final boolean a(Class<?> cls) {
        if (cls == null) {
            return false;
        }
        for (Method method : cls.getMethods()) {
            if (method.getName().equalsIgnoreCase("queryIntentActivities")) {
                return ea1.f7460a.isAssignableFrom(method.getReturnType());
            }
        }
        return false;
    }
}
