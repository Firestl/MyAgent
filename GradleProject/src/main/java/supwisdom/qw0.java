package supwisdom;

import android.graphics.Bitmap;
import java.lang.ref.Reference;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public abstract class qw0 implements sw0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, Reference<Bitmap>> f8965a = Collections.synchronizedMap(new HashMap());

    @Override // supwisdom.sw0
    public Bitmap a(String str) {
        Reference<Bitmap> reference = this.f8965a.get(str);
        if (reference != null) {
            return reference.get();
        }
        return null;
    }

    public abstract Reference<Bitmap> a(Bitmap bitmap);

    @Override // supwisdom.sw0
    public void clear() {
        this.f8965a.clear();
    }

    @Override // supwisdom.sw0
    public Bitmap remove(String str) {
        Reference<Bitmap> referenceRemove = this.f8965a.remove(str);
        if (referenceRemove == null) {
            return null;
        }
        return referenceRemove.get();
    }

    @Override // supwisdom.sw0
    public Collection<String> a() {
        HashSet hashSet;
        synchronized (this.f8965a) {
            hashSet = new HashSet(this.f8965a.keySet());
        }
        return hashSet;
    }

    @Override // supwisdom.sw0
    public boolean a(String str, Bitmap bitmap) {
        this.f8965a.put(str, a(bitmap));
        return true;
    }
}
