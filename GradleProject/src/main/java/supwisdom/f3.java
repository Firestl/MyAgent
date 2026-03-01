package supwisdom;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import androidx.appcompat.resources.R;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: ResourceManagerInternal.java */
/* JADX INFO: loaded from: classes.dex */
public final class f3 {
    public static f3 i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WeakHashMap<Context, q4<ColorStateList>> f7562a;
    public p4<String, d> b;
    public q4<String> c;
    public final WeakHashMap<Context, m4<WeakReference<Drawable.ConstantState>>> d = new WeakHashMap<>(0);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TypedValue f7563e;
    public boolean f;
    public e g;
    public static final PorterDuff.Mode h = PorterDuff.Mode.SRC_IN;
    public static final c j = new c(6);

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public static class a implements d {
        @Override // supwisdom.f3.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return c1.e(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e2) {
                Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", e2);
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public static class b implements d {
        @Override // supwisdom.f3.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return qh.a(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e2) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", e2);
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public static class c extends n4<Integer, PorterDuffColorFilter> {
        public c(int i) {
            super(i);
        }

        public static int b(int i, PorterDuff.Mode mode) {
            return ((i + 31) * 31) + mode.hashCode();
        }

        public PorterDuffColorFilter a(int i, PorterDuff.Mode mode) {
            return b(Integer.valueOf(b(i, mode)));
        }

        public PorterDuffColorFilter a(int i, PorterDuff.Mode mode, PorterDuffColorFilter porterDuffColorFilter) {
            return a(Integer.valueOf(b(i, mode)), porterDuffColorFilter);
        }
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public interface d {
        Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme);
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public interface e {
        ColorStateList a(Context context, int i);

        PorterDuff.Mode a(int i);

        Drawable a(f3 f3Var, Context context, int i);

        boolean a(Context context, int i, Drawable drawable);

        boolean b(Context context, int i, Drawable drawable);
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    public static class f implements d {
        @Override // supwisdom.f3.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return wh.createFromXmlInner(context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e2) {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", e2);
                return null;
            }
        }
    }

    public static synchronized f3 a() {
        if (i == null) {
            f3 f3Var = new f3();
            i = f3Var;
            a(f3Var);
        }
        return i;
    }

    public synchronized Drawable b(Context context, int i2) {
        return a(context, i2, false);
    }

    public synchronized ColorStateList c(Context context, int i2) {
        ColorStateList colorStateListD;
        colorStateListD = d(context, i2);
        if (colorStateListD == null) {
            colorStateListD = this.g == null ? null : this.g.a(context, i2);
            if (colorStateListD != null) {
                a(context, i2, colorStateListD);
            }
        }
        return colorStateListD;
    }

    public final ColorStateList d(Context context, int i2) {
        q4<ColorStateList> q4Var;
        WeakHashMap<Context, q4<ColorStateList>> weakHashMap = this.f7562a;
        if (weakHashMap == null || (q4Var = weakHashMap.get(context)) == null) {
            return null;
        }
        return q4Var.a(i2);
    }

    public final Drawable e(Context context, int i2) {
        int next;
        p4<String, d> p4Var = this.b;
        if (p4Var == null || p4Var.isEmpty()) {
            return null;
        }
        q4<String> q4Var = this.c;
        if (q4Var != null) {
            String strA = q4Var.a(i2);
            if ("appcompat_skip_skip".equals(strA) || (strA != null && this.b.get(strA) == null)) {
                return null;
            }
        } else {
            this.c = new q4<>();
        }
        if (this.f7563e == null) {
            this.f7563e = new TypedValue();
        }
        TypedValue typedValue = this.f7563e;
        Resources resources = context.getResources();
        resources.getValue(i2, typedValue, true);
        long jA = a(typedValue);
        Drawable drawableA = a(context, jA);
        if (drawableA != null) {
            return drawableA;
        }
        CharSequence charSequence = typedValue.string;
        if (charSequence != null && charSequence.toString().endsWith(".xml")) {
            try {
                XmlResourceParser xml = resources.getXml(i2);
                AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
                do {
                    next = xml.next();
                    if (next == 2) {
                        break;
                    }
                } while (next != 1);
                if (next != 2) {
                    throw new XmlPullParserException("No start tag found");
                }
                String name = xml.getName();
                this.c.a(i2, name);
                d dVar = this.b.get(name);
                if (dVar != null) {
                    drawableA = dVar.a(context, xml, attributeSetAsAttributeSet, context.getTheme());
                }
                if (drawableA != null) {
                    drawableA.setChangingConfigurations(typedValue.changingConfigurations);
                    a(context, jA, drawableA);
                }
            } catch (Exception e2) {
                Log.e("ResourceManagerInternal", "Exception while inflating drawable", e2);
            }
        }
        if (drawableA == null) {
            this.c.a(i2, "appcompat_skip_skip");
        }
        return drawableA;
    }

    public synchronized void b(Context context) {
        m4<WeakReference<Drawable.ConstantState>> m4Var = this.d.get(context);
        if (m4Var != null) {
            m4Var.a();
        }
    }

    public static void a(f3 f3Var) {
        if (Build.VERSION.SDK_INT < 24) {
            f3Var.a("vector", new f());
            f3Var.a("animated-vector", new b());
            f3Var.a("animated-selector", new a());
        }
    }

    public synchronized void a(e eVar) {
        this.g = eVar;
    }

    public synchronized Drawable a(Context context, int i2, boolean z) {
        Drawable drawableE;
        a(context);
        drawableE = e(context, i2);
        if (drawableE == null) {
            drawableE = a(context, i2);
        }
        if (drawableE == null) {
            drawableE = y7.c(context, i2);
        }
        if (drawableE != null) {
            drawableE = a(context, i2, z, drawableE);
        }
        if (drawableE != null) {
            y2.b(drawableE);
        }
        return drawableE;
    }

    public static long a(TypedValue typedValue) {
        return (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
    }

    public final Drawable a(Context context, int i2) {
        if (this.f7563e == null) {
            this.f7563e = new TypedValue();
        }
        TypedValue typedValue = this.f7563e;
        context.getResources().getValue(i2, typedValue, true);
        long jA = a(typedValue);
        Drawable drawableA = a(context, jA);
        if (drawableA != null) {
            return drawableA;
        }
        e eVar = this.g;
        Drawable drawableA2 = eVar == null ? null : eVar.a(this, context, i2);
        if (drawableA2 != null) {
            drawableA2.setChangingConfigurations(typedValue.changingConfigurations);
            a(context, jA, drawableA2);
        }
        return drawableA2;
    }

    public final Drawable a(Context context, int i2, boolean z, Drawable drawable) {
        ColorStateList colorStateListC = c(context, i2);
        if (colorStateListC != null) {
            if (y2.a(drawable)) {
                drawable = drawable.mutate();
            }
            Drawable drawableI = y8.i(drawable);
            y8.a(drawableI, colorStateListC);
            PorterDuff.Mode modeA = a(i2);
            if (modeA == null) {
                return drawableI;
            }
            y8.a(drawableI, modeA);
            return drawableI;
        }
        e eVar = this.g;
        if ((eVar == null || !eVar.b(context, i2, drawable)) && !a(context, i2, drawable) && z) {
            return null;
        }
        return drawable;
    }

    public final synchronized Drawable a(Context context, long j2) {
        m4<WeakReference<Drawable.ConstantState>> m4Var = this.d.get(context);
        if (m4Var == null) {
            return null;
        }
        WeakReference<Drawable.ConstantState> weakReferenceA = m4Var.a(j2);
        if (weakReferenceA != null) {
            Drawable.ConstantState constantState = weakReferenceA.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            m4Var.c(j2);
        }
        return null;
    }

    public final synchronized boolean a(Context context, long j2, Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState == null) {
            return false;
        }
        m4<WeakReference<Drawable.ConstantState>> m4Var = this.d.get(context);
        if (m4Var == null) {
            m4Var = new m4<>();
            this.d.put(context, m4Var);
        }
        m4Var.c(j2, new WeakReference<>(constantState));
        return true;
    }

    public synchronized Drawable a(Context context, u3 u3Var, int i2) {
        Drawable drawableE = e(context, i2);
        if (drawableE == null) {
            drawableE = u3Var.a(i2);
        }
        if (drawableE == null) {
            return null;
        }
        return a(context, i2, false, drawableE);
    }

    public boolean a(Context context, int i2, Drawable drawable) {
        e eVar = this.g;
        return eVar != null && eVar.a(context, i2, drawable);
    }

    public final void a(String str, d dVar) {
        if (this.b == null) {
            this.b = new p4<>();
        }
        this.b.put(str, dVar);
    }

    public PorterDuff.Mode a(int i2) {
        e eVar = this.g;
        if (eVar == null) {
            return null;
        }
        return eVar.a(i2);
    }

    public final void a(Context context, int i2, ColorStateList colorStateList) {
        if (this.f7562a == null) {
            this.f7562a = new WeakHashMap<>();
        }
        q4<ColorStateList> q4Var = this.f7562a.get(context);
        if (q4Var == null) {
            q4Var = new q4<>();
            this.f7562a.put(context, q4Var);
        }
        q4Var.a(i2, colorStateList);
    }

    public static void a(Drawable drawable, n3 n3Var, int[] iArr) {
        if (y2.a(drawable) && drawable.mutate() != drawable) {
            Log.d("ResourceManagerInternal", "Mutated drawable is not the same instance as the input.");
            return;
        }
        if (!n3Var.d && !n3Var.c) {
            drawable.clearColorFilter();
        } else {
            drawable.setColorFilter(a(n3Var.d ? n3Var.f8470a : null, n3Var.c ? n3Var.b : h, iArr));
        }
        if (Build.VERSION.SDK_INT <= 23) {
            drawable.invalidateSelf();
        }
    }

    public static PorterDuffColorFilter a(ColorStateList colorStateList, PorterDuff.Mode mode, int[] iArr) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return a(colorStateList.getColorForState(iArr, 0), mode);
    }

    public static synchronized PorterDuffColorFilter a(int i2, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilterA;
        porterDuffColorFilterA = j.a(i2, mode);
        if (porterDuffColorFilterA == null) {
            porterDuffColorFilterA = new PorterDuffColorFilter(i2, mode);
            j.a(i2, mode, porterDuffColorFilterA);
        }
        return porterDuffColorFilterA;
    }

    public final void a(Context context) {
        if (this.f) {
            return;
        }
        this.f = true;
        Drawable drawableB = b(context, R.drawable.abc_vector_test);
        if (drawableB == null || !a(drawableB)) {
            this.f = false;
            throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
        }
    }

    public static boolean a(Drawable drawable) {
        return (drawable instanceof wh) || "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName());
    }
}
