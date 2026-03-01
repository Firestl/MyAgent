package supwisdom;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: ViewModel.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class ge {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, Object> f7715a = new HashMap();

    public final void a() {
        Map<String, Object> map = this.f7715a;
        if (map != null) {
            synchronized (map) {
                Iterator<Object> it = this.f7715a.values().iterator();
                while (it.hasNext()) {
                    a(it.next());
                }
            }
        }
        b();
    }

    public void b() {
    }

    public static void a(Object obj) {
        if (obj instanceof Closeable) {
            try {
                ((Closeable) obj).close();
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }
    }
}
