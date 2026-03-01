package supwisdom;

import android.net.Uri;
import com.huawei.secure.android.common.encrypt.keystore.aes.AesCbcKS;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: Aes128DataSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class ab0 implements s70 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s70 f6881a;
    public final byte[] b;
    public final byte[] c;
    public CipherInputStream d;

    public ab0(s70 s70Var, byte[] bArr, byte[] bArr2) {
        this.f6881a = s70Var;
        this.b = bArr;
        this.c = bArr2;
    }

    @Override // supwisdom.s70
    public long a(u70 u70Var) throws IOException {
        try {
            Cipher cipher = Cipher.getInstance(AesCbcKS.c);
            try {
                cipher.init(2, new SecretKeySpec(this.b, "AES"), new IvParameterSpec(this.c));
                this.d = new CipherInputStream(new t70(this.f6881a, u70Var), cipher);
                return -1L;
            } catch (InvalidAlgorithmParameterException | InvalidKeyException e2) {
                throw new RuntimeException(e2);
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e3) {
            throw new RuntimeException(e3);
        }
    }

    @Override // supwisdom.s70
    public Uri b() {
        return this.f6881a.b();
    }

    @Override // supwisdom.s70
    public void a() throws IOException {
        this.d = null;
        this.f6881a.a();
    }

    @Override // supwisdom.s70
    public int a(byte[] bArr, int i, int i2) throws IOException {
        e80.b(this.d != null);
        int i3 = this.d.read(bArr, i, i2);
        if (i3 < 0) {
            return -1;
        }
        return i3;
    }
}
