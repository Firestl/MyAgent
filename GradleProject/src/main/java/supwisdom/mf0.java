package supwisdom;

import android.accounts.Account;
import android.content.Context;
import android.os.IInterface;
import android.os.Looper;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import supwisdom.mc0;
import supwisdom.pc0;

/* JADX WARN: Unexpected interfaces in signature: [java.lang.Object] */
/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class mf0<T extends IInterface> extends BaseGmsClient<T> implements mc0.f {
    public final Set<Scope> x;
    public final Account y;

    /* JADX WARN: Illegal instructions before constructor call */
    public mf0(@RecentlyNonNull Context context, @RecentlyNonNull Looper looper, int i, @RecentlyNonNull lf0 lf0Var, @RecentlyNonNull ad0 ad0Var, @RecentlyNonNull gd0 gd0Var) {
        nf0 nf0VarA = nf0.a(context);
        gc0 gc0VarB = gc0.b();
        pf0.a(ad0Var);
        pf0.a(gd0Var);
        this(context, looper, nf0VarA, gc0VarB, i, lf0Var, ad0Var, gd0Var);
    }

    @Override // supwisdom.mc0.f
    public Set<Scope> a() {
        return g() ? this.x : Collections.emptySet();
    }

    public Set<Scope> a(@RecentlyNonNull Set<Scope> set) {
        return set;
    }

    public final Set<Scope> b(Set<Scope> set) {
        a(set);
        Iterator<Scope> it = set.iterator();
        while (it.hasNext()) {
            if (!set.contains(it.next())) {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
        }
        return set;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    @RecentlyNullable
    public final Account k() {
        return this.y;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    @RecentlyNonNull
    public final Set<Scope> q() {
        return this.x;
    }

    public static BaseGmsClient.a a(ad0 ad0Var) {
        if (ad0Var == null) {
            return null;
        }
        return new dg0(ad0Var);
    }

    public static BaseGmsClient.b a(gd0 gd0Var) {
        if (gd0Var == null) {
            return null;
        }
        return new eg0(gd0Var);
    }

    @Deprecated
    public mf0(@RecentlyNonNull Context context, @RecentlyNonNull Looper looper, int i, @RecentlyNonNull lf0 lf0Var, @RecentlyNonNull pc0.a aVar, @RecentlyNonNull pc0.b bVar) {
        this(context, looper, i, lf0Var, (ad0) aVar, (gd0) bVar);
    }

    public mf0(Context context, Looper looper, nf0 nf0Var, gc0 gc0Var, int i, lf0 lf0Var, ad0 ad0Var, gd0 gd0Var) {
        super(context, looper, nf0Var, gc0Var, i, a(ad0Var), a(gd0Var), lf0Var.f());
        this.y = lf0Var.a();
        Set<Scope> setC = lf0Var.c();
        b(setC);
        this.x = setC;
    }
}
