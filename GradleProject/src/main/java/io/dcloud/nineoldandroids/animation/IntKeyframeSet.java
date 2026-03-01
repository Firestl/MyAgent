package io.dcloud.nineoldandroids.animation;

import android.view.animation.Interpolator;
import io.dcloud.nineoldandroids.animation.Keyframe;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class IntKeyframeSet extends KeyframeSet {
    public int deltaValue;
    public boolean firstTime;
    public int firstValue;
    public int lastValue;

    public IntKeyframeSet(Keyframe.IntKeyframe... intKeyframeArr) {
        super(intKeyframeArr);
        this.firstTime = true;
    }

    public int getIntValue(float f) {
        int i = this.mNumKeyframes;
        if (i == 2) {
            if (this.firstTime) {
                this.firstTime = false;
                this.firstValue = ((Keyframe.IntKeyframe) this.mKeyframes.get(0)).getIntValue();
                int intValue = ((Keyframe.IntKeyframe) this.mKeyframes.get(1)).getIntValue();
                this.lastValue = intValue;
                this.deltaValue = intValue - this.firstValue;
            }
            Interpolator interpolator = this.mInterpolator;
            if (interpolator != null) {
                f = interpolator.getInterpolation(f);
            }
            TypeEvaluator typeEvaluator = this.mEvaluator;
            return typeEvaluator == null ? this.firstValue + ((int) (f * this.deltaValue)) : ((Number) typeEvaluator.evaluate(f, Integer.valueOf(this.firstValue), Integer.valueOf(this.lastValue))).intValue();
        }
        if (f <= 0.0f) {
            Keyframe.IntKeyframe intKeyframe = (Keyframe.IntKeyframe) this.mKeyframes.get(0);
            Keyframe.IntKeyframe intKeyframe2 = (Keyframe.IntKeyframe) this.mKeyframes.get(1);
            int intValue2 = intKeyframe.getIntValue();
            int intValue3 = intKeyframe2.getIntValue();
            float fraction = intKeyframe.getFraction();
            float fraction2 = intKeyframe2.getFraction();
            Interpolator interpolator2 = intKeyframe2.getInterpolator();
            if (interpolator2 != null) {
                f = interpolator2.getInterpolation(f);
            }
            float f2 = (f - fraction) / (fraction2 - fraction);
            TypeEvaluator typeEvaluator2 = this.mEvaluator;
            return typeEvaluator2 == null ? intValue2 + ((int) (f2 * (intValue3 - intValue2))) : ((Number) typeEvaluator2.evaluate(f2, Integer.valueOf(intValue2), Integer.valueOf(intValue3))).intValue();
        }
        if (f >= 1.0f) {
            Keyframe.IntKeyframe intKeyframe3 = (Keyframe.IntKeyframe) this.mKeyframes.get(i - 2);
            Keyframe.IntKeyframe intKeyframe4 = (Keyframe.IntKeyframe) this.mKeyframes.get(this.mNumKeyframes - 1);
            int intValue4 = intKeyframe3.getIntValue();
            int intValue5 = intKeyframe4.getIntValue();
            float fraction3 = intKeyframe3.getFraction();
            float fraction4 = intKeyframe4.getFraction();
            Interpolator interpolator3 = intKeyframe4.getInterpolator();
            if (interpolator3 != null) {
                f = interpolator3.getInterpolation(f);
            }
            float f3 = (f - fraction3) / (fraction4 - fraction3);
            TypeEvaluator typeEvaluator3 = this.mEvaluator;
            return typeEvaluator3 == null ? intValue4 + ((int) (f3 * (intValue5 - intValue4))) : ((Number) typeEvaluator3.evaluate(f3, Integer.valueOf(intValue4), Integer.valueOf(intValue5))).intValue();
        }
        Keyframe.IntKeyframe intKeyframe5 = (Keyframe.IntKeyframe) this.mKeyframes.get(0);
        int i2 = 1;
        while (true) {
            int i3 = this.mNumKeyframes;
            if (i2 >= i3) {
                return ((Number) this.mKeyframes.get(i3 - 1).getValue()).intValue();
            }
            Keyframe.IntKeyframe intKeyframe6 = (Keyframe.IntKeyframe) this.mKeyframes.get(i2);
            if (f < intKeyframe6.getFraction()) {
                Interpolator interpolator4 = intKeyframe6.getInterpolator();
                if (interpolator4 != null) {
                    f = interpolator4.getInterpolation(f);
                }
                float fraction5 = (f - intKeyframe5.getFraction()) / (intKeyframe6.getFraction() - intKeyframe5.getFraction());
                int intValue6 = intKeyframe5.getIntValue();
                int intValue7 = intKeyframe6.getIntValue();
                TypeEvaluator typeEvaluator4 = this.mEvaluator;
                return typeEvaluator4 == null ? intValue6 + ((int) (fraction5 * (intValue7 - intValue6))) : ((Number) typeEvaluator4.evaluate(fraction5, Integer.valueOf(intValue6), Integer.valueOf(intValue7))).intValue();
            }
            i2++;
            intKeyframe5 = intKeyframe6;
        }
    }

    @Override // io.dcloud.nineoldandroids.animation.KeyframeSet
    public Object getValue(float f) {
        return Integer.valueOf(getIntValue(f));
    }

    @Override // io.dcloud.nineoldandroids.animation.KeyframeSet
    /* JADX INFO: renamed from: clone */
    public IntKeyframeSet m766clone() {
        ArrayList<Keyframe> arrayList = this.mKeyframes;
        int size = arrayList.size();
        Keyframe.IntKeyframe[] intKeyframeArr = new Keyframe.IntKeyframe[size];
        for (int i = 0; i < size; i++) {
            intKeyframeArr[i] = (Keyframe.IntKeyframe) arrayList.get(i).m765clone();
        }
        return new IntKeyframeSet(intKeyframeArr);
    }
}
