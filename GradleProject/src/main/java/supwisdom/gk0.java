package supwisdom;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.signin.internal.zaa;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class gk0 implements Parcelable.Creator<zaa> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zaa createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        int iV = 0;
        Intent intent = null;
        int iV2 = 0;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            int iA2 = SafeParcelReader.a(iA);
            if (iA2 == 1) {
                iV = SafeParcelReader.v(parcel, iA);
            } else if (iA2 == 2) {
                iV2 = SafeParcelReader.v(parcel, iA);
            } else if (iA2 != 3) {
                SafeParcelReader.y(parcel, iA);
            } else {
                intent = (Intent) SafeParcelReader.a(parcel, iA, Intent.CREATOR);
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new zaa(iV, iV2, intent);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zaa[] newArray(int i) {
        return new zaa[i];
    }
}
