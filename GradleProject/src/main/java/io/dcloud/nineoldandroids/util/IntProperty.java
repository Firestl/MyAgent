package io.dcloud.nineoldandroids.util;

/* JADX INFO: loaded from: classes3.dex */
public abstract class IntProperty<T> extends Property<T, Integer> {
    public IntProperty(String str) {
        super(Integer.class, str);
    }

    public abstract void setValue(T t, int i);

    @Override // io.dcloud.nineoldandroids.util.Property
    public final void set(T t, Integer num) {
        set((Object) t, Integer.valueOf(num.intValue()));
    }
}
