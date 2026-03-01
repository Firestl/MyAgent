package supwisdom;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.firebase.appindexing.internal.Thing;
import com.google.firebase.appindexing.internal.zzac;

/* JADX INFO: compiled from: com.google.firebase:firebase-appindexing@@20.0.0 */
/* JADX INFO: loaded from: classes.dex */
public final class ls0 implements Parcelable.Creator<Thing> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Thing createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        Bundle bundleF = null;
        zzac zzacVar = null;
        String strN = null;
        String strN2 = null;
        int iV = 0;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            int iA2 = SafeParcelReader.a(iA);
            if (iA2 == 1) {
                bundleF = SafeParcelReader.f(parcel, iA);
            } else if (iA2 == 2) {
                zzacVar = (zzac) SafeParcelReader.a(parcel, iA, zzac.CREATOR);
            } else if (iA2 == 3) {
                strN = SafeParcelReader.n(parcel, iA);
            } else if (iA2 == 4) {
                strN2 = SafeParcelReader.n(parcel, iA);
            } else if (iA2 != 1000) {
                SafeParcelReader.y(parcel, iA);
            } else {
                iV = SafeParcelReader.v(parcel, iA);
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new Thing(iV, bundleF, zzacVar, strN, strN2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Thing[] newArray(int i) {
        return new Thing[i];
    }
}
