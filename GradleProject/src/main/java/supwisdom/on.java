package supwisdom;

import java.util.Iterator;

/* JADX INFO: compiled from: ChartDataUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class on {
    public static boolean a(Iterable<?> iterable) {
        Iterator<?> it = iterable.iterator();
        while (it.hasNext()) {
            if (it.next() != null) {
                return false;
            }
        }
        return true;
    }
}
