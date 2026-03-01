package com.newcapec.smcrypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* JADX INFO: loaded from: classes2.dex */
public class NCPSM4 {
    public NCPSM4() {
        Security.addProvider(new BouncyCastleProvider());
    }

    public int SM4Decrypt(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        if (bArr4 == null || bArr2 == null || bArr == null || i % 16 != 0 || bArr.length < i || bArr2.length < 16 || bArr4.length < i) {
            return 8001;
        }
        byte[] bArr5 = new byte[i];
        System.arraycopy(bArr, 0, bArr5, 0, i);
        byte[] bArr6 = new byte[16];
        System.arraycopy(bArr2, 0, bArr6, 0, 16);
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr6, "SM4");
        if (bArr3 == null) {
            try {
                Cipher cipher = Cipher.getInstance("SM4/ECB/NoPadding", BouncyCastleProvider.PROVIDER_NAME);
                cipher.init(2, secretKeySpec);
                System.arraycopy(cipher.doFinal(bArr5), 0, bArr4, 0, i);
                return 8000;
            } catch (InvalidKeyException unused) {
                return NCPBase.NCPSM_ERR_INVAKEY;
            } catch (NoSuchAlgorithmException unused2) {
                return NCPBase.NCPSM_ERR_NOALG;
            } catch (NoSuchProviderException unused3) {
                return NCPBase.NCPSM_ERR_NOPROVIDER;
            } catch (BadPaddingException unused4) {
                return NCPBase.NCPSM_ERR_PADDING;
            } catch (IllegalBlockSizeException unused5) {
                return NCPBase.NCPSM_ERR_BLOCKSIZE;
            } catch (NoSuchPaddingException unused6) {
                return NCPBase.NCPSM_ERR_NOPADDING;
            }
        }
        if (bArr3.length < 16) {
            return 8001;
        }
        byte[] bArr7 = new byte[16];
        System.arraycopy(bArr3, 0, bArr7, 0, 16);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr7);
        try {
            Cipher cipher2 = Cipher.getInstance("SM4/CBC/NoPadding", BouncyCastleProvider.PROVIDER_NAME);
            cipher2.init(2, secretKeySpec, ivParameterSpec);
            System.arraycopy(cipher2.doFinal(bArr5), 0, bArr4, 0, i);
            return 8000;
        } catch (InvalidAlgorithmParameterException unused7) {
            return NCPBase.NCPSM_ERR_INVALGPARAM;
        } catch (InvalidKeyException unused8) {
            return NCPBase.NCPSM_ERR_INVAKEY;
        } catch (NoSuchAlgorithmException unused9) {
            return NCPBase.NCPSM_ERR_NOALG;
        } catch (NoSuchProviderException unused10) {
            return NCPBase.NCPSM_ERR_NOPROVIDER;
        } catch (BadPaddingException unused11) {
            return NCPBase.NCPSM_ERR_PADDING;
        } catch (IllegalBlockSizeException unused12) {
            return NCPBase.NCPSM_ERR_BLOCKSIZE;
        } catch (NoSuchPaddingException unused13) {
            return NCPBase.NCPSM_ERR_NOPADDING;
        }
    }

    public int SM4Encrypt(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        if (bArr == null || bArr2 == null || bArr4 == null || i % 16 != 0 || bArr.length < i || bArr2.length < 16 || bArr4.length < i) {
            return 8001;
        }
        byte[] bArr5 = new byte[i];
        System.arraycopy(bArr, 0, bArr5, 0, i);
        byte[] bArr6 = new byte[16];
        System.arraycopy(bArr2, 0, bArr6, 0, 16);
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr6, "SM4");
        if (bArr3 == null) {
            try {
                Cipher cipher = Cipher.getInstance("SM4/ECB/NoPadding", BouncyCastleProvider.PROVIDER_NAME);
                cipher.init(1, secretKeySpec);
                System.arraycopy(cipher.doFinal(bArr5), 0, bArr4, 0, i);
                return 8000;
            } catch (InvalidKeyException unused) {
                return NCPBase.NCPSM_ERR_INVAKEY;
            } catch (NoSuchAlgorithmException unused2) {
                return NCPBase.NCPSM_ERR_NOALG;
            } catch (NoSuchProviderException unused3) {
                return NCPBase.NCPSM_ERR_NOPROVIDER;
            } catch (BadPaddingException unused4) {
                return NCPBase.NCPSM_ERR_PADDING;
            } catch (IllegalBlockSizeException unused5) {
                return NCPBase.NCPSM_ERR_BLOCKSIZE;
            } catch (NoSuchPaddingException unused6) {
                return NCPBase.NCPSM_ERR_NOPADDING;
            }
        }
        if (bArr3.length < 16) {
            return 8001;
        }
        byte[] bArr7 = new byte[16];
        System.arraycopy(bArr3, 0, bArr7, 0, 16);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr7);
        try {
            Cipher cipher2 = Cipher.getInstance("SM4/CBC/NoPadding", BouncyCastleProvider.PROVIDER_NAME);
            cipher2.init(1, secretKeySpec, ivParameterSpec);
            System.arraycopy(cipher2.doFinal(bArr5), 0, bArr4, 0, i);
            return 8000;
        } catch (InvalidAlgorithmParameterException unused7) {
            return NCPBase.NCPSM_ERR_INVALGPARAM;
        } catch (InvalidKeyException unused8) {
            return NCPBase.NCPSM_ERR_INVAKEY;
        } catch (NoSuchAlgorithmException unused9) {
            return NCPBase.NCPSM_ERR_NOALG;
        } catch (NoSuchProviderException unused10) {
            return NCPBase.NCPSM_ERR_NOPROVIDER;
        } catch (BadPaddingException unused11) {
            return NCPBase.NCPSM_ERR_PADDING;
        } catch (IllegalBlockSizeException unused12) {
            return NCPBase.NCPSM_ERR_BLOCKSIZE;
        } catch (NoSuchPaddingException unused13) {
            return NCPBase.NCPSM_ERR_NOPADDING;
        }
    }
}
