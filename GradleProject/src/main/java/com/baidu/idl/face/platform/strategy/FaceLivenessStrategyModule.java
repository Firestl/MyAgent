package com.baidu.idl.face.platform.strategy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.text.TextUtils;
import com.baidu.idl.face.platform.FaceConfig;
import com.baidu.idl.face.platform.FaceEnvironment;
import com.baidu.idl.face.platform.FaceStatusEnum;
import com.baidu.idl.face.platform.ILivenessStrategy;
import com.baidu.idl.face.platform.ILivenessStrategyCallback;
import com.baidu.idl.face.platform.LivenessTypeEnum;
import com.baidu.idl.face.platform.common.ConstantHelper;
import com.baidu.idl.face.platform.common.LogHelper;
import com.baidu.idl.face.platform.common.SoundPoolHelper;
import com.baidu.idl.face.platform.decode.FaceModule;
import com.baidu.idl.face.platform.model.FaceExtInfo;
import com.baidu.idl.face.platform.model.FaceModel;
import com.baidu.idl.face.platform.utils.BitmapUtils;
import com.baidu.idl.facesdk.FaceTracker;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class FaceLivenessStrategyModule extends FaceStrategyModule implements ILivenessStrategy {
    public static final String TAG = "FaceLivenessStrategyModule";
    public HashMap<String, String> mBase64ImageMap;
    public int[] mBestFaceImage;
    public Context mContext;
    public Rect mDetectRect;
    public DetectStrategy mDetectStrategy;
    public ILivenessStrategyCallback mILivenessStrategyCallback;
    public volatile boolean mIsEnableSound;
    public boolean mIsFirstTipsed;
    public volatile boolean mIsTipsed;
    public LivenessStrategy mLivenessStrategy;
    public Rect mPreviewRect;
    public SoundPoolHelper mSoundPlayHelper;
    public HashMap<FaceStatusEnum, String> mTipsMap;

    public class UILivenessResultRunnable implements Runnable {
        public final FaceModel mModel;

        public UILivenessResultRunnable(FaceModel faceModel) {
            this.mModel = faceModel;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            FaceLivenessStrategyModule.this.processUIResult(this.mModel);
        }
    }

    public FaceLivenessStrategyModule(Context context, FaceTracker faceTracker) {
        super(faceTracker);
        this.mSoundPlayHelper = null;
        this.mIsEnableSound = true;
        this.mIsTipsed = false;
        this.mIsFirstTipsed = false;
        this.mBase64ImageMap = new HashMap<>();
        this.mTipsMap = new HashMap<>();
        LogHelper.addLog("appid", context.getPackageName());
        this.mContext = context;
        this.mDetectStrategy = new DetectStrategy();
        this.mLivenessStrategy = new LivenessStrategy();
        this.mSoundPlayHelper = new SoundPoolHelper(context);
        this.mLaunchTime = System.currentTimeMillis();
    }

    private String getStatusTextResId(FaceStatusEnum faceStatusEnum) {
        if (this.mTipsMap.containsKey(faceStatusEnum)) {
            return this.mTipsMap.get(faceStatusEnum);
        }
        int tipsId = FaceEnvironment.getTipsId(faceStatusEnum);
        if (tipsId <= 0) {
            return "";
        }
        String string = this.mContext.getResources().getString(tipsId);
        this.mTipsMap.put(faceStatusEnum, string);
        return string;
    }

    private boolean isPrepareDataSuccess(int i) {
        return !TextUtils.isEmpty(this.mFaceModule.getDetectBestImage(i));
    }

    private void processUICallback(FaceStatusEnum faceStatusEnum) {
        if (faceStatusEnum == FaceStatusEnum.Error_DetectTimeout || faceStatusEnum == FaceStatusEnum.Error_LivenessTimeout || faceStatusEnum == FaceStatusEnum.Error_Timeout) {
            LogHelper.addLogWithKey(ConstantHelper.LOG_ETM, Long.valueOf(System.currentTimeMillis()));
            LogHelper.sendLog();
        }
        ILivenessStrategyCallback iLivenessStrategyCallback = this.mILivenessStrategyCallback;
        if (iLivenessStrategyCallback != null) {
            iLivenessStrategyCallback.onLivenessCompletion(faceStatusEnum, getStatusTextResId(faceStatusEnum), null);
        }
    }

    private void processUICompletion(int i, FaceStatusEnum faceStatusEnum) throws Throwable {
        this.mIsProcessing = false;
        this.mIsCompletion = true;
        LogHelper.addLogWithKey(ConstantHelper.LOG_ETM, Long.valueOf(System.currentTimeMillis()));
        LogHelper.addLogWithKey("finish", 1);
        LogHelper.sendLog();
        if (this.mILivenessStrategyCallback != null) {
            this.mBase64ImageMap.put("bestImage", this.mFaceModule.getDetectBestImage(i));
            processUIStrategyDelay(new Runnable() { // from class: com.baidu.idl.face.platform.strategy.FaceLivenessStrategyModule.1
                @Override // java.lang.Runnable
                public void run() {
                    FaceLivenessStrategyModule.this.processUITips(FaceStatusEnum.Liveness_Completion);
                }
            }, 500L);
            this.mILivenessStrategyCallback.onLivenessCompletion(faceStatusEnum, getStatusTextResId(faceStatusEnum), this.mBase64ImageMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processUIResult(FaceModel faceModel) throws Throwable {
        FaceExtInfo faceExtInfo;
        if (this.mIsProcessing) {
            long jCurrentTimeMillis = System.currentTimeMillis() - this.mLaunchTime;
            long j = FaceEnvironment.TIME_MODULE;
            if (jCurrentTimeMillis > j && j != 0) {
                this.mIsProcessing = false;
                processUICallback(FaceStatusEnum.Error_Timeout);
                return;
            }
            if (System.currentTimeMillis() - this.mLaunchTime < 1600) {
                return;
            }
            if (faceModel == null || faceModel.getFaceInfos() == null || faceModel.getFaceInfos().length <= 0) {
                faceExtInfo = null;
                DetectStrategy detectStrategy = this.mDetectStrategy;
                if (detectStrategy != null) {
                    detectStrategy.reset();
                }
            } else {
                faceExtInfo = faceModel.getFaceInfos()[0];
                LogHelper.addLogWithKey(ConstantHelper.LOG_FTM, Long.valueOf(System.currentTimeMillis()));
            }
            FaceStatusEnum faceStatusEnum = FaceStatusEnum.Detect_NoFace;
            if (faceExtInfo == null) {
                if (faceStatusEnum == faceStatusEnum) {
                    if (this.mNoFaceTime == 0) {
                        this.mNoFaceTime = System.currentTimeMillis();
                    } else if (System.currentTimeMillis() - this.mNoFaceTime > FaceEnvironment.TIME_DETECT_MODULE) {
                        this.mIsProcessing = false;
                        processUICallback(FaceStatusEnum.Error_DetectTimeout);
                        return;
                    }
                    if (this.mNoFaceTime == 0 || System.currentTimeMillis() - this.mNoFaceTime <= FaceEnvironment.TIME_DETECT_NO_FACE_CONTINUOUS) {
                        return;
                    }
                    this.mIsTipsed = false;
                    this.mDetectStrategy.reset();
                    this.mLivenessStrategy.reset();
                    HashMap<String, String> map = this.mBase64ImageMap;
                    if (map != null) {
                        map.clear();
                    }
                } else {
                    this.mNoFaceTime = 0L;
                }
                if (!this.mDetectStrategy.isTimeout()) {
                    processUITips(faceStatusEnum);
                    return;
                } else {
                    this.mIsProcessing = false;
                    processUICallback(FaceStatusEnum.Error_DetectTimeout);
                    return;
                }
            }
            LivenessTypeEnum currentLivenessType = this.mLivenessStrategy.getCurrentLivenessType();
            this.mDetectStrategy.setLiveness(currentLivenessType);
            FaceStatusEnum faceStatusEnumCheckDetect = this.mDetectStrategy.checkDetect(this.mPreviewRect, this.mDetectRect, faceExtInfo.getPitch(), faceExtInfo.getYaw(), faceExtInfo.getLandmarksOutOfDetectCount(this.mDetectRect), faceExtInfo.getFaceWidth(), faceModel.getFaceModuleState());
            if (faceStatusEnumCheckDetect != FaceStatusEnum.OK) {
                if (processUITips(faceStatusEnumCheckDetect)) {
                    if (faceStatusEnumCheckDetect == FaceStatusEnum.Detect_NoFace) {
                        this.mIsTipsed = false;
                        this.mDetectStrategy.reset();
                        this.mLivenessStrategy.reset();
                        HashMap<String, String> map2 = this.mBase64ImageMap;
                        if (map2 != null) {
                            map2.clear();
                        }
                    } else {
                        this.mIsTipsed = false;
                        this.mLivenessStrategy.resetState();
                        this.mNoFaceTime = 0L;
                    }
                }
                if (this.mDetectStrategy.isTimeout()) {
                    this.mIsProcessing = false;
                    processUICallback(FaceStatusEnum.Error_DetectTimeout);
                    return;
                }
                return;
            }
            LogHelper.addLogWithKey(ConstantHelper.LOG_BTM, Long.valueOf(System.currentTimeMillis()));
            this.mNoFaceTime = 0L;
            if (this.mLivenessStrategy.isTimeout()) {
                this.mIsProcessing = false;
                processUICallback(FaceStatusEnum.Error_LivenessTimeout);
                return;
            }
            if (this.mLivenessStrategy.isCurrentLivenessCheckSuccess()) {
                if (this.mLivenessStrategy.isLivenessCheckSuccess()) {
                    if (!isPrepareDataSuccess(faceExtInfo.getFaceId())) {
                        return;
                    }
                    if (processUITips(FaceStatusEnum.Liveness_OK)) {
                        processUICompletion(faceExtInfo.getFaceId(), FaceStatusEnum.OK);
                    }
                } else if (this.mIsTipsed && processUITips(FaceStatusEnum.Liveness_OK)) {
                    this.mLivenessStrategy.nextLiveness();
                    this.mIsTipsed = false;
                }
            } else if (processUITips(this.mLivenessStrategy.getCurrentLivenessStatus())) {
                this.mIsTipsed = true;
            }
            if (this.mIsTipsed) {
                this.mLivenessStrategy.checkLiveness(faceExtInfo.getLiveInfo());
            }
            if (this.mLivenessStrategy.isCurrentLivenessCheckSuccess()) {
                saveLivenessImage(currentLivenessType, faceModel.getArgbImage(), this.mPreviewRect);
                LogHelper.addLogWithKey(ConstantHelper.LOG_PTM, Long.valueOf(System.currentTimeMillis()));
                LogHelper.addLivenessLog(currentLivenessType.ordinal());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean processUITips(FaceStatusEnum faceStatusEnum) {
        if (faceStatusEnum == null) {
            return false;
        }
        this.mSoundPlayHelper.setEnableSound(this.mIsEnableSound);
        boolean zPlaySound = this.mSoundPlayHelper.playSound(faceStatusEnum);
        if (!zPlaySound) {
            return zPlaySound;
        }
        LogHelper.addTipsLogWithKey(faceStatusEnum.name());
        processUICallback(faceStatusEnum);
        return zPlaySound;
    }

    private void saveLivenessImage(LivenessTypeEnum livenessTypeEnum, int[] iArr, Rect rect) throws Throwable {
        if (this.mBase64ImageMap.containsKey(livenessTypeEnum.name())) {
            return;
        }
        Bitmap bitmapCreateLivenessBitmap = BitmapUtils.createLivenessBitmap(this.mContext, iArr, rect);
        String strBitmapToJpegBase64 = BitmapUtils.bitmapToJpegBase64(bitmapCreateLivenessBitmap, 80);
        if (strBitmapToJpegBase64 != null && strBitmapToJpegBase64.length() > 0) {
            this.mBase64ImageMap.put(livenessTypeEnum.name(), strBitmapToJpegBase64.replace("\\/", "/"));
        }
        if (bitmapCreateLivenessBitmap == null || bitmapCreateLivenessBitmap.isRecycled()) {
            return;
        }
        bitmapCreateLivenessBitmap.recycle();
    }

    @Override // com.baidu.idl.face.platform.ILivenessStrategy
    public String getBestFaceImage() throws Throwable {
        Exception e2;
        String strBitmapToJpegBase64;
        FaceModule faceModule = this.mFaceModule;
        if (faceModule == null || faceModule.getBestFaceImage() == null || this.mFaceModule.getBestFaceImage().length <= 0) {
            return "";
        }
        try {
            int[] bestFaceImage = this.mFaceModule.getBestFaceImage();
            int iHeight = this.mPreviewRect.height();
            int iWidth = this.mPreviewRect.width();
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iWidth, iHeight, Bitmap.Config.ARGB_8888);
            bitmapCreateBitmap.setPixels(bestFaceImage, 0, iWidth, 0, 0, iWidth, iHeight);
            strBitmapToJpegBase64 = BitmapUtils.bitmapToJpegBase64(bitmapCreateBitmap, 100);
            if (strBitmapToJpegBase64 != null) {
                try {
                    if (strBitmapToJpegBase64.length() > 0) {
                        strBitmapToJpegBase64 = strBitmapToJpegBase64.replace("\\/", "/");
                    }
                } catch (Exception e3) {
                    e2 = e3;
                    e2.printStackTrace();
                }
            }
        } catch (Exception e4) {
            e2 = e4;
            strBitmapToJpegBase64 = "";
        }
        return strBitmapToJpegBase64;
    }

    @Override // com.baidu.idl.face.platform.ILivenessStrategy
    public void livenessStrategy(byte[] bArr) {
        if (!this.mIsFirstTipsed) {
            this.mIsFirstTipsed = true;
            processUITips(FaceStatusEnum.Detect_NoFace);
        }
        if (this.mIsProcessing) {
            process(bArr);
        }
    }

    @Override // com.baidu.idl.face.platform.strategy.FaceStrategyModule
    public void processStrategy(byte[] bArr) {
        FaceEnvironment.isDebugable();
        processUIStrategy(new UILivenessResultRunnable(this.mFaceModule.detect(bArr, this.mPreviewRect.height(), this.mPreviewRect.width())));
    }

    @Override // com.baidu.idl.face.platform.strategy.FaceStrategyModule, com.baidu.idl.face.platform.IDetectStrategy
    public void reset() {
        if (this.mLivenessStrategy != null && !this.mIsCompletion) {
            this.mLivenessStrategy.reset();
        }
        if (this.mBase64ImageMap == null || this.mIsCompletion) {
            return;
        }
        this.mBase64ImageMap.clear();
    }

    public void setConfigValue(FaceConfig faceConfig) {
        DetectStrategy detectStrategy;
        if (faceConfig == null || (detectStrategy = this.mDetectStrategy) == null) {
            return;
        }
        detectStrategy.setHeadAngle(faceConfig.getHeadPitchValue(), faceConfig.getHeadYawValue(), faceConfig.getHeadRollValue());
    }

    @Override // com.baidu.idl.face.platform.ILivenessStrategy
    public void setLivenessStrategyConfig(List<LivenessTypeEnum> list, Rect rect, Rect rect2, ILivenessStrategyCallback iLivenessStrategyCallback) {
        this.mLivenessStrategy.setLivenessList(list);
        this.mPreviewRect = rect;
        this.mDetectRect = rect2;
        this.mILivenessStrategyCallback = iLivenessStrategyCallback;
    }

    @Override // com.baidu.idl.face.platform.ILivenessStrategy
    public void setLivenessStrategySoundEnable(boolean z) {
        this.mIsEnableSound = z;
    }

    @Override // com.baidu.idl.face.platform.ILivenessStrategy
    public void setPreviewDegree(int i) {
        FaceModule faceModule = this.mFaceModule;
        if (faceModule != null) {
            faceModule.setPreviewDegree(i);
        }
    }
}
