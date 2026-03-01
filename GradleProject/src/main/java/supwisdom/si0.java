package supwisdom;

import com.google.android.gms.internal.icing.zzcf;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: com.google.firebase:firebase-appindexing@@20.0.0 */
/* JADX INFO: loaded from: classes.dex */
public final class si0 extends ui0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f9175a = 0;
    public final int b;
    public final /* synthetic */ zzcf c;

    public si0(zzcf zzcfVar) {
        this.c = zzcfVar;
        this.b = this.c.zzc();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f9175a < this.b;
    }

    @Override // supwisdom.xi0
    public final byte zza() {
        int i = this.f9175a;
        if (i >= this.b) {
            throw new NoSuchElementException();
        }
        this.f9175a = i + 1;
        return this.c.zzb(i);
    }
}
