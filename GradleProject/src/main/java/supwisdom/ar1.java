package supwisdom;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'f' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:372)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:337)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:322)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: compiled from: StreamOpFlag.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ar1 {
    public static final ar1 f;
    public static final ar1 g;
    public static final ar1 h;
    public static final ar1 i;
    public static final ar1 j;
    public static final int k;
    public static final int l;
    public static final int m;
    public static final int n;
    public static final int o;
    public static final /* synthetic */ ar1[] p;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<b, Integer> f6969a;
    public final int b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f6970e;

    /* JADX INFO: compiled from: StreamOpFlag.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Map<b, Integer> f6971a;

        public a(Map<b, Integer> map) {
            this.f6971a = map;
        }

        public a a(b bVar, Integer num) {
            this.f6971a.put(bVar, num);
            return this;
        }

        public a b(b bVar) {
            a(bVar, 1);
            return this;
        }

        public a c(b bVar) {
            a(bVar, 3);
            return this;
        }

        public a a(b bVar) {
            a(bVar, 2);
            return this;
        }

        public Map<b, Integer> a() {
            Map<b, Integer> map = this.f6971a;
            int i = 0;
            if (map instanceof ConcurrentMap) {
                ConcurrentMap concurrentMap = (ConcurrentMap) map;
                b[] bVarArrValues = b.values();
                int length = bVarArrValues.length;
                while (i < length) {
                    concurrentMap.putIfAbsent(bVarArrValues[i], 0);
                    i++;
                }
                return concurrentMap;
            }
            b[] bVarArrValues2 = b.values();
            int length2 = bVarArrValues2.length;
            while (i < length2) {
                iq1.a(this.f6971a, bVarArrValues2[i], 0);
                i++;
            }
            return this.f6971a;
        }
    }

    /* JADX INFO: compiled from: StreamOpFlag.java */
    public enum b {
        SPLITERATOR,
        STREAM,
        OP,
        TERMINAL_OP,
        UPSTREAM_TERMINAL_OP
    }

    static {
        a aVarB = b(b.SPLITERATOR);
        aVarB.b(b.STREAM);
        aVarB.c(b.OP);
        f = new ar1("DISTINCT", 0, 0, aVarB);
        a aVarB2 = b(b.SPLITERATOR);
        aVarB2.b(b.STREAM);
        aVarB2.c(b.OP);
        g = new ar1("SORTED", 1, 1, aVarB2);
        a aVarB3 = b(b.SPLITERATOR);
        aVarB3.b(b.STREAM);
        aVarB3.c(b.OP);
        aVarB3.a(b.TERMINAL_OP);
        aVarB3.a(b.UPSTREAM_TERMINAL_OP);
        h = new ar1("ORDERED", 2, 2, aVarB3);
        a aVarB4 = b(b.SPLITERATOR);
        aVarB4.b(b.STREAM);
        aVarB4.a(b.OP);
        i = new ar1("SIZED", 3, 3, aVarB4);
        a aVarB5 = b(b.OP);
        aVarB5.b(b.TERMINAL_OP);
        ar1 ar1Var = new ar1("SHORT_CIRCUIT", 4, 12, aVarB5);
        j = ar1Var;
        p = new ar1[]{f, g, h, i, ar1Var};
        k = a(b.SPLITERATOR);
        l = a(b.STREAM);
        a(b.OP);
        a(b.TERMINAL_OP);
        a(b.UPSTREAM_TERMINAL_OP);
        a();
        int i2 = l;
        m = i2;
        int i3 = i2 << 1;
        n = i3;
        o = i2 | i3;
        ar1 ar1Var2 = f;
        int i4 = ar1Var2.c;
        int i5 = ar1Var2.d;
        ar1 ar1Var3 = g;
        int i6 = ar1Var3.c;
        int i7 = ar1Var3.d;
        ar1 ar1Var4 = h;
        int i8 = ar1Var4.c;
        int i9 = ar1Var4.d;
        ar1 ar1Var5 = i;
        int i10 = ar1Var5.c;
        int i11 = ar1Var5.d;
        int i12 = j.c;
    }

    public ar1(String str, int i2, int i3, a aVar) {
        this.f6969a = aVar.a();
        int i4 = i3 * 2;
        this.b = i4;
        this.c = 1 << i4;
        this.d = 2 << i4;
        this.f6970e = 3 << i4;
    }

    public static int a(b bVar) {
        int iIntValue = 0;
        for (ar1 ar1Var : values()) {
            iIntValue |= ar1Var.f6969a.get(bVar).intValue() << ar1Var.b;
        }
        return iIntValue;
    }

    public static a b(b bVar) {
        a aVar = new a(new EnumMap(b.class));
        aVar.b(bVar);
        return aVar;
    }

    public static ar1 valueOf(String str) {
        return (ar1) Enum.valueOf(ar1.class, str);
    }

    public static ar1[] values() {
        return (ar1[]) p.clone();
    }

    public static int a() {
        int i2 = 0;
        for (ar1 ar1Var : values()) {
            i2 |= ar1Var.f6970e;
        }
        return i2;
    }

    public static int a(lq1<?> lq1Var) {
        int iB = lq1Var.b();
        if ((iB & 4) == 0) {
            return k & iB;
        }
        lq1Var.a();
        throw null;
    }
}
