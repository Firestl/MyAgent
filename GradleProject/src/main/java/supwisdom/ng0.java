package supwisdom;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ConnectionTelemetryConfiguration;
import com.google.android.gms.common.internal.RootTelemetryConfiguration;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class ng0 implements Parcelable.Creator<ConnectionTelemetryConfiguration> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ ConnectionTelemetryConfiguration createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        RootTelemetryConfiguration rootTelemetryConfiguration = null;
        int[] iArrJ = null;
        boolean zR = false;
        boolean zR2 = false;
        int iV = 0;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            int iA2 = SafeParcelReader.a(iA);
            if (iA2 == 1) {
                rootTelemetryConfiguration = (RootTelemetryConfiguration) SafeParcelReader.a(parcel, iA, RootTelemetryConfiguration.CREATOR);
            } else if (iA2 == 2) {
                zR = SafeParcelReader.r(parcel, iA);
            } else if (iA2 == 3) {
                zR2 = SafeParcelReader.r(parcel, iA);
            } else if (iA2 == 4) {
                iArrJ = SafeParcelReader.j(parcel, iA);
            } else if (iA2 != 5) {
                SafeParcelReader.y(parcel, iA);
            } else {
                iV = SafeParcelReader.v(parcel, iA);
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new ConnectionTelemetryConfiguration(rootTelemetryConfiguration, zR, zR2, iArrJ, iV);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ ConnectionTelemetryConfiguration[] newArray(int i) {
        return new ConnectionTelemetryConfiguration[i];
    }
}
