package supwisdom;

import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: PrfAesCmac.java */
/* JADX INFO: loaded from: classes.dex */
@Immutable
public final class ds0 implements io0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SecretKey f7375a;
    public byte[] b;
    public byte[] c;

    public ds0(byte[] bArr) throws GeneralSecurityException {
        is0.a(bArr.length);
        this.f7375a = new SecretKeySpec(bArr, "AES");
        a();
    }

    public static Cipher b() throws GeneralSecurityException {
        return as0.f.a("AES/ECB/NoPadding");
    }

    @Override // supwisdom.io0
    public byte[] a(byte[] bArr, int i) throws GeneralSecurityException {
        if (i > 16) {
            throw new InvalidAlgorithmParameterException("outputLength too large, max is 16 bytes");
        }
        Cipher cipherB = b();
        cipherB.init(1, this.f7375a);
        int iMax = Math.max(1, (int) Math.ceil(((double) bArr.length) / 16.0d));
        byte[] bArrA = iMax * 16 == bArr.length ? zr0.a(bArr, (iMax - 1) * 16, this.b, 0, 16) : zr0.b(xr0.a(Arrays.copyOfRange(bArr, (iMax - 1) * 16, bArr.length)), this.c);
        byte[] bArrDoFinal = new byte[16];
        for (int i2 = 0; i2 < iMax - 1; i2++) {
            bArrDoFinal = cipherB.doFinal(zr0.a(bArrDoFinal, 0, bArr, i2 * 16, 16));
        }
        return Arrays.copyOf(cipherB.doFinal(zr0.b(bArrA, bArrDoFinal)), i);
    }

    public final void a() throws GeneralSecurityException {
        Cipher cipherB = b();
        cipherB.init(1, this.f7375a);
        byte[] bArrB = xr0.b(cipherB.doFinal(new byte[16]));
        this.b = bArrB;
        this.c = xr0.b(bArrB);
    }
}
