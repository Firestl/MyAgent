package supwisdom;

import android.R;
import android.annotation.TargetApi;
import android.view.View;
import android.view.Window;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class a91 implements u81 {
    @Override // supwisdom.u81
    @TargetApi(23)
    public void a(Window window, int i) {
        window.clearFlags(67108864);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(i);
        View viewFindViewById = window.findViewById(R.id.content);
        if (viewFindViewById != null) {
            viewFindViewById.setForeground(null);
        }
    }
}
