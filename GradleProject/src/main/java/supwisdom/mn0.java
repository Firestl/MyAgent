package supwisdom;

import com.google.crypto.tink.proto.OutputPrefixType;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import supwisdom.cp0;

/* JADX INFO: compiled from: CryptoFormat.java */
/* JADX INFO: loaded from: classes.dex */
public final class mn0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f8410a = new byte[0];

    /* JADX INFO: compiled from: CryptoFormat.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f8411a;

        static {
            int[] iArr = new int[OutputPrefixType.values().length];
            f8411a = iArr;
            try {
                iArr[OutputPrefixType.LEGACY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8411a[OutputPrefixType.CRUNCHY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8411a[OutputPrefixType.TINK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8411a[OutputPrefixType.RAW.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static byte[] a(cp0.c cVar) throws GeneralSecurityException {
        int i = a.f8411a[cVar.p().ordinal()];
        if (i == 1 || i == 2) {
            return ByteBuffer.allocate(5).put((byte) 0).putInt(cVar.o()).array();
        }
        if (i == 3) {
            return ByteBuffer.allocate(5).put((byte) 1).putInt(cVar.o()).array();
        }
        if (i == 4) {
            return f8410a;
        }
        throw new GeneralSecurityException("unknown output prefix type");
    }
}
