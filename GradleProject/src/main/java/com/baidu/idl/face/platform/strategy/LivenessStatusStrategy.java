package com.baidu.idl.face.platform.strategy;

import android.util.Log;
import com.baidu.idl.face.platform.FaceEnvironment;
import com.baidu.idl.face.platform.FaceStatusEnum;
import com.baidu.idl.face.platform.LivenessTypeEnum;
import com.baidu.idl.face.platform.model.FaceExtInfo;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class LivenessStatusStrategy {
    public static final String TAG = "LivenessStatusStrategy";
    public long mLivenessDuration;
    public volatile int mLivenessIndex;
    public List<LivenessTypeEnum> mLivenessList;
    public boolean mLivenessTimeoutFlag = false;
    public volatile LivenessTypeEnum mCurrentLivenessTypeEnum = null;
    public long mFaceID = -1;
    public HashMap<LivenessTypeEnum, Boolean> mLivenessStatusMap = new HashMap<>();

    /* JADX INFO: renamed from: com.baidu.idl.face.platform.strategy.LivenessStatusStrategy$1, reason: invalid class name */
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

    public LivenessStatusStrategy() {
        this.mLivenessDuration = 0L;
        this.mLivenessIndex = 0;
        this.mLivenessIndex = 0;
        this.mLivenessDuration = System.currentTimeMillis();
    }

    private void clearLivenessStatus() {
        this.mLivenessStatusMap.clear();
        for (int i = 0; i < this.mLivenessList.size(); i++) {
            this.mLivenessStatusMap.put(this.mLivenessList.get(i), false);
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

    public boolean isCurrentLivenessSuccess() {
        if (this.mLivenessStatusMap.containsKey(this.mCurrentLivenessTypeEnum)) {
            return this.mLivenessStatusMap.get(this.mCurrentLivenessTypeEnum).booleanValue();
        }
        return false;
    }

    public boolean isLivenessSuccess() {
        for (Map.Entry<LivenessTypeEnum, Boolean> entry : this.mLivenessStatusMap.entrySet()) {
            if (!entry.getValue().booleanValue()) {
                entry.getKey().name();
                return false;
            }
        }
        return true;
    }

    public boolean isTimeout() {
        return this.mLivenessTimeoutFlag;
    }

    public boolean nextLiveness() {
        if (this.mLivenessIndex + 1 >= this.mLivenessList.size()) {
            return false;
        }
        this.mLivenessIndex++;
        this.mCurrentLivenessTypeEnum = this.mLivenessList.get(this.mLivenessIndex);
        this.mLivenessDuration = System.currentTimeMillis();
        return true;
    }

    public void processLiveness(FaceExtInfo faceExtInfo) {
        if (System.currentTimeMillis() - this.mLivenessDuration > FaceEnvironment.TIME_LIVENESS_MODULE) {
            this.mLivenessTimeoutFlag = true;
            return;
        }
        if (faceExtInfo != null) {
            if (faceExtInfo.getFaceId() != this.mFaceID) {
                this.mFaceID = faceExtInfo.getFaceId();
            }
            switch (AnonymousClass1.$SwitchMap$com$baidu$idl$face$platform$LivenessTypeEnum[this.mCurrentLivenessTypeEnum.ordinal()]) {
                case 1:
                    Log.e(TAG, "ext Eye " + faceExtInfo.isLiveEye());
                    break;
                case 2:
                    Log.e(TAG, "ext Mouth " + faceExtInfo.isLiveMouth());
                    break;
                case 3:
                    Log.e(TAG, "ext HeadUp " + faceExtInfo.isLiveHeadUp());
                    break;
                case 4:
                    Log.e(TAG, "ext HeadDown " + faceExtInfo.isLiveHeadDown());
                    break;
                case 5:
                    Log.e(TAG, "ext HeadLeft " + faceExtInfo.isLiveHeadTurnLeft());
                    break;
                case 6:
                    Log.e(TAG, "ext HeadRight " + faceExtInfo.isLiveHeadTurnRight());
                    break;
                case 7:
                    Log.e(TAG, "ext HeadLeftOrRight " + faceExtInfo.isLiveHeadTurnLeft() + "-" + faceExtInfo.isLiveHeadTurnRight());
                    break;
            }
            if (this.mLivenessList.contains(LivenessTypeEnum.Eye) && !this.mLivenessStatusMap.containsKey(LivenessTypeEnum.Eye)) {
                this.mLivenessStatusMap.put(LivenessTypeEnum.Eye, Boolean.valueOf(faceExtInfo.isLiveEye()));
            } else if (this.mCurrentLivenessTypeEnum == LivenessTypeEnum.Eye && faceExtInfo.isLiveEye()) {
                this.mLivenessStatusMap.put(LivenessTypeEnum.Eye, Boolean.valueOf(faceExtInfo.isLiveEye()));
            }
            if (this.mLivenessList.contains(LivenessTypeEnum.Mouth) && !this.mLivenessStatusMap.containsKey(LivenessTypeEnum.Mouth)) {
                this.mLivenessStatusMap.put(LivenessTypeEnum.Mouth, Boolean.valueOf(faceExtInfo.isLiveMouth()));
            } else if (this.mCurrentLivenessTypeEnum == LivenessTypeEnum.Mouth && faceExtInfo.isLiveMouth()) {
                this.mLivenessStatusMap.put(LivenessTypeEnum.Mouth, Boolean.valueOf(faceExtInfo.isLiveMouth()));
            }
            if (this.mLivenessList.contains(LivenessTypeEnum.HeadUp) && !this.mLivenessStatusMap.containsKey(LivenessTypeEnum.HeadUp)) {
                this.mLivenessStatusMap.put(LivenessTypeEnum.HeadUp, Boolean.valueOf(faceExtInfo.isLiveHeadUp()));
            } else if (this.mCurrentLivenessTypeEnum == LivenessTypeEnum.HeadUp && faceExtInfo.isLiveHeadUp()) {
                this.mLivenessStatusMap.put(LivenessTypeEnum.HeadUp, Boolean.valueOf(faceExtInfo.isLiveHeadUp()));
            }
            if (this.mLivenessList.contains(LivenessTypeEnum.HeadDown) && !this.mLivenessStatusMap.containsKey(LivenessTypeEnum.HeadDown)) {
                this.mLivenessStatusMap.put(LivenessTypeEnum.HeadDown, Boolean.valueOf(faceExtInfo.isLiveHeadDown()));
            } else if (this.mCurrentLivenessTypeEnum == LivenessTypeEnum.HeadDown && faceExtInfo.isLiveHeadDown()) {
                this.mLivenessStatusMap.put(LivenessTypeEnum.HeadDown, Boolean.valueOf(faceExtInfo.isLiveHeadDown()));
            }
            if (this.mLivenessList.contains(LivenessTypeEnum.HeadLeft) && !this.mLivenessStatusMap.containsKey(LivenessTypeEnum.HeadLeft)) {
                this.mLivenessStatusMap.put(LivenessTypeEnum.HeadLeft, Boolean.valueOf(faceExtInfo.isLiveHeadTurnLeft()));
            } else if (this.mCurrentLivenessTypeEnum == LivenessTypeEnum.HeadLeft && faceExtInfo.isLiveHeadTurnLeft()) {
                this.mLivenessStatusMap.put(LivenessTypeEnum.HeadLeft, Boolean.valueOf(faceExtInfo.isLiveHeadTurnLeft()));
            }
            if (this.mLivenessList.contains(LivenessTypeEnum.HeadRight) && !this.mLivenessStatusMap.containsKey(LivenessTypeEnum.HeadRight)) {
                this.mLivenessStatusMap.put(LivenessTypeEnum.HeadRight, Boolean.valueOf(faceExtInfo.isLiveHeadTurnRight()));
            } else if (this.mCurrentLivenessTypeEnum == LivenessTypeEnum.HeadRight && faceExtInfo.isLiveHeadTurnRight()) {
                this.mLivenessStatusMap.put(LivenessTypeEnum.HeadRight, Boolean.valueOf(faceExtInfo.isLiveHeadTurnRight()));
            }
            if (this.mLivenessList.contains(LivenessTypeEnum.HeadLeftOrRight) && !this.mLivenessStatusMap.containsKey(LivenessTypeEnum.HeadLeftOrRight)) {
                this.mLivenessStatusMap.put(LivenessTypeEnum.HeadLeftOrRight, Boolean.valueOf(faceExtInfo.isLiveHeadTurnLeftOrRight()));
            } else if (this.mCurrentLivenessTypeEnum == LivenessTypeEnum.HeadLeftOrRight && faceExtInfo.isLiveHeadTurnLeftOrRight()) {
                this.mLivenessStatusMap.put(LivenessTypeEnum.HeadLeftOrRight, Boolean.valueOf(faceExtInfo.isLiveHeadTurnLeftOrRight()));
            }
        }
    }

    public void reset() {
        this.mLivenessIndex = 0;
        clearLivenessStatus();
        if (this.mLivenessList != null && this.mLivenessIndex < this.mLivenessList.size()) {
            this.mCurrentLivenessTypeEnum = this.mLivenessList.get(this.mLivenessIndex);
        }
        this.mLivenessDuration = System.currentTimeMillis();
        this.mLivenessTimeoutFlag = false;
    }

    public void resetState() {
        this.mLivenessDuration = System.currentTimeMillis();
        this.mLivenessTimeoutFlag = false;
    }

    public void setLivenessList(List<LivenessTypeEnum> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        this.mLivenessList = list;
        this.mCurrentLivenessTypeEnum = list.get(0);
        clearLivenessStatus();
        StringBuilder sb = new StringBuilder();
        Iterator<LivenessTypeEnum> it = this.mLivenessList.iterator();
        while (it.hasNext()) {
            sb.append(it.next().name());
            sb.append("-");
        }
    }
}
