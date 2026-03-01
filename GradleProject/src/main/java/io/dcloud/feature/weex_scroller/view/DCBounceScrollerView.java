package io.dcloud.feature.weex_scroller.view;

import android.content.Context;
import com.taobao.weex.ui.component.DCWXScroller;
import com.taobao.weex.ui.view.refresh.wrapper.BaseBounceView;

/* JADX INFO: loaded from: classes3.dex */
public class DCBounceScrollerView extends BaseBounceView<DCWXScrollView> {
    public DCBounceScrollerView(Context context, int i, DCWXScroller dCWXScroller) {
        super(context, i);
        init(context);
        if (getInnerView() != null) {
            getInnerView().setWAScroller(dCWXScroller);
        }
    }

    @Override // com.taobao.weex.ui.view.refresh.wrapper.BaseBounceView
    public void onLoadmoreComplete() {
    }

    @Override // com.taobao.weex.ui.view.refresh.wrapper.BaseBounceView
    public void onRefreshingComplete() {
    }

    @Override // com.taobao.weex.ui.view.refresh.wrapper.BaseBounceView
    public DCWXScrollView setInnerView(Context context) {
        return new DCWXScrollView(context);
    }
}
