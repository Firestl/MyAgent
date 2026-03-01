package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.net.Proxy;

/* JADX INFO: compiled from: RequestLine.java */
/* JADX INFO: loaded from: classes3.dex */
public final class eu1 {
    public static String a(bt1 bt1Var, Proxy.Type type) {
        StringBuilder sb = new StringBuilder();
        sb.append(bt1Var.e());
        sb.append(' ');
        if (b(bt1Var, type)) {
            sb.append(bt1Var.g());
        } else {
            sb.append(a(bt1Var.g()));
        }
        sb.append(" HTTP/1.1");
        return sb.toString();
    }

    public static boolean b(bt1 bt1Var, Proxy.Type type) {
        return !bt1Var.d() && type == Proxy.Type.HTTP;
    }

    public static String a(vs1 vs1Var) {
        String strC = vs1Var.c();
        String strE = vs1Var.e();
        if (strE == null) {
            return strC;
        }
        return strC + Operators.CONDITION_IF + strE;
    }
}
