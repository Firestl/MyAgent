package supwisdom;

import android.graphics.Bitmap;
import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
public class lx0 extends kx0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Bitmap f8327a;

    public Bitmap a() {
        return this.f8327a;
    }

    @Override // supwisdom.kx0, supwisdom.hx0
    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
        this.f8327a = bitmap;
    }
}
