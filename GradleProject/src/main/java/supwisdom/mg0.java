package supwisdom;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.ConnectionTelemetryConfiguration;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.zzc;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class mg0 implements Parcelable.Creator<zzc> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzc createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        Bundle bundleF = null;
        Feature[] featureArr = null;
        ConnectionTelemetryConfiguration connectionTelemetryConfiguration = null;
        int iV = 0;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            int iA2 = SafeParcelReader.a(iA);
            if (iA2 == 1) {
                bundleF = SafeParcelReader.f(parcel, iA);
            } else if (iA2 == 2) {
                featureArr = (Feature[]) SafeParcelReader.b(parcel, iA, Feature.CREATOR);
            } else if (iA2 == 3) {
                iV = SafeParcelReader.v(parcel, iA);
            } else if (iA2 != 4) {
                SafeParcelReader.y(parcel, iA);
            } else {
                connectionTelemetryConfiguration = (ConnectionTelemetryConfiguration) SafeParcelReader.a(parcel, iA, ConnectionTelemetryConfiguration.CREATOR);
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new zzc(bundleF, featureArr, iV, connectionTelemetryConfiguration);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzc[] newArray(int i) {
        return new zzc[i];
    }
}
