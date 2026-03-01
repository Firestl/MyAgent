package supwisdom;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.icing.zzi;

/* JADX INFO: compiled from: com.google.firebase:firebase-appindexing@@20.0.0 */
/* JADX INFO: loaded from: classes.dex */
public final class sj0 implements Parcelable.Creator<zzi> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzi createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        String strN = null;
        String strN2 = null;
        String strN3 = null;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            int iA2 = SafeParcelReader.a(iA);
            if (iA2 == 1) {
                strN = SafeParcelReader.n(parcel, iA);
            } else if (iA2 == 2) {
                strN2 = SafeParcelReader.n(parcel, iA);
            } else if (iA2 != 3) {
                SafeParcelReader.y(parcel, iA);
            } else {
                strN3 = SafeParcelReader.n(parcel, iA);
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new zzi(strN, strN2, strN3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzi[] newArray(int i) {
        return new zzi[i];
    }
}
