package supwisdom;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.server.response.SafeParcelResponse;
import com.google.android.gms.common.server.response.zaj;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class hh0 implements Parcelable.Creator<SafeParcelResponse> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ SafeParcelResponse createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        Parcel parcelL = null;
        zaj zajVar = null;
        int iV = 0;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            int iA2 = SafeParcelReader.a(iA);
            if (iA2 == 1) {
                iV = SafeParcelReader.v(parcel, iA);
            } else if (iA2 == 2) {
                parcelL = SafeParcelReader.l(parcel, iA);
            } else if (iA2 != 3) {
                SafeParcelReader.y(parcel, iA);
            } else {
                zajVar = (zaj) SafeParcelReader.a(parcel, iA, zaj.CREATOR);
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new SafeParcelResponse(iV, parcelL, zajVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ SafeParcelResponse[] newArray(int i) {
        return new SafeParcelResponse[i];
    }
}
