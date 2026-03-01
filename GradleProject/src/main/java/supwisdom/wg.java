package supwisdom;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: ViewGroupOverlayApi14.java */
/* JADX INFO: loaded from: classes.dex */
public class wg extends ch implements yg {
    public wg(Context context, ViewGroup viewGroup, View view) {
        super(context, viewGroup, view);
    }

    public static wg a(ViewGroup viewGroup) {
        return (wg) ch.c(viewGroup);
    }

    @Override // supwisdom.yg
    public void b(View view) {
        this.f7210a.b(view);
    }

    @Override // supwisdom.yg
    public void a(View view) {
        this.f7210a.a(view);
    }
}
