package com.igexin.c.a.d;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.PowerManager;
import com.igexin.push.d.c.o;
import com.igexin.push.g.n;
import com.taobao.weex.ui.component.list.template.CellDataManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes2.dex */
public class g extends BroadcastReceiver implements Comparator<f> {
    public static final String F = "AlarmTaskSchedule.";
    public static final String G = "AlarmTaskScheduleBak.";
    public static final String H = "AlarmNioTaskSchedule.";
    public static final String h = "TaskService";
    public static final byte j = -1;
    public static final byte k = 0;
    public static final byte l = 1;
    public static final byte m = 2;
    public static final byte n = -128;
    public static final byte o = 7;
    public PendingIntent A;
    public String B;
    public volatile long C;
    public volatile boolean D;
    public boolean t;
    public PowerManager v;
    public AlarmManager w;
    public Intent x;
    public PendingIntent y;
    public Intent z;
    public static final String i = g.class.getName();
    public static final long E = TimeUnit.SECONDS.toMillis(2);
    public final ReentrantLock u = new ReentrantLock();
    public boolean I = false;
    public final HashMap<Long, com.igexin.c.a.d.a.c> q = new HashMap<>(7);
    public final e<f> s = new e<>(this, this);
    public final d r = new d();
    public final b p = new b();

    /* JADX INFO: renamed from: com.igexin.c.a.d.g$1, reason: invalid class name */
    public class AnonymousClass1 extends IntentFilter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f3200a;

        public AnonymousClass1(Context context) {
            this.f3200a = context;
            addAction(g.F + this.f3200a.getPackageName());
            addAction(g.G + this.f3200a.getPackageName());
            addAction("android.intent.action.SCREEN_OFF");
            addAction("android.intent.action.SCREEN_ON");
        }
    }

    public final class a {
        public volatile int g;
        public final ReentrantLock c = new ReentrantLock();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final BlockingQueue<f> f3201a = new SynchronousQueue();
        public final HashMap<Integer, RunnableC0074a> b = new HashMap<>();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public volatile long f3202e = TimeUnit.SECONDS.toNanos(60);
        public volatile int f = 0;
        public ThreadFactory d = new b();
        public volatile int h = Integer.MAX_VALUE;

        /* JADX INFO: renamed from: com.igexin.c.a.d.g$a$a, reason: collision with other inner class name */
        public final class RunnableC0074a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final BlockingQueue<f> f3203a = new LinkedBlockingQueue();
            public f b;
            public f c;
            public volatile int d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public volatile boolean f3204e;

            public RunnableC0074a(f fVar) {
                this.b = fVar;
            }

            private void a() {
                this.f3203a.clear();
                this.c = null;
            }

            private void a(f fVar) {
                if (this.d == 0) {
                    this.d = fVar.C;
                }
                boolean z = true;
                while (z) {
                    try {
                        try {
                            fVar.b_();
                            fVar.n();
                            if (!fVar.v) {
                                fVar.d_();
                            }
                            boolean z2 = fVar.m;
                            boolean z3 = fVar.p;
                            long j = fVar.w;
                        } catch (Exception e2) {
                            com.igexin.c.a.c.a.a(e2);
                            com.igexin.c.a.c.a.a(g.h + e2.toString(), new Object[0]);
                            fVar.v = true;
                            fVar.E = e2;
                            fVar.o();
                            fVar.k();
                            g.this.a((Object) fVar);
                            g.this.e();
                            if (!fVar.v) {
                                fVar.d_();
                            }
                            boolean z4 = fVar.m;
                            boolean z5 = fVar.p;
                            long j2 = fVar.w;
                            if (fVar.m || !fVar.p || fVar.w == 0) {
                            }
                        }
                    } finally {
                    }
                    if (fVar.m || !fVar.p || fVar.w == 0) {
                        fVar = null;
                        z = false;
                    }
                }
            }

            private f b() {
                while (this.d != 0) {
                    try {
                        f fVarPoll = this.f3203a.poll(a.this.f3202e, TimeUnit.NANOSECONDS);
                        if (fVarPoll != null) {
                            return fVarPoll;
                        }
                        if (this.f3203a.isEmpty()) {
                            ReentrantLock reentrantLock = a.this.c;
                            reentrantLock.lock();
                            try {
                                if (this.f3203a.isEmpty()) {
                                    a.this.b.remove(Integer.valueOf(this.d));
                                    this.d = 0;
                                    return null;
                                }
                            } finally {
                                reentrantLock.unlock();
                            }
                        } else {
                            continue;
                        }
                    } catch (InterruptedException e2) {
                        com.igexin.c.a.c.a.a(e2);
                    }
                }
                return null;
            }

            @Override // java.lang.Runnable
            public final void run() {
                boolean zA = true;
                while (zA) {
                    try {
                        try {
                            f fVarB = this.b;
                            this.b = null;
                            while (true) {
                                if (fVarB == null) {
                                    fVarB = b();
                                    if (fVarB == null && (fVarB = a.this.b()) == null) {
                                        zA = a.this.a(this);
                                        if (!zA) {
                                        }
                                    }
                                }
                                this.c = null;
                                if (this.d == 0) {
                                    this.d = fVarB.C;
                                }
                                f fVar = fVarB;
                                boolean z = true;
                                while (z) {
                                    try {
                                        try {
                                            fVar.b_();
                                            fVar.n();
                                            if (!fVar.v) {
                                                fVar.d_();
                                            }
                                            boolean z2 = fVar.m;
                                            boolean z3 = fVar.p;
                                            long j = fVar.w;
                                        } catch (Exception e2) {
                                            com.igexin.c.a.c.a.a(e2);
                                            com.igexin.c.a.c.a.a(g.h + e2.toString(), new Object[0]);
                                            fVar.v = true;
                                            fVar.E = e2;
                                            fVar.o();
                                            fVar.k();
                                            g.this.a((Object) fVar);
                                            g.this.e();
                                            if (!fVar.v) {
                                                fVar.d_();
                                            }
                                            boolean z4 = fVar.m;
                                            boolean z5 = fVar.p;
                                            long j2 = fVar.w;
                                            if (fVar.m || !fVar.p || fVar.w == 0) {
                                            }
                                        }
                                    } catch (Throwable th) {
                                        if (!fVar.v) {
                                            fVar.d_();
                                        }
                                        boolean z6 = fVar.m;
                                        boolean z7 = fVar.p;
                                        long j3 = fVar.w;
                                        if (fVar.m || !fVar.p || fVar.w == 0) {
                                            throw th;
                                        }
                                    }
                                    if (fVar.m || !fVar.p || fVar.w == 0) {
                                        fVar = null;
                                        z = false;
                                    }
                                }
                                this.c = fVarB;
                                fVarB = null;
                            }
                            throw th;
                        } catch (Exception e3) {
                            com.igexin.c.a.c.a.a(e3);
                            com.igexin.c.a.c.a.a("TaskService|Worker|run()|error" + e3.toString(), new Object[0]);
                            zA = a.this.a(this);
                            if (!zA) {
                                a();
                            }
                        }
                    } catch (Throwable th2) {
                        if (!a.this.a(this)) {
                            a();
                        }
                        throw th2;
                    }
                }
            }
        }

        public final class b implements ThreadFactory {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final AtomicInteger f3205a = new AtomicInteger(0);

            public b() {
            }

            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return new Thread(runnable, "TS-pool-" + this.f3205a.incrementAndGet());
            }
        }

        public a() {
        }

        private void c(f fVar) {
            if (fVar == null) {
                throw null;
            }
            if (fVar.C != 0) {
                ReentrantLock reentrantLock = this.c;
                reentrantLock.lock();
                try {
                    RunnableC0074a runnableC0074a = this.b.get(Integer.valueOf(fVar.C));
                    if (runnableC0074a != null) {
                        runnableC0074a.f3203a.offer(fVar);
                        return;
                    }
                } finally {
                    reentrantLock.unlock();
                }
            }
            if (this.g >= this.f || !a(fVar)) {
                if (!this.f3201a.offer(fVar)) {
                    b(fVar);
                } else if (this.g == 0) {
                    a();
                }
            }
        }

        private void d(f fVar) {
            if (this.g >= this.f || !a(fVar)) {
                if (!this.f3201a.offer(fVar)) {
                    if (!b(fVar)) {
                    }
                } else if (this.g == 0) {
                    a();
                }
            }
        }

        private Thread e(f fVar) {
            int i;
            RunnableC0074a runnableC0074a = new RunnableC0074a(fVar);
            if (fVar != null && (i = fVar.C) != 0) {
                this.b.put(Integer.valueOf(i), runnableC0074a);
            }
            Thread threadNewThread = this.d.newThread(runnableC0074a);
            if (threadNewThread != null) {
                this.g++;
            }
            return threadNewThread;
        }

        public final void a() {
            ReentrantLock reentrantLock = this.c;
            reentrantLock.lock();
            try {
                Thread threadE = null;
                if (this.g < Math.max(this.f, 1) && !this.f3201a.isEmpty()) {
                    threadE = e(null);
                }
                if (threadE != null) {
                    threadE.start();
                }
            } finally {
                reentrantLock.unlock();
            }
        }

        public final boolean a(f fVar) {
            ReentrantLock reentrantLock = this.c;
            reentrantLock.lock();
            try {
                Thread threadE = this.g < this.f ? e(fVar) : null;
                if (threadE == null) {
                    return false;
                }
                threadE.start();
                return true;
            } finally {
                reentrantLock.unlock();
            }
        }

        public final boolean a(RunnableC0074a runnableC0074a) {
            ReentrantLock reentrantLock = this.c;
            reentrantLock.lock();
            try {
                int i = this.g - 1;
                this.g = i;
                if (i == 0 && !this.f3201a.isEmpty()) {
                    Thread threadE = e(null);
                    if (threadE != null) {
                        threadE.start();
                    }
                } else if (!runnableC0074a.f3203a.isEmpty()) {
                    return true;
                }
                this.b.remove(Integer.valueOf(runnableC0074a.d));
                reentrantLock.unlock();
                return false;
            } finally {
                reentrantLock.unlock();
            }
        }

        public final f b() {
            f fVarPoll;
            while (true) {
                try {
                    fVarPoll = this.g > this.f ? this.f3201a.poll(this.f3202e, TimeUnit.NANOSECONDS) : this.f3201a.take();
                } catch (InterruptedException e2) {
                    com.igexin.c.a.c.a.a(e2);
                }
                if (fVarPoll != null) {
                    return fVarPoll;
                }
                if (this.f3201a.isEmpty()) {
                    return null;
                }
            }
        }

        public final boolean b(f fVar) {
            ReentrantLock reentrantLock = this.c;
            reentrantLock.lock();
            try {
                Thread threadE = this.g < this.h ? e(fVar) : null;
                if (threadE == null) {
                    return false;
                }
                threadE.start();
                return true;
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public final class b extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public volatile boolean f3206a = true;
        public long b;
        public long c;
        public a d;

        public b() {
            setName("TS-processor");
        }

        public static void a() {
        }

        /* JADX WARN: Can't wrap try/catch for region: R(11:(4:(2:(3:129|11|(2:136|133)(3:122|83|126))|125)|102|72|(2:76|(4:131|82|134|133)(4:132|81|135|133))(4:123|83|126|125))(6:101|14|(5:120|16|(1:18)|19|(2:21|(5:23|99|24|124|(4:108|26|27|119)(2:107|28))(1:106))(3:105|42|43))(2:44|(4:121|48|127|125))|3|4|(1:5))|97|50|51|(1:53)|54|(2:56|(1:58))|59|102|72|(0)(0)) */
        /* JADX WARN: Can't wrap try/catch for region: R(12:7|(4:(2:(3:129|11|(2:136|133)(3:122|83|126))|125)|102|72|(2:76|(4:131|82|134|133)(4:132|81|135|133))(4:123|83|126|125))(6:101|14|(5:120|16|(1:18)|19|(2:21|(5:23|99|24|124|(4:108|26|27|119)(2:107|28))(1:106))(3:105|42|43))(2:44|(4:121|48|127|125))|3|4|(1:5))|97|50|51|(1:53)|54|(2:56|(1:58))|59|102|72|(0)(0)) */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x007f, code lost:
        
            if (r5.g >= r5.f) goto L109;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x0085, code lost:
        
            if (r5.a(r6) != false) goto L115;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x008d, code lost:
        
            if (r5.f3201a.offer(r6) == false) goto L111;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0091, code lost:
        
            if (r5.g != 0) goto L117;
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x0093, code lost:
        
            r5.a();
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x0098, code lost:
        
            r5.b(r6);
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x00fe, code lost:
        
            r5 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:63:0x00ff, code lost:
        
            com.igexin.c.a.c.a.a(r5);
            com.igexin.c.a.c.a.a(com.igexin.c.a.d.g.h, r5.toString());
            com.igexin.c.a.c.a.a("TaskService|SERVICE_PROCESSING|error|" + r5.toString(), new java.lang.Object[0]);
            r6.v = true;
            r6.E = r5;
            r6.o();
            r6.k();
            r13.f3207e.r.a(r6);
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x0131, code lost:
        
            r13.f3207e.f();
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x0138, code lost:
        
            if (r6.v == false) goto L66;
         */
        /* JADX WARN: Code restructure failed: missing block: B:66:0x013a, code lost:
        
            r6.d_();
         */
        /* JADX WARN: Code restructure failed: missing block: B:68:0x013f, code lost:
        
            if (r6.m == false) goto L69;
         */
        /* JADX WARN: Removed duplicated region for block: B:123:0x0164 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:76:0x0155  */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void run() {
            /*
                Method dump skipped, instruction units count: 424
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.igexin.c.a.d.g.b.run():void");
        }
    }

    public g() {
        f.H = this;
    }

    public static int a(f fVar, f fVar2) {
        if (fVar.w < fVar2.w) {
            return -1;
        }
        if (fVar.w > fVar2.w) {
            return 1;
        }
        int i2 = fVar.D;
        int i3 = fVar2.D;
        if (i2 > i3) {
            return -1;
        }
        if (i2 < i3) {
            return 1;
        }
        if (fVar.x < fVar2.x) {
            return -1;
        }
        if (fVar.x > fVar2.x) {
            return 1;
        }
        return fVar.hashCode() - fVar2.hashCode();
    }

    private void a() {
        try {
            if (this.y != null) {
                this.w.cancel(this.y);
            }
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(h, th.toString());
        }
    }

    private void a(int i2, TimeUnit timeUnit) {
        this.p.b = TimeUnit.MILLISECONDS.convert(i2, timeUnit);
    }

    private void a(Context context) {
        if (this.I) {
            return;
        }
        if (!n.l()) {
            this.v = (PowerManager) context.getSystemService("power");
            this.D = true;
            this.w = (AlarmManager) context.getSystemService("alarm");
            try {
                if (Build.VERSION.SDK_INT >= 31) {
                    this.t = ((Boolean) AlarmManager.class.getDeclaredMethod("canScheduleExactAlarms", new Class[0]).invoke(this.w, new Object[0])).booleanValue();
                } else {
                    this.t = true;
                }
            } catch (Throwable th) {
                com.igexin.c.a.c.a.a(th);
            }
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(context);
            if (Build.VERSION.SDK_INT > 33) {
                context.registerReceiver(this, anonymousClass1, com.igexin.push.core.e.ac, null, 4);
            } else {
                context.registerReceiver(this, anonymousClass1, com.igexin.push.core.e.ac, null);
            }
            this.B = H + context.getPackageName();
            if (Build.VERSION.SDK_INT > 33) {
                context.registerReceiver(this, new IntentFilter(this.B), com.igexin.push.core.e.ac, null, 4);
            } else {
                context.registerReceiver(this, new IntentFilter(this.B), com.igexin.push.core.e.ac, null);
            }
            int i2 = 134217728;
            if (n.a(context) >= 31 && Build.VERSION.SDK_INT >= 30) {
                i2 = 201326592;
            }
            this.x = new Intent(F + context.getPackageName());
            this.y = PendingIntent.getBroadcast(context, hashCode(), this.x, i2);
            hashCode();
            this.z = new Intent(this.B);
            this.A = PendingIntent.getBroadcast(context, hashCode() + 2, this.z, i2);
            hashCode();
        }
        this.p.start();
        try {
            Thread.yield();
        } catch (Throwable th2) {
            com.igexin.c.a.c.a.a(th2);
        }
        this.I = true;
    }

    public static boolean a(com.igexin.c.a.d.a.e eVar, com.igexin.c.a.d.a.c cVar) {
        int iC = eVar.c();
        if (iC <= Integer.MIN_VALUE || iC >= 0) {
            if (iC < 0 || iC >= Integer.MAX_VALUE) {
                return false;
            }
            return cVar.a(eVar);
        }
        f fVar = (f) eVar;
        boolean zA = fVar.v ? false : cVar.a(eVar);
        if (zA) {
            fVar.d_();
        }
        return zA;
    }

    private boolean a(f fVar) {
        e<f> eVar = this.s;
        return eVar != null && eVar.c(fVar);
    }

    private boolean a(f fVar, boolean z, int i2, long j2, byte b2, Object obj, com.igexin.c.a.d.a.d dVar, int i3, com.igexin.c.a.d.a.g gVar) {
        if (fVar == null) {
            throw null;
        }
        fVar.A = i2;
        fVar.a((int) b2);
        fVar.F = obj;
        fVar.O = dVar;
        fVar.a(j2, TimeUnit.MILLISECONDS);
        fVar.a(i3, gVar);
        return a(fVar, z);
    }

    private boolean a(Class cls) {
        e<f> eVar = this.s;
        return eVar != null && eVar.a(cls);
    }

    @TargetApi(19)
    private void b(long j2) {
        if (n.l()) {
            return;
        }
        com.igexin.c.a.c.a.a("setnioalarm|" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(j2)), new Object[0]);
        if (j2 < 0) {
            j2 = System.currentTimeMillis() + E;
        }
        try {
            if (Build.VERSION.SDK_INT < 19) {
                this.w.set(0, j2, this.A);
                return;
            }
            try {
                if (this.t) {
                    this.w.setExact(0, j2, this.A);
                } else if (Build.VERSION.SDK_INT > 23) {
                    this.w.setAndAllowWhileIdle(0, j2, this.y);
                } else {
                    this.w.set(0, j2, this.A);
                }
            } catch (Exception unused) {
                this.w.set(0, j2, this.A);
            }
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(h, th.toString());
        }
    }

    private boolean b() {
        e<f> eVar = this.s;
        if (eVar == null) {
            return false;
        }
        eVar.c.clear();
        return true;
    }

    @TargetApi(19)
    public final void a(long j2) {
        if (this.D) {
            com.igexin.c.a.c.a.a("setalarm|" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(j2)), new Object[0]);
            if (j2 < 0) {
                j2 = System.currentTimeMillis() + E;
            }
            try {
                if (this.y != null) {
                    if (Build.VERSION.SDK_INT < 19) {
                        this.w.set(0, j2, this.y);
                        return;
                    }
                    try {
                        if (this.t) {
                            this.w.setExact(0, j2, this.y);
                        } else if (Build.VERSION.SDK_INT > 23) {
                            this.w.setAndAllowWhileIdle(0, j2, this.y);
                        } else {
                            this.w.set(0, j2, this.y);
                        }
                    } catch (Throwable unused) {
                        this.w.set(0, j2, this.y);
                    }
                }
            } catch (Throwable th) {
                com.igexin.c.a.c.a.a(h, th.toString());
                com.igexin.c.a.c.a.a(h + th.toString(), new Object[0]);
            }
        }
    }

    public final boolean a(com.igexin.c.a.d.a.c cVar) {
        ReentrantLock reentrantLock = this.u;
        if (reentrantLock.tryLock()) {
            try {
                if (this.q.containsKey(Long.valueOf(cVar.g()))) {
                    return false;
                }
                this.q.put(Long.valueOf(cVar.g()), cVar);
                reentrantLock.unlock();
                return true;
            } catch (Throwable th) {
                try {
                    com.igexin.c.a.c.a.a(th);
                    com.igexin.c.a.c.a.a("TaskService|" + th.toString(), new Object[0]);
                } finally {
                    reentrantLock.unlock();
                }
            }
        }
        return false;
    }

    public final boolean a(f fVar, boolean z) {
        if (fVar == null) {
            throw null;
        }
        int iIncrementAndGet = 0;
        if (fVar.q || fVar.m) {
            return false;
        }
        fVar.getClass().getName();
        e<f> eVar = this.s;
        if ((fVar instanceof com.igexin.c.a.b.f) && (((com.igexin.c.a.b.f) fVar).d instanceof o)) {
            if (z) {
                iIncrementAndGet = Integer.MAX_VALUE;
            }
        } else if (z) {
            iIncrementAndGet = eVar.d.incrementAndGet();
        }
        fVar.D = iIncrementAndGet;
        return eVar.a(fVar);
    }

    public final boolean a(f fVar, boolean z, boolean z2) {
        if (fVar == null) {
            throw null;
        }
        boolean z3 = false;
        if (fVar.n) {
            return false;
        }
        if (!z || z2) {
            if (z2 && z) {
                z3 = true;
            }
            return a(fVar, z3);
        }
        fVar.d();
        try {
            try {
                fVar.b_();
                fVar.n();
                if (!fVar.v) {
                    fVar.d_();
                }
                return true;
            } catch (Exception e2) {
                com.igexin.c.a.c.a.a(e2);
                fVar.v = true;
                fVar.E = e2;
                fVar.k();
                fVar.o();
                a((Object) fVar);
                e();
                if (!fVar.v) {
                    fVar.d_();
                }
                return false;
            }
        } catch (Throwable th) {
            if (!fVar.v) {
                fVar.d_();
            }
            throw th;
        }
    }

    public final boolean a(Object obj) {
        if (obj == null) {
            return false;
        }
        obj.getClass().getName();
        obj.hashCode();
        try {
            if (obj instanceof com.igexin.push.d.c.n) {
                obj.getClass().getName();
                obj.hashCode();
            }
        } catch (Exception e2) {
            com.igexin.c.a.c.a.a(e2);
        }
        obj.getClass().getName();
        obj.hashCode();
        com.igexin.c.a.c.a.a("TaskService|responseQueue ++ task = " + obj.getClass().getName() + CellDataManager.VIRTUAL_COMPONENT_SEPRATOR + obj.hashCode(), new Object[0]);
        if (!(obj instanceof com.igexin.c.a.d.a.e)) {
            throw new ClassCastException("response Obj is not a TaskResult ");
        }
        com.igexin.c.a.d.a.e eVar = (com.igexin.c.a.d.a.e) obj;
        if (eVar.i()) {
            return false;
        }
        eVar.a(false);
        if ((obj instanceof com.igexin.push.d.b.a) || (obj instanceof com.igexin.push.d.b.b)) {
            this.r.a();
            com.igexin.c.a.c.a.a("TaskService|change to primaryQueue", new Object[0]);
        }
        this.r.a(eVar);
        return true;
    }

    @Override // java.util.Comparator
    public /* synthetic */ int compare(f fVar, f fVar2) {
        f fVar3 = fVar;
        f fVar4 = fVar2;
        if (fVar3.w < fVar4.w) {
            return -1;
        }
        if (fVar3.w > fVar4.w) {
            return 1;
        }
        int i2 = fVar3.D;
        int i3 = fVar4.D;
        if (i2 > i3) {
            return -1;
        }
        if (i2 < i3) {
            return 1;
        }
        if (fVar3.x < fVar4.x) {
            return -1;
        }
        if (fVar3.x > fVar4.x) {
            return 1;
        }
        return fVar3.hashCode() - fVar4.hashCode();
    }

    public final void d() {
        try {
            if (this.A != null) {
                this.w.cancel(this.A);
            }
        } catch (Throwable th) {
            com.igexin.c.a.c.a.a(h, th.toString());
        }
    }

    public final void e() {
        b bVar = this.p;
        if (bVar == null || bVar.isInterrupted()) {
            return;
        }
        this.p.interrupt();
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ac A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0000 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f() {
        /*
            Method dump skipped, instruction units count: 207
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.c.a.d.g.f():void");
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
            this.D = true;
            com.igexin.c.a.c.a.a("screenoff", new Object[0]);
            if (this.s.g.get() > 0) {
                a(this.s.g.get());
                return;
            }
            return;
        }
        if ("android.intent.action.SCREEN_ON".equals(intent.getAction())) {
            this.D = false;
            com.igexin.c.a.c.a.a("screenon", new Object[0]);
            return;
        }
        if (intent.getAction().startsWith(F) || intent.getAction().startsWith(G)) {
            Calendar.getInstance().getTime().toLocaleString();
            com.igexin.c.a.c.a.a("receivealarm|" + this.D, new Object[0]);
            e();
            return;
        }
        if (this.B.equals(intent.getAction())) {
            Calendar calendar = Calendar.getInstance();
            com.igexin.c.a.c.a.b(i, "CPU ON + NioAlarmReceiver:-> cTime; " + calendar.getTime().toLocaleString());
            try {
                com.igexin.c.a.c.a.a(h, " alarm time out #######");
                com.igexin.c.a.c.a.a("TaskService|alarm time out #######", new Object[0]);
                com.igexin.c.a.b.a.a.d.a().f();
            } catch (Exception e2) {
                com.igexin.c.a.c.a.a(e2);
            }
        }
    }
}
