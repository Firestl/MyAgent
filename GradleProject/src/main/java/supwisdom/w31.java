package supwisdom;

import com.sangfor.dx.rop.annotation.AnnotationVisibility;
import com.taobao.weex.el.parse.Operators;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public final class w31 extends r61 implements Comparable<w31>, t61 {
    public final w51 b;
    public final AnnotationVisibility c;
    public final TreeMap<v51, y31> d;

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(w31 w31Var) {
        int iCompareTo = this.b.compareTo((u41) w31Var.b);
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        int iCompareTo2 = this.c.compareTo(w31Var.c);
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        Iterator<y31> it = this.d.values().iterator();
        Iterator<y31> it2 = w31Var.d.values().iterator();
        while (it.hasNext() && it2.hasNext()) {
            int iCompareTo3 = it.next().compareTo(it2.next());
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        if (it.hasNext()) {
            return 1;
        }
        return it2.hasNext() ? -1 : 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof w31)) {
            return false;
        }
        w31 w31Var = (w31) obj;
        if (this.b.equals(w31Var.b) && this.c == w31Var.c) {
            return this.d.equals(w31Var.d);
        }
        return false;
    }

    public w51 getType() {
        return this.b;
    }

    public Collection<y31> h() {
        return Collections.unmodifiableCollection(this.d.values());
    }

    public int hashCode() {
        return (((this.b.hashCode() * 31) + this.d.hashCode()) * 31) + this.c.hashCode();
    }

    public AnnotationVisibility i() {
        return this.c;
    }

    @Override // supwisdom.t61
    public String toHuman() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.c.toHuman());
        sb.append("-annotation ");
        sb.append(this.b.toHuman());
        sb.append(" {");
        boolean z = true;
        for (y31 y31Var : this.d.values()) {
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(y31Var.a().toHuman());
            sb.append(": ");
            sb.append(y31Var.b().toHuman());
        }
        sb.append(Operators.BLOCK_END_STR);
        return sb.toString();
    }

    public String toString() {
        return toHuman();
    }
}
