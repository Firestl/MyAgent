package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: DebugUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class ga {
    public static void a(Object obj, StringBuilder sb) {
        int iLastIndexOf;
        if (obj == null) {
            sb.append(com.igexin.push.core.b.m);
            return;
        }
        String simpleName = obj.getClass().getSimpleName();
        if ((simpleName == null || simpleName.length() <= 0) && (iLastIndexOf = (simpleName = obj.getClass().getName()).lastIndexOf(46)) > 0) {
            simpleName = simpleName.substring(iLastIndexOf + 1);
        }
        sb.append(simpleName);
        sb.append(Operators.BLOCK_START);
        sb.append(Integer.toHexString(System.identityHashCode(obj)));
    }
}
