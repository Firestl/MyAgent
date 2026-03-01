package supwisdom;

import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public final class fo implements ko, lo {
    @Override // supwisdom.lo
    public final Object a(Object obj) {
        return ((Enum) obj).name();
    }

    @Override // supwisdom.ko
    public final Object a(Object obj, Type type) {
        return Enum.valueOf((Class) type, obj.toString());
    }

    @Override // supwisdom.ko, supwisdom.lo
    public final boolean a(Class<?> cls) {
        return Enum.class.isAssignableFrom(cls);
    }
}
