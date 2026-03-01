package io.dcloud.feature.nativeObj;

import android.view.View;
import io.dcloud.common.DHInterface.IReflectAble;

/* JADX INFO: loaded from: classes3.dex */
public interface INativeViewChildView extends IReflectAble {
    View obtainMainView();

    void updateLayout();
}
