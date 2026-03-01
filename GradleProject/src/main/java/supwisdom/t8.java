package supwisdom;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Map;
import supwisdom.h8;
import supwisdom.x9;

/* JADX INFO: compiled from: TypefaceCompatApi26Impl.java */
/* JADX INFO: loaded from: classes.dex */
public class t8 extends r8 {
    public final Class<?> g;
    public final Constructor<?> h;
    public final Method i;
    public final Method j;
    public final Method k;
    public final Method l;
    public final Method m;

    public t8() throws NoSuchMethodException {
        Method methodD;
        Constructor<?> constructorE;
        Method methodB;
        Method methodC;
        Method methodF;
        Method methodA;
        Class<?> cls = null;
        try {
            Class<?> clsD = d();
            constructorE = e(clsD);
            methodB = b(clsD);
            methodC = c(clsD);
            methodF = f(clsD);
            methodA = a(clsD);
            methodD = d(clsD);
            cls = clsD;
        } catch (ClassNotFoundException | NoSuchMethodException e2) {
            Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class " + e2.getClass().getName(), e2);
            methodD = null;
            constructorE = null;
            methodB = null;
            methodC = null;
            methodF = null;
            methodA = null;
        }
        this.g = cls;
        this.h = constructorE;
        this.i = methodB;
        this.j = methodC;
        this.k = methodF;
        this.l = methodA;
        this.m = methodD;
    }

    private Object b() {
        try {
            return this.h.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            return null;
        }
    }

    public final boolean a(Context context, Object obj, String str, int i, int i2, int i3, FontVariationAxis[] fontVariationAxisArr) {
        try {
            return ((Boolean) this.i.invoke(obj, context.getAssets(), str, 0, false, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), fontVariationAxisArr)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public final boolean c() {
        if (this.i == null) {
            Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        return this.i != null;
    }

    public Class<?> d() throws ClassNotFoundException {
        return Class.forName("android.graphics.FontFamily");
    }

    public Constructor<?> e(Class<?> cls) throws NoSuchMethodException {
        return cls.getConstructor(new Class[0]);
    }

    public Method f(Class<?> cls) throws NoSuchMethodException {
        return cls.getMethod("freeze", new Class[0]);
    }

    public final void b(Object obj) {
        try {
            this.l.invoke(obj, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException unused) {
        }
    }

    public Method d(Class<?> cls) throws NoSuchMethodException {
        Class cls2 = Integer.TYPE;
        Method declaredMethod = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", Array.newInstance(cls, 1).getClass(), cls2, cls2);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }

    public Method b(Class<?> cls) throws NoSuchMethodException {
        Class<?> cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromAssetManager", AssetManager.class, String.class, Integer.TYPE, Boolean.TYPE, cls2, cls2, cls2, FontVariationAxis[].class);
    }

    public final boolean c(Object obj) {
        try {
            return ((Boolean) this.k.invoke(obj, new Object[0])).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public final boolean a(Object obj, ByteBuffer byteBuffer, int i, int i2, int i3) {
        try {
            return ((Boolean) this.j.invoke(obj, byteBuffer, Integer.valueOf(i), null, Integer.valueOf(i2), Integer.valueOf(i3))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    public Method c(Class<?> cls) throws NoSuchMethodException {
        Class<?> cls2 = Integer.TYPE;
        return cls.getMethod("addFontFromBuffer", ByteBuffer.class, cls2, FontVariationAxis[].class, cls2, cls2);
    }

    public Typeface a(Object obj) {
        try {
            Object objNewInstance = Array.newInstance(this.g, 1);
            Array.set(objNewInstance, 0, obj);
            return (Typeface) this.m.invoke(null, objNewInstance, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    @Override // supwisdom.r8, supwisdom.w8
    public Typeface a(Context context, h8.b bVar, Resources resources, int i) {
        if (!c()) {
            return super.a(context, bVar, resources, i);
        }
        Object objB = b();
        if (objB == null) {
            return null;
        }
        for (h8.c cVar : bVar.a()) {
            if (!a(context, objB, cVar.a(), cVar.c(), cVar.e(), cVar.f() ? 1 : 0, FontVariationAxis.fromFontVariationSettings(cVar.d()))) {
                b(objB);
                return null;
            }
        }
        if (c(objB)) {
            return a(objB);
        }
        return null;
    }

    @Override // supwisdom.r8, supwisdom.w8
    public Typeface a(Context context, CancellationSignal cancellationSignal, x9.b[] bVarArr, int i) {
        Typeface typefaceA;
        if (bVarArr.length < 1) {
            return null;
        }
        if (!c()) {
            x9.b bVarA = a(bVarArr, i);
            try {
                ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(bVarA.c(), "r", cancellationSignal);
                if (parcelFileDescriptorOpenFileDescriptor == null) {
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                        parcelFileDescriptorOpenFileDescriptor.close();
                    }
                    return null;
                }
                try {
                    Typeface typefaceBuild = new Typeface.Builder(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor()).setWeight(bVarA.d()).setItalic(bVarA.e()).build();
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                        parcelFileDescriptorOpenFileDescriptor.close();
                    }
                    return typefaceBuild;
                } finally {
                }
            } catch (IOException unused) {
                return null;
            }
        }
        Map<Uri, ByteBuffer> mapA = x8.a(context, bVarArr, cancellationSignal);
        Object objB = b();
        if (objB == null) {
            return null;
        }
        boolean z = false;
        for (x9.b bVar : bVarArr) {
            ByteBuffer byteBuffer = mapA.get(bVar.c());
            if (byteBuffer != null) {
                if (!a(objB, byteBuffer, bVar.b(), bVar.d(), bVar.e() ? 1 : 0)) {
                    b(objB);
                    return null;
                }
                z = true;
            }
        }
        if (!z) {
            b(objB);
            return null;
        }
        if (c(objB) && (typefaceA = a(objB)) != null) {
            return Typeface.create(typefaceA, i);
        }
        return null;
    }

    @Override // supwisdom.w8
    public Typeface a(Context context, Resources resources, int i, String str, int i2) {
        if (!c()) {
            return super.a(context, resources, i, str, i2);
        }
        Object objB = b();
        if (objB == null) {
            return null;
        }
        if (!a(context, objB, str, 0, -1, -1, null)) {
            b(objB);
            return null;
        }
        if (c(objB)) {
            return a(objB);
        }
        return null;
    }

    public Method a(Class<?> cls) throws NoSuchMethodException {
        return cls.getMethod("abortCreation", new Class[0]);
    }
}
