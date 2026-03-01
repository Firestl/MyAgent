package androidx.transition;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.Transition;
import com.bumptech.glide.load.engine.GlideException;
import java.util.ArrayList;
import java.util.Iterator;
import supwisdom.l8;
import supwisdom.og;
import supwisdom.pg;
import supwisdom.rg;
import supwisdom.tg;
import supwisdom.ug;

/* JADX INFO: loaded from: classes.dex */
public class TransitionSet extends Transition {
    public ArrayList<Transition> J;
    public boolean K;
    public int L;
    public boolean M;
    public int N;

    public class a extends pg {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Transition f1439a;

        public a(Transition transition) {
            this.f1439a = transition;
        }

        @Override // androidx.transition.Transition.f
        public void c(Transition transition) {
            this.f1439a.o();
            transition.b(this);
        }
    }

    public static class b extends pg {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public TransitionSet f1440a;

        public b(TransitionSet transitionSet) {
            this.f1440a = transitionSet;
        }

        @Override // supwisdom.pg, androidx.transition.Transition.f
        public void a(Transition transition) {
            TransitionSet transitionSet = this.f1440a;
            if (transitionSet.M) {
                return;
            }
            transitionSet.p();
            this.f1440a.M = true;
        }

        @Override // androidx.transition.Transition.f
        public void c(Transition transition) {
            TransitionSet transitionSet = this.f1440a;
            int i = transitionSet.L - 1;
            transitionSet.L = i;
            if (i == 0) {
                transitionSet.M = false;
                transitionSet.a();
            }
            transition.b(this);
        }
    }

    public TransitionSet() {
        this.J = new ArrayList<>();
        this.K = true;
        this.M = false;
        this.N = 0;
    }

    public TransitionSet c(int i) {
        if (i == 0) {
            this.K = true;
        } else {
            if (i != 1) {
                throw new AndroidRuntimeException("Invalid parameter for TransitionSet ordering: " + i);
            }
            this.K = false;
        }
        return this;
    }

    @Override // androidx.transition.Transition
    public void e(View view) {
        super.e(view);
        int size = this.J.size();
        for (int i = 0; i < size; i++) {
            this.J.get(i).e(view);
        }
    }

    @Override // androidx.transition.Transition
    public void o() {
        if (this.J.isEmpty()) {
            p();
            a();
            return;
        }
        s();
        if (this.K) {
            Iterator<Transition> it = this.J.iterator();
            while (it.hasNext()) {
                it.next().o();
            }
            return;
        }
        for (int i = 1; i < this.J.size(); i++) {
            this.J.get(i - 1).a(new a(this.J.get(i)));
        }
        Transition transition = this.J.get(0);
        if (transition != null) {
            transition.o();
        }
    }

    public int r() {
        return this.J.size();
    }

    public final void s() {
        b bVar = new b(this);
        Iterator<Transition> it = this.J.iterator();
        while (it.hasNext()) {
            it.next().a(bVar);
        }
        this.L = this.J.size();
    }

    @Override // androidx.transition.Transition
    /* JADX INFO: renamed from: clone */
    public Transition mo0clone() {
        TransitionSet transitionSet = (TransitionSet) super.mo0clone();
        transitionSet.J = new ArrayList<>();
        int size = this.J.size();
        for (int i = 0; i < size; i++) {
            transitionSet.a(this.J.get(i).mo0clone());
        }
        return transitionSet;
    }

    @Override // androidx.transition.Transition
    public TransitionSet d(View view) {
        for (int i = 0; i < this.J.size(); i++) {
            this.J.get(i).d(view);
        }
        super.d(view);
        return this;
    }

    @Override // androidx.transition.Transition
    public /* bridge */ /* synthetic */ Transition a(long j) {
        a(j);
        return this;
    }

    public Transition b(int i) {
        if (i < 0 || i >= this.J.size()) {
            return null;
        }
        return this.J.get(i);
    }

    @Override // androidx.transition.Transition
    public void c(tg tgVar) {
        if (b(tgVar.b)) {
            for (Transition transition : this.J) {
                if (transition.b(tgVar.b)) {
                    transition.c(tgVar);
                    tgVar.c.add(transition);
                }
            }
        }
    }

    public TransitionSet a(Transition transition) {
        this.J.add(transition);
        transition.r = this;
        long j = this.c;
        if (j >= 0) {
            transition.a(j);
        }
        if ((this.N & 1) != 0) {
            transition.a(e());
        }
        if ((this.N & 2) != 0) {
            transition.a(h());
        }
        if ((this.N & 4) != 0) {
            transition.a(g());
        }
        if ((this.N & 8) != 0) {
            transition.a(d());
        }
        return this;
    }

    @Override // androidx.transition.Transition
    public TransitionSet b(long j) {
        super.b(j);
        return this;
    }

    public TransitionSet(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.J = new ArrayList<>();
        this.K = true;
        this.M = false;
        this.N = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, og.g);
        c(l8.b(typedArrayObtainStyledAttributes, (XmlResourceParser) attributeSet, "transitionOrdering", 0, 0));
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // androidx.transition.Transition
    public TransitionSet b(Transition.f fVar) {
        super.b(fVar);
        return this;
    }

    @Override // androidx.transition.Transition
    public void b(tg tgVar) {
        super.b(tgVar);
        int size = this.J.size();
        for (int i = 0; i < size; i++) {
            this.J.get(i).b(tgVar);
        }
    }

    @Override // androidx.transition.Transition
    public void c(View view) {
        super.c(view);
        int size = this.J.size();
        for (int i = 0; i < size; i++) {
            this.J.get(i).c(view);
        }
    }

    @Override // androidx.transition.Transition
    public TransitionSet a(long j) {
        super.a(j);
        if (this.c >= 0) {
            int size = this.J.size();
            for (int i = 0; i < size; i++) {
                this.J.get(i).a(j);
            }
        }
        return this;
    }

    @Override // androidx.transition.Transition
    public TransitionSet a(TimeInterpolator timeInterpolator) {
        this.N |= 1;
        ArrayList<Transition> arrayList = this.J;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                this.J.get(i).a(timeInterpolator);
            }
        }
        super.a(timeInterpolator);
        return this;
    }

    @Override // androidx.transition.Transition
    public TransitionSet a(View view) {
        for (int i = 0; i < this.J.size(); i++) {
            this.J.get(i).a(view);
        }
        super.a(view);
        return this;
    }

    @Override // androidx.transition.Transition
    public TransitionSet a(Transition.f fVar) {
        super.a(fVar);
        return this;
    }

    @Override // androidx.transition.Transition
    public void a(PathMotion pathMotion) {
        super.a(pathMotion);
        this.N |= 4;
        for (int i = 0; i < this.J.size(); i++) {
            this.J.get(i).a(pathMotion);
        }
    }

    @Override // androidx.transition.Transition
    public void a(ViewGroup viewGroup, ug ugVar, ug ugVar2, ArrayList<tg> arrayList, ArrayList<tg> arrayList2) {
        long jI = i();
        int size = this.J.size();
        for (int i = 0; i < size; i++) {
            Transition transition = this.J.get(i);
            if (jI > 0 && (this.K || i == 0)) {
                long jI2 = transition.i();
                if (jI2 > 0) {
                    transition.b(jI2 + jI);
                } else {
                    transition.b(jI);
                }
            }
            transition.a(viewGroup, ugVar, ugVar2, arrayList, arrayList2);
        }
    }

    @Override // androidx.transition.Transition
    public void a(tg tgVar) {
        if (b(tgVar.b)) {
            for (Transition transition : this.J) {
                if (transition.b(tgVar.b)) {
                    transition.a(tgVar);
                    tgVar.c.add(transition);
                }
            }
        }
    }

    @Override // androidx.transition.Transition
    public void a(rg rgVar) {
        super.a(rgVar);
        this.N |= 2;
        int size = this.J.size();
        for (int i = 0; i < size; i++) {
            this.J.get(i).a(rgVar);
        }
    }

    @Override // androidx.transition.Transition
    public void a(Transition.e eVar) {
        super.a(eVar);
        this.N |= 8;
        int size = this.J.size();
        for (int i = 0; i < size; i++) {
            this.J.get(i).a(eVar);
        }
    }

    @Override // androidx.transition.Transition
    public String a(String str) {
        String strA = super.a(str);
        for (int i = 0; i < this.J.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(strA);
            sb.append("\n");
            sb.append(this.J.get(i).a(str + GlideException.IndentedAppendable.INDENT));
            strA = sb.toString();
        }
        return strA;
    }
}
