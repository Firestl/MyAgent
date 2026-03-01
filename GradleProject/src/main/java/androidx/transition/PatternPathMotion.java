package androidx.transition;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;
import supwisdom.l8;
import supwisdom.og;
import supwisdom.p8;

/* JADX INFO: loaded from: classes.dex */
public class PatternPathMotion extends PathMotion {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Path f1432a = new Path();
    public final Matrix b = new Matrix();

    public PatternPathMotion() {
        this.f1432a.lineTo(1.0f, 0.0f);
    }

    public void a(Path path) {
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float[] fArr = new float[2];
        pathMeasure.getPosTan(pathMeasure.getLength(), fArr, null);
        float f = fArr[0];
        float f2 = fArr[1];
        pathMeasure.getPosTan(0.0f, fArr, null);
        float f3 = fArr[0];
        float f4 = fArr[1];
        if (f3 == f && f4 == f2) {
            throw new IllegalArgumentException("pattern must not end at the starting point");
        }
        this.b.setTranslate(-f3, -f4);
        float f5 = f - f3;
        float f6 = f2 - f4;
        float fA = 1.0f / a(f5, f6);
        this.b.postScale(fA, fA);
        this.b.postRotate((float) Math.toDegrees(-Math.atan2(f6, f5)));
        path.transform(this.b, this.f1432a);
    }

    public PatternPathMotion(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, og.i);
        try {
            String strA = l8.a(typedArrayObtainStyledAttributes, (XmlPullParser) attributeSet, "patternPathData", 0);
            if (strA != null) {
                a(p8.b(strA));
                return;
            }
            throw new RuntimeException("pathData must be supplied for patternPathMotion");
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // androidx.transition.PathMotion
    public Path a(float f, float f2, float f3, float f4) {
        float f5 = f3 - f;
        float f6 = f4 - f2;
        float fA = a(f5, f6);
        double dAtan2 = Math.atan2(f6, f5);
        this.b.setScale(fA, fA);
        this.b.postRotate((float) Math.toDegrees(dAtan2));
        this.b.postTranslate(f, f2);
        Path path = new Path();
        this.f1432a.transform(this.b, path);
        return path;
    }

    public static float a(float f, float f2) {
        return (float) Math.sqrt((f * f) + (f2 * f2));
    }
}
