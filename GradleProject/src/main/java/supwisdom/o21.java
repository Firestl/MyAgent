package supwisdom;

import com.sangfor.dex.util.ExceptionWithContext;
import com.sangfor.dx.dex.file.ItemType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class o21 extends t21 {
    public static final Comparator<p21> j = new a();
    public final ArrayList<p21> f;
    public final HashMap<p21, p21> g;
    public final c h;
    public int i;

    /* JADX INFO: compiled from: Proguard */
    public static class a implements Comparator<p21> {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(p21 p21Var, p21 p21Var2) {
            return p21Var.a().compareTo(p21Var2.a());
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f8609a;

        static {
            int[] iArr = new int[c.values().length];
            f8609a = iArr;
            try {
                iArr[c.INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8609a[c.TYPE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: compiled from: Proguard */
    public enum c {
        NONE,
        TYPE,
        INSTANCE
    }

    public o21(String str, t11 t11Var, int i, c cVar) {
        super(str, t11Var, i);
        this.f = new ArrayList<>(100);
        this.g = new HashMap<>(100);
        this.h = cVar;
        this.i = -1;
    }

    public void a(p21 p21Var) {
        h();
        try {
            if (p21Var.e() > a()) {
                throw new IllegalArgumentException("incompatible item alignment");
            }
            this.f.add(p21Var);
        } catch (NullPointerException unused) {
            throw new NullPointerException("item == null");
        }
    }

    public synchronized <T extends p21> T b(T t) {
        h();
        T t2 = (T) this.g.get(t);
        if (t2 != null) {
            return t2;
        }
        a((p21) t);
        this.g.put(t, t);
        return t;
    }

    @Override // supwisdom.t21
    public void c(h61 h61Var) {
        boolean zE = h61Var.e();
        t11 t11VarB = b();
        int iC = 0;
        boolean z = true;
        for (p21 p21Var : this.f) {
            if (zE) {
                if (z) {
                    z = false;
                } else {
                    h61Var.a(0, "\n");
                }
            }
            int iE = p21Var.e() - 1;
            int i = (~iE) & (iC + iE);
            if (iC != i) {
                h61Var.a(i - iC);
                iC = i;
            }
            p21Var.a(t11VarB, h61Var);
            iC += p21Var.c();
        }
        if (iC != this.i) {
            throw new RuntimeException("output size mismatch");
        }
    }

    @Override // supwisdom.t21
    public Collection<? extends f21> d() {
        return this.f;
    }

    @Override // supwisdom.t21
    public void f() {
        t11 t11VarB = b();
        int i = 0;
        while (true) {
            int size = this.f.size();
            if (i >= size) {
                return;
            }
            while (i < size) {
                this.f.get(i).a(t11VarB);
                i++;
            }
        }
    }

    @Override // supwisdom.t21
    public int i() {
        g();
        return this.i;
    }

    public void j() {
        g();
        int i = b.f8609a[this.h.ordinal()];
        if (i == 1) {
            Collections.sort(this.f);
        } else if (i == 2) {
            Collections.sort(this.f, j);
        }
        int size = this.f.size();
        int iC = 0;
        for (int i2 = 0; i2 < size; i2++) {
            p21 p21Var = this.f.get(i2);
            try {
                int iA = p21Var.a(this, iC);
                if (iA < iC) {
                    throw new RuntimeException("bogus place() result for " + p21Var);
                }
                iC = p21Var.c() + iA;
            } catch (RuntimeException e2) {
                throw ExceptionWithContext.withContext(e2, "...while placing " + p21Var);
            }
        }
        this.i = iC;
    }

    @Override // supwisdom.t21
    public int a(f21 f21Var) {
        return ((p21) f21Var).d();
    }

    public void a(h61 h61Var, ItemType itemType, String str) {
        g();
        TreeMap treeMap = new TreeMap();
        for (p21 p21Var : this.f) {
            if (p21Var.a() == itemType) {
                treeMap.put(p21Var.g(), p21Var);
            }
        }
        if (treeMap.size() == 0) {
            return;
        }
        h61Var.a(0, str);
        for (Map.Entry entry : treeMap.entrySet()) {
            h61Var.a(0, ((p21) entry.getValue()).f() + ' ' + ((String) entry.getKey()) + '\n');
        }
    }
}
