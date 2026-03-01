package io.dcloud.feature.weex.adapter.widget.refresh;

import android.content.Context;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes3.dex */
public class WeexDcRefreshLayout extends DCWeexBaseRefreshLayout {
    public boolean mDragger;
    public float mStartX;
    public float mStartY;
    public int mTouchSlop;

    public WeexDcRefreshLayout(Context context) {
        super(context);
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    private boolean canScrollVertically(View view) {
        if (view instanceof ViewGroup) {
            if (!view.canScrollVertically(-1)) {
                int i = 0;
                while (true) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    if (i >= viewGroup.getChildCount()) {
                        break;
                    }
                    if (canScrollVertically(viewGroup.getChildAt(i))) {
                        return true;
                    }
                    i++;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    private boolean isViewRefresh() {
        for (int i = 0; i < getChildCount(); i++) {
            if (canScrollVertically(getChildAt(i))) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0047  */
    @Override // io.dcloud.feature.weex.adapter.widget.refresh.DCWeexBaseRefreshLayout, android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            boolean r0 = r5.isEnabled()
            if (r0 == 0) goto L58
            int r0 = r6.getAction()
            boolean r1 = r5.isViewRefresh()
            r2 = 0
            if (r1 != 0) goto L12
            return r2
        L12:
            if (r0 == 0) goto L4a
            r1 = 1
            if (r0 == r1) goto L47
            r3 = 2
            if (r0 == r3) goto L1e
            r1 = 3
            if (r0 == r1) goto L47
            goto L58
        L1e:
            boolean r0 = r5.mDragger
            if (r0 == 0) goto L23
            return r2
        L23:
            float r0 = r6.getY()
            float r3 = r6.getX()
            float r4 = r5.mStartX
            float r3 = r3 - r4
            float r3 = java.lang.Math.abs(r3)
            float r4 = r5.mStartY
            float r0 = r0 - r4
            float r0 = java.lang.Math.abs(r0)
            int r4 = r5.mTouchSlop
            float r4 = (float) r4
            int r4 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r4 <= 0) goto L58
            int r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r0 <= 0) goto L58
            r5.mDragger = r1
            return r2
        L47:
            r5.mDragger = r2
            goto L58
        L4a:
            float r0 = r6.getY()
            r5.mStartY = r0
            float r0 = r6.getX()
            r5.mStartX = r0
            r5.mDragger = r2
        L58:
            boolean r6 = super.onInterceptTouchEvent(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.weex.adapter.widget.refresh.WeexDcRefreshLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }
}
