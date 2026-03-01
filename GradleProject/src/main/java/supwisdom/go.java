package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class go {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static List<ko> f7750a;

    static {
        ArrayList arrayList = new ArrayList();
        f7750a = arrayList;
        arrayList.add(new no());
        f7750a.add(new fo());
        f7750a.add(new eo());
        f7750a.add(new jo());
        f7750a.add(new mo());
        f7750a.add(new Cdo());
        f7750a.add(new co());
        f7750a.add(new io());
    }

    public static final <T> T a(Object obj, Type type) {
        T t;
        for (ko koVar : f7750a) {
            if (koVar.a(oo.a(type)) && (t = (T) koVar.a(obj, type)) != null) {
                return t;
            }
        }
        return null;
    }

    public static final Object a(String str, Type type) {
        Object bVar;
        if (str == null || str.length() == 0) {
            return null;
        }
        String strTrim = str.trim();
        if (strTrim.startsWith(Operators.ARRAY_START_STR) && strTrim.endsWith(Operators.ARRAY_END_STR)) {
            bVar = new org.json.alipay.a(strTrim);
        } else {
            if (!strTrim.startsWith(Operators.BLOCK_START_STR) || !strTrim.endsWith(Operators.BLOCK_END_STR)) {
                return a((Object) strTrim, type);
            }
            bVar = new org.json.alipay.b(strTrim);
        }
        return a(bVar, type);
    }
}
