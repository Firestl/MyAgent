package supwisdom;

import com.google.android.exoplayer2.i.q;

/* JADX INFO: compiled from: DefaultHttpDataSourceFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class a80 extends q.b {
    public final String b;
    public final d80<? super s70> c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f6873e;
    public final boolean f;

    public a80(String str, d80<? super s70> d80Var) {
        this(str, d80Var, 8000, 8000, false);
    }

    @Override // com.google.android.exoplayer2.i.q.b
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public z70 a(q.g gVar) {
        return new z70(this.b, null, this.c, this.d, this.f6873e, this.f, gVar);
    }

    public a80(String str, d80<? super s70> d80Var, int i, int i2, boolean z) {
        this.b = str;
        this.c = d80Var;
        this.d = i;
        this.f6873e = i2;
        this.f = z;
    }
}
