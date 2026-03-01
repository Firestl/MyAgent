package supwisdom;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import rx.internal.util.RxThreadFactory;
import supwisdom.uw1;

/* JADX INFO: compiled from: EventLoopsScheduler.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ux1 extends uw1 implements yx1 {
    public static final int c;
    public static final c d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b f9451e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ThreadFactory f9452a;
    public final AtomicReference<b> b = new AtomicReference<>(f9451e);

    /* JADX INFO: compiled from: EventLoopsScheduler.java */
    public static final class a extends uw1.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final fy1 f9453a = new fy1();
        public final sz1 b;
        public final fy1 c;
        public final c d;

        /* JADX INFO: renamed from: supwisdom.ux1$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: EventLoopsScheduler.java */
        public class C0233a implements ax1 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ ax1 f9454a;

            public C0233a(ax1 ax1Var) {
                this.f9454a = ax1Var;
            }

            @Override // supwisdom.ax1
            public void call() {
                if (a.this.isUnsubscribed()) {
                    return;
                }
                this.f9454a.call();
            }
        }

        public a(c cVar) {
            sz1 sz1Var = new sz1();
            this.b = sz1Var;
            this.c = new fy1(this.f9453a, sz1Var);
            this.d = cVar;
        }

        @Override // supwisdom.uw1.a
        public yw1 a(ax1 ax1Var) {
            return isUnsubscribed() ? tz1.a() : this.d.a(new C0233a(ax1Var), 0L, null, this.f9453a);
        }

        @Override // supwisdom.yw1
        public boolean isUnsubscribed() {
            return this.c.isUnsubscribed();
        }

        @Override // supwisdom.yw1
        public void unsubscribe() {
            this.c.unsubscribe();
        }
    }

    /* JADX INFO: compiled from: EventLoopsScheduler.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f9455a;
        public final c[] b;
        public long c;

        public b(ThreadFactory threadFactory, int i) {
            this.f9455a = i;
            this.b = new c[i];
            for (int i2 = 0; i2 < i; i2++) {
                this.b[i2] = new c(threadFactory);
            }
        }

        public c a() {
            int i = this.f9455a;
            if (i == 0) {
                return ux1.d;
            }
            c[] cVarArr = this.b;
            long j = this.c;
            this.c = 1 + j;
            return cVarArr[(int) (j % ((long) i))];
        }

        public void b() {
            for (c cVar : this.b) {
                cVar.unsubscribe();
            }
        }
    }

    /* JADX INFO: compiled from: EventLoopsScheduler.java */
    public static final class c extends xx1 {
        public c(ThreadFactory threadFactory) {
            super(threadFactory);
        }
    }

    static {
        int iIntValue = Integer.getInteger("rx.scheduler.max-computation-threads", 0).intValue();
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        if (iIntValue <= 0 || iIntValue > iAvailableProcessors) {
            iIntValue = iAvailableProcessors;
        }
        c = iIntValue;
        c cVar = new c(RxThreadFactory.NONE);
        d = cVar;
        cVar.unsubscribe();
        f9451e = new b(null, 0);
    }

    public ux1(ThreadFactory threadFactory) {
        this.f9452a = threadFactory;
        c();
    }

    @Override // supwisdom.uw1
    public uw1.a a() {
        return new a(this.b.get().a());
    }

    public void c() {
        b bVar = new b(this.f9452a, c);
        if (this.b.compareAndSet(f9451e, bVar)) {
            return;
        }
        bVar.b();
    }

    @Override // supwisdom.yx1
    public void shutdown() {
        b bVar;
        b bVar2;
        do {
            bVar = this.b.get();
            bVar2 = f9451e;
            if (bVar == bVar2) {
                return;
            }
        } while (!this.b.compareAndSet(bVar, bVar2));
        bVar.b();
    }

    public yw1 a(ax1 ax1Var) {
        return this.b.get().a().b(ax1Var, -1L, TimeUnit.NANOSECONDS);
    }
}
