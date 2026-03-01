package supwisdom;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: GapWorker.java */
/* JADX INFO: loaded from: classes.dex */
public final class ff implements Runnable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final ThreadLocal<ff> f7596e = new ThreadLocal<>();
    public static Comparator<c> f = new a();
    public long b;
    public long c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList<RecyclerView> f7597a = new ArrayList<>();
    public ArrayList<c> d = new ArrayList<>();

    /* JADX INFO: compiled from: GapWorker.java */
    public static class a implements Comparator<c> {
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(c cVar, c cVar2) {
            if ((cVar.d == null) != (cVar2.d == null)) {
                return cVar.d == null ? 1 : -1;
            }
            boolean z = cVar.f7599a;
            if (z != cVar2.f7599a) {
                return z ? -1 : 1;
            }
            int i = cVar2.b - cVar.b;
            if (i != 0) {
                return i;
            }
            int i2 = cVar.c - cVar2.c;
            if (i2 != 0) {
                return i2;
            }
            return 0;
        }
    }

    /* JADX INFO: compiled from: GapWorker.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f7599a;
        public int b;
        public int c;
        public RecyclerView d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f7600e;

        public void a() {
            this.f7599a = false;
            this.b = 0;
            this.c = 0;
            this.d = null;
            this.f7600e = 0;
        }
    }

    public void a(RecyclerView recyclerView) {
        this.f7597a.add(recyclerView);
    }

    public void b(RecyclerView recyclerView) {
        this.f7597a.remove(recyclerView);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            r9.a(RecyclerView.TRACE_PREFETCH_TAG);
            if (!this.f7597a.isEmpty()) {
                int size = this.f7597a.size();
                long jMax = 0;
                for (int i = 0; i < size; i++) {
                    RecyclerView recyclerView = this.f7597a.get(i);
                    if (recyclerView.getWindowVisibility() == 0) {
                        jMax = Math.max(recyclerView.getDrawingTime(), jMax);
                    }
                }
                if (jMax != 0) {
                    b(TimeUnit.MILLISECONDS.toNanos(jMax) + this.c);
                }
            }
        } finally {
            this.b = 0L;
            r9.a();
        }
    }

    public void a(RecyclerView recyclerView, int i, int i2) {
        if (recyclerView.isAttachedToWindow() && this.b == 0) {
            this.b = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        recyclerView.mPrefetchRegistry.b(i, i2);
    }

    public void b(long j) {
        a();
        a(j);
    }

    public final void a() {
        c cVar;
        int size = this.f7597a.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            RecyclerView recyclerView = this.f7597a.get(i2);
            if (recyclerView.getWindowVisibility() == 0) {
                recyclerView.mPrefetchRegistry.a(recyclerView, false);
                i += recyclerView.mPrefetchRegistry.d;
            }
        }
        this.d.ensureCapacity(i);
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            RecyclerView recyclerView2 = this.f7597a.get(i4);
            if (recyclerView2.getWindowVisibility() == 0) {
                b bVar = recyclerView2.mPrefetchRegistry;
                int iAbs = Math.abs(bVar.f7598a) + Math.abs(bVar.b);
                for (int i5 = 0; i5 < bVar.d * 2; i5 += 2) {
                    if (i3 >= this.d.size()) {
                        cVar = new c();
                        this.d.add(cVar);
                    } else {
                        cVar = this.d.get(i3);
                    }
                    int i6 = bVar.c[i5 + 1];
                    cVar.f7599a = i6 <= iAbs;
                    cVar.b = iAbs;
                    cVar.c = i6;
                    cVar.d = recyclerView2;
                    cVar.f7600e = bVar.c[i5];
                    i3++;
                }
            }
        }
        Collections.sort(this.d, f);
    }

    /* JADX INFO: compiled from: GapWorker.java */
    public static class b implements RecyclerView.o.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f7598a;
        public int b;
        public int[] c;
        public int d;

        public void a(RecyclerView recyclerView, boolean z) {
            this.d = 0;
            int[] iArr = this.c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            RecyclerView.o oVar = recyclerView.mLayout;
            if (recyclerView.mAdapter == null || oVar == null || !oVar.isItemPrefetchEnabled()) {
                return;
            }
            if (z) {
                if (!recyclerView.mAdapterHelper.c()) {
                    oVar.collectInitialPrefetchPositions(recyclerView.mAdapter.getItemCount(), this);
                }
            } else if (!recyclerView.hasPendingAdapterUpdates()) {
                oVar.collectAdjacentPrefetchPositions(this.f7598a, this.b, recyclerView.mState, this);
            }
            int i = this.d;
            if (i > oVar.mPrefetchMaxCountObserved) {
                oVar.mPrefetchMaxCountObserved = i;
                oVar.mPrefetchMaxObservedInInitialPrefetch = z;
                recyclerView.mRecycler.j();
            }
        }

        public void b(int i, int i2) {
            this.f7598a = i;
            this.b = i2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.o.c
        public void a(int i, int i2) {
            if (i < 0) {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            }
            if (i2 >= 0) {
                int i3 = this.d * 2;
                int[] iArr = this.c;
                if (iArr == null) {
                    int[] iArr2 = new int[4];
                    this.c = iArr2;
                    Arrays.fill(iArr2, -1);
                } else if (i3 >= iArr.length) {
                    int[] iArr3 = new int[i3 * 2];
                    this.c = iArr3;
                    System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
                }
                int[] iArr4 = this.c;
                iArr4[i3] = i;
                iArr4[i3 + 1] = i2;
                this.d++;
                return;
            }
            throw new IllegalArgumentException("Pixel distance must be non-negative");
        }

        public boolean a(int i) {
            if (this.c != null) {
                int i2 = this.d * 2;
                for (int i3 = 0; i3 < i2; i3 += 2) {
                    if (this.c[i3] == i) {
                        return true;
                    }
                }
            }
            return false;
        }

        public void a() {
            int[] iArr = this.c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.d = 0;
        }
    }

    public static boolean a(RecyclerView recyclerView, int i) {
        int iB = recyclerView.mChildHelper.b();
        for (int i2 = 0; i2 < iB; i2++) {
            RecyclerView.b0 childViewHolderInt = RecyclerView.getChildViewHolderInt(recyclerView.mChildHelper.e(i2));
            if (childViewHolderInt.mPosition == i && !childViewHolderInt.isInvalid()) {
                return true;
            }
        }
        return false;
    }

    public final RecyclerView.b0 a(RecyclerView recyclerView, int i, long j) {
        if (a(recyclerView, i)) {
            return null;
        }
        RecyclerView.u uVar = recyclerView.mRecycler;
        try {
            recyclerView.onEnterLayoutOrScroll();
            RecyclerView.b0 b0VarA = uVar.a(i, false, j);
            if (b0VarA != null) {
                if (b0VarA.isBound() && !b0VarA.isInvalid()) {
                    uVar.b(b0VarA.itemView);
                } else {
                    uVar.a(b0VarA, false);
                }
            }
            return b0VarA;
        } finally {
            recyclerView.onExitLayoutOrScroll(false);
        }
    }

    public final void a(RecyclerView recyclerView, long j) {
        if (recyclerView == null) {
            return;
        }
        if (recyclerView.mDataSetHasChangedAfterLayout && recyclerView.mChildHelper.b() != 0) {
            recyclerView.removeAndRecycleViews();
        }
        b bVar = recyclerView.mPrefetchRegistry;
        bVar.a(recyclerView, true);
        if (bVar.d != 0) {
            try {
                r9.a(RecyclerView.TRACE_NESTED_PREFETCH_TAG);
                recyclerView.mState.a(recyclerView.mAdapter);
                for (int i = 0; i < bVar.d * 2; i += 2) {
                    a(recyclerView, bVar.c[i], j);
                }
            } finally {
                r9.a();
            }
        }
    }

    public final void a(c cVar, long j) {
        RecyclerView.b0 b0VarA = a(cVar.d, cVar.f7600e, cVar.f7599a ? Long.MAX_VALUE : j);
        if (b0VarA == null || b0VarA.mNestedRecyclerView == null || !b0VarA.isBound() || b0VarA.isInvalid()) {
            return;
        }
        a(b0VarA.mNestedRecyclerView.get(), j);
    }

    public final void a(long j) {
        for (int i = 0; i < this.d.size(); i++) {
            c cVar = this.d.get(i);
            if (cVar.d == null) {
                return;
            }
            a(cVar, j);
            cVar.a();
        }
    }
}
