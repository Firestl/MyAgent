package supwisdom;

import android.util.Log;
import android.util.Pair;
import java.nio.ByteBuffer;
import java.util.UUID;

/* JADX INFO: compiled from: PsshAtomUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class f30 {
    public static byte[] a(UUID uuid, byte[] bArr) {
        int length = bArr.length + 32;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(length);
        byteBufferAllocate.putInt(length);
        byteBufferAllocate.putInt(y20.V);
        byteBufferAllocate.putInt(0);
        byteBufferAllocate.putLong(uuid.getMostSignificantBits());
        byteBufferAllocate.putLong(uuid.getLeastSignificantBits());
        byteBufferAllocate.putInt(bArr.length);
        byteBufferAllocate.put(bArr);
        return byteBufferAllocate.array();
    }

    public static Pair<UUID, byte[]> b(byte[] bArr) {
        o80 o80Var = new o80(bArr);
        if (o80Var.c() < 32) {
            return null;
        }
        o80Var.c(0);
        if (o80Var.n() != o80Var.b() + 4 || o80Var.n() != y20.V) {
            return null;
        }
        int iA = y20.a(o80Var.n());
        if (iA > 1) {
            Log.w("PsshAtomUtil", "Unsupported pssh version: " + iA);
            return null;
        }
        UUID uuid = new UUID(o80Var.p(), o80Var.p());
        if (iA == 1) {
            o80Var.d(o80Var.t() * 16);
        }
        int iT = o80Var.t();
        if (iT != o80Var.b()) {
            return null;
        }
        byte[] bArr2 = new byte[iT];
        o80Var.a(bArr2, 0, iT);
        return Pair.create(uuid, bArr2);
    }

    public static UUID a(byte[] bArr) {
        Pair<UUID, byte[]> pairB = b(bArr);
        if (pairB == null) {
            return null;
        }
        return (UUID) pairB.first;
    }
}
