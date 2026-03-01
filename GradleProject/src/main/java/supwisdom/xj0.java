package supwisdom;

import com.google.android.gms.internal.icing.zzm;
import com.google.android.gms.internal.icing.zzs;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-appindexing@@20.0.0 */
/* JADX INFO: loaded from: classes.dex */
public final class xj0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f9782a;
    public String b;
    public boolean c;
    public boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List<zzm> f9783e = new ArrayList();
    public String f;

    public xj0(String str) {
        this.f9782a = str;
    }

    public final zzs a() {
        String str = this.f9782a;
        String str2 = this.b;
        boolean z = this.c;
        boolean z2 = this.d;
        List<zzm> list = this.f9783e;
        return new zzs(str, str2, z, 1, z2, null, (zzm[]) list.toArray(new zzm[list.size()]), this.f, null);
    }

    public final xj0 a(String str) {
        this.b = "blob";
        return this;
    }

    public final xj0 a(boolean z) {
        this.c = true;
        return this;
    }
}
