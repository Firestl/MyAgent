package io.dcloud.feature.weex_livepusher;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.LinearLayout;
import com.dmcbig.mediapicker.utils.FileUtils;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.adapter.IWXUserTrackAdapter;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.Constants;
import com.taobao.weex.ui.component.WXComponent;
import com.tencent.rtmp.ITXLivePushListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePushConfig;
import com.tencent.rtmp.TXLivePusher;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.umeng.commonsdk.internal.utils.f;
import io.dcloud.application.DCLoudApplicationImpl;
import io.dcloud.common.adapter.util.PermissionUtil;
import io.dcloud.common.constant.AbsoluteConst;
import io.dcloud.common.util.PdrUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class TCPusherView extends LinearLayout implements ITXLivePushListener, TXLivePusher.OnBGMNotify {
    public String BGMPath;
    public boolean autoPush;
    public int beautyLevel;
    public boolean cameraType;
    public WXComponent component;
    public boolean isAutoFocus;
    public boolean isPreview;

    @SuppressLint({"HandlerLeak"})
    public Handler mHandler;
    public WXSDKInstance mInstance;
    public TXLivePushConfig mLivePushConfig;
    public TXLivePusher mLivePusher;
    public String mSrc;
    public List<String> permissions;
    public TXCloudVideoView pusherView;
    public boolean torchIsOn;
    public int videoQulity;
    public int videoResolution;
    public int whiteLevel;

    public TCPusherView(Context context, WXComponent wXComponent, boolean z) {
        super(context);
        this.cameraType = true;
        this.videoQulity = 6;
        this.beautyLevel = 0;
        this.whiteLevel = 0;
        this.mHandler = new Handler() { // from class: io.dcloud.feature.weex_livepusher.TCPusherView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what == 1) {
                    TCPusherView.this.init();
                }
            }
        };
        this.isPreview = false;
        this.torchIsOn = false;
        this.permissions = new ArrayList();
        this.component = wXComponent;
        this.mInstance = wXComponent.getInstance();
        TXLivePushConfig tXLivePushConfig = new TXLivePushConfig();
        this.mLivePushConfig = tXLivePushConfig;
        tXLivePushConfig.setVideoEncodeGop(5);
        this.mLivePushConfig.enableNearestIP(false);
        this.mLivePushConfig.setFrontCamera(z);
        this.cameraType = z;
        if (!z) {
            this.mLivePushConfig.setTouchFocus(false);
        }
        this.mLivePushConfig.setPauseFlag(3);
        this.mLivePusher = new TXLivePusher(this.component.getContext());
        init();
    }

    private void changeSrc(String str) {
        this.mLivePushConfig.setCustomModeType(0);
        this.mLivePushConfig.setPauseImg(300, 5);
        this.mLivePusher.startPusher(str);
    }

    private Bitmap decodeResource(String str) {
        return BitmapFactory.decodeFile(str, new BitmapFactory.Options());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fireEvent(String str, Map<String, Object> map) {
        if (this.component.containsEvent(str)) {
            this.component.fireEvent(str, map);
        }
    }

    public void autoFocus(boolean z) {
        if (this.cameraType) {
            return;
        }
        this.isAutoFocus = z;
        this.mLivePushConfig.setTouchFocus(z);
    }

    public void destory() {
        this.pusherView.stop(true);
        this.mLivePusher.setPushListener(null);
        this.mLivePusher.setBGMNofify(null);
    }

    public void enableCamera(boolean z) {
        this.mLivePushConfig.enablePureAudioPush(!z);
    }

    public void init() {
        this.mLivePusher.setConfig(this.mLivePushConfig);
        this.mLivePusher.setPushListener(this);
        this.mLivePusher.setBGMNofify(this);
        this.mLivePusher.setVideoQuality(this.videoQulity, false, false);
        ((Activity) getContext()).getWindow().addFlags(128);
        TXCloudVideoView tXCloudVideoView = new TXCloudVideoView(getContext());
        this.pusherView = tXCloudVideoView;
        addView(tXCloudVideoView, new LinearLayout.LayoutParams(-1, -1));
        PermissionUtil.requestSystemPermissions((Activity) this.mInstance.getContext(), new String[]{"android.permission.RECORD_AUDIO", "android.permission.CAMERA"}, PermissionUtil.getRequestCode(), new PermissionUtil.Request() { // from class: io.dcloud.feature.weex_livepusher.TCPusherView.2
            @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
            public void onDenied(String str) {
                HashMap map = new HashMap();
                if (str.equals(PermissionUtil.PMS_CAMERA)) {
                    map.put(IWXUserTrackAdapter.MONITOR_ERROR_CODE, 10001);
                    map.put(IWXUserTrackAdapter.MONITOR_ERROR_MSG, DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_weex_livepusher_msg_user_permission_loss_camera));
                } else if (str.equals(PermissionUtil.PMS_RECORD)) {
                    map.put(IWXUserTrackAdapter.MONITOR_ERROR_CODE, 10002);
                    map.put(IWXUserTrackAdapter.MONITOR_ERROR_MSG, DCLoudApplicationImpl.self().getContext().getString(R.string.dcloud_feature_weex_livepusher_msg_user_permission_loss_record));
                }
                HashMap map2 = new HashMap(1);
                map2.put("detail", map);
                TCPusherView.this.fireEvent("error", map2);
            }

            @Override // io.dcloud.common.adapter.util.PermissionUtil.Request
            public void onGranted(String str) throws Throwable {
                TCPusherView.this.permissions.add(str);
                if (TCPusherView.this.permissions.size() == 2 && TCPusherView.this.autoPush) {
                    if (TCPusherView.this.mSrc == null) {
                        TCPusherView tCPusherView = TCPusherView.this;
                        tCPusherView.mSrc = (String) tCPusherView.component.getAttrs().get("url");
                    }
                    TCPusherView.this.start(null);
                }
            }
        });
    }

    @Override // com.tencent.rtmp.TXLivePusher.OnBGMNotify
    public void onBGMComplete(int i) {
        fireEvent("bgmcomplete", new HashMap());
    }

    @Override // com.tencent.rtmp.TXLivePusher.OnBGMNotify
    public void onBGMProgress(long j, long j2) {
        HashMap map = new HashMap();
        map.put(AbsoluteConst.JSON_KEY_PROGRESS, Long.valueOf(j));
        map.put("duration", Long.valueOf(j2));
        HashMap map2 = new HashMap(1);
        map2.put("detail", map);
        fireEvent("bgmprogress", map2);
    }

    @Override // com.tencent.rtmp.TXLivePusher.OnBGMNotify
    public void onBGMStart() {
        fireEvent("bgmstart", new HashMap());
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (PdrUtil.isEmpty(this.component.getStyles().getBackgroundColor())) {
            setBackgroundColor(-16777216);
        }
    }

    @Override // com.tencent.rtmp.ITXLivePushListener
    public void onNetStatus(Bundle bundle) {
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        try {
            map2.put("videoBitrate", Integer.valueOf(bundle.getInt(TXLiveConstants.NET_STATUS_VIDEO_BITRATE)));
            map2.put("audioBitrate", Integer.valueOf(bundle.getInt(TXLiveConstants.NET_STATUS_AUDIO_BITRATE)));
            map2.put("videoFPS", Integer.valueOf(bundle.getInt(TXLiveConstants.NET_STATUS_VIDEO_FPS)));
            map2.put("videoGOP", Integer.valueOf(bundle.getInt(TXLiveConstants.NET_STATUS_VIDEO_GOP)));
            map2.put("netSpeed", Integer.valueOf(bundle.getInt(TXLiveConstants.NET_STATUS_NET_SPEED)));
            map2.put("netJitter", 0);
            map2.put("videoWidth", Integer.valueOf(bundle.getInt(TXLiveConstants.NET_STATUS_VIDEO_WIDTH)));
            map2.put("videoHeight", Integer.valueOf(bundle.getInt(TXLiveConstants.NET_STATUS_VIDEO_HEIGHT)));
        } catch (Exception unused) {
        }
        map.put(f.f5404a, map2);
        HashMap map3 = new HashMap(1);
        map3.put("detail", map);
        fireEvent("netstatus", map3);
    }

    @Override // com.tencent.rtmp.ITXLivePushListener
    public void onPushEvent(int i, Bundle bundle) {
        HashMap map = new HashMap();
        map.put("code", Integer.valueOf(i));
        String string = "";
        if (bundle != null && bundle.getString(TXLiveConstants.EVT_DESCRIPTION) != null) {
            string = bundle.getString(TXLiveConstants.EVT_DESCRIPTION);
        }
        map.put("message", string);
        HashMap map2 = new HashMap(1);
        map2.put("detail", map);
        fireEvent("statechange", map2);
    }

    public void pause(JSCallback jSCallback) {
        this.pusherView.onPause();
        this.mLivePusher.pausePusher();
        HashMap map = new HashMap();
        map.put("type", "success");
        if (jSCallback != null) {
            jSCallback.invoke(map);
        }
    }

    public void pauseBGM(JSCallback jSCallback) {
        if (this.mLivePusher.pauseBGM()) {
            HashMap map = new HashMap();
            map.put("type", "success");
            if (jSCallback != null) {
                jSCallback.invoke(map);
                return;
            }
            return;
        }
        HashMap map2 = new HashMap();
        map2.put("type", Constants.Event.FAIL);
        if (jSCallback != null) {
            jSCallback.invoke(map2);
        }
    }

    public void playBGM(String str, JSCallback jSCallback) {
        HashMap map = new HashMap();
        if (PdrUtil.isEmpty(str)) {
            map.put("type", Constants.Event.FAIL);
            if (jSCallback != null) {
                jSCallback.invoke(map);
                return;
            }
            return;
        }
        if (PdrUtil.isNetPath(str)) {
            this.BGMPath = str;
        } else {
            this.BGMPath = this.mInstance.rewriteUri(Uri.parse(str), "image").getPath();
        }
        if (this.mLivePusher.playBGM(this.BGMPath)) {
            map.put("type", "success");
            if (jSCallback != null) {
                jSCallback.invoke(map);
                return;
            }
            return;
        }
        map.put("type", Constants.Event.FAIL);
        if (jSCallback != null) {
            jSCallback.invoke(map);
        }
    }

    public void preview(JSCallback jSCallback) throws Throwable {
        this.mLivePushConfig.setFrontCamera(this.cameraType);
        this.mLivePusher.setConfig(this.mLivePushConfig);
        this.isPreview = true;
        this.mLivePusher.startCameraPreview(this.pusherView);
        HashMap map = new HashMap();
        map.put("type", "success");
        if (jSCallback != null) {
            jSCallback.invoke(map);
        }
    }

    public void resume(JSCallback jSCallback) {
        this.pusherView.onResume();
        this.mLivePusher.resumePusher();
        HashMap map = new HashMap();
        map.put("type", "success");
        if (jSCallback != null) {
            jSCallback.invoke(map);
        }
    }

    public void resumeBGM(JSCallback jSCallback) {
        if (this.mLivePusher.resumeBGM()) {
            HashMap map = new HashMap();
            map.put("type", "success");
            if (jSCallback != null) {
                jSCallback.invoke(map);
                return;
            }
            return;
        }
        HashMap map2 = new HashMap();
        map2.put("type", Constants.Event.FAIL);
        if (jSCallback != null) {
            jSCallback.invoke(map2);
        }
    }

    public void sCamera(JSCallback jSCallback) {
        HashMap map = new HashMap();
        this.cameraType = !this.cameraType;
        this.mLivePusher.switchCamera();
        map.put("type", "success");
        if (jSCallback != null) {
            jSCallback.invoke(map);
        }
    }

    public void setAutoPush(boolean z) {
        this.autoPush = z;
    }

    public void setBGMute(boolean z) {
        this.mLivePushConfig.setPauseFlag(3);
    }

    public void setBGNVolume(int i, JSCallback jSCallback) {
        if (this.mLivePusher.setBGMVolume(i)) {
            HashMap map = new HashMap();
            map.put("type", "success");
            if (jSCallback != null) {
                jSCallback.invoke(map);
                return;
            }
            return;
        }
        HashMap map2 = new HashMap();
        map2.put("type", Constants.Event.FAIL);
        if (jSCallback != null) {
            jSCallback.invoke(map2);
        }
    }

    public void setBeauty(int i) {
        if (i < 0) {
            i = 0;
        } else if (i > 9) {
            i = 9;
        }
        this.beautyLevel = i;
        this.mLivePushConfig.setBeautyFilter(i, this.whiteLevel, 0);
        this.mLivePusher.setBeautyFilter(0, this.beautyLevel, this.whiteLevel, 0);
    }

    public void setMaxBitrate(int i) {
        this.mLivePushConfig.setMaxVideoBitrate(i);
    }

    public void setMinBitrate(int i) {
        this.mLivePushConfig.setMinVideoBitrate(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setMode(java.lang.String r6) {
        /*
            r5 = this;
            int r0 = r6.hashCode()
            r1 = 2300(0x8fc, float:3.223E-42)
            r2 = 2
            r3 = 1
            r4 = 0
            if (r0 == r1) goto L38
            r1 = 2641(0xa51, float:3.701E-42)
            if (r0 == r1) goto L2e
            r1 = 69570(0x10fc2, float:9.7488E-41)
            if (r0 == r1) goto L24
            r1 = 81473(0x13e41, float:1.14168E-40)
            if (r0 == r1) goto L1a
            goto L42
        L1a:
            java.lang.String r0 = "RTC"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L42
            r6 = 4
            goto L43
        L24:
            java.lang.String r0 = "FHD"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L42
            r6 = 2
            goto L43
        L2e:
            java.lang.String r0 = "SD"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L42
            r6 = 0
            goto L43
        L38:
            java.lang.String r0 = "HD"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L42
            r6 = 1
            goto L43
        L42:
            r6 = -1
        L43:
            if (r6 == 0) goto L5a
            if (r6 == r3) goto L55
            if (r6 == r2) goto L4f
            r6 = 6
            r5.videoQulity = r6
            r5.videoResolution = r4
            goto L71
        L4f:
            r6 = 3
            r5.videoQulity = r6
            r5.videoResolution = r2
            goto L71
        L55:
            r5.videoQulity = r2
            r5.videoResolution = r3
            goto L71
        L5a:
            r5.videoQulity = r3
            r5.videoResolution = r4
            com.tencent.rtmp.TXLivePushConfig r6 = r5.mLivePushConfig
            r6.setAutoAdjustBitrate(r4)
            com.tencent.rtmp.TXLivePushConfig r6 = r5.mLivePushConfig
            r0 = 700(0x2bc, float:9.81E-43)
            r6.setVideoBitrate(r0)
            com.tencent.rtmp.TXLivePusher r6 = r5.mLivePusher
            com.tencent.rtmp.TXLivePushConfig r0 = r5.mLivePushConfig
            r6.setConfig(r0)
        L71:
            com.tencent.rtmp.TXLivePusher r6 = r5.mLivePusher
            int r0 = r5.videoQulity
            r6.setVideoQuality(r0, r4, r4)
            com.tencent.rtmp.TXLivePushConfig r6 = r5.mLivePushConfig
            int r0 = r5.videoResolution
            r6.setVideoResolution(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.dcloud.feature.weex_livepusher.TCPusherView.setMode(java.lang.String):void");
    }

    public void setMute(boolean z) {
        this.mLivePusher.setMute(z);
    }

    public void setOritation(String str) {
        if (str.equals("vertical")) {
            this.mLivePushConfig.setHomeOrientation(1);
            this.mLivePusher.setRenderRotation(0);
        } else if (str.equals(Constants.Value.HORIZONTAL)) {
            this.mLivePushConfig.setHomeOrientation(0);
            this.mLivePusher.setRenderRotation(0);
        }
    }

    public void setSrc(String str) {
        if ((!PdrUtil.isEmpty(this.mSrc) || PdrUtil.isEmpty(str)) && this.mSrc.equals(str)) {
            return;
        }
        this.mSrc = str;
    }

    public void setWaintImage(String str) {
        Bitmap bitmapDecodeResource = decodeResource(this.component.getInstance().rewriteUri(Uri.parse(str), "image").getPath());
        if (bitmapDecodeResource != null) {
            this.mLivePushConfig.setPauseImg(bitmapDecodeResource);
            this.mLivePushConfig.setPauseFlag(1);
        }
    }

    public void setWhite(int i) {
        if (i < 0) {
            i = 0;
        } else if (i > 9) {
            i = 9;
        }
        this.whiteLevel = i;
        this.mLivePushConfig.setBeautyFilter(this.beautyLevel, i, 0);
        this.mLivePusher.setBeautyFilter(0, this.beautyLevel, this.whiteLevel, 0);
    }

    public void setZoom(boolean z) {
        this.mLivePushConfig.setEnableZoom(z);
    }

    public void snapShot(final JSCallback jSCallback) {
        this.mLivePusher.snapshot(new TXLivePusher.ITXSnapshotListener() { // from class: io.dcloud.feature.weex_livepusher.TCPusherView.3
            @Override // com.tencent.rtmp.TXLivePusher.ITXSnapshotListener
            public void onSnapshot(Bitmap bitmap) {
                if (bitmap == null) {
                    HashMap map = new HashMap();
                    map.put("type", Constants.Event.FAIL);
                    map.put("code", "-99");
                    map.put("message", "data error");
                    JSCallback jSCallback2 = jSCallback;
                    if (jSCallback2 != null) {
                        jSCallback2.invoke(map);
                        return;
                    }
                    return;
                }
                String path = TCPusherView.this.mInstance.rewriteUri(Uri.parse("_doc/snapshot/snapshot_" + System.currentTimeMillis() + FileUtils.JPEG_FILE_SUFFIX), "image").getPath();
                try {
                    File file = new File(path);
                    if (!file.exists()) {
                        if (!file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                        }
                        file.createNewFile();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    HashMap map2 = new HashMap();
                    HashMap map3 = new HashMap();
                    map3.put("width", Integer.valueOf(bitmap.getWidth()));
                    map3.put("height", Integer.valueOf(bitmap.getHeight()));
                    map3.put("tempImagePath", path);
                    map2.put("message", map3);
                    map2.put("type", "success");
                    map2.put("code", 0);
                    if (jSCallback != null) {
                        jSCallback.invoke(map2);
                    }
                } catch (Exception e2) {
                    HashMap map4 = new HashMap();
                    map4.put("code", "-99");
                    map4.put("message", e2.getMessage());
                    map4.put("type", Constants.Event.FAIL);
                    JSCallback jSCallback3 = jSCallback;
                    if (jSCallback3 != null) {
                        jSCallback3.invoke(map4);
                    }
                }
            }
        });
    }

    public void start(JSCallback jSCallback) throws Throwable {
        HashMap map = new HashMap();
        if (PdrUtil.isEmpty(this.mSrc)) {
            map.put("type", Constants.Event.FAIL);
            if (jSCallback != null) {
                jSCallback.invoke(map);
                return;
            }
            return;
        }
        this.mLivePushConfig.setFrontCamera(this.cameraType);
        this.mLivePushConfig.setBeautyFilter(this.beautyLevel, this.whiteLevel, 0);
        this.mLivePusher.setConfig(this.mLivePushConfig);
        if (!this.isPreview) {
            this.mLivePusher.startCameraPreview(this.pusherView);
        }
        if (this.mLivePusher.startPusher(this.mSrc) == 0) {
            map.put("type", "success");
        } else {
            map.put("type", Constants.Event.FAIL);
        }
        if (jSCallback != null) {
            jSCallback.invoke(map);
        }
    }

    public void stopBGM(JSCallback jSCallback) {
        if (this.mLivePusher.stopBGM()) {
            HashMap map = new HashMap();
            map.put("type", "success");
            if (jSCallback != null) {
                jSCallback.invoke(map);
                return;
            }
            return;
        }
        HashMap map2 = new HashMap();
        map2.put("type", Constants.Event.FAIL);
        if (jSCallback != null) {
            jSCallback.invoke(map2);
        }
    }

    public void stopPreview(JSCallback jSCallback) {
        this.isPreview = false;
        this.mLivePusher.stopCameraPreview(false);
        HashMap map = new HashMap();
        map.put("type", "success");
        if (jSCallback != null) {
            jSCallback.invoke(map);
        }
    }

    public void stopPusher(JSCallback jSCallback) {
        ((Activity) getContext()).getWindow().clearFlags(128);
        this.mLivePusher.stopBGM();
        this.mLivePusher.stopCameraPreview(false);
        this.mLivePusher.stopScreenCapture();
        this.mLivePusher.stopPusher();
        HashMap map = new HashMap();
        map.put("type", "success");
        if (jSCallback != null) {
            jSCallback.invoke(map);
        }
    }

    public void switchCamera(String str) {
        this.cameraType = str.equals("front");
        if (this.mLivePusher.isPushing()) {
            this.mLivePusher.switchCamera();
        }
        this.mLivePushConfig.setFrontCamera(this.cameraType);
        this.mLivePusher.setConfig(this.mLivePushConfig);
    }

    public void toggleTorch(JSCallback jSCallback) {
        boolean z = !this.torchIsOn;
        this.torchIsOn = z;
        if (this.mLivePusher.turnOnFlashLight(z)) {
            HashMap map = new HashMap();
            map.put("type", "success");
            if (jSCallback != null) {
                jSCallback.invoke(map);
                return;
            }
            return;
        }
        HashMap map2 = new HashMap();
        map2.put("type", Constants.Event.FAIL);
        if (jSCallback != null) {
            jSCallback.invoke(map2);
        }
    }
}
