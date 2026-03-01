package supwisdom;

import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Mac;

/* JADX INFO: compiled from: PrfHmacJce.java */
/* JADX INFO: loaded from: classes.dex */
@Immutable
public final class es0 implements io0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ThreadLocal<Mac> f7519a = new a();
    public final String b;
    public final Key c;
    public final int d;

    /* JADX INFO: compiled from: PrfHmacJce.java */
    public class a extends ThreadLocal<Mac> {
        public a() {
        }

        @Override // java.lang.ThreadLocal
        public Mac initialValue() {
            try {
                Mac macA = as0.g.a(es0.this.b);
                macA.init(es0.this.c);
                return macA;
            } catch (GeneralSecurityException e2) {
                throw new IllegalStateException(e2);
            }
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public es0(String str, Key key) throws GeneralSecurityException {
        this.b = str;
        this.c = key;
        if (key.getEncoded().length < 16) {
            throw new InvalidAlgorithmParameterException("key size too small, need at least 16 bytes");
        }
        byte b = -1;
        switch (str.hashCode()) {
            case -1823053428:
                if (str.equals("HMACSHA1")) {
                    b = 0;
                }
                break;
            case 392315118:
                if (str.equals("HMACSHA256")) {
                    b = 1;
                }
                break;
            case 392316170:
                if (str.equals("HMACSHA384")) {
                    b = 2;
                }
                break;
            case 392317873:
                if (str.equals("HMACSHA512")) {
                    b = 3;
                }
                break;
        }
        if (b == 0) {
            this.d = 20;
        } else if (b == 1) {
            this.d = 32;
        } else if (b == 2) {
            this.d = 48;
        } else {
            if (b != 3) {
                throw new NoSuchAlgorithmException("unknown Hmac algorithm: " + str);
            }
            this.d = 64;
        }
        this.f7519a.get();
    }

    @Override // supwisdom.io0
    public byte[] a(byte[] bArr, int i) throws GeneralSecurityException {
        if (i > this.d) {
            throw new InvalidAlgorithmParameterException("tag size too big");
        }
        this.f7519a.get().update(bArr);
        return Arrays.copyOf(this.f7519a.get().doFinal(), i);
    }
}
