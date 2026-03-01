package supwisdom;

import android.graphics.Bitmap;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class uw0 extends rw0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Map<String, Bitmap> f9449e;

    public uw0(int i) {
        super(i);
        this.f9449e = Collections.synchronizedMap(new LinkedHashMap(10, 1.1f, true));
    }

    @Override // supwisdom.qw0
    public Reference<Bitmap> a(Bitmap bitmap) {
        return new WeakReference(bitmap);
    }

    @Override // supwisdom.rw0
    public int b(Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    @Override // supwisdom.rw0
    public Bitmap c() {
        Bitmap value;
        synchronized (this.f9449e) {
            Iterator<Map.Entry<String, Bitmap>> it = this.f9449e.entrySet().iterator();
            if (it.hasNext()) {
                value = it.next().getValue();
                it.remove();
            } else {
                value = null;
            }
        }
        return value;
    }

    @Override // supwisdom.rw0, supwisdom.qw0, supwisdom.sw0
    public void clear() {
        this.f9449e.clear();
        super.clear();
    }

    @Override // supwisdom.rw0, supwisdom.qw0, supwisdom.sw0
    public Bitmap remove(String str) {
        this.f9449e.remove(str);
        return super.remove(str);
    }

    @Override // supwisdom.qw0, supwisdom.sw0
    public Bitmap a(String str) {
        this.f9449e.get(str);
        return super.a(str);
    }

    @Override // supwisdom.rw0, supwisdom.qw0, supwisdom.sw0
    public boolean a(String str, Bitmap bitmap) {
        if (!super.a(str, bitmap)) {
            return false;
        }
        this.f9449e.put(str, bitmap);
        return true;
    }
}
