package supwisdom;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: ResourcesCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class k8 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal<TypedValue> f8138a = new ThreadLocal<>();
    public static final WeakHashMap<b, SparseArray<a>> b = new WeakHashMap<>(0);
    public static final Object c = new Object();

    /* JADX INFO: compiled from: ResourcesCompat.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ColorStateList f8139a;
        public final Configuration b;

        public a(ColorStateList colorStateList, Configuration configuration) {
            this.f8139a = colorStateList;
            this.b = configuration;
        }
    }

    /* JADX INFO: compiled from: ResourcesCompat.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Resources f8140a;
        public final Resources.Theme b;

        public b(Resources resources, Resources.Theme theme) {
            this.f8140a = resources;
            this.b = theme;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || b.class != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            return this.f8140a.equals(bVar.f8140a) && ia.a(this.b, bVar.b);
        }

        public int hashCode() {
            return ia.a(this.f8140a, this.b);
        }
    }

    /* JADX INFO: compiled from: ResourcesCompat.java */
    public static abstract class c {

        /* JADX INFO: compiled from: ResourcesCompat.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Typeface f8141a;

            public a(Typeface typeface) {
                this.f8141a = typeface;
            }

            @Override // java.lang.Runnable
            public void run() {
                c.this.a(this.f8141a);
            }
        }

        /* JADX INFO: compiled from: ResourcesCompat.java */
        public class b implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f8142a;

            public b(int i) {
                this.f8142a = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                c.this.a(this.f8142a);
            }
        }

        public abstract void a(int i);

        public abstract void a(Typeface typeface);

        public final void a(Typeface typeface, Handler handler) {
            a(handler).post(new a(typeface));
        }

        public final void a(int i, Handler handler) {
            a(handler).post(new b(i));
        }

        public static Handler a(Handler handler) {
            return handler == null ? new Handler(Looper.getMainLooper()) : handler;
        }
    }

    /* JADX INFO: compiled from: ResourcesCompat.java */
    public static final class d {

        /* JADX INFO: compiled from: ResourcesCompat.java */
        public static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final Object f8143a = new Object();
            public static Method b;
            public static boolean c;

            /* JADX WARN: Removed duplicated region for block: B:30:0x0027 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public static void a(android.content.res.Resources.Theme r6) {
                /*
                    java.lang.Object r0 = supwisdom.k8.d.a.f8143a
                    monitor-enter(r0)
                    boolean r1 = supwisdom.k8.d.a.c     // Catch: java.lang.Throwable -> L3e
                    r2 = 0
                    if (r1 != 0) goto L23
                    r1 = 1
                    java.lang.Class<android.content.res.Resources$Theme> r3 = android.content.res.Resources.Theme.class
                    java.lang.String r4 = "rebase"
                    java.lang.Class[] r5 = new java.lang.Class[r2]     // Catch: java.lang.NoSuchMethodException -> L19 java.lang.Throwable -> L3e
                    java.lang.reflect.Method r3 = r3.getDeclaredMethod(r4, r5)     // Catch: java.lang.NoSuchMethodException -> L19 java.lang.Throwable -> L3e
                    supwisdom.k8.d.a.b = r3     // Catch: java.lang.NoSuchMethodException -> L19 java.lang.Throwable -> L3e
                    r3.setAccessible(r1)     // Catch: java.lang.NoSuchMethodException -> L19 java.lang.Throwable -> L3e
                    goto L21
                L19:
                    r3 = move-exception
                    java.lang.String r4 = "ResourcesCompat"
                    java.lang.String r5 = "Failed to retrieve rebase() method"
                    android.util.Log.i(r4, r5, r3)     // Catch: java.lang.Throwable -> L3e
                L21:
                    supwisdom.k8.d.a.c = r1     // Catch: java.lang.Throwable -> L3e
                L23:
                    java.lang.reflect.Method r1 = supwisdom.k8.d.a.b     // Catch: java.lang.Throwable -> L3e
                    if (r1 == 0) goto L3c
                    java.lang.reflect.Method r1 = supwisdom.k8.d.a.b     // Catch: java.lang.reflect.InvocationTargetException -> L2f java.lang.IllegalAccessException -> L31 java.lang.Throwable -> L3e
                    java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.reflect.InvocationTargetException -> L2f java.lang.IllegalAccessException -> L31 java.lang.Throwable -> L3e
                    r1.invoke(r6, r2)     // Catch: java.lang.reflect.InvocationTargetException -> L2f java.lang.IllegalAccessException -> L31 java.lang.Throwable -> L3e
                    goto L3c
                L2f:
                    r6 = move-exception
                    goto L32
                L31:
                    r6 = move-exception
                L32:
                    java.lang.String r1 = "ResourcesCompat"
                    java.lang.String r2 = "Failed to invoke rebase() method via reflection"
                    android.util.Log.i(r1, r2, r6)     // Catch: java.lang.Throwable -> L3e
                    r6 = 0
                    supwisdom.k8.d.a.b = r6     // Catch: java.lang.Throwable -> L3e
                L3c:
                    monitor-exit(r0)     // Catch: java.lang.Throwable -> L3e
                    return
                L3e:
                    r6 = move-exception
                    monitor-exit(r0)     // Catch: java.lang.Throwable -> L3e
                    throw r6
                */
                throw new UnsupportedOperationException("Method not decompiled: supwisdom.k8.d.a.a(android.content.res.Resources$Theme):void");
            }
        }

        /* JADX INFO: compiled from: ResourcesCompat.java */
        public static class b {
            public static void a(Resources.Theme theme) {
                theme.rebase();
            }
        }

        public static void a(Resources.Theme theme) {
            int i = Build.VERSION.SDK_INT;
            if (i >= 29) {
                b.a(theme);
            } else if (i >= 23) {
                a.a(theme);
            }
        }
    }

    public static ColorStateList a(Resources resources, int i, Resources.Theme theme) throws Resources.NotFoundException {
        if (Build.VERSION.SDK_INT >= 23) {
            return resources.getColorStateList(i, theme);
        }
        b bVar = new b(resources, theme);
        ColorStateList colorStateListA = a(bVar, i);
        if (colorStateListA != null) {
            return colorStateListA;
        }
        ColorStateList colorStateListC = c(resources, i, theme);
        if (colorStateListC == null) {
            return resources.getColorStateList(i);
        }
        a(bVar, i, colorStateListC);
        return colorStateListC;
    }

    public static Drawable b(Resources resources, int i, Resources.Theme theme) throws Resources.NotFoundException {
        return Build.VERSION.SDK_INT >= 21 ? resources.getDrawable(i, theme) : resources.getDrawable(i);
    }

    public static ColorStateList c(Resources resources, int i, Resources.Theme theme) {
        if (a(resources, i)) {
            return null;
        }
        try {
            return f8.a(resources, resources.getXml(i), theme);
        } catch (Exception e2) {
            Log.e("ResourcesCompat", "Failed to inflate ColorStateList, leaving it to the framework", e2);
            return null;
        }
    }

    public static ColorStateList a(b bVar, int i) {
        a aVar;
        synchronized (c) {
            SparseArray<a> sparseArray = b.get(bVar);
            if (sparseArray != null && sparseArray.size() > 0 && (aVar = sparseArray.get(i)) != null) {
                if (aVar.b.equals(bVar.f8140a.getConfiguration())) {
                    return aVar.f8139a;
                }
                sparseArray.remove(i);
            }
            return null;
        }
    }

    public static void a(b bVar, int i, ColorStateList colorStateList) {
        synchronized (c) {
            SparseArray<a> sparseArray = b.get(bVar);
            if (sparseArray == null) {
                sparseArray = new SparseArray<>();
                b.put(bVar, sparseArray);
            }
            sparseArray.append(i, new a(colorStateList, bVar.f8140a.getConfiguration()));
        }
    }

    public static boolean a(Resources resources, int i) {
        TypedValue typedValueA = a();
        resources.getValue(i, typedValueA, true);
        int i2 = typedValueA.type;
        return i2 >= 28 && i2 <= 31;
    }

    public static TypedValue a() {
        TypedValue typedValue = f8138a.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        f8138a.set(typedValue2);
        return typedValue2;
    }

    public static Typeface a(Context context, int i) throws Resources.NotFoundException {
        if (context.isRestricted()) {
            return null;
        }
        return a(context, i, new TypedValue(), 0, null, null, false, false);
    }

    public static void a(Context context, int i, c cVar, Handler handler) throws Resources.NotFoundException {
        na.a(cVar);
        if (context.isRestricted()) {
            cVar.a(-4, handler);
        } else {
            a(context, i, new TypedValue(), 0, cVar, handler, false, false);
        }
    }

    public static Typeface a(Context context, int i, TypedValue typedValue, int i2, c cVar) throws Resources.NotFoundException {
        if (context.isRestricted()) {
            return null;
        }
        return a(context, i, typedValue, i2, cVar, null, true, false);
    }

    public static Typeface a(Context context, int i, TypedValue typedValue, int i2, c cVar, Handler handler, boolean z, boolean z2) {
        Resources resources = context.getResources();
        resources.getValue(i, typedValue, true);
        Typeface typefaceA = a(context, resources, typedValue, i, i2, cVar, handler, z, z2);
        if (typefaceA != null || cVar != null || z2) {
            return typefaceA;
        }
        throw new Resources.NotFoundException("Font resource ID #0x" + Integer.toHexString(i) + " could not be retrieved.");
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00a6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Typeface a(android.content.Context r15, android.content.res.Resources r16, android.util.TypedValue r17, int r18, int r19, supwisdom.k8.c r20, android.os.Handler r21, boolean r22, boolean r23) {
        /*
            Method dump skipped, instruction units count: 217
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.k8.a(android.content.Context, android.content.res.Resources, android.util.TypedValue, int, int, supwisdom.k8$c, android.os.Handler, boolean, boolean):android.graphics.Typeface");
    }
}
