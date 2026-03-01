package supwisdom;

import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import com.bumptech.glide.load.engine.GlideException;
import com.taobao.weex.el.parse.Operators;
import java.io.PrintWriter;
import java.util.ArrayList;
import supwisdom.ed;
import supwisdom.hd;

/* JADX INFO: compiled from: BackStackRecord.java */
/* JADX INFO: loaded from: classes.dex */
public final class xc extends hd implements ed.k {
    public final ed r;
    public boolean s;
    public int t = -1;

    public xc(ed edVar) {
        this.r = edVar;
    }

    public void a(String str, PrintWriter printWriter) {
        a(str, printWriter, true);
    }

    @Override // supwisdom.hd
    public hd b(Fragment fragment) {
        ed edVar = fragment.mFragmentManager;
        if (edVar == null || edVar == this.r) {
            super.b(fragment);
            return this;
        }
        throw new IllegalStateException("Cannot detach Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    @Override // supwisdom.hd
    public hd c(Fragment fragment) {
        ed edVar = fragment.mFragmentManager;
        if (edVar == null || edVar == this.r) {
            super.c(fragment);
            return this;
        }
        throw new IllegalStateException("Cannot remove Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    public void e() {
        int size = this.f7826a.size();
        for (int i = 0; i < size; i++) {
            hd.a aVar = this.f7826a.get(i);
            Fragment fragment = aVar.b;
            if (fragment != null) {
                fragment.setNextTransition(this.f, this.g);
            }
            switch (aVar.f7828a) {
                case 1:
                    fragment.setNextAnim(aVar.c);
                    this.r.a(fragment, false);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.f7828a);
                case 3:
                    fragment.setNextAnim(aVar.d);
                    this.r.s(fragment);
                    break;
                case 4:
                    fragment.setNextAnim(aVar.d);
                    this.r.k(fragment);
                    break;
                case 5:
                    fragment.setNextAnim(aVar.c);
                    this.r.x(fragment);
                    break;
                case 6:
                    fragment.setNextAnim(aVar.d);
                    this.r.e(fragment);
                    break;
                case 7:
                    fragment.setNextAnim(aVar.c);
                    this.r.c(fragment);
                    break;
                case 8:
                    this.r.w(fragment);
                    break;
                case 9:
                    this.r.w(null);
                    break;
                case 10:
                    this.r.a(fragment, aVar.h);
                    break;
            }
            if (!this.p && aVar.f7828a != 1 && fragment != null) {
                this.r.p(fragment);
            }
        }
        if (this.p) {
            return;
        }
        ed edVar = this.r;
        edVar.a(edVar.p, true);
    }

    public String f() {
        return this.i;
    }

    public boolean g() {
        for (int i = 0; i < this.f7826a.size(); i++) {
            if (b(this.f7826a.get(i))) {
                return true;
            }
        }
        return false;
    }

    public void h() {
        if (this.q != null) {
            for (int i = 0; i < this.q.size(); i++) {
                this.q.get(i).run();
            }
            this.q = null;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.t >= 0) {
            sb.append(" #");
            sb.append(this.t);
        }
        if (this.i != null) {
            sb.append(Operators.SPACE_STR);
            sb.append(this.i);
        }
        sb.append(Operators.BLOCK_END_STR);
        return sb.toString();
    }

    public void a(String str, PrintWriter printWriter, boolean z) {
        String str2;
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.i);
            printWriter.print(" mIndex=");
            printWriter.print(this.t);
            printWriter.print(" mCommitted=");
            printWriter.println(this.s);
            if (this.f != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.f));
                printWriter.print(" mTransitionStyle=#");
                printWriter.println(Integer.toHexString(this.g));
            }
            if (this.b != 0 || this.c != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.b));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.c));
            }
            if (this.d != 0 || this.f7827e != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.d));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.f7827e));
            }
            if (this.j != 0 || this.k != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.j));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.k);
            }
            if (this.l != 0 || this.m != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.l));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.m);
            }
        }
        if (this.f7826a.isEmpty()) {
            return;
        }
        printWriter.print(str);
        printWriter.println("Operations:");
        int size = this.f7826a.size();
        for (int i = 0; i < size; i++) {
            hd.a aVar = this.f7826a.get(i);
            switch (aVar.f7828a) {
                case 0:
                    str2 = "NULL";
                    break;
                case 1:
                    str2 = "ADD";
                    break;
                case 2:
                    str2 = "REPLACE";
                    break;
                case 3:
                    str2 = "REMOVE";
                    break;
                case 4:
                    str2 = "HIDE";
                    break;
                case 5:
                    str2 = "SHOW";
                    break;
                case 6:
                    str2 = "DETACH";
                    break;
                case 7:
                    str2 = "ATTACH";
                    break;
                case 8:
                    str2 = "SET_PRIMARY_NAV";
                    break;
                case 9:
                    str2 = "UNSET_PRIMARY_NAV";
                    break;
                case 10:
                    str2 = "OP_SET_MAX_LIFECYCLE";
                    break;
                default:
                    str2 = "cmd=" + aVar.f7828a;
                    break;
            }
            printWriter.print(str);
            printWriter.print("  Op #");
            printWriter.print(i);
            printWriter.print(": ");
            printWriter.print(str2);
            printWriter.print(Operators.SPACE_STR);
            printWriter.println(aVar.b);
            if (z) {
                if (aVar.c != 0 || aVar.d != 0) {
                    printWriter.print(str);
                    printWriter.print("enterAnim=#");
                    printWriter.print(Integer.toHexString(aVar.c));
                    printWriter.print(" exitAnim=#");
                    printWriter.println(Integer.toHexString(aVar.d));
                }
                if (aVar.f7829e != 0 || aVar.f != 0) {
                    printWriter.print(str);
                    printWriter.print("popEnterAnim=#");
                    printWriter.print(Integer.toHexString(aVar.f7829e));
                    printWriter.print(" popExitAnim=#");
                    printWriter.println(Integer.toHexString(aVar.f));
                }
            }
        }
    }

    @Override // supwisdom.hd
    public int b() {
        return a(true);
    }

    @Override // supwisdom.hd
    public void c() {
        d();
        this.r.b((ed.k) this, true);
    }

    public boolean b(int i) {
        int size = this.f7826a.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragment = this.f7826a.get(i2).b;
            int i3 = fragment != null ? fragment.mContainerId : 0;
            if (i3 != 0 && i3 == i) {
                return true;
            }
        }
        return false;
    }

    public void b(boolean z) {
        for (int size = this.f7826a.size() - 1; size >= 0; size--) {
            hd.a aVar = this.f7826a.get(size);
            Fragment fragment = aVar.b;
            if (fragment != null) {
                fragment.setNextTransition(ed.e(this.f), this.g);
            }
            switch (aVar.f7828a) {
                case 1:
                    fragment.setNextAnim(aVar.f);
                    this.r.s(fragment);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.f7828a);
                case 3:
                    fragment.setNextAnim(aVar.f7829e);
                    this.r.a(fragment, false);
                    break;
                case 4:
                    fragment.setNextAnim(aVar.f7829e);
                    this.r.x(fragment);
                    break;
                case 5:
                    fragment.setNextAnim(aVar.f);
                    this.r.k(fragment);
                    break;
                case 6:
                    fragment.setNextAnim(aVar.f7829e);
                    this.r.c(fragment);
                    break;
                case 7:
                    fragment.setNextAnim(aVar.f);
                    this.r.e(fragment);
                    break;
                case 8:
                    this.r.w(null);
                    break;
                case 9:
                    this.r.w(fragment);
                    break;
                case 10:
                    this.r.a(fragment, aVar.g);
                    break;
            }
            if (!this.p && aVar.f7828a != 3 && fragment != null) {
                this.r.p(fragment);
            }
        }
        if (this.p || !z) {
            return;
        }
        ed edVar = this.r;
        edVar.a(edVar.p, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.fragment.app.Fragment b(java.util.ArrayList<androidx.fragment.app.Fragment> r6, androidx.fragment.app.Fragment r7) {
        /*
            r5 = this;
            java.util.ArrayList<supwisdom.hd$a> r0 = r5.f7826a
            int r0 = r0.size()
            r1 = 1
            int r0 = r0 - r1
        L8:
            if (r0 < 0) goto L35
            java.util.ArrayList<supwisdom.hd$a> r2 = r5.f7826a
            java.lang.Object r2 = r2.get(r0)
            supwisdom.hd$a r2 = (supwisdom.hd.a) r2
            int r3 = r2.f7828a
            if (r3 == r1) goto L2d
            r4 = 3
            if (r3 == r4) goto L27
            switch(r3) {
                case 6: goto L27;
                case 7: goto L2d;
                case 8: goto L25;
                case 9: goto L22;
                case 10: goto L1d;
                default: goto L1c;
            }
        L1c:
            goto L32
        L1d:
            androidx.lifecycle.Lifecycle$State r3 = r2.g
            r2.h = r3
            goto L32
        L22:
            androidx.fragment.app.Fragment r7 = r2.b
            goto L32
        L25:
            r7 = 0
            goto L32
        L27:
            androidx.fragment.app.Fragment r2 = r2.b
            r6.add(r2)
            goto L32
        L2d:
            androidx.fragment.app.Fragment r2 = r2.b
            r6.remove(r2)
        L32:
            int r0 = r0 + (-1)
            goto L8
        L35:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.xc.b(java.util.ArrayList, androidx.fragment.app.Fragment):androidx.fragment.app.Fragment");
    }

    public static boolean b(hd.a aVar) {
        Fragment fragment = aVar.b;
        return (fragment == null || !fragment.mAdded || fragment.mView == null || fragment.mDetached || fragment.mHidden || !fragment.isPostponed()) ? false : true;
    }

    @Override // supwisdom.hd
    public void a(int i, Fragment fragment, String str, int i2) {
        super.a(i, fragment, str, i2);
        fragment.mFragmentManager = this.r;
    }

    @Override // supwisdom.hd
    public hd a(Fragment fragment, Lifecycle.State state) {
        if (fragment.mFragmentManager == this.r) {
            if (state.isAtLeast(Lifecycle.State.CREATED)) {
                super.a(fragment, state);
                return this;
            }
            throw new IllegalArgumentException("Cannot set maximum Lifecycle below " + Lifecycle.State.CREATED);
        }
        throw new IllegalArgumentException("Cannot setMaxLifecycle for Fragment not attached to FragmentManager " + this.r);
    }

    public void a(int i) {
        if (this.h) {
            if (ed.H) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i);
            }
            int size = this.f7826a.size();
            for (int i2 = 0; i2 < size; i2++) {
                hd.a aVar = this.f7826a.get(i2);
                Fragment fragment = aVar.b;
                if (fragment != null) {
                    fragment.mBackStackNesting += i;
                    if (ed.H) {
                        Log.v("FragmentManager", "Bump nesting of " + aVar.b + " to " + aVar.b.mBackStackNesting);
                    }
                }
            }
        }
    }

    @Override // supwisdom.hd
    public int a() {
        return a(false);
    }

    public int a(boolean z) {
        if (!this.s) {
            if (ed.H) {
                Log.v("FragmentManager", "Commit: " + this);
                PrintWriter printWriter = new PrintWriter(new ha("FragmentManager"));
                a(GlideException.IndentedAppendable.INDENT, printWriter);
                printWriter.close();
            }
            this.s = true;
            if (this.h) {
                this.t = this.r.b(this);
            } else {
                this.t = -1;
            }
            this.r.a(this, z);
            return this.t;
        }
        throw new IllegalStateException("commit already called");
    }

    @Override // supwisdom.ed.k
    public boolean a(ArrayList<xc> arrayList, ArrayList<Boolean> arrayList2) {
        if (ed.H) {
            Log.v("FragmentManager", "Run: " + this);
        }
        arrayList.add(this);
        arrayList2.add(false);
        if (!this.h) {
            return true;
        }
        this.r.a(this);
        return true;
    }

    public boolean a(ArrayList<xc> arrayList, int i, int i2) {
        if (i2 == i) {
            return false;
        }
        int size = this.f7826a.size();
        int i3 = -1;
        for (int i4 = 0; i4 < size; i4++) {
            Fragment fragment = this.f7826a.get(i4).b;
            int i5 = fragment != null ? fragment.mContainerId : 0;
            if (i5 != 0 && i5 != i3) {
                for (int i6 = i; i6 < i2; i6++) {
                    xc xcVar = arrayList.get(i6);
                    int size2 = xcVar.f7826a.size();
                    for (int i7 = 0; i7 < size2; i7++) {
                        Fragment fragment2 = xcVar.f7826a.get(i7).b;
                        if ((fragment2 != null ? fragment2.mContainerId : 0) == i5) {
                            return true;
                        }
                    }
                }
                i3 = i5;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.fragment.app.Fragment a(java.util.ArrayList<androidx.fragment.app.Fragment> r17, androidx.fragment.app.Fragment r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r3 = r18
            r4 = 0
        L7:
            java.util.ArrayList<supwisdom.hd$a> r5 = r0.f7826a
            int r5 = r5.size()
            if (r4 >= r5) goto Lba
            java.util.ArrayList<supwisdom.hd$a> r5 = r0.f7826a
            java.lang.Object r5 = r5.get(r4)
            supwisdom.hd$a r5 = (supwisdom.hd.a) r5
            int r6 = r5.f7828a
            r7 = 0
            r8 = 1
            if (r6 == r8) goto Lb2
            r9 = 2
            r10 = 3
            r11 = 9
            if (r6 == r9) goto L58
            if (r6 == r10) goto L41
            r9 = 6
            if (r6 == r9) goto L41
            r7 = 7
            if (r6 == r7) goto Lb2
            r7 = 8
            if (r6 == r7) goto L31
            goto Lb7
        L31:
            java.util.ArrayList<supwisdom.hd$a> r6 = r0.f7826a
            supwisdom.hd$a r7 = new supwisdom.hd$a
            r7.<init>(r11, r3)
            r6.add(r4, r7)
            int r4 = r4 + 1
            androidx.fragment.app.Fragment r3 = r5.b
            goto Lb7
        L41:
            androidx.fragment.app.Fragment r6 = r5.b
            r1.remove(r6)
            androidx.fragment.app.Fragment r5 = r5.b
            if (r5 != r3) goto Lb7
            java.util.ArrayList<supwisdom.hd$a> r3 = r0.f7826a
            supwisdom.hd$a r6 = new supwisdom.hd$a
            r6.<init>(r11, r5)
            r3.add(r4, r6)
            int r4 = r4 + 1
            r3 = r7
            goto Lb7
        L58:
            androidx.fragment.app.Fragment r6 = r5.b
            int r9 = r6.mContainerId
            int r12 = r17.size()
            int r12 = r12 - r8
            r13 = 0
        L62:
            if (r12 < 0) goto La2
            java.lang.Object r14 = r1.get(r12)
            androidx.fragment.app.Fragment r14 = (androidx.fragment.app.Fragment) r14
            int r15 = r14.mContainerId
            if (r15 != r9) goto L9f
            if (r14 != r6) goto L72
            r13 = 1
            goto L9f
        L72:
            if (r14 != r3) goto L81
            java.util.ArrayList<supwisdom.hd$a> r3 = r0.f7826a
            supwisdom.hd$a r15 = new supwisdom.hd$a
            r15.<init>(r11, r14)
            r3.add(r4, r15)
            int r4 = r4 + 1
            r3 = r7
        L81:
            supwisdom.hd$a r15 = new supwisdom.hd$a
            r15.<init>(r10, r14)
            int r2 = r5.c
            r15.c = r2
            int r2 = r5.f7829e
            r15.f7829e = r2
            int r2 = r5.d
            r15.d = r2
            int r2 = r5.f
            r15.f = r2
            java.util.ArrayList<supwisdom.hd$a> r2 = r0.f7826a
            r2.add(r4, r15)
            r1.remove(r14)
            int r4 = r4 + r8
        L9f:
            int r12 = r12 + (-1)
            goto L62
        La2:
            if (r13 == 0) goto Lac
            java.util.ArrayList<supwisdom.hd$a> r2 = r0.f7826a
            r2.remove(r4)
            int r4 = r4 + (-1)
            goto Lb7
        Lac:
            r5.f7828a = r8
            r1.add(r6)
            goto Lb7
        Lb2:
            androidx.fragment.app.Fragment r2 = r5.b
            r1.add(r2)
        Lb7:
            int r4 = r4 + r8
            goto L7
        Lba:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.xc.a(java.util.ArrayList, androidx.fragment.app.Fragment):androidx.fragment.app.Fragment");
    }

    public void a(Fragment.e eVar) {
        for (int i = 0; i < this.f7826a.size(); i++) {
            hd.a aVar = this.f7826a.get(i);
            if (b(aVar)) {
                aVar.b.setOnStartEnterTransitionListener(eVar);
            }
        }
    }
}
