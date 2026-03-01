package supwisdom;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.Mac;

/* JADX INFO: compiled from: FingerprintManagerCompat.java */
/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class e9 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f7452a;

    /* JADX INFO: compiled from: FingerprintManagerCompat.java */
    public class a extends FingerprintManager.AuthenticationCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b f7453a;

        public a(b bVar) {
            this.f7453a = bVar;
        }

        @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
        public void onAuthenticationError(int i, CharSequence charSequence) {
            this.f7453a.onAuthenticationError(i, charSequence);
        }

        @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
        public void onAuthenticationFailed() {
            this.f7453a.onAuthenticationFailed();
        }

        @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
        public void onAuthenticationHelp(int i, CharSequence charSequence) {
            this.f7453a.onAuthenticationHelp(i, charSequence);
        }

        @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult authenticationResult) {
            this.f7453a.onAuthenticationSucceeded(new c(e9.a(authenticationResult.getCryptoObject())));
        }
    }

    /* JADX INFO: compiled from: FingerprintManagerCompat.java */
    public static abstract class b {
        public abstract void onAuthenticationError(int i, CharSequence charSequence);

        public abstract void onAuthenticationFailed();

        public abstract void onAuthenticationHelp(int i, CharSequence charSequence);

        public abstract void onAuthenticationSucceeded(c cVar);
    }

    /* JADX INFO: compiled from: FingerprintManagerCompat.java */
    public static final class c {
        public c(d dVar) {
        }
    }

    public e9(Context context) {
        this.f7452a = context;
    }

    public static e9 a(Context context) {
        return new e9(context);
    }

    public boolean b() {
        FingerprintManager fingerprintManagerB;
        return Build.VERSION.SDK_INT >= 23 && (fingerprintManagerB = b(this.f7452a)) != null && fingerprintManagerB.isHardwareDetected();
    }

    public boolean a() {
        FingerprintManager fingerprintManagerB;
        return Build.VERSION.SDK_INT >= 23 && (fingerprintManagerB = b(this.f7452a)) != null && fingerprintManagerB.hasEnrolledFingerprints();
    }

    /* JADX INFO: compiled from: FingerprintManagerCompat.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Signature f7454a;
        public final Cipher b;
        public final Mac c;

        public d(Signature signature) {
            this.f7454a = signature;
            this.b = null;
            this.c = null;
        }

        public Cipher a() {
            return this.b;
        }

        public Mac b() {
            return this.c;
        }

        public Signature c() {
            return this.f7454a;
        }

        public d(Cipher cipher) {
            this.b = cipher;
            this.f7454a = null;
            this.c = null;
        }

        public d(Mac mac) {
            this.c = mac;
            this.b = null;
            this.f7454a = null;
        }
    }

    public static FingerprintManager b(Context context) {
        int i = Build.VERSION.SDK_INT;
        if (i == 23) {
            return (FingerprintManager) context.getSystemService(FingerprintManager.class);
        }
        if (i <= 23 || !context.getPackageManager().hasSystemFeature("android.hardware.fingerprint")) {
            return null;
        }
        return (FingerprintManager) context.getSystemService(FingerprintManager.class);
    }

    public void a(d dVar, int i, l9 l9Var, b bVar, Handler handler) {
        FingerprintManager fingerprintManagerB;
        if (Build.VERSION.SDK_INT < 23 || (fingerprintManagerB = b(this.f7452a)) == null) {
            return;
        }
        fingerprintManagerB.authenticate(a(dVar), l9Var != null ? (CancellationSignal) l9Var.b() : null, i, a(bVar), handler);
    }

    public static FingerprintManager.CryptoObject a(d dVar) {
        if (dVar == null) {
            return null;
        }
        if (dVar.a() != null) {
            return new FingerprintManager.CryptoObject(dVar.a());
        }
        if (dVar.c() != null) {
            return new FingerprintManager.CryptoObject(dVar.c());
        }
        if (dVar.b() != null) {
            return new FingerprintManager.CryptoObject(dVar.b());
        }
        return null;
    }

    public static d a(FingerprintManager.CryptoObject cryptoObject) {
        if (cryptoObject == null) {
            return null;
        }
        if (cryptoObject.getCipher() != null) {
            return new d(cryptoObject.getCipher());
        }
        if (cryptoObject.getSignature() != null) {
            return new d(cryptoObject.getSignature());
        }
        if (cryptoObject.getMac() != null) {
            return new d(cryptoObject.getMac());
        }
        return null;
    }

    public static FingerprintManager.AuthenticationCallback a(b bVar) {
        return new a(bVar);
    }
}
