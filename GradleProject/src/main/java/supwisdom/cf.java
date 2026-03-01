package supwisdom;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.RecyclerView;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: DefaultItemAnimator.java */
/* JADX INFO: loaded from: classes.dex */
public class cf extends qf {
    public static TimeInterpolator s;
    public ArrayList<RecyclerView.b0> h = new ArrayList<>();
    public ArrayList<RecyclerView.b0> i = new ArrayList<>();
    public ArrayList<j> j = new ArrayList<>();
    public ArrayList<i> k = new ArrayList<>();
    public ArrayList<ArrayList<RecyclerView.b0>> l = new ArrayList<>();
    public ArrayList<ArrayList<j>> m = new ArrayList<>();
    public ArrayList<ArrayList<i>> n = new ArrayList<>();
    public ArrayList<RecyclerView.b0> o = new ArrayList<>();
    public ArrayList<RecyclerView.b0> p = new ArrayList<>();
    public ArrayList<RecyclerView.b0> q = new ArrayList<>();
    public ArrayList<RecyclerView.b0> r = new ArrayList<>();

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ArrayList f7190a;

        public a(ArrayList arrayList) {
            this.f7190a = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (j jVar : this.f7190a) {
                cf.this.b(jVar.f7201a, jVar.b, jVar.c, jVar.d, jVar.f7202e);
            }
            this.f7190a.clear();
            cf.this.m.remove(this.f7190a);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ArrayList f7191a;

        public b(ArrayList arrayList) {
            this.f7191a = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f7191a.iterator();
            while (it.hasNext()) {
                cf.this.a((i) it.next());
            }
            this.f7191a.clear();
            cf.this.n.remove(this.f7191a);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ArrayList f7192a;

        public c(ArrayList arrayList) {
            this.f7192a = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = this.f7192a.iterator();
            while (it.hasNext()) {
                cf.this.t((RecyclerView.b0) it.next());
            }
            this.f7192a.clear();
            cf.this.l.remove(this.f7192a);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class d extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ RecyclerView.b0 f7193a;
        public final /* synthetic */ ViewPropertyAnimator b;
        public final /* synthetic */ View c;

        public d(RecyclerView.b0 b0Var, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f7193a = b0Var;
            this.b = viewPropertyAnimator;
            this.c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.b.setListener(null);
            this.c.setAlpha(1.0f);
            cf.this.l(this.f7193a);
            cf.this.q.remove(this.f7193a);
            cf.this.j();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            cf.this.m(this.f7193a);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class e extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ RecyclerView.b0 f7194a;
        public final /* synthetic */ View b;
        public final /* synthetic */ ViewPropertyAnimator c;

        public e(RecyclerView.b0 b0Var, View view, ViewPropertyAnimator viewPropertyAnimator) {
            this.f7194a = b0Var;
            this.b = view;
            this.c = viewPropertyAnimator;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.b.setAlpha(1.0f);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.c.setListener(null);
            cf.this.h(this.f7194a);
            cf.this.o.remove(this.f7194a);
            cf.this.j();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            cf.this.i(this.f7194a);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class f extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ RecyclerView.b0 f7195a;
        public final /* synthetic */ int b;
        public final /* synthetic */ View c;
        public final /* synthetic */ int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ ViewPropertyAnimator f7196e;

        public f(RecyclerView.b0 b0Var, int i, View view, int i2, ViewPropertyAnimator viewPropertyAnimator) {
            this.f7195a = b0Var;
            this.b = i;
            this.c = view;
            this.d = i2;
            this.f7196e = viewPropertyAnimator;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            if (this.b != 0) {
                this.c.setTranslationX(0.0f);
            }
            if (this.d != 0) {
                this.c.setTranslationY(0.0f);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f7196e.setListener(null);
            cf.this.j(this.f7195a);
            cf.this.p.remove(this.f7195a);
            cf.this.j();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            cf.this.k(this.f7195a);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class g extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ i f7197a;
        public final /* synthetic */ ViewPropertyAnimator b;
        public final /* synthetic */ View c;

        public g(i iVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f7197a = iVar;
            this.b = viewPropertyAnimator;
            this.c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.b.setListener(null);
            this.c.setAlpha(1.0f);
            this.c.setTranslationX(0.0f);
            this.c.setTranslationY(0.0f);
            cf.this.a(this.f7197a.f7199a, true);
            cf.this.r.remove(this.f7197a.f7199a);
            cf.this.j();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            cf.this.b(this.f7197a.f7199a, true);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public class h extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ i f7198a;
        public final /* synthetic */ ViewPropertyAnimator b;
        public final /* synthetic */ View c;

        public h(i iVar, ViewPropertyAnimator viewPropertyAnimator, View view) {
            this.f7198a = iVar;
            this.b = viewPropertyAnimator;
            this.c = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.b.setListener(null);
            this.c.setAlpha(1.0f);
            this.c.setTranslationX(0.0f);
            this.c.setTranslationY(0.0f);
            cf.this.a(this.f7198a.b, false);
            cf.this.r.remove(this.f7198a.b);
            cf.this.j();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            cf.this.b(this.f7198a.b, false);
        }
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public static class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public RecyclerView.b0 f7201a;
        public int b;
        public int c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f7202e;

        public j(RecyclerView.b0 b0Var, int i, int i2, int i3, int i4) {
            this.f7201a = b0Var;
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.f7202e = i4;
        }
    }

    @Override // supwisdom.qf
    public boolean a(RecyclerView.b0 b0Var, int i2, int i3, int i4, int i5) {
        View view = b0Var.itemView;
        int translationX = i2 + ((int) view.getTranslationX());
        int translationY = i3 + ((int) b0Var.itemView.getTranslationY());
        v(b0Var);
        int i6 = i4 - translationX;
        int i7 = i5 - translationY;
        if (i6 == 0 && i7 == 0) {
            j(b0Var);
            return false;
        }
        if (i6 != 0) {
            view.setTranslationX(-i6);
        }
        if (i7 != 0) {
            view.setTranslationY(-i7);
        }
        this.j.add(new j(b0Var, translationX, translationY, i4, i5));
        return true;
    }

    public void b(RecyclerView.b0 b0Var, int i2, int i3, int i4, int i5) {
        View view = b0Var.itemView;
        int i6 = i4 - i2;
        int i7 = i5 - i3;
        if (i6 != 0) {
            view.animate().translationX(0.0f);
        }
        if (i7 != 0) {
            view.animate().translationY(0.0f);
        }
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.p.add(b0Var);
        viewPropertyAnimatorAnimate.setDuration(e()).setListener(new f(b0Var, i6, view, i7, viewPropertyAnimatorAnimate)).start();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void c(RecyclerView.b0 b0Var) {
        View view = b0Var.itemView;
        view.animate().cancel();
        int size = this.j.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (this.j.get(size).f7201a == b0Var) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                j(b0Var);
                this.j.remove(size);
            }
        }
        a(this.k, b0Var);
        if (this.h.remove(b0Var)) {
            view.setAlpha(1.0f);
            l(b0Var);
        }
        if (this.i.remove(b0Var)) {
            view.setAlpha(1.0f);
            h(b0Var);
        }
        for (int size2 = this.n.size() - 1; size2 >= 0; size2--) {
            ArrayList<i> arrayList = this.n.get(size2);
            a(arrayList, b0Var);
            if (arrayList.isEmpty()) {
                this.n.remove(size2);
            }
        }
        for (int size3 = this.m.size() - 1; size3 >= 0; size3--) {
            ArrayList<j> arrayList2 = this.m.get(size3);
            int size4 = arrayList2.size() - 1;
            while (true) {
                if (size4 < 0) {
                    break;
                }
                if (arrayList2.get(size4).f7201a == b0Var) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    j(b0Var);
                    arrayList2.remove(size4);
                    if (arrayList2.isEmpty()) {
                        this.m.remove(size3);
                    }
                } else {
                    size4--;
                }
            }
        }
        for (int size5 = this.l.size() - 1; size5 >= 0; size5--) {
            ArrayList<RecyclerView.b0> arrayList3 = this.l.get(size5);
            if (arrayList3.remove(b0Var)) {
                view.setAlpha(1.0f);
                h(b0Var);
                if (arrayList3.isEmpty()) {
                    this.l.remove(size5);
                }
            }
        }
        this.q.remove(b0Var);
        this.o.remove(b0Var);
        this.r.remove(b0Var);
        this.p.remove(b0Var);
        j();
    }

    @Override // supwisdom.qf
    public boolean f(RecyclerView.b0 b0Var) {
        v(b0Var);
        b0Var.itemView.setAlpha(0.0f);
        this.i.add(b0Var);
        return true;
    }

    @Override // supwisdom.qf
    public boolean g(RecyclerView.b0 b0Var) {
        v(b0Var);
        this.h.add(b0Var);
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void i() {
        boolean z = !this.h.isEmpty();
        boolean z2 = !this.j.isEmpty();
        boolean z3 = !this.k.isEmpty();
        boolean z4 = !this.i.isEmpty();
        if (z || z2 || z4 || z3) {
            Iterator<RecyclerView.b0> it = this.h.iterator();
            while (it.hasNext()) {
                u(it.next());
            }
            this.h.clear();
            if (z2) {
                ArrayList<j> arrayList = new ArrayList<>();
                arrayList.addAll(this.j);
                this.m.add(arrayList);
                this.j.clear();
                a aVar = new a(arrayList);
                if (z) {
                    lb.a(arrayList.get(0).f7201a.itemView, aVar, f());
                } else {
                    aVar.run();
                }
            }
            if (z3) {
                ArrayList<i> arrayList2 = new ArrayList<>();
                arrayList2.addAll(this.k);
                this.n.add(arrayList2);
                this.k.clear();
                b bVar = new b(arrayList2);
                if (z) {
                    lb.a(arrayList2.get(0).f7199a.itemView, bVar, f());
                } else {
                    bVar.run();
                }
            }
            if (z4) {
                ArrayList<RecyclerView.b0> arrayList3 = new ArrayList<>();
                arrayList3.addAll(this.i);
                this.l.add(arrayList3);
                this.i.clear();
                c cVar = new c(arrayList3);
                if (z || z2 || z3) {
                    lb.a(arrayList3.get(0).itemView, cVar, (z ? f() : 0L) + Math.max(z2 ? e() : 0L, z3 ? d() : 0L));
                } else {
                    cVar.run();
                }
            }
        }
    }

    public void j() {
        if (g()) {
            return;
        }
        a();
    }

    public void t(RecyclerView.b0 b0Var) {
        View view = b0Var.itemView;
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.o.add(b0Var);
        viewPropertyAnimatorAnimate.alpha(1.0f).setDuration(c()).setListener(new e(b0Var, view, viewPropertyAnimatorAnimate)).start();
    }

    public final void u(RecyclerView.b0 b0Var) {
        View view = b0Var.itemView;
        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
        this.q.add(b0Var);
        viewPropertyAnimatorAnimate.setDuration(f()).alpha(0.0f).setListener(new d(b0Var, viewPropertyAnimatorAnimate, view)).start();
    }

    public final void v(RecyclerView.b0 b0Var) {
        if (s == null) {
            s = new ValueAnimator().getInterpolator();
        }
        b0Var.itemView.animate().setInterpolator(s);
        c(b0Var);
    }

    /* JADX INFO: compiled from: DefaultItemAnimator.java */
    public static class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public RecyclerView.b0 f7199a;
        public RecyclerView.b0 b;
        public int c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f7200e;
        public int f;

        public i(RecyclerView.b0 b0Var, RecyclerView.b0 b0Var2) {
            this.f7199a = b0Var;
            this.b = b0Var2;
        }

        public String toString() {
            return "ChangeInfo{oldHolder=" + this.f7199a + ", newHolder=" + this.b + ", fromX=" + this.c + ", fromY=" + this.d + ", toX=" + this.f7200e + ", toY=" + this.f + Operators.BLOCK_END;
        }

        public i(RecyclerView.b0 b0Var, RecyclerView.b0 b0Var2, int i, int i2, int i3, int i4) {
            this(b0Var, b0Var2);
            this.c = i;
            this.d = i2;
            this.f7200e = i3;
            this.f = i4;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean g() {
        return (this.i.isEmpty() && this.k.isEmpty() && this.j.isEmpty() && this.h.isEmpty() && this.p.isEmpty() && this.q.isEmpty() && this.o.isEmpty() && this.r.isEmpty() && this.m.isEmpty() && this.l.isEmpty() && this.n.isEmpty()) ? false : true;
    }

    public final void b(i iVar) {
        RecyclerView.b0 b0Var = iVar.f7199a;
        if (b0Var != null) {
            a(iVar, b0Var);
        }
        RecyclerView.b0 b0Var2 = iVar.b;
        if (b0Var2 != null) {
            a(iVar, b0Var2);
        }
    }

    @Override // supwisdom.qf
    public boolean a(RecyclerView.b0 b0Var, RecyclerView.b0 b0Var2, int i2, int i3, int i4, int i5) {
        if (b0Var == b0Var2) {
            return a(b0Var, i2, i3, i4, i5);
        }
        float translationX = b0Var.itemView.getTranslationX();
        float translationY = b0Var.itemView.getTranslationY();
        float alpha = b0Var.itemView.getAlpha();
        v(b0Var);
        int i6 = (int) ((i4 - i2) - translationX);
        int i7 = (int) ((i5 - i3) - translationY);
        b0Var.itemView.setTranslationX(translationX);
        b0Var.itemView.setTranslationY(translationY);
        b0Var.itemView.setAlpha(alpha);
        if (b0Var2 != null) {
            v(b0Var2);
            b0Var2.itemView.setTranslationX(-i6);
            b0Var2.itemView.setTranslationY(-i7);
            b0Var2.itemView.setAlpha(0.0f);
        }
        this.k.add(new i(b0Var, b0Var2, i2, i3, i4, i5));
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void b() {
        int size = this.j.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            j jVar = this.j.get(size);
            View view = jVar.f7201a.itemView;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            j(jVar.f7201a);
            this.j.remove(size);
        }
        for (int size2 = this.h.size() - 1; size2 >= 0; size2--) {
            l(this.h.get(size2));
            this.h.remove(size2);
        }
        int size3 = this.i.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            RecyclerView.b0 b0Var = this.i.get(size3);
            b0Var.itemView.setAlpha(1.0f);
            h(b0Var);
            this.i.remove(size3);
        }
        for (int size4 = this.k.size() - 1; size4 >= 0; size4--) {
            b(this.k.get(size4));
        }
        this.k.clear();
        if (g()) {
            for (int size5 = this.m.size() - 1; size5 >= 0; size5--) {
                ArrayList<j> arrayList = this.m.get(size5);
                for (int size6 = arrayList.size() - 1; size6 >= 0; size6--) {
                    j jVar2 = arrayList.get(size6);
                    View view2 = jVar2.f7201a.itemView;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    j(jVar2.f7201a);
                    arrayList.remove(size6);
                    if (arrayList.isEmpty()) {
                        this.m.remove(arrayList);
                    }
                }
            }
            for (int size7 = this.l.size() - 1; size7 >= 0; size7--) {
                ArrayList<RecyclerView.b0> arrayList2 = this.l.get(size7);
                for (int size8 = arrayList2.size() - 1; size8 >= 0; size8--) {
                    RecyclerView.b0 b0Var2 = arrayList2.get(size8);
                    b0Var2.itemView.setAlpha(1.0f);
                    h(b0Var2);
                    arrayList2.remove(size8);
                    if (arrayList2.isEmpty()) {
                        this.l.remove(arrayList2);
                    }
                }
            }
            for (int size9 = this.n.size() - 1; size9 >= 0; size9--) {
                ArrayList<i> arrayList3 = this.n.get(size9);
                for (int size10 = arrayList3.size() - 1; size10 >= 0; size10--) {
                    b(arrayList3.get(size10));
                    if (arrayList3.isEmpty()) {
                        this.n.remove(arrayList3);
                    }
                }
            }
            a(this.q);
            a(this.p);
            a(this.o);
            a(this.r);
            a();
        }
    }

    public void a(i iVar) {
        RecyclerView.b0 b0Var = iVar.f7199a;
        View view = b0Var == null ? null : b0Var.itemView;
        RecyclerView.b0 b0Var2 = iVar.b;
        View view2 = b0Var2 != null ? b0Var2.itemView : null;
        if (view != null) {
            ViewPropertyAnimator duration = view.animate().setDuration(d());
            this.r.add(iVar.f7199a);
            duration.translationX(iVar.f7200e - iVar.c);
            duration.translationY(iVar.f - iVar.d);
            duration.alpha(0.0f).setListener(new g(iVar, duration, view)).start();
        }
        if (view2 != null) {
            ViewPropertyAnimator viewPropertyAnimatorAnimate = view2.animate();
            this.r.add(iVar.b);
            viewPropertyAnimatorAnimate.translationX(0.0f).translationY(0.0f).setDuration(d()).alpha(1.0f).setListener(new h(iVar, viewPropertyAnimatorAnimate, view2)).start();
        }
    }

    public final void a(List<i> list, RecyclerView.b0 b0Var) {
        for (int size = list.size() - 1; size >= 0; size--) {
            i iVar = list.get(size);
            if (a(iVar, b0Var) && iVar.f7199a == null && iVar.b == null) {
                list.remove(iVar);
            }
        }
    }

    public final boolean a(i iVar, RecyclerView.b0 b0Var) {
        boolean z = false;
        if (iVar.b == b0Var) {
            iVar.b = null;
        } else {
            if (iVar.f7199a != b0Var) {
                return false;
            }
            iVar.f7199a = null;
            z = true;
        }
        b0Var.itemView.setAlpha(1.0f);
        b0Var.itemView.setTranslationX(0.0f);
        b0Var.itemView.setTranslationY(0.0f);
        a(b0Var, z);
        return true;
    }

    public void a(List<RecyclerView.b0> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            list.get(size).itemView.animate().cancel();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public boolean a(RecyclerView.b0 b0Var, List<Object> list) {
        return !list.isEmpty() || super.a(b0Var, list);
    }
}
