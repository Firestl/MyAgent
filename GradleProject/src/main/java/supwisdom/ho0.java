package supwisdom;

import com.google.crypto.tink.proto.OutputPrefixType;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Logger;
import supwisdom.vn0;

/* JADX INFO: compiled from: MacWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class ho0 implements wn0<un0, un0> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f7858a = Logger.getLogger(ho0.class.getName());

    /* JADX INFO: compiled from: MacWrapper.java */
    public static class b implements un0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final vn0<un0> f7859a;
        public final byte[] b;

        @Override // supwisdom.un0
        public void a(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
            if (bArr.length <= 5) {
                throw new GeneralSecurityException("tag too short");
            }
            byte[] bArrCopyOf = Arrays.copyOf(bArr, 5);
            byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, 5, bArr.length);
            for (vn0.b<un0> bVar : this.f7859a.a(bArrCopyOf)) {
                try {
                    if (bVar.b().equals(OutputPrefixType.LEGACY)) {
                        bVar.c().a(bArrCopyOfRange, zr0.a(bArr2, this.b));
                        return;
                    } else {
                        bVar.c().a(bArrCopyOfRange, bArr2);
                        return;
                    }
                } catch (GeneralSecurityException e2) {
                    ho0.f7858a.info("tag prefix matches a key, but cannot verify: " + e2);
                }
            }
            Iterator<vn0.b<un0>> it = this.f7859a.b().iterator();
            while (it.hasNext()) {
                try {
                    it.next().c().a(bArr, bArr2);
                    return;
                } catch (GeneralSecurityException unused) {
                }
            }
            throw new GeneralSecurityException("invalid MAC");
        }

        public b(vn0<un0> vn0Var) {
            this.b = new byte[]{0};
            this.f7859a = vn0Var;
        }
    }

    public static void d() throws GeneralSecurityException {
        xn0.a((wn0) new ho0());
    }

    @Override // supwisdom.wn0
    public Class<un0> b() {
        return un0.class;
    }

    @Override // supwisdom.wn0
    public un0 a(vn0<un0> vn0Var) throws GeneralSecurityException {
        return new b(vn0Var);
    }

    @Override // supwisdom.wn0
    public Class<un0> a() {
        return un0.class;
    }
}
