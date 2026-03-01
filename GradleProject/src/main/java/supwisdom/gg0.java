package supwisdom;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.zao;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class gg0 implements Parcelable.Creator<zao> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zao createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        long jW = 0;
        long jW2 = 0;
        int iV = 0;
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
            } else if (iA2 == 4) {
                jW = SafeParcelReader.w(parcel, iA);
            } else if (iA2 != 5) {
                SafeParcelReader.y(parcel, iA);
            } else {
                jW2 = SafeParcelReader.w(parcel, iA);
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new zao(iV, iV2, iV3, jW, jW2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zao[] newArray(int i) {
        return new zao[i];
    }
}
