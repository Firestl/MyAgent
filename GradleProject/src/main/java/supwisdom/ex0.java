package supwisdom;

import android.graphics.Bitmap;
import android.os.Handler;
import com.nostra13.dcloudimageloader.core.assist.FailReason;
import com.nostra13.dcloudimageloader.core.assist.ImageScaleType;
import com.nostra13.dcloudimageloader.core.assist.LoadedFrom;
import com.nostra13.dcloudimageloader.core.assist.ViewScaleType;
import com.nostra13.dcloudimageloader.core.download.ImageDownloader;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import supwisdom.yw0;

/* JADX INFO: loaded from: classes2.dex */
public final class ex0 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final bx0 f7540a;
    public final dx0 b;
    public final Handler c;
    public final ax0 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ImageDownloader f7541e;
    public final ImageDownloader f;
    public final ImageDownloader g;
    public final px0 h;
    public final boolean i;
    public final String j;
    public final String k;
    public final wx0 l;
    public final ix0 m;
    public final yw0 n;
    public final hx0 o;
    public LoadedFrom p = LoadedFrom.NETWORK;
    public boolean q = false;

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ FailReason.FailType f7542a;
        public final /* synthetic */ Throwable b;

        public a(FailReason.FailType failType, Throwable th) {
            this.f7542a = failType;
            this.b = th;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ex0.this.n.r()) {
                ex0 ex0Var = ex0.this;
                ex0Var.l.a(ex0Var.n.b(ex0Var.d.f6992a));
            }
            ex0 ex0Var2 = ex0.this;
            ex0Var2.o.onLoadingFailed(ex0Var2.j, ex0Var2.l.a(), new FailReason(this.f7542a, this.b));
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ex0 ex0Var = ex0.this;
            ex0Var.o.onLoadingCancelled(ex0Var.j, ex0Var.l.a());
        }
    }

    public ex0(bx0 bx0Var, dx0 dx0Var, Handler handler) {
        this.f7540a = bx0Var;
        this.b = dx0Var;
        this.c = handler;
        ax0 ax0Var = bx0Var.f7120a;
        this.d = ax0Var;
        this.f7541e = ax0Var.r;
        this.f = ax0Var.w;
        this.g = ax0Var.x;
        this.h = ax0Var.s;
        this.i = ax0Var.u;
        this.j = dx0Var.f7392a;
        this.k = dx0Var.b;
        this.l = dx0Var.c;
        this.m = dx0Var.d;
        this.n = dx0Var.f7393e;
        this.o = dx0Var.f;
    }

    public final boolean b() {
        return c() || d();
    }

    public final boolean c() {
        if (!this.l.b()) {
            return false;
        }
        this.q = true;
        b("ImageAware was collected by GC. Task is cancelled. [%s]");
        f();
        return true;
    }

    public final boolean d() {
        boolean z = !this.k.equals(this.f7540a.b(this.l));
        if (z) {
            b("ImageAware is reused for another image. Task is cancelled. [%s]");
            f();
        }
        return z;
    }

    public final boolean e() {
        if (!this.n.n()) {
            return false;
        }
        a("Delay %d ms before loading...  [%s]", Integer.valueOf(this.n.b()), this.k);
        try {
            Thread.sleep(this.n.b());
            return b();
        } catch (InterruptedException unused) {
            by0.b("Task was interrupted [%s]", this.k);
            return true;
        }
    }

    public final void f() {
        if (Thread.interrupted()) {
            return;
        }
        if (this.n.m()) {
            this.o.onLoadingCancelled(this.j, this.l.a());
        } else {
            this.c.post(new b());
        }
    }

    public final ImageDownloader g() {
        return this.f7540a.d() ? this.f : this.f7540a.e() ? this.g : this.f7541e;
    }

    public final File h() {
        File parentFile;
        File fileA = this.d.q.a(this.j);
        File parentFile2 = fileA.getParentFile();
        if ((parentFile2 == null || (!parentFile2.exists() && !parentFile2.mkdirs())) && (parentFile = (fileA = this.d.v.a(this.j)).getParentFile()) != null && !parentFile.exists()) {
            parentFile.mkdirs();
        }
        return fileA;
    }

    public String i() {
        return this.j;
    }

    public final Bitmap j() {
        Bitmap bitmapA;
        IOException e2;
        File fileH = h();
        Bitmap bitmap = null;
        try {
            if (fileH.exists()) {
                b("Load image from disc cache [%s]");
                this.p = LoadedFrom.DISC_CACHE;
                bitmapA = a(ImageDownloader.Scheme.FILE.wrap(fileH.getAbsolutePath()));
                try {
                    if (this.q) {
                        return null;
                    }
                } catch (IOException e3) {
                    e2 = e3;
                    by0.a(e2);
                    a(FailReason.FailType.IO_ERROR, e2);
                    if (!fileH.exists()) {
                        return bitmapA;
                    }
                    fileH.delete();
                    return bitmapA;
                } catch (IllegalStateException unused) {
                    a(FailReason.FailType.NETWORK_DENIED, (Throwable) null);
                    return bitmapA;
                } catch (OutOfMemoryError e4) {
                    e = e4;
                    bitmap = bitmapA;
                    by0.a(e);
                    a(FailReason.FailType.OUT_OF_MEMORY, e);
                    return bitmap;
                } catch (Throwable th) {
                    th = th;
                    bitmap = bitmapA;
                    by0.a(th);
                    a(FailReason.FailType.UNKNOWN, th);
                    return bitmap;
                }
            } else {
                bitmapA = null;
            }
            if (bitmapA != null && bitmapA.getWidth() > 0 && bitmapA.getHeight() > 0) {
                return bitmapA;
            }
            b("Load image from network [%s]");
            this.p = LoadedFrom.NETWORK;
            String strB = this.n.j() ? b(fileH) : this.j;
            if (b()) {
                return bitmapA;
            }
            bitmapA = a(strB);
            if (this.q) {
                return null;
            }
            if (bitmapA != null && bitmapA.getWidth() > 0 && bitmapA.getHeight() > 0) {
                return bitmapA;
            }
            a(FailReason.FailType.DECODING_ERROR, (Throwable) null);
            return bitmapA;
        } catch (IOException e5) {
            bitmapA = null;
            e2 = e5;
        } catch (IllegalStateException unused2) {
            bitmapA = null;
        } catch (OutOfMemoryError e6) {
            e = e6;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public final boolean k() {
        AtomicBoolean atomicBooleanB = this.f7540a.b();
        synchronized (atomicBooleanB) {
            if (atomicBooleanB.get()) {
                b("ImageLoader is paused. Waiting...  [%s]");
                try {
                    atomicBooleanB.wait();
                    b(".. Resume loading [%s]");
                } catch (InterruptedException unused) {
                    by0.b("Task was interrupted [%s]", this.k);
                    return true;
                }
            }
        }
        return b();
    }

    @Override // java.lang.Runnable
    public void run() {
        if (k() || e()) {
            return;
        }
        ReentrantLock reentrantLock = this.b.g;
        b("Start display image task [%s]");
        if (reentrantLock.isLocked()) {
            b("Image already is loading. Waiting... [%s]");
        }
        reentrantLock.lock();
        try {
            if (b()) {
                return;
            }
            Bitmap bitmapA = this.d.p.a(this.k);
            if (bitmapA == null) {
                bitmapA = j();
                if (this.q) {
                    return;
                }
                if (bitmapA == null) {
                    return;
                }
                if (!b() && !a()) {
                    if (this.n.p()) {
                        b("PreProcess image before caching in memory [%s]");
                        bitmapA = this.n.h().a(bitmapA);
                        if (bitmapA == null) {
                            by0.b("Pre-processor returned null [%s]", new Object[0]);
                        }
                    }
                    if (bitmapA != null && this.n.i()) {
                        b("Cache image in memory [%s]");
                        this.d.p.a(this.k, bitmapA);
                    }
                }
                return;
            }
            this.p = LoadedFrom.MEMORY_CACHE;
            b("...Get cached bitmap from memory after waiting. [%s]");
            if (bitmapA != null && this.n.o()) {
                b("PostProcess image before displaying [%s]");
                bitmapA = this.n.g().a(bitmapA);
                if (bitmapA == null) {
                    by0.b("Pre-processor returned null [%s]", this.k);
                }
            }
            reentrantLock.unlock();
            if (b() || a()) {
                return;
            }
            xw0 xw0Var = new xw0(bitmapA, this.b, this.f7540a, this.p);
            xw0Var.a(this.i);
            if (this.n.m()) {
                xw0Var.run();
            } else {
                this.c.post(xw0Var);
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public final boolean a() {
        boolean zInterrupted = Thread.interrupted();
        if (zInterrupted) {
            b("Task was interrupted [%s]");
        }
        return zInterrupted;
    }

    public final void b(String str) {
        if (this.i) {
            by0.a(str, this.k);
        }
    }

    public final Bitmap a(String str) throws IOException {
        ViewScaleType viewScaleTypeC;
        if (c() || (viewScaleTypeC = this.l.c()) == null) {
            return null;
        }
        return this.h.a(new qx0(this.k, str, this.m, viewScaleTypeC, g(), this.n));
    }

    public final String b(File file) {
        b("Cache image on disc [%s]");
        try {
            ax0 ax0Var = this.d;
            int i = ax0Var.d;
            int i2 = ax0Var.f6993e;
            if (!((i > 0 || i2 > 0) ? a(file, i, i2) : false)) {
                a(file);
            }
            this.d.q.a(this.j, file);
            return ImageDownloader.Scheme.FILE.wrap(file.getAbsolutePath());
        } catch (IOException e2) {
            by0.a(e2);
            if (file.exists()) {
                file.delete();
            }
            return this.j;
        }
    }

    public final void a(File file) throws IOException {
        InputStream inputStreamA = g().a(this.j, this.n.d());
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), 1195);
            try {
                ay0.a(inputStreamA, bufferedOutputStream);
            } finally {
                ay0.a(bufferedOutputStream);
            }
        } finally {
            ay0.a(inputStreamA);
        }
    }

    public final boolean a(File file, int i, int i2) throws IOException {
        ix0 ix0Var = new ix0(i, i2);
        yw0.b bVar = new yw0.b();
        bVar.a(this.n);
        bVar.a(ImageScaleType.IN_SAMPLE_INT);
        Bitmap bitmapA = this.h.a(new qx0(this.k, this.j, ix0Var, ViewScaleType.FIT_INSIDE, g(), bVar.a()));
        if (bitmapA == null) {
            return false;
        }
        if (this.d.h != null) {
            b("Process image before cache on disc [%s]");
            bitmapA = this.d.h.a(bitmapA);
            if (bitmapA == null) {
                by0.b("Bitmap processor for disc cache returned null [%s]", this.k);
                return false;
            }
        }
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), 1195);
        try {
            ax0 ax0Var = this.d;
            boolean zCompress = bitmapA.compress(ax0Var.f, ax0Var.g, bufferedOutputStream);
            ay0.a(bufferedOutputStream);
            bitmapA.recycle();
            return zCompress;
        } catch (Throwable th) {
            ay0.a(bufferedOutputStream);
            throw th;
        }
    }

    public final void a(FailReason.FailType failType, Throwable th) {
        if (Thread.interrupted()) {
            return;
        }
        if (this.n.m()) {
            this.o.onLoadingFailed(this.j, this.l.a(), new FailReason(failType, th));
        } else {
            this.c.post(new a(failType, th));
        }
    }

    public final void a(String str, Object... objArr) {
        if (this.i) {
            by0.a(str, objArr);
        }
    }
}
