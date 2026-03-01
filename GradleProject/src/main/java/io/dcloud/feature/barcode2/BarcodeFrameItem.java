package io.dcloud.feature.barcode2;

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
import android.os.Handler;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import com.dcloud.zxing2.BarcodeFormat;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.base.R;
import io.dcloud.common.DHInterface.IApp;
import io.dcloud.common.DHInterface.IEventCallback;
import io.dcloud.common.DHInterface.IFeature;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameItem;
import io.dcloud.common.adapter.ui.AdaFrameView;
import io.dcloud.common.adapter.util.CanvasHelper;
import io.dcloud.common.adapter.util.Logger;
import io.dcloud.common.adapter.util.MessageHandler;
import io.dcloud.common.adapter.util.PermissionUtil;
import io.dcloud.common.adapter.util.ViewOptions;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.BaseInfo;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import supwisdom.xv;

/* JADX INFO: loaded from: classes2.dex */
public class BarcodeFrameItem extends AdaFrameItem implements TextureView.SurfaceTextureListener, IBarHandler {
    public static final int AZTEC = 3;
    public static final float BEEP_VOLUME = 0.8f;
    public static final int CODABAR = 7;
    public static final int CODE128 = 10;
    public static final int CODE39 = 8;
    public static final int CODE93 = 9;
    public static final int DATAMATRIX = 4;
    public static final int EAN13 = 1;
    public static final int EAN8 = 2;
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
    public Vector<BarcodeFormat> decodeFormats;
    public String errorMsg;
    public CaptureActivityHandler handler;
    public boolean hasSurface;
    public InactivityTimer inactivityTimer;
    public boolean isCancelScan;
    public boolean isVerticalScreen;
    public Bitmap lastBitmap;
    public Context mAct;
    public IApp mAppHandler;
    public Map<String, String> mCallbackIds;
    public boolean mConserve;
    public IWebview mContainerWebview;
    public JSONArray mDivRectJson;
    public String mFilename;
    public JSONArray mFilters;
    public int mOrientationState;
    public String mPosition;
    public BarcodeProxy mProxy;
    public boolean mRunning;
    public JSONObject mStyles;
    public String mUuid;
    public IWebview mWebViewImpl;
    public MediaPlayer mediaPlayer;
    public boolean noPermission;
    public boolean playBeep;
    public TextureView surfaceView;
    public boolean vibrate;
    public ViewfinderView viewfinderView;
    public static final String TAG = BarcodeFrameItem.class.getSimpleName();
    public static BarcodeFrameItem sBarcodeFrameItem = null;

    public BarcodeFrameItem(BarcodeProxy barcodeProxy, IWebview iWebview, String str, JSONArray jSONArray, JSONArray jSONArray2, JSONObject jSONObject) {
        super(iWebview.getContext());
        this.playBeep = true;
        this.vibrate = true;
        this.mCallbackIds = null;
        this.mRunning = false;
        this.errorMsg = null;
        this.mConserve = false;
        this.mFilename = null;
        this.noPermission = false;
        this.mPosition = AbsoluteConst.JSON_VALUE_POSITION_STATIC;
        this.autoDecodeCharset = false;
        this.isVerticalScreen = true;
        this.isCancelScan = false;
        this.lastBitmap = null;
        this.beepListener = new MediaPlayer.OnCompletionListener() { // from class: io.dcloud.feature.barcode2.BarcodeFrameItem.6
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.seekTo(0);
            }
        };
        sBarcodeFrameItem = this;
        this.mProxy = barcodeProxy;
        this.mUuid = str;
        this.mCallbackIds = new HashMap();
        this.mAct = iWebview.getContext();
        this.mWebViewImpl = iWebview;
        this.mContainerWebview = iWebview;
        this.mAppHandler = iWebview.obtainApp();
        this.mDivRectJson = jSONArray;
        this.mStyles = jSONObject;
        this.mFilters = jSONArray2;
        final AbsoluteLayout.LayoutParams frameLayoutParam = getFrameLayoutParam(jSONArray, jSONObject);
        AbsoluteLayout absoluteLayout = new AbsoluteLayout(this.mAct) { // from class: io.dcloud.feature.barcode2.BarcodeFrameItem.1
            public Paint paint = new Paint();

            @Override // android.view.View
            public void onDraw(Canvas canvas) {
                super.onDraw(canvas);
                if (BarcodeFrameItem.this.noPermission) {
                    this.paint.setColor(-1);
                    this.paint.setTextSize(CanvasHelper.dip2px(BarcodeFrameItem.this.mAct, 18.0f));
                    this.paint.setTextAlign(Paint.Align.CENTER);
                    Paint.FontMetrics fontMetrics = this.paint.getFontMetrics();
                    float f = fontMetrics.top;
                    float f2 = fontMetrics.bottom;
                    AbsoluteLayout.LayoutParams layoutParams = frameLayoutParam;
                    canvas.drawText(DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_barcode2_no_camera_permission), layoutParams.width / 2, (int) (((layoutParams.height / 2) - (f / 2.0f)) - (f2 / 2.0f)), this.paint);
                }
            }
        };
        setMainView(absoluteLayout);
        if (jSONObject != null) {
            initStyles(jSONObject, absoluteLayout);
        }
        initDecodeFormats(jSONArray2);
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

    public static int convertTypestrToNum(BarcodeFormat barcodeFormat) {
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

    private AbsoluteLayout.LayoutParams getFrameLayoutParam(JSONArray jSONArray, JSONObject jSONObject) {
        int height;
        float scale = this.mContainerWebview.getScale();
        if (jSONArray.length() > 3) {
            Rect rect = DetectorViewConfig.getInstance().gatherRect;
            rect.left = PdrUtil.parseInt(JSONUtil.getString(jSONArray, 0), 0);
            rect.top = PdrUtil.parseInt(JSONUtil.getString(jSONArray, 1), 0);
            rect.right = rect.left + PdrUtil.parseInt(JSONUtil.getString(jSONArray, 2), 0);
            int i = rect.top + PdrUtil.parseInt(JSONUtil.getString(jSONArray, 3), 0);
            rect.bottom = i;
            rect.left = (int) (rect.left * scale);
            rect.top = (int) (rect.top * scale);
            rect.right = (int) (rect.right * scale);
            rect.bottom = (int) (i * scale);
            if (rect.width() == 0 || rect.height() == 0) {
                return null;
            }
            return (AbsoluteLayout.LayoutParams) AdaFrameItem.LayoutParamsUtil.createLayoutParams(rect.left, rect.top, rect.width(), rect.height());
        }
        if (jSONObject == null) {
            return null;
        }
        AdaFrameView adaFrameView = (AdaFrameView) this.mContainerWebview.obtainFrameView();
        ViewOptions viewOptionsObtainFrameOptions = adaFrameView.obtainFrameOptions();
        if (this.mPosition.equals(AbsoluteConst.JSON_VALUE_POSITION_ABSOLUTE) || (height = ((ViewGroup) adaFrameView.obtainWebviewParent().obtainMainView()).getHeight()) == 0) {
            height = viewOptionsObtainFrameOptions.height;
        }
        int iConvertToScreenInt = PdrUtil.convertToScreenInt(JSONUtil.getString(jSONObject, "left"), viewOptionsObtainFrameOptions.width, 0, scale);
        int iConvertToScreenInt2 = PdrUtil.convertToScreenInt(JSONUtil.getString(jSONObject, "top"), height, 0, scale);
        String string = JSONUtil.getString(jSONObject, "width");
        int i2 = viewOptionsObtainFrameOptions.width;
        int iConvertToScreenInt3 = PdrUtil.convertToScreenInt(string, i2, i2, scale);
        int iConvertToScreenInt4 = PdrUtil.convertToScreenInt(JSONUtil.getString(jSONObject, "height"), height, height, scale);
        Rect rect2 = DetectorViewConfig.getInstance().gatherRect;
        rect2.left = iConvertToScreenInt;
        rect2.top = iConvertToScreenInt2;
        rect2.right = iConvertToScreenInt + iConvertToScreenInt3;
        rect2.bottom = iConvertToScreenInt2 + iConvertToScreenInt4;
        if (rect2.width() == 0 || rect2.height() == 0) {
            return null;
        }
        return (AbsoluteLayout.LayoutParams) AdaFrameItem.LayoutParamsUtil.createLayoutParams(rect2.left, rect2.top, rect2.width(), rect2.height());
    }

    private void initBeepSound() {
        if (this.mediaPlayer == null) {
            getActivity().setVolumeControlStream(3);
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.mediaPlayer = mediaPlayer;
            mediaPlayer.setAudioStreamType(3);
            this.mediaPlayer.setOnCompletionListener(this.beepListener);
            try {
                AssetFileDescriptor assetFileDescriptorOpenFd = this.mAct.getResources().getAssets().openFd(AbsoluteConst.RES_BEEP);
                this.mediaPlayer.setDataSource(assetFileDescriptorOpenFd.getFileDescriptor(), assetFileDescriptorOpenFd.getStartOffset(), assetFileDescriptorOpenFd.getLength());
                assetFileDescriptorOpenFd.close();
                this.mediaPlayer.setVolume(0.8f, 0.8f);
                this.mediaPlayer.prepare();
            } catch (IOException unused) {
                this.mediaPlayer = null;
            }
        }
    }

    private void initCamera(SurfaceTexture surfaceTexture) {
        try {
            CameraManager.get().openDriver(surfaceTexture);
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
        } catch (RuntimeException e3) {
            this.errorMsg = e3.getMessage();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initCameraView(AbsoluteLayout.LayoutParams layoutParams, AbsoluteLayout absoluteLayout) {
        CameraManager.init(getActivity().getApplication(), this.mAppHandler.isVerticalScreen());
        CameraManager.sScreenWidth = this.mAppHandler.getInt(0);
        CameraManager.sScreenAllHeight = this.mAppHandler.getInt(2);
        if (getActivity().getResources().getConfiguration().orientation == 1) {
            initPortraitCameraView(layoutParams, absoluteLayout);
        } else {
            initLandScapeCameraView(layoutParams, absoluteLayout);
        }
    }

    private void initDecodeFormats(JSONArray jSONArray) {
        int i;
        this.decodeFormats = new Vector<>();
        if (jSONArray == null || jSONArray.length() == 0) {
            this.decodeFormats.add(BarcodeFormat.EAN_13);
            this.decodeFormats.add(BarcodeFormat.EAN_8);
            this.decodeFormats.add(BarcodeFormat.QR_CODE);
            return;
        }
        int length = jSONArray.length();
        for (int i2 = 0; i2 < length; i2++) {
            try {
                i = jSONArray.getInt(i2);
            } catch (JSONException e2) {
                e2.printStackTrace();
                i = -1;
            }
            if (i != -1) {
                this.decodeFormats.add(convertNumToBarcodeFormat(i));
            }
        }
    }

    private void initLandScapeCameraView(AbsoluteLayout.LayoutParams layoutParams, final AbsoluteLayout absoluteLayout) {
        int i;
        int i2;
        Rect rect = DetectorViewConfig.getInstance().gatherRect;
        Point cr = CameraManager.getCR(rect.width(), rect.height());
        if (cr == null) {
            this.noPermission = true;
            MessageHandler.sendMessage(new MessageHandler.IMessages() { // from class: io.dcloud.feature.barcode2.BarcodeFrameItem.4
                @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
                public void execute(Object obj) {
                    absoluteLayout.setBackgroundColor(-16777216);
                    absoluteLayout.invalidate();
                }
            }, null);
            return;
        }
        int i3 = layoutParams.height;
        int i4 = cr.x;
        int i5 = cr.y;
        int i6 = (i3 * i4) / i5;
        int i7 = layoutParams.width;
        if (i6 < i7) {
            int i8 = (i5 * i7) / i4;
            int i9 = (i3 - i8) / 2;
            DetectorViewConfig.detectorRectOffestTop = i9;
            DetectorViewConfig.detectorRectOffestLeft = 0;
            i6 = i7;
            i = 0;
            i3 = i8;
            i2 = i9;
        } else {
            i = (i7 - i6) / 2;
            DetectorViewConfig.detectorRectOffestLeft = i;
            DetectorViewConfig.detectorRectOffestTop = 0;
            i2 = 0;
        }
        this.surfaceView.setClickable(false);
        absoluteLayout.addView(this.surfaceView, new AbsoluteLayout.LayoutParams(i6, i3, i, i2));
        DetectorViewConfig.getInstance().initSurfaceViewRect(i, i2, i6, i3);
        absoluteLayout.addView(this.viewfinderView);
    }

    private void initPortraitCameraView(AbsoluteLayout.LayoutParams layoutParams, final AbsoluteLayout absoluteLayout) {
        int i;
        int i2;
        Rect rect = DetectorViewConfig.getInstance().gatherRect;
        Point cr = CameraManager.getCR(rect.height(), rect.width());
        if (cr == null) {
            this.noPermission = true;
            MessageHandler.sendMessage(new MessageHandler.IMessages() { // from class: io.dcloud.feature.barcode2.BarcodeFrameItem.3
                @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
                public void execute(Object obj) {
                    absoluteLayout.setBackgroundColor(-16777216);
                    absoluteLayout.invalidate();
                }
            }, null);
            return;
        }
        int i3 = layoutParams.width;
        int i4 = cr.x;
        int i5 = cr.y;
        int i6 = (i3 * i4) / i5;
        int i7 = layoutParams.height;
        if (i6 < i7) {
            int i8 = (i5 * i7) / i4;
            int i9 = (i3 - i8) / 2;
            DetectorViewConfig.detectorRectOffestLeft = i9;
            DetectorViewConfig.detectorRectOffestTop = 0;
            i6 = i7;
            i = 0;
            i3 = i8;
            i2 = i9;
        } else {
            i = (i7 - i6) / 2;
            DetectorViewConfig.detectorRectOffestTop = i;
            DetectorViewConfig.detectorRectOffestLeft = 0;
            i2 = 0;
        }
        this.surfaceView.setClickable(false);
        absoluteLayout.addView(this.surfaceView, new AbsoluteLayout.LayoutParams(i3, i6, i2, i));
        DetectorViewConfig.getInstance().initSurfaceViewRect(i2, i, i3, i6);
        absoluteLayout.addView(this.viewfinderView);
    }

    private void initStyles(JSONObject jSONObject, View view) {
        this.mStyles = jSONObject;
        DetectorViewConfig.laserColor = -65536;
        DetectorViewConfig.cornerColor = -65536;
        if (jSONObject.has("position")) {
            this.mPosition = jSONObject.optString("position");
        }
        if (!TextUtils.isEmpty(jSONObject.optString("scanbarColor"))) {
            int iStringToColor = PdrUtil.stringToColor(jSONObject.optString("scanbarColor"));
            if (iStringToColor == -1) {
                iStringToColor = DetectorViewConfig.laserColor;
            }
            DetectorViewConfig.laserColor = iStringToColor;
        }
        if (!TextUtils.isEmpty(jSONObject.optString("frameColor"))) {
            int iStringToColor2 = PdrUtil.stringToColor(jSONObject.optString("frameColor"));
            if (iStringToColor2 == -1) {
                iStringToColor2 = DetectorViewConfig.laserColor;
            }
            DetectorViewConfig.cornerColor = iStringToColor2;
        }
        if (TextUtils.isEmpty(jSONObject.optString("background"))) {
            return;
        }
        int iStringToColor3 = PdrUtil.stringToColor(jSONObject.optString("background"));
        if (iStringToColor3 == -1) {
            iStringToColor3 = DetectorViewConfig.laserColor;
        }
        view.setBackgroundColor(iStringToColor3);
    }

    private void listenHideAndShow(IWebview iWebview) {
        iWebview.obtainFrameView().addFrameViewListener(new IEventCallback() { // from class: io.dcloud.feature.barcode2.BarcodeFrameItem.5
            @Override // io.dcloud.common.DHInterface.IEventCallback
            public Object onCallBack(String str, Object obj) {
                if (PdrUtil.isEquals(str, "hide") || PdrUtil.isEquals(str, AbsoluteConst.EVENTS_WINDOW_CLOSE)) {
                    BarcodeFrameItem.this.onPause();
                    return null;
                }
                if (!PdrUtil.isEquals(str, AbsoluteConst.EVENTS_SHOW_ANIMATION_END)) {
                    return null;
                }
                BarcodeFrameItem.this.onResume(true);
                return null;
            }
        });
    }

    private void playBeepSoundAndVibrate() {
        MediaPlayer mediaPlayer;
        if (this.playBeep && (mediaPlayer = this.mediaPlayer) != null) {
            mediaPlayer.start();
        }
        if (this.vibrate) {
            try {
                ((Vibrator) this.mAct.getSystemService("vibrator")).vibrate(200L);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void resumeOrientationState() {
        this.mAppHandler.setRequestedOrientation(this.mOrientationState);
    }

    private void saveOrientationState() {
        this.mOrientationState = this.mAppHandler.getRequestedOrientation();
    }

    public void addCallBackId(String str, String str2) {
        if (this.mCallbackIds.containsKey(str)) {
            return;
        }
        this.mCallbackIds.put(str, str2);
    }

    public void appendToFrameView(AdaFrameView adaFrameView) {
        if (obtainMainView() != null && obtainMainView().getParent() != null) {
            removeMapFrameItem(this.mContainerWebview);
        }
        this.mContainerWebview = adaFrameView.obtainWebView();
        toFrameView();
    }

    @Override // io.dcloud.feature.barcode2.decoding.IBarHandler
    public void autoFocus() {
        this.handler.autoFocus();
    }

    public void cancel() {
        if (this.mRunning) {
            CaptureActivityHandler captureActivityHandler = this.handler;
            if (captureActivityHandler != null) {
                captureActivityHandler.stopDecode();
            }
            getViewfinderView().stopUpdateScreenTimer();
            this.mRunning = false;
        }
    }

    public void cancel_scan() {
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
                this.lastBitmap = byte2bitmap(lastBitmapData, cameraHandler);
            }
            CameraManager.get().closeDriver();
            this.mRunning = false;
            this.isCancelScan = true;
        }
    }

    public void close_scan() {
        dispose();
        setMainView(null);
        System.gc();
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameItem
    public void dispose() {
        super.dispose();
        Logger.d(IFeature.F_BARCODE, "dispose");
        onPause();
        DetectorViewConfig.clearData();
        this.mProxy.mBarcodeView = null;
        this.surfaceView = null;
        Bitmap bitmap = this.lastBitmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.lastBitmap.recycle();
            this.lastBitmap = null;
        }
        CameraManager.get().clearLastBitmapData();
        resumeOrientationState();
        BarcodeProxyMgr.getBarcodeProxyMgr().removeBarcodeProxy(this.mUuid);
    }

    @Override // io.dcloud.feature.barcode2.decoding.IBarHandler
    public void drawViewfinder() {
        this.viewfinderView.drawViewfinder();
    }

    @Override // io.dcloud.feature.barcode2.decoding.IBarHandler
    public Handler getHandler() {
        return this.handler;
    }

    public JSONObject getJsBarcode() {
        if (obtainMainView() == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("uuid", this.mUuid);
            jSONObject.put("filters", this.mFilters);
            jSONObject.put("options", this.mStyles);
            jSONObject.put("autoDecodeCharset", this.autoDecodeCharset);
            return jSONObject;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return jSONObject;
        }
    }

    @Override // io.dcloud.feature.barcode2.decoding.IBarHandler
    public ViewfinderView getViewfinderView() {
        return this.viewfinderView;
    }

    @Override // io.dcloud.feature.barcode2.decoding.IBarHandler
    public void handleDecode(xv xvVar, Bitmap bitmap) {
        String str;
        this.inactivityTimer.onActivity();
        playBeepSoundAndVibrate();
        boolean zSaveBitmapToFile = this.mConserve ? PdrUtil.saveBitmapToFile(bitmap, this.mFilename) : false;
        int iConvertTypestrToNum = convertTypestrToNum(xvVar.a());
        if (zSaveBitmapToFile) {
            String strObtainAppDocPath = this.mWebViewImpl.obtainFrameView().obtainApp().obtainAppDocPath();
            Logger.d("doc:" + strObtainAppDocPath);
            if (this.mFilename.startsWith(strObtainAppDocPath)) {
                this.mFilename = BaseInfo.REL_PRIVATE_DOC_DIR + this.mFilename.substring(strObtainAppDocPath.length() - 1);
            }
            String strConvert2RelPath = this.mWebViewImpl.obtainFrameView().obtainApp().convert2RelPath(this.mFilename);
            Logger.d("Filename:" + this.mFilename + ";relPath:" + strConvert2RelPath);
            str = StringUtil.format("{type:%d,message:%s,file:'%s',charSet:'%s'}", Integer.valueOf(iConvertTypestrToNum), JSONUtil.toJSONableString(xvVar.e()), strConvert2RelPath, xvVar.f);
        } else {
            str = StringUtil.format("{type:%d,message:%s,charSet:'%s'}", Integer.valueOf(iConvertTypestrToNum), JSONUtil.toJSONableString(xvVar.e()), xvVar.f);
        }
        runJsCallBack(str, JSUtil.OK, true, true);
        cancel();
    }

    @Override // io.dcloud.feature.barcode2.decoding.IBarHandler
    public boolean isRunning() {
        return this.mRunning;
    }

    public void onDestroy() {
        this.inactivityTimer.shutdown();
        this.hasSurface = false;
        this.decodeFormats = null;
        this.characterSet = null;
        this.mCallbackIds.clear();
    }

    public void onPause() {
        CaptureActivityHandler captureActivityHandler = this.handler;
        if (captureActivityHandler != null) {
            captureActivityHandler.quitSynchronously();
            this.handler = null;
        }
        if (!this.noPermission) {
            CameraManager.get().closeDriver();
        }
        boolean z = this.mRunning;
        cancel();
        this.mRunning = z;
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameItem
    public void onPopFromStack(boolean z) {
        super.onPopFromStack(z);
        if (z) {
            onPause();
        }
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameItem
    public void onPushToStack(boolean z) {
        super.onPushToStack(z);
        if (z) {
            onResume(false);
        }
    }

    public void onResume(boolean z) {
        SurfaceTexture surfaceTexture = this.surfaceView.getSurfaceTexture();
        if (this.lastBitmap != null && this.isCancelScan && z) {
            this.surfaceView.setBackground(new BitmapDrawable(this.mAct.getResources(), this.lastBitmap));
        }
        if (this.hasSurface) {
            initCamera(surfaceTexture);
        } else {
            this.surfaceView.setSurfaceTextureListener(this);
        }
        if (((AudioManager) this.mAct.getSystemService("audio")).getRingerMode() != 2) {
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
        if (this.hasSurface) {
            return;
        }
        this.hasSurface = true;
        if (this.isCancelScan) {
            return;
        }
        try {
            initCamera(surfaceTexture);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
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

    public void removeMapFrameItem(IWebview iWebview) {
        if (this.mPosition.equals(AbsoluteConst.JSON_VALUE_POSITION_ABSOLUTE)) {
            iWebview.obtainFrameView().removeFrameItem(this);
        } else {
            iWebview.removeFrameItem(this);
        }
    }

    public void runJsCallBack(String str, int i, boolean z, boolean z2) {
        for (String str2 : this.mCallbackIds.keySet()) {
            IWebview iWebviewFindWebviewByUuid = BarcodeProxyMgr.getBarcodeProxyMgr().findWebviewByUuid(this.mWebViewImpl, this.mCallbackIds.get(str2));
            if (iWebviewFindWebviewByUuid != null) {
                Deprecated_JSUtil.execCallback(iWebviewFindWebviewByUuid, str2, str, i, z, z2);
            }
        }
    }

    public void setFlash(boolean z) {
        CameraManager.get().setFlashlight(z);
    }

    public void start() {
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
            this.surfaceView.setBackground(null);
            Bitmap bitmap = this.lastBitmap;
            if (bitmap != null && !bitmap.isRecycled()) {
                this.lastBitmap.recycle();
                this.lastBitmap = null;
            }
            CameraManager.get().clearLastBitmapData();
            this.surfaceView.postInvalidate();
            initCamera(this.surfaceView.getSurfaceTexture());
        }
        this.mRunning = true;
        this.isCancelScan = false;
    }

    public void toFrameView() {
        final AbsoluteLayout absoluteLayout = (AbsoluteLayout) obtainMainView();
        final AbsoluteLayout.LayoutParams frameLayoutParam = getFrameLayoutParam(this.mDivRectJson, this.mStyles);
        this.surfaceView = new TextureView(this.mAct);
        this.viewfinderView = new ViewfinderView(this.mAct, this);
        this.hasSurface = false;
        this.inactivityTimer = new InactivityTimer(getActivity());
        PermissionUtil.usePermission(this.mContainerWebview.getActivity(), PermissionUtil.PMS_CAMERA, new PermissionUtil.StreamPermissionRequest(this.mContainerWebview.obtainApp()) { // from class: io.dcloud.feature.barcode2.BarcodeFrameItem.2
            @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
            public void onDenied(String str) {
                BarcodeFrameItem.this.noPermission = true;
                MessageHandler.sendMessage(new MessageHandler.IMessages() { // from class: io.dcloud.feature.barcode2.BarcodeFrameItem.2.1
                    @Override // io.dcloud.common.adapter.util.MessageHandler.IMessages
                    public void execute(Object obj) {
                        absoluteLayout.setBackgroundColor(-16777216);
                        absoluteLayout.invalidate();
                    }
                }, null);
            }

            @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
            public void onGranted(String str) {
                BarcodeFrameItem.this.initCameraView(frameLayoutParam, absoluteLayout);
            }
        });
        onResume(false);
        saveOrientationState();
        boolean zIsVerticalScreen = this.mAppHandler.isVerticalScreen();
        this.isVerticalScreen = zIsVerticalScreen;
        if (zIsVerticalScreen) {
            this.mAppHandler.setRequestedOrientation("portrait");
        } else {
            this.mAppHandler.setRequestedOrientation("landscape");
        }
        listenHideAndShow(this.mContainerWebview);
        if (this.mPosition.equals(AbsoluteConst.JSON_VALUE_POSITION_ABSOLUTE)) {
            this.mContainerWebview.obtainFrameView().addFrameItem(this, frameLayoutParam);
        } else {
            this.mContainerWebview.addFrameItem(this, frameLayoutParam);
        }
    }

    public void upateStyles(JSONObject jSONObject) {
        JSONUtil.combinJSONObject(this.mStyles, jSONObject);
        if (jSONObject.has("top") || jSONObject.has("left") || jSONObject.has("width") || jSONObject.has("height") || jSONObject.has("position")) {
            AbsoluteLayout.LayoutParams frameLayoutParam = getFrameLayoutParam(this.mDivRectJson, this.mStyles);
            if (!jSONObject.has("position")) {
                obtainMainView().setLayoutParams(frameLayoutParam);
                return;
            }
            String strOptString = jSONObject.optString("position");
            if (strOptString.equals(this.mPosition)) {
                return;
            }
            if (this.mPosition.equals(AbsoluteConst.JSON_VALUE_POSITION_ABSOLUTE)) {
                this.mContainerWebview.obtainFrameView().removeFrameItem(this);
                this.mContainerWebview.addFrameItem(this, frameLayoutParam);
            } else {
                this.mContainerWebview.removeFrameItem(this);
                this.mContainerWebview.obtainFrameView().addFrameItem(this, frameLayoutParam);
            }
            this.mPosition = strOptString;
        }
    }
}
