package supwisdom;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: compiled from: TintContextWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class m3 extends ContextWrapper {
    public static final Object c = new Object();
    public static ArrayList<WeakReference<m3>> d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Resources f8349a;
    public final Resources.Theme b;

    public m3(Context context) {
        super(context);
        if (!u3.b()) {
            this.f8349a = new o3(this, context.getResources());
            this.b = null;
            return;
        }
        u3 u3Var = new u3(this, context.getResources());
        this.f8349a = u3Var;
        Resources.Theme themeNewTheme = u3Var.newTheme();
        this.b = themeNewTheme;
        themeNewTheme.setTo(context.getTheme());
    }

    public static boolean a(Context context) {
        if ((context instanceof m3) || (context.getResources() instanceof o3) || (context.getResources() instanceof u3)) {
            return false;
        }
        return Build.VERSION.SDK_INT < 21 || u3.b();
    }

    public static Context b(Context context) {
        if (!a(context)) {
            return context;
        }
        synchronized (c) {
            if (d == null) {
                d = new ArrayList<>();
            } else {
                for (int size = d.size() - 1; size >= 0; size--) {
                    WeakReference<m3> weakReference = d.get(size);
                    if (weakReference == null || weakReference.get() == null) {
                        d.remove(size);
                    }
                }
                for (int size2 = d.size() - 1; size2 >= 0; size2--) {
                    WeakReference<m3> weakReference2 = d.get(size2);
                    m3 m3Var = weakReference2 != null ? weakReference2.get() : null;
                    if (m3Var != null && m3Var.getBaseContext() == context) {
                        return m3Var;
                    }
                }
            }
            m3 m3Var2 = new m3(context);
            d.add(new WeakReference<>(m3Var2));
            return m3Var2;
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return this.f8349a.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return this.f8349a;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.b;
        return theme == null ? super.getTheme() : theme;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        Resources.Theme theme = this.b;
        if (theme == null) {
            super.setTheme(i);
        } else {
            theme.applyStyle(i, true);
        }
    }
}
