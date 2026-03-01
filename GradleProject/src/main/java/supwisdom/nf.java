package supwisdom;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: PagerSnapHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class nf extends rf {
    public static final int MAX_SCROLL_ON_FLING_DURATION = 100;
    public mf mHorizontalHelper;
    public mf mVerticalHelper;

    /* JADX INFO: compiled from: PagerSnapHelper.java */
    public class a extends kf {
        public a(Context context) {
            super(context);
        }

        @Override // supwisdom.kf
        public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
            return 100.0f / displayMetrics.densityDpi;
        }

        @Override // supwisdom.kf
        public int calculateTimeForScrolling(int i) {
            return Math.min(100, super.calculateTimeForScrolling(i));
        }

        @Override // supwisdom.kf, androidx.recyclerview.widget.RecyclerView.x
        public void onTargetFound(View view, RecyclerView.y yVar, RecyclerView.x.a aVar) {
            nf nfVar = nf.this;
            int[] iArrCalculateDistanceToFinalSnap = nfVar.calculateDistanceToFinalSnap(nfVar.mRecyclerView.getLayoutManager(), view);
            int i = iArrCalculateDistanceToFinalSnap[0];
            int i2 = iArrCalculateDistanceToFinalSnap[1];
            int iCalculateTimeForDeceleration = calculateTimeForDeceleration(Math.max(Math.abs(i), Math.abs(i2)));
            if (iCalculateTimeForDeceleration > 0) {
                aVar.a(i, i2, iCalculateTimeForDeceleration, this.mDecelerateInterpolator);
            }
        }
    }

    private int distanceToCenter(RecyclerView.o oVar, View view, mf mfVar) {
        return (mfVar.d(view) + (mfVar.b(view) / 2)) - (oVar.getClipToPadding() ? mfVar.f() + (mfVar.g() / 2) : mfVar.a() / 2);
    }

    private View findCenterView(RecyclerView.o oVar, mf mfVar) {
        int childCount = oVar.getChildCount();
        View view = null;
        if (childCount == 0) {
            return null;
        }
        int iF = oVar.getClipToPadding() ? mfVar.f() + (mfVar.g() / 2) : mfVar.a() / 2;
        int i = Integer.MAX_VALUE;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = oVar.getChildAt(i2);
            int iAbs = Math.abs((mfVar.d(childAt) + (mfVar.b(childAt) / 2)) - iF);
            if (iAbs < i) {
                view = childAt;
                i = iAbs;
            }
        }
        return view;
    }

    private View findStartView(RecyclerView.o oVar, mf mfVar) {
        int childCount = oVar.getChildCount();
        View view = null;
        if (childCount == 0) {
            return null;
        }
        int i = Integer.MAX_VALUE;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = oVar.getChildAt(i2);
            int iD = mfVar.d(childAt);
            if (iD < i) {
                view = childAt;
                i = iD;
            }
        }
        return view;
    }

    private mf getHorizontalHelper(RecyclerView.o oVar) {
        mf mfVar = this.mHorizontalHelper;
        if (mfVar == null || mfVar.f8379a != oVar) {
            this.mHorizontalHelper = mf.a(oVar);
        }
        return this.mHorizontalHelper;
    }

    private mf getVerticalHelper(RecyclerView.o oVar) {
        mf mfVar = this.mVerticalHelper;
        if (mfVar == null || mfVar.f8379a != oVar) {
            this.mVerticalHelper = mf.b(oVar);
        }
        return this.mVerticalHelper;
    }

    @Override // supwisdom.rf
    public int[] calculateDistanceToFinalSnap(RecyclerView.o oVar, View view) {
        int[] iArr = new int[2];
        if (oVar.canScrollHorizontally()) {
            iArr[0] = distanceToCenter(oVar, view, getHorizontalHelper(oVar));
        } else {
            iArr[0] = 0;
        }
        if (oVar.canScrollVertically()) {
            iArr[1] = distanceToCenter(oVar, view, getVerticalHelper(oVar));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    @Override // supwisdom.rf
    public kf createSnapScroller(RecyclerView.o oVar) {
        if (oVar instanceof RecyclerView.x.b) {
            return new a(this.mRecyclerView.getContext());
        }
        return null;
    }

    @Override // supwisdom.rf
    public View findSnapView(RecyclerView.o oVar) {
        if (oVar.canScrollVertically()) {
            return findCenterView(oVar, getVerticalHelper(oVar));
        }
        if (oVar.canScrollHorizontally()) {
            return findCenterView(oVar, getHorizontalHelper(oVar));
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // supwisdom.rf
    public int findTargetSnapPosition(RecyclerView.o oVar, int i, int i2) {
        int position;
        PointF pointFComputeScrollVectorForPosition;
        int itemCount = oVar.getItemCount();
        if (itemCount == 0) {
            return -1;
        }
        View viewFindStartView = null;
        if (oVar.canScrollVertically()) {
            viewFindStartView = findStartView(oVar, getVerticalHelper(oVar));
        } else if (oVar.canScrollHorizontally()) {
            viewFindStartView = findStartView(oVar, getHorizontalHelper(oVar));
        }
        if (viewFindStartView == null || (position = oVar.getPosition(viewFindStartView)) == -1) {
            return -1;
        }
        boolean z = false;
        boolean z2 = !oVar.canScrollHorizontally() ? i2 <= 0 : i <= 0;
        if ((oVar instanceof RecyclerView.x.b) && (pointFComputeScrollVectorForPosition = ((RecyclerView.x.b) oVar).computeScrollVectorForPosition(itemCount - 1)) != null && (pointFComputeScrollVectorForPosition.x < 0.0f || pointFComputeScrollVectorForPosition.y < 0.0f)) {
            z = true;
        }
        return z ? z2 ? position - 1 : position : z2 ? position + 1 : position;
    }
}
