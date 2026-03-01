package supwisdom;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.zau;
import com.google.android.gms.signin.internal.zak;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class jk0 implements Parcelable.Creator<zak> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zak createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        ConnectionResult connectionResult = null;
        zau zauVar = null;
        int iV = 0;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            int iA2 = SafeParcelReader.a(iA);
            if (iA2 == 1) {
                iV = SafeParcelReader.v(parcel, iA);
            } else if (iA2 == 2) {
                connectionResult = (ConnectionResult) SafeParcelReader.a(parcel, iA, ConnectionResult.CREATOR);
            } else if (iA2 != 3) {
                SafeParcelReader.y(parcel, iA);
            } else {
                zauVar = (zau) SafeParcelReader.a(parcel, iA, zau.CREATOR);
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new zak(iV, connectionResult, zauVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zak[] newArray(int i) {
        return new zak[i];
    }
}
