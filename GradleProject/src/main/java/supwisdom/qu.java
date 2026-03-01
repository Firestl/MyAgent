package supwisdom;

import android.os.Build;
import android.view.View;
import android.view.ViewParent;

/* JADX INFO: loaded from: classes.dex */
public class qu {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f8953a;

    public static class a extends e {
    }

    public interface b {
        void a(ViewParent viewParent, View view);

        void a(ViewParent viewParent, View view, int i, int i2, int i3, int i4);

        void a(ViewParent viewParent, View view, int i, int i2, int[] iArr);

        boolean a(ViewParent viewParent, View view, float f, float f2);

        boolean a(ViewParent viewParent, View view, float f, float f2, boolean z);

        boolean a(ViewParent viewParent, View view, View view2, int i);

        void b(ViewParent viewParent, View view, View view2, int i);
    }

    public static class c extends a {
    }

    public static class d extends c {
        @Override // supwisdom.qu.e, supwisdom.qu.b
        public boolean a(ViewParent viewParent, View view, float f, float f2, boolean z) {
            return ru.a(viewParent, view, f, f2, z);
        }

        @Override // supwisdom.qu.e, supwisdom.qu.b
        public void b(ViewParent viewParent, View view, View view2, int i) {
            ru.a(viewParent, view, view2, i);
        }

        @Override // supwisdom.qu.e, supwisdom.qu.b
        public boolean a(ViewParent viewParent, View view, float f, float f2) {
            return ru.a(viewParent, view, f, f2);
        }

        @Override // supwisdom.qu.e, supwisdom.qu.b
        public void a(ViewParent viewParent, View view, int i, int i2, int[] iArr) {
            ru.a(viewParent, view, i, i2, iArr);
        }

        @Override // supwisdom.qu.e, supwisdom.qu.b
        public void a(ViewParent viewParent, View view, int i, int i2, int i3, int i4) {
            ru.a(viewParent, view, i, i2, i3, i4);
        }

        @Override // supwisdom.qu.e, supwisdom.qu.b
        public boolean a(ViewParent viewParent, View view, View view2, int i) {
            return ru.b(viewParent, view, view2, i);
        }

        @Override // supwisdom.qu.e, supwisdom.qu.b
        public void a(ViewParent viewParent, View view) {
            ru.a(viewParent, view);
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            f8953a = new d();
            return;
        }
        if (i >= 19) {
            f8953a = new c();
        } else if (i >= 14) {
            f8953a = new a();
        } else {
            f8953a = new e();
        }
    }

    public static boolean a(ViewParent viewParent, View view, float f, float f2, boolean z) {
        return f8953a.a(viewParent, view, f, f2, z);
    }

    public static boolean b(ViewParent viewParent, View view, View view2, int i) {
        return f8953a.a(viewParent, view, view2, i);
    }

    public static class e implements b {
        @Override // supwisdom.qu.b
        public boolean a(ViewParent viewParent, View view, float f, float f2, boolean z) {
            if (viewParent instanceof hu) {
                return ((hu) viewParent).onNestedFling(view, f, f2, z);
            }
            return false;
        }

        @Override // supwisdom.qu.b
        public void b(ViewParent viewParent, View view, View view2, int i) {
            if (viewParent instanceof hu) {
                ((hu) viewParent).onNestedScrollAccepted(view, view2, i);
            }
        }

        @Override // supwisdom.qu.b
        public boolean a(ViewParent viewParent, View view, float f, float f2) {
            if (viewParent instanceof hu) {
                return ((hu) viewParent).onNestedPreFling(view, f, f2);
            }
            return false;
        }

        @Override // supwisdom.qu.b
        public void a(ViewParent viewParent, View view, int i, int i2, int[] iArr) {
            if (viewParent instanceof hu) {
                ((hu) viewParent).onNestedPreScroll(view, i, i2, iArr);
            }
        }

        @Override // supwisdom.qu.b
        public void a(ViewParent viewParent, View view, int i, int i2, int i3, int i4) {
            if (viewParent instanceof hu) {
                ((hu) viewParent).onNestedScroll(view, i, i2, i3, i4);
            }
        }

        @Override // supwisdom.qu.b
        public boolean a(ViewParent viewParent, View view, View view2, int i) {
            if (viewParent instanceof hu) {
                return ((hu) viewParent).onStartNestedScroll(view, view2, i);
            }
            return false;
        }

        @Override // supwisdom.qu.b
        public void a(ViewParent viewParent, View view) {
            if (viewParent instanceof hu) {
                ((hu) viewParent).onStopNestedScroll(view);
            }
        }
    }

    public static boolean a(ViewParent viewParent, View view, float f, float f2) {
        return f8953a.a(viewParent, view, f, f2);
    }

    public static void a(ViewParent viewParent, View view, int i, int i2, int[] iArr) {
        f8953a.a(viewParent, view, i, i2, iArr);
    }

    public static void a(ViewParent viewParent, View view, int i, int i2, int i3, int i4) {
        f8953a.a(viewParent, view, i, i2, i3, i4);
    }

    public static void a(ViewParent viewParent, View view, View view2, int i) {
        f8953a.b(viewParent, view, view2, i);
    }

    public static void a(ViewParent viewParent, View view) {
        f8953a.a(viewParent, view);
    }
}
