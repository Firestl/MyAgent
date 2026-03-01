package supwisdom;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.zat;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class hg0 implements Parcelable.Creator<zat> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zat createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        Account account = null;
        GoogleSignInAccount googleSignInAccount = null;
        int iV = 0;
        int iV2 = 0;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            int iA2 = SafeParcelReader.a(iA);
            if (iA2 == 1) {
                iV = SafeParcelReader.v(parcel, iA);
            } else if (iA2 == 2) {
                account = (Account) SafeParcelReader.a(parcel, iA, Account.CREATOR);
            } else if (iA2 == 3) {
                iV2 = SafeParcelReader.v(parcel, iA);
            } else if (iA2 != 4) {
                SafeParcelReader.y(parcel, iA);
            } else {
                googleSignInAccount = (GoogleSignInAccount) SafeParcelReader.a(parcel, iA, GoogleSignInAccount.CREATOR);
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new zat(iV, account, iV2, googleSignInAccount);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zat[] newArray(int i) {
        return new zat[i];
    }
}
