package supwisdom;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatDelegateImpl;
import androidx.appcompat.widget.Toolbar;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import supwisdom.i1;

/* JADX INFO: compiled from: AppCompatDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f9224a = -100;
    public static final k4<WeakReference<t0>> b = new k4<>();
    public static final Object c = new Object();

    public static t0 a(Activity activity, s0 s0Var) {
        return new AppCompatDelegateImpl(activity, s0Var);
    }

    public static void c(t0 t0Var) {
        synchronized (c) {
            Iterator<WeakReference<t0>> it = b.iterator();
            while (it.hasNext()) {
                t0 t0Var2 = it.next().get();
                if (t0Var2 == t0Var || t0Var2 == null) {
                    it.remove();
                }
            }
        }
    }

    public static int k() {
        return f9224a;
    }

    public abstract <T extends View> T a(int i);

    public abstract i1 a(i1.a aVar);

    public abstract q0 a();

    @Deprecated
    public void a(Context context) {
    }

    public abstract void a(Configuration configuration);

    public abstract void a(Bundle bundle);

    public abstract void a(View view);

    public abstract void a(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void a(Toolbar toolbar);

    public abstract void a(CharSequence charSequence);

    public int b() {
        return -100;
    }

    public Context b(Context context) {
        a(context);
        return context;
    }

    public abstract void b(Bundle bundle);

    public abstract void b(View view, ViewGroup.LayoutParams layoutParams);

    public abstract boolean b(int i);

    public abstract MenuInflater c();

    public abstract void c(int i);

    public abstract void c(Bundle bundle);

    public abstract ActionBar d();

    public void d(int i) {
    }

    public abstract void e();

    public abstract void f();

    public abstract void g();

    public abstract void h();

    public abstract void i();

    public abstract void j();

    public static t0 a(Dialog dialog, s0 s0Var) {
        return new AppCompatDelegateImpl(dialog, s0Var);
    }

    public static void b(t0 t0Var) {
        synchronized (c) {
            c(t0Var);
        }
    }

    public static void a(t0 t0Var) {
        synchronized (c) {
            c(t0Var);
            b.add(new WeakReference<>(t0Var));
        }
    }
}
