package supwisdom;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.util.Property;
import android.view.View;
import java.lang.reflect.Field;

/* JADX INFO: compiled from: ViewUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class fh {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final jh f7605a;
    public static Field b;
    public static boolean c;
    public static final Property<View, Float> d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Property<View, Rect> f7606e;

    /* JADX INFO: compiled from: ViewUtils.java */
    public static class a extends Property<View, Float> {
        public a(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(View view) {
            return Float.valueOf(fh.c(view));
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void set(View view, Float f) {
            fh.a(view, f.floatValue());
        }
    }

    /* JADX INFO: compiled from: ViewUtils.java */
    public static class b extends Property<View, Rect> {
        public b(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Rect get(View view) {
            return lb.h(view);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void set(View view, Rect rect) {
            lb.a(view, rect);
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 22) {
            f7605a = new ih();
        } else if (i >= 21) {
            f7605a = new hh();
        } else if (i >= 19) {
            f7605a = new gh();
        } else {
            f7605a = new jh();
        }
        d = new a(Float.class, "translationAlpha");
        f7606e = new b(Rect.class, "clipBounds");
    }

    public static void a(View view, float f) {
        f7605a.a(view, f);
    }

    public static eh b(View view) {
        return Build.VERSION.SDK_INT >= 18 ? new dh(view) : ch.c(view);
    }

    public static float c(View view) {
        return f7605a.b(view);
    }

    public static nh d(View view) {
        return Build.VERSION.SDK_INT >= 18 ? new mh(view) : new lh(view.getWindowToken());
    }

    public static void e(View view) {
        f7605a.c(view);
    }

    public static void a(View view) {
        f7605a.a(view);
    }

    public static void c(View view, Matrix matrix) {
        f7605a.c(view, matrix);
    }

    public static void a(View view, int i) {
        a();
        Field field = b;
        if (field != null) {
            try {
                b.setInt(view, i | (field.getInt(view) & (-13)));
            } catch (IllegalAccessException unused) {
            }
        }
    }

    public static void b(View view, Matrix matrix) {
        f7605a.b(view, matrix);
    }

    public static void a(View view, Matrix matrix) {
        f7605a.a(view, matrix);
    }

    public static void a(View view, int i, int i2, int i3, int i4) {
        f7605a.a(view, i, i2, i3, i4);
    }

    public static void a() {
        if (c) {
            return;
        }
        try {
            Field declaredField = View.class.getDeclaredField("mViewFlags");
            b = declaredField;
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException unused) {
            Log.i("ViewUtils", "fetchViewFlagsField: ");
        }
        c = true;
    }
}
