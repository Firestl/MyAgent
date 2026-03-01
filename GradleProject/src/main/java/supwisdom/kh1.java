package supwisdom;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.google.zxing.client.android.share.BookMarkColumns;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import java.util.concurrent.atomic.AtomicInteger;
import supwisdom.jh1;

/* JADX INFO: compiled from: RequestCreator.java */
/* JADX INFO: loaded from: classes2.dex */
public class kh1 {
    public static final AtomicInteger m = new AtomicInteger();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Picasso f8175a;
    public final jh1.b b;
    public boolean c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f8176e = true;
    public int f;
    public int g;
    public int h;
    public int i;
    public Drawable j;
    public Drawable k;
    public Object l;

    public kh1(Picasso picasso, Uri uri, int i) {
        if (picasso.o) {
            throw new IllegalStateException("Picasso instance already shut down. Cannot submit new requests.");
        }
        this.f8175a = picasso;
        this.b = new jh1.b(uri, i, picasso.l);
    }

    public kh1 a(int i, int i2) {
        this.b.a(i, i2);
        return this;
    }

    public kh1 b() {
        this.d = false;
        return this;
    }

    public kh1 a(ph1 ph1Var) {
        this.b.a(ph1Var);
        return this;
    }

    public void a(ImageView imageView) {
        a(imageView, (vg1) null);
    }

    public void a(ImageView imageView, vg1 vg1Var) {
        Bitmap bitmapB;
        long jNanoTime = System.nanoTime();
        rh1.a();
        if (imageView != null) {
            if (!this.b.b()) {
                this.f8175a.a(imageView);
                if (this.f8176e) {
                    hh1.a(imageView, a());
                    return;
                }
                return;
            }
            if (this.d) {
                if (!this.b.c()) {
                    int width = imageView.getWidth();
                    int height = imageView.getHeight();
                    if (width != 0 && height != 0) {
                        this.b.a(width, height);
                    } else {
                        if (this.f8176e) {
                            hh1.a(imageView, a());
                        }
                        this.f8175a.a(imageView, new yg1(this, imageView, vg1Var));
                        return;
                    }
                } else {
                    throw new IllegalStateException("Fit cannot be used with resize.");
                }
            }
            jh1 jh1VarA = a(jNanoTime);
            String strA = rh1.a(jh1VarA);
            if (MemoryPolicy.shouldReadFromMemoryCache(this.h) && (bitmapB = this.f8175a.b(strA)) != null) {
                this.f8175a.a(imageView);
                Picasso picasso = this.f8175a;
                hh1.a(imageView, picasso.f3963e, bitmapB, Picasso.LoadedFrom.MEMORY, this.c, picasso.m);
                if (this.f8175a.n) {
                    rh1.a("Main", "completed", jh1VarA.g(), "from " + Picasso.LoadedFrom.MEMORY);
                }
                if (vg1Var != null) {
                    vg1Var.onSuccess();
                    return;
                }
                return;
            }
            if (this.f8176e) {
                hh1.a(imageView, a());
            }
            this.f8175a.a((rg1) new bh1(this.f8175a, imageView, jh1VarA, this.h, this.i, this.g, this.k, strA, this.l, vg1Var, this.c));
            return;
        }
        throw new IllegalArgumentException("Target must not be null.");
    }

    public final Drawable a() {
        if (this.f != 0) {
            return this.f8175a.f3963e.getResources().getDrawable(this.f);
        }
        return this.j;
    }

    public final jh1 a(long j) {
        int andIncrement = m.getAndIncrement();
        jh1 jh1VarA = this.b.a();
        jh1VarA.f8062a = andIncrement;
        jh1VarA.b = j;
        boolean z = this.f8175a.n;
        if (z) {
            rh1.a("Main", BookMarkColumns.CREATED, jh1VarA.g(), jh1VarA.toString());
        }
        this.f8175a.a(jh1VarA);
        if (jh1VarA != jh1VarA) {
            jh1VarA.f8062a = andIncrement;
            jh1VarA.b = j;
            if (z) {
                rh1.a("Main", "changed", jh1VarA.d(), "into " + jh1VarA);
            }
        }
        return jh1VarA;
    }
}
