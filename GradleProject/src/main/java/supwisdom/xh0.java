package supwisdom;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class xh0 implements Parcelable.Creator<ConnectionResult> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ ConnectionResult createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        PendingIntent pendingIntent = null;
        String strN = null;
        int iV = 0;
        int iV2 = 0;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            int iA2 = SafeParcelReader.a(iA);
            if (iA2 == 1) {
                iV = SafeParcelReader.v(parcel, iA);
            } else if (iA2 == 2) {
                iV2 = SafeParcelReader.v(parcel, iA);
            } else if (iA2 == 3) {
                pendingIntent = (PendingIntent) SafeParcelReader.a(parcel, iA, PendingIntent.CREATOR);
            } else if (iA2 != 4) {
                SafeParcelReader.y(parcel, iA);
            } else {
                strN = SafeParcelReader.n(parcel, iA);
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new ConnectionResult(iV, iV2, pendingIntent, strN);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ ConnectionResult[] newArray(int i) {
        return new ConnectionResult[i];
    }
}
