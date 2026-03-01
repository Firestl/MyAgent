package org.bouncycastle.pqc.crypto.qtesla;

/* JADX INFO: loaded from: classes3.dex */
public class QTESLASecurityCategory {
    public static final int PROVABLY_SECURE_I = 5;
    public static final int PROVABLY_SECURE_III = 6;

    public static String getName(int i) {
        if (i == 5) {
            return "qTESLA-p-I";
        }
        if (i == 6) {
            return "qTESLA-p-III";
        }
        throw new IllegalArgumentException("unknown security category: " + i);
    }

    public static int getPrivateSize(int i) {
        if (i == 5) {
            return QTesla1p.CRYPTO_SECRETKEYBYTES;
        }
        if (i == 6) {
            return QTesla3p.CRYPTO_SECRETKEYBYTES;
        }
        throw new IllegalArgumentException("unknown security category: " + i);
    }

    public static int getPublicSize(int i) {
        if (i == 5) {
            return QTesla1p.CRYPTO_PUBLICKEYBYTES;
        }
        if (i == 6) {
            return QTesla3p.CRYPTO_PUBLICKEYBYTES;
        }
        throw new IllegalArgumentException("unknown security category: " + i);
    }

    public static int getSignatureSize(int i) {
        if (i == 5) {
            return QTesla1p.CRYPTO_BYTES;
        }
        if (i == 6) {
            return QTesla3p.CRYPTO_BYTES;
        }
        throw new IllegalArgumentException("unknown security category: " + i);
    }

    public static void validate(int i) {
        if (i == 5 || i == 6) {
            return;
        }
        throw new IllegalArgumentException("unknown security category: " + i);
    }
}
