package supwisdom;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;

/* JADX INFO: compiled from: ViewGroupOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
public class xg implements yg {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ViewGroupOverlay f9763a;

    public xg(ViewGroup viewGroup) {
        this.f9763a = viewGroup.getOverlay();
    }

    @Override // supwisdom.eh
    public void a(Drawable drawable) {
        this.f9763a.add(drawable);
    }

    @Override // supwisdom.eh
    public void b(Drawable drawable) {
        this.f9763a.remove(drawable);
    }

    @Override // supwisdom.yg
    public void a(View view) {
        this.f9763a.add(view);
    }

    @Override // supwisdom.yg
    public void b(View view) {
        this.f9763a.remove(view);
    }
}
