package supwisdom;

import com.google.android.gms.internal.icing.zzcl;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.firebase:firebase-appindexing@@20.0.0 */
/* JADX INFO: loaded from: classes.dex */
public final class ej0 extends fj0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f7504a;
    public final int b;
    public int c;

    public final void a(byte[] bArr, int i, int i2) throws IOException {
        try {
            System.arraycopy(bArr, 0, this.f7504a, this.c, i2);
            this.c += i2;
        } catch (IndexOutOfBoundsException e2) {
            throw new zzcl(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.c), Integer.valueOf(this.b), Integer.valueOf(i2)), e2);
        }
    }
}
