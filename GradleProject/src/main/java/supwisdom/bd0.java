package supwisdom;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.UnsupportedApiCallException;
import com.google.android.gms.common.api.internal.zace;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.RootTelemetryConfiguration;
import com.google.android.gms.common.internal.zaaa;
import com.google.android.gms.common.internal.zao;
import io.dcloud.common.DHInterface.IApp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.GuardedBy;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;
import supwisdom.mc0;
import supwisdom.of0;
import supwisdom.pc0;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
/* JADX INFO: loaded from: classes.dex */
public class bd0 implements Handler.Callback {

    @RecentlyNonNull
    public static final Status r = new Status(4, "Sign-out occurred while this API call was in progress.");
    public static final Status s = new Status(4, "The user must be signed in to make this API call.");
    public static final Object t = new Object();

    @GuardedBy("lock")
    public static bd0 u;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public zaaa f7054e;
    public yf0 f;
    public final Context g;
    public final gc0 h;
    public final fg0 i;

    @NotOnlyInitialized
    public final Handler p;
    public volatile boolean q;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f7053a = 5000;
    public long b = com.igexin.push.config.c.l;
    public long c = 10000;
    public boolean d = false;
    public final AtomicInteger j = new AtomicInteger(1);
    public final AtomicInteger k = new AtomicInteger(0);
    public final Map<wc0<?>, a<?>> l = new ConcurrentHashMap(5, 0.75f, 1);

    @GuardedBy("lock")
    public ye0 m = null;

    @GuardedBy("lock")
    public final Set<wc0<?>> n = new k4();
    public final Set<wc0<?>> o = new k4();

    /* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
    public class a<O extends mc0.d> implements pc0.a, pc0.b, ve0 {

        @NotOnlyInitialized
        public final mc0.f b;
        public final wc0<O> c;
        public final int g;
        public final zace h;
        public boolean i;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Queue<sd0> f7055a = new LinkedList();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final Set<se0> f7056e = new HashSet();
        public final Map<ed0<?>, he0> f = new HashMap();
        public final List<b> j = new ArrayList();
        public ConnectionResult k = null;
        public int l = 0;
        public final we0 d = new we0();

        public a(oc0<O> oc0Var) {
            this.b = oc0Var.a(bd0.this.p.getLooper(), this);
            this.c = oc0Var.a();
            this.g = oc0Var.d();
            if (this.b.g()) {
                this.h = oc0Var.a(bd0.this.g, bd0.this.p);
            } else {
                this.h = null;
            }
        }

        public final void a(int i) {
            d();
            this.i = true;
            this.d.a(i, this.b.f());
            bd0.this.p.sendMessageDelayed(Message.obtain(bd0.this.p, 9, this.c), bd0.this.f7053a);
            bd0.this.p.sendMessageDelayed(Message.obtain(bd0.this.p, 11, this.c), bd0.this.b);
            bd0.this.i.a();
            Iterator<he0> it = this.f.values().iterator();
            while (it.hasNext()) {
                it.next().c.run();
            }
        }

        public final boolean b(ConnectionResult connectionResult) {
            synchronized (bd0.t) {
                if (bd0.this.m != null && bd0.this.n.contains(this.c)) {
                    bd0.this.m.a(connectionResult, this.g);
                    throw null;
                }
            }
            return false;
        }

        public final Map<ed0<?>, he0> c() {
            return this.f;
        }

        public final void d() {
            pf0.a(bd0.this.p);
            this.k = null;
        }

        public final ConnectionResult e() {
            pf0.a(bd0.this.p);
            return this.k;
        }

        public final void f() {
            pf0.a(bd0.this.p);
            if (this.i) {
                i();
            }
        }

        public final void g() {
            pf0.a(bd0.this.p);
            if (this.i) {
                q();
                a(bd0.this.h.a(bd0.this.g) == 18 ? new Status(21, "Connection timed out waiting for Google Play services update to complete.") : new Status(22, "API failed to connect while resuming due to an unknown error."));
                this.b.a("Timing out connection while resuming.");
            }
        }

        public final boolean h() {
            return a(true);
        }

        public final void i() {
            pf0.a(bd0.this.p);
            if (this.b.isConnected() || this.b.isConnecting()) {
                return;
            }
            try {
                int iA = bd0.this.i.a(bd0.this.g, this.b);
                if (iA == 0) {
                    c cVar = bd0.this.new c(this.b, this.c);
                    if (this.b.g()) {
                        zace zaceVar = this.h;
                        pf0.a(zaceVar);
                        zaceVar.zaa(cVar);
                    }
                    try {
                        this.b.a(cVar);
                        return;
                    } catch (SecurityException e2) {
                        a(new ConnectionResult(10), e2);
                        return;
                    }
                }
                ConnectionResult connectionResult = new ConnectionResult(iA, null);
                String name = this.b.getClass().getName();
                String strValueOf = String.valueOf(connectionResult);
                StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 35 + String.valueOf(strValueOf).length());
                sb.append("The service for ");
                sb.append(name);
                sb.append(" is not available: ");
                sb.append(strValueOf);
                Log.w("GoogleApiManager", sb.toString());
                onConnectionFailed(connectionResult);
            } catch (IllegalStateException e3) {
                a(new ConnectionResult(10), e3);
            }
        }

        public final boolean j() {
            return this.b.isConnected();
        }

        public final boolean k() {
            return this.b.g();
        }

        public final int l() {
            return this.g;
        }

        public final int m() {
            return this.l;
        }

        public final void n() {
            this.l++;
        }

        public final void o() {
            d();
            c(ConnectionResult.f2320e);
            q();
            Iterator<he0> it = this.f.values().iterator();
            while (it.hasNext()) {
                he0 next = it.next();
                if (a(next.f7832a.b()) != null) {
                    it.remove();
                } else {
                    try {
                        next.f7832a.a(this.b, new rk0<>());
                    } catch (DeadObjectException unused) {
                        onConnectionSuspended(3);
                        this.b.a("DeadObjectException thrown while calling register listener method.");
                    } catch (RemoteException unused2) {
                        it.remove();
                    }
                }
            }
            p();
            r();
        }

        @Override // supwisdom.ad0
        public final void onConnected(Bundle bundle) {
            if (Looper.myLooper() == bd0.this.p.getLooper()) {
                o();
            } else {
                bd0.this.p.post(new wd0(this));
            }
        }

        @Override // supwisdom.gd0
        public final void onConnectionFailed(ConnectionResult connectionResult) {
            a(connectionResult, (Exception) null);
        }

        @Override // supwisdom.ad0
        public final void onConnectionSuspended(int i) {
            if (Looper.myLooper() == bd0.this.p.getLooper()) {
                a(i);
            } else {
                bd0.this.p.post(new vd0(this, i));
            }
        }

        public final void p() {
            ArrayList arrayList = new ArrayList(this.f7055a);
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                sd0 sd0Var = (sd0) obj;
                if (!this.b.isConnected()) {
                    return;
                }
                if (b(sd0Var)) {
                    this.f7055a.remove(sd0Var);
                }
            }
        }

        public final void q() {
            if (this.i) {
                bd0.this.p.removeMessages(11, this.c);
                bd0.this.p.removeMessages(9, this.c);
                this.i = false;
            }
        }

        public final void r() {
            bd0.this.p.removeMessages(12, this.c);
            bd0.this.p.sendMessageDelayed(bd0.this.p.obtainMessage(12, this.c), bd0.this.c);
        }

        public final void c(sd0 sd0Var) {
            sd0Var.a(this.d, k());
            try {
                sd0Var.a((a<?>) this);
            } catch (DeadObjectException unused) {
                onConnectionSuspended(1);
                this.b.a("DeadObjectException thrown while running ApiCallRunner.");
            } catch (Throwable th) {
                throw new IllegalStateException(String.format("Error in GoogleApi implementation for client %s.", this.b.getClass().getName()), th);
            }
        }

        public final Status d(ConnectionResult connectionResult) {
            return bd0.b((wc0<?>) this.c, connectionResult);
        }

        public final mc0.f b() {
            return this.b;
        }

        public final boolean b(sd0 sd0Var) {
            if (!(sd0Var instanceof pe0)) {
                c(sd0Var);
                return true;
            }
            pe0 pe0Var = (pe0) sd0Var;
            Feature featureA = a(pe0Var.b((a<?>) this));
            if (featureA == null) {
                c(sd0Var);
                return true;
            }
            String name = this.b.getClass().getName();
            String strC = featureA.c();
            long jD = featureA.d();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 77 + String.valueOf(strC).length());
            sb.append(name);
            sb.append(" could not execute call because it requires feature (");
            sb.append(strC);
            sb.append(", ");
            sb.append(jD);
            sb.append(").");
            Log.w("GoogleApiManager", sb.toString());
            if (bd0.this.q && pe0Var.c(this)) {
                b bVar = new b(this.c, featureA, null);
                int iIndexOf = this.j.indexOf(bVar);
                if (iIndexOf >= 0) {
                    b bVar2 = this.j.get(iIndexOf);
                    bd0.this.p.removeMessages(15, bVar2);
                    bd0.this.p.sendMessageDelayed(Message.obtain(bd0.this.p, 15, bVar2), bd0.this.f7053a);
                    return false;
                }
                this.j.add(bVar);
                bd0.this.p.sendMessageDelayed(Message.obtain(bd0.this.p, 15, bVar), bd0.this.f7053a);
                bd0.this.p.sendMessageDelayed(Message.obtain(bd0.this.p, 16, bVar), bd0.this.b);
                ConnectionResult connectionResult = new ConnectionResult(2, null);
                if (b(connectionResult)) {
                    return false;
                }
                bd0.this.a(connectionResult, this.g);
                return false;
            }
            pe0Var.a(new UnsupportedApiCallException(featureA));
            return true;
        }

        public final void c(ConnectionResult connectionResult) {
            for (se0 se0Var : this.f7056e) {
                String strB = null;
                if (of0.a(connectionResult, ConnectionResult.f2320e)) {
                    strB = this.b.b();
                }
                se0Var.a(this.c, connectionResult, strB);
            }
            this.f7056e.clear();
        }

        public final void a(ConnectionResult connectionResult) {
            pf0.a(bd0.this.p);
            mc0.f fVar = this.b;
            String name = fVar.getClass().getName();
            String strValueOf = String.valueOf(connectionResult);
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 25 + String.valueOf(strValueOf).length());
            sb.append("onSignInFailed for ");
            sb.append(name);
            sb.append(" with ");
            sb.append(strValueOf);
            fVar.a(sb.toString());
            onConnectionFailed(connectionResult);
        }

        public final void a(ConnectionResult connectionResult, Exception exc) {
            pf0.a(bd0.this.p);
            zace zaceVar = this.h;
            if (zaceVar != null) {
                zaceVar.zaa();
            }
            d();
            bd0.this.i.a();
            c(connectionResult);
            if (this.b instanceof uf0) {
                bd0.a(bd0.this, true);
                bd0.this.p.sendMessageDelayed(bd0.this.p.obtainMessage(19), 300000L);
            }
            if (connectionResult.c() == 4) {
                a(bd0.s);
                return;
            }
            if (this.f7055a.isEmpty()) {
                this.k = connectionResult;
                return;
            }
            if (exc == null) {
                if (!bd0.this.q) {
                    a(d(connectionResult));
                    return;
                }
                a(d(connectionResult), null, true);
                if (this.f7055a.isEmpty() || b(connectionResult) || bd0.this.a(connectionResult, this.g)) {
                    return;
                }
                if (connectionResult.c() == 18) {
                    this.i = true;
                }
                if (this.i) {
                    bd0.this.p.sendMessageDelayed(Message.obtain(bd0.this.p, 9, this.c), bd0.this.f7053a);
                    return;
                } else {
                    a(d(connectionResult));
                    return;
                }
            }
            pf0.a(bd0.this.p);
            a(null, exc, false);
        }

        public final void b(b bVar) {
            Feature[] featureArrB;
            if (this.j.remove(bVar)) {
                bd0.this.p.removeMessages(15, bVar);
                bd0.this.p.removeMessages(16, bVar);
                Feature feature = bVar.b;
                ArrayList arrayList = new ArrayList(this.f7055a.size());
                for (sd0 sd0Var : this.f7055a) {
                    if ((sd0Var instanceof pe0) && (featureArrB = ((pe0) sd0Var).b((a<?>) this)) != null && lh0.a(featureArrB, feature)) {
                        arrayList.add(sd0Var);
                    }
                }
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList.get(i);
                    i++;
                    sd0 sd0Var2 = (sd0) obj;
                    this.f7055a.remove(sd0Var2);
                    sd0Var2.a(new UnsupportedApiCallException(feature));
                }
            }
        }

        public final void a(sd0 sd0Var) {
            pf0.a(bd0.this.p);
            if (this.b.isConnected()) {
                if (b(sd0Var)) {
                    r();
                    return;
                } else {
                    this.f7055a.add(sd0Var);
                    return;
                }
            }
            this.f7055a.add(sd0Var);
            ConnectionResult connectionResult = this.k;
            if (connectionResult != null && connectionResult.f()) {
                onConnectionFailed(this.k);
            } else {
                i();
            }
        }

        public final void a() {
            pf0.a(bd0.this.p);
            a(bd0.r);
            this.d.b();
            for (ed0 ed0Var : (ed0[]) this.f.keySet().toArray(new ed0[0])) {
                a(new qe0(ed0Var, new rk0()));
            }
            c(new ConnectionResult(4));
            if (this.b.isConnected()) {
                this.b.a(new xd0(this));
            }
        }

        public final void a(Status status, Exception exc, boolean z) {
            pf0.a(bd0.this.p);
            if ((status == null) != (exc == null)) {
                Iterator<sd0> it = this.f7055a.iterator();
                while (it.hasNext()) {
                    sd0 next = it.next();
                    if (!z || next.f9154a == 2) {
                        if (status != null) {
                            next.a(status);
                        } else {
                            next.a(exc);
                        }
                        it.remove();
                    }
                }
                return;
            }
            throw new IllegalArgumentException("Status XOR exception should be null");
        }

        public final void a(Status status) {
            pf0.a(bd0.this.p);
            a(status, null, false);
        }

        public final boolean a(boolean z) {
            pf0.a(bd0.this.p);
            if (!this.b.isConnected() || this.f.size() != 0) {
                return false;
            }
            if (!this.d.a()) {
                this.b.a("Timing out service connection.");
                return true;
            }
            if (z) {
                r();
            }
            return false;
        }

        public final void a(se0 se0Var) {
            pf0.a(bd0.this.p);
            this.f7056e.add(se0Var);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final Feature a(Feature[] featureArr) {
            if (featureArr != null && featureArr.length != 0) {
                Feature[] featureArrE = this.b.e();
                if (featureArrE == null) {
                    featureArrE = new Feature[0];
                }
                j4 j4Var = new j4(featureArrE.length);
                for (Feature feature : featureArrE) {
                    j4Var.put(feature.c(), Long.valueOf(feature.d()));
                }
                for (Feature feature2 : featureArr) {
                    Long l = (Long) j4Var.get(feature2.c());
                    if (l == null || l.longValue() < feature2.d()) {
                        return feature2;
                    }
                }
            }
            return null;
        }

        public final void a(b bVar) {
            if (this.j.contains(bVar) && !this.i) {
                if (!this.b.isConnected()) {
                    i();
                } else {
                    p();
                }
            }
        }

        public static /* synthetic */ boolean a(a aVar, boolean z) {
            return aVar.a(false);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
    public class c implements le0, BaseGmsClient.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final mc0.f f7058a;
        public final wc0<?> b;
        public IAccountAccessor c = null;
        public Set<Scope> d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f7059e = false;

        public c(mc0.f fVar, wc0<?> wc0Var) {
            this.f7058a = fVar;
            this.b = wc0Var;
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.c
        public final void a(ConnectionResult connectionResult) {
            bd0.this.p.post(new zd0(this, connectionResult));
        }

        @Override // supwisdom.le0
        public final void b(ConnectionResult connectionResult) {
            a aVar = (a) bd0.this.l.get(this.b);
            if (aVar != null) {
                aVar.a(connectionResult);
            }
        }

        @Override // supwisdom.le0
        public final void a(IAccountAccessor iAccountAccessor, Set<Scope> set) {
            if (iAccountAccessor == null || set == null) {
                Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
                b(new ConnectionResult(4));
            } else {
                this.c = iAccountAccessor;
                this.d = set;
                a();
            }
        }

        public final void a() {
            IAccountAccessor iAccountAccessor;
            if (!this.f7059e || (iAccountAccessor = this.c) == null) {
                return;
            }
            this.f7058a.a(iAccountAccessor, this.d);
        }

        public static /* synthetic */ boolean a(c cVar, boolean z) {
            cVar.f7059e = true;
            return true;
        }
    }

    public bd0(Context context, Looper looper, gc0 gc0Var) {
        this.q = true;
        this.g = context;
        this.p = new ji0(looper, this);
        this.h = gc0Var;
        this.i = new fg0(gc0Var);
        if (qh0.a(context)) {
            this.q = false;
        }
        Handler handler = this.p;
        handler.sendMessage(handler.obtainMessage(6));
    }

    @RecentlyNonNull
    public static bd0 a(@RecentlyNonNull Context context) {
        bd0 bd0Var;
        synchronized (t) {
            if (u == null) {
                HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
                handlerThread.start();
                u = new bd0(context.getApplicationContext(), handlerThread.getLooper(), gc0.b());
            }
            bd0Var = u;
        }
        return bd0Var;
    }

    public final a<?> b(oc0<?> oc0Var) {
        Object objA = oc0Var.a();
        a<?> aVar = this.l.get(objA);
        if (aVar == null) {
            aVar = new a<>(oc0Var);
            this.l.put((wc0<?>) objA, aVar);
        }
        if (aVar.k()) {
            this.o.add((wc0<?>) objA);
        }
        aVar.i();
        return aVar;
    }

    public final boolean c() {
        if (this.d) {
            return false;
        }
        RootTelemetryConfiguration rootTelemetryConfigurationA = qf0.b().a();
        if (rootTelemetryConfigurationA != null && !rootTelemetryConfigurationA.e()) {
            return false;
        }
        int iA = this.i.a(this.g, 203390000);
        return iA == -1 || iA == 0;
    }

    public final void d() {
        zaaa zaaaVar = this.f7054e;
        if (zaaaVar != null) {
            if (zaaaVar.c() > 0 || c()) {
                e().zaa(zaaaVar);
            }
            this.f7054e = null;
        }
    }

    public final yf0 e() {
        if (this.f == null) {
            this.f = new tf0(this.g);
        }
        return this.f;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(@RecentlyNonNull Message message) {
        int i = message.what;
        a<?> aVar = null;
        switch (i) {
            case 1:
                this.c = ((Boolean) message.obj).booleanValue() ? 10000L : 300000L;
                this.p.removeMessages(12);
                for (wc0<?> wc0Var : this.l.keySet()) {
                    Handler handler = this.p;
                    handler.sendMessageDelayed(handler.obtainMessage(12, wc0Var), this.c);
                }
                return true;
            case 2:
                se0 se0Var = (se0) message.obj;
                Iterator<wc0<?>> it = se0Var.a().iterator();
                while (true) {
                    if (it.hasNext()) {
                        wc0<?> next = it.next();
                        a<?> aVar2 = this.l.get(next);
                        if (aVar2 == null) {
                            se0Var.a(next, new ConnectionResult(13), null);
                        } else if (aVar2.j()) {
                            se0Var.a(next, ConnectionResult.f2320e, aVar2.b().b());
                        } else {
                            ConnectionResult connectionResultE = aVar2.e();
                            if (connectionResultE != null) {
                                se0Var.a(next, connectionResultE, null);
                            } else {
                                aVar2.a(se0Var);
                                aVar2.i();
                            }
                        }
                    }
                }
                return true;
            case 3:
                for (a<?> aVar3 : this.l.values()) {
                    aVar3.d();
                    aVar3.i();
                }
                return true;
            case 4:
            case 8:
            case 13:
                ge0 ge0Var = (ge0) message.obj;
                a<?> aVarB = this.l.get(ge0Var.c.a());
                if (aVarB == null) {
                    aVarB = b(ge0Var.c);
                }
                if (!aVarB.k() || this.k.get() == ge0Var.b) {
                    aVarB.a(ge0Var.f7716a);
                } else {
                    ge0Var.f7716a.a(r);
                    aVarB.a();
                }
                return true;
            case 5:
                int i2 = message.arg1;
                ConnectionResult connectionResult = (ConnectionResult) message.obj;
                Iterator<a<?>> it2 = this.l.values().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        a<?> next2 = it2.next();
                        if (next2.l() == i2) {
                            aVar = next2;
                        }
                    }
                }
                if (aVar == null) {
                    StringBuilder sb = new StringBuilder(76);
                    sb.append("Could not find API instance ");
                    sb.append(i2);
                    sb.append(" while trying to fail enqueued calls.");
                    Log.wtf("GoogleApiManager", sb.toString(), new Exception());
                } else if (connectionResult.c() == 13) {
                    String strA = this.h.a(connectionResult.c());
                    String strD = connectionResult.d();
                    StringBuilder sb2 = new StringBuilder(String.valueOf(strA).length() + 69 + String.valueOf(strD).length());
                    sb2.append("Error resolution was canceled by the user, original error message: ");
                    sb2.append(strA);
                    sb2.append(": ");
                    sb2.append(strD);
                    aVar.a(new Status(17, sb2.toString()));
                } else {
                    aVar.a(b((wc0<?>) aVar.c, connectionResult));
                }
                return true;
            case 6:
                if (this.g.getApplicationContext() instanceof Application) {
                    xc0.a((Application) this.g.getApplicationContext());
                    xc0.b().a(new ud0(this));
                    if (!xc0.b().a(true)) {
                        this.c = 300000L;
                    }
                }
                return true;
            case 7:
                b((oc0<?>) message.obj);
                return true;
            case 9:
                if (this.l.containsKey(message.obj)) {
                    this.l.get(message.obj).f();
                }
                return true;
            case 10:
                Iterator<wc0<?>> it3 = this.o.iterator();
                while (it3.hasNext()) {
                    a<?> aVarRemove = this.l.remove(it3.next());
                    if (aVarRemove != null) {
                        aVarRemove.a();
                    }
                }
                this.o.clear();
                return true;
            case 11:
                if (this.l.containsKey(message.obj)) {
                    this.l.get(message.obj).g();
                }
                return true;
            case 12:
                if (this.l.containsKey(message.obj)) {
                    this.l.get(message.obj).h();
                }
                return true;
            case 14:
                ze0 ze0Var = (ze0) message.obj;
                wc0<?> wc0VarA = ze0Var.a();
                if (this.l.containsKey(wc0VarA)) {
                    ze0Var.b().a(Boolean.valueOf(a.a((a) this.l.get(wc0VarA), false)));
                } else {
                    ze0Var.b().a(false);
                }
                return true;
            case 15:
                b bVar = (b) message.obj;
                if (this.l.containsKey(bVar.f7057a)) {
                    this.l.get(bVar.f7057a).a(bVar);
                }
                return true;
            case 16:
                b bVar2 = (b) message.obj;
                if (this.l.containsKey(bVar2.f7057a)) {
                    this.l.get(bVar2.f7057a).b(bVar2);
                }
                return true;
            case 17:
                d();
                return true;
            case 18:
                ce0 ce0Var = (ce0) message.obj;
                if (ce0Var.c == 0) {
                    e().zaa(new zaaa(ce0Var.b, Arrays.asList(ce0Var.f7179a)));
                } else {
                    zaaa zaaaVar = this.f7054e;
                    if (zaaaVar != null) {
                        List<zao> listD = zaaaVar.d();
                        if (this.f7054e.c() != ce0Var.b || (listD != null && listD.size() >= ce0Var.d)) {
                            this.p.removeMessages(17);
                            d();
                        } else {
                            this.f7054e.a(ce0Var.f7179a);
                        }
                    }
                    if (this.f7054e == null) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(ce0Var.f7179a);
                        this.f7054e = new zaaa(ce0Var.b, arrayList);
                        Handler handler2 = this.p;
                        handler2.sendMessageDelayed(handler2.obtainMessage(17), ce0Var.c);
                    }
                }
                return true;
            case 19:
                this.d = false;
                return true;
            default:
                StringBuilder sb3 = new StringBuilder(31);
                sb3.append("Unknown message id: ");
                sb3.append(i);
                Log.w("GoogleApiManager", sb3.toString());
                return false;
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.5.0 */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final wc0<?> f7057a;
        public final Feature b;

        public b(wc0<?> wc0Var, Feature feature) {
            this.f7057a = wc0Var;
            this.b = feature;
        }

        public final boolean equals(Object obj) {
            if (obj != null && (obj instanceof b)) {
                b bVar = (b) obj;
                if (of0.a(this.f7057a, bVar.f7057a) && of0.a(this.b, bVar.b)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return of0.a(this.f7057a, this.b);
        }

        public final String toString() {
            of0.a aVarA = of0.a(this);
            aVarA.a("key", this.f7057a);
            aVarA.a(IApp.ConfigProperty.CONFIG_FEATURE, this.b);
            return aVarA.toString();
        }

        public /* synthetic */ b(wc0 wc0Var, Feature feature, ud0 ud0Var) {
            this(wc0Var, feature);
        }
    }

    public final void b() {
        Handler handler = this.p;
        handler.sendMessage(handler.obtainMessage(3));
    }

    public final void b(@RecentlyNonNull ConnectionResult connectionResult, int i) {
        if (a(connectionResult, i)) {
            return;
        }
        Handler handler = this.p;
        handler.sendMessage(handler.obtainMessage(5, i, 0, connectionResult));
    }

    public final int a() {
        return this.j.getAndIncrement();
    }

    public final void a(@RecentlyNonNull oc0<?> oc0Var) {
        Handler handler = this.p;
        handler.sendMessage(handler.obtainMessage(7, oc0Var));
    }

    public final a a(wc0<?> wc0Var) {
        return this.l.get(wc0Var);
    }

    public static Status b(wc0<?> wc0Var, ConnectionResult connectionResult) {
        String strA = wc0Var.a();
        String strValueOf = String.valueOf(connectionResult);
        StringBuilder sb = new StringBuilder(String.valueOf(strA).length() + 63 + String.valueOf(strValueOf).length());
        sb.append("API: ");
        sb.append(strA);
        sb.append(" is not available on this device. Connection failed with: ");
        sb.append(strValueOf);
        return new Status(connectionResult, sb.toString());
    }

    public final <O extends mc0.d, ResultT> void a(@RecentlyNonNull oc0<O> oc0Var, int i, @RecentlyNonNull kd0<mc0.b, ResultT> kd0Var, @RecentlyNonNull rk0<ResultT> rk0Var, @RecentlyNonNull jd0 jd0Var) {
        a(rk0Var, kd0Var.c(), oc0Var);
        re0 re0Var = new re0(i, kd0Var, rk0Var, jd0Var);
        Handler handler = this.p;
        handler.sendMessage(handler.obtainMessage(4, new ge0(re0Var, this.k.get(), oc0Var)));
    }

    public final <T> void a(rk0<T> rk0Var, int i, oc0<?> oc0Var) {
        de0 de0VarA;
        if (i == 0 || (de0VarA = de0.a(this, i, oc0Var.a())) == null) {
            return;
        }
        qk0<T> qk0VarA = rk0Var.a();
        Handler handler = this.p;
        handler.getClass();
        qk0VarA.a(td0.a(handler), de0VarA);
    }

    public final boolean a(ConnectionResult connectionResult, int i) {
        return this.h.a(this.g, connectionResult, i);
    }

    public final void a(zao zaoVar, int i, long j, int i2) {
        Handler handler = this.p;
        handler.sendMessage(handler.obtainMessage(18, new ce0(zaoVar, i, j, i2)));
    }

    public static /* synthetic */ boolean a(bd0 bd0Var, boolean z) {
        bd0Var.d = true;
        return true;
    }
}
