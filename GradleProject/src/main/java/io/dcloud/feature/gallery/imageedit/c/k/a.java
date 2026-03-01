package io.dcloud.feature.gallery.imageedit.c.k;

import android.graphics.Matrix;
import android.graphics.RectF;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Matrix f6547a = new Matrix();

    public static int a(int i) {
        int i2 = 1;
        for (int i3 = i; i3 > 1; i3 >>= 1) {
            i2 <<= 1;
        }
        return i2 != i ? i2 << 1 : i2;
    }

    public static void a(RectF rectF, RectF rectF2) {
        rectF2.offset(rectF.centerX() - rectF2.centerX(), rectF.centerY() - rectF2.centerY());
    }

    public static io.dcloud.feature.gallery.imageedit.c.i.a b(RectF rectF, RectF rectF2, float f, float f2) {
        io.dcloud.feature.gallery.imageedit.c.i.a aVar = new io.dcloud.feature.gallery.imageedit.c.i.a(0.0f, 0.0f, 1.0f, 0.0f);
        if (rectF2.contains(rectF)) {
            return aVar;
        }
        if (rectF2.width() < rectF.width() && rectF2.height() < rectF.height()) {
            aVar.c = Math.min(rectF.width() / rectF2.width(), rectF.height() / rectF2.height());
        }
        RectF rectF3 = new RectF();
        Matrix matrix = f6547a;
        float f3 = aVar.c;
        matrix.setScale(f3, f3, f, f2);
        matrix.mapRect(rectF3, rectF2);
        if (rectF3.width() < rectF.width()) {
            aVar.f6541a += rectF.centerX() - rectF3.centerX();
        } else {
            float f4 = rectF3.left;
            float f5 = rectF.left;
            if (f4 > f5) {
                aVar.f6541a += f5 - f4;
            } else {
                float f6 = rectF3.right;
                float f7 = rectF.right;
                if (f6 < f7) {
                    aVar.f6541a += f7 - f6;
                }
            }
        }
        if (rectF3.height() < rectF.height()) {
            aVar.b += rectF.centerY() - rectF3.centerY();
        } else {
            float f8 = rectF3.top;
            float f9 = rectF.top;
            if (f8 > f9) {
                aVar.b += f9 - f8;
            } else {
                float f10 = rectF3.bottom;
                float f11 = rectF.bottom;
                if (f10 < f11) {
                    aVar.b += f11 - f10;
                }
            }
        }
        return aVar;
    }

    public static void a(RectF rectF, RectF rectF2, float f) {
        a(rectF, rectF2, f, f, f, f);
    }

    public static void a(RectF rectF, RectF rectF2, float f, float f2, float f3, float f4) {
        if (rectF.isEmpty() || rectF2.isEmpty()) {
            return;
        }
        if (rectF.width() < f + f3) {
            f = 0.0f;
            f3 = 0.0f;
        }
        if (rectF.height() < f2 + f4) {
            f2 = 0.0f;
            f4 = 0.0f;
        }
        float fMin = Math.min(((rectF.width() - f) - f3) / rectF2.width(), ((rectF.height() - f2) - f4) / rectF2.height());
        rectF2.set(0.0f, 0.0f, rectF2.width() * fMin, rectF2.height() * fMin);
        rectF2.offset((rectF.centerX() + ((f - f3) / 2.0f)) - rectF2.centerX(), (rectF.centerY() + ((f2 - f4) / 2.0f)) - rectF2.centerY());
    }

    public static io.dcloud.feature.gallery.imageedit.c.i.a a(RectF rectF, RectF rectF2, boolean z) {
        io.dcloud.feature.gallery.imageedit.c.i.a aVar = new io.dcloud.feature.gallery.imageedit.c.i.a(0.0f, 0.0f, 1.0f, 0.0f);
        if (rectF2.contains(rectF) && !z) {
            return aVar;
        }
        if (z || (rectF2.width() < rectF.width() && rectF2.height() < rectF.height())) {
            aVar.c = Math.min(rectF.width() / rectF2.width(), rectF.height() / rectF2.height());
        }
        RectF rectF3 = new RectF();
        Matrix matrix = f6547a;
        float f = aVar.c;
        matrix.setScale(f, f, rectF2.centerX(), rectF2.centerY());
        matrix.mapRect(rectF3, rectF2);
        if (rectF3.width() < rectF.width()) {
            aVar.f6541a += rectF.centerX() - rectF3.centerX();
        } else {
            float f2 = rectF3.left;
            float f3 = rectF.left;
            if (f2 > f3) {
                aVar.f6541a += f3 - f2;
            } else {
                float f4 = rectF3.right;
                float f5 = rectF.right;
                if (f4 < f5) {
                    aVar.f6541a += f5 - f4;
                }
            }
        }
        if (rectF3.height() < rectF.height()) {
            aVar.b += rectF.centerY() - rectF3.centerY();
        } else {
            float f6 = rectF3.top;
            float f7 = rectF.top;
            if (f6 > f7) {
                aVar.b += f7 - f6;
            } else {
                float f8 = rectF3.bottom;
                float f9 = rectF.bottom;
                if (f8 < f9) {
                    aVar.b += f9 - f8;
                }
            }
        }
        return aVar;
    }

    public static io.dcloud.feature.gallery.imageedit.c.i.a b(RectF rectF, RectF rectF2) {
        io.dcloud.feature.gallery.imageedit.c.i.a aVar = new io.dcloud.feature.gallery.imageedit.c.i.a(0.0f, 0.0f, 1.0f, 0.0f);
        if (rectF.equals(rectF2)) {
            return aVar;
        }
        aVar.c = Math.max(rectF.width() / rectF2.width(), rectF.height() / rectF2.height());
        RectF rectF3 = new RectF();
        Matrix matrix = f6547a;
        float f = aVar.c;
        matrix.setScale(f, f, rectF2.centerX(), rectF2.centerY());
        matrix.mapRect(rectF3, rectF2);
        aVar.f6541a += rectF.centerX() - rectF3.centerX();
        aVar.b += rectF.centerY() - rectF3.centerY();
        return aVar;
    }

    public static io.dcloud.feature.gallery.imageedit.c.i.a a(RectF rectF, RectF rectF2, float f, float f2) {
        io.dcloud.feature.gallery.imageedit.c.i.a aVar = new io.dcloud.feature.gallery.imageedit.c.i.a(0.0f, 0.0f, 1.0f, 0.0f);
        if (rectF2.contains(rectF)) {
            return aVar;
        }
        if (rectF2.width() < rectF.width() || rectF2.height() < rectF.height()) {
            aVar.c = Math.max(rectF.width() / rectF2.width(), rectF.height() / rectF2.height());
        }
        RectF rectF3 = new RectF();
        Matrix matrix = f6547a;
        float f3 = aVar.c;
        matrix.setScale(f3, f3, f, f2);
        matrix.mapRect(rectF3, rectF2);
        float f4 = rectF3.left;
        float f5 = rectF.left;
        if (f4 > f5) {
            aVar.f6541a += f5 - f4;
        } else {
            float f6 = rectF3.right;
            float f7 = rectF.right;
            if (f6 < f7) {
                aVar.f6541a += f7 - f6;
            }
        }
        float f8 = rectF3.top;
        float f9 = rectF.top;
        if (f8 > f9) {
            aVar.b += f9 - f8;
        } else {
            float f10 = rectF3.bottom;
            float f11 = rectF.bottom;
            if (f10 < f11) {
                aVar.b += f11 - f10;
            }
        }
        return aVar;
    }
}
