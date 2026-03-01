package io.dcloud.feature.weex.extend;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.utils.WXUtils;
import io.dcloud.feature.weex_amap.adapter.Constant;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public class DCCoverViewComponent extends WXVContainer<ViewGroup> {
    public HashMap<String, String> CalloutMarkerIds;
    public FrameLayout realView;

    public DCCoverViewComponent(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
        this.CalloutMarkerIds = new HashMap<>();
    }

    @Override // io.dcloud.feature.uniapp.ui.component.AbsVContainer
    public void addChild(WXComponent wXComponent, int i) {
        Object obj;
        if ((wXComponent instanceof DCCoverViewComponent) && (obj = wXComponent.getAttrs().get("markerId")) != null) {
            this.CalloutMarkerIds.put(obj.toString(), wXComponent.getRef());
        }
        super.addChild(wXComponent, i);
    }

    public HashMap<String, String> getCalloutMarkerIds() {
        return this.CalloutMarkerIds;
    }

    @Override // io.dcloud.feature.uniapp.ui.component.AbsVContainer
    public void remove(WXComponent wXComponent, boolean z) {
        if (!(wXComponent instanceof DCCoverViewComponent)) {
            super.remove(wXComponent, z);
            return;
        }
        Object obj = wXComponent.getAttrs().get("marker-id");
        if (obj == null || this.CalloutMarkerIds.get(obj).equals(wXComponent.getRef())) {
            return;
        }
        this.CalloutMarkerIds.remove(obj);
    }

    @Override // io.dcloud.feature.uniapp.ui.component.AbsVContainer, com.taobao.weex.ui.component.WXComponent
    public ViewGroup getRealView() {
        return this.realView;
    }

    @Override // com.taobao.weex.ui.component.WXComponent
    public ViewGroup initComponentHostView(Context context) {
        ViewGroup viewGroup;
        String string = "";
        if (getStyles().containsKey(Constants.Name.OVERFLOW)) {
            string = WXUtils.getString(getStyles().get(Constants.Name.OVERFLOW), "");
        } else if (getStyles().containsKey("overflowY")) {
            string = WXUtils.getString(getStyles().get("overflowY"), "");
        }
        this.realView = new FrameLayout(context);
        if (string.equals(Constants.Event.SCROLL)) {
            ScrollView scrollView = new ScrollView(context);
            scrollView.addView(this.realView, new FrameLayout.LayoutParams(-1, -1));
            viewGroup = scrollView;
        } else {
            viewGroup = this.realView;
        }
        Object obj = getAttrs().get("slot");
        if (obj != null && obj.equals(Constant.JSONKEY.CALLOUT)) {
            viewGroup.setVisibility(4);
        }
        return viewGroup;
    }
}
