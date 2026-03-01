package supwisdom;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import androidx.core.R;
import java.io.IOException;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: GradientColorInflaterCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class i8 {
    public static Shader a(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        String name = xmlPullParser.getName();
        if (!name.equals("gradient")) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid gradient color tag " + name);
        }
        TypedArray typedArrayA = l8.a(resources, theme, attributeSet, R.styleable.GradientColor);
        float fA = l8.a(typedArrayA, xmlPullParser, "startX", R.styleable.GradientColor_android_startX, 0.0f);
        float fA2 = l8.a(typedArrayA, xmlPullParser, "startY", R.styleable.GradientColor_android_startY, 0.0f);
        float fA3 = l8.a(typedArrayA, xmlPullParser, "endX", R.styleable.GradientColor_android_endX, 0.0f);
        float fA4 = l8.a(typedArrayA, xmlPullParser, "endY", R.styleable.GradientColor_android_endY, 0.0f);
        float fA5 = l8.a(typedArrayA, xmlPullParser, "centerX", R.styleable.GradientColor_android_centerX, 0.0f);
        float fA6 = l8.a(typedArrayA, xmlPullParser, "centerY", R.styleable.GradientColor_android_centerY, 0.0f);
        int iB = l8.b(typedArrayA, xmlPullParser, "type", R.styleable.GradientColor_android_type, 0);
        int iA = l8.a(typedArrayA, xmlPullParser, "startColor", R.styleable.GradientColor_android_startColor, 0);
        boolean zA = l8.a(xmlPullParser, "centerColor");
        int iA2 = l8.a(typedArrayA, xmlPullParser, "centerColor", R.styleable.GradientColor_android_centerColor, 0);
        int iA3 = l8.a(typedArrayA, xmlPullParser, "endColor", R.styleable.GradientColor_android_endColor, 0);
        int iB2 = l8.b(typedArrayA, xmlPullParser, "tileMode", R.styleable.GradientColor_android_tileMode, 0);
        float fA7 = l8.a(typedArrayA, xmlPullParser, "gradientRadius", R.styleable.GradientColor_android_gradientRadius, 0.0f);
        typedArrayA.recycle();
        a aVarA = a(b(resources, xmlPullParser, attributeSet, theme), iA, iA3, zA, iA2);
        if (iB != 1) {
            return iB != 2 ? new LinearGradient(fA, fA2, fA3, fA4, aVarA.f7927a, aVarA.b, a(iB2)) : new SweepGradient(fA5, fA6, aVarA.f7927a, aVarA.b);
        }
        if (fA7 > 0.0f) {
            return new RadialGradient(fA5, fA6, fA7, aVarA.f7927a, aVarA.b, a(iB2));
        }
        throw new XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0089, code lost:
    
        if (r4.size() <= 0) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0090, code lost:
    
        return new supwisdom.i8.a(r4, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0091, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static supwisdom.i8.a b(android.content.res.Resources r8, org.xmlpull.v1.XmlPullParser r9, android.util.AttributeSet r10, android.content.res.Resources.Theme r11) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            int r0 = r9.getDepth()
            r1 = 1
            int r0 = r0 + r1
            java.util.ArrayList r2 = new java.util.ArrayList
            r3 = 20
            r2.<init>(r3)
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>(r3)
        L12:
            int r3 = r9.next()
            if (r3 == r1) goto L85
            int r5 = r9.getDepth()
            if (r5 >= r0) goto L21
            r6 = 3
            if (r3 == r6) goto L85
        L21:
            r6 = 2
            if (r3 == r6) goto L25
            goto L12
        L25:
            if (r5 > r0) goto L12
            java.lang.String r3 = r9.getName()
            java.lang.String r5 = "item"
            boolean r3 = r3.equals(r5)
            if (r3 != 0) goto L34
            goto L12
        L34:
            int[] r3 = androidx.core.R.styleable.GradientColorItem
            android.content.res.TypedArray r3 = supwisdom.l8.a(r8, r11, r10, r3)
            int r5 = androidx.core.R.styleable.GradientColorItem_android_color
            boolean r5 = r3.hasValue(r5)
            int r6 = androidx.core.R.styleable.GradientColorItem_android_offset
            boolean r6 = r3.hasValue(r6)
            if (r5 == 0) goto L6a
            if (r6 == 0) goto L6a
            int r5 = androidx.core.R.styleable.GradientColorItem_android_color
            r6 = 0
            int r5 = r3.getColor(r5, r6)
            int r6 = androidx.core.R.styleable.GradientColorItem_android_offset
            r7 = 0
            float r6 = r3.getFloat(r6, r7)
            r3.recycle()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)
            r4.add(r3)
            java.lang.Float r3 = java.lang.Float.valueOf(r6)
            r2.add(r3)
            goto L12
        L6a:
            org.xmlpull.v1.XmlPullParserException r8 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r9 = r9.getPositionDescription()
            r10.append(r9)
            java.lang.String r9 = ": <item> tag requires a 'color' attribute and a 'offset' attribute!"
            r10.append(r9)
            java.lang.String r9 = r10.toString()
            r8.<init>(r9)
            throw r8
        L85:
            int r8 = r4.size()
            if (r8 <= 0) goto L91
            supwisdom.i8$a r8 = new supwisdom.i8$a
            r8.<init>(r4, r2)
            return r8
        L91:
            r8 = 0
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.i8.b(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):supwisdom.i8$a");
    }

    /* JADX INFO: compiled from: GradientColorInflaterCompat.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int[] f7927a;
        public final float[] b;

        public a(List<Integer> list, List<Float> list2) {
            int size = list.size();
            this.f7927a = new int[size];
            this.b = new float[size];
            for (int i = 0; i < size; i++) {
                this.f7927a[i] = list.get(i).intValue();
                this.b[i] = list2.get(i).floatValue();
            }
        }

        public a(int i, int i2) {
            this.f7927a = new int[]{i, i2};
            this.b = new float[]{0.0f, 1.0f};
        }

        public a(int i, int i2, int i3) {
            this.f7927a = new int[]{i, i2, i3};
            this.b = new float[]{0.0f, 0.5f, 1.0f};
        }
    }

    public static a a(a aVar, int i, int i2, boolean z, int i3) {
        if (aVar != null) {
            return aVar;
        }
        if (z) {
            return new a(i, i3, i2);
        }
        return new a(i, i2);
    }

    public static Shader.TileMode a(int i) {
        if (i == 1) {
            return Shader.TileMode.REPEAT;
        }
        if (i != 2) {
            return Shader.TileMode.CLAMP;
        }
        return Shader.TileMode.MIRROR;
    }
}
