package supwisdom;

import android.util.SparseArray;
import java.io.IOException;

/* JADX INFO: compiled from: ChunkExtractorWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public final class o90 implements z40 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y30 f8646a;
    public final com.google.android.exoplayer2.j b;
    public final SparseArray<a> c = new SparseArray<>();
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b f8647e;
    public e50 f;
    public com.google.android.exoplayer2.j[] g;

    /* JADX INFO: compiled from: ChunkExtractorWrapper.java */
    public interface b {
        f50 a(int i, int i2);
    }

    public o90(y30 y30Var, com.google.android.exoplayer2.j jVar) {
        this.f8646a = y30Var;
        this.b = jVar;
    }

    public void a(b bVar) {
        this.f8647e = bVar;
        if (!this.d) {
            this.f8646a.a(this);
            this.d = true;
            return;
        }
        this.f8646a.a(0L, 0L);
        for (int i = 0; i < this.c.size(); i++) {
            this.c.valueAt(i).a(bVar);
        }
    }

    public e50 b() {
        return this.f;
    }

    public com.google.android.exoplayer2.j[] c() {
        return this.g;
    }

    /* JADX INFO: compiled from: ChunkExtractorWrapper.java */
    public static final class a implements f50 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f8648a;
        public final int b;
        public final com.google.android.exoplayer2.j c;
        public com.google.android.exoplayer2.j d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public f50 f8649e;

        public a(int i, int i2, com.google.android.exoplayer2.j jVar) {
            this.f8648a = i;
            this.b = i2;
            this.c = jVar;
        }

        public void a(b bVar) {
            if (bVar == null) {
                this.f8649e = new w30();
                return;
            }
            f50 f50VarA = bVar.a(this.f8648a, this.b);
            this.f8649e = f50VarA;
            if (f50VarA != null) {
                f50VarA.a(this.d);
            }
        }

        @Override // supwisdom.f50
        public void a(com.google.android.exoplayer2.j jVar) {
            com.google.android.exoplayer2.j jVarA = jVar.a(this.c);
            this.d = jVarA;
            this.f8649e.a(jVarA);
        }

        @Override // supwisdom.f50
        public int a(v40 v40Var, int i, boolean z) throws InterruptedException, IOException {
            return this.f8649e.a(v40Var, i, z);
        }

        @Override // supwisdom.f50
        public void a(o80 o80Var, int i) {
            this.f8649e.a(o80Var, i);
        }

        @Override // supwisdom.f50
        public void a(long j, int i, int i2, int i3, byte[] bArr) {
            this.f8649e.a(j, i, i2, i3, bArr);
        }
    }

    @Override // supwisdom.z40
    public f50 a(int i, int i2) {
        a aVar = this.c.get(i);
        if (aVar != null) {
            return aVar;
        }
        e80.b(this.g == null);
        a aVar2 = new a(i, i2, this.b);
        aVar2.a(this.f8647e);
        this.c.put(i, aVar2);
        return aVar2;
    }

    @Override // supwisdom.z40
    public void a() {
        com.google.android.exoplayer2.j[] jVarArr = new com.google.android.exoplayer2.j[this.c.size()];
        for (int i = 0; i < this.c.size(); i++) {
            jVarArr[i] = this.c.valueAt(i).d;
        }
        this.g = jVarArr;
    }

    @Override // supwisdom.z40
    public void a(e50 e50Var) {
        this.f = e50Var;
    }
}
