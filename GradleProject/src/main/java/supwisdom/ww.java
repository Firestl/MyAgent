package supwisdom;

import com.dcloud.zxing2.FormatException;
import com.google.zxing.client.android.encode.MECARDContactEncoder;
import com.google.zxing.common.StringUtils;
import com.google.zxing.datamatrix.encoder.HighLevelEncoder;
import com.google.zxing.maxicode.decoder.DecodedBitStreamParser;
import com.huawei.secure.android.common.util.LogsUtil;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.list.template.TemplateDom;
import com.taobao.weex.utils.FunctionParser;
import com.taobao.weex.utils.WXUtils;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

/* JADX INFO: loaded from: classes.dex */
public final class ww {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final char[] f9671a = {LogsUtil.b, LogsUtil.b, LogsUtil.b, ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', FunctionParser.Lexer.NINE, FunctionParser.Lexer.A_UPPER, 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', Matrix.MATRIX_TYPE_RANDOM_LT, 'M', 'N', 'O', 'P', 'Q', Matrix.MATRIX_TYPE_RANDOM_REGULAR, 'S', 'T', Matrix.MATRIX_TYPE_RANDOM_UT, 'V', 'W', 'X', 'Y', 'Z'};
    public static final char[] b;
    public static final char[] c;
    public static final char[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final char[] f9672e;

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f9673a;

        static {
            int[] iArr = new int[b.values().length];
            f9673a = iArr;
            try {
                iArr[b.C40_ENCODE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9673a[b.TEXT_ENCODE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9673a[b.ANSIX12_ENCODE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9673a[b.EDIFACT_ENCODE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f9673a[b.BASE256_ENCODE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public enum b {
        PAD_ENCODE,
        ASCII_ENCODE,
        C40_ENCODE,
        TEXT_ENCODE,
        ANSIX12_ENCODE,
        EDIFACT_ENCODE,
        BASE256_ENCODE
    }

    static {
        char[] cArr = {'!', Operators.QUOTE, '#', '$', WXUtils.PERCENT, '&', Operators.SINGLE_QUOTE, Operators.BRACKET_START, Operators.BRACKET_END, LogsUtil.b, FunctionParser.Lexer.PLUS, ',', FunctionParser.Lexer.MINUS, '.', '/', Operators.CONDITION_IF_MIDDLE, MECARDContactEncoder.TERMINATOR, '<', '=', '>', Operators.CONDITION_IF, TemplateDom.SEPARATOR, Operators.ARRAY_START, '\\', Operators.ARRAY_END, '^', '_'};
        b = cArr;
        c = new char[]{LogsUtil.b, LogsUtil.b, LogsUtil.b, ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', FunctionParser.Lexer.NINE, FunctionParser.Lexer.A_LOWER, 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', FunctionParser.Lexer.Z_LOWER};
        d = cArr;
        f9672e = new char[]{'`', FunctionParser.Lexer.A_UPPER, 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', Matrix.MATRIX_TYPE_RANDOM_LT, 'M', 'N', 'O', 'P', 'Q', Matrix.MATRIX_TYPE_RANDOM_REGULAR, 'S', 'T', Matrix.MATRIX_TYPE_RANDOM_UT, 'V', 'W', 'X', 'Y', 'Z', Operators.BLOCK_START, '|', Operators.BLOCK_END, '~', 127};
    }

    public static hw a(byte[] bArr) throws FormatException {
        gw gwVar = new gw(bArr);
        StringBuilder sb = new StringBuilder(100);
        StringBuilder sb2 = new StringBuilder(0);
        ArrayList arrayList = new ArrayList(1);
        b bVarA = b.ASCII_ENCODE;
        do {
            b bVar = b.ASCII_ENCODE;
            if (bVarA == bVar) {
                bVarA = a(gwVar, sb, sb2);
            } else {
                int i = a.f9673a[bVarA.ordinal()];
                if (i == 1) {
                    b(gwVar, sb);
                } else if (i == 2) {
                    d(gwVar, sb);
                } else if (i == 3) {
                    a(gwVar, sb);
                } else if (i == 4) {
                    c(gwVar, sb);
                } else {
                    if (i != 5) {
                        throw FormatException.getFormatInstance();
                    }
                    a(gwVar, sb, arrayList);
                }
                bVarA = bVar;
            }
            if (bVarA == b.PAD_ENCODE) {
                break;
            }
        } while (gwVar.a() > 0);
        if (sb2.length() > 0) {
            sb.append((CharSequence) sb2);
        }
        String string = sb.toString();
        if (arrayList.isEmpty()) {
            arrayList = null;
        }
        return new hw(bArr, string, arrayList, null);
    }

    public static void b(gw gwVar, StringBuilder sb) throws FormatException {
        int iA;
        int[] iArr = new int[3];
        boolean z = false;
        int i = 0;
        while (gwVar.a() != 8 && (iA = gwVar.a(8)) != 254) {
            a(iA, gwVar.a(8), iArr);
            for (int i2 = 0; i2 < 3; i2++) {
                int i3 = iArr[i2];
                if (i != 0) {
                    if (i != 1) {
                        if (i == 2) {
                            char[] cArr = b;
                            if (i3 < cArr.length) {
                                char c2 = cArr[i3];
                                if (z) {
                                    sb.append((char) (c2 + 128));
                                    z = false;
                                } else {
                                    sb.append(c2);
                                }
                            } else if (i3 == 27) {
                                sb.append(DecodedBitStreamParser.GS);
                            } else {
                                if (i3 != 30) {
                                    throw FormatException.getFormatInstance();
                                }
                                z = true;
                            }
                            i = 0;
                        } else {
                            if (i != 3) {
                                throw FormatException.getFormatInstance();
                            }
                            if (z) {
                                sb.append((char) (i3 + 224));
                                z = false;
                                i = 0;
                            } else {
                                sb.append((char) (i3 + 96));
                                i = 0;
                            }
                        }
                    } else if (z) {
                        sb.append((char) (i3 + 128));
                        z = false;
                        i = 0;
                    } else {
                        sb.append((char) i3);
                        i = 0;
                    }
                } else if (i3 < 3) {
                    i = i3 + 1;
                } else {
                    char[] cArr2 = f9671a;
                    if (i3 >= cArr2.length) {
                        throw FormatException.getFormatInstance();
                    }
                    char c3 = cArr2[i3];
                    if (z) {
                        sb.append((char) (c3 + 128));
                        z = false;
                    } else {
                        sb.append(c3);
                    }
                }
            }
            if (gwVar.a() <= 0) {
                return;
            }
        }
    }

    public static void c(gw gwVar, StringBuilder sb) {
        while (gwVar.a() > 16) {
            for (int i = 0; i < 4; i++) {
                int iA = gwVar.a(6);
                if (iA == 31) {
                    int iB = 8 - gwVar.b();
                    if (iB != 8) {
                        gwVar.a(iB);
                        return;
                    }
                    return;
                }
                if ((iA & 32) == 0) {
                    iA |= 64;
                }
                sb.append((char) iA);
            }
            if (gwVar.a() <= 0) {
                return;
            }
        }
    }

    public static void d(gw gwVar, StringBuilder sb) throws FormatException {
        int iA;
        int[] iArr = new int[3];
        boolean z = false;
        int i = 0;
        while (gwVar.a() != 8 && (iA = gwVar.a(8)) != 254) {
            a(iA, gwVar.a(8), iArr);
            for (int i2 = 0; i2 < 3; i2++) {
                int i3 = iArr[i2];
                if (i != 0) {
                    if (i != 1) {
                        if (i == 2) {
                            char[] cArr = d;
                            if (i3 < cArr.length) {
                                char c2 = cArr[i3];
                                if (z) {
                                    sb.append((char) (c2 + 128));
                                    z = false;
                                } else {
                                    sb.append(c2);
                                }
                            } else if (i3 == 27) {
                                sb.append(DecodedBitStreamParser.GS);
                            } else {
                                if (i3 != 30) {
                                    throw FormatException.getFormatInstance();
                                }
                                z = true;
                            }
                            i = 0;
                        } else {
                            if (i != 3) {
                                throw FormatException.getFormatInstance();
                            }
                            char[] cArr2 = f9672e;
                            if (i3 >= cArr2.length) {
                                throw FormatException.getFormatInstance();
                            }
                            char c3 = cArr2[i3];
                            if (z) {
                                sb.append((char) (c3 + 128));
                                z = false;
                                i = 0;
                            } else {
                                sb.append(c3);
                                i = 0;
                            }
                        }
                    } else if (z) {
                        sb.append((char) (i3 + 128));
                        z = false;
                        i = 0;
                    } else {
                        sb.append((char) i3);
                        i = 0;
                    }
                } else if (i3 < 3) {
                    i = i3 + 1;
                } else {
                    char[] cArr3 = c;
                    if (i3 >= cArr3.length) {
                        throw FormatException.getFormatInstance();
                    }
                    char c4 = cArr3[i3];
                    if (z) {
                        sb.append((char) (c4 + 128));
                        z = false;
                    } else {
                        sb.append(c4);
                    }
                }
            }
            if (gwVar.a() <= 0) {
                return;
            }
        }
    }

    public static b a(gw gwVar, StringBuilder sb, StringBuilder sb2) throws FormatException {
        boolean z = false;
        do {
            int iA = gwVar.a(8);
            if (iA == 0) {
                throw FormatException.getFormatInstance();
            }
            if (iA <= 128) {
                if (z) {
                    iA += 128;
                }
                sb.append((char) (iA - 1));
                return b.ASCII_ENCODE;
            }
            if (iA == 129) {
                return b.PAD_ENCODE;
            }
            if (iA <= 229) {
                int i = iA - 130;
                if (i < 10) {
                    sb.append('0');
                }
                sb.append(i);
            } else {
                if (iA == 230) {
                    return b.C40_ENCODE;
                }
                if (iA == 231) {
                    return b.BASE256_ENCODE;
                }
                if (iA == 232) {
                    sb.append(DecodedBitStreamParser.GS);
                } else if (iA != 233 && iA != 234) {
                    if (iA == 235) {
                        z = true;
                    } else if (iA == 236) {
                        sb.append(HighLevelEncoder.MACRO_05_HEADER);
                        sb2.insert(0, HighLevelEncoder.MACRO_TRAILER);
                    } else if (iA == 237) {
                        sb.append(HighLevelEncoder.MACRO_06_HEADER);
                        sb2.insert(0, HighLevelEncoder.MACRO_TRAILER);
                    } else {
                        if (iA == 238) {
                            return b.ANSIX12_ENCODE;
                        }
                        if (iA == 239) {
                            return b.TEXT_ENCODE;
                        }
                        if (iA == 240) {
                            return b.EDIFACT_ENCODE;
                        }
                        if (iA != 241 && iA >= 242 && (iA != 254 || gwVar.a() != 0)) {
                            throw FormatException.getFormatInstance();
                        }
                    }
                }
            }
        } while (gwVar.a() > 0);
        return b.ASCII_ENCODE;
    }

    public static void a(gw gwVar, StringBuilder sb, Collection<byte[]> collection) throws FormatException {
        int iC = gwVar.c() + 1;
        int i = iC + 1;
        int iA = a(gwVar.a(8), iC);
        if (iA == 0) {
            iA = gwVar.a() / 8;
        } else if (iA >= 250) {
            iA = ((iA - 249) * 250) + a(gwVar.a(8), i);
            i++;
        }
        if (iA >= 0) {
            byte[] bArr = new byte[iA];
            int i2 = 0;
            while (i2 < iA) {
                if (gwVar.a() >= 8) {
                    bArr[i2] = (byte) a(gwVar.a(8), i);
                    i2++;
                    i++;
                } else {
                    throw FormatException.getFormatInstance();
                }
            }
            collection.add(bArr);
            try {
                sb.append(new String(bArr, StringUtils.ISO88591));
                return;
            } catch (UnsupportedEncodingException e2) {
                throw new IllegalStateException("Platform does not support required encoding: " + e2);
            }
        }
        throw FormatException.getFormatInstance();
    }

    public static void a(int i, int i2, int[] iArr) {
        int i3 = ((i << 8) + i2) - 1;
        int i4 = i3 / 1600;
        iArr[0] = i4;
        int i5 = i3 - (i4 * 1600);
        int i6 = i5 / 40;
        iArr[1] = i6;
        iArr[2] = i5 - (i6 * 40);
    }

    public static int a(int i, int i2) {
        int i3 = i - (((i2 * 149) % 255) + 1);
        return i3 >= 0 ? i3 : i3 + 256;
    }

    public static void a(gw gwVar, StringBuilder sb) throws FormatException {
        int iA;
        int[] iArr = new int[3];
        while (gwVar.a() != 8 && (iA = gwVar.a(8)) != 254) {
            a(iA, gwVar.a(8), iArr);
            for (int i = 0; i < 3; i++) {
                int i2 = iArr[i];
                if (i2 == 0) {
                    sb.append('\r');
                } else if (i2 == 1) {
                    sb.append(LogsUtil.b);
                } else if (i2 == 2) {
                    sb.append('>');
                } else if (i2 == 3) {
                    sb.append(' ');
                } else if (i2 < 14) {
                    sb.append((char) (i2 + 44));
                } else if (i2 < 40) {
                    sb.append((char) (i2 + 51));
                } else {
                    throw FormatException.getFormatInstance();
                }
            }
            if (gwVar.a() <= 0) {
                return;
            }
        }
    }
}
