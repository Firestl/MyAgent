package supwisdom;

import com.alibaba.fastjson.parser.JSONLexer;
import com.efs.sdk.base.newsharedpreferences.SharedPreferencesNewImpl;
import com.taobao.weex.wson.Wson;
import com.tencent.rtmp.TXLivePusher;
import dc.squareup.okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.jcajce.provider.asymmetric.edec.KeyFactorySpi;
import org.bouncycastle.util.encoders.UTF8;

/* JADX INFO: loaded from: classes.dex */
public class i0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f7893a = {-42, -112, -23, -2, -52, -31, 61, -73, 22, -74, 20, -62, 40, -5, 44, 5, 43, Wson.NUMBER_BIG_INTEGER_TYPE, -102, 118, 42, -66, 4, -61, -86, 68, 19, 38, 73, -122, 6, -103, -100, 66, UTF8.S_P4A, -12, -111, -17, -104, 122, 51, 84, 11, 67, -19, -49, -84, 98, -28, -77, 28, -87, -55, 8, -24, -107, com.igexin.c.a.d.g.n, -33, -108, -6, 117, -113, 63, -90, 71, 7, -89, -4, -13, Wson.STRING_TYPE, 23, -70, -125, 89, 60, 25, -26, -123, 79, -88, 104, 107, -127, -78, KeyFactorySpi.Ed448_type, Wson.NUMBER_DOUBLE_TYPE, -38, -117, -8, -21, 15, 75, KeyFactorySpi.Ed25519_type, 86, -99, 53, 30, 36, com.umeng.analytics.pro.db.l, 94, 99, 88, -47, -94, 37, 34, 124, 59, 1, PublicSuffixDatabase.EXCEPTION_MARKER, 120, -121, -44, 0, Wson.NUMBER_FLOAT_TYPE, 87, -97, -45, 39, 82, 76, 54, 2, -25, -96, -60, -56, -98, -22, -65, -118, -46, UTF8.S_P3B, -57, 56, -75, -93, -9, TXLivePusher.SEI_MSG_TYPE, -50, -7, 97, 21, -95, com.umeng.analytics.pro.co.k, -82, 93, -92, -101, 52, JSONLexer.EOI, 85, -83, -109, 50, 48, -11, -116, -79, -29, 29, -10, -30, 46, com.umeng.analytics.pro.co.h, Wson.BOOLEAN_TYPE_FALSE, -54, UTF8.S_P4B, -64, 41, 35, -85, 13, 83, 78, KeyFactorySpi.x448_type, -43, -37, 55, 69, -34, -3, -114, 47, 3, -1, 106, 114, 109, Wson.NUMBER_LONG_TYPE, Wson.ARRAY_TYPE, 81, -115, 27, -81, -110, -69, -35, PSSSigner.TRAILER_IMPLICIT, 127, 17, -39, 92, 65, com.umeng.analytics.pro.co.j, 16, 90, -40, 10, -63, 49, -120, -91, -51, Wson.MAP_TYPE, -67, 45, Wson.BOOLEAN_TYPE_TRUE, -48, SharedPreferencesNewImpl.FINISH_MARK, -72, -27, -76, -80, -119, Wson.NUMBER_INT_TYPE, -105, 74, 12, -106, 119, 126, Wson.NUMBER_BIG_DECIMAL_TYPE, -71, -15, 9, -59, KeyFactorySpi.x25519_type, -58, -124, 24, -16, 125, -20, 58, -36, 77, 32, 121, -18, 95, 62, -41, -53, 57, 72};
    public static final int[] b = {-1548633402, 1453994832, 1736282519, -1301273892};
    public static final int[] c = {462357, 472066609, 943670861, 1415275113, 1886879365, -1936483679, -1464879427, -993275175, -521670923, -66909679, 404694573, 876298825, 1347903077, 1819507329, -2003855715, -1532251463, -1060647211, -589042959, -117504499, 337322537, 808926789, 1280531041, 1752135293, -2071227751, -1599623499, -1128019247, -656414995, -184876535, 269950501, 741554753, 1213159005, 1684763257};

    public final byte a(byte b2) {
        return f7893a[b2 & 255];
    }

    public final long a(long j, int i) {
        return (j >> (32 - i)) | (((-1) & j) << i);
    }

    public final long a(byte[] bArr, int i) {
        return (((long) (bArr[i + 3] & 255)) & 4294967295L) | (((long) (bArr[i] & 255)) << 24) | ((long) ((bArr[i + 1] & 255) << 16)) | ((long) ((bArr[i + 2] & 255) << 8));
    }

    public final void a(long j, byte[] bArr, int i) {
        bArr[i] = (byte) ((j >> 24) & 255);
        bArr[i + 1] = (byte) ((j >> 16) & 255);
        bArr[i + 2] = (byte) ((j >> 8) & 255);
        bArr[i + 3] = (byte) (j & 255);
    }

    public final void a(long[] jArr, byte[] bArr, byte[] bArr2) {
        long[] jArr2 = new long[36];
        jArr2[0] = a(bArr, 0);
        jArr2[1] = a(bArr, 4);
        jArr2[2] = a(bArr, 8);
        jArr2[3] = a(bArr, 12);
        int i = 0;
        while (i < 32) {
            long j = jArr2[i];
            int i2 = i + 1;
            long j2 = ((jArr2[i2] ^ jArr2[i + 2]) ^ jArr2[i + 3]) ^ jArr[i];
            byte[] bArr3 = new byte[4];
            a(j2, bArr3, 0);
            long jA = a(new byte[]{a(bArr3[0]), a(bArr3[1]), a(bArr3[2]), a(bArr3[3])}, 0);
            jArr2[i + 4] = ((((jA ^ a(jA, 2)) ^ a(jA, 10)) ^ a(jA, 18)) ^ a(jA, 24)) ^ j;
            i = i2;
        }
        a(jArr2[35], bArr2, 0);
        a(jArr2[34], bArr2, 4);
        a(jArr2[33], bArr2, 8);
        a(jArr2[32], bArr2, 12);
    }

    public byte[] a(j0 j0Var, byte[] bArr, byte[] bArr2) throws Exception {
        if (bArr == null || bArr.length != 16) {
            throw new Exception("iv error!");
        }
        if (bArr2 == null) {
            throw new Exception("input is null!");
        }
        if (j0Var.c && j0Var.f8006a == 1) {
            bArr2 = b(bArr2, 1);
        }
        int length = bArr2.length;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr2);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (j0Var.f8006a == 1) {
            while (length > 0) {
                byte[] bArr3 = new byte[16];
                byte[] bArr4 = new byte[16];
                byte[] bArr5 = new byte[16];
                byteArrayInputStream.read(bArr3);
                for (int i = 0; i < 16; i++) {
                    bArr4[i] = (byte) (bArr3[i] ^ bArr[i]);
                }
                a(j0Var.b, bArr4, bArr5);
                System.arraycopy(bArr5, 0, bArr, 0, 16);
                byteArrayOutputStream.write(bArr5);
                length -= 16;
            }
        } else {
            byte[] bArr6 = new byte[16];
            while (length > 0) {
                byte[] bArr7 = new byte[16];
                byte[] bArr8 = new byte[16];
                byte[] bArr9 = new byte[16];
                byteArrayInputStream.read(bArr7);
                System.arraycopy(bArr7, 0, bArr6, 0, 16);
                a(j0Var.b, bArr7, bArr8);
                for (int i2 = 0; i2 < 16; i2++) {
                    bArr9[i2] = (byte) (bArr8[i2] ^ bArr[i2]);
                }
                System.arraycopy(bArr6, 0, bArr, 0, 16);
                byteArrayOutputStream.write(bArr9);
                length -= 16;
            }
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (j0Var.c && j0Var.f8006a == 0) {
            byteArray = b(byteArray, 0);
        }
        byteArrayInputStream.close();
        byteArrayOutputStream.close();
        return byteArray;
    }

    public void b(j0 j0Var, byte[] bArr) throws Exception {
        if (j0Var == null) {
            throw new Exception("ctx is null!");
        }
        if (bArr == null || bArr.length != 16) {
            throw new Exception("key error!");
        }
        j0Var.f8006a = 1;
        a(j0Var.b, bArr);
    }

    public final byte[] b(byte[] bArr, int i) {
        if (bArr == null) {
            return null;
        }
        if (i != 1) {
            int i2 = bArr[bArr.length - 1];
            byte[] bArr2 = new byte[bArr.length - i2];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length - i2);
            return bArr2;
        }
        int length = 16 - (bArr.length % 16);
        byte[] bArr3 = new byte[bArr.length + length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        bArr3[bArr.length] = com.igexin.c.a.d.g.n;
        for (int i3 = 1; i3 < length; i3++) {
            bArr3[bArr.length + i3] = 0;
        }
        return bArr3;
    }

    public void a(j0 j0Var, byte[] bArr) throws Exception {
        if (j0Var == null) {
            throw new Exception("ctx is null!");
        }
        if (bArr == null || bArr.length != 16) {
            throw new Exception("key error!");
        }
        j0Var.f8006a = 0;
        a(j0Var.b, bArr);
        for (int i = 0; i < 16; i++) {
            long[] jArr = j0Var.b;
            long j = jArr[i];
            int i2 = 31 - i;
            jArr[i] = jArr[i2];
            jArr[i2] = j;
        }
    }

    public final void a(long[] jArr, byte[] bArr) {
        long[] jArr2 = new long[36];
        char c2 = 3;
        long[] jArr3 = {a(bArr, 0), a(bArr, 4), a(bArr, 8), a(bArr, 12)};
        long j = jArr3[0];
        int[] iArr = b;
        jArr2[0] = j ^ ((long) iArr[0]);
        jArr2[1] = jArr3[1] ^ ((long) iArr[1]);
        jArr2[2] = jArr3[2] ^ ((long) iArr[2]);
        jArr2[3] = jArr3[3] ^ ((long) iArr[3]);
        int i = 0;
        while (i < 32) {
            int i2 = i + 4;
            long j2 = jArr2[i];
            int i3 = i + 1;
            byte[] bArr2 = new byte[4];
            byte[] bArr3 = new byte[4];
            a(((jArr2[i3] ^ jArr2[i + 2]) ^ jArr2[i + 3]) ^ ((long) c[i]), bArr2, 0);
            bArr3[0] = a(bArr2[0]);
            bArr3[1] = a(bArr2[1]);
            bArr3[2] = a(bArr2[2]);
            bArr3[c2] = a(bArr2[c2]);
            long jA = a(bArr3, 0);
            jArr2[i2] = j2 ^ (a(jA, 23) ^ (a(jA, 13) ^ jA));
            jArr[i] = jArr2[i2];
            i = i3;
            c2 = 3;
        }
    }
}
