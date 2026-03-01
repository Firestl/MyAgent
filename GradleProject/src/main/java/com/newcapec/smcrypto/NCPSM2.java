package com.newcapec.smcrypto;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.InvalidKeySpecException;
import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithID;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.signers.SM2Signer;
import org.bouncycastle.crypto.signers.StandardDSAEncoding;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.bouncycastle.util.encoders.Hex;

/* JADX INFO: loaded from: classes2.dex */
public class NCPSM2 {
    public NCPSM2() {
        Security.addProvider(new BouncyCastleProvider());
    }

    public int CompressPubKey(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null || bArr.length < 64) {
            return 8001;
        }
        if (bArr2.length < 33) {
            return 8002;
        }
        byte[] bArr3 = new byte[32];
        byte[] bArr4 = new byte[32];
        System.arraycopy(bArr, 0, bArr3, 0, 32);
        System.arraycopy(bArr, 32, bArr4, 0, 32);
        BigInteger bigInteger = new BigInteger(ByteUtils.toHexString(bArr3), 16);
        BigInteger bigInteger2 = new BigInteger(ByteUtils.toHexString(bArr4), 16);
        ECNamedCurveParameterSpec parameterSpec = ECNamedCurveTable.getParameterSpec("sm2p256v1");
        System.arraycopy(new ECDomainParameters(parameterSpec.getCurve(), parameterSpec.getG(), parameterSpec.getN()).getCurve().createPoint(bigInteger, bigInteger2).getEncoded(true), 0, bArr2, 0, 33);
        return 8000;
    }

    public int GenSM2Keypair(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr2 == null || bArr.length < 64 || bArr2.length < 32) {
            return 8002;
        }
        try {
            ECGenParameterSpec eCGenParameterSpec = new ECGenParameterSpec("sm2p256v1");
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", new BouncyCastleProvider());
            keyPairGenerator.initialize(eCGenParameterSpec, new SecureRandom());
            KeyPair keyPairGenerateKeyPair = keyPairGenerator.generateKeyPair();
            ECPublicKey eCPublicKey = (ECPublicKey) keyPairGenerateKeyPair.getPublic();
            ECPrivateKey eCPrivateKey = (ECPrivateKey) keyPairGenerateKeyPair.getPrivate();
            System.arraycopy(eCPublicKey.getQ().getXCoord().getEncoded(), 0, bArr, 0, 32);
            System.arraycopy(eCPublicKey.getQ().getYCoord().getEncoded(), 0, bArr, 32, 32);
            byte[] byteArray = eCPrivateKey.getD().toByteArray();
            if (byteArray.length == 32) {
                System.arraycopy(byteArray, 0, bArr2, 0, 32);
                return 8000;
            }
            if (byteArray.length < 32) {
                System.arraycopy(byteArray, 0, bArr2, 32 - byteArray.length, byteArray.length);
                return 8000;
            }
            System.arraycopy(byteArray, 1, bArr2, 0, 32);
            return 8000;
        } catch (InvalidAlgorithmParameterException e2) {
            e2.printStackTrace();
            return NCPBase.NCPSM_ERR_INVALGPARAM;
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
            return NCPBase.NCPSM_ERR_NOALG;
        } catch (Exception e4) {
            e4.printStackTrace();
            return NCPBase.NCPSM_ERR_UNKNOWN;
        }
    }

    public int GetPriKeyObject(byte[] bArr, ECPrivateKey[] eCPrivateKeyArr) {
        if (bArr == null || eCPrivateKeyArr == null || bArr.length < 32) {
            return 8001;
        }
        if (eCPrivateKeyArr.length < 1) {
            return 8002;
        }
        try {
            eCPrivateKeyArr[0] = (ECPrivateKey) KeyFactory.getInstance("EC", new BouncyCastleProvider()).generatePrivate(new ECPrivateKeySpec(new BigInteger(ByteUtils.toHexString(bArr), 16), ECNamedCurveTable.getParameterSpec("sm2p256v1")));
            return 8000;
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return NCPBase.NCPSM_ERR_NOALG;
        } catch (InvalidKeySpecException e3) {
            e3.printStackTrace();
            return NCPBase.NCPSM_ERR_INVAKEYSPEC;
        } catch (Exception e4) {
            e4.printStackTrace();
            return NCPBase.NCPSM_ERR_UNKNOWN;
        }
    }

    public int GetPubKeyObject(byte[] bArr, ECPublicKey[] eCPublicKeyArr) {
        if (bArr == null || eCPublicKeyArr == null || bArr.length < 64) {
            return 8001;
        }
        if (eCPublicKeyArr.length < 1) {
            return 8002;
        }
        try {
            byte[] bArr2 = new byte[32];
            byte[] bArr3 = new byte[32];
            System.arraycopy(bArr, 0, bArr2, 0, 32);
            System.arraycopy(bArr, 32, bArr3, 0, 32);
            BigInteger bigInteger = new BigInteger(ByteUtils.toHexString(bArr2), 16);
            BigInteger bigInteger2 = new BigInteger(ByteUtils.toHexString(bArr3), 16);
            ECNamedCurveParameterSpec parameterSpec = ECNamedCurveTable.getParameterSpec("sm2p256v1");
            ECDomainParameters eCDomainParameters = new ECDomainParameters(parameterSpec.getCurve(), parameterSpec.getG(), parameterSpec.getN());
            ECCurve curve = eCDomainParameters.getCurve();
            eCPublicKeyArr[0] = (ECPublicKey) KeyFactory.getInstance("EC", new BouncyCastleProvider()).generatePublic(new ECPublicKeySpec(curve.createPoint(bigInteger, bigInteger2), new ECParameterSpec(curve, eCDomainParameters.getG(), eCDomainParameters.getN())));
            return 8000;
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return NCPBase.NCPSM_ERR_NOALG;
        } catch (InvalidKeySpecException e3) {
            e3.printStackTrace();
            return NCPBase.NCPSM_ERR_INVAKEYSPEC;
        } catch (Exception e4) {
            e4.printStackTrace();
            return NCPBase.NCPSM_ERR_UNKNOWN;
        }
    }

    public int SM2Decrypt(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, int[] iArr) {
        if (bArr == null || bArr2 == null || bArr.length < 32 || bArr2.length < i) {
            return 8001;
        }
        int i2 = i - 97;
        iArr[0] = i2;
        if (bArr3 == null) {
            return 8000;
        }
        if (bArr3.length < i2) {
            return 8002;
        }
        ECPrivateKey[] eCPrivateKeyArr = new ECPrivateKey[1];
        int iGetPriKeyObject = GetPriKeyObject(bArr, eCPrivateKeyArr);
        if (iGetPriKeyObject != 8000) {
            return iGetPriKeyObject;
        }
        try {
            ECParameterSpec parameters = eCPrivateKeyArr[0].getParameters();
            ECPrivateKeyParameters eCPrivateKeyParameters = new ECPrivateKeyParameters(eCPrivateKeyArr[0].getD(), new ECDomainParameters(parameters.getCurve(), parameters.getG(), parameters.getN()));
            SM2Engine sM2Engine = new SM2Engine();
            sM2Engine.init(false, eCPrivateKeyParameters);
            byte[] bArrProcessBlock = sM2Engine.processBlock(bArr2, 0, i);
            System.arraycopy(bArrProcessBlock, 0, bArr3, 0, bArrProcessBlock.length);
            return 8000;
        } catch (InvalidCipherTextException e2) {
            e2.printStackTrace();
            return NCPBase.NCPSM_ERR_IVALCIPHER;
        } catch (Exception e3) {
            e3.printStackTrace();
            return NCPBase.NCPSM_ERR_UNKNOWN;
        }
    }

    public int SM2Encrypt(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, int[] iArr) {
        if (bArr == null || bArr2 == null || bArr.length < 64 || bArr2.length < i) {
            return 8001;
        }
        int i2 = i + 97;
        iArr[0] = i2;
        if (bArr3 == null) {
            return 8000;
        }
        if (bArr3.length < i2) {
            return 8002;
        }
        ECPublicKey[] eCPublicKeyArr = new ECPublicKey[1];
        int iGetPubKeyObject = GetPubKeyObject(bArr, eCPublicKeyArr);
        if (iGetPubKeyObject != 8000) {
            return iGetPubKeyObject;
        }
        try {
            ECParameterSpec parameters = eCPublicKeyArr[0].getParameters();
            ECPublicKeyParameters eCPublicKeyParameters = new ECPublicKeyParameters(eCPublicKeyArr[0].getQ(), new ECDomainParameters(parameters.getCurve(), parameters.getG(), parameters.getN()));
            SM2Engine sM2Engine = new SM2Engine();
            sM2Engine.init(true, new ParametersWithRandom(eCPublicKeyParameters, new SecureRandom()));
            byte[] bArrProcessBlock = sM2Engine.processBlock(bArr2, 0, i);
            System.arraycopy(bArrProcessBlock, 0, bArr3, 0, bArrProcessBlock.length);
            return 8000;
        } catch (InvalidCipherTextException e2) {
            e2.printStackTrace();
            return NCPBase.NCPSM_ERR_IVALCIPHER;
        } catch (Exception e3) {
            e3.printStackTrace();
            return NCPBase.NCPSM_ERR_UNKNOWN;
        }
    }

    public int SM2Sign(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        if (bArr == null || bArr2 == null || bArr3 == null || bArr.length < 32 || bArr2.length < i || bArr3.length < 64) {
            return 8001;
        }
        ECPrivateKey[] eCPrivateKeyArr = new ECPrivateKey[1];
        int iGetPriKeyObject = GetPriKeyObject(bArr, eCPrivateKeyArr);
        if (iGetPriKeyObject != 8000) {
            return iGetPriKeyObject;
        }
        try {
            ECParameterSpec parameters = eCPrivateKeyArr[0].getParameters();
            ECPrivateKeyParameters eCPrivateKeyParameters = new ECPrivateKeyParameters(eCPrivateKeyArr[0].getD(), new ECDomainParameters(parameters.getCurve(), parameters.getG(), parameters.getN()));
            SM2Signer sM2Signer = new SM2Signer();
            sM2Signer.init(true, new ParametersWithID(eCPrivateKeyParameters, new byte[]{49, 50, 51, 52, 53, 54, 55, 56, 49, 50, 51, 52, 53, 54, 55, 56}));
            sM2Signer.update(bArr2, 0, i);
            BigInteger[] bigIntegerArrDecode = StandardDSAEncoding.INSTANCE.decode(parameters.getCurve().getOrder(), sM2Signer.generateSignature());
            byte[] byteArray = bigIntegerArrDecode[0].toByteArray();
            if (byteArray.length == 32) {
                System.arraycopy(byteArray, 0, bArr3, 0, 32);
            } else if (byteArray.length < 32) {
                System.arraycopy(byteArray, 0, bArr3, 32 - byteArray.length, byteArray.length);
            } else {
                System.arraycopy(byteArray, 1, bArr3, 0, 32);
            }
            byte[] byteArray2 = bigIntegerArrDecode[1].toByteArray();
            if (byteArray2.length == 32) {
                System.arraycopy(byteArray2, 0, bArr3, 32, 32);
            } else if (byteArray2.length < 32) {
                System.arraycopy(byteArray2, 0, bArr3, 64 - byteArray2.length, byteArray2.length);
            } else {
                System.arraycopy(byteArray2, 1, bArr3, 32, 32);
            }
            return 8000;
        } catch (IOException e2) {
            e2.printStackTrace();
            return NCPBase.NCPSM_ERR_IO;
        } catch (CryptoException e3) {
            e3.printStackTrace();
            return NCPBase.NCPSM_ERR_CRYPTO;
        } catch (Exception e4) {
            e4.printStackTrace();
            return NCPBase.NCPSM_ERR_UNKNOWN;
        }
    }

    public int SM2Verify(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) {
        if (bArr == null || bArr2 == null || bArr3 == null || bArr.length < 64 || bArr2.length < i || bArr3.length < 64) {
            return 8001;
        }
        ECPublicKey[] eCPublicKeyArr = new ECPublicKey[1];
        int iGetPubKeyObject = GetPubKeyObject(bArr, eCPublicKeyArr);
        if (iGetPubKeyObject != 8000) {
            return iGetPubKeyObject;
        }
        byte[] bArr4 = new byte[32];
        byte[] bArr5 = new byte[32];
        System.arraycopy(bArr3, 0, bArr4, 0, 32);
        System.arraycopy(bArr3, 32, bArr5, 0, 32);
        BigInteger bigInteger = new BigInteger(ByteUtils.toHexString(bArr4), 16);
        BigInteger bigInteger2 = new BigInteger(ByteUtils.toHexString(bArr5), 16);
        try {
            ECParameterSpec parameters = eCPublicKeyArr[0].getParameters();
            ECPublicKeyParameters eCPublicKeyParameters = new ECPublicKeyParameters(eCPublicKeyArr[0].getQ(), new ECDomainParameters(parameters.getCurve(), parameters.getG(), parameters.getN()));
            byte[] bArrEncode = StandardDSAEncoding.INSTANCE.encode(parameters.getCurve().getOrder(), bigInteger, bigInteger2);
            SM2Signer sM2Signer = new SM2Signer();
            sM2Signer.init(false, new ParametersWithID(eCPublicKeyParameters, new byte[]{49, 50, 51, 52, 53, 54, 55, 56, 49, 50, 51, 52, 53, 54, 55, 56}));
            sM2Signer.update(bArr2, 0, i);
            if (sM2Signer.verifySignature(bArrEncode)) {
                return 8000;
            }
            return NCPBase.NCPSM_ERR_SIGNVERFITY;
        } catch (IOException e2) {
            e2.printStackTrace();
            return NCPBase.NCPSM_ERR_IO;
        } catch (Exception e3) {
            e3.printStackTrace();
            return NCPBase.NCPSM_ERR_UNKNOWN;
        }
    }

    public int UnCompressPubKey(byte[] bArr, byte[] bArr2) {
        if (bArr2 == null || bArr == null || bArr.length < 33) {
            return 8001;
        }
        if (bArr2.length < 64) {
            return 8002;
        }
        try {
            ECPoint eCPointDecodePoint = GMNamedCurves.getByName("sm2p256v1").getCurve().decodePoint(Hex.decode(ByteUtils.toHexString(bArr)));
            System.arraycopy(eCPointDecodePoint.getXCoord().getEncoded(), 0, bArr2, 0, 32);
            System.arraycopy(eCPointDecodePoint.getYCoord().getEncoded(), 0, bArr2, 32, 32);
            return 8000;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 8003;
        }
    }
}
