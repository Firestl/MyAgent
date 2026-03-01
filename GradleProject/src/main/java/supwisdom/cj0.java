package supwisdom;

import com.google.android.gms.internal.icing.zzdj;

/* JADX INFO: compiled from: com.google.firebase:firebase-appindexing@@20.0.0 */
/* JADX INFO: loaded from: classes.dex */
public final class cj0 extends dj0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f7218a;
    public int b;
    public int c;

    public /* synthetic */ cj0(byte[] bArr, int i, int i2, boolean z, bj0 bj0Var) {
        super(null);
        this.c = Integer.MAX_VALUE;
        this.f7218a = 0;
    }

    public final int a(int i) throws zzdj {
        int i2 = this.c;
        this.c = 0;
        int i3 = this.f7218a + this.b;
        this.f7218a = i3;
        if (i3 > 0) {
            this.b = i3;
            this.f7218a = 0;
        } else {
            this.b = 0;
        }
        return i2;
    }
}
