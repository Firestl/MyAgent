package supwisdom;

import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import supwisdom.uq0;

/* JADX INFO: compiled from: KeyTypeManager.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class pn0<KeyProtoT extends uq0> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class<KeyProtoT> f8809a;
    public final Map<Class<?>, b<?, KeyProtoT>> b;
    public final Class<?> c;

    /* JADX INFO: compiled from: KeyTypeManager.java */
    public static abstract class a<KeyFormatProtoT extends uq0, KeyT> {
        public a(Class<KeyFormatProtoT> cls) {
        }

        public abstract KeyT a(KeyFormatProtoT keyformatprotot) throws GeneralSecurityException;

        public abstract KeyFormatProtoT a(ByteString byteString) throws InvalidProtocolBufferException;

        public abstract void b(KeyFormatProtoT keyformatprotot) throws GeneralSecurityException;
    }

    /* JADX INFO: compiled from: KeyTypeManager.java */
    public static abstract class b<PrimitiveT, KeyT> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Class<PrimitiveT> f8810a;

        public b(Class<PrimitiveT> cls) {
            this.f8810a = cls;
        }

        public final Class<PrimitiveT> a() {
            return this.f8810a;
        }

        public abstract PrimitiveT a(KeyT keyt) throws GeneralSecurityException;
    }

    @SafeVarargs
    public pn0(Class<KeyProtoT> cls, b<?, KeyProtoT>... bVarArr) {
        this.f8809a = cls;
        HashMap map = new HashMap();
        for (b<?, KeyProtoT> bVar : bVarArr) {
            if (map.containsKey(bVar.a())) {
                throw new IllegalArgumentException("KeyTypeManager constructed with duplicate factories for primitive " + bVar.a().getCanonicalName());
            }
            map.put(bVar.a(), bVar);
        }
        if (bVarArr.length > 0) {
            this.c = bVarArr[0].a();
        } else {
            this.c = Void.class;
        }
        this.b = Collections.unmodifiableMap(map);
    }

    public final <P> P a(KeyProtoT keyprotot, Class<P> cls) throws GeneralSecurityException {
        b<?, KeyProtoT> bVar = this.b.get(cls);
        if (bVar != null) {
            return (P) bVar.a(keyprotot);
        }
        throw new IllegalArgumentException("Requested primitive class " + cls.getCanonicalName() + " not supported.");
    }

    public abstract KeyProtoT a(ByteString byteString) throws InvalidProtocolBufferException;

    public abstract void a(KeyProtoT keyprotot) throws GeneralSecurityException;

    public final Class<KeyProtoT> b() {
        return this.f8809a;
    }

    public abstract String c();

    public abstract a<?, KeyProtoT> d();

    public abstract KeyData.KeyMaterialType e();

    public final Set<Class<?>> f() {
        return this.b.keySet();
    }

    public final Class<?> a() {
        return this.c;
    }
}
