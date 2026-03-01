package io.dcloud.media.live.push;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.LinearLayout;
import com.dmcbig.mediapicker.utils.FileUtils;
import com.seu.magicfilter.utils.MagicFilterType;
import com.taobao.weex.common.Constants;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.common.DHInterface.IFrameView;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.adapter.ui.AdaFrameItem;
import io.dcloud.common.adapter.ui.AdaUniWebView;
import io.dcloud.common.adapter.util.ViewOptions;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.Deprecated_JSUtil;
import io.dcloud.common.util.JSONUtil;
import io.dcloud.common.util.JSUtil;
import io.dcloud.common.util.PdrUtil;
import io.dcloud.common.util.StringUtil;
import io.dcloud.feature.livepusher.R;
import io.dcloud.media.live.LivePusherStateListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;
import net.ossrs.yasea.SrsCameraView;
import net.ossrs.yasea.SrsEncodeHandler;
import net.ossrs.yasea.SrsPublisher;
import net.ossrs.yasea.SrsRecordHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import supwisdom.i00;

/* JADX INFO: loaded from: classes3.dex */
public class LivePusher extends AdaFrameItem implements i00.a, SrsEncodeHandler.SrsEncodeListener, SrsRecordHandler.SrsRecordListener {
    public static final String EVENT_RESULT_TEMPLATE = "{code:'%d',message:'%s'}";
    public static String EVENT_TEMPLATE = "window.__Media_Live__Push__.execCallback_LivePush('%s', %s,'%s');";
    public AbsoluteLayout.LayoutParams _lp;
    public JSONArray arr;
    public String aspect;
    public int bBeauty;
    public boolean bOpenCamera;
    public boolean bSilence;
    public int bWhiteness;
    public boolean bautoFocus;
    public int cacheCameraHeight;
    public int cacheCameraWidth;
    public SrsCameraView cameraView;
    public int curBitrate;
    public double curFps;
    public String curPusherID;
    public long curTotalSize;
    public int defaultHeight;
    public int defaultWidth;
    public HashMap<String, HashMap<String, IWebview>> eventCallBacks;
    public IWebview initWebview;
    public boolean isFromCreate;
    public boolean isInited;
    public boolean isRegisterResize;
    public LivePusherStateListener listener;
    public SrsPublisher mPublisher;
    public LinearLayout mainView;
    public String mode;
    public OrientationEventListener moel;
    public boolean onCloseing;
    public boolean onStoped;
    public JSONObject options;
    public int oritation;
    public String position;
    public int pusherHeight;
    public int pusherWidth;
    public String rtmpULR;
    public Pattern rtmpUrlPattern;
    public String startCallbackID;
    public boolean startPreview;
    public IWebview startWebview;
    public boolean stopPreview;
    public EScreenOrientation styOrientation;
    public String userId;
    public String uuid;

    public enum EScreenOrientation {
        VERTIAL,
        HORIZONTAL
    }

    public LivePusher(IWebview iWebview, JSONArray jSONArray) {
        super(iWebview.getContext());
        this.rtmpUrlPattern = Pattern.compile("^rtmp://([^/:]+)(:(\\d+))*/([^/]+)(/(.*))*$");
        this.isInited = false;
        this.onCloseing = false;
        this.onStoped = false;
        this.curPusherID = null;
        this.startCallbackID = null;
        this.startWebview = null;
        this._lp = null;
        this.options = null;
        this.position = AbsoluteConst.JSON_VALUE_POSITION_STATIC;
        this.rtmpULR = null;
        this.bautoFocus = true;
        this.bBeauty = 0;
        this.bSilence = false;
        this.bOpenCamera = true;
        this.bWhiteness = 0;
        this.aspect = "3:4";
        this.mode = "HD";
        this.pusherWidth = 0;
        this.pusherHeight = 0;
        this.styOrientation = EScreenOrientation.VERTIAL;
        this.defaultWidth = 640;
        this.defaultHeight = 480;
        this.isFromCreate = false;
        this.startPreview = false;
        this.isRegisterResize = false;
        this.stopPreview = false;
        this.oritation = 0;
        this.curFps = 30.0d;
        this.curBitrate = 200;
        this.curTotalSize = 0L;
        setUserId(jSONArray.optString(1));
        this.uuid = jSONArray.optString(0);
        this.arr = jSONArray.optJSONArray(2);
        JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(3);
        this.options = jSONObjectOptJSONObject;
        this.position = JSONUtil.getString(jSONObjectOptJSONObject, "position");
        this.mainView = new LinearLayout(iWebview.getContext()) { // from class: io.dcloud.media.live.push.LivePusher.1
            public Paint paint = new Paint();

            @Override // android.widget.LinearLayout, android.view.View
            public void onDraw(Canvas canvas) {
                super.onDraw(canvas);
            }
        };
        SrsCameraView srsCameraView = new SrsCameraView(iWebview.getContext(), null);
        this.cameraView = srsCameraView;
        srsCameraView.setCameraId(0);
        setMainView(this.mainView);
        this.eventCallBacks = new HashMap<>();
    }

    private void changeMode(int i, int i2, int i3) {
        if (this.aspect.equals("3:4")) {
            this.cacheCameraHeight = i3;
            this.cacheCameraWidth = i2;
        } else if (this.aspect.equals("9:16")) {
            this.cacheCameraHeight = i3;
            this.cacheCameraWidth = i;
        }
    }

    public static void focusOnTouch(SrsCameraView srsCameraView) {
        if (srsCameraView == null || srsCameraView.getCamera() == null) {
            return;
        }
        Camera.Parameters parameters = srsCameraView.getCamera().getParameters();
        if (!parameters.getFocusMode().equals("auto") && parameters.getSupportedFocusModes() != null && parameters.getSupportedFocusModes().contains("auto")) {
            parameters.setFocusMode("auto");
            srsCameraView.getCamera().setParameters(parameters);
        }
        srsCameraView.getCamera().cancelAutoFocus();
        srsCameraView.getCamera().autoFocus(new Camera.AutoFocusCallback() { // from class: io.dcloud.media.live.push.LivePusher.4
            @Override // android.hardware.Camera.AutoFocusCallback
            public void onAutoFocus(boolean z, Camera camera) {
                Camera.Parameters parameters2 = camera.getParameters();
                camera.cancelAutoFocus();
                if (parameters2.getFocusMode() == "continuous-picture" || parameters2.getSupportedFocusModes() == null || !parameters2.getSupportedFocusModes().contains("continuous-picture")) {
                    return;
                }
                parameters2.setFocusMode("continuous-picture");
                camera.setParameters(parameters2);
            }
        });
    }

    public static String getImageFileName() {
        return new SimpleDateFormat("/yyyy-MM-dd-HH-mm-ss-SSS").format(new Date()).concat(FileUtils.JPEG_FILE_SUFFIX);
    }

    private Rect getRect(IWebview iWebview, JSONArray jSONArray) {
        ViewOptions viewOptionsObtainFrameOptions = ((AdaFrameItem) iWebview.obtainFrameView()).obtainFrameOptions();
        float scale = iWebview.getScale();
        Rect rect = new Rect();
        String string = JSONUtil.getString(jSONArray, 2);
        int i = viewOptionsObtainFrameOptions.width;
        int iConvertToScreenInt = PdrUtil.convertToScreenInt(string, i, i, scale);
        String string2 = JSONUtil.getString(jSONArray, 3);
        int i2 = viewOptionsObtainFrameOptions.height;
        int iConvertToScreenInt2 = PdrUtil.convertToScreenInt(string2, i2, i2, scale);
        rect.left = PdrUtil.convertToScreenInt(JSONUtil.getString(jSONArray, 0), viewOptionsObtainFrameOptions.width, 0, scale);
        int iConvertToScreenInt3 = PdrUtil.convertToScreenInt(JSONUtil.getString(jSONArray, 1), viewOptionsObtainFrameOptions.height, 0, scale);
        rect.top = iConvertToScreenInt3;
        rect.right = rect.left + iConvertToScreenInt;
        rect.bottom = iConvertToScreenInt3 + iConvertToScreenInt2;
        this.pusherHeight = iConvertToScreenInt2;
        this.pusherWidth = iConvertToScreenInt;
        updateViewRect((AdaFrameItem) iWebview.obtainFrameView(), new int[]{rect.left, rect.top, rect.width(), rect.height()}, new int[]{viewOptionsObtainFrameOptions.width, viewOptionsObtainFrameOptions.height});
        return rect;
    }

    public static String saveBitmap(Bitmap bitmap, String str) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return file.getAbsolutePath();
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveImg(final Bitmap bitmap, final String str, final IWebview iWebview, final String str2) {
        new Thread(new Runnable() { // from class: io.dcloud.media.live.push.LivePusher.9
            @Override // java.lang.Runnable
            public void run() {
                Bitmap bitmapRotateBitmap = LivePusher.this.rotateBitmap(bitmap);
                Deprecated_JSUtil.execCallback(iWebview, str2, StringUtil.format("{width:\"%d\",height:\"%d\",tempImagePath:\"%s\"}", Integer.valueOf(bitmapRotateBitmap.getWidth()), Integer.valueOf(bitmapRotateBitmap.getHeight()), LivePusher.saveBitmap(bitmapRotateBitmap, str)), JSUtil.OK, true, false);
                bitmap.recycle();
                bitmapRotateBitmap.recycle();
            }
        }).start();
    }

    private void setOrientation() {
        int rotation = this.initWebview.getActivity().getWindowManager().getDefaultDisplay().getRotation();
        if (rotation == 0) {
            this.cameraView.getCamera().setDisplayOrientation(0);
            return;
        }
        if (rotation == 1) {
            this.cameraView.getCamera().setDisplayOrientation(270);
        } else if (rotation == 2) {
            this.cameraView.getCamera().setDisplayOrientation(180);
        } else {
            if (rotation != 3) {
                return;
            }
            this.cameraView.getCamera().setDisplayOrientation(90);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void setResolution(java.lang.String r7) {
        /*
            r6 = this;
            int r0 = r7.hashCode()
            r1 = 2300(0x8fc, float:3.223E-42)
            r2 = 2
            r3 = 1
            if (r0 == r1) goto L28
            r1 = 2641(0xa51, float:3.701E-42)
            if (r0 == r1) goto L1e
            r1 = 69570(0x10fc2, float:9.7488E-41)
            if (r0 == r1) goto L14
            goto L32
        L14:
            java.lang.String r0 = "FHD"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L32
            r7 = 0
            goto L33
        L1e:
            java.lang.String r0 = "SD"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L32
            r7 = 2
            goto L33
        L28:
            java.lang.String r0 = "HD"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L32
            r7 = 1
            goto L33
        L32:
            r7 = -1
        L33:
            r0 = 1080(0x438, float:1.513E-42)
            r1 = 1440(0x5a0, float:2.018E-42)
            r4 = 1920(0x780, float:2.69E-42)
            if (r7 == 0) goto L6f
            r5 = 480(0x1e0, float:6.73E-43)
            if (r7 == r3) goto L4d
            if (r7 == r2) goto L45
            r6.changeMode(r4, r1, r0)
            goto L72
        L45:
            r7 = 360(0x168, float:5.04E-43)
            r0 = 320(0x140, float:4.48E-43)
            r6.changeMode(r7, r0, r5)
            goto L72
        L4d:
            java.lang.String r7 = r6.aspect
            java.lang.String r0 = "3:4"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L5e
            r6.cacheCameraWidth = r5
            r7 = 640(0x280, float:8.97E-43)
            r6.cacheCameraHeight = r7
            goto L72
        L5e:
            java.lang.String r7 = r6.aspect
            java.lang.String r0 = "9:16"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L72
            r6.cacheCameraWidth = r5
            r7 = 720(0x2d0, float:1.009E-42)
            r6.cacheCameraHeight = r7
            goto L72
        L6f:
            r6.changeMode(r4, r1, r0)
        L72:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.media.live.push.LivePusher.setResolution(java.lang.String):void");
    }

    private void statusEventListener(String str, String str2) {
        if (this.eventCallBacks.containsKey(str)) {
            HashMap<String, IWebview> map = this.eventCallBacks.get(str);
            for (String str3 : map.keySet()) {
                if (map.get(str3) instanceof AdaUniWebView) {
                    EVENT_TEMPLATE = "plus.__Media_Live__Push__.execCallback_LivePush('%s', %s,'%s');";
                } else {
                    EVENT_TEMPLATE = "window.__Media_Live__Push__.execCallback_LivePush('%s', %s,'%s');";
                }
                map.get(str3).executeScript(StringUtil.format(EVENT_TEMPLATE, str, str2, str3));
            }
        }
    }

    public void addEventListener(IWebview iWebview, JSONArray jSONArray) {
        jSONArray.optString(1);
        String strOptString = jSONArray.optString(2);
        String strOptString2 = jSONArray.optString(3);
        HashMap<String, IWebview> map = this.eventCallBacks.get(strOptString);
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(strOptString2, iWebview);
        this.eventCallBacks.put(strOptString, map);
    }

    public void appendLivePusher(String str, IFrameView iFrameView) {
        if (obtainMainView() != null && obtainMainView().getParent() != null) {
            removeFromFrame();
        }
        IWebview iWebviewObtainWebView = iFrameView.obtainWebView();
        this.initWebview = iWebviewObtainWebView;
        Rect rect = getRect(iWebviewObtainWebView, this.arr);
        AbsoluteLayout.LayoutParams layoutParams = (AbsoluteLayout.LayoutParams) AdaFrameItem.LayoutParamsUtil.createLayoutParams(rect.left, rect.top, rect.width(), rect.height());
        this._lp = layoutParams;
        if (!this.isFromCreate) {
            this.initWebview.addFrameItem(this, layoutParams);
        } else if (AbsoluteConst.JSON_VALUE_POSITION_ABSOLUTE.equals(this.position)) {
            this.initWebview.obtainFrameView().addFrameItem(this, this._lp);
        } else {
            this.initWebview.addFrameItem(this, this._lp);
        }
    }

    public void destory(String str) {
        this.curPusherID = str;
        this.mPublisher.stopPublish();
        try {
            this.listener.onRtmpStopped(this.curPusherID);
        } catch (Exception unused) {
        }
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameItem
    public void dispose() {
        super.dispose();
        stop(null, null);
        SrsPublisher srsPublisher = this.mPublisher;
        if (srsPublisher != null) {
            srsPublisher.stopPublish();
        }
        destory(this.uuid);
        OrientationEventListener orientationEventListener = this.moel;
        if (orientationEventListener != null) {
            orientationEventListener.disable();
        }
    }

    public String getUserId() {
        return this.userId;
    }

    public void initLivePusher(IWebview iWebview, JSONArray jSONArray) {
        this.initWebview = iWebview;
        try {
            if (this.arr == null || this.arr.length() <= 0) {
                this.isFromCreate = true;
                JSONArray jSONArray2 = new JSONArray();
                this.arr = jSONArray2;
                jSONArray2.put(0, JSONUtil.getString(this.options, "left"));
                this.arr.put(1, JSONUtil.getString(this.options, "top"));
                this.arr.put(2, JSONUtil.getString(this.options, "width"));
                this.arr.put(3, JSONUtil.getString(this.options, "height"));
            }
            if (this.options != null) {
                setOptions(null, this.options);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        Rect rect = getRect(iWebview, this.arr);
        if (this.cacheCameraWidth < 99 || this.cacheCameraHeight < 99) {
            int iWidth = rect.width() % 2 == 0 ? rect.width() : rect.width() - 1;
            int iHeight = rect.height() % 2 == 0 ? rect.height() : rect.height() - 1;
            this.cacheCameraWidth = iWidth;
            this.cacheCameraHeight = iHeight;
        }
        if (rect.width() == 0 || rect.height() == 0) {
            return;
        }
        this._lp = (AbsoluteLayout.LayoutParams) AdaFrameItem.LayoutParamsUtil.createLayoutParams(rect.left, rect.top, rect.width(), rect.height());
        SrsCameraView srsCameraView = this.cameraView;
        if (srsCameraView == null) {
            SrsCameraView srsCameraView2 = new SrsCameraView(iWebview.getContext(), null);
            this.cameraView = srsCameraView2;
            if (srsCameraView2.getParent() == null) {
                this.mainView.addView(this.cameraView, this._lp);
            }
            SrsPublisher srsPublisher = new SrsPublisher(this.cameraView);
            this.mPublisher = srsPublisher;
            srsPublisher.setEncodeHandler(new SrsEncodeHandler(this));
            this.mPublisher.setRtmpHandler(new i00(this));
            this.mPublisher.setRecordHandler(new SrsRecordHandler(this));
        } else if (this.mPublisher == null) {
            if (srsCameraView.getParent() == null) {
                this.mainView.addView(this.cameraView, this._lp);
            }
            SrsPublisher srsPublisher2 = new SrsPublisher(this.cameraView);
            this.mPublisher = srsPublisher2;
            srsPublisher2.setEncodeHandler(new SrsEncodeHandler(this));
            this.mPublisher.setRtmpHandler(new i00(this));
            this.mPublisher.setRecordHandler(new SrsRecordHandler(this));
        }
        if (this.styOrientation == EScreenOrientation.HORIZONTAL) {
            SrsPublisher srsPublisher3 = this.mPublisher;
            AbsoluteLayout.LayoutParams layoutParams = this._lp;
            srsPublisher3.setPreviewResolution(layoutParams.width, layoutParams.height);
            this.mPublisher.setOutputResolution(this.cacheCameraWidth, this.cacheCameraHeight);
            this.mPublisher.setVideoHDMode();
        } else {
            SrsPublisher srsPublisher4 = this.mPublisher;
            AbsoluteLayout.LayoutParams layoutParams2 = this._lp;
            srsPublisher4.setPreviewResolution(layoutParams2.height, layoutParams2.width);
            this.mPublisher.setOutputResolution(this.cacheCameraHeight, this.cacheCameraWidth);
            this.mPublisher.setVideoHDMode();
        }
        this.mPublisher.startCamera();
        this.mPublisher.resumeRecord();
        if (this.bautoFocus) {
            this.cameraView.setOnTouchListener(new View.OnTouchListener() { // from class: io.dcloud.media.live.push.LivePusher.2
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    LivePusher.focusOnTouch(LivePusher.this.cameraView);
                    return false;
                }
            });
        }
        this.isInited = true;
        if (this.isFromCreate) {
            return;
        }
        iWebview.addFrameItem(this, this._lp);
    }

    @Override // net.ossrs.yasea.SrsEncodeHandler.SrsEncodeListener
    public void onEncodeIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        statusEventListener("statechange", StringUtil.format(EVENT_RESULT_TEMPLATE, 1104, illegalArgumentException.getMessage()));
    }

    @Override // net.ossrs.yasea.SrsEncodeHandler.SrsEncodeListener
    public void onNetworkResume() {
        statusEventListener("statechange", StringUtil.format(EVENT_RESULT_TEMPLATE, 1102, "onNetworkResume"));
    }

    @Override // net.ossrs.yasea.SrsEncodeHandler.SrsEncodeListener
    public void onNetworkWeak() {
    }

    @Override // net.ossrs.yasea.SrsRecordHandler.SrsRecordListener
    public void onRecordFinished(String str) {
    }

    @Override // net.ossrs.yasea.SrsRecordHandler.SrsRecordListener
    public void onRecordIOException(IOException iOException) {
        statusEventListener("statechange", StringUtil.format(EVENT_RESULT_TEMPLATE, 3005, iOException.getMessage()));
    }

    @Override // net.ossrs.yasea.SrsRecordHandler.SrsRecordListener
    public void onRecordIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        statusEventListener("statechange", StringUtil.format(EVENT_RESULT_TEMPLATE, 1303, illegalArgumentException.getMessage()));
    }

    @Override // net.ossrs.yasea.SrsRecordHandler.SrsRecordListener
    public void onRecordPause() {
    }

    @Override // net.ossrs.yasea.SrsRecordHandler.SrsRecordListener
    public void onRecordResume() {
    }

    @Override // net.ossrs.yasea.SrsRecordHandler.SrsRecordListener
    public void onRecordStarted(String str) {
        statusEventListener("statechange", StringUtil.format(EVENT_RESULT_TEMPLATE, 1004, str));
    }

    @Override // io.dcloud.common.adapter.ui.AdaFrameItem
    public void onResize() {
        if (this.isRegisterResize) {
            return;
        }
        super.onResize();
        Rect rect = getRect(this.initWebview, this.arr);
        this.mainView.setLayoutParams((AbsoluteLayout.LayoutParams) AdaFrameItem.LayoutParamsUtil.createLayoutParams(rect.left, rect.top, rect.width(), rect.height()));
        setOrientation();
    }

    @Override // supwisdom.i00.a
    public void onRtmpAudioBitrateChanged(double d) {
        this.curBitrate = (int) d;
        statusEventListener("netstatus", StringUtil.format("{fps:%.0f,bitrate:%d,totalsize:%d}", Double.valueOf(this.curFps), Integer.valueOf(this.curBitrate), Long.valueOf(this.curTotalSize)));
    }

    @Override // supwisdom.i00.a
    public void onRtmpAudioStreaming() {
    }

    @Override // supwisdom.i00.a
    public void onRtmpConnected(String str) {
        statusEventListener("statechange", StringUtil.format(EVENT_RESULT_TEMPLATE, 1002, str));
        String str2 = this.startCallbackID;
        if (str2 != null) {
            JSUtil.execCallback(this.startWebview, str2, "", JSUtil.OK, false);
            this.startCallbackID = null;
            this.startWebview = null;
        }
    }

    @Override // supwisdom.i00.a
    public void onRtmpConnecting(String str) {
        statusEventListener("statechange", StringUtil.format(EVENT_RESULT_TEMPLATE, 1001, str));
    }

    @Override // supwisdom.i00.a
    public void onRtmpDisconnected() {
        statusEventListener("statechange", StringUtil.format(EVENT_RESULT_TEMPLATE, 3004, "RtmpDisconnected"));
    }

    @Override // supwisdom.i00.a
    public void onRtmpIOException(IOException iOException) {
        statusEventListener("statechange", StringUtil.format(EVENT_RESULT_TEMPLATE, 3003, iOException.toString()));
    }

    @Override // supwisdom.i00.a
    public void onRtmpIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        statusEventListener("statechange", StringUtil.format(EVENT_RESULT_TEMPLATE, 3004, illegalArgumentException.toString()));
    }

    @Override // supwisdom.i00.a
    public void onRtmpIllegalStateException(IllegalStateException illegalStateException) {
        statusEventListener("statechange", StringUtil.format(EVENT_RESULT_TEMPLATE, 3005, illegalStateException.toString()));
    }

    @Override // supwisdom.i00.a
    public void onRtmpSocketException(SocketException socketException) {
        statusEventListener("statechange", StringUtil.format(EVENT_RESULT_TEMPLATE, 3002, socketException.toString()));
    }

    @Override // supwisdom.i00.a
    public void onRtmpStopped() {
        statusEventListener("statechange", StringUtil.format(EVENT_RESULT_TEMPLATE, 1001, "onRtmpStopped"));
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: io.dcloud.media.live.push.LivePusher.10
            @Override // java.lang.Runnable
            public void run() {
                if (LivePusher.this.onStoped) {
                    try {
                        if (!LivePusher.this.stopPreview) {
                            LivePusher.this.cameraView.setBackgroundColor(-16777216);
                        }
                        LivePusher.this.onStoped = false;
                    } catch (Exception unused) {
                    }
                }
                try {
                    LivePusher.this.listener.onRtmpStopped(LivePusher.this.curPusherID);
                    LivePusher.this.cameraView.setBackgroundColor(-16777216);
                } catch (Exception unused2) {
                }
            }
        });
    }

    @Override // supwisdom.i00.a
    public void onRtmpVideoBitrateChanged(double d) {
        this.curBitrate = (int) d;
        statusEventListener("netstatus", StringUtil.format("{fps:%.0f,bitrate:%d,totalsize:%d}", Double.valueOf(this.curFps), Integer.valueOf(this.curBitrate), Long.valueOf(this.curTotalSize)));
    }

    @Override // supwisdom.i00.a
    public void onRtmpVideoFpsChanged(double d) {
        this.curFps = d;
        statusEventListener("netstatus", StringUtil.format("{fps:%.0f,bitrate:%d,totalsize:%d}", Double.valueOf(d), Integer.valueOf(this.curBitrate), Long.valueOf(this.curTotalSize)));
    }

    @Override // supwisdom.i00.a
    public void onRtmpVideoStreaming() {
    }

    public void pause(IWebview iWebview, JSONArray jSONArray) {
        SrsPublisher srsPublisher = this.mPublisher;
        if (srsPublisher != null) {
            srsPublisher.pausePublish();
        }
    }

    public void preview(IWebview iWebview) {
        if (!this.isInited) {
            initLivePusher(iWebview, null);
        }
        this.startPreview = true;
        if (this.bOpenCamera) {
            this.cameraView.setBackgroundColor(0);
            this.mPublisher.startCamera();
        }
    }

    public void removeFromFrame() {
        if (AbsoluteConst.JSON_VALUE_POSITION_ABSOLUTE.equals(this.position)) {
            this.initWebview.obtainFrameView().removeFrameItem(this);
        } else {
            this.initWebview.removeFrameItem(this);
        }
    }

    public void resize(IWebview iWebview, JSONArray jSONArray) {
        Rect rect = getRect(iWebview, jSONArray.optJSONArray(1));
        this.mainView.setLayoutParams((AbsoluteLayout.LayoutParams) AdaFrameItem.LayoutParamsUtil.createLayoutParams(rect.left, rect.top, rect.width(), rect.height()));
        this.isRegisterResize = true;
    }

    public void resume(IWebview iWebview, JSONArray jSONArray) {
        SrsPublisher srsPublisher = this.mPublisher;
        if (srsPublisher != null) {
            try {
                srsPublisher.resumePublish();
            } catch (Exception unused) {
            }
        }
    }

    public Bitmap rotateBitmap(Bitmap bitmap) {
        if (this.cameraView.getCameraId() == 0) {
            this.oritation -= 180;
        }
        if (this.oritation == 0 || bitmap == null) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(this.oritation, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (bitmap != null) {
            bitmap.recycle();
        }
        return bitmapCreateBitmap;
    }

    public void setOptions(IWebview iWebview, JSONObject jSONObject) {
        this.options = JSONUtil.combinJSONObject(this.options, jSONObject);
        if (jSONObject.has("top") || jSONObject.has("left") || jSONObject.has("width") || jSONObject.has("height") || jSONObject.has("position")) {
            String string = JSONUtil.getString(jSONObject, "position");
            try {
                this.arr.put(0, JSONUtil.getString(jSONObject, "left"));
                this.arr.put(1, JSONUtil.getString(jSONObject, "top"));
                this.arr.put(2, JSONUtil.getString(jSONObject, "width"));
                this.arr.put(3, JSONUtil.getString(jSONObject, "height"));
            } catch (JSONException unused) {
            }
            Rect rect = getRect(this.initWebview, this.arr);
            this._lp = (AbsoluteLayout.LayoutParams) AdaFrameItem.LayoutParamsUtil.createLayoutParams(rect.left, rect.top, rect.width(), rect.height());
            if (jSONObject.has("position")) {
                if (string.equals(this.position)) {
                    obtainMainView().setLayoutParams(this._lp);
                } else if (AbsoluteConst.JSON_VALUE_POSITION_ABSOLUTE.equals(this.position)) {
                    this.initWebview.obtainFrameView().removeFrameItem(this);
                    this.initWebview.addFrameItem(this, this._lp);
                } else if (AbsoluteConst.JSON_VALUE_POSITION_STATIC.equals(this.position)) {
                    this.initWebview.removeFrameItem(this);
                    this.initWebview.obtainFrameView().addFrameItem(this, this._lp);
                }
                this.position = string;
            } else {
                obtainMainView().setLayoutParams(this._lp);
            }
        }
        JSONObject jSONObject2 = this.options;
        if (jSONObject2 != null) {
            this.rtmpULR = jSONObject2.optString("url");
            this.bSilence = this.options.optBoolean("mute", false);
            boolean zOptBoolean = this.options.optBoolean("enable-camera", true);
            this.bOpenCamera = zOptBoolean;
            SrsCameraView srsCameraView = this.cameraView;
            if (srsCameraView != null) {
                if (zOptBoolean) {
                    srsCameraView.setBackgroundColor(0);
                } else {
                    srsCameraView.setBackgroundColor(-16777216);
                }
            }
            this.bautoFocus = this.options.optBoolean("auto-focus", true);
            String strOptString = this.options.optString(Constants.Name.ORIENTATION);
            if (strOptString == null || !strOptString.equalsIgnoreCase("HORIZONTAL")) {
                this.styOrientation = EScreenOrientation.VERTIAL;
            } else {
                this.styOrientation = EScreenOrientation.HORIZONTAL;
            }
            this.bBeauty = this.options.optInt("beauty", 0);
            this.bWhiteness = this.options.optInt("whiteness", 0);
            if (this.mPublisher != null) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: io.dcloud.media.live.push.LivePusher.3
                    @Override // java.lang.Runnable
                    public void run() {
                        LivePusher livePusher = LivePusher.this;
                        if (livePusher.bWhiteness < 1 && livePusher.bBeauty < 1) {
                            livePusher.mPublisher.switchCameraFilter(MagicFilterType.NONE);
                        }
                        LivePusher livePusher2 = LivePusher.this;
                        int i = livePusher2.bWhiteness;
                        if (i > 0) {
                            livePusher2.mPublisher.switchCameraFilter(MagicFilterType.WHITECAT, i);
                        }
                        LivePusher livePusher3 = LivePusher.this;
                        if (livePusher3.bBeauty > 0) {
                            livePusher3.mPublisher.switchCameraFilter(MagicFilterType.BEAUTY);
                        }
                    }
                }, 1000L);
            }
            String strOptString2 = this.options.optString("aspect", "3:4");
            this.aspect = strOptString2;
            if (strOptString2.equals("16:9")) {
                this.aspect = "9:16";
            } else {
                this.aspect = "3:4";
            }
            String upperCase = this.options.optString("mode", "FUD").toUpperCase();
            this.mode = upperCase;
            setResolution(upperCase);
        }
    }

    public void setStatusListener(LivePusherStateListener livePusherStateListener) {
        this.listener = livePusherStateListener;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public void snapshot(final IWebview iWebview, JSONArray jSONArray) {
        final String strOptString = jSONArray.optString(1);
        final String strConcat = iWebview.obtainFrameView().obtainApp().obtainAppDocPath().concat(getImageFileName());
        File parentFile = new File(strConcat).getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        this.cameraView.getCamera().takePicture(new Camera.ShutterCallback() { // from class: io.dcloud.media.live.push.LivePusher.6
            @Override // android.hardware.Camera.ShutterCallback
            public void onShutter() {
            }
        }, new Camera.PictureCallback() { // from class: io.dcloud.media.live.push.LivePusher.7
            @Override // android.hardware.Camera.PictureCallback
            public void onPictureTaken(byte[] bArr, Camera camera) {
            }
        }, new Camera.PictureCallback() { // from class: io.dcloud.media.live.push.LivePusher.8
            @Override // android.hardware.Camera.PictureCallback
            public void onPictureTaken(byte[] bArr, Camera camera) {
                int i;
                String string = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_media_livepusher_error_screen_capture);
                if (bArr != null) {
                    Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
                    if (bitmapDecodeByteArray != null) {
                        LivePusher.this.saveImg(bitmapDecodeByteArray, strConcat, iWebview, strOptString);
                        LivePusher.this.mPublisher.startCamera();
                        return;
                    } else {
                        string = DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_media_livepusher_error_screen_data_convert_fail);
                        i = 2;
                    }
                } else {
                    i = 1;
                }
                Deprecated_JSUtil.execCallback(iWebview, strOptString, StringUtil.format("{code:\"%\",message:\"\"}", Integer.valueOf(i), string), JSUtil.ERROR, false, false);
            }
        });
    }

    public void start(IWebview iWebview, JSONArray jSONArray) {
        this.startCallbackID = jSONArray.optString(1);
        this.startWebview = iWebview;
        SrsPublisher srsPublisher = this.mPublisher;
        if (srsPublisher != null) {
            boolean z = this.bOpenCamera;
            if (z) {
                boolean z2 = this.bSilence;
                if (z2) {
                    srsPublisher.setSendVideoOnly(z2);
                }
            } else {
                srsPublisher.setSendAudioOnly(z);
            }
            if (!this.rtmpUrlPattern.matcher(this.rtmpULR).matches()) {
                if (!this.startPreview) {
                    this.mPublisher.stopCamera();
                    this.cameraView.setBackgroundColor(-16777216);
                }
                try {
                    JSUtil.execCallback(iWebview, this.startCallbackID, new JSONObject("{code:1,message:'rtmp url invalable'}"), JSUtil.ERROR, false);
                    return;
                } catch (Exception unused) {
                    return;
                }
            }
            if (this.bOpenCamera) {
                this.cameraView.setBackgroundColor(0);
                this.mPublisher.startPublish(this.rtmpULR);
                this.mPublisher.startCamera();
            } else {
                this.cameraView.setBackgroundColor(-16777216);
                this.mPublisher.stopCamera();
                this.mPublisher.startAudio();
                this.mPublisher.startPublish(this.rtmpULR);
            }
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: io.dcloud.media.live.push.LivePusher.5
                @Override // java.lang.Runnable
                public void run() {
                    LivePusher livePusher = LivePusher.this;
                    if (livePusher.bWhiteness < 1 && livePusher.bBeauty < 1) {
                        livePusher.mPublisher.switchCameraFilter(MagicFilterType.NONE);
                    }
                    LivePusher livePusher2 = LivePusher.this;
                    int i = livePusher2.bWhiteness;
                    if (i > 0) {
                        livePusher2.mPublisher.switchCameraFilter(MagicFilterType.WHITECAT, i);
                    }
                    LivePusher livePusher3 = LivePusher.this;
                    if (livePusher3.bBeauty > 0) {
                        livePusher3.mPublisher.switchCameraFilter(MagicFilterType.BEAUTY);
                    }
                }
            }, 1000L);
        }
    }

    public void stop(IWebview iWebview, JSONObject jSONObject) {
        if (jSONObject != null) {
            this.stopPreview = jSONObject.optBoolean("preview", false);
        }
        SrsPublisher srsPublisher = this.mPublisher;
        if (srsPublisher != null) {
            srsPublisher.stopPublish();
            this.onStoped = true;
        }
    }

    public void switchCamera(IWebview iWebview, JSONArray jSONArray) {
        SrsPublisher srsPublisher = this.mPublisher;
        srsPublisher.switchCameraFace((srsPublisher.getCameraId() + 1) % Camera.getNumberOfCameras());
    }
}
