package supwisdom;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import java.security.GeneralSecurityException;
import javax.crypto.spec.SecretKeySpec;
import supwisdom.pn0;
import supwisdom.ro0;
import supwisdom.so0;
import supwisdom.vo0;

/* JADX INFO: compiled from: HmacKeyManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class fo0 extends pn0<ro0> {

    /* JADX INFO: compiled from: HmacKeyManager.java */
    public class a extends pn0.b<un0, ro0> {
        public a(Class cls) {
            super(cls);
        }

        @Override // supwisdom.pn0.b
        public un0 a(ro0 ro0Var) throws GeneralSecurityException {
            HashType hashTypeN = ro0Var.o().n();
            SecretKeySpec secretKeySpec = new SecretKeySpec(ro0Var.n().toByteArray(), "HMAC");
            int iO = ro0Var.o().o();
            int i = c.f7623a[hashTypeN.ordinal()];
            if (i == 1) {
                return new fs0(new es0("HMACSHA1", secretKeySpec), iO);
            }
            if (i == 2) {
                return new fs0(new es0("HMACSHA256", secretKeySpec), iO);
            }
            if (i == 3) {
                return new fs0(new es0("HMACSHA512", secretKeySpec), iO);
            }
            throw new GeneralSecurityException("unknown hash");
        }
    }

    /* JADX INFO: compiled from: HmacKeyManager.java */
    public class b extends pn0.a<so0, ro0> {
        public b(Class cls) {
            super(cls);
        }

        @Override // supwisdom.pn0.a
        public void b(so0 so0Var) throws GeneralSecurityException {
            if (so0Var.n() < 16) {
                throw new GeneralSecurityException("key too short");
            }
            fo0.b(so0Var.o());
        }

        @Override // supwisdom.pn0.a
        public so0 a(ByteString byteString) throws InvalidProtocolBufferException {
            return so0.a(byteString, xp0.a());
        }

        @Override // supwisdom.pn0.a
        public ro0 a(so0 so0Var) throws GeneralSecurityException {
            ro0.b bVarR = ro0.r();
            bVarR.a(fo0.this.g());
            bVarR.a(so0Var.o());
            bVarR.a(ByteString.copyFrom(gs0.a(so0Var.n())));
            return bVarR.build();
        }
    }

    /* JADX INFO: compiled from: HmacKeyManager.java */
    public static /* synthetic */ class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f7623a;

        static {
            int[] iArr = new int[HashType.values().length];
            f7623a = iArr;
            try {
                iArr[HashType.SHA1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7623a[HashType.SHA256.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7623a[HashType.SHA512.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public fo0() {
        super(ro0.class, new a(un0.class));
    }

    public static void b(vo0 vo0Var) throws GeneralSecurityException {
        if (vo0Var.o() < 10) {
            throw new GeneralSecurityException("tag size too small");
        }
        int i = c.f7623a[vo0Var.n().ordinal()];
        if (i == 1) {
            if (vo0Var.o() > 20) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else if (i == 2) {
            if (vo0Var.o() > 32) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else {
            if (i != 3) {
                throw new GeneralSecurityException("unknown hash type");
            }
            if (vo0Var.o() > 64) {
                throw new GeneralSecurityException("tag size too big");
            }
        }
    }

    public static final KeyTemplate h() {
        return a(32, 16, HashType.SHA256);
    }

    @Override // supwisdom.pn0
    public String c() {
        return "type.googleapis.com/google.crypto.tink.HmacKey";
    }

    @Override // supwisdom.pn0
    public pn0.a<so0, ro0> d() {
        return new b(so0.class);
    }

    @Override // supwisdom.pn0
    public KeyData.KeyMaterialType e() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    public int g() {
        return 0;
    }

    @Override // supwisdom.pn0
    public void a(ro0 ro0Var) throws GeneralSecurityException {
        is0.a(ro0Var.p(), g());
        if (ro0Var.n().size() >= 16) {
            b(ro0Var.o());
            return;
        }
        throw new GeneralSecurityException("key too short");
    }

    @Override // supwisdom.pn0
    public ro0 a(ByteString byteString) throws InvalidProtocolBufferException {
        return ro0.a(byteString, xp0.a());
    }

    public static void a(boolean z) throws GeneralSecurityException {
        xn0.a(new fo0(), z);
    }

    public static KeyTemplate a(int i, int i2, HashType hashType) {
        vo0.b bVarR = vo0.r();
        bVarR.a(hashType);
        bVarR.a(i2);
        vo0 vo0VarBuild = bVarR.build();
        so0.b bVarQ = so0.q();
        bVarQ.a(vo0VarBuild);
        bVarQ.a(i);
        return KeyTemplate.a(new fo0().c(), bVarQ.build().toByteArray(), KeyTemplate.OutputPrefixType.TINK);
    }
}
