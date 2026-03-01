package supwisdom;

import android.content.Context;
import net.grandcentrix.tray.core.TrayStorage;

/* JADX INFO: compiled from: TrayPreferences.java */
/* JADX INFO: loaded from: classes3.dex */
public class qr1 extends rr1<wr1> {
    public qr1(Context context, String str, int i, TrayStorage.Type type) {
        super(new wr1(context, str, type), i);
    }

    public qr1(Context context, String str, int i) {
        this(context, str, i, TrayStorage.Type.USER);
    }
}
