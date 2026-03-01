package supwisdom;

import android.accounts.Account;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.internal.zace;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import supwisdom.bd0;
import supwisdom.lf0;
import supwisdom.mc0;
import supwisdom.mc0.d;
import supwisdom.pc0;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public class oc0<O extends mc0.d> implements qc0<O> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f8653a;
    public final String b;
    public final mc0<O> c;
    public final O d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final wc0<O> f8654e;
    public final int f;
    public final jd0 g;
    public final bd0 h;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
    public static class a {

        @RecentlyNonNull
        public static final a c = new C0223a().a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        @RecentlyNonNull
        public final jd0 f8655a;

        @RecentlyNonNull
        public final Looper b;

        /* JADX INFO: renamed from: supwisdom.oc0$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
        public static class C0223a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public jd0 f8656a;
            public Looper b;

            @RecentlyNonNull
            public a a() {
                if (this.f8656a == null) {
                    this.f8656a = new vc0();
                }
                if (this.b == null) {
                    this.b = Looper.getMainLooper();
                }
                return new a(this.f8656a, this.b);
            }
        }

        public a(jd0 jd0Var, Account account, Looper looper) {
            this.f8655a = jd0Var;
            this.b = looper;
        }
    }

    public oc0(@RecentlyNonNull Context context, @RecentlyNonNull mc0<O> mc0Var, @RecentlyNonNull O o, @RecentlyNonNull a aVar) {
        pf0.a(context, "Null context is not permitted.");
        pf0.a(mc0Var, "Api must not be null.");
        pf0.a(aVar, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        this.f8653a = context.getApplicationContext();
        String strA = a(context);
        this.b = strA;
        this.c = mc0Var;
        this.d = o;
        Looper looper = aVar.b;
        this.f8654e = wc0.a(mc0Var, o, strA);
        new ae0(this);
        bd0 bd0VarA = bd0.a(this.f8653a);
        this.h = bd0VarA;
        this.f = bd0VarA.a();
        this.g = aVar.f8655a;
        this.h.a((oc0<?>) this);
    }

    public final <TResult, A extends mc0.b> qk0<TResult> a(int i, kd0<A, TResult> kd0Var) {
        rk0 rk0Var = new rk0();
        this.h.a(this, i, kd0Var, rk0Var, this.g);
        return rk0Var.a();
    }

    @RecentlyNonNull
    public lf0.a b() {
        Account accountB;
        GoogleSignInAccount googleSignInAccountA;
        GoogleSignInAccount googleSignInAccountA2;
        lf0.a aVar = new lf0.a();
        O o = this.d;
        if (!(o instanceof mc0.d.b) || (googleSignInAccountA2 = ((mc0.d.b) o).a()) == null) {
            O o2 = this.d;
            accountB = o2 instanceof mc0.d.a ? ((mc0.d.a) o2).b() : null;
        } else {
            accountB = googleSignInAccountA2.b();
        }
        aVar.a(accountB);
        O o3 = this.d;
        aVar.a((!(o3 instanceof mc0.d.b) || (googleSignInAccountA = ((mc0.d.b) o3).a()) == null) ? Collections.emptySet() : googleSignInAccountA.j());
        aVar.b(this.f8653a.getClass().getName());
        aVar.a(this.f8653a.getPackageName());
        return aVar;
    }

    @RecentlyNullable
    public String c() {
        return this.b;
    }

    public final int d() {
        return this.f;
    }

    @RecentlyNonNull
    public <TResult, A extends mc0.b> qk0<TResult> a(@RecentlyNonNull kd0<A, TResult> kd0Var) {
        return a(2, kd0Var);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final mc0.f a(Looper looper, bd0.a<O> aVar) {
        lf0 lf0VarA = b().a();
        mc0.a<?, O> aVarA = this.c.a();
        pf0.a(aVarA);
        mc0.f fVarA = aVarA.a(this.f8653a, looper, lf0VarA, this.d, (pc0.a) aVar, (pc0.b) aVar);
        String strC = c();
        if (strC != null && (fVarA instanceof BaseGmsClient)) {
            ((BaseGmsClient) fVarA).b(strC);
        }
        if (strC != null && (fVarA instanceof fd0)) {
            ((fd0) fVarA).b(strC);
        }
        return fVarA;
    }

    @Override // supwisdom.qc0
    @RecentlyNonNull
    public wc0<O> a() {
        return this.f8654e;
    }

    public static String a(Object obj) {
        if (!th0.h()) {
            return null;
        }
        try {
            return (String) Context.class.getMethod("getAttributionTag", new Class[0]).invoke(obj, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return null;
        }
    }

    public final zace a(Context context, Handler handler) {
        return new zace(context, handler, b().a());
    }
}
