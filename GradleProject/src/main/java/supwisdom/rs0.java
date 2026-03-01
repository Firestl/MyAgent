package supwisdom;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.firebase.appindexing.internal.zzac;

/* JADX INFO: compiled from: com.google.firebase:firebase-appindexing@@20.0.0 */
/* JADX INFO: loaded from: classes.dex */
public final class rs0 implements Parcelable.Creator<zzac> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzac createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        String strN = null;
        Bundle bundleF = null;
        Bundle bundleF2 = null;
        boolean zR = false;
        int iV = 0;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            int iA2 = SafeParcelReader.a(iA);
            if (iA2 == 1) {
                zR = SafeParcelReader.r(parcel, iA);
            } else if (iA2 == 2) {
                iV = SafeParcelReader.v(parcel, iA);
            } else if (iA2 == 3) {
                strN = SafeParcelReader.n(parcel, iA);
            } else if (iA2 == 4) {
                bundleF = SafeParcelReader.f(parcel, iA);
            } else if (iA2 != 5) {
                SafeParcelReader.y(parcel, iA);
            } else {
                bundleF2 = SafeParcelReader.f(parcel, iA);
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new zzac(zR, iV, strN, bundleF, bundleF2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzac[] newArray(int i) {
        return new zzac[i];
    }
}
