package io.dcloud.nineoldandroids.animation;

/* JADX INFO: loaded from: classes3.dex */
public class IntEvaluator implements TypeEvaluator<Integer> {
    @Override // io.dcloud.nineoldandroids.animation.TypeEvaluator
    public Integer evaluate(float f, Integer num, Integer num2) {
        return Integer.valueOf((int) (num.intValue() + (f * (num2.intValue() - r3))));
    }
}
