package supwisdom;

import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import java.security.GeneralSecurityException;
import supwisdom.pn0;
import supwisdom.uq0;

/* JADX INFO: compiled from: KeyManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class on0<PrimitiveT, KeyProtoT extends uq0> implements nn0<PrimitiveT> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final pn0<KeyProtoT> f8688a;
    public final Class<PrimitiveT> b;

    public on0(pn0<KeyProtoT> pn0Var, Class<PrimitiveT> cls) {
        if (!pn0Var.f().contains(cls) && !Void.class.equals(cls)) {
            throw new IllegalArgumentException(String.format("Given internalKeyMananger %s does not support primitive class %s", pn0Var.toString(), cls.getName()));
        }
        this.f8688a = pn0Var;
        this.b = cls;
    }

    @Override // supwisdom.nn0
    public final boolean a(String str) {
        return str.equals(a());
    }

    @Override // supwisdom.nn0
    public final PrimitiveT b(ByteString byteString) throws GeneralSecurityException {
        try {
            return a(this.f8688a.a(byteString));
        } catch (InvalidProtocolBufferException e2) {
            throw new GeneralSecurityException("Failures parsing proto of type " + this.f8688a.b().getName(), e2);
        }
    }

    /* JADX INFO: compiled from: KeyManagerImpl.java */
    public static class a<KeyFormatProtoT extends uq0, KeyProtoT extends uq0> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final pn0.a<KeyFormatProtoT, KeyProtoT> f8689a;

        public a(pn0.a<KeyFormatProtoT, KeyProtoT> aVar) {
            this.f8689a = aVar;
        }

        public final KeyProtoT a(KeyFormatProtoT keyformatprotot) throws GeneralSecurityException {
            this.f8689a.b(keyformatprotot);
            return this.f8689a.a(keyformatprotot);
        }

        public KeyProtoT a(ByteString byteString) throws InvalidProtocolBufferException, GeneralSecurityException {
            return (KeyProtoT) a(this.f8689a.a(byteString));
        }
    }

    public final String a() {
        return this.f8688a.c();
    }

    @Override // supwisdom.nn0
    public final KeyData a(ByteString byteString) throws GeneralSecurityException {
        try {
            uq0 uq0VarA = b().a(byteString);
            KeyData.b bVarS = KeyData.s();
            bVarS.a(a());
            bVarS.a(uq0VarA.c());
            bVarS.a(this.f8688a.e());
            return bVarS.build();
        } catch (InvalidProtocolBufferException e2) {
            throw new GeneralSecurityException("Unexpected proto", e2);
        }
    }

    public final a<?, KeyProtoT> b() {
        return new a<>(this.f8688a.d());
    }

    public final PrimitiveT a(KeyProtoT keyprotot) throws GeneralSecurityException {
        if (!Void.class.equals(this.b)) {
            this.f8688a.a(keyprotot);
            return (PrimitiveT) this.f8688a.a(keyprotot, this.b);
        }
        throw new GeneralSecurityException("Cannot create a primitive for Void");
    }
}
