package supwisdom;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.ProviderException;
import javax.annotation.concurrent.GuardedBy;
import supwisdom.bo0;

/* JADX INFO: compiled from: AndroidKeysetManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class zn0 {
    public static final String b = "zn0";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @GuardedBy("this")
    public rn0 f10016a;

    /* JADX INFO: compiled from: AndroidKeysetManager.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f10017a;

        static {
            int[] iArr = new int[OutputPrefixType.values().length];
            f10017a = iArr;
            try {
                iArr[OutputPrefixType.TINK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10017a[OutputPrefixType.LEGACY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10017a[OutputPrefixType.RAW.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f10017a[OutputPrefixType.CRUNCHY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: compiled from: AndroidKeysetManager.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public sn0 f10018a = null;
        public tn0 b = null;
        public String c = null;
        public kn0 d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f10019e = true;
        public KeyTemplate f = null;
        public KeyStore g = null;

        @GuardedBy("this")
        public rn0 h;

        public final kn0 d() throws GeneralSecurityException {
            bo0 bo0Var;
            if (!zn0.d()) {
                Log.w(zn0.b, "Android Keystore requires at least Android M");
                return null;
            }
            if (this.g != null) {
                bo0.b bVar = new bo0.b();
                bVar.a(this.g);
                bo0Var = bVar.a();
            } else {
                bo0Var = new bo0();
            }
            boolean zB = bo0Var.b(this.c);
            if (!zB) {
                try {
                    bo0.c(this.c);
                } catch (GeneralSecurityException | ProviderException e2) {
                    Log.w(zn0.b, "cannot use Android Keystore, it'll be disabled", e2);
                    return null;
                }
            }
            try {
                return bo0Var.a(this.c);
            } catch (GeneralSecurityException | ProviderException e3) {
                if (zB) {
                    throw new KeyStoreException(String.format("the master key %s exists but is unusable", this.c), e3);
                }
                Log.w(zn0.b, "cannot use Android Keystore, it'll be disabled", e3);
                return null;
            }
        }

        public b a(Context context, String str, String str2) throws IOException {
            if (context == null) {
                throw new IllegalArgumentException("need an Android context");
            }
            if (str == null) {
                throw new IllegalArgumentException("need a keyset name");
            }
            this.f10018a = new co0(context, str, str2);
            this.b = new do0(context, str, str2);
            return this;
        }

        public final rn0 b() throws GeneralSecurityException, IOException {
            kn0 kn0Var = this.d;
            if (kn0Var != null) {
                try {
                    return rn0.a(qn0.a(this.f10018a, kn0Var));
                } catch (InvalidProtocolBufferException | GeneralSecurityException e2) {
                    Log.w(zn0.b, "cannot decrypt keyset: ", e2);
                }
            }
            return rn0.a(ln0.a(this.f10018a));
        }

        public final rn0 c() throws GeneralSecurityException, IOException {
            try {
                return b();
            } catch (FileNotFoundException e2) {
                Log.w(zn0.b, "keyset not found, will generate a new one", e2);
                if (this.f == null) {
                    throw new GeneralSecurityException("cannot read or generate keyset");
                }
                rn0 rn0VarD = rn0.d();
                rn0VarD.a(this.f);
                rn0VarD.b(rn0VarD.a().b().b(0).n());
                if (this.d != null) {
                    rn0VarD.a().a(this.b, this.d);
                } else {
                    ln0.a(rn0VarD.a(), this.b);
                }
                return rn0VarD;
            }
        }

        public b a(String str) {
            if (str.startsWith("android-keystore://")) {
                if (this.f10019e) {
                    this.c = str;
                    return this;
                }
                throw new IllegalArgumentException("cannot call withMasterKeyUri() after calling doNotUseKeystore()");
            }
            throw new IllegalArgumentException("key URI must start with android-keystore://");
        }

        public b a(KeyTemplate keyTemplate) {
            this.f = keyTemplate;
            return this;
        }

        public synchronized zn0 a() throws GeneralSecurityException, IOException {
            if (this.c != null) {
                this.d = d();
            }
            this.h = c();
            return new zn0(this, null);
        }
    }

    public /* synthetic */ zn0(b bVar, a aVar) throws GeneralSecurityException, IOException {
        this(bVar);
    }

    public static boolean d() {
        return Build.VERSION.SDK_INT >= 23;
    }

    public synchronized qn0 a() throws GeneralSecurityException {
        return this.f10016a.a();
    }

    public zn0(b bVar) throws GeneralSecurityException, IOException {
        tn0 unused = bVar.b;
        kn0 unused2 = bVar.d;
        this.f10016a = bVar.h;
    }
}
