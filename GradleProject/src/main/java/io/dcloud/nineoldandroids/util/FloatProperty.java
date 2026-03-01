package io.dcloud.nineoldandroids.util;

/* JADX INFO: loaded from: classes3.dex */
public abstract class FloatProperty<T> extends Property<T, Float> {
    public FloatProperty(String str) {
        super(Float.class, str);
    }

    public abstract void setValue(T t, float f);

    @Override // io.dcloud.nineoldandroids.util.Property
    public final void set(T t, Float f) {
        setValue(t, f.floatValue());
    }
}
