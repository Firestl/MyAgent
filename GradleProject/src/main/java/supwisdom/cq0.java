package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.CodedOutputStream;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import supwisdom.cq0.b;
import supwisdom.gq0;
import supwisdom.hq0;
import supwisdom.uq0;

/* JADX INFO: compiled from: FieldSet.java */
/* JADX INFO: loaded from: classes.dex */
public final class cq0<T extends b<T>> {
    public static final cq0 d = new cq0(true);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final or0<T, Object> f7244a;
    public boolean b;
    public boolean c;

    /* JADX INFO: compiled from: FieldSet.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f7245a;
        public static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            b = iArr;
            try {
                iArr[WireFormat.FieldType.DOUBLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[WireFormat.FieldType.FLOAT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[WireFormat.FieldType.INT64.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[WireFormat.FieldType.UINT64.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[WireFormat.FieldType.INT32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                b[WireFormat.FieldType.FIXED32.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                b[WireFormat.FieldType.BOOL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                b[WireFormat.FieldType.GROUP.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                b[WireFormat.FieldType.MESSAGE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                b[WireFormat.FieldType.STRING.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                b[WireFormat.FieldType.BYTES.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                b[WireFormat.FieldType.UINT32.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                b[WireFormat.FieldType.SFIXED32.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                b[WireFormat.FieldType.SFIXED64.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                b[WireFormat.FieldType.SINT32.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                b[WireFormat.FieldType.SINT64.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                b[WireFormat.FieldType.ENUM.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            int[] iArr2 = new int[WireFormat.JavaType.values().length];
            f7245a = iArr2;
            try {
                iArr2[WireFormat.JavaType.INT.ordinal()] = 1;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f7245a[WireFormat.JavaType.LONG.ordinal()] = 2;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f7245a[WireFormat.JavaType.FLOAT.ordinal()] = 3;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f7245a[WireFormat.JavaType.DOUBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                f7245a[WireFormat.JavaType.BOOLEAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                f7245a[WireFormat.JavaType.STRING.ordinal()] = 6;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                f7245a[WireFormat.JavaType.BYTE_STRING.ordinal()] = 7;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                f7245a[WireFormat.JavaType.ENUM.ordinal()] = 8;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                f7245a[WireFormat.JavaType.MESSAGE.ordinal()] = 9;
            } catch (NoSuchFieldError unused27) {
            }
        }
    }

    /* JADX INFO: compiled from: FieldSet.java */
    public interface b<T extends b<T>> extends Comparable<T> {
        boolean D();

        WireFormat.FieldType E();

        WireFormat.JavaType F();

        uq0.a a(uq0.a aVar, uq0 uq0Var);

        int getNumber();

        boolean isPacked();
    }

    public cq0() {
        this.f7244a = or0.c(16);
    }

    public static boolean c(WireFormat.FieldType fieldType, Object obj) {
        gq0.a(obj);
        switch (a.f7245a[fieldType.getJavaType().ordinal()]) {
            case 1:
                return obj instanceof Integer;
            case 2:
                return obj instanceof Long;
            case 3:
                return obj instanceof Float;
            case 4:
                return obj instanceof Double;
            case 5:
                return obj instanceof Boolean;
            case 6:
                return obj instanceof String;
            case 7:
                return (obj instanceof ByteString) || (obj instanceof byte[]);
            case 8:
                return (obj instanceof Integer) || (obj instanceof gq0.c);
            case 9:
                return (obj instanceof uq0) || (obj instanceof hq0);
            default:
                return false;
        }
    }

    public static <T extends b<T>> cq0<T> i() {
        return d;
    }

    public static <T extends b<T>> cq0<T> j() {
        return new cq0<>();
    }

    public Iterator<Map.Entry<T, Object>> a() {
        return this.c ? new hq0.c(this.f7244a.b().iterator()) : this.f7244a.b().iterator();
    }

    public void b(T t, Object obj) {
        if (!t.D()) {
            a(t.E(), obj);
        } else {
            if (!(obj instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                a(t.E(), it.next());
            }
            obj = arrayList;
        }
        if (obj instanceof hq0) {
            this.c = true;
        }
        this.f7244a.put(t, obj);
    }

    public boolean d() {
        return this.f7244a.isEmpty();
    }

    public boolean e() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof cq0) {
            return this.f7244a.equals(((cq0) obj).f7244a);
        }
        return false;
    }

    public boolean f() {
        for (int i = 0; i < this.f7244a.d(); i++) {
            if (!c(this.f7244a.a(i))) {
                return false;
            }
        }
        Iterator it = this.f7244a.f().iterator();
        while (it.hasNext()) {
            if (!c((Map.Entry) it.next())) {
                return false;
            }
        }
        return true;
    }

    public Iterator<Map.Entry<T, Object>> g() {
        return this.c ? new hq0.c(this.f7244a.entrySet().iterator()) : this.f7244a.entrySet().iterator();
    }

    public void h() {
        if (this.b) {
            return;
        }
        this.f7244a.i();
        this.b = true;
    }

    public int hashCode() {
        return this.f7244a.hashCode();
    }

    public cq0<T> clone() {
        cq0<T> cq0VarJ = j();
        for (int i = 0; i < this.f7244a.d(); i++) {
            Map.Entry<K, Object> entryA = this.f7244a.a(i);
            cq0VarJ.b((b) entryA.getKey(), entryA.getValue());
        }
        Iterator it = this.f7244a.f().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            cq0VarJ.b((b) entry.getKey(), entry.getValue());
        }
        cq0VarJ.c = this.c;
        return cq0VarJ;
    }

    public cq0(boolean z) {
        this(or0.c(0));
        h();
    }

    public Object a(T t) {
        Object obj = this.f7244a.get(t);
        return obj instanceof hq0 ? ((hq0) obj).c() : obj;
    }

    public cq0(or0<T, Object> or0Var) {
        this.f7244a = or0Var;
        h();
    }

    public void a(T t, Object obj) {
        List arrayList;
        if (t.D()) {
            a(t.E(), obj);
            Object objA = a((b) t);
            if (objA == null) {
                arrayList = new ArrayList();
                this.f7244a.put(t, arrayList);
            } else {
                arrayList = (List) objA;
            }
            arrayList.add(obj);
            return;
        }
        throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
    }

    public static <T extends b<T>> boolean c(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        if (key.F() == WireFormat.JavaType.MESSAGE) {
            if (key.D()) {
                Iterator it = ((List) entry.getValue()).iterator();
                while (it.hasNext()) {
                    if (!((uq0) it.next()).g()) {
                        return false;
                    }
                }
            } else {
                Object value = entry.getValue();
                if (value instanceof uq0) {
                    if (!((uq0) value).g()) {
                        return false;
                    }
                } else {
                    if (value instanceof hq0) {
                        return true;
                    }
                    throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
                }
            }
        }
        return true;
    }

    public final void b(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        Object value = entry.getValue();
        if (value instanceof hq0) {
            value = ((hq0) value).c();
        }
        if (key.D()) {
            Object objA = a((b) key);
            if (objA == null) {
                objA = new ArrayList();
            }
            Iterator it = ((List) value).iterator();
            while (it.hasNext()) {
                ((List) objA).add(a(it.next()));
            }
            this.f7244a.put(key, objA);
            return;
        }
        if (key.F() == WireFormat.JavaType.MESSAGE) {
            Object objA2 = a((b) key);
            if (objA2 == null) {
                this.f7244a.put(key, a(value));
                return;
            } else {
                this.f7244a.put(key, key.a(((uq0) objA2).b(), (uq0) value).build());
                return;
            }
        }
        this.f7244a.put(key, a(value));
    }

    public final void a(WireFormat.FieldType fieldType, Object obj) {
        if (!c(fieldType, obj)) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    public static int a(WireFormat.FieldType fieldType, boolean z) {
        if (z) {
            return 2;
        }
        return fieldType.getWireType();
    }

    public void a(cq0<T> cq0Var) {
        for (int i = 0; i < cq0Var.f7244a.d(); i++) {
            b(cq0Var.f7244a.a(i));
        }
        Iterator it = cq0Var.f7244a.f().iterator();
        while (it.hasNext()) {
            b((Map.Entry) it.next());
        }
    }

    public static Object a(Object obj) {
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    public int c() {
        int iC = 0;
        for (int i = 0; i < this.f7244a.d(); i++) {
            Map.Entry<K, Object> entryA = this.f7244a.a(i);
            iC += c((b<?>) entryA.getKey(), entryA.getValue());
        }
        Iterator it = this.f7244a.f().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            iC += c((b<?>) entry.getKey(), entry.getValue());
        }
        return iC;
    }

    public static void a(CodedOutputStream codedOutputStream, WireFormat.FieldType fieldType, int i, Object obj) throws IOException {
        if (fieldType == WireFormat.FieldType.GROUP) {
            codedOutputStream.a(i, (uq0) obj);
        } else {
            codedOutputStream.f(i, a(fieldType, false));
            a(codedOutputStream, fieldType, obj);
        }
    }

    public static int c(b<?> bVar, Object obj) {
        WireFormat.FieldType fieldTypeE = bVar.E();
        int number = bVar.getNumber();
        if (bVar.D()) {
            int iA = 0;
            if (bVar.isPacked()) {
                Iterator it = ((List) obj).iterator();
                while (it.hasNext()) {
                    iA += b(fieldTypeE, it.next());
                }
                return CodedOutputStream.n(number) + iA + CodedOutputStream.k(iA);
            }
            Iterator it2 = ((List) obj).iterator();
            while (it2.hasNext()) {
                iA += a(fieldTypeE, number, it2.next());
            }
            return iA;
        }
        return a(fieldTypeE, number, obj);
    }

    public static void a(CodedOutputStream codedOutputStream, WireFormat.FieldType fieldType, Object obj) throws IOException {
        switch (a.b[fieldType.ordinal()]) {
            case 1:
                codedOutputStream.a(((Double) obj).doubleValue());
                break;
            case 2:
                codedOutputStream.a(((Float) obj).floatValue());
                break;
            case 3:
                codedOutputStream.b(((Long) obj).longValue());
                break;
            case 4:
                codedOutputStream.e(((Long) obj).longValue());
                break;
            case 5:
                codedOutputStream.c(((Integer) obj).intValue());
                break;
            case 6:
                codedOutputStream.a(((Long) obj).longValue());
                break;
            case 7:
                codedOutputStream.b(((Integer) obj).intValue());
                break;
            case 8:
                codedOutputStream.a(((Boolean) obj).booleanValue());
                break;
            case 9:
                codedOutputStream.a((uq0) obj);
                break;
            case 10:
                codedOutputStream.b((uq0) obj);
                break;
            case 11:
                if (obj instanceof ByteString) {
                    codedOutputStream.a((ByteString) obj);
                } else {
                    codedOutputStream.a((String) obj);
                }
                break;
            case 12:
                if (obj instanceof ByteString) {
                    codedOutputStream.a((ByteString) obj);
                } else {
                    codedOutputStream.a((byte[]) obj);
                }
                break;
            case 13:
                codedOutputStream.f(((Integer) obj).intValue());
                break;
            case 14:
                codedOutputStream.d(((Integer) obj).intValue());
                break;
            case 15:
                codedOutputStream.c(((Long) obj).longValue());
                break;
            case 16:
                codedOutputStream.e(((Integer) obj).intValue());
                break;
            case 17:
                codedOutputStream.d(((Long) obj).longValue());
                break;
            case 18:
                if (obj instanceof gq0.c) {
                    codedOutputStream.a(((gq0.c) obj).getNumber());
                } else {
                    codedOutputStream.a(((Integer) obj).intValue());
                }
                break;
        }
    }

    public int b() {
        int iA = 0;
        for (int i = 0; i < this.f7244a.d(); i++) {
            iA += a((Map.Entry) this.f7244a.a(i));
        }
        Iterator it = this.f7244a.f().iterator();
        while (it.hasNext()) {
            iA += a((Map.Entry) it.next());
        }
        return iA;
    }

    public static int b(WireFormat.FieldType fieldType, Object obj) {
        switch (a.b[fieldType.ordinal()]) {
            case 1:
                return CodedOutputStream.b(((Double) obj).doubleValue());
            case 2:
                return CodedOutputStream.b(((Float) obj).floatValue());
            case 3:
                return CodedOutputStream.g(((Long) obj).longValue());
            case 4:
                return CodedOutputStream.j(((Long) obj).longValue());
            case 5:
                return CodedOutputStream.i(((Integer) obj).intValue());
            case 6:
                return CodedOutputStream.f(((Long) obj).longValue());
            case 7:
                return CodedOutputStream.h(((Integer) obj).intValue());
            case 8:
                return CodedOutputStream.b(((Boolean) obj).booleanValue());
            case 9:
                return CodedOutputStream.c((uq0) obj);
            case 10:
                if (obj instanceof hq0) {
                    return CodedOutputStream.a((hq0) obj);
                }
                return CodedOutputStream.d((uq0) obj);
            case 11:
                if (obj instanceof ByteString) {
                    return CodedOutputStream.b((ByteString) obj);
                }
                return CodedOutputStream.b((String) obj);
            case 12:
                if (obj instanceof ByteString) {
                    return CodedOutputStream.b((ByteString) obj);
                }
                return CodedOutputStream.b((byte[]) obj);
            case 13:
                return CodedOutputStream.o(((Integer) obj).intValue());
            case 14:
                return CodedOutputStream.l(((Integer) obj).intValue());
            case 15:
                return CodedOutputStream.h(((Long) obj).longValue());
            case 16:
                return CodedOutputStream.m(((Integer) obj).intValue());
            case 17:
                return CodedOutputStream.i(((Long) obj).longValue());
            case 18:
                if (obj instanceof gq0.c) {
                    return CodedOutputStream.g(((gq0.c) obj).getNumber());
                }
                return CodedOutputStream.g(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public final int a(Map.Entry<T, Object> entry) {
        T key = entry.getKey();
        Object value = entry.getValue();
        if (key.F() == WireFormat.JavaType.MESSAGE && !key.D() && !key.isPacked()) {
            if (value instanceof hq0) {
                return CodedOutputStream.a(entry.getKey().getNumber(), (hq0) value);
            }
            return CodedOutputStream.c(entry.getKey().getNumber(), (uq0) value);
        }
        return c((b<?>) key, value);
    }

    public static int a(WireFormat.FieldType fieldType, int i, Object obj) {
        int iN = CodedOutputStream.n(i);
        if (fieldType == WireFormat.FieldType.GROUP) {
            iN *= 2;
        }
        return iN + b(fieldType, obj);
    }
}
