package supwisdom;

import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Log;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.Arrays;
import javax.annotation.concurrent.GuardedBy;
import javax.crypto.KeyGenerator;

/* JADX INFO: compiled from: AndroidKeystoreKmsClient.java */
/* JADX INFO: loaded from: classes.dex */
public final class bo0 {
    public static final String c = "bo0";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f7086a;

    @GuardedBy("this")
    public KeyStore b;

    public static void c(String str) throws GeneralSecurityException {
        if (new bo0().b(str)) {
            throw new IllegalArgumentException(String.format("cannot generate a new key %s because it already exists; please delete it with deleteKey() and try again", str));
        }
        String strA = is0.a("android-keystore://", str);
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "AndroidKeyStore");
        keyGenerator.init(new KeyGenParameterSpec.Builder(strA, 3).setKeySize(256).setBlockModes("GCM").setEncryptionPaddings("NoPadding").build());
        keyGenerator.generateKey();
    }

    public synchronized boolean b(String str) throws GeneralSecurityException {
        String strA;
        strA = is0.a("android-keystore://", str);
        try {
        } catch (NullPointerException unused) {
            Log.w(c, "Keystore is temporarily unavailable, wait 20ms, reinitialize Keystore and try again.");
            try {
                Thread.sleep(20L);
                KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
                this.b = keyStore;
                keyStore.load(null);
            } catch (IOException e2) {
                throw new GeneralSecurityException(e2);
            } catch (InterruptedException unused2) {
            }
            return this.b.containsAlias(strA);
        }
        return this.b.containsAlias(strA);
    }

    /* JADX INFO: compiled from: AndroidKeystoreKmsClient.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f7087a = null;
        public KeyStore b;

        public b() {
            this.b = null;
            if (!bo0.b()) {
                throw new IllegalStateException("need Android Keystore on Android M or newer");
            }
            try {
                KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
                this.b = keyStore;
                keyStore.load(null);
            } catch (IOException | GeneralSecurityException e2) {
                throw new IllegalStateException(e2);
            }
        }

        public b a(KeyStore keyStore) {
            if (keyStore == null) {
                throw new IllegalArgumentException("val cannot be null");
            }
            this.b = keyStore;
            return this;
        }

        public bo0 a() {
            return new bo0(this);
        }
    }

    public bo0() throws GeneralSecurityException {
        this(new b());
    }

    public synchronized kn0 a(String str) throws GeneralSecurityException {
        ao0 ao0Var;
        if (this.f7086a != null && !this.f7086a.equals(str)) {
            throw new GeneralSecurityException(String.format("this client is bound to %s, cannot load keys bound to %s", this.f7086a, str));
        }
        ao0Var = new ao0(is0.a("android-keystore://", str), this.b);
        a(ao0Var);
        return ao0Var;
    }

    public bo0(b bVar) {
        this.f7086a = bVar.f7087a;
        this.b = bVar.b;
    }

    public static kn0 a(kn0 kn0Var) throws GeneralSecurityException {
        byte[] bArrA = gs0.a(10);
        byte[] bArr = new byte[0];
        if (Arrays.equals(bArrA, kn0Var.b(kn0Var.a(bArrA, bArr), bArr))) {
            return kn0Var;
        }
        throw new KeyStoreException("cannot use Android Keystore: encryption/decryption of non-empty message and empty aad returns an incorrect result");
    }

    public static boolean b() {
        return Build.VERSION.SDK_INT >= 23;
    }
}
