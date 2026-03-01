package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import com.taobao.weex.el.parse.Operators;
import java.util.List;
import supwisdom.gf;
import supwisdom.kf;
import supwisdom.mf;
import supwisdom.pf;

/* JADX INFO: loaded from: classes.dex */
public class LinearLayoutManager extends RecyclerView.o implements gf.i, RecyclerView.x.b {
    public static final boolean DEBUG = false;
    public static final int HORIZONTAL = 0;
    public static final int INVALID_OFFSET = Integer.MIN_VALUE;
    public static final float MAX_SCROLL_FACTOR = 0.33333334f;
    public static final String TAG = "LinearLayoutManager";
    public static final int VERTICAL = 1;
    public final a mAnchorInfo;
    public int mInitialPrefetchItemCount;
    public boolean mLastStackFromEnd;
    public final b mLayoutChunkResult;
    public c mLayoutState;
    public int mOrientation;
    public mf mOrientationHelper;
    public SavedState mPendingSavedState;
    public int mPendingScrollPosition;
    public int mPendingScrollPositionOffset;
    public boolean mRecycleChildrenOnDetach;
    public boolean mReverseLayout;
    public boolean mShouldReverseLayout;
    public boolean mSmoothScrollbarEnabled;
    public boolean mStackFromEnd;

    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1351a;
        public int b;
        public boolean c;

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

        public boolean a() {
            return this.f1351a >= 0;
        }

        public void b() {
            this.f1351a = -1;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f1351a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c ? 1 : 0);
        }

        public SavedState(Parcel parcel) {
            this.f1351a = parcel.readInt();
            this.b = parcel.readInt();
            this.c = parcel.readInt() == 1;
        }

        public SavedState(SavedState savedState) {
            this.f1351a = savedState.f1351a;
            this.b = savedState.b;
            this.c = savedState.c;
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1354a;
        public boolean b;
        public boolean c;
        public boolean d;

        public void a() {
            this.f1354a = 0;
            this.b = false;
            this.c = false;
            this.d = false;
        }
    }

    public static class c {
        public int b;
        public int c;
        public int d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1356e;
        public int f;
        public int g;
        public boolean i;
        public int j;
        public boolean l;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1355a = true;
        public int h = 0;
        public List<RecyclerView.b0> k = null;

        public boolean a(RecyclerView.y yVar) {
            int i = this.d;
            return i >= 0 && i < yVar.a();
        }

        public final View b() {
            int size = this.k.size();
            for (int i = 0; i < size; i++) {
                View view = this.k.get(i).itemView;
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                if (!layoutParams.c() && this.d == layoutParams.a()) {
                    a(view);
                    return view;
                }
            }
            return null;
        }

        public View a(RecyclerView.u uVar) {
            if (this.k != null) {
                return b();
            }
            View viewD = uVar.d(this.d);
            this.d += this.f1356e;
            return viewD;
        }

        public void a() {
            a((View) null);
        }

        public void a(View view) {
            View viewB = b(view);
            if (viewB == null) {
                this.d = -1;
            } else {
                this.d = ((RecyclerView.LayoutParams) viewB.getLayoutParams()).a();
            }
        }

        public View b(View view) {
            int iA;
            int size = this.k.size();
            View view2 = null;
            int i = Integer.MAX_VALUE;
            for (int i2 = 0; i2 < size; i2++) {
                View view3 = this.k.get(i2).itemView;
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view3.getLayoutParams();
                if (view3 != view && !layoutParams.c() && (iA = (layoutParams.a() - this.d) * this.f1356e) >= 0 && iA < i) {
                    view2 = view3;
                    if (iA == 0) {
                        break;
                    }
                    i = iA;
                }
            }
            return view2;
        }
    }

    public LinearLayoutManager(Context context) {
        this(context, 1, false);
    }

    private int computeScrollExtent(RecyclerView.y yVar) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        return pf.a(yVar, this.mOrientationHelper, findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled);
    }

    private int computeScrollOffset(RecyclerView.y yVar) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        return pf.a(yVar, this.mOrientationHelper, findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }

    private int computeScrollRange(RecyclerView.y yVar) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        return pf.b(yVar, this.mOrientationHelper, findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled);
    }

    private View findFirstPartiallyOrCompletelyInvisibleChild(RecyclerView.u uVar, RecyclerView.y yVar) {
        return findOnePartiallyOrCompletelyInvisibleChild(0, getChildCount());
    }

    private View findFirstReferenceChild(RecyclerView.u uVar, RecyclerView.y yVar) {
        return findReferenceChild(uVar, yVar, 0, getChildCount(), yVar.a());
    }

    private View findFirstVisibleChildClosestToEnd(boolean z, boolean z2) {
        return this.mShouldReverseLayout ? findOneVisibleChild(0, getChildCount(), z, z2) : findOneVisibleChild(getChildCount() - 1, -1, z, z2);
    }

    private View findFirstVisibleChildClosestToStart(boolean z, boolean z2) {
        return this.mShouldReverseLayout ? findOneVisibleChild(getChildCount() - 1, -1, z, z2) : findOneVisibleChild(0, getChildCount(), z, z2);
    }

    private View findLastPartiallyOrCompletelyInvisibleChild(RecyclerView.u uVar, RecyclerView.y yVar) {
        return findOnePartiallyOrCompletelyInvisibleChild(getChildCount() - 1, -1);
    }

    private View findLastReferenceChild(RecyclerView.u uVar, RecyclerView.y yVar) {
        return findReferenceChild(uVar, yVar, getChildCount() - 1, -1, yVar.a());
    }

    private View findPartiallyOrCompletelyInvisibleChildClosestToEnd(RecyclerView.u uVar, RecyclerView.y yVar) {
        return this.mShouldReverseLayout ? findFirstPartiallyOrCompletelyInvisibleChild(uVar, yVar) : findLastPartiallyOrCompletelyInvisibleChild(uVar, yVar);
    }

    private View findPartiallyOrCompletelyInvisibleChildClosestToStart(RecyclerView.u uVar, RecyclerView.y yVar) {
        return this.mShouldReverseLayout ? findLastPartiallyOrCompletelyInvisibleChild(uVar, yVar) : findFirstPartiallyOrCompletelyInvisibleChild(uVar, yVar);
    }

    private View findReferenceChildClosestToEnd(RecyclerView.u uVar, RecyclerView.y yVar) {
        return this.mShouldReverseLayout ? findFirstReferenceChild(uVar, yVar) : findLastReferenceChild(uVar, yVar);
    }

    private View findReferenceChildClosestToStart(RecyclerView.u uVar, RecyclerView.y yVar) {
        return this.mShouldReverseLayout ? findLastReferenceChild(uVar, yVar) : findFirstReferenceChild(uVar, yVar);
    }

    private int fixLayoutEndGap(int i, RecyclerView.u uVar, RecyclerView.y yVar, boolean z) {
        int iB;
        int iB2 = this.mOrientationHelper.b() - i;
        if (iB2 <= 0) {
            return 0;
        }
        int i2 = -scrollBy(-iB2, uVar, yVar);
        int i3 = i + i2;
        if (!z || (iB = this.mOrientationHelper.b() - i3) <= 0) {
            return i2;
        }
        this.mOrientationHelper.a(iB);
        return iB + i2;
    }

    private int fixLayoutStartGap(int i, RecyclerView.u uVar, RecyclerView.y yVar, boolean z) {
        int iF;
        int iF2 = i - this.mOrientationHelper.f();
        if (iF2 <= 0) {
            return 0;
        }
        int i2 = -scrollBy(iF2, uVar, yVar);
        int i3 = i + i2;
        if (!z || (iF = i3 - this.mOrientationHelper.f()) <= 0) {
            return i2;
        }
        this.mOrientationHelper.a(-iF);
        return i2 - iF;
    }

    private View getChildClosestToEnd() {
        return getChildAt(this.mShouldReverseLayout ? 0 : getChildCount() - 1);
    }

    private View getChildClosestToStart() {
        return getChildAt(this.mShouldReverseLayout ? getChildCount() - 1 : 0);
    }

    private void layoutForPredictiveAnimations(RecyclerView.u uVar, RecyclerView.y yVar, int i, int i2) {
        if (!yVar.e() || getChildCount() == 0 || yVar.d() || !supportsPredictiveItemAnimations()) {
            return;
        }
        List<RecyclerView.b0> listF = uVar.f();
        int size = listF.size();
        int position = getPosition(getChildAt(0));
        int iB = 0;
        int iB2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            RecyclerView.b0 b0Var = listF.get(i3);
            if (!b0Var.isRemoved()) {
                if (((b0Var.getLayoutPosition() < position) != this.mShouldReverseLayout ? (byte) -1 : (byte) 1) == -1) {
                    iB += this.mOrientationHelper.b(b0Var.itemView);
                } else {
                    iB2 += this.mOrientationHelper.b(b0Var.itemView);
                }
            }
        }
        this.mLayoutState.k = listF;
        if (iB > 0) {
            updateLayoutStateToFillStart(getPosition(getChildClosestToStart()), i);
            c cVar = this.mLayoutState;
            cVar.h = iB;
            cVar.c = 0;
            cVar.a();
            fill(uVar, this.mLayoutState, yVar, false);
        }
        if (iB2 > 0) {
            updateLayoutStateToFillEnd(getPosition(getChildClosestToEnd()), i2);
            c cVar2 = this.mLayoutState;
            cVar2.h = iB2;
            cVar2.c = 0;
            cVar2.a();
            fill(uVar, this.mLayoutState, yVar, false);
        }
        this.mLayoutState.k = null;
    }

    private void logChildren() {
        Log.d(TAG, "internal representation of views on the screen");
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            Log.d(TAG, "item " + getPosition(childAt) + ", coord:" + this.mOrientationHelper.d(childAt));
        }
        Log.d(TAG, "==============");
    }

    private void recycleByLayoutState(RecyclerView.u uVar, c cVar) {
        if (!cVar.f1355a || cVar.l) {
            return;
        }
        if (cVar.f == -1) {
            recycleViewsFromEnd(uVar, cVar.g);
        } else {
            recycleViewsFromStart(uVar, cVar.g);
        }
    }

    private void recycleChildren(RecyclerView.u uVar, int i, int i2) {
        if (i == i2) {
            return;
        }
        if (i2 <= i) {
            while (i > i2) {
                removeAndRecycleViewAt(i, uVar);
                i--;
            }
        } else {
            for (int i3 = i2 - 1; i3 >= i; i3--) {
                removeAndRecycleViewAt(i3, uVar);
            }
        }
    }

    private void recycleViewsFromEnd(RecyclerView.u uVar, int i) {
        int childCount = getChildCount();
        if (i < 0) {
            return;
        }
        int iA = this.mOrientationHelper.a() - i;
        if (this.mShouldReverseLayout) {
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                if (this.mOrientationHelper.d(childAt) < iA || this.mOrientationHelper.f(childAt) < iA) {
                    recycleChildren(uVar, 0, i2);
                    return;
                }
            }
            return;
        }
        int i3 = childCount - 1;
        for (int i4 = i3; i4 >= 0; i4--) {
            View childAt2 = getChildAt(i4);
            if (this.mOrientationHelper.d(childAt2) < iA || this.mOrientationHelper.f(childAt2) < iA) {
                recycleChildren(uVar, i3, i4);
                return;
            }
        }
    }

    private void recycleViewsFromStart(RecyclerView.u uVar, int i) {
        if (i < 0) {
            return;
        }
        int childCount = getChildCount();
        if (!this.mShouldReverseLayout) {
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                if (this.mOrientationHelper.a(childAt) > i || this.mOrientationHelper.e(childAt) > i) {
                    recycleChildren(uVar, 0, i2);
                    return;
                }
            }
            return;
        }
        int i3 = childCount - 1;
        for (int i4 = i3; i4 >= 0; i4--) {
            View childAt2 = getChildAt(i4);
            if (this.mOrientationHelper.a(childAt2) > i || this.mOrientationHelper.e(childAt2) > i) {
                recycleChildren(uVar, i3, i4);
                return;
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

    private boolean updateAnchorFromChildren(RecyclerView.u uVar, RecyclerView.y yVar, a aVar) {
        if (getChildCount() == 0) {
            return false;
        }
        View focusedChild = getFocusedChild();
        if (focusedChild != null && aVar.a(focusedChild, yVar)) {
            aVar.b(focusedChild, getPosition(focusedChild));
            return true;
        }
        if (this.mLastStackFromEnd != this.mStackFromEnd) {
            return false;
        }
        View viewFindReferenceChildClosestToEnd = aVar.d ? findReferenceChildClosestToEnd(uVar, yVar) : findReferenceChildClosestToStart(uVar, yVar);
        if (viewFindReferenceChildClosestToEnd == null) {
            return false;
        }
        aVar.a(viewFindReferenceChildClosestToEnd, getPosition(viewFindReferenceChildClosestToEnd));
        if (!yVar.d() && supportsPredictiveItemAnimations()) {
            if (this.mOrientationHelper.d(viewFindReferenceChildClosestToEnd) >= this.mOrientationHelper.b() || this.mOrientationHelper.a(viewFindReferenceChildClosestToEnd) < this.mOrientationHelper.f()) {
                aVar.c = aVar.d ? this.mOrientationHelper.b() : this.mOrientationHelper.f();
            }
        }
        return true;
    }

    private boolean updateAnchorFromPendingData(RecyclerView.y yVar, a aVar) {
        int i;
        if (!yVar.d() && (i = this.mPendingScrollPosition) != -1) {
            if (i >= 0 && i < yVar.a()) {
                aVar.b = this.mPendingScrollPosition;
                SavedState savedState = this.mPendingSavedState;
                if (savedState != null && savedState.a()) {
                    boolean z = this.mPendingSavedState.c;
                    aVar.d = z;
                    if (z) {
                        aVar.c = this.mOrientationHelper.b() - this.mPendingSavedState.b;
                    } else {
                        aVar.c = this.mOrientationHelper.f() + this.mPendingSavedState.b;
                    }
                    return true;
                }
                if (this.mPendingScrollPositionOffset != Integer.MIN_VALUE) {
                    boolean z2 = this.mShouldReverseLayout;
                    aVar.d = z2;
                    if (z2) {
                        aVar.c = this.mOrientationHelper.b() - this.mPendingScrollPositionOffset;
                    } else {
                        aVar.c = this.mOrientationHelper.f() + this.mPendingScrollPositionOffset;
                    }
                    return true;
                }
                View viewFindViewByPosition = findViewByPosition(this.mPendingScrollPosition);
                if (viewFindViewByPosition == null) {
                    if (getChildCount() > 0) {
                        aVar.d = (this.mPendingScrollPosition < getPosition(getChildAt(0))) == this.mShouldReverseLayout;
                    }
                    aVar.a();
                } else {
                    if (this.mOrientationHelper.b(viewFindViewByPosition) > this.mOrientationHelper.g()) {
                        aVar.a();
                        return true;
                    }
                    if (this.mOrientationHelper.d(viewFindViewByPosition) - this.mOrientationHelper.f() < 0) {
                        aVar.c = this.mOrientationHelper.f();
                        aVar.d = false;
                        return true;
                    }
                    if (this.mOrientationHelper.b() - this.mOrientationHelper.a(viewFindViewByPosition) < 0) {
                        aVar.c = this.mOrientationHelper.b();
                        aVar.d = true;
                        return true;
                    }
                    aVar.c = aVar.d ? this.mOrientationHelper.a(viewFindViewByPosition) + this.mOrientationHelper.h() : this.mOrientationHelper.d(viewFindViewByPosition);
                }
                return true;
            }
            this.mPendingScrollPosition = -1;
            this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        }
        return false;
    }

    private void updateAnchorInfoForLayout(RecyclerView.u uVar, RecyclerView.y yVar, a aVar) {
        if (updateAnchorFromPendingData(yVar, aVar) || updateAnchorFromChildren(uVar, yVar, aVar)) {
            return;
        }
        aVar.a();
        aVar.b = this.mStackFromEnd ? yVar.a() - 1 : 0;
    }

    private void updateLayoutState(int i, int i2, boolean z, RecyclerView.y yVar) {
        int iF;
        this.mLayoutState.l = resolveIsInfinite();
        this.mLayoutState.h = getExtraLayoutSpace(yVar);
        c cVar = this.mLayoutState;
        cVar.f = i;
        if (i == 1) {
            cVar.h += this.mOrientationHelper.c();
            View childClosestToEnd = getChildClosestToEnd();
            this.mLayoutState.f1356e = this.mShouldReverseLayout ? -1 : 1;
            c cVar2 = this.mLayoutState;
            int position = getPosition(childClosestToEnd);
            c cVar3 = this.mLayoutState;
            cVar2.d = position + cVar3.f1356e;
            cVar3.b = this.mOrientationHelper.a(childClosestToEnd);
            iF = this.mOrientationHelper.a(childClosestToEnd) - this.mOrientationHelper.b();
        } else {
            View childClosestToStart = getChildClosestToStart();
            this.mLayoutState.h += this.mOrientationHelper.f();
            this.mLayoutState.f1356e = this.mShouldReverseLayout ? 1 : -1;
            c cVar4 = this.mLayoutState;
            int position2 = getPosition(childClosestToStart);
            c cVar5 = this.mLayoutState;
            cVar4.d = position2 + cVar5.f1356e;
            cVar5.b = this.mOrientationHelper.d(childClosestToStart);
            iF = (-this.mOrientationHelper.d(childClosestToStart)) + this.mOrientationHelper.f();
        }
        c cVar6 = this.mLayoutState;
        cVar6.c = i2;
        if (z) {
            cVar6.c = i2 - iF;
        }
        this.mLayoutState.g = iF;
    }

    private void updateLayoutStateToFillEnd(a aVar) {
        updateLayoutStateToFillEnd(aVar.b, aVar.c);
    }

    private void updateLayoutStateToFillStart(a aVar) {
        updateLayoutStateToFillStart(aVar.b, aVar.c);
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

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void collectAdjacentPrefetchPositions(int i, int i2, RecyclerView.y yVar, RecyclerView.o.c cVar) {
        if (this.mOrientation != 0) {
            i = i2;
        }
        if (getChildCount() == 0 || i == 0) {
            return;
        }
        ensureLayoutState();
        updateLayoutState(i > 0 ? 1 : -1, Math.abs(i), true, yVar);
        collectPrefetchPositionsForLayoutState(yVar, this.mLayoutState, cVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void collectInitialPrefetchPositions(int i, RecyclerView.o.c cVar) {
        boolean z;
        int i2;
        SavedState savedState = this.mPendingSavedState;
        if (savedState == null || !savedState.a()) {
            resolveShouldLayoutReverse();
            z = this.mShouldReverseLayout;
            i2 = this.mPendingScrollPosition;
            if (i2 == -1) {
                i2 = z ? i - 1 : 0;
            }
        } else {
            SavedState savedState2 = this.mPendingSavedState;
            z = savedState2.c;
            i2 = savedState2.f1351a;
        }
        int i3 = z ? -1 : 1;
        for (int i4 = 0; i4 < this.mInitialPrefetchItemCount && i2 >= 0 && i2 < i; i4++) {
            cVar.a(i2, 0);
            i2 += i3;
        }
    }

    public void collectPrefetchPositionsForLayoutState(RecyclerView.y yVar, c cVar, RecyclerView.o.c cVar2) {
        int i = cVar.d;
        if (i < 0 || i >= yVar.a()) {
            return;
        }
        cVar2.a(i, Math.max(0, cVar.g));
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
        if (getChildCount() == 0) {
            return null;
        }
        int i2 = (i < getPosition(getChildAt(0))) != this.mShouldReverseLayout ? -1 : 1;
        return this.mOrientation == 0 ? new PointF(i2, 0.0f) : new PointF(0.0f, i2);
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

    public int convertFocusDirectionToLayoutDirection(int i) {
        return i != 1 ? i != 2 ? i != 17 ? i != 33 ? i != 66 ? (i == 130 && this.mOrientation == 1) ? 1 : Integer.MIN_VALUE : this.mOrientation == 0 ? 1 : Integer.MIN_VALUE : this.mOrientation == 1 ? -1 : Integer.MIN_VALUE : this.mOrientation == 0 ? -1 : Integer.MIN_VALUE : (this.mOrientation != 1 && isLayoutRTL()) ? -1 : 1 : (this.mOrientation != 1 && isLayoutRTL()) ? 1 : -1;
    }

    public c createLayoutState() {
        return new c();
    }

    public void ensureLayoutState() {
        if (this.mLayoutState == null) {
            this.mLayoutState = createLayoutState();
        }
    }

    public int fill(RecyclerView.u uVar, c cVar, RecyclerView.y yVar, boolean z) {
        int i = cVar.c;
        int i2 = cVar.g;
        if (i2 != Integer.MIN_VALUE) {
            if (i < 0) {
                cVar.g = i2 + i;
            }
            recycleByLayoutState(uVar, cVar);
        }
        int i3 = cVar.c + cVar.h;
        b bVar = this.mLayoutChunkResult;
        while (true) {
            if ((!cVar.l && i3 <= 0) || !cVar.a(yVar)) {
                break;
            }
            bVar.a();
            layoutChunk(uVar, yVar, cVar, bVar);
            if (!bVar.b) {
                cVar.b += bVar.f1354a * cVar.f;
                if (!bVar.c || this.mLayoutState.k != null || !yVar.d()) {
                    int i4 = cVar.c;
                    int i5 = bVar.f1354a;
                    cVar.c = i4 - i5;
                    i3 -= i5;
                }
                int i6 = cVar.g;
                if (i6 != Integer.MIN_VALUE) {
                    int i7 = i6 + bVar.f1354a;
                    cVar.g = i7;
                    int i8 = cVar.c;
                    if (i8 < 0) {
                        cVar.g = i7 + i8;
                    }
                    recycleByLayoutState(uVar, cVar);
                }
                if (z && bVar.d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i - cVar.c;
    }

    public int findFirstCompletelyVisibleItemPosition() {
        View viewFindOneVisibleChild = findOneVisibleChild(0, getChildCount(), true, false);
        if (viewFindOneVisibleChild == null) {
            return -1;
        }
        return getPosition(viewFindOneVisibleChild);
    }

    public int findFirstVisibleItemPosition() {
        View viewFindOneVisibleChild = findOneVisibleChild(0, getChildCount(), false, true);
        if (viewFindOneVisibleChild == null) {
            return -1;
        }
        return getPosition(viewFindOneVisibleChild);
    }

    public int findLastCompletelyVisibleItemPosition() {
        View viewFindOneVisibleChild = findOneVisibleChild(getChildCount() - 1, -1, true, false);
        if (viewFindOneVisibleChild == null) {
            return -1;
        }
        return getPosition(viewFindOneVisibleChild);
    }

    public int findLastVisibleItemPosition() {
        View viewFindOneVisibleChild = findOneVisibleChild(getChildCount() - 1, -1, false, true);
        if (viewFindOneVisibleChild == null) {
            return -1;
        }
        return getPosition(viewFindOneVisibleChild);
    }

    public View findOnePartiallyOrCompletelyInvisibleChild(int i, int i2) {
        int i3;
        int i4;
        ensureLayoutState();
        if ((i2 > i ? (byte) 1 : i2 < i ? (byte) -1 : (byte) 0) == 0) {
            return getChildAt(i);
        }
        if (this.mOrientationHelper.d(getChildAt(i)) < this.mOrientationHelper.f()) {
            i3 = 16644;
            i4 = 16388;
        } else {
            i3 = 4161;
            i4 = 4097;
        }
        return this.mOrientation == 0 ? this.mHorizontalBoundCheck.a(i, i2, i3, i4) : this.mVerticalBoundCheck.a(i, i2, i3, i4);
    }

    public View findOneVisibleChild(int i, int i2, boolean z, boolean z2) {
        ensureLayoutState();
        int i3 = MediaSessionCompat.MAX_BITMAP_SIZE_IN_DP;
        int i4 = z ? 24579 : MediaSessionCompat.MAX_BITMAP_SIZE_IN_DP;
        if (!z2) {
            i3 = 0;
        }
        return this.mOrientation == 0 ? this.mHorizontalBoundCheck.a(i, i2, i4, i3) : this.mVerticalBoundCheck.a(i, i2, i4, i3);
    }

    public View findReferenceChild(RecyclerView.u uVar, RecyclerView.y yVar, int i, int i2, int i3) {
        ensureLayoutState();
        int iF = this.mOrientationHelper.f();
        int iB = this.mOrientationHelper.b();
        int i4 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View childAt = getChildAt(i);
            int position = getPosition(childAt);
            if (position >= 0 && position < i3) {
                if (((RecyclerView.LayoutParams) childAt.getLayoutParams()).c()) {
                    if (view2 == null) {
                        view2 = childAt;
                    }
                } else {
                    if (this.mOrientationHelper.d(childAt) < iB && this.mOrientationHelper.a(childAt) >= iF) {
                        return childAt;
                    }
                    if (view == null) {
                        view = childAt;
                    }
                }
            }
            i += i4;
        }
        return view != null ? view : view2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View findViewByPosition(int i) {
        int childCount = getChildCount();
        if (childCount == 0) {
            return null;
        }
        int position = i - getPosition(getChildAt(0));
        if (position >= 0 && position < childCount) {
            View childAt = getChildAt(position);
            if (getPosition(childAt) == i) {
                return childAt;
            }
        }
        return super.findViewByPosition(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    public int getExtraLayoutSpace(RecyclerView.y yVar) {
        if (yVar.c()) {
            return this.mOrientationHelper.g();
        }
        return 0;
    }

    public int getInitialPrefetchItemCount() {
        return this.mInitialPrefetchItemCount;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public boolean getRecycleChildrenOnDetach() {
        return this.mRecycleChildrenOnDetach;
    }

    public boolean getReverseLayout() {
        return this.mReverseLayout;
    }

    public boolean getStackFromEnd() {
        return this.mStackFromEnd;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean isAutoMeasureEnabled() {
        return true;
    }

    public boolean isLayoutRTL() {
        return getLayoutDirection() == 1;
    }

    public boolean isSmoothScrollbarEnabled() {
        return this.mSmoothScrollbarEnabled;
    }

    public void layoutChunk(RecyclerView.u uVar, RecyclerView.y yVar, c cVar, b bVar) {
        int i;
        int i2;
        int i3;
        int paddingLeft;
        int iC;
        View viewA = cVar.a(uVar);
        if (viewA == null) {
            bVar.b = true;
            return;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) viewA.getLayoutParams();
        if (cVar.k == null) {
            if (this.mShouldReverseLayout == (cVar.f == -1)) {
                addView(viewA);
            } else {
                addView(viewA, 0);
            }
        } else {
            if (this.mShouldReverseLayout == (cVar.f == -1)) {
                addDisappearingView(viewA);
            } else {
                addDisappearingView(viewA, 0);
            }
        }
        measureChildWithMargins(viewA, 0, 0);
        bVar.f1354a = this.mOrientationHelper.b(viewA);
        if (this.mOrientation == 1) {
            if (isLayoutRTL()) {
                iC = getWidth() - getPaddingRight();
                paddingLeft = iC - this.mOrientationHelper.c(viewA);
            } else {
                paddingLeft = getPaddingLeft();
                iC = this.mOrientationHelper.c(viewA) + paddingLeft;
            }
            if (cVar.f == -1) {
                int i4 = cVar.b;
                i3 = i4;
                i2 = iC;
                i = i4 - bVar.f1354a;
            } else {
                int i5 = cVar.b;
                i = i5;
                i2 = iC;
                i3 = bVar.f1354a + i5;
            }
        } else {
            int paddingTop = getPaddingTop();
            int iC2 = this.mOrientationHelper.c(viewA) + paddingTop;
            if (cVar.f == -1) {
                int i6 = cVar.b;
                i2 = i6;
                i = paddingTop;
                i3 = iC2;
                paddingLeft = i6 - bVar.f1354a;
            } else {
                int i7 = cVar.b;
                i = paddingTop;
                i2 = bVar.f1354a + i7;
                i3 = iC2;
                paddingLeft = i7;
            }
        }
        layoutDecoratedWithMargins(viewA, paddingLeft, i, i2, i3);
        if (layoutParams.c() || layoutParams.b()) {
            bVar.c = true;
        }
        bVar.d = viewA.hasFocusable();
    }

    public void onAnchorReady(RecyclerView.u uVar, RecyclerView.y yVar, a aVar, int i) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.u uVar) {
        super.onDetachedFromWindow(recyclerView, uVar);
        if (this.mRecycleChildrenOnDetach) {
            removeAndRecycleAllViews(uVar);
            uVar.a();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public View onFocusSearchFailed(View view, int i, RecyclerView.u uVar, RecyclerView.y yVar) {
        int iConvertFocusDirectionToLayoutDirection;
        resolveShouldLayoutReverse();
        if (getChildCount() == 0 || (iConvertFocusDirectionToLayoutDirection = convertFocusDirectionToLayoutDirection(i)) == Integer.MIN_VALUE) {
            return null;
        }
        ensureLayoutState();
        ensureLayoutState();
        updateLayoutState(iConvertFocusDirectionToLayoutDirection, (int) (this.mOrientationHelper.g() * 0.33333334f), false, yVar);
        c cVar = this.mLayoutState;
        cVar.g = Integer.MIN_VALUE;
        cVar.f1355a = false;
        fill(uVar, cVar, yVar, true);
        View viewFindPartiallyOrCompletelyInvisibleChildClosestToStart = iConvertFocusDirectionToLayoutDirection == -1 ? findPartiallyOrCompletelyInvisibleChildClosestToStart(uVar, yVar) : findPartiallyOrCompletelyInvisibleChildClosestToEnd(uVar, yVar);
        View childClosestToStart = iConvertFocusDirectionToLayoutDirection == -1 ? getChildClosestToStart() : getChildClosestToEnd();
        if (!childClosestToStart.hasFocusable()) {
            return viewFindPartiallyOrCompletelyInvisibleChildClosestToStart;
        }
        if (viewFindPartiallyOrCompletelyInvisibleChildClosestToStart == null) {
            return null;
        }
        return childClosestToStart;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            accessibilityEvent.setFromIndex(findFirstVisibleItemPosition());
            accessibilityEvent.setToIndex(findLastVisibleItemPosition());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onLayoutChildren(RecyclerView.u uVar, RecyclerView.y yVar) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int iFixLayoutEndGap;
        int i6;
        View viewFindViewByPosition;
        int iD;
        int iB;
        int i7 = -1;
        if (!(this.mPendingSavedState == null && this.mPendingScrollPosition == -1) && yVar.a() == 0) {
            removeAndRecycleAllViews(uVar);
            return;
        }
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null && savedState.a()) {
            this.mPendingScrollPosition = this.mPendingSavedState.f1351a;
        }
        ensureLayoutState();
        this.mLayoutState.f1355a = false;
        resolveShouldLayoutReverse();
        View focusedChild = getFocusedChild();
        if (!this.mAnchorInfo.f1353e || this.mPendingScrollPosition != -1 || this.mPendingSavedState != null) {
            this.mAnchorInfo.b();
            a aVar = this.mAnchorInfo;
            aVar.d = this.mShouldReverseLayout ^ this.mStackFromEnd;
            updateAnchorInfoForLayout(uVar, yVar, aVar);
            this.mAnchorInfo.f1353e = true;
        } else if (focusedChild != null && (this.mOrientationHelper.d(focusedChild) >= this.mOrientationHelper.b() || this.mOrientationHelper.a(focusedChild) <= this.mOrientationHelper.f())) {
            this.mAnchorInfo.b(focusedChild, getPosition(focusedChild));
        }
        int extraLayoutSpace = getExtraLayoutSpace(yVar);
        if (this.mLayoutState.j >= 0) {
            i = extraLayoutSpace;
            extraLayoutSpace = 0;
        } else {
            i = 0;
        }
        int iF = extraLayoutSpace + this.mOrientationHelper.f();
        int iC = i + this.mOrientationHelper.c();
        if (yVar.d() && (i6 = this.mPendingScrollPosition) != -1 && this.mPendingScrollPositionOffset != Integer.MIN_VALUE && (viewFindViewByPosition = findViewByPosition(i6)) != null) {
            if (this.mShouldReverseLayout) {
                iB = this.mOrientationHelper.b() - this.mOrientationHelper.a(viewFindViewByPosition);
                iD = this.mPendingScrollPositionOffset;
            } else {
                iD = this.mOrientationHelper.d(viewFindViewByPosition) - this.mOrientationHelper.f();
                iB = this.mPendingScrollPositionOffset;
            }
            int i8 = iB - iD;
            if (i8 > 0) {
                iF += i8;
            } else {
                iC -= i8;
            }
        }
        if (!this.mAnchorInfo.d ? !this.mShouldReverseLayout : this.mShouldReverseLayout) {
            i7 = 1;
        }
        onAnchorReady(uVar, yVar, this.mAnchorInfo, i7);
        detachAndScrapAttachedViews(uVar);
        this.mLayoutState.l = resolveIsInfinite();
        this.mLayoutState.i = yVar.d();
        a aVar2 = this.mAnchorInfo;
        if (aVar2.d) {
            updateLayoutStateToFillStart(aVar2);
            c cVar = this.mLayoutState;
            cVar.h = iF;
            fill(uVar, cVar, yVar, false);
            c cVar2 = this.mLayoutState;
            i3 = cVar2.b;
            int i9 = cVar2.d;
            int i10 = cVar2.c;
            if (i10 > 0) {
                iC += i10;
            }
            updateLayoutStateToFillEnd(this.mAnchorInfo);
            c cVar3 = this.mLayoutState;
            cVar3.h = iC;
            cVar3.d += cVar3.f1356e;
            fill(uVar, cVar3, yVar, false);
            c cVar4 = this.mLayoutState;
            i2 = cVar4.b;
            int i11 = cVar4.c;
            if (i11 > 0) {
                updateLayoutStateToFillStart(i9, i3);
                c cVar5 = this.mLayoutState;
                cVar5.h = i11;
                fill(uVar, cVar5, yVar, false);
                i3 = this.mLayoutState.b;
            }
        } else {
            updateLayoutStateToFillEnd(aVar2);
            c cVar6 = this.mLayoutState;
            cVar6.h = iC;
            fill(uVar, cVar6, yVar, false);
            c cVar7 = this.mLayoutState;
            i2 = cVar7.b;
            int i12 = cVar7.d;
            int i13 = cVar7.c;
            if (i13 > 0) {
                iF += i13;
            }
            updateLayoutStateToFillStart(this.mAnchorInfo);
            c cVar8 = this.mLayoutState;
            cVar8.h = iF;
            cVar8.d += cVar8.f1356e;
            fill(uVar, cVar8, yVar, false);
            c cVar9 = this.mLayoutState;
            i3 = cVar9.b;
            int i14 = cVar9.c;
            if (i14 > 0) {
                updateLayoutStateToFillEnd(i12, i2);
                c cVar10 = this.mLayoutState;
                cVar10.h = i14;
                fill(uVar, cVar10, yVar, false);
                i2 = this.mLayoutState.b;
            }
        }
        if (getChildCount() > 0) {
            if (this.mShouldReverseLayout ^ this.mStackFromEnd) {
                int iFixLayoutEndGap2 = fixLayoutEndGap(i2, uVar, yVar, true);
                i4 = i3 + iFixLayoutEndGap2;
                i5 = i2 + iFixLayoutEndGap2;
                iFixLayoutEndGap = fixLayoutStartGap(i4, uVar, yVar, false);
            } else {
                int iFixLayoutStartGap = fixLayoutStartGap(i3, uVar, yVar, true);
                i4 = i3 + iFixLayoutStartGap;
                i5 = i2 + iFixLayoutStartGap;
                iFixLayoutEndGap = fixLayoutEndGap(i5, uVar, yVar, false);
            }
            i3 = i4 + iFixLayoutEndGap;
            i2 = i5 + iFixLayoutEndGap;
        }
        layoutForPredictiveAnimations(uVar, yVar, i3, i2);
        if (yVar.d()) {
            this.mAnchorInfo.b();
        } else {
            this.mOrientationHelper.i();
        }
        this.mLastStackFromEnd = this.mStackFromEnd;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void onLayoutCompleted(RecyclerView.y yVar) {
        super.onLayoutCompleted(yVar);
        this.mPendingSavedState = null;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
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
        if (this.mPendingSavedState != null) {
            return new SavedState(this.mPendingSavedState);
        }
        SavedState savedState = new SavedState();
        if (getChildCount() > 0) {
            ensureLayoutState();
            boolean z = this.mLastStackFromEnd ^ this.mShouldReverseLayout;
            savedState.c = z;
            if (z) {
                View childClosestToEnd = getChildClosestToEnd();
                savedState.b = this.mOrientationHelper.b() - this.mOrientationHelper.a(childClosestToEnd);
                savedState.f1351a = getPosition(childClosestToEnd);
            } else {
                View childClosestToStart = getChildClosestToStart();
                savedState.f1351a = getPosition(childClosestToStart);
                savedState.b = this.mOrientationHelper.d(childClosestToStart) - this.mOrientationHelper.f();
            }
        } else {
            savedState.b();
        }
        return savedState;
    }

    @Override // supwisdom.gf.i
    public void prepareForDrop(View view, View view2, int i, int i2) {
        assertNotInLayoutOrScroll("Cannot drop a view during a scroll or layout calculation");
        ensureLayoutState();
        resolveShouldLayoutReverse();
        int position = getPosition(view);
        int position2 = getPosition(view2);
        byte b2 = position < position2 ? (byte) 1 : (byte) -1;
        if (this.mShouldReverseLayout) {
            if (b2 == 1) {
                scrollToPositionWithOffset(position2, this.mOrientationHelper.b() - (this.mOrientationHelper.d(view2) + this.mOrientationHelper.b(view)));
                return;
            } else {
                scrollToPositionWithOffset(position2, this.mOrientationHelper.b() - this.mOrientationHelper.a(view2));
                return;
            }
        }
        if (b2 == -1) {
            scrollToPositionWithOffset(position2, this.mOrientationHelper.d(view2));
        } else {
            scrollToPositionWithOffset(position2, this.mOrientationHelper.a(view2) - this.mOrientationHelper.b(view));
        }
    }

    public boolean resolveIsInfinite() {
        return this.mOrientationHelper.d() == 0 && this.mOrientationHelper.a() == 0;
    }

    public int scrollBy(int i, RecyclerView.u uVar, RecyclerView.y yVar) {
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        this.mLayoutState.f1355a = true;
        ensureLayoutState();
        int i2 = i > 0 ? 1 : -1;
        int iAbs = Math.abs(i);
        updateLayoutState(i2, iAbs, true, yVar);
        c cVar = this.mLayoutState;
        int iFill = cVar.g + fill(uVar, cVar, yVar, false);
        if (iFill < 0) {
            return 0;
        }
        if (iAbs > iFill) {
            i = i2 * iFill;
        }
        this.mOrientationHelper.a(-i);
        this.mLayoutState.j = i;
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int scrollHorizontallyBy(int i, RecyclerView.u uVar, RecyclerView.y yVar) {
        if (this.mOrientation == 1) {
            return 0;
        }
        return scrollBy(i, uVar, yVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void scrollToPosition(int i) {
        this.mPendingScrollPosition = i;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            savedState.b();
        }
        requestLayout();
    }

    public void scrollToPositionWithOffset(int i, int i2) {
        this.mPendingScrollPosition = i;
        this.mPendingScrollPositionOffset = i2;
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            savedState.b();
        }
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public int scrollVerticallyBy(int i, RecyclerView.u uVar, RecyclerView.y yVar) {
        if (this.mOrientation == 0) {
            return 0;
        }
        return scrollBy(i, uVar, yVar);
    }

    public void setInitialPrefetchItemCount(int i) {
        this.mInitialPrefetchItemCount = i;
    }

    public void setOrientation(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("invalid orientation:" + i);
        }
        assertNotInLayoutOrScroll(null);
        if (i != this.mOrientation || this.mOrientationHelper == null) {
            mf mfVarA = mf.a(this, i);
            this.mOrientationHelper = mfVarA;
            this.mAnchorInfo.f1352a = mfVarA;
            this.mOrientation = i;
            requestLayout();
        }
    }

    public void setRecycleChildrenOnDetach(boolean z) {
        this.mRecycleChildrenOnDetach = z;
    }

    public void setReverseLayout(boolean z) {
        assertNotInLayoutOrScroll(null);
        if (z == this.mReverseLayout) {
            return;
        }
        this.mReverseLayout = z;
        requestLayout();
    }

    public void setSmoothScrollbarEnabled(boolean z) {
        this.mSmoothScrollbarEnabled = z;
    }

    public void setStackFromEnd(boolean z) {
        assertNotInLayoutOrScroll(null);
        if (this.mStackFromEnd == z) {
            return;
        }
        this.mStackFromEnd = z;
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean shouldMeasureTwice() {
        return (getHeightMode() == 1073741824 || getWidthMode() == 1073741824 || !hasFlexibleChildInBothOrientations()) ? false : true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.y yVar, int i) {
        kf kfVar = new kf(recyclerView.getContext());
        kfVar.setTargetPosition(i);
        startSmoothScroll(kfVar);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null && this.mLastStackFromEnd == this.mStackFromEnd;
    }

    public void validateChildOrder() {
        Log.d(TAG, "validating child count " + getChildCount());
        if (getChildCount() < 1) {
            return;
        }
        int position = getPosition(getChildAt(0));
        int iD = this.mOrientationHelper.d(getChildAt(0));
        if (this.mShouldReverseLayout) {
            for (int i = 1; i < getChildCount(); i++) {
                View childAt = getChildAt(i);
                int position2 = getPosition(childAt);
                int iD2 = this.mOrientationHelper.d(childAt);
                if (position2 < position) {
                    logChildren();
                    StringBuilder sb = new StringBuilder();
                    sb.append("detected invalid position. loc invalid? ");
                    sb.append(iD2 < iD);
                    throw new RuntimeException(sb.toString());
                }
                if (iD2 > iD) {
                    logChildren();
                    throw new RuntimeException("detected invalid location");
                }
            }
            return;
        }
        for (int i2 = 1; i2 < getChildCount(); i2++) {
            View childAt2 = getChildAt(i2);
            int position3 = getPosition(childAt2);
            int iD3 = this.mOrientationHelper.d(childAt2);
            if (position3 < position) {
                logChildren();
                StringBuilder sb2 = new StringBuilder();
                sb2.append("detected invalid position. loc invalid? ");
                sb2.append(iD3 < iD);
                throw new RuntimeException(sb2.toString());
            }
            if (iD3 < iD) {
                logChildren();
                throw new RuntimeException("detected invalid location");
            }
        }
    }

    public LinearLayoutManager(Context context, int i, boolean z) {
        this.mOrientation = 1;
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mStackFromEnd = false;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo = new a();
        this.mLayoutChunkResult = new b();
        this.mInitialPrefetchItemCount = 2;
        setOrientation(i);
        setReverseLayout(z);
    }

    private void updateLayoutStateToFillEnd(int i, int i2) {
        this.mLayoutState.c = this.mOrientationHelper.b() - i2;
        this.mLayoutState.f1356e = this.mShouldReverseLayout ? -1 : 1;
        c cVar = this.mLayoutState;
        cVar.d = i;
        cVar.f = 1;
        cVar.b = i2;
        cVar.g = Integer.MIN_VALUE;
    }

    private void updateLayoutStateToFillStart(int i, int i2) {
        this.mLayoutState.c = i2 - this.mOrientationHelper.f();
        c cVar = this.mLayoutState;
        cVar.d = i;
        cVar.f1356e = this.mShouldReverseLayout ? 1 : -1;
        c cVar2 = this.mLayoutState;
        cVar2.f = -1;
        cVar2.b = i2;
        cVar2.g = Integer.MIN_VALUE;
    }

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public mf f1352a;
        public int b;
        public int c;
        public boolean d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1353e;

        public a() {
            b();
        }

        public void a() {
            this.c = this.d ? this.f1352a.b() : this.f1352a.f();
        }

        public void b() {
            this.b = -1;
            this.c = Integer.MIN_VALUE;
            this.d = false;
            this.f1353e = false;
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.b + ", mCoordinate=" + this.c + ", mLayoutFromEnd=" + this.d + ", mValid=" + this.f1353e + Operators.BLOCK_END;
        }

        public boolean a(View view, RecyclerView.y yVar) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            return !layoutParams.c() && layoutParams.a() >= 0 && layoutParams.a() < yVar.a();
        }

        public void b(View view, int i) {
            int iH = this.f1352a.h();
            if (iH >= 0) {
                a(view, i);
                return;
            }
            this.b = i;
            if (this.d) {
                int iB = (this.f1352a.b() - iH) - this.f1352a.a(view);
                this.c = this.f1352a.b() - iB;
                if (iB > 0) {
                    int iB2 = this.c - this.f1352a.b(view);
                    int iF = this.f1352a.f();
                    int iMin = iB2 - (iF + Math.min(this.f1352a.d(view) - iF, 0));
                    if (iMin < 0) {
                        this.c += Math.min(iB, -iMin);
                        return;
                    }
                    return;
                }
                return;
            }
            int iD = this.f1352a.d(view);
            int iF2 = iD - this.f1352a.f();
            this.c = iD;
            if (iF2 > 0) {
                int iB3 = (this.f1352a.b() - Math.min(0, (this.f1352a.b() - iH) - this.f1352a.a(view))) - (iD + this.f1352a.b(view));
                if (iB3 < 0) {
                    this.c -= Math.min(iF2, -iB3);
                }
            }
        }

        public void a(View view, int i) {
            if (this.d) {
                this.c = this.f1352a.a(view) + this.f1352a.h();
            } else {
                this.c = this.f1352a.d(view);
            }
            this.b = i;
        }
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.mOrientation = 1;
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mStackFromEnd = false;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo = new a();
        this.mLayoutChunkResult = new b();
        this.mInitialPrefetchItemCount = 2;
        RecyclerView.o.d properties = RecyclerView.o.getProperties(context, attributeSet, i, i2);
        setOrientation(properties.f1371a);
        setReverseLayout(properties.c);
        setStackFromEnd(properties.d);
    }
}
