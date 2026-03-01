package supwisdom;

import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.OutputPrefixType;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import supwisdom.cp0;

/* JADX INFO: compiled from: PrimitiveSet.java */
/* JADX INFO: loaded from: classes.dex */
public final class vn0<P> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ConcurrentMap<c, List<b<P>>> f9528a = new ConcurrentHashMap();
    public final Class<P> b;

    /* JADX INFO: compiled from: PrimitiveSet.java */
    public static final class b<P> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final P f9529a;
        public final byte[] b;
        public final KeyStatusType c;
        public final OutputPrefixType d;

        public b(P p, byte[] bArr, KeyStatusType keyStatusType, OutputPrefixType outputPrefixType, int i) {
            this.f9529a = p;
            this.b = Arrays.copyOf(bArr, bArr.length);
            this.c = keyStatusType;
            this.d = outputPrefixType;
        }

        public final byte[] a() {
            byte[] bArr = this.b;
            if (bArr == null) {
                return null;
            }
            return Arrays.copyOf(bArr, bArr.length);
        }

        public OutputPrefixType b() {
            return this.d;
        }

        public P c() {
            return this.f9529a;
        }

        public KeyStatusType d() {
            return this.c;
        }
    }

    /* JADX INFO: compiled from: PrimitiveSet.java */
    public static class c implements Comparable<c> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final byte[] f9530a;

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(c cVar) {
            int length;
            int length2;
            byte[] bArr = this.f9530a;
            int length3 = bArr.length;
            byte[] bArr2 = cVar.f9530a;
            if (length3 != bArr2.length) {
                length = bArr.length;
                length2 = bArr2.length;
            } else {
                int i = 0;
                while (true) {
                    byte[] bArr3 = this.f9530a;
                    if (i >= bArr3.length) {
                        return 0;
                    }
                    byte b = bArr3[i];
                    byte[] bArr4 = cVar.f9530a;
                    if (b != bArr4[i]) {
                        length = bArr3[i];
                        length2 = bArr4[i];
                        break;
                    }
                    i++;
                }
            }
            return length - length2;
        }

        public boolean equals(Object obj) {
            if (obj instanceof c) {
                return Arrays.equals(this.f9530a, ((c) obj).f9530a);
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(this.f9530a);
        }

        public String toString() {
            return cs0.a(this.f9530a);
        }

        public c(byte[] bArr) {
            this.f9530a = Arrays.copyOf(bArr, bArr.length);
        }
    }

    public vn0(Class<P> cls) {
        this.b = cls;
    }

    public List<b<P>> a(byte[] bArr) {
        List<b<P>> list = this.f9528a.get(new c(bArr));
        return list != null ? list : Collections.emptyList();
    }

    public List<b<P>> b() {
        return a(mn0.f8410a);
    }

    public static <P> vn0<P> a(Class<P> cls) {
        return new vn0<>(cls);
    }

    public void a(b<P> bVar) {
        if (bVar != null) {
            if (bVar.d() == KeyStatusType.ENABLED) {
                if (a(bVar.a()).isEmpty()) {
                    throw new IllegalArgumentException("the primary entry cannot be set to an entry which is not held by this primitive set");
                }
                return;
            }
            throw new IllegalArgumentException("the primary entry has to be ENABLED");
        }
        throw new IllegalArgumentException("the primary entry must be non-null");
    }

    public b<P> a(P p, cp0.c cVar) throws GeneralSecurityException {
        if (cVar.q() == KeyStatusType.ENABLED) {
            b<P> bVar = new b<>(p, mn0.a(cVar), cVar.q(), cVar.p(), cVar.o());
            ArrayList arrayList = new ArrayList();
            arrayList.add(bVar);
            c cVar2 = new c(bVar.a());
            List<b<P>> listPut = this.f9528a.put(cVar2, Collections.unmodifiableList(arrayList));
            if (listPut != null) {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(listPut);
                arrayList2.add(bVar);
                this.f9528a.put(cVar2, Collections.unmodifiableList(arrayList2));
            }
            return bVar;
        }
        throw new GeneralSecurityException("only ENABLED key is allowed");
    }

    public Class<P> a() {
        return this.b;
    }
}
