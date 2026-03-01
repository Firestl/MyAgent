package org.bouncycastle.crypto.generators;

import com.tencent.liteav.TXLiteAVCode;
import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.params.GOST3410Parameters;
import org.bouncycastle.crypto.params.GOST3410ValidationParameters;
import org.bouncycastle.util.BigIntegers;

/* JADX INFO: loaded from: classes3.dex */
public class GOST3410ParametersGenerator {
    public static final BigInteger ONE = BigInteger.valueOf(1);
    public static final BigInteger TWO = BigInteger.valueOf(2);
    public SecureRandom init_random;
    public int size;
    public int typeproc;

    private int procedure_A(int i, int i2, BigInteger[] bigIntegerArr, int i3) {
        BigInteger bigInteger;
        BigInteger[] bigIntegerArr2;
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        int iNextInt = i;
        while (true) {
            if (iNextInt >= 0 && iNextInt <= 65536) {
                break;
            }
            iNextInt = this.init_random.nextInt() / 32768;
        }
        int iNextInt2 = i2;
        while (true) {
            if (iNextInt2 >= 0 && iNextInt2 <= 65536 && iNextInt2 / 2 != 0) {
                break;
            }
            iNextInt2 = (this.init_random.nextInt() / 32768) + 1;
        }
        BigInteger bigInteger4 = new BigInteger(Integer.toString(iNextInt2));
        BigInteger bigInteger5 = new BigInteger("19381");
        BigInteger bigInteger6 = new BigInteger(Integer.toString(iNextInt));
        int i4 = 0;
        BigInteger[] bigIntegerArr3 = {bigInteger6};
        int[] iArr = {i3};
        int i5 = 0;
        int i6 = 0;
        while (iArr[i5] >= 17) {
            int length = iArr.length + 1;
            int[] iArr2 = new int[length];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            iArr = new int[length];
            System.arraycopy(iArr2, 0, iArr, 0, length);
            i6 = i5 + 1;
            iArr[i6] = iArr[i5] / 2;
            i5 = i6;
        }
        BigInteger[] bigIntegerArr4 = new BigInteger[i6 + 1];
        int i7 = 16;
        bigIntegerArr4[i6] = new BigInteger("8003", 16);
        int i8 = i6 - 1;
        int i9 = 0;
        while (true) {
            if (i9 >= i6) {
                bigInteger = bigIntegerArr3[0];
                break;
            }
            int i10 = iArr[i8] / i7;
            while (true) {
                int length2 = bigIntegerArr3.length;
                BigInteger[] bigIntegerArr5 = new BigInteger[length2];
                System.arraycopy(bigIntegerArr3, i4, bigIntegerArr5, i4, bigIntegerArr3.length);
                bigIntegerArr2 = new BigInteger[i10 + 1];
                System.arraycopy(bigIntegerArr5, i4, bigIntegerArr2, i4, length2);
                int i11 = 0;
                while (i11 < i10) {
                    int i12 = i11 + 1;
                    bigIntegerArr2[i12] = bigIntegerArr2[i11].multiply(bigInteger5).add(bigInteger4).mod(TWO.pow(i7));
                    i11 = i12;
                }
                BigInteger bigInteger7 = new BigInteger("0");
                for (int i13 = 0; i13 < i10; i13++) {
                    bigInteger7 = bigInteger7.add(bigIntegerArr2[i13].multiply(TWO.pow(i13 * 16)));
                }
                bigIntegerArr2[i4] = bigIntegerArr2[i10];
                int i14 = i8 + 1;
                BigInteger bigIntegerAdd = TWO.pow(iArr[i8] - 1).divide(bigIntegerArr4[i14]).add(TWO.pow(iArr[i8] - 1).multiply(bigInteger7).divide(bigIntegerArr4[i14].multiply(TWO.pow(i10 * 16))));
                if (bigIntegerAdd.mod(TWO).compareTo(ONE) == 0) {
                    bigIntegerAdd = bigIntegerAdd.add(ONE);
                }
                int i15 = 0;
                while (true) {
                    long j = i15;
                    bigInteger2 = bigInteger4;
                    bigIntegerArr4[i8] = bigIntegerArr4[i14].multiply(bigIntegerAdd.add(BigInteger.valueOf(j))).add(ONE);
                    bigInteger3 = bigInteger5;
                    if (bigIntegerArr4[i8].compareTo(TWO.pow(iArr[i8])) == 1) {
                        break;
                    }
                    if (TWO.modPow(bigIntegerArr4[i14].multiply(bigIntegerAdd.add(BigInteger.valueOf(j))), bigIntegerArr4[i8]).compareTo(ONE) == 0 && TWO.modPow(bigIntegerAdd.add(BigInteger.valueOf(j)), bigIntegerArr4[i8]).compareTo(ONE) != 0) {
                        break;
                    }
                    i15 += 2;
                    bigInteger4 = bigInteger2;
                    bigInteger5 = bigInteger3;
                }
                bigIntegerArr3 = bigIntegerArr2;
                bigInteger4 = bigInteger2;
                bigInteger5 = bigInteger3;
                i4 = 0;
                i7 = 16;
            }
            i8--;
            if (i8 < 0) {
                bigIntegerArr[0] = bigIntegerArr4[0];
                bigIntegerArr[1] = bigIntegerArr4[1];
                bigInteger = bigIntegerArr2[0];
                break;
            }
            i9++;
            bigIntegerArr3 = bigIntegerArr2;
            bigInteger4 = bigInteger2;
            bigInteger5 = bigInteger3;
            i4 = 0;
            i7 = 16;
        }
        return bigInteger.intValue();
    }

    private long procedure_Aa(long j, long j2, BigInteger[] bigIntegerArr, int i) {
        BigInteger bigInteger;
        BigInteger[] bigIntegerArr2;
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        long jNextInt = j;
        while (true) {
            if (jNextInt >= 0 && jNextInt <= 4294967296L) {
                break;
            }
            jNextInt = this.init_random.nextInt() * 2;
        }
        long jNextInt2 = j2;
        while (true) {
            if (jNextInt2 >= 0 && jNextInt2 <= 4294967296L && jNextInt2 / 2 != 0) {
                break;
            }
            jNextInt2 = (this.init_random.nextInt() * 2) + 1;
        }
        BigInteger bigInteger4 = new BigInteger(Long.toString(jNextInt2));
        BigInteger bigInteger5 = new BigInteger("97781173");
        BigInteger bigInteger6 = new BigInteger(Long.toString(jNextInt));
        int i2 = 0;
        BigInteger[] bigIntegerArr3 = {bigInteger6};
        int[] iArr = {i};
        int i3 = 0;
        int i4 = 0;
        while (iArr[i3] >= 33) {
            int length = iArr.length + 1;
            int[] iArr2 = new int[length];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            iArr = new int[length];
            System.arraycopy(iArr2, 0, iArr, 0, length);
            i4 = i3 + 1;
            iArr[i4] = iArr[i3] / 2;
            i3 = i4;
        }
        BigInteger[] bigIntegerArr4 = new BigInteger[i4 + 1];
        bigIntegerArr4[i4] = new BigInteger("8000000B", 16);
        int i5 = i4 - 1;
        int i6 = 0;
        while (true) {
            if (i6 >= i4) {
                bigInteger = bigIntegerArr3[0];
                break;
            }
            int i7 = 32;
            int i8 = iArr[i5] / 32;
            while (true) {
                int length2 = bigIntegerArr3.length;
                BigInteger[] bigIntegerArr5 = new BigInteger[length2];
                System.arraycopy(bigIntegerArr3, i2, bigIntegerArr5, i2, bigIntegerArr3.length);
                bigIntegerArr2 = new BigInteger[i8 + 1];
                System.arraycopy(bigIntegerArr5, i2, bigIntegerArr2, i2, length2);
                int i9 = 0;
                while (i9 < i8) {
                    int i10 = i9 + 1;
                    bigIntegerArr2[i10] = bigIntegerArr2[i9].multiply(bigInteger5).add(bigInteger4).mod(TWO.pow(i7));
                    i9 = i10;
                }
                BigInteger bigInteger7 = new BigInteger("0");
                for (int i11 = 0; i11 < i8; i11++) {
                    bigInteger7 = bigInteger7.add(bigIntegerArr2[i11].multiply(TWO.pow(i11 * 32)));
                }
                bigIntegerArr2[i2] = bigIntegerArr2[i8];
                int i12 = i5 + 1;
                BigInteger bigIntegerAdd = TWO.pow(iArr[i5] - 1).divide(bigIntegerArr4[i12]).add(TWO.pow(iArr[i5] - 1).multiply(bigInteger7).divide(bigIntegerArr4[i12].multiply(TWO.pow(i8 * 32))));
                if (bigIntegerAdd.mod(TWO).compareTo(ONE) == 0) {
                    bigIntegerAdd = bigIntegerAdd.add(ONE);
                }
                int i13 = 0;
                while (true) {
                    long j3 = i13;
                    bigInteger2 = bigInteger4;
                    bigIntegerArr4[i5] = bigIntegerArr4[i12].multiply(bigIntegerAdd.add(BigInteger.valueOf(j3))).add(ONE);
                    bigInteger3 = bigInteger5;
                    if (bigIntegerArr4[i5].compareTo(TWO.pow(iArr[i5])) == 1) {
                        break;
                    }
                    if (TWO.modPow(bigIntegerArr4[i12].multiply(bigIntegerAdd.add(BigInteger.valueOf(j3))), bigIntegerArr4[i5]).compareTo(ONE) == 0 && TWO.modPow(bigIntegerAdd.add(BigInteger.valueOf(j3)), bigIntegerArr4[i5]).compareTo(ONE) != 0) {
                        break;
                    }
                    i13 += 2;
                    bigInteger4 = bigInteger2;
                    bigInteger5 = bigInteger3;
                }
                bigInteger4 = bigInteger2;
                bigIntegerArr3 = bigIntegerArr2;
                bigInteger5 = bigInteger3;
                i2 = 0;
                i7 = 32;
            }
            i5--;
            if (i5 < 0) {
                bigIntegerArr[0] = bigIntegerArr4[0];
                bigIntegerArr[1] = bigIntegerArr4[1];
                bigInteger = bigIntegerArr2[0];
                break;
            }
            i6++;
            bigInteger4 = bigInteger2;
            bigIntegerArr3 = bigIntegerArr2;
            bigInteger5 = bigInteger3;
            i2 = 0;
        }
        return bigInteger.longValue();
    }

    private void procedure_B(int i, int i2, BigInteger[] bigIntegerArr) {
        int iNextInt = i;
        while (true) {
            if (iNextInt >= 0 && iNextInt <= 65536) {
                break;
            } else {
                iNextInt = this.init_random.nextInt() / 32768;
            }
        }
        int iNextInt2 = i2;
        while (true) {
            if (iNextInt2 >= 0 && iNextInt2 <= 65536 && iNextInt2 / 2 != 0) {
                break;
            } else {
                iNextInt2 = (this.init_random.nextInt() / 32768) + 1;
            }
        }
        BigInteger[] bigIntegerArr2 = new BigInteger[2];
        BigInteger bigInteger = new BigInteger(Integer.toString(iNextInt2));
        BigInteger bigInteger2 = new BigInteger("19381");
        int iProcedure_A = procedure_A(iNextInt, iNextInt2, bigIntegerArr2, 256);
        BigInteger bigInteger3 = bigIntegerArr2[0];
        int iProcedure_A2 = procedure_A(iProcedure_A, iNextInt2, bigIntegerArr2, 512);
        BigInteger bigInteger4 = bigIntegerArr2[0];
        BigInteger[] bigIntegerArr3 = new BigInteger[65];
        bigIntegerArr3[0] = new BigInteger(Integer.toString(iProcedure_A2));
        while (true) {
            int i3 = 0;
            while (i3 < 64) {
                int i4 = i3 + 1;
                bigIntegerArr3[i4] = bigIntegerArr3[i3].multiply(bigInteger2).add(bigInteger).mod(TWO.pow(16));
                i3 = i4;
            }
            BigInteger bigInteger5 = new BigInteger("0");
            for (int i5 = 0; i5 < 64; i5++) {
                bigInteger5 = bigInteger5.add(bigIntegerArr3[i5].multiply(TWO.pow(i5 * 16)));
            }
            bigIntegerArr3[0] = bigIntegerArr3[64];
            int i6 = 1024;
            BigInteger bigIntegerAdd = TWO.pow(TXLiteAVCode.EVT_CAMERA_REMOVED).divide(bigInteger3.multiply(bigInteger4)).add(TWO.pow(TXLiteAVCode.EVT_CAMERA_REMOVED).multiply(bigInteger5).divide(bigInteger3.multiply(bigInteger4).multiply(TWO.pow(1024))));
            if (bigIntegerAdd.mod(TWO).compareTo(ONE) == 0) {
                bigIntegerAdd = bigIntegerAdd.add(ONE);
            }
            BigInteger bigInteger6 = bigIntegerAdd;
            int i7 = 0;
            while (true) {
                long j = i7;
                BigInteger bigIntegerAdd2 = bigInteger3.multiply(bigInteger4).multiply(bigInteger6.add(BigInteger.valueOf(j))).add(ONE);
                if (bigIntegerAdd2.compareTo(TWO.pow(i6)) == 1) {
                    break;
                }
                if (TWO.modPow(bigInteger3.multiply(bigInteger4).multiply(bigInteger6.add(BigInteger.valueOf(j))), bigIntegerAdd2).compareTo(ONE) == 0 && TWO.modPow(bigInteger3.multiply(bigInteger6.add(BigInteger.valueOf(j))), bigIntegerAdd2).compareTo(ONE) != 0) {
                    bigIntegerArr[0] = bigIntegerAdd2;
                    bigIntegerArr[1] = bigInteger3;
                    return;
                } else {
                    i7 += 2;
                    i6 = 1024;
                }
            }
        }
    }

    private void procedure_Bb(long j, long j2, BigInteger[] bigIntegerArr) {
        long jNextInt = j;
        while (true) {
            if (jNextInt >= 0 && jNextInt <= 4294967296L) {
                break;
            } else {
                jNextInt = this.init_random.nextInt() * 2;
            }
        }
        long jNextInt2 = j2;
        while (true) {
            if (jNextInt2 >= 0 && jNextInt2 <= 4294967296L && jNextInt2 / 2 != 0) {
                break;
            } else {
                jNextInt2 = (this.init_random.nextInt() * 2) + 1;
            }
        }
        BigInteger[] bigIntegerArr2 = new BigInteger[2];
        BigInteger bigInteger = new BigInteger(Long.toString(jNextInt2));
        BigInteger bigInteger2 = new BigInteger("97781173");
        long j3 = jNextInt2;
        long jProcedure_Aa = procedure_Aa(jNextInt, j3, bigIntegerArr2, 256);
        BigInteger bigInteger3 = bigIntegerArr2[0];
        long jProcedure_Aa2 = procedure_Aa(jProcedure_Aa, j3, bigIntegerArr2, 512);
        BigInteger bigInteger4 = bigIntegerArr2[0];
        BigInteger[] bigIntegerArr3 = new BigInteger[33];
        bigIntegerArr3[0] = new BigInteger(Long.toString(jProcedure_Aa2));
        while (true) {
            int i = 0;
            while (i < 32) {
                int i2 = i + 1;
                bigIntegerArr3[i2] = bigIntegerArr3[i].multiply(bigInteger2).add(bigInteger).mod(TWO.pow(32));
                i = i2;
            }
            BigInteger bigInteger5 = new BigInteger("0");
            for (int i3 = 0; i3 < 32; i3++) {
                bigInteger5 = bigInteger5.add(bigIntegerArr3[i3].multiply(TWO.pow(i3 * 32)));
            }
            bigIntegerArr3[0] = bigIntegerArr3[32];
            int i4 = 1024;
            BigInteger bigIntegerAdd = TWO.pow(TXLiteAVCode.EVT_CAMERA_REMOVED).divide(bigInteger3.multiply(bigInteger4)).add(TWO.pow(TXLiteAVCode.EVT_CAMERA_REMOVED).multiply(bigInteger5).divide(bigInteger3.multiply(bigInteger4).multiply(TWO.pow(1024))));
            if (bigIntegerAdd.mod(TWO).compareTo(ONE) == 0) {
                bigIntegerAdd = bigIntegerAdd.add(ONE);
            }
            int i5 = 0;
            while (true) {
                long j4 = i5;
                BigInteger bigIntegerAdd2 = bigInteger3.multiply(bigInteger4).multiply(bigIntegerAdd.add(BigInteger.valueOf(j4))).add(ONE);
                if (bigIntegerAdd2.compareTo(TWO.pow(i4)) == 1) {
                    break;
                }
                if (TWO.modPow(bigInteger3.multiply(bigInteger4).multiply(bigIntegerAdd.add(BigInteger.valueOf(j4))), bigIntegerAdd2).compareTo(ONE) == 0 && TWO.modPow(bigInteger3.multiply(bigIntegerAdd.add(BigInteger.valueOf(j4))), bigIntegerAdd2).compareTo(ONE) != 0) {
                    bigIntegerArr[0] = bigIntegerAdd2;
                    bigIntegerArr[1] = bigInteger3;
                    return;
                } else {
                    i5 += 2;
                    i4 = 1024;
                }
            }
        }
    }

    private BigInteger procedure_C(BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger bigIntegerSubtract = bigInteger.subtract(ONE);
        BigInteger bigIntegerDivide = bigIntegerSubtract.divide(bigInteger2);
        int iBitLength = bigInteger.bitLength();
        while (true) {
            BigInteger bigIntegerCreateRandomBigInteger = BigIntegers.createRandomBigInteger(iBitLength, this.init_random);
            if (bigIntegerCreateRandomBigInteger.compareTo(ONE) > 0 && bigIntegerCreateRandomBigInteger.compareTo(bigIntegerSubtract) < 0) {
                BigInteger bigIntegerModPow = bigIntegerCreateRandomBigInteger.modPow(bigIntegerDivide, bigInteger);
                if (bigIntegerModPow.compareTo(ONE) != 0) {
                    return bigIntegerModPow;
                }
            }
        }
    }

    public GOST3410Parameters generateParameters() {
        BigInteger[] bigIntegerArr = new BigInteger[2];
        if (this.typeproc == 1) {
            int iNextInt = this.init_random.nextInt();
            int iNextInt2 = this.init_random.nextInt();
            int i = this.size;
            if (i == 512) {
                procedure_A(iNextInt, iNextInt2, bigIntegerArr, 512);
            } else {
                if (i != 1024) {
                    throw new IllegalArgumentException("Ooops! key size 512 or 1024 bit.");
                }
                procedure_B(iNextInt, iNextInt2, bigIntegerArr);
            }
            BigInteger bigInteger = bigIntegerArr[0];
            BigInteger bigInteger2 = bigIntegerArr[1];
            return new GOST3410Parameters(bigInteger, bigInteger2, procedure_C(bigInteger, bigInteger2), new GOST3410ValidationParameters(iNextInt, iNextInt2));
        }
        long jNextLong = this.init_random.nextLong();
        long jNextLong2 = this.init_random.nextLong();
        int i2 = this.size;
        if (i2 == 512) {
            procedure_Aa(jNextLong, jNextLong2, bigIntegerArr, 512);
        } else {
            if (i2 != 1024) {
                throw new IllegalStateException("Ooops! key size 512 or 1024 bit.");
            }
            procedure_Bb(jNextLong, jNextLong2, bigIntegerArr);
        }
        BigInteger bigInteger3 = bigIntegerArr[0];
        BigInteger bigInteger4 = bigIntegerArr[1];
        return new GOST3410Parameters(bigInteger3, bigInteger4, procedure_C(bigInteger3, bigInteger4), new GOST3410ValidationParameters(jNextLong, jNextLong2));
    }

    public void init(int i, int i2, SecureRandom secureRandom) {
        this.size = i;
        this.typeproc = i2;
        this.init_random = secureRandom;
    }
}
