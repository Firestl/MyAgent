package supwisdom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.bumptech.glide.manager.DefaultConnectivityMonitorFactory;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import supwisdom.fh1;

/* JADX INFO: compiled from: Dispatcher.java */
/* JADX INFO: loaded from: classes2.dex */
public class zg1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f9998a;
    public final Context b;
    public final ExecutorService c;
    public final Downloader d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Map<String, tg1> f9999e;
    public final Map<Object, rg1> f;
    public final Map<Object, rg1> g;
    public final Set<Object> h;
    public final Handler i;
    public final Handler j;
    public final ug1 k;
    public final nh1 l;
    public final List<tg1> m;
    public final c n;
    public final boolean o;
    public boolean p;

    /* JADX INFO: compiled from: Dispatcher.java */
    public static class a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final zg1 f10000a;

        /* JADX INFO: renamed from: supwisdom.zg1$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Dispatcher.java */
        public class RunnableC0236a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Message f10001a;

            public RunnableC0236a(a aVar, Message message) {
                this.f10001a = message;
            }

            @Override // java.lang.Runnable
            public void run() {
                throw new AssertionError("Unknown handler message received: " + this.f10001a.what);
            }
        }

        public a(Looper looper, zg1 zg1Var) {
            super(looper);
            this.f10000a = zg1Var;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.f10000a.e((rg1) message.obj);
                    break;
                case 2:
                    this.f10000a.d((rg1) message.obj);
                    break;
                case 3:
                case 8:
                default:
                    Picasso.p.post(new RunnableC0236a(this, message));
                    break;
                case 4:
                    this.f10000a.f((tg1) message.obj);
                    break;
                case 5:
                    this.f10000a.g((tg1) message.obj);
                    break;
                case 6:
                    this.f10000a.a((tg1) message.obj, false);
                    break;
                case 7:
                    this.f10000a.b();
                    break;
                case 9:
                    this.f10000a.b((NetworkInfo) message.obj);
                    break;
                case 10:
                    this.f10000a.b(message.arg1 == 1);
                    break;
                case 11:
                    this.f10000a.a(message.obj);
                    break;
                case 12:
                    this.f10000a.b(message.obj);
                    break;
            }
        }
    }

    /* JADX INFO: compiled from: Dispatcher.java */
    public static class b extends HandlerThread {
        public b() {
            super("Picasso-Dispatcher", 10);
        }
    }

    /* JADX INFO: compiled from: Dispatcher.java */
    public static class c extends BroadcastReceiver {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final zg1 f10002a;

        public c(zg1 zg1Var) {
            this.f10002a = zg1Var;
        }

        public void a() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.AIRPLANE_MODE");
            if (this.f10002a.o) {
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            }
            this.f10002a.b.registerReceiver(this, intentFilter);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            String action = intent.getAction();
            if ("android.intent.action.AIRPLANE_MODE".equals(action)) {
                if (intent.hasExtra("state")) {
                    this.f10002a.a(intent.getBooleanExtra("state", false));
                }
            } else if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
                this.f10002a.a(((ConnectivityManager) rh1.a(context, "connectivity")).getActiveNetworkInfo());
            }
        }
    }

    public zg1(Context context, ExecutorService executorService, Handler handler, Downloader downloader, ug1 ug1Var, nh1 nh1Var) {
        b bVar = new b();
        this.f9998a = bVar;
        bVar.start();
        rh1.a(this.f9998a.getLooper());
        this.b = context;
        this.c = executorService;
        this.f9999e = new LinkedHashMap();
        this.f = new WeakHashMap();
        this.g = new WeakHashMap();
        this.h = new HashSet();
        this.i = new a(this.f9998a.getLooper(), this);
        this.d = downloader;
        this.j = handler;
        this.k = ug1Var;
        this.l = nh1Var;
        this.m = new ArrayList(4);
        this.p = rh1.d(this.b);
        this.o = rh1.b(context, DefaultConnectivityMonitorFactory.NETWORK_PERMISSION);
        c cVar = new c(this);
        this.n = cVar;
        cVar.a();
    }

    public void a(rg1 rg1Var) {
        Handler handler = this.i;
        handler.sendMessage(handler.obtainMessage(2, rg1Var));
    }

    public void b(rg1 rg1Var) {
        Handler handler = this.i;
        handler.sendMessage(handler.obtainMessage(1, rg1Var));
    }

    public void c(tg1 tg1Var) {
        Handler handler = this.i;
        handler.sendMessage(handler.obtainMessage(6, tg1Var));
    }

    public void d(tg1 tg1Var) {
        Handler handler = this.i;
        handler.sendMessageDelayed(handler.obtainMessage(5, tg1Var), 500L);
    }

    public void e(rg1 rg1Var) {
        a(rg1Var, true);
    }

    public void f(tg1 tg1Var) {
        if (MemoryPolicy.shouldWriteToMemoryCache(tg1Var.i())) {
            this.k.a(tg1Var.g(), tg1Var.l());
        }
        this.f9999e.remove(tg1Var.g());
        a(tg1Var);
        if (tg1Var.j().n) {
            rh1.a("Dispatcher", "batched", rh1.a(tg1Var), "for completion");
        }
    }

    public void g(tg1 tg1Var) {
        if (tg1Var.n()) {
            return;
        }
        boolean z = false;
        if (this.c.isShutdown()) {
            a(tg1Var, false);
            return;
        }
        NetworkInfo activeNetworkInfo = this.o ? ((ConnectivityManager) rh1.a(this.b, "connectivity")).getActiveNetworkInfo() : null;
        boolean z2 = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        boolean zA = tg1Var.a(this.p, activeNetworkInfo);
        boolean zO = tg1Var.o();
        if (!zA) {
            if (this.o && zO) {
                z = true;
            }
            a(tg1Var, z);
            if (z) {
                e(tg1Var);
                return;
            }
            return;
        }
        if (this.o && !z2) {
            a(tg1Var, zO);
            if (zO) {
                e(tg1Var);
                return;
            }
            return;
        }
        if (tg1Var.j().n) {
            rh1.a("Dispatcher", "retrying", rh1.a(tg1Var));
        }
        if (tg1Var.f() instanceof fh1.a) {
            tg1Var.i |= NetworkPolicy.NO_CACHE.index;
        }
        tg1Var.n = this.c.submit(tg1Var);
    }

    public void a(NetworkInfo networkInfo) {
        Handler handler = this.i;
        handler.sendMessage(handler.obtainMessage(9, networkInfo));
    }

    public void b(tg1 tg1Var) {
        Handler handler = this.i;
        handler.sendMessage(handler.obtainMessage(4, tg1Var));
    }

    public final void c(rg1 rg1Var) {
        Object objJ = rg1Var.j();
        if (objJ != null) {
            rg1Var.k = true;
            this.f.put(objJ, rg1Var);
        }
    }

    public void d(rg1 rg1Var) {
        String strC = rg1Var.c();
        tg1 tg1Var = this.f9999e.get(strC);
        if (tg1Var != null) {
            tg1Var.b(rg1Var);
            if (tg1Var.a()) {
                this.f9999e.remove(strC);
                if (rg1Var.f().n) {
                    rh1.a("Dispatcher", "canceled", rg1Var.h().d());
                }
            }
        }
        if (this.h.contains(rg1Var.i())) {
            this.g.remove(rg1Var.j());
            if (rg1Var.f().n) {
                rh1.a("Dispatcher", "canceled", rg1Var.h().d(), "because paused request got canceled");
            }
        }
        rg1 rg1VarRemove = this.f.remove(rg1Var.j());
        if (rg1VarRemove == null || !rg1VarRemove.f().n) {
            return;
        }
        rh1.a("Dispatcher", "canceled", rg1VarRemove.h().d(), "from replaying");
    }

    public final void e(tg1 tg1Var) {
        rg1 rg1VarC = tg1Var.c();
        if (rg1VarC != null) {
            c(rg1VarC);
        }
        List<rg1> listD = tg1Var.d();
        if (listD != null) {
            int size = listD.size();
            for (int i = 0; i < size; i++) {
                c(listD.get(i));
            }
        }
    }

    public void a(boolean z) {
        Handler handler = this.i;
        handler.sendMessage(handler.obtainMessage(10, z ? 1 : 0, 0));
    }

    public void b(Object obj) {
        if (this.h.remove(obj)) {
            ArrayList arrayList = null;
            Iterator<rg1> it = this.g.values().iterator();
            while (it.hasNext()) {
                rg1 next = it.next();
                if (next.i().equals(obj)) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(next);
                    it.remove();
                }
            }
            if (arrayList != null) {
                Handler handler = this.j;
                handler.sendMessage(handler.obtainMessage(13, arrayList));
            }
        }
    }

    public void a(rg1 rg1Var, boolean z) {
        if (this.h.contains(rg1Var.i())) {
            this.g.put(rg1Var.j(), rg1Var);
            if (rg1Var.f().n) {
                rh1.a("Dispatcher", "paused", rg1Var.b.d(), "because tag '" + rg1Var.i() + "' is paused");
                return;
            }
            return;
        }
        tg1 tg1Var = this.f9999e.get(rg1Var.c());
        if (tg1Var != null) {
            tg1Var.a(rg1Var);
            return;
        }
        if (this.c.isShutdown()) {
            if (rg1Var.f().n) {
                rh1.a("Dispatcher", "ignored", rg1Var.b.d(), "because shut down");
                return;
            }
            return;
        }
        tg1 tg1VarA = tg1.a(rg1Var.f(), this, this.k, this.l, rg1Var);
        tg1VarA.n = this.c.submit(tg1VarA);
        this.f9999e.put(rg1Var.c(), tg1VarA);
        if (z) {
            this.f.remove(rg1Var.j());
        }
        if (rg1Var.f().n) {
            rh1.a("Dispatcher", "enqueued", rg1Var.b.d());
        }
    }

    public void b() {
        ArrayList arrayList = new ArrayList(this.m);
        this.m.clear();
        Handler handler = this.j;
        handler.sendMessage(handler.obtainMessage(8, arrayList));
        a((List<tg1>) arrayList);
    }

    public void b(boolean z) {
        this.p = z;
    }

    public void b(NetworkInfo networkInfo) {
        ExecutorService executorService = this.c;
        if (executorService instanceof ih1) {
            ((ih1) executorService).a(networkInfo);
        }
        if (networkInfo == null || !networkInfo.isConnected()) {
            return;
        }
        a();
    }

    public void a(Object obj) {
        if (this.h.add(obj)) {
            Iterator<tg1> it = this.f9999e.values().iterator();
            while (it.hasNext()) {
                tg1 next = it.next();
                boolean z = next.j().n;
                rg1 rg1VarC = next.c();
                List<rg1> listD = next.d();
                boolean z2 = (listD == null || listD.isEmpty()) ? false : true;
                if (rg1VarC != null || z2) {
                    if (rg1VarC != null && rg1VarC.i().equals(obj)) {
                        next.b(rg1VarC);
                        this.g.put(rg1VarC.j(), rg1VarC);
                        if (z) {
                            rh1.a("Dispatcher", "paused", rg1VarC.b.d(), "because tag '" + obj + "' was paused");
                        }
                    }
                    if (z2) {
                        for (int size = listD.size() - 1; size >= 0; size--) {
                            rg1 rg1Var = listD.get(size);
                            if (rg1Var.i().equals(obj)) {
                                next.b(rg1Var);
                                this.g.put(rg1Var.j(), rg1Var);
                                if (z) {
                                    rh1.a("Dispatcher", "paused", rg1Var.b.d(), "because tag '" + obj + "' was paused");
                                }
                            }
                        }
                    }
                    if (next.a()) {
                        it.remove();
                        if (z) {
                            rh1.a("Dispatcher", "canceled", rh1.a(next), "all actions paused");
                        }
                    }
                }
            }
        }
    }

    public void a(tg1 tg1Var, boolean z) {
        if (tg1Var.j().n) {
            String strA = rh1.a(tg1Var);
            StringBuilder sb = new StringBuilder();
            sb.append("for error");
            sb.append(z ? " (will replay)" : "");
            rh1.a("Dispatcher", "batched", strA, sb.toString());
        }
        this.f9999e.remove(tg1Var.g());
        a(tg1Var);
    }

    public final void a() {
        if (this.f.isEmpty()) {
            return;
        }
        Iterator<rg1> it = this.f.values().iterator();
        while (it.hasNext()) {
            rg1 next = it.next();
            it.remove();
            if (next.f().n) {
                rh1.a("Dispatcher", "replaying", next.h().d());
            }
            a(next, false);
        }
    }

    public final void a(tg1 tg1Var) {
        if (tg1Var.n()) {
            return;
        }
        this.m.add(tg1Var);
        if (this.i.hasMessages(7)) {
            return;
        }
        this.i.sendEmptyMessageDelayed(7, 200L);
    }

    public final void a(List<tg1> list) {
        if (list == null || list.isEmpty() || !list.get(0).j().n) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (tg1 tg1Var : list) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(rh1.a(tg1Var));
        }
        rh1.a("Dispatcher", "delivered", sb.toString());
    }
}
