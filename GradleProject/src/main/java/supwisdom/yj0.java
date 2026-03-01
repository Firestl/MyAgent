package supwisdom;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.icing.zzm;
import com.google.android.gms.internal.icing.zzs;
import com.google.android.gms.internal.icing.zzu;

/* JADX INFO: compiled from: com.google.firebase:firebase-appindexing@@20.0.0 */
/* JADX INFO: loaded from: classes.dex */
public final class yj0 implements Parcelable.Creator<zzs> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzs createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        String strN = null;
        String strN2 = null;
        String strN3 = null;
        zzm[] zzmVarArr = null;
        String strN4 = null;
        zzu zzuVar = null;
        boolean zR = false;
        int iV = 1;
        boolean zR2 = false;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            int iA2 = SafeParcelReader.a(iA);
            if (iA2 == 11) {
                strN4 = SafeParcelReader.n(parcel, iA);
            } else if (iA2 != 12) {
                switch (iA2) {
                    case 1:
                        strN = SafeParcelReader.n(parcel, iA);
                        break;
                    case 2:
                        strN2 = SafeParcelReader.n(parcel, iA);
                        break;
                    case 3:
                        zR = SafeParcelReader.r(parcel, iA);
                        break;
                    case 4:
                        iV = SafeParcelReader.v(parcel, iA);
                        break;
                    case 5:
                        zR2 = SafeParcelReader.r(parcel, iA);
                        break;
                    case 6:
                        strN3 = SafeParcelReader.n(parcel, iA);
                        break;
                    case 7:
                        zzmVarArr = (zzm[]) SafeParcelReader.b(parcel, iA, zzm.CREATOR);
                        break;
                    default:
                        SafeParcelReader.y(parcel, iA);
                        break;
                }
            } else {
                zzuVar = (zzu) SafeParcelReader.a(parcel, iA, zzu.CREATOR);
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new zzs(strN, strN2, zR, iV, zR2, strN3, zzmVarArr, strN4, zzuVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzs[] newArray(int i) {
        return new zzs[i];
    }
}
