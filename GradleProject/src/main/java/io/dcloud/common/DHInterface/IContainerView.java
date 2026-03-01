package io.dcloud.common.DHInterface;

import android.view.ViewGroup;
import io.dcloud.common.adapter.ui.AdaFrameItem;

/* JADX INFO: loaded from: classes2.dex */
public interface IContainerView {
    void addFrameItem(AdaFrameItem adaFrameItem);

    void addFrameItem(AdaFrameItem adaFrameItem, ViewGroup.LayoutParams layoutParams);

    void removeAllFrameItem();

    void removeFrameItem(AdaFrameItem adaFrameItem);
}
