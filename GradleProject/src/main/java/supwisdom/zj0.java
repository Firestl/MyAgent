package supwisdom;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.icing.zzu;

/* JADX INFO: compiled from: com.google.firebase:firebase-appindexing@@20.0.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zj0 implements Parcelable.Creator<zzu> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzu createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        boolean zR = false;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            if (SafeParcelReader.a(iA) != 1) {
                SafeParcelReader.y(parcel, iA);
            } else {
                zR = SafeParcelReader.r(parcel, iA);
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new zzu(zR);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzu[] newArray(int i) {
        return new zzu[i];
    }
}
