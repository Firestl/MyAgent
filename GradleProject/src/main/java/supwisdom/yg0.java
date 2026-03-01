package supwisdom;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.RootTelemetryConfiguration;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class yg0 implements Parcelable.Creator<RootTelemetryConfiguration> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ RootTelemetryConfiguration createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        int iV = 0;
        boolean zR = false;
        boolean zR2 = false;
        int iV2 = 0;
        int iV3 = 0;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            int iA2 = SafeParcelReader.a(iA);
            if (iA2 == 1) {
                iV = SafeParcelReader.v(parcel, iA);
            } else if (iA2 == 2) {
                zR = SafeParcelReader.r(parcel, iA);
            } else if (iA2 == 3) {
                zR2 = SafeParcelReader.r(parcel, iA);
            } else if (iA2 == 4) {
                iV2 = SafeParcelReader.v(parcel, iA);
            } else if (iA2 != 5) {
                SafeParcelReader.y(parcel, iA);
            } else {
                iV3 = SafeParcelReader.v(parcel, iA);
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new RootTelemetryConfiguration(iV, zR, zR2, iV2, iV3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ RootTelemetryConfiguration[] newArray(int i) {
        return new RootTelemetryConfiguration[i];
    }
}
