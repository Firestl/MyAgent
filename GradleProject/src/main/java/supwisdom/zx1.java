package supwisdom;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import supwisdom.uw1;

/* JADX INFO: compiled from: TrampolineScheduler.java */
/* JADX INFO: loaded from: classes3.dex */
public final class zx1 extends uw1 {

    /* JADX INFO: compiled from: TrampolineScheduler.java */
    public static final class a extends uw1.a implements yw1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final AtomicInteger f10044a = new AtomicInteger();
        public final PriorityBlockingQueue<b> b = new PriorityBlockingQueue<>();
        public final rz1 c = new rz1();
        public final AtomicInteger d = new AtomicInteger();

        /* JADX INFO: renamed from: supwisdom.zx1$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: TrampolineScheduler.java */
        public class C0237a implements ax1 {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ b f10045a;

            public C0237a(b bVar) {
                this.f10045a = bVar;
            }

            @Override // supwisdom.ax1
            public void call() {
                a.this.b.remove(this.f10045a);
            }
        }

        @Override // supwisdom.uw1.a
        public yw1 a(ax1 ax1Var) {
            return a(ax1Var, a());
        }

        @Override // supwisdom.yw1
        public boolean isUnsubscribed() {
            return this.c.isUnsubscribed();
        }

        @Override // supwisdom.yw1
        public void unsubscribe() {
            this.c.unsubscribe();
        }

        public final yw1 a(ax1 ax1Var, long j) {
            if (this.c.isUnsubscribed()) {
                return tz1.a();
            }
            b bVar = new b(ax1Var, Long.valueOf(j), this.f10044a.incrementAndGet());
            this.b.add(bVar);
            if (this.d.getAndIncrement() != 0) {
                return tz1.a(new C0237a(bVar));
            }
            do {
                b bVarPoll = this.b.poll();
                if (bVarPoll != null) {
                    bVarPoll.f10046a.call();
                }
            } while (this.d.decrementAndGet() > 0);
            return tz1.a();
        }
    }

    /* JADX INFO: compiled from: TrampolineScheduler.java */
    public static final class b implements Comparable<b> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ax1 f10046a;
        public final Long b;
        public final int c;

        public b(ax1 ax1Var, Long l, int i) {
            this.f10046a = ax1Var;
            this.b = l;
            this.c = i;
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(b bVar) {
            int iCompareTo = this.b.compareTo(bVar.b);
            return iCompareTo == 0 ? zx1.a(this.c, bVar.c) : iCompareTo;
        }
    }

    static {
        new zx1();
    }

    public static int a(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    @Override // supwisdom.uw1
    public uw1.a a() {
        return new a();
    }
}
