package supwisdom;

import android.os.RemoteException;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RecentlyNullable;
import com.google.android.gms.common.Feature;
import supwisdom.mc0;
import supwisdom.mc0.b;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class kd0<A extends mc0.b, ResultT> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Feature[] f8163a;
    public final boolean b;
    public final int c;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
    public static class a<A extends mc0.b, ResultT> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public id0<A, rk0<ResultT>> f8164a;
        public boolean b;
        public Feature[] c;
        public int d;

        public a() {
            this.b = true;
            this.d = 0;
        }

        @RecentlyNonNull
        public a<A, ResultT> a(@RecentlyNonNull id0<A, rk0<ResultT>> id0Var) {
            this.f8164a = id0Var;
            return this;
        }

        @RecentlyNonNull
        public a<A, ResultT> a(@RecentlyNonNull Feature... featureArr) {
            this.c = featureArr;
            return this;
        }

        @RecentlyNonNull
        public a<A, ResultT> a(boolean z) {
            this.b = z;
            return this;
        }

        @RecentlyNonNull
        public kd0<A, ResultT> a() {
            pf0.a(this.f8164a != null, "execute parameter required");
            return new ne0(this, this.c, this.b, this.d);
        }
    }

    public kd0(@RecentlyNonNull Feature[] featureArr, boolean z, int i) {
        this.f8163a = featureArr;
        this.b = featureArr != null && z;
        this.c = i;
    }

    @RecentlyNonNull
    public static <A extends mc0.b, ResultT> a<A, ResultT> d() {
        return new a<>();
    }

    public abstract void a(@RecentlyNonNull A a2, @RecentlyNonNull rk0<ResultT> rk0Var) throws RemoteException;

    public boolean a() {
        return this.b;
    }

    @RecentlyNullable
    public final Feature[] b() {
        return this.f8163a;
    }

    public final int c() {
        return this.c;
    }
}
