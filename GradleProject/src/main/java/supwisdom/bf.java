package supwisdom;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ChildHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class bf {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f7061a;
    public final a b = new a();
    public final List<View> c = new ArrayList();

    /* JADX INFO: compiled from: ChildHelper.java */
    public interface b {
        int a();

        View a(int i);

        void a(View view);

        void a(View view, int i);

        void a(View view, int i, ViewGroup.LayoutParams layoutParams);

        int b(View view);

        void b();

        void b(int i);

        RecyclerView.b0 c(View view);

        void c(int i);

        void d(View view);
    }

    public bf(b bVar) {
        this.f7061a = bVar;
    }

    public void a(View view, boolean z) {
        a(view, -1, z);
    }

    public final void b(View view) {
        this.c.add(view);
        this.f7061a.a(view);
    }

    public View c(int i) {
        return this.f7061a.a(d(i));
    }

    public final int d(int i) {
        if (i < 0) {
            return -1;
        }
        int iA = this.f7061a.a();
        int i2 = i;
        while (i2 < iA) {
            int iB = i - (i2 - this.b.b(i2));
            if (iB == 0) {
                while (this.b.c(i2)) {
                    i2++;
                }
                return i2;
            }
            i2 += iB;
        }
        return -1;
    }

    public void e(View view) {
        int iB = this.f7061a.b(view);
        if (iB < 0) {
            return;
        }
        if (this.b.d(iB)) {
            h(view);
        }
        this.f7061a.c(iB);
    }

    public void f(int i) {
        int iD = d(i);
        View viewA = this.f7061a.a(iD);
        if (viewA == null) {
            return;
        }
        if (this.b.d(iD)) {
            h(viewA);
        }
        this.f7061a.c(iD);
    }

    public void g(View view) {
        int iB = this.f7061a.b(view);
        if (iB < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
        if (this.b.c(iB)) {
            this.b.a(iB);
            h(view);
        } else {
            throw new RuntimeException("trying to unhide a view that was not hidden" + view);
        }
    }

    public final boolean h(View view) {
        if (!this.c.remove(view)) {
            return false;
        }
        this.f7061a.d(view);
        return true;
    }

    public String toString() {
        return this.b.toString() + ", hidden list:" + this.c.size();
    }

    /* JADX INFO: compiled from: ChildHelper.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f7062a = 0;
        public a b;

        public final void a() {
            if (this.b == null) {
                this.b = new a();
            }
        }

        public void b() {
            this.f7062a = 0L;
            a aVar = this.b;
            if (aVar != null) {
                aVar.b();
            }
        }

        public boolean c(int i) {
            if (i < 64) {
                return (this.f7062a & (1 << i)) != 0;
            }
            a();
            return this.b.c(i - 64);
        }

        public boolean d(int i) {
            if (i >= 64) {
                a();
                return this.b.d(i - 64);
            }
            long j = 1 << i;
            boolean z = (this.f7062a & j) != 0;
            long j2 = this.f7062a & (~j);
            this.f7062a = j2;
            long j3 = j - 1;
            this.f7062a = (j2 & j3) | Long.rotateRight((~j3) & j2, 1);
            a aVar = this.b;
            if (aVar != null) {
                if (aVar.c(0)) {
                    e(63);
                }
                this.b.d(0);
            }
            return z;
        }

        public void e(int i) {
            if (i < 64) {
                this.f7062a |= 1 << i;
            } else {
                a();
                this.b.e(i - 64);
            }
        }

        public String toString() {
            if (this.b == null) {
                return Long.toBinaryString(this.f7062a);
            }
            return this.b.toString() + "xx" + Long.toBinaryString(this.f7062a);
        }

        public void a(int i) {
            if (i >= 64) {
                a aVar = this.b;
                if (aVar != null) {
                    aVar.a(i - 64);
                    return;
                }
                return;
            }
            this.f7062a &= ~(1 << i);
        }

        public int b(int i) {
            a aVar = this.b;
            if (aVar == null) {
                if (i >= 64) {
                    return Long.bitCount(this.f7062a);
                }
                return Long.bitCount(this.f7062a & ((1 << i) - 1));
            }
            if (i < 64) {
                return Long.bitCount(this.f7062a & ((1 << i) - 1));
            }
            return aVar.b(i - 64) + Long.bitCount(this.f7062a);
        }

        public void a(int i, boolean z) {
            if (i >= 64) {
                a();
                this.b.a(i - 64, z);
                return;
            }
            boolean z2 = (this.f7062a & Long.MIN_VALUE) != 0;
            long j = (1 << i) - 1;
            long j2 = this.f7062a;
            this.f7062a = ((j2 & (~j)) << 1) | (j2 & j);
            if (z) {
                e(i);
            } else {
                a(i);
            }
            if (z2 || this.b != null) {
                a();
                this.b.a(0, z2);
            }
        }
    }

    public void a(View view, int i, boolean z) {
        int iA = i < 0 ? this.f7061a.a() : d(i);
        this.b.a(iA, z);
        if (z) {
            b(view);
        }
        this.f7061a.a(view, iA);
    }

    public View b(int i) {
        int size = this.c.size();
        for (int i2 = 0; i2 < size; i2++) {
            View view = this.c.get(i2);
            RecyclerView.b0 b0VarC = this.f7061a.c(view);
            if (b0VarC.getLayoutPosition() == i && !b0VarC.isInvalid() && !b0VarC.isRemoved()) {
                return view;
            }
        }
        return null;
    }

    public void c() {
        this.b.b();
        for (int size = this.c.size() - 1; size >= 0; size--) {
            this.f7061a.d(this.c.get(size));
            this.c.remove(size);
        }
        this.f7061a.b();
    }

    public boolean d(View view) {
        return this.c.contains(view);
    }

    public View e(int i) {
        return this.f7061a.a(i);
    }

    public boolean f(View view) {
        int iB = this.f7061a.b(view);
        if (iB == -1) {
            h(view);
            return true;
        }
        if (!this.b.c(iB)) {
            return false;
        }
        this.b.d(iB);
        h(view);
        this.f7061a.c(iB);
        return true;
    }

    public void a(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        int iD;
        if (i < 0) {
            iD = this.f7061a.a();
        } else {
            iD = d(i);
        }
        this.b.a(iD, z);
        if (z) {
            b(view);
        }
        this.f7061a.a(view, iD, layoutParams);
    }

    public int c(View view) {
        int iB = this.f7061a.b(view);
        if (iB == -1 || this.b.c(iB)) {
            return -1;
        }
        return iB - this.b.b(iB);
    }

    public int b() {
        return this.f7061a.a();
    }

    public int a() {
        return this.f7061a.a() - this.c.size();
    }

    public void a(int i) {
        int iD = d(i);
        this.b.d(iD);
        this.f7061a.b(iD);
    }

    public void a(View view) {
        int iB = this.f7061a.b(view);
        if (iB >= 0) {
            this.b.e(iB);
            b(view);
        } else {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
    }
}
