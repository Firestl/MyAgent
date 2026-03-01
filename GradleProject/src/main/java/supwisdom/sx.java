package supwisdom;

import com.dcloud.zxing2.NotFoundException;
import com.dcloud.zxing2.ReaderException;

/* JADX INFO: loaded from: classes.dex */
public final class sx {
    public static final int[] c = {1, 1, 2};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final qx f9218a = new qx();
    public final rx b = new rx();

    public xv a(int i, ew ewVar, int i2) throws NotFoundException {
        int[] iArrA = tx.a(ewVar, i2, false, c);
        try {
            return this.b.a(i, ewVar, iArrA);
        } catch (ReaderException unused) {
            return this.f9218a.a(i, ewVar, iArrA);
        }
    }
}
