package supwisdom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;
import supwisdom.h8;
import supwisdom.x9;

/* JADX INFO: compiled from: TypefaceCompatBaseImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class w8 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @SuppressLint({"BanConcurrentHashMap"})
    public ConcurrentHashMap<Long, h8.b> f9596a = new ConcurrentHashMap<>();

    /* JADX INFO: compiled from: TypefaceCompatBaseImpl.java */
    public class a implements c<x9.b> {
        public a(w8 w8Var) {
        }

        @Override // supwisdom.w8.c
        public int a(x9.b bVar) {
            return bVar.d();
        }

        @Override // supwisdom.w8.c
        public boolean b(x9.b bVar) {
            return bVar.e();
        }
    }

    /* JADX INFO: compiled from: TypefaceCompatBaseImpl.java */
    public class b implements c<h8.c> {
        public b(w8 w8Var) {
        }

        @Override // supwisdom.w8.c
        public int a(h8.c cVar) {
            return cVar.e();
        }

        @Override // supwisdom.w8.c
        public boolean b(h8.c cVar) {
            return cVar.f();
        }
    }

    /* JADX INFO: compiled from: TypefaceCompatBaseImpl.java */
    public interface c<T> {
        int a(T t);

        boolean b(T t);
    }

    public static <T> T a(T[] tArr, int i, c<T> cVar) {
        int i2 = (i & 1) == 0 ? 400 : 700;
        boolean z = (i & 2) != 0;
        T t = null;
        int i3 = Integer.MAX_VALUE;
        for (T t2 : tArr) {
            int iAbs = (Math.abs(cVar.a(t2) - i2) * 2) + (cVar.b(t2) == z ? 0 : 1);
            if (t == null || i3 > iAbs) {
                t = t2;
                i3 = iAbs;
            }
        }
        return t;
    }

    public static long b(Typeface typeface) {
        if (typeface == null) {
            return 0L;
        }
        try {
            Field declaredField = Typeface.class.getDeclaredField("native_instance");
            declaredField.setAccessible(true);
            return ((Number) declaredField.get(typeface)).longValue();
        } catch (IllegalAccessException e2) {
            Log.e("TypefaceCompatBaseImpl", "Could not retrieve font from family.", e2);
            return 0L;
        } catch (NoSuchFieldException e3) {
            Log.e("TypefaceCompatBaseImpl", "Could not retrieve font from family.", e3);
            return 0L;
        }
    }

    public x9.b a(x9.b[] bVarArr, int i) {
        return (x9.b) a(bVarArr, i, new a(this));
    }

    public Typeface a(Context context, InputStream inputStream) {
        File fileA = x8.a(context);
        if (fileA == null) {
            return null;
        }
        try {
            if (x8.a(fileA, inputStream)) {
                return Typeface.createFromFile(fileA.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            fileA.delete();
        }
    }

    public Typeface a(Context context, CancellationSignal cancellationSignal, x9.b[] bVarArr, int i) throws Throwable {
        InputStream inputStreamOpenInputStream;
        InputStream inputStream = null;
        if (bVarArr.length < 1) {
            return null;
        }
        try {
            inputStreamOpenInputStream = context.getContentResolver().openInputStream(a(bVarArr, i).c());
            try {
                Typeface typefaceA = a(context, inputStreamOpenInputStream);
                x8.a(inputStreamOpenInputStream);
                return typefaceA;
            } catch (IOException unused) {
                x8.a(inputStreamOpenInputStream);
                return null;
            } catch (Throwable th) {
                th = th;
                inputStream = inputStreamOpenInputStream;
                x8.a(inputStream);
                throw th;
            }
        } catch (IOException unused2) {
            inputStreamOpenInputStream = null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public final h8.c a(h8.b bVar, int i) {
        return (h8.c) a(bVar.a(), i, new b(this));
    }

    public Typeface a(Context context, h8.b bVar, Resources resources, int i) {
        h8.c cVarA = a(bVar, i);
        if (cVarA == null) {
            return null;
        }
        Typeface typefaceA = q8.a(context, resources, cVarA.b(), cVarA.a(), i);
        a(typefaceA, bVar);
        return typefaceA;
    }

    public Typeface a(Context context, Resources resources, int i, String str, int i2) {
        File fileA = x8.a(context);
        if (fileA == null) {
            return null;
        }
        try {
            if (x8.a(fileA, resources, i)) {
                return Typeface.createFromFile(fileA.getPath());
            }
            return null;
        } catch (RuntimeException unused) {
            return null;
        } finally {
            fileA.delete();
        }
    }

    public h8.b a(Typeface typeface) {
        long jB = b(typeface);
        if (jB == 0) {
            return null;
        }
        return this.f9596a.get(Long.valueOf(jB));
    }

    public final void a(Typeface typeface, h8.b bVar) {
        long jB = b(typeface);
        if (jB != 0) {
            this.f9596a.put(Long.valueOf(jB), bVar);
        }
    }
}
