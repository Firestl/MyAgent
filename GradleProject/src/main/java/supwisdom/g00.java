package supwisdom;

import android.util.Log;
import com.huawei.secure.android.common.encrypt.hash.HMACSHA256;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: Crypto.java */
/* JADX INFO: loaded from: classes.dex */
public class g00 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Mac f7674a;

    public g00() {
        try {
            this.f7674a = Mac.getInstance(HMACSHA256.b);
        } catch (SecurityException e2) {
            Log.e("Crypto", "Security exception when getting HMAC", e2);
        } catch (NoSuchAlgorithmException unused) {
            Log.e("Crypto", "HMAC SHA256 does not exist");
        }
    }

    public byte[] a(byte[] bArr, byte[] bArr2, int i) {
        try {
            this.f7674a.init(new SecretKeySpec(bArr2, 0, i, HMACSHA256.b));
            return this.f7674a.doFinal(bArr);
        } catch (InvalidKeyException e2) {
            Log.e("Crypto", "Invalid key", e2);
            return null;
        }
    }
}
