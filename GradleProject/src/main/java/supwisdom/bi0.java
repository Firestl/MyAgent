package supwisdom;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.zzj;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class bi0 implements Parcelable.Creator<zzj> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzj createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        String strN = null;
        IBinder iBinderU = null;
        boolean zR = false;
        boolean zR2 = false;
        boolean zR3 = false;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            int iA2 = SafeParcelReader.a(iA);
            if (iA2 == 1) {
                strN = SafeParcelReader.n(parcel, iA);
            } else if (iA2 == 2) {
                zR = SafeParcelReader.r(parcel, iA);
            } else if (iA2 == 3) {
                zR2 = SafeParcelReader.r(parcel, iA);
            } else if (iA2 == 4) {
                iBinderU = SafeParcelReader.u(parcel, iA);
            } else if (iA2 != 5) {
                SafeParcelReader.y(parcel, iA);
            } else {
                zR3 = SafeParcelReader.r(parcel, iA);
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new zzj(strN, zR, zR2, iBinderU, zR3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzj[] newArray(int i) {
        return new zzj[i];
    }
}
