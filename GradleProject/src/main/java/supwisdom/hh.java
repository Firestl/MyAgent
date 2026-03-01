package supwisdom;

import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: ViewUtilsApi21.java */
/* JADX INFO: loaded from: classes.dex */
public class hh extends gh {
    public static Method f;
    public static boolean g;
    public static Method h;
    public static boolean i;
    public static Method j;
    public static boolean k;

    @Override // supwisdom.jh
    public void a(View view, Matrix matrix) {
        c();
        Method method = j;
        if (method != null) {
            try {
                method.invoke(view, matrix);
            } catch (IllegalAccessException e2) {
                throw new RuntimeException(e2.getCause());
            } catch (InvocationTargetException unused) {
            }
        }
    }

    @Override // supwisdom.jh
    public void b(View view, Matrix matrix) {
        d();
        Method method = f;
        if (method != null) {
            try {
                method.invoke(view, matrix);
            } catch (IllegalAccessException unused) {
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        }
    }

    @Override // supwisdom.jh
    public void c(View view, Matrix matrix) {
        e();
        Method method = h;
        if (method != null) {
            try {
                method.invoke(view, matrix);
            } catch (IllegalAccessException unused) {
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        }
    }

    public final void d() {
        if (g) {
            return;
        }
        try {
            Method declaredMethod = View.class.getDeclaredMethod("transformMatrixToGlobal", Matrix.class);
            f = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (NoSuchMethodException e2) {
            Log.i("ViewUtilsApi21", "Failed to retrieve transformMatrixToGlobal method", e2);
        }
        g = true;
    }

    public final void e() {
        if (i) {
            return;
        }
        try {
            Method declaredMethod = View.class.getDeclaredMethod("transformMatrixToLocal", Matrix.class);
            h = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (NoSuchMethodException e2) {
            Log.i("ViewUtilsApi21", "Failed to retrieve transformMatrixToLocal method", e2);
        }
        i = true;
    }

    public final void c() {
        if (k) {
            return;
        }
        try {
            Method declaredMethod = View.class.getDeclaredMethod("setAnimationMatrix", Matrix.class);
            j = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (NoSuchMethodException e2) {
            Log.i("ViewUtilsApi21", "Failed to retrieve setAnimationMatrix method", e2);
        }
        k = true;
    }
}
