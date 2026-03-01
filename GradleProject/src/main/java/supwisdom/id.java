package supwisdom;

import android.graphics.Rect;
import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: FragmentTransition.java */
/* JADX INFO: loaded from: classes.dex */
public class id {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f7938a = {0, 3, 0, 1, 5, 4, 7, 6, 9, 8, 10};
    public static final kd b;
    public static final kd c;

    /* JADX INFO: compiled from: FragmentTransition.java */
    public static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ArrayList f7939a;

        public a(ArrayList arrayList) {
            this.f7939a = arrayList;
        }

        @Override // java.lang.Runnable
        public void run() {
            id.a((ArrayList<View>) this.f7939a, 4);
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    public static class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Object f7940a;
        public final /* synthetic */ kd b;
        public final /* synthetic */ View c;
        public final /* synthetic */ Fragment d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ ArrayList f7941e;
        public final /* synthetic */ ArrayList f;
        public final /* synthetic */ ArrayList g;
        public final /* synthetic */ Object h;

        public b(Object obj, kd kdVar, View view, Fragment fragment, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
            this.f7940a = obj;
            this.b = kdVar;
            this.c = view;
            this.d = fragment;
            this.f7941e = arrayList;
            this.f = arrayList2;
            this.g = arrayList3;
            this.h = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj = this.f7940a;
            if (obj != null) {
                this.b.b(obj, this.c);
                this.f.addAll(id.a(this.b, this.f7940a, this.d, (ArrayList<View>) this.f7941e, this.c));
            }
            if (this.g != null) {
                if (this.h != null) {
                    ArrayList<View> arrayList = new ArrayList<>();
                    arrayList.add(this.c);
                    this.b.a(this.h, this.g, arrayList);
                }
                this.g.clear();
                this.g.add(this.c);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    public static class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Fragment f7942a;
        public final /* synthetic */ Fragment b;
        public final /* synthetic */ boolean c;
        public final /* synthetic */ j4 d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ View f7943e;
        public final /* synthetic */ kd f;
        public final /* synthetic */ Rect g;

        public c(Fragment fragment, Fragment fragment2, boolean z, j4 j4Var, View view, kd kdVar, Rect rect) {
            this.f7942a = fragment;
            this.b = fragment2;
            this.c = z;
            this.d = j4Var;
            this.f7943e = view;
            this.f = kdVar;
            this.g = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            id.a(this.f7942a, this.b, this.c, (j4<String, View>) this.d, false);
            View view = this.f7943e;
            if (view != null) {
                this.f.a(view, this.g);
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    public static class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ kd f7944a;
        public final /* synthetic */ j4 b;
        public final /* synthetic */ Object c;
        public final /* synthetic */ e d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ ArrayList f7945e;
        public final /* synthetic */ View f;
        public final /* synthetic */ Fragment g;
        public final /* synthetic */ Fragment h;
        public final /* synthetic */ boolean i;
        public final /* synthetic */ ArrayList j;
        public final /* synthetic */ Object k;
        public final /* synthetic */ Rect l;

        public d(kd kdVar, j4 j4Var, Object obj, e eVar, ArrayList arrayList, View view, Fragment fragment, Fragment fragment2, boolean z, ArrayList arrayList2, Object obj2, Rect rect) {
            this.f7944a = kdVar;
            this.b = j4Var;
            this.c = obj;
            this.d = eVar;
            this.f7945e = arrayList;
            this.f = view;
            this.g = fragment;
            this.h = fragment2;
            this.i = z;
            this.j = arrayList2;
            this.k = obj2;
            this.l = rect;
        }

        @Override // java.lang.Runnable
        public void run() {
            j4<String, View> j4VarA = id.a(this.f7944a, (j4<String, String>) this.b, this.c, this.d);
            if (j4VarA != null) {
                this.f7945e.addAll(j4VarA.values());
                this.f7945e.add(this.f);
            }
            id.a(this.g, this.h, this.i, j4VarA, false);
            Object obj = this.c;
            if (obj != null) {
                this.f7944a.b(obj, this.j, this.f7945e);
                View viewA = id.a(j4VarA, this.d, this.k, this.i);
                if (viewA != null) {
                    this.f7944a.a(viewA, this.l);
                }
            }
        }
    }

    /* JADX INFO: compiled from: FragmentTransition.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Fragment f7946a;
        public boolean b;
        public xc c;
        public Fragment d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f7947e;
        public xc f;
    }

    static {
        b = Build.VERSION.SDK_INT >= 21 ? new jd() : null;
        c = a();
    }

    public static kd a() {
        try {
            return (kd) Class.forName("supwisdom.bg").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void b(ed edVar, int i, e eVar, View view, j4<String, String> j4Var) {
        Fragment fragment;
        Fragment fragment2;
        kd kdVarA;
        Object obj;
        ViewGroup viewGroup = edVar.r.a() ? (ViewGroup) edVar.r.a(i) : null;
        if (viewGroup == null || (kdVarA = a((fragment2 = eVar.d), (fragment = eVar.f7946a))) == null) {
            return;
        }
        boolean z = eVar.b;
        boolean z2 = eVar.f7947e;
        ArrayList<View> arrayList = new ArrayList<>();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object objA = a(kdVarA, fragment, z);
        Object objB = b(kdVarA, fragment2, z2);
        Object objB2 = b(kdVarA, viewGroup, view, j4Var, eVar, arrayList2, arrayList, objA, objB);
        if (objA == null && objB2 == null) {
            obj = objB;
            if (obj == null) {
                return;
            }
        } else {
            obj = objB;
        }
        ArrayList<View> arrayListA = a(kdVarA, obj, fragment2, arrayList2, view);
        ArrayList<View> arrayListA2 = a(kdVarA, objA, fragment, arrayList, view);
        a(arrayListA2, 4);
        Object objA2 = a(kdVarA, objA, obj, objB2, fragment, z);
        if (objA2 != null) {
            a(kdVarA, obj, fragment2, arrayListA);
            ArrayList<String> arrayListA3 = kdVarA.a(arrayList);
            kdVarA.a(objA2, objA, arrayListA2, obj, arrayListA, objB2, arrayList);
            kdVarA.a(viewGroup, objA2);
            kdVarA.a(viewGroup, arrayList2, arrayList, arrayListA3, j4Var);
            a(arrayListA2, 0);
            kdVarA.b(objB2, arrayList2, arrayList);
        }
    }

    public static void a(ed edVar, ArrayList<xc> arrayList, ArrayList<Boolean> arrayList2, int i, int i2, boolean z) {
        if (edVar.p < 1) {
            return;
        }
        SparseArray sparseArray = new SparseArray();
        for (int i3 = i; i3 < i2; i3++) {
            xc xcVar = arrayList.get(i3);
            if (arrayList2.get(i3).booleanValue()) {
                b(xcVar, (SparseArray<e>) sparseArray, z);
            } else {
                a(xcVar, (SparseArray<e>) sparseArray, z);
            }
        }
        if (sparseArray.size() != 0) {
            View view = new View(edVar.q.c());
            int size = sparseArray.size();
            for (int i4 = 0; i4 < size; i4++) {
                int iKeyAt = sparseArray.keyAt(i4);
                j4<String, String> j4VarA = a(iKeyAt, arrayList, arrayList2, i, i2);
                e eVar = (e) sparseArray.valueAt(i4);
                if (z) {
                    b(edVar, iKeyAt, eVar, view, j4VarA);
                } else {
                    a(edVar, iKeyAt, eVar, view, j4VarA);
                }
            }
        }
    }

    public static j4<String, String> a(int i, ArrayList<xc> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        ArrayList<String> arrayList3;
        ArrayList<String> arrayList4;
        j4<String, String> j4Var = new j4<>();
        for (int i4 = i3 - 1; i4 >= i2; i4--) {
            xc xcVar = arrayList.get(i4);
            if (xcVar.b(i)) {
                boolean zBooleanValue = arrayList2.get(i4).booleanValue();
                ArrayList<String> arrayList5 = xcVar.n;
                if (arrayList5 != null) {
                    int size = arrayList5.size();
                    if (zBooleanValue) {
                        arrayList3 = xcVar.n;
                        arrayList4 = xcVar.o;
                    } else {
                        ArrayList<String> arrayList6 = xcVar.n;
                        arrayList3 = xcVar.o;
                        arrayList4 = arrayList6;
                    }
                    for (int i5 = 0; i5 < size; i5++) {
                        String str = arrayList4.get(i5);
                        String str2 = arrayList3.get(i5);
                        String strRemove = j4Var.remove(str2);
                        if (strRemove != null) {
                            j4Var.put(str, strRemove);
                        } else {
                            j4Var.put(str, str2);
                        }
                    }
                }
            }
        }
        return j4Var;
    }

    public static Object b(kd kdVar, Fragment fragment, boolean z) {
        Object exitTransition;
        if (fragment == null) {
            return null;
        }
        if (z) {
            exitTransition = fragment.getReturnTransition();
        } else {
            exitTransition = fragment.getExitTransition();
        }
        return kdVar.b(exitTransition);
    }

    public static Object b(kd kdVar, ViewGroup viewGroup, View view, j4<String, String> j4Var, e eVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object obj3;
        View view2;
        Rect rect;
        Fragment fragment = eVar.f7946a;
        Fragment fragment2 = eVar.d;
        if (fragment != null) {
            fragment.requireView().setVisibility(0);
        }
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = eVar.b;
        Object objA = j4Var.isEmpty() ? null : a(kdVar, fragment, fragment2, z);
        j4<String, View> j4VarB = b(kdVar, j4Var, objA, eVar);
        j4<String, View> j4VarA = a(kdVar, j4Var, objA, eVar);
        if (j4Var.isEmpty()) {
            if (j4VarB != null) {
                j4VarB.clear();
            }
            if (j4VarA != null) {
                j4VarA.clear();
            }
            obj3 = null;
        } else {
            a(arrayList, j4VarB, j4Var.keySet());
            a(arrayList2, j4VarA, j4Var.values());
            obj3 = objA;
        }
        if (obj == null && obj2 == null && obj3 == null) {
            return null;
        }
        a(fragment, fragment2, z, j4VarB, true);
        if (obj3 != null) {
            arrayList2.add(view);
            kdVar.b(obj3, view, arrayList);
            a(kdVar, obj3, obj2, j4VarB, eVar.f7947e, eVar.f);
            Rect rect2 = new Rect();
            View viewA = a(j4VarA, eVar, obj, z);
            if (viewA != null) {
                kdVar.a(obj, rect2);
            }
            rect = rect2;
            view2 = viewA;
        } else {
            view2 = null;
            rect = null;
        }
        hb.a(viewGroup, new c(fragment, fragment2, z, j4VarA, view2, kdVar, rect));
        return obj3;
    }

    public static void a(kd kdVar, Object obj, Fragment fragment, ArrayList<View> arrayList) {
        if (fragment != null && obj != null && fragment.mAdded && fragment.mHidden && fragment.mHiddenChanged) {
            fragment.setHideReplaced(true);
            kdVar.a(obj, fragment.getView(), arrayList);
            hb.a(fragment.mContainer, new a(arrayList));
        }
    }

    public static void a(ed edVar, int i, e eVar, View view, j4<String, String> j4Var) {
        Fragment fragment;
        Fragment fragment2;
        kd kdVarA;
        Object obj;
        ViewGroup viewGroup = edVar.r.a() ? (ViewGroup) edVar.r.a(i) : null;
        if (viewGroup == null || (kdVarA = a((fragment2 = eVar.d), (fragment = eVar.f7946a))) == null) {
            return;
        }
        boolean z = eVar.b;
        boolean z2 = eVar.f7947e;
        Object objA = a(kdVarA, fragment, z);
        Object objB = b(kdVarA, fragment2, z2);
        ArrayList arrayList = new ArrayList();
        ArrayList<View> arrayList2 = new ArrayList<>();
        Object objA2 = a(kdVarA, viewGroup, view, j4Var, eVar, (ArrayList<View>) arrayList, arrayList2, objA, objB);
        if (objA == null && objA2 == null) {
            obj = objB;
            if (obj == null) {
                return;
            }
        } else {
            obj = objB;
        }
        ArrayList<View> arrayListA = a(kdVarA, obj, fragment2, (ArrayList<View>) arrayList, view);
        Object obj2 = (arrayListA == null || arrayListA.isEmpty()) ? null : obj;
        kdVarA.a(objA, view);
        Object objA3 = a(kdVarA, objA, obj2, objA2, fragment, eVar.b);
        if (objA3 != null) {
            ArrayList<View> arrayList3 = new ArrayList<>();
            kdVarA.a(objA3, objA, arrayList3, obj2, arrayListA, objA2, arrayList2);
            a(kdVarA, viewGroup, fragment, view, arrayList2, objA, arrayList3, obj2, arrayListA);
            kdVarA.a((View) viewGroup, arrayList2, (Map<String, String>) j4Var);
            kdVarA.a(viewGroup, objA3);
            kdVarA.a(viewGroup, arrayList2, (Map<String, String>) j4Var);
        }
    }

    public static j4<String, View> b(kd kdVar, j4<String, String> j4Var, Object obj, e eVar) {
        w7 exitTransitionCallback;
        ArrayList<String> arrayList;
        if (!j4Var.isEmpty() && obj != null) {
            Fragment fragment = eVar.d;
            j4<String, View> j4Var2 = new j4<>();
            kdVar.a((Map<String, View>) j4Var2, fragment.requireView());
            xc xcVar = eVar.f;
            if (eVar.f7947e) {
                exitTransitionCallback = fragment.getEnterTransitionCallback();
                arrayList = xcVar.o;
            } else {
                exitTransitionCallback = fragment.getExitTransitionCallback();
                arrayList = xcVar.n;
            }
            j4Var2.retainAll(arrayList);
            if (exitTransitionCallback != null) {
                exitTransitionCallback.a(arrayList, j4Var2);
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    String str = arrayList.get(size);
                    View view = j4Var2.get(str);
                    if (view == null) {
                        j4Var.remove(str);
                    } else if (!str.equals(lb.z(view))) {
                        j4Var.put(lb.z(view), j4Var.remove(str));
                    }
                }
            } else {
                j4Var.retainAll(j4Var2.keySet());
            }
            return j4Var2;
        }
        j4Var.clear();
        return null;
    }

    public static void a(kd kdVar, ViewGroup viewGroup, Fragment fragment, View view, ArrayList<View> arrayList, Object obj, ArrayList<View> arrayList2, Object obj2, ArrayList<View> arrayList3) {
        hb.a(viewGroup, new b(obj, kdVar, view, fragment, arrayList, arrayList2, arrayList3, obj2));
    }

    public static kd a(Fragment fragment, Fragment fragment2) {
        ArrayList arrayList = new ArrayList();
        if (fragment != null) {
            Object exitTransition = fragment.getExitTransition();
            if (exitTransition != null) {
                arrayList.add(exitTransition);
            }
            Object returnTransition = fragment.getReturnTransition();
            if (returnTransition != null) {
                arrayList.add(returnTransition);
            }
            Object sharedElementReturnTransition = fragment.getSharedElementReturnTransition();
            if (sharedElementReturnTransition != null) {
                arrayList.add(sharedElementReturnTransition);
            }
        }
        if (fragment2 != null) {
            Object enterTransition = fragment2.getEnterTransition();
            if (enterTransition != null) {
                arrayList.add(enterTransition);
            }
            Object reenterTransition = fragment2.getReenterTransition();
            if (reenterTransition != null) {
                arrayList.add(reenterTransition);
            }
            Object sharedElementEnterTransition = fragment2.getSharedElementEnterTransition();
            if (sharedElementEnterTransition != null) {
                arrayList.add(sharedElementEnterTransition);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        kd kdVar = b;
        if (kdVar != null && a(kdVar, arrayList)) {
            return b;
        }
        kd kdVar2 = c;
        if (kdVar2 != null && a(kdVar2, arrayList)) {
            return c;
        }
        if (b == null && c == null) {
            return null;
        }
        throw new IllegalArgumentException("Invalid Transition types");
    }

    public static void b(xc xcVar, SparseArray<e> sparseArray, boolean z) {
        if (xcVar.r.r.a()) {
            for (int size = xcVar.f7826a.size() - 1; size >= 0; size--) {
                a(xcVar, xcVar.f7826a.get(size), sparseArray, true, z);
            }
        }
    }

    public static boolean a(kd kdVar, List<Object> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!kdVar.a(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static Object a(kd kdVar, Fragment fragment, Fragment fragment2, boolean z) {
        Object sharedElementEnterTransition;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        if (z) {
            sharedElementEnterTransition = fragment2.getSharedElementReturnTransition();
        } else {
            sharedElementEnterTransition = fragment.getSharedElementEnterTransition();
        }
        return kdVar.c(kdVar.b(sharedElementEnterTransition));
    }

    public static Object a(kd kdVar, Fragment fragment, boolean z) {
        Object enterTransition;
        if (fragment == null) {
            return null;
        }
        if (z) {
            enterTransition = fragment.getReenterTransition();
        } else {
            enterTransition = fragment.getEnterTransition();
        }
        return kdVar.b(enterTransition);
    }

    public static void a(ArrayList<View> arrayList, j4<String, View> j4Var, Collection<String> collection) {
        for (int size = j4Var.size() - 1; size >= 0; size--) {
            View viewValueAt = j4Var.valueAt(size);
            if (collection.contains(lb.z(viewValueAt))) {
                arrayList.add(viewValueAt);
            }
        }
    }

    public static Object a(kd kdVar, ViewGroup viewGroup, View view, j4<String, String> j4Var, e eVar, ArrayList<View> arrayList, ArrayList<View> arrayList2, Object obj, Object obj2) {
        Object objA;
        j4<String, String> j4Var2;
        Object obj3;
        Rect rect;
        Fragment fragment = eVar.f7946a;
        Fragment fragment2 = eVar.d;
        if (fragment == null || fragment2 == null) {
            return null;
        }
        boolean z = eVar.b;
        if (j4Var.isEmpty()) {
            j4Var2 = j4Var;
            objA = null;
        } else {
            objA = a(kdVar, fragment, fragment2, z);
            j4Var2 = j4Var;
        }
        j4<String, View> j4VarB = b(kdVar, j4Var2, objA, eVar);
        if (j4Var.isEmpty()) {
            obj3 = null;
        } else {
            arrayList.addAll(j4VarB.values());
            obj3 = objA;
        }
        if (obj == null && obj2 == null && obj3 == null) {
            return null;
        }
        a(fragment, fragment2, z, j4VarB, true);
        if (obj3 != null) {
            rect = new Rect();
            kdVar.b(obj3, view, arrayList);
            a(kdVar, obj3, obj2, j4VarB, eVar.f7947e, eVar.f);
            if (obj != null) {
                kdVar.a(obj, rect);
            }
        } else {
            rect = null;
        }
        hb.a(viewGroup, new d(kdVar, j4Var, obj3, eVar, arrayList2, view, fragment, fragment2, z, arrayList, obj, rect));
        return obj3;
    }

    public static j4<String, View> a(kd kdVar, j4<String, String> j4Var, Object obj, e eVar) {
        w7 enterTransitionCallback;
        ArrayList<String> arrayList;
        String strA;
        Fragment fragment = eVar.f7946a;
        View view = fragment.getView();
        if (!j4Var.isEmpty() && obj != null && view != null) {
            j4<String, View> j4Var2 = new j4<>();
            kdVar.a((Map<String, View>) j4Var2, view);
            xc xcVar = eVar.c;
            if (eVar.b) {
                enterTransitionCallback = fragment.getExitTransitionCallback();
                arrayList = xcVar.n;
            } else {
                enterTransitionCallback = fragment.getEnterTransitionCallback();
                arrayList = xcVar.o;
            }
            if (arrayList != null) {
                j4Var2.retainAll(arrayList);
                j4Var2.retainAll(j4Var.values());
            }
            if (enterTransitionCallback != null) {
                enterTransitionCallback.a(arrayList, j4Var2);
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    String str = arrayList.get(size);
                    View view2 = j4Var2.get(str);
                    if (view2 == null) {
                        String strA2 = a(j4Var, str);
                        if (strA2 != null) {
                            j4Var.remove(strA2);
                        }
                    } else if (!str.equals(lb.z(view2)) && (strA = a(j4Var, str)) != null) {
                        j4Var.put(strA, lb.z(view2));
                    }
                }
            } else {
                a(j4Var, j4Var2);
            }
            return j4Var2;
        }
        j4Var.clear();
        return null;
    }

    public static String a(j4<String, String> j4Var, String str) {
        int size = j4Var.size();
        for (int i = 0; i < size; i++) {
            if (str.equals(j4Var.valueAt(i))) {
                return j4Var.keyAt(i);
            }
        }
        return null;
    }

    public static View a(j4<String, View> j4Var, e eVar, Object obj, boolean z) {
        ArrayList<String> arrayList;
        String str;
        xc xcVar = eVar.c;
        if (obj == null || j4Var == null || (arrayList = xcVar.n) == null || arrayList.isEmpty()) {
            return null;
        }
        if (z) {
            str = xcVar.n.get(0);
        } else {
            str = xcVar.o.get(0);
        }
        return j4Var.get(str);
    }

    public static void a(kd kdVar, Object obj, Object obj2, j4<String, View> j4Var, boolean z, xc xcVar) {
        String str;
        ArrayList<String> arrayList = xcVar.n;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        if (z) {
            str = xcVar.o.get(0);
        } else {
            str = xcVar.n.get(0);
        }
        View view = j4Var.get(str);
        kdVar.c(obj, view);
        if (obj2 != null) {
            kdVar.c(obj2, view);
        }
    }

    public static void a(j4<String, String> j4Var, j4<String, View> j4Var2) {
        for (int size = j4Var.size() - 1; size >= 0; size--) {
            if (!j4Var2.containsKey(j4Var.valueAt(size))) {
                j4Var.removeAt(size);
            }
        }
    }

    public static void a(Fragment fragment, Fragment fragment2, boolean z, j4<String, View> j4Var, boolean z2) {
        w7 enterTransitionCallback;
        if (z) {
            enterTransitionCallback = fragment2.getEnterTransitionCallback();
        } else {
            enterTransitionCallback = fragment.getEnterTransitionCallback();
        }
        if (enterTransitionCallback != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int size = j4Var == null ? 0 : j4Var.size();
            for (int i = 0; i < size; i++) {
                arrayList2.add(j4Var.keyAt(i));
                arrayList.add(j4Var.valueAt(i));
            }
            if (z2) {
                enterTransitionCallback.b(arrayList2, arrayList, null);
            } else {
                enterTransitionCallback.a(arrayList2, arrayList, (List<View>) null);
            }
        }
    }

    public static ArrayList<View> a(kd kdVar, Object obj, Fragment fragment, ArrayList<View> arrayList, View view) {
        if (obj == null) {
            return null;
        }
        ArrayList<View> arrayList2 = new ArrayList<>();
        View view2 = fragment.getView();
        if (view2 != null) {
            kdVar.a(arrayList2, view2);
        }
        if (arrayList != null) {
            arrayList2.removeAll(arrayList);
        }
        if (arrayList2.isEmpty()) {
            return arrayList2;
        }
        arrayList2.add(view);
        kdVar.a(obj, arrayList2);
        return arrayList2;
    }

    public static void a(ArrayList<View> arrayList, int i) {
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            arrayList.get(size).setVisibility(i);
        }
    }

    public static Object a(kd kdVar, Object obj, Object obj2, Object obj3, Fragment fragment, boolean z) {
        boolean allowEnterTransitionOverlap;
        if (obj == null || obj2 == null || fragment == null) {
            allowEnterTransitionOverlap = true;
        } else if (z) {
            allowEnterTransitionOverlap = fragment.getAllowReturnTransitionOverlap();
        } else {
            allowEnterTransitionOverlap = fragment.getAllowEnterTransitionOverlap();
        }
        if (allowEnterTransitionOverlap) {
            return kdVar.b(obj2, obj, obj3);
        }
        return kdVar.a(obj2, obj, obj3);
    }

    public static void a(xc xcVar, SparseArray<e> sparseArray, boolean z) {
        int size = xcVar.f7826a.size();
        for (int i = 0; i < size; i++) {
            a(xcVar, xcVar.f7826a.get(i), sparseArray, false, z);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0096  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(supwisdom.xc r16, supwisdom.hd.a r17, android.util.SparseArray<supwisdom.id.e> r18, boolean r19, boolean r20) {
        /*
            Method dump skipped, instruction units count: 242
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.id.a(supwisdom.xc, supwisdom.hd$a, android.util.SparseArray, boolean, boolean):void");
    }

    public static e a(e eVar, SparseArray<e> sparseArray, int i) {
        if (eVar != null) {
            return eVar;
        }
        e eVar2 = new e();
        sparseArray.put(i, eVar2);
        return eVar2;
    }
}
