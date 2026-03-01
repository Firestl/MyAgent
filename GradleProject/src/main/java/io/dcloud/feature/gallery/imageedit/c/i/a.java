package io.dcloud.feature.gallery.imageedit.c.i;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f6541a;
    public float b;
    public float c;
    public float d;

    public a(float f, float f2, float f3, float f4) {
        this.f6541a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
    }

    public void a(float f, float f2, float f3, float f4) {
        this.f6541a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
    }

    public String toString() {
        return "IMGHoming{x=" + this.f6541a + ", y=" + this.b + ", scale=" + this.c + ", rotate=" + this.d + Operators.BLOCK_END;
    }

    public void a(a aVar) {
        this.c *= aVar.c;
        this.f6541a -= aVar.f6541a;
        this.b -= aVar.b;
    }

    public static boolean a(a aVar, a aVar2) {
        return Float.compare(aVar.d, aVar2.d) != 0;
    }
}
