package supwisdom;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.server.response.zaj;
import com.google.android.gms.common.server.response.zam;
import java.util.ArrayList;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class gh0 implements Parcelable.Creator<zaj> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zaj createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        ArrayList arrayListC = null;
        String strN = null;
        int iV = 0;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            int iA2 = SafeParcelReader.a(iA);
            if (iA2 == 1) {
                iV = SafeParcelReader.v(parcel, iA);
            } else if (iA2 == 2) {
                arrayListC = SafeParcelReader.c(parcel, iA, zam.CREATOR);
            } else if (iA2 != 3) {
                SafeParcelReader.y(parcel, iA);
            } else {
                strN = SafeParcelReader.n(parcel, iA);
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new zaj(iV, arrayListC, strN);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zaj[] newArray(int i) {
        return new zaj[i];
    }
}
