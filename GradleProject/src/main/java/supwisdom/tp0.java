package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.CodedOutputStream;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import com.google.crypto.tink.shaded.protobuf.Writer;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import supwisdom.oq0;

/* JADX INFO: compiled from: CodedOutputStreamWriter.java */
/* JADX INFO: loaded from: classes.dex */
public final class tp0 implements Writer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CodedOutputStream f9310a;

    /* JADX INFO: compiled from: CodedOutputStreamWriter.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f9311a;

        static {
            int[] iArr = new int[WireFormat.FieldType.values().length];
            f9311a = iArr;
            try {
                iArr[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9311a[WireFormat.FieldType.FIXED32.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9311a[WireFormat.FieldType.INT32.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9311a[WireFormat.FieldType.SFIXED32.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9311a[WireFormat.FieldType.SINT32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f9311a[WireFormat.FieldType.UINT32.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f9311a[WireFormat.FieldType.FIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f9311a[WireFormat.FieldType.INT64.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f9311a[WireFormat.FieldType.SFIXED64.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f9311a[WireFormat.FieldType.SINT64.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f9311a[WireFormat.FieldType.UINT64.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f9311a[WireFormat.FieldType.STRING.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    public tp0(CodedOutputStream codedOutputStream) {
        gq0.a(codedOutputStream, "output");
        CodedOutputStream codedOutputStream2 = codedOutputStream;
        this.f9310a = codedOutputStream2;
        codedOutputStream2.f2535a = this;
    }

    public static tp0 a(CodedOutputStream codedOutputStream) {
        tp0 tp0Var = codedOutputStream.f2535a;
        return tp0Var != null ? tp0Var : new tp0(codedOutputStream);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void b(int i, long j) throws IOException {
        this.f9310a.e(i, j);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void c(int i, int i2) throws IOException {
        this.f9310a.d(i, i2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void d(int i, long j) throws IOException {
        this.f9310a.c(i, j);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void e(int i, int i2) throws IOException {
        this.f9310a.a(i, i2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void f(int i, int i2) throws IOException {
        this.f9310a.e(i, i2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void g(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.f9310a.b(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.f9310a.f(i, 2);
        int iH = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iH += CodedOutputStream.h(list.get(i3).intValue());
        }
        this.f9310a.f(iH);
        while (i2 < list.size()) {
            this.f9310a.b(list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void h(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.f9310a.a(i, list.get(i2).booleanValue());
                i2++;
            }
            return;
        }
        this.f9310a.f(i, 2);
        int iB = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iB += CodedOutputStream.b(list.get(i3).booleanValue());
        }
        this.f9310a.f(iB);
        while (i2 < list.size()) {
            this.f9310a.a(list.get(i2).booleanValue());
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void i(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.f9310a.g(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.f9310a.f(i, 2);
        int iO = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iO += CodedOutputStream.o(list.get(i3).intValue());
        }
        this.f9310a.f(iO);
        while (i2 < list.size()) {
            this.f9310a.f(list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void j(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.f9310a.d(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        this.f9310a.f(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += CodedOutputStream.i(list.get(i4).longValue());
        }
        this.f9310a.f(i3);
        while (i2 < list.size()) {
            this.f9310a.d(list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void k(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.f9310a.e(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.f9310a.f(i, 2);
        int iM = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iM += CodedOutputStream.m(list.get(i3).intValue());
        }
        this.f9310a.f(iM);
        while (i2 < list.size()) {
            this.f9310a.e(list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void l(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.f9310a.b(i, list.get(i2).longValue());
                i2++;
            }
            return;
        }
        this.f9310a.f(i, 2);
        int iG = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iG += CodedOutputStream.g(list.get(i3).longValue());
        }
        this.f9310a.f(iG);
        while (i2 < list.size()) {
            this.f9310a.b(list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void m(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.f9310a.a(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.f9310a.f(i, 2);
        int iG = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iG += CodedOutputStream.g(list.get(i3).intValue());
        }
        this.f9310a.f(iG);
        while (i2 < list.size()) {
            this.f9310a.a(list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void n(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.f9310a.a(i, list.get(i2).doubleValue());
                i2++;
            }
            return;
        }
        this.f9310a.f(i, 2);
        int iB = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iB += CodedOutputStream.b(list.get(i3).doubleValue());
        }
        this.f9310a.f(iB);
        while (i2 < list.size()) {
            this.f9310a.a(list.get(i2).doubleValue());
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void b(int i, int i2) throws IOException {
        this.f9310a.b(i, i2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void c(int i, long j) throws IOException {
        this.f9310a.b(i, j);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void d(int i, int i2) throws IOException {
        this.f9310a.c(i, i2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void e(int i, long j) throws IOException {
        this.f9310a.d(i, j);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void f(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.f9310a.d(i, list.get(i2).intValue());
                i2++;
            }
            return;
        }
        this.f9310a.f(i, 2);
        int iL = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            iL += CodedOutputStream.l(list.get(i3).intValue());
        }
        this.f9310a.f(iL);
        while (i2 < list.size()) {
            this.f9310a.d(list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public Writer.FieldOrder a() {
        return Writer.FieldOrder.ASCENDING;
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void b(int i, Object obj, lr0 lr0Var) throws IOException {
        this.f9310a.a(i, (uq0) obj, lr0Var);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void c(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9310a.f(i, 2);
            int iJ = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iJ += CodedOutputStream.j(list.get(i3).longValue());
            }
            this.f9310a.f(iJ);
            while (i2 < list.size()) {
                this.f9310a.e(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9310a.e(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void d(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9310a.f(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += CodedOutputStream.i(list.get(i4).intValue());
            }
            this.f9310a.f(i3);
            while (i2 < list.size()) {
                this.f9310a.c(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9310a.c(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void e(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9310a.f(i, 2);
            int iF = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iF += CodedOutputStream.f(list.get(i3).longValue());
            }
            this.f9310a.f(iF);
            while (i2 < list.size()) {
                this.f9310a.a(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9310a.a(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void a(int i, float f) throws IOException {
        this.f9310a.a(i, f);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void b(int i) throws IOException {
        this.f9310a.f(i, 4);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void a(int i, double d) throws IOException {
        this.f9310a.a(i, d);
    }

    public final void b(int i, Object obj) throws IOException {
        if (obj instanceof String) {
            this.f9310a.a(i, (String) obj);
        } else {
            this.f9310a.a(i, (ByteString) obj);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void a(int i, long j) throws IOException {
        this.f9310a.a(i, j);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void a(int i, boolean z) throws IOException {
        this.f9310a.a(i, z);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void a(int i, String str) throws IOException {
        this.f9310a.a(i, str);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void b(int i, List<ByteString> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.f9310a.a(i, list.get(i2));
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void a(int i, ByteString byteString) throws IOException {
        this.f9310a.a(i, byteString);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void a(int i, int i2) throws IOException {
        this.f9310a.g(i, i2);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void b(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9310a.f(i, 2);
            int iH = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iH += CodedOutputStream.h(list.get(i3).longValue());
            }
            this.f9310a.f(iH);
            while (i2 < list.size()) {
                this.f9310a.c(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9310a.c(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void a(int i, Object obj, lr0 lr0Var) throws IOException {
        this.f9310a.b(i, (uq0) obj, lr0Var);
    }

    public final <V> void c(int i, oq0.a<Long, V> aVar, Map<Long, V> map) throws IOException {
        int size = map.size();
        long[] jArr = new long[size];
        Iterator<Long> it = map.keySet().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            jArr[i2] = it.next().longValue();
            i2++;
        }
        Arrays.sort(jArr);
        for (int i3 = 0; i3 < size; i3++) {
            long j = jArr[i3];
            V v = map.get(Long.valueOf(j));
            this.f9310a.f(i, 2);
            this.f9310a.f(oq0.a(aVar, Long.valueOf(j), v));
            oq0.a(this.f9310a, aVar, Long.valueOf(j), v);
        }
    }

    public final <K, V> void d(int i, oq0.a<K, V> aVar, Map<K, V> map) throws IOException {
        switch (a.f9311a[aVar.f8699a.ordinal()]) {
            case 1:
                V v = map.get(Boolean.FALSE);
                if (v != null) {
                    a(i, false, v, aVar);
                }
                V v2 = map.get(Boolean.TRUE);
                if (v2 != null) {
                    a(i, true, v2, aVar);
                    return;
                }
                return;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                b(i, aVar, map);
                return;
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                c(i, aVar, map);
                return;
            case 12:
                e(i, aVar, map);
                return;
            default:
                throw new IllegalArgumentException("does not support key type: " + aVar.f8699a);
        }
    }

    public final <V> void e(int i, oq0.a<String, V> aVar, Map<String, V> map) throws IOException {
        int size = map.size();
        String[] strArr = new String[size];
        Iterator<String> it = map.keySet().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            strArr[i2] = it.next();
            i2++;
        }
        Arrays.sort(strArr);
        for (int i3 = 0; i3 < size; i3++) {
            String str = strArr[i3];
            V v = map.get(str);
            this.f9310a.f(i, 2);
            this.f9310a.f(oq0.a(aVar, str, v));
            oq0.a(this.f9310a, aVar, str, v);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void a(int i) throws IOException {
        this.f9310a.f(i, 3);
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public final void a(int i, Object obj) throws IOException {
        if (obj instanceof ByteString) {
            this.f9310a.b(i, (ByteString) obj);
        } else {
            this.f9310a.b(i, (uq0) obj);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void a(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.f9310a.f(i, 2);
            int iB = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iB += CodedOutputStream.b(list.get(i3).floatValue());
            }
            this.f9310a.f(iB);
            while (i2 < list.size()) {
                this.f9310a.a(list.get(i2).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9310a.a(i, list.get(i2).floatValue());
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void b(int i, List<?> list, lr0 lr0Var) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            a(i, list.get(i2), lr0Var);
        }
    }

    public final <V> void b(int i, oq0.a<Integer, V> aVar, Map<Integer, V> map) throws IOException {
        int size = map.size();
        int[] iArr = new int[size];
        Iterator<Integer> it = map.keySet().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            iArr[i2] = it.next().intValue();
            i2++;
        }
        Arrays.sort(iArr);
        for (int i3 = 0; i3 < size; i3++) {
            int i4 = iArr[i3];
            V v = map.get(Integer.valueOf(i4));
            this.f9310a.f(i, 2);
            this.f9310a.f(oq0.a(aVar, Integer.valueOf(i4), v));
            oq0.a(this.f9310a, aVar, Integer.valueOf(i4), v);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void a(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof kq0) {
            kq0 kq0Var = (kq0) list;
            while (i2 < list.size()) {
                b(i, kq0Var.b(i2));
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.f9310a.a(i, list.get(i2));
            i2++;
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public void a(int i, List<?> list, lr0 lr0Var) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            b(i, list.get(i2), lr0Var);
        }
    }

    @Override // com.google.crypto.tink.shaded.protobuf.Writer
    public <K, V> void a(int i, oq0.a<K, V> aVar, Map<K, V> map) throws IOException {
        if (this.f9310a.b()) {
            d(i, aVar, map);
            return;
        }
        for (Map.Entry<K, V> entry : map.entrySet()) {
            this.f9310a.f(i, 2);
            this.f9310a.f(oq0.a(aVar, entry.getKey(), entry.getValue()));
            oq0.a(this.f9310a, aVar, entry.getKey(), entry.getValue());
        }
    }

    public final <V> void a(int i, boolean z, V v, oq0.a<Boolean, V> aVar) throws IOException {
        this.f9310a.f(i, 2);
        this.f9310a.f(oq0.a(aVar, Boolean.valueOf(z), v));
        oq0.a(this.f9310a, aVar, Boolean.valueOf(z), v);
    }
}
