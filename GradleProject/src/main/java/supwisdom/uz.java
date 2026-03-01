package supwisdom;

import com.dcloud.zxing2.DecodeHintType;
import com.dcloud.zxing2.FormatException;
import com.dcloud.zxing2.common.CharacterSetECI;
import com.dcloud.zxing2.qrcode.decoder.ErrorCorrectionLevel;
import com.dcloud.zxing2.qrcode.decoder.Mode;
import com.google.zxing.common.StringUtils;
import com.huawei.secure.android.common.util.LogsUtil;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.utils.FunctionParser;
import com.taobao.weex.utils.WXUtils;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

/* JADX INFO: loaded from: classes.dex */
public final class uz {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final char[] f9456a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', FunctionParser.Lexer.NINE, FunctionParser.Lexer.A_UPPER, 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', Matrix.MATRIX_TYPE_RANDOM_LT, 'M', 'N', 'O', 'P', 'Q', Matrix.MATRIX_TYPE_RANDOM_REGULAR, 'S', 'T', Matrix.MATRIX_TYPE_RANDOM_UT, 'V', 'W', 'X', 'Y', 'Z', ' ', '$', WXUtils.PERCENT, LogsUtil.b, FunctionParser.Lexer.PLUS, FunctionParser.Lexer.MINUS, '.', '/', Operators.CONDITION_IF_MIDDLE};

    public static hw a(byte[] bArr, yz yzVar, ErrorCorrectionLevel errorCorrectionLevel, Map<DecodeHintType, ?> map) throws FormatException {
        Mode mode;
        Mode mode2;
        int i;
        String strA;
        gw gwVar = new gw(bArr);
        StringBuilder sb = new StringBuilder(50);
        int i2 = 1;
        ArrayList arrayList = new ArrayList(1);
        String str = "";
        int i3 = -1;
        int iA = -1;
        boolean z = false;
        CharacterSetECI characterSetECIByValue = null;
        while (true) {
            try {
                Mode modeForBits = gwVar.a() < 4 ? Mode.TERMINATOR : Mode.forBits(gwVar.a(4));
                Mode mode3 = Mode.TERMINATOR;
                if (modeForBits == mode3) {
                    mode = mode3;
                    mode2 = modeForBits;
                    i = i3;
                    strA = str;
                } else {
                    if (modeForBits != Mode.FNC1_FIRST_POSITION && modeForBits != Mode.FNC1_SECOND_POSITION) {
                        if (modeForBits != Mode.STRUCTURED_APPEND) {
                            if (modeForBits == Mode.ECI) {
                                characterSetECIByValue = CharacterSetECI.getCharacterSetECIByValue(a(gwVar));
                                if (characterSetECIByValue == null) {
                                    throw FormatException.getFormatInstance();
                                }
                            } else if (modeForBits == Mode.HANZI) {
                                int iA2 = gwVar.a(4);
                                int iA3 = gwVar.a(modeForBits.getCharacterCountBits(yzVar));
                                if (iA2 == i2) {
                                    a(gwVar, sb, iA3);
                                }
                            } else {
                                int iA4 = gwVar.a(modeForBits.getCharacterCountBits(yzVar));
                                if (modeForBits == Mode.NUMERIC) {
                                    c(gwVar, sb, iA4);
                                } else if (modeForBits == Mode.ALPHANUMERIC) {
                                    a(gwVar, sb, iA4, z);
                                } else if (modeForBits == Mode.BYTE) {
                                    mode = mode3;
                                    mode2 = modeForBits;
                                    i = i3;
                                    strA = a(gwVar, sb, iA4, characterSetECIByValue, arrayList, map);
                                } else {
                                    mode = mode3;
                                    mode2 = modeForBits;
                                    if (mode2 != Mode.KANJI) {
                                        throw FormatException.getFormatInstance();
                                    }
                                    b(gwVar, sb, iA4);
                                    i = i3;
                                    strA = str;
                                }
                            }
                            mode = mode3;
                            mode2 = modeForBits;
                            i = i3;
                            strA = str;
                        } else {
                            if (gwVar.a() < 16) {
                                throw FormatException.getFormatInstance();
                            }
                            int iA5 = gwVar.a(8);
                            iA = gwVar.a(8);
                            strA = str;
                            mode = mode3;
                            mode2 = modeForBits;
                            i = iA5;
                        }
                    }
                    mode = mode3;
                    mode2 = modeForBits;
                    i = i3;
                    z = true;
                    strA = str;
                }
                if (mode2 == mode) {
                    hw hwVar = new hw(bArr, sb.toString(), arrayList.isEmpty() ? null : arrayList, errorCorrectionLevel == null ? null : errorCorrectionLevel.toString(), i, iA);
                    hwVar.h = strA;
                    return hwVar;
                }
                str = strA;
                i2 = 1;
                i3 = i;
            } catch (IllegalArgumentException unused) {
                throw FormatException.getFormatInstance();
            }
        }
    }

    public static void b(gw gwVar, StringBuilder sb, int i) throws FormatException {
        if (i * 13 > gwVar.a()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[i * 2];
        int i2 = 0;
        while (i > 0) {
            int iA = gwVar.a(13);
            int i3 = (iA % 192) | ((iA / 192) << 8);
            int i4 = i3 + (i3 < 7936 ? 33088 : 49472);
            bArr[i2] = (byte) (i4 >> 8);
            bArr[i2 + 1] = (byte) i4;
            i2 += 2;
            i--;
        }
        try {
            sb.append(new String(bArr, "UTF-8"));
        } catch (UnsupportedEncodingException unused) {
            throw FormatException.getFormatInstance();
        }
    }

    public static void c(gw gwVar, StringBuilder sb, int i) throws FormatException {
        while (i >= 3) {
            if (gwVar.a() < 10) {
                throw FormatException.getFormatInstance();
            }
            int iA = gwVar.a(10);
            if (iA >= 1000) {
                throw FormatException.getFormatInstance();
            }
            sb.append(a(iA / 100));
            sb.append(a((iA / 10) % 10));
            sb.append(a(iA % 10));
            i -= 3;
        }
        if (i == 2) {
            if (gwVar.a() < 7) {
                throw FormatException.getFormatInstance();
            }
            int iA2 = gwVar.a(7);
            if (iA2 >= 100) {
                throw FormatException.getFormatInstance();
            }
            sb.append(a(iA2 / 10));
            sb.append(a(iA2 % 10));
            return;
        }
        if (i == 1) {
            if (gwVar.a() < 4) {
                throw FormatException.getFormatInstance();
            }
            int iA3 = gwVar.a(4);
            if (iA3 >= 10) {
                throw FormatException.getFormatInstance();
            }
            sb.append(a(iA3));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(supwisdom.gw r3, java.lang.StringBuilder r4, int r5, boolean r6) throws com.dcloud.zxing2.FormatException {
        /*
            int r0 = r4.length()
        L4:
            r1 = 1
            if (r5 <= r1) goto L2d
            int r1 = r3.a()
            r2 = 11
            if (r1 < r2) goto L28
            int r1 = r3.a(r2)
            int r2 = r1 / 45
            char r2 = a(r2)
            r4.append(r2)
            int r1 = r1 % 45
            char r1 = a(r1)
            r4.append(r1)
            int r5 = r5 + (-2)
            goto L4
        L28:
            com.dcloud.zxing2.FormatException r3 = com.dcloud.zxing2.FormatException.getFormatInstance()
            throw r3
        L2d:
            if (r5 != r1) goto L47
            int r5 = r3.a()
            r2 = 6
            if (r5 < r2) goto L42
            int r3 = r3.a(r2)
            char r3 = a(r3)
            r4.append(r3)
            goto L47
        L42:
            com.dcloud.zxing2.FormatException r3 = com.dcloud.zxing2.FormatException.getFormatInstance()
            throw r3
        L47:
            if (r6 == 0) goto L72
        L49:
            int r3 = r4.length()
            if (r0 >= r3) goto L72
            char r3 = r4.charAt(r0)
            r5 = 37
            if (r3 != r5) goto L6f
            int r3 = r4.length()
            int r3 = r3 - r1
            if (r0 >= r3) goto L6a
            int r3 = r0 + 1
            char r6 = r4.charAt(r3)
            if (r6 != r5) goto L6a
            r4.deleteCharAt(r3)
            goto L6f
        L6a:
            r3 = 29
            r4.setCharAt(r0, r3)
        L6f:
            int r0 = r0 + 1
            goto L49
        L72:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: supwisdom.uz.a(supwisdom.gw, java.lang.StringBuilder, int, boolean):void");
    }

    public static String a(gw gwVar, StringBuilder sb, int i, CharacterSetECI characterSetECI, Collection<byte[]> collection, Map<DecodeHintType, ?> map) throws FormatException {
        String strName;
        if (i * 8 <= gwVar.a()) {
            byte[] bArr = new byte[i];
            for (int i2 = 0; i2 < i; i2++) {
                bArr[i2] = (byte) gwVar.a(8);
            }
            if (characterSetECI == null) {
                strName = nw.a(bArr, map);
            } else {
                strName = characterSetECI.name();
            }
            try {
                sb.append(new String(bArr, strName));
                collection.add(bArr);
                return strName;
            } catch (UnsupportedEncodingException unused) {
                throw FormatException.getFormatInstance();
            }
        }
        throw FormatException.getFormatInstance();
    }

    public static void a(gw gwVar, StringBuilder sb, int i) throws FormatException {
        if (i * 13 <= gwVar.a()) {
            byte[] bArr = new byte[i * 2];
            int i2 = 0;
            while (i > 0) {
                int iA = gwVar.a(13);
                int i3 = (iA % 96) | ((iA / 96) << 8);
                int i4 = i3 + (i3 < 959 ? 41377 : 42657);
                bArr[i2] = (byte) ((i4 >> 8) & 255);
                bArr[i2 + 1] = (byte) (i4 & 255);
                i2 += 2;
                i--;
            }
            try {
                sb.append(new String(bArr, StringUtils.GB2312));
                return;
            } catch (UnsupportedEncodingException unused) {
                throw FormatException.getFormatInstance();
            }
        }
        throw FormatException.getFormatInstance();
    }

    public static int a(gw gwVar) throws FormatException {
        int iA = gwVar.a(8);
        if ((iA & 128) == 0) {
            return iA & 127;
        }
        if ((iA & 192) == 128) {
            return gwVar.a(8) | ((iA & 63) << 8);
        }
        if ((iA & 224) == 192) {
            return gwVar.a(16) | ((iA & 31) << 16);
        }
        throw FormatException.getFormatInstance();
    }

    public static char a(int i) throws FormatException {
        char[] cArr = f9456a;
        if (i < cArr.length) {
            return cArr[i];
        }
        throw FormatException.getFormatInstance();
    }
}
