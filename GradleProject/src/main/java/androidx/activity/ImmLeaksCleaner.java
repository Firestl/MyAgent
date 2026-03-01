package androidx.activity;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.lifecycle.Lifecycle;
import java.lang.reflect.Field;
import supwisdom.vd;
import supwisdom.xd;

/* JADX INFO: loaded from: classes.dex */
public final class ImmLeaksCleaner implements vd {
    public static int b;
    public static Field c;
    public static Field d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Field f1076e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Activity f1077a;

    public ImmLeaksCleaner(Activity activity) {
        this.f1077a = activity;
    }

    @Override // supwisdom.vd
    public void a(xd xdVar, Lifecycle.Event event) {
        if (event != Lifecycle.Event.ON_DESTROY) {
            return;
        }
        if (b == 0) {
            a();
        }
        if (b == 1) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.f1077a.getSystemService("input_method");
            try {
                Object obj = c.get(inputMethodManager);
                if (obj == null) {
                    return;
                }
                synchronized (obj) {
                    try {
                        try {
                            View view = (View) d.get(inputMethodManager);
                            if (view == null) {
                                return;
                            }
                            if (view.isAttachedToWindow()) {
                                return;
                            }
                            try {
                                f1076e.set(inputMethodManager, null);
                                inputMethodManager.isActive();
                            } catch (IllegalAccessException unused) {
                            }
                        } catch (ClassCastException unused2) {
                        } catch (IllegalAccessException unused3) {
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            } catch (IllegalAccessException unused4) {
            }
        }
    }

    public static void a() {
        try {
            b = 2;
            Field declaredField = InputMethodManager.class.getDeclaredField("mServedView");
            d = declaredField;
            declaredField.setAccessible(true);
            Field declaredField2 = InputMethodManager.class.getDeclaredField("mNextServedView");
            f1076e = declaredField2;
            declaredField2.setAccessible(true);
            Field declaredField3 = InputMethodManager.class.getDeclaredField("mH");
            c = declaredField3;
            declaredField3.setAccessible(true);
            b = 1;
        } catch (NoSuchFieldException unused) {
        }
    }
}
