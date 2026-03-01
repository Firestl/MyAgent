package supwisdom;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.android.gms.common.server.converter.zab;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class ah0 implements Parcelable.Creator<zab> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zab createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        int iV = 0;
        StringToIntConverter stringToIntConverter = null;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            int iA2 = SafeParcelReader.a(iA);
            if (iA2 == 1) {
                iV = SafeParcelReader.v(parcel, iA);
            } else if (iA2 != 2) {
                SafeParcelReader.y(parcel, iA);
            } else {
                stringToIntConverter = (StringToIntConverter) SafeParcelReader.a(parcel, iA, StringToIntConverter.CREATOR);
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new zab(iV, stringToIntConverter);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zab[] newArray(int i) {
        return new zab[i];
    }
}
