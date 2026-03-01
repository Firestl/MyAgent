package supwisdom;

import com.taobao.weex.common.WXRequest;
import com.taobao.weex.wson.Wson;
import dc.squareup.okhttp3.internal.http2.Http2Connection;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bouncycastle.jcajce.provider.asymmetric.edec.KeyFactorySpi;
import supwisdom.s30;

/* JADX INFO: compiled from: OpusReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class r30 extends s30 {
    public static final int o = x80.g("Opus");
    public static final byte[] p = {79, KeyFactorySpi.Ed25519_type, 117, Wson.STRING_TYPE, 72, Wson.NUMBER_BIG_DECIMAL_TYPE, 97, Wson.NUMBER_DOUBLE_TYPE};
    public boolean n;

    public static boolean b(o80 o80Var) {
        int iB = o80Var.b();
        byte[] bArr = p;
        if (iB < bArr.length) {
            return false;
        }
        byte[] bArr2 = new byte[bArr.length];
        o80Var.a(bArr2, 0, bArr.length);
        return Arrays.equals(bArr2, p);
    }

    @Override // supwisdom.s30
    public void a(boolean z) {
        super.a(z);
        if (z) {
            this.n = false;
        }
    }

    @Override // supwisdom.s30
    public long a(o80 o80Var) {
        return b(a(o80Var.f8644a));
    }

    @Override // supwisdom.s30
    public boolean a(o80 o80Var, long j, s30.b bVar) throws InterruptedException, IOException {
        if (!this.n) {
            byte[] bArrCopyOf = Arrays.copyOf(o80Var.f8644a, o80Var.c());
            int i = bArrCopyOf[9] & 255;
            int i2 = ((bArrCopyOf[11] & 255) << 8) | (bArrCopyOf[10] & 255);
            ArrayList arrayList = new ArrayList(3);
            arrayList.add(bArrCopyOf);
            a(arrayList, i2);
            a(arrayList, 3840);
            bVar.f9118a = com.google.android.exoplayer2.j.a(null, "audio/opus", null, -1, -1, i, 48000, arrayList, null, 0, null);
            this.n = true;
            return true;
        }
        boolean z = o80Var.n() == o;
        o80Var.c(0);
        return z;
    }

    public final void a(List<byte[]> list, int i) {
        list.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong((((long) i) * Http2Connection.DEGRADED_PONG_TIMEOUT_NS) / 48000).array());
    }

    public final long a(byte[] bArr) {
        int i = bArr[0] & 255;
        int i2 = i & 3;
        int i3 = 2;
        if (i2 == 0) {
            i3 = 1;
        } else if (i2 != 1 && i2 != 2) {
            i3 = bArr[1] & 63;
        }
        int i4 = i >> 3;
        return i3 * (i4 >= 16 ? com.igexin.push.c.b.b << r1 : i4 >= 12 ? 10000 << (r1 & 1) : (i4 & 3) == 3 ? WXRequest.DEFAULT_TIMEOUT_MS : 10000 << r1);
    }
}
