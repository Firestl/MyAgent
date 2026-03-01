package supwisdom;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* JADX INFO: compiled from: FragmentTransaction.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class hd {
    public int b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f7827e;
    public int f;
    public int g;
    public boolean h;
    public String i;
    public int j;
    public CharSequence k;
    public int l;
    public CharSequence m;
    public ArrayList<String> n;
    public ArrayList<String> o;
    public ArrayList<Runnable> q;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList<a> f7826a = new ArrayList<>();
    public boolean p = false;

    /* JADX INFO: compiled from: FragmentTransaction.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f7828a;
        public Fragment b;
        public int c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f7829e;
        public int f;
        public Lifecycle.State g;
        public Lifecycle.State h;

        public a() {
        }

        public a(int i, Fragment fragment) {
            this.f7828a = i;
            this.b = fragment;
            Lifecycle.State state = Lifecycle.State.RESUMED;
            this.g = state;
            this.h = state;
        }

        public a(int i, Fragment fragment, Lifecycle.State state) {
            this.f7828a = i;
            this.b = fragment;
            this.g = fragment.mMaxState;
            this.h = state;
        }
    }

    public abstract int a();

    public void a(a aVar) {
        this.f7826a.add(aVar);
        aVar.c = this.b;
        aVar.d = this.c;
        aVar.f7829e = this.d;
        aVar.f = this.f7827e;
    }

    public abstract int b();

    public hd b(Fragment fragment) {
        a(new a(6, fragment));
        return this;
    }

    public hd c(Fragment fragment) {
        a(new a(3, fragment));
        return this;
    }

    public abstract void c();

    public hd d() {
        if (this.h) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        return this;
    }

    public hd a(Fragment fragment, String str) {
        a(0, fragment, str, 1);
        return this;
    }

    public hd a(int i, Fragment fragment) {
        a(i, fragment, null, 1);
        return this;
    }

    public hd a(int i, Fragment fragment, String str) {
        a(i, fragment, str, 1);
        return this;
    }

    public void a(int i, Fragment fragment, String str, int i2) {
        Class<?> cls = fragment.getClass();
        int modifiers = cls.getModifiers();
        if (!cls.isAnonymousClass() && Modifier.isPublic(modifiers) && (!cls.isMemberClass() || Modifier.isStatic(modifiers))) {
            if (str != null) {
                String str2 = fragment.mTag;
                if (str2 != null && !str.equals(str2)) {
                    throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.mTag + " now " + str);
                }
                fragment.mTag = str;
            }
            if (i != 0) {
                if (i != -1) {
                    int i3 = fragment.mFragmentId;
                    if (i3 != 0 && i3 != i) {
                        throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.mFragmentId + " now " + i);
                    }
                    fragment.mFragmentId = i;
                    fragment.mContainerId = i;
                } else {
                    throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + str + " to container view with no id");
                }
            }
            a(new a(i2, fragment));
            return;
        }
        throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
    }

    public hd a(Fragment fragment) {
        a(new a(7, fragment));
        return this;
    }

    public hd a(Fragment fragment, Lifecycle.State state) {
        a(new a(10, fragment, state));
        return this;
    }
}
