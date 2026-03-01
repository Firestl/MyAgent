package io.dcloud.feature.weex.extend;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.ui.action.BasicComponentData;
import com.taobao.weex.ui.component.WXImage;
import com.taobao.weex.ui.component.WXVContainer;
import io.dcloud.feature.weex.adapter.GlideImageAdapter;

/* JADX INFO: loaded from: classes3.dex */
public class DCCoverImageComponent extends WXImage {
    public DCCoverImageComponent(WXSDKInstance wXSDKInstance, WXVContainer wXVContainer, BasicComponentData basicComponentData) {
        super(wXSDKInstance, wXVContainer, basicComponentData);
    }

    @Override // com.taobao.weex.ui.component.WXImage
    public void setImage(String str, WXImageStrategy wXImageStrategy) {
        GlideImageAdapter.setImage(str, getHostView(), getImageQuality(), wXImageStrategy);
    }
}
