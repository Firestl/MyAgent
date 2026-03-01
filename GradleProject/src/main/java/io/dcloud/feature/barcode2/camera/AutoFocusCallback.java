package io.dcloud.feature.barcode2.camera;

import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public final class AutoFocusCallback implements Camera.AutoFocusCallback {
    public static final long AUTOFOCUS_INTERVAL_MS = 200;
    public static final String TAG = AutoFocusCallback.class.getSimpleName();
    public Handler autoFocusHandler;
    public int autoFocusMessage;

    @Override // android.hardware.Camera.AutoFocusCallback
    public void onAutoFocus(boolean z, Camera camera) {
        Handler handler = this.autoFocusHandler;
        if (handler == null) {
            Log.d(TAG, "Got auto-focus callback, but no handler for it");
            return;
        }
        this.autoFocusHandler.sendMessageDelayed(handler.obtainMessage(this.autoFocusMessage, Boolean.valueOf(z)), 200L);
        this.autoFocusHandler = null;
    }

    public void setHandler(Handler handler, int i) {
        this.autoFocusHandler = handler;
        this.autoFocusMessage = i;
    }
}
