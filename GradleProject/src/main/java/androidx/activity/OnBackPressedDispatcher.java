package androidx.activity;

import androidx.lifecycle.Lifecycle;
import java.util.ArrayDeque;
import java.util.Iterator;
import supwisdom.n0;
import supwisdom.o0;
import supwisdom.vd;
import supwisdom.xd;

/* JADX INFO: loaded from: classes.dex */
public final class OnBackPressedDispatcher {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Runnable f1078a;
    public final ArrayDeque<o0> b = new ArrayDeque<>();

    public class LifecycleOnBackPressedCancellable implements vd, n0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Lifecycle f1079a;
        public final o0 b;
        public n0 c;

        public LifecycleOnBackPressedCancellable(Lifecycle lifecycle, o0 o0Var) {
            this.f1079a = lifecycle;
            this.b = o0Var;
            lifecycle.a(this);
        }

        @Override // supwisdom.vd
        public void a(xd xdVar, Lifecycle.Event event) {
            if (event == Lifecycle.Event.ON_START) {
                this.c = OnBackPressedDispatcher.this.a(this.b);
                return;
            }
            if (event != Lifecycle.Event.ON_STOP) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    cancel();
                }
            } else {
                n0 n0Var = this.c;
                if (n0Var != null) {
                    n0Var.cancel();
                }
            }
        }

        @Override // supwisdom.n0
        public void cancel() {
            this.f1079a.b(this);
            this.b.b(this);
            n0 n0Var = this.c;
            if (n0Var != null) {
                n0Var.cancel();
                this.c = null;
            }
        }
    }

    public class a implements n0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final o0 f1080a;

        public a(o0 o0Var) {
            this.f1080a = o0Var;
        }

        @Override // supwisdom.n0
        public void cancel() {
            OnBackPressedDispatcher.this.b.remove(this.f1080a);
            this.f1080a.b(this);
        }
    }

    public OnBackPressedDispatcher(Runnable runnable) {
        this.f1078a = runnable;
    }

    public n0 a(o0 o0Var) {
        this.b.add(o0Var);
        a aVar = new a(o0Var);
        o0Var.a(aVar);
        return aVar;
    }

    public void a(xd xdVar, o0 o0Var) {
        Lifecycle lifecycle = xdVar.getLifecycle();
        if (lifecycle.a() == Lifecycle.State.DESTROYED) {
            return;
        }
        o0Var.a(new LifecycleOnBackPressedCancellable(lifecycle, o0Var));
    }

    public void a() {
        Iterator<o0> itDescendingIterator = this.b.descendingIterator();
        while (itDescendingIterator.hasNext()) {
            o0 next = itDescendingIterator.next();
            if (next.b()) {
                next.a();
                return;
            }
        }
        Runnable runnable = this.f1078a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
