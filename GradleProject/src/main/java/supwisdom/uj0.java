package supwisdom;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.icing.zzm;

/* JADX INFO: compiled from: com.google.firebase:firebase-appindexing@@20.0.0 */
/* JADX INFO: loaded from: classes.dex */
public final class uj0 implements Parcelable.Creator<zzm> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzm createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        int iV = 0;
        Bundle bundleF = null;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            int iA2 = SafeParcelReader.a(iA);
            if (iA2 == 1) {
                iV = SafeParcelReader.v(parcel, iA);
            } else if (iA2 != 2) {
                SafeParcelReader.y(parcel, iA);
            } else {
                bundleF = SafeParcelReader.f(parcel, iA);
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new zzm(iV, bundleF);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzm[] newArray(int i) {
        return new zzm[i];
    }
}
