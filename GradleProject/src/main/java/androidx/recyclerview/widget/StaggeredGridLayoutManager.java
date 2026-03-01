package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import supwisdom.jf;
import supwisdom.kf;
import supwisdom.mf;
import supwisdom.pf;
import supwisdom.xb;

/* JADX INFO: loaded from: classes.dex */
public class StaggeredGridLayoutManager extends RecyclerView.o implements RecyclerView.x.b {
    public static final boolean DEBUG = false;

    @Deprecated
    public static final int GAP_HANDLING_LAZY = 1;
    public static final int GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS = 2;
    public static final int GAP_HANDLING_NONE = 0;
    public static final int HORIZONTAL = 0;
    public static final int INVALID_OFFSET = Integer.MIN_VALUE;
    public static final float MAX_SCROLL_FACTOR = 0.33333334f;
    public static final String TAG = "StaggeredGridLManager";
    public static final int VERTICAL = 1;
    public int mFullSizeSpec;
    public boolean mLastLayoutFromEnd;
    public boolean mLastLayoutRTL;
    public final jf mLayoutState;
    public int mOrientation;
    public SavedState mPendingSavedState;
    public int[] mPrefetchDistances;
    public mf mPrimaryOrientation;
    public BitSet mRemainingSpans;
    public mf mSecondaryOrientation;
    public int mSizePerSpan;
    public c[] mSpans;
    public int mSpanCount = -1;
    public boolean mReverseLayout = false;
    public boolean mShouldReverseLayout = false;
    public int mPendingScrollPosition = -1;
    public int mPendingScrollPositionOffset = Integer.MIN_VALUE;
    public LazySpanLookup mLazySpanLookup = new LazySpanLookup();
    public int mGapStrategy = 2;
    public final Rect mTmpRect = new Rect();
    public final b mAnchorInfo = new b();
    public boolean mLaidOutInvalidFullSpan = false;
    public boolean mSmoothScrollbarEnabled = true;
    public final Runnable mCheckForGapsRunnable = new a();

    public static class LayoutParams extends RecyclerView.LayoutParams {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public c f1381e;
        public boolean f;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public void a(boolean z) {
            this.f = z;
        }

        public final int e() {
            c cVar = this.f1381e;
            if (cVar == null) {
                return -1;
            }
            return cVar.f1390e;
        }

        public boolean f() {
            return this.f;
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1384a;
        public int b;
        public int c;
        public int[] d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1385e;
        public int[] f;
        public List<LazySpanLookup.FullSpanItem> g;
        public boolean h;
        public boolean i;
        public boolean j;

        public static class a implements Parcelable.Creator<SavedState> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState() {
        }

        public void a() {
            this.d = null;
            this.c = 0;
            this.f1384a = -1;
            this.b = -1;
        }

        public void b() {
            this.d = null;
            this.c = 0;
            this.f1385e = 0;
            this.f = null;
            this.g = null;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f1384a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            if (this.c > 0) {
                parcel.writeIntArray(this.d);
            }
            parcel.writeInt(this.f1385e);
            if (this.f1385e > 0) {
                parcel.writeIntArray(this.f);
            }
            parcel.writeInt(this.h ? 1 : 0);
            parcel.writeInt(this.i ? 1 : 0);
            parcel.writeInt(this.j ? 1 : 0);
            parcel.writeList(this.g);
        }

        public SavedState(Parcel parcel) {
            this.f1384a = parcel.readInt();
            this.b = parcel.readInt();
            int i = parcel.readInt();
            this.c = i;
            if (i > 0) {
                int[] iArr = new int[i];
                this.d = iArr;
                parcel.readIntArray(iArr);
            }
            int i2 = parcel.readInt();
            this.f1385e = i2;
            if (i2 > 0) {
                int[] iArr2 = new int[i2];
                this.f = iArr2;
                parcel.readIntArray(iArr2);
            }
            this.h = parcel.readInt() == 1;
            this.i = parcel.readInt() == 1;
            this.j = parcel.readInt() == 1;
            this.g = parcel.readArrayList(LazySpanLookup.FullSpanItem.class.getClassLoader());
        }

        public SavedState(SavedState savedState) {
            this.c = savedState.c;
            this.f1384a = savedState.f1384a;
            this.b = savedState.b;
            this.d = savedState.d;
            this.f1385e = savedState.f1385e;
            this.f = savedState.f;
            this.h = savedState.h;
            this.i = savedState.i;
            this.j = savedState.j;
            this.g = savedState.g;
        }
    }

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            StaggeredGridLayoutManager.this.checkForGaps();
        }
    }

    public class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ArrayList<View> f1389a = new ArrayList<>();
        public int b = Integer.MIN_VALUE;
        public int c = Integer.MIN_VALUE;
        public int d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int f1390e;

        public c(int i) {
            this.f1390e = i;
        }

        public int a(int i) {
            int i2 = this.c;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (this.f1389a.size() == 0) {
                return i;
            }
            a();
            return this.c;
        }

        public int b(int i) {
            int i2 = this.b;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (this.f1389a.size() == 0) {
                return i;
            }
            b();
            return this.b;
        }

        public void c(View view) {
            LayoutParams layoutParamsB = b(view);
            layoutParamsB.f1381e = this;
            this.f1389a.add(0, view);
            this.b = Integer.MIN_VALUE;
            if (this.f1389a.size() == 1) {
                this.c = Integer.MIN_VALUE;
            }
            if (layoutParamsB.c() || layoutParamsB.b()) {
                this.d += StaggeredGridLayoutManager.this.mPrimaryOrientation.b(view);
            }
        }

        public void d(int i) {
            this.b = i;
            this.c = i;
        }

        public int e() {
            return StaggeredGridLayoutManager.this.mReverseLayout ? a(this.f1389a.size() - 1, -1, true) : a(0, this.f1389a.size(), true);
        }

        public int f() {
            return StaggeredGridLayoutManager.this.mReverseLayout ? b(this.f1389a.size() - 1, -1, false) : b(0, this.f1389a.size(), false);
        }

        public int g() {
            return StaggeredGridLayoutManager.this.mReverseLayout ? b(0, this.f1389a.size(), true) : b(this.f1389a.size() - 1, -1, true);
        }

        public int h() {
            return StaggeredGridLayoutManager.this.mReverseLayout ? a(0, this.f1389a.size(), true) : a(this.f1389a.size() - 1, -1, true);
        }

        public int i() {
            return StaggeredGridLayoutManager.this.mReverseLayout ? b(0, this.f1389a.size(), false) : b(this.f1389a.size() - 1, -1, false);
        }

        public int j() {
            return this.d;
        }

        public int k() {
            int i = this.c;
            if (i != Integer.MIN_VALUE) {
                return i;
            }
            a();
            return this.c;
        }

        public int l() {
            int i = this.b;
            if (i != Integer.MIN_VALUE) {
                return i;
            }
            b();
            return this.b;
        }

        public void m() {
            this.b = Integer.MIN_VALUE;
            this.c = Integer.MIN_VALUE;
        }

        public void n() {
            int size = this.f1389a.size();
            View viewRemove = this.f1389a.remove(size - 1);
            LayoutParams layoutParamsB = b(viewRemove);
            layoutParamsB.f1381e = null;
            if (layoutParamsB.c() || layoutParamsB.b()) {
                this.d -= StaggeredGridLayoutManager.this.mPrimaryOrientation.b(viewRemove);
            }
            if (size == 1) {
                this.b = Integer.MIN_VALUE;
            }
            this.c = Integer.MIN_VALUE;
        }

        public void o() {
            View viewRemove = this.f1389a.remove(0);
            LayoutParams layoutParamsB = b(viewRemove);
            layoutParamsB.f1381e = null;
            if (this.f1389a.size() == 0) {
                this.c = Integer.MIN_VALUE;
            }
            if (layoutParamsB.c() || layoutParamsB.b()) {
                this.d -= StaggeredGridLayoutManager.this.mPrimaryOrientation.b(viewRemove);
            }
            this.b = Integer.MIN_VALUE;
        }

        public int d() {
            return StaggeredGridLayoutManager.this.mReverseLayout ? b(this.f1389a.size() - 1, -1, true) : b(0, this.f1389a.size(), true);
        }

        public void a() {
            LazySpanLookup.FullSpanItem fullSpanItemC;
            ArrayList<View> arrayList = this.f1389a;
            View view = arrayList.get(arrayList.size() - 1);
            LayoutParams layoutParamsB = b(view);
            this.c = StaggeredGridLayoutManager.this.mPrimaryOrientation.a(view);
            if (layoutParamsB.f && (fullSpanItemC = StaggeredGridLayoutManager.this.mLazySpanLookup.c(layoutParamsB.a())) != null && fullSpanItemC.b == 1) {
                this.c += fullSpanItemC.a(this.f1390e);
            }
        }

        public void b() {
            LazySpanLookup.FullSpanItem fullSpanItemC;
            View view = this.f1389a.get(0);
            LayoutParams layoutParamsB = b(view);
            this.b = StaggeredGridLayoutManager.this.mPrimaryOrientation.d(view);
            if (layoutParamsB.f && (fullSpanItemC = StaggeredGridLayoutManager.this.mLazySpanLookup.c(layoutParamsB.a())) != null && fullSpanItemC.b == -1) {
                this.b -= fullSpanItemC.a(this.f1390e);
            }
        }

        public void c() {
            this.f1389a.clear();
            m();
            this.d = 0;
        }

        public void c(int i) {
            int i2 = this.b;
            if (i2 != Integer.MIN_VALUE) {
                this.b = i2 + i;
            }
            int i3 = this.c;
            if (i3 != Integer.MIN_VALUE) {
                this.c = i3 + i;
            }
        }

        public void a(View view) {
            LayoutParams layoutParamsB = b(view);
            layoutParamsB.f1381e = this;
            this.f1389a.add(view);
            this.c = Integer.MIN_VALUE;
            if (this.f1389a.size() == 1) {
                this.b = Integer.MIN_VALUE;
            }
            if (layoutParamsB.c() || layoutParamsB.b()) {
                this.d += StaggeredGridLayoutManager.this.mPrimaryOrientation.b(view);
            }
        }

        public LayoutParams b(View view) {
            return (LayoutParams) view.getLayoutParams();
        }

        public int b(int i, int i2, boolean z) {
            return a(i, i2, z, true, false);
        }

        public void a(boolean z, int i) {
            int iB;
            if (z) {
                iB = a(Integer.MIN_VALUE);
            } else {
                iB = b(Integer.MIN_VALUE);
            }
            c();
            if (iB == Integer.MIN_VALUE) {
                return;
            }
            if (!z || iB >= StaggeredGridLayoutManager.this.mPrimaryOrientation.b()) {
                if (z || iB <= StaggeredGridLayoutManager.this.mPrimaryOrientation.f()) {
                    if (i != Integer.MIN_VALUE) {
                        iB += i;
                    }
                    this.c = iB;
                    this.b = iB;
                }
            }
        }

        public int a(int i, int i2, boolean z, boolean z2, boolean z3) {
            int iF = StaggeredGridLayoutManager.this.mPrimaryOrientation.f();
            int iB = StaggeredGridLayoutManager.this.mPrimaryOrientation.b();
            int i3 = i2 > i ? 1 : -1;
            while (i != i2) {
                View view = this.f1389a.get(i);
                int iD = StaggeredGridLayoutManager.this.mPrimaryOrientation.d(view);
                int iA = StaggeredGridLayoutManager.this.mPrimaryOrientation.a(view);
                boolean z4 = false;
                boolean z5 = !z3 ? iD >= iB : iD > iB;
                if (!z3 ? iA > iF : iA >= iF) {
                    z4 = true;
                }
                if (z5 && z4) {
                    if (z && z2) {
                        if (iD >= iF && iA <= iB) {
                            return StaggeredGridLayoutManager.this.getPosition(view);
                        }
                    } else {
                        if (z2) {
                            return StaggeredGridLayoutManager.this.getPosition(view);
                        }
                        if (iD < iF || iA > iB) {
                            return StaggeredGridLayoutManager.this.getPosition(view);
                        }
                    }
                }
                i += i3;
            }
            return -1;
        }

        public int a(int i, int i2, boolean z) {
            return a(i, i2, false, false, z);
        }

        public View a(int i, int i2) {
            View view = null;
            if (i2 == -1) {
                int size = this.f1389a.size();
                int i3 = 0;
                while (i3 < size) {
                    View view2 = this.f1389a.get(i3);
                    StaggeredGridLayoutManager staggeredGridLayoutManager = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager.mReverseLayout && staggeredGridLayoutManager.getPosition(view2) <= i) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager2 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager2.mReverseLayout && staggeredGridLayoutManager2.getPosition(view2) >= i) || !view2.hasFocusable()) {
                        break;
                    }
                    i3++;
                    view = view2;
                }
            } else {
                int size2 = this.f1389a.size() - 1;
                while (size2 >= 0) {
                    View view3 = this.f1389a.get(size2);
                    StaggeredGridLayoutManager staggeredGridLayoutManager3 = StaggeredGridLayoutManager.this;
                    if (staggeredGridLayoutManager3.mReverseLayout && staggeredGridLayoutManager3.getPosition(view3) >= i) {
                        break;
                    }
                    StaggeredGridLayoutManager staggeredGridLayoutManager4 = StaggeredGridLayoutManager.this;
                    if ((!staggeredGridLayoutManager4.mReverseLayout && staggeredGridLayoutManager4.getPosition(view3) <= i) || !view3.hasFocusable()) {
                        break;
                    }
                    size2--;
                    view = view3;
                }
            }
            return view;
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        RecyclerView.o.d properties = RecyclerView.o.getProperties(context, attributeSet, i, i2);
        setOrientation(properties.f1371a);
        setSpanCount(properties.b);
        setReverseLayout(properties.c);
        this.mLayoutState = new jf();
        createOrientationHelpers();
    }

    private void appendViewToAllSpans(View view) {
        for (int i = this.mSpanCount - 1; i >= 0; i--) {
            this.mSpans[i].a(view);
        }
    }

    private void applyPendingSavedState(b bVar) {
        SavedState savedState = this.mPendingSavedState;
        int i = savedState.c;
        if (i > 0) {
            if (i == this.mSpanCount) {
                for (int i2 = 0; i2 < this.mSpanCount; i2++) {
                    this.mSpans[i2].c();
                    SavedState savedState2 = this.mPendingSavedState;
                    int iB = savedState2.d[i2];
                    if (iB != Integer.MIN_VALUE) {
                        iB += savedState2.i ? this.mPrimaryOrientation.b() : this.mPrimaryOrientation.f();
                    }
                    this.mSpans[i2].d(iB);
                }
            } else {
                savedState.b();
                SavedState savedState3 = this.mPendingSavedState;
                savedState3.f1384a = savedState3.b;
            }
        }
        SavedState savedState4 = this.mPendingSavedState;
        this.mLastLayoutRTL = savedState4.j;
        setReverseLayout(savedState4.h);
        resolveShouldLayoutReverse();
        SavedState savedState5 = this.mPendingSavedState;
        int i3 = savedState5.f1384a;
        if (i3 != -1) {
            this.mPendingScrollPosition = i3;
            bVar.c = savedState5.i;
        } else {
            bVar.c = this.mShouldReverseLayout;
        }
        SavedState savedState6 = this.mPendingSavedState;
        if (savedState6.f1385e > 1) {
            LazySpanLookup lazySpanLookup = this.mLazySpanLookup;
            lazySpanLookup.f1382a = savedState6.f;
            lazySpanLookup.b = savedState6.g;
        }
    }

    private void attachViewToSpans(View view, LayoutParams layoutParams, jf jfVar) {
        if (jfVar.f8056e == 1) {
            if (layoutParams.f) {
                appendViewToAllSpans(view);
                return;
            } else {
                layoutParams.f1381e.a(view);
                return;
            }
        }
        if (layoutParams.f) {
            prependViewToAllSpans(view);
        } else {
            layoutParams.f1381e.c(view);
        }
    }

    private int calculateScrollDirectionForPosition(int i) {
        if (getChildCount() == 0) {
            return this.mShouldReverseLayout ? 1 : -1;
        }
        return (i < getFirstChildPosition()) != this.mShouldReverseLayout ? -1 : 1;
    }

    private boolean checkSpanForGap(c cVar) {
        if (this.mShouldReverseLayout) {
            if (cVar.k() < this.mPrimaryOrientation.b()) {
                ArrayList<View> arrayList = cVar.f1389a;
                return !cVar.b(arrayList.get(arrayList.size() - 1)).f;
            }
        } else if (cVar.l() > this.mPrimaryOrientation.f()) {
            return !cVar.b(cVar.f1389a.get(0)).f;
        }
        return false;
    }

    private int computeScrollExtent(RecyclerView.y yVar) {
        if (getChildCount() == 0) {
            return 0;
        }
        return pf.a(yVar, this.mPrimaryOrientation, findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled);
    }

    private int computeScrollOffset(RecyclerView.y yVar) {
        if (getChildCount() == 0) {
            return 0;
        }
        return pf.a(yVar, this.mPrimaryOrientation, findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }

    private int computeScrollRange(RecyclerView.y yVar) {
        if (getChildCount() == 0) {
            return 0;
        }
        return pf.b(yVar, this.mPrimaryOrientation, findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled);
    }

    private int convertFocusDirectionToLayoutDirection(int i) {
        return i != 1 ? i != 2 ? i != 17 ? i != 33 ? i != 66 ? (i == 130 && this.mOrientation == 1) ? 1 : Integer.MIN_VALUE : this.mOrientation == 0 ? 1 : Integer.MIN_VALUE : this.mOrientation == 1 ? -1 : Integer.MIN_VALUE : this.mOrientation == 0 ? -1 : Integer.MIN_VALUE : (this.mOrientation != 1 && isLayoutRTL()) ? -1 : 1 : (this.mOrientation != 1 && isLayoutRTL()) ? 1 : -1;
    }

    private LazySpanLookup.FullSpanItem createFullSpanItemFromEnd(int i) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.c = new int[this.mSpanCount];
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            fullSpanItem.c[i2] = i - this.mSpans[i2].a(i);
        }
        return fullSpanItem;
    }

    private LazySpanLookup.FullSpanItem createFullSpanItemFromStart(int i) {
        LazySpanLookup.FullSpanItem fullSpanItem = new LazySpanLookup.FullSpanItem();
        fullSpanItem.c = new int[this.mSpanCount];
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            fullSpanItem.c[i2] = this.mSpans[i2].b(i) - i;
        }
        return fullSpanItem;
    }

    private void createOrientationHelpers() {
        this.mPrimaryOrientation = mf.a(this, this.mOrientation);
        this.mSecondaryOrientation = mf.a(this, 1 - this.mOrientation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r9v7 */
    private int fill(RecyclerView.u uVar, jf jfVar, RecyclerView.y yVar) {
        int i;
        c nextSpan;
        int iB;
        int i2;
        int iB2;
        int iB3;
        ?? r9 = 0;
        this.mRemainingSpans.set(0, this.mSpanCount, true);
        if (this.mLayoutState.i) {
            i = jfVar.f8056e == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else {
            i = jfVar.f8056e == 1 ? jfVar.g + jfVar.b : jfVar.f - jfVar.b;
        }
        updateAllRemainingSpans(jfVar.f8056e, i);
        int iB4 = this.mShouldReverseLayout ? this.mPrimaryOrientation.b() : this.mPrimaryOrientation.f();
        boolean z = false;
        while (jfVar.a(yVar) && (this.mLayoutState.i || !this.mRemainingSpans.isEmpty())) {
            View viewA = jfVar.a(uVar);
            LayoutParams layoutParams = (LayoutParams) viewA.getLayoutParams();
            int iA = layoutParams.a();
            int iD = this.mLazySpanLookup.d(iA);
            boolean z2 = iD == -1;
            if (z2) {
                nextSpan = layoutParams.f ? this.mSpans[r9] : getNextSpan(jfVar);
                this.mLazySpanLookup.a(iA, nextSpan);
            } else {
                nextSpan = this.mSpans[iD];
            }
            c cVar = nextSpan;
            layoutParams.f1381e = cVar;
            if (jfVar.f8056e == 1) {
                addView(viewA);
            } else {
                addView(viewA, r9);
            }
            measureChildWithDecorationsAndMargin(viewA, layoutParams, r9);
            if (jfVar.f8056e == 1) {
                int maxEnd = layoutParams.f ? getMaxEnd(iB4) : cVar.a(iB4);
                int iB5 = this.mPrimaryOrientation.b(viewA) + maxEnd;
                if (z2 && layoutParams.f) {
                    LazySpanLookup.FullSpanItem fullSpanItemCreateFullSpanItemFromEnd = createFullSpanItemFromEnd(maxEnd);
                    fullSpanItemCreateFullSpanItemFromEnd.b = -1;
                    fullSpanItemCreateFullSpanItemFromEnd.f1383a = iA;
                    this.mLazySpanLookup.a(fullSpanItemCreateFullSpanItemFromEnd);
                }
                i2 = iB5;
                iB = maxEnd;
            } else {
                int minStart = layoutParams.f ? getMinStart(iB4) : cVar.b(iB4);
                iB = minStart - this.mPrimaryOrientation.b(viewA);
                if (z2 && layoutParams.f) {
                    LazySpanLookup.FullSpanItem fullSpanItemCreateFullSpanItemFromStart = createFullSpanItemFromStart(minStart);
                    fullSpanItemCreateFullSpanItemFromStart.b = 1;
                    fullSpanItemCreateFullSpanItemFromStart.f1383a = iA;
                    this.mLazySpanLookup.a(fullSpanItemCreateFullSpanItemFromStart);
                }
                i2 = minStart;
            }
            if (layoutParams.f && jfVar.d == -1) {
                if (z2) {
                    this.mLaidOutInvalidFullSpan = true;
                } else {
                    if (!(jfVar.f8056e == 1 ? areAllEndsEqual() : areAllStartsEqual())) {
                        LazySpanLookup.FullSpanItem fullSpanItemC = this.mLazySpanLookup.c(iA);
                        if (fullSpanItemC != null) {
                            fullSpanItemC.d = true;
                        }
                        this.mLaidOutInvalidFullSpan = true;
                    }
                }
            }
            attachViewToSpans(viewA, layoutParams, jfVar);
            if (isLayoutRTL() && this.mOrientation == 1) {
                int iB6 = layoutParams.f ? this.mSecondaryOrientation.b() : this.mSecondaryOrientation.b() - (((this.mSpanCount - 1) - cVar.f1390e) * this.mSizePerSpan);
                iB3 = iB6;
                iB2 = iB6 - this.mSecondaryOrientation.b(viewA);
            } else {
                int iF = layoutParams.f ? this.mSecondaryOrientation.f() : (cVar.f1390e * this.mSizePerSpan) + this.mSecondaryOrientation.f();
                iB2 = iF;
                iB3 = this.mSecondaryOrientation.b(viewA) + iF;
            }
            if (this.mOrientation == 1) {
                layoutDecoratedWithMargins(viewA, iB2, iB, iB3, i2);
            } else {
                layoutDecoratedWithMargins(viewA, iB, iB2, i2, iB3);
            }
            if (layoutParams.f) {
                updateAllRemainingSpans(this.mLayoutState.f8056e, i);
            } else {
                updateRemainingSpans(cVar, this.mLayoutState.f8056e, i);
            }
            recycle(uVar, this.mLayoutState);
            if (this.mLayoutState.h && viewA.hasFocusable()) {
                if (layoutParams.f) {
                    this.mRemainingSpans.clear();
                } else {
                    this.mRemainingSpans.set(cVar.f1390e, false);
                }
            }
            z = true;
            r9 = 0;
        }
        if (!z) {
            recycle(uVar, this.mLayoutState);
        }
        int iF2 = this.mLayoutState.f8056e == -1 ? this.mPrimaryOrientation.f() - getMinStart(this.mPrimaryOrientation.f()) : getMaxEnd(this.mPrimaryOrientation.b()) - this.mPrimaryOrientation.b();
        if (iF2 > 0) {
            return Math.min(jfVar.b, iF2);
        }
        return 0;
    }

    private int findFirstReferenceChildPosition(int i) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            int position = getPosition(getChildAt(i2));
            if (position >= 0 && position < i) {
                return position;
            }
        }
        return 0;
    }

    private int findLastReferenceChildPosition(int i) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            int position = getPosition(getChildAt(childCount));
            if (position >= 0 && position < i) {
                return position;
            }
        }
        return 0;
    }

    private void fixEndGap(RecyclerView.u uVar, RecyclerView.y yVar, boolean z) {
        int iB;
        int maxEnd = getMaxEnd(Integer.MIN_VALUE);
        if (maxEnd != Integer.MIN_VALUE && (iB = this.mPrimaryOrientation.b() - maxEnd) > 0) {
            int i = iB - (-scrollBy(-iB, uVar, yVar));
            if (!z || i <= 0) {
                return;
            }
            this.mPrimaryOrientation.a(i);
        }
    }

    private void fixStartGap(RecyclerView.u uVar, RecyclerView.y yVar, boolean z) {
        int iF;
        int minStart = getMinStart(Integer.MAX_VALUE);
        if (minStart != Integer.MAX_VALUE && (iF = minStart - this.mPrimaryOrientation.f()) > 0) {
            int iScrollBy = iF - scrollBy(iF, uVar, yVar);
            if (!z || iScrollBy <= 0) {
                return;
            }
            this.mPrimaryOrientation.a(-iScrollBy);
        }
    }

    private int getMaxEnd(int i) {
        int iA = this.mSpans[0].a(i);
        for (int i2 = 1; i2 < this.mSpanCount; i2++) {
            int iA2 = this.mSpans[i2].a(i);
            if (iA2 > iA) {
                iA = iA2;
            }
        }
        return iA;
    }

    private int getMaxStart(int i) {
        int iB = this.mSpans[0].b(i);
        for (int i2 = 1; i2 < this.mSpanCount; i2++) {
            int iB2 = this.mSpans[i2].b(i);
            if (iB2 > iB) {
                iB = iB2;
            }
        }
        return iB;
    }

    private int getMinEnd(int i) {
        int iA = this.mSpans[0].a(i);
        for (int i2 = 1; i2 < this.mSpanCount; i2++) {
            int iA2 = this.mSpans[i2].a(i);
            if (iA2 < iA) {
                iA = iA2;
            }
        }
        return iA;
    }

    private int getMinStart(int i) {
        int iB = this.mSpans[0].b(i);
        for (int i2 = 1; i2 < this.mSpanCount; i2++) {
            int iB2 = this.mSpans[i2].b(i);
            if (iB2 < iB) {
                iB = iB2;
            }
        }
        return iB;
    }

    private c getNextSpan(jf jfVar) {
        int i;
        int i2;
        int i3 = -1;
        if (preferLastSpan(jfVar.f8056e)) {
            i = this.mSpanCount - 1;
            i2 = -1;
        } else {
            i = 0;
            i3 = this.mSpanCount;
            i2 = 1;
        }
        c cVar = null;
        if (jfVar.f8056e == 1) {
            int i4 = Integer.MAX_VALUE;
            int iF = this.mPrimaryOrientation.f();
            while (i != i3) {
                c cVar2 = this.mSpans[i];
                int iA = cVar2.a(iF);
                if (iA < i4) {
                    cVar = cVar2;
                    i4 = iA;
                }
                i += i2;
            }
            return cVar;
        }
        int i5 = Integer.MIN_VALUE;
        int iB = this.mPrimaryOrientation.b();
        while (i != i3) {
            c cVar3 = this.mSpans[i];
            int iB2 = cVar3.b(iB);
            if (iB2 > i5) {
                cVar = cVar3;
                i5 = iB2;
            }
            i += i2;
        }
        return cVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0043 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void handleUpdate(int r7, int r8, int r9) {
        /*
            r6 = this;
            boolean r0 = r6.mShouldReverseLayout
            if (r0 == 0) goto L9
            int r0 = r6.getLastChildPosition()
            goto Ld
        L9:
            int r0 = r6.getFirstChildPosition()
        Ld:
            r1 = 8
            if (r9 != r1) goto L1a
            if (r7 >= r8) goto L16
            int r2 = r8 + 1
            goto L1c
        L16:
            int r2 = r7 + 1
            r3 = r8
            goto L1d
        L1a:
            int r2 = r7 + r8
        L1c:
            r3 = r7
        L1d:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r4 = r6.mLazySpanLookup
            r4.e(r3)
            r4 = 1
            if (r9 == r4) goto L3c
            r5 = 2
            if (r9 == r5) goto L36
            if (r9 == r1) goto L2b
            goto L41
        L2b:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.mLazySpanLookup
            r9.b(r7, r4)
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r7 = r6.mLazySpanLookup
            r7.a(r8, r4)
            goto L41
        L36:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.mLazySpanLookup
            r9.b(r7, r8)
            goto L41
        L3c:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup r9 = r6.mLazySpanLookup
            r9.a(r7, r8)
        L41:
            if (r2 > r0) goto L44
            return
        L44:
            boolean r7 = r6.mShouldReverseLayout
            if (r7 == 0) goto L4d
            int r7 = r6.getFirstChildPosition()
            goto L51
        L4d:
            int r7 = r6.getLastChildPosition()
        L51:
            if (r3 > r7) goto L56
            r6.requestLayout()
        L56:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.handleUpdate(int, int, int):void");
    }

    private void measureChildWithDecorationsAndMargin(View view, LayoutParams layoutParams, boolean z) {
        if (layoutParams.f) {
            if (this.mOrientation == 1) {
                measureChildWithDecorationsAndMargin(view, this.mFullSizeSpec, RecyclerView.o.getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom(), ((ViewGroup.MarginLayoutParams) layoutParams).height, true), z);
                return;
            } else {
                measureChildWithDecorationsAndMargin(view, RecyclerView.o.getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight(), ((ViewGroup.MarginLayoutParams) layoutParams).width, true), this.mFullSizeSpec, z);
                return;
            }
        }
        if (this.mOrientation == 1) {
            measureChildWithDecorationsAndMargin(view, RecyclerView.o.getChildMeasureSpec(this.mSizePerSpan, getWidthMode(), 0, ((ViewGroup.MarginLayoutParams) layoutParams).width, false), RecyclerView.o.getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingTop() + getPaddingBottom(), ((ViewGroup.MarginLayoutParams) layoutParams).height, true), z);
        } else {
            measureChildWithDecorationsAndMargin(view, RecyclerView.o.getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingLeft() + getPaddingRight(), ((ViewGroup.MarginLayoutParams) layoutParams).width, true), RecyclerView.o.getChildMeasureSpec(this.mSizePerSpan, getHeightMode(), 0, ((ViewGroup.MarginLayoutParams) layoutParams).height, false), z);
        }
    }

    private boolean preferLastSpan(int i) {
        if (this.mOrientation == 0) {
            return (i == -1) != this.mShouldReverseLayout;
        }
        return ((i == -1) == this.mShouldReverseLayout) == isLayoutRTL();
    }

    private void prependViewToAllSpans(View view) {
        for (int i = this.mSpanCount - 1; i >= 0; i--) {
            this.mSpans[i].c(view);
        }
    }

    private void recycle(RecyclerView.u uVar, jf jfVar) {
        if (!jfVar.f8055a || jfVar.i) {
            return;
        }
        if (jfVar.b == 0) {
            if (jfVar.f8056e == -1) {
                recycleFromEnd(uVar, jfVar.g);
                return;
            } else {
                recycleFromStart(uVar, jfVar.f);
                return;
            }
        }
        if (jfVar.f8056e != -1) {
            int minEnd = getMinEnd(jfVar.g) - jfVar.g;
            recycleFromStart(uVar, minEnd < 0 ? jfVar.f : Math.min(minEnd, jfVar.b) + jfVar.f);
        } else {
            int i = jfVar.f;
            int maxStart = i - getMaxStart(i);
            recycleFromEnd(uVar, maxStart < 0 ? jfVar.g : jfVar.g - Math.min(maxStart, jfVar.b));
        }
    }

    private void recycleFromEnd(RecyclerView.u uVar, int i) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (this.mPrimaryOrientation.d(childAt) < i || this.mPrimaryOrientation.f(childAt) < i) {
                return;
            }
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (layoutParams.f) {
                for (int i2 = 0; i2 < this.mSpanCount; i2++) {
                    if (this.mSpans[i2].f1389a.size() == 1) {
                        return;
                    }
                }
                for (int i3 = 0; i3 < this.mSpanCount; i3++) {
                    this.mSpans[i3].n();
                }
            } else if (layoutParams.f1381e.f1389a.size() == 1) {
                return;
            } else {
                layoutParams.f1381e.n();
            }
            removeAndRecycleView(childAt, uVar);
        }
    }

    private void recycleFromStart(RecyclerView.u uVar, int i) {
        while (getChildCount() > 0) {
            View childAt = getChildAt(0);
            if (this.mPrimaryOrientation.a(childAt) > i || this.mPrimaryOrientation.e(childAt) > i) {
                return;
            }
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (layoutParams.f) {
                for (int i2 = 0; i2 < this.mSpanCount; i2++) {
                    if (this.mSpans[i2].f1389a.size() == 1) {
                        return;
                    }
                }
                for (int i3 = 0; i3 < this.mSpanCount; i3++) {
                    this.mSpans[i3].o();
                }
            } else if (layoutParams.f1381e.f1389a.size() == 1) {
                return;
            } else {
                layoutParams.f1381e.o();
            }
            removeAndRecycleView(childAt, uVar);
        }
    }

    private void repositionToWrapContentIfNecessary() {
        if (this.mSecondaryOrientation.d() == 1073741824) {
            return;
        }
        float fMax = 0.0f;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            float fB = this.mSecondaryOrientation.b(childAt);
            if (fB >= fMax) {
                if (((LayoutParams) childAt.getLayoutParams()).f()) {
                    fB = (fB * 1.0f) / this.mSpanCount;
                }
                fMax = Math.max(fMax, fB);
            }
        }
        int i2 = this.mSizePerSpan;
        int iRound = Math.round(fMax * this.mSpanCount);
        if (this.mSecondaryOrientation.d() == Integer.MIN_VALUE) {
            iRound = Math.min(iRound, this.mSecondaryOrientation.g());
        }
        updateMeasureSpecs(iRound);
        if (this.mSizePerSpan == i2) {
            return;
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt2 = getChildAt(i3);
            LayoutParams layoutParams = (LayoutParams) childAt2.getLayoutParams();
            if (!layoutParams.f) {
                if (isLayoutRTL() && this.mOrientation == 1) {
                    int i4 = this.mSpanCount;
                    int i5 = layoutParams.f1381e.f1390e;
                    childAt2.offsetLeftAndRight(((-((i4 - 1) - i5)) * this.mSizePerSpan) - ((-((i4 - 1) - i5)) * i2));
                } else {
                    int i6 = layoutParams.f1381e.f1390e;
                    int i7 = this.mSizePerSpan * i6;
                    int i8 = i6 * i2;
                    if (this.mOrientation == 1) {
                        childAt2.offsetLeftAndRight(i7 - i8);
                    } else {
                        childAt2.offsetTopAndBottom(i7 - i8);
                    }
                }
            }
        }
    }

    private void resolveShouldLayoutReverse() {
        if (this.mOrientation == 1 || !isLayoutRTL()) {
            this.mShouldReverseLayout = this.mReverseLayout;
        } else {
            this.mShouldReverseLayout = !this.mReverseLayout;
        }
    }

    private void setLayoutStateDirection(int i) {
        jf jfVar = this.mLayoutState;
        jfVar.f8056e = i;
        jfVar.d = this.mShouldReverseLayout != (i == -1) ? -1 : 1;
    }

    private void updateAllRemainingSpans(int i, int i2) {
        for (int i3 = 0; i3 < this.mSpanCount; i3++) {
            if (!this.mSpans[i3].f1389a.isEmpty()) {
                updateRemainingSpans(this.mSpans[i3], i, i2);
            }
        }
    }

    private boolean updateAnchorFromChildren(RecyclerView.y yVar, b bVar) {
        bVar.f1387a = this.mLastLayoutFromEnd ? findLastReferenceChildPosition(yVar.a()) : findFirstReferenceChildPosition(yVar.a());
        bVar.b = Integer.MIN_VALUE;
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void updateLayoutState(int r5, androidx.recyclerview.widget.RecyclerView.y r6) {
        /*
            r4 = this;
            supwisdom.jf r0 = r4.mLayoutState
            r1 = 0
            r0.b = r1
            r0.c = r5
            boolean r0 = r4.isSmoothScrolling()
            r2 = 1
            if (r0 == 0) goto L2e
            int r6 = r6.b()
            r0 = -1
            if (r6 == r0) goto L2e
            boolean r0 = r4.mShouldReverseLayout
            if (r6 >= r5) goto L1b
            r5 = 1
            goto L1c
        L1b:
            r5 = 0
        L1c:
            if (r0 != r5) goto L25
            supwisdom.mf r5 = r4.mPrimaryOrientation
            int r5 = r5.g()
            goto L2f
        L25:
            supwisdom.mf r5 = r4.mPrimaryOrientation
            int r5 = r5.g()
            r6 = r5
            r5 = 0
            goto L30
        L2e:
            r5 = 0
        L2f:
            r6 = 0
        L30:
            boolean r0 = r4.getClipToPadding()
            if (r0 == 0) goto L4d
            supwisdom.jf r0 = r4.mLayoutState
            supwisdom.mf r3 = r4.mPrimaryOrientation
            int r3 = r3.f()
            int r3 = r3 - r6
            r0.f = r3
            supwisdom.jf r6 = r4.mLayoutState
            supwisdom.mf r0 = r4.mPrimaryOrientation
            int r0 = r0.b()
            int r0 = r0 + r5
            r6.g = r0
            goto L5d
        L4d:
            supwisdom.jf r0 = r4.mLayoutState
            supwisdom.mf r3 = r4.mPrimaryOrientation
            int r3 = r3.a()
            int r3 = r3 + r5
            r0.g = r3
            supwisdom.jf r5 = r4.mLayoutState
            int r6 = -r6
            r5.f = r6
        L5d:
            supwisdom.jf r5 = r4.mLayoutState
            r5.h = r1
            r5.f8055a = r2
            supwisdom.mf r6 = r4.mPrimaryOrientation
            int r6 = r6.d()
            if (r6 != 0) goto L74
            supwisdom.mf r6 = r4.mPrimaryOrientation
            int r6 = r6.a()
            if (r6 != 0) goto L74
            r1 = 1
        L74:
            r5.i = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.updateLayoutState(int, androidx.recyclerview.widget.RecyclerView$y):void");
    }

    private void updateRemainingSpans(c cVar, int i, int i2) {
        int iJ = cVar.j();
        if (i == -1) {
            if (cVar.l() + iJ <= i2) {
                this.mRemainingSpans.set(cVar.f1390e, false);
            }
        } else if (cVar.k() - iJ >= i2) {
            this.mRemainingSpans.set(cVar.f1390e, false);
        }
    }

    private int updateSpecWithExtra(int i, int i2, int i3) {
        if (i2 == 0 && i3 == 0) {
            return i;
        }
        int mode = View.MeasureSpec.getMode(i);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i) - i2) - i3), mode) : i;
    }

    public boolean areAllEndsEqual() {
        int iA = this.mSpans[0].a(Integer.MIN_VALUE);
        for (int i = 1; i < this.mSpanCount; i++) {
            if (this.mSpans[i].a(Integer.MIN_VALUE) != iA) {
                return false;
            }
        }
        return true;
    }

    public boolean areAllStartsEqual() {
        int iB = this.mSpans[0].b(Integer.MIN_VALUE);
        for (int i = 1; i < this.mSpanCount; i++) {
            if (this.mSpans[i].b(Integer.MIN_VALUE) != iB) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void assertNotInLayoutOrScroll(String str) {
        if (this.mPendingSavedState == null) {
            super.assertNotInLayoutOrScroll(str);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean canScrollHorizontally() {
        return this.mOrientation == 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean canScrollVertically() {
        return this.mOrientation == 1;
    }

    public boolean checkForGaps() {
        int firstChildPosition;
        int lastChildPosition;
        if (getChildCount() == 0 || this.mGapStrategy == 0 || !isAttachedToWindow()) {
            return false;
        }
        if (this.mShouldReverseLayout) {
            firstChildPosition = getLastChildPosition();
            lastChildPosition = getFirstChildPosition();
        } else {
            firstChildPosition = getFirstChildPosition();
            lastChildPosition = getLastChildPosition();
        }
        if (firstChildPosition == 0 && hasGapsToFix() != null) {
            this.mLazySpanLookup.a();
            requestSimpleAnimationsInNextLayout();
            requestLayout();
            return true;
        }
        if (!this.mLaidOutInvalidFullSpan) {
            return false;
        }
        int i = this.mShouldReverseLayout ? -1 : 1;
        int i2 = lastChildPosition + 1;
        LazySpanLookup.FullSpanItem fullSpanItemA = this.mLazySpanLookup.a(firstChildPosition, i2, i, true);
        if (fullSpanItemA == null) {
            this.mLaidOutInvalidFullSpan = false;
            this.mLazySpanLookup.b(i2);
            return false;
        }
        LazySpanLookup.FullSpanItem fullSpanItemA2 = this.mLazySpanLookup.a(firstChildPosition, fullSpanItemA.f1383a, i * (-1), true);
        if (fullSpanItemA2 == null) {
            this.mLazySpanLookup.b(fullSpanItemA.f1383a);
        } else {
            this.mLazySpanLookup.b(fullSpanItemA2.f1383a + 1);
        }
        requestSimpleAnimationsInNextLayout();
        requestLayout();
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void collectAdjacentPrefetchPositions(int i, int i2, RecyclerView.y yVar, RecyclerView.o.c cVar) {
        int iA;
        int iB;
        if (this.mOrientation != 0) {
            i = i2;
        }
        if (getChildCount() == 0 || i == 0) {
            return;
        }
        prepareLayoutStateForDelta(i, yVar);
        int[] iArr = this.mPrefetchDistances;
        if (iArr == null || iArr.length < this.mSpanCount) {
            this.mPrefetchDistances = new int[this.mSpanCount];
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.mSpanCount; i4++) {
            jf jfVar = this.mLayoutState;
            if (jfVar.d == -1) {
                iA = jfVar.f;
                iB = this.mSpans[i4].b(iA);
            } else {
                iA = this.mSpans[i4].a(jfVar.g);
                iB = this.mLayoutState.g;
            }
            int i5 = iA - iB;
            if (i5 >= 0) {
                this.mPrefetchDistances[i3] = i5;
                i3++;
            }
        }
        Arrays.sort(this.mPrefetchDistances, 0, i3);
        for (int i6 = 0; i6 < i3 && this.mLayoutState.a(yVar); i6++) {
            cVar.a(this.mLayoutState.c, this.mPrefetchDistances[i6]);
            jf jfVar2 = this.mLayoutState;
            jfVar2.c += jfVar2.d;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeHorizontalScrollExtent(RecyclerView.y yVar) {
        return computeScrollExtent(yVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeHorizontalScrollOffset(RecyclerView.y yVar) {
        return computeScrollOffset(yVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeHorizontalScrollRange(RecyclerView.y yVar) {
        return computeScrollRange(yVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.x.b
    public PointF computeScrollVectorForPosition(int i) {
        int iCalculateScrollDirectionForPosition = calculateScrollDirectionForPosition(i);
        PointF pointF = new PointF();
        if (iCalculateScrollDirectionForPosition == 0) {
            return null;
        }
        if (this.mOrientation == 0) {
            pointF.x = iCalculateScrollDirectionForPosition;
            pointF.y = 0.0f;
        } else {
            pointF.x = 0.0f;
            pointF.y = iCalculateScrollDirectionForPosition;
        }
        return pointF;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeVerticalScrollExtent(RecyclerView.y yVar) {
        return computeScrollExtent(yVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeVerticalScrollOffset(RecyclerView.y yVar) {
        return computeScrollOffset(yVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int computeVerticalScrollRange(RecyclerView.y yVar) {
        return computeScrollRange(yVar);
    }

    public int[] findFirstCompletelyVisibleItemPositions(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.mSpanCount];
        } else if (iArr.length < this.mSpanCount) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + iArr.length);
        }
        for (int i = 0; i < this.mSpanCount; i++) {
            iArr[i] = this.mSpans[i].d();
        }
        return iArr;
    }

    public View findFirstVisibleItemClosestToEnd(boolean z) {
        int iF = this.mPrimaryOrientation.f();
        int iB = this.mPrimaryOrientation.b();
        View view = null;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            int iD = this.mPrimaryOrientation.d(childAt);
            int iA = this.mPrimaryOrientation.a(childAt);
            if (iA > iF && iD < iB) {
                if (iA <= iB || !z) {
                    return childAt;
                }
                if (view == null) {
                    view = childAt;
                }
            }
        }
        return view;
    }

    public View findFirstVisibleItemClosestToStart(boolean z) {
        int iF = this.mPrimaryOrientation.f();
        int iB = this.mPrimaryOrientation.b();
        int childCount = getChildCount();
        View view = null;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int iD = this.mPrimaryOrientation.d(childAt);
            if (this.mPrimaryOrientation.a(childAt) > iF && iD < iB) {
                if (iD >= iF || !z) {
                    return childAt;
                }
                if (view == null) {
                    view = childAt;
                }
            }
        }
        return view;
    }

    public int findFirstVisibleItemPositionInt() {
        View viewFindFirstVisibleItemClosestToEnd = this.mShouldReverseLayout ? findFirstVisibleItemClosestToEnd(true) : findFirstVisibleItemClosestToStart(true);
        if (viewFindFirstVisibleItemClosestToEnd == null) {
            return -1;
        }
        return getPosition(viewFindFirstVisibleItemClosestToEnd);
    }

    public int[] findFirstVisibleItemPositions(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.mSpanCount];
        } else if (iArr.length < this.mSpanCount) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + iArr.length);
        }
        for (int i = 0; i < this.mSpanCount; i++) {
            iArr[i] = this.mSpans[i].f();
        }
        return iArr;
    }

    public int[] findLastCompletelyVisibleItemPositions(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.mSpanCount];
        } else if (iArr.length < this.mSpanCount) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + iArr.length);
        }
        for (int i = 0; i < this.mSpanCount; i++) {
            iArr[i] = this.mSpans[i].g();
        }
        return iArr;
    }

    public int[] findLastVisibleItemPositions(int[] iArr) {
        if (iArr == null) {
            iArr = new int[this.mSpanCount];
        } else if (iArr.length < this.mSpanCount) {
            throw new IllegalArgumentException("Provided int[]'s size must be more than or equal to span count. Expected:" + this.mSpanCount + ", array size:" + iArr.length);
        }
        for (int i = 0; i < this.mSpanCount; i++) {
            iArr[i] = this.mSpans[i].i();
        }
        return iArr;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return this.mOrientation == 0 ? new LayoutParams(-2, -1) : new LayoutParams(-1, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int getColumnCountForAccessibility(RecyclerView.u uVar, RecyclerView.y yVar) {
        return this.mOrientation == 1 ? this.mSpanCount : super.getColumnCountForAccessibility(uVar, yVar);
    }

    public int getFirstChildPosition() {
        if (getChildCount() == 0) {
            return 0;
        }
        return getPosition(getChildAt(0));
    }

    public int getGapStrategy() {
        return this.mGapStrategy;
    }

    public int getLastChildPosition() {
        int childCount = getChildCount();
        if (childCount == 0) {
            return 0;
        }
        return getPosition(getChildAt(childCount - 1));
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public boolean getReverseLayout() {
        return this.mReverseLayout;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int getRowCountForAccessibility(RecyclerView.u uVar, RecyclerView.y yVar) {
        return this.mOrientation == 0 ? this.mSpanCount : super.getRowCountForAccessibility(uVar, yVar);
    }

    public int getSpanCount() {
        return this.mSpanCount;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.View hasGapsToFix() {
        /*
            r12 = this;
            int r0 = r12.getChildCount()
            r1 = 1
            int r0 = r0 - r1
            java.util.BitSet r2 = new java.util.BitSet
            int r3 = r12.mSpanCount
            r2.<init>(r3)
            int r3 = r12.mSpanCount
            r4 = 0
            r2.set(r4, r3, r1)
            int r3 = r12.mOrientation
            r5 = -1
            if (r3 != r1) goto L20
            boolean r3 = r12.isLayoutRTL()
            if (r3 == 0) goto L20
            r3 = 1
            goto L21
        L20:
            r3 = -1
        L21:
            boolean r6 = r12.mShouldReverseLayout
            if (r6 == 0) goto L27
            r6 = -1
            goto L2b
        L27:
            int r0 = r0 + 1
            r6 = r0
            r0 = 0
        L2b:
            if (r0 >= r6) goto L2e
            r5 = 1
        L2e:
            if (r0 == r6) goto Lab
            android.view.View r7 = r12.getChildAt(r0)
            android.view.ViewGroup$LayoutParams r8 = r7.getLayoutParams()
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LayoutParams r8 = (androidx.recyclerview.widget.StaggeredGridLayoutManager.LayoutParams) r8
            androidx.recyclerview.widget.StaggeredGridLayoutManager$c r9 = r8.f1381e
            int r9 = r9.f1390e
            boolean r9 = r2.get(r9)
            if (r9 == 0) goto L54
            androidx.recyclerview.widget.StaggeredGridLayoutManager$c r9 = r8.f1381e
            boolean r9 = r12.checkSpanForGap(r9)
            if (r9 == 0) goto L4d
            return r7
        L4d:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$c r9 = r8.f1381e
            int r9 = r9.f1390e
            r2.clear(r9)
        L54:
            boolean r9 = r8.f
            if (r9 == 0) goto L59
            goto La9
        L59:
            int r9 = r0 + r5
            if (r9 == r6) goto La9
            android.view.View r9 = r12.getChildAt(r9)
            boolean r10 = r12.mShouldReverseLayout
            if (r10 == 0) goto L77
            supwisdom.mf r10 = r12.mPrimaryOrientation
            int r10 = r10.a(r7)
            supwisdom.mf r11 = r12.mPrimaryOrientation
            int r11 = r11.a(r9)
            if (r10 >= r11) goto L74
            return r7
        L74:
            if (r10 != r11) goto L8a
            goto L88
        L77:
            supwisdom.mf r10 = r12.mPrimaryOrientation
            int r10 = r10.d(r7)
            supwisdom.mf r11 = r12.mPrimaryOrientation
            int r11 = r11.d(r9)
            if (r10 <= r11) goto L86
            return r7
        L86:
            if (r10 != r11) goto L8a
        L88:
            r10 = 1
            goto L8b
        L8a:
            r10 = 0
        L8b:
            if (r10 == 0) goto La9
            android.view.ViewGroup$LayoutParams r9 = r9.getLayoutParams()
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LayoutParams r9 = (androidx.recyclerview.widget.StaggeredGridLayoutManager.LayoutParams) r9
            androidx.recyclerview.widget.StaggeredGridLayoutManager$c r8 = r8.f1381e
            int r8 = r8.f1390e
            androidx.recyclerview.widget.StaggeredGridLayoutManager$c r9 = r9.f1381e
            int r9 = r9.f1390e
            int r8 = r8 - r9
            if (r8 >= 0) goto La0
            r8 = 1
            goto La1
        La0:
            r8 = 0
        La1:
            if (r3 >= 0) goto La5
            r9 = 1
            goto La6
        La5:
            r9 = 0
        La6:
            if (r8 == r9) goto La9
            return r7
        La9:
            int r0 = r0 + r5
            goto L2e
        Lab:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.hasGapsToFix():android.view.View");
    }

    public void invalidateSpanAssignments() {
        this.mLazySpanLookup.a();
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean isAutoMeasureEnabled() {
        return this.mGapStrategy != 0;
    }

    public boolean isLayoutRTL() {
        return getLayoutDirection() == 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void offsetChildrenHorizontal(int i) {
        super.offsetChildrenHorizontal(i);
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            this.mSpans[i2].c(i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void offsetChildrenVertical(int i) {
        super.offsetChildrenVertical(i);
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            this.mSpans[i2].c(i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.u uVar) {
        super.onDetachedFromWindow(recyclerView, uVar);
        removeCallbacks(this.mCheckForGapsRunnable);
        for (int i = 0; i < this.mSpanCount; i++) {
            this.mSpans[i].c();
        }
        recyclerView.requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View onFocusSearchFailed(View view, int i, RecyclerView.u uVar, RecyclerView.y yVar) {
        View viewFindContainingItemView;
        View viewA;
        if (getChildCount() == 0 || (viewFindContainingItemView = findContainingItemView(view)) == null) {
            return null;
        }
        resolveShouldLayoutReverse();
        int iConvertFocusDirectionToLayoutDirection = convertFocusDirectionToLayoutDirection(i);
        if (iConvertFocusDirectionToLayoutDirection == Integer.MIN_VALUE) {
            return null;
        }
        LayoutParams layoutParams = (LayoutParams) viewFindContainingItemView.getLayoutParams();
        boolean z = layoutParams.f;
        c cVar = layoutParams.f1381e;
        int lastChildPosition = iConvertFocusDirectionToLayoutDirection == 1 ? getLastChildPosition() : getFirstChildPosition();
        updateLayoutState(lastChildPosition, yVar);
        setLayoutStateDirection(iConvertFocusDirectionToLayoutDirection);
        jf jfVar = this.mLayoutState;
        jfVar.c = jfVar.d + lastChildPosition;
        jfVar.b = (int) (this.mPrimaryOrientation.g() * 0.33333334f);
        jf jfVar2 = this.mLayoutState;
        jfVar2.h = true;
        jfVar2.f8055a = false;
        fill(uVar, jfVar2, yVar);
        this.mLastLayoutFromEnd = this.mShouldReverseLayout;
        if (!z && (viewA = cVar.a(lastChildPosition, iConvertFocusDirectionToLayoutDirection)) != null && viewA != viewFindContainingItemView) {
            return viewA;
        }
        if (preferLastSpan(iConvertFocusDirectionToLayoutDirection)) {
            for (int i2 = this.mSpanCount - 1; i2 >= 0; i2--) {
                View viewA2 = this.mSpans[i2].a(lastChildPosition, iConvertFocusDirectionToLayoutDirection);
                if (viewA2 != null && viewA2 != viewFindContainingItemView) {
                    return viewA2;
                }
            }
        } else {
            for (int i3 = 0; i3 < this.mSpanCount; i3++) {
                View viewA3 = this.mSpans[i3].a(lastChildPosition, iConvertFocusDirectionToLayoutDirection);
                if (viewA3 != null && viewA3 != viewFindContainingItemView) {
                    return viewA3;
                }
            }
        }
        boolean z2 = (this.mReverseLayout ^ true) == (iConvertFocusDirectionToLayoutDirection == -1);
        if (!z) {
            View viewFindViewByPosition = findViewByPosition(z2 ? cVar.e() : cVar.h());
            if (viewFindViewByPosition != null && viewFindViewByPosition != viewFindContainingItemView) {
                return viewFindViewByPosition;
            }
        }
        if (preferLastSpan(iConvertFocusDirectionToLayoutDirection)) {
            for (int i4 = this.mSpanCount - 1; i4 >= 0; i4--) {
                if (i4 != cVar.f1390e) {
                    View viewFindViewByPosition2 = findViewByPosition(z2 ? this.mSpans[i4].e() : this.mSpans[i4].h());
                    if (viewFindViewByPosition2 != null && viewFindViewByPosition2 != viewFindContainingItemView) {
                        return viewFindViewByPosition2;
                    }
                }
            }
        } else {
            for (int i5 = 0; i5 < this.mSpanCount; i5++) {
                View viewFindViewByPosition3 = findViewByPosition(z2 ? this.mSpans[i5].e() : this.mSpans[i5].h());
                if (viewFindViewByPosition3 != null && viewFindViewByPosition3 != viewFindContainingItemView) {
                    return viewFindViewByPosition3;
                }
            }
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            View viewFindFirstVisibleItemClosestToStart = findFirstVisibleItemClosestToStart(false);
            View viewFindFirstVisibleItemClosestToEnd = findFirstVisibleItemClosestToEnd(false);
            if (viewFindFirstVisibleItemClosestToStart == null || viewFindFirstVisibleItemClosestToEnd == null) {
                return;
            }
            int position = getPosition(viewFindFirstVisibleItemClosestToStart);
            int position2 = getPosition(viewFindFirstVisibleItemClosestToEnd);
            if (position < position2) {
                accessibilityEvent.setFromIndex(position);
                accessibilityEvent.setToIndex(position2);
            } else {
                accessibilityEvent.setFromIndex(position2);
                accessibilityEvent.setToIndex(position);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.u uVar, RecyclerView.y yVar, View view, xb xbVar) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            super.onInitializeAccessibilityNodeInfoForItem(view, xbVar);
            return;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        if (this.mOrientation == 0) {
            xbVar.b(xb.c.a(layoutParams2.e(), layoutParams2.f ? this.mSpanCount : 1, -1, -1, layoutParams2.f, false));
        } else {
            xbVar.b(xb.c.a(-1, -1, layoutParams2.e(), layoutParams2.f ? this.mSpanCount : 1, layoutParams2.f, false));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onItemsAdded(RecyclerView recyclerView, int i, int i2) {
        handleUpdate(i, i2, 1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onItemsChanged(RecyclerView recyclerView) {
        this.mLazySpanLookup.a();
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onItemsMoved(RecyclerView recyclerView, int i, int i2, int i3) {
        handleUpdate(i, i2, 8);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onItemsRemoved(RecyclerView recyclerView, int i, int i2) {
        handleUpdate(i, i2, 2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onItemsUpdated(RecyclerView recyclerView, int i, int i2, Object obj) {
        handleUpdate(i, i2, 4);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onLayoutChildren(RecyclerView.u uVar, RecyclerView.y yVar) {
        onLayoutChildren(uVar, yVar, true);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onLayoutCompleted(RecyclerView.y yVar) {
        super.onLayoutCompleted(yVar);
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo.b();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.mPendingSavedState = (SavedState) parcelable;
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public Parcelable onSaveInstanceState() {
        int iB;
        int iF;
        int[] iArr;
        if (this.mPendingSavedState != null) {
            return new SavedState(this.mPendingSavedState);
        }
        SavedState savedState = new SavedState();
        savedState.h = this.mReverseLayout;
        savedState.i = this.mLastLayoutFromEnd;
        savedState.j = this.mLastLayoutRTL;
        LazySpanLookup lazySpanLookup = this.mLazySpanLookup;
        if (lazySpanLookup == null || (iArr = lazySpanLookup.f1382a) == null) {
            savedState.f1385e = 0;
        } else {
            savedState.f = iArr;
            savedState.f1385e = iArr.length;
            savedState.g = lazySpanLookup.b;
        }
        if (getChildCount() > 0) {
            savedState.f1384a = this.mLastLayoutFromEnd ? getLastChildPosition() : getFirstChildPosition();
            savedState.b = findFirstVisibleItemPositionInt();
            int i = this.mSpanCount;
            savedState.c = i;
            savedState.d = new int[i];
            for (int i2 = 0; i2 < this.mSpanCount; i2++) {
                if (this.mLastLayoutFromEnd) {
                    iB = this.mSpans[i2].a(Integer.MIN_VALUE);
                    if (iB != Integer.MIN_VALUE) {
                        iF = this.mPrimaryOrientation.b();
                        iB -= iF;
                    }
                } else {
                    iB = this.mSpans[i2].b(Integer.MIN_VALUE);
                    if (iB != Integer.MIN_VALUE) {
                        iF = this.mPrimaryOrientation.f();
                        iB -= iF;
                    }
                }
                savedState.d[i2] = iB;
            }
        } else {
            savedState.f1384a = -1;
            savedState.b = -1;
            savedState.c = 0;
        }
        return savedState;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onScrollStateChanged(int i) {
        if (i == 0) {
            checkForGaps();
        }
    }

    public void prepareLayoutStateForDelta(int i, RecyclerView.y yVar) {
        int firstChildPosition;
        int i2;
        if (i > 0) {
            firstChildPosition = getLastChildPosition();
            i2 = 1;
        } else {
            firstChildPosition = getFirstChildPosition();
            i2 = -1;
        }
        this.mLayoutState.f8055a = true;
        updateLayoutState(firstChildPosition, yVar);
        setLayoutStateDirection(i2);
        jf jfVar = this.mLayoutState;
        jfVar.c = firstChildPosition + jfVar.d;
        jfVar.b = Math.abs(i);
    }

    public int scrollBy(int i, RecyclerView.u uVar, RecyclerView.y yVar) {
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        prepareLayoutStateForDelta(i, yVar);
        int iFill = fill(uVar, this.mLayoutState, yVar);
        if (this.mLayoutState.b >= iFill) {
            i = i < 0 ? -iFill : iFill;
        }
        this.mPrimaryOrientation.a(-i);
        this.mLastLayoutFromEnd = this.mShouldReverseLayout;
        jf jfVar = this.mLayoutState;
        jfVar.b = 0;
        recycle(uVar, jfVar);
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int scrollHorizontallyBy(int i, RecyclerView.u uVar, RecyclerView.y yVar) {
        return scrollBy(i, uVar, yVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void scrollToPosition(int i) {
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null && savedState.f1384a != i) {
            savedState.a();
        }
        this.mPendingScrollPosition = i;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        requestLayout();
    }

    public void scrollToPositionWithOffset(int i, int i2) {
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            savedState.a();
        }
        this.mPendingScrollPosition = i;
        this.mPendingScrollPositionOffset = i2;
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int scrollVerticallyBy(int i, RecyclerView.u uVar, RecyclerView.y yVar) {
        return scrollBy(i, uVar, yVar);
    }

    public void setGapStrategy(int i) {
        assertNotInLayoutOrScroll(null);
        if (i == this.mGapStrategy) {
            return;
        }
        if (i != 0 && i != 2) {
            throw new IllegalArgumentException("invalid gap strategy. Must be GAP_HANDLING_NONE or GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS");
        }
        this.mGapStrategy = i;
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void setMeasuredDimension(Rect rect, int i, int i2) {
        int iChooseSize;
        int iChooseSize2;
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        if (this.mOrientation == 1) {
            iChooseSize2 = RecyclerView.o.chooseSize(i2, rect.height() + paddingTop, getMinimumHeight());
            iChooseSize = RecyclerView.o.chooseSize(i, (this.mSizePerSpan * this.mSpanCount) + paddingLeft, getMinimumWidth());
        } else {
            iChooseSize = RecyclerView.o.chooseSize(i, rect.width() + paddingLeft, getMinimumWidth());
            iChooseSize2 = RecyclerView.o.chooseSize(i2, (this.mSizePerSpan * this.mSpanCount) + paddingTop, getMinimumHeight());
        }
        setMeasuredDimension(iChooseSize, iChooseSize2);
    }

    public void setOrientation(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        assertNotInLayoutOrScroll(null);
        if (i == this.mOrientation) {
            return;
        }
        this.mOrientation = i;
        mf mfVar = this.mPrimaryOrientation;
        this.mPrimaryOrientation = this.mSecondaryOrientation;
        this.mSecondaryOrientation = mfVar;
        requestLayout();
    }

    public void setReverseLayout(boolean z) {
        assertNotInLayoutOrScroll(null);
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null && savedState.h != z) {
            savedState.h = z;
        }
        this.mReverseLayout = z;
        requestLayout();
    }

    public void setSpanCount(int i) {
        assertNotInLayoutOrScroll(null);
        if (i != this.mSpanCount) {
            invalidateSpanAssignments();
            this.mSpanCount = i;
            this.mRemainingSpans = new BitSet(this.mSpanCount);
            this.mSpans = new c[this.mSpanCount];
            for (int i2 = 0; i2 < this.mSpanCount; i2++) {
                this.mSpans[i2] = new c(i2);
            }
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.y yVar, int i) {
        kf kfVar = new kf(recyclerView.getContext());
        kfVar.setTargetPosition(i);
        startSmoothScroll(kfVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null;
    }

    public boolean updateAnchorFromPendingData(RecyclerView.y yVar, b bVar) {
        int i;
        if (!yVar.d() && (i = this.mPendingScrollPosition) != -1) {
            if (i >= 0 && i < yVar.a()) {
                SavedState savedState = this.mPendingSavedState;
                if (savedState == null || savedState.f1384a == -1 || savedState.c < 1) {
                    View viewFindViewByPosition = findViewByPosition(this.mPendingScrollPosition);
                    if (viewFindViewByPosition != null) {
                        bVar.f1387a = this.mShouldReverseLayout ? getLastChildPosition() : getFirstChildPosition();
                        if (this.mPendingScrollPositionOffset != Integer.MIN_VALUE) {
                            if (bVar.c) {
                                bVar.b = (this.mPrimaryOrientation.b() - this.mPendingScrollPositionOffset) - this.mPrimaryOrientation.a(viewFindViewByPosition);
                            } else {
                                bVar.b = (this.mPrimaryOrientation.f() + this.mPendingScrollPositionOffset) - this.mPrimaryOrientation.d(viewFindViewByPosition);
                            }
                            return true;
                        }
                        if (this.mPrimaryOrientation.b(viewFindViewByPosition) > this.mPrimaryOrientation.g()) {
                            bVar.b = bVar.c ? this.mPrimaryOrientation.b() : this.mPrimaryOrientation.f();
                            return true;
                        }
                        int iD = this.mPrimaryOrientation.d(viewFindViewByPosition) - this.mPrimaryOrientation.f();
                        if (iD < 0) {
                            bVar.b = -iD;
                            return true;
                        }
                        int iB = this.mPrimaryOrientation.b() - this.mPrimaryOrientation.a(viewFindViewByPosition);
                        if (iB < 0) {
                            bVar.b = iB;
                            return true;
                        }
                        bVar.b = Integer.MIN_VALUE;
                    } else {
                        int i2 = this.mPendingScrollPosition;
                        bVar.f1387a = i2;
                        int i3 = this.mPendingScrollPositionOffset;
                        if (i3 == Integer.MIN_VALUE) {
                            bVar.c = calculateScrollDirectionForPosition(i2) == 1;
                            bVar.a();
                        } else {
                            bVar.a(i3);
                        }
                        bVar.d = true;
                    }
                } else {
                    bVar.b = Integer.MIN_VALUE;
                    bVar.f1387a = this.mPendingScrollPosition;
                }
                return true;
            }
            this.mPendingScrollPosition = -1;
            this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        }
        return false;
    }

    public void updateAnchorInfoForLayout(RecyclerView.y yVar, b bVar) {
        if (updateAnchorFromPendingData(yVar, bVar) || updateAnchorFromChildren(yVar, bVar)) {
            return;
        }
        bVar.a();
        bVar.f1387a = 0;
    }

    public void updateMeasureSpecs(int i) {
        this.mSizePerSpan = i / this.mSpanCount;
        this.mFullSizeSpec = View.MeasureSpec.makeMeasureSpec(i, this.mSecondaryOrientation.d());
    }

    public static class LazySpanLookup {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int[] f1382a;
        public List<FullSpanItem> b;

        public void a(int i, c cVar) {
            a(i);
            this.f1382a[i] = cVar.f1390e;
        }

        public int b(int i) {
            List<FullSpanItem> list = this.b;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    if (this.b.get(size).f1383a >= i) {
                        this.b.remove(size);
                    }
                }
            }
            return e(i);
        }

        public final void c(int i, int i2) {
            List<FullSpanItem> list = this.b;
            if (list == null) {
                return;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.b.get(size);
                int i3 = fullSpanItem.f1383a;
                if (i3 >= i) {
                    fullSpanItem.f1383a = i3 + i2;
                }
            }
        }

        public int d(int i) {
            int[] iArr = this.f1382a;
            if (iArr == null || i >= iArr.length) {
                return -1;
            }
            return iArr[i];
        }

        public int e(int i) {
            int[] iArr = this.f1382a;
            if (iArr == null || i >= iArr.length) {
                return -1;
            }
            int iF = f(i);
            if (iF == -1) {
                int[] iArr2 = this.f1382a;
                Arrays.fill(iArr2, i, iArr2.length, -1);
                return this.f1382a.length;
            }
            int i2 = iF + 1;
            Arrays.fill(this.f1382a, i, i2, -1);
            return i2;
        }

        public final int f(int i) {
            if (this.b == null) {
                return -1;
            }
            FullSpanItem fullSpanItemC = c(i);
            if (fullSpanItemC != null) {
                this.b.remove(fullSpanItemC);
            }
            int size = this.b.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    i2 = -1;
                    break;
                }
                if (this.b.get(i2).f1383a >= i) {
                    break;
                }
                i2++;
            }
            if (i2 == -1) {
                return -1;
            }
            FullSpanItem fullSpanItem = this.b.get(i2);
            this.b.remove(i2);
            return fullSpanItem.f1383a;
        }

        public int g(int i) {
            int length = this.f1382a.length;
            while (length <= i) {
                length *= 2;
            }
            return length;
        }

        public void a(int i) {
            int[] iArr = this.f1382a;
            if (iArr == null) {
                int[] iArr2 = new int[Math.max(i, 10) + 1];
                this.f1382a = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i >= iArr.length) {
                int[] iArr3 = new int[g(i)];
                this.f1382a = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
                int[] iArr4 = this.f1382a;
                Arrays.fill(iArr4, iArr.length, iArr4.length, -1);
            }
        }

        public final void d(int i, int i2) {
            List<FullSpanItem> list = this.b;
            if (list == null) {
                return;
            }
            int i3 = i + i2;
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.b.get(size);
                int i4 = fullSpanItem.f1383a;
                if (i4 >= i) {
                    if (i4 < i3) {
                        this.b.remove(size);
                    } else {
                        fullSpanItem.f1383a = i4 - i2;
                    }
                }
            }
        }

        public FullSpanItem c(int i) {
            List<FullSpanItem> list = this.b;
            if (list == null) {
                return null;
            }
            for (int size = list.size() - 1; size >= 0; size--) {
                FullSpanItem fullSpanItem = this.b.get(size);
                if (fullSpanItem.f1383a == i) {
                    return fullSpanItem;
                }
            }
            return null;
        }

        public static class FullSpanItem implements Parcelable {
            public static final Parcelable.Creator<FullSpanItem> CREATOR = new a();

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f1383a;
            public int b;
            public int[] c;
            public boolean d;

            public static class a implements Parcelable.Creator<FullSpanItem> {
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public FullSpanItem createFromParcel(Parcel parcel) {
                    return new FullSpanItem(parcel);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public FullSpanItem[] newArray(int i) {
                    return new FullSpanItem[i];
                }
            }

            public FullSpanItem(Parcel parcel) {
                this.f1383a = parcel.readInt();
                this.b = parcel.readInt();
                this.d = parcel.readInt() == 1;
                int i = parcel.readInt();
                if (i > 0) {
                    int[] iArr = new int[i];
                    this.c = iArr;
                    parcel.readIntArray(iArr);
                }
            }

            public int a(int i) {
                int[] iArr = this.c;
                if (iArr == null) {
                    return 0;
                }
                return iArr[i];
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public String toString() {
                return "FullSpanItem{mPosition=" + this.f1383a + ", mGapDir=" + this.b + ", mHasUnwantedGapAfter=" + this.d + ", mGapPerSpan=" + Arrays.toString(this.c) + Operators.BLOCK_END;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.f1383a);
                parcel.writeInt(this.b);
                parcel.writeInt(this.d ? 1 : 0);
                int[] iArr = this.c;
                if (iArr == null || iArr.length <= 0) {
                    parcel.writeInt(0);
                } else {
                    parcel.writeInt(iArr.length);
                    parcel.writeIntArray(this.c);
                }
            }

            public FullSpanItem() {
            }
        }

        public void b(int i, int i2) {
            int[] iArr = this.f1382a;
            if (iArr == null || i >= iArr.length) {
                return;
            }
            int i3 = i + i2;
            a(i3);
            int[] iArr2 = this.f1382a;
            System.arraycopy(iArr2, i3, iArr2, i, (iArr2.length - i) - i2);
            int[] iArr3 = this.f1382a;
            Arrays.fill(iArr3, iArr3.length - i2, iArr3.length, -1);
            d(i, i2);
        }

        public void a() {
            int[] iArr = this.f1382a;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.b = null;
        }

        public void a(int i, int i2) {
            int[] iArr = this.f1382a;
            if (iArr == null || i >= iArr.length) {
                return;
            }
            int i3 = i + i2;
            a(i3);
            int[] iArr2 = this.f1382a;
            System.arraycopy(iArr2, i, iArr2, i3, (iArr2.length - i) - i2);
            Arrays.fill(this.f1382a, i, i3, -1);
            c(i, i2);
        }

        public void a(FullSpanItem fullSpanItem) {
            if (this.b == null) {
                this.b = new ArrayList();
            }
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                FullSpanItem fullSpanItem2 = this.b.get(i);
                if (fullSpanItem2.f1383a == fullSpanItem.f1383a) {
                    this.b.remove(i);
                }
                if (fullSpanItem2.f1383a >= fullSpanItem.f1383a) {
                    this.b.add(i, fullSpanItem);
                    return;
                }
            }
            this.b.add(fullSpanItem);
        }

        public FullSpanItem a(int i, int i2, int i3, boolean z) {
            List<FullSpanItem> list = this.b;
            if (list == null) {
                return null;
            }
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                FullSpanItem fullSpanItem = this.b.get(i4);
                int i5 = fullSpanItem.f1383a;
                if (i5 >= i2) {
                    return null;
                }
                if (i5 >= i && (i3 == 0 || fullSpanItem.b == i3 || (z && fullSpanItem.d))) {
                    return fullSpanItem;
                }
            }
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:89:0x015a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void onLayoutChildren(androidx.recyclerview.widget.RecyclerView.u r9, androidx.recyclerview.widget.RecyclerView.y r10, boolean r11) {
        /*
            Method dump skipped, instruction units count: 379
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.onLayoutChildren(androidx.recyclerview.widget.RecyclerView$u, androidx.recyclerview.widget.RecyclerView$y, boolean):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    public class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1387a;
        public int b;
        public boolean c;
        public boolean d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1388e;
        public int[] f;

        public b() {
            b();
        }

        public void a(c[] cVarArr) {
            int length = cVarArr.length;
            int[] iArr = this.f;
            if (iArr == null || iArr.length < length) {
                this.f = new int[StaggeredGridLayoutManager.this.mSpans.length];
            }
            for (int i = 0; i < length; i++) {
                this.f[i] = cVarArr[i].b(Integer.MIN_VALUE);
            }
        }

        public void b() {
            this.f1387a = -1;
            this.b = Integer.MIN_VALUE;
            this.c = false;
            this.d = false;
            this.f1388e = false;
            int[] iArr = this.f;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
        }

        public void a() {
            this.b = this.c ? StaggeredGridLayoutManager.this.mPrimaryOrientation.b() : StaggeredGridLayoutManager.this.mPrimaryOrientation.f();
        }

        public void a(int i) {
            if (this.c) {
                this.b = StaggeredGridLayoutManager.this.mPrimaryOrientation.b() - i;
            } else {
                this.b = StaggeredGridLayoutManager.this.mPrimaryOrientation.f() + i;
            }
        }
    }

    public StaggeredGridLayoutManager(int i, int i2) {
        this.mOrientation = i2;
        setSpanCount(i);
        this.mLayoutState = new jf();
        createOrientationHelpers();
    }

    private void measureChildWithDecorationsAndMargin(View view, int i, int i2, boolean z) {
        boolean zShouldMeasureChild;
        calculateItemDecorationsForChild(view, this.mTmpRect);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
        Rect rect = this.mTmpRect;
        int iUpdateSpecWithExtra = updateSpecWithExtra(i, i3 + rect.left, ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin + rect.right);
        int i4 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
        Rect rect2 = this.mTmpRect;
        int iUpdateSpecWithExtra2 = updateSpecWithExtra(i2, i4 + rect2.top, ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin + rect2.bottom);
        if (z) {
            zShouldMeasureChild = shouldReMeasureChild(view, iUpdateSpecWithExtra, iUpdateSpecWithExtra2, layoutParams);
        } else {
            zShouldMeasureChild = shouldMeasureChild(view, iUpdateSpecWithExtra, iUpdateSpecWithExtra2, layoutParams);
        }
        if (zShouldMeasureChild) {
            view.measure(iUpdateSpecWithExtra, iUpdateSpecWithExtra2);
        }
    }
}
