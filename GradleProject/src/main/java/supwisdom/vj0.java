package supwisdom;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.icing.zzo;
import com.google.android.gms.internal.icing.zzx;
import java.util.ArrayList;

/* JADX INFO: compiled from: com.google.firebase:firebase-appindexing@@20.0.0 */
/* JADX INFO: loaded from: classes.dex */
public final class vj0 implements Parcelable.Creator<zzo> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzo createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        Status status = null;
        ArrayList arrayListC = null;
        String[] strArrO = null;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            int iA2 = SafeParcelReader.a(iA);
            if (iA2 == 1) {
                status = (Status) SafeParcelReader.a(parcel, iA, Status.CREATOR);
            } else if (iA2 == 2) {
                arrayListC = SafeParcelReader.c(parcel, iA, zzx.CREATOR);
            } else if (iA2 != 3) {
                SafeParcelReader.y(parcel, iA);
            } else {
                strArrO = SafeParcelReader.o(parcel, iA);
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new zzo(status, arrayListC, strArrO);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzo[] newArray(int i) {
        return new zzo[i];
    }
}
