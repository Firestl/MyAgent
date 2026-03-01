package supwisdom;

import android.graphics.Bitmap;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
public abstract class rw0 extends qw0 {
    public final int b;
    public final List<Bitmap> d = Collections.synchronizedList(new LinkedList());
    public final AtomicInteger c = new AtomicInteger();

    public rw0(int i) {
        this.b = i;
        if (i > 16777216) {
            by0.d("You set too large memory cache size (more than %1$d Mb)", 16);
        }
    }

    @Override // supwisdom.qw0, supwisdom.sw0
    public boolean a(String str, Bitmap bitmap) {
        boolean z;
        int iB = b(bitmap);
        int iB2 = b();
        int iAddAndGet = this.c.get();
        if (iB < iB2) {
            while (iAddAndGet + iB > iB2) {
                Bitmap bitmapC = c();
                if (this.d.remove(bitmapC)) {
                    iAddAndGet = this.c.addAndGet(-b(bitmapC));
                }
            }
            this.d.add(bitmap);
            this.c.addAndGet(iB);
            z = true;
        } else {
            z = false;
        }
        super.a(str, bitmap);
        return z;
    }

    public int b() {
        return this.b;
    }

    public abstract int b(Bitmap bitmap);

    public abstract Bitmap c();

    @Override // supwisdom.qw0, supwisdom.sw0
    public void clear() {
        this.d.clear();
        this.c.set(0);
        super.clear();
    }

    @Override // supwisdom.qw0, supwisdom.sw0
    public Bitmap remove(String str) {
        Bitmap bitmapA = super.a(str);
        if (bitmapA != null && this.d.remove(bitmapA)) {
            this.c.addAndGet(-b(bitmapA));
        }
        return super.remove(str);
    }
}
