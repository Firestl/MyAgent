package supwisdom;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.Transition;
import androidx.transition.TransitionSet;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: FragmentTransitionSupport.java */
/* JADX INFO: loaded from: classes.dex */
public class bg extends kd {

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    public class a extends Transition.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Rect f7064a;

        public a(bg bgVar, Rect rect) {
            this.f7064a = rect;
        }

        @Override // androidx.transition.Transition.e
        public Rect a(Transition transition) {
            return this.f7064a;
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    public class b implements Transition.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ View f7065a;
        public final /* synthetic */ ArrayList b;

        public b(bg bgVar, View view, ArrayList arrayList) {
            this.f7065a = view;
            this.b = arrayList;
        }

        @Override // androidx.transition.Transition.f
        public void a(Transition transition) {
        }

        @Override // androidx.transition.Transition.f
        public void b(Transition transition) {
        }

        @Override // androidx.transition.Transition.f
        public void c(Transition transition) {
            transition.b(this);
            this.f7065a.setVisibility(8);
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                ((View) this.b.get(i)).setVisibility(0);
            }
        }

        @Override // androidx.transition.Transition.f
        public void d(Transition transition) {
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    public class c implements Transition.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Object f7066a;
        public final /* synthetic */ ArrayList b;
        public final /* synthetic */ Object c;
        public final /* synthetic */ ArrayList d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ Object f7067e;
        public final /* synthetic */ ArrayList f;

        public c(Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2, Object obj3, ArrayList arrayList3) {
            this.f7066a = obj;
            this.b = arrayList;
            this.c = obj2;
            this.d = arrayList2;
            this.f7067e = obj3;
            this.f = arrayList3;
        }

        @Override // androidx.transition.Transition.f
        public void a(Transition transition) {
            Object obj = this.f7066a;
            if (obj != null) {
                bg.this.a(obj, this.b, (ArrayList<View>) null);
            }
            Object obj2 = this.c;
            if (obj2 != null) {
                bg.this.a(obj2, this.d, (ArrayList<View>) null);
            }
            Object obj3 = this.f7067e;
            if (obj3 != null) {
                bg.this.a(obj3, this.f, (ArrayList<View>) null);
            }
        }

        @Override // androidx.transition.Transition.f
        public void b(Transition transition) {
        }

        @Override // androidx.transition.Transition.f
        public void c(Transition transition) {
        }

        @Override // androidx.transition.Transition.f
        public void d(Transition transition) {
        }
    }

    /* JADX INFO: compiled from: FragmentTransitionSupport.java */
    public class d extends Transition.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Rect f7068a;

        public d(bg bgVar, Rect rect) {
            this.f7068a = rect;
        }

        @Override // androidx.transition.Transition.e
        public Rect a(Transition transition) {
            Rect rect = this.f7068a;
            if (rect == null || rect.isEmpty()) {
                return null;
            }
            return this.f7068a;
        }
    }

    @Override // supwisdom.kd
    public boolean a(Object obj) {
        return obj instanceof Transition;
    }

    @Override // supwisdom.kd
    public Object b(Object obj) {
        if (obj != null) {
            return ((Transition) obj).mo0clone();
        }
        return null;
    }

    @Override // supwisdom.kd
    public Object c(Object obj) {
        if (obj == null) {
            return null;
        }
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.a((Transition) obj);
        return transitionSet;
    }

    @Override // supwisdom.kd
    public void a(Object obj, ArrayList<View> arrayList) {
        Transition transition = (Transition) obj;
        if (transition == null) {
            return;
        }
        int i = 0;
        if (transition instanceof TransitionSet) {
            TransitionSet transitionSet = (TransitionSet) transition;
            int iR = transitionSet.r();
            while (i < iR) {
                a(transitionSet.b(i), arrayList);
                i++;
            }
            return;
        }
        if (a(transition) || !kd.a((List) transition.m())) {
            return;
        }
        int size = arrayList.size();
        while (i < size) {
            transition.a(arrayList.get(i));
            i++;
        }
    }

    @Override // supwisdom.kd
    public void b(Object obj, View view, ArrayList<View> arrayList) {
        TransitionSet transitionSet = (TransitionSet) obj;
        List<View> listM = transitionSet.m();
        listM.clear();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            kd.a(listM, arrayList.get(i));
        }
        listM.add(view);
        arrayList.add(view);
        a(transitionSet, arrayList);
    }

    @Override // supwisdom.kd
    public void c(Object obj, View view) {
        if (view != null) {
            Rect rect = new Rect();
            a(view, rect);
            ((Transition) obj).a(new a(this, rect));
        }
    }

    @Override // supwisdom.kd
    public Object b(Object obj, Object obj2, Object obj3) {
        TransitionSet transitionSet = new TransitionSet();
        if (obj != null) {
            transitionSet.a((Transition) obj);
        }
        if (obj2 != null) {
            transitionSet.a((Transition) obj2);
        }
        if (obj3 != null) {
            transitionSet.a((Transition) obj3);
        }
        return transitionSet;
    }

    public static boolean a(Transition transition) {
        return (kd.a((List) transition.j()) && kd.a((List) transition.k()) && kd.a((List) transition.l())) ? false : true;
    }

    @Override // supwisdom.kd
    public void b(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        TransitionSet transitionSet = (TransitionSet) obj;
        if (transitionSet != null) {
            transitionSet.m().clear();
            transitionSet.m().addAll(arrayList2);
            a((Object) transitionSet, arrayList, arrayList2);
        }
    }

    @Override // supwisdom.kd
    public void a(Object obj, View view, ArrayList<View> arrayList) {
        ((Transition) obj).a(new b(this, view, arrayList));
    }

    @Override // supwisdom.kd
    public Object a(Object obj, Object obj2, Object obj3) {
        Transition transition = (Transition) obj;
        Transition transition2 = (Transition) obj2;
        Transition transition3 = (Transition) obj3;
        if (transition != null && transition2 != null) {
            TransitionSet transitionSet = new TransitionSet();
            transitionSet.a(transition);
            transitionSet.a(transition2);
            transitionSet.c(1);
            transition = transitionSet;
        } else if (transition == null) {
            transition = transition2 != null ? transition2 : null;
        }
        if (transition3 == null) {
            return transition;
        }
        TransitionSet transitionSet2 = new TransitionSet();
        if (transition != null) {
            transitionSet2.a(transition);
        }
        transitionSet2.a(transition3);
        return transitionSet2;
    }

    @Override // supwisdom.kd
    public void b(Object obj, View view) {
        if (obj != null) {
            ((Transition) obj).d(view);
        }
    }

    @Override // supwisdom.kd
    public void a(ViewGroup viewGroup, Object obj) {
        qg.a(viewGroup, (Transition) obj);
    }

    @Override // supwisdom.kd
    public void a(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3) {
        ((Transition) obj).a(new c(obj2, arrayList, obj3, arrayList2, obj4, arrayList3));
    }

    @Override // supwisdom.kd
    public void a(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        Transition transition = (Transition) obj;
        int i = 0;
        if (transition instanceof TransitionSet) {
            TransitionSet transitionSet = (TransitionSet) transition;
            int iR = transitionSet.r();
            while (i < iR) {
                a((Object) transitionSet.b(i), arrayList, arrayList2);
                i++;
            }
            return;
        }
        if (a(transition)) {
            return;
        }
        List<View> listM = transition.m();
        if (listM.size() == arrayList.size() && listM.containsAll(arrayList)) {
            int size = arrayList2 == null ? 0 : arrayList2.size();
            while (i < size) {
                transition.a(arrayList2.get(i));
                i++;
            }
            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                transition.d(arrayList.get(size2));
            }
        }
    }

    @Override // supwisdom.kd
    public void a(Object obj, View view) {
        if (obj != null) {
            ((Transition) obj).a(view);
        }
    }

    @Override // supwisdom.kd
    public void a(Object obj, Rect rect) {
        if (obj != null) {
            ((Transition) obj).a(new d(this, rect));
        }
    }
}
