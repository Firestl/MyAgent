package supwisdom;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.StateSet;
import androidx.appcompat.resources.R;
import io.dcloud.common.constant.AbsoluteConst;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import supwisdom.d1;
import supwisdom.f1;

/* JADX INFO: compiled from: AnimatedStateListDrawableCompat.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedAPI"})
public class c1 extends f1 implements z8 {
    public c o;
    public g p;
    public int q;
    public int r;
    public boolean s;

    /* JADX INFO: compiled from: AnimatedStateListDrawableCompat.java */
    public static class b extends g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Animatable f7131a;

        public b(Animatable animatable) {
            super();
            this.f7131a = animatable;
        }

        @Override // supwisdom.c1.g
        public void c() {
            this.f7131a.start();
        }

        @Override // supwisdom.c1.g
        public void d() {
            this.f7131a.stop();
        }
    }

    /* JADX INFO: compiled from: AnimatedStateListDrawableCompat.java */
    public static class c extends f1.a {
        public m4<Long> K;
        public q4<Integer> L;

        public c(c cVar, c1 c1Var, Resources resources) {
            super(cVar, c1Var, resources);
            if (cVar != null) {
                this.K = cVar.K;
                this.L = cVar.L;
            } else {
                this.K = new m4<>();
                this.L = new q4<>();
            }
        }

        public static long f(int i, int i2) {
            return ((long) i2) | (((long) i) << 32);
        }

        public int a(int i, int i2, Drawable drawable, boolean z) {
            int iA = super.a(drawable);
            long jF = f(i, i2);
            long j = z ? 8589934592L : 0L;
            long j2 = iA;
            this.K.a(jF, Long.valueOf(j2 | j));
            if (z) {
                this.K.a(f(i2, i), Long.valueOf(4294967296L | j2 | j));
            }
            return iA;
        }

        public int b(int[] iArr) {
            int iA = super.a(iArr);
            return iA >= 0 ? iA : super.a(StateSet.WILD_CARD);
        }

        public int c(int i, int i2) {
            return (int) this.K.b(f(i, i2), -1L).longValue();
        }

        public int d(int i) {
            if (i < 0) {
                return 0;
            }
            return this.L.b(i, 0).intValue();
        }

        public boolean e(int i, int i2) {
            return (this.K.b(f(i, i2), -1L).longValue() & 8589934592L) != 0;
        }

        @Override // supwisdom.f1.a, supwisdom.d1.c
        public void n() {
            this.K = this.K.clone();
            this.L = this.L.clone();
        }

        @Override // supwisdom.f1.a, android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new c1(this, null);
        }

        public boolean d(int i, int i2) {
            return (this.K.b(f(i, i2), -1L).longValue() & 4294967296L) != 0;
        }

        @Override // supwisdom.f1.a, android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new c1(this, resources);
        }

        public int a(int[] iArr, Drawable drawable, int i) {
            int iA = super.a(iArr, drawable);
            this.L.c(iA, Integer.valueOf(i));
            return iA;
        }
    }

    /* JADX INFO: compiled from: AnimatedStateListDrawableCompat.java */
    public static class d extends g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final qh f7132a;

        public d(qh qhVar) {
            super();
            this.f7132a = qhVar;
        }

        @Override // supwisdom.c1.g
        public void c() {
            this.f7132a.start();
        }

        @Override // supwisdom.c1.g
        public void d() {
            this.f7132a.stop();
        }
    }

    /* JADX INFO: compiled from: AnimatedStateListDrawableCompat.java */
    public static class e extends g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ObjectAnimator f7133a;
        public final boolean b;

        public e(AnimationDrawable animationDrawable, boolean z, boolean z2) {
            super();
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            int i = z ? numberOfFrames - 1 : 0;
            int i2 = z ? 0 : numberOfFrames - 1;
            f fVar = new f(animationDrawable, z);
            ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(animationDrawable, "currentIndex", i, i2);
            if (Build.VERSION.SDK_INT >= 18) {
                objectAnimatorOfInt.setAutoCancel(true);
            }
            objectAnimatorOfInt.setDuration(fVar.a());
            objectAnimatorOfInt.setInterpolator(fVar);
            this.b = z2;
            this.f7133a = objectAnimatorOfInt;
        }

        @Override // supwisdom.c1.g
        public boolean a() {
            return this.b;
        }

        @Override // supwisdom.c1.g
        public void b() {
            this.f7133a.reverse();
        }

        @Override // supwisdom.c1.g
        public void c() {
            this.f7133a.start();
        }

        @Override // supwisdom.c1.g
        public void d() {
            this.f7133a.cancel();
        }
    }

    /* JADX INFO: compiled from: AnimatedStateListDrawableCompat.java */
    public static abstract class g {
        public g() {
        }

        public boolean a() {
            return false;
        }

        public void b() {
        }

        public abstract void c();

        public abstract void d();
    }

    public c1() {
        this(null, null);
    }

    public static c1 e(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        String name = xmlPullParser.getName();
        if (name.equals("animated-selector")) {
            c1 c1Var = new c1();
            c1Var.a(context, resources, xmlPullParser, attributeSet, theme);
            return c1Var;
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid animated-selector tag " + name);
    }

    public final boolean b(int i) {
        int iB;
        int iC;
        g bVar;
        g gVar = this.p;
        if (gVar == null) {
            iB = b();
        } else {
            if (i == this.q) {
                return true;
            }
            if (i == this.r && gVar.a()) {
                gVar.b();
                this.q = this.r;
                this.r = i;
                return true;
            }
            iB = this.q;
            gVar.d();
        }
        this.p = null;
        this.r = -1;
        this.q = -1;
        c cVar = this.o;
        int iD = cVar.d(iB);
        int iD2 = cVar.d(i);
        if (iD2 == 0 || iD == 0 || (iC = cVar.c(iD, iD2)) < 0) {
            return false;
        }
        boolean zE = cVar.e(iD, iD2);
        a(iC);
        Object current = getCurrent();
        if (current instanceof AnimationDrawable) {
            bVar = new e((AnimationDrawable) current, cVar.d(iD, iD2), zE);
        } else {
            if (!(current instanceof qh)) {
                if (current instanceof Animatable) {
                    bVar = new b((Animatable) current);
                }
                return false;
            }
            bVar = new d((qh) current);
        }
        bVar.c();
        this.p = bVar;
        this.r = iB;
        this.q = i;
        return true;
    }

    public final int c(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        TypedArray typedArrayA = l8.a(resources, theme, attributeSet, R.styleable.AnimatedStateListDrawableItem);
        int resourceId = typedArrayA.getResourceId(R.styleable.AnimatedStateListDrawableItem_android_id, 0);
        int resourceId2 = typedArrayA.getResourceId(R.styleable.AnimatedStateListDrawableItem_android_drawable, -1);
        Drawable drawableB = resourceId2 > 0 ? f3.a().b(context, resourceId2) : null;
        typedArrayA.recycle();
        int[] iArrA = a(attributeSet);
        if (drawableB == null) {
            do {
                next = xmlPullParser.next();
            } while (next == 4);
            if (next != 2) {
                throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
            }
            drawableB = xmlPullParser.getName().equals("vector") ? wh.createFromXmlInner(resources, xmlPullParser, attributeSet, theme) : Build.VERSION.SDK_INT >= 21 ? Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme) : Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet);
        }
        if (drawableB != null) {
            return this.o.a(iArrA, drawableB, resourceId);
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
    }

    public final void d() {
        onStateChange(getState());
    }

    @Override // supwisdom.f1, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // supwisdom.d1, android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        super.jumpToCurrentState();
        g gVar = this.p;
        if (gVar != null) {
            gVar.d();
            this.p = null;
            a(this.q);
            this.q = -1;
            this.r = -1;
        }
    }

    @Override // supwisdom.f1, supwisdom.d1, android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.s) {
            super.mutate();
            if (this == this) {
                this.o.n();
                this.s = true;
            }
        }
        return this;
    }

    @Override // supwisdom.f1, supwisdom.d1, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        int iB = this.o.b(iArr);
        boolean z = iB != b() && (b(iB) || a(iB));
        Drawable current = getCurrent();
        return current != null ? z | current.setState(iArr) : z;
    }

    @Override // supwisdom.d1, android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (this.p != null && (visible || z2)) {
            if (z) {
                this.p.c();
            } else {
                jumpToCurrentState();
            }
        }
        return visible;
    }

    public c1(c cVar, Resources resources) {
        super(null);
        this.q = -1;
        this.r = -1;
        a(new c(cVar, this, resources));
        onStateChange(getState());
        jumpToCurrentState();
    }

    public final int d(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        TypedArray typedArrayA = l8.a(resources, theme, attributeSet, R.styleable.AnimatedStateListDrawableTransition);
        int resourceId = typedArrayA.getResourceId(R.styleable.AnimatedStateListDrawableTransition_android_fromId, -1);
        int resourceId2 = typedArrayA.getResourceId(R.styleable.AnimatedStateListDrawableTransition_android_toId, -1);
        int resourceId3 = typedArrayA.getResourceId(R.styleable.AnimatedStateListDrawableTransition_android_drawable, -1);
        Drawable drawableB = resourceId3 > 0 ? f3.a().b(context, resourceId3) : null;
        boolean z = typedArrayA.getBoolean(R.styleable.AnimatedStateListDrawableTransition_android_reversible, false);
        typedArrayA.recycle();
        if (drawableB == null) {
            do {
                next = xmlPullParser.next();
            } while (next == 4);
            if (next != 2) {
                throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable");
            }
            drawableB = xmlPullParser.getName().equals("animated-vector") ? qh.a(context, resources, xmlPullParser, attributeSet, theme) : Build.VERSION.SDK_INT >= 21 ? Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme) : Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet);
        }
        if (drawableB == null) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable");
        }
        if (resourceId != -1 && resourceId2 != -1) {
            return this.o.a(resourceId, resourceId2, drawableB, z);
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires 'fromId' & 'toId' attributes");
    }

    public void a(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        TypedArray typedArrayA = l8.a(resources, theme, attributeSet, R.styleable.AnimatedStateListDrawableCompat);
        setVisible(typedArrayA.getBoolean(R.styleable.AnimatedStateListDrawableCompat_android_visible, true), true);
        a(typedArrayA);
        a(resources);
        typedArrayA.recycle();
        b(context, resources, xmlPullParser, attributeSet, theme);
        d();
    }

    /* JADX INFO: compiled from: AnimatedStateListDrawableCompat.java */
    public static class f implements TimeInterpolator {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int[] f7134a;
        public int b;
        public int c;

        public f(AnimationDrawable animationDrawable, boolean z) {
            a(animationDrawable, z);
        }

        public int a(AnimationDrawable animationDrawable, boolean z) {
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            this.b = numberOfFrames;
            int[] iArr = this.f7134a;
            if (iArr == null || iArr.length < numberOfFrames) {
                this.f7134a = new int[numberOfFrames];
            }
            int[] iArr2 = this.f7134a;
            int i = 0;
            for (int i2 = 0; i2 < numberOfFrames; i2++) {
                int duration = animationDrawable.getDuration(z ? (numberOfFrames - i2) - 1 : i2);
                iArr2[i2] = duration;
                i += duration;
            }
            this.c = i;
            return i;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            int i = (int) ((f * this.c) + 0.5f);
            int i2 = this.b;
            int[] iArr = this.f7134a;
            int i3 = 0;
            while (i3 < i2 && i >= iArr[i3]) {
                i -= iArr[i3];
                i3++;
            }
            return (i3 / i2) + (i3 < i2 ? i / this.c : 0.0f);
        }

        public int a() {
            return this.c;
        }
    }

    public final void a(TypedArray typedArray) {
        c cVar = this.o;
        if (Build.VERSION.SDK_INT >= 21) {
            cVar.d |= typedArray.getChangingConfigurations();
        }
        cVar.b(typedArray.getBoolean(R.styleable.AnimatedStateListDrawableCompat_android_variablePadding, cVar.i));
        cVar.a(typedArray.getBoolean(R.styleable.AnimatedStateListDrawableCompat_android_constantSize, cVar.l));
        cVar.b(typedArray.getInt(R.styleable.AnimatedStateListDrawableCompat_android_enterFadeDuration, cVar.A));
        cVar.c(typedArray.getInt(R.styleable.AnimatedStateListDrawableCompat_android_exitFadeDuration, cVar.B));
        setDither(typedArray.getBoolean(R.styleable.AnimatedStateListDrawableCompat_android_dither, cVar.x));
    }

    @Override // supwisdom.f1, supwisdom.d1
    public c a() {
        return new c(this.o, this, null);
    }

    @Override // supwisdom.f1, supwisdom.d1
    public void a(d1.c cVar) {
        super.a(cVar);
        if (cVar instanceof c) {
            this.o = (c) cVar;
        }
    }

    public final void b(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth() + 1;
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return;
            }
            int depth2 = xmlPullParser.getDepth();
            if (depth2 < depth && next == 3) {
                return;
            }
            if (next == 2 && depth2 <= depth) {
                if (xmlPullParser.getName().equals(AbsoluteConst.XML_ITEM)) {
                    c(context, resources, xmlPullParser, attributeSet, theme);
                } else if (xmlPullParser.getName().equals(AbsoluteConst.JSON_KEY_TRANSITION)) {
                    d(context, resources, xmlPullParser, attributeSet, theme);
                }
            }
        }
    }
}
