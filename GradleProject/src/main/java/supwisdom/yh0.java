package supwisdom;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class yh0 implements Parcelable.Creator<Feature> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Feature createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        String strN = null;
        int iV = 0;
        long jW = -1;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            int iA2 = SafeParcelReader.a(iA);
            if (iA2 == 1) {
                strN = SafeParcelReader.n(parcel, iA);
            } else if (iA2 == 2) {
                iV = SafeParcelReader.v(parcel, iA);
            } else if (iA2 != 3) {
                SafeParcelReader.y(parcel, iA);
            } else {
                jW = SafeParcelReader.w(parcel, iA);
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new Feature(strN, iV, jW);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Feature[] newArray(int i) {
        return new Feature[i];
    }
}
