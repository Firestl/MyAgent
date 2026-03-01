package supwisdom;

import com.google.crypto.tink.shaded.protobuf.CodedOutputStream;
import com.google.crypto.tink.shaded.protobuf.WireFormat;
import java.io.IOException;

/* JADX INFO: compiled from: MapEntryLite.java */
/* JADX INFO: loaded from: classes.dex */
public class oq0<K, V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a<K, V> f8698a;

    /* JADX INFO: compiled from: MapEntryLite.java */
    public static class a<K, V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WireFormat.FieldType f8699a;
        public final K b;
        public final WireFormat.FieldType c;
        public final V d;
    }

    public static <K, V> void a(CodedOutputStream codedOutputStream, a<K, V> aVar, K k, V v) throws IOException {
        cq0.a(codedOutputStream, aVar.f8699a, 1, k);
        cq0.a(codedOutputStream, aVar.c, 2, v);
    }

    public static <K, V> int a(a<K, V> aVar, K k, V v) {
        return cq0.a(aVar.f8699a, 1, k) + cq0.a(aVar.c, 2, v);
    }

    public int a(int i, K k, V v) {
        return CodedOutputStream.n(i) + CodedOutputStream.j(a(this.f8698a, k, v));
    }

    public a<K, V> a() {
        return this.f8698a;
    }
}
