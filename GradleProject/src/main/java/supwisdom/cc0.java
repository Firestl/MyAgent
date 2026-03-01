package supwisdom;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class cc0 implements Parcelable.Creator<GoogleSignInAccount> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ GoogleSignInAccount createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        String strN = null;
        String strN2 = null;
        String strN3 = null;
        String strN4 = null;
        Uri uri = null;
        String strN5 = null;
        String strN6 = null;
        ArrayList arrayListC = null;
        String strN7 = null;
        String strN8 = null;
        long jW = 0;
        int iV = 0;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            switch (SafeParcelReader.a(iA)) {
                case 1:
                    iV = SafeParcelReader.v(parcel, iA);
                    break;
                case 2:
                    strN = SafeParcelReader.n(parcel, iA);
                    break;
                case 3:
                    strN2 = SafeParcelReader.n(parcel, iA);
                    break;
                case 4:
                    strN3 = SafeParcelReader.n(parcel, iA);
                    break;
                case 5:
                    strN4 = SafeParcelReader.n(parcel, iA);
                    break;
                case 6:
                    uri = (Uri) SafeParcelReader.a(parcel, iA, Uri.CREATOR);
                    break;
                case 7:
                    strN5 = SafeParcelReader.n(parcel, iA);
                    break;
                case 8:
                    jW = SafeParcelReader.w(parcel, iA);
                    break;
                case 9:
                    strN6 = SafeParcelReader.n(parcel, iA);
                    break;
                case 10:
                    arrayListC = SafeParcelReader.c(parcel, iA, Scope.CREATOR);
                    break;
                case 11:
                    strN7 = SafeParcelReader.n(parcel, iA);
                    break;
                case 12:
                    strN8 = SafeParcelReader.n(parcel, iA);
                    break;
                default:
                    SafeParcelReader.y(parcel, iA);
                    break;
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new GoogleSignInAccount(iV, strN, strN2, strN3, strN4, uri, strN5, jW, strN6, arrayListC, strN7, strN8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ GoogleSignInAccount[] newArray(int i) {
        return new GoogleSignInAccount[i];
    }
}
