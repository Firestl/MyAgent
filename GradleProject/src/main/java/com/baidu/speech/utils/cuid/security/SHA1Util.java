package com.baidu.speech.utils.cuid.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public final class SHA1Util {
    public static byte[] sha1(byte[] bArr) {
        try {
            return MessageDigest.getInstance(McElieceCCA2KeyGenParameterSpec.SHA1).digest(bArr);
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException(e2);
        }
    }
}
