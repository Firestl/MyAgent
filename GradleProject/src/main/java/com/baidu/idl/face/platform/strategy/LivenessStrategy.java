package com.baidu.idl.face.platform.strategy;

import com.baidu.idl.face.platform.FaceEnvironment;
import com.baidu.idl.face.platform.FaceStatusEnum;
import com.baidu.idl.face.platform.LivenessTypeEnum;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class LivenessStrategy {
    public static final String TAG = "LivenessStrategy";
    public long mDuration;
    public volatile int mIndex;
    public List<LivenessTypeEnum> mLivenessList;
    public volatile LivenessTypeEnum mCurrentLivenessTypeEnum = null;
    public volatile boolean mIsCurrentCheckSuccess = false;
    public boolean mTimeoutFlag = false;

    /* JADX INFO: renamed from: com.baidu.idl.face.platform.strategy.LivenessStrategy$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$baidu$idl$face$platform$LivenessTypeEnum;

        static {
            int[] iArr = new int[LivenessTypeEnum.values().length];
            $SwitchMap$com$baidu$idl$face$platform$LivenessTypeEnum = iArr;
            try {
                iArr[LivenessTypeEnum.Eye.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$baidu$idl$face$platform$LivenessTypeEnum[LivenessTypeEnum.Mouth.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$baidu$idl$face$platform$LivenessTypeEnum[LivenessTypeEnum.HeadUp.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$baidu$idl$face$platform$LivenessTypeEnum[LivenessTypeEnum.HeadDown.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$baidu$idl$face$platform$LivenessTypeEnum[LivenessTypeEnum.HeadLeft.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$baidu$idl$face$platform$LivenessTypeEnum[LivenessTypeEnum.HeadRight.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$baidu$idl$face$platform$LivenessTypeEnum[LivenessTypeEnum.HeadLeftOrRight.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public LivenessStrategy() {
        this.mDuration = 0L;
        this.mIndex = 0;
        this.mIndex = 0;
        this.mDuration = System.currentTimeMillis();
    }

    public static boolean getLivenessStatus(int[] iArr, LivenessTypeEnum livenessTypeEnum) {
        if (livenessTypeEnum == LivenessTypeEnum.Eye) {
            if (iArr[0] != 1) {
                return false;
            }
        } else if (livenessTypeEnum == LivenessTypeEnum.Mouth) {
            if (iArr[3] != 1) {
                return false;
            }
        } else if (livenessTypeEnum == LivenessTypeEnum.HeadLeft) {
            if (iArr[5] != 1) {
                return false;
            }
        } else if (livenessTypeEnum == LivenessTypeEnum.HeadRight) {
            if (iArr[6] != 1) {
                return false;
            }
        } else if (livenessTypeEnum == LivenessTypeEnum.HeadUp) {
            if (iArr[8] != 1) {
                return false;
            }
        } else if (livenessTypeEnum == LivenessTypeEnum.HeadDown) {
            if (iArr[9] != 1) {
                return false;
            }
        } else {
            if (livenessTypeEnum != LivenessTypeEnum.HeadLeftOrRight) {
                return false;
            }
            if (iArr[5] != 1 && iArr[6] != 1) {
                return false;
            }
        }
        return true;
    }

    public void checkLiveness(int[] iArr) {
        if (this.mIndex >= this.mLivenessList.size() || this.mIsCurrentCheckSuccess) {
            return;
        }
        if (System.currentTimeMillis() - this.mDuration <= FaceEnvironment.TIME_LIVENESS_MODULE) {
            this.mIsCurrentCheckSuccess = getLivenessStatus(iArr, this.mCurrentLivenessTypeEnum);
        } else {
            this.mTimeoutFlag = true;
            this.mIsCurrentCheckSuccess = false;
        }
    }

    public FaceStatusEnum getCurrentLivenessStatus() {
        if (this.mCurrentLivenessTypeEnum != null) {
            switch (AnonymousClass1.$SwitchMap$com$baidu$idl$face$platform$LivenessTypeEnum[this.mCurrentLivenessTypeEnum.ordinal()]) {
                case 1:
                    return FaceStatusEnum.Liveness_Eye;
                case 2:
                    return FaceStatusEnum.Liveness_Mouth;
                case 3:
                    return FaceStatusEnum.Liveness_HeadUp;
                case 4:
                    return FaceStatusEnum.Liveness_HeadDown;
                case 5:
                    return FaceStatusEnum.Liveness_HeadLeft;
                case 6:
                    return FaceStatusEnum.Liveness_HeadRight;
                case 7:
                    return FaceStatusEnum.Liveness_HeadLeftRight;
            }
        }
        return null;
    }

    public LivenessTypeEnum getCurrentLivenessType() {
        return this.mCurrentLivenessTypeEnum;
    }

    public boolean isCurrentLivenessCheckSuccess() {
        return this.mIsCurrentCheckSuccess;
    }

    public boolean isLivenessCheckSuccess() {
        return this.mIsCurrentCheckSuccess && this.mIndex >= this.mLivenessList.size() - 1;
    }

    public boolean isTimeout() {
        return this.mTimeoutFlag;
    }

    public void nextLiveness() {
        if (this.mIndex + 1 < this.mLivenessList.size()) {
            this.mIndex++;
            this.mIsCurrentCheckSuccess = false;
            this.mCurrentLivenessTypeEnum = this.mLivenessList.get(this.mIndex);
            this.mDuration = System.currentTimeMillis();
        }
    }

    public void reset() {
        this.mIsCurrentCheckSuccess = false;
        this.mIndex = 0;
        if (this.mLivenessList != null && this.mIndex < this.mLivenessList.size()) {
            this.mCurrentLivenessTypeEnum = this.mLivenessList.get(this.mIndex);
        }
        this.mDuration = System.currentTimeMillis();
        this.mTimeoutFlag = false;
    }

    public void resetState() {
        this.mIsCurrentCheckSuccess = false;
        this.mDuration = System.currentTimeMillis();
        this.mTimeoutFlag = false;
    }

    public void setLivenessList(List<LivenessTypeEnum> list) {
        this.mLivenessList = list;
        if (list == null || this.mIndex >= this.mLivenessList.size()) {
            return;
        }
        this.mCurrentLivenessTypeEnum = this.mLivenessList.get(this.mIndex);
    }
}
