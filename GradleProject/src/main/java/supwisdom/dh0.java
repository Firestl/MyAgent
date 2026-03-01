package supwisdom;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.server.converter.zab;
import com.google.android.gms.common.server.response.FastJsonResponse;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class dh0 implements Parcelable.Creator<FastJsonResponse.Field> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ FastJsonResponse.Field createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        String strN = null;
        String strN2 = null;
        zab zabVar = null;
        int iV = 0;
        int iV2 = 0;
        boolean zR = false;
        int iV3 = 0;
        boolean zR2 = false;
        int iV4 = 0;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            switch (SafeParcelReader.a(iA)) {
                case 1:
                    iV = SafeParcelReader.v(parcel, iA);
                    break;
                case 2:
                    iV2 = SafeParcelReader.v(parcel, iA);
                    break;
                case 3:
                    zR = SafeParcelReader.r(parcel, iA);
                    break;
                case 4:
                    iV3 = SafeParcelReader.v(parcel, iA);
                    break;
                case 5:
                    zR2 = SafeParcelReader.r(parcel, iA);
                    break;
                case 6:
                    strN = SafeParcelReader.n(parcel, iA);
                    break;
                case 7:
                    iV4 = SafeParcelReader.v(parcel, iA);
                    break;
                case 8:
                    strN2 = SafeParcelReader.n(parcel, iA);
                    break;
                case 9:
                    zabVar = (zab) SafeParcelReader.a(parcel, iA, zab.CREATOR);
                    break;
                default:
                    SafeParcelReader.y(parcel, iA);
                    break;
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new FastJsonResponse.Field(iV, iV2, zR, iV3, zR2, strN, iV4, strN2, zabVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ FastJsonResponse.Field[] newArray(int i) {
        return new FastJsonResponse.Field[i];
    }
}
