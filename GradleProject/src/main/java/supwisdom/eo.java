package supwisdom;

import java.lang.reflect.Type;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public final class eo implements ko, lo {
    @Override // supwisdom.lo
    public final Object a(Object obj) {
        return Long.valueOf(((Date) obj).getTime());
    }

    @Override // supwisdom.ko
    public final Object a(Object obj, Type type) {
        return new Date(((Long) obj).longValue());
    }

    @Override // supwisdom.ko, supwisdom.lo
    public final boolean a(Class<?> cls) {
        return Date.class.isAssignableFrom(cls);
    }
}
