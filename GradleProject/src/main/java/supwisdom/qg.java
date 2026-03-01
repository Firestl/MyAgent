package supwisdom;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.transition.AutoTransition;
import androidx.transition.Transition;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: TransitionManager.java */
/* JADX INFO: loaded from: classes.dex */
public class qg {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Transition f8914a = new AutoTransition();
    public static ThreadLocal<WeakReference<j4<ViewGroup, ArrayList<Transition>>>> b = new ThreadLocal<>();
    public static ArrayList<ViewGroup> c = new ArrayList<>();

    /* JADX INFO: compiled from: TransitionManager.java */
    public static class a implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Transition f8915a;
        public ViewGroup b;

        /* JADX INFO: renamed from: supwisdom.qg$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: TransitionManager.java */
        public class C0229a extends pg {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ j4 f8916a;

            public C0229a(j4 j4Var) {
                this.f8916a = j4Var;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.transition.Transition.f
            public void c(Transition transition) {
                ((ArrayList) this.f8916a.get(a.this.b)).remove(transition);
            }
        }

        public a(Transition transition, ViewGroup viewGroup) {
            this.f8915a = transition;
            this.b = viewGroup;
        }

        public final void a() {
            this.b.getViewTreeObserver().removeOnPreDrawListener(this);
            this.b.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            a();
            if (!qg.c.remove(this.b)) {
                return true;
            }
            j4<ViewGroup, ArrayList<Transition>> j4VarA = qg.a();
            ArrayList<Transition> arrayList = j4VarA.get(this.b);
            ArrayList arrayList2 = null;
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                j4VarA.put(this.b, arrayList);
            } else if (arrayList.size() > 0) {
                arrayList2 = new ArrayList(arrayList);
            }
            arrayList.add(this.f8915a);
            this.f8915a.a(new C0229a(j4VarA));
            this.f8915a.a(this.b, false);
            if (arrayList2 != null) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((Transition) it.next()).e(this.b);
                }
            }
            this.f8915a.a(this.b);
            return true;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            a();
            qg.c.remove(this.b);
            ArrayList<Transition> arrayList = qg.a().get(this.b);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator<Transition> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().e(this.b);
                }
            }
            this.f8915a.a(true);
        }
    }

    public static j4<ViewGroup, ArrayList<Transition>> a() {
        j4<ViewGroup, ArrayList<Transition>> j4Var;
        WeakReference<j4<ViewGroup, ArrayList<Transition>>> weakReference = b.get();
        if (weakReference != null && (j4Var = weakReference.get()) != null) {
            return j4Var;
        }
        j4<ViewGroup, ArrayList<Transition>> j4Var2 = new j4<>();
        b.set(new WeakReference<>(j4Var2));
        return j4Var2;
    }

    public static void b(ViewGroup viewGroup, Transition transition) {
        if (transition == null || viewGroup == null) {
            return;
        }
        a aVar = new a(transition, viewGroup);
        viewGroup.addOnAttachStateChangeListener(aVar);
        viewGroup.getViewTreeObserver().addOnPreDrawListener(aVar);
    }

    public static void c(ViewGroup viewGroup, Transition transition) {
        ArrayList<Transition> arrayList = a().get(viewGroup);
        if (arrayList != null && arrayList.size() > 0) {
            Iterator<Transition> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().c(viewGroup);
            }
        }
        if (transition != null) {
            transition.a(viewGroup, true);
        }
        mg mgVarA = mg.a(viewGroup);
        if (mgVarA != null) {
            mgVarA.a();
        }
    }

    public static void a(ViewGroup viewGroup, Transition transition) {
        if (c.contains(viewGroup) || !lb.M(viewGroup)) {
            return;
        }
        c.add(viewGroup);
        if (transition == null) {
            transition = f8914a;
        }
        Transition transitionMo0clone = transition.mo0clone();
        c(viewGroup, transitionMo0clone);
        mg.a(viewGroup, null);
        b(viewGroup, transitionMo0clone);
    }
}
