package supwisdom;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.zzq;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class ei0 implements Parcelable.Creator<zzq> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzq createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        boolean zR = false;
        String strN = null;
        IBinder iBinderU = null;
        boolean zR2 = false;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            int iA2 = SafeParcelReader.a(iA);
            if (iA2 == 1) {
                strN = SafeParcelReader.n(parcel, iA);
            } else if (iA2 == 2) {
                iBinderU = SafeParcelReader.u(parcel, iA);
            } else if (iA2 == 3) {
                zR = SafeParcelReader.r(parcel, iA);
            } else if (iA2 != 4) {
                SafeParcelReader.y(parcel, iA);
            } else {
                zR2 = SafeParcelReader.r(parcel, iA);
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new zzq(strN, iBinderU, zR, zR2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzq[] newArray(int i) {
        return new zzq[i];
    }
}
