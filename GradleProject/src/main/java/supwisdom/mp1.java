package supwisdom;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import supwisdom.bt1;
import supwisdom.op1;

/* JADX INFO: loaded from: classes2.dex */
public class mp1 implements InvocationHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ np1 f8415a;

    public mp1(np1 np1Var) {
        this.f8415a = np1Var;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        np1 np1Var = this.f8415a;
        op1 op1VarA = np1Var.c.get(method);
        if (op1VarA == null) {
            synchronized (np1.class) {
                op1VarA = new op1.b(np1Var, method).a();
                np1Var.c.put(method, op1VarA);
            }
        }
        if (objArr != null && objArr.length > 0) {
            for (int i = 0; i < objArr.length; i++) {
                op1VarA.f[i].a(op1VarA, String.valueOf(objArr[i]));
            }
        }
        if (op1VarA.h == null) {
            op1VarA.h = op1VarA.f8692a.a(op1VarA.d);
        }
        vs1 vs1VarA = op1VarA.h.a();
        ss1 ss1VarA = op1VarA.f8693e ? op1VarA.g.a() : null;
        op1VarA.g = null;
        op1VarA.h = null;
        bt1.a aVar = new bt1.a();
        aVar.a(vs1VarA);
        aVar.a(op1VarA.c, ss1VarA);
        return op1VarA.b.a(aVar.a());
    }
}
