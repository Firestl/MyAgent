package supwisdom;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class mo implements ko {
    @Override // supwisdom.ko
    public final Object a(Object obj, Type type) {
        if (!obj.getClass().equals(org.json.alipay.a.class)) {
            return null;
        }
        org.json.alipay.a aVar = (org.json.alipay.a) obj;
        HashSet hashSet = new HashSet();
        Class cls = type instanceof ParameterizedType ? ((ParameterizedType) type).getActualTypeArguments()[0] : Object.class;
        for (int i = 0; i < aVar.a(); i++) {
            hashSet.add(go.a(aVar.a(i), cls));
        }
        return hashSet;
    }

    @Override // supwisdom.ko, supwisdom.lo
    public final boolean a(Class<?> cls) {
        return Set.class.isAssignableFrom(cls);
    }
}
