package supwisdom;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.zaaa;
import com.google.android.gms.common.internal.zao;
import java.util.ArrayList;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class zf0 implements Parcelable.Creator<zaaa> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zaaa createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        int iV = 0;
        ArrayList arrayListC = null;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            int iA2 = SafeParcelReader.a(iA);
            if (iA2 == 1) {
                iV = SafeParcelReader.v(parcel, iA);
            } else if (iA2 != 2) {
                SafeParcelReader.y(parcel, iA);
            } else {
                arrayListC = SafeParcelReader.c(parcel, iA, zao.CREATOR);
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new zaaa(iV, arrayListC);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zaaa[] newArray(int i) {
        return new zaaa[i];
    }
}
