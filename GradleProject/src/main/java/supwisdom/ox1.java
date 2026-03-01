package supwisdom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.exceptions.OnErrorThrowable;
import supwisdom.rw1;

/* JADX INFO: compiled from: OperatorReplay.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ox1<T> extends dz1<T> implements yw1 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final ex1 f8731e = new a();
    public final rw1<? extends T> b;
    public final AtomicReference<i<T>> c;
    public final ex1<? extends h<T>> d;

    /* JADX INFO: compiled from: OperatorReplay.java */
    public static class a implements ex1 {
        @Override // supwisdom.ex1
        public Object call() {
            return new l(16);
        }
    }

    /* JADX INFO: compiled from: OperatorReplay.java */
    public static class b implements ex1<h<T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f8732a;

        public b(int i) {
            this.f8732a = i;
        }

        @Override // supwisdom.ex1
        public h<T> call() {
            return new k(this.f8732a);
        }
    }

    /* JADX INFO: compiled from: OperatorReplay.java */
    public static class c implements ex1<h<T>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f8733a;
        public final /* synthetic */ long b;
        public final /* synthetic */ uw1 c;

        public c(int i, long j, uw1 uw1Var) {
            this.f8733a = i;
            this.b = j;
            this.c = uw1Var;
        }

        @Override // supwisdom.ex1
        public h<T> call() {
            return new j(this.f8733a, this.b, this.c);
        }
    }

    /* JADX INFO: compiled from: OperatorReplay.java */
    public static class d implements rw1.a<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ AtomicReference f8734a;
        public final /* synthetic */ ex1 b;

        public d(AtomicReference atomicReference, ex1 ex1Var) {
            this.f8734a = atomicReference;
            this.b = ex1Var;
        }

        @Override // supwisdom.bx1
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void call(xw1<? super T> xw1Var) {
            i iVar;
            while (true) {
                iVar = (i) this.f8734a.get();
                if (iVar != null) {
                    break;
                }
                i iVar2 = new i((h) this.b.call());
                iVar2.b();
                if (this.f8734a.compareAndSet(iVar, iVar2)) {
                    iVar = iVar2;
                    break;
                }
            }
            f<T> fVar = new f<>(iVar, xw1Var);
            iVar.a(fVar);
            xw1Var.add(fVar);
            iVar.f8739a.a((f) fVar);
            xw1Var.setProducer(fVar);
        }
    }

    /* JADX INFO: compiled from: OperatorReplay.java */
    public static class e<T> extends AtomicReference<g> implements h<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public g f8735a;
        public int b;
        public long c;

        public e() {
            g gVar = new g(null, 0L);
            this.f8735a = gVar;
            set(gVar);
        }

        public final void a(g gVar) {
            this.f8735a.set(gVar);
            this.f8735a = gVar;
            this.b++;
        }

        public Object b(Object obj) {
            return obj;
        }

        public final void b(g gVar) {
            set(gVar);
        }

        public Object c(Object obj) {
            return obj;
        }

        public final void c() {
            g gVar = get().get();
            if (gVar == null) {
                throw new IllegalStateException("Empty list!");
            }
            this.b--;
            b(gVar);
        }

        public void d() {
            throw null;
        }

        public void e() {
        }

        public g b() {
            return get();
        }

        @Override // supwisdom.ox1.h
        public final void a(T t) {
            Object objB = b(ix1.d(t));
            long j = this.c + 1;
            this.c = j;
            a(new g(objB, j));
            d();
        }

        @Override // supwisdom.ox1.h
        public final void a(Throwable th) {
            Object objB = b(ix1.a(th));
            long j = this.c + 1;
            this.c = j;
            a(new g(objB, j));
            e();
        }

        @Override // supwisdom.ox1.h
        public final void a() {
            Object objB = b(ix1.a());
            long j = this.c + 1;
            this.c = j;
            a(new g(objB, j));
            e();
        }

        @Override // supwisdom.ox1.h
        public final void a(f<T> fVar) {
            xw1<? super T> xw1Var;
            g gVar;
            synchronized (fVar) {
                if (fVar.f8737e) {
                    fVar.f = true;
                    return;
                }
                fVar.f8737e = true;
                while (!fVar.isUnsubscribed()) {
                    g gVarB = (g) fVar.a();
                    if (gVarB == null) {
                        gVarB = b();
                        fVar.c = gVarB;
                        fVar.a(gVarB.b);
                    }
                    if (fVar.isUnsubscribed() || (xw1Var = fVar.b) == null) {
                        return;
                    }
                    long j = fVar.get();
                    long j2 = 0;
                    while (j2 != j && (gVar = gVarB.get()) != null) {
                        Object objC = c(gVar.f8738a);
                        try {
                            if (ix1.a(xw1Var, objC)) {
                                fVar.c = null;
                                return;
                            }
                            j2++;
                            if (fVar.isUnsubscribed()) {
                                return;
                            } else {
                                gVarB = gVar;
                            }
                        } catch (Throwable th) {
                            fVar.c = null;
                            zw1.b(th);
                            fVar.unsubscribe();
                            if (ix1.c(objC) || ix1.b(objC)) {
                                return;
                            }
                            xw1Var.onError(OnErrorThrowable.addValueAsLastCause(th, ix1.a(objC)));
                            return;
                        }
                    }
                    if (j2 != 0) {
                        fVar.c = gVarB;
                        if (j != Long.MAX_VALUE) {
                            fVar.b(j2);
                        }
                    }
                    synchronized (fVar) {
                        if (!fVar.f) {
                            fVar.f8737e = false;
                            return;
                        }
                        fVar.f = false;
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: OperatorReplay.java */
    public static final class g extends AtomicReference<g> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f8738a;
        public final long b;

        public g(Object obj, long j) {
            this.f8738a = obj;
            this.b = j;
        }
    }

    /* JADX INFO: compiled from: OperatorReplay.java */
    public interface h<T> {
        void a();

        void a(T t);

        void a(Throwable th);

        void a(f<T> fVar);
    }

    /* JADX INFO: compiled from: OperatorReplay.java */
    public static final class i<T> extends xw1<T> implements yw1 {
        public static final f[] p = new f[0];

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final h<T> f8739a;
        public boolean b;
        public volatile boolean c;
        public volatile long f;
        public long g;
        public boolean i;
        public boolean j;
        public long k;
        public long l;
        public volatile tw1 m;
        public List<f<T>> n;
        public boolean o;
        public final by1<f<T>> d = new by1<>();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public f<T>[] f8740e = p;
        public final AtomicBoolean h = new AtomicBoolean();

        /* JADX INFO: compiled from: OperatorReplay.java */
        public class a implements ax1 {
            public a() {
            }

            @Override // supwisdom.ax1
            public void call() {
                if (i.this.c) {
                    return;
                }
                synchronized (i.this.d) {
                    if (!i.this.c) {
                        i.this.d.c();
                        i.this.f++;
                        i.this.c = true;
                    }
                }
            }
        }

        public i(h<T> hVar) {
            this.f8739a = hVar;
            request(0L);
        }

        public boolean a(f<T> fVar) {
            if (fVar == null) {
                throw null;
            }
            if (this.c) {
                return false;
            }
            synchronized (this.d) {
                if (this.c) {
                    return false;
                }
                this.d.a(fVar);
                this.f++;
                return true;
            }
        }

        public void b() {
            add(tz1.a(new a()));
        }

        public void c(f<T> fVar) {
            if (this.c) {
                return;
            }
            synchronized (this.d) {
                if (this.c) {
                    return;
                }
                this.d.b(fVar);
                if (this.d.a()) {
                    this.f8740e = p;
                }
                this.f++;
            }
        }

        @Override // supwisdom.sw1
        public void onCompleted() {
            if (this.b) {
                return;
            }
            this.b = true;
            try {
                this.f8739a.a();
                c();
            } finally {
                unsubscribe();
            }
        }

        @Override // supwisdom.sw1
        public void onError(Throwable th) {
            if (this.b) {
                return;
            }
            this.b = true;
            try {
                this.f8739a.a(th);
                c();
            } finally {
                unsubscribe();
            }
        }

        @Override // supwisdom.sw1
        public void onNext(T t) {
            if (this.b) {
                return;
            }
            this.f8739a.a(t);
            c();
        }

        @Override // supwisdom.xw1
        public void setProducer(tw1 tw1Var) {
            if (this.m != null) {
                throw new IllegalStateException("Only a single producer can be set on a Subscriber.");
            }
            this.m = tw1Var;
            b(null);
            c();
        }

        public void b(f<T> fVar) {
            long jMax;
            List<f<T>> list;
            boolean z;
            long jMax2;
            if (isUnsubscribed()) {
                return;
            }
            synchronized (this) {
                if (this.i) {
                    if (fVar != null) {
                        List arrayList = this.n;
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                            this.n = arrayList;
                        }
                        arrayList.add(fVar);
                    } else {
                        this.o = true;
                    }
                    this.j = true;
                    return;
                }
                this.i = true;
                long j = this.k;
                if (fVar != null) {
                    jMax = Math.max(j, fVar.d.get());
                } else {
                    long jMax3 = j;
                    for (f<T> fVar2 : a()) {
                        if (fVar2 != null) {
                            jMax3 = Math.max(jMax3, fVar2.d.get());
                        }
                    }
                    jMax = jMax3;
                }
                a(jMax, j);
                while (!isUnsubscribed()) {
                    synchronized (this) {
                        if (!this.j) {
                            this.i = false;
                            return;
                        }
                        this.j = false;
                        list = this.n;
                        this.n = null;
                        z = this.o;
                        this.o = false;
                    }
                    long j2 = this.k;
                    if (list != null) {
                        Iterator<f<T>> it = list.iterator();
                        jMax2 = j2;
                        while (it.hasNext()) {
                            jMax2 = Math.max(jMax2, it.next().d.get());
                        }
                    } else {
                        jMax2 = j2;
                    }
                    if (z) {
                        for (f<T> fVar3 : a()) {
                            if (fVar3 != null) {
                                jMax2 = Math.max(jMax2, fVar3.d.get());
                            }
                        }
                    }
                    a(jMax2, j2);
                }
            }
        }

        public f<T>[] a() {
            f<T>[] fVarArr;
            synchronized (this.d) {
                f<T>[] fVarArrD = this.d.d();
                int length = fVarArrD.length;
                fVarArr = new f[length];
                System.arraycopy(fVarArrD, 0, fVarArr, 0, length);
            }
            return fVarArr;
        }

        public void c() {
            f<T>[] fVarArr = this.f8740e;
            if (this.g != this.f) {
                synchronized (this.d) {
                    fVarArr = this.f8740e;
                    f<T>[] fVarArrD = this.d.d();
                    int length = fVarArrD.length;
                    if (fVarArr.length != length) {
                        fVarArr = new f[length];
                        this.f8740e = fVarArr;
                    }
                    System.arraycopy(fVarArrD, 0, fVarArr, 0, length);
                    this.g = this.f;
                }
            }
            h<T> hVar = this.f8739a;
            for (f<T> fVar : fVarArr) {
                if (fVar != null) {
                    hVar.a((f) fVar);
                }
            }
        }

        public void a(long j, long j2) {
            long j3 = this.l;
            tw1 tw1Var = this.m;
            long j4 = j - j2;
            if (j4 == 0) {
                if (j3 == 0 || tw1Var == null) {
                    return;
                }
                this.l = 0L;
                tw1Var.request(j3);
                return;
            }
            this.k = j;
            if (tw1Var == null) {
                long j5 = j3 + j4;
                if (j5 < 0) {
                    j5 = Long.MAX_VALUE;
                }
                this.l = j5;
                return;
            }
            if (j3 != 0) {
                this.l = 0L;
                tw1Var.request(j3 + j4);
            } else {
                tw1Var.request(j4);
            }
        }
    }

    /* JADX INFO: compiled from: OperatorReplay.java */
    public static final class j<T> extends e<T> {
        public final uw1 d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final long f8742e;
        public final int f;

        public j(int i, long j, uw1 uw1Var) {
            this.d = uw1Var;
            this.f = i;
            this.f8742e = j;
        }

        @Override // supwisdom.ox1.e
        public Object b(Object obj) {
            return new qz1(this.d.b(), obj);
        }

        @Override // supwisdom.ox1.e
        public Object c(Object obj) {
            return ((qz1) obj).b();
        }

        @Override // supwisdom.ox1.e
        public void d() {
            g gVar;
            long jB = this.d.b() - this.f8742e;
            g gVar2 = get();
            g gVar3 = gVar2.get();
            int i = 0;
            while (true) {
                g gVar4 = gVar3;
                gVar = gVar2;
                gVar2 = gVar4;
                if (gVar2 != null) {
                    int i2 = this.b;
                    if (i2 <= this.f) {
                        if (((qz1) gVar2.f8738a).a() > jB) {
                            break;
                        }
                        i++;
                        this.b--;
                        gVar3 = gVar2.get();
                    } else {
                        i++;
                        this.b = i2 - 1;
                        gVar3 = gVar2.get();
                    }
                } else {
                    break;
                }
            }
            if (i != 0) {
                b(gVar);
            }
        }

        @Override // supwisdom.ox1.e
        public void e() {
            g gVar;
            long jB = this.d.b() - this.f8742e;
            g gVar2 = get();
            g gVar3 = gVar2.get();
            int i = 0;
            while (true) {
                g gVar4 = gVar3;
                gVar = gVar2;
                gVar2 = gVar4;
                if (gVar2 == null || this.b <= 1 || ((qz1) gVar2.f8738a).a() > jB) {
                    break;
                }
                i++;
                this.b--;
                gVar3 = gVar2.get();
            }
            if (i != 0) {
                b(gVar);
            }
        }

        @Override // supwisdom.ox1.e
        public g b() {
            g gVar;
            long jB = this.d.b() - this.f8742e;
            g gVar2 = get();
            g gVar3 = gVar2.get();
            while (true) {
                g gVar4 = gVar3;
                gVar = gVar2;
                gVar2 = gVar4;
                if (gVar2 == null) {
                    break;
                }
                Object obj = gVar2.f8738a;
                Object objC = c(obj);
                if (ix1.b(objC) || ix1.c(objC) || ((qz1) obj).a() > jB) {
                    break;
                }
                gVar3 = gVar2.get();
            }
            return gVar;
        }
    }

    /* JADX INFO: compiled from: OperatorReplay.java */
    public static final class k<T> extends e<T> {
        public final int d;

        public k(int i) {
            this.d = i;
        }

        @Override // supwisdom.ox1.e
        public void d() {
            if (this.b > this.d) {
                c();
            }
        }
    }

    public ox1(rw1.a<T> aVar, rw1<? extends T> rw1Var, AtomicReference<i<T>> atomicReference, ex1<? extends h<T>> ex1Var) {
        super(aVar);
        this.b = rw1Var;
        this.c = atomicReference;
        this.d = ex1Var;
    }

    public static <T> dz1<T> a(rw1<? extends T> rw1Var) {
        return a(rw1Var, f8731e);
    }

    @Override // supwisdom.yw1
    public boolean isUnsubscribed() {
        i<T> iVar = this.c.get();
        return iVar == null || iVar.isUnsubscribed();
    }

    @Override // supwisdom.yw1
    public void unsubscribe() {
        this.c.lazySet(null);
    }

    /* JADX INFO: compiled from: OperatorReplay.java */
    public static final class f<T> extends AtomicLong implements tw1, yw1 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final i<T> f8736a;
        public xw1<? super T> b;
        public Object c;
        public final AtomicLong d = new AtomicLong();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f8737e;
        public boolean f;

        public f(i<T> iVar, xw1<? super T> xw1Var) {
            this.f8736a = iVar;
            this.b = xw1Var;
        }

        public void a(long j) {
            long j2;
            long j3;
            do {
                j2 = this.d.get();
                j3 = j2 + j;
                if (j3 < 0) {
                    j3 = Long.MAX_VALUE;
                }
            } while (!this.d.compareAndSet(j2, j3));
        }

        public long b(long j) {
            long j2;
            long j3;
            if (j <= 0) {
                throw new IllegalArgumentException("Cant produce zero or less");
            }
            do {
                j2 = get();
                if (j2 == Long.MIN_VALUE) {
                    return Long.MIN_VALUE;
                }
                j3 = j2 - j;
                if (j3 < 0) {
                    throw new IllegalStateException("More produced (" + j + ") than requested (" + j2 + ")");
                }
            } while (!compareAndSet(j2, j3));
            return j3;
        }

        @Override // supwisdom.yw1
        public boolean isUnsubscribed() {
            return get() == Long.MIN_VALUE;
        }

        @Override // supwisdom.tw1
        public void request(long j) {
            long j2;
            long j3;
            if (j < 0) {
                return;
            }
            do {
                j2 = get();
                if (j2 == Long.MIN_VALUE) {
                    return;
                }
                if (j2 >= 0 && j == 0) {
                    return;
                }
                j3 = j2 + j;
                if (j3 < 0) {
                    j3 = Long.MAX_VALUE;
                }
            } while (!compareAndSet(j2, j3));
            a(j);
            this.f8736a.b(this);
            this.f8736a.f8739a.a((f) this);
        }

        @Override // supwisdom.yw1
        public void unsubscribe() {
            if (get() == Long.MIN_VALUE || getAndSet(Long.MIN_VALUE) == Long.MIN_VALUE) {
                return;
            }
            this.f8736a.c(this);
            this.f8736a.b(this);
            this.b = null;
        }

        public <U> U a() {
            return (U) this.c;
        }
    }

    /* JADX INFO: compiled from: OperatorReplay.java */
    public static final class l<T> extends ArrayList<Object> implements h<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public volatile int f8743a;

        public l(int i) {
            super(i);
        }

        @Override // supwisdom.ox1.h
        public void a(T t) {
            add(ix1.d(t));
            this.f8743a++;
        }

        @Override // supwisdom.ox1.h
        public void a(Throwable th) {
            add(ix1.a(th));
            this.f8743a++;
        }

        @Override // supwisdom.ox1.h
        public void a() {
            add(ix1.a());
            this.f8743a++;
        }

        @Override // supwisdom.ox1.h
        public void a(f<T> fVar) {
            synchronized (fVar) {
                if (fVar.f8737e) {
                    fVar.f = true;
                    return;
                }
                fVar.f8737e = true;
                while (!fVar.isUnsubscribed()) {
                    int i = this.f8743a;
                    Integer num = (Integer) fVar.a();
                    int iIntValue = num != null ? num.intValue() : 0;
                    xw1<? super T> xw1Var = fVar.b;
                    if (xw1Var == null) {
                        return;
                    }
                    long j = fVar.get();
                    long j2 = 0;
                    while (j2 != j && iIntValue < i) {
                        Object obj = get(iIntValue);
                        try {
                            if (ix1.a(xw1Var, obj) || fVar.isUnsubscribed()) {
                                return;
                            }
                            iIntValue++;
                            j2++;
                        } catch (Throwable th) {
                            zw1.b(th);
                            fVar.unsubscribe();
                            if (ix1.c(obj) || ix1.b(obj)) {
                                return;
                            }
                            xw1Var.onError(OnErrorThrowable.addValueAsLastCause(th, ix1.a(obj)));
                            return;
                        }
                    }
                    if (j2 != 0) {
                        fVar.c = Integer.valueOf(iIntValue);
                        if (j != Long.MAX_VALUE) {
                            fVar.b(j2);
                        }
                    }
                    synchronized (fVar) {
                        if (!fVar.f) {
                            fVar.f8737e = false;
                            return;
                        }
                        fVar.f = false;
                    }
                }
            }
        }
    }

    public static <T> dz1<T> a(rw1<? extends T> rw1Var, int i2) {
        return i2 == Integer.MAX_VALUE ? a(rw1Var) : a(rw1Var, new b(i2));
    }

    public static <T> dz1<T> a(rw1<? extends T> rw1Var, long j2, TimeUnit timeUnit, uw1 uw1Var) {
        return a(rw1Var, j2, timeUnit, uw1Var, Integer.MAX_VALUE);
    }

    public static <T> dz1<T> a(rw1<? extends T> rw1Var, long j2, TimeUnit timeUnit, uw1 uw1Var, int i2) {
        return a(rw1Var, new c(i2, timeUnit.toMillis(j2), uw1Var));
    }

    public static <T> dz1<T> a(rw1<? extends T> rw1Var, ex1<? extends h<T>> ex1Var) {
        AtomicReference atomicReference = new AtomicReference();
        return new ox1(new d(atomicReference, ex1Var), rw1Var, atomicReference, ex1Var);
    }

    @Override // supwisdom.dz1
    public void a(bx1<? super yw1> bx1Var) {
        i<T> iVar;
        while (true) {
            iVar = this.c.get();
            if (iVar != null && !iVar.isUnsubscribed()) {
                break;
            }
            i<T> iVar2 = new i<>(this.d.call());
            iVar2.b();
            if (this.c.compareAndSet(iVar, iVar2)) {
                iVar = iVar2;
                break;
            }
        }
        boolean z = !iVar.h.get() && iVar.h.compareAndSet(false, true);
        bx1Var.call(iVar);
        if (z) {
            this.b.b(iVar);
        }
    }
}
