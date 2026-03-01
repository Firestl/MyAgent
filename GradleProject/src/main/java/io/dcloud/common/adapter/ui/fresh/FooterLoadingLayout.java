package io.dcloud.common.adapter.ui.fresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import io.dcloud.common.adapter.ui.fresh.ILoadingLayout;

/* JADX INFO: loaded from: classes2.dex */
public class FooterLoadingLayout extends LoadingLayout {
    public FooterLoadingLayout(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        setState(ILoadingLayout.State.RESET);
    }

    @Override // io.dcloud.common.adapter.ui.fresh.LoadingLayout
    public View createLoadingView(Context context, AttributeSet attributeSet) {
        TextView textView = new TextView(context);
        textView.setText("createLoadingView");
        return textView;
    }

    @Override // io.dcloud.common.adapter.ui.fresh.LoadingLayout, io.dcloud.common.adapter.ui.fresh.ILoadingLayout
    public int getContentSize() {
        return (int) (getResources().getDisplayMetrics().density * 100.0f);
    }

    @Override // io.dcloud.common.adapter.ui.fresh.LoadingLayout
    public void onNoMoreData() {
    }

    @Override // io.dcloud.common.adapter.ui.fresh.LoadingLayout
    public void onPullToRefresh() {
    }

    @Override // io.dcloud.common.adapter.ui.fresh.LoadingLayout
    public void onRefreshing() {
    }

    @Override // io.dcloud.common.adapter.ui.fresh.LoadingLayout
    public void onReleaseToRefresh() {
    }

    @Override // io.dcloud.common.adapter.ui.fresh.LoadingLayout
    public void onReset() {
    }

    @Override // io.dcloud.common.adapter.ui.fresh.LoadingLayout
    public void onStateChanged(ILoadingLayout.State state, ILoadingLayout.State state2) {
        super.onStateChanged(state, state2);
    }

    @Override // io.dcloud.common.adapter.ui.fresh.LoadingLayout
    public void setLastUpdatedLabel(CharSequence charSequence) {
    }

    public FooterLoadingLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }
}
