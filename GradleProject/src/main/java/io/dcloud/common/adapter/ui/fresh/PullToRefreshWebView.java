package io.dcloud.common.adapter.ui.fresh;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;

/* JADX INFO: loaded from: classes2.dex */
public class PullToRefreshWebView extends PullToRefreshBase<View> {
    public PullToRefreshWebView(Context context) {
        super(context);
    }

    @Override // io.dcloud.common.adapter.ui.fresh.PullToRefreshBase
    public boolean isReadyForPullDown() {
        return this.mRefreshableView.getScrollY() == 0;
    }

    @Override // io.dcloud.common.adapter.ui.fresh.PullToRefreshBase
    public boolean isReadyForPullUp() {
        float f = this.mRefreshableView.getContext().getResources().getDisplayMetrics().density;
        int measuredHeight = this.mRefreshableView.getMeasuredHeight();
        T t = this.mRefreshableView;
        if (t instanceof WebView) {
            measuredHeight = ((WebView) t).getContentHeight();
        }
        return ((double) this.mRefreshableView.getScrollY()) >= Math.floor(Double.valueOf(String.valueOf(((float) measuredHeight) * f)).doubleValue()) - ((double) this.mRefreshableView.getHeight());
    }

    @Override // android.view.View
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
    }
}
