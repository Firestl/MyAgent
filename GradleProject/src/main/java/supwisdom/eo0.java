package supwisdom;

import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import java.security.GeneralSecurityException;
import supwisdom.jo0;
import supwisdom.pn0;

/* JADX INFO: compiled from: AesCmacKeyManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class eo0 extends pn0<jo0> {

    /* JADX INFO: compiled from: AesCmacKeyManager.java */
    public class a extends pn0.b<un0, jo0> {
        public a(Class cls) {
            super(cls);
        }

        @Override // supwisdom.pn0.b
        public un0 a(jo0 jo0Var) throws GeneralSecurityException {
            return new fs0(new ds0(jo0Var.n().toByteArray()), jo0Var.o().n());
        }
    }

    /* JADX INFO: compiled from: AesCmacKeyManager.java */
    public class b extends pn0.a<ko0, jo0> {
        public b(eo0 eo0Var, Class cls) {
            super(cls);
        }

        @Override // supwisdom.pn0.a
        public void b(ko0 ko0Var) throws GeneralSecurityException {
            eo0.b(ko0Var.o());
            eo0.b(ko0Var.n());
        }

        @Override // supwisdom.pn0.a
        public ko0 a(ByteString byteString) throws InvalidProtocolBufferException {
            return ko0.a(byteString, xp0.a());
        }

        @Override // supwisdom.pn0.a
        public jo0 a(ko0 ko0Var) throws GeneralSecurityException {
            jo0.b bVarR = jo0.r();
            bVarR.a(0);
            bVarR.a(ByteString.copyFrom(gs0.a(ko0Var.n())));
            bVarR.a(ko0Var.o());
            return bVarR.build();
        }
    }

    public eo0() {
        super(jo0.class, new a(un0.class));
    }

    public static void b(no0 no0Var) throws GeneralSecurityException {
        if (no0Var.n() < 10) {
            throw new GeneralSecurityException("tag size too short");
        }
        if (no0Var.n() > 16) {
            throw new GeneralSecurityException("tag size too long");
        }
    }

    @Override // supwisdom.pn0
    public String c() {
        return "type.googleapis.com/google.crypto.tink.AesCmacKey";
    }

    @Override // supwisdom.pn0
    public pn0.a<ko0, jo0> d() {
        return new b(this, ko0.class);
    }

    @Override // supwisdom.pn0
    public KeyData.KeyMaterialType e() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    public int g() {
        return 0;
    }

    public static void b(int i) throws GeneralSecurityException {
        if (i != 32) {
            throw new GeneralSecurityException("AesCmacKey size wrong, must be 32 bytes");
        }
    }

    @Override // supwisdom.pn0
    public void a(jo0 jo0Var) throws GeneralSecurityException {
        is0.a(jo0Var.p(), g());
        b(jo0Var.n().size());
        b(jo0Var.o());
    }

    @Override // supwisdom.pn0
    public jo0 a(ByteString byteString) throws InvalidProtocolBufferException {
        return jo0.a(byteString, xp0.a());
    }

    public static void a(boolean z) throws GeneralSecurityException {
        xn0.a(new eo0(), z);
    }
}
