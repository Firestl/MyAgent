package supwisdom;

import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: compiled from: OneShotPreDrawListener.java */
/* JADX INFO: loaded from: classes.dex */
public final class hb implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f7821a;
    public ViewTreeObserver b;
    public final Runnable c;

    public hb(View view, Runnable runnable) {
        this.f7821a = view;
        this.b = view.getViewTreeObserver();
        this.c = runnable;
    }

    public static hb a(View view, Runnable runnable) {
        if (view == null) {
            throw new NullPointerException("view == null");
        }
        if (runnable == null) {
            throw new NullPointerException("runnable == null");
        }
        hb hbVar = new hb(view, runnable);
        view.getViewTreeObserver().addOnPreDrawListener(hbVar);
        view.addOnAttachStateChangeListener(hbVar);
        return hbVar;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        a();
        this.c.run();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        this.b = view.getViewTreeObserver();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        a();
    }

    public void a() {
        if (this.b.isAlive()) {
            this.b.removeOnPreDrawListener(this);
        } else {
            this.f7821a.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        this.f7821a.removeOnAttachStateChangeListener(this);
    }
}
