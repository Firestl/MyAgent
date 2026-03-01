package com.igexin.push.core.i.a;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class b<T extends Drawable> implements l, m<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final T f3466a;

    public b(T t) {
        this.f3466a = (T) k.a(t);
    }

    @Override // com.igexin.push.core.i.a.m
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final T c() {
        Drawable.ConstantState constantState = this.f3466a.getConstantState();
        return constantState == null ? this.f3466a : (T) constantState.newDrawable();
    }

    @Override // com.igexin.push.core.i.a.l
    public void b() {
        T t = this.f3466a;
        if (t instanceof BitmapDrawable) {
            ((BitmapDrawable) t).getBitmap().prepareToDraw();
        } else if (t instanceof e) {
            ((e) t).a().prepareToDraw();
        }
    }
}
