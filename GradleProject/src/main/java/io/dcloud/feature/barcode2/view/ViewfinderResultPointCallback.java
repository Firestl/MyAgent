package io.dcloud.feature.barcode2.view;

import supwisdom.yv;
import supwisdom.zv;

/* JADX INFO: loaded from: classes2.dex */
public final class ViewfinderResultPointCallback implements zv {
    public final ViewfinderView viewfinderView;

    public ViewfinderResultPointCallback(ViewfinderView viewfinderView) {
        this.viewfinderView = viewfinderView;
    }

    @Override // supwisdom.zv
    public void foundPossibleResultPoint(yv yvVar) {
        this.viewfinderView.addPossibleResultPoint(yvVar);
    }
}
