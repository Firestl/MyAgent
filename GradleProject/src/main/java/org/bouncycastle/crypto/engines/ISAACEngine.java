package org.bouncycastle.crypto.engines;

import com.tencent.liteav.TXLiteAVCode;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Pack;

/* JADX INFO: loaded from: classes3.dex */
public class ISAACEngine implements StreamCipher {
    public final int sizeL = 8;
    public final int stateArraySize = 256;
    public int[] engineState = null;
    public int[] results = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f6799a = 0;
    public int b = 0;
    public int c = 0;
    public int index = 0;
    public byte[] keyStream = new byte[1024];
    public byte[] workingKey = null;
    public boolean initialised = false;

    private void isaac() {
        int i;
        int i2;
        int i3 = this.b;
        int i4 = this.c + 1;
        this.c = i4;
        this.b = i3 + i4;
        for (int i5 = 0; i5 < 256; i5++) {
            int i6 = this.engineState[i5];
            int i7 = i5 & 3;
            if (i7 == 0) {
                i = this.f6799a;
                i2 = i << 13;
            } else if (i7 == 1) {
                i = this.f6799a;
                i2 = i >>> 6;
            } else if (i7 == 2) {
                i = this.f6799a;
                i2 = i << 2;
            } else if (i7 != 3) {
                int i8 = this.f6799a;
                int[] iArr = this.engineState;
                int i9 = i8 + iArr[(i5 + 128) & 255];
                this.f6799a = i9;
                int i10 = iArr[(i6 >>> 2) & 255] + i9 + this.b;
                iArr[i5] = i10;
                int[] iArr2 = this.results;
                int i11 = iArr[(i10 >>> 10) & 255] + i6;
                this.b = i11;
                iArr2[i5] = i11;
            } else {
                i = this.f6799a;
                i2 = i >>> 16;
            }
            this.f6799a = i ^ i2;
            int i82 = this.f6799a;
            int[] iArr3 = this.engineState;
            int i92 = i82 + iArr3[(i5 + 128) & 255];
            this.f6799a = i92;
            int i102 = iArr3[(i6 >>> 2) & 255] + i92 + this.b;
            iArr3[i5] = i102;
            int[] iArr22 = this.results;
            int i112 = iArr3[(i102 >>> 10) & 255] + i6;
            this.b = i112;
            iArr22[i5] = i112;
        }
    }

    private void mix(int[] iArr) {
        iArr[0] = iArr[0] ^ (iArr[1] << 11);
        iArr[3] = iArr[3] + iArr[0];
        iArr[1] = iArr[1] + iArr[2];
        iArr[1] = iArr[1] ^ (iArr[2] >>> 2);
        iArr[4] = iArr[4] + iArr[1];
        iArr[2] = iArr[2] + iArr[3];
        iArr[2] = iArr[2] ^ (iArr[3] << 8);
        iArr[5] = iArr[5] + iArr[2];
        iArr[3] = iArr[3] + iArr[4];
        iArr[3] = iArr[3] ^ (iArr[4] >>> 16);
        iArr[6] = iArr[6] + iArr[3];
        iArr[4] = iArr[4] + iArr[5];
        iArr[4] = iArr[4] ^ (iArr[5] << 10);
        iArr[7] = iArr[7] + iArr[4];
        iArr[5] = iArr[5] + iArr[6];
        iArr[5] = (iArr[6] >>> 4) ^ iArr[5];
        iArr[0] = iArr[0] + iArr[5];
        iArr[6] = iArr[6] + iArr[7];
        iArr[6] = iArr[6] ^ (iArr[7] << 8);
        iArr[1] = iArr[1] + iArr[6];
        iArr[7] = iArr[7] + iArr[0];
        iArr[7] = iArr[7] ^ (iArr[0] >>> 9);
        iArr[2] = iArr[2] + iArr[7];
        iArr[0] = iArr[0] + iArr[1];
    }

    private void setKey(byte[] bArr) {
        this.workingKey = bArr;
        if (this.engineState == null) {
            this.engineState = new int[256];
        }
        if (this.results == null) {
            this.results = new int[256];
        }
        for (int i = 0; i < 256; i++) {
            int[] iArr = this.engineState;
            this.results[i] = 0;
            iArr[i] = 0;
        }
        this.c = 0;
        this.b = 0;
        this.f6799a = 0;
        this.index = 0;
        int length = bArr.length + (bArr.length & 3);
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        for (int i2 = 0; i2 < length; i2 += 4) {
            this.results[i2 >>> 2] = Pack.littleEndianToInt(bArr2, i2);
        }
        int[] iArr2 = new int[8];
        for (int i3 = 0; i3 < 8; i3++) {
            iArr2[i3] = -1640531527;
        }
        for (int i4 = 0; i4 < 4; i4++) {
            mix(iArr2);
        }
        int i5 = 0;
        while (i5 < 2) {
            for (int i6 = 0; i6 < 256; i6 += 8) {
                for (int i7 = 0; i7 < 8; i7++) {
                    iArr2[i7] = iArr2[i7] + (i5 < 1 ? this.results[i6 + i7] : this.engineState[i6 + i7]);
                }
                mix(iArr2);
                for (int i8 = 0; i8 < 8; i8++) {
                    this.engineState[i6 + i8] = iArr2[i8];
                }
            }
            i5++;
        }
        isaac();
        this.initialised = true;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "ISAAC";
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            setKey(((KeyParameter) cipherParameters).getKey());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to ISAAC init - " + cipherParameters.getClass().getName());
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (!this.initialised) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        for (int i4 = 0; i4 < i2; i4++) {
            if (this.index == 0) {
                isaac();
                this.keyStream = Pack.intToBigEndian(this.results);
            }
            byte[] bArr3 = this.keyStream;
            int i5 = this.index;
            bArr2[i4 + i3] = (byte) (bArr3[i5] ^ bArr[i4 + i]);
            this.index = (i5 + 1) & TXLiteAVCode.EVT_CAMERA_REMOVED;
        }
        return i2;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void reset() {
        setKey(this.workingKey);
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public byte returnByte(byte b) {
        if (this.index == 0) {
            isaac();
            this.keyStream = Pack.intToBigEndian(this.results);
        }
        byte[] bArr = this.keyStream;
        int i = this.index;
        byte b2 = (byte) (b ^ bArr[i]);
        this.index = (i + 1) & TXLiteAVCode.EVT_CAMERA_REMOVED;
        return b2;
    }
}
