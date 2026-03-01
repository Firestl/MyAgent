package supwisdom;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.OutputPrefixType;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Iterator;
import javax.annotation.concurrent.GuardedBy;
import supwisdom.cp0;

/* JADX INFO: compiled from: KeysetManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class rn0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @GuardedBy("this")
    public final cp0.b f9075a;

    public rn0(cp0.b bVar) {
        this.f9075a = bVar;
    }

    public static rn0 a(qn0 qn0Var) {
        return new rn0(qn0Var.a().b());
    }

    public static int c() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bArr = new byte[4];
        int i = 0;
        while (i == 0) {
            secureRandom.nextBytes(bArr);
            i = ((bArr[0] & 127) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | (bArr[3] & 255);
        }
        return i;
    }

    public static rn0 d() {
        return new rn0(cp0.s());
    }

    public synchronized rn0 b(int i) throws GeneralSecurityException {
        for (int i2 = 0; i2 < this.f9075a.j(); i2++) {
            cp0.c cVarA = this.f9075a.a(i2);
            if (cVarA.o() == i) {
                if (!cVarA.q().equals(KeyStatusType.ENABLED)) {
                    throw new GeneralSecurityException("cannot set key as primary because it's not enabled: " + i);
                }
                this.f9075a.b(i);
            }
        }
        throw new GeneralSecurityException("key not found: " + i);
        return this;
    }

    public synchronized qn0 a() throws GeneralSecurityException {
        return qn0.b(this.f9075a.build());
    }

    public synchronized rn0 a(KeyTemplate keyTemplate) throws GeneralSecurityException {
        a(keyTemplate.a(), false);
        return this;
    }

    @Deprecated
    public synchronized int a(yo0 yo0Var, boolean z) throws GeneralSecurityException {
        cp0.c cVarA;
        cVarA = a(yo0Var);
        this.f9075a.a(cVarA);
        if (z) {
            this.f9075a.b(cVarA.o());
        }
        return cVarA.o();
    }

    public final synchronized cp0.c a(yo0 yo0Var) throws GeneralSecurityException {
        cp0.c.a aVarT;
        KeyData keyDataA = xn0.a(yo0Var);
        int iB = b();
        OutputPrefixType outputPrefixTypeN = yo0Var.n();
        if (outputPrefixTypeN == OutputPrefixType.UNKNOWN_PREFIX) {
            outputPrefixTypeN = OutputPrefixType.TINK;
        }
        aVarT = cp0.c.t();
        aVarT.a(keyDataA);
        aVarT.a(iB);
        aVarT.a(KeyStatusType.ENABLED);
        aVarT.a(outputPrefixTypeN);
        return aVarT.build();
    }

    public final synchronized int b() {
        int iC;
        iC = c();
        while (a(iC)) {
            iC = c();
        }
        return iC;
    }

    public final synchronized boolean a(int i) {
        Iterator<cp0.c> it = this.f9075a.k().iterator();
        while (it.hasNext()) {
            if (it.next().o() == i) {
                return true;
            }
        }
        return false;
    }
}
