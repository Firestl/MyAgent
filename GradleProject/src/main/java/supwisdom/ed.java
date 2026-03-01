package supwisdom;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import androidx.activity.OnBackPressedDispatcher;
import androidx.fragment.app.BackStackState;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManagerState;
import androidx.fragment.app.FragmentState;
import androidx.lifecycle.Lifecycle;
import com.bumptech.glide.load.engine.GlideException;
import com.umeng.analytics.pro.q;
import com.xiaomi.mipush.sdk.Constants;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.bouncycastle.asn1.util.ASN1Dump;
import supwisdom.dd;

/* JADX INFO: compiled from: FragmentManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class ed extends dd implements LayoutInflater.Factory2 {
    public static boolean H = false;
    public static final Interpolator I = new DecelerateInterpolator(2.5f);
    public static final Interpolator J = new DecelerateInterpolator(1.5f);
    public ArrayList<Boolean> A;
    public ArrayList<Fragment> B;
    public ArrayList<m> E;
    public fd F;
    public ArrayList<k> c;
    public boolean d;
    public ArrayList<xc> h;
    public ArrayList<Fragment> i;
    public OnBackPressedDispatcher j;
    public ArrayList<xc> l;
    public ArrayList<Integer> m;
    public ArrayList<dd.b> n;
    public cd q;
    public zc r;
    public Fragment s;
    public Fragment t;
    public boolean u;
    public boolean v;
    public boolean w;
    public boolean x;
    public boolean y;
    public ArrayList<xc> z;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f7463e = 0;
    public final ArrayList<Fragment> f = new ArrayList<>();
    public final HashMap<String, Fragment> g = new HashMap<>();
    public final o0 k = new a(false);
    public final CopyOnWriteArrayList<i> o = new CopyOnWriteArrayList<>();
    public int p = 0;
    public Bundle C = null;
    public SparseArray<Parcelable> D = null;
    public Runnable G = new b();

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public class a extends o0 {
        public a(boolean z) {
            super(z);
        }

        @Override // supwisdom.o0
        public void a() {
            ed.this.A();
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ed.this.v();
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public class c implements Animation.AnimationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f7465a;
        public final /* synthetic */ Fragment b;

        /* JADX INFO: compiled from: FragmentManagerImpl.java */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (c.this.b.getAnimatingAway() != null) {
                    c.this.b.setAnimatingAway(null);
                    c cVar = c.this;
                    ed edVar = ed.this;
                    Fragment fragment = cVar.b;
                    edVar.a(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
                }
            }
        }

        public c(ViewGroup viewGroup, Fragment fragment) {
            this.f7465a = viewGroup;
            this.b = fragment;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.f7465a.post(new a());
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public class d extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f7467a;
        public final /* synthetic */ View b;
        public final /* synthetic */ Fragment c;

        public d(ViewGroup viewGroup, View view, Fragment fragment) {
            this.f7467a = viewGroup;
            this.b = view;
            this.c = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f7467a.endViewTransition(this.b);
            Animator animator2 = this.c.getAnimator();
            this.c.setAnimator(null);
            if (animator2 == null || this.f7467a.indexOfChild(this.b) >= 0) {
                return;
            }
            ed edVar = ed.this;
            Fragment fragment = this.c;
            edVar.a(fragment, fragment.getStateAfterAnimating(), 0, 0, false);
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public class e extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f7468a;
        public final /* synthetic */ View b;
        public final /* synthetic */ Fragment c;

        public e(ed edVar, ViewGroup viewGroup, View view, Fragment fragment) {
            this.f7468a = viewGroup;
            this.b = view;
            this.c = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f7468a.endViewTransition(this.b);
            animator.removeListener(this);
            Fragment fragment = this.c;
            View view = fragment.mView;
            if (view == null || !fragment.mHidden) {
                return;
            }
            view.setVisibility(8);
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public class f extends bd {
        public f() {
        }

        @Override // supwisdom.bd
        public Fragment a(ClassLoader classLoader, String str) {
            cd cdVar = ed.this.q;
            return cdVar.a(cdVar.c(), str, (Bundle) null);
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public static final class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final dd.a f7472a;
        public final boolean b;
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public static class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final int[] f7473a = {R.attr.name, R.attr.id, R.attr.tag};
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public interface k {
        boolean a(ArrayList<xc> arrayList, ArrayList<Boolean> arrayList2);
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public class l implements k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f7474a;
        public final int b;
        public final int c;

        public l(String str, int i, int i2) {
            this.f7474a = str;
            this.b = i;
            this.c = i2;
        }

        @Override // supwisdom.ed.k
        public boolean a(ArrayList<xc> arrayList, ArrayList<Boolean> arrayList2) {
            Fragment fragment = ed.this.t;
            if (fragment == null || this.b >= 0 || this.f7474a != null || !fragment.getChildFragmentManager().e()) {
                return ed.this.a(arrayList, arrayList2, this.f7474a, this.b, this.c);
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public static class m implements Fragment.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final boolean f7475a;
        public final xc b;
        public int c;

        public m(xc xcVar, boolean z) {
            this.f7475a = z;
            this.b = xcVar;
        }

        @Override // androidx.fragment.app.Fragment.e
        public void a() {
            this.c++;
        }

        @Override // androidx.fragment.app.Fragment.e
        public void b() {
            int i = this.c - 1;
            this.c = i;
            if (i != 0) {
                return;
            }
            this.b.r.G();
        }

        public void c() {
            xc xcVar = this.b;
            xcVar.r.a(xcVar, this.f7475a, false, false);
        }

        public void d() {
            boolean z = this.c > 0;
            ed edVar = this.b.r;
            int size = edVar.f.size();
            for (int i = 0; i < size; i++) {
                Fragment fragment = edVar.f.get(i);
                fragment.setOnStartEnterTransitionListener(null);
                if (z && fragment.isPostponed()) {
                    fragment.startPostponedEnterTransition();
                }
            }
            xc xcVar = this.b;
            xcVar.r.a(xcVar, this.f7475a, !z, true);
        }

        public boolean e() {
            return this.c == 0;
        }
    }

    public static int b(int i2, boolean z) {
        if (i2 == 4097) {
            return z ? 1 : 2;
        }
        if (i2 == 4099) {
            return z ? 5 : 6;
        }
        if (i2 != 8194) {
            return -1;
        }
        return z ? 3 : 4;
    }

    public static int e(int i2) {
        if (i2 == 4097) {
            return q.a.s;
        }
        if (i2 != 4099) {
            return i2 != 8194 ? 0 : 4097;
        }
        return 4099;
    }

    public void A() {
        v();
        if (this.k.b()) {
            e();
        } else {
            this.j.a();
        }
    }

    public boolean B() {
        return this.x;
    }

    public boolean C() {
        return this.v || this.w;
    }

    public void D() {
        this.v = false;
        this.w = false;
        int size = this.f.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragment = this.f.get(i2);
            if (fragment != null) {
                fragment.noteStateNotSaved();
            }
        }
    }

    public void E() {
        if (this.n != null) {
            for (int i2 = 0; i2 < this.n.size(); i2++) {
                this.n.get(i2).a();
            }
        }
    }

    public Parcelable F() {
        ArrayList<String> arrayList;
        int size;
        w();
        u();
        v();
        this.v = true;
        BackStackState[] backStackStateArr = null;
        if (this.g.isEmpty()) {
            return null;
        }
        ArrayList<FragmentState> arrayList2 = new ArrayList<>(this.g.size());
        boolean z = false;
        for (Fragment fragment : this.g.values()) {
            if (fragment != null) {
                if (fragment.mFragmentManager != this) {
                    a(new IllegalStateException("Failure saving state: active " + fragment + " was removed from the FragmentManager"));
                    throw null;
                }
                FragmentState fragmentState = new FragmentState(fragment);
                arrayList2.add(fragmentState);
                if (fragment.mState <= 0 || fragmentState.m != null) {
                    fragmentState.m = fragment.mSavedFragmentState;
                } else {
                    fragmentState.m = u(fragment);
                    String str = fragment.mTargetWho;
                    if (str != null) {
                        Fragment fragment2 = this.g.get(str);
                        if (fragment2 == null) {
                            a(new IllegalStateException("Failure saving state: " + fragment + " has target not in fragment manager: " + fragment.mTargetWho));
                            throw null;
                        }
                        if (fragmentState.m == null) {
                            fragmentState.m = new Bundle();
                        }
                        a(fragmentState.m, "android:target_state", fragment2);
                        int i2 = fragment.mTargetRequestCode;
                        if (i2 != 0) {
                            fragmentState.m.putInt("android:target_req_state", i2);
                        }
                    }
                }
                if (H) {
                    Log.v("FragmentManager", "Saved state of " + fragment + ": " + fragmentState.m);
                }
                z = true;
            }
        }
        if (!z) {
            if (H) {
                Log.v("FragmentManager", "saveAllState: no fragments!");
            }
            return null;
        }
        int size2 = this.f.size();
        if (size2 > 0) {
            arrayList = new ArrayList<>(size2);
            for (Fragment fragment3 : this.f) {
                arrayList.add(fragment3.mWho);
                if (fragment3.mFragmentManager != this) {
                    a(new IllegalStateException("Failure saving state: active " + fragment3 + " was removed from the FragmentManager"));
                    throw null;
                }
                if (H) {
                    Log.v("FragmentManager", "saveAllState: adding fragment (" + fragment3.mWho + "): " + fragment3);
                }
            }
        } else {
            arrayList = null;
        }
        ArrayList<xc> arrayList3 = this.h;
        if (arrayList3 != null && (size = arrayList3.size()) > 0) {
            backStackStateArr = new BackStackState[size];
            for (int i3 = 0; i3 < size; i3++) {
                backStackStateArr[i3] = new BackStackState(this.h.get(i3));
                if (H) {
                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i3 + ": " + this.h.get(i3));
                }
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.f1299a = arrayList2;
        fragmentManagerState.b = arrayList;
        fragmentManagerState.c = backStackStateArr;
        Fragment fragment4 = this.t;
        if (fragment4 != null) {
            fragmentManagerState.d = fragment4.mWho;
        }
        fragmentManagerState.f1300e = this.f7463e;
        return fragmentManagerState;
    }

    public void G() {
        synchronized (this) {
            boolean z = false;
            boolean z2 = (this.E == null || this.E.isEmpty()) ? false : true;
            if (this.c != null && this.c.size() == 1) {
                z = true;
            }
            if (z2 || z) {
                this.q.d().removeCallbacks(this.G);
                this.q.d().post(this.G);
                I();
            }
        }
    }

    public void H() {
        for (Fragment fragment : this.g.values()) {
            if (fragment != null) {
                r(fragment);
            }
        }
    }

    public final void I() {
        ArrayList<k> arrayList = this.c;
        if (arrayList == null || arrayList.isEmpty()) {
            this.k.a(x() > 0 && m(this.s));
        } else {
            this.k.a(true);
        }
    }

    public final void a(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new ha("FragmentManager"));
        cd cdVar = this.q;
        if (cdVar != null) {
            try {
                cdVar.a(GlideException.IndentedAppendable.INDENT, (FileDescriptor) null, printWriter, new String[0]);
                throw runtimeException;
            } catch (Exception e2) {
                Log.e("FragmentManager", "Failed dumping state", e2);
                throw runtimeException;
            }
        }
        try {
            a(GlideException.IndentedAppendable.INDENT, (FileDescriptor) null, printWriter, new String[0]);
            throw runtimeException;
        } catch (Exception e3) {
            Log.e("FragmentManager", "Failed dumping state", e3);
            throw runtimeException;
        }
    }

    @Override // supwisdom.dd
    public boolean b() {
        boolean zV = v();
        w();
        return zV;
    }

    public void c(Fragment fragment) {
        if (H) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.mDetached) {
            fragment.mDetached = false;
            if (fragment.mAdded) {
                return;
            }
            if (this.f.contains(fragment)) {
                throw new IllegalStateException("Fragment already added: " + fragment);
            }
            if (H) {
                Log.v("FragmentManager", "add from attach: " + fragment);
            }
            synchronized (this.f) {
                this.f.add(fragment);
            }
            fragment.mAdded = true;
            if (l(fragment)) {
                this.u = true;
            }
        }
    }

    @Override // supwisdom.dd
    public List<Fragment> d() {
        List<Fragment> list;
        if (this.f.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.f) {
            list = (List) this.f.clone();
        }
        return list;
    }

    @Override // supwisdom.dd
    public boolean e() {
        h();
        return a((String) null, -1, 0);
    }

    public final void f() {
        this.g.values().removeAll(Collections.singleton(null));
    }

    public void g(Fragment fragment) {
        if (!fragment.mFromLayout || fragment.mPerformedCreateView) {
            return;
        }
        fragment.performCreateView(fragment.performGetLayoutInflater(fragment.mSavedFragmentState), null, fragment.mSavedFragmentState);
        View view = fragment.mView;
        if (view == null) {
            fragment.mInnerView = null;
            return;
        }
        fragment.mInnerView = view;
        view.setSaveFromParentEnabled(false);
        if (fragment.mHidden) {
            fragment.mView.setVisibility(8);
        }
        fragment.onViewCreated(fragment.mView, fragment.mSavedFragmentState);
        a(fragment, fragment.mView, fragment.mSavedFragmentState, false);
    }

    public final void h() {
        if (C()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    public fd i(Fragment fragment) {
        return this.F.c(fragment);
    }

    public ie j(Fragment fragment) {
        return this.F.d(fragment);
    }

    public void k(Fragment fragment) {
        if (H) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (fragment.mHidden) {
            return;
        }
        fragment.mHidden = true;
        fragment.mHiddenChanged = true ^ fragment.mHiddenChanged;
    }

    public void l() {
        this.x = true;
        v();
        a(0);
        this.q = null;
        this.r = null;
        this.s = null;
        if (this.j != null) {
            this.k.c();
            this.j = null;
        }
    }

    public boolean m(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        ed edVar = fragment.mFragmentManager;
        return fragment == edVar.z() && m(edVar.s);
    }

    public void n(Fragment fragment) {
        if (this.g.get(fragment.mWho) != null) {
            return;
        }
        this.g.put(fragment.mWho, fragment);
        if (fragment.mRetainInstanceChangedWhileDetached) {
            if (fragment.mRetainInstance) {
                b(fragment);
            } else {
                t(fragment);
            }
            fragment.mRetainInstanceChangedWhileDetached = false;
        }
        if (H) {
            Log.v("FragmentManager", "Added fragment to active set " + fragment);
        }
    }

    public void o(Fragment fragment) {
        if (this.g.get(fragment.mWho) == null) {
            return;
        }
        if (H) {
            Log.v("FragmentManager", "Removed fragment from active set " + fragment);
        }
        for (Fragment fragment2 : this.g.values()) {
            if (fragment2 != null && fragment.mWho.equals(fragment2.mTargetWho)) {
                fragment2.mTarget = fragment;
                fragment2.mTargetWho = null;
            }
        }
        this.g.put(fragment.mWho, null);
        t(fragment);
        String str = fragment.mTargetWho;
        if (str != null) {
            fragment.mTarget = this.g.get(str);
        }
        fragment.initState();
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.f7473a);
        if (attributeValue == null) {
            attributeValue = typedArrayObtainStyledAttributes.getString(0);
        }
        String str2 = attributeValue;
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(1, -1);
        String string = typedArrayObtainStyledAttributes.getString(2);
        typedArrayObtainStyledAttributes.recycle();
        if (str2 == null || !bd.b(context.getClassLoader(), str2)) {
            return null;
        }
        int id = view != null ? view.getId() : 0;
        if (id == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + str2);
        }
        Fragment fragmentB = resourceId != -1 ? b(resourceId) : null;
        if (fragmentB == null && string != null) {
            fragmentB = a(string);
        }
        if (fragmentB == null && id != -1) {
            fragmentB = b(id);
        }
        if (H) {
            Log.v("FragmentManager", "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + str2 + " existing=" + fragmentB);
        }
        if (fragmentB == null) {
            fragmentB = c().a(context.getClassLoader(), str2);
            fragmentB.mFromLayout = true;
            fragmentB.mFragmentId = resourceId != 0 ? resourceId : id;
            fragmentB.mContainerId = id;
            fragmentB.mTag = string;
            fragmentB.mInLayout = true;
            fragmentB.mFragmentManager = this;
            cd cdVar = this.q;
            fragmentB.mHost = cdVar;
            fragmentB.onInflate(cdVar.c(), attributeSet, fragmentB.mSavedFragmentState);
            a(fragmentB, true);
        } else {
            if (fragmentB.mInLayout) {
                throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + str2);
            }
            fragmentB.mInLayout = true;
            cd cdVar2 = this.q;
            fragmentB.mHost = cdVar2;
            fragmentB.onInflate(cdVar2.c(), attributeSet, fragmentB.mSavedFragmentState);
        }
        Fragment fragment = fragmentB;
        if (this.p >= 1 || !fragment.mFromLayout) {
            q(fragment);
        } else {
            a(fragment, 1, 0, 0, false);
        }
        View view2 = fragment.mView;
        if (view2 != null) {
            if (resourceId != 0) {
                view2.setId(resourceId);
            }
            if (fragment.mView.getTag() == null) {
                fragment.mView.setTag(string);
            }
            return fragment.mView;
        }
        throw new IllegalStateException("Fragment " + str2 + " did not create a view.");
    }

    public void p(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        if (!this.g.containsKey(fragment.mWho)) {
            if (H) {
                Log.v("FragmentManager", "Ignoring moving " + fragment + " to state " + this.p + "since it is not added to " + this);
                return;
            }
            return;
        }
        int iMin = this.p;
        if (fragment.mRemoving) {
            iMin = fragment.isInBackStack() ? Math.min(iMin, 1) : Math.min(iMin, 0);
        }
        a(fragment, iMin, fragment.getNextTransition(), fragment.getNextTransitionStyle(), false);
        if (fragment.mView != null) {
            Fragment fragmentH = h(fragment);
            if (fragmentH != null) {
                View view = fragmentH.mView;
                ViewGroup viewGroup = fragment.mContainer;
                int iIndexOfChild = viewGroup.indexOfChild(view);
                int iIndexOfChild2 = viewGroup.indexOfChild(fragment.mView);
                if (iIndexOfChild2 < iIndexOfChild) {
                    viewGroup.removeViewAt(iIndexOfChild2);
                    viewGroup.addView(fragment.mView, iIndexOfChild);
                }
            }
            if (fragment.mIsNewlyAdded && fragment.mContainer != null) {
                float f2 = fragment.mPostponedAlpha;
                if (f2 > 0.0f) {
                    fragment.mView.setAlpha(f2);
                }
                fragment.mPostponedAlpha = 0.0f;
                fragment.mIsNewlyAdded = false;
                g gVarA = a(fragment, fragment.getNextTransition(), true, fragment.getNextTransitionStyle());
                if (gVarA != null) {
                    Animation animation = gVarA.f7469a;
                    if (animation != null) {
                        fragment.mView.startAnimation(animation);
                    } else {
                        gVarA.b.setTarget(fragment.mView);
                        gVarA.b.start();
                    }
                }
            }
        }
        if (fragment.mHiddenChanged) {
            d(fragment);
        }
    }

    public void q(Fragment fragment) {
        a(fragment, this.p, 0, 0, false);
    }

    public void r(Fragment fragment) {
        if (fragment.mDeferStart) {
            if (this.d) {
                this.y = true;
            } else {
                fragment.mDeferStart = false;
                a(fragment, this.p, 0, 0, false);
            }
        }
    }

    public void s(Fragment fragment) {
        if (H) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.mBackStackNesting);
        }
        boolean z = !fragment.isInBackStack();
        if (!fragment.mDetached || z) {
            synchronized (this.f) {
                this.f.remove(fragment);
            }
            if (l(fragment)) {
                this.u = true;
            }
            fragment.mAdded = false;
            fragment.mRemoving = true;
        }
    }

    public void t(Fragment fragment) {
        if (C()) {
            if (H) {
                Log.v("FragmentManager", "Ignoring removeRetainedFragment as the state is already saved");
            }
        } else if (this.F.e(fragment) && H) {
            Log.v("FragmentManager", "Updating retained Fragments: Removed " + fragment);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Fragment fragment = this.s;
        if (fragment != null) {
            ga.a(fragment, sb);
        } else {
            ga.a(this.q, sb);
        }
        sb.append("}}");
        return sb.toString();
    }

    public final void u() {
        for (Fragment fragment : this.g.values()) {
            if (fragment != null) {
                if (fragment.getAnimatingAway() != null) {
                    int stateAfterAnimating = fragment.getStateAfterAnimating();
                    View animatingAway = fragment.getAnimatingAway();
                    Animation animation = animatingAway.getAnimation();
                    if (animation != null) {
                        animation.cancel();
                        animatingAway.clearAnimation();
                    }
                    fragment.setAnimatingAway(null);
                    a(fragment, stateAfterAnimating, 0, 0, false);
                } else if (fragment.getAnimator() != null) {
                    fragment.getAnimator().end();
                }
            }
        }
    }

    public boolean v() {
        c(true);
        boolean z = false;
        while (b(this.z, this.A)) {
            this.d = true;
            try {
                c(this.z, this.A);
                i();
                z = true;
            } catch (Throwable th) {
                i();
                throw th;
            }
        }
        I();
        t();
        f();
        return z;
    }

    public final void w() {
        if (this.E != null) {
            while (!this.E.isEmpty()) {
                this.E.remove(0).d();
            }
        }
    }

    public int x() {
        ArrayList<xc> arrayList = this.h;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public LayoutInflater.Factory2 y() {
        return this;
    }

    public Fragment z() {
        return this.t;
    }

    public final void i() {
        this.d = false;
        this.A.clear();
        this.z.clear();
    }

    public void j() {
        this.v = false;
        this.w = false;
        a(2);
    }

    public void q() {
        this.v = false;
        this.w = false;
        a(4);
    }

    public void x(Fragment fragment) {
        if (H) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.mHidden) {
            fragment.mHidden = false;
            fragment.mHiddenChanged = !fragment.mHiddenChanged;
        }
    }

    public void b(Fragment fragment) {
        if (C()) {
            if (H) {
                Log.v("FragmentManager", "Ignoring addRetainedFragment as the state is already saved");
            }
        } else if (this.F.a(fragment) && H) {
            Log.v("FragmentManager", "Updating retained Fragments: Added " + fragment);
        }
    }

    public void e(Fragment fragment) {
        if (H) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (fragment.mDetached) {
            return;
        }
        fragment.mDetached = true;
        if (fragment.mAdded) {
            if (H) {
                Log.v("FragmentManager", "remove from detach: " + fragment);
            }
            synchronized (this.f) {
                this.f.remove(fragment);
            }
            if (l(fragment)) {
                this.u = true;
            }
            fragment.mAdded = false;
        }
    }

    public final void f(Fragment fragment) {
        if (fragment == null || this.g.get(fragment.mWho) != fragment) {
            return;
        }
        fragment.performPrimaryNavigationFragmentChanged();
    }

    public final Fragment h(Fragment fragment) {
        ViewGroup viewGroup = fragment.mContainer;
        View view = fragment.mView;
        if (viewGroup != null && view != null) {
            for (int iIndexOf = this.f.indexOf(fragment) - 1; iIndexOf >= 0; iIndexOf--) {
                Fragment fragment2 = this.f.get(iIndexOf);
                if (fragment2.mContainer == viewGroup && fragment2.mView != null) {
                    return fragment2;
                }
            }
        }
        return null;
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public static class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Animation f7469a;
        public final Animator b;

        public g(Animation animation) {
            this.f7469a = animation;
            this.b = null;
            if (animation == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }

        public g(Animator animator) {
            this.f7469a = null;
            this.b = animator;
            if (animator == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }
    }

    public void w(Fragment fragment) {
        if (fragment != null && (this.g.get(fragment.mWho) != fragment || (fragment.mHost != null && fragment.getFragmentManager() != this))) {
            throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
        }
        Fragment fragment2 = this.t;
        this.t = fragment;
        f(fragment2);
        f(this.t);
    }

    public void f(Fragment fragment, boolean z) {
        Fragment fragment2 = this.s;
        if (fragment2 != null) {
            dd fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof ed) {
                ((ed) fragmentManager).f(fragment, true);
            }
        }
        for (i iVar : this.o) {
            if (!z || iVar.b) {
                iVar.f7472a.e(this, fragment);
            }
        }
    }

    public void k() {
        this.v = false;
        this.w = false;
        a(1);
    }

    public void m() {
        a(1);
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    public static class h extends AnimationSet implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ViewGroup f7470a;
        public final View b;
        public boolean c;
        public boolean d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f7471e;

        public h(Animation animation, ViewGroup viewGroup, View view) {
            super(false);
            this.f7471e = true;
            this.f7470a = viewGroup;
            this.b = view;
            addAnimation(animation);
            this.f7470a.post(this);
        }

        @Override // android.view.animation.AnimationSet, android.view.animation.Animation
        public boolean getTransformation(long j, Transformation transformation) {
            this.f7471e = true;
            if (this.c) {
                return !this.d;
            }
            if (!super.getTransformation(j, transformation)) {
                this.c = true;
                hb.a(this.f7470a, this);
            }
            return true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.c || !this.f7471e) {
                this.f7470a.endViewTransition(this.b);
                this.d = true;
            } else {
                this.f7471e = false;
                this.f7470a.post(this);
            }
        }

        @Override // android.view.animation.Animation
        public boolean getTransformation(long j, Transformation transformation, float f) {
            this.f7471e = true;
            if (this.c) {
                return !this.d;
            }
            if (!super.getTransformation(j, transformation, f)) {
                this.c = true;
                hb.a(this.f7470a, this);
            }
            return true;
        }
    }

    public boolean d(int i2) {
        return this.p >= i2;
    }

    public void r() {
        this.v = false;
        this.w = false;
        a(3);
    }

    public void d(Fragment fragment) {
        Animator animator;
        if (fragment.mView != null) {
            g gVarA = a(fragment, fragment.getNextTransition(), !fragment.mHidden, fragment.getNextTransitionStyle());
            if (gVarA != null && (animator = gVarA.b) != null) {
                animator.setTarget(fragment.mView);
                if (fragment.mHidden) {
                    if (fragment.isHideReplaced()) {
                        fragment.setHideReplaced(false);
                    } else {
                        ViewGroup viewGroup = fragment.mContainer;
                        View view = fragment.mView;
                        viewGroup.startViewTransition(view);
                        gVarA.b.addListener(new e(this, viewGroup, view, fragment));
                    }
                } else {
                    fragment.mView.setVisibility(0);
                }
                gVarA.b.start();
            } else {
                if (gVarA != null) {
                    fragment.mView.startAnimation(gVarA.f7469a);
                    gVarA.f7469a.start();
                }
                fragment.mView.setVisibility((!fragment.mHidden || fragment.isHideReplaced()) ? 0 : 8);
                if (fragment.isHideReplaced()) {
                    fragment.setHideReplaced(false);
                }
            }
        }
        if (fragment.mAdded && l(fragment)) {
            this.u = true;
        }
        fragment.mHiddenChanged = false;
        fragment.onHiddenChanged(fragment.mHidden);
    }

    public void t() {
        if (this.y) {
            this.y = false;
            H();
        }
    }

    public void h(Fragment fragment, boolean z) {
        Fragment fragment2 = this.s;
        if (fragment2 != null) {
            dd fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof ed) {
                ((ed) fragmentManager).h(fragment, true);
            }
        }
        for (i iVar : this.o) {
            if (!z || iVar.b) {
                iVar.f7472a.g(this, fragment);
            }
        }
    }

    public Fragment b(int i2) {
        for (int size = this.f.size() - 1; size >= 0; size--) {
            Fragment fragment = this.f.get(size);
            if (fragment != null && fragment.mFragmentId == i2) {
                return fragment;
            }
        }
        for (Fragment fragment2 : this.g.values()) {
            if (fragment2 != null && fragment2.mFragmentId == i2) {
                return fragment2;
            }
        }
        return null;
    }

    public void n() {
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            Fragment fragment = this.f.get(i2);
            if (fragment != null) {
                fragment.performLowMemory();
            }
        }
    }

    public void g(Fragment fragment, boolean z) {
        Fragment fragment2 = this.s;
        if (fragment2 != null) {
            dd fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof ed) {
                ((ed) fragmentManager).g(fragment, true);
            }
        }
        for (i iVar : this.o) {
            if (!z || iVar.b) {
                iVar.f7472a.f(this, fragment);
            }
        }
    }

    public final boolean l(Fragment fragment) {
        return (fragment.mHasMenu && fragment.mMenuVisible) || fragment.mChildFragmentManager.g();
    }

    public void v(Fragment fragment) {
        if (fragment.mInnerView == null) {
            return;
        }
        SparseArray<Parcelable> sparseArray = this.D;
        if (sparseArray == null) {
            this.D = new SparseArray<>();
        } else {
            sparseArray.clear();
        }
        fragment.mInnerView.saveHierarchyState(this.D);
        if (this.D.size() > 0) {
            fragment.mSavedViewState = this.D;
            this.D = null;
        }
    }

    @Override // supwisdom.dd
    public hd a() {
        return new xc(this);
    }

    @Override // supwisdom.dd
    public void a(int i2, int i3) {
        if (i2 >= 0) {
            a((k) new l(null, i2, i3), false);
            return;
        }
        throw new IllegalArgumentException("Bad id: " + i2);
    }

    public void o() {
        a(3);
    }

    public void s() {
        this.w = true;
        a(2);
    }

    public Bundle u(Fragment fragment) {
        if (this.C == null) {
            this.C = new Bundle();
        }
        fragment.performSaveInstanceState(this.C);
        d(fragment, this.C, false);
        Bundle bundle = null;
        if (!this.C.isEmpty()) {
            Bundle bundle2 = this.C;
            this.C = null;
            bundle = bundle2;
        }
        if (fragment.mView != null) {
            v(fragment);
        }
        if (fragment.mSavedViewState != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", fragment.mSavedViewState);
        }
        if (!fragment.mUserVisibleHint) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", fragment.mUserVisibleHint);
        }
        return bundle;
    }

    public final boolean a(String str, int i2, int i3) {
        v();
        c(true);
        Fragment fragment = this.t;
        if (fragment != null && i2 < 0 && str == null && fragment.getChildFragmentManager().e()) {
            return true;
        }
        boolean zA = a(this.z, this.A, str, i2, i3);
        if (zA) {
            this.d = true;
            try {
                c(this.z, this.A);
            } finally {
                i();
            }
        }
        I();
        t();
        f();
        return zA;
    }

    public Fragment b(String str) {
        Fragment fragmentFindFragmentByWho;
        for (Fragment fragment : this.g.values()) {
            if (fragment != null && (fragmentFindFragmentByWho = fragment.findFragmentByWho(str)) != null) {
                return fragmentFindFragmentByWho;
            }
        }
        return null;
    }

    public void c(int i2) {
        synchronized (this) {
            this.l.set(i2, null);
            if (this.m == null) {
                this.m = new ArrayList<>();
            }
            if (H) {
                Log.v("FragmentManager", "Freeing back stack index " + i2);
            }
            this.m.add(Integer.valueOf(i2));
        }
    }

    public void e(Fragment fragment, boolean z) {
        Fragment fragment2 = this.s;
        if (fragment2 != null) {
            dd fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof ed) {
                ((ed) fragmentManager).e(fragment, true);
            }
        }
        for (i iVar : this.o) {
            if (!z || iVar.b) {
                iVar.f7472a.d(this, fragment);
            }
        }
    }

    public int b(xc xcVar) {
        synchronized (this) {
            if (this.m != null && this.m.size() > 0) {
                int iIntValue = this.m.remove(this.m.size() - 1).intValue();
                if (H) {
                    Log.v("FragmentManager", "Adding back stack index " + iIntValue + " with " + xcVar);
                }
                this.l.set(iIntValue, xcVar);
                return iIntValue;
            }
            if (this.l == null) {
                this.l = new ArrayList<>();
            }
            int size = this.l.size();
            if (H) {
                Log.v("FragmentManager", "Setting back stack index " + size + " to " + xcVar);
            }
            this.l.add(xcVar);
            return size;
        }
    }

    public boolean g() {
        boolean zL = false;
        for (Fragment fragment : this.g.values()) {
            if (fragment != null) {
                zL = l(fragment);
            }
            if (zL) {
                return true;
            }
        }
        return false;
    }

    public final void c(boolean z) {
        if (!this.d) {
            if (this.q != null) {
                if (Looper.myLooper() == this.q.d().getLooper()) {
                    if (!z) {
                        h();
                    }
                    if (this.z == null) {
                        this.z = new ArrayList<>();
                        this.A = new ArrayList<>();
                    }
                    this.d = true;
                    try {
                        a((ArrayList<xc>) null, (ArrayList<Boolean>) null);
                        return;
                    } finally {
                        this.d = false;
                    }
                }
                throw new IllegalStateException("Must be called from main thread of fragment host");
            }
            throw new IllegalStateException("Fragment host has been destroyed");
        }
        throw new IllegalStateException("FragmentManager is already executing transactions");
    }

    @Override // supwisdom.dd
    public void a(Bundle bundle, String str, Fragment fragment) {
        if (fragment.mFragmentManager == this) {
            bundle.putString(str, fragment.mWho);
            return;
        }
        a(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        throw null;
    }

    public void b(k kVar, boolean z) {
        if (z && (this.q == null || this.x)) {
            return;
        }
        c(z);
        if (kVar.a(this.z, this.A)) {
            this.d = true;
            try {
                c(this.z, this.A);
            } finally {
                i();
            }
        }
        I();
        t();
        f();
    }

    @Override // supwisdom.dd
    public Fragment a(Bundle bundle, String str) {
        String string = bundle.getString(str);
        if (string == null) {
            return null;
        }
        Fragment fragment = this.g.get(string);
        if (fragment != null) {
            return fragment;
        }
        a(new IllegalStateException("Fragment no longer exists for key " + str + ": unique id " + string));
        throw null;
    }

    public void d(Fragment fragment, boolean z) {
        Fragment fragment2 = this.s;
        if (fragment2 != null) {
            dd fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof ed) {
                ((ed) fragmentManager).d(fragment, true);
            }
        }
        for (i iVar : this.o) {
            if (!z || iVar.b) {
                iVar.f7472a.c(this, fragment);
            }
        }
    }

    public void p() {
        I();
        f(this.t);
    }

    @Override // supwisdom.dd
    public Fragment.SavedState a(Fragment fragment) {
        Bundle bundleU;
        if (fragment.mFragmentManager == this) {
            if (fragment.mState <= 0 || (bundleU = u(fragment)) == null) {
                return null;
            }
            return new Fragment.SavedState(bundleU);
        }
        a(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        throw null;
    }

    public final void c(ArrayList<xc> arrayList, ArrayList<Boolean> arrayList2) {
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        if (arrayList2 != null && arrayList.size() == arrayList2.size()) {
            a(arrayList, arrayList2);
            int size = arrayList.size();
            int i2 = 0;
            int i3 = 0;
            while (i2 < size) {
                if (!arrayList.get(i2).p) {
                    if (i3 != i2) {
                        a(arrayList, arrayList2, i3, i2);
                    }
                    i3 = i2 + 1;
                    if (arrayList2.get(i2).booleanValue()) {
                        while (i3 < size && arrayList2.get(i3).booleanValue() && !arrayList.get(i3).p) {
                            i3++;
                        }
                    }
                    a(arrayList, arrayList2, i2, i3);
                    i2 = i3 - 1;
                }
                i2++;
            }
            if (i3 != size) {
                a(arrayList, arrayList2, i3, size);
                return;
            }
            return;
        }
        throw new IllegalStateException("Internal error with the back stack records");
    }

    @Override // supwisdom.dd
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        int size3;
        int size4;
        String str2 = str + ASN1Dump.TAB;
        if (!this.g.isEmpty()) {
            printWriter.print(str);
            printWriter.print("Active Fragments in ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(Constants.COLON_SEPARATOR);
            for (Fragment fragment : this.g.values()) {
                printWriter.print(str);
                printWriter.println(fragment);
                if (fragment != null) {
                    fragment.dump(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }
        int size5 = this.f.size();
        if (size5 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i2 = 0; i2 < size5; i2++) {
                Fragment fragment2 = this.f.get(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(fragment2.toString());
            }
        }
        ArrayList<Fragment> arrayList = this.i;
        if (arrayList != null && (size4 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i3 = 0; i3 < size4; i3++) {
                Fragment fragment3 = this.i.get(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(fragment3.toString());
            }
        }
        ArrayList<xc> arrayList2 = this.h;
        if (arrayList2 != null && (size3 = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i4 = 0; i4 < size3; i4++) {
                xc xcVar = this.h.get(i4);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i4);
                printWriter.print(": ");
                printWriter.println(xcVar.toString());
                xcVar.a(str2, printWriter);
            }
        }
        synchronized (this) {
            if (this.l != null && (size2 = this.l.size()) > 0) {
                printWriter.print(str);
                printWriter.println("Back Stack Indices:");
                for (int i5 = 0; i5 < size2; i5++) {
                    Object obj = (xc) this.l.get(i5);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i5);
                    printWriter.print(": ");
                    printWriter.println(obj);
                }
            }
            if (this.m != null && this.m.size() > 0) {
                printWriter.print(str);
                printWriter.print("mAvailBackStackIndices: ");
                printWriter.println(Arrays.toString(this.m.toArray()));
            }
        }
        ArrayList<k> arrayList3 = this.c;
        if (arrayList3 != null && (size = arrayList3.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Pending Actions:");
            for (int i6 = 0; i6 < size; i6++) {
                Object obj2 = (k) this.c.get(i6);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i6);
                printWriter.print(": ");
                printWriter.println(obj2);
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.q);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.r);
        if (this.s != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.s);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.p);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.v);
        printWriter.print(" mStopped=");
        printWriter.print(this.w);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.x);
        if (this.u) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.u);
        }
    }

    public final void b(k4<Fragment> k4Var) {
        int size = k4Var.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragmentF = k4Var.f(i2);
            if (!fragmentF.mAdded) {
                View viewRequireView = fragmentF.requireView();
                fragmentF.mPostponedAlpha = viewRequireView.getAlpha();
                viewRequireView.setAlpha(0.0f);
            }
        }
    }

    public void d(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragment2 = this.s;
        if (fragment2 != null) {
            dd fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof ed) {
                ((ed) fragmentManager).d(fragment, bundle, true);
            }
        }
        for (i iVar : this.o) {
            if (!z || iVar.b) {
                iVar.f7472a.d(this, fragment, bundle);
            }
        }
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    public static void b(ArrayList<xc> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        while (i2 < i3) {
            xc xcVar = arrayList.get(i2);
            if (arrayList2.get(i2).booleanValue()) {
                xcVar.a(-1);
                xcVar.b(i2 == i3 + (-1));
            } else {
                xcVar.a(1);
                xcVar.e();
            }
            i2++;
        }
    }

    @Override // supwisdom.dd
    public bd c() {
        if (super.c() == dd.b) {
            Fragment fragment = this.s;
            if (fragment != null) {
                return fragment.mFragmentManager.c();
            }
            a(new f());
        }
        return super.c();
    }

    public final boolean b(ArrayList<xc> arrayList, ArrayList<Boolean> arrayList2) {
        synchronized (this) {
            if (this.c != null && this.c.size() != 0) {
                int size = this.c.size();
                boolean zA = false;
                for (int i2 = 0; i2 < size; i2++) {
                    zA |= this.c.get(i2).a(arrayList, arrayList2);
                }
                this.c.clear();
                this.q.d().removeCallbacks(this.G);
                return zA;
            }
            return false;
        }
    }

    public void c(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragment2 = this.s;
        if (fragment2 != null) {
            dd fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof ed) {
                ((ed) fragmentManager).c(fragment, bundle, true);
            }
        }
        for (i iVar : this.o) {
            if (!z || iVar.b) {
                iVar.f7472a.c(this, fragment, bundle);
            }
        }
    }

    public void b(boolean z) {
        for (int size = this.f.size() - 1; size >= 0; size--) {
            Fragment fragment = this.f.get(size);
            if (fragment != null) {
                fragment.performPictureInPictureModeChanged(z);
            }
        }
    }

    public void c(Fragment fragment, boolean z) {
        Fragment fragment2 = this.s;
        if (fragment2 != null) {
            dd fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof ed) {
                ((ed) fragmentManager).c(fragment, true);
            }
        }
        for (i iVar : this.o) {
            if (!z || iVar.b) {
                iVar.f7472a.b(this, fragment);
            }
        }
    }

    public boolean b(Menu menu) {
        if (this.p < 1) {
            return false;
        }
        boolean z = false;
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            Fragment fragment = this.f.get(i2);
            if (fragment != null && fragment.performPrepareOptionsMenu(menu)) {
                z = true;
            }
        }
        return z;
    }

    public boolean b(MenuItem menuItem) {
        if (this.p < 1) {
            return false;
        }
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            Fragment fragment = this.f.get(i2);
            if (fragment != null && fragment.performOptionsItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void b(Fragment fragment, Context context, boolean z) {
        Fragment fragment2 = this.s;
        if (fragment2 != null) {
            dd fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof ed) {
                ((ed) fragmentManager).b(fragment, context, true);
            }
        }
        for (i iVar : this.o) {
            if (!z || iVar.b) {
                iVar.f7472a.b(this, fragment, context);
            }
        }
    }

    public void b(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragment2 = this.s;
        if (fragment2 != null) {
            dd fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof ed) {
                ((ed) fragmentManager).b(fragment, bundle, true);
            }
        }
        for (i iVar : this.o) {
            if (!z || iVar.b) {
                iVar.f7472a.b(this, fragment, bundle);
            }
        }
    }

    public void b(Fragment fragment, boolean z) {
        Fragment fragment2 = this.s;
        if (fragment2 != null) {
            dd fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof ed) {
                ((ed) fragmentManager).b(fragment, true);
            }
        }
        for (i iVar : this.o) {
            if (!z || iVar.b) {
                iVar.f7472a.a(this, fragment);
            }
        }
    }

    public static g a(float f2, float f3, float f4, float f5) {
        AnimationSet animationSet = new AnimationSet(false);
        ScaleAnimation scaleAnimation = new ScaleAnimation(f2, f3, f2, f3, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(I);
        scaleAnimation.setDuration(220L);
        animationSet.addAnimation(scaleAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(f4, f5);
        alphaAnimation.setInterpolator(J);
        alphaAnimation.setDuration(220L);
        animationSet.addAnimation(alphaAnimation);
        return new g(animationSet);
    }

    public static g a(float f2, float f3) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(f2, f3);
        alphaAnimation.setInterpolator(J);
        alphaAnimation.setDuration(220L);
        return new g(alphaAnimation);
    }

    public g a(Fragment fragment, int i2, boolean z, int i3) {
        int iB;
        int nextAnim = fragment.getNextAnim();
        boolean z2 = false;
        fragment.setNextAnim(0);
        ViewGroup viewGroup = fragment.mContainer;
        if (viewGroup != null && viewGroup.getLayoutTransition() != null) {
            return null;
        }
        Animation animationOnCreateAnimation = fragment.onCreateAnimation(i2, z, nextAnim);
        if (animationOnCreateAnimation != null) {
            return new g(animationOnCreateAnimation);
        }
        Animator animatorOnCreateAnimator = fragment.onCreateAnimator(i2, z, nextAnim);
        if (animatorOnCreateAnimator != null) {
            return new g(animatorOnCreateAnimator);
        }
        if (nextAnim != 0) {
            boolean zEquals = "anim".equals(this.q.c().getResources().getResourceTypeName(nextAnim));
            if (zEquals) {
                try {
                    Animation animationLoadAnimation = AnimationUtils.loadAnimation(this.q.c(), nextAnim);
                    if (animationLoadAnimation != null) {
                        return new g(animationLoadAnimation);
                    }
                    z2 = true;
                } catch (Resources.NotFoundException e2) {
                    throw e2;
                } catch (RuntimeException unused) {
                }
            }
            if (!z2) {
                try {
                    Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(this.q.c(), nextAnim);
                    if (animatorLoadAnimator != null) {
                        return new g(animatorLoadAnimator);
                    }
                } catch (RuntimeException e3) {
                    if (!zEquals) {
                        Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(this.q.c(), nextAnim);
                        if (animationLoadAnimation2 != null) {
                            return new g(animationLoadAnimation2);
                        }
                    } else {
                        throw e3;
                    }
                }
            }
        }
        if (i2 == 0 || (iB = b(i2, z)) < 0) {
            return null;
        }
        switch (iB) {
            case 1:
                return a(1.125f, 1.0f, 0.0f, 1.0f);
            case 2:
                return a(1.0f, 0.975f, 1.0f, 0.0f);
            case 3:
                return a(0.975f, 1.0f, 0.0f, 1.0f);
            case 4:
                return a(1.0f, 1.075f, 1.0f, 0.0f);
            case 5:
                return a(0.0f, 1.0f);
            case 6:
                return a(1.0f, 0.0f);
            default:
                if (i3 == 0 && this.q.h()) {
                    i3 = this.q.g();
                }
                if (i3 == 0) {
                }
                return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:151:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0405  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x04d9  */
    /* JADX WARN: Removed duplicated region for block: B:275:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(androidx.fragment.app.Fragment r19, int r20, int r21, int r22, boolean r23) {
        /*
            Method dump skipped, instruction units count: 1282
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.ed.a(androidx.fragment.app.Fragment, int, int, int, boolean):void");
    }

    public final void a(Fragment fragment, g gVar, int i2) {
        View view = fragment.mView;
        ViewGroup viewGroup = fragment.mContainer;
        viewGroup.startViewTransition(view);
        fragment.setStateAfterAnimating(i2);
        if (gVar.f7469a != null) {
            h hVar = new h(gVar.f7469a, viewGroup, view);
            fragment.setAnimatingAway(fragment.mView);
            hVar.setAnimationListener(new c(viewGroup, fragment));
            fragment.mView.startAnimation(hVar);
            return;
        }
        Animator animator = gVar.b;
        fragment.setAnimator(animator);
        animator.addListener(new d(viewGroup, view, fragment));
        animator.setTarget(fragment.mView);
        animator.start();
    }

    public void a(int i2, boolean z) {
        cd cdVar;
        if (this.q == null && i2 != 0) {
            throw new IllegalStateException("No activity");
        }
        if (z || i2 != this.p) {
            this.p = i2;
            int size = this.f.size();
            for (int i3 = 0; i3 < size; i3++) {
                p(this.f.get(i3));
            }
            for (Fragment fragment : this.g.values()) {
                if (fragment != null && (fragment.mRemoving || fragment.mDetached)) {
                    if (!fragment.mIsNewlyAdded) {
                        p(fragment);
                    }
                }
            }
            H();
            if (this.u && (cdVar = this.q) != null && this.p == 4) {
                cdVar.i();
                this.u = false;
            }
        }
    }

    public void a(Fragment fragment, boolean z) {
        if (H) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        n(fragment);
        if (fragment.mDetached) {
            return;
        }
        if (!this.f.contains(fragment)) {
            synchronized (this.f) {
                this.f.add(fragment);
            }
            fragment.mAdded = true;
            fragment.mRemoving = false;
            if (fragment.mView == null) {
                fragment.mHiddenChanged = false;
            }
            if (l(fragment)) {
                this.u = true;
            }
            if (z) {
                q(fragment);
                return;
            }
            return;
        }
        throw new IllegalStateException("Fragment already added: " + fragment);
    }

    @Override // supwisdom.dd
    public Fragment a(String str) {
        if (str != null) {
            for (int size = this.f.size() - 1; size >= 0; size--) {
                Fragment fragment = this.f.get(size);
                if (fragment != null && str.equals(fragment.mTag)) {
                    return fragment;
                }
            }
        }
        if (str == null) {
            return null;
        }
        for (Fragment fragment2 : this.g.values()) {
            if (fragment2 != null && str.equals(fragment2.mTag)) {
                return fragment2;
            }
        }
        return null;
    }

    public void a(k kVar, boolean z) {
        if (!z) {
            h();
        }
        synchronized (this) {
            if (!this.x && this.q != null) {
                if (this.c == null) {
                    this.c = new ArrayList<>();
                }
                this.c.add(kVar);
                G();
                return;
            }
            if (!z) {
                throw new IllegalStateException("Activity has been destroyed");
            }
        }
    }

    public void a(int i2, xc xcVar) {
        synchronized (this) {
            if (this.l == null) {
                this.l = new ArrayList<>();
            }
            int size = this.l.size();
            if (i2 < size) {
                if (H) {
                    Log.v("FragmentManager", "Setting back stack index " + i2 + " to " + xcVar);
                }
                this.l.set(i2, xcVar);
            } else {
                while (size < i2) {
                    this.l.add(null);
                    if (this.m == null) {
                        this.m = new ArrayList<>();
                    }
                    if (H) {
                        Log.v("FragmentManager", "Adding available back stack index " + size);
                    }
                    this.m.add(Integer.valueOf(size));
                    size++;
                }
                if (H) {
                    Log.v("FragmentManager", "Adding back stack index " + i2 + " with " + xcVar);
                }
                this.l.add(xcVar);
            }
        }
    }

    public final void a(ArrayList<xc> arrayList, ArrayList<Boolean> arrayList2) {
        int iIndexOf;
        int iIndexOf2;
        ArrayList<m> arrayList3 = this.E;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i2 = 0;
        while (i2 < size) {
            m mVar = this.E.get(i2);
            if (arrayList != null && !mVar.f7475a && (iIndexOf2 = arrayList.indexOf(mVar.b)) != -1 && arrayList2.get(iIndexOf2).booleanValue()) {
                this.E.remove(i2);
                i2--;
                size--;
                mVar.c();
            } else if (mVar.e() || (arrayList != null && mVar.b.a(arrayList, 0, arrayList.size()))) {
                this.E.remove(i2);
                i2--;
                size--;
                if (arrayList != null && !mVar.f7475a && (iIndexOf = arrayList.indexOf(mVar.b)) != -1 && arrayList2.get(iIndexOf).booleanValue()) {
                    mVar.c();
                } else {
                    mVar.d();
                }
            }
            i2++;
        }
    }

    public final void a(ArrayList<xc> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3) {
        int i4;
        int i5;
        int i6 = i2;
        boolean z = arrayList.get(i6).p;
        ArrayList<Fragment> arrayList3 = this.B;
        if (arrayList3 == null) {
            this.B = new ArrayList<>();
        } else {
            arrayList3.clear();
        }
        this.B.addAll(this.f);
        Fragment fragmentZ = z();
        boolean z2 = false;
        for (int i7 = i6; i7 < i3; i7++) {
            xc xcVar = arrayList.get(i7);
            if (!arrayList2.get(i7).booleanValue()) {
                fragmentZ = xcVar.a(this.B, fragmentZ);
            } else {
                fragmentZ = xcVar.b(this.B, fragmentZ);
            }
            z2 = z2 || xcVar.h;
        }
        this.B.clear();
        if (!z) {
            id.a(this, arrayList, arrayList2, i2, i3, false);
        }
        b(arrayList, arrayList2, i2, i3);
        if (z) {
            k4<Fragment> k4Var = new k4<>();
            a(k4Var);
            int iA = a(arrayList, arrayList2, i2, i3, k4Var);
            b(k4Var);
            i4 = iA;
        } else {
            i4 = i3;
        }
        if (i4 != i6 && z) {
            id.a(this, arrayList, arrayList2, i2, i4, true);
            a(this.p, true);
        }
        while (i6 < i3) {
            xc xcVar2 = arrayList.get(i6);
            if (arrayList2.get(i6).booleanValue() && (i5 = xcVar2.t) >= 0) {
                c(i5);
                xcVar2.t = -1;
            }
            xcVar2.h();
            i6++;
        }
        if (z2) {
            E();
        }
    }

    public final int a(ArrayList<xc> arrayList, ArrayList<Boolean> arrayList2, int i2, int i3, k4<Fragment> k4Var) {
        int i4 = i3;
        for (int i5 = i3 - 1; i5 >= i2; i5--) {
            xc xcVar = arrayList.get(i5);
            boolean zBooleanValue = arrayList2.get(i5).booleanValue();
            if (xcVar.g() && !xcVar.a(arrayList, i5 + 1, i3)) {
                if (this.E == null) {
                    this.E = new ArrayList<>();
                }
                m mVar = new m(xcVar, zBooleanValue);
                this.E.add(mVar);
                xcVar.a(mVar);
                if (zBooleanValue) {
                    xcVar.e();
                } else {
                    xcVar.b(false);
                }
                i4--;
                if (i5 != i4) {
                    arrayList.remove(i5);
                    arrayList.add(i4, xcVar);
                }
                a(k4Var);
            }
        }
        return i4;
    }

    public void a(xc xcVar, boolean z, boolean z2, boolean z3) {
        if (z) {
            xcVar.b(z3);
        } else {
            xcVar.e();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(xcVar);
        arrayList2.add(Boolean.valueOf(z));
        if (z2) {
            id.a(this, (ArrayList<xc>) arrayList, (ArrayList<Boolean>) arrayList2, 0, 1, true);
        }
        if (z3) {
            a(this.p, true);
        }
        for (Fragment fragment : this.g.values()) {
            if (fragment != null && fragment.mView != null && fragment.mIsNewlyAdded && xcVar.b(fragment.mContainerId)) {
                float f2 = fragment.mPostponedAlpha;
                if (f2 > 0.0f) {
                    fragment.mView.setAlpha(f2);
                }
                if (z3) {
                    fragment.mPostponedAlpha = 0.0f;
                } else {
                    fragment.mPostponedAlpha = -1.0f;
                    fragment.mIsNewlyAdded = false;
                }
            }
        }
    }

    public final void a(k4<Fragment> k4Var) {
        int i2 = this.p;
        if (i2 < 1) {
            return;
        }
        int iMin = Math.min(i2, 3);
        int size = this.f.size();
        for (int i3 = 0; i3 < size; i3++) {
            Fragment fragment = this.f.get(i3);
            if (fragment.mState < iMin) {
                a(fragment, iMin, fragment.getNextAnim(), fragment.getNextTransition(), false);
                if (fragment.mView != null && !fragment.mHidden && fragment.mIsNewlyAdded) {
                    k4Var.add(fragment);
                }
            }
        }
    }

    public void a(xc xcVar) {
        if (this.h == null) {
            this.h = new ArrayList<>();
        }
        this.h.add(xcVar);
    }

    public boolean a(ArrayList<xc> arrayList, ArrayList<Boolean> arrayList2, String str, int i2, int i3) {
        int i4;
        ArrayList<xc> arrayList3 = this.h;
        if (arrayList3 == null) {
            return false;
        }
        if (str == null && i2 < 0 && (i3 & 1) == 0) {
            int size = arrayList3.size() - 1;
            if (size < 0) {
                return false;
            }
            arrayList.add(this.h.remove(size));
            arrayList2.add(true);
        } else {
            if (str != null || i2 >= 0) {
                int size2 = this.h.size() - 1;
                while (size2 >= 0) {
                    xc xcVar = this.h.get(size2);
                    if ((str != null && str.equals(xcVar.f())) || (i2 >= 0 && i2 == xcVar.t)) {
                        break;
                    }
                    size2--;
                }
                if (size2 < 0) {
                    return false;
                }
                if ((i3 & 1) != 0) {
                    while (true) {
                        size2--;
                        if (size2 < 0) {
                            break;
                        }
                        xc xcVar2 = this.h.get(size2);
                        if (str == null || !str.equals(xcVar2.f())) {
                            if (i2 < 0 || i2 != xcVar2.t) {
                                break;
                            }
                        }
                    }
                }
                i4 = size2;
            } else {
                i4 = -1;
            }
            if (i4 == this.h.size() - 1) {
                return false;
            }
            for (int size3 = this.h.size() - 1; size3 > i4; size3--) {
                arrayList.add(this.h.remove(size3));
                arrayList2.add(true);
            }
        }
        return true;
    }

    public void a(Parcelable parcelable) {
        FragmentState next;
        if (parcelable == null) {
            return;
        }
        FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
        if (fragmentManagerState.f1299a == null) {
            return;
        }
        for (Fragment fragment : this.F.c()) {
            if (H) {
                Log.v("FragmentManager", "restoreSaveState: re-attaching retained " + fragment);
            }
            Iterator<FragmentState> it = fragmentManagerState.f1299a.iterator();
            while (true) {
                if (it.hasNext()) {
                    next = it.next();
                    if (next.b.equals(fragment.mWho)) {
                        break;
                    }
                } else {
                    next = null;
                    break;
                }
            }
            if (next == null) {
                if (H) {
                    Log.v("FragmentManager", "Discarding retained Fragment " + fragment + " that was not found in the set of active Fragments " + fragmentManagerState.f1299a);
                }
                a(fragment, 1, 0, 0, false);
                fragment.mRemoving = true;
                a(fragment, 0, 0, 0, false);
            } else {
                next.n = fragment;
                fragment.mSavedViewState = null;
                fragment.mBackStackNesting = 0;
                fragment.mInLayout = false;
                fragment.mAdded = false;
                Fragment fragment2 = fragment.mTarget;
                fragment.mTargetWho = fragment2 != null ? fragment2.mWho : null;
                fragment.mTarget = null;
                Bundle bundle = next.m;
                if (bundle != null) {
                    bundle.setClassLoader(this.q.c().getClassLoader());
                    fragment.mSavedViewState = next.m.getSparseParcelableArray("android:view_state");
                    fragment.mSavedFragmentState = next.m;
                }
            }
        }
        this.g.clear();
        for (FragmentState fragmentState : fragmentManagerState.f1299a) {
            if (fragmentState != null) {
                Fragment fragmentA = fragmentState.a(this.q.c().getClassLoader(), c());
                fragmentA.mFragmentManager = this;
                if (H) {
                    Log.v("FragmentManager", "restoreSaveState: active (" + fragmentA.mWho + "): " + fragmentA);
                }
                this.g.put(fragmentA.mWho, fragmentA);
                fragmentState.n = null;
            }
        }
        this.f.clear();
        ArrayList<String> arrayList = fragmentManagerState.b;
        if (arrayList != null) {
            for (String str : arrayList) {
                Fragment fragment3 = this.g.get(str);
                if (fragment3 != null) {
                    fragment3.mAdded = true;
                    if (H) {
                        Log.v("FragmentManager", "restoreSaveState: added (" + str + "): " + fragment3);
                    }
                    if (!this.f.contains(fragment3)) {
                        synchronized (this.f) {
                            this.f.add(fragment3);
                        }
                    } else {
                        throw new IllegalStateException("Already added " + fragment3);
                    }
                } else {
                    a(new IllegalStateException("No instantiated fragment for (" + str + ")"));
                    throw null;
                }
            }
        }
        if (fragmentManagerState.c != null) {
            this.h = new ArrayList<>(fragmentManagerState.c.length);
            int i2 = 0;
            while (true) {
                BackStackState[] backStackStateArr = fragmentManagerState.c;
                if (i2 >= backStackStateArr.length) {
                    break;
                }
                xc xcVarA = backStackStateArr[i2].a(this);
                if (H) {
                    Log.v("FragmentManager", "restoreAllState: back stack #" + i2 + " (index " + xcVarA.t + "): " + xcVarA);
                    PrintWriter printWriter = new PrintWriter(new ha("FragmentManager"));
                    xcVarA.a(GlideException.IndentedAppendable.INDENT, printWriter, false);
                    printWriter.close();
                }
                this.h.add(xcVarA);
                int i3 = xcVarA.t;
                if (i3 >= 0) {
                    a(i3, xcVarA);
                }
                i2++;
            }
        } else {
            this.h = null;
        }
        String str2 = fragmentManagerState.d;
        if (str2 != null) {
            Fragment fragment4 = this.g.get(str2);
            this.t = fragment4;
            f(fragment4);
        }
        this.f7463e = fragmentManagerState.f1300e;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void a(cd cdVar, zc zcVar, Fragment fragment) {
        if (this.q == null) {
            this.q = cdVar;
            this.r = zcVar;
            this.s = fragment;
            if (fragment != null) {
                I();
            }
            if (cdVar instanceof p0) {
                p0 p0Var = (p0) cdVar;
                this.j = p0Var.getOnBackPressedDispatcher();
                xd xdVar = p0Var;
                if (fragment != null) {
                    xdVar = fragment;
                }
                this.j.a(xdVar, this.k);
            }
            if (fragment != null) {
                this.F = fragment.mFragmentManager.i(fragment);
                return;
            } else if (cdVar instanceof je) {
                this.F = fd.a(((je) cdVar).getViewModelStore());
                return;
            } else {
                this.F = new fd(false);
                return;
            }
        }
        throw new IllegalStateException("Already attached");
    }

    public final void a(int i2) {
        try {
            this.d = true;
            a(i2, false);
            this.d = false;
            v();
        } catch (Throwable th) {
            this.d = false;
            throw th;
        }
    }

    public void a(boolean z) {
        for (int size = this.f.size() - 1; size >= 0; size--) {
            Fragment fragment = this.f.get(size);
            if (fragment != null) {
                fragment.performMultiWindowModeChanged(z);
            }
        }
    }

    public void a(Configuration configuration) {
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            Fragment fragment = this.f.get(i2);
            if (fragment != null) {
                fragment.performConfigurationChanged(configuration);
            }
        }
    }

    public boolean a(Menu menu, MenuInflater menuInflater) {
        if (this.p < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        boolean z = false;
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            Fragment fragment = this.f.get(i2);
            if (fragment != null && fragment.performCreateOptionsMenu(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(fragment);
                z = true;
            }
        }
        if (this.i != null) {
            for (int i3 = 0; i3 < this.i.size(); i3++) {
                Fragment fragment2 = this.i.get(i3);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.onDestroyOptionsMenu();
                }
            }
        }
        this.i = arrayList;
        return z;
    }

    public boolean a(MenuItem menuItem) {
        if (this.p < 1) {
            return false;
        }
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            Fragment fragment = this.f.get(i2);
            if (fragment != null && fragment.performContextItemSelected(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void a(Menu menu) {
        if (this.p < 1) {
            return;
        }
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            Fragment fragment = this.f.get(i2);
            if (fragment != null) {
                fragment.performOptionsMenuClosed(menu);
            }
        }
    }

    public void a(Fragment fragment, Lifecycle.State state) {
        if (this.g.get(fragment.mWho) == fragment && (fragment.mHost == null || fragment.getFragmentManager() == this)) {
            fragment.mMaxState = state;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    public void a(Fragment fragment, Context context, boolean z) {
        Fragment fragment2 = this.s;
        if (fragment2 != null) {
            dd fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof ed) {
                ((ed) fragmentManager).a(fragment, context, true);
            }
        }
        for (i iVar : this.o) {
            if (!z || iVar.b) {
                iVar.f7472a.a(this, fragment, context);
            }
        }
    }

    public void a(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragment2 = this.s;
        if (fragment2 != null) {
            dd fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof ed) {
                ((ed) fragmentManager).a(fragment, bundle, true);
            }
        }
        for (i iVar : this.o) {
            if (!z || iVar.b) {
                iVar.f7472a.a(this, fragment, bundle);
            }
        }
    }

    public void a(Fragment fragment, View view, Bundle bundle, boolean z) {
        Fragment fragment2 = this.s;
        if (fragment2 != null) {
            dd fragmentManager = fragment2.getFragmentManager();
            if (fragmentManager instanceof ed) {
                ((ed) fragmentManager).a(fragment, view, bundle, true);
            }
        }
        for (i iVar : this.o) {
            if (!z || iVar.b) {
                iVar.f7472a.a(this, fragment, view, bundle);
            }
        }
    }
}
