package supwisdom;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class ec0 implements Parcelable.Creator<GoogleSignInOptions> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ GoogleSignInOptions createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        ArrayList arrayListC = null;
        Account account = null;
        String strN = null;
        String strN2 = null;
        ArrayList arrayListC2 = null;
        String strN3 = null;
        int iV = 0;
        boolean zR = false;
        boolean zR2 = false;
        boolean zR3 = false;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            switch (SafeParcelReader.a(iA)) {
                case 1:
                    iV = SafeParcelReader.v(parcel, iA);
                    break;
                case 2:
                    arrayListC = SafeParcelReader.c(parcel, iA, Scope.CREATOR);
                    break;
                case 3:
                    account = (Account) SafeParcelReader.a(parcel, iA, Account.CREATOR);
                    break;
                case 4:
                    zR = SafeParcelReader.r(parcel, iA);
                    break;
                case 5:
                    zR2 = SafeParcelReader.r(parcel, iA);
                    break;
                case 6:
                    zR3 = SafeParcelReader.r(parcel, iA);
                    break;
                case 7:
                    strN = SafeParcelReader.n(parcel, iA);
                    break;
                case 8:
                    strN2 = SafeParcelReader.n(parcel, iA);
                    break;
                case 9:
                    arrayListC2 = SafeParcelReader.c(parcel, iA, GoogleSignInOptionsExtensionParcelable.CREATOR);
                    break;
                case 10:
                    strN3 = SafeParcelReader.n(parcel, iA);
                    break;
                default:
                    SafeParcelReader.y(parcel, iA);
                    break;
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new GoogleSignInOptions(iV, (ArrayList<Scope>) arrayListC, account, zR, zR2, zR3, strN, strN2, (ArrayList<GoogleSignInOptionsExtensionParcelable>) arrayListC2, strN3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ GoogleSignInOptions[] newArray(int i) {
        return new GoogleSignInOptions[i];
    }
}
