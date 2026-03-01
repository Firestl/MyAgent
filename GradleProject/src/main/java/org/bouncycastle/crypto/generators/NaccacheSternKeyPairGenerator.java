package org.bouncycastle.crypto.generators;

import com.baidu.speech.core.BDSHttpRequestMaker;
import com.huawei.agconnect.exception.AGCServerException;
import com.igexin.push.core.a.c.h;
import java.io.PrintStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Vector;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.NaccacheSternKeyGenerationParameters;
import org.bouncycastle.crypto.params.NaccacheSternKeyParameters;
import org.bouncycastle.crypto.params.NaccacheSternPrivateKeyParameters;
import org.bouncycastle.util.BigIntegers;

/* JADX INFO: loaded from: classes3.dex */
public class NaccacheSternKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    public NaccacheSternKeyGenerationParameters param;
    public static int[] smallPrimes = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, h.b, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, BDSHttpRequestMaker.TYPE_DOWNLOAD_FINAL, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, AGCServerException.TOKEN_INVALID, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557};
    public static final BigInteger ONE = BigInteger.valueOf(1);

    public static Vector findFirstPrimes(int i) {
        Vector vector = new Vector(i);
        for (int i2 = 0; i2 != i; i2++) {
            vector.addElement(BigInteger.valueOf(smallPrimes[i2]));
        }
        return vector;
    }

    public static BigInteger generatePrime(int i, int i2, SecureRandom secureRandom) {
        BigInteger bigIntegerCreateRandomPrime;
        do {
            bigIntegerCreateRandomPrime = BigIntegers.createRandomPrime(i, i2, secureRandom);
        } while (bigIntegerCreateRandomPrime.bitLength() != i);
        return bigIntegerCreateRandomPrime;
    }

    public static int getInt(SecureRandom secureRandom, int i) {
        int iNextInt;
        int i2;
        if (((-i) & i) == i) {
            return (int) ((((long) i) * ((long) (secureRandom.nextInt() & Integer.MAX_VALUE))) >> 31);
        }
        do {
            iNextInt = secureRandom.nextInt() & Integer.MAX_VALUE;
            i2 = iNextInt % i;
        } while ((iNextInt - i2) + (i - 1) < 0);
        return i2;
    }

    public static Vector permuteList(Vector vector, SecureRandom secureRandom) {
        Vector vector2 = new Vector();
        Vector vector3 = new Vector();
        for (int i = 0; i < vector.size(); i++) {
            vector3.addElement(vector.elementAt(i));
        }
        vector2.addElement(vector3.elementAt(0));
        while (true) {
            vector3.removeElementAt(0);
            if (vector3.size() == 0) {
                return vector2;
            }
            vector2.insertElementAt(vector3.elementAt(0), getInt(secureRandom, vector2.size() + 1));
        }
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        long j;
        BigInteger bigIntegerGeneratePrime;
        BigInteger bigIntegerAdd;
        BigInteger bigIntegerGeneratePrime2;
        BigInteger bigInteger;
        BigInteger bigIntegerAdd2;
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        BigInteger bigInteger4;
        long j2;
        BigInteger bigInteger5;
        BigInteger bigIntegerMod;
        boolean z;
        BigInteger bigInteger6;
        PrintStream printStream;
        StringBuilder sb;
        String str;
        BigInteger bigIntegerCreateRandomPrime;
        int i;
        SecureRandom secureRandom;
        int strength = this.param.getStrength();
        SecureRandom random = this.param.getRandom();
        int certainty = this.param.getCertainty();
        boolean zIsDebug = this.param.isDebug();
        if (zIsDebug) {
            System.out.println("Fetching first " + this.param.getCntSmallPrimes() + " primes.");
        }
        Vector vectorPermuteList = permuteList(findFirstPrimes(this.param.getCntSmallPrimes()), random);
        BigInteger bigIntegerMultiply = ONE;
        BigInteger bigIntegerMultiply2 = bigIntegerMultiply;
        for (int i2 = 0; i2 < vectorPermuteList.size() / 2; i2++) {
            bigIntegerMultiply2 = bigIntegerMultiply2.multiply((BigInteger) vectorPermuteList.elementAt(i2));
        }
        for (int size = vectorPermuteList.size() / 2; size < vectorPermuteList.size(); size++) {
            bigIntegerMultiply = bigIntegerMultiply.multiply((BigInteger) vectorPermuteList.elementAt(size));
        }
        BigInteger bigIntegerMultiply3 = bigIntegerMultiply2.multiply(bigIntegerMultiply);
        int iBitLength = (((strength - bigIntegerMultiply3.bitLength()) - 48) / 2) + 1;
        BigInteger bigIntegerGeneratePrime3 = generatePrime(iBitLength, certainty, random);
        BigInteger bigIntegerGeneratePrime4 = generatePrime(iBitLength, certainty, random);
        if (zIsDebug) {
            System.out.println("generating p and q");
        }
        BigInteger bigIntegerShiftLeft = bigIntegerGeneratePrime3.multiply(bigIntegerMultiply2).shiftLeft(1);
        BigInteger bigIntegerShiftLeft2 = bigIntegerGeneratePrime4.multiply(bigIntegerMultiply).shiftLeft(1);
        long j3 = 0;
        while (true) {
            j = j3 + 1;
            bigIntegerGeneratePrime = generatePrime(24, certainty, random);
            bigIntegerAdd = bigIntegerGeneratePrime.multiply(bigIntegerShiftLeft).add(ONE);
            if (bigIntegerAdd.isProbablePrime(certainty)) {
                while (true) {
                    do {
                        bigIntegerGeneratePrime2 = generatePrime(24, certainty, random);
                    } while (bigIntegerGeneratePrime.equals(bigIntegerGeneratePrime2));
                    bigInteger = bigIntegerShiftLeft2;
                    bigIntegerAdd2 = bigIntegerGeneratePrime2.multiply(bigIntegerShiftLeft2).add(ONE);
                    if (bigIntegerAdd2.isProbablePrime(certainty)) {
                        break;
                    }
                    bigIntegerShiftLeft2 = bigInteger;
                }
                bigInteger2 = bigIntegerShiftLeft;
                if (!bigIntegerMultiply3.gcd(bigIntegerGeneratePrime.multiply(bigIntegerGeneratePrime2)).equals(ONE)) {
                    continue;
                } else {
                    if (bigIntegerAdd.multiply(bigIntegerAdd2).bitLength() >= strength) {
                        break;
                    }
                    if (zIsDebug) {
                        System.out.println("key size too small. Should be " + strength + " but is actually " + bigIntegerAdd.multiply(bigIntegerAdd2).bitLength());
                    }
                }
            } else {
                bigInteger = bigIntegerShiftLeft2;
                bigInteger2 = bigIntegerShiftLeft;
            }
            j3 = j;
            bigIntegerShiftLeft2 = bigInteger;
            bigIntegerShiftLeft = bigInteger2;
        }
        if (zIsDebug) {
            bigInteger3 = bigIntegerGeneratePrime4;
            System.out.println("needed " + j + " tries to generate p and q.");
        } else {
            bigInteger3 = bigIntegerGeneratePrime4;
        }
        BigInteger bigIntegerMultiply4 = bigIntegerAdd.multiply(bigIntegerAdd2);
        BigInteger bigIntegerMultiply5 = bigIntegerAdd.subtract(ONE).multiply(bigIntegerAdd2.subtract(ONE));
        if (zIsDebug) {
            System.out.println("generating g");
        }
        long j4 = 0;
        while (true) {
            Vector vector = new Vector();
            bigInteger4 = bigIntegerAdd2;
            j2 = j4;
            int i3 = 0;
            bigInteger5 = bigIntegerAdd;
            while (i3 != vectorPermuteList.size()) {
                BigInteger bigIntegerDivide = bigIntegerMultiply5.divide((BigInteger) vectorPermuteList.elementAt(i3));
                while (true) {
                    j2++;
                    bigIntegerCreateRandomPrime = BigIntegers.createRandomPrime(strength, certainty, random);
                    i = strength;
                    secureRandom = random;
                    if (bigIntegerCreateRandomPrime.modPow(bigIntegerDivide, bigIntegerMultiply4).equals(ONE)) {
                        strength = i;
                        random = secureRandom;
                    }
                }
                vector.addElement(bigIntegerCreateRandomPrime);
                i3++;
                strength = i;
                random = secureRandom;
            }
            int i4 = strength;
            SecureRandom secureRandom2 = random;
            bigIntegerMod = ONE;
            for (int i5 = 0; i5 < vectorPermuteList.size(); i5++) {
                bigIntegerMod = bigIntegerMod.multiply(((BigInteger) vector.elementAt(i5)).modPow(bigIntegerMultiply3.divide((BigInteger) vectorPermuteList.elementAt(i5)), bigIntegerMultiply4)).mod(bigIntegerMultiply4);
            }
            int i6 = 0;
            while (true) {
                if (i6 >= vectorPermuteList.size()) {
                    z = false;
                    break;
                }
                if (bigIntegerMod.modPow(bigIntegerMultiply5.divide((BigInteger) vectorPermuteList.elementAt(i6)), bigIntegerMultiply4).equals(ONE)) {
                    if (zIsDebug) {
                        System.out.println("g has order phi(n)/" + vectorPermuteList.elementAt(i6) + "\n g: " + bigIntegerMod);
                    }
                    z = true;
                } else {
                    i6++;
                }
            }
            if (z) {
                bigInteger6 = bigInteger3;
            } else if (bigIntegerMod.modPow(bigIntegerMultiply5.divide(BigInteger.valueOf(4L)), bigIntegerMultiply4).equals(ONE)) {
                if (zIsDebug) {
                    printStream = System.out;
                    sb = new StringBuilder();
                    str = "g has order phi(n)/4\n g:";
                    sb.append(str);
                    sb.append(bigIntegerMod);
                    printStream.println(sb.toString());
                }
                bigInteger6 = bigInteger3;
            } else if (bigIntegerMod.modPow(bigIntegerMultiply5.divide(bigIntegerGeneratePrime), bigIntegerMultiply4).equals(ONE)) {
                if (zIsDebug) {
                    printStream = System.out;
                    sb = new StringBuilder();
                    str = "g has order phi(n)/p'\n g: ";
                    sb.append(str);
                    sb.append(bigIntegerMod);
                    printStream.println(sb.toString());
                }
                bigInteger6 = bigInteger3;
            } else if (bigIntegerMod.modPow(bigIntegerMultiply5.divide(bigIntegerGeneratePrime2), bigIntegerMultiply4).equals(ONE)) {
                if (zIsDebug) {
                    printStream = System.out;
                    sb = new StringBuilder();
                    str = "g has order phi(n)/q'\n g: ";
                    sb.append(str);
                    sb.append(bigIntegerMod);
                    printStream.println(sb.toString());
                }
                bigInteger6 = bigInteger3;
            } else if (!bigIntegerMod.modPow(bigIntegerMultiply5.divide(bigIntegerGeneratePrime3), bigIntegerMultiply4).equals(ONE)) {
                bigInteger6 = bigInteger3;
                if (!bigIntegerMod.modPow(bigIntegerMultiply5.divide(bigInteger6), bigIntegerMultiply4).equals(ONE)) {
                    break;
                }
                if (zIsDebug) {
                    System.out.println("g has order phi(n)/b\n g: " + bigIntegerMod);
                }
            } else {
                if (zIsDebug) {
                    printStream = System.out;
                    sb = new StringBuilder();
                    str = "g has order phi(n)/a\n g: ";
                    sb.append(str);
                    sb.append(bigIntegerMod);
                    printStream.println(sb.toString());
                }
                bigInteger6 = bigInteger3;
            }
            bigInteger3 = bigInteger6;
            bigIntegerAdd = bigInteger5;
            j4 = j2;
            bigIntegerAdd2 = bigInteger4;
            strength = i4;
            random = secureRandom2;
        }
        if (zIsDebug) {
            System.out.println("needed " + j2 + " tries to generate g");
            System.out.println();
            System.out.println("found new NaccacheStern cipher variables:");
            System.out.println("smallPrimes: " + vectorPermuteList);
            System.out.println("sigma:...... " + bigIntegerMultiply3 + " (" + bigIntegerMultiply3.bitLength() + " bits)");
            PrintStream printStream2 = System.out;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("a:.......... ");
            sb2.append(bigIntegerGeneratePrime3);
            printStream2.println(sb2.toString());
            System.out.println("b:.......... " + bigInteger6);
            System.out.println("p':......... " + bigIntegerGeneratePrime);
            System.out.println("q':......... " + bigIntegerGeneratePrime2);
            System.out.println("p:.......... " + bigInteger5);
            System.out.println("q:.......... " + bigInteger4);
            System.out.println("n:.......... " + bigIntegerMultiply4);
            System.out.println("phi(n):..... " + bigIntegerMultiply5);
            System.out.println("g:.......... " + bigIntegerMod);
            System.out.println();
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new NaccacheSternKeyParameters(false, bigIntegerMod, bigIntegerMultiply4, bigIntegerMultiply3.bitLength()), (AsymmetricKeyParameter) new NaccacheSternPrivateKeyParameters(bigIntegerMod, bigIntegerMultiply4, bigIntegerMultiply3.bitLength(), vectorPermuteList, bigIntegerMultiply5));
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.param = (NaccacheSternKeyGenerationParameters) keyGenerationParameters;
    }
}
