package supwisdom;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.firebase.appindexing.internal.zzb;

/* JADX INFO: compiled from: com.google.firebase:firebase-appindexing@@20.0.0 */
/* JADX INFO: loaded from: classes.dex */
public final class ss0 implements Parcelable.Creator<zzb> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzb createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        String strN = null;
        String strN2 = null;
        byte[] bArrG = null;
        int iV = 0;
        boolean zR = false;
        boolean zR2 = false;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            switch (SafeParcelReader.a(iA)) {
                case 1:
                    iV = SafeParcelReader.v(parcel, iA);
                    break;
                case 2:
                    zR = SafeParcelReader.r(parcel, iA);
                    break;
                case 3:
                    strN = SafeParcelReader.n(parcel, iA);
                    break;
                case 4:
                    strN2 = SafeParcelReader.n(parcel, iA);
                    break;
                case 5:
                    bArrG = SafeParcelReader.g(parcel, iA);
                    break;
                case 6:
                    zR2 = SafeParcelReader.r(parcel, iA);
                    break;
                default:
                    SafeParcelReader.y(parcel, iA);
                    break;
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new zzb(iV, zR, strN, strN2, bArrG, zR2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzb[] newArray(int i) {
        return new zzb[i];
    }
}
