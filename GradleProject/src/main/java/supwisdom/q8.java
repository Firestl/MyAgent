package supwisdom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import supwisdom.h8;
import supwisdom.k8;
import supwisdom.x9;

/* JADX INFO: compiled from: TypefaceCompat.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"NewApi"})
public class q8 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final w8 f8888a;
    public static final n4<String, Typeface> b;

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            f8888a = new v8();
        } else if (i >= 28) {
            f8888a = new u8();
        } else if (i >= 26) {
            f8888a = new t8();
        } else if (i >= 24 && s8.a()) {
            f8888a = new s8();
        } else if (Build.VERSION.SDK_INT >= 21) {
            f8888a = new r8();
        } else {
            f8888a = new w8();
        }
        b = new n4<>(16);
    }

    public static String a(Resources resources, int i, int i2) {
        return resources.getResourcePackageName(i) + "-" + i + "-" + i2;
    }

    public static Typeface b(Resources resources, int i, int i2) {
        return b.b(a(resources, i, i2));
    }

    /* JADX INFO: compiled from: TypefaceCompat.java */
    public static class a extends x9.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public k8.c f8889a;

        public a(k8.c cVar) {
            this.f8889a = cVar;
        }

        @Override // supwisdom.x9.c
        public void a(Typeface typeface) {
            k8.c cVar = this.f8889a;
            if (cVar != null) {
                cVar.a(typeface);
            }
        }

        @Override // supwisdom.x9.c
        public void a(int i) {
            k8.c cVar = this.f8889a;
            if (cVar != null) {
                cVar.a(i);
            }
        }
    }

    public static Typeface a(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        Typeface typefaceCreate = Typeface.create(str, 0);
        Typeface typefaceCreate2 = Typeface.create(Typeface.DEFAULT, 0);
        if (typefaceCreate == null || typefaceCreate.equals(typefaceCreate2)) {
            return null;
        }
        return typefaceCreate;
    }

    public static Typeface b(Context context, Typeface typeface, int i) {
        h8.b bVarA = f8888a.a(typeface);
        if (bVarA == null) {
            return null;
        }
        return f8888a.a(context, bVarA, context.getResources(), i);
    }

    public static Typeface a(Context context, h8.a aVar, Resources resources, int i, int i2, k8.c cVar, Handler handler, boolean z) {
        Typeface typefaceA;
        if (aVar instanceof h8.d) {
            h8.d dVar = (h8.d) aVar;
            Typeface typefaceA2 = a(dVar.c());
            if (typefaceA2 != null) {
                if (cVar != null) {
                    cVar.a(typefaceA2, handler);
                }
                return typefaceA2;
            }
            typefaceA = x9.a(context, dVar.b(), i2, !z ? cVar != null : dVar.a() != 0, z ? dVar.d() : -1, k8.c.a(handler), new a(cVar));
        } else {
            typefaceA = f8888a.a(context, (h8.b) aVar, resources, i2);
            if (cVar != null) {
                if (typefaceA != null) {
                    cVar.a(typefaceA, handler);
                } else {
                    cVar.a(-3, handler);
                }
            }
        }
        if (typefaceA != null) {
            b.a(a(resources, i, i2), typefaceA);
        }
        return typefaceA;
    }

    public static Typeface a(Context context, Resources resources, int i, String str, int i2) {
        Typeface typefaceA = f8888a.a(context, resources, i, str, i2);
        if (typefaceA != null) {
            b.a(a(resources, i, i2), typefaceA);
        }
        return typefaceA;
    }

    public static Typeface a(Context context, CancellationSignal cancellationSignal, x9.b[] bVarArr, int i) {
        return f8888a.a(context, cancellationSignal, bVarArr, i);
    }

    public static Typeface a(Context context, Typeface typeface, int i) {
        Typeface typefaceB;
        if (context != null) {
            return (Build.VERSION.SDK_INT >= 21 || (typefaceB = b(context, typeface, i)) == null) ? Typeface.create(typeface, i) : typefaceB;
        }
        throw new IllegalArgumentException("Context cannot be null");
    }
}
