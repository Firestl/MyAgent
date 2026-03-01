package com.baidu.idl.face.platform.strategy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class FaceLivenessStrategyExtModule extends FaceStrategyModule implements ILivenessStrategy {
    public static final String TAG = "FaceLivenessStrategyExtModule";
    public HashMap<String, String> mBase64ImageMap;
    public Context mContext;
    public Rect mDetectRect;
    public DetectStrategy mDetectStrategy;
    public ILivenessStrategyCallback mILivenessStrategyCallback;
    public volatile boolean mIsEnableSound;
    public boolean mIsFirstLivenessSuccessTipsed;
    public boolean mIsFirstTipsed;
    public volatile LivenessStatus mLivenessStatus;
    public LivenessStatusStrategy mLivenessStrategy;
    public long mLivenessTipsDurationTime;
    public long mLivenessTipsTime;
    public Rect mPreviewRect;
    public SoundPoolHelper mSoundPlayHelper;
    public HashMap<FaceStatusEnum, String> mTipsMap;

    /* JADX INFO: renamed from: com.baidu.idl.face.platform.strategy.FaceLivenessStrategyExtModule$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum;
        public static final /* synthetic */ int[] $SwitchMap$com$baidu$idl$face$platform$strategy$FaceLivenessStrategyExtModule$LivenessStatus;

        static {
            int[] iArr = new int[LivenessStatus.values().length];
            $SwitchMap$com$baidu$idl$face$platform$strategy$FaceLivenessStrategyExtModule$LivenessStatus = iArr;
            try {
                iArr[LivenessStatus.LivenessReady.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$baidu$idl$face$platform$strategy$FaceLivenessStrategyExtModule$LivenessStatus[LivenessStatus.LivenessTips.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$baidu$idl$face$platform$strategy$FaceLivenessStrategyExtModule$LivenessStatus[LivenessStatus.LivenessOK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[FaceStatusEnum.values().length];
            $SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum = iArr2;
            try {
                iArr2[FaceStatusEnum.Detect_NoFace.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum[FaceStatusEnum.Detect_FacePointOut.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public enum LivenessStatus {
        LivenessReady,
        LivenessTips,
        LivenessOK,
        LivenessOKTips
    }

    public class UILivenessResultRunnable implements Runnable {
        public final FaceModel mModel;

        public UILivenessResultRunnable(FaceModel faceModel) {
            this.mModel = faceModel;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            FaceLivenessStrategyExtModule.this.processUIResult(this.mModel);
        }
    }

    public FaceLivenessStrategyExtModule(Context context, FaceTracker faceTracker) {
        super(faceTracker);
        this.mSoundPlayHelper = null;
        this.mIsEnableSound = true;
        this.mIsFirstTipsed = false;
        this.mIsFirstLivenessSuccessTipsed = false;
        this.mBase64ImageMap = new HashMap<>();
        this.mTipsMap = new HashMap<>();
        this.mLivenessTipsTime = 0L;
        this.mLivenessTipsDurationTime = 0L;
        this.mLivenessStatus = LivenessStatus.LivenessReady;
        LogHelper.addLog("appid", context.getPackageName());
        this.mContext = context;
        this.mDetectStrategy = new DetectStrategy();
        this.mLivenessStrategy = new LivenessStatusStrategy();
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

    private void processUICallback(FaceStatusEnum faceStatusEnum) throws Throwable {
        if (faceStatusEnum == FaceStatusEnum.Error_DetectTimeout || faceStatusEnum == FaceStatusEnum.Error_LivenessTimeout || faceStatusEnum == FaceStatusEnum.Error_Timeout) {
            LogHelper.addLogWithKey(ConstantHelper.LOG_ETM, Long.valueOf(System.currentTimeMillis()));
            LogHelper.sendLog();
        }
        if (faceStatusEnum != FaceStatusEnum.OK && faceStatusEnum != FaceStatusEnum.Liveness_Completion) {
            ILivenessStrategyCallback iLivenessStrategyCallback = this.mILivenessStrategyCallback;
            if (iLivenessStrategyCallback != null) {
                iLivenessStrategyCallback.onLivenessCompletion(faceStatusEnum, getStatusTextResId(faceStatusEnum), null);
                return;
            }
            return;
        }
        Log.e(TAG, "processUICompletion");
        this.mIsProcessing = false;
        this.mIsCompletion = true;
        LogHelper.addLogWithKey(ConstantHelper.LOG_ETM, Long.valueOf(System.currentTimeMillis()));
        LogHelper.addLogWithKey("finish", 1);
        LogHelper.sendLog();
        if (this.mILivenessStrategyCallback != null) {
            ArrayList<String> detectBestImageList = this.mFaceModule.getDetectBestImageList();
            for (int i = 0; i < detectBestImageList.size(); i++) {
                this.mBase64ImageMap.put("bestImage" + i, detectBestImageList.get(i));
            }
            this.mILivenessStrategyCallback.onLivenessCompletion(faceStatusEnum, getStatusTextResId(faceStatusEnum), this.mBase64ImageMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processUIResult(FaceModel faceModel) throws Throwable {
        if (this.mIsProcessing) {
            long jCurrentTimeMillis = System.currentTimeMillis() - this.mLaunchTime;
            long j = FaceEnvironment.TIME_MODULE;
            if (jCurrentTimeMillis > j && j != 0) {
                this.mIsProcessing = false;
                processUICallback(FaceStatusEnum.Error_Timeout);
                return;
            }
            FaceExtInfo faceExtInfo = null;
            FaceStatusEnum faceModuleState = FaceStatusEnum.Detect_NoFace;
            LivenessTypeEnum currentLivenessType = this.mLivenessStrategy.getCurrentLivenessType();
            if (faceModel == null || faceModel.getFaceInfos() == null || faceModel.getFaceInfos().length <= 0) {
                DetectStrategy detectStrategy = this.mDetectStrategy;
                if (detectStrategy != null) {
                    detectStrategy.reset();
                }
            } else {
                faceModuleState = faceModel.getFaceModuleState();
                faceExtInfo = faceModel.getFaceInfos()[0];
                LogHelper.addLogWithKey(ConstantHelper.LOG_FTM, Long.valueOf(System.currentTimeMillis()));
            }
            FaceStatusEnum faceStatusEnumCheckDetect = faceModuleState;
            if (faceExtInfo != null) {
                faceStatusEnumCheckDetect = this.mDetectStrategy.checkDetect(this.mPreviewRect, this.mDetectRect, faceExtInfo.getPitch(), faceExtInfo.getYaw(), faceExtInfo.getLandmarksOutOfDetectCount(this.mDetectRect), faceExtInfo.getFaceWidth(), faceStatusEnumCheckDetect);
            }
            FaceStatusEnum faceStatusEnum = FaceStatusEnum.OK;
            if (faceStatusEnumCheckDetect != faceStatusEnum) {
                if (this.mDetectStrategy.isTimeout()) {
                    this.mIsProcessing = false;
                    processUICallback(FaceStatusEnum.Error_DetectTimeout);
                    return;
                }
                int i = AnonymousClass1.$SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum[faceStatusEnumCheckDetect.ordinal()];
                if (i != 1 && i != 2) {
                    processUITips(faceStatusEnumCheckDetect);
                    this.mDetectStrategy.reset();
                    this.mLivenessStatus = LivenessStatus.LivenessReady;
                    this.mLivenessStrategy.resetState();
                    return;
                }
                if (this.mNoFaceTime == 0) {
                    this.mNoFaceTime = System.currentTimeMillis();
                }
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                long j2 = this.mNoFaceTime;
                if (jCurrentTimeMillis2 - j2 > FaceEnvironment.TIME_DETECT_MODULE) {
                    this.mIsProcessing = false;
                    processUICallback(FaceStatusEnum.Error_DetectTimeout);
                    return;
                }
                if (FaceStatusEnum.Detect_NoFace != faceStatusEnumCheckDetect) {
                    this.mDetectStrategy.reset();
                    this.mLivenessStatus = LivenessStatus.LivenessReady;
                    this.mLivenessStrategy.resetState();
                } else {
                    if (this.mIsFirstLivenessSuccessTipsed && j2 != 0 && System.currentTimeMillis() - this.mNoFaceTime < FaceEnvironment.TIME_DETECT_NO_FACE_CONTINUOUS) {
                        return;
                    }
                    this.mIsFirstLivenessSuccessTipsed = false;
                    this.mDetectStrategy.reset();
                    this.mLivenessStatus = LivenessStatus.LivenessReady;
                    this.mLivenessStrategy.reset();
                    HashMap<String, String> map = this.mBase64ImageMap;
                    if (map != null) {
                        map.clear();
                    }
                }
                processUITips(faceStatusEnumCheckDetect);
                return;
            }
            if (faceExtInfo == null || faceStatusEnumCheckDetect != faceStatusEnum) {
                return;
            }
            if (this.mLivenessStrategy.getCurrentLivenessStatus() != FaceStatusEnum.Liveness_HeadLeftRight && this.mLivenessStrategy.getCurrentLivenessStatus() != FaceStatusEnum.Liveness_HeadLeft && this.mLivenessStrategy.getCurrentLivenessStatus() != FaceStatusEnum.Liveness_HeadRight) {
                this.mLivenessStrategy.processLiveness(faceExtInfo);
            } else if (this.mLivenessStatus == LivenessStatus.LivenessTips && System.currentTimeMillis() - this.mLivenessTipsTime > this.mLivenessTipsDurationTime) {
                this.mLivenessStrategy.processLiveness(faceExtInfo);
            }
            if (this.mLivenessStrategy.isCurrentLivenessSuccess()) {
                saveLivenessImage(this.mLivenessStrategy.getCurrentLivenessType(), faceModel.getArgbImage(), this.mPreviewRect);
            }
            this.mNoFaceTime = 0L;
            this.mDetectStrategy.setLiveness(currentLivenessType);
            LogHelper.addLogWithKey(ConstantHelper.LOG_BTM, Long.valueOf(System.currentTimeMillis()));
            if (this.mLivenessStrategy.isTimeout()) {
                this.mIsProcessing = false;
                processUICallback(FaceStatusEnum.Error_LivenessTimeout);
                return;
            }
            Log.e(TAG, "switch =========================");
            int i2 = AnonymousClass1.$SwitchMap$com$baidu$idl$face$platform$strategy$FaceLivenessStrategyExtModule$LivenessStatus[this.mLivenessStatus.ordinal()];
            if (i2 == 1) {
                Log.e(TAG, "switch " + this.mLivenessStatus.name() + "-" + this.mLivenessStrategy.getCurrentLivenessStatus());
                if (processUITips(this.mLivenessStrategy.getCurrentLivenessStatus())) {
                    if (this.mLivenessTipsDurationTime == 0) {
                        this.mLivenessTipsDurationTime = this.mSoundPlayHelper.getPlayDuration();
                    }
                    this.mLivenessStatus = LivenessStatus.LivenessTips;
                    this.mLivenessTipsTime = System.currentTimeMillis();
                    return;
                }
                return;
            }
            if (i2 == 2) {
                Log.e(TAG, "switch " + this.mLivenessStatus.name() + "-" + this.mLivenessStrategy.getCurrentLivenessStatus());
                if (!this.mLivenessStrategy.isCurrentLivenessSuccess()) {
                    processUITips(this.mLivenessStrategy.getCurrentLivenessStatus());
                    return;
                }
                this.mLivenessStatus = LivenessStatus.LivenessOK;
                this.mLivenessTipsTime = 0L;
                this.mLivenessTipsDurationTime = 0L;
                return;
            }
            if (i2 != 3) {
                return;
            }
            Log.e(TAG, "switch " + this.mLivenessStatus.name() + "-" + this.mLivenessStrategy.getCurrentLivenessStatus());
            if (processUITips(FaceStatusEnum.Liveness_OK)) {
                if (!this.mIsFirstLivenessSuccessTipsed) {
                    this.mIsFirstLivenessSuccessTipsed = true;
                }
                if (this.mLivenessStrategy.nextLiveness()) {
                    this.mLivenessStatus = LivenessStatus.LivenessReady;
                    this.mLivenessTipsTime = 0L;
                    this.mLivenessTipsDurationTime = 0L;
                } else if (this.mLivenessStrategy.isLivenessSuccess()) {
                    processUICallback(FaceStatusEnum.OK);
                }
            }
        }
    }

    private boolean processUITips(FaceStatusEnum faceStatusEnum) throws Throwable {
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
    public void livenessStrategy(byte[] bArr) throws Throwable {
        if (!this.mIsFirstTipsed) {
            this.mIsFirstTipsed = true;
            processUITips(FaceStatusEnum.Detect_FacePointOut);
        } else if (this.mIsProcessing) {
            process(bArr);
        }
    }

    @Override // com.baidu.idl.face.platform.strategy.FaceStrategyModule
    public void processStrategy(byte[] bArr) {
        processUIStrategy(new UILivenessResultRunnable(this.mFaceModule.detect(bArr, this.mPreviewRect.height(), this.mPreviewRect.width())));
    }

    @Override // com.baidu.idl.face.platform.strategy.FaceStrategyModule, com.baidu.idl.face.platform.IDetectStrategy
    public void reset() {
        super.reset();
        if (this.mLivenessStrategy != null && !this.mIsCompletion) {
            this.mLivenessStrategy.reset();
        }
        if (this.mBase64ImageMap != null && !this.mIsCompletion) {
            this.mBase64ImageMap.clear();
        }
        SoundPoolHelper soundPoolHelper = this.mSoundPlayHelper;
        if (soundPoolHelper != null) {
            soundPoolHelper.release();
        }
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
