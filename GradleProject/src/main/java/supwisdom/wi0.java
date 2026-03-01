package supwisdom;

import com.google.android.gms.internal.icing.zzcf;

/* JADX INFO: compiled from: com.google.firebase:firebase-appindexing@@20.0.0 */
/* JADX INFO: loaded from: classes.dex */
public final class wi0 extends zi0 {
    public final int b;

    public wi0(byte[] bArr, int i, int i2) {
        super(bArr);
        zzcf.zzm(0, i2, bArr.length);
        this.b = i2;
    }

    @Override // supwisdom.zi0
    public final int a() {
        return 0;
    }

    @Override // supwisdom.zi0, com.google.android.gms.internal.icing.zzcf
    public final byte zza(int i) {
        int i2 = this.b;
        if (((i2 - (i + 1)) | i) >= 0) {
            return this.f10006a[i];
        }
        if (i < 0) {
            StringBuilder sb = new StringBuilder(22);
            sb.append("Index < 0: ");
            sb.append(i);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder(40);
        sb2.append("Index > length: ");
        sb2.append(i);
        sb2.append(", ");
        sb2.append(i2);
        throw new ArrayIndexOutOfBoundsException(sb2.toString());
    }

    @Override // supwisdom.zi0, com.google.android.gms.internal.icing.zzcf
    public final byte zzb(int i) {
        return this.f10006a[i];
    }

    @Override // supwisdom.zi0, com.google.android.gms.internal.icing.zzcf
    public final int zzc() {
        return this.b;
    }
}
