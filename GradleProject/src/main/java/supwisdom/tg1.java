package supwisdom;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.NetworkInfo;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import supwisdom.fh1;
import supwisdom.lh1;

/* JADX INFO: compiled from: BitmapHunter.java */
/* JADX INFO: loaded from: classes2.dex */
public class tg1 implements Runnable {
    public static final Object t = new Object();
    public static final ThreadLocal<StringBuilder> u = new a();
    public static final AtomicInteger v = new AtomicInteger();
    public static final lh1 w = new b();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f9285a = v.incrementAndGet();
    public final Picasso b;
    public final zg1 c;
    public final ug1 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final nh1 f9286e;
    public final String f;
    public final jh1 g;
    public final int h;
    public int i;
    public final lh1 j;
    public rg1 k;
    public List<rg1> l;
    public Bitmap m;
    public Future<?> n;
    public Picasso.LoadedFrom o;
    public Exception p;
    public int q;
    public int r;
    public Picasso.Priority s;

    /* JADX INFO: compiled from: BitmapHunter.java */
    public static class a extends ThreadLocal<StringBuilder> {
        @Override // java.lang.ThreadLocal
        public StringBuilder initialValue() {
            return new StringBuilder("Picasso-");
        }
    }

    /* JADX INFO: compiled from: BitmapHunter.java */
    public static class b extends lh1 {
        @Override // supwisdom.lh1
        public lh1.a a(jh1 jh1Var, int i) throws IOException {
            throw new IllegalStateException("Unrecognized type of request: " + jh1Var);
        }

        @Override // supwisdom.lh1
        public boolean a(jh1 jh1Var) {
            return true;
        }
    }

    /* JADX INFO: compiled from: BitmapHunter.java */
    public static class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ph1 f9287a;
        public final /* synthetic */ RuntimeException b;

        public c(ph1 ph1Var, RuntimeException runtimeException) {
            this.f9287a = ph1Var;
            this.b = runtimeException;
        }

        @Override // java.lang.Runnable
        public void run() {
            throw new RuntimeException("Transformation " + this.f9287a.a() + " crashed with exception.", this.b);
        }
    }

    /* JADX INFO: compiled from: BitmapHunter.java */
    public static class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ StringBuilder f9288a;

        public d(StringBuilder sb) {
            this.f9288a = sb;
        }

        @Override // java.lang.Runnable
        public void run() {
            throw new NullPointerException(this.f9288a.toString());
        }
    }

    /* JADX INFO: compiled from: BitmapHunter.java */
    public static class e implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ph1 f9289a;

        public e(ph1 ph1Var) {
            this.f9289a = ph1Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            throw new IllegalStateException("Transformation " + this.f9289a.a() + " returned input Bitmap but recycled it.");
        }
    }

    /* JADX INFO: compiled from: BitmapHunter.java */
    public static class f implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ph1 f9290a;

        public f(ph1 ph1Var) {
            this.f9290a = ph1Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            throw new IllegalStateException("Transformation " + this.f9290a.a() + " mutated input Bitmap but failed to recycle the original.");
        }
    }

    public tg1(Picasso picasso, zg1 zg1Var, ug1 ug1Var, nh1 nh1Var, rg1 rg1Var, lh1 lh1Var) {
        this.b = picasso;
        this.c = zg1Var;
        this.d = ug1Var;
        this.f9286e = nh1Var;
        this.k = rg1Var;
        this.f = rg1Var.c();
        this.g = rg1Var.h();
        this.s = rg1Var.g();
        this.h = rg1Var.d();
        this.i = rg1Var.e();
        this.j = lh1Var;
        this.r = lh1Var.a();
    }

    public static Bitmap a(InputStream inputStream, jh1 jh1Var) throws IOException {
        dh1 dh1Var = new dh1(inputStream);
        long jA = dh1Var.a(65536);
        BitmapFactory.Options optionsB = lh1.b(jh1Var);
        boolean zA = lh1.a(optionsB);
        boolean zB = rh1.b(dh1Var);
        dh1Var.a(jA);
        if (zB) {
            byte[] bArrC = rh1.c(dh1Var);
            if (zA) {
                BitmapFactory.decodeByteArray(bArrC, 0, bArrC.length, optionsB);
                lh1.a(jh1Var.h, jh1Var.i, optionsB, jh1Var);
            }
            return BitmapFactory.decodeByteArray(bArrC, 0, bArrC.length, optionsB);
        }
        if (zA) {
            BitmapFactory.decodeStream(dh1Var, null, optionsB);
            lh1.a(jh1Var.h, jh1Var.i, optionsB, jh1Var);
            dh1Var.a(jA);
        }
        Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(dh1Var, null, optionsB);
        if (bitmapDecodeStream != null) {
            return bitmapDecodeStream;
        }
        throw new IOException("Failed to decode stream.");
    }

    public static boolean a(boolean z, int i, int i2, int i3, int i4) {
        return !z || i > i3 || i2 > i4;
    }

    public void b(rg1 rg1Var) {
        boolean zRemove;
        if (this.k == rg1Var) {
            this.k = null;
            zRemove = true;
        } else {
            List<rg1> list = this.l;
            zRemove = list != null ? list.remove(rg1Var) : false;
        }
        if (zRemove && rg1Var.g() == this.s) {
            this.s = b();
        }
        if (this.b.n) {
            rh1.a("Hunter", "removed", rg1Var.b.d(), rh1.a(this, "from "));
        }
    }

    public rg1 c() {
        return this.k;
    }

    public List<rg1> d() {
        return this.l;
    }

    public jh1 e() {
        return this.g;
    }

    public Exception f() {
        return this.p;
    }

    public String g() {
        return this.f;
    }

    public Picasso.LoadedFrom h() {
        return this.o;
    }

    public int i() {
        return this.h;
    }

    public Picasso j() {
        return this.b;
    }

    public Picasso.Priority k() {
        return this.s;
    }

    public Bitmap l() {
        return this.m;
    }

    public Bitmap m() throws IOException {
        Bitmap bitmapA;
        if (MemoryPolicy.shouldReadFromMemoryCache(this.h)) {
            bitmapA = this.d.a(this.f);
            if (bitmapA != null) {
                this.f9286e.b();
                this.o = Picasso.LoadedFrom.MEMORY;
                if (this.b.n) {
                    rh1.a("Hunter", "decoded", this.g.d(), "from cache");
                }
                return bitmapA;
            }
        } else {
            bitmapA = null;
        }
        this.g.c = this.r == 0 ? NetworkPolicy.OFFLINE.index : this.i;
        lh1.a aVarA = this.j.a(this.g, this.i);
        if (aVarA != null) {
            this.o = aVarA.c();
            this.q = aVarA.b();
            bitmapA = aVarA.a();
            if (bitmapA == null) {
                InputStream inputStreamD = aVarA.d();
                try {
                    Bitmap bitmapA2 = a(inputStreamD, this.g);
                    rh1.a(inputStreamD);
                    bitmapA = bitmapA2;
                } catch (Throwable th) {
                    rh1.a(inputStreamD);
                    throw th;
                }
            }
        }
        if (bitmapA != null) {
            if (this.b.n) {
                rh1.a("Hunter", "decoded", this.g.d());
            }
            this.f9286e.a(bitmapA);
            if (this.g.f() || this.q != 0) {
                synchronized (t) {
                    if (this.g.e() || this.q != 0) {
                        bitmapA = a(this.g, bitmapA, this.q);
                        if (this.b.n) {
                            rh1.a("Hunter", "transformed", this.g.d());
                        }
                    }
                    if (this.g.b()) {
                        bitmapA = a(this.g.g, bitmapA);
                        if (this.b.n) {
                            rh1.a("Hunter", "transformed", this.g.d(), "from custom transformations");
                        }
                    }
                }
                if (bitmapA != null) {
                    this.f9286e.b(bitmapA);
                }
            }
        }
        return bitmapA;
    }

    public boolean n() {
        Future<?> future = this.n;
        return future != null && future.isCancelled();
    }

    public boolean o() {
        return this.j.b();
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            try {
                try {
                    try {
                        a(this.g);
                        if (this.b.n) {
                            rh1.a("Hunter", "executing", rh1.a(this));
                        }
                        Bitmap bitmapM = m();
                        this.m = bitmapM;
                        if (bitmapM == null) {
                            this.c.c(this);
                        } else {
                            this.c.b(this);
                        }
                    } catch (OutOfMemoryError e2) {
                        StringWriter stringWriter = new StringWriter();
                        this.f9286e.a().a(new PrintWriter(stringWriter));
                        this.p = new RuntimeException(stringWriter.toString(), e2);
                        this.c.c(this);
                    } catch (fh1.a e3) {
                        this.p = e3;
                        this.c.d(this);
                    }
                } catch (Downloader.ResponseException e4) {
                    if (!e4.localCacheOnly || e4.responseCode != 504) {
                        this.p = e4;
                    }
                    this.c.c(this);
                } catch (Exception e5) {
                    this.p = e5;
                    this.c.c(this);
                }
            } catch (IOException e6) {
                this.p = e6;
                this.c.d(this);
            }
        } finally {
            Thread.currentThread().setName("Picasso-Idle");
        }
    }

    public final Picasso.Priority b() {
        Picasso.Priority priorityG = Picasso.Priority.LOW;
        List<rg1> list = this.l;
        boolean z = true;
        boolean z2 = (list == null || list.isEmpty()) ? false : true;
        if (this.k == null && !z2) {
            z = false;
        }
        if (!z) {
            return priorityG;
        }
        rg1 rg1Var = this.k;
        if (rg1Var != null) {
            priorityG = rg1Var.g();
        }
        if (z2) {
            int size = this.l.size();
            for (int i = 0; i < size; i++) {
                Picasso.Priority priorityG2 = this.l.get(i).g();
                if (priorityG2.ordinal() > priorityG.ordinal()) {
                    priorityG = priorityG2;
                }
            }
        }
        return priorityG;
    }

    public void a(rg1 rg1Var) {
        boolean z = this.b.n;
        jh1 jh1Var = rg1Var.b;
        if (this.k == null) {
            this.k = rg1Var;
            if (z) {
                List<rg1> list = this.l;
                if (list != null && !list.isEmpty()) {
                    rh1.a("Hunter", "joined", jh1Var.d(), rh1.a(this, "to "));
                    return;
                } else {
                    rh1.a("Hunter", "joined", jh1Var.d(), "to empty hunter");
                    return;
                }
            }
            return;
        }
        if (this.l == null) {
            this.l = new ArrayList(3);
        }
        this.l.add(rg1Var);
        if (z) {
            rh1.a("Hunter", "joined", jh1Var.d(), rh1.a(this, "to "));
        }
        Picasso.Priority priorityG = rg1Var.g();
        if (priorityG.ordinal() > this.s.ordinal()) {
            this.s = priorityG;
        }
    }

    public boolean a() {
        Future<?> future;
        if (this.k != null) {
            return false;
        }
        List<rg1> list = this.l;
        return (list == null || list.isEmpty()) && (future = this.n) != null && future.cancel(false);
    }

    public boolean a(boolean z, NetworkInfo networkInfo) {
        if (!(this.r > 0)) {
            return false;
        }
        this.r--;
        return this.j.a(z, networkInfo);
    }

    public static void a(jh1 jh1Var) {
        String strA = jh1Var.a();
        StringBuilder sb = u.get();
        sb.ensureCapacity(strA.length() + 8);
        sb.replace(8, sb.length(), strA);
        Thread.currentThread().setName(sb.toString());
    }

    public static tg1 a(Picasso picasso, zg1 zg1Var, ug1 ug1Var, nh1 nh1Var, rg1 rg1Var) {
        jh1 jh1VarH = rg1Var.h();
        List<lh1> listA = picasso.a();
        int size = listA.size();
        for (int i = 0; i < size; i++) {
            lh1 lh1Var = listA.get(i);
            if (lh1Var.a(jh1VarH)) {
                return new tg1(picasso, zg1Var, ug1Var, nh1Var, rg1Var, lh1Var);
            }
        }
        return new tg1(picasso, zg1Var, ug1Var, nh1Var, rg1Var, w);
    }

    public static Bitmap a(List<ph1> list, Bitmap bitmap) {
        int size = list.size();
        int i = 0;
        while (i < size) {
            ph1 ph1Var = list.get(i);
            try {
                Bitmap bitmapTransform = ph1Var.transform(bitmap);
                if (bitmapTransform == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Transformation ");
                    sb.append(ph1Var.a());
                    sb.append(" returned null after ");
                    sb.append(i);
                    sb.append(" previous transformation(s).\n\nTransformation list:\n");
                    Iterator<ph1> it = list.iterator();
                    while (it.hasNext()) {
                        sb.append(it.next().a());
                        sb.append('\n');
                    }
                    Picasso.p.post(new d(sb));
                    return null;
                }
                if (bitmapTransform == bitmap && bitmap.isRecycled()) {
                    Picasso.p.post(new e(ph1Var));
                    return null;
                }
                if (bitmapTransform != bitmap && !bitmap.isRecycled()) {
                    Picasso.p.post(new f(ph1Var));
                    return null;
                }
                i++;
                bitmap = bitmapTransform;
            } catch (RuntimeException e2) {
                Picasso.p.post(new c(ph1Var, e2));
                return null;
            }
        }
        return bitmap;
    }

    public static Bitmap a(jh1 jh1Var, Bitmap bitmap, int i) {
        int i2;
        int i3;
        int i4;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        int iCeil;
        int i5;
        int i6;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        boolean z = jh1Var.l;
        Matrix matrix = new Matrix();
        int i7 = 0;
        if (jh1Var.e()) {
            int i8 = jh1Var.h;
            int i9 = jh1Var.i;
            float f7 = jh1Var.m;
            if (f7 != 0.0f) {
                if (jh1Var.p) {
                    matrix.setRotate(f7, jh1Var.n, jh1Var.o);
                } else {
                    matrix.setRotate(f7);
                }
            }
            if (jh1Var.j) {
                float f8 = i8;
                float f9 = f8 / width;
                float f10 = i9;
                float f11 = f10 / height;
                if (f9 > f11) {
                    iCeil = (int) Math.ceil(r10 * (f11 / f9));
                    i6 = (height - iCeil) / 2;
                    f11 = f10 / iCeil;
                    f6 = f9;
                    i5 = width;
                } else {
                    int iCeil2 = (int) Math.ceil(r6 * (f9 / f11));
                    f6 = f8 / iCeil2;
                    iCeil = height;
                    i7 = (width - iCeil2) / 2;
                    i5 = iCeil2;
                    i6 = 0;
                }
                if (a(z, width, height, i8, i9)) {
                    matrix.preScale(f6, f11);
                }
                i4 = i6;
                i2 = i5;
                i3 = iCeil;
            } else {
                if (jh1Var.k) {
                    float f12 = i8 / width;
                    float f13 = i9 / height;
                    if (f12 >= f13) {
                        f12 = f13;
                    }
                    if (a(z, width, height, i8, i9)) {
                        matrix.preScale(f12, f12);
                    }
                } else if ((i8 != 0 || i9 != 0) && (i8 != width || i9 != height)) {
                    if (i8 != 0) {
                        f2 = i8;
                        f3 = width;
                    } else {
                        f2 = i9;
                        f3 = height;
                    }
                    float f14 = f2 / f3;
                    if (i9 != 0) {
                        f4 = i9;
                        f5 = height;
                    } else {
                        f4 = i8;
                        f5 = width;
                    }
                    float f15 = f4 / f5;
                    if (a(z, width, height, i8, i9)) {
                        matrix.preScale(f14, f15);
                    }
                }
                i2 = width;
                i3 = height;
                i4 = 0;
            }
        } else {
            i2 = width;
            i3 = height;
            i4 = 0;
        }
        if (i != 0) {
            matrix.preRotate(i);
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, i7, i4, i2, i3, matrix, true);
        if (bitmapCreateBitmap == bitmap) {
            return bitmap;
        }
        bitmap.recycle();
        return bitmapCreateBitmap;
    }
}
