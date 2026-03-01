package io.dcloud.feature.gallery.imageedit.c.j;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public interface e {

    public interface a {
        <V extends View & io.dcloud.feature.gallery.imageedit.c.j.a> boolean a(V v);

        <V extends View & io.dcloud.feature.gallery.imageedit.c.j.a> void b(V v);

        <V extends View & io.dcloud.feature.gallery.imageedit.c.j.a> void c(V v);
    }

    void a(Canvas canvas);

    void a(a aVar);

    boolean a();

    void b(a aVar);

    boolean b();

    boolean dismiss();

    RectF getFrame();
}
