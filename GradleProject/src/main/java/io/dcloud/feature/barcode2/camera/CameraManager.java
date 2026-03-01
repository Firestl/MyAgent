package io.dcloud.feature.barcode2.camera;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Build;
import android.os.Handler;
import io.dcloud.feature.barcode2.decoding.IBarHandler;
import io.dcloud.feature.barcode2.view.DetectorViewConfig;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public final class CameraManager {
    public static final int MAX_FRAME_HEIGHT = 640;
    public static final int MAX_FRAME_WIDTH = 640;
    public static final int MIN_FRAME_HEIGHT = 240;
    public static final int MIN_FRAME_WIDTH = 240;
    public static final int SDK_INT;
    public static final String TAG = "CameraManager";
    public static CameraManager cameraManager;
    public static boolean mIsVerticalScreen;
    public static Camera.Parameters parameters;
    public static int sScreenAllHeight;
    public static int sScreenWidth;
    public final AutoFocusCallback autoFocusCallback;
    public Camera camera;
    public final CameraConfigurationManager configManager;
    public final Context context;
    public Rect framingRect;
    public boolean horizontalOrientation = false;
    public boolean initialized;
    public final PreviewCallback previewCallback;
    public boolean previewing;
    public boolean useOneShotPreviewCallback;

    static {
        int i;
        try {
            i = Integer.parseInt(Build.VERSION.SDK);
        } catch (NumberFormatException unused) {
            i = 10000;
        }
        SDK_INT = i;
        mIsVerticalScreen = true;
        parameters = null;
    }

    public CameraManager(Context context) {
        this.context = context;
        CameraConfigurationManager cameraConfigurationManager = new CameraConfigurationManager(context);
        this.configManager = cameraConfigurationManager;
        this.useOneShotPreviewCallback = Integer.parseInt(Build.VERSION.SDK) > 3;
        this.previewCallback = new PreviewCallback(cameraConfigurationManager, this.useOneShotPreviewCallback);
        this.useOneShotPreviewCallback = false;
        this.autoFocusCallback = new AutoFocusCallback();
    }

    public static CameraManager get() {
        return cameraManager;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x004b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Point getCR(int r6, int r7) {
        /*
            r0 = 0
            android.hardware.Camera r1 = android.hardware.Camera.open()     // Catch: java.lang.Exception -> L26
            android.hardware.Camera$Parameters r2 = io.dcloud.feature.barcode2.camera.CameraManager.parameters     // Catch: java.lang.Exception -> L23
            if (r2 != 0) goto Lf
            android.hardware.Camera$Parameters r2 = r1.getParameters()     // Catch: java.lang.Exception -> L23
            io.dcloud.feature.barcode2.camera.CameraManager.parameters = r2     // Catch: java.lang.Exception -> L23
        Lf:
            android.graphics.Point r2 = new android.graphics.Point     // Catch: java.lang.Exception -> L23
            r2.<init>(r6, r7)     // Catch: java.lang.Exception -> L23
            android.hardware.Camera$Parameters r3 = io.dcloud.feature.barcode2.camera.CameraManager.parameters     // Catch: java.lang.Exception -> L23
            android.graphics.Point r2 = io.dcloud.feature.barcode2.camera.CameraConfigurationManager.getCameraResolution(r3, r2)     // Catch: java.lang.Exception -> L23
            r1.release()     // Catch: java.lang.Exception -> L1e
            goto L52
        L1e:
            r3 = move-exception
            r5 = r3
            r3 = r2
            r2 = r5
            goto L29
        L23:
            r2 = move-exception
            r3 = r0
            goto L29
        L26:
            r2 = move-exception
            r1 = r0
            r3 = r1
        L29:
            android.graphics.Point r4 = new android.graphics.Point
            r4.<init>(r6, r7)
            android.hardware.Camera$Parameters r6 = io.dcloud.feature.barcode2.camera.CameraManager.parameters
            if (r6 == 0) goto L38
            android.graphics.Point r6 = io.dcloud.feature.barcode2.camera.CameraConfigurationManager.getCameraResolution(r6, r4)
            r3 = r6
            goto L48
        L38:
            if (r1 == 0) goto L48
            android.hardware.Camera$Parameters r6 = r1.getParameters()     // Catch: java.lang.Exception -> L48
            io.dcloud.feature.barcode2.camera.CameraManager.parameters = r6     // Catch: java.lang.Exception -> L48
            android.graphics.Point r3 = io.dcloud.feature.barcode2.camera.CameraConfigurationManager.getCameraResolution(r6, r4)     // Catch: java.lang.Exception -> L48
            r1.release()     // Catch: java.lang.Exception -> L48
            goto L49
        L48:
            r0 = r1
        L49:
            if (r0 == 0) goto L4e
            r0.release()
        L4e:
            r2.printStackTrace()
            r2 = r3
        L52:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.barcode2.camera.CameraManager.getCR(int, int):android.graphics.Point");
    }

    public static void init(Context context) {
        if (cameraManager == null) {
            cameraManager = new CameraManager(context);
        }
    }

    public PlanarYUVLuminanceSource buildLuminanceSource(byte[] bArr, int i, int i2) {
        Rect framingRectInPreview = getFramingRectInPreview();
        int previewFormat = this.configManager.getPreviewFormat();
        String previewFormatString = this.configManager.getPreviewFormatString();
        if (previewFormat == 16 || previewFormat == 17) {
            return new PlanarYUVLuminanceSource(bArr, i, i2, framingRectInPreview.left, framingRectInPreview.top, framingRectInPreview.width(), framingRectInPreview.height());
        }
        if ("yuv420p".equals(previewFormatString)) {
            return new PlanarYUVLuminanceSource(bArr, i, i2, framingRectInPreview.left, framingRectInPreview.top, framingRectInPreview.width(), framingRectInPreview.height());
        }
        throw new IllegalArgumentException("Unsupported picture format: " + previewFormat + '/' + previewFormatString);
    }

    public void clearLastBitmapData() {
        PreviewCallback previewCallback = this.previewCallback;
        if (previewCallback != null) {
            previewCallback.setLastBitmapData(null);
        }
    }

    public void closeDriver() {
        if (this.camera != null) {
            FlashlightManager.disableFlashlight();
            this.camera.setPreviewCallback(null);
            this.camera.release();
            this.camera = null;
        }
    }

    public AutoFocusCallback getAutoFocusCallback() {
        return this.autoFocusCallback;
    }

    public Camera getCameraHandler() {
        return this.camera;
    }

    public Rect getFramingRectInPreview() {
        return mIsVerticalScreen ? getPortraitFramingRectInPreview() : getLandscapeFramingRectInPreview();
    }

    public Rect getLandscapeFramingRectInPreview() {
        Point cameraResolution = this.configManager.getCameraResolution();
        Rect detectorRect = DetectorViewConfig.getInstance().getDetectorRect();
        Rect rect = DetectorViewConfig.getInstance().surfaceViewRect;
        int iWidth = rect.width() / cameraResolution.y;
        int iWidth2 = ((detectorRect.left - DetectorViewConfig.detectorRectOffestLeft) * cameraResolution.x) / rect.width();
        int iHeight = ((detectorRect.height() * cameraResolution.y) / rect.height()) + iWidth2;
        int iHeight2 = ((rect.bottom - detectorRect.bottom) * cameraResolution.y) / rect.height();
        return new Rect(iWidth2, iHeight2, iHeight, ((detectorRect.width() * cameraResolution.y) / rect.height()) + iHeight2);
    }

    public byte[] getLastBitmapData() {
        PreviewCallback previewCallback = this.previewCallback;
        if (previewCallback == null) {
            return null;
        }
        return previewCallback.getLastBitmapData();
    }

    public Rect getPortraitFramingRectInPreview() {
        Point cameraResolution = this.configManager.getCameraResolution();
        Rect detectorRect = DetectorViewConfig.getInstance().getDetectorRect();
        Rect rect = DetectorViewConfig.getInstance().surfaceViewRect;
        int iWidth = rect.width() / cameraResolution.y;
        int iHeight = ((detectorRect.top - DetectorViewConfig.detectorRectOffestTop) * cameraResolution.x) / rect.height();
        int iHeight2 = ((detectorRect.height() * cameraResolution.x) / rect.height()) + iHeight;
        int iWidth2 = ((rect.right - detectorRect.right) * cameraResolution.y) / rect.width();
        return new Rect(iHeight, iWidth2, iHeight2, ((detectorRect.width() * cameraResolution.x) / rect.height()) + iWidth2);
    }

    public void openDriver(SurfaceTexture surfaceTexture) throws IOException {
        if (this.camera == null) {
            Camera cameraOpen = Camera.open();
            this.camera = cameraOpen;
            if (cameraOpen == null) {
                throw new IOException();
            }
            cameraOpen.setPreviewTexture(surfaceTexture);
            if (mIsVerticalScreen) {
                this.camera.setDisplayOrientation(90);
            } else {
                this.camera.setDisplayOrientation(this.horizontalOrientation ? 180 : 0);
            }
            this.configManager.initFromCameraParameters(this.camera);
            this.configManager.setDesiredCameraParameters(this.camera);
        }
    }

    public void removeAutoFocus() {
        Camera camera = this.camera;
        if (camera != null) {
            camera.cancelAutoFocus();
        }
    }

    public void requestAutoFocus(Handler handler, int i) {
        if (this.camera == null || !this.previewing) {
            return;
        }
        this.autoFocusCallback.setHandler(handler, i);
        try {
            this.camera.autoFocus(this.autoFocusCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void requestPreviewFrame(IBarHandler iBarHandler, Handler handler, int i) {
        if (this.camera == null || !this.previewing) {
            return;
        }
        this.previewCallback.setHandler(iBarHandler, handler, i, mIsVerticalScreen);
        if (this.useOneShotPreviewCallback) {
            this.camera.setOneShotPreviewCallback(this.previewCallback);
        } else {
            this.camera.setPreviewCallback(this.previewCallback);
        }
    }

    public void setFlashlight(boolean z) {
        if (z) {
            FlashlightManager.enableFlashlight();
        } else {
            FlashlightManager.disableFlashlight();
        }
    }

    public void setHorizontalOrientation(boolean z) {
        this.horizontalOrientation = z;
    }

    public void startPreview() {
        try {
            Camera camera = this.camera;
            if (camera == null || this.previewing) {
                return;
            }
            camera.startPreview();
            this.previewing = true;
        } catch (Exception unused) {
        }
    }

    public void stopPreview() {
        try {
            Camera camera = this.camera;
            if (camera == null || !this.previewing) {
                return;
            }
            if (!this.useOneShotPreviewCallback) {
                camera.setPreviewCallback(null);
            }
            this.camera.stopPreview();
            this.previewCallback.setHandler(null, null, 0, mIsVerticalScreen);
            this.autoFocusCallback.setHandler(null, 0);
            this.previewing = false;
        } catch (Exception unused) {
        }
    }

    public static void init(Context context, boolean z) {
        mIsVerticalScreen = z;
        init(context);
    }
}
