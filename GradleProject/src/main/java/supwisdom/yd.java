package supwisdom;

import androidx.lifecycle.Lifecycle;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: LifecycleRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class yd extends Lifecycle {
    public final WeakReference<xd> c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a4<wd, b> f9873a = new a4<>();
    public int d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f9874e = false;
    public boolean f = false;
    public ArrayList<Lifecycle.State> g = new ArrayList<>();
    public Lifecycle.State b = Lifecycle.State.INITIALIZED;

    /* JADX INFO: compiled from: LifecycleRegistry.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f9875a;
        public static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[Lifecycle.State.values().length];
            b = iArr;
            try {
                iArr[Lifecycle.State.INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[Lifecycle.State.CREATED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[Lifecycle.State.STARTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[Lifecycle.State.RESUMED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[Lifecycle.State.DESTROYED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[Lifecycle.Event.values().length];
            f9875a = iArr2;
            try {
                iArr2[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9875a[Lifecycle.Event.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f9875a[Lifecycle.Event.ON_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f9875a[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f9875a[Lifecycle.Event.ON_RESUME.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f9875a[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f9875a[Lifecycle.Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    /* JADX INFO: compiled from: LifecycleRegistry.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Lifecycle.State f9876a;
        public vd b;

        public b(wd wdVar, Lifecycle.State state) {
            this.b = ae.a(wdVar);
            this.f9876a = state;
        }

        public void a(xd xdVar, Lifecycle.Event event) {
            Lifecycle.State stateB = yd.b(event);
            this.f9876a = yd.a(this.f9876a, stateB);
            this.b.a(xdVar, event);
            this.f9876a = stateB;
        }
    }

    public yd(xd xdVar) {
        this.c = new WeakReference<>(xdVar);
    }

    public static Lifecycle.Event e(Lifecycle.State state) {
        int i = a.b[state.ordinal()];
        if (i == 1) {
            throw new IllegalArgumentException();
        }
        if (i == 2) {
            return Lifecycle.Event.ON_DESTROY;
        }
        if (i == 3) {
            return Lifecycle.Event.ON_STOP;
        }
        if (i == 4) {
            return Lifecycle.Event.ON_PAUSE;
        }
        if (i == 5) {
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException("Unexpected state value " + state);
    }

    public static Lifecycle.Event f(Lifecycle.State state) {
        int i = a.b[state.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return Lifecycle.Event.ON_START;
            }
            if (i == 3) {
                return Lifecycle.Event.ON_RESUME;
            }
            if (i == 4) {
                throw new IllegalArgumentException();
            }
            if (i != 5) {
                throw new IllegalArgumentException("Unexpected state value " + state);
            }
        }
        return Lifecycle.Event.ON_CREATE;
    }

    @Deprecated
    public void a(Lifecycle.State state) {
        d(state);
    }

    public final void b(Lifecycle.State state) {
        if (this.b == state) {
            return;
        }
        this.b = state;
        if (this.f9874e || this.d != 0) {
            this.f = true;
            return;
        }
        this.f9874e = true;
        d();
        this.f9874e = false;
    }

    public final Lifecycle.State c(wd wdVar) {
        Map.Entry<wd, b> entryB = this.f9873a.b(wdVar);
        Lifecycle.State state = null;
        Lifecycle.State state2 = entryB != null ? entryB.getValue().f9876a : null;
        if (!this.g.isEmpty()) {
            state = this.g.get(r0.size() - 1);
        }
        return a(a(this.b, state2), state);
    }

    public void d(Lifecycle.State state) {
        b(state);
    }

    public void a(Lifecycle.Event event) {
        b(b(event));
    }

    public final void d() {
        xd xdVar = this.c.get();
        if (xdVar == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state.");
        }
        while (!b()) {
            this.f = false;
            if (this.b.compareTo(this.f9873a.b().getValue().f9876a) < 0) {
                a(xdVar);
            }
            Map.Entry<wd, b> entryH = this.f9873a.h();
            if (!this.f && entryH != null && this.b.compareTo(entryH.getValue().f9876a) > 0) {
                b(xdVar);
            }
        }
        this.f = false;
    }

    @Override // androidx.lifecycle.Lifecycle
    public void a(wd wdVar) {
        xd xdVar;
        Lifecycle.State state = this.b;
        Lifecycle.State state2 = Lifecycle.State.DESTROYED;
        if (state != state2) {
            state2 = Lifecycle.State.INITIALIZED;
        }
        b bVar = new b(wdVar, state2);
        if (this.f9873a.b(wdVar, bVar) == null && (xdVar = this.c.get()) != null) {
            boolean z = this.d != 0 || this.f9874e;
            Lifecycle.State stateC = c(wdVar);
            this.d++;
            while (bVar.f9876a.compareTo(stateC) < 0 && this.f9873a.contains(wdVar)) {
                c(bVar.f9876a);
                bVar.a(xdVar, f(bVar.f9876a));
                c();
                stateC = c(wdVar);
            }
            if (!z) {
                d();
            }
            this.d--;
        }
    }

    public final void c() {
        this.g.remove(r0.size() - 1);
    }

    public final void c(Lifecycle.State state) {
        this.g.add(state);
    }

    public final boolean b() {
        if (this.f9873a.size() == 0) {
            return true;
        }
        Lifecycle.State state = this.f9873a.b().getValue().f9876a;
        Lifecycle.State state2 = this.f9873a.h().getValue().f9876a;
        return state == state2 && this.b == state2;
    }

    @Override // androidx.lifecycle.Lifecycle
    public void b(wd wdVar) {
        this.f9873a.remove(wdVar);
    }

    public static Lifecycle.State b(Lifecycle.Event event) {
        switch (a.f9875a[event.ordinal()]) {
            case 1:
            case 2:
                return Lifecycle.State.CREATED;
            case 3:
            case 4:
                return Lifecycle.State.STARTED;
            case 5:
                return Lifecycle.State.RESUMED;
            case 6:
                return Lifecycle.State.DESTROYED;
            default:
                throw new IllegalArgumentException("Unexpected event value " + event);
        }
    }

    @Override // androidx.lifecycle.Lifecycle
    public Lifecycle.State a() {
        return this.b;
    }

    public final void b(xd xdVar) {
        b4<wd, b>.d dVarG = this.f9873a.g();
        while (dVarG.hasNext() && !this.f) {
            Map.Entry next = dVarG.next();
            b bVar = (b) next.getValue();
            while (bVar.f9876a.compareTo(this.b) < 0 && !this.f && this.f9873a.contains((wd) next.getKey())) {
                c(bVar.f9876a);
                bVar.a(xdVar, f(bVar.f9876a));
                c();
            }
        }
    }

    public final void a(xd xdVar) {
        Iterator<Map.Entry<wd, b>> itA = this.f9873a.a();
        while (itA.hasNext() && !this.f) {
            Map.Entry<wd, b> next = itA.next();
            b value = next.getValue();
            while (value.f9876a.compareTo(this.b) > 0 && !this.f && this.f9873a.contains(next.getKey())) {
                Lifecycle.Event eventE = e(value.f9876a);
                c(b(eventE));
                value.a(xdVar, eventE);
                c();
            }
        }
    }

    public static Lifecycle.State a(Lifecycle.State state, Lifecycle.State state2) {
        return (state2 == null || state2.compareTo(state) >= 0) ? state : state2;
    }
}
