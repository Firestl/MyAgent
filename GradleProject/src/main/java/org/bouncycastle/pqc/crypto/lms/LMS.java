package org.bouncycastle.pqc.crypto.lms;

import org.bouncycastle.crypto.Digest;

/* JADX INFO: loaded from: classes3.dex */
public class LMS {
    public static final short D_INTR = -31869;
    public static final short D_LEAF = -32126;

    public static byte[] algorithm6a(LMSSignature lMSSignature, byte[] bArr, int i, byte[] bArr2) {
        int q = lMSSignature.getQ();
        if (lMSSignature.getOtsSignature().getType().getType() != i) {
            throw new IllegalArgumentException("ots type from lsm signature does not match ots signature type from embedded ots signature");
        }
        LMSigParameters parameter = lMSSignature.getParameter();
        parameter.getM();
        int h = parameter.getH();
        byte[][] y = lMSSignature.getY();
        byte[] bArrLm_ots_validate_signature_calculate = LM_OTS.lm_ots_validate_signature_calculate(LMOtsParameters.getParametersForType(i), bArr, q, lMSSignature.getOtsSignature(), bArr2, false);
        int i2 = (1 << h) + q;
        Digest digest = DigestUtil.getDigest(parameter.getDigestOID());
        int digestSize = digest.getDigestSize();
        byte[] bArr3 = new byte[digestSize];
        digest.update(bArr, 0, bArr.length);
        LmsUtils.u32str(i2, digest);
        LmsUtils.u16str(D_LEAF, digest);
        digest.update(bArrLm_ots_validate_signature_calculate, 0, bArrLm_ots_validate_signature_calculate.length);
        digest.doFinal(bArr3, 0);
        int i3 = 0;
        while (i2 > 1) {
            if ((i2 & 1) == 1) {
                digest.update(bArr, 0, bArr.length);
                LmsUtils.u32str(i2 / 2, digest);
                LmsUtils.u16str(D_INTR, digest);
                digest.update(y[i3], 0, y[i3].length);
                digest.update(bArr3, 0, digestSize);
            } else {
                digest.update(bArr, 0, bArr.length);
                LmsUtils.u32str(i2 / 2, digest);
                LmsUtils.u16str(D_INTR, digest);
                digest.update(bArr3, 0, digestSize);
                digest.update(y[i3], 0, y[i3].length);
            }
            digest.doFinal(bArr3, 0);
            i2 /= 2;
            i3++;
        }
        return bArr3;
    }

    public static LMSPrivateKeyParameters generateKeys(LMSigParameters lMSigParameters, LMOtsParameters lMOtsParameters, int i, byte[] bArr, byte[] bArr2) throws IllegalArgumentException {
        if (bArr2 != null && bArr2.length >= lMSigParameters.getM()) {
            return new LMSPrivateKeyParameters(lMSigParameters, lMOtsParameters, i, bArr, 1 << lMSigParameters.getH(), bArr2);
        }
        throw new IllegalArgumentException("root seed is less than " + lMSigParameters.getM());
    }

    public static LMSSignature generateSign(LMSPrivateKeyParameters lMSPrivateKeyParameters, byte[] bArr) {
        LMSigParameters sigParameters = lMSPrivateKeyParameters.getSigParameters();
        int h = sigParameters.getH();
        int index = lMSPrivateKeyParameters.getIndex();
        LMOtsSignature lMOtsSignatureLm_ots_generate_signature = LM_OTS.lm_ots_generate_signature(lMSPrivateKeyParameters.getNextOtsPrivateKey(), bArr, false);
        int i = (1 << h) + index;
        byte[][] bArr2 = new byte[h][];
        for (int i2 = 0; i2 < h; i2++) {
            bArr2[i2] = lMSPrivateKeyParameters.findT((i / (1 << i2)) ^ 1);
        }
        return new LMSSignature(index, lMOtsSignatureLm_ots_generate_signature, sigParameters, bArr2);
    }

    public static boolean verifySignature(LMSPublicKeyParameters lMSPublicKeyParameters, LMSSignature lMSSignature, byte[] bArr) {
        return lMSPublicKeyParameters.matchesT1(algorithm6a(lMSSignature, lMSPublicKeyParameters.refI(), lMSPublicKeyParameters.getOtsParameters().getType(), bArr));
    }
}
