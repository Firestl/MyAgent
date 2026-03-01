package supwisdom;

import android.os.Build;
import android.view.ViewGroup;

/* JADX INFO: compiled from: ViewGroupUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class zg {
    public static yg a(ViewGroup viewGroup) {
        return Build.VERSION.SDK_INT >= 18 ? new xg(viewGroup) : wg.a(viewGroup);
    }

    public static void a(ViewGroup viewGroup, boolean z) {
        if (Build.VERSION.SDK_INT >= 18) {
            bh.a(viewGroup, z);
        } else {
            ah.a(viewGroup, z);
        }
    }
}
