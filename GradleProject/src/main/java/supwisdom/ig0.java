package supwisdom;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.zau;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class ig0 implements Parcelable.Creator<zau> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zau createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        IBinder iBinderU = null;
        ConnectionResult connectionResult = null;
        int iV = 0;
        boolean zR = false;
        boolean zR2 = false;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            int iA2 = SafeParcelReader.a(iA);
            if (iA2 == 1) {
                iV = SafeParcelReader.v(parcel, iA);
            } else if (iA2 == 2) {
                iBinderU = SafeParcelReader.u(parcel, iA);
            } else if (iA2 == 3) {
                connectionResult = (ConnectionResult) SafeParcelReader.a(parcel, iA, ConnectionResult.CREATOR);
            } else if (iA2 == 4) {
                zR = SafeParcelReader.r(parcel, iA);
            } else if (iA2 != 5) {
                SafeParcelReader.y(parcel, iA);
            } else {
                zR2 = SafeParcelReader.r(parcel, iA);
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new zau(iV, iBinderU, connectionResult, zR, zR2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zau[] newArray(int i) {
        return new zau[i];
    }
}
