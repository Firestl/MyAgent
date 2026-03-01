package supwisdom;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import io.dcloud.feature.weex_amap.adapter.Constant;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.bouncycastle.math.ec.rfc7748.X25519Field;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import supwisdom.p8;

/* JADX INFO: compiled from: VectorDrawableCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class wh extends vh {
    public static final PorterDuff.Mode j = PorterDuff.Mode.SRC_IN;
    public h b;
    public PorterDuffColorFilter c;
    public ColorFilter d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f9624e;
    public boolean f;
    public final float[] g;
    public final Matrix h;
    public final Rect i;

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class b extends f {
        public b() {
        }

        public void a(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            if (l8.a(xmlPullParser, "pathData")) {
                TypedArray typedArrayA = l8.a(resources, theme, attributeSet, oh.d);
                a(typedArrayA, xmlPullParser);
                typedArrayA.recycle();
            }
        }

        @Override // supwisdom.wh.f
        public boolean b() {
            return true;
        }

        public b(b bVar) {
            super(bVar);
        }

        public final void a(TypedArray typedArray, XmlPullParser xmlPullParser) {
            String string = typedArray.getString(0);
            if (string != null) {
                this.b = string;
            }
            String string2 = typedArray.getString(1);
            if (string2 != null) {
                this.f9628a = p8.a(string2);
            }
            this.c = l8.b(typedArray, xmlPullParser, "fillType", 2, 0);
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static abstract class e {
        public e() {
        }

        public boolean a() {
            return false;
        }

        public boolean a(int[] iArr) {
            return false;
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class h extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f9631a;
        public g b;
        public ColorStateList c;
        public PorterDuff.Mode d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f9632e;
        public Bitmap f;
        public ColorStateList g;
        public PorterDuff.Mode h;
        public int i;
        public boolean j;
        public boolean k;
        public Paint l;

        public h(h hVar) {
            this.c = null;
            this.d = wh.j;
            if (hVar != null) {
                this.f9631a = hVar.f9631a;
                g gVar = new g(hVar.b);
                this.b = gVar;
                if (hVar.b.f9630e != null) {
                    gVar.f9630e = new Paint(hVar.b.f9630e);
                }
                if (hVar.b.d != null) {
                    this.b.d = new Paint(hVar.b.d);
                }
                this.c = hVar.c;
                this.d = hVar.d;
                this.f9632e = hVar.f9632e;
            }
        }

        public void a(Canvas canvas, ColorFilter colorFilter, Rect rect) {
            canvas.drawBitmap(this.f, (Rect) null, rect, a(colorFilter));
        }

        public boolean b() {
            return this.b.getRootAlpha() < 255;
        }

        public void c(int i, int i2) {
            this.f.eraseColor(0);
            this.b.a(new Canvas(this.f), i, i2, (ColorFilter) null);
        }

        public void d() {
            this.g = this.c;
            this.h = this.d;
            this.i = this.b.getRootAlpha();
            this.j = this.f9632e;
            this.k = false;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f9631a;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new wh(this);
        }

        public void b(int i, int i2) {
            if (this.f == null || !a(i, i2)) {
                this.f = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                this.k = true;
            }
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new wh(this);
        }

        public Paint a(ColorFilter colorFilter) {
            if (!b() && colorFilter == null) {
                return null;
            }
            if (this.l == null) {
                Paint paint = new Paint();
                this.l = paint;
                paint.setFilterBitmap(true);
            }
            this.l.setAlpha(this.b.getRootAlpha());
            this.l.setColorFilter(colorFilter);
            return this.l;
        }

        public boolean c() {
            return this.b.a();
        }

        public boolean a(int i, int i2) {
            return i == this.f.getWidth() && i2 == this.f.getHeight();
        }

        public boolean a() {
            return !this.k && this.g == this.c && this.h == this.d && this.j == this.f9632e && this.i == this.b.getRootAlpha();
        }

        public h() {
            this.c = null;
            this.d = wh.j;
            this.b = new g();
        }

        public boolean a(int[] iArr) {
            boolean zA = this.b.a(iArr);
            this.k |= zA;
            return zA;
        }
    }

    public wh() {
        this.f = true;
        this.g = new float[9];
        this.h = new Matrix();
        this.i = new Rect();
        this.b = new h();
    }

    public static wh createFromXmlInner(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        wh whVar = new wh();
        whVar.inflate(resources, xmlPullParser, attributeSet, theme);
        return whVar;
    }

    public Object a(String str) {
        return this.b.b.p.get(str);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.f9511a;
        if (drawable == null) {
            return false;
        }
        y8.a(drawable);
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f9511a;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        copyBounds(this.i);
        if (this.i.width() <= 0 || this.i.height() <= 0) {
            return;
        }
        ColorFilter colorFilter = this.d;
        if (colorFilter == null) {
            colorFilter = this.c;
        }
        canvas.getMatrix(this.h);
        this.h.getValues(this.g);
        float fAbs = Math.abs(this.g[0]);
        float fAbs2 = Math.abs(this.g[4]);
        float fAbs3 = Math.abs(this.g[1]);
        float fAbs4 = Math.abs(this.g[3]);
        if (fAbs3 != 0.0f || fAbs4 != 0.0f) {
            fAbs = 1.0f;
            fAbs2 = 1.0f;
        }
        int iMin = Math.min(2048, (int) (this.i.width() * fAbs));
        int iMin2 = Math.min(2048, (int) (this.i.height() * fAbs2));
        if (iMin <= 0 || iMin2 <= 0) {
            return;
        }
        int iSave = canvas.save();
        Rect rect = this.i;
        canvas.translate(rect.left, rect.top);
        if (a()) {
            canvas.translate(this.i.width(), 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        this.i.offsetTo(0, 0);
        this.b.b(iMin, iMin2);
        if (!this.f) {
            this.b.c(iMin, iMin2);
        } else if (!this.b.a()) {
            this.b.c(iMin, iMin2);
            this.b.d();
        }
        this.b.a(canvas, colorFilter, this.i);
        canvas.restoreToCount(iSave);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.f9511a;
        return drawable != null ? y8.c(drawable) : this.b.b.getRootAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.f9511a;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.b.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        Drawable drawable = this.f9511a;
        return drawable != null ? y8.d(drawable) : this.d;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        if (this.f9511a != null && Build.VERSION.SDK_INT >= 24) {
            return new i(this.f9511a.getConstantState());
        }
        this.b.f9631a = getChangingConfigurations();
        return this.b;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.f9511a;
        return drawable != null ? drawable.getIntrinsicHeight() : (int) this.b.b.j;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.f9511a;
        return drawable != null ? drawable.getIntrinsicWidth() : (int) this.b.b.i;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f9511a;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        Drawable drawable = this.f9511a;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        Drawable drawable = this.f9511a;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.f9511a;
        return drawable != null ? y8.f(drawable) : this.b.f9632e;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        h hVar;
        ColorStateList colorStateList;
        Drawable drawable = this.f9511a;
        return drawable != null ? drawable.isStateful() : super.isStateful() || ((hVar = this.b) != null && (hVar.c() || ((colorStateList = this.b.c) != null && colorStateList.isStateful())));
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.f9511a;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.f9624e && super.mutate() == this) {
            this.b = new h(this.b);
            this.f9624e = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f9511a;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        Drawable drawable = this.f9511a;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        boolean z = false;
        h hVar = this.b;
        ColorStateList colorStateList = hVar.c;
        if (colorStateList != null && (mode = hVar.d) != null) {
            this.c = a(this.c, colorStateList, mode);
            invalidateSelf();
            z = true;
        }
        if (!hVar.c() || !hVar.a(iArr)) {
            return z;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void scheduleSelf(Runnable runnable, long j2) {
        Drawable drawable = this.f9511a;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j2);
        } else {
            super.scheduleSelf(runnable, j2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        Drawable drawable = this.f9511a;
        if (drawable != null) {
            drawable.setAlpha(i2);
        } else if (this.b.b.getRootAlpha() != i2) {
            this.b.b.setRootAlpha(i2);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.f9511a;
        if (drawable != null) {
            y8.a(drawable, z);
        } else {
            this.b.f9632e = z;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f9511a;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.d = colorFilter;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable, supwisdom.z8
    public void setTint(int i2) {
        Drawable drawable = this.f9511a;
        if (drawable != null) {
            y8.b(drawable, i2);
        } else {
            setTintList(ColorStateList.valueOf(i2));
        }
    }

    @Override // android.graphics.drawable.Drawable, supwisdom.z8
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f9511a;
        if (drawable != null) {
            y8.a(drawable, colorStateList);
            return;
        }
        h hVar = this.b;
        if (hVar.c != colorStateList) {
            hVar.c = colorStateList;
            this.c = a(this.c, colorStateList, hVar.d);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable, supwisdom.z8
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f9511a;
        if (drawable != null) {
            y8.a(drawable, mode);
            return;
        }
        h hVar = this.b;
        if (hVar.d != mode) {
            hVar.d = mode;
            this.c = a(this.c, hVar.c, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.f9511a;
        return drawable != null ? drawable.setVisible(z, z2) : super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable
    public void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.f9511a;
        if (drawable != null) {
            drawable.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class i extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Drawable.ConstantState f9633a;

        public i(Drawable.ConstantState constantState) {
            this.f9633a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.f9633a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f9633a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            wh whVar = new wh();
            whVar.f9511a = (VectorDrawable) this.f9633a.newDrawable();
            return whVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            wh whVar = new wh();
            whVar.f9511a = (VectorDrawable) this.f9633a.newDrawable(resources);
            return whVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            wh whVar = new wh();
            whVar.f9511a = (VectorDrawable) this.f9633a.newDrawable(resources, theme);
            return whVar;
        }
    }

    public PorterDuffColorFilter a(PorterDuffColorFilter porterDuffColorFilter, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class c extends f {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int[] f9625e;
        public g8 f;
        public float g;
        public g8 h;
        public float i;
        public float j;
        public float k;
        public float l;
        public float m;
        public Paint.Cap n;
        public Paint.Join o;
        public float p;

        public c() {
            this.g = 0.0f;
            this.i = 1.0f;
            this.j = 1.0f;
            this.k = 0.0f;
            this.l = 1.0f;
            this.m = 0.0f;
            this.n = Paint.Cap.BUTT;
            this.o = Paint.Join.MITER;
            this.p = 4.0f;
        }

        public final Paint.Cap a(int i, Paint.Cap cap) {
            return i != 0 ? i != 1 ? i != 2 ? cap : Paint.Cap.SQUARE : Paint.Cap.ROUND : Paint.Cap.BUTT;
        }

        public float getFillAlpha() {
            return this.j;
        }

        public int getFillColor() {
            return this.h.a();
        }

        public float getStrokeAlpha() {
            return this.i;
        }

        public int getStrokeColor() {
            return this.f.a();
        }

        public float getStrokeWidth() {
            return this.g;
        }

        public float getTrimPathEnd() {
            return this.l;
        }

        public float getTrimPathOffset() {
            return this.m;
        }

        public float getTrimPathStart() {
            return this.k;
        }

        public void setFillAlpha(float f) {
            this.j = f;
        }

        public void setFillColor(int i) {
            this.h.a(i);
        }

        public void setStrokeAlpha(float f) {
            this.i = f;
        }

        public void setStrokeColor(int i) {
            this.f.a(i);
        }

        public void setStrokeWidth(float f) {
            this.g = f;
        }

        public void setTrimPathEnd(float f) {
            this.l = f;
        }

        public void setTrimPathOffset(float f) {
            this.m = f;
        }

        public void setTrimPathStart(float f) {
            this.k = f;
        }

        public final Paint.Join a(int i, Paint.Join join) {
            if (i == 0) {
                return Paint.Join.MITER;
            }
            if (i != 1) {
                return i != 2 ? join : Paint.Join.BEVEL;
            }
            return Paint.Join.ROUND;
        }

        public void a(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray typedArrayA = l8.a(resources, theme, attributeSet, oh.c);
            a(typedArrayA, xmlPullParser, theme);
            typedArrayA.recycle();
        }

        public final void a(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) {
            this.f9625e = null;
            if (l8.a(xmlPullParser, "pathData")) {
                String string = typedArray.getString(0);
                if (string != null) {
                    this.b = string;
                }
                String string2 = typedArray.getString(2);
                if (string2 != null) {
                    this.f9628a = p8.a(string2);
                }
                this.h = l8.a(typedArray, xmlPullParser, theme, Constant.Name.FILL_COLOR, 1, 0);
                this.j = l8.a(typedArray, xmlPullParser, "fillAlpha", 12, this.j);
                this.n = a(l8.b(typedArray, xmlPullParser, "strokeLineCap", 8, -1), this.n);
                this.o = a(l8.b(typedArray, xmlPullParser, "strokeLineJoin", 9, -1), this.o);
                this.p = l8.a(typedArray, xmlPullParser, "strokeMiterLimit", 10, this.p);
                this.f = l8.a(typedArray, xmlPullParser, theme, Constant.Name.STROKE_COLOR, 3, 0);
                this.i = l8.a(typedArray, xmlPullParser, "strokeAlpha", 11, this.i);
                this.g = l8.a(typedArray, xmlPullParser, Constant.Name.STROKE_WIDTH, 4, this.g);
                this.l = l8.a(typedArray, xmlPullParser, "trimPathEnd", 6, this.l);
                this.m = l8.a(typedArray, xmlPullParser, "trimPathOffset", 7, this.m);
                this.k = l8.a(typedArray, xmlPullParser, "trimPathStart", 5, this.k);
                this.c = l8.b(typedArray, xmlPullParser, "fillType", 13, this.c);
            }
        }

        public c(c cVar) {
            super(cVar);
            this.g = 0.0f;
            this.i = 1.0f;
            this.j = 1.0f;
            this.k = 0.0f;
            this.l = 1.0f;
            this.m = 0.0f;
            this.n = Paint.Cap.BUTT;
            this.o = Paint.Join.MITER;
            this.p = 4.0f;
            this.f9625e = cVar.f9625e;
            this.f = cVar.f;
            this.g = cVar.g;
            this.i = cVar.i;
            this.h = cVar.h;
            this.c = cVar.c;
            this.j = cVar.j;
            this.k = cVar.k;
            this.l = cVar.l;
            this.m = cVar.m;
            this.n = cVar.n;
            this.o = cVar.o;
            this.p = cVar.p;
        }

        @Override // supwisdom.wh.e
        public boolean a() {
            return this.h.d() || this.f.d();
        }

        @Override // supwisdom.wh.e
        public boolean a(int[] iArr) {
            return this.f.a(iArr) | this.h.a(iArr);
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class d extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Matrix f9626a;
        public final ArrayList<e> b;
        public float c;
        public float d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public float f9627e;
        public float f;
        public float g;
        public float h;
        public float i;
        public final Matrix j;
        public int k;
        public int[] l;
        public String m;

        public d(d dVar, j4<String, Object> j4Var) {
            f bVar;
            super();
            this.f9626a = new Matrix();
            this.b = new ArrayList<>();
            this.c = 0.0f;
            this.d = 0.0f;
            this.f9627e = 0.0f;
            this.f = 1.0f;
            this.g = 1.0f;
            this.h = 0.0f;
            this.i = 0.0f;
            this.j = new Matrix();
            this.m = null;
            this.c = dVar.c;
            this.d = dVar.d;
            this.f9627e = dVar.f9627e;
            this.f = dVar.f;
            this.g = dVar.g;
            this.h = dVar.h;
            this.i = dVar.i;
            this.l = dVar.l;
            String str = dVar.m;
            this.m = str;
            this.k = dVar.k;
            if (str != null) {
                j4Var.put(str, this);
            }
            this.j.set(dVar.j);
            ArrayList<e> arrayList = dVar.b;
            for (int i = 0; i < arrayList.size(); i++) {
                e eVar = arrayList.get(i);
                if (eVar instanceof d) {
                    this.b.add(new d((d) eVar, j4Var));
                } else {
                    if (eVar instanceof c) {
                        bVar = new c((c) eVar);
                    } else {
                        if (!(eVar instanceof b)) {
                            throw new IllegalStateException("Unknown object in the tree!");
                        }
                        bVar = new b((b) eVar);
                    }
                    this.b.add(bVar);
                    String str2 = bVar.b;
                    if (str2 != null) {
                        j4Var.put(str2, bVar);
                    }
                }
            }
        }

        public void a(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray typedArrayA = l8.a(resources, theme, attributeSet, oh.b);
            a(typedArrayA, xmlPullParser);
            typedArrayA.recycle();
        }

        public final void b() {
            this.j.reset();
            this.j.postTranslate(-this.d, -this.f9627e);
            this.j.postScale(this.f, this.g);
            this.j.postRotate(this.c, 0.0f, 0.0f);
            this.j.postTranslate(this.h + this.d, this.i + this.f9627e);
        }

        public String getGroupName() {
            return this.m;
        }

        public Matrix getLocalMatrix() {
            return this.j;
        }

        public float getPivotX() {
            return this.d;
        }

        public float getPivotY() {
            return this.f9627e;
        }

        public float getRotation() {
            return this.c;
        }

        public float getScaleX() {
            return this.f;
        }

        public float getScaleY() {
            return this.g;
        }

        public float getTranslateX() {
            return this.h;
        }

        public float getTranslateY() {
            return this.i;
        }

        public void setPivotX(float f) {
            if (f != this.d) {
                this.d = f;
                b();
            }
        }

        public void setPivotY(float f) {
            if (f != this.f9627e) {
                this.f9627e = f;
                b();
            }
        }

        public void setRotation(float f) {
            if (f != this.c) {
                this.c = f;
                b();
            }
        }

        public void setScaleX(float f) {
            if (f != this.f) {
                this.f = f;
                b();
            }
        }

        public void setScaleY(float f) {
            if (f != this.g) {
                this.g = f;
                b();
            }
        }

        public void setTranslateX(float f) {
            if (f != this.h) {
                this.h = f;
                b();
            }
        }

        public void setTranslateY(float f) {
            if (f != this.i) {
                this.i = f;
                b();
            }
        }

        public final void a(TypedArray typedArray, XmlPullParser xmlPullParser) {
            this.l = null;
            this.c = l8.a(typedArray, xmlPullParser, "rotation", 5, this.c);
            this.d = typedArray.getFloat(1, this.d);
            this.f9627e = typedArray.getFloat(2, this.f9627e);
            this.f = l8.a(typedArray, xmlPullParser, "scaleX", 3, this.f);
            this.g = l8.a(typedArray, xmlPullParser, "scaleY", 4, this.g);
            this.h = l8.a(typedArray, xmlPullParser, "translateX", 6, this.h);
            this.i = l8.a(typedArray, xmlPullParser, "translateY", 7, this.i);
            String string = typedArray.getString(0);
            if (string != null) {
                this.m = string;
            }
            b();
        }

        @Override // supwisdom.wh.e
        public boolean a() {
            for (int i = 0; i < this.b.size(); i++) {
                if (this.b.get(i).a()) {
                    return true;
                }
            }
            return false;
        }

        @Override // supwisdom.wh.e
        public boolean a(int[] iArr) {
            boolean zA = false;
            for (int i = 0; i < this.b.size(); i++) {
                zA |= this.b.get(i).a(iArr);
            }
            return zA;
        }

        public d() {
            super();
            this.f9626a = new Matrix();
            this.b = new ArrayList<>();
            this.c = 0.0f;
            this.d = 0.0f;
            this.f9627e = 0.0f;
            this.f = 1.0f;
            this.g = 1.0f;
            this.h = 0.0f;
            this.i = 0.0f;
            this.j = new Matrix();
            this.m = null;
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static abstract class f extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public p8.b[] f9628a;
        public String b;
        public int c;
        public int d;

        public f() {
            super();
            this.f9628a = null;
            this.c = 0;
        }

        public void a(Path path) {
            path.reset();
            p8.b[] bVarArr = this.f9628a;
            if (bVarArr != null) {
                p8.b.a(bVarArr, path);
            }
        }

        public boolean b() {
            return false;
        }

        public p8.b[] getPathData() {
            return this.f9628a;
        }

        public String getPathName() {
            return this.b;
        }

        public void setPathData(p8.b[] bVarArr) {
            if (p8.a(this.f9628a, bVarArr)) {
                p8.b(this.f9628a, bVarArr);
            } else {
                this.f9628a = p8.a(bVarArr);
            }
        }

        public f(f fVar) {
            super();
            this.f9628a = null;
            this.c = 0;
            this.b = fVar.b;
            this.d = fVar.d;
            this.f9628a = p8.a(fVar.f9628a);
        }
    }

    public static wh a(Resources resources, int i2, Resources.Theme theme) {
        int next;
        if (Build.VERSION.SDK_INT >= 24) {
            wh whVar = new wh();
            whVar.f9511a = k8.b(resources, i2, theme);
            new i(whVar.f9511a.getConstantState());
            return whVar;
        }
        try {
            XmlResourceParser xml = resources.getXml(i2);
            AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
            do {
                next = xml.next();
                if (next == 2) {
                    break;
                }
            } while (next != 1);
            if (next == 2) {
                return createFromXmlInner(resources, (XmlPullParser) xml, attributeSetAsAttributeSet, theme);
            }
            throw new XmlPullParserException("No start tag found");
        } catch (IOException e2) {
            Log.e("VectorDrawableCompat", "parser error", e2);
            return null;
        } catch (XmlPullParserException e3) {
            Log.e("VectorDrawableCompat", "parser error", e3);
            return null;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        Drawable drawable = this.f9511a;
        if (drawable != null) {
            y8.a(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        h hVar = this.b;
        hVar.b = new g();
        TypedArray typedArrayA = l8.a(resources, theme, attributeSet, oh.f8671a);
        a(typedArrayA, xmlPullParser, theme);
        typedArrayA.recycle();
        hVar.f9631a = getChangingConfigurations();
        hVar.k = true;
        a(resources, xmlPullParser, attributeSet, theme);
        this.c = a(this.c, hVar.c, hVar.d);
    }

    public wh(h hVar) {
        this.f = true;
        this.g = new float[9];
        this.h = new Matrix();
        this.i = new Rect();
        this.b = hVar;
        this.c = a(this.c, hVar.c, hVar.d);
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    public static class g {
        public static final Matrix q = new Matrix();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Path f9629a;
        public final Path b;
        public final Matrix c;
        public Paint d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Paint f9630e;
        public PathMeasure f;
        public int g;
        public final d h;
        public float i;
        public float j;
        public float k;
        public float l;
        public int m;
        public String n;
        public Boolean o;
        public final j4<String, Object> p;

        public g() {
            this.c = new Matrix();
            this.i = 0.0f;
            this.j = 0.0f;
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 255;
            this.n = null;
            this.o = null;
            this.p = new j4<>();
            this.h = new d();
            this.f9629a = new Path();
            this.b = new Path();
        }

        public static float a(float f, float f2, float f3, float f4) {
            return (f * f4) - (f2 * f3);
        }

        public final void a(d dVar, Matrix matrix, Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            dVar.f9626a.set(matrix);
            dVar.f9626a.preConcat(dVar.j);
            canvas.save();
            for (int i3 = 0; i3 < dVar.b.size(); i3++) {
                e eVar = dVar.b.get(i3);
                if (eVar instanceof d) {
                    a((d) eVar, dVar.f9626a, canvas, i, i2, colorFilter);
                } else if (eVar instanceof f) {
                    a(dVar, (f) eVar, canvas, i, i2, colorFilter);
                }
            }
            canvas.restore();
        }

        public float getAlpha() {
            return getRootAlpha() / 255.0f;
        }

        public int getRootAlpha() {
            return this.m;
        }

        public void setAlpha(float f) {
            setRootAlpha((int) (f * 255.0f));
        }

        public void setRootAlpha(int i) {
            this.m = i;
        }

        public void a(Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            a(this.h, q, canvas, i, i2, colorFilter);
        }

        public g(g gVar) {
            this.c = new Matrix();
            this.i = 0.0f;
            this.j = 0.0f;
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 255;
            this.n = null;
            this.o = null;
            j4<String, Object> j4Var = new j4<>();
            this.p = j4Var;
            this.h = new d(gVar.h, j4Var);
            this.f9629a = new Path(gVar.f9629a);
            this.b = new Path(gVar.b);
            this.i = gVar.i;
            this.j = gVar.j;
            this.k = gVar.k;
            this.l = gVar.l;
            this.g = gVar.g;
            this.m = gVar.m;
            this.n = gVar.n;
            String str = gVar.n;
            if (str != null) {
                this.p.put(str, this);
            }
            this.o = gVar.o;
        }

        public final void a(d dVar, f fVar, Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            float f = i / this.k;
            float f2 = i2 / this.l;
            float fMin = Math.min(f, f2);
            Matrix matrix = dVar.f9626a;
            this.c.set(matrix);
            this.c.postScale(f, f2);
            float fA = a(matrix);
            if (fA == 0.0f) {
                return;
            }
            fVar.a(this.f9629a);
            Path path = this.f9629a;
            this.b.reset();
            if (fVar.b()) {
                this.b.setFillType(fVar.c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                this.b.addPath(path, this.c);
                canvas.clipPath(this.b);
                return;
            }
            c cVar = (c) fVar;
            if (cVar.k != 0.0f || cVar.l != 1.0f) {
                float f3 = cVar.k;
                float f4 = cVar.m;
                float f5 = (f3 + f4) % 1.0f;
                float f6 = (cVar.l + f4) % 1.0f;
                if (this.f == null) {
                    this.f = new PathMeasure();
                }
                this.f.setPath(this.f9629a, false);
                float length = this.f.getLength();
                float f7 = f5 * length;
                float f8 = f6 * length;
                path.reset();
                if (f7 > f8) {
                    this.f.getSegment(f7, length, path, true);
                    this.f.getSegment(0.0f, f8, path, true);
                } else {
                    this.f.getSegment(f7, f8, path, true);
                }
                path.rLineTo(0.0f, 0.0f);
            }
            this.b.addPath(path, this.c);
            if (cVar.h.e()) {
                g8 g8Var = cVar.h;
                if (this.f9630e == null) {
                    Paint paint = new Paint(1);
                    this.f9630e = paint;
                    paint.setStyle(Paint.Style.FILL);
                }
                Paint paint2 = this.f9630e;
                if (g8Var.c()) {
                    Shader shaderB = g8Var.b();
                    shaderB.setLocalMatrix(this.c);
                    paint2.setShader(shaderB);
                    paint2.setAlpha(Math.round(cVar.j * 255.0f));
                } else {
                    paint2.setShader(null);
                    paint2.setAlpha(255);
                    paint2.setColor(wh.a(g8Var.a(), cVar.j));
                }
                paint2.setColorFilter(colorFilter);
                this.b.setFillType(cVar.c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                canvas.drawPath(this.b, paint2);
            }
            if (cVar.f.e()) {
                g8 g8Var2 = cVar.f;
                if (this.d == null) {
                    Paint paint3 = new Paint(1);
                    this.d = paint3;
                    paint3.setStyle(Paint.Style.STROKE);
                }
                Paint paint4 = this.d;
                Paint.Join join = cVar.o;
                if (join != null) {
                    paint4.setStrokeJoin(join);
                }
                Paint.Cap cap = cVar.n;
                if (cap != null) {
                    paint4.setStrokeCap(cap);
                }
                paint4.setStrokeMiter(cVar.p);
                if (g8Var2.c()) {
                    Shader shaderB2 = g8Var2.b();
                    shaderB2.setLocalMatrix(this.c);
                    paint4.setShader(shaderB2);
                    paint4.setAlpha(Math.round(cVar.i * 255.0f));
                } else {
                    paint4.setShader(null);
                    paint4.setAlpha(255);
                    paint4.setColor(wh.a(g8Var2.a(), cVar.i));
                }
                paint4.setColorFilter(colorFilter);
                paint4.setStrokeWidth(cVar.g * fMin * fA);
                canvas.drawPath(this.b, paint4);
            }
        }

        public final float a(Matrix matrix) {
            float[] fArr = {0.0f, 1.0f, 1.0f, 0.0f};
            matrix.mapVectors(fArr);
            float fHypot = (float) Math.hypot(fArr[0], fArr[1]);
            float fHypot2 = (float) Math.hypot(fArr[2], fArr[3]);
            float fA = a(fArr[0], fArr[1], fArr[2], fArr[3]);
            float fMax = Math.max(fHypot, fHypot2);
            if (fMax > 0.0f) {
                return Math.abs(fA) / fMax;
            }
            return 0.0f;
        }

        public boolean a() {
            if (this.o == null) {
                this.o = Boolean.valueOf(this.h.a());
            }
            return this.o.booleanValue();
        }

        public boolean a(int[] iArr) {
            return this.h.a(iArr);
        }
    }

    public static int a(int i2, float f2) {
        return (i2 & X25519Field.M24) | (((int) (Color.alpha(i2) * f2)) << 24);
    }

    public static PorterDuff.Mode a(int i2, PorterDuff.Mode mode) {
        if (i2 == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i2 == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i2 != 9) {
            switch (i2) {
                case 14:
                    return PorterDuff.Mode.MULTIPLY;
                case 15:
                    return PorterDuff.Mode.SCREEN;
                case 16:
                    return PorterDuff.Mode.ADD;
                default:
                    return mode;
            }
        }
        return PorterDuff.Mode.SRC_ATOP;
    }

    public final void a(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) throws XmlPullParserException {
        h hVar = this.b;
        g gVar = hVar.b;
        hVar.d = a(l8.b(typedArray, xmlPullParser, "tintMode", 6, -1), PorterDuff.Mode.SRC_IN);
        ColorStateList colorStateListA = l8.a(typedArray, xmlPullParser, theme, "tint", 1);
        if (colorStateListA != null) {
            hVar.c = colorStateListA;
        }
        hVar.f9632e = l8.a(typedArray, xmlPullParser, "autoMirrored", 5, hVar.f9632e);
        gVar.k = l8.a(typedArray, xmlPullParser, "viewportWidth", 7, gVar.k);
        float fA = l8.a(typedArray, xmlPullParser, "viewportHeight", 8, gVar.l);
        gVar.l = fA;
        if (gVar.k <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        }
        if (fA > 0.0f) {
            gVar.i = typedArray.getDimension(3, gVar.i);
            float dimension = typedArray.getDimension(2, gVar.j);
            gVar.j = dimension;
            if (gVar.i <= 0.0f) {
                throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires width > 0");
            }
            if (dimension > 0.0f) {
                gVar.setAlpha(l8.a(typedArray, xmlPullParser, Constant.JSONKEY.ALPHE, 4, gVar.getAlpha()));
                String string = typedArray.getString(0);
                if (string != null) {
                    gVar.n = string;
                    gVar.p.put(string, gVar);
                    return;
                }
                return;
            }
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires height > 0");
        }
        throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
    }

    public final void a(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        h hVar = this.b;
        g gVar = hVar.b;
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(gVar.h);
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        boolean z = true;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                d dVar = (d) arrayDeque.peek();
                if ("path".equals(name)) {
                    c cVar = new c();
                    cVar.a(resources, attributeSet, theme, xmlPullParser);
                    dVar.b.add(cVar);
                    if (cVar.getPathName() != null) {
                        gVar.p.put(cVar.getPathName(), cVar);
                    }
                    z = false;
                    hVar.f9631a = cVar.d | hVar.f9631a;
                } else if ("clip-path".equals(name)) {
                    b bVar = new b();
                    bVar.a(resources, attributeSet, theme, xmlPullParser);
                    dVar.b.add(bVar);
                    if (bVar.getPathName() != null) {
                        gVar.p.put(bVar.getPathName(), bVar);
                    }
                    hVar.f9631a = bVar.d | hVar.f9631a;
                } else if ("group".equals(name)) {
                    d dVar2 = new d();
                    dVar2.a(resources, attributeSet, theme, xmlPullParser);
                    dVar.b.add(dVar2);
                    arrayDeque.push(dVar2);
                    if (dVar2.getGroupName() != null) {
                        gVar.p.put(dVar2.getGroupName(), dVar2);
                    }
                    hVar.f9631a = dVar2.k | hVar.f9631a;
                }
            } else if (eventType == 3 && "group".equals(xmlPullParser.getName())) {
                arrayDeque.pop();
            }
            eventType = xmlPullParser.next();
        }
        if (z) {
            throw new XmlPullParserException("no path defined");
        }
    }

    public void a(boolean z) {
        this.f = z;
    }

    public final boolean a() {
        return Build.VERSION.SDK_INT >= 17 && isAutoMirrored() && y8.e(this) == 1;
    }
}
