package com.baidu.idl.face.platform.strategy;

import android.graphics.Rect;
import com.baidu.idl.face.platform.FaceEnvironment;
import com.baidu.idl.face.platform.FaceStatusEnum;
import com.baidu.idl.face.platform.LivenessTypeEnum;

/* JADX INFO: loaded from: classes.dex */
public class DetectStrategy {
    public static final String TAG = "DetectStrategy";
    public FaceStatusEnum mCurrentFaceStatus;
    public LivenessTypeEnum mLivenessTypeEnum;
    public int mHeadPitchValue = 10;
    public int mHeadYawValue = 10;
    public int mHeadRollValue = 10;
    public long mDuration = 0;
    public boolean mTimeoutFlag = false;
    public boolean mIsDetectSuccess = false;

    /* JADX INFO: renamed from: com.baidu.idl.face.platform.strategy.DetectStrategy$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum;

        static {
            int[] iArr = new int[FaceStatusEnum.values().length];
            $SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum = iArr;
            try {
                iArr[FaceStatusEnum.Detect_PoorIllumintion.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum[FaceStatusEnum.Detect_ImageBlured.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum[FaceStatusEnum.Detect_OccLeftEye.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum[FaceStatusEnum.Detect_OccRightEye.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum[FaceStatusEnum.Detect_OccNose.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum[FaceStatusEnum.Detect_OccMouth.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum[FaceStatusEnum.Detect_OccLeftContour.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum[FaceStatusEnum.Detect_OccRightContour.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum[FaceStatusEnum.Detect_OccChin.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum[FaceStatusEnum.Detect_FaceZoomIn.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum[FaceStatusEnum.Detect_FaceZoomOut.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum[FaceStatusEnum.Detect_FacePointOut.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum[FaceStatusEnum.Detect_PitchOutOfUpMaxRange.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum[FaceStatusEnum.Detect_PitchOutOfDownMaxRange.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum[FaceStatusEnum.Detect_PitchOutOfLeftMaxRange.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum[FaceStatusEnum.Detect_PitchOutOfRightMaxRange.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }

    private void checkTimeout(FaceStatusEnum faceStatusEnum) {
        FaceStatusEnum faceStatusEnum2 = this.mCurrentFaceStatus;
        if (faceStatusEnum2 == null || faceStatusEnum2 != faceStatusEnum) {
            this.mCurrentFaceStatus = faceStatusEnum;
            this.mDuration = System.currentTimeMillis();
            this.mTimeoutFlag = false;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (this.mCurrentFaceStatus != faceStatusEnum || jCurrentTimeMillis - this.mDuration <= FaceEnvironment.TIME_DETECT_MODULE) {
            return;
        }
        this.mTimeoutFlag = true;
    }

    private FaceStatusEnum getHeadPose(float f, float f2) {
        LivenessTypeEnum livenessTypeEnum;
        LivenessTypeEnum livenessTypeEnum2;
        int i = this.mHeadYawValue;
        float f3 = i;
        float f4 = i;
        int i2 = this.mHeadPitchValue;
        float f5 = i2;
        float f6 = i2;
        if (f > f5 && this.mLivenessTypeEnum != LivenessTypeEnum.HeadDown) {
            return FaceStatusEnum.Detect_PitchOutOfDownMaxRange;
        }
        if (f < f6 * (-1.0f) && this.mLivenessTypeEnum != LivenessTypeEnum.HeadUp) {
            return FaceStatusEnum.Detect_PitchOutOfUpMaxRange;
        }
        if (f2 > f3 && (livenessTypeEnum2 = this.mLivenessTypeEnum) != LivenessTypeEnum.HeadLeft && livenessTypeEnum2 != LivenessTypeEnum.HeadLeftOrRight) {
            return FaceStatusEnum.Detect_PitchOutOfLeftMaxRange;
        }
        if (f2 >= f4 * (-1.0f) || (livenessTypeEnum = this.mLivenessTypeEnum) == LivenessTypeEnum.HeadRight || livenessTypeEnum == LivenessTypeEnum.HeadLeftOrRight) {
            return null;
        }
        return FaceStatusEnum.Detect_PitchOutOfRightMaxRange;
    }

    private boolean isDefaultDetectStatus(FaceStatusEnum faceStatusEnum) {
        switch (AnonymousClass1.$SwitchMap$com$baidu$idl$face$platform$FaceStatusEnum[faceStatusEnum.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                return true;
            default:
                return false;
        }
    }

    public FaceStatusEnum checkDetect(Rect rect, Rect rect2, float f, float f2, int i, int i2, FaceStatusEnum faceStatusEnum) {
        if (isDefaultDetectStatus(faceStatusEnum)) {
            checkTimeout(faceStatusEnum);
            return faceStatusEnum;
        }
        if (i2 > rect2.width() * 1) {
            FaceStatusEnum faceStatusEnum2 = FaceStatusEnum.Detect_FaceZoomOut;
            checkTimeout(faceStatusEnum2);
            return faceStatusEnum2;
        }
        if (i2 < rect2.width() * 0.4f) {
            FaceStatusEnum faceStatusEnum3 = FaceStatusEnum.Detect_FaceZoomIn;
            checkTimeout(faceStatusEnum3);
            return faceStatusEnum3;
        }
        FaceStatusEnum headPose = getHeadPose(f, f2);
        if (headPose != null) {
            faceStatusEnum = headPose;
        }
        if (i > 10) {
            FaceStatusEnum faceStatusEnum4 = FaceStatusEnum.Detect_FacePointOut;
            checkTimeout(faceStatusEnum4);
            return faceStatusEnum4;
        }
        checkTimeout(faceStatusEnum);
        if (faceStatusEnum == FaceStatusEnum.OK) {
            this.mIsDetectSuccess = true;
        }
        return faceStatusEnum;
    }

    public boolean isDetectCheckSuccess() {
        return this.mIsDetectSuccess;
    }

    public boolean isTimeout() {
        return this.mTimeoutFlag;
    }

    public void reset() {
        this.mDuration = 0L;
        this.mTimeoutFlag = false;
        this.mIsDetectSuccess = false;
        this.mCurrentFaceStatus = null;
    }

    public void setHeadAngle(int i, int i2, int i3) {
        this.mHeadPitchValue = i;
        this.mHeadYawValue = i2;
        this.mHeadRollValue = i3;
    }

    public void setLiveness(LivenessTypeEnum livenessTypeEnum) {
        this.mLivenessTypeEnum = livenessTypeEnum;
    }
}
