package io.dcloud.feature.gallery.imageedit.c.i;

import android.animation.TypeEvaluator;

/* JADX INFO: loaded from: classes2.dex */
public class b implements TypeEvaluator<a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a f6542a;

    @Override // android.animation.TypeEvaluator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public a evaluate(float f, a aVar, a aVar2) {
        float f2 = aVar.f6541a;
        float f3 = f2 + ((aVar2.f6541a - f2) * f);
        float f4 = aVar.b;
        float f5 = f4 + ((aVar2.b - f4) * f);
        float f6 = aVar.c;
        float f7 = f6 + ((aVar2.c - f6) * f);
        float f8 = aVar.d;
        float f9 = f8 + (f * (aVar2.d - f8));
        a aVar3 = this.f6542a;
        if (aVar3 == null) {
            this.f6542a = new a(f3, f5, f7, f9);
        } else {
            aVar3.a(f3, f5, f7, f9);
        }
        return this.f6542a;
    }
}
