package supwisdom;

import com.google.android.gms.internal.icing.zzcf;
import java.io.IOException;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: com.google.firebase:firebase-appindexing@@20.0.0 */
/* JADX INFO: loaded from: classes.dex */
public class zi0 extends yi0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f10006a;

    public zi0(byte[] bArr) {
        if (bArr == null) {
            throw null;
        }
        this.f10006a = bArr;
    }

    public int a() {
        return 0;
    }

    @Override // com.google.android.gms.internal.icing.zzcf
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcf) || zzc() != ((zzcf) obj).zzc()) {
            return false;
        }
        if (zzc() == 0) {
            return true;
        }
        if (!(obj instanceof zi0)) {
            return obj.equals(this);
        }
        zi0 zi0Var = (zi0) obj;
        int iZzl = zzl();
        int iZzl2 = zi0Var.zzl();
        if (iZzl != 0 && iZzl2 != 0 && iZzl != iZzl2) {
            return false;
        }
        int iZzc = zzc();
        if (iZzc > zi0Var.zzc()) {
            int iZzc2 = zzc();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(iZzc);
            sb.append(iZzc2);
            throw new IllegalArgumentException(sb.toString());
        }
        if (iZzc > zi0Var.zzc()) {
            int iZzc3 = zi0Var.zzc();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(iZzc);
            sb2.append(", ");
            sb2.append(iZzc3);
            throw new IllegalArgumentException(sb2.toString());
        }
        if (!(zi0Var instanceof zi0)) {
            return zi0Var.zze(0, iZzc).equals(zze(0, iZzc));
        }
        byte[] bArr = this.f10006a;
        byte[] bArr2 = zi0Var.f10006a;
        zi0Var.a();
        int i = 0;
        int i2 = 0;
        while (i < iZzc) {
            if (bArr[i] != bArr2[i2]) {
                return false;
            }
            i++;
            i2++;
        }
        return true;
    }

    @Override // com.google.android.gms.internal.icing.zzcf
    public byte zza(int i) {
        return this.f10006a[i];
    }

    @Override // com.google.android.gms.internal.icing.zzcf
    public byte zzb(int i) {
        return this.f10006a[i];
    }

    @Override // com.google.android.gms.internal.icing.zzcf
    public int zzc() {
        return this.f10006a.length;
    }

    @Override // com.google.android.gms.internal.icing.zzcf
    public final zzcf zze(int i, int i2) {
        zzcf.zzm(0, i2, zzc());
        return i2 == 0 ? zzcf.zzb : new wi0(this.f10006a, 0, i2);
    }

    @Override // com.google.android.gms.internal.icing.zzcf
    public final void zzf(ri0 ri0Var) throws IOException {
        ((ej0) ri0Var).a(this.f10006a, 0, zzc());
    }

    @Override // com.google.android.gms.internal.icing.zzcf
    public final String zzg(Charset charset) {
        return new String(this.f10006a, 0, zzc(), charset);
    }

    @Override // com.google.android.gms.internal.icing.zzcf
    public final boolean zzh() {
        return qj0.a(this.f10006a, 0, zzc());
    }

    @Override // com.google.android.gms.internal.icing.zzcf
    public final int zzi(int i, int i2, int i3) {
        return gj0.a(i, this.f10006a, 0, i3);
    }
}
