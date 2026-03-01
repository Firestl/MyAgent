package supwisdom;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.util.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.List;
import supwisdom.h8;
import supwisdom.x9;

/* JADX INFO: compiled from: TypefaceCompatApi24Impl.java */
/* JADX INFO: loaded from: classes.dex */
public class s8 extends w8 {
    public static final Class<?> b;
    public static final Constructor<?> c;
    public static final Method d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Method f9132e;

    static {
        Class<?> cls;
        Method method;
        Method method2;
        Constructor<?> constructor = null;
        try {
            cls = Class.forName("android.graphics.FontFamily");
            Constructor<?> constructor2 = cls.getConstructor(new Class[0]);
            method2 = cls.getMethod("addFontWeightStyle", ByteBuffer.class, Integer.TYPE, List.class, Integer.TYPE, Boolean.TYPE);
            method = Typeface.class.getMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass());
            constructor = constructor2;
        } catch (ClassNotFoundException | NoSuchMethodException e2) {
            Log.e("TypefaceCompatApi24Impl", e2.getClass().getName(), e2);
            cls = null;
            method = null;
            method2 = null;
        }
        c = constructor;
        b = cls;
        d = method2;
        f9132e = method;
    }

    public static boolean a() {
        if (d == null) {
            Log.w("TypefaceCompatApi24Impl", "Unable to collect necessary private methods.Fallback to legacy implementation.");
        }
        return d != null;
    }

    public static Object b() {
        try {
            return c.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            return null;
        }
    }

    public static boolean a(Object obj, ByteBuffer byteBuffer, int i, int i2, boolean z) {
        try {
            return ((Boolean) d.invoke(obj, byteBuffer, Integer.valueOf(i), null, Integer.valueOf(i2), Boolean.valueOf(z))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public static Typeface a(Object obj) {
        try {
            Object objNewInstance = Array.newInstance(b, 1);
            Array.set(objNewInstance, 0, obj);
            return (Typeface) f9132e.invoke(null, objNewInstance);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    @Override // supwisdom.w8
    public Typeface a(Context context, CancellationSignal cancellationSignal, x9.b[] bVarArr, int i) {
        Object objB = b();
        if (objB == null) {
            return null;
        }
        p4 p4Var = new p4();
        for (x9.b bVar : bVarArr) {
            Uri uriC = bVar.c();
            ByteBuffer byteBufferA = (ByteBuffer) p4Var.get(uriC);
            if (byteBufferA == null) {
                byteBufferA = x8.a(context, cancellationSignal, uriC);
                p4Var.put(uriC, byteBufferA);
            }
            if (byteBufferA == null || !a(objB, byteBufferA, bVar.b(), bVar.d(), bVar.e())) {
                return null;
            }
        }
        Typeface typefaceA = a(objB);
        if (typefaceA == null) {
            return null;
        }
        return Typeface.create(typefaceA, i);
    }

    @Override // supwisdom.w8
    public Typeface a(Context context, h8.b bVar, Resources resources, int i) {
        Object objB = b();
        if (objB == null) {
            return null;
        }
        for (h8.c cVar : bVar.a()) {
            ByteBuffer byteBufferA = x8.a(context, resources, cVar.b());
            if (byteBufferA == null || !a(objB, byteBufferA, cVar.c(), cVar.e(), cVar.f())) {
                return null;
            }
        }
        return a(objB);
    }
}
