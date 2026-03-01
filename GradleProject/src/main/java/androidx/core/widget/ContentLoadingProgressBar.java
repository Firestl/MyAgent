package androidx.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/* JADX INFO: loaded from: classes.dex */
public class ContentLoadingProgressBar extends ProgressBar {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f1277a;
    public final Runnable b;
    public final Runnable c;

    public ContentLoadingProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.f1277a = false;
        this.b = new Runnable() { // from class: supwisdom.dc
            @Override // java.lang.Runnable
            public final void run() {
                this.f7323a.a();
            }
        };
        this.c = new Runnable() { // from class: supwisdom.ec
            @Override // java.lang.Runnable
            public final void run() {
                this.f7461a.b();
            }
        };
    }

    public /* synthetic */ void a() {
        setVisibility(8);
    }

    public /* synthetic */ void b() {
        if (this.f1277a) {
            return;
        }
        System.currentTimeMillis();
        setVisibility(0);
    }

    public final void c() {
        removeCallbacks(this.b);
        removeCallbacks(this.c);
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        c();
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c();
    }
}
