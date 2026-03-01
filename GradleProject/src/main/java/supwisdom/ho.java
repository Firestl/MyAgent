package supwisdom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ho {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static List<lo> f7857a;

    static {
        ArrayList arrayList = new ArrayList();
        f7857a = arrayList;
        arrayList.add(new no());
        f7857a.add(new fo());
        f7857a.add(new eo());
        f7857a.add(new jo());
        f7857a.add(new Cdo());
        f7857a.add(new co());
        f7857a.add(new io());
    }

    public static String a(Object obj) {
        if (obj == null) {
            return null;
        }
        Object objB = b(obj);
        if (oo.a(objB.getClass())) {
            return org.json.alipay.b.c(objB.toString());
        }
        if (Collection.class.isAssignableFrom(objB.getClass())) {
            return new org.json.alipay.a((Collection) objB).toString();
        }
        if (Map.class.isAssignableFrom(objB.getClass())) {
            return new org.json.alipay.b((Map) objB).toString();
        }
        throw new IllegalArgumentException("Unsupported Class : " + objB.getClass());
    }

    public static Object b(Object obj) {
        Object objA;
        if (obj == null) {
            return null;
        }
        for (lo loVar : f7857a) {
            if (loVar.a(obj.getClass()) && (objA = loVar.a(obj)) != null) {
                return objA;
            }
        }
        throw new IllegalArgumentException("Unsupported Class : " + obj.getClass());
    }
}
