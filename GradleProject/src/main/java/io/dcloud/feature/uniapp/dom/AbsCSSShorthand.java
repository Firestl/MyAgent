package io.dcloud.feature.uniapp.dom;

import android.text.TextUtils;
import java.lang.Enum;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbsCSSShorthand<T extends Enum<? extends CSSProperty>> implements Cloneable {
    public float[] values;

    public enum CORNER implements CSSProperty {
        BORDER_TOP_LEFT,
        BORDER_TOP_RIGHT,
        BORDER_BOTTOM_RIGHT,
        BORDER_BOTTOM_LEFT,
        ALL
    }

    public interface CSSProperty {
    }

    public enum EDGE implements CSSProperty {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
        ALL
    }

    public enum TYPE {
        MARGIN,
        PADDING,
        BORDER
    }

    public AbsCSSShorthand(float[] fArr) {
        replace(fArr);
    }

    private float getInternal(Enum<? extends CSSProperty> r3) {
        if (r3.name().equals(EDGE.ALL.name()) || r3.name().equals(CORNER.ALL.name())) {
            return 0.0f;
        }
        return this.values[r3.ordinal()];
    }

    private void setInternal(Enum<? extends CSSProperty> r3, float f) {
        if (r3.name().equals(EDGE.ALL.name()) || r3.name().equals(CORNER.ALL.name())) {
            Arrays.fill(this.values, f);
        } else {
            this.values[r3.ordinal()] = f;
        }
    }

    public float get(Enum<? extends CSSProperty> r1) {
        return getInternal(r1);
    }

    public final void replace(float[] fArr) {
        this.values = fArr;
    }

    public void set(Enum<? extends CSSProperty> r1, float f) {
        setInternal(r1, f);
    }

    public String toString() {
        return TextUtils.isEmpty(this.values.toString()) ? "" : Arrays.toString(this.values);
    }

    @Override // 
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public AbsCSSShorthand mo29clone() throws CloneNotSupportedException {
        return (AbsCSSShorthand) super.clone();
    }

    public float get(CORNER corner) {
        return getInternal(corner);
    }

    public void set(CORNER corner, float f) {
        setInternal(corner, f);
    }

    public AbsCSSShorthand() {
        this(false);
    }

    public AbsCSSShorthand(boolean z) {
        float[] fArr = new float[Math.max(EDGE.values().length, CORNER.values().length)];
        this.values = fArr;
        if (z) {
            Arrays.fill(fArr, Float.NaN);
        }
    }
}
