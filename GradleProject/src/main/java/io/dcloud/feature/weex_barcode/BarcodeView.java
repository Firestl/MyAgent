package io.dcloud.feature.weex_barcode;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.graphics.YuvImage;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.TextureView;
import android.widget.AbsoluteLayout;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.dcloud.zxing2.BarcodeFormat;
import com.google.zxing.client.android.LocaleManager;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.component.WXComponent;
import io.dcloud.common.adapter.util.PermissionUtil;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.ThreadPool;
import io.dcloud.feature.barcode2.camera.CameraManager;
import io.dcloud.feature.barcode2.decoding.CaptureActivityHandler;
import io.dcloud.feature.barcode2.decoding.IBarHandler;
import io.dcloud.feature.barcode2.decoding.InactivityTimer;
import io.dcloud.feature.barcode2.view.DetectorViewConfig;
import io.dcloud.feature.barcode2.view.ViewfinderView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import supwisdom.xv;

/* JADX INFO: loaded from: classes3.dex */
public class BarcodeView extends AbsoluteLayout implements IBarHandler, TextureView.SurfaceTextureListener {
    public static final int AZTEC = 3;
    public static final float BEEP_VOLUME = 0.8f;
    public static final int CODABAR = 7;
    public static final int CODE128 = 10;
    public static final int CODE39 = 8;
    public static final int CODE93 = 9;
    public static final int DATAMATRIX = 4;
    public static final int EAN13 = 1;
    public static final int EAN8 = 2;
    public static final int ID_ADD_VIEW = 201;
    public static final int ID_START_SCAN = 203;
    public static final int ID_UPDATE_VIEW = 202;
    public static final int ITF = 11;
    public static final int MAXICODE = 12;
    public static final int PDF417 = 13;
    public static final int QR = 0;
    public static final int RSS14 = 14;
    public static final int RSSEXPANDED = 15;
    public static final int UNKOWN = -1000;
    public static final int UPCA = 5;
    public static final int UPCE = 6;
    public static final long VIBRATE_DURATION = 200;
    public boolean autoDecodeCharset;
    public final MediaPlayer.OnCompletionListener beepListener;
    public String characterSet;
    public WXComponent component;
    public Context context;
    public Vector<BarcodeFormat> decodeFormats;
    public String errorMsg;
    public CaptureActivityHandler handler;
    public boolean hasSurface;
    public InactivityTimer inactivityTimer;
    public boolean isCancelScan;
    public boolean isSurfaceAvaliable;
    public boolean isVerticalScreen;
    public Bitmap lastBiptmap;
    public boolean mConserve;
    public String mFilename;

    @SuppressLint({"HandlerLeak"})
    public Handler mHandler;
    public WXSDKInstance mInstance;
    public int mOrientationState;
    public boolean mRunning;
    public MediaPlayer mediaPlayer;
    public boolean nopermission;
    public boolean playBeep;
    public TextureView surfaceView;
    public boolean vibrate;
    public int viewHeight;
    public int viewWidth;
    public ViewfinderView viewfinderView;

    /* JADX WARN: Removed duplicated region for block: B:12:0x0081  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public BarcodeView(android.content.Context r3, com.taobao.weex.ui.component.WXComponent r4, com.taobao.weex.WXSDKInstance r5) {
        /*
            r2 = this;
            r2.<init>(r3)
            r0 = 0
            r2.hasSurface = r0
            r1 = 0
            r2.errorMsg = r1
            r2.mConserve = r0
            r2.isSurfaceAvaliable = r0
            r2.isVerticalScreen = r0
            r2.autoDecodeCharset = r0
            io.dcloud.feature.weex_barcode.BarcodeView$1 r1 = new io.dcloud.feature.weex_barcode.BarcodeView$1
            r1.<init>()
            r2.mHandler = r1
            r2.isCancelScan = r0
            r2.mRunning = r0
            io.dcloud.feature.weex_barcode.BarcodeView$5 r1 = new io.dcloud.feature.weex_barcode.BarcodeView$5
            r1.<init>()
            r2.beepListener = r1
            r2.component = r4
            r2.mInstance = r5
            android.view.TextureView r4 = new android.view.TextureView
            r4.<init>(r3)
            r2.surfaceView = r4
            io.dcloud.feature.barcode2.view.ViewfinderView r4 = new io.dcloud.feature.barcode2.view.ViewfinderView
            r4.<init>(r3, r2)
            r2.viewfinderView = r4
            io.dcloud.feature.barcode2.decoding.InactivityTimer r4 = new io.dcloud.feature.barcode2.decoding.InactivityTimer
            r5 = r3
            android.app.Activity r5 = (android.app.Activity) r5
            r4.<init>(r5)
            r2.inactivityTimer = r4
            r2.context = r3
            r2.saveOrientationState()
            android.content.Context r4 = r2.context
            io.dcloud.feature.barcode2.camera.CameraManager.init(r4, r0)
            android.view.WindowManager r4 = r5.getWindowManager()
            android.view.Display r4 = r4.getDefaultDisplay()
            int r4 = r4.getRotation()
            r5 = 1
            if (r4 == 0) goto L81
            if (r4 == r5) goto L72
            r1 = 2
            if (r4 == r1) goto L81
            r3 = 3
            if (r4 == r3) goto L61
            goto L8e
        L61:
            io.dcloud.feature.barcode2.camera.CameraManager r3 = io.dcloud.feature.barcode2.camera.CameraManager.get()
            r3.setHorizontalOrientation(r5)
            android.content.Context r3 = r2.context
            android.app.Activity r3 = (android.app.Activity) r3
            r4 = 8
            r3.setRequestedOrientation(r4)
            goto L8e
        L72:
            io.dcloud.feature.barcode2.camera.CameraManager r3 = io.dcloud.feature.barcode2.camera.CameraManager.get()
            r3.setHorizontalOrientation(r0)
            android.content.Context r3 = r2.context
            android.app.Activity r3 = (android.app.Activity) r3
            r3.setRequestedOrientation(r0)
            goto L8e
        L81:
            android.content.Context r4 = r2.context
            android.app.Activity r4 = (android.app.Activity) r4
            r1 = 7
            r4.setRequestedOrientation(r1)
            io.dcloud.feature.barcode2.camera.CameraManager.init(r3, r5)
            r2.isVerticalScreen = r5
        L8e:
            r2.onResume(r0)
            r2.hasSurface = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.weex_barcode.BarcodeView.<init>(android.content.Context, com.taobao.weex.ui.component.WXComponent, com.taobao.weex.WXSDKInstance):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addBarcodeView() {
        AbsoluteLayout.LayoutParams layoutParams = setLayoutParams();
        if (layoutParams == null) {
            return;
        }
        Message message = new Message();
        message.what = 201;
        message.obj = layoutParams;
        this.mHandler.sendMessage(message);
    }

    private Bitmap byte2bitmap(byte[] bArr, Camera camera) {
        Bitmap bitmapDecodeByteArray = null;
        try {
            Camera.Size previewSize = camera.getParameters().getPreviewSize();
            YuvImage yuvImage = new YuvImage(bArr, 17, previewSize.width, previewSize.height, null);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            yuvImage.compressToJpeg(new Rect(0, 0, previewSize.width, previewSize.height), 80, byteArrayOutputStream);
            bitmapDecodeByteArray = BitmapFactory.decodeByteArray(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size());
            byteArrayOutputStream.close();
            Matrix matrix = new Matrix();
            matrix.postRotate(90.0f);
            return Bitmap.createBitmap(bitmapDecodeByteArray, 0, 0, bitmapDecodeByteArray.getWidth(), bitmapDecodeByteArray.getHeight(), matrix, true);
        } catch (Exception e2) {
            e2.printStackTrace();
            return bitmapDecodeByteArray;
        }
    }

    private void cancel() {
        if (this.mRunning) {
            CaptureActivityHandler captureActivityHandler = this.handler;
            if (captureActivityHandler != null) {
                captureActivityHandler.stopDecode();
            }
            getViewfinderView().stopUpdateScreenTimer();
            this.mRunning = false;
        }
    }

    private BarcodeFormat convertNumToBarcodeFormat(int i) {
        switch (i) {
            case 0:
                return BarcodeFormat.QR_CODE;
            case 1:
                return BarcodeFormat.EAN_13;
            case 2:
                return BarcodeFormat.EAN_8;
            case 3:
                return BarcodeFormat.AZTEC;
            case 4:
                return BarcodeFormat.DATA_MATRIX;
            case 5:
                return BarcodeFormat.UPC_A;
            case 6:
                return BarcodeFormat.UPC_E;
            case 7:
                return BarcodeFormat.CODABAR;
            case 8:
                return BarcodeFormat.CODE_39;
            case 9:
                return BarcodeFormat.CODE_93;
            case 10:
                return BarcodeFormat.CODE_128;
            case 11:
                return BarcodeFormat.ITF;
            case 12:
                return BarcodeFormat.MAXICODE;
            case 13:
                return BarcodeFormat.PDF_417;
            case 14:
                return BarcodeFormat.RSS_14;
            case 15:
                return BarcodeFormat.RSS_EXPANDED;
            default:
                return null;
        }
    }

    private int convertTypestrToNum(BarcodeFormat barcodeFormat) {
        if (barcodeFormat == BarcodeFormat.QR_CODE) {
            return 0;
        }
        if (barcodeFormat == BarcodeFormat.EAN_13) {
            return 1;
        }
        if (barcodeFormat == BarcodeFormat.EAN_8) {
            return 2;
        }
        if (barcodeFormat == BarcodeFormat.AZTEC) {
            return 3;
        }
        if (barcodeFormat == BarcodeFormat.DATA_MATRIX) {
            return 4;
        }
        if (barcodeFormat == BarcodeFormat.UPC_A) {
            return 5;
        }
        if (barcodeFormat == BarcodeFormat.UPC_E) {
            return 6;
        }
        if (barcodeFormat == BarcodeFormat.CODABAR) {
            return 7;
        }
        if (barcodeFormat == BarcodeFormat.CODE_39) {
            return 8;
        }
        if (barcodeFormat == BarcodeFormat.CODE_93) {
            return 9;
        }
        if (barcodeFormat == BarcodeFormat.CODE_128) {
            return 10;
        }
        if (barcodeFormat == BarcodeFormat.ITF) {
            return 11;
        }
        if (barcodeFormat == BarcodeFormat.MAXICODE) {
            return 12;
        }
        if (barcodeFormat == BarcodeFormat.PDF_417) {
            return 13;
        }
        if (barcodeFormat == BarcodeFormat.RSS_14) {
            return 14;
        }
        return barcodeFormat == BarcodeFormat.RSS_EXPANDED ? 15 : -1000;
    }

    private void fireEvent(String str, Map<String, Object> map) {
        if (this.component.containsEvent(str)) {
            HashMap map2 = new HashMap();
            map2.put("detail", map);
            this.component.fireEvent(str, map2);
        }
    }

    private void initBeepSound() {
        if (this.mediaPlayer == null) {
            ((Activity) this.context).setVolumeControlStream(3);
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.mediaPlayer = mediaPlayer;
            mediaPlayer.setAudioStreamType(3);
            this.mediaPlayer.setOnCompletionListener(this.beepListener);
            try {
                AssetFileDescriptor assetFileDescriptorOpenFd = this.context.getResources().getAssets().openFd(AbsoluteConst.RES_BEEP);
                this.mediaPlayer.setDataSource(assetFileDescriptorOpenFd.getFileDescriptor(), assetFileDescriptorOpenFd.getStartOffset(), assetFileDescriptorOpenFd.getLength());
                assetFileDescriptorOpenFd.close();
                this.mediaPlayer.setVolume(0.8f, 0.8f);
                this.mediaPlayer.prepare();
            } catch (IOException unused) {
                this.mediaPlayer = null;
            }
        }
    }

    private void initCamera() {
        try {
            CameraManager.get().openDriver(this.surfaceView.getSurfaceTexture());
            CaptureActivityHandler captureActivityHandler = this.handler;
            if (captureActivityHandler != null) {
                captureActivityHandler.resume();
                return;
            }
            CaptureActivityHandler captureActivityHandler2 = new CaptureActivityHandler(this, this.decodeFormats, this.characterSet, this.autoDecodeCharset);
            this.handler = captureActivityHandler2;
            if (this.mRunning) {
                captureActivityHandler2.restartPreviewAndDecode();
            }
        } catch (IOException e2) {
            this.errorMsg = e2.getMessage();
        }
    }

    private void playBeepSoundAndVibrate() {
        MediaPlayer mediaPlayer;
        if (this.playBeep && (mediaPlayer = this.mediaPlayer) != null) {
            mediaPlayer.start();
        }
        if (this.vibrate) {
            try {
                ((Vibrator) this.context.getSystemService("vibrator")).vibrate(200L);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void resumeOrientationState() {
        ((Activity) this.context).setRequestedOrientation(this.mOrientationState);
    }

    private void saveOrientationState() {
        this.mOrientationState = ((Activity) this.context).getRequestedOrientation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AbsoluteLayout.LayoutParams setLayoutParams() {
        int i;
        int i2;
        int i3;
        int i4;
        CameraManager.sScreenWidth = this.context.getResources().getDisplayMetrics().widthPixels;
        CameraManager.sScreenAllHeight = this.context.getResources().getDisplayMetrics().heightPixels;
        Rect rect = DetectorViewConfig.getInstance().gatherRect;
        int i5 = 0;
        rect.left = 0;
        rect.top = 0;
        rect.right = this.viewWidth;
        rect.bottom = this.viewHeight;
        Point cr = this.isVerticalScreen ? CameraManager.getCR(rect.height(), rect.width()) : CameraManager.getCR(rect.width(), rect.height());
        if (cr == null) {
            cr = new Point(this.viewWidth, this.viewHeight);
        }
        if (this.isVerticalScreen) {
            i2 = this.viewWidth;
            int i6 = cr.x;
            int i7 = cr.y;
            int i8 = (i2 * i6) / i7;
            i = this.viewHeight;
            if (i8 < i) {
                int i9 = (i7 * i) / i6;
                int i10 = (i2 - i9) / 2;
                DetectorViewConfig.detectorRectOffestLeft = i10;
                DetectorViewConfig.detectorRectOffestTop = 0;
                i5 = i10;
                i2 = i9;
                i3 = i;
                i4 = 0;
            } else {
                int i11 = (i6 * i2) / i7;
                i4 = (i - i11) / 2;
                DetectorViewConfig.detectorRectOffestTop = i4;
                DetectorViewConfig.detectorRectOffestLeft = 0;
                i3 = i11;
            }
        } else {
            i = this.viewHeight;
            int i12 = cr.x;
            int i13 = cr.y;
            int i14 = (i * i12) / i13;
            int i15 = this.viewWidth;
            if (i14 < i15) {
                i3 = (i13 * i15) / i12;
                i4 = (i - i3) / 2;
                DetectorViewConfig.detectorRectOffestTop = i4;
                DetectorViewConfig.detectorRectOffestLeft = 0;
                i2 = i15;
            } else {
                i2 = (i12 * i) / i13;
                int i16 = (i15 - i2) / 2;
                DetectorViewConfig.detectorRectOffestLeft = i16;
                DetectorViewConfig.detectorRectOffestTop = 0;
                i5 = i16;
                i3 = i;
                i4 = 0;
            }
        }
        AbsoluteLayout.LayoutParams layoutParams = new AbsoluteLayout.LayoutParams(i2, i3, i5, i4);
        DetectorViewConfig.getInstance().initSurfaceViewRect(i5, i4, i2, i3);
        return layoutParams;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startP() {
        initCamera();
        if (this.decodeFormats == null) {
            initDecodeFormats(null);
        }
        if (!TextUtils.isEmpty(this.errorMsg)) {
            HashMap map = new HashMap();
            map.put("code", 8);
            map.put("message", this.errorMsg);
            map.put("type", Constants.Event.FAIL);
            fireEvent("error", map);
            return;
        }
        if (this.mRunning) {
            return;
        }
        getViewfinderView().startUpdateScreenTimer();
        CaptureActivityHandler captureActivityHandler = this.handler;
        if (captureActivityHandler != null) {
            captureActivityHandler.restartPreviewAndDecode();
        } else {
            onResume(false);
        }
        if (this.isCancelScan) {
            this.surfaceView.setBackgroundDrawable(null);
            Bitmap bitmap = this.lastBiptmap;
            if (bitmap != null && !bitmap.isRecycled()) {
                this.lastBiptmap.recycle();
                this.lastBiptmap = null;
            }
            CameraManager.get().clearLastBitmapData();
            this.surfaceView.postInvalidate();
            initCamera();
        }
        this.mRunning = true;
        this.isCancelScan = false;
    }

    @Override // io.dcloud.feature.barcode2.decoding.IBarHandler
    public void autoFocus() {
        this.handler.autoFocus();
    }

    public void cancelScan() {
        if (this.mRunning) {
            CaptureActivityHandler captureActivityHandler = this.handler;
            if (captureActivityHandler != null) {
                captureActivityHandler.quitSynchronously();
                this.handler = null;
            }
            getViewfinderView().stopUpdateScreenTimer();
            CameraManager.get().removeAutoFocus();
            CameraManager.get().stopPreview();
            byte[] lastBitmapData = CameraManager.get().getLastBitmapData();
            Camera cameraHandler = CameraManager.get().getCameraHandler();
            if (lastBitmapData != null && cameraHandler != null) {
                this.lastBiptmap = byte2bitmap(lastBitmapData, cameraHandler);
            }
            CameraManager.get().closeDriver();
            this.mRunning = false;
            this.isCancelScan = true;
        }
    }

    public void closeScan() {
        onPause();
        CameraManager.get().closeDriver();
        DetectorViewConfig.clearData();
        this.surfaceView = null;
        Bitmap bitmap = this.lastBiptmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.lastBiptmap.recycle();
            this.lastBiptmap = null;
        }
        CameraManager.get().clearLastBitmapData();
        System.gc();
    }

    @Override // io.dcloud.feature.barcode2.decoding.IBarHandler
    public void drawViewfinder() {
        this.viewfinderView.drawViewfinder();
    }

    @Override // android.view.View, io.dcloud.feature.barcode2.decoding.IBarHandler
    public Handler getHandler() {
        return this.handler;
    }

    @Override // io.dcloud.feature.barcode2.decoding.IBarHandler
    public ViewfinderView getViewfinderView() {
        return this.viewfinderView;
    }

    @Override // io.dcloud.feature.barcode2.decoding.IBarHandler
    public void handleDecode(xv xvVar, Bitmap bitmap) {
        boolean zSaveBitmapToFile;
        String path;
        this.inactivityTimer.onActivity();
        playBeepSoundAndVibrate();
        if (this.mConserve) {
            if (!PdrUtil.isEmpty(this.mFilename) && !PdrUtil.isDeviceRootDir(this.mFilename) && !this.mFilename.startsWith(BaseInfo.REL_PRIVATE_DOC_DIR)) {
                this.mFilename = BaseInfo.REL_PRIVATE_DOC_DIR + this.mFilename;
            }
            path = this.mInstance.rewriteUri(Uri.parse(this.mFilename), "image").getPath();
            zSaveBitmapToFile = PdrUtil.saveBitmapToFile(bitmap, path);
        } else {
            zSaveBitmapToFile = false;
            path = null;
        }
        int iConvertTypestrToNum = convertTypestrToNum(xvVar.a());
        Map<String, Object> map = new HashMap<>();
        map.put("code", Integer.valueOf(iConvertTypestrToNum));
        map.put("message", xvVar.e());
        if (zSaveBitmapToFile && !PdrUtil.isEmpty(path)) {
            map.put("file", path);
        }
        map.put("type", "success");
        map.put("charSet", xvVar.f);
        fireEvent("marked", map);
        cancelScan();
    }

    public void initBarcodeView(int i, int i2) {
        this.viewWidth = i;
        this.viewHeight = i2;
        ThreadPool.self().addThreadTask(new Runnable() { // from class: io.dcloud.feature.weex_barcode.BarcodeView.2
            @Override // java.lang.Runnable
            public void run() {
                BarcodeView.this.addBarcodeView();
            }
        });
    }

    public void initDecodeFormats(JSONArray jSONArray) {
        int iIntValue;
        this.decodeFormats = new Vector<>();
        if (jSONArray == null || jSONArray.size() == 0) {
            this.decodeFormats.add(BarcodeFormat.EAN_13);
            this.decodeFormats.add(BarcodeFormat.EAN_8);
            this.decodeFormats.add(BarcodeFormat.QR_CODE);
            return;
        }
        int size = jSONArray.size();
        for (int i = 0; i < size; i++) {
            try {
                iIntValue = jSONArray.getInteger(i).intValue();
            } catch (JSONException e2) {
                e2.printStackTrace();
                iIntValue = -1;
            }
            if (iIntValue != -1) {
                this.decodeFormats.add(convertNumToBarcodeFormat(iIntValue));
            }
        }
    }

    @Override // io.dcloud.feature.barcode2.decoding.IBarHandler
    public boolean isRunning() {
        return this.mRunning;
    }

    public void onDestory() {
        resumeOrientationState();
        this.inactivityTimer.shutdown();
        this.hasSurface = false;
        this.decodeFormats = null;
        this.characterSet = null;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.nopermission) {
            TextPaint textPaint = new TextPaint();
            textPaint.setColor(-1);
            textPaint.setTextSize(PdrUtil.pxFromDp(18.0f, this.context.getResources().getDisplayMetrics()));
            textPaint.setTextAlign(Paint.Align.CENTER);
            Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
            int i = (int) (((this.viewHeight / 2.0f) - (fontMetrics.top / 2.0f)) - (fontMetrics.bottom / 2.0f));
            int i2 = this.viewWidth / 2;
            if ((Build.VERSION.SDK_INT >= 24 ? getResources().getConfiguration().getLocales().get(0).getLanguage() : getResources().getConfiguration().locale.getLanguage()).equalsIgnoreCase(LocaleManager.DEFAULT_LANGUAGE)) {
                canvas.drawText("Need camera permission", i2, i, textPaint);
            } else {
                canvas.drawText("未获得相机权限", i2, i, textPaint);
            }
        }
    }

    public void onPause() {
        CaptureActivityHandler captureActivityHandler = this.handler;
        if (captureActivityHandler != null) {
            captureActivityHandler.quitSynchronously();
            this.handler = null;
        }
        if (!this.nopermission) {
            CameraManager.get().closeDriver();
        }
        boolean z = this.mRunning;
        cancel();
        this.mRunning = z;
    }

    public void onResume(boolean z) {
        if (this.lastBiptmap != null && this.isCancelScan && z) {
            this.surfaceView.setBackgroundDrawable(new BitmapDrawable(this.context.getResources(), this.lastBiptmap));
        }
        if (!this.hasSurface) {
            this.surfaceView.setSurfaceTextureListener(this);
        }
        if (((AudioManager) this.context.getSystemService("audio")).getRingerMode() != 2) {
            this.playBeep = false;
        }
        initBeepSound();
        if (z && this.mRunning) {
            this.mRunning = false;
            start();
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.isSurfaceAvaliable = true;
        if (this.hasSurface) {
            return;
        }
        this.hasSurface = true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.hasSurface = false;
        return false;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void setAutoDecodeCharset(boolean z) {
        this.autoDecodeCharset = z;
    }

    public void setBackground(int i) {
        if (i == -1) {
            i = DetectorViewConfig.laserColor;
        }
        setBackgroundColor(i);
    }

    public void setConserve(boolean z) {
        this.mConserve = z;
    }

    public void setFilename(String str) {
        this.mFilename = str;
    }

    public void setFlash(boolean z) {
        CameraManager.get().setFlashlight(z);
    }

    public void setFrameColor(int i) {
        if (i == -1) {
            i = DetectorViewConfig.laserColor;
        }
        DetectorViewConfig.cornerColor = i;
    }

    public void setPlayBeep(boolean z) {
        this.playBeep = z;
    }

    public void setScanBarColor(int i) {
        if (i == -1) {
            i = DetectorViewConfig.laserColor;
        }
        DetectorViewConfig.laserColor = i;
    }

    public void setVibrate(boolean z) {
        this.vibrate = z;
    }

    public void start() {
        PermissionUtil.useSystemPermissions((Activity) this.context, new String[]{"android.permission.CAMERA"}, new PermissionUtil.Request() { // from class: io.dcloud.feature.weex_barcode.BarcodeView.4
            @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
            public void onDenied(String str) {
                BarcodeView.this.nopermission = true;
                BarcodeView.this.setBackground(-16777216);
                BarcodeView.this.invalidate();
            }

            @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
            public void onGranted(String str) {
                BarcodeView.this.postDelayed(new Runnable() { // from class: io.dcloud.feature.weex_barcode.BarcodeView.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (!BarcodeView.this.isSurfaceAvaliable) {
                            BarcodeView.this.postDelayed(this, 100L);
                            return;
                        }
                        AbsoluteLayout.LayoutParams layoutParams = BarcodeView.this.setLayoutParams();
                        if (layoutParams == null) {
                            return;
                        }
                        Message message = new Message();
                        message.what = 203;
                        message.obj = layoutParams;
                        BarcodeView.this.mHandler.sendMessage(message);
                    }
                }, BarcodeView.this.isSurfaceAvaliable ? 0L : 200L);
            }
        });
    }

    public void updateStyles(int i, int i2) {
        if (this.viewHeight == i2 && this.viewWidth == i) {
            return;
        }
        this.viewWidth = i;
        this.viewHeight = i2;
        ThreadPool.self().addThreadTask(new Runnable() { // from class: io.dcloud.feature.weex_barcode.BarcodeView.3
            @Override // java.lang.Runnable
            public void run() {
                AbsoluteLayout.LayoutParams layoutParams = BarcodeView.this.setLayoutParams();
                if (layoutParams == null) {
                    return;
                }
                Message message = new Message();
                message.what = 202;
                message.obj = layoutParams;
                BarcodeView.this.mHandler.sendMessage(message);
            }
        });
    }
}
