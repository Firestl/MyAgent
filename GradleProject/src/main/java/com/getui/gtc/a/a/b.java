package com.getui.gtc.a.a;

import android.os.Process;
import android.text.TextUtils;
import android.util.Base64;
import io.dcloud.common.util.Md5Utils;
import java.net.HttpURLConnection;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.HashMap;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public f f2088a;
    public HttpURLConnection b;
    public boolean c = false;
    public SecretKeySpec d;

    public b(f fVar) {
        this.f2088a = fVar;
        byte[] bArr = new byte[16];
        new SecureRandom().nextBytes(bArr);
        this.d = new SecretKeySpec(bArr, "AES/CFB/NoPadding");
    }

    private void a() {
        HttpURLConnection httpURLConnection = this.b;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
                this.b = null;
            } catch (Throwable th) {
                com.getui.gtc.i.c.a.b(th);
            }
        }
    }

    private byte[] a(HttpURLConnection httpURLConnection, byte[] bArr) {
        try {
            if (!this.f2088a.d) {
                return this.f2088a.g ? p.b(c.a(bArr)) : bArr;
            }
            String headerField = httpURLConnection.getHeaderField("GT_ERR");
            if (headerField != null && "0".equals(headerField)) {
                String headerField2 = httpURLConnection.getHeaderField("GT_T");
                if (headerField2 == null) {
                    throw new SecurityException("sdk config response error, GT_T header not found");
                }
                byte[] bytes = headerField2.getBytes();
                MessageDigest messageDigest = MessageDigest.getInstance(Md5Utils.ALGORITHM);
                messageDigest.update(bytes);
                IvParameterSpec ivParameterSpec = new IvParameterSpec(messageDigest.digest());
                String headerField3 = httpURLConnection.getHeaderField("GT_C_S");
                if (headerField3 == null) {
                    throw new SecurityException("sdk config response error, GT_C_S header not found");
                }
                byte[] bArrDecode = Base64.decode(headerField3, 2);
                Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
                cipher.init(2, this.d, ivParameterSpec);
                byte[] bArrDoFinal = cipher.doFinal(bArr);
                byte[] bArr2 = new byte[bArrDoFinal.length + bytes.length];
                System.arraycopy(bytes, 0, bArr2, 0, bytes.length);
                System.arraycopy(bArrDoFinal, 0, bArr2, bytes.length, bArrDoFinal.length);
                MessageDigest messageDigest2 = MessageDigest.getInstance("SHA1");
                messageDigest2.update(bArr2);
                if (Arrays.equals(messageDigest2.digest(), bArrDecode)) {
                    return bArrDoFinal;
                }
                throw new SecurityException("sdk config response error, response body sign check failed");
            }
            if (headerField != null) {
                throw new SecurityException("sdk config response error, error code is ".concat(String.valueOf(headerField)));
            }
            throw new SecurityException("sdk config response error, GT_ERR header not found");
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
            return null;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:16|(2:120|17)|(11:19|(1:21)|22|135|23|24|125|25|(4:26|(1:28)(1:146)|92|93)|29|(8:31|133|32|(2:143|37)|118|41|45|46))(3:51|(1:53)|54)|129|55|(2:139|60)|(2:114|65)|92|93) */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0132, code lost:
    
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0133, code lost:
    
        com.getui.gtc.i.c.a.b(r2);
     */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0163 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x016d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0177 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0155 A[Catch: all -> 0x0183, TryCatch #0 {all -> 0x0183, blocks: (B:73:0x0151, B:75:0x0155, B:76:0x015a), top: B:112:0x0151 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private byte[] a(java.util.Map<java.lang.String, java.util.List<java.lang.String>> r9) {
        /*
            Method dump skipped, instruction units count: 422
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.a.a.b.a(java.util.Map):byte[]");
    }

    private byte[] a(byte[] bArr) {
        try {
            byte[] bArrA = g.a(bArr);
            byte[] bArr2 = new byte[0];
            if (bArrA == null) {
                bArrA = bArr2;
            }
            String strValueOf = String.valueOf(System.currentTimeMillis());
            byte[] bArr3 = new byte[16];
            new SecureRandom().nextBytes(bArr3);
            PublicKey publicKeyGeneratePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(com.igexin.push.a.k, 0)));
            Cipher cipher = Cipher.getInstance("RSA/NONE/OAEPWithSHA1AndMGF1Padding");
            cipher.init(1, publicKeyGeneratePublic);
            byte[] bArrDoFinal = cipher.doFinal(this.d.getEncoded());
            byte[] bArr4 = new byte[bArrDoFinal.length + 16];
            System.arraycopy(bArr3, 0, bArr4, 0, 16);
            System.arraycopy(bArrDoFinal, 0, bArr4, 16, bArrDoFinal.length);
            String strEncodeToString = Base64.encodeToString(bArr4, 2);
            byte[] bytes = strValueOf.getBytes();
            byte[] bArr5 = new byte[bytes.length + bArrA.length];
            System.arraycopy(bytes, 0, bArr5, 0, bytes.length);
            System.arraycopy(bArrA, 0, bArr5, bytes.length, bArrA.length);
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(bArr5);
            String strEncodeToString2 = Base64.encodeToString(messageDigest.digest(), 2);
            MessageDigest messageDigest2 = MessageDigest.getInstance(Md5Utils.ALGORITHM);
            messageDigest2.update(strEncodeToString2.getBytes());
            IvParameterSpec ivParameterSpec = new IvParameterSpec(messageDigest2.digest());
            Cipher cipher2 = Cipher.getInstance("AES/CFB/NoPadding");
            cipher2.init(1, this.d, ivParameterSpec);
            byte[] bArrDoFinal2 = cipher2.doFinal(bArrA);
            this.b.addRequestProperty("GT_T", strValueOf);
            this.b.addRequestProperty("GT_C_T", "1");
            this.b.addRequestProperty("GT_C_K", com.igexin.push.a.j);
            this.b.addRequestProperty("GT_C_V", strEncodeToString);
            this.b.addRequestProperty("GT_C_S", strEncodeToString2);
            return bArrDoFinal2;
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x01ad A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x01b7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01a3 A[Catch: all -> 0x01c3, TryCatch #0 {all -> 0x01c3, blocks: (B:63:0x019f, B:65:0x01a3, B:66:0x01a8), top: B:92:0x019f }] */
    @android.annotation.TargetApi(21)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private byte[] b(java.util.Map<java.lang.String, java.util.List<java.lang.String>> r10) {
        /*
            Method dump skipped, instruction units count: 476
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.a.a.b.b(java.util.Map):byte[]");
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            Process.setThreadPriority(10);
            if (this.c) {
                return;
            }
            this.c = false;
            if (this.f2088a == null || TextUtils.isEmpty(this.f2088a.f2092a)) {
                return;
            }
            HashMap map = new HashMap();
            byte[] bArrB = this.f2088a.b == null ? b(map) : a(map);
            if (bArrB != null) {
                this.f2088a.a(map, bArrB);
            }
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
        }
    }
}
