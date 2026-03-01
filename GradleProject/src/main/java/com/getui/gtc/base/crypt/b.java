package com.getui.gtc.base.crypt;

import io.dcloud.common.util.Md5Utils;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SecretKey f2126a;

    public b(String str) throws NoSuchAlgorithmException {
        this.f2126a = CryptTools.wrapperKey("RC4", CryptTools.digest(Md5Utils.ALGORITHM, str.getBytes()));
    }

    public final byte[] a(byte[] bArr) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        return CryptTools.encrypt("RC4", this.f2126a, (IvParameterSpec) null, bArr);
    }

    public final byte[] b(byte[] bArr) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        return CryptTools.decrypt("RC4", this.f2126a, (IvParameterSpec) null, bArr);
    }
}
