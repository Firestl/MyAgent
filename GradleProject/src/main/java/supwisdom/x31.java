package supwisdom;

import com.taobao.weex.el.parse.Operators;
import java.util.Iterator;
import java.util.TreeMap;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class x31 extends r61 implements Comparable<x31> {
    public final TreeMap<w51, w31> b = new TreeMap<>();

    static {
        new x31().e();
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(x31 x31Var) {
        Iterator<w31> it = this.b.values().iterator();
        Iterator<w31> it2 = x31Var.b.values().iterator();
        while (it.hasNext() && it2.hasNext()) {
            int iCompareTo = it.next().compareTo(it2.next());
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        if (it.hasNext()) {
            return 1;
        }
        return it2.hasNext() ? -1 : 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof x31) {
            return this.b.equals(((x31) obj).b);
        }
        return false;
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("annotations{");
        boolean z = true;
        for (w31 w31Var : this.b.values()) {
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(w31Var.toHuman());
        }
        sb.append(Operators.BLOCK_END_STR);
        return sb.toString();
    }
}
