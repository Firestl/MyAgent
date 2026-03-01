package supwisdom;

import java.security.SecureRandom;

/* JADX INFO: compiled from: Random.java */
/* JADX INFO: loaded from: classes.dex */
public final class gs0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal<SecureRandom> f7768a = new a();

    /* JADX INFO: compiled from: Random.java */
    public class a extends ThreadLocal<SecureRandom> {
        @Override // java.lang.ThreadLocal
        public SecureRandom initialValue() {
            return gs0.b();
        }
    }

    public static SecureRandom b() {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextLong();
        return secureRandom;
    }

    public static byte[] a(int i) {
        byte[] bArr = new byte[i];
        f7768a.get().nextBytes(bArr);
        return bArr;
    }
}
