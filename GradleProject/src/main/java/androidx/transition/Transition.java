package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.InflateException;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import com.taobao.weex.ui.component.WXEmbed;
import com.taobao.weex.ui.component.list.template.CellDataManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import supwisdom.fh;
import supwisdom.j4;
import supwisdom.l8;
import supwisdom.lb;
import supwisdom.m4;
import supwisdom.nh;
import supwisdom.og;
import supwisdom.rg;
import supwisdom.tg;
import supwisdom.ug;
import supwisdom.yf;

/* JADX INFO: loaded from: classes.dex */
public abstract class Transition implements Cloneable {
    public static final int[] G = {2, 1, 3, 4};
    public static final PathMotion H = new a();
    public static ThreadLocal<j4<Animator, d>> I = new ThreadLocal<>();
    public rg C;
    public e D;
    public j4<String, String> E;
    public ArrayList<tg> t;
    public ArrayList<tg> u;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f1433a = getClass().getName();
    public long b = -1;
    public long c = -1;
    public TimeInterpolator d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ArrayList<Integer> f1434e = new ArrayList<>();
    public ArrayList<View> f = new ArrayList<>();
    public ArrayList<String> g = null;
    public ArrayList<Class> h = null;
    public ArrayList<Integer> i = null;
    public ArrayList<View> j = null;
    public ArrayList<Class> k = null;
    public ArrayList<String> l = null;
    public ArrayList<Integer> m = null;
    public ArrayList<View> n = null;
    public ArrayList<Class> o = null;
    public ug p = new ug();
    public ug q = new ug();
    public TransitionSet r = null;
    public int[] s = G;
    public boolean v = false;
    public ArrayList<Animator> w = new ArrayList<>();
    public int x = 0;
    public boolean y = false;
    public boolean z = false;
    public ArrayList<f> A = null;
    public ArrayList<Animator> B = new ArrayList<>();
    public PathMotion F = H;

    public static class a extends PathMotion {
        @Override // androidx.transition.PathMotion
        public Path a(float f, float f2, float f3, float f4) {
            Path path = new Path();
            path.moveTo(f, f2);
            path.lineTo(f3, f4);
            return path;
        }
    }

    public class b extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ j4 f1435a;

        public b(j4 j4Var) {
            this.f1435a = j4Var;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1435a.remove(animator);
            Transition.this.w.remove(animator);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            Transition.this.w.add(animator);
        }
    }

    public class c extends AnimatorListenerAdapter {
        public c() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            Transition.this.a();
            animator.removeListener(this);
        }
    }

    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public View f1437a;
        public String b;
        public tg c;
        public nh d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Transition f1438e;

        public d(View view, String str, Transition transition, nh nhVar, tg tgVar) {
            this.f1437a = view;
            this.b = str;
            this.c = tgVar;
            this.d = nhVar;
            this.f1438e = transition;
        }
    }

    public static abstract class e {
        public abstract Rect a(Transition transition);
    }

    public interface f {
        void a(Transition transition);

        void b(Transition transition);

        void c(Transition transition);

        void d(Transition transition);
    }

    public Transition() {
    }

    public static boolean a(int i) {
        return i >= 1 && i <= 4;
    }

    public static int[] b(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        int[] iArr = new int[stringTokenizer.countTokens()];
        int i = 0;
        while (stringTokenizer.hasMoreTokens()) {
            String strTrim = stringTokenizer.nextToken().trim();
            if ("id".equalsIgnoreCase(strTrim)) {
                iArr[i] = 3;
            } else if ("instance".equalsIgnoreCase(strTrim)) {
                iArr[i] = 1;
            } else if ("name".equalsIgnoreCase(strTrim)) {
                iArr[i] = 2;
            } else if (WXEmbed.ITEM_ID.equalsIgnoreCase(strTrim)) {
                iArr[i] = 4;
            } else {
                if (!strTrim.isEmpty()) {
                    throw new InflateException("Unknown match type in matchOrder: '" + strTrim + "'");
                }
                int[] iArr2 = new int[iArr.length - 1];
                System.arraycopy(iArr, 0, iArr2, 0, i);
                i--;
                iArr = iArr2;
            }
            i++;
        }
        return iArr;
    }

    public static j4<Animator, d> q() {
        j4<Animator, d> j4Var = I.get();
        if (j4Var != null) {
            return j4Var;
        }
        j4<Animator, d> j4Var2 = new j4<>();
        I.set(j4Var2);
        return j4Var2;
    }

    public Animator a(ViewGroup viewGroup, tg tgVar, tg tgVar2) {
        return null;
    }

    public Transition a(long j) {
        this.c = j;
        return this;
    }

    public abstract void a(tg tgVar);

    public tg c(View view, boolean z) {
        TransitionSet transitionSet = this.r;
        if (transitionSet != null) {
            return transitionSet.c(view, z);
        }
        return (z ? this.p : this.q).f9409a.get(view);
    }

    public abstract void c(tg tgVar);

    public Transition d(View view) {
        this.f.remove(view);
        return this;
    }

    public TimeInterpolator e() {
        return this.d;
    }

    public String f() {
        return this.f1433a;
    }

    public PathMotion g() {
        return this.F;
    }

    public rg h() {
        return this.C;
    }

    public long i() {
        return this.b;
    }

    public List<Integer> j() {
        return this.f1434e;
    }

    public List<String> k() {
        return this.g;
    }

    public List<Class> l() {
        return this.h;
    }

    public List<View> m() {
        return this.f;
    }

    public String[] n() {
        return null;
    }

    public void o() {
        p();
        j4<Animator, d> j4VarQ = q();
        for (Animator animator : this.B) {
            if (j4VarQ.containsKey(animator)) {
                p();
                a(animator, j4VarQ);
            }
        }
        this.B.clear();
        a();
    }

    public void p() {
        if (this.x == 0) {
            ArrayList<f> arrayList = this.A;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.A.clone();
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    ((f) arrayList2.get(i)).a(this);
                }
            }
            this.z = false;
        }
        this.x++;
    }

    public String toString() {
        return a("");
    }

    public Transition a(TimeInterpolator timeInterpolator) {
        this.d = timeInterpolator;
        return this;
    }

    @Override // 
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public Transition mo0clone() {
        try {
            Transition transition = (Transition) super.clone();
            transition.B = new ArrayList<>();
            transition.p = new ug();
            transition.q = new ug();
            transition.t = null;
            transition.u = null;
            return transition;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public e d() {
        return this.D;
    }

    public void e(View view) {
        if (this.y) {
            if (!this.z) {
                j4<Animator, d> j4VarQ = q();
                int size = j4VarQ.size();
                nh nhVarD = fh.d(view);
                for (int i = size - 1; i >= 0; i--) {
                    d dVarValueAt = j4VarQ.valueAt(i);
                    if (dVarValueAt.f1437a != null && nhVarD.equals(dVarValueAt.d)) {
                        yf.b(j4VarQ.keyAt(i));
                    }
                }
                ArrayList<f> arrayList = this.A;
                if (arrayList != null && arrayList.size() > 0) {
                    ArrayList arrayList2 = (ArrayList) this.A.clone();
                    int size2 = arrayList2.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        ((f) arrayList2.get(i2)).d(this);
                    }
                }
            }
            this.y = false;
        }
    }

    public void a(int... iArr) {
        if (iArr != null && iArr.length != 0) {
            for (int i = 0; i < iArr.length; i++) {
                if (a(iArr[i])) {
                    if (a(iArr, i)) {
                        throw new IllegalArgumentException("matches contains a duplicate value");
                    }
                } else {
                    throw new IllegalArgumentException("matches contains invalid value");
                }
            }
            this.s = (int[]) iArr.clone();
            return;
        }
        this.s = G;
    }

    public void c(View view) {
        if (this.z) {
            return;
        }
        j4<Animator, d> j4VarQ = q();
        int size = j4VarQ.size();
        nh nhVarD = fh.d(view);
        for (int i = size - 1; i >= 0; i--) {
            d dVarValueAt = j4VarQ.valueAt(i);
            if (dVarValueAt.f1437a != null && nhVarD.equals(dVarValueAt.d)) {
                yf.a(j4VarQ.keyAt(i));
            }
        }
        ArrayList<f> arrayList = this.A;
        if (arrayList != null && arrayList.size() > 0) {
            ArrayList arrayList2 = (ArrayList) this.A.clone();
            int size2 = arrayList2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                ((f) arrayList2.get(i2)).b(this);
            }
        }
        this.y = true;
    }

    public static boolean a(int[] iArr, int i) {
        int i2 = iArr[i];
        for (int i3 = 0; i3 < i; i3++) {
            if (iArr[i3] == i2) {
                return true;
            }
        }
        return false;
    }

    public final void a(j4<View, tg> j4Var, j4<View, tg> j4Var2, m4<View> m4Var, m4<View> m4Var2) {
        View viewA;
        int iC = m4Var.c();
        for (int i = 0; i < iC; i++) {
            View viewC = m4Var.c(i);
            if (viewC != null && b(viewC) && (viewA = m4Var2.a(m4Var.a(i))) != null && b(viewA)) {
                tg tgVar = j4Var.get(viewC);
                tg tgVar2 = j4Var2.get(viewA);
                if (tgVar != null && tgVar2 != null) {
                    this.t.add(tgVar);
                    this.u.add(tgVar2);
                    j4Var.remove(viewC);
                    j4Var2.remove(viewA);
                }
            }
        }
    }

    public long b() {
        return this.c;
    }

    public Transition b(long j) {
        this.b = j;
        return this;
    }

    public final void b(j4<View, tg> j4Var, j4<View, tg> j4Var2) {
        tg tgVarRemove;
        View view;
        for (int size = j4Var.size() - 1; size >= 0; size--) {
            View viewKeyAt = j4Var.keyAt(size);
            if (viewKeyAt != null && b(viewKeyAt) && (tgVarRemove = j4Var2.remove(viewKeyAt)) != null && (view = tgVarRemove.b) != null && b(view)) {
                this.t.add(j4Var.removeAt(size));
                this.u.add(tgVarRemove);
            }
        }
    }

    public Rect c() {
        e eVar = this.D;
        if (eVar == null) {
            return null;
        }
        return eVar.a(this);
    }

    public final void a(j4<View, tg> j4Var, j4<View, tg> j4Var2, SparseArray<View> sparseArray, SparseArray<View> sparseArray2) {
        View view;
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            View viewValueAt = sparseArray.valueAt(i);
            if (viewValueAt != null && b(viewValueAt) && (view = sparseArray2.get(sparseArray.keyAt(i))) != null && b(view)) {
                tg tgVar = j4Var.get(viewValueAt);
                tg tgVar2 = j4Var2.get(view);
                if (tgVar != null && tgVar2 != null) {
                    this.t.add(tgVar);
                    this.u.add(tgVar2);
                    j4Var.remove(viewValueAt);
                    j4Var2.remove(view);
                }
            }
        }
    }

    public boolean b(View view) {
        ArrayList<Class> arrayList;
        ArrayList<String> arrayList2;
        int id = view.getId();
        ArrayList<Integer> arrayList3 = this.i;
        if (arrayList3 != null && arrayList3.contains(Integer.valueOf(id))) {
            return false;
        }
        ArrayList<View> arrayList4 = this.j;
        if (arrayList4 != null && arrayList4.contains(view)) {
            return false;
        }
        ArrayList<Class> arrayList5 = this.k;
        if (arrayList5 != null) {
            int size = arrayList5.size();
            for (int i = 0; i < size; i++) {
                if (this.k.get(i).isInstance(view)) {
                    return false;
                }
            }
        }
        if (this.l != null && lb.z(view) != null && this.l.contains(lb.z(view))) {
            return false;
        }
        if ((this.f1434e.size() == 0 && this.f.size() == 0 && (((arrayList = this.h) == null || arrayList.isEmpty()) && ((arrayList2 = this.g) == null || arrayList2.isEmpty()))) || this.f1434e.contains(Integer.valueOf(id)) || this.f.contains(view)) {
            return true;
        }
        ArrayList<String> arrayList6 = this.g;
        if (arrayList6 != null && arrayList6.contains(lb.z(view))) {
            return true;
        }
        if (this.h != null) {
            for (int i2 = 0; i2 < this.h.size(); i2++) {
                if (this.h.get(i2).isInstance(view)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Transition(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, og.f8669a);
        XmlResourceParser xmlResourceParser = (XmlResourceParser) attributeSet;
        long jB = l8.b(typedArrayObtainStyledAttributes, xmlResourceParser, "duration", 1, -1);
        if (jB >= 0) {
            a(jB);
        }
        long jB2 = l8.b(typedArrayObtainStyledAttributes, xmlResourceParser, "startDelay", 2, -1);
        if (jB2 > 0) {
            b(jB2);
        }
        int iC = l8.c(typedArrayObtainStyledAttributes, xmlResourceParser, "interpolator", 0, 0);
        if (iC > 0) {
            a(AnimationUtils.loadInterpolator(context, iC));
        }
        String strA = l8.a(typedArrayObtainStyledAttributes, xmlResourceParser, "matchOrder", 3);
        if (strA != null) {
            a(b(strA));
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void a(j4<View, tg> j4Var, j4<View, tg> j4Var2, j4<String, View> j4Var3, j4<String, View> j4Var4) {
        View view;
        int size = j4Var3.size();
        for (int i = 0; i < size; i++) {
            View viewValueAt = j4Var3.valueAt(i);
            if (viewValueAt != null && b(viewValueAt) && (view = j4Var4.get(j4Var3.keyAt(i))) != null && b(view)) {
                tg tgVar = j4Var.get(viewValueAt);
                tg tgVar2 = j4Var2.get(view);
                if (tgVar != null && tgVar2 != null) {
                    this.t.add(tgVar);
                    this.u.add(tgVar2);
                    j4Var.remove(viewValueAt);
                    j4Var2.remove(view);
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x002e, code lost:
    
        if (r3 < 0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0030, code lost:
    
        if (r8 == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0032, code lost:
    
        r7 = r6.u;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0035, code lost:
    
        r7 = r6.t;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x003e, code lost:
    
        return r7.get(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:?, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public supwisdom.tg b(android.view.View r7, boolean r8) {
        /*
            r6 = this;
            androidx.transition.TransitionSet r0 = r6.r
            if (r0 == 0) goto L9
            supwisdom.tg r7 = r0.b(r7, r8)
            return r7
        L9:
            if (r8 == 0) goto Le
            java.util.ArrayList<supwisdom.tg> r0 = r6.t
            goto L10
        Le:
            java.util.ArrayList<supwisdom.tg> r0 = r6.u
        L10:
            r1 = 0
            if (r0 != 0) goto L14
            return r1
        L14:
            int r2 = r0.size()
            r3 = -1
            r4 = 0
        L1a:
            if (r4 >= r2) goto L2e
            java.lang.Object r5 = r0.get(r4)
            supwisdom.tg r5 = (supwisdom.tg) r5
            if (r5 != 0) goto L25
            return r1
        L25:
            android.view.View r5 = r5.b
            if (r5 != r7) goto L2b
            r3 = r4
            goto L2e
        L2b:
            int r4 = r4 + 1
            goto L1a
        L2e:
            if (r3 < 0) goto L3e
            if (r8 == 0) goto L35
            java.util.ArrayList<supwisdom.tg> r7 = r6.u
            goto L37
        L35:
            java.util.ArrayList<supwisdom.tg> r7 = r6.t
        L37:
            java.lang.Object r7 = r7.get(r3)
            r1 = r7
            supwisdom.tg r1 = (supwisdom.tg) r1
        L3e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.Transition.b(android.view.View, boolean):supwisdom.tg");
    }

    public final void a(j4<View, tg> j4Var, j4<View, tg> j4Var2) {
        for (int i = 0; i < j4Var.size(); i++) {
            tg tgVarValueAt = j4Var.valueAt(i);
            if (b(tgVarValueAt.b)) {
                this.t.add(tgVarValueAt);
                this.u.add(null);
            }
        }
        for (int i2 = 0; i2 < j4Var2.size(); i2++) {
            tg tgVarValueAt2 = j4Var2.valueAt(i2);
            if (b(tgVarValueAt2.b)) {
                this.u.add(tgVarValueAt2);
                this.t.add(null);
            }
        }
    }

    public Transition b(f fVar) {
        ArrayList<f> arrayList = this.A;
        if (arrayList == null) {
            return this;
        }
        arrayList.remove(fVar);
        if (this.A.size() == 0) {
            this.A = null;
        }
        return this;
    }

    public void b(tg tgVar) {
        String[] strArrA;
        if (this.C == null || tgVar.f9283a.isEmpty() || (strArrA = this.C.a()) == null) {
            return;
        }
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= strArrA.length) {
                z = true;
                break;
            } else if (!tgVar.f9283a.containsKey(strArrA[i])) {
                break;
            } else {
                i++;
            }
        }
        if (z) {
            return;
        }
        this.C.a(tgVar);
    }

    public final void a(ug ugVar, ug ugVar2) {
        j4<View, tg> j4Var = new j4<>(ugVar.f9409a);
        j4<View, tg> j4Var2 = new j4<>(ugVar2.f9409a);
        int i = 0;
        while (true) {
            int[] iArr = this.s;
            if (i < iArr.length) {
                int i2 = iArr[i];
                if (i2 == 1) {
                    b(j4Var, j4Var2);
                } else if (i2 == 2) {
                    a(j4Var, j4Var2, ugVar.d, ugVar2.d);
                } else if (i2 == 3) {
                    a(j4Var, j4Var2, ugVar.b, ugVar2.b);
                } else if (i2 == 4) {
                    a(j4Var, j4Var2, ugVar.c, ugVar2.c);
                }
                i++;
            } else {
                a(j4Var, j4Var2);
                return;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(android.view.ViewGroup r21, supwisdom.ug r22, supwisdom.ug r23, java.util.ArrayList<supwisdom.tg> r24, java.util.ArrayList<supwisdom.tg> r25) {
        /*
            Method dump skipped, instruction units count: 346
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.Transition.a(android.view.ViewGroup, supwisdom.ug, supwisdom.ug, java.util.ArrayList, java.util.ArrayList):void");
    }

    public final void a(Animator animator, j4<Animator, d> j4Var) {
        if (animator != null) {
            animator.addListener(new b(j4Var));
            a(animator);
        }
    }

    public Transition a(View view) {
        this.f.add(view);
        return this;
    }

    public void a(ViewGroup viewGroup, boolean z) {
        ArrayList<String> arrayList;
        ArrayList<Class> arrayList2;
        j4<String, String> j4Var;
        a(z);
        if ((this.f1434e.size() <= 0 && this.f.size() <= 0) || (((arrayList = this.g) != null && !arrayList.isEmpty()) || ((arrayList2 = this.h) != null && !arrayList2.isEmpty()))) {
            a((View) viewGroup, z);
        } else {
            for (int i = 0; i < this.f1434e.size(); i++) {
                View viewFindViewById = viewGroup.findViewById(this.f1434e.get(i).intValue());
                if (viewFindViewById != null) {
                    tg tgVar = new tg();
                    tgVar.b = viewFindViewById;
                    if (z) {
                        c(tgVar);
                    } else {
                        a(tgVar);
                    }
                    tgVar.c.add(this);
                    b(tgVar);
                    if (z) {
                        a(this.p, viewFindViewById, tgVar);
                    } else {
                        a(this.q, viewFindViewById, tgVar);
                    }
                }
            }
            for (int i2 = 0; i2 < this.f.size(); i2++) {
                View view = this.f.get(i2);
                tg tgVar2 = new tg();
                tgVar2.b = view;
                if (z) {
                    c(tgVar2);
                } else {
                    a(tgVar2);
                }
                tgVar2.c.add(this);
                b(tgVar2);
                if (z) {
                    a(this.p, view, tgVar2);
                } else {
                    a(this.q, view, tgVar2);
                }
            }
        }
        if (z || (j4Var = this.E) == null) {
            return;
        }
        int size = j4Var.size();
        ArrayList arrayList3 = new ArrayList(size);
        for (int i3 = 0; i3 < size; i3++) {
            arrayList3.add(this.p.d.remove(this.E.keyAt(i3)));
        }
        for (int i4 = 0; i4 < size; i4++) {
            View view2 = (View) arrayList3.get(i4);
            if (view2 != null) {
                this.p.d.put(this.E.valueAt(i4), view2);
            }
        }
    }

    public static void a(ug ugVar, View view, tg tgVar) {
        ugVar.f9409a.put(view, tgVar);
        int id = view.getId();
        if (id >= 0) {
            if (ugVar.b.indexOfKey(id) >= 0) {
                ugVar.b.put(id, null);
            } else {
                ugVar.b.put(id, view);
            }
        }
        String strZ = lb.z(view);
        if (strZ != null) {
            if (ugVar.d.containsKey(strZ)) {
                ugVar.d.put(strZ, null);
            } else {
                ugVar.d.put(strZ, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                if (ugVar.c.b(itemIdAtPosition) >= 0) {
                    View viewA = ugVar.c.a(itemIdAtPosition);
                    if (viewA != null) {
                        lb.b(viewA, false);
                        ugVar.c.c(itemIdAtPosition, null);
                        return;
                    }
                    return;
                }
                lb.b(view, true);
                ugVar.c.c(itemIdAtPosition, view);
            }
        }
    }

    public void a(boolean z) {
        if (z) {
            this.p.f9409a.clear();
            this.p.b.clear();
            this.p.c.a();
        } else {
            this.q.f9409a.clear();
            this.q.b.clear();
            this.q.c.a();
        }
    }

    public final void a(View view, boolean z) {
        if (view == null) {
            return;
        }
        int id = view.getId();
        ArrayList<Integer> arrayList = this.i;
        if (arrayList == null || !arrayList.contains(Integer.valueOf(id))) {
            ArrayList<View> arrayList2 = this.j;
            if (arrayList2 == null || !arrayList2.contains(view)) {
                ArrayList<Class> arrayList3 = this.k;
                if (arrayList3 != null) {
                    int size = arrayList3.size();
                    for (int i = 0; i < size; i++) {
                        if (this.k.get(i).isInstance(view)) {
                            return;
                        }
                    }
                }
                if (view.getParent() instanceof ViewGroup) {
                    tg tgVar = new tg();
                    tgVar.b = view;
                    if (z) {
                        c(tgVar);
                    } else {
                        a(tgVar);
                    }
                    tgVar.c.add(this);
                    b(tgVar);
                    if (z) {
                        a(this.p, view, tgVar);
                    } else {
                        a(this.q, view, tgVar);
                    }
                }
                if (view instanceof ViewGroup) {
                    ArrayList<Integer> arrayList4 = this.m;
                    if (arrayList4 == null || !arrayList4.contains(Integer.valueOf(id))) {
                        ArrayList<View> arrayList5 = this.n;
                        if (arrayList5 == null || !arrayList5.contains(view)) {
                            ArrayList<Class> arrayList6 = this.o;
                            if (arrayList6 != null) {
                                int size2 = arrayList6.size();
                                for (int i2 = 0; i2 < size2; i2++) {
                                    if (this.o.get(i2).isInstance(view)) {
                                        return;
                                    }
                                }
                            }
                            ViewGroup viewGroup = (ViewGroup) view;
                            for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
                                a(viewGroup.getChildAt(i3), z);
                            }
                        }
                    }
                }
            }
        }
    }

    public void a(ViewGroup viewGroup) {
        d dVar;
        this.t = new ArrayList<>();
        this.u = new ArrayList<>();
        a(this.p, this.q);
        j4<Animator, d> j4VarQ = q();
        int size = j4VarQ.size();
        nh nhVarD = fh.d(viewGroup);
        for (int i = size - 1; i >= 0; i--) {
            Animator animatorKeyAt = j4VarQ.keyAt(i);
            if (animatorKeyAt != null && (dVar = j4VarQ.get(animatorKeyAt)) != null && dVar.f1437a != null && nhVarD.equals(dVar.d)) {
                tg tgVar = dVar.c;
                View view = dVar.f1437a;
                tg tgVarC = c(view, true);
                tg tgVarB = b(view, true);
                if (!(tgVarC == null && tgVarB == null) && dVar.f1438e.a(tgVar, tgVarB)) {
                    if (!animatorKeyAt.isRunning() && !animatorKeyAt.isStarted()) {
                        j4VarQ.remove(animatorKeyAt);
                    } else {
                        animatorKeyAt.cancel();
                    }
                }
            }
        }
        a(viewGroup, this.p, this.q, this.t, this.u);
        o();
    }

    public boolean a(tg tgVar, tg tgVar2) {
        if (tgVar == null || tgVar2 == null) {
            return false;
        }
        String[] strArrN = n();
        if (strArrN != null) {
            for (String str : strArrN) {
                if (!a(tgVar, tgVar2, str)) {
                }
            }
            return false;
        }
        Iterator<String> it = tgVar.f9283a.keySet().iterator();
        while (it.hasNext()) {
            if (a(tgVar, tgVar2, it.next())) {
            }
        }
        return false;
        return true;
    }

    public static boolean a(tg tgVar, tg tgVar2, String str) {
        Object obj = tgVar.f9283a.get(str);
        Object obj2 = tgVar2.f9283a.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        return true ^ obj.equals(obj2);
    }

    public void a(Animator animator) {
        if (animator == null) {
            a();
            return;
        }
        if (b() >= 0) {
            animator.setDuration(b());
        }
        if (i() >= 0) {
            animator.setStartDelay(i());
        }
        if (e() != null) {
            animator.setInterpolator(e());
        }
        animator.addListener(new c());
        animator.start();
    }

    public void a() {
        int i = this.x - 1;
        this.x = i;
        if (i == 0) {
            ArrayList<f> arrayList = this.A;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.A.clone();
                int size = arrayList2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((f) arrayList2.get(i2)).c(this);
                }
            }
            for (int i3 = 0; i3 < this.p.c.c(); i3++) {
                View viewC = this.p.c.c(i3);
                if (viewC != null) {
                    lb.b(viewC, false);
                }
            }
            for (int i4 = 0; i4 < this.q.c.c(); i4++) {
                View viewC2 = this.q.c.c(i4);
                if (viewC2 != null) {
                    lb.b(viewC2, false);
                }
            }
            this.z = true;
        }
    }

    public Transition a(f fVar) {
        if (this.A == null) {
            this.A = new ArrayList<>();
        }
        this.A.add(fVar);
        return this;
    }

    public void a(PathMotion pathMotion) {
        if (pathMotion == null) {
            this.F = H;
        } else {
            this.F = pathMotion;
        }
    }

    public void a(e eVar) {
        this.D = eVar;
    }

    public void a(rg rgVar) {
        this.C = rgVar;
    }

    public String a(String str) {
        String str2 = str + getClass().getSimpleName() + CellDataManager.VIRTUAL_COMPONENT_SEPRATOR + Integer.toHexString(hashCode()) + ": ";
        if (this.c != -1) {
            str2 = str2 + "dur(" + this.c + ") ";
        }
        if (this.b != -1) {
            str2 = str2 + "dly(" + this.b + ") ";
        }
        if (this.d != null) {
            str2 = str2 + "interp(" + this.d + ") ";
        }
        if (this.f1434e.size() <= 0 && this.f.size() <= 0) {
            return str2;
        }
        String str3 = str2 + "tgts(";
        if (this.f1434e.size() > 0) {
            for (int i = 0; i < this.f1434e.size(); i++) {
                if (i > 0) {
                    str3 = str3 + ", ";
                }
                str3 = str3 + this.f1434e.get(i);
            }
        }
        if (this.f.size() > 0) {
            for (int i2 = 0; i2 < this.f.size(); i2++) {
                if (i2 > 0) {
                    str3 = str3 + ", ";
                }
                str3 = str3 + this.f.get(i2);
            }
        }
        return str3 + ")";
    }
}
