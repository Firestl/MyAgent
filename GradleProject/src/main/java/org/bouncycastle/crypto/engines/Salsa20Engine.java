package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.MaxBytesExceededException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.SkippingStreamCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Pack;
import org.bouncycastle.util.Strings;

/* JADX INFO: loaded from: classes3.dex */
public class Salsa20Engine implements SkippingStreamCipher {
    public static final int DEFAULT_ROUNDS = 20;
    public static final int STATE_SIZE = 16;
    public static final int[] TAU_SIGMA = Pack.littleEndianToInt(Strings.toByteArray("expand 16-byte kexpand 32-byte k"), 0, 8);
    public static final byte[] sigma = Strings.toByteArray("expand 32-byte k");
    public static final byte[] tau = Strings.toByteArray("expand 16-byte k");
    public int cW0;
    public int cW1;
    public int cW2;
    public int[] engineState;
    public int index;
    public boolean initialised;
    public byte[] keyStream;
    public int rounds;
    public int[] x;

    public Salsa20Engine() {
        this(20);
    }

    public Salsa20Engine(int i) {
        this.index = 0;
        this.engineState = new int[16];
        this.x = new int[16];
        this.keyStream = new byte[64];
        this.initialised = false;
        if (i <= 0 || (i & 1) != 0) {
            throw new IllegalArgumentException("'rounds' must be a positive, even number");
        }
        this.rounds = i;
    }

    private boolean limitExceeded() {
        int i = this.cW0 + 1;
        this.cW0 = i;
        if (i == 0) {
            int i2 = this.cW1 + 1;
            this.cW1 = i2;
            if (i2 == 0) {
                int i3 = this.cW2 + 1;
                this.cW2 = i3;
                return (i3 & 32) != 0;
            }
        }
        return false;
    }

    private boolean limitExceeded(int i) {
        int i2 = this.cW0 + i;
        this.cW0 = i2;
        if (i2 >= i || i2 < 0) {
            return false;
        }
        int i3 = this.cW1 + 1;
        this.cW1 = i3;
        if (i3 != 0) {
            return false;
        }
        int i4 = this.cW2 + 1;
        this.cW2 = i4;
        return (i4 & 32) != 0;
    }

    private void resetLimitCounter() {
        this.cW0 = 0;
        this.cW1 = 0;
        this.cW2 = 0;
    }

    public static int rotl(int i, int i2) {
        return (i >>> (-i2)) | (i << i2);
    }

    public static void salsaCore(int i, int[] iArr, int[] iArr2) {
        if (iArr.length != 16) {
            throw new IllegalArgumentException();
        }
        if (iArr2.length != 16) {
            throw new IllegalArgumentException();
        }
        if (i % 2 != 0) {
            throw new IllegalArgumentException("Number of rounds must be even");
        }
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = iArr[2];
        int i5 = iArr[3];
        int i6 = iArr[4];
        int i7 = iArr[5];
        int i8 = iArr[6];
        int i9 = 7;
        int i10 = iArr[7];
        int i11 = iArr[8];
        int i12 = 9;
        int i13 = iArr[9];
        int i14 = iArr[10];
        int i15 = iArr[11];
        int i16 = iArr[12];
        int i17 = 13;
        int i18 = iArr[13];
        int i19 = iArr[14];
        int iRotl = iArr[15];
        int iRotl2 = i19;
        int iRotl3 = i18;
        int iRotl4 = i16;
        int iRotl5 = i15;
        int iRotl6 = i14;
        int iRotl7 = i13;
        int i20 = i11;
        int iRotl8 = i10;
        int iRotl9 = i8;
        int iRotl10 = i7;
        int i21 = i6;
        int i22 = i5;
        int iRotl11 = i4;
        int iRotl12 = i3;
        int i23 = i2;
        int i24 = i;
        while (i24 > 0) {
            int iRotl13 = rotl(i23 + iRotl4, i9) ^ i21;
            int iRotl14 = i20 ^ rotl(iRotl13 + i23, i12);
            int iRotl15 = iRotl4 ^ rotl(iRotl14 + iRotl13, i17);
            int iRotl16 = rotl(iRotl15 + iRotl14, 18) ^ i23;
            int iRotl17 = iRotl7 ^ rotl(iRotl10 + iRotl12, i9);
            int iRotl18 = iRotl3 ^ rotl(iRotl17 + iRotl10, i12);
            int iRotl19 = iRotl12 ^ rotl(iRotl18 + iRotl17, i17);
            int iRotl20 = rotl(iRotl19 + iRotl18, 18) ^ iRotl10;
            int iRotl21 = iRotl2 ^ rotl(iRotl6 + iRotl9, 7);
            int iRotl22 = iRotl11 ^ rotl(iRotl21 + iRotl6, 9);
            int iRotl23 = iRotl9 ^ rotl(iRotl22 + iRotl21, 13);
            int iRotl24 = iRotl6 ^ rotl(iRotl23 + iRotl22, 18);
            int iRotl25 = i22 ^ rotl(iRotl + iRotl5, 7);
            int iRotl26 = iRotl8 ^ rotl(iRotl25 + iRotl, 9);
            int i25 = i24;
            int iRotl27 = iRotl5 ^ rotl(iRotl26 + iRotl25, 13);
            int iRotl28 = iRotl ^ rotl(iRotl27 + iRotl26, 18);
            iRotl12 = iRotl19 ^ rotl(iRotl16 + iRotl25, 7);
            iRotl11 = iRotl22 ^ rotl(iRotl12 + iRotl16, 9);
            int iRotl29 = iRotl25 ^ rotl(iRotl11 + iRotl12, 13);
            int iRotl30 = iRotl16 ^ rotl(iRotl29 + iRotl11, 18);
            iRotl9 = iRotl23 ^ rotl(iRotl20 + iRotl13, 7);
            iRotl8 = iRotl26 ^ rotl(iRotl9 + iRotl20, 9);
            int iRotl31 = rotl(iRotl8 + iRotl9, 13) ^ iRotl13;
            iRotl10 = iRotl20 ^ rotl(iRotl31 + iRotl8, 18);
            iRotl5 = iRotl27 ^ rotl(iRotl24 + iRotl17, 7);
            int iRotl32 = rotl(iRotl5 + iRotl24, 9) ^ iRotl14;
            iRotl7 = iRotl17 ^ rotl(iRotl32 + iRotl5, 13);
            iRotl6 = iRotl24 ^ rotl(iRotl7 + iRotl32, 18);
            iRotl4 = iRotl15 ^ rotl(iRotl28 + iRotl21, 7);
            iRotl3 = iRotl18 ^ rotl(iRotl4 + iRotl28, 9);
            iRotl2 = iRotl21 ^ rotl(iRotl3 + iRotl4, 13);
            iRotl = iRotl28 ^ rotl(iRotl2 + iRotl3, 18);
            i22 = iRotl29;
            i20 = iRotl32;
            i23 = iRotl30;
            i21 = iRotl31;
            i17 = 13;
            i12 = 9;
            i9 = 7;
            i24 = i25 - 2;
        }
        iArr2[0] = i23 + iArr[0];
        iArr2[1] = iRotl12 + iArr[1];
        iArr2[2] = iRotl11 + iArr[2];
        iArr2[3] = i22 + iArr[3];
        iArr2[4] = i21 + iArr[4];
        iArr2[5] = iRotl10 + iArr[5];
        iArr2[6] = iRotl9 + iArr[6];
        iArr2[7] = iRotl8 + iArr[7];
        iArr2[8] = i20 + iArr[8];
        iArr2[9] = iRotl7 + iArr[9];
        iArr2[10] = iRotl6 + iArr[10];
        iArr2[11] = iRotl5 + iArr[11];
        iArr2[12] = iRotl4 + iArr[12];
        iArr2[13] = iRotl3 + iArr[13];
        iArr2[14] = iRotl2 + iArr[14];
        iArr2[15] = iRotl + iArr[15];
    }

    public void advanceCounter() {
        int[] iArr = this.engineState;
        int i = iArr[8] + 1;
        iArr[8] = i;
        if (i == 0) {
            iArr[9] = iArr[9] + 1;
        }
    }

    public void advanceCounter(long j) {
        int i = (int) (j >>> 32);
        int i2 = (int) j;
        if (i > 0) {
            int[] iArr = this.engineState;
            iArr[9] = iArr[9] + i;
        }
        int[] iArr2 = this.engineState;
        int i3 = iArr2[8];
        iArr2[8] = iArr2[8] + i2;
        if (i3 == 0 || iArr2[8] >= i3) {
            return;
        }
        iArr2[9] = iArr2[9] + 1;
    }

    public void generateKeyStream(byte[] bArr) {
        salsaCore(this.rounds, this.engineState, this.x);
        Pack.intToLittleEndian(this.x, bArr, 0);
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        if (this.rounds == 20) {
            return "Salsa20";
        }
        return "Salsa20/" + this.rounds;
    }

    public long getCounter() {
        int[] iArr = this.engineState;
        return (((long) iArr[9]) << 32) | (((long) iArr[8]) & 4294967295L);
    }

    public int getNonceSize() {
        return 8;
    }

    @Override // org.bouncycastle.crypto.SkippingCipher
    public long getPosition() {
        return (getCounter() * 64) + ((long) this.index);
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException(getAlgorithmName() + " Init parameters must include an IV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        byte[] iv = parametersWithIV.getIV();
        if (iv == null || iv.length != getNonceSize()) {
            throw new IllegalArgumentException(getAlgorithmName() + " requires exactly " + getNonceSize() + " bytes of IV");
        }
        CipherParameters parameters = parametersWithIV.getParameters();
        if (parameters == null) {
            if (!this.initialised) {
                throw new IllegalStateException(getAlgorithmName() + " KeyParameter can not be null for first initialisation");
            }
            setKey(null, iv);
        } else {
            if (!(parameters instanceof KeyParameter)) {
                throw new IllegalArgumentException(getAlgorithmName() + " Init parameters must contain a KeyParameter (or null for re-init)");
            }
            setKey(((KeyParameter) parameters).getKey(), iv);
        }
        reset();
        this.initialised = true;
    }

    public void packTauOrSigma(int i, int[] iArr, int i2) {
        int i3 = (i - 16) / 4;
        int[] iArr2 = TAU_SIGMA;
        iArr[i2] = iArr2[i3];
        iArr[i2 + 1] = iArr2[i3 + 1];
        iArr[i2 + 2] = iArr2[i3 + 2];
        iArr[i2 + 3] = iArr2[i3 + 3];
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
        if (limitExceeded(i2)) {
            throw new MaxBytesExceededException("2^70 byte limit per IV would be exceeded; Change IV");
        }
        for (int i4 = 0; i4 < i2; i4++) {
            byte[] bArr3 = this.keyStream;
            int i5 = this.index;
            bArr2[i4 + i3] = (byte) (bArr3[i5] ^ bArr[i4 + i]);
            int i6 = (i5 + 1) & 63;
            this.index = i6;
            if (i6 == 0) {
                advanceCounter();
                generateKeyStream(this.keyStream);
            }
        }
        return i2;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void reset() {
        this.index = 0;
        resetLimitCounter();
        resetCounter();
        generateKeyStream(this.keyStream);
    }

    public void resetCounter() {
        int[] iArr = this.engineState;
        iArr[9] = 0;
        iArr[8] = 0;
    }

    public void retreatCounter() {
        int[] iArr = this.engineState;
        if (iArr[8] == 0 && iArr[9] == 0) {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        }
        int[] iArr2 = this.engineState;
        int i = iArr2[8] - 1;
        iArr2[8] = i;
        if (i == -1) {
            iArr2[9] = iArr2[9] - 1;
        }
    }

    public void retreatCounter(long j) {
        int i = (int) (j >>> 32);
        int i2 = (int) j;
        if (i != 0) {
            int[] iArr = this.engineState;
            if ((((long) iArr[9]) & 4294967295L) < (((long) i) & 4294967295L)) {
                throw new IllegalStateException("attempt to reduce counter past zero.");
            }
            iArr[9] = iArr[9] - i;
        }
        int[] iArr2 = this.engineState;
        if ((((long) iArr2[8]) & 4294967295L) >= (4294967295L & ((long) i2))) {
            iArr2[8] = iArr2[8] - i2;
        } else {
            if (iArr2[9] == 0) {
                throw new IllegalStateException("attempt to reduce counter past zero.");
            }
            iArr2[9] = iArr2[9] - 1;
            iArr2[8] = iArr2[8] - i2;
        }
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public byte returnByte(byte b) {
        if (limitExceeded()) {
            throw new MaxBytesExceededException("2^70 byte limit per IV; Change IV");
        }
        byte[] bArr = this.keyStream;
        int i = this.index;
        byte b2 = (byte) (b ^ bArr[i]);
        int i2 = (i + 1) & 63;
        this.index = i2;
        if (i2 == 0) {
            advanceCounter();
            generateKeyStream(this.keyStream);
        }
        return b2;
    }

    @Override // org.bouncycastle.crypto.SkippingCipher
    public long seekTo(long j) {
        reset();
        return skip(j);
    }

    public void setKey(byte[] bArr, byte[] bArr2) {
        if (bArr != null) {
            if (bArr.length != 16 && bArr.length != 32) {
                throw new IllegalArgumentException(getAlgorithmName() + " requires 128 bit or 256 bit key");
            }
            int length = (bArr.length - 16) / 4;
            int[] iArr = this.engineState;
            int[] iArr2 = TAU_SIGMA;
            iArr[0] = iArr2[length];
            iArr[5] = iArr2[length + 1];
            iArr[10] = iArr2[length + 2];
            iArr[15] = iArr2[length + 3];
            Pack.littleEndianToInt(bArr, 0, iArr, 1, 4);
            Pack.littleEndianToInt(bArr, bArr.length - 16, this.engineState, 11, 4);
        }
        Pack.littleEndianToInt(bArr2, 0, this.engineState, 6, 2);
    }

    @Override // org.bouncycastle.crypto.SkippingCipher
    public long skip(long j) {
        long j2;
        if (j >= 0) {
            if (j >= 64) {
                long j3 = j / 64;
                advanceCounter(j3);
                j2 = j - (j3 * 64);
            } else {
                j2 = j;
            }
            int i = this.index;
            int i2 = (((int) j2) + i) & 63;
            this.index = i2;
            if (i2 < i) {
                advanceCounter();
            }
        } else {
            long j4 = -j;
            if (j4 >= 64) {
                long j5 = j4 / 64;
                retreatCounter(j5);
                j4 -= j5 * 64;
            }
            for (long j6 = 0; j6 < j4; j6++) {
                if (this.index == 0) {
                    retreatCounter();
                }
                this.index = (this.index - 1) & 63;
            }
        }
        generateKeyStream(this.keyStream);
        return j;
    }
}
