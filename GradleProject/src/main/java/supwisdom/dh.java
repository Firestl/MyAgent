package supwisdom;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewOverlay;

/* JADX INFO: compiled from: ViewOverlayApi18.java */
/* JADX INFO: loaded from: classes.dex */
public class dh implements eh {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ViewOverlay f7341a;

    public dh(View view) {
        this.f7341a = view.getOverlay();
    }

    @Override // supwisdom.eh
    public void a(Drawable drawable) {
        this.f7341a.add(drawable);
    }

    @Override // supwisdom.eh
    public void b(Drawable drawable) {
        this.f7341a.remove(drawable);
    }
}
