package supwisdom;

import com.baidu.speech.utils.cuid.util.DeviceId;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/* JADX INFO: loaded from: classes3.dex */
public class hq1 {
    public static String a(String str, String str2) throws InvalidKeySpecException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        byte[] bArr;
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] bytes = str2.getBytes(Charset.forName("UTF-8"));
        byte[] bArr2 = gq1.f7765a;
        byte[] bArr3 = new byte[bytes.length];
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            boolean z = true;
            if (i2 >= bytes.length) {
                break;
            }
            byte b = bytes[i2];
            if (b != 61 && gq1.b[b] == -1) {
                z = false;
            }
            if (z) {
                bArr3[i3] = bytes[i2];
                i3++;
            }
            i2++;
        }
        byte[] bArr4 = new byte[i3];
        System.arraycopy(bArr3, 0, bArr4, 0, i3);
        if (i3 == 0) {
            bArr = new byte[0];
        } else {
            int i4 = i3 / 4;
            while (true) {
                if (bArr4[i3 - 1] == 61) {
                    i3--;
                    if (i3 == 0) {
                        bArr = new byte[0];
                        break;
                    }
                } else {
                    byte[] bArr5 = new byte[i3 - i4];
                    int i5 = 0;
                    for (int i6 = 0; i6 < i4; i6++) {
                        int i7 = i6 * 4;
                        byte b2 = bArr4[i7 + 2];
                        byte b3 = bArr4[i7 + 3];
                        byte[] bArr6 = gq1.b;
                        byte b4 = bArr6[bArr4[i7]];
                        byte b5 = bArr6[bArr4[i7 + 1]];
                        if (b2 != 61 && b3 != 61) {
                            byte b6 = bArr6[b2];
                            byte b7 = bArr6[b3];
                            bArr5[i5] = (byte) ((b4 << 2) | (b5 >> 4));
                            bArr5[i5 + 1] = (byte) (((b5 & 15) << 4) | ((b6 >> 2) & 15));
                            bArr5[i5 + 2] = (byte) ((b6 << 6) | b7);
                        } else if (b2 == 61) {
                            bArr5[i5] = (byte) ((b5 >> 4) | (b4 << 2));
                        } else if (b3 == 61) {
                            byte b8 = bArr6[b2];
                            bArr5[i5] = (byte) ((b4 << 2) | (b5 >> 4));
                            bArr5[i5 + 1] = (byte) (((b5 & 15) << 4) | ((b8 >> 2) & 15));
                        }
                        i5 += 3;
                    }
                    bArr = bArr5;
                }
            }
        }
        PublicKey publicKeyGeneratePublic = keyFactory.generatePublic(new X509EncodedKeySpec(bArr));
        Cipher cipher = Cipher.getInstance(DeviceId.RSA_ALGORITHM);
        cipher.init(1, publicKeyGeneratePublic);
        int length = str.getBytes().length;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i8 = 0;
        int i9 = 0;
        while (true) {
            int i10 = length - i8;
            if (i10 <= 0) {
                break;
            }
            byte[] bArrDoFinal = i10 > 117 ? cipher.doFinal(str.getBytes(), i8, 117) : cipher.doFinal(str.getBytes(), i8, i10);
            byteArrayOutputStream.write(bArrDoFinal, 0, bArrDoFinal.length);
            i9++;
            i8 = i9 * 117;
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        byte[] bArr7 = gq1.f7765a;
        int length2 = byteArray.length * 8;
        int i11 = length2 % 24;
        int i12 = length2 / 24;
        byte[] bArr8 = new byte[i11 != 0 ? (i12 + 1) * 4 : i12 * 4];
        int i13 = 0;
        while (i < i12) {
            int i14 = i * 3;
            byte b9 = byteArray[i14];
            byte b10 = byteArray[i14 + 1];
            byte b11 = byteArray[i14 + 2];
            byte b12 = (byte) (b10 & 15);
            byte b13 = (byte) (b9 & 3);
            int i15 = b9 & com.igexin.c.a.d.g.n;
            int i16 = b9 >> 2;
            if (i15 != 0) {
                i16 ^= 192;
            }
            byte b14 = (byte) i16;
            int i17 = b10 & com.igexin.c.a.d.g.n;
            int i18 = b10 >> 4;
            if (i17 != 0) {
                i18 ^= 240;
            }
            byte b15 = (byte) i18;
            int i19 = b11 >> 6;
            if ((b11 & com.igexin.c.a.d.g.n) != 0) {
                i19 ^= 252;
            }
            byte[] bArr9 = gq1.c;
            bArr8[i13] = bArr9[b14];
            bArr8[i13 + 1] = bArr9[b15 | (b13 << 4)];
            bArr8[i13 + 2] = bArr9[((byte) i19) | (b12 << 2)];
            bArr8[i13 + 3] = bArr9[b11 & 63];
            i13 += 4;
            i++;
        }
        int i20 = i * 3;
        if (i11 == 8) {
            byte b16 = byteArray[i20];
            byte b17 = (byte) (b16 & 3);
            int i21 = b16 & com.igexin.c.a.d.g.n;
            int i22 = b16 >> 2;
            if (i21 != 0) {
                i22 ^= 192;
            }
            byte[] bArr10 = gq1.c;
            bArr8[i13] = bArr10[(byte) i22];
            bArr8[i13 + 1] = bArr10[b17 << 4];
            bArr8[i13 + 2] = 61;
            bArr8[i13 + 3] = 61;
        } else if (i11 == 16) {
            byte b18 = byteArray[i20];
            byte b19 = byteArray[i20 + 1];
            byte b20 = (byte) (b19 & 15);
            byte b21 = (byte) (b18 & 3);
            int i23 = b18 & com.igexin.c.a.d.g.n;
            int i24 = b18 >> 2;
            if (i23 != 0) {
                i24 ^= 192;
            }
            byte b22 = (byte) i24;
            int i25 = b19 & com.igexin.c.a.d.g.n;
            int i26 = b19 >> 4;
            if (i25 != 0) {
                i26 ^= 240;
            }
            byte[] bArr11 = gq1.c;
            bArr8[i13] = bArr11[b22];
            bArr8[i13 + 1] = bArr11[((byte) i26) | (b21 << 4)];
            bArr8[i13 + 2] = bArr11[b20 << 2];
            bArr8[i13 + 3] = 61;
        }
        return new String(bArr8, Charset.forName("UTF-8"));
    }
}
