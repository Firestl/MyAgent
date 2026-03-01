package supwisdom;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class df0 implements Parcelable.Creator<Status> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Status createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        String strN = null;
        PendingIntent pendingIntent = null;
        ConnectionResult connectionResult = null;
        int iV = 0;
        int iV2 = 0;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            int iA2 = SafeParcelReader.a(iA);
            if (iA2 == 1) {
                iV2 = SafeParcelReader.v(parcel, iA);
            } else if (iA2 == 2) {
                strN = SafeParcelReader.n(parcel, iA);
            } else if (iA2 == 3) {
                pendingIntent = (PendingIntent) SafeParcelReader.a(parcel, iA, PendingIntent.CREATOR);
            } else if (iA2 == 4) {
                connectionResult = (ConnectionResult) SafeParcelReader.a(parcel, iA, ConnectionResult.CREATOR);
            } else if (iA2 != 1000) {
                SafeParcelReader.y(parcel, iA);
            } else {
                iV = SafeParcelReader.v(parcel, iA);
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new Status(iV, iV2, strN, pendingIntent, connectionResult);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Status[] newArray(int i) {
        return new Status[i];
    }
}
