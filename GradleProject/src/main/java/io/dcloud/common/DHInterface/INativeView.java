package io.dcloud.common.DHInterface;

import android.view.View;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public interface INativeView {
    void attachToViewGroup(IFrameView iFrameView);

    int getInnerHeight();

    String getStyleBackgroundColor();

    int getStyleLeft();

    int getStyleWidth();

    String getViewId();

    String getViewType();

    String getViewUUId();

    boolean isAnimate();

    boolean isDock();

    boolean isDockTop();

    boolean isStatusBar();

    View obtanMainView();

    void setNativeShowType(boolean z);

    void setStyleBackgroundColor(int i);

    void setStyleBackgroundColor(String str);

    void setStyleLeft(int i);

    void setWebAnimationRuning(boolean z);

    JSONObject toJSON();
}
