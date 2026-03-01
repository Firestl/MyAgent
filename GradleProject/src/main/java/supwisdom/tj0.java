package supwisdom;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.icing.zzk;
import com.google.android.gms.internal.icing.zzs;

/* JADX INFO: compiled from: com.google.firebase:firebase-appindexing@@20.0.0 */
/* JADX INFO: loaded from: classes.dex */
public final class tj0 implements Parcelable.Creator<zzk> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzk createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        String strN = null;
        zzs zzsVar = null;
        byte[] bArrG = null;
        int iV = -1;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            int iA2 = SafeParcelReader.a(iA);
            if (iA2 == 1) {
                strN = SafeParcelReader.n(parcel, iA);
            } else if (iA2 == 3) {
                zzsVar = (zzs) SafeParcelReader.a(parcel, iA, zzs.CREATOR);
            } else if (iA2 == 4) {
                iV = SafeParcelReader.v(parcel, iA);
            } else if (iA2 != 5) {
                SafeParcelReader.y(parcel, iA);
            } else {
                bArrG = SafeParcelReader.g(parcel, iA);
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new zzk(strN, zzsVar, iV, bArrG);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzk[] newArray(int i) {
        return new zzk[i];
    }
}
