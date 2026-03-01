package supwisdom;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.icing.zzg;
import com.google.android.gms.internal.icing.zzk;

/* JADX INFO: compiled from: com.google.firebase:firebase-appindexing@@20.0.0 */
/* JADX INFO: loaded from: classes.dex */
public final class rj0 implements Parcelable.Creator<zzg> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzg createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        zzk[] zzkVarArr = null;
        String strN = null;
        Account account = null;
        boolean zR = false;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            int iA2 = SafeParcelReader.a(iA);
            if (iA2 == 1) {
                zzkVarArr = (zzk[]) SafeParcelReader.b(parcel, iA, zzk.CREATOR);
            } else if (iA2 == 2) {
                strN = SafeParcelReader.n(parcel, iA);
            } else if (iA2 == 3) {
                zR = SafeParcelReader.r(parcel, iA);
            } else if (iA2 != 4) {
                SafeParcelReader.y(parcel, iA);
            } else {
                account = (Account) SafeParcelReader.a(parcel, iA, Account.CREATOR);
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new zzg(zzkVarArr, strN, zR, account);
    }

    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ zzg[] newArray(int i) {
        return new zzg[i];
    }
}
