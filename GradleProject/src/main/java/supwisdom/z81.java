package supwisdom;

import android.annotation.TargetApi;
import android.view.Window;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class z81 implements u81 {
    @Override // supwisdom.u81
    @TargetApi(21)
    public void a(Window window, int i) {
        window.clearFlags(67108864);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(i);
    }
}
