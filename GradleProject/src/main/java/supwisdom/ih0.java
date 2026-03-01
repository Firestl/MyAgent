package supwisdom;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.server.FavaDiagnosticsEntity;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class ih0 implements Parcelable.Creator<FavaDiagnosticsEntity> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ FavaDiagnosticsEntity createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        int iV = 0;
        String strN = null;
        int iV2 = 0;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            int iA2 = SafeParcelReader.a(iA);
            if (iA2 == 1) {
                iV = SafeParcelReader.v(parcel, iA);
            } else if (iA2 == 2) {
                strN = SafeParcelReader.n(parcel, iA);
            } else if (iA2 != 3) {
                SafeParcelReader.y(parcel, iA);
            } else {
                iV2 = SafeParcelReader.v(parcel, iA);
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new FavaDiagnosticsEntity(iV, strN, iV2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ FavaDiagnosticsEntity[] newArray(int i) {
        return new FavaDiagnosticsEntity[i];
    }
}
