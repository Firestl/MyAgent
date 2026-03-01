package supwisdom;

import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: GhostViewApi21.java */
/* JADX INFO: loaded from: classes.dex */
public class dg implements eg {
    public static Class<?> b;
    public static boolean c;
    public static Method d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f7334e;
    public static Method f;
    public static boolean g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f7335a;

    public dg(View view) {
        this.f7335a = view;
    }

    public static eg a(View view, ViewGroup viewGroup, Matrix matrix) {
        a();
        Method method = d;
        if (method != null) {
            try {
                return new dg((View) method.invoke(null, view, viewGroup, matrix));
            } catch (IllegalAccessException unused) {
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        }
        return null;
    }

    public static void b() {
        if (c) {
            return;
        }
        try {
            b = Class.forName("android.view.GhostView");
        } catch (ClassNotFoundException e2) {
            Log.i("GhostViewApi21", "Failed to retrieve GhostView class", e2);
        }
        c = true;
    }

    public static void c() {
        if (g) {
            return;
        }
        try {
            b();
            Method declaredMethod = b.getDeclaredMethod("removeGhost", View.class);
            f = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (NoSuchMethodException e2) {
            Log.i("GhostViewApi21", "Failed to retrieve removeGhost method", e2);
        }
        g = true;
    }

    @Override // supwisdom.eg
    public void a(ViewGroup viewGroup, View view) {
    }

    @Override // supwisdom.eg
    public void setVisibility(int i) {
        this.f7335a.setVisibility(i);
    }

    public static void a(View view) {
        c();
        Method method = f;
        if (method != null) {
            try {
                method.invoke(null, view);
            } catch (IllegalAccessException unused) {
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        }
    }

    public static void a() {
        if (f7334e) {
            return;
        }
        try {
            b();
            Method declaredMethod = b.getDeclaredMethod("addGhost", View.class, ViewGroup.class, Matrix.class);
            d = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (NoSuchMethodException e2) {
            Log.i("GhostViewApi21", "Failed to retrieve addGhost method", e2);
        }
        f7334e = true;
    }
}
