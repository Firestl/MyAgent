package io.dcloud.nineoldandroids.animation;

import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Keyframe implements Cloneable {
    public float mFraction;
    public Class mValueType;
    public Interpolator mInterpolator = null;
    public boolean mHasValue = false;

    public static class FloatKeyframe extends Keyframe {
        public float mValue;

        public FloatKeyframe(float f, float f2) {
            this.mFraction = f;
            this.mValue = f2;
            this.mValueType = Float.TYPE;
            this.mHasValue = true;
        }

        public float getFloatValue() {
            return this.mValue;
        }

        @Override // io.dcloud.nineoldandroids.animation.Keyframe
        public Object getValue() {
            return Float.valueOf(this.mValue);
        }

        @Override // io.dcloud.nineoldandroids.animation.Keyframe
        public void setValue(Object obj) {
            if (obj == null || obj.getClass() != Float.class) {
                return;
            }
            this.mValue = ((Float) obj).floatValue();
            this.mHasValue = true;
        }

        @Override // io.dcloud.nineoldandroids.animation.Keyframe
        /* JADX INFO: renamed from: clone */
        public FloatKeyframe m765clone() {
            FloatKeyframe floatKeyframe = new FloatKeyframe(getFraction(), this.mValue);
            floatKeyframe.setInterpolator(getInterpolator());
            return floatKeyframe;
        }

        public FloatKeyframe(float f) {
            this.mFraction = f;
            this.mValueType = Float.TYPE;
        }
    }

    public static class IntKeyframe extends Keyframe {
        public int mValue;

        public IntKeyframe(float f, int i) {
            this.mFraction = f;
            this.mValue = i;
            this.mValueType = Integer.TYPE;
            this.mHasValue = true;
        }

        public int getIntValue() {
            return this.mValue;
        }

        @Override // io.dcloud.nineoldandroids.animation.Keyframe
        public Object getValue() {
            return Integer.valueOf(this.mValue);
        }

        @Override // io.dcloud.nineoldandroids.animation.Keyframe
        public void setValue(Object obj) {
            if (obj == null || obj.getClass() != Integer.class) {
                return;
            }
            this.mValue = ((Integer) obj).intValue();
            this.mHasValue = true;
        }

        @Override // io.dcloud.nineoldandroids.animation.Keyframe
        /* JADX INFO: renamed from: clone */
        public IntKeyframe m765clone() {
            IntKeyframe intKeyframe = new IntKeyframe(getFraction(), this.mValue);
            intKeyframe.setInterpolator(getInterpolator());
            return intKeyframe;
        }

        public IntKeyframe(float f) {
            this.mFraction = f;
            this.mValueType = Integer.TYPE;
        }
    }

    public static class ObjectKeyframe extends Keyframe {
        public Object mValue;

        public ObjectKeyframe(float f, Object obj) {
            this.mFraction = f;
            this.mValue = obj;
            boolean z = obj != null;
            this.mHasValue = z;
            this.mValueType = z ? obj.getClass() : Object.class;
        }

        @Override // io.dcloud.nineoldandroids.animation.Keyframe
        public Object getValue() {
            return this.mValue;
        }

        @Override // io.dcloud.nineoldandroids.animation.Keyframe
        public void setValue(Object obj) {
            this.mValue = obj;
            this.mHasValue = obj != null;
        }

        @Override // io.dcloud.nineoldandroids.animation.Keyframe
        /* JADX INFO: renamed from: clone */
        public ObjectKeyframe m765clone() {
            ObjectKeyframe objectKeyframe = new ObjectKeyframe(getFraction(), this.mValue);
            objectKeyframe.setInterpolator(getInterpolator());
            return objectKeyframe;
        }
    }

    public static Keyframe ofFloat(float f, float f2) {
        return new FloatKeyframe(f, f2);
    }

    public static Keyframe ofInt(float f, int i) {
        return new IntKeyframe(f, i);
    }

    public static Keyframe ofObject(float f, Object obj) {
        return new ObjectKeyframe(f, obj);
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public abstract Keyframe m765clone();

    public float getFraction() {
        return this.mFraction;
    }

    public Interpolator getInterpolator() {
        return this.mInterpolator;
    }

    public Class getType() {
        return this.mValueType;
    }

    public abstract Object getValue();

    public boolean hasValue() {
        return this.mHasValue;
    }

    public void setFraction(float f) {
        this.mFraction = f;
    }

    public void setInterpolator(Interpolator interpolator) {
        this.mInterpolator = interpolator;
    }

    public abstract void setValue(Object obj);

    public static Keyframe ofFloat(float f) {
        return new FloatKeyframe(f);
    }

    public static Keyframe ofInt(float f) {
        return new IntKeyframe(f);
    }

    public static Keyframe ofObject(float f) {
        return new ObjectKeyframe(f, null);
    }
}
