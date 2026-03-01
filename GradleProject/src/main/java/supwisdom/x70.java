package supwisdom;

import android.content.Context;
import android.net.Uri;
import com.facebook.common.util.UriUtil;
import java.io.IOException;

/* JADX INFO: compiled from: DefaultDataSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class x70 implements s70 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s70 f9715a;
    public final s70 b;
    public final s70 c;
    public final s70 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public s70 f9716e;

    public x70(Context context, d80<? super s70> d80Var, s70 s70Var) {
        e80.a(s70Var);
        this.f9715a = s70Var;
        this.b = new com.google.android.exoplayer2.i.p(d80Var);
        this.c = new com.google.android.exoplayer2.i.c(context, d80Var);
        this.d = new com.google.android.exoplayer2.i.e(context, d80Var);
    }

    @Override // supwisdom.s70
    public long a(u70 u70Var) throws IOException {
        e80.b(this.f9716e == null);
        String scheme = u70Var.f9382a.getScheme();
        if (x80.a(u70Var.f9382a)) {
            if (u70Var.f9382a.getPath().startsWith("/android_asset/")) {
                this.f9716e = this.c;
            } else {
                this.f9716e = this.b;
            }
        } else if (UriUtil.LOCAL_ASSET_SCHEME.equals(scheme)) {
            this.f9716e = this.c;
        } else if ("content".equals(scheme)) {
            this.f9716e = this.d;
        } else {
            this.f9716e = this.f9715a;
        }
        return this.f9716e.a(u70Var);
    }

    @Override // supwisdom.s70
    public Uri b() {
        s70 s70Var = this.f9716e;
        if (s70Var == null) {
            return null;
        }
        return s70Var.b();
    }

    @Override // supwisdom.s70
    public int a(byte[] bArr, int i, int i2) throws IOException {
        return this.f9716e.a(bArr, i, i2);
    }

    @Override // supwisdom.s70
    public void a() throws IOException {
        s70 s70Var = this.f9716e;
        if (s70Var != null) {
            try {
                s70Var.a();
            } finally {
                this.f9716e = null;
            }
        }
    }
}
