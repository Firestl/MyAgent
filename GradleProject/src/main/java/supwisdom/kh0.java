package supwisdom;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.stats.WakeLockEvent;
import java.util.ArrayList;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class kh0 implements Parcelable.Creator<WakeLockEvent> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ WakeLockEvent createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        long jW = 0;
        long jW2 = 0;
        long jW3 = 0;
        String strN = null;
        ArrayList<String> arrayListP = null;
        String strN2 = null;
        String strN3 = null;
        String strN4 = null;
        String strN5 = null;
        int iV = 0;
        int iV2 = 0;
        int iV3 = 0;
        int iV4 = 0;
        float fT = 0.0f;
        boolean zR = false;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            switch (SafeParcelReader.a(iA)) {
                case 1:
                    iV = SafeParcelReader.v(parcel, iA);
                    break;
                case 2:
                    jW = SafeParcelReader.w(parcel, iA);
                    break;
                case 3:
                case 7:
                case 9:
                default:
                    SafeParcelReader.y(parcel, iA);
                    break;
                case 4:
                    strN = SafeParcelReader.n(parcel, iA);
                    break;
                case 5:
                    iV3 = SafeParcelReader.v(parcel, iA);
                    break;
                case 6:
                    arrayListP = SafeParcelReader.p(parcel, iA);
                    break;
                case 8:
                    jW2 = SafeParcelReader.w(parcel, iA);
                    break;
                case 10:
                    strN3 = SafeParcelReader.n(parcel, iA);
                    break;
                case 11:
                    iV2 = SafeParcelReader.v(parcel, iA);
                    break;
                case 12:
                    strN2 = SafeParcelReader.n(parcel, iA);
                    break;
                case 13:
                    strN4 = SafeParcelReader.n(parcel, iA);
                    break;
                case 14:
                    iV4 = SafeParcelReader.v(parcel, iA);
                    break;
                case 15:
                    fT = SafeParcelReader.t(parcel, iA);
                    break;
                case 16:
                    jW3 = SafeParcelReader.w(parcel, iA);
                    break;
                case 17:
                    strN5 = SafeParcelReader.n(parcel, iA);
                    break;
                case 18:
                    zR = SafeParcelReader.r(parcel, iA);
                    break;
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new WakeLockEvent(iV, jW, iV2, strN, iV3, arrayListP, strN2, jW2, iV4, strN3, strN4, fT, jW3, strN5, zR);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ WakeLockEvent[] newArray(int i) {
        return new WakeLockEvent[i];
    }
}
