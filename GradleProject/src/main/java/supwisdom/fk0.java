package supwisdom;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.zat;
import com.google.android.gms.signin.internal.zae;
import com.google.android.gms.signin.internal.zaf;
import com.google.android.gms.signin.internal.zag;
import com.google.android.gms.signin.internal.zaj;
import com.google.android.gms.signin.internal.zak;
import com.huawei.hms.api.HuaweiApiClientImpl;
import supwisdom.pc0;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public class fk0 extends mf0<zag> implements ok0 {
    public final lf0 A;
    public final Bundle B;
    public final Integer C;
    public final boolean z;

    public fk0(@RecentlyNonNull Context context, @RecentlyNonNull Looper looper, boolean z, @RecentlyNonNull lf0 lf0Var, @RecentlyNonNull Bundle bundle, @RecentlyNonNull pc0.a aVar, @RecentlyNonNull pc0.b bVar) {
        super(context, looper, 44, lf0Var, aVar, bVar);
        this.z = z;
        this.A = lf0Var;
        this.B = bundle;
        this.C = lf0Var.h();
    }

    @Override // supwisdom.ok0
    public final void a(zae zaeVar) {
        pf0.a(zaeVar, "Expecting a valid ISignInCallbacks");
        try {
            Account accountB = this.A.b();
            GoogleSignInAccount googleSignInAccountA = HuaweiApiClientImpl.DEFAULT_ACCOUNT.equals(accountB.name) ? ac0.a(n()).a() : null;
            Integer num = this.C;
            pf0.a(num);
            ((zag) r()).zaa(new zaj(new zat(accountB, num.intValue(), googleSignInAccountA)), zaeVar);
        } catch (RemoteException e2) {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try {
                zaeVar.zaa(new zak(8));
            } catch (RemoteException unused) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", e2);
            }
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, supwisdom.mc0.f
    public int d() {
        return 12451000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, supwisdom.mc0.f
    public boolean g() {
        return this.z;
    }

    @Override // supwisdom.ok0
    public final void h() {
        a(new BaseGmsClient.d());
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    @RecentlyNonNull
    public Bundle o() {
        if (!n().getPackageName().equals(this.A.d())) {
            this.B.putString("com.google.android.gms.signin.internal.realClientPackageName", this.A.d());
        }
        return this.B;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    @RecentlyNonNull
    public String s() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    @RecentlyNonNull
    public String t() {
        return "com.google.android.gms.signin.service.START";
    }

    public fk0(@RecentlyNonNull Context context, @RecentlyNonNull Looper looper, boolean z, @RecentlyNonNull lf0 lf0Var, @RecentlyNonNull ek0 ek0Var, @RecentlyNonNull pc0.a aVar, @RecentlyNonNull pc0.b bVar) {
        this(context, looper, true, lf0Var, a(lf0Var), aVar, bVar);
    }

    @RecentlyNonNull
    public static Bundle a(@RecentlyNonNull lf0 lf0Var) {
        ek0 ek0VarG = lf0Var.g();
        Integer numH = lf0Var.h();
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", lf0Var.a());
        if (numH != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", numH.intValue());
        }
        if (ek0VarG != null) {
            bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", false);
            bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", false);
            bundle.putString("com.google.android.gms.signin.internal.serverClientId", null);
            bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
            bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", false);
            bundle.putString("com.google.android.gms.signin.internal.hostedDomain", null);
            bundle.putString("com.google.android.gms.signin.internal.logSessionId", null);
            bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", false);
        }
        return bundle;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    @RecentlyNonNull
    public /* synthetic */ IInterface a(@RecentlyNonNull IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
        if (iInterfaceQueryLocalInterface instanceof zag) {
            return (zag) iInterfaceQueryLocalInterface;
        }
        return new zaf(iBinder);
    }
}
