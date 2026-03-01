package supwisdom;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.zaw;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class kg0 implements Parcelable.Creator<zaw> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zaw createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        int iV = 0;
        Scope[] scopeArr = null;
        int iV2 = 0;
        int iV3 = 0;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            int iA2 = SafeParcelReader.a(iA);
            if (iA2 == 1) {
                iV = SafeParcelReader.v(parcel, iA);
            } else if (iA2 == 2) {
                iV2 = SafeParcelReader.v(parcel, iA);
            } else if (iA2 == 3) {
                iV3 = SafeParcelReader.v(parcel, iA);
            } else if (iA2 != 4) {
                SafeParcelReader.y(parcel, iA);
            } else {
                scopeArr = (Scope[]) SafeParcelReader.b(parcel, iA, Scope.CREATOR);
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new zaw(iV, iV2, iV3, scopeArr);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zaw[] newArray(int i) {
        return new zaw[i];
    }
}
