package com.newcapec.smcrypto;

import dc.squareup.okhttp3.internal.ws.WebSocketProtocol;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public class SMKeyAlg {
    public Logger log;
    public NCPSM2 ncpsm2 = new NCPSM2();
    public NCPSM3 ncpsm3 = new NCPSM3();
    public NCPSM4 ncpsm4 = new NCPSM4();

    public SMKeyAlg(Logger logger) {
        this.log = logger;
    }

    public int CheckAuthExpire(byte[] bArr) {
        this.log.log(Level.INFO, "[smcrypto] start CheckAuthExpire...");
        if (bArr == null || bArr.length < 2) {
            this.log.log(Level.SEVERE, "[smcrypto] CheckAuthExpire qr12 param error!");
            return 8001;
        }
        byte[] bArr2 = new byte[8];
        bArr2[6] = bArr[0];
        bArr2[7] = bArr[1];
        long jByte8ToInt64 = NCPBase.byte8ToInt64(bArr2, 0);
        if (jByte8ToInt64 < 0 || jByte8ToInt64 > WebSocketProtocol.PAYLOAD_SHORT_MAX) {
            this.log.log(Level.SEVERE, "[smcrypto] CheckAuthExpire expired setting error!");
            return 8001;
        }
        if ((((((jByte8ToInt64 + 1) * 24) * 60) * 60) - 28800) - (System.currentTimeMillis() / 1000) > 0) {
            return 8000;
        }
        this.log.log(Level.SEVERE, "[smcrypto] CheckAuthExpire expired!");
        return NCPBase.NCPSM_ERR_AUTHDATEOUT;
    }

    public int CheckCRC(byte[] bArr) {
        this.log.log(Level.INFO, "[smcrypto] start CheckCRC...");
        if (bArr == null || bArr.length < 48) {
            this.log.log(Level.SEVERE, "[smcrypto] CheckCRC qr5To14 param error!");
            return 8001;
        }
        byte[] bArr2 = new byte[32];
        this.ncpsm3.SM3Final(bArr, 46, bArr2);
        for (int i = 0; i < 16; i++) {
            int i2 = i * 2;
            bArr[46] = (byte) (bArr[46] ^ bArr2[i2]);
            bArr[47] = (byte) (bArr[47] ^ bArr2[i2 + 1]);
        }
        if (bArr[46] == 0 && bArr[47] == 0) {
            return 8000;
        }
        this.log.log(Level.SEVERE, "[smcrypto] CheckCRC QRCode CRC check failed!");
        return NCPBase.NCPSM_ERR_CRC;
    }

    public int CheckDev(byte b, byte[] bArr) {
        this.log.log(Level.INFO, "[smcrypto] start CheckDev...");
        if ((((((b ^ bArr[0]) ^ bArr[1]) ^ bArr[2]) ^ bArr[3]) ^ bArr[4]) == 0) {
            return 8000;
        }
        this.log.log(Level.SEVERE, "[smcrypto] CheckDev device check failed!");
        return NCPBase.NCPSM_ERR_DEVCRC;
    }

    public int CheckQRCycle(byte b, byte[] bArr) {
        this.log.log(Level.INFO, "[smcrypto] start CheckQRCycle...");
        if (bArr == null || bArr.length < 4) {
            this.log.log(Level.SEVERE, "[smcrypto] CheckQRCycle qr15 param error!");
            return 8001;
        }
        byte[] bArr2 = new byte[8];
        byte[] bArr3 = {0, 0, 0, 0, bArr[0], bArr[1], bArr[2], bArr[3]};
        bArr2[7] = b;
        if ((NCPBase.byte8ToInt64(bArr3, 0) + NCPBase.byte8ToInt64(bArr2, 0)) - (System.currentTimeMillis() / 1000) > 0) {
            return 8000;
        }
        this.log.log(Level.SEVERE, "[smcrypto] CheckQRCycle QRCode expired!");
        return NCPBase.NCPSM_ERR_QRDATEOUT;
    }

    public int CheckSign(byte[] bArr, byte[] bArr2) {
        this.log.log(Level.INFO, "[smcrypto] start CheckSign...");
        if (bArr == null || bArr.length < 122 || bArr2 == null || bArr2.length < 33) {
            this.log.log(Level.SEVERE, "[smcrypto] CheckSign param error!");
            return 8001;
        }
        byte[] bArr3 = new byte[64];
        int iUnCompressPubKey = this.ncpsm2.UnCompressPubKey(bArr2, bArr3);
        if (8000 == iUnCompressPubKey) {
            byte[] bArr4 = new byte[64];
            System.arraycopy(bArr, 58, bArr4, 0, 64);
            return this.ncpsm2.SM2Verify(bArr3, bArr, 58, bArr4);
        }
        this.log.log(Level.SEVERE, "[smcrypto] CheckSign UnCompressPubKey failed, errno:" + iUnCompressPubKey);
        return iUnCompressPubKey;
    }

    public int GenSM2Key(String str, String[] strArr) {
        this.log.log(Level.INFO, "[smcrypto] start GenSM2Key...");
        if (str == null || strArr == null) {
            this.log.log(Level.SEVERE, "[smcrypto] GenSM2Key param is null!");
            return 8001;
        }
        if (str.length() > 256 || strArr.length < 2) {
            this.log.log(Level.SEVERE, "[smcrypto] GenSM2Key param length error!");
            return 8001;
        }
        byte[] bArr = new byte[64];
        byte[] bArr2 = new byte[32];
        int iGenSM2Keypair = this.ncpsm2.GenSM2Keypair(bArr, bArr2);
        if (8000 != iGenSM2Keypair) {
            this.log.log(Level.SEVERE, "[smcrypto] GenSM2Key GenSM2Keypair error!");
            return iGenSM2Keypair;
        }
        byte[] bytes = str.getBytes();
        byte[] bArr3 = new byte[32];
        this.ncpsm3.SM3Final(bytes, bytes.length, bArr3);
        byte[] bArr4 = new byte[33];
        int iCompressPubKey = this.ncpsm2.CompressPubKey(bArr, bArr4);
        if (8000 != iCompressPubKey) {
            this.log.log(Level.SEVERE, "[smcrypto] GenSM2Key CompressPubKey error, errno:" + iCompressPubKey);
            return iCompressPubKey;
        }
        byte[] bArr5 = new byte[32];
        int iSM4Encrypt = this.ncpsm4.SM4Encrypt(bArr2, 32, bArr3, null, bArr5);
        if (8000 == iSM4Encrypt) {
            strArr[0] = NCPBase.hexToString(bArr4);
            strArr[1] = NCPBase.hexToString(bArr5);
            return iSM4Encrypt;
        }
        this.log.log(Level.SEVERE, "[smcrypto] GenSM2Key SM4Encrypt error, errno:" + iSM4Encrypt);
        return iSM4Encrypt;
    }

    public int GetAuthExpire(String str, byte[] bArr) {
        this.log.log(Level.INFO, "[smcrypto] start GetAuthExpire...");
        if (str == null || bArr == null || str.length() != 10 || bArr.length < 2) {
            this.log.log(Level.INFO, "[smcrypto] GetAuthExpire param error!");
            return 8001;
        }
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(simpleDateFormat.parse("1970-01-01 00:00:00"));
            long timeInMillis = calendar.getTimeInMillis();
            calendar.setTime(simpleDateFormat.parse(String.valueOf(str) + " 00:00:00"));
            byte[] bArrInt64ToByte8 = NCPBase.int64ToByte8((calendar.getTimeInMillis() - timeInMillis) / 86400000);
            System.arraycopy(bArrInt64ToByte8, bArrInt64ToByte8.length - 2, bArr, 0, 2);
            return 8000;
        } catch (ParseException unused) {
            this.log.log(Level.SEVERE, "[smcrypto] GetAuthExpire expireTime param format error!");
            return 8001;
        }
    }

    public int GetCRC(byte[] bArr, byte[] bArr2) {
        this.log.log(Level.INFO, "[smcrypto] start GetCRC...");
        if (bArr == null || bArr.length < 46 || bArr2 == null || bArr2.length < 2) {
            this.log.log(Level.INFO, "[smcrypto] GetCRC param error!");
            return 8001;
        }
        byte[] bArr3 = new byte[32];
        this.ncpsm3.SM3Final(bArr, 46, bArr3);
        byte b = 0;
        byte b2 = 0;
        for (int i = 0; i < 16; i++) {
            int i2 = i * 2;
            b = (byte) (b ^ bArr3[i2]);
            b2 = (byte) (b2 ^ bArr3[i2 + 1]);
        }
        bArr2[0] = b;
        bArr2[1] = b2;
        return 8000;
    }

    public int GetDevCode(String str, byte[] bArr) {
        this.log.log(Level.INFO, "[smcrypto] start GetDevCode...");
        if (str == null || str.length() > 256 || bArr.length < 1) {
            this.log.log(Level.SEVERE, "[smcrypto] GetDevCode deviceCode param error!");
            return 8001;
        }
        byte[] bytes = str.getBytes();
        byte[] bArr2 = new byte[32];
        this.ncpsm3.SM3Final(bytes, bytes.length, bArr2);
        bArr[0] = 0;
        for (int i = 0; i < 32; i++) {
            bArr[0] = (byte) (bArr[0] ^ bArr2[i]);
        }
        return 8000;
    }

    public int GetQRDateAndDevCRC(String str, byte[] bArr) {
        this.log.log(Level.INFO, "[smcrypto] start GetQRDateAndDevCRC...");
        if (str == null || str.length() > 256 || bArr == null || bArr.length < 5) {
            this.log.log(Level.SEVERE, "[smcrypto] GetQRDateAndDevCRC param error!");
            return 8001;
        }
        byte[] bArr2 = new byte[5];
        byte[] bytes = str.getBytes();
        byte[] bArr3 = new byte[32];
        this.ncpsm3.SM3Final(bytes, bytes.length, bArr3);
        byte b = 0;
        for (int i = 0; i < 32; i++) {
            b = (byte) (b ^ bArr3[i]);
        }
        System.arraycopy(NCPBase.int64ToByte8(System.currentTimeMillis() / 1000), 4, bArr2, 0, 4);
        bArr2[4] = (byte) ((((bArr2[0] ^ bArr2[1]) ^ bArr2[2]) ^ bArr2[3]) ^ b);
        System.arraycopy(bArr2, 0, bArr, 0, 5);
        return 8000;
    }

    public int SM2Sign(String str, String str2, String str3, byte[] bArr, int i, byte[] bArr2) {
        this.log.log(Level.INFO, "[smcrypto] start SM2Sign...");
        if (str == null || str3 == null || bArr == null || bArr2 == null) {
            this.log.log(Level.SEVERE, "[smcrypto] SM2Sign param is null!");
            return 8001;
        }
        if (str.length() > 256 || str3.length() != 64 || bArr.length < i || bArr2.length < 64) {
            this.log.log(Level.SEVERE, "[smcrypto] SM2Sign param length error!");
            return 8001;
        }
        byte[] bytes = str.getBytes();
        byte[] bArr3 = new byte[32];
        this.ncpsm3.SM3Final(bytes, bytes.length, bArr3);
        byte[] bArr4 = new byte[32];
        int iSM4Decrypt = this.ncpsm4.SM4Decrypt(NCPBase.stringToHex(str3), 32, bArr3, null, bArr4);
        if (8000 != iSM4Decrypt) {
            this.log.log(Level.SEVERE, "[smcrypto] SM2Sign SM4Decrypt error, errno:" + iSM4Decrypt);
            return iSM4Decrypt;
        }
        byte[] bArr5 = new byte[64];
        int iSM2Sign = this.ncpsm2.SM2Sign(bArr4, bArr, i, bArr5);
        if (8000 == iSM2Sign) {
            System.arraycopy(bArr5, 0, bArr2, 0, 64);
            return iSM2Sign;
        }
        this.log.log(Level.SEVERE, "[smcrypto] SM2Sign error, errno:" + iSM2Sign);
        return iSM2Sign;
    }

    public int SM2Verify(String str, byte[] bArr, int i, byte[] bArr2) {
        this.log.log(Level.INFO, "[smcrypto] start SM2Verify...");
        if (str == null || bArr == null || bArr2 == null) {
            this.log.log(Level.SEVERE, "[smcrypto] SM2Verify param is null!");
            return 8001;
        }
        if (str.length() != 66 || bArr.length < i || bArr2.length < 64) {
            this.log.log(Level.SEVERE, "[smcrypto] SM2Verify param length error!");
            return 8001;
        }
        byte[] bArr3 = new byte[64];
        int iUnCompressPubKey = this.ncpsm2.UnCompressPubKey(NCPBase.stringToHex(str), bArr3);
        if (8000 == iUnCompressPubKey) {
            return this.ncpsm2.SM2Verify(bArr3, bArr, i, bArr2);
        }
        this.log.log(Level.SEVERE, "[smcrypto] SM2Verify UnCompressPubKey error, errno:" + iUnCompressPubKey);
        return iUnCompressPubKey;
    }

    public int SafetyRecv(String str, String str2, String str3, byte[] bArr, int i, byte[] bArr2, int[] iArr) {
        this.log.log(Level.INFO, "[smcrypto] start SafetyRecv...");
        if (str == null || str2 == null || str3 == null || bArr == null) {
            this.log.log(Level.SEVERE, "[smcrypto] SafetyRecv param is null!");
            return 8001;
        }
        if (str.length() > 256 || str2.length() != 64 || str3.length() != 66 || bArr.length < i || i < 193) {
            this.log.log(Level.SEVERE, "[smcrypto] SafetyRecv param length error!");
            return 8001;
        }
        int i2 = i - 177;
        if (i2 % 16 != 0) {
            this.log.log(Level.SEVERE, "[smcrypto] SafetyRecv cipherLen Param length error!");
            return 8001;
        }
        byte[] bArr3 = new byte[64];
        int i3 = i - 64;
        System.arraycopy(bArr, i3, bArr3, 0, 64);
        byte[] bArr4 = new byte[64];
        int iUnCompressPubKey = this.ncpsm2.UnCompressPubKey(NCPBase.stringToHex(str3), bArr4);
        if (8000 != iUnCompressPubKey) {
            this.log.log(Level.SEVERE, "[smcrypto] SafetyRecv UnCompressPubKey error, errno:" + iUnCompressPubKey);
            return iUnCompressPubKey;
        }
        int iSM2Verify = this.ncpsm2.SM2Verify(bArr4, bArr, i3, bArr3);
        if (8000 != iSM2Verify) {
            this.log.log(Level.SEVERE, "[smcrypto] SafetyRecv SM2Verify error, errno:" + iSM2Verify);
            return iSM2Verify;
        }
        byte[] bArr5 = new byte[113];
        System.arraycopy(bArr, i2, bArr5, 0, 113);
        byte[] bytes = str.getBytes();
        byte[] bArr6 = new byte[32];
        this.ncpsm3.SM3Final(bytes, bytes.length, bArr6);
        byte[] bArr7 = new byte[32];
        int iSM4Decrypt = this.ncpsm4.SM4Decrypt(NCPBase.stringToHex(str2), 32, bArr6, null, bArr7);
        if (8000 != iSM4Decrypt) {
            this.log.log(Level.SEVERE, "[smcrypto] SafetyRecv SM4Decrypt error, errno:" + iSM4Decrypt);
            return iSM4Decrypt;
        }
        int[] iArr2 = {0};
        this.ncpsm2.SM2Decrypt(bArr7, bArr5, 113, null, iArr2);
        if (iArr2[0] != 16) {
            this.log.log(Level.SEVERE, "[smcrypto] SafetyRecv SM2Decrypt error!");
            return NCPBase.NCPSM_ERR_IVALCIPHER;
        }
        byte[] bArr8 = new byte[16];
        int iSM2Decrypt = this.ncpsm2.SM2Decrypt(bArr7, bArr5, 113, bArr8, iArr2);
        if (8000 != iSM2Decrypt) {
            this.log.log(Level.SEVERE, "[smcrypto] SafetyRecv SM2Decrypt error, errno:" + iSM2Decrypt);
            return iSM2Decrypt;
        }
        byte[] bArr9 = new byte[i2];
        System.arraycopy(bArr, 0, bArr9, 0, i2);
        byte[] bArr10 = new byte[i2];
        int iSM4Decrypt2 = this.ncpsm4.SM4Decrypt(bArr9, i2, bArr8, null, bArr10);
        if (8000 != iSM4Decrypt2) {
            this.log.log(Level.SEVERE, "[smcrypto] SafetyRecv SM4Decrypt error, errno:" + iSM4Decrypt2);
            return iSM4Decrypt2;
        }
        iArr[0] = i2 - bArr10[i - 178];
        if (bArr2 == null || bArr2.length < iArr[0]) {
            this.log.log(Level.SEVERE, "[smcrypto] SafetyRecv plaint error!");
            return 8002;
        }
        System.arraycopy(bArr10, 0, bArr2, 0, iArr[0]);
        return iSM4Decrypt2;
    }

    public int SafetySend(String str, String str2, String str3, String str4, byte[] bArr, int i, byte[] bArr2, int[] iArr) {
        this.log.log(Level.INFO, "[smcrypto] start SafetySend...");
        if (str == null || str3 == null || str4 == null || bArr == null) {
            this.log.log(Level.SEVERE, "[smcrypto] SafetySend param is null!");
            return 8001;
        }
        if (str.length() > 256 || str3.length() != 64 || str4.length() != 66 || bArr.length < i) {
            this.log.log(Level.SEVERE, "[smcrypto] SafetySend param length error!");
            return 8001;
        }
        byte b = (byte) (16 - (i % 16));
        int i2 = i + b;
        int i3 = i2 + 177;
        iArr[0] = i3;
        if (bArr2 == null || bArr2.length < i3) {
            this.log.log(Level.WARNING, "[smcrypto] SafetySend cipher param error!");
            return 8002;
        }
        byte[] bArr3 = new byte[16];
        new SecureRandom().nextBytes(bArr3);
        byte[] bArr4 = new byte[i2];
        byte[] bArr5 = new byte[i2];
        System.arraycopy(bArr, 0, bArr4, 0, i);
        for (int i4 = 0; i4 < b; i4++) {
            bArr4[i + i4] = b;
        }
        int iSM4Encrypt = this.ncpsm4.SM4Encrypt(bArr4, i2, bArr3, null, bArr5);
        if (8000 != iSM4Encrypt) {
            this.log.log(Level.SEVERE, "[smcrypto] SafetySend SM4Encrypt error, errno:" + iSM4Encrypt);
            return iSM4Encrypt;
        }
        System.arraycopy(bArr5, 0, bArr2, 0, i2);
        byte[] bArr6 = new byte[64];
        int iUnCompressPubKey = this.ncpsm2.UnCompressPubKey(NCPBase.stringToHex(str4), bArr6);
        if (8000 != iUnCompressPubKey) {
            this.log.log(Level.SEVERE, "[smcrypto] SafetySend UnCompressPubKey error, errno:" + iUnCompressPubKey);
            return iUnCompressPubKey;
        }
        byte[] bArr7 = new byte[128];
        int[] iArr2 = new int[1];
        int iSM2Encrypt = this.ncpsm2.SM2Encrypt(bArr6, bArr3, 16, bArr7, iArr2);
        if (8000 != iSM2Encrypt) {
            this.log.log(Level.SEVERE, "[smcrypto] SafetySend SM2Encrypt error, errno:" + iSM2Encrypt);
            return iSM2Encrypt;
        }
        System.arraycopy(bArr7, 0, bArr2, i2, iArr2[0]);
        byte[] bytes = str.getBytes();
        byte[] bArr8 = new byte[32];
        this.ncpsm3.SM3Final(bytes, bytes.length, bArr8);
        byte[] bArr9 = new byte[32];
        int iSM4Decrypt = this.ncpsm4.SM4Decrypt(NCPBase.stringToHex(str3), 32, bArr8, null, bArr9);
        if (8000 != iSM4Decrypt) {
            this.log.log(Level.SEVERE, "[smcrypto] SafetySend SM4Decrypt error, errno:" + iSM4Decrypt);
            return iSM4Decrypt;
        }
        byte[] bArr10 = new byte[64];
        int iSM2Sign = this.ncpsm2.SM2Sign(bArr9, bArr2, iArr2[0] + i2, bArr10);
        if (8000 == iSM2Sign) {
            System.arraycopy(bArr10, 0, bArr2, i2 + iArr2[0], 64);
            return iSM2Sign;
        }
        this.log.log(Level.SEVERE, "[smcrypto] SafetySend SM2Sign error, errno:" + iSM2Sign);
        return iSM2Sign;
    }
}
