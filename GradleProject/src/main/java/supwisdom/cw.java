package supwisdom;

import com.baidu.speech.utils.cuid.util.DeviceId;
import com.dcloud.zxing2.FormatException;
import com.dcloud.zxing2.common.reedsolomon.ReedSolomonException;
import com.huawei.hms.framework.common.ContainerUtils;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.list.template.CellDataManager;
import com.tencent.qphone.base.util.QLog;
import io.dcloud.common.util.Base64;
import io.dcloud.common.util.JSUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class cw {
    public static final String[] b = {"CTRL_PS", Operators.SPACE_STR, "A", "B", "C", QLog.TAG_REPORTLEVEL_DEVELOPER, QLog.TAG_REPORTLEVEL_USER, "F", "G", "H", "I", "J", "K", "L", "M", "N", DeviceId.CUIDInfo.I_FIXED, "P", "Q", "R", "S", "T", "U", "V", QLog.TAG_REPORTLEVEL_COLORUSER, "X", "Y", "Z", "CTRL_LL", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    public static final String[] c = {"CTRL_PS", Operators.SPACE_STR, "a", "b", "c", cn.com.chinatelecom.account.api.a.d.f1473a, "e", cn.com.chinatelecom.account.api.e.f.f1517a, com.loc.z.f, "h", "i", com.loc.z.j, "k", cn.com.chinatelecom.account.api.e.l.f1524a, WXComponent.PROP_FS_MATCH_PARENT, "n", "o", "p", "q", "r", "s", "t", com.umeng.analytics.pro.bm.aL, "v", "w", Constants.Name.X, Constants.Name.Y, com.umeng.analytics.pro.bm.aH, "CTRL_US", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    public static final String[] d = {"CTRL_PS", Operators.SPACE_STR, "\u0001", com.xiaomi.push.i.f, "\u0003", "\u0004", "\u0005", "\u0006", "\u0007", "\b", "\t", "\n", "\u000b", "\f", "\r", "\u001b", "\u001c", "\u001d", "\u001e", "\u001f", CellDataManager.VIRTUAL_COMPONENT_SEPRATOR, "\\", "^", "_", "`", "|", com.xiaomi.mipush.sdk.Constants.WAVE_SEPARATOR, "\u007f", "CTRL_LL", "CTRL_UL", "CTRL_PL", "CTRL_BS"};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String[] f7264e = {"", "\r", Base64.CRLF, ". ", ", ", ": ", Operators.AND_NOT, JSUtil.QUOTE, "#", Operators.DOLLAR_STR, "%", "&", "'", "(", ")", Operators.MUL, Operators.PLUS, ",", "-", Operators.DOT_STR, "/", com.xiaomi.mipush.sdk.Constants.COLON_SEPARATOR, ";", Operators.L, ContainerUtils.KEY_VALUE_DELIMITER, Operators.G, Operators.CONDITION_IF_STRING, Operators.ARRAY_START_STR, Operators.ARRAY_END_STR, Operators.BLOCK_START_STR, Operators.BLOCK_END_STR, "CTRL_UL"};
    public static final String[] f = {"CTRL_PS", Operators.SPACE_STR, "0", "1", "2", "3", "4", "5", com.tencent.connect.common.Constants.VIA_SHARE_TYPE_INFO, "7", com.tencent.connect.common.Constants.VIA_SHARE_TYPE_PUBLISHVIDEO, com.tencent.connect.common.Constants.VIA_SHARE_TYPE_MINI_PROGRAM, ",", Operators.DOT_STR, "CTRL_UL", "CTRL_US"};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public aw f7265a;

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f7266a;

        static {
            int[] iArr = new int[b.values().length];
            f7266a = iArr;
            try {
                iArr[b.UPPER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7266a[b.LOWER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7266a[b.MIXED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7266a[b.PUNCT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f7266a[b.DIGIT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public enum b {
        UPPER,
        LOWER,
        MIXED,
        DIGIT,
        PUNCT,
        BINARY
    }

    public static int a(int i, boolean z) {
        return ((z ? 88 : 112) + (i * 16)) * i;
    }

    public static String b(boolean[] zArr) {
        int length = zArr.length;
        b bVar = b.UPPER;
        StringBuilder sb = new StringBuilder(20);
        b bVar2 = bVar;
        int i = 0;
        while (i < length) {
            if (bVar != b.BINARY) {
                int i2 = bVar == b.DIGIT ? 4 : 5;
                if (length - i < i2) {
                    break;
                }
                int iA = a(zArr, i, i2);
                i += i2;
                String strA = a(bVar, iA);
                if (strA.startsWith("CTRL_")) {
                    b bVarA = a(strA.charAt(5));
                    if (strA.charAt(6) == 'L') {
                        bVar = bVarA;
                        bVar2 = bVar;
                    } else {
                        bVar = bVarA;
                    }
                } else {
                    sb.append(strA);
                    bVar = bVar2;
                }
            } else {
                if (length - i < 5) {
                    break;
                }
                int iA2 = a(zArr, i, 5);
                i += 5;
                if (iA2 == 0) {
                    if (length - i < 11) {
                        break;
                    }
                    iA2 = a(zArr, i, 11) + 31;
                    i += 11;
                }
                int i3 = 0;
                while (true) {
                    if (i3 >= iA2) {
                        break;
                    }
                    if (length - i < 8) {
                        i = length;
                        break;
                    }
                    sb.append((char) a(zArr, i, 8));
                    i += 8;
                    i3++;
                }
                bVar = bVar2;
            }
        }
        return sb.toString();
    }

    public final boolean[] a(boolean[] zArr) throws FormatException {
        qw qwVar;
        int i = 8;
        if (this.f7265a.d() <= 2) {
            i = 6;
            qwVar = qw.j;
        } else if (this.f7265a.d() <= 8) {
            qwVar = qw.n;
        } else if (this.f7265a.d() <= 22) {
            i = 10;
            qwVar = qw.i;
        } else {
            i = 12;
            qwVar = qw.h;
        }
        int iC = this.f7265a.c();
        int length = zArr.length / i;
        if (length < iC) {
            throw FormatException.getFormatInstance();
        }
        int length2 = zArr.length % i;
        int i2 = length - iC;
        int[] iArr = new int[length];
        int i3 = 0;
        while (i3 < length) {
            iArr[i3] = a(zArr, length2, i);
            i3++;
            length2 += i;
        }
        try {
            new sw(qwVar).a(iArr, i2);
            int i4 = (1 << i) - 1;
            int i5 = 0;
            for (int i6 = 0; i6 < iC; i6++) {
                int i7 = iArr[i6];
                if (i7 == 0 || i7 == i4) {
                    throw FormatException.getFormatInstance();
                }
                if (i7 == 1 || i7 == i4 - 1) {
                    i5++;
                }
            }
            boolean[] zArr2 = new boolean[(iC * i) - i5];
            int i8 = 0;
            for (int i9 = 0; i9 < iC; i9++) {
                int i10 = iArr[i9];
                if (i10 == 1 || i10 == i4 - 1) {
                    Arrays.fill(zArr2, i8, (i8 + i) - 1, i10 > 1);
                    i8 += i - 1;
                } else {
                    int i11 = i - 1;
                    while (i11 >= 0) {
                        int i12 = i8 + 1;
                        zArr2[i8] = ((1 << i11) & i10) != 0;
                        i11--;
                        i8 = i12;
                    }
                }
            }
            return zArr2;
        } catch (ReedSolomonException e2) {
            throw FormatException.getFormatInstance(e2);
        }
    }

    public hw a(aw awVar) throws FormatException {
        this.f7265a = awVar;
        return new hw(null, b(a(a(awVar.a()))), null, null);
    }

    public boolean[] a(fw fwVar) {
        boolean zE = this.f7265a.e();
        int iD = this.f7265a.d();
        int i = iD * 4;
        int i2 = zE ? i + 11 : i + 14;
        int[] iArr = new int[i2];
        boolean[] zArr = new boolean[a(iD, zE)];
        int i3 = 2;
        if (zE) {
            for (int i4 = 0; i4 < i2; i4++) {
                iArr[i4] = i4;
            }
        } else {
            int i5 = i2 / 2;
            int i6 = ((i2 + 1) + (((i5 - 1) / 15) * 2)) / 2;
            for (int i7 = 0; i7 < i5; i7++) {
                iArr[(i5 - i7) - 1] = (i6 - r12) - 1;
                iArr[i5 + i7] = (i7 / 15) + i7 + i6 + 1;
            }
        }
        int i8 = 0;
        int i9 = 0;
        while (i8 < iD) {
            int i10 = (iD - i8) * 4;
            int i11 = zE ? i10 + 9 : i10 + 12;
            int i12 = i8 * 2;
            int i13 = (i2 - 1) - i12;
            int i14 = 0;
            while (i14 < i11) {
                int i15 = i14 * 2;
                int i16 = 0;
                while (i16 < i3) {
                    int i17 = i12 + i16;
                    int i18 = i12 + i14;
                    zArr[i9 + i15 + i16] = fwVar.b(iArr[i17], iArr[i18]);
                    int i19 = iArr[i18];
                    int i20 = i13 - i16;
                    zArr[(i11 * 2) + i9 + i15 + i16] = fwVar.b(i19, iArr[i20]);
                    int i21 = i13 - i14;
                    zArr[(i11 * 4) + i9 + i15 + i16] = fwVar.b(iArr[i20], iArr[i21]);
                    zArr[(i11 * 6) + i9 + i15 + i16] = fwVar.b(iArr[i21], iArr[i17]);
                    i16++;
                    iD = iD;
                    zE = zE;
                    i3 = 2;
                }
                i14++;
                i3 = 2;
            }
            i9 += i11 * 8;
            i8++;
            i3 = 2;
        }
        return zArr;
    }

    public static String a(b bVar, int i) {
        int i2 = a.f7266a[bVar.ordinal()];
        if (i2 == 1) {
            return b[i];
        }
        if (i2 == 2) {
            return c[i];
        }
        if (i2 == 3) {
            return d[i];
        }
        if (i2 == 4) {
            return f7264e[i];
        }
        if (i2 == 5) {
            return f[i];
        }
        throw new IllegalStateException("Bad table");
    }

    public static b a(char c2) {
        if (c2 == 'B') {
            return b.BINARY;
        }
        if (c2 == 'D') {
            return b.DIGIT;
        }
        if (c2 == 'P') {
            return b.PUNCT;
        }
        if (c2 == 'L') {
            return b.LOWER;
        }
        if (c2 != 'M') {
            return b.UPPER;
        }
        return b.MIXED;
    }

    public static int a(boolean[] zArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = i; i4 < i + i2; i4++) {
            i3 <<= 1;
            if (zArr[i4]) {
                i3 |= 1;
            }
        }
        return i3;
    }
}
