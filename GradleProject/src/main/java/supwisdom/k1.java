package supwisdom;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.view.LayoutInflater;
import androidx.appcompat.R;

/* JADX INFO: compiled from: ContextThemeWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class k1 extends ContextWrapper {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f8106a;
    public Resources.Theme b;
    public LayoutInflater c;
    public Configuration d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Resources f8107e;

    public k1() {
        super(null);
    }

    public void a(Configuration configuration) {
        if (this.f8107e != null) {
            throw new IllegalStateException("getResources() or getAssets() has already been called");
        }
        if (this.d != null) {
            throw new IllegalStateException("Override configuration has already been set");
        }
        this.d = new Configuration(configuration);
    }

    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public int b() {
        return this.f8106a;
    }

    public final void c() {
        boolean z = this.b == null;
        if (z) {
            this.b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.b.setTo(theme);
            }
        }
        a(this.b, this.f8106a, z);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return getResources().getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return a();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return getBaseContext().getSystemService(str);
        }
        if (this.c == null) {
            this.c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.b;
        if (theme != null) {
            return theme;
        }
        if (this.f8106a == 0) {
            this.f8106a = R.style.Theme_AppCompat_Light;
        }
        c();
        return this.b;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        if (this.f8106a != i) {
            this.f8106a = i;
            c();
        }
    }

    public k1(Context context, int i) {
        super(context);
        this.f8106a = i;
    }

    public k1(Context context, Resources.Theme theme) {
        super(context);
        this.b = theme;
    }

    public final Resources a() {
        if (this.f8107e == null) {
            Configuration configuration = this.d;
            if (configuration == null) {
                this.f8107e = super.getResources();
            } else if (Build.VERSION.SDK_INT >= 17) {
                this.f8107e = createConfigurationContext(configuration).getResources();
            } else {
                Resources resources = super.getResources();
                Configuration configuration2 = new Configuration(resources.getConfiguration());
                configuration2.updateFrom(this.d);
                this.f8107e = new Resources(resources.getAssets(), resources.getDisplayMetrics(), configuration2);
            }
        }
        return this.f8107e;
    }

    public void a(Resources.Theme theme, int i, boolean z) {
        theme.applyStyle(i, true);
    }
}
