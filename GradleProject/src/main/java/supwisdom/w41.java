package supwisdom;

import com.taobao.weex.el.parse.Operators;

/* JADX INFO: compiled from: Proguard */
/* JADX INFO: loaded from: classes2.dex */
public class w41 extends u41 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f9585a;

    /* JADX INFO: compiled from: Proguard */
    public static final class a extends l61 implements Comparable<a> {
        public a(int i) {
            super(i);
        }

        public void a(int i, u41 u41Var) {
            a(i, (Object) u41Var);
        }

        public u41 d(int i) {
            return (u41) a(i);
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(a aVar) {
            int size = size();
            int size2 = aVar.size();
            int i = size < size2 ? size : size2;
            for (int i2 = 0; i2 < i; i2++) {
                int iCompareTo = ((u41) a(i2)).compareTo((u41) aVar.a(i2));
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            }
            if (size < size2) {
                return -1;
            }
            return size > size2 ? 1 : 0;
        }
    }

    public w41(a aVar) {
        if (aVar == null) {
            throw new NullPointerException("list == null");
        }
        aVar.g();
        this.f9585a = aVar;
    }

    @Override // supwisdom.u41
    public int b(u41 u41Var) {
        return this.f9585a.compareTo(((w41) u41Var).f9585a);
    }

    @Override // supwisdom.u41
    public String c() {
        return "array";
    }

    public a d() {
        return this.f9585a;
    }

    public boolean equals(Object obj) {
        if (obj instanceof w41) {
            return this.f9585a.equals(((w41) obj).f9585a);
        }
        return false;
    }

    public int hashCode() {
        return this.f9585a.hashCode();
    }

    @Override // supwisdom.t61
    public String toHuman() {
        return this.f9585a.a(Operators.BLOCK_START_STR, ", ", Operators.BLOCK_END_STR);
    }

    public String toString() {
        return this.f9585a.b("array{", ", ", Operators.BLOCK_END_STR);
    }
}
