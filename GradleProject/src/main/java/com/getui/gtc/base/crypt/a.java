package com.getui.gtc.base.crypt;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Calendar;
import javax.crypto.SecretKey;
import javax.security.auth.x500.X500Principal;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f2125a = new Object();
    public final Object b = new Object();

    @TargetApi(18)
    private KeyPair a(Context context, String str) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
        AlgorithmParameterSpec algorithmParameterSpecBuild;
        KeyPair keyPairGenerateKeyPair;
        synchronized (this.b) {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
            if (Build.VERSION.SDK_INT >= 23) {
                algorithmParameterSpecBuild = new KeyGenParameterSpec.Builder(str, 3).setBlockModes("ECB").setEncryptionPaddings("PKCS1Padding").setKeySize(1024).build();
            } else {
                Calendar calendar = Calendar.getInstance();
                Calendar calendar2 = Calendar.getInstance();
                calendar2.add(1, 30);
                algorithmParameterSpecBuild = new KeyPairGeneratorSpec.Builder(context).setAlias(str).setSubject(new X500Principal("CN=".concat(String.valueOf(str)))).setSerialNumber(BigInteger.TEN).setStartDate(calendar.getTime()).setEndDate(calendar2.getTime()).build();
            }
            keyPairGenerator.initialize(algorithmParameterSpecBuild);
            keyPairGenerateKeyPair = keyPairGenerator.generateKeyPair();
        }
        return keyPairGenerateKeyPair;
    }

    @TargetApi(18)
    public final KeyPair a(Context context, String str, boolean z) throws NoSuchAlgorithmException, UnrecoverableKeyException, IOException, KeyStoreException, CertificateException, NoSuchProviderException, InvalidAlgorithmParameterException {
        synchronized (this.b) {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            if (keyStore.containsAlias(str) || !z) {
                return new KeyPair(keyStore.getCertificate(str).getPublicKey(), (PrivateKey) keyStore.getKey(str, null));
            }
            return a(context, str);
        }
    }

    @TargetApi(23)
    public final SecretKey a(String str) throws NoSuchAlgorithmException, UnrecoverableKeyException, IOException, KeyStoreException, CertificateException, NoSuchProviderException, InvalidAlgorithmParameterException {
        SecretKey secretKey;
        synchronized (this.f2125a) {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            keyStore.containsAlias(str);
            secretKey = (SecretKey) keyStore.getKey(str, null);
        }
        return secretKey;
    }
}
