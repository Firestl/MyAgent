package supwisdom;

import com.google.android.gms.internal.icing.zzcf;
import java.util.Comparator;

/* JADX INFO: compiled from: com.google.firebase:firebase-appindexing@@20.0.0 */
/* JADX INFO: loaded from: classes.dex */
public final class ti0 implements Comparator<zzcf> {
    @Override // java.util.Comparator
    public final /* bridge */ /* synthetic */ int compare(zzcf zzcfVar, zzcf zzcfVar2) {
        zzcf zzcfVar3 = zzcfVar;
        zzcf zzcfVar4 = zzcfVar2;
        si0 si0Var = new si0(zzcfVar3);
        si0 si0Var2 = new si0(zzcfVar4);
        while (si0Var.hasNext() && si0Var2.hasNext()) {
            int iCompare = Integer.compare(si0Var.zza() & 255, si0Var2.zza() & 255);
            if (iCompare != 0) {
                return iCompare;
            }
        }
        return Integer.compare(zzcfVar3.zzc(), zzcfVar4.zzc());
    }
}
