package io.dcloud.nineoldandroids.animation;

/* JADX INFO: loaded from: classes3.dex */
public class FloatEvaluator implements TypeEvaluator<Number> {
    @Override // io.dcloud.nineoldandroids.animation.TypeEvaluator
    public Float evaluate(float f, Number number, Number number2) {
        float fFloatValue = number.floatValue();
        return Float.valueOf(fFloatValue + (f * (number2.floatValue() - fFloatValue)));
    }
}
