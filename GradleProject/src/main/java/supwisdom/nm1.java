package supwisdom;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: AppCacheMgr.java */
/* JADX INFO: loaded from: classes2.dex */
public class nm1 {
    public static final nm1 b = new nm1();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Map<String, Long> f8545a = new HashMap();

    public synchronized void a(File file) {
        if (file != null) {
            if (file.exists()) {
                if (this.f8545a.containsKey(file.getPath())) {
                }
            }
        }
    }

    public synchronized Long a() {
        Long lValueOf;
        lValueOf = 0L;
        Iterator<Map.Entry<String, Long>> it = this.f8545a.entrySet().iterator();
        while (it.hasNext()) {
            lValueOf = Long.valueOf(lValueOf.longValue() + it.next().getValue().longValue());
        }
        return lValueOf;
    }
}
