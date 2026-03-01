package supwisdom;

import android.accounts.Account;
import android.content.Context;
import android.os.Looper;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.IAccountAccessor;
import java.util.Set;
import supwisdom.mc0.d;
import supwisdom.pc0;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class mc0<O extends d> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a<?, O> f8374a;
    public final String b;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
    public static abstract class a<T extends f, O> extends e<T, O> {
        @RecentlyNonNull
        @Deprecated
        public T a(@RecentlyNonNull Context context, @RecentlyNonNull Looper looper, @RecentlyNonNull lf0 lf0Var, @RecentlyNonNull O o, @RecentlyNonNull pc0.a aVar, @RecentlyNonNull pc0.b bVar) {
            return (T) a(context, looper, lf0Var, (Object) o, (ad0) aVar, (gd0) bVar);
        }

        @RecentlyNonNull
        public T a(@RecentlyNonNull Context context, @RecentlyNonNull Looper looper, @RecentlyNonNull lf0 lf0Var, @RecentlyNonNull O o, @RecentlyNonNull ad0 ad0Var, @RecentlyNonNull gd0 gd0Var) {
            throw new UnsupportedOperationException("buildClient must be implemented");
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
    public interface b {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
    public static class c<C extends b> {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
    public interface d {

        @RecentlyNonNull
        public static final C0219d D1 = new C0219d();

        /* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
        public interface a extends c, e {
            @RecentlyNonNull
            Account b();
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
        public interface b extends c {
            @RecentlyNullable
            GoogleSignInAccount a();
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
        public interface c extends d {
        }

        /* JADX INFO: renamed from: supwisdom.mc0$d$d, reason: collision with other inner class name */
        /* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
        public static final class C0219d implements e {
            public C0219d() {
            }
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
        public interface e extends d {
        }

        /* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
        public interface f extends c, e {
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
    public static abstract class e<T extends b, O> {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
    public interface f extends b {
        Set<Scope> a();

        void a(@RecentlyNonNull BaseGmsClient.c cVar);

        void a(@RecentlyNonNull BaseGmsClient.e eVar);

        void a(IAccountAccessor iAccountAccessor, Set<Scope> set);

        void a(@RecentlyNonNull String str);

        @RecentlyNonNull
        String b();

        boolean c();

        int d();

        void disconnect();

        @RecentlyNonNull
        Feature[] e();

        @RecentlyNullable
        String f();

        boolean g();

        boolean isConnected();

        boolean isConnecting();
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
    public static final class g<C extends f> extends c<C> {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <C extends f> mc0(@RecentlyNonNull String str, @RecentlyNonNull a<C, O> aVar, @RecentlyNonNull g<C> gVar) {
        pf0.a(aVar, "Cannot construct an Api with a null ClientBuilder");
        pf0.a(gVar, "Cannot construct an Api with a null ClientKey");
        this.b = str;
        this.f8374a = aVar;
    }

    @RecentlyNonNull
    public final a<?, O> a() {
        return this.f8374a;
    }

    @RecentlyNonNull
    public final String b() {
        return this.b;
    }
}
