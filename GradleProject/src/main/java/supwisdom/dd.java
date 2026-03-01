package supwisdom;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

/* JADX INFO: compiled from: FragmentManager.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class dd {
    public static final bd b = new bd();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public bd f7325a = null;

    /* JADX INFO: compiled from: FragmentManager.java */
    public static abstract class a {
        public abstract void a(dd ddVar, Fragment fragment);

        public abstract void a(dd ddVar, Fragment fragment, Context context);

        public abstract void a(dd ddVar, Fragment fragment, Bundle bundle);

        public abstract void a(dd ddVar, Fragment fragment, View view, Bundle bundle);

        public abstract void b(dd ddVar, Fragment fragment);

        public abstract void b(dd ddVar, Fragment fragment, Context context);

        public abstract void b(dd ddVar, Fragment fragment, Bundle bundle);

        public abstract void c(dd ddVar, Fragment fragment);

        public abstract void c(dd ddVar, Fragment fragment, Bundle bundle);

        public abstract void d(dd ddVar, Fragment fragment);

        public abstract void d(dd ddVar, Fragment fragment, Bundle bundle);

        public abstract void e(dd ddVar, Fragment fragment);

        public abstract void f(dd ddVar, Fragment fragment);

        public abstract void g(dd ddVar, Fragment fragment);
    }

    /* JADX INFO: compiled from: FragmentManager.java */
    public interface b {
        void a();
    }

    public abstract Fragment.SavedState a(Fragment fragment);

    public abstract Fragment a(Bundle bundle, String str);

    public abstract Fragment a(String str);

    public abstract hd a();

    public abstract void a(int i, int i2);

    public abstract void a(Bundle bundle, String str, Fragment fragment);

    public abstract void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public void a(bd bdVar) {
        this.f7325a = bdVar;
    }

    public abstract boolean b();

    public bd c() {
        if (this.f7325a == null) {
            this.f7325a = b;
        }
        return this.f7325a;
    }

    public abstract List<Fragment> d();

    public abstract boolean e();
}
