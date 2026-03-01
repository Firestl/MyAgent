package com.baidu.idl.face.platform.strategy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.idl.face.platform.FaceConfig;
import com.baidu.idl.face.platform.FaceEnvironment;
import com.baidu.idl.face.platform.FaceStatusEnum;
import com.baidu.idl.face.platform.IDetectStrategy;
import com.baidu.idl.face.platform.IDetectStrategyCallback;
import com.baidu.idl.face.platform.common.ConstantHelper;
import com.baidu.idl.face.platform.common.LogHelper;
import com.baidu.idl.face.platform.common.SoundPoolHelper;
import com.baidu.idl.face.platform.decode.FaceModule;
import com.baidu.idl.face.platform.model.FaceExtInfo;
import com.baidu.idl.face.platform.model.FaceModel;
import com.baidu.idl.face.platform.utils.BitmapUtils;
import com.baidu.idl.facesdk.FaceTracker;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class FaceDetectStrategyModule extends FaceStrategyModule implements IDetectStrategy {
    public static final String TAG = "FaceDetectStrategyModule";
    public HashMap<String, String> mBase64ImageMap;
    public Context mContext;
    public Rect mDetectRect;
    public DetectStrategy mDetectStrategy;
    public IDetectStrategyCallback mIDetectStrategyCallback;
    public volatile boolean mIsEnableSound;
    public boolean mIsFirstTipsed;
    public Rect mPreviewRect;
    public SoundPoolHelper mSoundPlayHelper;
    public HashMap<FaceStatusEnum, String> mTipsMap;

    public class UIDetectResultRunnable implements Runnable {
        public final FaceModel mModel;

        public UIDetectResultRunnable(FaceModel faceModel) {
            this.mModel = faceModel;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            FaceDetectStrategyModule.this.processUIResult(this.mModel);
        }
    }

    public FaceDetectStrategyModule(Context context, FaceTracker faceTracker) {
        super(faceTracker);
        this.mSoundPlayHelper = null;
        this.mIsFirstTipsed = false;
        this.mIsEnableSound = true;
        this.mBase64ImageMap = new HashMap<>();
        this.mTipsMap = new HashMap<>();
        LogHelper.addLog("appid", context.getPackageName());
        this.mContext = context;
        this.mDetectStrategy = new DetectStrategy();
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
        IDetectStrategyCallback iDetectStrategyCallback = this.mIDetectStrategyCallback;
        if (iDetectStrategyCallback != null) {
            iDetectStrategyCallback.onDetectCompletion(faceStatusEnum, getStatusTextResId(faceStatusEnum), null);
        }
    }

    private void processUICompletion(int i, FaceStatusEnum faceStatusEnum) throws Throwable {
        this.mIsProcessing = false;
        this.mIsCompletion = true;
        LogHelper.addLogWithKey(ConstantHelper.LOG_ETM, Long.valueOf(System.currentTimeMillis()));
        LogHelper.addLogWithKey("finish", 1);
        LogHelper.sendLog();
        if (this.mIDetectStrategyCallback != null) {
            this.mBase64ImageMap.put("bestImage", this.mFaceModule.getDetectBestImage(i));
            processUIStrategyDelay(new Runnable() { // from class: com.baidu.idl.face.platform.strategy.FaceDetectStrategyModule.1
                @Override // java.lang.Runnable
                public void run() {
                    FaceDetectStrategyModule.this.processUITips(FaceStatusEnum.Liveness_Completion);
                }
            }, 500L);
            this.mIDetectStrategyCallback.onDetectCompletion(faceStatusEnum, getStatusTextResId(faceStatusEnum), this.mBase64ImageMap);
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
                    this.mDetectStrategy.reset();
                    if (this.mNoFaceTime == 0) {
                        this.mNoFaceTime = System.currentTimeMillis();
                    } else if (System.currentTimeMillis() - this.mNoFaceTime > FaceEnvironment.TIME_DETECT_MODULE) {
                        this.mIsProcessing = false;
                        processUICallback(FaceStatusEnum.Error_DetectTimeout);
                        return;
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
            FaceStatusEnum faceStatusEnumCheckDetect = this.mDetectStrategy.checkDetect(this.mPreviewRect, this.mDetectRect, faceExtInfo.getPitch(), faceExtInfo.getYaw(), faceExtInfo.getLandmarksOutOfDetectCount(this.mDetectRect), faceExtInfo.getFaceWidth(), faceModel.getFaceModuleState());
            if (faceStatusEnumCheckDetect == FaceStatusEnum.OK) {
                LogHelper.addLogWithKey(ConstantHelper.LOG_BTM, Long.valueOf(System.currentTimeMillis()));
                if (isPrepareDataSuccess(faceExtInfo.getFaceId()) && processUITips(FaceStatusEnum.Liveness_OK)) {
                    processUICompletion(faceExtInfo.getFaceId(), FaceStatusEnum.OK);
                    return;
                }
                return;
            }
            if (faceStatusEnumCheckDetect == FaceStatusEnum.Detect_NoFace) {
                this.mDetectStrategy.reset();
            }
            if (!this.mDetectStrategy.isTimeout()) {
                processUITips(faceStatusEnumCheckDetect);
            } else {
                this.mIsProcessing = false;
                processUICallback(FaceStatusEnum.Error_DetectTimeout);
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

    @Override // com.baidu.idl.face.platform.IDetectStrategy
    public void detectStrategy(byte[] bArr) {
        if (!this.mIsFirstTipsed) {
            this.mIsFirstTipsed = true;
            processUITips(FaceStatusEnum.Detect_NoFace);
        }
        if (this.mIsProcessing) {
            process(bArr);
        }
    }

    @Override // com.baidu.idl.face.platform.IDetectStrategy
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
                    Log.e(TAG, "getBestFaceImage Exception " + e2.getMessage());
                }
            }
        } catch (Exception e4) {
            e2 = e4;
            strBitmapToJpegBase64 = "";
        }
        return strBitmapToJpegBase64;
    }

    @Override // com.baidu.idl.face.platform.strategy.FaceStrategyModule
    public void processStrategy(byte[] bArr) {
        processUIStrategy(new UIDetectResultRunnable(this.mFaceModule.detect(bArr, this.mPreviewRect.height(), this.mPreviewRect.width())));
    }

    @Override // com.baidu.idl.face.platform.strategy.FaceStrategyModule, com.baidu.idl.face.platform.IDetectStrategy
    public /* bridge */ /* synthetic */ void reset() {
        super.reset();
    }

    public void setConfigValue(FaceConfig faceConfig) {
        DetectStrategy detectStrategy;
        if (faceConfig == null || (detectStrategy = this.mDetectStrategy) == null) {
            return;
        }
        detectStrategy.setHeadAngle(faceConfig.getHeadPitchValue(), faceConfig.getHeadYawValue(), faceConfig.getHeadRollValue());
    }

    @Override // com.baidu.idl.face.platform.IDetectStrategy
    public void setDetectStrategyConfig(Rect rect, Rect rect2, IDetectStrategyCallback iDetectStrategyCallback) {
        this.mPreviewRect = rect;
        this.mDetectRect = rect2;
        this.mIDetectStrategyCallback = iDetectStrategyCallback;
    }

    @Override // com.baidu.idl.face.platform.IDetectStrategy
    public void setDetectStrategySoundEnable(boolean z) {
        this.mIsEnableSound = z;
    }

    @Override // com.baidu.idl.face.platform.IDetectStrategy
    public void setPreviewDegree(int i) {
        FaceModule faceModule = this.mFaceModule;
        if (faceModule != null) {
            faceModule.setPreviewDegree(i);
        }
    }
}
