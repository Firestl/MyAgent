package supwisdom;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import supwisdom.po0;

/* JADX INFO: compiled from: KeysetHandle.java */
/* JADX INFO: loaded from: classes.dex */
public final class qn0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final cp0 f8940a;

    public qn0(cp0 cp0Var) {
        this.f8940a = cp0Var;
    }

    public static final qn0 b(cp0 cp0Var) throws GeneralSecurityException {
        a(cp0Var);
        return new qn0(cp0Var);
    }

    public cp0 a() {
        return this.f8940a;
    }

    public String toString() {
        return b().toString();
    }

    public static final qn0 a(sn0 sn0Var, kn0 kn0Var) throws GeneralSecurityException, IOException {
        po0 po0VarA = sn0Var.a();
        a(po0VarA);
        return new qn0(a(po0VarA, kn0Var));
    }

    public dp0 b() {
        return yn0.a(this.f8940a);
    }

    public void a(tn0 tn0Var, kn0 kn0Var) throws GeneralSecurityException, IOException {
        tn0Var.a(a(this.f8940a, kn0Var));
    }

    public static po0 a(cp0 cp0Var, kn0 kn0Var) throws GeneralSecurityException {
        byte[] bArrA = kn0Var.a(cp0Var.toByteArray(), new byte[0]);
        try {
            if (cp0.a(kn0Var.b(bArrA, new byte[0]), xp0.a()).equals(cp0Var)) {
                po0.b bVarP = po0.p();
                bVarP.a(ByteString.copyFrom(bArrA));
                bVarP.a(yn0.a(cp0Var));
                return bVarP.build();
            }
            throw new GeneralSecurityException("cannot encrypt keyset");
        } catch (InvalidProtocolBufferException unused) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
    }

    public static cp0 a(po0 po0Var, kn0 kn0Var) throws GeneralSecurityException {
        try {
            cp0 cp0VarA = cp0.a(kn0Var.b(po0Var.n().toByteArray(), new byte[0]), xp0.a());
            a(cp0VarA);
            return cp0VarA;
        } catch (InvalidProtocolBufferException unused) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
    }

    public static void a(cp0 cp0Var) throws GeneralSecurityException {
        if (cp0Var == null || cp0Var.o() <= 0) {
            throw new GeneralSecurityException("empty keyset");
        }
    }

    public static void a(po0 po0Var) throws GeneralSecurityException {
        if (po0Var == null || po0Var.n().size() == 0) {
            throw new GeneralSecurityException("empty keyset");
        }
    }

    public final <B, P> P a(Class<P> cls, Class<B> cls2) throws GeneralSecurityException {
        return (P) xn0.a(xn0.a(this, cls2), cls);
    }

    public <P> P a(Class<P> cls) throws GeneralSecurityException {
        Class<?> clsA = xn0.a((Class<?>) cls);
        if (clsA != null) {
            return (P) a(cls, clsA);
        }
        throw new GeneralSecurityException("No wrapper found for " + cls.getName());
    }
}
