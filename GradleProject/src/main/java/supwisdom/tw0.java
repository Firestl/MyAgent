package supwisdom;

import android.graphics.Bitmap;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class tw0 implements sw0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final sw0 f9325a;
    public final Comparator<String> b;

    public tw0(sw0 sw0Var, Comparator<String> comparator) {
        this.f9325a = sw0Var;
        this.b = comparator;
    }

    @Override // supwisdom.sw0
    public Bitmap a(String str) {
        return this.f9325a.a(str);
    }

    @Override // supwisdom.sw0
    public void clear() {
        this.f9325a.clear();
    }

    @Override // supwisdom.sw0
    public Bitmap remove(String str) {
        return this.f9325a.remove(str);
    }

    @Override // supwisdom.sw0
    public Collection<String> a() {
        return this.f9325a.a();
    }

    @Override // supwisdom.sw0
    public boolean a(String str, Bitmap bitmap) {
        String next;
        synchronized (this.f9325a) {
            Iterator<String> it = this.f9325a.a().iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (this.b.compare(str, next) == 0) {
                    break;
                }
            }
            if (next != null) {
                this.f9325a.remove(next);
            }
        }
        return this.f9325a.a(str, bitmap);
    }
}
