package io.dcloud.feature.gallery.imageedit.c.j;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.View;
import io.dcloud.feature.gallery.imageedit.c.j.a;
import io.dcloud.feature.gallery.imageedit.c.j.e;

/* JADX INFO: loaded from: classes2.dex */
public class c<StickerView extends View & a> implements e, e.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public RectF f6545a;
    public StickerView b;
    public e.a c;
    public boolean d = false;

    public c(StickerView stickerview) {
        this.b = stickerview;
    }

    @Override // io.dcloud.feature.gallery.imageedit.c.j.e
    public boolean a() {
        if (b()) {
            return false;
        }
        this.d = true;
        c(this.b);
        return true;
    }

    @Override // io.dcloud.feature.gallery.imageedit.c.j.e
    public boolean b() {
        return this.d;
    }

    public boolean c() {
        return a(this.b);
    }

    @Override // io.dcloud.feature.gallery.imageedit.c.j.e
    public boolean dismiss() {
        if (!b()) {
            return false;
        }
        this.d = false;
        b(this.b);
        return true;
    }

    @Override // io.dcloud.feature.gallery.imageedit.c.j.e
    public RectF getFrame() {
        if (this.f6545a == null) {
            this.f6545a = new RectF(0.0f, 0.0f, this.b.getWidth(), this.b.getHeight());
            float x = this.b.getX() + this.b.getPivotX();
            float y = this.b.getY() + this.b.getPivotY();
            Matrix matrix = new Matrix();
            matrix.setTranslate(this.b.getX(), this.b.getY());
            matrix.postScale(this.b.getScaleX(), this.b.getScaleY(), x, y);
            matrix.mapRect(this.f6545a);
        }
        return this.f6545a;
    }

    @Override // io.dcloud.feature.gallery.imageedit.c.j.e
    public void b(e.a aVar) {
        this.c = null;
    }

    @Override // io.dcloud.feature.gallery.imageedit.c.j.e.a
    public <V extends View & a> void c(V v) {
        v.invalidate();
        e.a aVar = this.c;
        if (aVar != null) {
            aVar.c(v);
        }
    }

    @Override // io.dcloud.feature.gallery.imageedit.c.j.e.a
    public <V extends View & a> void b(V v) {
        this.f6545a = null;
        v.invalidate();
        e.a aVar = this.c;
        if (aVar != null) {
            aVar.b(v);
        }
    }

    @Override // io.dcloud.feature.gallery.imageedit.c.j.e
    public void a(e.a aVar) {
        this.c = aVar;
    }

    @Override // io.dcloud.feature.gallery.imageedit.c.j.e.a
    public <V extends View & a> boolean a(V v) {
        e.a aVar = this.c;
        return aVar != null && aVar.a(v);
    }
}
