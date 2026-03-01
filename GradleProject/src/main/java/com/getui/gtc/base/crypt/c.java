package com.getui.gtc.base.crypt;

import android.content.Context;
import com.baidu.speech.utils.cuid.util.DeviceId;
import com.getui.gtc.base.util.io.IOUtils;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public KeyPair f2127a;
    public b b;
    public File c;

    public c(Context context, KeyPair keyPair) throws NoSuchAlgorithmException {
        this.c = context.getFilesDir();
        this.f2127a = keyPair;
        if (keyPair == null) {
            this.b = new b(context.getPackageName());
        }
    }

    public final SecretKey a(String str) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, IOException, InvalidAlgorithmParameterException {
        return a(str, true, this.f2127a);
    }

    public final SecretKey a(String str, boolean z, KeyPair keyPair) throws Throwable {
        byte[] bArrB;
        File file = new File(this.c, str);
        if (!file.exists() && z) {
            SecretKey secretKeyGenerateKey = CryptTools.generateKey("AES", 128);
            byte[] encoded = secretKeyGenerateKey.getEncoded();
            KeyPair keyPair2 = this.f2127a;
            IOUtils.saveToFile(keyPair2 != null ? CryptTools.encrypt(DeviceId.RSA_ALGORITHM, keyPair2.getPublic(), encoded) : this.b.a(encoded), new File(this.c, str));
            return secretKeyGenerateKey;
        }
        try {
            byte[] file2 = IOUtils.readFile(file);
            if (keyPair != null) {
                bArrB = CryptTools.decrypt(DeviceId.RSA_ALGORITHM, keyPair.getPrivate(), file2);
            } else if (this.f2127a != null) {
                keyPair = this.f2127a;
                bArrB = CryptTools.decrypt(DeviceId.RSA_ALGORITHM, keyPair.getPrivate(), file2);
            } else {
                bArrB = this.b.b(file2);
            }
            return CryptTools.wrapperKey("AES", bArrB);
        } catch (IOException | InvalidAlgorithmParameterException unused) {
            return null;
        }
    }

    public final IvParameterSpec b(String str, boolean z, KeyPair keyPair) throws Throwable {
        byte[] bArrB;
        File file = new File(this.c, str);
        if (!file.exists() && z) {
            byte[] bArr = new byte[16];
            new SecureRandom().nextBytes(bArr);
            KeyPair keyPair2 = this.f2127a;
            IOUtils.saveToFile(keyPair2 != null ? CryptTools.encrypt(DeviceId.RSA_ALGORITHM, keyPair2.getPublic(), bArr) : this.b.a(bArr), new File(this.c, str));
            return new IvParameterSpec(bArr);
        }
        try {
            byte[] file2 = IOUtils.readFile(file);
            if (keyPair != null) {
                bArrB = CryptTools.decrypt(DeviceId.RSA_ALGORITHM, keyPair.getPrivate(), file2);
            } else if (this.f2127a != null) {
                keyPair = this.f2127a;
                bArrB = CryptTools.decrypt(DeviceId.RSA_ALGORITHM, keyPair.getPrivate(), file2);
            } else {
                bArrB = this.b.b(file2);
            }
            return new IvParameterSpec(bArrB);
        } catch (IOException | InvalidAlgorithmParameterException unused) {
            return null;
        }
    }
}
