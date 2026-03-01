package supwisdom;

import android.text.TextUtils;
import java.lang.reflect.Proxy;
import supwisdom.np1;

/* JADX INFO: loaded from: classes2.dex */
public class fp1 extends cp1 {
    public static volatile fp1 d;

    public static <T> T a(Class<T> cls) {
        np1 np1Var;
        fp1 fp1VarC = c();
        fp1VarC.getClass();
        if (!TextUtils.equals(bq1.f().e(), cp1.c)) {
            cp1.c = bq1.f().e();
        }
        if (cp1.b.get(cp1.c + cls.getName()) != null) {
            np1Var = cp1.b.get(cp1.c + cls.getName());
        } else {
            np1.a aVar = new np1.a();
            String str = cp1.c;
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("base url can not be null!");
            }
            aVar.f8551a = vs1.d(str);
            zs1 zs1VarB = fp1VarC.b();
            aVar.b = zs1VarB;
            if (zs1VarB == null) {
                aVar.b = new zs1();
            }
            np1Var = new np1(aVar);
            cp1.b.put(cp1.c + cls.getName(), np1Var);
        }
        np1Var.getClass();
        return (T) Proxy.newProxyInstance(np1.class.getClassLoader(), new Class[]{cls}, new mp1(np1Var));
    }

    public static fp1 c() {
        if (d == null) {
            synchronized (fp1.class) {
                if (d == null) {
                    d = new fp1();
                }
            }
        }
        return d;
    }
}
