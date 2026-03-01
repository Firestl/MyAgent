package supwisdom;

import android.net.Uri;
import com.google.android.exoplayer2.i.r;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: ParsingLoadable.java */
/* JADX INFO: loaded from: classes.dex */
public final class c80<T> implements r.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u70 f7160a;
    public final int b;
    public final s70 c;
    public final a<? extends T> d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile T f7161e;
    public volatile boolean f;
    public volatile long g;

    /* JADX INFO: compiled from: ParsingLoadable.java */
    public interface a<T> {
        T a(Uri uri, InputStream inputStream) throws IOException;
    }

    public c80(s70 s70Var, Uri uri, int i, a<? extends T> aVar) {
        this.c = s70Var;
        this.f7160a = new u70(uri, 1);
        this.b = i;
        this.d = aVar;
    }

    @Override // com.google.android.exoplayer2.i.r.c
    public final void a() {
        this.f = true;
    }

    @Override // com.google.android.exoplayer2.i.r.c
    public final boolean b() {
        return this.f;
    }

    @Override // com.google.android.exoplayer2.i.r.c
    public final void c() throws InterruptedException, IOException {
        t70 t70Var = new t70(this.c, this.f7160a);
        try {
            t70Var.b();
            this.f7161e = this.d.a(this.c.b(), t70Var);
        } finally {
            this.g = t70Var.a();
            x80.a(t70Var);
        }
    }

    public final T d() {
        return this.f7161e;
    }

    public long e() {
        return this.g;
    }
}
