package io.dcloud.feature.barcode2.camera;

import android.graphics.Point;
import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;
import io.dcloud.feature.barcode2.decoding.IBarHandler;

/* JADX INFO: loaded from: classes2.dex */
public final class PreviewCallback implements Camera.PreviewCallback {
    public static final String TAG = PreviewCallback.class.getSimpleName();
    public static boolean mIsVerticalScreen = true;
    public final CameraConfigurationManager configManager;
    public byte[] lastBitmapData = null;
    public IBarHandler mBarHandler;
    public Handler previewHandler;
    public int previewMessage;
    public final boolean useOneShotPreviewCallback;

    public PreviewCallback(CameraConfigurationManager cameraConfigurationManager, boolean z) {
        this.configManager = cameraConfigurationManager;
        this.useOneShotPreviewCallback = z;
    }

    public byte[] getLastBitmapData() {
        return this.lastBitmapData;
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        IBarHandler iBarHandler = this.mBarHandler;
        if (iBarHandler == null || !iBarHandler.isRunning()) {
            return;
        }
        Point cameraResolution = this.configManager.getCameraResolution();
        if (!this.useOneShotPreviewCallback) {
            camera.setPreviewCallback(null);
        }
        Handler handler = this.previewHandler;
        if (handler != null) {
            int i = this.previewMessage;
            (i == 1004 ? mIsVerticalScreen ? handler.obtainMessage(1005, cameraResolution.x, cameraResolution.y, bArr) : handler.obtainMessage(1006, cameraResolution.x, cameraResolution.y, bArr) : handler.obtainMessage(i, cameraResolution.x, cameraResolution.y, bArr)).sendToTarget();
            this.previewHandler = null;
        } else {
            Log.d(TAG, "Got preview callback, but no handler for it");
        }
        this.lastBitmapData = bArr;
    }

    public void setHandler(IBarHandler iBarHandler, Handler handler, int i, boolean z) {
        this.mBarHandler = iBarHandler;
        this.previewHandler = handler;
        this.previewMessage = i;
        mIsVerticalScreen = z;
    }

    public void setLastBitmapData(byte[] bArr) {
        this.lastBitmapData = bArr;
    }
}
