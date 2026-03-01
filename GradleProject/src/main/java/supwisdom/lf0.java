package supwisdom;

import android.accounts.Account;
import android.view.View;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.api.Scope;
import com.huawei.hms.api.HuaweiApiClientImpl;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public final class lf0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Nullable
    public final Account f8281a;
    public final Set<Scope> b;
    public final Set<Scope> c;
    public final Map<mc0<?>, b> d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final View f8282e;
    public final String f;
    public final String g;
    public final ek0 h;
    public Integer i;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        @Nullable
        public Account f8283a;
        public k4<Scope> b;
        public String c;
        public String d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ek0 f8284e = ek0.f7509a;

        @RecentlyNonNull
        public final a a(@Nullable Account account) {
            this.f8283a = account;
            return this;
        }

        @RecentlyNonNull
        public final a b(@RecentlyNonNull String str) {
            this.d = str;
            return this;
        }

        @RecentlyNonNull
        public final a a(@RecentlyNonNull Collection<Scope> collection) {
            if (this.b == null) {
                this.b = new k4<>();
            }
            this.b.addAll(collection);
            return this;
        }

        @RecentlyNonNull
        public final a a(@RecentlyNonNull String str) {
            this.c = str;
            return this;
        }

        @RecentlyNonNull
        public final lf0 a() {
            return new lf0(this.f8283a, this.b, null, 0, null, this.c, this.d, this.f8284e, false);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Set<Scope> f8285a;
    }

    public lf0(@Nullable Account account, @RecentlyNonNull Set<Scope> set, @RecentlyNonNull Map<mc0<?>, b> map, int i, @RecentlyNonNull View view, @RecentlyNonNull String str, @RecentlyNonNull String str2, @RecentlyNonNull ek0 ek0Var, boolean z) {
        this.f8281a = account;
        this.b = set == null ? Collections.emptySet() : Collections.unmodifiableSet(set);
        this.d = map == null ? Collections.emptyMap() : map;
        this.f8282e = view;
        this.f = str;
        this.g = str2;
        this.h = ek0Var;
        HashSet hashSet = new HashSet(this.b);
        Iterator<b> it = this.d.values().iterator();
        while (it.hasNext()) {
            hashSet.addAll(it.next().f8285a);
        }
        this.c = Collections.unmodifiableSet(hashSet);
    }

    @RecentlyNullable
    public final Account a() {
        return this.f8281a;
    }

    @RecentlyNonNull
    public final Account b() {
        Account account = this.f8281a;
        return account != null ? account : new Account(HuaweiApiClientImpl.DEFAULT_ACCOUNT, "com.google");
    }

    @RecentlyNonNull
    public final Set<Scope> c() {
        return this.c;
    }

    @RecentlyNullable
    public final String d() {
        return this.f;
    }

    @RecentlyNonNull
    public final Set<Scope> e() {
        return this.b;
    }

    @RecentlyNullable
    public final String f() {
        return this.g;
    }

    @RecentlyNonNull
    public final ek0 g() {
        return this.h;
    }

    @RecentlyNullable
    public final Integer h() {
        return this.i;
    }

    public final void a(@RecentlyNonNull Integer num) {
        this.i = num;
    }
}
