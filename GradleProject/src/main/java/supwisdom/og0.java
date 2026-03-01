package supwisdom;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.GetServiceRequest;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class og0 implements Parcelable.Creator<GetServiceRequest> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ GetServiceRequest createFromParcel(Parcel parcel) {
        int iB = SafeParcelReader.b(parcel);
        String strN = null;
        IBinder iBinderU = null;
        Scope[] scopeArr = null;
        Bundle bundleF = null;
        Account account = null;
        Feature[] featureArr = null;
        Feature[] featureArr2 = null;
        String strN2 = null;
        int iV = 0;
        int iV2 = 0;
        int iV3 = 0;
        boolean zR = false;
        int iV4 = 0;
        boolean zR2 = false;
        while (parcel.dataPosition() < iB) {
            int iA = SafeParcelReader.a(parcel);
            switch (SafeParcelReader.a(iA)) {
                case 1:
                    iV = SafeParcelReader.v(parcel, iA);
                    break;
                case 2:
                    iV2 = SafeParcelReader.v(parcel, iA);
                    break;
                case 3:
                    iV3 = SafeParcelReader.v(parcel, iA);
                    break;
                case 4:
                    strN = SafeParcelReader.n(parcel, iA);
                    break;
                case 5:
                    iBinderU = SafeParcelReader.u(parcel, iA);
                    break;
                case 6:
                    scopeArr = (Scope[]) SafeParcelReader.b(parcel, iA, Scope.CREATOR);
                    break;
                case 7:
                    bundleF = SafeParcelReader.f(parcel, iA);
                    break;
                case 8:
                    account = (Account) SafeParcelReader.a(parcel, iA, Account.CREATOR);
                    break;
                case 9:
                default:
                    SafeParcelReader.y(parcel, iA);
                    break;
                case 10:
                    featureArr = (Feature[]) SafeParcelReader.b(parcel, iA, Feature.CREATOR);
                    break;
                case 11:
                    featureArr2 = (Feature[]) SafeParcelReader.b(parcel, iA, Feature.CREATOR);
                    break;
                case 12:
                    zR = SafeParcelReader.r(parcel, iA);
                    break;
                case 13:
                    iV4 = SafeParcelReader.v(parcel, iA);
                    break;
                case 14:
                    zR2 = SafeParcelReader.r(parcel, iA);
                    break;
                case 15:
                    strN2 = SafeParcelReader.n(parcel, iA);
                    break;
            }
        }
        SafeParcelReader.q(parcel, iB);
        return new GetServiceRequest(iV, iV2, iV3, strN, iBinderU, scopeArr, bundleF, account, featureArr, featureArr2, zR, iV4, zR2, strN2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ GetServiceRequest[] newArray(int i) {
        return new GetServiceRequest[i];
    }
}
