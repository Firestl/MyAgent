package supwisdom;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.firebase.appindexing.internal.zzb;
import com.google.firebase.appindexing.internal.zzc;

/* JADX INFO: compiled from: com.google.firebase:firebase-appindexing@@20.0.0 */
/* JADX INFO: loaded from: classes.dex */
public final class ns0 implements Parcelable.Creator<zzc> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzc createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        String strN = null;
        String strN2 = null;
        String strN3 = null;
        String strN4 = null;
        zzb zzbVar = null;
        String strN5 = null;
        Bundle bundleF = null;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            switch (SafeParcelReader.a(iA)) {
                case 1:
                    strN = SafeParcelReader.n(parcel, iA);
                    break;
                case 2:
                    strN2 = SafeParcelReader.n(parcel, iA);
                    break;
                case 3:
                    strN3 = SafeParcelReader.n(parcel, iA);
                    break;
                case 4:
                    strN4 = SafeParcelReader.n(parcel, iA);
                    break;
                case 5:
                    zzbVar = (zzb) SafeParcelReader.a(parcel, iA, zzb.CREATOR);
                    break;
                case 6:
                    strN5 = SafeParcelReader.n(parcel, iA);
                    break;
                case 7:
                    bundleF = SafeParcelReader.f(parcel, iA);
                    break;
                default:
                    SafeParcelReader.y(parcel, iA);
                    break;
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new zzc(strN, strN2, strN3, strN4, zzbVar, strN5, bundleF);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzc[] newArray(int i) {
        return new zzc[i];
    }
}
