package supwisdom;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.firebase.appindexing.internal.Thing;
import com.google.firebase.appindexing.internal.zzc;
import com.google.firebase.appindexing.internal.zzz;

/* JADX INFO: compiled from: com.google.firebase:firebase-appindexing@@20.0.0 */
/* JADX INFO: loaded from: classes.dex */
public final class js0 implements Parcelable.Creator<zzz> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzz createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        Thing[] thingArr = null;
        String[] strArrO = null;
        String[] strArrO2 = null;
        zzc zzcVar = null;
        String strN = null;
        String strN2 = null;
        int iV = 0;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            switch (SafeParcelReader.a(iA)) {
                case 1:
                    iV = SafeParcelReader.v(parcel, iA);
                    break;
                case 2:
                    thingArr = (Thing[]) SafeParcelReader.b(parcel, iA, Thing.CREATOR);
                    break;
                case 3:
                    strArrO = SafeParcelReader.o(parcel, iA);
                    break;
                case 4:
                default:
                    SafeParcelReader.y(parcel, iA);
                    break;
                case 5:
                    strArrO2 = SafeParcelReader.o(parcel, iA);
                    break;
                case 6:
                    zzcVar = (zzc) SafeParcelReader.a(parcel, iA, zzc.CREATOR);
                    break;
                case 7:
                    strN = SafeParcelReader.n(parcel, iA);
                    break;
                case 8:
                    strN2 = SafeParcelReader.n(parcel, iA);
                    break;
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new zzz(iV, thingArr, strArrO, strArrO2, zzcVar, strN, strN2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzz[] newArray(int i) {
        return new zzz[i];
    }
}
