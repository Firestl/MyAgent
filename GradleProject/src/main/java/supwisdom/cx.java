package supwisdom;

import com.alibaba.fastjson.parser.JSONLexer;
import com.efs.sdk.base.newsharedpreferences.SharedPreferencesNewImpl;
import com.google.zxing.maxicode.decoder.DecodedBitStreamParser;
import dc.squareup.okhttp3.internal.publicsuffix.PublicSuffixDatabase;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/* JADX INFO: loaded from: classes.dex */
public final class cx {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final NumberFormat f7269a = new DecimalFormat("000000000");
    public static final NumberFormat b = new DecimalFormat("000");
    public static final String[] c = {"\nABCDEFGHIJKLMNOPQRSTUVWXYZ\ufffa\u001c\u001d\u001e\ufffb ￼\"#$%&'()*+,-./0123456789:\ufff1\ufff2\ufff3\ufff4\ufff8", "`abcdefghijklmnopqrstuvwxyz\ufffa\u001c\u001d\u001e\ufffb{￼}~\u007f;<=>?[\\]^_ ,./:@!|￼\ufff5\ufff6￼\ufff0\ufff2\ufff3\ufff4\ufff7", "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚ\ufffa\u001c\u001d\u001eÛÜÝÞßª¬±²³µ¹º¼½¾\u0080\u0081\u0082\u0083\u0084\u0085\u0086\u0087\u0088\u0089\ufff7 \ufff9\ufff3\ufff4\ufff8", "àáâãäåæçèéêëìíîïðñòóôõö÷øùú\ufffa\u001c\u001d\u001e\ufffbûüýþÿ¡¨«¯°´·¸»¿\u008a\u008b\u008c\u008d\u008e\u008f\u0090\u0091\u0092\u0093\u0094\ufff7 \ufff2\ufff9\ufff4\ufff8", "\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\ufffa￼￼\u001b\ufffb\u001c\u001d\u001e\u001f\u009f ¢£¤¥¦§©\u00ad®¶\u0095\u0096\u0097\u0098\u0099\u009a\u009b\u009c\u009d\u009e\ufff7 \ufff2\ufff3\ufff9\ufff8", "\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'()*+,-./0123456789:;<=>?"};

    public static hw a(byte[] bArr, int i) {
        String strD;
        StringBuilder sb = new StringBuilder(144);
        if (i == 2 || i == 3) {
            if (i == 2) {
                strD = new DecimalFormat("0000000000".substring(0, c(bArr))).format(b(bArr));
            } else {
                strD = d(bArr);
            }
            NumberFormat numberFormat = b;
            String str = numberFormat.format(a(bArr));
            String str2 = numberFormat.format(e(bArr));
            sb.append(a(bArr, 10, 84));
            if (sb.toString().startsWith("[)>\u001e01\u001d")) {
                sb.insert(9, strD + DecodedBitStreamParser.GS + str + DecodedBitStreamParser.GS + str2 + DecodedBitStreamParser.GS);
            } else {
                sb.insert(0, strD + DecodedBitStreamParser.GS + str + DecodedBitStreamParser.GS + str2 + DecodedBitStreamParser.GS);
            }
        } else if (i == 4) {
            sb.append(a(bArr, 1, 93));
        } else if (i == 5) {
            sb.append(a(bArr, 1, 77));
        }
        return new hw(bArr, sb.toString(), null, String.valueOf(i));
    }

    public static int b(byte[] bArr) {
        return a(bArr, new byte[]{PublicSuffixDatabase.EXCEPTION_MARKER, 34, 35, 36, 25, JSONLexer.EOI, 27, 28, 29, 30, 19, 20, 21, 22, 23, 24, 13, com.umeng.analytics.pro.db.l, 15, 16, 17, SharedPreferencesNewImpl.FINISH_MARK, 7, 8, 9, 10, 11, 12, 1, 2});
    }

    public static int c(byte[] bArr) {
        return a(bArr, new byte[]{39, 40, 41, 42, com.umeng.analytics.pro.co.j, 32});
    }

    public static String d(byte[] bArr) {
        String[] strArr = c;
        return String.valueOf(new char[]{strArr[0].charAt(a(bArr, new byte[]{39, 40, 41, 42, com.umeng.analytics.pro.co.j, 32})), strArr[0].charAt(a(bArr, new byte[]{PublicSuffixDatabase.EXCEPTION_MARKER, 34, 35, 36, 25, JSONLexer.EOI})), strArr[0].charAt(a(bArr, new byte[]{27, 28, 29, 30, 19, 20})), strArr[0].charAt(a(bArr, new byte[]{21, 22, 23, 24, 13, com.umeng.analytics.pro.db.l})), strArr[0].charAt(a(bArr, new byte[]{15, 16, 17, SharedPreferencesNewImpl.FINISH_MARK, 7, 8})), strArr[0].charAt(a(bArr, new byte[]{9, 10, 11, 12, 1, 2}))});
    }

    public static int e(byte[] bArr) {
        return a(bArr, new byte[]{55, 56, 57, 58, 59, 60, 49, 50, 51, 52});
    }

    public static int a(int i, byte[] bArr) {
        int i2 = i - 1;
        return ((1 << (5 - (i2 % 6))) & bArr[i2 / 6]) == 0 ? 0 : 1;
    }

    public static int a(byte[] bArr) {
        return a(bArr, new byte[]{53, 54, 43, 44, 45, 46, 47, 48, 37, 38});
    }

    public static int a(byte[] bArr, byte[] bArr2) {
        if (bArr2.length != 0) {
            int iA = 0;
            for (int i = 0; i < bArr2.length; i++) {
                iA += a(bArr2[i], bArr) << ((bArr2.length - i) - 1);
            }
            return iA;
        }
        throw new IllegalArgumentException();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static String a(byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        int i3 = i;
        int i4 = 0;
        int i5 = 0;
        int i6 = -1;
        while (i3 < i + i2) {
            char cCharAt = c[i4].charAt(bArr[i3]);
            switch (cCharAt) {
                case 65520:
                case 65521:
                case 65522:
                case 65523:
                case 65524:
                    i5 = i4;
                    i4 = cCharAt - DecodedBitStreamParser.SHIFTA;
                    i6 = 1;
                    break;
                case 65525:
                    i6 = 2;
                    i5 = i4;
                    i4 = 0;
                    break;
                case 65526:
                    i6 = 3;
                    i5 = i4;
                    i4 = 0;
                    break;
                case 65527:
                    i4 = 0;
                    i6 = -1;
                    break;
                case 65528:
                    i4 = 1;
                    i6 = -1;
                    break;
                case 65529:
                    i6 = -1;
                    break;
                case 65530:
                default:
                    sb.append(cCharAt);
                    break;
                case 65531:
                    int i7 = i3 + 1;
                    int i8 = bArr[i7] << 24;
                    int i9 = i7 + 1;
                    int i10 = i8 + (bArr[i9] << SharedPreferencesNewImpl.FINISH_MARK);
                    int i11 = i9 + 1;
                    int i12 = i10 + (bArr[i11] << 12);
                    int i13 = i11 + 1;
                    int i14 = i12 + (bArr[i13] << 6);
                    i3 = i13 + 1;
                    sb.append(f7269a.format(i14 + bArr[i3]));
                    break;
            }
            int i15 = i6 - 1;
            if (i6 == 0) {
                i4 = i5;
            }
            i3++;
            i6 = i15;
        }
        while (sb.length() > 0 && sb.charAt(sb.length() - 1) == 65532) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
