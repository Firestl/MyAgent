package io.dcloud.feature.weex_media;

import android.content.Context;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;
import com.taobao.weex.ui.IFComponentHolder;
import com.taobao.weex.ui.SimpleComponentHolder;
import com.taobao.weex.ui.component.WXBasicComponentType;
import com.taobao.weex.ui.component.WXComponent;
import io.dcloud.feature.weex.WeexInstanceMgr;
import io.dcloud.feature.weex_media.VideoInnerViewComponent;

/* JADX INFO: loaded from: classes3.dex */
public class VideoPlayerPlugin {
    public static void initPlugin(Context context) {
        try {
            WXSDKEngine.registerComponent("u-video", (Class<? extends WXComponent>) VideoComponent.class);
            WXSDKEngine.registerComponent((IFComponentHolder) new SimpleComponentHolder(VideoInnerViewComponent.class, new VideoInnerViewComponent.Ceator()), false, "u-scalable");
            WeexInstanceMgr.self().addComponentByName("video", VideoComponent.class);
            WeexInstanceMgr.self().addComponentByName(WXBasicComponentType.DIV, VideoInnerViewComponent.class);
        } catch (WXException e2) {
            e2.printStackTrace();
        }
    }
}
