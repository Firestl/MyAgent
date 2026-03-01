package io.dcloud.feature.barcode2.decoding;

import android.graphics.Bitmap;
import android.os.Handler;
import io.dcloud.feature.barcode2.view.ViewfinderView;
import supwisdom.xv;

/* JADX INFO: loaded from: classes2.dex */
public interface IBarHandler {
    void autoFocus();

    void drawViewfinder();

    Handler getHandler();

    ViewfinderView getViewfinderView();

    void handleDecode(xv xvVar, Bitmap bitmap);

    boolean isRunning();
}
