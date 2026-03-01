package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.CodedOutputStream;
import com.google.crypto.tink.shaded.protobuf.GeneratedMessageLite;
import com.google.crypto.tink.shaded.protobuf.Writer;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import supwisdom.cq0;
import supwisdom.gq0;

/* JADX INFO: compiled from: SchemaUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class nr0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class<?> f8556a = a();
    public static final rr0<?, ?> b = a(false);
    public static final rr0<?, ?> c = a(true);
    public static final rr0<?, ?> d = new tr0();

    public static void a(Class<?> cls) {
        Class<?> cls2;
        if (!GeneratedMessageLite.class.isAssignableFrom(cls) && (cls2 = f8556a) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void b(int i, List<Double> list, Writer writer, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.n(i, list, z);
    }

    public static void c(int i, List<Integer> list, Writer writer, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.m(i, list, z);
    }

    public static void d(int i, List<Integer> list, Writer writer, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.g(i, list, z);
    }

    public static void e(int i, List<Long> list, Writer writer, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.e(i, list, z);
    }

    public static void f(int i, List<Float> list, Writer writer, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.a(i, list, z);
    }

    public static void g(int i, List<Integer> list, Writer writer, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.d(i, list, z);
    }

    public static void h(int i, List<Long> list, Writer writer, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.l(i, list, z);
    }

    public static void i(int i, List<Integer> list, Writer writer, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.f(i, list, z);
    }

    public static void j(int i, List<Long> list, Writer writer, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.b(i, list, z);
    }

    public static void k(int i, List<Integer> list, Writer writer, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.k(i, list, z);
    }

    public static void l(int i, List<Long> list, Writer writer, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.j(i, list, z);
    }

    public static void m(int i, List<Integer> list, Writer writer, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.i(i, list, z);
    }

    public static void n(int i, List<Long> list, Writer writer, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.c(i, list, z);
    }

    public static void b(int i, List<String> list, Writer writer) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.a(i, list);
    }

    public static int c(List<?> list) {
        return list.size() * 4;
    }

    public static int d(List<?> list) {
        return list.size() * 8;
    }

    public static int e(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof fq0) {
            fq0 fq0Var = (fq0) list;
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.i(fq0Var.e(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.i(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int f(List<Long> list) {
        int iG;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof mq0) {
            mq0 mq0Var = (mq0) list;
            iG = 0;
            while (i < size) {
                iG += CodedOutputStream.g(mq0Var.d(i));
                i++;
            }
        } else {
            iG = 0;
            while (i < size) {
                iG += CodedOutputStream.g(list.get(i).longValue());
                i++;
            }
        }
        return iG;
    }

    public static int g(List<Integer> list) {
        int iM;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof fq0) {
            fq0 fq0Var = (fq0) list;
            iM = 0;
            while (i < size) {
                iM += CodedOutputStream.m(fq0Var.e(i));
                i++;
            }
        } else {
            iM = 0;
            while (i < size) {
                iM += CodedOutputStream.m(list.get(i).intValue());
                i++;
            }
        }
        return iM;
    }

    public static int h(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof mq0) {
            mq0 mq0Var = (mq0) list;
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.i(mq0Var.d(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.i(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    public static int i(List<Integer> list) {
        int iO;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof fq0) {
            fq0 fq0Var = (fq0) list;
            iO = 0;
            while (i < size) {
                iO += CodedOutputStream.o(fq0Var.e(i));
                i++;
            }
        } else {
            iO = 0;
            while (i < size) {
                iO += CodedOutputStream.o(list.get(i).intValue());
                i++;
            }
        }
        return iO;
    }

    public static int j(List<Long> list) {
        int iJ;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof mq0) {
            mq0 mq0Var = (mq0) list;
            iJ = 0;
            while (i < size) {
                iJ += CodedOutputStream.j(mq0Var.d(i));
                i++;
            }
        } else {
            iJ = 0;
            while (i < size) {
                iJ += CodedOutputStream.j(list.get(i).longValue());
                i++;
            }
        }
        return iJ;
    }

    public static void a(int i, List<Boolean> list, Writer writer, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.h(i, list, z);
    }

    public static int c(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (z) {
            return CodedOutputStream.n(i) + CodedOutputStream.j(size * 4);
        }
        return size * CodedOutputStream.i(i, 0);
    }

    public static int d(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (z) {
            return CodedOutputStream.n(i) + CodedOutputStream.j(size * 8);
        }
        return size * CodedOutputStream.f(i, 0L);
    }

    public static void b(int i, List<?> list, Writer writer, lr0 lr0Var) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.b(i, list, lr0Var);
    }

    public static void a(int i, List<ByteString> list, Writer writer) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.b(i, list);
    }

    public static int b(List<Integer> list) {
        int iG;
        int size = list.size();
        int i = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof fq0) {
            fq0 fq0Var = (fq0) list;
            iG = 0;
            while (i < size) {
                iG += CodedOutputStream.g(fq0Var.e(i));
                i++;
            }
        } else {
            iG = 0;
            while (i < size) {
                iG += CodedOutputStream.g(list.get(i).intValue());
                i++;
            }
        }
        return iG;
    }

    public static void a(int i, List<?> list, Writer writer, lr0 lr0Var) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        writer.a(i, list, lr0Var);
    }

    public static rr0<?, ?> c() {
        return b;
    }

    public static rr0<?, ?> d() {
        return c;
    }

    public static int e(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iE = e(list);
        if (z) {
            return CodedOutputStream.n(i) + CodedOutputStream.j(iE);
        }
        return iE + (size * CodedOutputStream.n(i));
    }

    public static int f(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        int iF = f(list);
        if (z) {
            return CodedOutputStream.n(i) + CodedOutputStream.j(iF);
        }
        return iF + (list.size() * CodedOutputStream.n(i));
    }

    public static int g(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iG = g(list);
        if (z) {
            return CodedOutputStream.n(i) + CodedOutputStream.j(iG);
        }
        return iG + (size * CodedOutputStream.n(i));
    }

    public static int h(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iH = h(list);
        if (z) {
            return CodedOutputStream.n(i) + CodedOutputStream.j(iH);
        }
        return iH + (size * CodedOutputStream.n(i));
    }

    public static int i(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = i(list);
        if (z) {
            return CodedOutputStream.n(i) + CodedOutputStream.j(i2);
        }
        return i2 + (size * CodedOutputStream.n(i));
    }

    public static int j(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iJ = j(list);
        if (z) {
            return CodedOutputStream.n(i) + CodedOutputStream.j(iJ);
        }
        return iJ + (size * CodedOutputStream.n(i));
    }

    public static int a(List<?> list) {
        return list.size();
    }

    public static int a(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (z) {
            return CodedOutputStream.n(i) + CodedOutputStream.j(size);
        }
        return size * CodedOutputStream.b(i, true);
    }

    public static int b(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iB = b(list);
        if (z) {
            return CodedOutputStream.n(i) + CodedOutputStream.j(iB);
        }
        return iB + (size * CodedOutputStream.n(i));
    }

    public static rr0<?, ?> e() {
        return d;
    }

    public static int a(int i, Object obj, lr0 lr0Var) {
        if (obj instanceof iq0) {
            return CodedOutputStream.b(i, (iq0) obj);
        }
        return CodedOutputStream.d(i, (uq0) obj, lr0Var);
    }

    public static int b(int i, List<?> list) {
        int iB;
        int iB2;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int iN = CodedOutputStream.n(i) * size;
        if (list instanceof kq0) {
            kq0 kq0Var = (kq0) list;
            while (i2 < size) {
                Object objB = kq0Var.b(i2);
                if (objB instanceof ByteString) {
                    iB2 = CodedOutputStream.b((ByteString) objB);
                } else {
                    iB2 = CodedOutputStream.b((String) objB);
                }
                iN += iB2;
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                if (obj instanceof ByteString) {
                    iB = CodedOutputStream.b((ByteString) obj);
                } else {
                    iB = CodedOutputStream.b((String) obj);
                }
                iN += iB;
                i2++;
            }
        }
        return iN;
    }

    public static int a(int i, List<ByteString> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iN = size * CodedOutputStream.n(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            iN += CodedOutputStream.b(list.get(i2));
        }
        return iN;
    }

    public static int a(int i, List<uq0> list, lr0 lr0Var) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iC = 0;
        for (int i2 = 0; i2 < size; i2++) {
            iC += CodedOutputStream.c(i, list.get(i2), lr0Var);
        }
        return iC;
    }

    public static rr0<?, ?> a(boolean z) {
        try {
            Class<?> clsB = b();
            if (clsB == null) {
                return null;
            }
            return (rr0) clsB.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Class<?> a() {
        try {
            return Class.forName("com.google.crypto.tink.shaded.protobuf.GeneratedMessageV3");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static <T> void a(pq0 pq0Var, T t, T t2, long j) {
        vr0.a(t, j, pq0Var.a(vr0.n(t, j), vr0.n(t2, j)));
    }

    public static int b(int i, List<?> list, lr0 lr0Var) {
        int iC;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int iN = CodedOutputStream.n(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            if (obj instanceof iq0) {
                iC = CodedOutputStream.a((iq0) obj);
            } else {
                iC = CodedOutputStream.c((uq0) obj, lr0Var);
            }
            iN += iC;
        }
        return iN;
    }

    public static <T, FT extends cq0.b<FT>> void a(yp0<FT> yp0Var, T t, T t2) {
        cq0<T> cq0VarA = yp0Var.a(t2);
        if (cq0VarA.d()) {
            return;
        }
        yp0Var.b(t).a((cq0) cq0VarA);
    }

    public static <T, UT, UB> void a(rr0<UT, UB> rr0Var, T t, T t2) {
        rr0Var.c(t, rr0Var.a(rr0Var.b(t), rr0Var.b(t2)));
    }

    public static Class<?> b() {
        try {
            return Class.forName("com.google.crypto.tink.shaded.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static <UT, UB> UB a(int i, List<Integer> list, gq0.d<?> dVar, UB ub, rr0<UT, UB> rr0Var) {
        if (dVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int iIntValue = list.get(i3).intValue();
                if (dVar.a(iIntValue) != null) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(iIntValue));
                    }
                    i2++;
                } else {
                    ub = (UB) a(i, iIntValue, ub, rr0Var);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int iIntValue2 = it.next().intValue();
                if (dVar.a(iIntValue2) == null) {
                    ub = (UB) a(i, iIntValue2, ub, rr0Var);
                    it.remove();
                }
            }
        }
        return ub;
    }

    public static <UT, UB> UB a(int i, List<Integer> list, gq0.e eVar, UB ub, rr0<UT, UB> rr0Var) {
        if (eVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int iIntValue = list.get(i3).intValue();
                if (eVar.a(iIntValue)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(iIntValue));
                    }
                    i2++;
                } else {
                    ub = (UB) a(i, iIntValue, ub, rr0Var);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int iIntValue2 = it.next().intValue();
                if (!eVar.a(iIntValue2)) {
                    ub = (UB) a(i, iIntValue2, ub, rr0Var);
                    it.remove();
                }
            }
        }
        return ub;
    }

    public static <UT, UB> UB a(int i, int i2, UB ub, rr0<UT, UB> rr0Var) {
        if (ub == null) {
            ub = rr0Var.a();
        }
        rr0Var.b(ub, i, i2);
        return ub;
    }
}
