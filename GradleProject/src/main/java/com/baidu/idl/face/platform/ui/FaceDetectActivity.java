package com.baidu.idl.face.platform.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.media.AudioManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.aip.face.stat.Ast;
import com.baidu.idl.face.platform.FaceConfig;
import com.baidu.idl.face.platform.FaceSDKManager;
import com.baidu.idl.face.platform.FaceStatusEnum;
import com.baidu.idl.face.platform.IDetectStrategy;
import com.baidu.idl.face.platform.IDetectStrategyCallback;
import com.baidu.idl.face.platform.ui.utils.CameraUtils;
import com.baidu.idl.face.platform.ui.utils.VolumeUtils;
import com.baidu.idl.face.platform.ui.widget.FaceDetectRoundView;
import com.baidu.idl.face.platform.utils.Base64Utils;
import com.baidu.idl.face.platform.utils.CameraPreviewUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class FaceDetectActivity extends Activity implements SurfaceHolder.Callback, Camera.PreviewCallback, Camera.ErrorCallback, VolumeUtils.VolumeCallback, IDetectStrategyCallback {
    public static final String DETECT_CONFIG = "FaceOptions";
    public static final String TAG = FaceDetectActivity.class.getSimpleName();
    public Camera mCamera;
    public int mCameraId;
    public Camera.Parameters mCameraParam;
    public ImageView mCloseView;
    public FaceConfig mFaceConfig;
    public FaceDetectRoundView mFaceDetectRoundView;
    public FrameLayout mFrameLayout;
    public IDetectStrategy mIDetectStrategy;
    public LinearLayout mImageLayout;
    public int mPreviewDegree;
    public int mPreviewHight;
    public int mPreviewWidth;
    public View mRootView;
    public ImageView mSoundView;
    public ImageView mSuccessView;
    public SurfaceHolder mSurfaceHolder;
    public SurfaceView mSurfaceView;
    public TextView mTipsBottomView;
    public Drawable mTipsIcon;
    public TextView mTipsTopView;
    public BroadcastReceiver mVolumeReceiver;
    public Rect mPreviewRect = new Rect();
    public int mDisplayWidth = 0;
    public int mDisplayHeight = 0;
    public int mSurfaceWidth = 0;
    public int mSurfaceHeight = 0;
    public volatile boolean mIsEnableSound = true;
    public HashMap<String, String> mBase64ImageMap = new HashMap<>();
    public boolean mIsCreateSurface = false;
    public volatile boolean mIsCompletion = false;

    /* JADX INFO: renamed from: com.baidu.idl.face.platform.ui.FaceDetectActivity$3, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        public static final /* synthetic */ int[] $SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum;

        static {
            int[] iArr = new int[FaceStatusEnum.values().length];
            $SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum = iArr;
            try {
                iArr[FaceStatusEnum.OK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum[FaceStatusEnum.Detect_PitchOutOfUpMaxRange.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum[FaceStatusEnum.Detect_PitchOutOfDownMaxRange.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum[FaceStatusEnum.Detect_PitchOutOfLeftMaxRange.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum[FaceStatusEnum.Detect_PitchOutOfRightMaxRange.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static Bitmap base64ToBitmap(String str) {
        byte[] bArrDecode = Base64Utils.decode(str, 2);
        return BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int displayOrientation(android.content.Context r4) {
        /*
            r3 = this;
            java.lang.String r0 = "window"
            java.lang.Object r4 = r4.getSystemService(r0)
            android.view.WindowManager r4 = (android.view.WindowManager) r4
            android.view.Display r4 = r4.getDefaultDisplay()
            int r4 = r4.getRotation()
            r0 = 1
            r1 = 0
            if (r4 == 0) goto L26
            if (r4 == r0) goto L23
            r2 = 2
            if (r4 == r2) goto L20
            r2 = 3
            if (r4 == r2) goto L1d
            goto L26
        L1d:
            r4 = 270(0x10e, float:3.78E-43)
            goto L27
        L20:
            r4 = 180(0xb4, float:2.52E-43)
            goto L27
        L23:
            r4 = 90
            goto L27
        L26:
            r4 = 0
        L27:
            int r1 = r1 - r4
            int r1 = r1 + 360
            int r1 = r1 % 360
            boolean r2 = com.baidu.idl.face.platform.utils.APIUtils.hasGingerbread()
            if (r2 == 0) goto L51
            android.hardware.Camera$CameraInfo r1 = new android.hardware.Camera$CameraInfo
            r1.<init>()
            int r2 = r3.mCameraId
            android.hardware.Camera.getCameraInfo(r2, r1)
            int r2 = r1.facing
            if (r2 != r0) goto L4a
            int r0 = r1.orientation
            int r0 = r0 + r4
            int r0 = r0 % 360
            int r4 = 360 - r0
            int r1 = r4 % 360
            goto L51
        L4a:
            int r0 = r1.orientation
            int r0 = r0 - r4
            int r0 = r0 + 360
            int r1 = r0 % 360
        L51:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.idl.face.platform.ui.FaceDetectActivity.displayOrientation(android.content.Context):int");
    }

    private void onRefreshSuccessView(boolean z) {
        if (this.mSuccessView.getTag() == null) {
            Rect faceRoundRect = this.mFaceDetectRoundView.getFaceRoundRect();
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mSuccessView.getLayoutParams();
            layoutParams.setMargins(faceRoundRect.centerX() - (this.mSuccessView.getWidth() / 2), faceRoundRect.top - (this.mSuccessView.getHeight() / 2), 0, 0);
            this.mSuccessView.setLayoutParams(layoutParams);
            this.mSuccessView.setTag("setlayout");
        }
        this.mSuccessView.setVisibility(z ? 0 : 4);
    }

    private void onRefreshTipsView(boolean z, String str) {
        if (!z) {
            this.mTipsTopView.setBackgroundResource(R.drawable.bg_tips_no);
            this.mTipsTopView.setCompoundDrawables(null, null, null, null);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.mTipsTopView.setText(str);
            return;
        }
        if (this.mTipsIcon == null) {
            Drawable drawable = getResources().getDrawable(R.mipmap.ic_warning);
            this.mTipsIcon = drawable;
            drawable.setBounds(0, 0, (int) (drawable.getMinimumWidth() * 0.7f), (int) (this.mTipsIcon.getMinimumHeight() * 0.7f));
            this.mTipsTopView.setCompoundDrawablePadding(15);
        }
        this.mTipsTopView.setBackgroundResource(R.drawable.bg_tips);
        this.mTipsTopView.setText(R.string.detect_standard);
        this.mTipsTopView.setCompoundDrawables(this.mTipsIcon, null, null, null);
    }

    private void onRefreshView(FaceStatusEnum faceStatusEnum, String str) {
        int i = AnonymousClass3.$SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum[faceStatusEnum.ordinal()];
        if (i == 1) {
            onRefreshTipsView(false, str);
            this.mTipsBottomView.setText("");
            this.mFaceDetectRoundView.processDrawState(false);
            onRefreshSuccessView(true);
            return;
        }
        if (i == 2 || i == 3 || i == 4 || i == 5) {
            onRefreshTipsView(true, str);
            this.mTipsBottomView.setText(str);
            this.mFaceDetectRoundView.processDrawState(true);
            onRefreshSuccessView(false);
            return;
        }
        onRefreshTipsView(false, str);
        this.mTipsBottomView.setText("");
        this.mFaceDetectRoundView.processDrawState(true);
        onRefreshSuccessView(false);
    }

    private Camera open() {
        int numberOfCameras = Camera.getNumberOfCameras();
        if (numberOfCameras == 0) {
            return null;
        }
        int i = 0;
        while (i < numberOfCameras) {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == 1) {
                break;
            }
            i++;
        }
        if (i < numberOfCameras) {
            Camera cameraOpen = Camera.open(i);
            this.mCameraId = i;
            return cameraOpen;
        }
        Camera cameraOpen2 = Camera.open(0);
        this.mCameraId = 0;
        return cameraOpen2;
    }

    private void saveImage(HashMap<String, String> map) {
        Set<Map.Entry<String, String>> setEntrySet = map.entrySet();
        this.mImageLayout.removeAllViews();
        Iterator<Map.Entry<String, String>> it = setEntrySet.iterator();
        while (it.hasNext()) {
            Bitmap bitmapBase64ToBitmap = base64ToBitmap(it.next().getValue());
            ImageView imageView = new ImageView(this);
            imageView.setImageBitmap(bitmapBase64ToBitmap);
            this.mImageLayout.addView(imageView, new LinearLayout.LayoutParams(300, 300));
        }
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(128);
        setContentView(R.layout.activity_face_detect_v3100);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.mDisplayWidth = displayMetrics.widthPixels;
        this.mDisplayHeight = displayMetrics.heightPixels;
        FaceSDKResSettings.initializeResId();
        this.mFaceConfig = FaceSDKManager.getInstance().getFaceConfig();
        this.mIsEnableSound = ((AudioManager) getSystemService("audio")).getStreamVolume(3) > 0 ? this.mFaceConfig.isSound : false;
        View viewFindViewById = findViewById(R.id.detect_root_layout);
        this.mRootView = viewFindViewById;
        this.mFrameLayout = (FrameLayout) viewFindViewById.findViewById(R.id.detect_surface_layout);
        SurfaceView surfaceView = new SurfaceView(this);
        this.mSurfaceView = surfaceView;
        SurfaceHolder holder = surfaceView.getHolder();
        this.mSurfaceHolder = holder;
        holder.setSizeFromLayout();
        this.mSurfaceHolder.addCallback(this);
        this.mSurfaceHolder.setType(3);
        this.mSurfaceView.setLayoutParams(new FrameLayout.LayoutParams((int) (this.mDisplayWidth * 0.75f), (int) (this.mDisplayHeight * 0.75f), 17));
        this.mFrameLayout.addView(this.mSurfaceView);
        this.mRootView.findViewById(R.id.detect_close).setOnClickListener(new View.OnClickListener() { // from class: com.baidu.idl.face.platform.ui.FaceDetectActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FaceDetectActivity.this.onBackPressed();
            }
        });
        this.mFaceDetectRoundView = (FaceDetectRoundView) this.mRootView.findViewById(R.id.detect_face_round);
        this.mCloseView = (ImageView) this.mRootView.findViewById(R.id.detect_close);
        ImageView imageView = (ImageView) this.mRootView.findViewById(R.id.detect_sound);
        this.mSoundView = imageView;
        imageView.setImageResource(this.mIsEnableSound ? R.mipmap.ic_enable_sound_ext : R.mipmap.ic_disable_sound_ext);
        this.mSoundView.setOnClickListener(new View.OnClickListener() { // from class: com.baidu.idl.face.platform.ui.FaceDetectActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FaceDetectActivity.this.mIsEnableSound = !r2.mIsEnableSound;
                FaceDetectActivity faceDetectActivity = FaceDetectActivity.this;
                faceDetectActivity.mSoundView.setImageResource(faceDetectActivity.mIsEnableSound ? R.mipmap.ic_enable_sound_ext : R.mipmap.ic_disable_sound_ext);
                FaceDetectActivity faceDetectActivity2 = FaceDetectActivity.this;
                IDetectStrategy iDetectStrategy = faceDetectActivity2.mIDetectStrategy;
                if (iDetectStrategy != null) {
                    iDetectStrategy.setDetectStrategySoundEnable(faceDetectActivity2.mIsEnableSound);
                }
            }
        });
        this.mTipsTopView = (TextView) this.mRootView.findViewById(R.id.detect_top_tips);
        this.mTipsBottomView = (TextView) this.mRootView.findViewById(R.id.detect_bottom_tips);
        this.mSuccessView = (ImageView) this.mRootView.findViewById(R.id.detect_success_image);
        this.mImageLayout = (LinearLayout) this.mRootView.findViewById(R.id.detect_result_image_layout);
        HashMap<String, String> map = this.mBase64ImageMap;
        if (map != null) {
            map.clear();
        }
    }

    @Override // com.baidu.idl.face.platform.IDetectStrategyCallback
    public void onDetectCompletion(FaceStatusEnum faceStatusEnum, String str, HashMap<String, String> map) throws Throwable {
        if (this.mIsCompletion) {
            return;
        }
        onRefreshView(faceStatusEnum, str);
        if (faceStatusEnum == FaceStatusEnum.OK) {
            this.mIsCompletion = true;
            saveImage(map);
        }
        Ast.getInstance().faceHit("detect");
    }

    @Override // android.hardware.Camera.ErrorCallback
    public void onError(int i, Camera camera) {
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        stopPreview();
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        FaceDetectRoundView faceDetectRoundView;
        if (this.mIsCompletion) {
            return;
        }
        if (this.mIDetectStrategy == null && (faceDetectRoundView = this.mFaceDetectRoundView) != null && faceDetectRoundView.getRound() > 0.0f) {
            IDetectStrategy detectStrategyModule = FaceSDKManager.getInstance().getDetectStrategyModule();
            this.mIDetectStrategy = detectStrategyModule;
            detectStrategyModule.setPreviewDegree(this.mPreviewDegree);
            this.mIDetectStrategy.setDetectStrategySoundEnable(this.mIsEnableSound);
            this.mIDetectStrategy.setDetectStrategyConfig(this.mPreviewRect, FaceDetectRoundView.getPreviewDetectRect(this.mDisplayWidth, this.mPreviewHight, this.mPreviewWidth), this);
        }
        IDetectStrategy iDetectStrategy = this.mIDetectStrategy;
        if (iDetectStrategy != null) {
            iDetectStrategy.detectStrategy(bArr);
        }
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        setVolumeControlStream(3);
        this.mVolumeReceiver = VolumeUtils.registerVolumeReceiver(this, this);
        TextView textView = this.mTipsTopView;
        if (textView != null) {
            textView.setText(R.string.detect_face_in);
        }
        startPreview();
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        VolumeUtils.unRegisterVolumeReceiver(this, this.mVolumeReceiver);
        this.mVolumeReceiver = null;
        IDetectStrategy iDetectStrategy = this.mIDetectStrategy;
        if (iDetectStrategy != null) {
            iDetectStrategy.reset();
        }
        stopPreview();
    }

    public void startPreview() {
        SurfaceView surfaceView = this.mSurfaceView;
        if (surfaceView != null && surfaceView.getHolder() != null) {
            SurfaceHolder holder = this.mSurfaceView.getHolder();
            this.mSurfaceHolder = holder;
            holder.addCallback(this);
        }
        if (this.mCamera == null) {
            try {
                this.mCamera = open();
            } catch (RuntimeException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        Camera camera = this.mCamera;
        if (camera == null) {
            return;
        }
        if (this.mCameraParam == null) {
            this.mCameraParam = camera.getParameters();
        }
        this.mCameraParam.setPictureFormat(256);
        int iDisplayOrientation = displayOrientation(this);
        this.mCamera.setDisplayOrientation(iDisplayOrientation);
        this.mCameraParam.set("rotation", iDisplayOrientation);
        this.mPreviewDegree = iDisplayOrientation;
        IDetectStrategy iDetectStrategy = this.mIDetectStrategy;
        if (iDetectStrategy != null) {
            iDetectStrategy.setPreviewDegree(iDisplayOrientation);
        }
        Point bestPreview = CameraPreviewUtils.getBestPreview(this.mCameraParam, new Point(this.mDisplayWidth, this.mDisplayHeight));
        int i = bestPreview.x;
        this.mPreviewWidth = i;
        int i2 = bestPreview.y;
        this.mPreviewHight = i2;
        this.mPreviewRect.set(0, 0, i2, i);
        this.mCameraParam.setPreviewSize(this.mPreviewWidth, this.mPreviewHight);
        this.mCamera.setParameters(this.mCameraParam);
        try {
            this.mCamera.setPreviewDisplay(this.mSurfaceHolder);
            this.mCamera.stopPreview();
            this.mCamera.setErrorCallback(this);
            this.mCamera.setPreviewCallback(this);
            this.mCamera.startPreview();
        } catch (RuntimeException e4) {
            e4.printStackTrace();
            CameraUtils.releaseCamera(this.mCamera);
            this.mCamera = null;
        } catch (Exception e5) {
            e5.printStackTrace();
            CameraUtils.releaseCamera(this.mCamera);
            this.mCamera = null;
        }
    }

    public void stopPreview() {
        Camera camera = this.mCamera;
        try {
            if (camera != null) {
                try {
                    try {
                        camera.setErrorCallback(null);
                        this.mCamera.setPreviewCallback(null);
                        this.mCamera.stopPreview();
                    } catch (RuntimeException e2) {
                        e2.printStackTrace();
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            SurfaceHolder surfaceHolder = this.mSurfaceHolder;
            if (surfaceHolder != null) {
                surfaceHolder.removeCallback(this);
            }
            if (this.mIDetectStrategy != null) {
                this.mIDetectStrategy = null;
            }
        } finally {
            CameraUtils.releaseCamera(this.mCamera);
            this.mCamera = null;
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.mSurfaceWidth = i2;
        this.mSurfaceHeight = i3;
        if (surfaceHolder.getSurface() == null) {
            return;
        }
        startPreview();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.mIsCreateSurface = true;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.mIsCreateSurface = false;
    }

    @Override // com.baidu.idl.face.platform.ui.utils.VolumeUtils.VolumeCallback
    public void volumeChanged() {
        try {
            AudioManager audioManager = (AudioManager) getSystemService("audio");
            if (audioManager != null) {
                this.mIsEnableSound = audioManager.getStreamVolume(3) > 0;
                this.mSoundView.setImageResource(this.mIsEnableSound ? R.mipmap.ic_enable_sound_ext : R.mipmap.ic_disable_sound_ext);
                if (this.mIDetectStrategy != null) {
                    this.mIDetectStrategy.setDetectStrategySoundEnable(this.mIsEnableSound);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
