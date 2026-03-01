package supwisdom;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.icing.zzg;
import com.google.android.gms.internal.icing.zzi;
import com.google.android.gms.internal.icing.zzx;

/* JADX INFO: compiled from: com.google.firebase:firebase-appindexing@@20.0.0 */
/* JADX INFO: loaded from: classes.dex */
public final class ak0 implements Parcelable.Creator<zzx> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzx createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        zzi zziVar = null;
        String strN = null;
        zzg zzgVar = null;
        String strN2 = null;
        long jW = 0;
        int iV = 0;
        boolean zR = false;
        int iV2 = -1;
        int iV3 = 0;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            switch (SafeParcelReader.a(iA)) {
                case 1:
                    zziVar = (zzi) SafeParcelReader.a(parcel, iA, zzi.CREATOR);
                    break;
                case 2:
                    jW = SafeParcelReader.w(parcel, iA);
                    break;
                case 3:
                    iV = SafeParcelReader.v(parcel, iA);
                    break;
                case 4:
                    strN = SafeParcelReader.n(parcel, iA);
                    break;
                case 5:
                    zzgVar = (zzg) SafeParcelReader.a(parcel, iA, zzg.CREATOR);
                    break;
                case 6:
                    zR = SafeParcelReader.r(parcel, iA);
                    break;
                case 7:
                    iV2 = SafeParcelReader.v(parcel, iA);
                    break;
                case 8:
                    iV3 = SafeParcelReader.v(parcel, iA);
                    break;
                case 9:
                    strN2 = SafeParcelReader.n(parcel, iA);
                    break;
                default:
                    SafeParcelReader.y(parcel, iA);
                    break;
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new zzx(zziVar, jW, iV, strN, zzgVar, zR, iV2, iV3, strN2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzx[] newArray(int i) {
        return new zzx[i];
    }
}
