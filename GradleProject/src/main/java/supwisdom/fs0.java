package supwisdom;

import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;

/* JADX INFO: compiled from: PrfMac.java */
/* JADX INFO: loaded from: classes.dex */
@Immutable
public class fs0 implements un0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final io0 f7640a;
    public final int b;

    public fs0(io0 io0Var, int i) throws GeneralSecurityException {
        this.f7640a = io0Var;
        this.b = i;
        if (i < 10) {
            throw new InvalidAlgorithmParameterException("tag size too small, need at least 10 bytes");
        }
        io0Var.a(new byte[0], i);
    }

    public byte[] a(byte[] bArr) throws GeneralSecurityException {
        return this.f7640a.a(bArr, this.b);
    }

    @Override // supwisdom.un0
    public void a(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (!zr0.a(a(bArr2), bArr)) {
            throw new GeneralSecurityException("invalid MAC");
        }
    }
}
